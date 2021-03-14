package com.persistent.connectors.tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import com.persistent.connectors.MySqlSinkConnector;
import com.persistent.connectors.jdbc.MysqlConnectionService;
import com.persistent.connectors.model.Student;
import com.persistent.connectors.service.ConnectorServiceFactoryImpl;

public class MySqlSinkTask extends SinkTask {

	private MysqlConnectionService mysqlConnectionService;
	private Gson gson = new Gson();
	private static final Logger LOGGER = LoggerFactory.getLogger(MySqlSinkTask.class);

	@Override
	public String version() {
		return MySqlSinkConnector.CONNECTOR_VERSION;
	}

	@Override
	public void start(Map<String, String> connectorConfigProperties) {
		LOGGER.info("@@@@ Starting the sink task  with connectorConfigProperties :" + connectorConfigProperties);
		this.mysqlConnectionService = ConnectorServiceFactoryImpl.INSTANCE.getService(MysqlConnectionService.class);
	}

	@Override
	public void put(Collection<SinkRecord> sinkRecords) {
		LOGGER.info("@@@@ Starting the put(Collection<SinkRecord> sinkRecords) :" + sinkRecords);
		final List<String> records = sinkRecords.stream().map(record -> String.valueOf(record.value()))
				.collect(Collectors.toList());
		LOGGER.info("Records size :" + records.size());
		if (!records.isEmpty()) {
			try (final Connection jdbcConnection = mysqlConnectionService.getConnection();
					final PreparedStatement preparedStatement = jdbcConnection
							.prepareStatement("INSERT INTO STUDENT(STUDENT_ID,STUDENT_NAME,AGE) VALUES(?,?,?)");) {
				records.forEach(record -> setPreparedStatementValues(preparedStatement, record));
				preparedStatement.executeBatch();
			} catch (Exception e) {
				LOGGER.info("@@@@ Error when calling addBatch() :" + e.getMessage());
			}
		}
	}

	private void setPreparedStatementValues(PreparedStatement preparedStatement, String studentRecord) {
		LOGGER.info("@@@@ Record :" + studentRecord);
		final Student student = this.gson.fromJson(studentRecord, Student.class);
		LOGGER.info("@@@@ Student :" + student);
		try {
			preparedStatement.setLong(1, student.getStudentId());
			preparedStatement.setString(2, student.getStudentName());
			preparedStatement.setInt(3, student.getAge());
			preparedStatement.addBatch();
		} catch (Exception e) {
			LOGGER.info("@@@@ Error when calling addBatch() :" + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void stop() {

	}

}

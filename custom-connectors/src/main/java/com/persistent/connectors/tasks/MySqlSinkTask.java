package com.persistent.connectors.tasks;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.kafka.connect.jdbc.MysqlConnectionService;
import org.apache.kafka.connect.service.ConnectorServiceFactoryImpl;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.persistent.connectors.MySqlSinkConnector;

public class MySqlSinkTask extends SinkTask {

	private Connection jdbcConnection;
	private static final Logger LOGGER = LoggerFactory.getLogger(MySqlSinkTask.class);

	@Override
	public String version() {
		return MySqlSinkConnector.CONNECTOR_VERSION;
	}

	@Override
	public void start(Map<String, String> connectorConfigProperties) {
		LOGGER.info("@@@@ Starting the sink task  with connectorConfigProperties :" + connectorConfigProperties);
		MysqlConnectionService mysqlConnectionService = ConnectorServiceFactoryImpl.INSTANCE
				.getService(MysqlConnectionService.class);
		this.jdbcConnection = mysqlConnectionService.getConnection();
	}

	@Override
	public void put(Collection<SinkRecord> sinkRecords) {
		LOGGER.info("@@@@ Starting the put(Collection<SinkRecord> sinkRecords) :" + sinkRecords);
		final List<String> records = sinkRecords.stream().map(record -> String.valueOf(record.value()))
				.collect(Collectors.toList());
		records.forEach(record -> {

		});
	}

	@Override
	public void stop() {
		LOGGER.info("@@@@ Starting the stop() :");
		if (this.jdbcConnection != null) {
			try {
				this.jdbcConnection.close();
			} catch (Exception e) {
				LOGGER.info("@@@@ Error when closing the connection :"+e.getMessage());
			}
		}
	}

}

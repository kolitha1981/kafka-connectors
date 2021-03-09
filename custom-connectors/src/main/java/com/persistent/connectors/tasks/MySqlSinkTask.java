package com.persistent.connectors.tasks;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

public class MySqlSinkTask extends SinkTask {

	@Override
	public String version() {
		return null;
	}

	@Override
	public void start(Map<String, String> connectorConfigProperties) {

	}

	@Override
	public void put(Collection<SinkRecord> sinkRecords) {
		final List<String> records = sinkRecords.stream().map(record -> String.valueOf(record.value()))
				.collect(Collectors.toList());
		records.forEach(record -> {

		});
	}

	@Override
	public void stop() {

	}

}

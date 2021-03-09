package com.persistent.connectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.persistent.connectors.config.MysqlConnectorConfig;
import com.persistent.connectors.tasks.MySqlSinkTask;

public class MySqlSinkConnector extends SinkConnector {

	private Map<String, String> connectorConfigProperties;
	private static final Logger LOGGER = LoggerFactory.getLogger(MySqlSinkConnector.class);

	public String version() {
		return null;
	}

	@Override
	public void start(Map<String, String> connectorConfigProperties) {
		LOGGER.info("@@@@@@@@@@ connectorConfigProperties" + connectorConfigProperties);
		connectorConfigProperties.forEach((key, value) -> {
			LOGGER.info("@@@@@@@@@@ Key: " + key);
			LOGGER.info("@@@@@@@@@@ Value: " + value);
		});
		try {
			this.connectorConfigProperties = connectorConfigProperties;
			new MysqlConnectorConfig(connectorConfigProperties);
		} catch (Exception e) {
			LOGGER.warn("@@@@@@ Error whane starting the connector: " + e.getMessage());
		}
	}

	@Override
	public Class<? extends Task> taskClass() {
		return MySqlSinkTask.class;
	}

	@Override
	public List<Map<String, String>> taskConfigs(int maxTasks) {
		final List<Map<String, String>> taskConfigs = new ArrayList<>();
		final Map<String, String> taskProps = new HashMap<>();
		taskProps.putAll(this.connectorConfigProperties);
		for (int i = 0; i < maxTasks; i++) {
			taskConfigs.add(taskProps);
		}
		return taskConfigs;
	}

	@Override
	public void stop() {

	}

	@Override
	public ConfigDef config() {
		return null;
	}

}

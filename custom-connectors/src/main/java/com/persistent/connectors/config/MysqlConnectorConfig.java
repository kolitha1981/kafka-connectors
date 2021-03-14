package com.persistent.connectors.config;

import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.connect.jdbc.MySqlDataSourceConfig;
import org.apache.kafka.connect.jdbc.MysqlConnectionService;
import org.apache.kafka.connect.service.ConnectorServiceFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.persistent.connectors.constants.MysqlConnectorConstants;

public class MysqlConnectorConfig extends AbstractConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(MysqlConnectorConfig.class);

	public MysqlConnectorConfig(ConfigDef definition, Map<?, ?> connectorConfigProperties) {
		super(definition, connectorConfigProperties);
		LOGGER.info("@@@@ Constructor MysqlConnectorConfig(ConfigDef definition, Map<?, ?> connectorConfigProperties)");
		final String userName = String.valueOf(connectorConfigProperties.get(MysqlConnectorConstants.MYSQL_USERNAME));
		LOGGER.info("@@@@ Username:"+ userName);
		final String password = String.valueOf(connectorConfigProperties.get(MysqlConnectorConstants.MYSQL_PASSWORD));
		LOGGER.info("@@@@ Password:"+ password);
		final String serverHostName = String
				.valueOf(connectorConfigProperties.get(MysqlConnectorConstants.MYSQL_SERVER_NAME_DOC));
		LOGGER.info("@@@@ Server host:"+ serverHostName);
		final String serverPort = String
				.valueOf(connectorConfigProperties.get(MysqlConnectorConstants.MYSQL_SERVER_PORT));
		LOGGER.info("@@@@ Server port:"+ serverPort);
		final String databaseName = String
				.valueOf(connectorConfigProperties.get(MysqlConnectorConstants.MYSQL_SERVER_DATABASE_NAME));
		LOGGER.info("@@@@ DatabaseName:"+ databaseName);
		final MySqlDataSourceConfig mySqlDataSourceConfig = MySqlDataSourceConfig.createConfig(serverHostName, serverPort,
				userName, password, databaseName);
		ConnectorServiceFactoryImpl.INSTANCE.register(new MysqlConnectionService(mySqlDataSourceConfig));
	}

	public MysqlConnectorConfig(Map<?, ?> connectorConfigProperties) {
		super(configurationdefinition(), connectorConfigProperties);
	}

	public static ConfigDef configurationdefinition() {
		LOGGER.info("@@@@ ConfigDef configurationdefinition()");
		return new ConfigDef()
				.define(MysqlConnectorConstants.MYSQL_USERNAME, Type.STRING, Importance.HIGH,
						MysqlConnectorConstants.MYSQL_USERNAME_DOC)
				.define(MysqlConnectorConstants.MYSQL_PASSWORD, Type.STRING, Importance.HIGH,
						MysqlConnectorConstants.MYSQL_PASSWORD_DOC)
				.define(MysqlConnectorConstants.MYSQL_SERVER_NAME, Type.STRING, Importance.HIGH,
						MysqlConnectorConstants.MYSQL_SERVER_NAME_DOC)
				.define(MysqlConnectorConstants.MYSQL_SERVER_DATABASE_NAME, Type.STRING, Importance.HIGH,
						MysqlConnectorConstants.MYSQL_SERVER_DATABASE_NAME_DOC)
				.define(MysqlConnectorConstants.MYSQL_SERVER_PORT, Type.STRING, Importance.HIGH,
						MysqlConnectorConstants.MYSQL_SERVER_PORT)
				.define(MysqlConnectorConstants.KAFKA_MYSQL_TABLE_VALUE_MAPPINGS, Type.LIST, Importance.HIGH,
						MysqlConnectorConstants.KAFKA_MYSQL_TABLE_VALUE_MAPPINGS_DOC);
	}

	public String getMySqlUserName() {
		return this.getString(MysqlConnectorConstants.MYSQL_USERNAME);
	}

	public String getMySqlPassword() {
		return this.getString(MysqlConnectorConstants.MYSQL_PASSWORD);
	}

	public String getMySqlDatabaseName() {
		return this.getString(MysqlConnectorConstants.MYSQL_SERVER_DATABASE_NAME);
	}

	public String getMySqlHostName() {
		return this.getString(MysqlConnectorConstants.MYSQL_SERVER_NAME);
	}

	public String getMySqlServerPort() {
		return this.getString(MysqlConnectorConstants.MYSQL_SERVER_PORT);
	}

	public List<String> getMySqlValueMappings() {
		return this.getList(MysqlConnectorConstants.KAFKA_MYSQL_TABLE_VALUE_MAPPINGS);
	}

}

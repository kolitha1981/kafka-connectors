package com.persistent.connectors.config;

import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

import com.persistent.connectors.constants.MysqlConnectorConstants;

public class MysqlConnectorConfig extends AbstractConfig {

	public MysqlConnectorConfig(ConfigDef definition, Map<?, ?> connectorConfigProperties) {
		super(definition, connectorConfigProperties);
	}
	
	public MysqlConnectorConfig(Map<?, ?> connectorConfigProperties) {
		super(configurationdefinition(), connectorConfigProperties);
	}

	public static ConfigDef configurationdefinition() {
		return new ConfigDef()
				.define(MysqlConnectorConstants.MYSQL_USERNAME, Type.STRING, Importance.HIGH,
						MysqlConnectorConstants.MYSQL_USERNAME_DOC)
				.define(MysqlConnectorConstants.MYSQL_PASSWORD, Type.STRING, Importance.HIGH,
						MysqlConnectorConstants.MYSQL_PASSWORD_DOC)
				.define(MysqlConnectorConstants.KAFKA_MYSQL_TABLE_VALUE_MAPPINGS, Type.LIST, Importance.HIGH,
						MysqlConnectorConstants.KAFKA_MYSQL_TABLE_VALUE_MAPPINGS_DOC);
	}
	
	public String getMySqlUserName() {
		return this.getString(MysqlConnectorConstants.MYSQL_USERNAME);
	}
	
	public String getMySqlPassword() {
		return this.getString(MysqlConnectorConstants.MYSQL_PASSWORD);
	}
	
	public List<String> getMySqlValueMappings() {
		return this.getList(MysqlConnectorConstants.KAFKA_MYSQL_TABLE_VALUE_MAPPINGS);
	}

}

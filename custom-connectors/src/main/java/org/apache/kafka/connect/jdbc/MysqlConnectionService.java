package org.apache.kafka.connect.jdbc;

import java.sql.Connection;

import com.persistent.connectors.exception.MysqlDataSourceException;
import com.zaxxer.hikari.HikariDataSource;

public class MysqlConnectionService {

	private HikariDataSource hikariDataSource;

	public MysqlConnectionService(MySqlDataSourceConfig mySqlDataSourceConfig) {
		this.hikariDataSource = mySqlDataSourceConfig.getDataSource();
	}

	public Connection getConnection() {
		try {
			return this.hikariDataSource.getConnection();
		} catch (Exception e) {
			throw new MysqlDataSourceException("@@@@@@ Error when retrieving connection: " + e.getMessage());
		}
	}

}

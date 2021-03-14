package com.persistent.connectors.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MySqlDataSourceConfig {

	private HikariDataSource hikariDataSource;

	private MySqlDataSourceConfig(HikariDataSource hikariDataSource) {
		this.hikariDataSource = hikariDataSource;
	}

	public HikariDataSource getDataSource() {
		return hikariDataSource;
	}

	public static MySqlDataSourceConfig createConfig
	     (String hostName, String port, String userName, String password, String dbName) {
		HikariConfig hikariConfiguration = new HikariConfig();
		final StringBuilder urlBuilder =  new StringBuilder("jdbc:mysql://")
				.append(hostName).append(":").append(port).append("/").append(dbName);
		hikariConfiguration.setJdbcUrl(urlBuilder.toString());
		hikariConfiguration.setDriverClassName("com.mysql.jdbc.Driver");
		hikariConfiguration.setUsername(userName);
		hikariConfiguration.setPassword(password);
		hikariConfiguration.addDataSourceProperty("maximumPoolSize", "10");
		hikariConfiguration.addDataSourceProperty("minimumIdle", "2");
		hikariConfiguration.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfiguration.addDataSourceProperty("useServerPrepStmts", "true");
		hikariConfiguration.addDataSourceProperty("prepStmtCacheSize", "250");
		hikariConfiguration.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		return new MySqlDataSourceConfig(new HikariDataSource(hikariConfiguration));
	}

}

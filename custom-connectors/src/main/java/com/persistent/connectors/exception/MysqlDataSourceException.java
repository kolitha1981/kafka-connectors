package com.persistent.connectors.exception;

public class MysqlDataSourceException extends RuntimeException {

	public MysqlDataSourceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MysqlDataSourceException(String arg0) {
		super(arg0);
	}	

}

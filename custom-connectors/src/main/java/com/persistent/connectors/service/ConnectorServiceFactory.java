package com.persistent.connectors.service;

public interface ConnectorServiceFactory {	
	
	<T extends Object> T getService(Class<T> serviceinstanceClazz);
	
	<T extends Object> void register(T serviceInstance);
}

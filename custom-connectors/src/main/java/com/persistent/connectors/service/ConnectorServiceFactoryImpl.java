package com.persistent.connectors.service;

import java.util.HashMap;
import java.util.Map;

public class ConnectorServiceFactoryImpl implements ConnectorServiceFactory {

	private Map<Class<?>, Object> serviceMap = new HashMap<>();

	public static final ConnectorServiceFactory INSTANCE = new ConnectorServiceFactoryImpl();

	private ConnectorServiceFactoryImpl() {}

	@Override
	public <T> T getService(Class<T> serviceinstanceClazz) {
		return (T) serviceMap.get(serviceinstanceClazz);
	}

	@Override
	public <T> void register(T serviceInstance) {
		if (serviceMap.get(serviceInstance.getClass()) == null) {
			serviceMap.put(serviceInstance.getClass(), serviceInstance);
		}
	}

}

/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.fabrica.distribution.provider.grpc.client;

import com.liferay.fabrica.core.distribution.client.Client;
import com.liferay.fabrica.distribution.provider.grpc.method.MethodCallInvocationHandler;

import io.grpc.ManagedChannel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Miguel Pastor
 */
public class ProxyClientBuilder {

	public ProxyClientBuilder(
		String serverName, ManagedChannel managedChannel) {

		_serverName = serverName;
		_managedChannel = managedChannel;
	}

	public Client build(ClassLoader classLoader, Class<?>[] classes) {
		InvocationHandler invocationHandler = new MethodCallInvocationHandler(
			_serverName, _managedChannel);

		List<Class<?>> classesList = Arrays.asList(classes);

		classesList.add(Client.class);

		Object clientProxy = Proxy.newProxyInstance(
			classLoader, classesList.toArray(new Class<?>[classesList.size()]),
			invocationHandler);

		return new ClientImpl(clientProxy, _managedChannel);
	}

	private final ManagedChannel _managedChannel;
	private final String _serverName;

	private static final class ClientImpl implements Client {

		public ClientImpl(Object client, ManagedChannel managedChannel) {
			_client = client;
			_managedChannel = managedChannel;
		}

		@Override
		public Object getClient() {
			return _client;
		}

		@Override
		public void shutdown() throws InterruptedException {
			_managedChannel.awaitTermination(5, TimeUnit.SECONDS);
		}

		private final Object _client;
		private final ManagedChannel _managedChannel;

	}

}
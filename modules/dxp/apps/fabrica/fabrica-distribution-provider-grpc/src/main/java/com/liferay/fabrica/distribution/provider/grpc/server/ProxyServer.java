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

package com.liferay.fabrica.distribution.provider.grpc.server;

import com.liferay.fabrica.distribution.provider.grpc.GrpcDistributionProvider;
import com.liferay.fabrica.distribution.provider.grpc.io.ServiceRequest;
import com.liferay.fabrica.distribution.provider.grpc.io.marshaller.RequestMarshaller;
import com.liferay.fabrica.distribution.provider.grpc.io.marshaller.ResponseMarshaller;
import com.liferay.fabrica.distribution.provider.grpc.method.MethodInvocation;
import com.liferay.fabrica.distribution.provider.grpc.reflection.ReflectionUtil;

import io.grpc.MethodDescriptor;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.ServerCalls;

import java.io.IOException;

import java.lang.reflect.Method;

import java.net.InetSocketAddress;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osgi.service.remoteserviceadmin.RemoteConstants;

/**
 * @author Miguel Pastor
 */
public class ProxyServer
	implements com.liferay.fabrica.core.distribution.server.Server {

	public static Service name(String name) {
		return new Builder(name);
	}

	@Override
	public InetSocketAddress getInetSocketAddress() {
		return _inetSocketAddress;
	}

	@Override
	public Map<String, ?> getInfo() {
		return _properties;
	}

	@Override
	public void start() throws IOException {
		_server.start();
	}

	@Override
	public void stop() throws InterruptedException {
		_server.awaitTermination();
	}

	public interface Address {

		public Build address(InetSocketAddress inetSocketAddress);

	}

	public interface Build {

		public ProxyServer build();

		public Build executor(ExecutorService executorService);

		public Build properties(Map<String, Object> properties);

	}

	public interface Contract {

		public Address contract(Class<?>... clazz);

	}

	public interface Service {

		public Contract service(Object service);

	}

	private final Collection<Class<?>> _classes = new HashSet<>();
	private ExecutorService _executorService;
	private InetSocketAddress _inetSocketAddress;
	private String _name;
	private final Map<String, Object> _properties = new HashMap();
	private Server _server;
	private Object _service;

	private static class Builder implements Address, Build, Contract, Service {

		public Builder(String name) {
			_proxyServer._name = name;

			_proxyServer._properties.put(
				GrpcDistributionProvider.GRPC_SERVER_NAME, name);
		}

		@Override
		public Build address(InetSocketAddress inetSocketAddress) {
			_proxyServer._inetSocketAddress = inetSocketAddress;

			_proxyServer._properties.put(
				GrpcDistributionProvider.GRPC_ENDPOINT_HOST,
				inetSocketAddress.getHostName());

			_proxyServer._properties.put(
				GrpcDistributionProvider.GRPC_ENDPOINT_PORT,
				inetSocketAddress.getPort());

			return this;
		}

		@Override
		public ProxyServer build() {
			_proxyServer._server = _registerServer();

			_proxyServer._properties.put(
				RemoteConstants.SERVICE_EXPORTED_CONFIGS,
				GrpcDistributionProvider.GRPC_SERVER_CONFIG);

			return _proxyServer;
		}

		@Override
		public Address contract(Class<?>... classes) {
			Collections.addAll(_proxyServer._classes, classes);

			return this;
		}

		@Override
		public Build executor(ExecutorService executorService) {
			_proxyServer._executorService = executorService;

			return this;
		}

		@Override
		public Build properties(Map<String, Object> properties) {
			_proxyServer._properties.putAll(properties);

			return this;
		}

		@Override
		public Contract service(Object service) {
			_proxyServer._service = service;

			return this;
		}

		private Server _registerServer() {
			InetSocketAddress inetSocketAddress =
				_proxyServer._inetSocketAddress;

			ServerBuilder<?> serverBuilder = ServerBuilder.forPort(
				inetSocketAddress.getPort());

			ServerServiceDefinition.Builder builder =
				ServerServiceDefinition.builder(_proxyServer._name);

			for (Class<?> clazz : _proxyServer._classes) {
				for (Method method :
						ReflectionUtil.getInterfaceMethods(clazz)) {

					String methodName =
						_proxyServer._name + "/" + method.getName();

					MethodDescriptor<ServiceRequest, Object> methodDescriptor =
						MethodDescriptor.create(
							MethodDescriptor.MethodType.UNARY, methodName,
							new RequestMarshaller(), new ResponseMarshaller());

					builder.addMethod(
						methodDescriptor,
						ServerCalls.asyncUnaryCall(
							new MethodInvocation(
								_proxyServer._service, method)));
				}
			}

			ExecutorService executorService = _proxyServer._executorService;

			if (executorService == null) {
				executorService = Executors.newFixedThreadPool(5);
			}

			ServerBuilder<?> serverBuilder1 = serverBuilder.executor(
				executorService);

			return serverBuilder1.build();
		}

		private final ProxyServer _proxyServer = new ProxyServer();

	}

}
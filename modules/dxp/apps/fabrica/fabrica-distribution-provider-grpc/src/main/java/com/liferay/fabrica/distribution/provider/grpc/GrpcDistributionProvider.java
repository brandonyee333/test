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

package com.liferay.fabrica.distribution.provider.grpc;

import com.liferay.fabrica.core.distribution.DistributionProvider;
import com.liferay.fabrica.core.distribution.client.Client;
import com.liferay.fabrica.core.distribution.server.Server;
import com.liferay.fabrica.distribution.provider.grpc.client.ProxyClientBuilder;
import com.liferay.fabrica.distribution.provider.grpc.server.ProxyServer;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.remoteserviceadmin.RemoteConstants;

/**
 * @author Miguel Pastor
 */
@Component
public class GrpcDistributionProvider implements DistributionProvider {

	public static final String GRPC_ENDPOINT_HOST = "grpc.endpoint.host";

	public static final String GRPC_ENDPOINT_PORT = "grpc.endpoint.port";

	public static final String GRPC_SERVER_CONFIG = "grpc.server";

	public static final String GRPC_SERVER_NAME = "grpc.server.name";

	public static final String GRPC_SERVER_SCHEME = "grpc.scheme";

	@Override
	public Client registerClient(
		Class<?>[] classes, Map<String, ?> properties) {

		String serviceImportedConfigs = (String)properties.get(
			RemoteConstants.SERVICE_IMPORTED_CONFIGS);

		String[] configurationTypes = serviceImportedConfigs.split(",");

		for (String configurationType : configurationTypes) {
			if (GRPC_SERVER_CONFIG.equals(configurationType)) {
				break;
			}
		}

		String host = (String)properties.get(GRPC_ENDPOINT_HOST);
		Integer port = (Integer)properties.get(GRPC_ENDPOINT_PORT);
		String scheme = (String)properties.get(GRPC_SERVER_SCHEME);
		String serverName = (String)properties.get(GRPC_SERVER_NAME);

		ManagedChannelBuilder<?> managedChannelBuilder = null;

		if (scheme != null) {
			managedChannelBuilder = ManagedChannelBuilder.forTarget(
				scheme + "://" + host);
		}
		else {
			managedChannelBuilder = ManagedChannelBuilder.forAddress(
				host, port);
		}

		ManagedChannel managedChannel = managedChannelBuilder.build();

		ProxyClientBuilder proxyClientBuilder = new ProxyClientBuilder(
			serverName, managedChannel);

		return proxyClientBuilder.build(
			GrpcDistributionProvider.class.getClassLoader(), classes);
	}

	@Override
	public Collection<Server> registerServer(
		Object object, Class<?>[] classes, Map<String, ?> properties) {

		String serviceName = (String)properties.get("remote.service.name");

		ProxyServer.Service service1 = ProxyServer.name(serviceName);

		ProxyServer.Contract contract = service1.service(object);

		ProxyServer.Address address = contract.contract(classes);

		ProxyServer.Build build = address.address(_getInetSocketAddress());

		Server server = build.build();

		try {
			server.start();
		}
		catch (IOException ioe) {
			return Collections.emptySet();
		}

		return Arrays.asList(server);
	}

	@Override
	public void unregisterServer(Server server) {
		try {
			server.stop();
		}
		catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}
	}

	private InetSocketAddress _getInetSocketAddress() {
		InetAddress inetAddress = null;

		try {
			inetAddress = InetAddress.getLocalHost();
		}
		catch (UnknownHostException uhe) {
			return null;
		}

		return new InetSocketAddress(inetAddress, _getPort());
	}

	private int _getPort() {
		ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

		return threadLocalRandom.nextInt(_PORT_MIN, _PORT_MAX + 1);
	}

	private static final int _PORT_MAX = (1 << 16) - 1;

	private static final int _PORT_MIN = 1025;

}
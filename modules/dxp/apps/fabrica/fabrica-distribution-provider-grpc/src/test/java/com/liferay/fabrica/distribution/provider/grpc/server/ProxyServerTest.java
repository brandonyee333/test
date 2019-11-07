/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.fabrica.distribution.provider.grpc.server;

import com.liferay.fabrica.core.distribution.server.Server;
import com.liferay.fabrica.distribution.provider.grpc.GrpcDistributionProvider;

import java.net.InetSocketAddress;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import org.osgi.service.remoteserviceadmin.RemoteConstants;

/**
 * @author Miguel Pastor
 */
public class ProxyServerTest {

	@Test
	public void testEndpointGeneration() {
		ProxyServer.Service service = ProxyServer.name("Test Server");

		ProxyServer.Contract contract = service.service(new AImpl());

		ProxyServer.Address address = contract.contract(A.class);

		ProxyServer.Build build = address.address(
			new InetSocketAddress("localhost", 11000));

		Server server = build.build();

		InetSocketAddress inetSocketAddress = server.getInetSocketAddress();

		Assert.assertEquals("localhost", inetSocketAddress.getHostName());
		Assert.assertEquals(11000, inetSocketAddress.getPort());

		Map<String, ?> info = server.getInfo();

		Assert.assertEquals(
			GrpcDistributionProvider.GRPC_SERVER_CONFIG,
			info.get(RemoteConstants.SERVICE_EXPORTED_CONFIGS));
	}

	private static class AImpl implements A {

		@Override
		public String a() {
			return "a";
		}

	}

	private interface A {

		public String a();

	}

}
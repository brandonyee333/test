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
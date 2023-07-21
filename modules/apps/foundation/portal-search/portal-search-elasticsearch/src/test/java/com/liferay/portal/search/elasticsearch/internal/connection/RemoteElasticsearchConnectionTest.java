/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.connection;

import com.liferay.portal.kernel.util.Props;

import java.net.InetSocketAddress;

import java.util.HashMap;
import java.util.List;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author André de Oliveira
 */
public class RemoteElasticsearchConnectionTest {

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		_remoteElasticsearchConnection = new RemoteElasticsearchConnection();

		_remoteElasticsearchConnection.props = _props;
	}

	@Test
	public void testModifyConnected() {
		HashMap<String, Object> properties = new HashMap<>();

		_remoteElasticsearchConnection.activate(properties);

		Assert.assertFalse(_remoteElasticsearchConnection.isConnected());

		_remoteElasticsearchConnection.connect();

		Assert.assertTrue(_remoteElasticsearchConnection.isConnected());

		assertTransportAddress("localhost", 9300);

		properties.put("transportAddresses", "127.0.0.1:9999");

		_remoteElasticsearchConnection.modified(properties);

		Assert.assertTrue(_remoteElasticsearchConnection.isConnected());

		assertTransportAddress("127.0.0.1", 9999);
	}

	@Test
	public void testModifyUnconnected() {
		Assert.assertFalse(_remoteElasticsearchConnection.isConnected());

		HashMap<String, Object> properties = new HashMap<>();

		_remoteElasticsearchConnection.modified(properties);

		Assert.assertFalse(_remoteElasticsearchConnection.isConnected());
	}

	protected void assertTransportAddress(String hostString, int port) {
		TransportClient transportClient =
			(TransportClient)_remoteElasticsearchConnection.getClient();

		List<TransportAddress> transportAddresses =
			transportClient.transportAddresses();

		Assert.assertEquals(
			transportAddresses.toString(), 1, transportAddresses.size());

		InetSocketTransportAddress inetSocketTransportAddress =
			(InetSocketTransportAddress)transportAddresses.get(0);

		InetSocketAddress inetSocketAddress =
			inetSocketTransportAddress.address();

		Assert.assertEquals(hostString, inetSocketAddress.getHostString());
		Assert.assertEquals(port, inetSocketAddress.getPort());
	}

	@Mock
	private Props _props;

	private RemoteElasticsearchConnection _remoteElasticsearchConnection;

}
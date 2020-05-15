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

package com.liferay.osb.asah.common.elasticsearch;

import com.liferay.osb.asah.common.constants.ServiceConstants;

import java.net.InetSocketAddress;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.transport.TransportAddress;

/**
 * @author Shinn Lok
 */
public class ClientUtil {

	public static TransportAddress getTransportAddress() {
		String[] transportAddressParts = StringUtils.split(
			ServiceConstants.LCP_ENGINE_ELASTICSEARCH_SERVER_IP, ':');

		int port = 9300;

		if (transportAddressParts.length == 2) {
			port = Integer.parseInt(transportAddressParts[1]);
		}

		InetSocketAddress inetSocketAddress = new InetSocketAddress(
			transportAddressParts[0], port);

		if (inetSocketAddress.getAddress() == null) {
			return null;
		}

		return new TransportAddress(inetSocketAddress);
	}

	public static void waitForConnection(Client client) {
		TransportClient transportClient = (TransportClient)client;

		List<DiscoveryNode> discoveryNodes = transportClient.connectedNodes();

		while (discoveryNodes.isEmpty()) {
			if (_log.isInfoEnabled()) {
				_log.info("Waiting for connection");
			}

			TransportAddress transportAddress = getTransportAddress();

			if (transportAddress != null) {
				List<TransportAddress> transportAddresses =
					transportClient.transportAddresses();

				if (!transportAddresses.contains(transportAddress)) {
					if (_log.isInfoEnabled()) {
						_log.info("Transport address changed");
					}

					for (TransportAddress curTransportAddress :
							transportAddresses) {

						transportClient.removeTransportAddress(
							curTransportAddress);
					}

					transportClient.addTransportAddress(transportAddress);
				}
			}

			try {
				Thread.sleep(10000);
			}
			catch (InterruptedException ie) {
				Thread thread = Thread.currentThread();

				thread.interrupt();
			}

			discoveryNodes = transportClient.connectedNodes();
		}
	}

	private static final Log _log = LogFactory.getLog(ClientUtil.class);

}
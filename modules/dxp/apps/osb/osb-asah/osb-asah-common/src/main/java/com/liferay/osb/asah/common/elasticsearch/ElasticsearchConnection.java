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

import java.io.InputStream;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequestBuilder;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class ElasticsearchConnection {

	public ClusterHealthResponse getClusterHealthResponse() {
		AdminClient adminClient = _transportClient.admin();

		ClusterAdminClient clusterAdminClient = adminClient.cluster();

		ClusterHealthRequestBuilder clusterHealthRequestBuilder =
			clusterAdminClient.prepareHealth();

		clusterHealthRequestBuilder.setWaitForYellowStatus();

		ClientUtil.waitForConnection(_transportClient);

		return clusterHealthRequestBuilder.get();
	}

	public RestHighLevelClient getRestHighLevelClient() {
		return _restHighLevelClient;
	}

	public Client getTransportClient() {
		return _transportClient;
	}

	@PostConstruct
	private void _connect() {
		Settings.Builder builder = Settings.builder();

		builder.loadFromSource(_readSettings(), XContentType.JSON);

		if (StringUtils.isBlank(
				ServiceConstants.LCP_ENGINE_ELASTICSEARCH_SERVER_IP)) {

			throw new IllegalStateException(
				"Unable to connect to Elasticsearch server. Please configure " +
					"the LCP_ENGINE_ELASTICSEARCH_SERVER_IP environment " +
						"variable");
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Connecting to Elasticsearch server at " +
					ServiceConstants.LCP_ENGINE_ELASTICSEARCH_SERVER_IP);
		}

		Settings settings = builder.build();

		TransportAddress transportAddress = ClientUtil.getTransportAddress();

		_restHighLevelClient = _createHttpClient(transportAddress.getAddress());
		_transportClient = _createTransportClient(settings, transportAddress);

		_testConnection();
	}

	private RestHighLevelClient _createHttpClient(String hostName) {
		return new RestHighLevelClient(
			RestClient.builder(new HttpHost(hostName, 9200, "http")));
	}

	private Client _createTransportClient(
		Settings settings, TransportAddress transportAddress) {

		TransportClient transportClient = new PreBuiltTransportClient(settings);

		transportClient.addTransportAddress(transportAddress);

		return transportClient;
	}

	@PreDestroy
	private void _disconnect() throws Exception {
		if (_restHighLevelClient != null) {
			_restHighLevelClient.close();
		}

		if (_transportClient != null) {
			_transportClient.close();
		}
	}

	private String _readSettings() {
		try {
			Class<?> clazz = getClass();

			InputStream inputStream = clazz.getResourceAsStream(
				"/elasticsearch_settings.json");

			return IOUtils.toString(inputStream, Charset.defaultCharset());
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private void _testConnection() {
		for (int i = _retryAttempts; i >= 0; i--) {
			try {
				getClusterHealthResponse();

				break;
			}
			catch (Exception e) {
				if (i == 0) {
					throw new IllegalStateException(
						"Unable to connect to Elasticsearch server", e);
				}
			}

			if (_log.isInfoEnabled()) {
				_log.info("Retrying to connect to Elasticsearch server");
			}

			try {
				Thread.sleep(_retryDelay);
			}
			catch (InterruptedException ie) {
				Thread thread = Thread.currentThread();

				thread.interrupt();
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchConnection.class);

	private RestHighLevelClient _restHighLevelClient;

	@Value("${osb.asah.elasticsearch.connection.retry.attempts:5}")
	private Integer _retryAttempts;

	@Value("${osb.asah.elasticsearch.connection.retry.delay:3000}")
	private Integer _retryDelay;

	private Client _transportClient;

}
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

package com.liferay.osb.asah.test.util.spring;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndices;
import com.liferay.osb.asah.test.util.elasticsearch.MessageBusChannel;
import com.liferay.osb.asah.test.util.messaging.MessageBusTestHelper;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.Socket;

import java.util.Objects;
import java.util.Set;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @author André Miranda
 * @author Marcellus Tavares
 */
public class OSBAsahTestExecutionListener
	extends AbstractTestExecutionListener {

	public OSBAsahTestExecutionListener() {
		if (!_isElasticsearchUp()) {
			throw new IllegalStateException(
				"Integration test infrastructure is not up. Please run " +
					"\"docker-compose -f docker-compose.integration-test.yml " +
						"up -d\" from the root project directory.");
		}
	}

	@Override
	public void afterTestClass(TestContext testContext) {
		Class<?> clazz = testContext.getTestClass();

		ElasticsearchIndex[] elasticsearchIndices = clazz.getAnnotationsByType(
			ElasticsearchIndex.class);

		for (ElasticsearchIndex elasticsearchIndex : elasticsearchIndices) {
			String indexName = _elasticsearchIndexManager.getIndexName(
				elasticsearchIndex.name(),
				elasticsearchIndex.weDeployDataService());

			_elasticsearchIndexManager.clear(indexName);
		}
	}

	@Override
	public void afterTestMethod(TestContext testContext) {
		Class<?> clazz = testContext.getTestClass();

		if (!clazz.isAnnotationPresent(ElasticsearchIndex.class) &&
			!clazz.isAnnotationPresent(ElasticsearchIndices.class)) {

			_elasticsearchIndexManager.clearIndices();
		}

		Set<ElasticsearchIndex> elasticsearchIndices =
			AnnotatedElementUtils.findMergedRepeatableAnnotations(
				testContext.getTestMethod(), ElasticsearchIndex.class);

		for (ElasticsearchIndex elasticsearchIndex : elasticsearchIndices) {
			if (!Objects.equals(elasticsearchIndex.configurationPath(), "")) {
				_elasticsearchIndexManager.delete(
					_elasticsearchIndexManager.getIndexName(
						elasticsearchIndex.name(),
						elasticsearchIndex.weDeployDataService()));
			}
		}

		MessageBusChannel messageBusChannel =
			AnnotatedElementUtils.findMergedAnnotation(
				testContext.getTestMethod(), MessageBusChannel.class);

		if (messageBusChannel != null) {
			MessageBusTestHelper pubSubMessageBusTestHelper =
				new MessageBusTestHelper(_messageBus);

			pubSubMessageBusTestHelper.clearMessageBusChannel(
				messageBusChannel);
		}
	}

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		Class<?> clazz = testContext.getTestClass();

		ElasticsearchIndex[] elasticsearchIndices = clazz.getAnnotationsByType(
			ElasticsearchIndex.class);

		if (elasticsearchIndices.length == 0) {
			return;
		}

		_elasticsearchIndexManager.addTemplates();

		for (ElasticsearchIndex elasticsearchIndex : elasticsearchIndices) {
			_prepareIndex(clazz, elasticsearchIndex);
		}
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		_elasticsearchIndexManager.addTemplates();

		Set<ElasticsearchIndex> elasticsearchIndices =
			AnnotatedElementUtils.findMergedRepeatableAnnotations(
				testContext.getTestMethod(), ElasticsearchIndex.class);

		for (ElasticsearchIndex elasticsearchIndex : elasticsearchIndices) {
			_prepareIndex(testContext.getTestClass(), elasticsearchIndex);
		}

		MessageBusChannel messageBusChannel =
			AnnotatedElementUtils.findMergedAnnotation(
				testContext.getTestMethod(), MessageBusChannel.class);

		if (messageBusChannel != null) {
			MessageBusTestHelper pubSubMessageBusTestHelper =
				new MessageBusTestHelper(_messageBus);

			pubSubMessageBusTestHelper.prepareMessageBusChannel(
				testContext.getTestClass(), messageBusChannel);
		}
	}

	private boolean _isElasticsearchUp() {
		return _pingHost(
			ServiceConstants.LCP_ENGINE_ELASTICSEARCH_SERVER_IP, 9200, 3000);
	}

	private boolean _pingHost(String hostname, int port, int timeout) {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(hostname, port), timeout);

			return true;
		}
		catch (IOException ioe) {
			return false;
		}
	}

	private void _prepareIndex(
			Class<?> clazz, ElasticsearchIndex elasticsearchIndex)
		throws Exception {

		String indexName = _elasticsearchIndexManager.getIndexName(
			elasticsearchIndex.name(),
			elasticsearchIndex.weDeployDataService());

		if (!Objects.equals(elasticsearchIndex.configurationPath(), "")) {
			String indexConfiguration = ResourceUtil.readResourceToString(
				"dependencies/" + elasticsearchIndex.weDeployDataService() +
					"/" + elasticsearchIndex.configurationPath(),
				clazz);

			_elasticsearchIndexManager.delete(indexName);

			_elasticsearchIndexManager.create(
				true, indexConfiguration, indexName);
		}

		if (!Objects.equals(elasticsearchIndex.resourcePath(), "")) {
			ElasticsearchInvoker elasticsearchInvoker =
				_elasticsearchInvokerFactory.forWeDeployDataService(
					elasticsearchIndex.weDeployDataService());

			elasticsearchInvoker.add(
				elasticsearchIndex.name(),
				new JSONArray(
					TestExecutionListenerUtil.replaceVariables(
						ResourceUtil.readResourceToString(
							"dependencies/" +
								elasticsearchIndex.weDeployDataService() + "/" +
									elasticsearchIndex.resourcePath(),
							clazz))));
		}
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private MessageBus _messageBus;

}
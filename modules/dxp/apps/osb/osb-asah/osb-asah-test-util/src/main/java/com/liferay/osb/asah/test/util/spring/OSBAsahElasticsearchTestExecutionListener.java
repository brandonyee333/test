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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndices;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.messaging.MessageBusTestHelper;

import java.util.Objects;
import java.util.Set;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @author André Miranda
 * @author Marcellus Tavares
 */
public class OSBAsahElasticsearchTestExecutionListener
	extends AbstractTestExecutionListener {

	@Override
	public void afterTestClass(TestContext testContext) {
		Class<?> clazz = testContext.getTestClass();

		ElasticsearchIndex[] elasticsearchIndices = clazz.getAnnotationsByType(
			ElasticsearchIndex.class);

		for (ElasticsearchIndex elasticsearchIndex : elasticsearchIndices) {
			String indexName = ElasticsearchIndexUtil.getIndexName(
				elasticsearchIndex.name(),
				elasticsearchIndex.weDeployDataService());

			_elasticsearchIndexManager.clear(indexName);
		}

		ProjectIdThreadLocal.remove();
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
					ElasticsearchIndexUtil.getIndexName(
						elasticsearchIndex.name(),
						elasticsearchIndex.weDeployDataService()));
			}
		}

		MessageBusChannel messageBusChannel =
			AnnotatedElementUtils.findMergedAnnotation(
				testContext.getTestMethod(), MessageBusChannel.class);

		if (messageBusChannel != null) {
			MessageBusTestHelper messageBusTestHelper =
				new MessageBusTestHelper(_messageBus);

			messageBusTestHelper.clearMessageBusChannel(messageBusChannel);
		}

		for (String cacheName : _cacheManager.getCacheNames()) {
			Cache cache = _cacheManager.getCache(cacheName);

			if (cache != null) {
				cache.invalidate();
			}
		}
	}

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

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
			MessageBusTestHelper messageBusTestHelper =
				new MessageBusTestHelper(_messageBus);

			messageBusTestHelper.prepareMessageBusChannel(
				testContext.getTestClass(), messageBusChannel);
		}
	}

	private void _prepareIndex(
			Class<?> clazz, ElasticsearchIndex elasticsearchIndex)
		throws Exception {

		String indexName = ElasticsearchIndexUtil.getIndexName(
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
				_elasticsearchInvokerManager.forWeDeployDataService(
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
	private CacheManager _cacheManager;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

	@Autowired
	private MessageBus _messageBus;

}
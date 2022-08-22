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

import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.messaging.MessageBusTestHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.test.context.TestContext;

/**
 * @author André Miranda
 * @author Marcellus Tavares
 */
@TestComponent
public class OSBAsahMessageBusTestExecutionListener
	extends BaseOSBAsahTestExecutionListener {

	@Override
	public void afterTestMethod(TestContext testContext) {
		ProjectIdThreadLocal.setProjectId("test");

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

		ProjectIdThreadLocal.remove();
	}

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		super.beforeTestClass(testContext);

		ProjectIdThreadLocal.setProjectId("test");
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

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

	@Autowired
	private CacheManager _cacheManager;

	@Autowired
	private MessageBus _messageBus;

}
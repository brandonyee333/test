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

package com.liferay.osb.asah.monolith.common.messaging.impl;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageListener;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Matchers;
import org.mockito.Mockito;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Marcellus Tavares
 */
public class MessageBusImplTest {

	@Before
	public void setUp() {
		_messageBusImpl = new MessageBusImpl();

		_messageBusImpl.init();
	}

	@After
	public void tearDown() {
		_messageBusImpl.destroy();
	}

	@Test
	public void testRegisterMessageListener() {
		_messageBusImpl.registerMessageListener(
			Channel.ANALYTICS_EVENTS_ACTIVITY,
			Mockito.mock(MessageListener.class),
			ServiceConstants.LCP_PROJECT_ID);
		_messageBusImpl.registerMessageListener(
			Channel.ANALYTICS_EVENTS_ACTIVITY,
			Mockito.mock(MessageListener.class),
			ServiceConstants.LCP_PROJECT_ID);

		Set<MessageListener> messageListeners = _getMessageBusMessageListeners(
			Channel.ANALYTICS_EVENTS_ACTIVITY);

		Assert.assertEquals(
			messageListeners.toString(), 2, messageListeners.size());
	}

	@Test
	public void testSendMessage() throws Exception {
		MessageListener dataSourcesMessageListener1 = Mockito.mock(
			MessageListener.class);

		_messageBusImpl.registerMessageListener(
			Channel.ANALYTICS_EVENTS_ACTIVITY, dataSourcesMessageListener1,
			ServiceConstants.LCP_PROJECT_ID);

		MessageListener dataSourcesMessageListener2 = Mockito.mock(
			MessageListener.class);

		_messageBusImpl.registerMessageListener(
			Channel.ANALYTICS_EVENTS_ACTIVITY, dataSourcesMessageListener2,
			ServiceConstants.LCP_PROJECT_ID);

		MessageListener upgradeCheckMessageListener = Mockito.mock(
			MessageListener.class);

		String message = RandomTestUtil.randomString();

		_messageBusImpl.sendMessage(
			Channel.ANALYTICS_EVENTS_ACTIVITY, message,
			ServiceConstants.LCP_PROJECT_ID);

		ExecutorService executorService =
			(ExecutorService)ReflectionTestUtils.getField(
				_messageBusImpl, "_executorService");

		Assert.assertNotNull(executorService);

		executorService.shutdown();

		executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

		Mockito.verify(
			dataSourcesMessageListener1, Mockito.times(1)
		).onMessage(
			Matchers.eq(message)
		);

		Mockito.verify(
			dataSourcesMessageListener2, Mockito.times(1)
		).onMessage(
			Matchers.eq(message)
		);

		Mockito.verifyZeroInteractions(upgradeCheckMessageListener);
	}

	@Test
	public void testUnregisterMessageListener() {
		MessageListener messageListener = Mockito.mock(MessageListener.class);

		_messageBusImpl.registerMessageListener(
			Channel.ANALYTICS_EVENTS_ACTIVITY, messageListener,
			ServiceConstants.LCP_PROJECT_ID);

		_messageBusImpl.unregisterMessageListener(messageListener);

		Set<MessageListener> messageListeners = _getMessageBusMessageListeners(
			Channel.ANALYTICS_EVENTS_ACTIVITY);

		Assert.assertEquals(
			messageListeners.toString(), 0, messageListeners.size());
	}

	private Set<MessageListener> _getMessageBusMessageListeners(
		Channel channel) {

		Object messageListenersObject = ReflectionTestUtils.getField(
			_messageBusImpl, "_messageListeners");

		Assert.assertNotNull(messageListenersObject);

		Map<String, Set<MessageListener>> messageListeners =
			(Map<String, Set<MessageListener>>)messageListenersObject;

		return messageListeners.get(
			StringUtils.lowerCase(
				String.format(
					"%s_%s", ServiceConstants.LCP_PROJECT_ID,
					channel.toString())));
	}

	private MessageBusImpl _messageBusImpl;

}
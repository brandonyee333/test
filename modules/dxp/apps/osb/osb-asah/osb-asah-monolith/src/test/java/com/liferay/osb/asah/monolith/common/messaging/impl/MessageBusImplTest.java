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

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageListener;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

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
			Channel.DATA_SOURCES_UPDATED, Mockito.mock(MessageListener.class));
		_messageBusImpl.registerMessageListener(
			Channel.DATA_SOURCES_UPDATED, Mockito.mock(MessageListener.class));

		Set<MessageListener> messageListeners = _getMessageBusMessageListeners(
			Channel.DATA_SOURCES_UPDATED);

		Assert.assertEquals(
			messageListeners.toString(), 2, messageListeners.size());
	}

	@Test
	public void testSendMessage() throws Exception {
		MessageListener dataSourcesMessageListener1 = Mockito.mock(
			MessageListener.class);

		_messageBusImpl.registerMessageListener(
			Channel.DATA_SOURCES_UPDATED, dataSourcesMessageListener1);

		MessageListener dataSourcesMessageListener2 = Mockito.mock(
			MessageListener.class);

		_messageBusImpl.registerMessageListener(
			Channel.DATA_SOURCES_UPDATED, dataSourcesMessageListener2);

		MessageListener upgradeCheckMessageListener = Mockito.mock(
			MessageListener.class);

		String message = RandomTestUtil.randomString();

		_messageBusImpl.sendMessage(Channel.DATA_SOURCES_UPDATED, message);

		ExecutorService executorService =
			(ExecutorService)ReflectionTestUtils.getField(
				_messageBusImpl, "_executorService");

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
			Channel.DATA_SOURCES_UPDATED, messageListener);

		_messageBusImpl.unregisterMessageListener(messageListener);

		Set<MessageListener> messageListeners = _getMessageBusMessageListeners(
			Channel.DATA_SOURCES_UPDATED);

		Assert.assertEquals(
			messageListeners.toString(), 0, messageListeners.size());
	}

	private Set<MessageListener> _getMessageBusMessageListeners(
		Channel channel) {

		Map<Channel, Set<MessageListener>> messageListeners =
			(Map<Channel, Set<MessageListener>>)ReflectionTestUtils.getField(
				_messageBusImpl, "_messageListeners");

		return messageListeners.get(channel);
	}

	private MessageBusImpl _messageBusImpl;

}
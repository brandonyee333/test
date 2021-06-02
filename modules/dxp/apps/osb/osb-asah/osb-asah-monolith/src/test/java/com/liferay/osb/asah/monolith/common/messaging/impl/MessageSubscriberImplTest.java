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
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Geyson Silva
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageSubscriberImplTest {

	@Before
	public void setUp() {
		_messageSubscriberImpl = new MessageSubscriberImpl(
			Channel.ANALYTICS_EVENTS_PAGE, "Analytics");
	}

	@Test
	public void testEnqueue() {
		ReflectionTestUtils.setField(_messageSubscriberImpl, "_queue", _queue);

		String message = RandomTestUtil.randomString();

		_messageSubscriberImpl.enqueue(message);

		Mockito.verify(
			_queue
		).offer(
			message
		);
	}

	@Test
	public void testEqualsAndHashCode() {
		EqualsVerifier<MessageSubscriberImpl> equalsVerifier =
			EqualsVerifier.forClass(MessageSubscriberImpl.class);

		equalsVerifier.suppress(
			Warning.ALL_FIELDS_SHOULD_BE_USED, Warning.STRICT_INHERITANCE);

		equalsVerifier.verify();
	}

	@Test
	public void testPullMessages() {
		String message1 = RandomTestUtil.randomString();
		String message2 = RandomTestUtil.randomString();
		String message3 = RandomTestUtil.randomString();

		_messageSubscriberImpl.enqueue(message1);
		_messageSubscriberImpl.enqueue(message2);
		_messageSubscriberImpl.enqueue(message3);

		List<String> messages = _messageSubscriberImpl.pullMessages(4);

		Assert.assertTrue(
			messages.containsAll(Arrays.asList(message1, message2, message3)));
	}

	private MessageSubscriberImpl _messageSubscriberImpl;

	@Mock
	private Queue<String> _queue;

}
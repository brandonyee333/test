/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.event;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.event.LCSEventListener;
import com.liferay.lcs.client.internal.BasePowerMockitoTestCase;

import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@RunWith(PowerMockRunner.class)
public class LCSEventManagerTest extends BasePowerMockitoTestCase {

	@Test
	public void testSubscribe() {
		LCSEventManager lcsEventManager = new LCSEventManager();

		TestLCSEventListenerImpl testLCSEventListenerImpl =
			new TestLCSEventListenerImpl();

		lcsEventManager.subscribe(
			LCSEvent.HANDSHAKE_SUCCESS, testLCSEventListenerImpl);

		lcsEventManager.publish(LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED);

		Queue<LCSEvent> receivedLCSEventsQueue =
			testLCSEventListenerImpl._lcsEventQueue;

		Assert.assertTrue(
			"Expected no received LCS events",
			receivedLCSEventsQueue.isEmpty());

		lcsEventManager.publish(LCSEvent.HANDSHAKE_SUCCESS);

		Assert.assertEquals(
			"Expected to receive LCS event", LCSEvent.HANDSHAKE_SUCCESS,
			receivedLCSEventsQueue.poll());
	}

	@Test
	public void testUnsubscribe() {
		LCSEventManager lcsEventManager = new LCSEventManager();

		TestLCSEventListenerImpl testLCSEventListenerImpl =
			new TestLCSEventListenerImpl();

		lcsEventManager.subscribe(
			LCSEvent.HANDSHAKE_SUCCESS, testLCSEventListenerImpl);

		lcsEventManager.publish(LCSEvent.HANDSHAKE_SUCCESS);

		Queue<LCSEvent> receivedLCSEventsQueue =
			testLCSEventListenerImpl._lcsEventQueue;

		Assert.assertEquals(
			"Expected to receive LCS event", LCSEvent.HANDSHAKE_SUCCESS,
			receivedLCSEventsQueue.poll());

		lcsEventManager.unsubscribe(testLCSEventListenerImpl);

		lcsEventManager.publish(LCSEvent.HANDSHAKE_SUCCESS);

		Assert.assertTrue(
			"Expected no received LCS events",
			receivedLCSEventsQueue.isEmpty());
	}

	private class TestLCSEventListenerImpl implements LCSEventListener {

		@Override
		public void onLCSEvent(LCSEvent lcsEvent) {
			_lcsEventQueue.offer(lcsEvent);
		}

		private final Queue<LCSEvent> _lcsEventQueue = new PriorityQueue<>();

	}

}
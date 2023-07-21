/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.messaging.advisor;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.BasePowerMockitoTestCase;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.platform.gateway.LCSGatewayClientImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.messaging.MessageBus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@PrepareForTest(JSONFactoryUtil.class)
@RunWith(PowerMockRunner.class)
public class MessageBusAdvisorTest extends BasePowerMockitoTestCase {

	@Before
	public void setUp() {
		mockStatic(JSONFactoryUtil.class);

		when(
			JSONFactoryUtil.createJSONObject()
		).thenReturn(
			mock(JSONObject.class)
		);
	}

	@Test
	public void testOnHandshakeSuccess() {
		LCSEventManager lcsEventManager = new LCSEventManager();

		MessageBusAdvisor spyMessageBusAdvisor = _spyMessageBusAdvisor(
			lcsEventManager);

		lcsEventManager.publish(LCSEvent.HANDSHAKE_SUCCESS);

		Mockito.verify(
			spyMessageBusAdvisor, Mockito.times(1)
		).onLCSEvent(
			LCSEvent.HANDSHAKE_SUCCESS
		);

		Mockito.verify(
			spyMessageBusAdvisor, Mockito.times(1)
		).processLCSPortletState(
			LCSPortletState.NO_SUBSCRIPTION
		);
	}

	@Test
	public void testOnLCSClusterEntryTokenCheckSuccess() {
		LCSEventManager lcsEventManager = new LCSEventManager();

		MessageBusAdvisor spyMessageBusAdvisor = _spyMessageBusAdvisor(
			lcsEventManager);

		lcsEventManager.publish(LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS);

		Mockito.verify(
			spyMessageBusAdvisor, Mockito.times(1)
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS
		);

		Mockito.verify(
			spyMessageBusAdvisor, Mockito.times(1)
		).processLCSPortletState(
			LCSPortletState.NO_CONNECTION
		);
	}

	@Test
	public void testOnLCSGatewayUnavailable() {
		LCSEventManager lcsEventManager = new LCSEventManager();

		MessageBusAdvisor spyMessageBusAdvisor = _spyMessageBusAdvisor(
			lcsEventManager);

		lcsEventManager.publish(LCSEvent.LCS_GATEWAY_UNAVAILABLE);

		Mockito.verify(
			spyMessageBusAdvisor, Mockito.times(1)
		).onLCSEvent(
			LCSEvent.LCS_GATEWAY_UNAVAILABLE
		);

		Mockito.verify(
			spyMessageBusAdvisor, Mockito.times(1)
		).processLCSPortletState(
			LCSPortletState.NO_CONNECTION
		);
	}

	private MessageBusAdvisor _spyMessageBusAdvisor(
		LCSEventManager lcsEventManager) {

		new LCSGatewayClientImpl(lcsEventManager);

		MessageBusAdvisor messageBusAdvisor = new MessageBusAdvisor(
			lcsEventManager, mock(MessageBus.class));

		lcsEventManager.unsubscribe(messageBusAdvisor);

		MessageBusAdvisor spyMessageBusAdvisor = spy(messageBusAdvisor);

		lcsEventManager.subscribe(
			LCSEvent.HANDSHAKE_SUCCESS, spyMessageBusAdvisor);
		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS,
			spyMessageBusAdvisor);
		lcsEventManager.subscribe(
			LCSEvent.LCS_GATEWAY_UNAVAILABLE, spyMessageBusAdvisor);

		return spyMessageBusAdvisor;
	}

}
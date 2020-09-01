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

package com.liferay.lcs.client.internal;

import com.liferay.lcs.client.alert.advisor.LCSAlertAdvisor;
import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.advisor.LCSClusterEntryTokenAdvisorImpl;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.advisor.UptimeAdvisor;
import com.liferay.lcs.client.internal.alert.advisor.LCSAlertAdvisorImpl;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.platform.gateway.LCSGatewayClientImpl;
import com.liferay.lcs.client.internal.platform.gateway.MockLCSGatewayClientImpl;
import com.liferay.lcs.client.internal.platform.http.RESTClientTransportException;
import com.liferay.lcs.client.internal.platform.portal.LCSPortalClient;
import com.liferay.lcs.client.internal.task.HandshakeTask;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.platform.gateway.LCSGatewayException;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.HandshakeResponseMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.portal.kernel.service.CompanyLocalService;

import java.net.UnknownHostException;

import java.util.concurrent.ExecutionException;

import org.mockito.Matchers;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Igor Beslic
 */
public abstract class BasePowerMockitoTestCase extends PowerMockito {

	protected LCSConfigurationProvider mockLCSConfigurationProvider() {
		LCSConfiguration lcsConfiguration = mock(LCSConfiguration.class);

		LCSConfigurationProvider lcsConfigurationProvider = mock(
			LCSConfigurationProvider.class);

		when(
			lcsConfigurationProvider.getLCSConfiguration()
		).thenReturn(
			lcsConfiguration
		);

		return lcsConfigurationProvider;
	}

	protected LCSPortalClient mockLCSPortalClientIsAuthorized(
			Boolean returnValue)
		throws Exception {

		LCSPortalClient lcsPortalClient = mock(LCSPortalClient.class);

		doReturn(
			returnValue
		).when(
			lcsPortalClient
		).isAuthorized(
			Matchers.anyString(), Matchers.anyString()
		);

		return lcsPortalClient;
	}

	protected LCSPortalClient mockLCSPortalClientIsAuthorizedThrowsException(
			Exception exception)
		throws Exception {

		LCSPortalClient lcsPortalClient = mock(LCSPortalClient.class);

		doThrow(
			exception
		).when(
			lcsPortalClient
		).isAuthorized(
			Matchers.anyString(), Matchers.anyString()
		);

		return lcsPortalClient;
	}

	protected LCSGatewayClient spyGetMessagesToReturnHandshakeResponseMessage(
		int errorCode, LCSEventManager lcsEventManager) {

		MockLCSGatewayClientImpl mockLCSGatewayClient =
			new MockLCSGatewayClientImpl(lcsEventManager);

		mockLCSGatewayClient.addMockMessage(
			_createHandshakeResponseMessage(errorCode));

		return mockLCSGatewayClient;
	}

	protected HandshakeTask spyHandshakeTask(
			LCSEventManager lcsEventManager, LCSGatewayClient lcsGatewayClient,
			LCSKeyAdvisor lcsKeyAdvisor, UptimeAdvisor uptimeAdvisor)
		throws Exception {

		CompanyLocalService companyLocalService = mock(
			CompanyLocalService.class);
		LCSAlertAdvisor lcsAlertAdvisor = new LCSAlertAdvisorImpl();

		HandshakeTask handshakeTask = spy(
			new HandshakeTask(
				companyLocalService, lcsAlertAdvisor, lcsEventManager,
				lcsGatewayClient, lcsKeyAdvisor, uptimeAdvisor));

		doReturn(
			new HandshakeMessage()
		).when(
			handshakeTask, "_createHandshakeMessage"
		);

		return handshakeTask;
	}

	protected LCSClusterEntryTokenAdvisorImpl
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(
				LCSEventManager lcsEventManager)
		throws Exception {

		LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisorImpl =
			new LCSClusterEntryTokenAdvisorImpl(
				mockLCSConfigurationProvider(), lcsEventManager);

		lcsEventManager.unsubscribe(lcsClusterEntryTokenAdvisorImpl);

		LCSClusterEntryTokenAdvisorImpl spyLCSClusterEntryTokenAdvisorImpl =
			spy(lcsClusterEntryTokenAdvisorImpl);

		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED,
			spyLCSClusterEntryTokenAdvisorImpl);
		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH,
			spyLCSClusterEntryTokenAdvisorImpl);
		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID,
			spyLCSClusterEntryTokenAdvisorImpl);
		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS,
			spyLCSClusterEntryTokenAdvisorImpl);
		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED,
			spyLCSClusterEntryTokenAdvisorImpl);
		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED,
			spyLCSClusterEntryTokenAdvisorImpl);

		doReturn(
			"mockedAccessToken"
		).when(
			spyLCSClusterEntryTokenAdvisorImpl
		).getLCSAccessToken();

		doReturn(
			"mockedAccessSecret"
		).when(
			spyLCSClusterEntryTokenAdvisorImpl
		).getLCSAccessSecret();

		// Skip JavaParser, will fix

		doNothing(
		).when(
			spyLCSClusterEntryTokenAdvisorImpl,
			"_deleteLCSCLusterEntryTokenFile"
		);

		return spyLCSClusterEntryTokenAdvisorImpl;
	}

	protected LCSGatewayClient spySendMessageToThrowLCSGatewayException(
			LCSEventManager lcsEventManager)
		throws Exception {

		LCSGatewayClient lcsGatewayClient = spy(
			new LCSGatewayClientImpl(lcsEventManager));

		doReturn(
			Boolean.TRUE
		).when(
			lcsGatewayClient
		).isAvailable();

		doThrow(
			new LCSGatewayException(
				"Unable to send message",
				new RESTClientTransportException.CommunicationFailure(
					"Test gateway communication failure",
					new ExecutionException(new UnknownHostException("Test"))))
		).when(
			lcsGatewayClient
		).sendMessage(
			Matchers.any(Message.class)
		);

		return lcsGatewayClient;
	}

	protected void verifyLCSClusterEntryTokenAdvisor(
			int maxNumberOfOnLCSEventInvocations,
			int maxNumberOfSpecificOnLCSEventInvocations,
			LCSEvent specificLCSEvent, int maxNumberOfDeleteMethodInvocations,
			LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisorImpl)
		throws Exception {

		Mockito.verify(
			lcsClusterEntryTokenAdvisorImpl,
			Mockito.times(maxNumberOfOnLCSEventInvocations)
		).onLCSEvent(
			Matchers.any(LCSEvent.class)
		);

		Mockito.verify(
			lcsClusterEntryTokenAdvisorImpl,
			Mockito.times(maxNumberOfSpecificOnLCSEventInvocations)
		).onLCSEvent(
			specificLCSEvent
		);

		verifyPrivate(
			lcsClusterEntryTokenAdvisorImpl,
			Mockito.times(maxNumberOfDeleteMethodInvocations)
		).invoke(
			"_deleteLCSCLusterEntryTokenFile"
		);
	}

	private HandshakeResponseMessage _createHandshakeResponseMessage(
		int errorCode) {

		HandshakeResponseMessage handshakeResponseMessage =
			new HandshakeResponseMessage();

		handshakeResponseMessage.setErrorCode(errorCode);
		handshakeResponseMessage.setErrorMessage(
			"{\"errorCode\": " + errorCode +
				", \"errorDescription\": \"Test\", \"status\": 400}");
		handshakeResponseMessage.setKey("mock");

		return handshakeResponseMessage;
	}

}
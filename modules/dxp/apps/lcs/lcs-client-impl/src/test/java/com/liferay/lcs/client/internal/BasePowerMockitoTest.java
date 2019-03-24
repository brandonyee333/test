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

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.advisor.LCSClusterEntryTokenAdvisorImpl;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.platform.gateway.LCSGatewayClientImpl;
import com.liferay.lcs.client.internal.platform.portal.LCSPortalClient;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.HandshakeResponseMessage;
import com.liferay.lcs.messaging.Message;

import java.util.ArrayList;

import org.mockito.Matchers;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Igor Beslic
 */
public class BasePowerMockitoTest extends PowerMockito {

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
			int errorCode, LCSEventManager lcsEventManager)
		throws Exception {

		LCSGatewayClient lcsGatewayClient = spy(
			new LCSGatewayClientImpl(lcsEventManager));

		doNothing(
		).when(
			lcsGatewayClient
		).deleteMessages(
			Matchers.anyString()
		);

		doNothing(
		).when(
			lcsGatewayClient
		).sendMessage(
			Matchers.any(Message.class)
		);

		doReturn(
			new ArrayList<Message>() {
				{
					add(_createHandshakeResponseMessage(errorCode));
				}
			}
		).when(
			lcsGatewayClient
		).getMessages(
			Matchers.anyString()
		);

		return lcsGatewayClient;
	}

	protected LCSClusterEntryTokenAdvisorImpl
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(
				LCSEventManager lcsEventManager)
		throws Exception {

		LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisorImpl = spy(
			new LCSClusterEntryTokenAdvisorImpl(lcsEventManager));

		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED,
			lcsClusterEntryTokenAdvisorImpl);
		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH,
			lcsClusterEntryTokenAdvisorImpl);
		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID,
			lcsClusterEntryTokenAdvisorImpl);
		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS,
			lcsClusterEntryTokenAdvisorImpl);
		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED,
			lcsClusterEntryTokenAdvisorImpl);
		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED,
			lcsClusterEntryTokenAdvisorImpl);

		doReturn(
			"mockedAccessToken"
		).when(
			lcsClusterEntryTokenAdvisorImpl
		).getLCSAccessToken();

		doReturn(
			"mockedAccessSecret"
		).when(
			lcsClusterEntryTokenAdvisorImpl
		).getLCSAccessSecret();

		// Skip JavaParser, will fix

		doNothing(
		).when(
			lcsClusterEntryTokenAdvisorImpl, "_deleteLCSCLusterEntryTokenFile"
		);

		return lcsClusterEntryTokenAdvisorImpl;
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
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

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.SignOffCommandMessage;
import com.liferay.lcs.messaging.SignOffReasonCode;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class SignOffTask implements ScheduledTask {

	@Override
	public Scope getScope() {
		return Scope.NODE;
	}

	public SignOffTask(
		LCSEventManager lcsEventManager, LCSGatewayClient lcsGatewayClient,
		LCSKeyAdvisor lcsKeyAdvisor,
		SignOffCommandMessage signOffCommandMessage) {

		_lcsEventManager = lcsEventManager;
		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_signOffCommandMessage = signOffCommandMessage;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void run() {
		_processSignOffCommandMessage();

		_sendSignOffMessage();
	}

	private void _sendSignOffMessage() {
		HandshakeMessage handshakeMessage = new HandshakeMessage();

		handshakeMessage.setServerManuallyShutdown(true);
		handshakeMessage.setSignOff(true);
		handshakeMessage.setKey(_lcsKeyAdvisor.getKey());

		try {
			if (_log.isTraceEnabled()) {
				_log.trace("Sending sign out message: " + handshakeMessage);
			}

			_lcsGatewayClient.sendMessage(handshakeMessage);

			if (_log.isWarnEnabled()) {
				_log.warn("Signed out and disconnected from LCS");
			}

			_lcsEventManager.publish(LCSEvent.SIGNOFF_SUCCESS);
		}
		catch (Exception e) {
			StringBundler sb = new StringBundler(3);

			sb.append("Unable to sign out of LCS. LCS will declare the ");
			sb.append("server offline after few minutes without ");
			sb.append("communication.");

			_log.error(sb.toString(), e);

			_lcsEventManager.publish(LCSEvent.SIGNOFF_FAILED);
		}
	}

	private void _processSignOffCommandMessage() {
		if (_signOffCommandMessage == null) {
			return;
		}

		SignOffReasonCode signOffReasonCode = _getSignOffReasonCode();

		if (_log.isInfoEnabled()) {
			_log.info(
				"Signing server out of LCS with reason code:" +
					signOffReasonCode);

			if (_log.isTraceEnabled()) {
				_log.trace(
					"Sign out command message: " + _signOffCommandMessage);
			}
		}

		if (signOffReasonCode == SignOffReasonCode.INVALIDATE_TOKEN) {
			_lcsEventManager.publish(
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED);
		}
		else if (signOffReasonCode == SignOffReasonCode.UNREGISTER) {
			_lcsEventManager.publish(LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED);
		}
	}

	private SignOffReasonCode _getSignOffReasonCode() {
		for (SignOffReasonCode signOffReasonCode : SignOffReasonCode.values()) {
			if (_signOffCommandMessage.getReasonCode() ==
				signOffReasonCode.getCode()) {

				return signOffReasonCode;
			}
		}

		return SignOffReasonCode.UNDEFINED;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SignOffTask.class);

	private LCSEventManager _lcsEventManager;

	private LCSGatewayClient _lcsGatewayClient;

	private LCSKeyAdvisor _lcsKeyAdvisor;

	private final SignOffCommandMessage _signOffCommandMessage;

}
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

package com.liferay.lcs.task;

import com.liferay.lcs.internal.event.LCSEvent;
import com.liferay.lcs.internal.event.LCSEventListener;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class SignOffTask implements Task {

	public SignOffTask(
		String key, LCSGatewayClient lcsGatewayClient,
		boolean serverManuallyShutdown) {

		_key = key;
		_lcsGatewayClient = lcsGatewayClient;
		_serverManuallyShutdown = serverManuallyShutdown;

		_lcsEventListeners.add(lcsGatewayClient);

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void run() {
		if (!_lcsGatewayClient.isAvailable()) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting signing out of LCS. LCS gateway is not " +
						"available.");
			}

			return;
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Running " + this);
		}

		doRun();
	}

	protected void doRun() {
		HandshakeMessage handshakeMessage = new HandshakeMessage();

		handshakeMessage.setServerManuallyShutdown(_serverManuallyShutdown);
		handshakeMessage.setSignOff(true);
		handshakeMessage.setKey(_key);

		try {
			if (_log.isTraceEnabled()) {
				_log.trace("Sending sign out message: " + handshakeMessage);
			}

			_lcsGatewayClient.sendMessage(handshakeMessage);

			if (_log.isWarnEnabled()) {
				_log.warn("Signed out and disconnected from LCS");
			}

			_notifyLCSEventListeners(LCSEvent.SIGNOFF_SUCCESS);
		}
		catch (Exception e) {
			StringBundler sb = new StringBundler(3);

			sb.append("Unable to sign out of LCS. LCS will declare the ");
			sb.append("server offline after few minutes without ");
			sb.append("communication.");

			String errorMessage = sb.toString();

			if (e instanceof JSONWebServiceException) {
				_log.error(errorMessage);
			}
			else {
				_log.error(errorMessage, e);
			}

			_notifyLCSEventListeners(LCSEvent.SIGNOFF_FAILED);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private void _notifyLCSEventListeners(LCSEvent lcsEvent) {
		for (LCSEventListener lcsEventListener : _lcsEventListeners) {
			try {
				lcsEventListener.onLCSEvent(lcsEvent);
			}
			catch (Throwable t) {
				_log.error("Unable to notify listener", t);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SignOffTask.class);

	private final String _key;
	private final List<LCSEventListener> _lcsEventListeners = new ArrayList<>();
	private final LCSGatewayClient _lcsGatewayClient;
	private final boolean _serverManuallyShutdown;

}
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

import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.util.KeyGenerator;
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class SignOffTask implements Task {

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void setHeartbeatInterval(long heartbeatInterval) {
		_heartbeatInterval = heartbeatInterval;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		_keyGenerator = keyGenerator;
	}

	public void setLCSConnectionManager(
		LCSConnectionManager lcsConnectionManager) {

		_lcsConnectionManager = lcsConnectionManager;
	}

	public void setServerManuallyShutdown(boolean serverManuallyShutdown) {
		_serverManuallyShutdown = serverManuallyShutdown;
	}

	protected void doRun() throws PortalException {
		if (_log.isTraceEnabled()) {
			_log.trace("Running sign off task");
		}

		String key = _keyGenerator.getKey();

		HandshakeMessage handshakeMessage = new HandshakeMessage();

		handshakeMessage.put(
			Message.KEY_SERVER_MANUALLY_SHUTDOWN, _serverManuallyShutdown);
		handshakeMessage.put(
			Message.KEY_SIGN_OFF, String.valueOf(_heartbeatInterval));
		handshakeMessage.setKey(key);

		try {
			_lcsConnectionManager.sendMessage(handshakeMessage);

			if (_log.isInfoEnabled()) {
				_log.info("Signed off from LCS platform");
			}
		}
		catch (Exception e) {
			_log.error("Unable to send sign off message", e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SignOffTask.class);

	private long _heartbeatInterval;
	private KeyGenerator _keyGenerator;
	private LCSConnectionManager _lcsConnectionManager;
	private boolean _serverManuallyShutdown;

}
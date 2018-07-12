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
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class SignOffTask implements Task {

	public SignOffTask(String key, LCSConnectionManager lcsConnectionManager) {
		_key = key;
		_lcsConnectionManager = lcsConnectionManager;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void setServerManuallyShutdown(boolean serverManuallyShutdown) {
		_serverManuallyShutdown = serverManuallyShutdown;
	}

	protected void doRun() throws PortalException {
		if (_log.isTraceEnabled()) {
			_log.trace("Running sign off task");
		}

		HandshakeMessage handshakeMessage = new HandshakeMessage();

		handshakeMessage.setServerManuallyShutdown(_serverManuallyShutdown);
		handshakeMessage.setSignOff(true);
		handshakeMessage.setKey(_key);

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

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SignOffTask.class);

	private final String _key;
	private final LCSConnectionManager _lcsConnectionManager;
	private boolean _serverManuallyShutdown;

}
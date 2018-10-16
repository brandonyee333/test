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
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
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

		_taskStateListeners = new ArrayList<>();

		_taskStateListeners.add(lcsGatewayClient);

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void run() {
		if (!_lcsGatewayClient.isAvailable()) {
			if (_log.isTraceEnabled()) {
				_log.trace("LCS gateway is unavailable, skip sign off task");
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
			_lcsGatewayClient.sendMessage(handshakeMessage);

			if (_log.isInfoEnabled()) {
				_log.info("Signed off from LCS platform");
			}

			_notifyOnTaskSuccessTaskStateListeners();
		}
		catch (Exception e) {
			_log.error("Unable to send sign off message", e);

			_notifyOnTaskFailTaskStateListeners();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private void _notifyOnTaskFailTaskStateListeners() {
		for (TaskStateListener taskStateListener : _taskStateListeners) {
			try {
				taskStateListener.onTaskFail(SignOffTask.class, 0);
			}
			catch (Throwable t) {
				_log.error("Unable to notify listener", t);
			}
		}
	}

	private void _notifyOnTaskSuccessTaskStateListeners() {
		for (TaskStateListener taskStateListener : _taskStateListeners) {
			try {
				taskStateListener.onTaskSuccess(SignOffTask.class);
			}
			catch (Throwable t) {
				_log.error("Unable to notify listener", t);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SignOffTask.class);

	private final String _key;
	private final LCSGatewayClient _lcsGatewayClient;
	private final boolean _serverManuallyShutdown;
	private final List<TaskStateListener> _taskStateListeners;

}
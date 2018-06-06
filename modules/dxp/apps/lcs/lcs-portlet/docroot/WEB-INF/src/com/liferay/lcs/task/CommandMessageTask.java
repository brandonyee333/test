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

import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;

import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class CommandMessageTask implements Task {

	public CommandMessageTask(
		String key, LCSConnectionManager lcsConnectionManager) {

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

	protected void doRun() throws Exception {
		if (_log.isTraceEnabled()) {
			_log.trace("Running command message task");
		}

		if (!_lcsConnectionManager.isReady()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Waiting for LCS connection manager");
			}

			return;
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Checking messages for " + _key);
		}

		List<Message> messages = _lcsConnectionManager.getMessages(_key);

		for (Message message : messages) {
			if (_log.isTraceEnabled()) {
				_log.trace("Processing message: " + message);
			}

			if (message instanceof CommandMessage) {
				MessageBusUtil.sendMessage("liferay/lcs_commands", message);

				_lcsConnectionManager.putLCSConnectionMetadata(
					"messageTaskTime",
					String.valueOf(System.currentTimeMillis()));
			}
			else {
				_log.error("Unknown message " + message);
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommandMessageTask.class);

	private final String _key;
	private final LCSConnectionManager _lcsConnectionManager;

}
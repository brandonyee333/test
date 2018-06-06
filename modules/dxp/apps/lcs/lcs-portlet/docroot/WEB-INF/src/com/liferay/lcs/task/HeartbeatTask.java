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

import com.liferay.lcs.messaging.HeartbeatMessage;
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class HeartbeatTask implements Task {

	public HeartbeatTask(
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
			_log.trace("Running heartbeat task");
		}

		if (!_lcsConnectionManager.isReady()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Waiting for LCS connection manager");
			}

			return;
		}

		HeartbeatMessage heartbeatMessage = new HeartbeatMessage();

		heartbeatMessage.setCreateTime(System.currentTimeMillis());
		heartbeatMessage.setKey(_key);

		_lcsConnectionManager.sendMessage(heartbeatMessage);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(HeartbeatTask.class);

	private final String _key;
	private final LCSConnectionManager _lcsConnectionManager;

}
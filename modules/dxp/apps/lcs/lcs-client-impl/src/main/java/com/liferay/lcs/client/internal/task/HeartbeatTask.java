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

import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.HeartbeatMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class HeartbeatTask extends BaseScheduledTask {

	@Override
	public Scope getScope() {
		return Scope.NODE;
	}

	public HeartbeatTask(String key, LCSGatewayClient lcsGatewayClient) {
		_key = key;
		_lcsGatewayClient = lcsGatewayClient;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	protected void doRun() {
		HeartbeatMessage heartbeatMessage = new HeartbeatMessage();

		heartbeatMessage.setCreateTime(System.currentTimeMillis());
		heartbeatMessage.setKey(_key);

		try {
			_lcsGatewayClient.sendMessage(heartbeatMessage);
		}
		catch (Exception e) {
			_log.error("Unable to send heartbeat message to LCS", e);
		}
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
	private final LCSGatewayClient _lcsGatewayClient;

}
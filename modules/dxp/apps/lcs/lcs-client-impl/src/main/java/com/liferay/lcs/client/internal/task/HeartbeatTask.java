/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.exception.CompressionException;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.platform.gateway.LCSGatewayException;
import com.liferay.lcs.messaging.HeartbeatMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class HeartbeatTask extends BaseTask {

	public HeartbeatTask(String key, LCSGatewayClient lcsGatewayClient) {
		_key = key;
		_lcsGatewayClient = lcsGatewayClient;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.MANAGEABLE;
	}

	protected void doRun() throws CompressionException, LCSGatewayException {
		HeartbeatMessage heartbeatMessage = new HeartbeatMessage();

		heartbeatMessage.setCreateTime(System.currentTimeMillis());
		heartbeatMessage.setKey(_key);

		_lcsGatewayClient.sendMessage(heartbeatMessage);
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
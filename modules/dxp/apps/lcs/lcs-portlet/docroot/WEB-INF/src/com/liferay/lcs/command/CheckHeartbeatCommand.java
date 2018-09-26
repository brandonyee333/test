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

package com.liferay.lcs.command;

import com.liferay.lcs.advisor.LCSKeyAdvisor;
import com.liferay.lcs.messaging.CheckHeartbeatCommandMessage;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.task.HeartbeatTask;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Ivica Cardic
 */
public class CheckHeartbeatCommand
	implements Command<CheckHeartbeatCommandMessage> {

	public CheckHeartbeatCommand(
		LCSGatewayClient lcsGatewayClient, LCSKeyAdvisor lcsKeyAdvisor) {

		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void execute(
		CheckHeartbeatCommandMessage checkHeartbeatCommandMessage) {

		if (_log.isTraceEnabled()) {
			_log.trace("Executing check heartbeat command");
		}

		HeartbeatTask heartbeatTask = new HeartbeatTask(
			_lcsKeyAdvisor.getKey(), _lcsGatewayClient);

		heartbeatTask.run();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CheckHeartbeatCommand.class);

	private final LCSGatewayClient _lcsGatewayClient;
	private final LCSKeyAdvisor _lcsKeyAdvisor;

}
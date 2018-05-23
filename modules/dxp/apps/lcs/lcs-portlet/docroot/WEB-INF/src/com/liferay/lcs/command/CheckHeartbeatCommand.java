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

import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.task.HeartbeatTask;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivica Cardic
 */
public class CheckHeartbeatCommand implements Command {

	public void destroy() {
		_executorService.shutdown();

		try {
			if (!_executorService.awaitTermination(5, TimeUnit.SECONDS)) {
				_executorService.shutdownNow();
			}
		}
		catch (InterruptedException ie) {
			_executorService.shutdownNow();
		}
	}

	@Override
	public void execute(CommandMessage commandMessage) {
		if (_log.isTraceEnabled()) {
			_log.trace("Executing check heartbeat command");
		}

		_executorService.execute(_heartbeatTask);
	}

	public void setHeartbeatTask(HeartbeatTask heartbeatTask) {
		_heartbeatTask = heartbeatTask;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CheckHeartbeatCommand.class);

	private final ExecutorService _executorService =
		Executors.newCachedThreadPool();
	private HeartbeatTask _heartbeatTask;

}
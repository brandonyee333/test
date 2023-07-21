/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.internal.messaging.advisor.MessageBusAdvisor;
import com.liferay.lcs.messaging.ScheduleMessageListenersCommandMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class ScheduleMessageListenersTask extends BaseTask {

	public ScheduleMessageListenersTask(
		MessageBusAdvisor messageBusAdvisor,
		ScheduleMessageListenersCommandMessage
			scheduleMessageListenersCommandMessage) {

		_messageBusAdvisor = messageBusAdvisor;
		_scheduleMessageListenersCommandMessage =
			scheduleMessageListenersCommandMessage;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void doRun() {
		List<Map<String, String>> schedulerContexts =
			_scheduleMessageListenersCommandMessage.getSchedulerContexts();

		if ((schedulerContexts == null) || schedulerContexts.isEmpty()) {
			return;
		}

		Collections.shuffle(schedulerContexts);

		for (Map<String, String> schedulerContext : schedulerContexts) {
			scheduleMessageListener(schedulerContext);
		}
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.COMMAND;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	protected void scheduleMessageListener(
		Map<String, String> schedulerContext) {

		_messageBusAdvisor.registerMessageListener(schedulerContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ScheduleMessageListenersTask.class);

	private final MessageBusAdvisor _messageBusAdvisor;
	private final ScheduleMessageListenersCommandMessage
		_scheduleMessageListenersCommandMessage;

}
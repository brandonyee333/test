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
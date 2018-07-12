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

import com.liferay.lcs.messaging.ScheduleMessageListenersCommandMessage;
import com.liferay.lcs.messaging.scheduler.MessageListenerSchedulerService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class ScheduleMessageListenersCommand
	implements Command<ScheduleMessageListenersCommandMessage> {

	@Override
	public void execute(
		ScheduleMessageListenersCommandMessage
			scheduleMessageListenersCommandMessage) {

		if (_log.isTraceEnabled()) {
			_log.trace("Executing schedule message listeners command");
		}

		List<Map<String, String>> schedulerContexts =
			scheduleMessageListenersCommandMessage.getSchedulerContexts();

		Collections.shuffle(schedulerContexts);

		if (schedulerContexts != null) {
			for (Map<String, String> schedulerContext : schedulerContexts) {
				scheduleMessageListener(schedulerContext);
			}
		}
	}

	public void setMessageListenerSchedulerService(
		MessageListenerSchedulerService messageListenerSchedulerService) {

		_messageListenerSchedulerService = messageListenerSchedulerService;
	}

	protected void scheduleMessageListener(
		Map<String, String> schedulerContext) {

		_messageListenerSchedulerService.scheduleMessageListener(
			schedulerContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ScheduleMessageListenersCommand.class);

	private MessageListenerSchedulerService _messageListenerSchedulerService;

}
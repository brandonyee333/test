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

package com.liferay.lcs.client.internal.command;

import com.liferay.lcs.client.internal.messaging.advisor.MessageBusAdvisor;
import com.liferay.lcs.messaging.ScheduleMessageListenersCommandMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(service = ScheduleMessageListenersCommand.class)
public class ScheduleMessageListenersCommand
	implements Command<ScheduleMessageListenersCommandMessage> {

	public ScheduleMessageListenersCommand() {
	}

	public ScheduleMessageListenersCommand(
		MessageBusAdvisor messageBusAdvisor) {

		_messageBusAdvisor = messageBusAdvisor;
	}

	@Override
	public void execute(
		ScheduleMessageListenersCommandMessage
			scheduleMessageListenersCommandMessage) {

		if (_log.isTraceEnabled()) {
			_log.trace("Executing schedule message listeners command");
		}

		List<Map<String, String>> schedulerContexts =
			scheduleMessageListenersCommandMessage.getSchedulerContexts();

		if ((schedulerContexts == null) || schedulerContexts.isEmpty()) {
			return;
		}

		Collections.shuffle(schedulerContexts);

		for (Map<String, String> schedulerContext : schedulerContexts) {
			scheduleMessageListener(schedulerContext);
		}
	}

	protected void scheduleMessageListener(
		Map<String, String> schedulerContext) {

		_messageBusAdvisor.registerMessageListener(schedulerContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ScheduleMessageListenersCommand.class);

	@Reference
	private MessageBusAdvisor _messageBusAdvisor;

}
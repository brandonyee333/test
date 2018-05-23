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
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public class ScheduleTasksCommand implements Command {

	@Override
	public void execute(CommandMessage commandMessage) throws PortalException {
		if (_log.isTraceEnabled()) {
			_log.trace("Executing schedule tasks command");
		}

		Map<String, List<Map<String, String>>> prioritySchedulerContexts =
			(Map<String, List<Map<String, String>>>)commandMessage.getPayload();

		List<String> priorityKeys = new ArrayList<>(
			prioritySchedulerContexts.keySet());

		Collections.sort(priorityKeys);

		for (String priorityKey : priorityKeys) {
			List<Map<String, String>> schedulerContexts =
				prioritySchedulerContexts.get(priorityKey);

			Collections.shuffle(schedulerContexts);

			if (schedulerContexts != null) {
				for (Map<String, String> schedulerContext : schedulerContexts) {
					scheduleTask(schedulerContext);
				}
			}
		}
	}

	public void setTaskSchedulerService(
		TaskSchedulerService taskSchedulerService) {

		_taskSchedulerService = taskSchedulerService;
	}

	protected void scheduleTask(Map<String, String> schedulerContext)
		throws PortalException {

		_taskSchedulerService.scheduleTask(schedulerContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ScheduleTasksCommand.class);

	private TaskSchedulerService _taskSchedulerService;

}
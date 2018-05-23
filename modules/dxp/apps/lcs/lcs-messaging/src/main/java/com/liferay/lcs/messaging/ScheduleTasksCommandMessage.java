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

package com.liferay.lcs.messaging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class ScheduleTasksCommandMessage extends CommandMessage {

	public Map<String, List<Map<String, String>>>
		getPrioritySchedulerContexts() {

		return _prioritySchedulerContexts;
	}

	public void setPrioritySchedulerContexts(
		Map<String, List<Map<String, String>>> prioritySchedulerContexts) {

		_prioritySchedulerContexts = prioritySchedulerContexts;
	}

	private Map<String, List<Map<String, String>>> _prioritySchedulerContexts =
		new HashMap<String, List<Map<String, String>>>();

}
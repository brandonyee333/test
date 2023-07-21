/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class ScheduleMessageListenersCommandMessage extends CommandMessage {

	public List<Map<String, String>> getSchedulerContexts() {
		return _schedulerContexts;
	}

	public boolean hasSchedulerContexts() {
		if (_schedulerContexts.isEmpty()) {
			return false;
		}

		return true;
	}

	public void setSchedulerContexts(
		List<Map<String, String>> schedulerContexts) {

		_schedulerContexts.clear();

		for (Map<String, String> schedulerContext : schedulerContexts) {
			Map<String, String> immutableSchedulerContext =
				new HashMap<String, String>();

			for (Map.Entry<String, String> entry :
					schedulerContext.entrySet()) {

				immutableSchedulerContext.put(entry.getKey(), entry.getValue());
			}

			_schedulerContexts.add(immutableSchedulerContext);
		}
	}

	private final List<Map<String, String>> _schedulerContexts =
		new ArrayList<Map<String, String>>();

}
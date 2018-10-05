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
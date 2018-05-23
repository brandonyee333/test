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
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class ScheduleMessageListenersCommandMessage extends CommandMessage {

	public List<Map<String, String>> getSchedulerContexts() {
		return _schedulerContexts;
	}

	public void setSchedulerContexts(
		List<Map<String, String>> schedulerContexts) {

		_schedulerContexts = schedulerContexts;
	}

	private List<Map<String, String>> _schedulerContexts =
		new ArrayList<Map<String, String>>();

}
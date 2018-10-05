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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Igor Beslic
 */
@RunWith(JUnit4.class)
public class ScheduleMessageListenersCommandMessageTest {

	@Test
	public void testSetSchedulerContexts() {
		ScheduleMessageListenersCommandMessage
			scheduleMessageListenersCommandMessage =
				new ScheduleMessageListenersCommandMessage();

		List<Map<String, String>> schedulerContexts =
			_getRandomSchedulerContexts(5);

		scheduleMessageListenersCommandMessage.setSchedulerContexts(
			schedulerContexts);

		Assert.assertEquals(
			"Scheduler contexts equal", schedulerContexts,
			scheduleMessageListenersCommandMessage.getSchedulerContexts());

		schedulerContexts.remove(2);

		Assert.assertNotEquals(
			"Scheduler contexts unmodified after origin changed",
			schedulerContexts,
			scheduleMessageListenersCommandMessage.getSchedulerContexts());
	}

	private List<Map<String, String>> _getRandomSchedulerContexts(
		int contextsSize) {

		List<Map<String, String>> schedulerContexts =
			new ArrayList<Map<String, String>>();

		for (int i = 0; i < contextsSize; i++) {
			Map<String, String> schedulerContext =
				new HashMap<String, String>();

			schedulerContext.put("period", "period" + i);
			schedulerContext.put("taskClass", "taskClass" + i);
			schedulerContext.put("taskScope", "scope" + i);

			schedulerContexts.add(schedulerContext);
		}

		return schedulerContexts;
	}

}
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
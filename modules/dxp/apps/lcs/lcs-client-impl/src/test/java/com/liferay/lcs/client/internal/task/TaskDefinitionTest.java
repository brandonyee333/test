/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class TaskDefinitionTest {

	@Test
	public void testEquals() {
		TaskDefinition taskDefinitionA = new TaskDefinition(
			0, 0, 0, new SendPortalPropertiesTask(null, null, null));

		TaskDefinition taskDefinitionB = new TaskDefinition(
			0, 0, 0, new SendPortalPropertiesTask(null, null, null));

		Assert.assertEquals(
			"Task definitions", taskDefinitionA, taskDefinitionB);

		taskDefinitionB = new TaskDefinition(0, 0, 0, new LicenseManagerTask());

		Assert.assertNotEquals(
			"Task definitions", taskDefinitionA, taskDefinitionB);
	}

	@Test
	public void testHashCode() {
		TaskDefinition taskDefinitionA = new TaskDefinition(
			0, 0, 0, new SendPortalPropertiesTask(null, null, null));

		TaskDefinition taskDefinitionB = new TaskDefinition(
			0, 0, 0, new SendPortalPropertiesTask(null, null, null));

		Assert.assertEquals(
			"Task definitions", taskDefinitionA.hashCode(),
			taskDefinitionB.hashCode());

		taskDefinitionB = new TaskDefinition(0, 0, 0, new LicenseManagerTask());

		Assert.assertNotEquals(
			"Task definitions", taskDefinitionA.hashCode(),
			taskDefinitionB.hashCode());
	}

	@Test
	public void testIsOnSchedule() throws Exception {
		_testIsOnSchedule(2000, 500);
	}

	@Test
	public void testIsOnScheduleIfNoDelay() throws Exception {
		_testIsOnSchedule(0, 500);
	}

	@Test
	public void testIsOnScheduleIfNoDelayAndNoPeriod() throws Exception {
		_testIsOnSchedule(0, 0);
	}

	@Test
	public void testIsOnScheduleIfNoPeriod() throws Exception {
		_testIsOnSchedule(2000, 0);
	}

	private void _testIsOnSchedule(long expectedDelay, long period)
		throws Exception {

		TaskDefinition taskDefinition = new TaskDefinition(
			expectedDelay, period, 0, new HeartbeatTask("test-key", null));

		long submitTime = System.currentTimeMillis();

		while (!taskDefinition.isOnSchedule()) {
			Thread.sleep(100);
		}

		long executeTime = System.currentTimeMillis();

		Assert.assertTrue(
			"The first task execution delayed",
			(executeTime - submitTime) >= expectedDelay);
	}

}
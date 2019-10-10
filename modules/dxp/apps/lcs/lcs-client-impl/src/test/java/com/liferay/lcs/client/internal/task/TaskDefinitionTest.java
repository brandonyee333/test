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
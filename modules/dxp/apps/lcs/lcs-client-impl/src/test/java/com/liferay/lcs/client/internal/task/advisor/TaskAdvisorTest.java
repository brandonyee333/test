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

package com.liferay.lcs.client.internal.task.advisor;

import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.osgi.framework.MockBundleContext;
import com.liferay.lcs.client.internal.task.CommandMessageCheckTask;
import com.liferay.lcs.client.internal.task.CommandQueueCheckTask;
import com.liferay.lcs.client.internal.task.HandshakeTask;
import com.liferay.lcs.client.internal.task.HeartbeatTask;
import com.liferay.lcs.client.internal.task.LCSClusterEntryTokenCheckTask;
import com.liferay.lcs.client.internal.task.Task;
import com.liferay.lcs.client.internal.task.TaskDefinition;
import com.liferay.lcs.client.internal.task.UptimeTask;
import com.liferay.portal.kernel.util.Time;

import java.util.Dictionary;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.BundleContext;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 * @author Ivica Cartdic
 */
@RunWith(PowerMockRunner.class)
public class TaskAdvisorTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		_bundleContext = new MockBundleContext<>();

		CommandQueueCheckTask commandQueueCheckTask =
			new CommandQueueCheckTask();

		Properties properties = new Properties();

		properties.put("component.name", CommandQueueCheckTask.class.getName());

		_bundleContext.registerService(
			Task.class.getName(), commandQueueCheckTask,
			(Dictionary)properties);

		_lcsKeyAdvisor = mock(LCSKeyAdvisor.class);
	}

	@Test
	public void testGetCommandMessageCheckTaskDefinition() {
		TaskAdvisor taskAdvisor = _getTaskAdvisor();

		TaskDefinition taskDefinition =
			taskAdvisor.getCommandMessageCheckTaskDefinition();

		Task task = taskDefinition.getTask();

		Assert.assertEquals(
			"Task class value", CommandMessageCheckTask.class, task.getClass());
	}

	@Test
	public void testGetCommandQueueCheckTaskDefinition() {
		TaskAdvisor taskAdvisor = _getTaskAdvisor();

		TaskDefinition taskDefinition =
			taskAdvisor.getCommandQueueCheckTaskDefinition();

		Task task = taskDefinition.getTask();

		Assert.assertEquals(
			"Task class value", CommandQueueCheckTask.class, task.getClass());
	}

	@Test
	public void testGetHandshakeTaskDefinition() {
		TaskAdvisor taskAdvisor = _getTaskAdvisor();

		TaskDefinition taskDefinition = taskAdvisor.getHandshakeTaskDefinition(
			false);

		Task task = taskDefinition.getTask();

		Assert.assertEquals(
			"Task class value", HandshakeTask.class, task.getClass());

		Assert.assertEquals(
			"Task definition initial delay value", 0,
			taskDefinition.getInitialDelay());

		taskDefinition = taskAdvisor.getHandshakeTaskDefinition(true);

		Assert.assertEquals(
			"Task definition initial delay value", Time.MINUTE,
			taskDefinition.getInitialDelay());
	}

	@Test
	public void testGetHeartBeatTaskDefinition() {
		TaskAdvisor taskAdvisor = _getTaskAdvisor();

		TaskDefinition taskDefinition =
			taskAdvisor.getHeartBeatTaskDefinition();

		Task task = taskDefinition.getTask();

		Assert.assertEquals(
			"Task class value", HeartbeatTask.class, task.getClass());
	}

	@Test
	public void testGetLCSClusterEntryTokenCheckTaskDefinition() {
		TaskAdvisor taskAdvisor = _getTaskAdvisor();

		TaskDefinition taskDefinition =
			taskAdvisor.getLCSClusterEntryTokenCheckTaskDefinition(false);

		Task task = taskDefinition.getTask();

		Assert.assertEquals(
			"Task class value", LCSClusterEntryTokenCheckTask.class,
			task.getClass());

		Assert.assertEquals(
			"Task definition initial delay value", 0,
			taskDefinition.getInitialDelay());

		taskDefinition = taskAdvisor.getLCSClusterEntryTokenCheckTaskDefinition(
			true);

		Assert.assertEquals(
			"Task definition initial delay value", Time.MINUTE,
			taskDefinition.getInitialDelay());
	}

	@Test
	public void testGetUptimeTaskDefinition() {
		TaskAdvisor taskAdvisor = _getTaskAdvisor();

		TaskDefinition taskDefinition = taskAdvisor.getUptimeTaskDefinition();

		Task task = taskDefinition.getTask();

		Assert.assertEquals(
			"Task class value", UptimeTask.class, task.getClass());

		Assert.assertEquals(
			"Task definition initial delay value", 10 * Time.SECOND,
			taskDefinition.getInitialDelay());

		Assert.assertEquals(
			"Task definition period value", Time.MINUTE,
			taskDefinition.getPeriod());
	}

	private TaskAdvisor _getTaskAdvisor() {
		return new TaskAdvisor(
			_bundleContext, new CommandMessageCheckTask(), new HandshakeTask(),
			new LCSClusterEntryTokenCheckTask(), _lcsKeyAdvisor,
			new UptimeTask());
	}

	private BundleContext _bundleContext;
	private LCSKeyAdvisor _lcsKeyAdvisor;

}
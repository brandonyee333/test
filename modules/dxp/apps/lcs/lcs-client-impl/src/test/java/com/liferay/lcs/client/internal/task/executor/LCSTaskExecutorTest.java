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

package com.liferay.lcs.client.internal.task.executor;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.BasePowerMockitoTestCase;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.runnable.LCSThreadFactory;
import com.liferay.lcs.client.internal.task.TaskDefinition;
import com.liferay.lcs.client.internal.task.TaskType;
import com.liferay.lcs.client.internal.task.TestTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@PrepareForTest(LCSTaskExecutor.class)
@RunWith(PowerMockRunner.class)
public class LCSTaskExecutorTest extends BasePowerMockitoTestCase {

	@Before
	public void setUp() {
		_threadFactory = new LCSThreadFactory();
	}

	@Test
	public void testCancelNonRequiredTasks() throws Exception {
		List<LCSEvent> cancelingEvents = new ArrayList<>();

		cancelingEvents.add(LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED);
		cancelingEvents.add(LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED);
		cancelingEvents.add(LCSEvent.LCS_GATEWAY_UNAVAILABLE);
		cancelingEvents.add(LCSEvent.SIGN_OFF_SUCCESS);

		Assert.assertFalse(
			"LCS event list is not empty", cancelingEvents.isEmpty());

		for (LCSEvent cancelingEvent : cancelingEvents) {
			_testCancelNonRequiredTaskes(cancelingEvent);
		}
	}

	@Test
	public void testCancelRequiredTasks() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSTaskExecutor lcsTaskExecutor = new LCSTaskExecutor(
			lcsEventManager, _threadFactory);

		TestTask requiredTestTask = new TestTask(TaskType.REQUIRED);

		TaskDefinition requiredTaskDefinition = new TaskDefinition(
			0, 100, 0, requiredTestTask);

		lcsTaskExecutor.submit(requiredTaskDefinition);

		TestTask onlineRequiredTestTask = new TestTask(
			TaskType.ONLINE_REQUIRED);

		TaskDefinition onlineRequiredTaskDefinition = new TaskDefinition(
			0, 200, 0, onlineRequiredTestTask);

		lcsTaskExecutor.submit(onlineRequiredTaskDefinition);

		_simulateOperationalState(lcsTaskExecutor, 2000);

		lcsTaskExecutor.deactivate();

		long requiredTaskExecutionCounter =
			requiredTestTask.getExecutionCounter();
		long onlineRequiredTaskExecutionCounter =
			onlineRequiredTestTask.getExecutionCounter();

		Thread.sleep(300);

		Assert.assertEquals(
			"Required task execution counter value",
			requiredTaskExecutionCounter,
			requiredTestTask.getExecutionCounter());

		Assert.assertEquals(
			"Required online task execution counter value",
			onlineRequiredTaskExecutionCounter,
			onlineRequiredTestTask.getExecutionCounter());

		Set<String> activeTaskSimpleClassNames =
			lcsTaskExecutor.getActiveTaskSimpleClassNames();

		Assert.assertTrue(
			"Active task simple class names empty",
			activeTaskSimpleClassNames.isEmpty());
	}

	@Test
	public void testDeactivate() throws Exception {
		LCSTaskExecutor lcsTaskExecutor = new LCSTaskExecutor(
			new LCSEventManager(), _threadFactory);

		TestTask requiredTestTask = new TestTask(TaskType.REQUIRED);

		TaskDefinition requiredTaskDefinition = new TaskDefinition(
			0, 50, 0, requiredTestTask);

		lcsTaskExecutor.submit(requiredTaskDefinition);

		Thread.sleep(200);

		Set<String> activeTaskSimpleClassNames =
			lcsTaskExecutor.getActiveTaskSimpleClassNames();

		Assert.assertFalse(
			"Active task simple class names is empty",
			activeTaskSimpleClassNames.isEmpty());

		lcsTaskExecutor.deactivate();

		long executionCounter = requiredTestTask.getExecutionCounter();

		Thread.sleep(200);

		Assert.assertEquals(
			"Required task execution counter value", executionCounter,
			requiredTestTask.getExecutionCounter());

		activeTaskSimpleClassNames =
			lcsTaskExecutor.getActiveTaskSimpleClassNames();

		Assert.assertTrue(
			"Active task simple class names is empty",
			activeTaskSimpleClassNames.isEmpty());
	}

	@Test
	public void testGetActiveServiceLabels() throws Exception {
		LCSTaskExecutor lcsTaskExecutor = new LCSTaskExecutor(
			new LCSEventManager(), _threadFactory);

		TestTask testTask = new TestTask(TaskType.MANAGEABLE);

		TaskDefinition taskDefinition = new TaskDefinition(
			1000, 0, 0, testTask);

		Set<String> activeTaskSimpleClassNames =
			lcsTaskExecutor.getActiveTaskSimpleClassNames();

		Assert.assertTrue(
			"Active task simple class names empty",
			activeTaskSimpleClassNames.isEmpty());

		lcsTaskExecutor.submit(taskDefinition);

		_simulateOperationalState(lcsTaskExecutor, 900);

		activeTaskSimpleClassNames =
			lcsTaskExecutor.getActiveTaskSimpleClassNames();

		Assert.assertTrue(
			"Active task simple class names empty",
			activeTaskSimpleClassNames.isEmpty());

		_simulateOperationalState(lcsTaskExecutor, 2000);

		activeTaskSimpleClassNames =
			lcsTaskExecutor.getActiveTaskSimpleClassNames();

		Assert.assertFalse(
			"Active task simple class names empty",
			activeTaskSimpleClassNames.isEmpty());

		Class<? extends TestTask> testTaskClass = testTask.getClass();

		Assert.assertTrue(
			"Executed task name present in active task simple class names",
			activeTaskSimpleClassNames.contains(testTaskClass.getSimpleName()));

		lcsTaskExecutor.deactivate();
	}

	@Test(expected = NullPointerException.class)
	public void testNPEAfterDeactivate() {
		LCSTaskExecutor lcsTaskExecutor = new LCSTaskExecutor(
			new LCSEventManager(), _threadFactory);

		TestTask requiredTestTask = new TestTask(TaskType.REQUIRED);

		TaskDefinition requiredTaskDefinition = new TaskDefinition(
			0, 300, 0, requiredTestTask);

		lcsTaskExecutor.submit(requiredTaskDefinition);

		lcsTaskExecutor.deactivate();

		lcsTaskExecutor.submit(new TaskDefinition(0, 0, 0, requiredTestTask));
	}

	@Test
	public void testSubmit() throws Exception {
		LCSTaskExecutor lcsTaskExecutor = new LCSTaskExecutor(
			new LCSEventManager(), _threadFactory);

		TestTask testTask = new TestTask(TaskType.MANAGEABLE);

		TaskDefinition taskDefinition = new TaskDefinition(
			1000, 0, 0, testTask);

		long submitTime = System.currentTimeMillis();

		Assert.assertEquals(
			"Task execution counter value", 0, testTask.getExecutionCounter());

		lcsTaskExecutor.submit(taskDefinition);

		_simulateOperationalState(lcsTaskExecutor, 900);

		Assert.assertEquals(
			"Task execution counter value", 0, testTask.getExecutionCounter());

		_simulateOperationalState(lcsTaskExecutor, 2000);

		Assert.assertTrue(
			"Task is setExecuted",
			(taskDefinition.getLastExecute() - submitTime) >= 1000);
		Assert.assertEquals(
			"Task execution counter value", 1, testTask.getExecutionCounter());

		Set<String> activeTaskSimpleClassNames =
			lcsTaskExecutor.getActiveTaskSimpleClassNames();

		Class<? extends TestTask> testTaskClass = testTask.getClass();

		Assert.assertTrue(
			"Executed task name present in active task simple class names",
			activeTaskSimpleClassNames.contains(testTaskClass.getSimpleName()));

		lcsTaskExecutor.deactivate();
	}

	@Test
	public void testSubmitCommand() throws Exception {
		LCSTaskExecutor lcsTaskExecutor = new LCSTaskExecutor(
			new LCSEventManager(), _threadFactory);

		TestTask testTask = new TestTask(TaskType.COMMAND);

		TaskDefinition taskDefinition = new TaskDefinition(
			1000, 0, 0, testTask);

		long submitTime = System.currentTimeMillis();

		Assert.assertEquals(
			"Task execution counter value", 0, testTask.getExecutionCounter());

		lcsTaskExecutor.submit(taskDefinition);

		_simulateOperationalState(lcsTaskExecutor, 900);

		Assert.assertEquals(
			"Task execution counter value", 0, testTask.getExecutionCounter());

		_simulateOperationalState(lcsTaskExecutor, 900);

		Assert.assertTrue(
			"Task is setExecuted",
			(taskDefinition.getLastExecute() - submitTime) >= 1000);
		Assert.assertEquals(
			"Task execution counter value", 1, testTask.getExecutionCounter());

		lcsTaskExecutor.deactivate();
	}

	@Test
	public void testSubmitIfPeriodSet() throws Exception {
		LCSTaskExecutor lcsTaskExecutor = new LCSTaskExecutor(
			new LCSEventManager(), _threadFactory);

		TestTask testTask = new TestTask(TaskType.MANAGEABLE);

		TaskDefinition taskDefinition = new TaskDefinition(
			1000, 200, 0, testTask);

		long submitTime = System.currentTimeMillis();

		Assert.assertEquals(
			"Task execution counter value", 0, testTask.getExecutionCounter());

		lcsTaskExecutor.submit(taskDefinition);

		Assert.assertEquals(
			"Task execution counter value", 0, testTask.getExecutionCounter());

		_simulateOperationalState(lcsTaskExecutor, 1900);

		Assert.assertTrue(
			"Task is setExecuted",
			(taskDefinition.getLastExecute() - submitTime) > 0);
		Assert.assertEquals(
			"Task execution counter value", 5, testTask.getExecutionCounter());

		lcsTaskExecutor.deactivate();
	}

	private void _simulateOperationalState(
			LCSTaskExecutor lcsTaskExecutor, long millisecondsToRun)
		throws Exception {

		long lap = System.currentTimeMillis();

		while ((System.currentTimeMillis() - lap) < millisecondsToRun) {
			lcsTaskExecutor.flush();

			Thread.sleep(50);
		}
	}

	private void _testCancelNonRequiredTaskes(LCSEvent lcsEvent)
		throws Exception {

		LCSTaskExecutor lcsTaskExecutor = spy(
			new LCSTaskExecutor(new LCSEventManager(), _threadFactory));

		verifyPrivate(
			lcsTaskExecutor, Mockito.never()
		).invoke(
			"_cancelNonrequiredTasks"
		);

		lcsTaskExecutor.onLCSEvent(lcsEvent);

		verifyPrivate(
			lcsTaskExecutor, Mockito.times(1)
		).invoke(
			"_cancelNonrequiredTasks"
		);

		verifyPrivate(
			lcsTaskExecutor, Mockito.never()
		).invoke(
			"_cancelAllTasks"
		);
	}

	private ThreadFactory _threadFactory;

}
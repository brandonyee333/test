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

/**
 * @author Igor Beslic
 */
public class TestTask implements Task {

	public TestTask() {
		_taskType = TaskType.MANAGEABLE;
	}

	public TestTask(TaskType taskType) {
		_taskType = taskType;
	}

	public long getExecutionCounter() {
		return _executionCounter;
	}

	@Override
	public TaskType getTaskType() {
		return _taskType;
	}

	@Override
	public void run() {
		_executionCounter++;
	}

	@Override
	public String toString() {
		return "TestTask{ _executionCounter=" + _executionCounter + '}';
	}

	private long _executionCounter;
	private final TaskType _taskType;

}
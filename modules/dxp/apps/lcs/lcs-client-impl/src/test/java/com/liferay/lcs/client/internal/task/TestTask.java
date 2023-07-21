/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
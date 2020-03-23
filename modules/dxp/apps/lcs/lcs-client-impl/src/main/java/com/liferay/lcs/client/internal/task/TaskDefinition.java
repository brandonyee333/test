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

import com.liferay.petra.string.StringBundler;

import java.util.Objects;

/**
 * @author Igor Beslic
 */
public class TaskDefinition {

	public TaskDefinition(
		long initialDelay, long period, int priority, Task task) {

		if (task == null) {
			throw new IllegalArgumentException(
				"Unable to create task definition if task is null");
		}

		_initialDelay = initialDelay;
		_period = period;
		_priority = priority;
		_task = task;

		_lastExecute = System.currentTimeMillis() + _initialDelay - _period;

		Class<? extends Task> taskClass = task.getClass();

		_taskName = taskClass.getName();
	}

	public boolean equals(Class clazz) {
		return _taskName.equals(clazz.getName());
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TaskDefinition)) {
			return false;
		}

		TaskDefinition other = (TaskDefinition)object;

		return Objects.equals(_taskName, other._taskName);
	}

	public long getInitialDelay() {
		return _initialDelay;
	}

	public long getLastExecute() {
		return _lastExecute;
	}

	public long getPeriod() {
		return _period;
	}

	public int getPriority() {
		return _priority;
	}

	public Task getTask() {
		return _task;
	}

	public String getTaskName() {
		return _taskName;
	}

	@Override
	public int hashCode() {
		return _taskName.hashCode();
	}

	public boolean hasValidTask() {
		if (_task == null) {
			return false;
		}

		return true;
	}

	public boolean isOnSchedule() {
		if (System.currentTimeMillis() >= (_lastExecute + _period)) {
			return true;
		}

		return false;
	}

	public boolean isRepeatable() {
		if (_period == 0) {
			return false;
		}

		return true;
	}

	public boolean isTaskType(TaskType taskType) {
		if (taskType == _task.getTaskType()) {
			return true;
		}

		return false;
	}

	public void setExecuted() {
		_lastExecute = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{className=");

		Class<?> clazz = getClass();

		sb.append(clazz.getName());

		sb.append(", initialDelay=");
		sb.append(_initialDelay);
		sb.append(", lastExecute=");
		sb.append(_lastExecute);
		sb.append(", period=");
		sb.append(_period);
		sb.append(", priority=");
		sb.append(_priority);
		sb.append(", task=");
		sb.append(_task);
		sb.append(", taskName=");
		sb.append(_taskName);
		sb.append("}");

		return sb.toString();
	}

	private final long _initialDelay;
	private long _lastExecute = Long.MIN_VALUE;
	private final long _period;
	private final int _priority;
	private final Task _task;
	private final String _taskName;

}
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.internal.command.queue.CommandQueue;
import com.liferay.lcs.client.internal.task.advisor.TaskAdvisor;
import com.liferay.lcs.client.internal.task.comparator.TaskDefinitionPriorityComparator;
import com.liferay.lcs.client.internal.task.executor.LCSTaskExecutor;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	name = "com.liferay.lcs.client.internal.task.CommandQueueCheckTask",
	service = Task.class
)
public class CommandQueueCheckTask extends BaseTask {

	public CommandQueueCheckTask() {
		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	public CommandQueueCheckTask(
		CommandQueue commandQueue, LCSTaskExecutor lcsTaskExecutor,
		TaskAdvisor taskAdvisor) {

		_commandQueue = commandQueue;
		_lcsTaskExecutor = lcsTaskExecutor;
		_taskAdvisor = taskAdvisor;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.ONLINE_REQUIRED;
	}

	@Activate
	protected void activate() {
		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	protected void doRun() {
		if (_log.isTraceEnabled()) {
			_log.trace("Checking command message queue");
		}

		Set<TaskDefinition> taskDefinitions = new TreeSet<>(
			new TaskDefinitionPriorityComparator());

		Message message = null;

		while ((message = _commandQueue.next()) != null) {
			List<TaskDefinition> commandTaskDefinitions =
				_taskAdvisor.getTaskDefinitions((CommandMessage)message);

			TaskDefinition signOffTaskDefinition =
				_taskAdvisor.findSignOffTaskDefinition(commandTaskDefinitions);

			if (signOffTaskDefinition != null) {
				taskDefinitions.clear();

				taskDefinitions.add(signOffTaskDefinition);

				_lcsTaskExecutor.process(taskDefinitions);

				if (_log.isDebugEnabled()) {
					_log.debug("SignOff task encounter. Leaving queue check.");
				}

				return;
			}

			taskDefinitions.addAll(commandTaskDefinitions);
		}

		if (!taskDefinitions.isEmpty()) {
			_lcsTaskExecutor.process(taskDefinitions);
		}

		_notifyLCSTaskExecutor();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private void _notifyLCSTaskExecutor() {
		_lcsTaskExecutor.flush();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommandQueueCheckTask.class);

	@Reference
	private CommandQueue _commandQueue;

	@Reference
	private LCSTaskExecutor _lcsTaskExecutor;

	@Reference
	private TaskAdvisor _taskAdvisor;

}
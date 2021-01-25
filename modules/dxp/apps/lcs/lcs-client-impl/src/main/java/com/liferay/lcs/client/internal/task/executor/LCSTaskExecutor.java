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
import com.liferay.lcs.client.event.LCSEventListener;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.task.Task;
import com.liferay.lcs.client.internal.task.TaskDefinition;
import com.liferay.lcs.client.internal.task.TaskType;
import com.liferay.lcs.client.task.TaskStatus;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 * @author Igor Beslic
 */
@Component(
	immediate = true,
	property = {"osgi.command.scope=lcs", "osgi.command.function=taskStatus"},
	service = {LCSTaskExecutor.class, TaskStatus.class}
)
public class LCSTaskExecutor implements LCSEventListener, TaskStatus {

	public LCSTaskExecutor() {
	}

	public LCSTaskExecutor(
		LCSEventManager lcsEventManager, ThreadFactory threadFactory) {

		_lcsEventManager = lcsEventManager;
		_threadFactory = threadFactory;

		_initExecutorService();

		_subscribeToLCSEvents();
	}

	public void flush() {
		for (TaskDefinition delayedTaskDefinition : _delayedTaskDefinitions) {
			submit(delayedTaskDefinition);
		}

		for (TaskDefinition taskDefinition : _repeatableTaskDefinitions) {
			submit(taskDefinition);
		}
	}

	@Override
	public Set<String> getActiveTaskSimpleClassNames() {
		return new HashSet<>(_activeTaskClassNames);
	}

	@Override
	public void onLCSEvent(LCSEvent lcsEvent) {
		if (_deactivatePending.get()) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting event processing. Deactivate is in progress.");
			}

			return;
		}

		if ((lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED) ||
			(lcsEvent == LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED) ||
			(lcsEvent == LCSEvent.LCS_GATEWAY_UNAVAILABLE) ||
			(lcsEvent == LCSEvent.SIGN_OFF_SUCCESS)) {

			_cancelNonrequiredTasks();
		}
	}

	public void process(Set<TaskDefinition> taskDefinitions) {
		for (TaskDefinition taskDefinition : taskDefinitions) {
			submit(taskDefinition);
		}
	}

	public void submit(TaskDefinition taskDefinition) {
		if (taskDefinition.isTaskType(TaskType.REQUIRED) ||
			taskDefinition.isTaskType(TaskType.ONLINE_REQUIRED)) {

			_submitImmediatelyToParentExecutorService(taskDefinition);

			return;
		}

		if (!taskDefinition.isOnSchedule()) {
			if (!_delayedTaskDefinitions.contains(taskDefinition)) {
				_delayedTaskDefinitions.add(taskDefinition);
			}

			return;
		}

		_delayedTaskDefinitions.remove(taskDefinition);

		_commonFutures.put(
			taskDefinition.getTaskName(),
			_scheduledExecutorService.schedule(
				taskDefinition.getTask(), 0, TimeUnit.MILLISECONDS));

		if (taskDefinition.isRepeatable() &&
			!_repeatableTaskDefinitions.contains(taskDefinition)) {

			_repeatableTaskDefinitions.add(taskDefinition);
		}

		_registerTaskExecution(taskDefinition);
	}

	public String taskStatus() {
		return "Scheduler Status: " + _scheduledExecutorService;
	}

	@Activate
	protected void activate() {
		_initExecutorService();

		_subscribeToLCSEvents();

		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Deactivate
	protected void deactivate() {
		_deactivatePending.set(true);

		if (_log.isTraceEnabled()) {
			_log.trace("Deactivate " + this);
		}

		_cancelAllTasks();

		_lcsEventManager.unsubscribe(this);

		_scheduledExecutorService.shutdown();

		try {
			if (!_scheduledExecutorService.awaitTermination(
					5, TimeUnit.SECONDS)) {

				_scheduledExecutorService.shutdownNow();
			}
		}
		catch (InterruptedException ie) {
			_scheduledExecutorService.shutdownNow();
		}

		_activeTaskClassNames.clear();
		_commonFutures.clear();
		_delayedTaskDefinitions.clear();
		_onlineFutures.clear();
		_repeatableTaskDefinitions.clear();
		_requiredFutures.clear();

		_scheduledExecutorService = null;

		if (_log.isTraceEnabled()) {
			_log.trace("Deactivated " + this);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private void _cancelAllTasks() {
		_cancelNonrequiredTasks();

		_cancelTasks(_requiredFutures);
	}

	private void _cancelNonrequiredTasks() {
		_delayedTaskDefinitions.clear();
		_repeatableTaskDefinitions.clear();

		_cancelTasks(_onlineFutures);
		_cancelTasks(_commonFutures);
	}

	private void _cancelTasks(Map<String, ScheduledFuture<?>> futures) {
		for (String taskName : futures.keySet()) {
			ScheduledFuture<?> scheduledFuture = futures.get(taskName);

			if (scheduledFuture.isDone()) {
				continue;
			}

			while (!scheduledFuture.isCancelled()) {
				scheduledFuture.cancel(true);
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Canceled task " + taskName);
			}
		}

		futures.clear();

		if (_log.isTraceEnabled()) {
			_log.trace(
				"Scheduled executor service status after cancellation " +
					_scheduledExecutorService);
		}
	}

	private void _initExecutorService() {
		_scheduledExecutorService = Executors.newScheduledThreadPool(
			10, _threadFactory);

		ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
			(ScheduledThreadPoolExecutor)_scheduledExecutorService;

		scheduledThreadPoolExecutor.
			setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
		scheduledThreadPoolExecutor.
			setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
		scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
	}

	private void _registerTaskExecution(TaskDefinition taskDefinition) {
		taskDefinition.setExecuted();

		Task task = taskDefinition.getTask();

		Class<?> clazz = task.getClass();

		_activeTaskClassNames.add(clazz.getSimpleName());
	}

	private void _submitImmediatelyToParentExecutorService(
		TaskDefinition taskDefinition) {

		ScheduledFuture<?> scheduledFuture = null;

		if (taskDefinition.isRepeatable()) {
			scheduledFuture = _scheduledExecutorService.scheduleAtFixedRate(
				taskDefinition.getTask(), taskDefinition.getInitialDelay(),
				taskDefinition.getPeriod(), TimeUnit.MILLISECONDS);
		}
		else {
			scheduledFuture = _scheduledExecutorService.schedule(
				taskDefinition.getTask(), taskDefinition.getInitialDelay(),
				TimeUnit.MILLISECONDS);
		}

		if (scheduledFuture == null) {
			_log.error("Unable to submit " + taskDefinition);

			return;
		}

		if (taskDefinition.isTaskType(TaskType.REQUIRED)) {
			_requiredFutures.put(taskDefinition.getTaskName(), scheduledFuture);
		}
		else {
			_onlineFutures.put(taskDefinition.getTaskName(), scheduledFuture);
		}

		_registerTaskExecution(taskDefinition);
	}

	private void _subscribeToLCSEvents() {
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED, this);
		_lcsEventManager.subscribe(LCSEvent.LCS_GATEWAY_UNAVAILABLE, this);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSTaskExecutor.class);

	private final Set<String> _activeTaskClassNames = new HashSet<>();
	private final Map<String, ScheduledFuture<?>> _commonFutures =
		new HashMap<>();
	private final AtomicBoolean _deactivatePending = new AtomicBoolean(false);
	private final List<TaskDefinition> _delayedTaskDefinitions =
		new CopyOnWriteArrayList<>();

	@Reference
	private LCSEventManager _lcsEventManager;

	private final Map<String, ScheduledFuture<?>> _onlineFutures =
		new HashMap<>();
	private final List<TaskDefinition> _repeatableTaskDefinitions =
		new ArrayList<>();
	private final Map<String, ScheduledFuture<?>> _requiredFutures =
		new HashMap<>();
	private ScheduledExecutorService _scheduledExecutorService;

	@Reference
	private ThreadFactory _threadFactory;

}
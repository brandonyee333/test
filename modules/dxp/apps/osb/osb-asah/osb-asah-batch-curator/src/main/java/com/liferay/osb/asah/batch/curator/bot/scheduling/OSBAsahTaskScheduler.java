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

package com.liferay.osb.asah.batch.curator.bot.scheduling;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class OSBAsahTaskScheduler {

	public void execute(OSBAsahTaskRunnable osbAsahTaskRunnable) {
		_threadPoolTaskExecutor.execute(osbAsahTaskRunnable);
	}

	public void executeUpdateDynamicMembershipsNanite(
		OSBAsahTaskRunnable osbAsahTaskRunnable) {

		_updateDynamicMembershipsNaniteThreadPoolTaskExecutor.execute(
			osbAsahTaskRunnable);
	}

	public void schedule(
		CronTrigger cronTrigger, Runnable runnable, String taskId) {

		if (taskId == null) {
			throw new IllegalArgumentException("TaskId is null");
		}

		ScheduledFuture<?> scheduledFuture = _threadPoolTaskScheduler.schedule(
			runnable, cronTrigger);

		_scheduledFuturesMap.put(taskId, scheduledFuture);
	}

	public void schedule(
		String cronExpression, Runnable runnable, String taskId) {

		schedule(new CronTrigger(cronExpression), runnable, taskId);
	}

	public void unschedule(String taskId) {
		ScheduledFuture<?> scheduledFuture = _scheduledFuturesMap.remove(
			taskId);

		if (scheduledFuture == null) {
			throw new IllegalArgumentException(
				"Unable to unschedule task " + taskId);
		}

		scheduledFuture.cancel(false);
	}

	private final Map<String, ScheduledFuture<?>> _scheduledFuturesMap =
		new HashMap<>();
	private final ExecutorService _threadPoolTaskExecutor =
		Executors.newFixedThreadPool(10);

	@Autowired
	private ThreadPoolTaskScheduler _threadPoolTaskScheduler;

	private final ExecutorService
		_updateDynamicMembershipsNaniteThreadPoolTaskExecutor =
			new ThreadPoolExecutor(
				1, 1, 0, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<>(100000),
				new OSBAsahRetryRejectedExecutionHandler());

}
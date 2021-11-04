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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class AsahTaskScheduler {

	public void execute(AsahTaskRunnable asahTaskRunnable) {
		_threadPoolTaskExecutor.execute(asahTaskRunnable);
	}

	public void executeUpdateDynamicMembershipsNanite(
		AsahTaskRunnable asahTaskRunnable) {

		_updateDynamicMembershipsNaniteThreadPoolTaskExecutor.execute(
			asahTaskRunnable);
	}

	public Map<String, ScheduledFuture<?>> getScheduledFuturesMap() {
		return Collections.unmodifiableMap(_scheduledFuturesMap);
	}

	public void schedule(
		CronTrigger cronTrigger, Runnable runnable, String scheduledTaskId) {

		if (scheduledTaskId == null) {
			throw new IllegalArgumentException("scheduledTaskId is null");
		}

		ScheduledFuture<?> scheduledFuture = _threadPoolTaskScheduler.schedule(
			runnable, cronTrigger);

		_scheduledFuturesMap.put(scheduledTaskId, scheduledFuture);
	}

	public void schedule(
		String cronExpression, Runnable runnable, String scheduledTaskId) {

		schedule(new CronTrigger(cronExpression), runnable, scheduledTaskId);
	}

	public void unschedule(String scheduledAsahTaskId) {
		ScheduledFuture<?> scheduledFuture = _scheduledFuturesMap.remove(
			scheduledAsahTaskId);

		if (scheduledFuture == null) {
			throw new IllegalArgumentException(
				"Unable to unschedule task " + scheduledAsahTaskId);
		}

		scheduledFuture.cancel(false);
	}

	@PreDestroy
	private void _destroy() {
		_threadPoolTaskExecutor.shutdown();
		_updateDynamicMembershipsNaniteThreadPoolTaskExecutor.shutdown();

		try {
			if (!_threadPoolTaskExecutor.awaitTermination(
					1, TimeUnit.MINUTES)) {

				_threadPoolTaskExecutor.shutdownNow();
			}
		}
		catch (InterruptedException interruptedException) {
			_log.error(
				"Interrupted while waiting for termination of executor",
				interruptedException);
		}

		try {
			if (!_updateDynamicMembershipsNaniteThreadPoolTaskExecutor.
					awaitTermination(1, TimeUnit.MINUTES)) {

				_updateDynamicMembershipsNaniteThreadPoolTaskExecutor.
					shutdownNow();
			}
		}
		catch (InterruptedException interruptedException) {
			_log.error(
				"Interrupted while waiting for termination of executor",
				interruptedException);
		}
	}

	private static final Log _log = LogFactory.getLog(AsahTaskScheduler.class);

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
				new AsahRetryRejectedExecutionHandler());

}
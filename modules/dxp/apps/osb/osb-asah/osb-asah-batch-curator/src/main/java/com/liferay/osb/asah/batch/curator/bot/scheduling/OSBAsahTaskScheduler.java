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

import com.liferay.osb.asah.batch.curator.bot.nanite.Nanite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

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

	public Nanite getNanite(String className) {
		return _nanitesMap.get(className);
	}

	@PostConstruct
	public void init() {
		for (Nanite nanite : _nanites) {
			Class<?> clazz = nanite.getClass();

			_nanitesMap.put(clazz.getSimpleName(), nanite);
		}
	}

	public void schedule(
		String cronExpression,
		OSBAsahScheduledTaskRunnable osbAsahScheduledTaskRunnable,
		String osbAsahTaskId) {

		ScheduledFuture<?> scheduledFuture = _threadPoolTaskScheduler.schedule(
			osbAsahScheduledTaskRunnable, new CronTrigger(cronExpression));

		if (osbAsahTaskId != null) {
			_scheduledFuturesMap.put(osbAsahTaskId, scheduledFuture);
		}
	}

	public void unschedule(String osbAsahTaskId) {
		ScheduledFuture<?> scheduledFuture = _scheduledFuturesMap.remove(
			osbAsahTaskId);

		if (scheduledFuture == null) {
			throw new IllegalArgumentException(
				"Unable to unschedule OSB Asah task " + osbAsahTaskId);
		}

		scheduledFuture.cancel(false);
	}

	@Autowired
	private List<Nanite> _nanites;

	private final Map<String, Nanite> _nanitesMap = new HashMap<>();
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
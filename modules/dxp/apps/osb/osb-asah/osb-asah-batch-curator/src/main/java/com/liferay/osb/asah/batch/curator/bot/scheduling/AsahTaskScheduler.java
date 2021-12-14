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
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class AsahTaskScheduler {

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
		_threadPoolTaskScheduler.setAwaitTerminationSeconds(60);

		_threadPoolTaskScheduler.shutdown();
	}

	private final Map<String, ScheduledFuture<?>> _scheduledFuturesMap =
		new HashMap<>();

	@Autowired
	private ThreadPoolTaskScheduler _threadPoolTaskScheduler;

}
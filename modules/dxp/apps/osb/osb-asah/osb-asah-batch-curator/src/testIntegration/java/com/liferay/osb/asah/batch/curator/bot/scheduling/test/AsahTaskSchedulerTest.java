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

package com.liferay.osb.asah.batch.curator.bot.scheduling.test;

import com.liferay.osb.asah.batch.curator.bot.scheduling.AsahTaskScheduler;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBatchCuratorSpringBootApplication.class)
public class AsahTaskSchedulerTest {

	@Before
	public void setUp() {
		Mockito.when(
			_threadPoolTaskScheduler.schedule(
				Mockito.any(Runnable.class), Mockito.any(Trigger.class))
		).thenReturn(
			Mockito.mock(ScheduledFuture.class)
		);
	}

	@Test
	public void testSchedule() {
		Runnable runnable = () -> {
		};

		_asahTaskScheduler.schedule(
			"0 0 0 * * ?", runnable, "450553576847486528");

		Mockito.verify(
			_threadPoolTaskScheduler, Mockito.times(1)
		).schedule(
			Mockito.eq(runnable), Mockito.any(CronTrigger.class)
		);

		Map<String, ScheduledFuture<?>> scheduledFuturesMap =
			_asahTaskScheduler.getScheduledFuturesMap();

		Assert.assertEquals(
			scheduledFuturesMap.toString(), 1, scheduledFuturesMap.size());
		Assert.assertTrue(
			scheduledFuturesMap.containsKey("450553576847486528"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testScheduleFail() {
		_asahTaskScheduler.schedule(
			"",
			() -> {
			},
			null);
	}

	@Test
	public void testUnschedule() {
		_asahTaskScheduler.schedule(
			"0 0 0 * * ?",
			() -> {
			},
			"450553576847486528");

		Map<String, ScheduledFuture<?>> scheduledFuturesMap =
			_asahTaskScheduler.getScheduledFuturesMap();

		ScheduledFuture<?> scheduledFuture = scheduledFuturesMap.get(
			"450553576847486528");

		_asahTaskScheduler.unschedule("450553576847486528");

		Assert.assertEquals(
			scheduledFuturesMap.toString(), 0, scheduledFuturesMap.size());

		Mockito.verify(
			scheduledFuture, Mockito.times(1)
		).cancel(
			Mockito.eq(false)
		);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnscheduleFail() {
		_asahTaskScheduler.unschedule(null);
	}

	@Autowired
	private AsahTaskScheduler _asahTaskScheduler;

	@MockBean
	private ThreadPoolTaskScheduler _threadPoolTaskScheduler;

}
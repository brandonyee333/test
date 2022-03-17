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

package com.liferay.osb.asah.dataflow.emulator.bot;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.AnalyticsEventsIngestionNanite;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.DXPEntitiesIngestionNanite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@EnableScheduling
public class OSBAsahDataflowEmulatorCuratorBot {

	@Scheduled(fixedDelay = DateUtil.MINUTE)
	public void checkIngestionNaniteWatermark() {
		_analyticsEventsIngestionNanite.checkWatermark();
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE)
	public void closeIngestionNaniteOpenSessions() {
		_analyticsEventsIngestionNanite.closeOpenSessions();
	}

	@Scheduled(fixedDelay = 10 * DateUtil.SECOND)
	public void runAnalyticsEventsIngestionNanite() {
		try {
			_analyticsEventsIngestionNanite.run();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	@Scheduled(fixedDelay = 5 * DateUtil.MINUTE)
	public void runDXPEntitiesIngestionNanite() {
		try {
			_dxpEntitiesIngestionNanite.run();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler =
			new ThreadPoolTaskScheduler();

		threadPoolTaskScheduler.setPoolSize(2);

		return threadPoolTaskScheduler;
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahDataflowEmulatorCuratorBot.class);

	@Autowired
	private AnalyticsEventsIngestionNanite _analyticsEventsIngestionNanite;

	@Autowired
	private DXPEntitiesIngestionNanite _dxpEntitiesIngestionNanite;

}
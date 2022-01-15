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
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.IngestionNanite;

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
		_runOnGlobalContext(_ingestionNanite::checkWatermark);
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE)
	public void closeIngestionNaniteOpenSessions() {
		_runOnGlobalContext(_ingestionNanite::closeOpenSessions);
	}

	@Scheduled(fixedDelay = 10 * DateUtil.SECOND)
	public void runIngestionNanite() {
		_runOnGlobalContext(
			() -> {
				try {
					_ingestionNanite.run();
				}
				catch (Exception exception) {
					_log.error(exception, exception);
				}
			});
	}

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler =
			new ThreadPoolTaskScheduler();

		threadPoolTaskScheduler.setPoolSize(2);

		return threadPoolTaskScheduler;
	}

	private void _runOnGlobalContext(Runnable runnable) {
		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			runnable.run();
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahDataflowEmulatorCuratorBot.class);

	@Autowired
	private IngestionNanite _ingestionNanite;

}
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

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Shinn Lok
 * @author André Miranda
 */
public class AsahRetryRejectedExecutionHandler
	implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(
		Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

		if (_log.isInfoEnabled()) {
			_log.info("Queue is full. Retrying in 5 seconds.");
		}

		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException ie) {
			_log.error(ie, ie);
		}

		threadPoolExecutor.execute(runnable);
	}

	private static final Log _log = LogFactory.getLog(
		AsahRetryRejectedExecutionHandler.class);

}
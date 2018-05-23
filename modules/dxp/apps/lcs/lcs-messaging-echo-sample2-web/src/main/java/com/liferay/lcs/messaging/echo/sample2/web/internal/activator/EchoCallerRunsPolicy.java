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

package com.liferay.lcs.messaging.echo.sample2.web.internal.activator;

import com.liferay.portal.kernel.concurrent.CallerRunsPolicy;
import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Riccardo Ferrari
 */
public class EchoCallerRunsPolicy extends CallerRunsPolicy {

	@Override
	public void rejectedExecution(
		Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"The current thread will handle the request because the " +
					"graph walker's task queue is at its maximum capacity");
		}

		super.rejectedExecution(runnable, threadPoolExecutor);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EchoCallerRunsPolicy.class);

}
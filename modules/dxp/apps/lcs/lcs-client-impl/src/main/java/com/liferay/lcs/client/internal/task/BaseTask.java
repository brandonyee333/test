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

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.internal.platform.http.RESTClientException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public abstract class BaseTask implements Task {

	@Override
	public void run() {
		if (_log.isTraceEnabled()) {
			_log.trace("Running task " + getClass());
		}

		try {
			long startTimeMillis = System.currentTimeMillis();

			doRun();

			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Executed %s in %dms", getClass(),
						System.currentTimeMillis() - startTimeMillis));
			}
		}
		catch (Exception e) {
			String errorMessage = "Unable to run task";

			if (e instanceof RESTClientException) {
				_log.error(errorMessage);
			}
			else {
				_log.error(errorMessage, e);
			}
		}
	}

	protected abstract void doRun() throws Exception;

	private static final Log _log = LogFactoryUtil.getLog(BaseTask.class);

}
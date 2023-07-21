/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
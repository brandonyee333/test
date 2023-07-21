/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.internal.advisor.UptimeAdvisor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(
	immediate = true, name = "com.liferay.lcs.client.internal.task.UptimeTask",
	service = Task.class
)
public class UptimeTask implements Task {

	public UptimeTask() {
		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.REQUIRED;
	}

	@Override
	public void run() {
		try {
			if (_log.isTraceEnabled()) {
				_log.trace("Running uptime update");
			}

			_uptimeAdvisor.updateCurrentUptime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Activate
	protected void activate() {
		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(UptimeTask.class);

	@Reference
	private UptimeAdvisor _uptimeAdvisor;

}
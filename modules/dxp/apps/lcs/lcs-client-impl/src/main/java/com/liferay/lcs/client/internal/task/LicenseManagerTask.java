/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.advisor.LCSPortletStateAdvisor;
import com.liferay.lcs.client.internal.advisor.LCSPortletStateAdvisorImpl;
import com.liferay.lcs.client.internal.messaging.advisor.MessageBusAdvisor;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	property = "lcs.client.scheduled.task.name=com.liferay.lcs.task.LicenseManagerTask",
	service = Task.class
)
public class LicenseManagerTask extends BaseTask {

	public LicenseManagerTask() {
	}

	public LicenseManagerTask(
		MessageBusAdvisor messageBusAdvisor,
		LCSPortletStateAdvisorImpl lcsPortletStateAdvisor) {

		_messageBusAdvisor = messageBusAdvisor;
		_lcsPortletStateAdvisor = lcsPortletStateAdvisor;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void doRun() {
		long currentTimeMills = System.currentTimeMillis();

		long lastLicenseCheckTime =
			_lcsPortletStateAdvisor.getLastLicenseCheckTime();

		if ((currentTimeMills - lastLicenseCheckTime) < _LICENSE_CHECK_PERIOD) {
			_messageBusAdvisor.processLCSPortletState(
				_lcsPortletStateAdvisor.getLCSPortletState(false));

			return;
		}

		LCSPortletState lcsPortletState =
			_lcsPortletStateAdvisor.getLCSPortletState(true);

		if (_log.isTraceEnabled()) {
			_log.trace("LCS portlet state: " + lcsPortletState);
		}

		_messageBusAdvisor.processLCSPortletState(lcsPortletState);

		_lcsPortletStateAdvisor.updateLicenseCheckTime();
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.MANAGEABLE;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private static final long _LICENSE_CHECK_PERIOD = 60000L * 15L;

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseManagerTask.class);

	@Reference
	private LCSPortletStateAdvisor _lcsPortletStateAdvisor;

	@Reference
	private MessageBusAdvisor _messageBusAdvisor;

}
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

package com.liferay.lcs.task;

import com.liferay.lcs.advisor.UptimeMonitoringAdvisor;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class UptimeMonitoringTask implements Task {

	public UptimeMonitoringTask() {
		BeanLocator beanLocator = PortletBeanLocatorUtil.getBeanLocator(
			"lcs-portlet");

		_uptimeMonitoringAdvisor = (UptimeMonitoringAdvisor)beanLocator.locate(
			_uptimeMonitoringAdvisorClass.getName());

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void run() {
		try {
			if (_log.isTraceEnabled()) {
				_log.trace("Running uptime update");
			}

			_uptimeMonitoringAdvisor.updateCurrentUptime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UptimeMonitoringTask.class);

	private final UptimeMonitoringAdvisor _uptimeMonitoringAdvisor;
	private Class<?> _uptimeMonitoringAdvisorClass =
		UptimeMonitoringAdvisor.class;

}
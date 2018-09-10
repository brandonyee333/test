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

import com.liferay.lcs.advisor.LCSKeyAdvisor;
import com.liferay.lcs.management.MBeanServerService;
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ServerDetector;

/**
 * @author Riccardo Ferrari
 */
public class ServerMetricsTaskFactory {

	public ServerMetricsTask getInstance() {
		ServerMetricsTask serverMetricsTask = null;

		if (ServerDetector.isTomcat()) {
			if (_log.isTraceEnabled()) {
				_log.trace("Setting Tomcat metrics task");
			}

			serverMetricsTask = new TomcatServerMetricsTask();
		}
		else if (ServerDetector.isWebLogic()) {
			if (_log.isTraceEnabled()) {
				_log.trace("Setting WebLogic metrics task");
			}

			serverMetricsTask = new WeblogicServerMetricsTask();
		}
		else {
			if (_log.isWarnEnabled()) {
				_log.warn(
					ServerDetector.getServerId() +
						" server metrics are not supported in LCS");
			}

			return null;
		}

		serverMetricsTask.setLCSKeyAdvisor(_lcsKeyAdvisor);
		serverMetricsTask.setLCSConnectionManager(_lcsConnectionManager);
		serverMetricsTask.setMBeanServerService(_mBeanServerService);

		return serverMetricsTask;
	}

	public void setLCSConnectionManager(
		LCSConnectionManager lcsConnectionManager) {

		_lcsConnectionManager = lcsConnectionManager;
	}

	public void setLCSKeyAdvisor(LCSKeyAdvisor lcsKeyAdvisor) {
		_lcsKeyAdvisor = lcsKeyAdvisor;
	}

	public void setmBeanServerService(MBeanServerService mBeanServerService) {
		_mBeanServerService = mBeanServerService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServerMetricsTaskFactory.class);

	private LCSConnectionManager _lcsConnectionManager;
	private LCSKeyAdvisor _lcsKeyAdvisor;
	private MBeanServerService _mBeanServerService;

}
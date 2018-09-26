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
import com.liferay.lcs.platform.gateway.LCSGatewayService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ServerDetector;

/**
 * @author Riccardo Ferrari
 */
public class ServerMetricsTaskFactory {

	public ServerMetricsTaskFactory(
		LCSGatewayService lcsGatewayService, LCSKeyAdvisor lcsKeyAdvisor,
		MBeanServerService mBeanServerService) {

		_lcsGatewayService = lcsGatewayService;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_mBeanServerService = mBeanServerService;
	}

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
		serverMetricsTask.setLCSGatewayService(_lcsGatewayService);
		serverMetricsTask.setMBeanServerService(_mBeanServerService);

		return serverMetricsTask;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServerMetricsTaskFactory.class);

	private final LCSGatewayService _lcsGatewayService;
	private final LCSKeyAdvisor _lcsKeyAdvisor;
	private final MBeanServerService _mBeanServerService;

}
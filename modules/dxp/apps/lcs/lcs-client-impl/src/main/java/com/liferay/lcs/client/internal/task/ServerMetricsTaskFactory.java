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

import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.management.MBeanServerService;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringBundler;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = ServerMetricsTaskFactory.class)
public class ServerMetricsTaskFactory {

	public ServerMetricsTaskFactory() {
	}

	public ServerMetricsTaskFactory(
		LCSGatewayClient lcsGatewayClient, LCSKeyAdvisor lcsKeyAdvisor,
		MBeanServerService mBeanServerService) {

		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_mBeanServerService = mBeanServerService;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		ServerMetricsTask serverMetricsTask = getInstance();

		if (serverMetricsTask == null) {
			return;
		}

		_scheduledTaskServiceRegistration = bundleContext.registerService(
			ScheduledTask.class, serverMetricsTask,
			new HashMapDictionary<String, String>() {
				{
					put(
						"lcs.client.scheduled.task.name",
						"com.liferay.lcs.task.ServerMetricsTask");
				}
			});
	}

	@Deactivate
	protected void deactivate() {
		if (_scheduledTaskServiceRegistration != null) {
			_scheduledTaskServiceRegistration.unregister();
		}
	}

	protected ServerMetricsTask getInstance() {
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
			StringBundler sb = new StringBundler(4);

			sb.append("LCS does not support ");
			sb.append(ServerDetector.getServerId());
			sb.append("analytics. Please disable the portal analytics ");
			sb.append("service in LCS.");

			_log.error(sb.toString());

			return null;
		}

		serverMetricsTask.setLCSKeyAdvisor(_lcsKeyAdvisor);
		serverMetricsTask.setLCSGatewayService(_lcsGatewayClient);
		serverMetricsTask.setMBeanServerService(_mBeanServerService);

		return serverMetricsTask;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServerMetricsTaskFactory.class);

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

	@Reference
	private MBeanServerService _mBeanServerService;

	private ServiceRegistration<ScheduledTask>
		_scheduledTaskServiceRegistration;

}
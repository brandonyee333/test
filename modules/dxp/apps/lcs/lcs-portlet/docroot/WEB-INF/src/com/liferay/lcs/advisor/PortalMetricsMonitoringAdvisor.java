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

package com.liferay.lcs.advisor;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Igor Beslic
 */
public class PortalMetricsMonitoringAdvisor implements MonitoringAdvisor {

	@Override
	public void activateMonitoring() {
		Bundle bundle = FrameworkUtil.getBundle(getClass());

		BundleContext bundleContext = bundle.getBundleContext();

		ServiceReference<ConfigurationAdmin> serviceReference =
			bundleContext.getServiceReference(ConfigurationAdmin.class);

		ConfigurationAdmin configurationAdmin = bundleContext.getService(
			serviceReference);

		try {
			Configuration configuration = configurationAdmin.getConfiguration(
				_MONITORING_CONFIGURATION_PID, StringPool.QUESTION);

			Dictionary<String, Object> properties =
				configuration.getProperties();

			if (properties == null) {
				properties = new Hashtable<>();
			}

			properties.put("monitorPortalRequest", Boolean.TRUE);
			properties.put("monitorPortletActionRequest", Boolean.TRUE);
			properties.put("monitorPortletEventRequest", Boolean.TRUE);
			properties.put("monitorPortletRenderRequest", Boolean.TRUE);
			properties.put("monitorPortletResourceRequest", Boolean.TRUE);
			properties.put("monitorServiceRequest", Boolean.TRUE);

			configuration.update(properties);

			if (_log.isInfoEnabled()) {
				_log.info("Monitoring activated");
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to activate monitoring", e);
			}
		}
		finally {
			bundleContext.ungetService(serviceReference);
		}
	}

	private static final String _MONITORING_CONFIGURATION_PID =
		"com.liferay.portal.monitoring.configuration.MonitoringConfiguration";

	private static final Log _log = LogFactoryUtil.getLog(
		PortalMetricsMonitoringAdvisor.class);

}
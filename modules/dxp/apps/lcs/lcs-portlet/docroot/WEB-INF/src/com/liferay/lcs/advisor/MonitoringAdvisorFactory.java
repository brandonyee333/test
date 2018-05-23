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

import com.liferay.lcs.messaging.PortalMetricsMessageListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Igor Beslic
 */
public class MonitoringAdvisorFactory {

	public static MonitoringAdvisor getInstance(Class messageListenerClass) {
		if (_monitoringAdvisors.containsKey(messageListenerClass)) {
			return _monitoringAdvisors.get(messageListenerClass);
		}

		MonitoringAdvisor monitoringAdvisor = null;

		if (messageListenerClass.equals(PortalMetricsMessageListener.class)) {
			monitoringAdvisor = new PortalMetricsMonitoringAdvisor();

			_monitoringAdvisors.put(messageListenerClass, monitoringAdvisor);
		}

		return monitoringAdvisor;
	}

	private static final Map<Class, MonitoringAdvisor> _monitoringAdvisors =
		new HashMap<>();

}
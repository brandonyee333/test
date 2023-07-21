/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.model;

import com.liferay.osgi.util.ServiceTrackerFactory;

import java.util.Map;

import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Jenny Chen
 */
public class MetricsModelRegistryUtil {

	public static MetricsModel<?> getMetricsModel(String modelClassName) {
		MetricsModelRegistry metricsModelRegistry =
			_serviceTracker.getService();

		return metricsModelRegistry.getMetricsModel(modelClassName);
	}

	public static Map<String, MetricsModel<?>> getMetricsModelsMap() {
		MetricsModelRegistry metricsModelRegistry =
			_serviceTracker.getService();

		return metricsModelRegistry.getMetricsModelsMap();
	}

	private static final ServiceTracker<?, MetricsModelRegistry>
		_serviceTracker = ServiceTrackerFactory.open(
			MetricsModelRegistry.class);

}
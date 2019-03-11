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

package com.liferay.osb.customer.metrics.api.model;

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
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.impl.internal.model;

import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModelRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModelRegistry.class)
public class MetricsModelRegistryImpl implements MetricsModelRegistry {

	@Override
	public MetricsModel<?> getMetricsModel(String modelClassName) {
		return _metricsModelsMap.get(modelClassName);
	}

	@Override
	public Map<String, MetricsModel<?>> getMetricsModelsMap() {
		return _metricsModelsMap;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void registerMetricsModel(
			MetricsModel<?> metricsModel, Map<String, Object> properties)
		throws Exception {

		Class<?> clazz = metricsModel.getModelClass();

		_metricsModelsMap.put(clazz.getName(), metricsModel);
	}

	protected void unregisterMetricsModel(
		MetricsModel<?> metricsModel, Map<String, Object> properties) {

		Class<?> clazz = metricsModel.getModelClass();

		_metricsModelsMap.remove(clazz.getName());
	}

	private final Map<String, MetricsModel<?>> _metricsModelsMap =
		new ConcurrentHashMap<>();

}
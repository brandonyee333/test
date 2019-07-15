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
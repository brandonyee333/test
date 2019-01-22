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

import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.api.model.MetricsModelRegistry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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
@Component(immediate = true)
public class MetricsModelRegistryImpl implements MetricsModelRegistry {

	@Override
	public MetricsModel<?> getMetricsModel(String modelClassName) {
		return _metricsModelsMap.get(modelClassName);
	}

	@Override
	public Map<String, MetricsModel<?>> getMetricsModelsMap() {
		return _metricsModelsMap;
	}

	@Override
	public String getServletContextName(String modelClassName) {
		return _servletContextNamesMap.get(modelClassName);
	}

	@Override
	public Map<String, String> getServletContextNamesMap() {
		return _servletContextNamesMap;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "unregisterMetricsModel"
	)
	protected synchronized void registerMetricsModel(
			MetricsModel<?> metricsModel, Map<String, Object> properties)
		throws Exception {

		String modelClassName = GetterUtil.getString(
			properties.get("model.class.name"));

		String servletContextName = GetterUtil.getString(
			properties.get("servlet.context.name"));

		if (Validator.isNotNull(modelClassName)) {
			_metricsModelsMap.put(modelClassName, metricsModel);

			if (Validator.isNotNull(servletContextName)) {
				_servletContextNamesMap.put(modelClassName, servletContextName);
			}
			else {
				_servletContextNamesMap.put(modelClassName, StringPool.BLANK);
			}
		}
	}

	protected synchronized void unregisterMetricsModel(
		MetricsModel<?> metricsModel, Map<String, Object> properties) {

		String modelClassName = GetterUtil.getString(
			properties.get("model.class.name"));

		if (Validator.isNotNull(modelClassName)) {
			_metricsModelsMap.remove(modelClassName);
			_servletContextNamesMap.remove(modelClassName);
		}
	}

	private final Map<String, MetricsModel<?>> _metricsModelsMap =
		new ConcurrentHashMap<>();
	private final Map<String, String> _servletContextNamesMap =
		new ConcurrentHashMap<>();

}
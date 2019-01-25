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

package com.liferay.osb.customer.metrics.models;

import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.impl.model.BaseMetricsModel;
import com.liferay.osb.customer.metrics.models.util.MetricsTransformationUtil;
import com.liferay.osb.model.OfferingBundle;
import com.liferay.osb.model.OfferingDefinition;
import com.liferay.osb.service.OfferingBundleLocalServiceUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class OfferingBundleMetricsModel
	extends BaseMetricsModel<OfferingBundle> {

	@Override
	public Map<String, String> getMappingTables() throws Exception {
		Map<String, String> mappingTablesMap = new HashMap<>();

		mappingTablesMap.put("OfferingDefinition", "offeringDefinitionId");

		return mappingTablesMap;
	}

	@Override
	public Map<String, List<String>> getMappingValues(
		BaseModel<OfferingBundle> model) {

		Map<String, Object> attributes = model.getModelAttributes();

		Long offeringBundleId = (Long)attributes.get("offeringBundleId");

		OfferingBundle offeringBundle =
			OfferingBundleLocalServiceUtil.fetchOfferingBundle(
				offeringBundleId);

		List<OfferingDefinition> offeringDefinitions =
			offeringBundle.getOfferingDefinitions();

		List<String> offeringDefinitionsValues = new ArrayList<>();

		for (OfferingDefinition offeringDefinition : offeringDefinitions) {
			offeringDefinitionsValues.add(
				String.valueOf(offeringDefinition.getOfferingDefinitionId()));
		}

		Map<String, List<String>> mappingValues = new HashMap<>();

		mappingValues.put("offeringBundleId", offeringDefinitionsValues);

		return mappingValues;
	}

	@Override
	public Class getModelClass() {
		return OfferingBundle.class;
	}

	@Override
	public boolean hasMapping() {
		return true;
	}

	@Override
	public Map<String, Object> transformAttributes(
		BaseModel<OfferingBundle> model) {

		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				model.getModelAttributes());

		return attributes;
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}
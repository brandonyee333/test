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

package com.liferay.osb.customer.metrics.model;

import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.util.MetricsTransformationUtil;
import com.liferay.osb.model.OfferingBundle;
import com.liferay.osb.model.OfferingDefinition;
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
	extends BaseModelMetricsModel<OfferingBundle> {

	@Override
	public Map<String, Object> getAttributes(OfferingBundle offeringBundle) {
		return _metricsTransformationUtil.transformSharedAttributes(
			offeringBundle.getModelAttributes());
	}

	@Override
	public String[] getMappingTables() {
		return _MAPPING_TABLES;
	}

	@Override
	public List<Map<String, String>> getMappingValues(
		OfferingBundle offeringBundle, String mappingTable) {

		List<Map<String, String>> mappingValues = new ArrayList<>();

		for (OfferingDefinition offeringDefinition :
				offeringBundle.getOfferingDefinitions()) {

			Map<String, String> mappingValue = new HashMap<>();

			mappingValue.put(
				"offeringBundleId",
				String.valueOf(offeringBundle.getOfferingBundleId()));
			mappingValue.put(
				"offeringDefinitionId",
				String.valueOf(offeringDefinition.getOfferingDefinitionId()));

			mappingValues.add(mappingValue);
		}

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

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private static final String[] _MAPPING_TABLES = {"OfferingDefinition"};

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}
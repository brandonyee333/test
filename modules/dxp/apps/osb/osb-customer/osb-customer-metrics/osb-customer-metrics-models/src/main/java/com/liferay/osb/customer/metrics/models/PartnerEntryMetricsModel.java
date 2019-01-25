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
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.SupportRegion;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

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
public class PartnerEntryMetricsModel extends BaseMetricsModel<PartnerEntry> {

	@Override
	public Map<String, Object> getAttributes(PartnerEntry partnerEntry) {
		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				partnerEntry.getModelAttributes());

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			attributes.put("status", WorkflowConstants.getStatusLabel(status));
		}

		return attributes;
	}

	@Override
	public String[] getMappingTables() {
		return _MAPPING_TABLES;
	}

	@Override
	public List<Map<String, String>> getMappingValues(
		PartnerEntry partnerEntry, String mappingTable) {

		List<Map<String, String>> mappingValues = new ArrayList<>();

		SupportRegion supportRegion = partnerEntry.getSupportRegion();

		Map<String, String> mappingValue = new HashMap<>();

		mappingValue.put(
			"partnerEntryId", String.valueOf(partnerEntry.getPartnerEntryId()));
		mappingValue.put(
			"supportRegionId",
			String.valueOf(supportRegion.getSupportRegionId()));

		mappingValues.add(mappingValue);

		return mappingValues;
	}

	@Override
	public Class getModelClass() {
		return PartnerEntry.class;
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

	private static final String[] _MAPPING_TABLES = {"SupportRegion"};

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}
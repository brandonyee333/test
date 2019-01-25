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
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.portal.kernel.model.BaseModel;
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
	public Map<String, String> getMappingTables() throws Exception {
		Map<String, String> mappingTablesMap = new HashMap<>();

		mappingTablesMap.put("SupportRegion", "supportRegionId");

		return mappingTablesMap;
	}

	@Override
	public Map<String, List<String>> getMappingValues(
		BaseModel<PartnerEntry> model) {

		Map<String, Object> attributes = model.getModelAttributes();

		Long partnerEntryId = (Long)attributes.get("partnerEntryId");

		PartnerEntry partnerEntry =
			PartnerEntryLocalServiceUtil.fetchPartnerEntry(partnerEntryId);

		SupportRegion supportRegion = partnerEntry.getSupportRegion();

		List<String> supportRegionValues = new ArrayList<>();

		supportRegionValues.add(
			String.valueOf(supportRegion.getSupportRegionId()));

		Map<String, List<String>> mappingValues = new HashMap<>();

		mappingValues.put("partnerEntryId", supportRegionValues);

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

	@Override
	public Map<String, Object> transformAttributes(
		BaseModel<PartnerEntry> model) {

		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				model.getModelAttributes());

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			attributes.put("status", WorkflowConstants.getStatusLabel(status));
		}

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
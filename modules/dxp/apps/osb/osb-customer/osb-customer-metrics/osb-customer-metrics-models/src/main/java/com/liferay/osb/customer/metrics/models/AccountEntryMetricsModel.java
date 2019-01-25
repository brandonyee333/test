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
import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.models.util.MetricsTransformationUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.util.WorkflowConstants;
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
public class AccountEntryMetricsModel
	extends BaseModelMetricsModel<AccountEntry> {

	@Override
	public Map<String, Object> getAttributes(AccountEntry accountEntry) {
		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				accountEntry.getModelAttributes());

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			attributes.put("type", AccountEntryConstants.getTypeLabel(type));
		}

		Integer industry = (Integer)attributes.get("industry");

		if (industry != null) {
			attributes.put(
				"industry", AccountEntryConstants.getIndustryLabel(industry));
		}

		Integer tier = (Integer)attributes.get("tier");

		if (tier != null) {
			attributes.put("tier", AccountEntryConstants.getTierLabel(tier));
		}

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
		AccountEntry accountEntry, String mappingTable) {

		List<Map<String, String>> mappingValues = new ArrayList<>();

		List<SupportRegion> supportRegions = accountEntry.getSupportRegions();

		for (SupportRegion supportRegion : supportRegions) {
			Map<String, String> mappingValue = new HashMap<>();

			mappingValue.put(
				"accountEntryId",
				String.valueOf(accountEntry.getAccountEntryId()));
			mappingValue.put(
				"supportRegionId",
				String.valueOf(supportRegion.getSupportRegionId()));

			mappingValues.add(mappingValue);
		}

		return mappingValues;
	}

	@Override
	public Class getModelClass() {
		return AccountEntry.class;
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
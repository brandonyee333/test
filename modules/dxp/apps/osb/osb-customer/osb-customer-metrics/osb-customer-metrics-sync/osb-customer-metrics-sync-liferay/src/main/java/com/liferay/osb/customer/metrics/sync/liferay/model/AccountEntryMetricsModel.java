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

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;

import java.util.ArrayList;
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

		/*
		TODO
		attributes.put("industry", accountEntry.getIndustryLabel());
		attributes.put("status", accountEntry.getStatusLabel());
		attributes.put(
			"tier", AccountEntryConstants.getTierLabel(accountEntry.getTier()));
		attributes.put("type", accountEntry.getTypeLabel());
		*/

		return attributes;
	}

	@Override
	public String[] getMappingTables() {
		return _MAPPING_TABLES;
	}

	@Override
	public List<Map<String, Object>> getMappingValues(
		AccountEntry accountEntry) {

		List<Map<String, Object>> mappingValues = new ArrayList<>();

		/*
		TODO

		for (SupportRegion supportRegion : accountEntry.getSupportRegions()) {
			Map<String, Object> mappingValue = new HashMap<>();

			mappingValue.put(
				"accountEntryId", accountEntry.getAccountEntryId());
			mappingValue.put("companyId", accountEntry.getCompanyId());
			mappingValue.put(
				"supportRegionId", supportRegion.getSupportRegionId());

			mappingValues.add(mappingValue);
		}
		*/

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

	private static final String[] _MAPPING_TABLES = {"SupportRegion"};

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}
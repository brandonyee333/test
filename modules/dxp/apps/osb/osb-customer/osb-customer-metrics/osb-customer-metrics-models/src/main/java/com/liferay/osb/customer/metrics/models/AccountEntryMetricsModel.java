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
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class AccountEntryMetricsModel extends BaseMetricsModel<AccountEntry> {

	@Override
	public List<String> getMappingValues(BaseModel<AccountEntry> model) {
		Map<String, Object> attributes = model.getModelAttributes();

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.fetchAccountEntry(accountEntryId);

		List<SupportRegion> supportRegions = accountEntry.getSupportRegions();

		List<String> mappingValues = new ArrayList<>();

		for (SupportRegion supportRegion : supportRegions) {
			mappingValues.add(
				String.valueOf(supportRegion.getSupportRegionId()));
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

	@Override
	public Map<String, Object> transformAttributes(
		BaseModel<AccountEntry> model) {

		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				model.getModelAttributes());

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
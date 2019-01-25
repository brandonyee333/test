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
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class PartnerWorkerMetricsModel extends BaseMetricsModel<PartnerWorker> {

	@Override
	public Map<String, Object> getAttributes(PartnerWorker partnerWorker) {
		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				partnerWorker.getModelAttributes());

		Integer role = (Integer)attributes.get("role");

		if (role != null) {
			attributes.put("role", PartnerWorkerConstants.getRoleLabel(role));
		}

		return attributes;
	}

	@Override
	public Class getModelClass() {
		return PartnerWorker.class;
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
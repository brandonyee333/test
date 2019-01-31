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

package com.liferay.osb.customer.metrics.liferay.sync.model;

import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.liferay.sync.model.util.MetricsTransformationUtil;
import com.liferay.osb.model.LicenseKey;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class LicenseKeyMetricsModel extends BaseModelMetricsModel<LicenseKey> {

	@Override
	public Map<String, Object> getAttributes(LicenseKey licenseKey) {
		return _metricsTransformationUtil.transformSharedAttributes(
			licenseKey.getModelAttributes());
	}

	@Override
	public Class getModelClass() {
		return LicenseKey.class;
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
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

import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class LicenseKeySetMetricsModel extends BaseModelMetricsModel {

	@Override
	public Class getModelClass() {
		return null;
	}

	/*
	TODO
	@Override
	public Map<String, Object> getAttributes(LicenseKeySet licenseKeySet) {
		return _metricsTransformationUtil.transformSharedAttributes(
			licenseKeySet.getModelAttributes());
	}

	@Override
	public Class getModelClass() {
		return LicenseKeySet.class;
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;
	*/

}
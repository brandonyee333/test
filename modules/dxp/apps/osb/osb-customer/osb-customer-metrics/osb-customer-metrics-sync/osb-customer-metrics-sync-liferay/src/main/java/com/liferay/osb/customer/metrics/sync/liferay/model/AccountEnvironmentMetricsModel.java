/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.admin.model.AccountEnvironment;
import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class AccountEnvironmentMetricsModel
	extends BaseModelMetricsModel<AccountEnvironment> {

	@Override
	public Map<String, Object> getAttributes(
		AccountEnvironment accountEnvironment) {

		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				accountEnvironment.getModelAttributes());

		attributes.put("envAS", accountEnvironment.getEnvASLabel());
		attributes.put("envBrowser", accountEnvironment.getEnvBrowserLabel());
		attributes.put("envCommerce", accountEnvironment.getEnvCommerceLabel());
		attributes.put("envCS", accountEnvironment.getEnvCSLabel());
		attributes.put("envDB", accountEnvironment.getEnvDBLabel());
		attributes.put("envJVM", accountEnvironment.getEnvJVMLabel());
		attributes.put("envLFR", accountEnvironment.getEnvLFRLabel());
		attributes.put("envOS", accountEnvironment.getEnvOSLabel());

		String envSearchLabels = StringUtil.merge(
			accountEnvironment.getEnvSearchLabels());

		attributes.put("envSearch", envSearchLabels);

		return attributes;
	}

	@Override
	public Class getModelClass() {
		return AccountEnvironment.class;
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}
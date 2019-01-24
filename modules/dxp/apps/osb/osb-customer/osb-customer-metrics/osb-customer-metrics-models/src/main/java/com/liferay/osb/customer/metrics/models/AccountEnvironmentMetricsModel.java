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
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.AccountEnvironmentConstants;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class AccountEnvironmentMetricsModel
	extends BaseMetricsModel<AccountEnvironment> {

	@Override
	public Class getModelClass() {
		return AccountEnvironment.class;
	}

	@Override
	public Map<String, Object> transformAttributes(
		BaseModel<AccountEnvironment> model) {

		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				model.getModelAttributes());

		Integer envOS = (Integer)attributes.get("envOS");

		if (envOS != null) {
			attributes.put(
				"envOS", AccountEnvironmentConstants.getEnvLabel(envOS));
		}

		Integer envDB = (Integer)attributes.get("envDB");

		if (envDB != null) {
			attributes.put(
				"envDB", AccountEnvironmentConstants.getEnvLabel(envDB));
		}

		Integer envJVM = (Integer)attributes.get("envJVM");

		if (envJVM != null) {
			attributes.put(
				"envJVM", AccountEnvironmentConstants.getEnvLabel(envJVM));
		}

		Integer envAS = (Integer)attributes.get("envAS");

		if (envAS != null) {
			attributes.put(
				"envAS", AccountEnvironmentConstants.getEnvLabel(envAS));
		}

		Integer envLFR = (Integer)attributes.get("envLFR");

		if (envLFR != null) {
			attributes.put(
				"envLFR", AccountEnvironmentConstants.getEnvLabel(envLFR));
		}

		Integer envBrowser = (Integer)attributes.get("envBrowser");

		if (envBrowser != null) {
			attributes.put(
				"envBrowser",
				AccountEnvironmentConstants.getEnvLabel(envBrowser));
		}

		Integer envCS = (Integer)attributes.get("envCS");

		if (envCS != null) {
			attributes.put(
				"envCS", AccountEnvironmentConstants.getEnvLabel(envCS));
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
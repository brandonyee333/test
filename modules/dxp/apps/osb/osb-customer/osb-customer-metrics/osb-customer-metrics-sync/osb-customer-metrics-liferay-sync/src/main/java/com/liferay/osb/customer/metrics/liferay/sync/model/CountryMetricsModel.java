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
import com.liferay.osb.customer.metrics.api.rabbitmq.constants.RoutingKeys;
import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.liferay.sync.model.util.MetricsTransformationUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.service.CountryService;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class CountryMetricsModel extends BaseModelMetricsModel<Country> {

	@Override
	public Map<String, Object> getAttributes(Country country) {
		return _metricsTransformationUtil.transformSharedAttributes(
			country.getModelAttributes());
	}

	@Override
	public Class getModelClass() {
		return Country.class;
	}

	@Override
	public void resyncAll() throws Exception {
		List<Country> countries = _countryService.getCountries();

		for (Country country : countries) {
			JSONObject jsonObject = messageFactory.createUpdateJSONObject(
				country.getModelClassName(), country);

			messagePublisher.sendMessage(
				RoutingKeys.METRICS_UPDATE, jsonObject);
		}
	}

	@Reference
	private CountryService _countryService;

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}
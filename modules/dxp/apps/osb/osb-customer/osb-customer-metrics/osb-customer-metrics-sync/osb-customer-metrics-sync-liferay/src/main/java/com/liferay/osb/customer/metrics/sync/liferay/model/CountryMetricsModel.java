/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.rabbitmq.constants.RoutingKeys;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;
import com.liferay.osb.distributed.messaging.Message;
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
	public boolean allowDeleteAll() {
		return false;
	}

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
			Message message = messageFactory.createUpdateMessage(
				country.getModelClassName(), country);

			messagePublisher.publish(RoutingKeys.METRICS_UPDATE, message);
		}
	}

	@Reference
	private CountryService _countryService;

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}
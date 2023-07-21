/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;
import com.liferay.portal.kernel.model.Address;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class AddressMetricsModel extends BaseModelMetricsModel<Address> {

	@Override
	public Map<String, Object> getAttributes(Address address) {
		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				address.getModelAttributes());

		attributes.put("addressId", -1 * address.getAddressId());

		attributes.remove("uuid");

		return attributes;
	}

	@Override
	public Class getModelClass() {
		return Address.class;
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}
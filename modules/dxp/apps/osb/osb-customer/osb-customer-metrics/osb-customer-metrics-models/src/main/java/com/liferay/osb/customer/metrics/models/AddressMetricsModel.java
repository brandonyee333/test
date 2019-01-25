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
import com.liferay.portal.kernel.model.Address;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class AddressMetricsModel extends BaseMetricsModel<Address> {

	@Override
	public Map<String, Object> getAttributes(Address address) {
		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				address.getModelAttributes());

		attributes.remove("uuid");

		Long addressId = (Long)attributes.get("addressId");

		if (addressId != null) {
			attributes.put("addressId", -addressId);
		}

		return attributes;
	}

	@Override
	public Class getModelClass() {
		return Address.class;
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}
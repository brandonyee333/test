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

package com.liferay.osb.customer.metrics.listeners;

import com.liferay.osb.customer.metrics.impl.model.BaseMetricsModelListener;
import com.liferay.osb.customer.metrics.listeners.constants.MetricsListenerConstants;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = ModelListener.class)
public class AddressModelListener extends BaseMetricsModelListener<Address> {

	@Override
	public boolean ignoreUpdate(Address address) throws ModelListenerException {
		Map<String, Object> attributes = address.getModelAttributes();

		Long classNameId = (Long)attributes.get("classNameId");

		if (!ArrayUtil.contains(_OSB_CLASS_NAME_IDS, classNameId)) {
			return true;
		}

		return false;
	}

	private static final long[] _OSB_CLASS_NAME_IDS = {
		MetricsListenerConstants.ACCOUNT_ENTRY_CLASS_NAME_ID,
		MetricsListenerConstants.PARTNER_ENTRY_CLASS_NAME_ID
	};

}
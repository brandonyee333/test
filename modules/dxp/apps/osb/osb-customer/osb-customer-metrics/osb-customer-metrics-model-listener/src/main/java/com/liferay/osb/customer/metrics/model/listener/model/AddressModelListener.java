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

package com.liferay.osb.customer.metrics.model.listener.model;

import com.liferay.osb.customer.metrics.impl.model.BaseMetricsModelListener;
import com.liferay.osb.customer.metrics.model.listener.constants.ClassNameConstants;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = ModelListener.class)
public class AddressModelListener extends BaseMetricsModelListener<Address> {

	@Override
	public boolean ignoreUpdate(Address address) throws ModelListenerException {
		if (ArrayUtil.contains(_OSB_CLASS_NAME_IDS, address.getClassNameId())) {
			return false;
		}

		return true;
	}

	private static final long[] _OSB_CLASS_NAME_IDS = {
		ClassNameConstants.ACCOUNT_ENTRY_CLASS_NAME_ID,
		ClassNameConstants.PARTNER_ENTRY_CLASS_NAME_ID
	};

}
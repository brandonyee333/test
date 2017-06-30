/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AppPricing;
import com.liferay.osb.service.AppPricingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Ryan Park
 */
public class CountryAppPricingImpl extends CountryAppPricingBaseImpl {

	public CountryAppPricingImpl() {
	}

	public AppPricing getAppPricing() throws PortalException, SystemException {
		return AppPricingLocalServiceUtil.getAppPricing(getAppPricingId());
	}

}
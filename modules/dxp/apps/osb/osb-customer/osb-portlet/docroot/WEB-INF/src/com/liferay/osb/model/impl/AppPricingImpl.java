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

import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.CurrencyEntry;
import com.liferay.osb.service.CurrencyEntryLocalServiceUtil;
import com.liferay.osb.util.CurrencyUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public class AppPricingImpl extends AppPricingBaseImpl {

	public AppPricingImpl() {
	}

	public String getCurrencyCode() throws PortalException, SystemException {
		CurrencyEntry currencyEntry =
			CurrencyEntryLocalServiceUtil.getCurrencyEntry(
				getCurrencyEntryId());

		return currencyEntry.getCurrencyCode();
	}

	public String getFormattedSupportPrice(int usageType, Locale locale)
		throws PortalException, SystemException {

		CurrencyEntry currencyEntry =
			CurrencyEntryLocalServiceUtil.getCurrencyEntry(
				getCurrencyEntryId());

		String formattedPrice = CurrencyUtil.format(
			locale, currencyEntry.getCountryId(), getSupportPrice(usageType));

		if (!formattedPrice.contains(currencyEntry.getCurrencyCode())) {
			formattedPrice +=
				StringPool.SPACE + currencyEntry.getCurrencyCode();
		}

		return formattedPrice;
	}

	public double getSupportPrice(int usageType) {
		if (usageType == AssetLicenseConstants.USAGE_TYPE_DEVELOPER) {
			return getDeveloperSupportPrice();
		}
		else if (usageType == AssetLicenseConstants.USAGE_TYPE_STANDARD) {
			return getStandardSupportPrice();
		}

		return 0;
	}

}
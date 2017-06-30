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

import com.liferay.osb.model.CurrencyEntry;
import com.liferay.osb.service.CurrencyEntryLocalServiceUtil;
import com.liferay.osb.util.CurrencyUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Country;
import com.liferay.portal.service.CountryServiceUtil;

import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 */
public class AppPricingItemImpl extends AppPricingItemBaseImpl {

	public AppPricingItemImpl() {
	}

	public Country getCountry() throws PortalException, SystemException {
		if (_country != null) {
			return _country;
		}

		CurrencyEntry currencyEntry = getCurrencyEntry();

		Country country = CountryServiceUtil.getCountry(
			currencyEntry.getCountryId());

		_country = country;

		return _country;
	}

	public String getCurrencyCode() throws SystemException {
		try {
			CurrencyEntry currencyEntry = getCurrencyEntry();

			return currencyEntry.getCurrencyCode();
		}
		catch (PortalException pe) {
		}

		return StringPool.BLANK;
	}

	public CurrencyEntry getCurrencyEntry()
		throws PortalException, SystemException {

		if (_currencyEntry != null) {
			return _currencyEntry;
		}

		CurrencyEntry currencyEntry =
			CurrencyEntryLocalServiceUtil.getCurrencyEntry(
				getCurrencyEntryId());

		_currencyEntry = currencyEntry;

		return _currencyEntry;
	}

	public String getFormattedPrice(Locale locale)
		throws PortalException, SystemException {

		CurrencyEntry currencyEntry = getCurrencyEntry();

		String formattedPrice = CurrencyUtil.format(
			locale, currencyEntry.getCountryId(), getPrice());

		if (!formattedPrice.contains(currencyEntry.getCurrencyCode())) {
			formattedPrice +=
				StringPool.SPACE + currencyEntry.getCurrencyCode();
		}

		return formattedPrice;
	}

	private Country _country;
	private CurrencyEntry _currencyEntry;

}
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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.CurrencyEntryCodeException;
import com.liferay.osb.CurrencyEntryCountryException;
import com.liferay.osb.DuplicateCurrencyEntryException;
import com.liferay.osb.model.CurrencyEntry;
import com.liferay.osb.service.base.CurrencyEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class CurrencyEntryLocalServiceImpl
	extends CurrencyEntryLocalServiceBaseImpl {

	public CurrencyEntry addCurrencyEntry(
			long countryId, String currencyCode, boolean marketplaceEnabled,
			double marketplaceMinPrice)
		throws PortalException, SystemException {

		validate(0, countryId, currencyCode);

		long currencyEntryId = counterLocalService.increment();

		CurrencyEntry currencyEntry = currencyEntryPersistence.create(
			currencyEntryId);

		currencyEntry.setCountryId(countryId);
		currencyEntry.setCurrencyCode(currencyCode);
		currencyEntry.setMarketplaceEnabled(marketplaceEnabled);
		currencyEntry.setMarketplaceMinPrice(marketplaceMinPrice);

		currencyEntryPersistence.update(currencyEntry, false);

		return currencyEntry;
	}

	public CurrencyEntry fetchCurrencyEntry(String currencyCode)
		throws SystemException {

		return currencyEntryPersistence.fetchByCurrencyCode(currencyCode);
	}

	public List<CurrencyEntry> getCurrencyEntries(boolean marketplaceEnabled)
		throws SystemException {

		return currencyEntryPersistence.findByMarketplaceEnabled(
			marketplaceEnabled);
	}

	public CurrencyEntry getCurrencyEntry(String currencyCode)
		throws PortalException, SystemException {

		return currencyEntryPersistence.findByCurrencyCode(currencyCode);
	}

	public CurrencyEntry updateCurrencyEntry(
			long currencyEntryId, long countryId, String currencyCode,
			boolean marketplaceEnabled, double marketplaceMinPrice)
		throws PortalException, SystemException {

		validate(currencyEntryId, countryId, currencyCode);

		CurrencyEntry currencyEntry = currencyEntryPersistence.findByPrimaryKey(
			currencyEntryId);

		currencyEntry.setCountryId(countryId);
		currencyEntry.setCurrencyCode(currencyCode);
		currencyEntry.setMarketplaceEnabled(marketplaceEnabled);
		currencyEntry.setMarketplaceMinPrice(marketplaceMinPrice);

		currencyEntryPersistence.update(currencyEntry, false);

		return currencyEntry;
	}

	protected void validate(
			long currencyEntryId, long countryId, String currencyCode)
		throws PortalException, SystemException {

		if (countryId <= 0) {
			throw new CurrencyEntryCountryException();
		}

		if (Validator.isNull(currencyCode)) {
			throw new CurrencyEntryCodeException();
		}

		CurrencyEntry currencyEntry =
			currencyEntryPersistence.fetchByCurrencyCode(currencyCode);

		if ((currencyEntry != null) &&
			(currencyEntry.getCurrencyEntryId() != currencyEntryId)) {

			throw new DuplicateCurrencyEntryException();
		}
	}

}
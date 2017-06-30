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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing CurrencyEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CurrencyEntry
 * @generated
 */
public class CurrencyEntryCacheModel implements CacheModel<CurrencyEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{currencyEntryId=");
		sb.append(currencyEntryId);
		sb.append(", countryId=");
		sb.append(countryId);
		sb.append(", currencyCode=");
		sb.append(currencyCode);
		sb.append(", marketplaceEnabled=");
		sb.append(marketplaceEnabled);
		sb.append(", marketplaceMinPrice=");
		sb.append(marketplaceMinPrice);
		sb.append("}");

		return sb.toString();
	}

	public CurrencyEntry toEntityModel() {
		CurrencyEntryImpl currencyEntryImpl = new CurrencyEntryImpl();

		currencyEntryImpl.setCurrencyEntryId(currencyEntryId);
		currencyEntryImpl.setCountryId(countryId);

		if (currencyCode == null) {
			currencyEntryImpl.setCurrencyCode(StringPool.BLANK);
		}
		else {
			currencyEntryImpl.setCurrencyCode(currencyCode);
		}

		currencyEntryImpl.setMarketplaceEnabled(marketplaceEnabled);
		currencyEntryImpl.setMarketplaceMinPrice(marketplaceMinPrice);

		currencyEntryImpl.resetOriginalValues();

		return currencyEntryImpl;
	}

	public long currencyEntryId;
	public long countryId;
	public String currencyCode;
	public boolean marketplaceEnabled;
	public double marketplaceMinPrice;
}
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

import com.liferay.osb.model.CountryAppPricing;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing CountryAppPricing in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CountryAppPricing
 * @generated
 */
public class CountryAppPricingCacheModel implements CacheModel<CountryAppPricing>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{countryAppPricingId=");
		sb.append(countryAppPricingId);
		sb.append(", appEntryId=");
		sb.append(appEntryId);
		sb.append(", appVersionId=");
		sb.append(appVersionId);
		sb.append(", appPricingId=");
		sb.append(appPricingId);
		sb.append(", countryId=");
		sb.append(countryId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	public CountryAppPricing toEntityModel() {
		CountryAppPricingImpl countryAppPricingImpl = new CountryAppPricingImpl();

		countryAppPricingImpl.setCountryAppPricingId(countryAppPricingId);
		countryAppPricingImpl.setAppEntryId(appEntryId);
		countryAppPricingImpl.setAppVersionId(appVersionId);
		countryAppPricingImpl.setAppPricingId(appPricingId);
		countryAppPricingImpl.setCountryId(countryId);

		if (name == null) {
			countryAppPricingImpl.setName(StringPool.BLANK);
		}
		else {
			countryAppPricingImpl.setName(name);
		}

		countryAppPricingImpl.resetOriginalValues();

		return countryAppPricingImpl;
	}

	public long countryAppPricingId;
	public long appEntryId;
	public long appVersionId;
	public long appPricingId;
	public long countryId;
	public String name;
}
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

import com.liferay.osb.model.AppPricingItem;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing AppPricingItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AppPricingItem
 * @generated
 */
public class AppPricingItemCacheModel implements CacheModel<AppPricingItem>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{appPricingItemId=");
		sb.append(appPricingItemId);
		sb.append(", appPricingId=");
		sb.append(appPricingId);
		sb.append(", assetLicenseId=");
		sb.append(assetLicenseId);
		sb.append(", currencyEntryId=");
		sb.append(currencyEntryId);
		sb.append(", price=");
		sb.append(price);
		sb.append("}");

		return sb.toString();
	}

	public AppPricingItem toEntityModel() {
		AppPricingItemImpl appPricingItemImpl = new AppPricingItemImpl();

		appPricingItemImpl.setAppPricingItemId(appPricingItemId);
		appPricingItemImpl.setAppPricingId(appPricingId);
		appPricingItemImpl.setAssetLicenseId(assetLicenseId);
		appPricingItemImpl.setCurrencyEntryId(currencyEntryId);
		appPricingItemImpl.setPrice(price);

		appPricingItemImpl.resetOriginalValues();

		return appPricingItemImpl;
	}

	public long appPricingItemId;
	public long appPricingId;
	public long assetLicenseId;
	public long currencyEntryId;
	public double price;
}
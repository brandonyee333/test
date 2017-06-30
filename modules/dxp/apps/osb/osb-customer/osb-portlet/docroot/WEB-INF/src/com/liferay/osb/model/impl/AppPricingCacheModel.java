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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AppPricing in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AppPricing
 * @generated
 */
public class AppPricingCacheModel implements CacheModel<AppPricing>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{appPricingId=");
		sb.append(appPricingId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", appEntryId=");
		sb.append(appEntryId);
		sb.append(", appVersionId=");
		sb.append(appVersionId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", currencyEntryId=");
		sb.append(currencyEntryId);
		sb.append(", standardSupportPrice=");
		sb.append(standardSupportPrice);
		sb.append(", developerSupportPrice=");
		sb.append(developerSupportPrice);
		sb.append(", rank=");
		sb.append(rank);
		sb.append("}");

		return sb.toString();
	}

	public AppPricing toEntityModel() {
		AppPricingImpl appPricingImpl = new AppPricingImpl();

		appPricingImpl.setAppPricingId(appPricingId);
		appPricingImpl.setUserId(userId);

		if (userName == null) {
			appPricingImpl.setUserName(StringPool.BLANK);
		}
		else {
			appPricingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			appPricingImpl.setCreateDate(null);
		}
		else {
			appPricingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			appPricingImpl.setModifiedDate(null);
		}
		else {
			appPricingImpl.setModifiedDate(new Date(modifiedDate));
		}

		appPricingImpl.setAppEntryId(appEntryId);
		appPricingImpl.setAppVersionId(appVersionId);

		if (name == null) {
			appPricingImpl.setName(StringPool.BLANK);
		}
		else {
			appPricingImpl.setName(name);
		}

		appPricingImpl.setCurrencyEntryId(currencyEntryId);
		appPricingImpl.setStandardSupportPrice(standardSupportPrice);
		appPricingImpl.setDeveloperSupportPrice(developerSupportPrice);
		appPricingImpl.setRank(rank);

		appPricingImpl.resetOriginalValues();

		return appPricingImpl;
	}

	public long appPricingId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long appEntryId;
	public long appVersionId;
	public String name;
	public long currencyEntryId;
	public double standardSupportPrice;
	public double developerSupportPrice;
	public int rank;
}
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

import com.liferay.osb.model.LCSSubscriptionEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing LCSSubscriptionEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LCSSubscriptionEntry
 * @generated
 */
public class LCSSubscriptionEntryCacheModel implements CacheModel<LCSSubscriptionEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{lcsSubscriptionEntryId=");
		sb.append(lcsSubscriptionEntryId);
		sb.append(", lcsProjectId=");
		sb.append(lcsProjectId);
		sb.append(", product=");
		sb.append(product);
		sb.append(", productVersion=");
		sb.append(productVersion);
		sb.append(", type=");
		sb.append(type);
		sb.append(", platform=");
		sb.append(platform);
		sb.append(", platformVersion=");
		sb.append(platformVersion);
		sb.append(", serversAllowed=");
		sb.append(serversAllowed);
		sb.append(", serversUsed=");
		sb.append(serversUsed);
		sb.append(", instanceSize=");
		sb.append(instanceSize);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", supportStartDate=");
		sb.append(supportStartDate);
		sb.append(", supportEndDate=");
		sb.append(supportEndDate);
		sb.append(", actualPrice=");
		sb.append(actualPrice);
		sb.append(", currencyCode=");
		sb.append(currencyCode);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	public LCSSubscriptionEntry toEntityModel() {
		LCSSubscriptionEntryImpl lcsSubscriptionEntryImpl = new LCSSubscriptionEntryImpl();

		lcsSubscriptionEntryImpl.setLcsSubscriptionEntryId(lcsSubscriptionEntryId);
		lcsSubscriptionEntryImpl.setLcsProjectId(lcsProjectId);

		if (product == null) {
			lcsSubscriptionEntryImpl.setProduct(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setProduct(product);
		}

		lcsSubscriptionEntryImpl.setProductVersion(productVersion);

		if (type == null) {
			lcsSubscriptionEntryImpl.setType(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setType(type);
		}

		if (platform == null) {
			lcsSubscriptionEntryImpl.setPlatform(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setPlatform(platform);
		}

		if (platformVersion == null) {
			lcsSubscriptionEntryImpl.setPlatformVersion(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setPlatformVersion(platformVersion);
		}

		lcsSubscriptionEntryImpl.setServersAllowed(serversAllowed);
		lcsSubscriptionEntryImpl.setServersUsed(serversUsed);
		lcsSubscriptionEntryImpl.setInstanceSize(instanceSize);

		if (startDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setStartDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setEndDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setEndDate(new Date(endDate));
		}

		if (supportStartDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setSupportStartDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setSupportStartDate(new Date(
					supportStartDate));
		}

		if (supportEndDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setSupportEndDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setSupportEndDate(new Date(supportEndDate));
		}

		lcsSubscriptionEntryImpl.setActualPrice(actualPrice);

		if (currencyCode == null) {
			lcsSubscriptionEntryImpl.setCurrencyCode(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setCurrencyCode(currencyCode);
		}

		lcsSubscriptionEntryImpl.setActive(active);

		lcsSubscriptionEntryImpl.resetOriginalValues();

		return lcsSubscriptionEntryImpl;
	}

	public long lcsSubscriptionEntryId;
	public long lcsProjectId;
	public String product;
	public int productVersion;
	public String type;
	public String platform;
	public String platformVersion;
	public int serversAllowed;
	public int serversUsed;
	public int instanceSize;
	public long startDate;
	public long endDate;
	public long supportStartDate;
	public long supportEndDate;
	public double actualPrice;
	public String currencyCode;
	public boolean active;
}
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

import com.liferay.osb.model.AssetAudit;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetAudit in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetAudit
 * @generated
 */
public class AssetAuditCacheModel implements CacheModel<AssetAudit>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{assetAuditId=");
		sb.append(assetAuditId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", legalEntityName=");
		sb.append(legalEntityName);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", vendorClassNameId=");
		sb.append(vendorClassNameId);
		sb.append(", vendorClassPK=");
		sb.append(vendorClassPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", domain=");
		sb.append(domain);
		sb.append(", time=");
		sb.append(time);
		sb.append(", currencyCode=");
		sb.append(currencyCode);
		sb.append(", price=");
		sb.append(price);
		sb.append("}");

		return sb.toString();
	}

	public AssetAudit toEntityModel() {
		AssetAuditImpl assetAuditImpl = new AssetAuditImpl();

		assetAuditImpl.setAssetAuditId(assetAuditId);
		assetAuditImpl.setCompanyId(companyId);
		assetAuditImpl.setUserId(userId);

		if (userName == null) {
			assetAuditImpl.setUserName(StringPool.BLANK);
		}
		else {
			assetAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetAuditImpl.setCreateDate(null);
		}
		else {
			assetAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetAuditImpl.setModifiedDate(null);
		}
		else {
			assetAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (legalEntityName == null) {
			assetAuditImpl.setLegalEntityName(StringPool.BLANK);
		}
		else {
			assetAuditImpl.setLegalEntityName(legalEntityName);
		}

		assetAuditImpl.setClassNameId(classNameId);
		assetAuditImpl.setClassPK(classPK);
		assetAuditImpl.setVendorClassNameId(vendorClassNameId);
		assetAuditImpl.setVendorClassPK(vendorClassPK);
		assetAuditImpl.setType(type);
		assetAuditImpl.setDomain(domain);
		assetAuditImpl.setTime(time);

		if (currencyCode == null) {
			assetAuditImpl.setCurrencyCode(StringPool.BLANK);
		}
		else {
			assetAuditImpl.setCurrencyCode(currencyCode);
		}

		assetAuditImpl.setPrice(price);

		assetAuditImpl.resetOriginalValues();

		return assetAuditImpl;
	}

	public long assetAuditId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String legalEntityName;
	public long classNameId;
	public long classPK;
	public long vendorClassNameId;
	public long vendorClassPK;
	public int type;
	public int domain;
	public long time;
	public String currencyCode;
	public double price;
}
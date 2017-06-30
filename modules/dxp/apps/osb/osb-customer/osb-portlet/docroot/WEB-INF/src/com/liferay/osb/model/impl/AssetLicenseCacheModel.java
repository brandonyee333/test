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

import com.liferay.osb.model.AssetLicense;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetLicense in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetLicense
 * @generated
 */
public class AssetLicenseCacheModel implements CacheModel<AssetLicense>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{assetLicenseId=");
		sb.append(assetLicenseId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", licenseId=");
		sb.append(licenseId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", requiredVersion=");
		sb.append(requiredVersion);
		sb.append(", usageType=");
		sb.append(usageType);
		sb.append(", licenseType=");
		sb.append(licenseType);
		sb.append(", licenseTypeAllotment=");
		sb.append(licenseTypeAllotment);
		sb.append(", lifetime=");
		sb.append(lifetime);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public AssetLicense toEntityModel() {
		AssetLicenseImpl assetLicenseImpl = new AssetLicenseImpl();

		assetLicenseImpl.setAssetLicenseId(assetLicenseId);
		assetLicenseImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			assetLicenseImpl.setCreateDate(null);
		}
		else {
			assetLicenseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetLicenseImpl.setModifiedDate(null);
		}
		else {
			assetLicenseImpl.setModifiedDate(new Date(modifiedDate));
		}

		assetLicenseImpl.setClassNameId(classNameId);
		assetLicenseImpl.setClassPK(classPK);

		if (licenseId == null) {
			assetLicenseImpl.setLicenseId(StringPool.BLANK);
		}
		else {
			assetLicenseImpl.setLicenseId(licenseId);
		}

		if (name == null) {
			assetLicenseImpl.setName(StringPool.BLANK);
		}
		else {
			assetLicenseImpl.setName(name);
		}

		assetLicenseImpl.setRequiredVersion(requiredVersion);
		assetLicenseImpl.setUsageType(usageType);
		assetLicenseImpl.setLicenseType(licenseType);
		assetLicenseImpl.setLicenseTypeAllotment(licenseTypeAllotment);
		assetLicenseImpl.setLifetime(lifetime);
		assetLicenseImpl.setStatus(status);

		assetLicenseImpl.resetOriginalValues();

		return assetLicenseImpl;
	}

	public long assetLicenseId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String licenseId;
	public String name;
	public double requiredVersion;
	public int usageType;
	public int licenseType;
	public long licenseTypeAllotment;
	public long lifetime;
	public int status;
}
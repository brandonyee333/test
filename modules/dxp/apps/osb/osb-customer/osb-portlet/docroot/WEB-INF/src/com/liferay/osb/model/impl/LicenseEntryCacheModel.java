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

import com.liferay.osb.model.LicenseEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing LicenseEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntry
 * @generated
 */
public class LicenseEntryCacheModel implements CacheModel<LicenseEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{licenseEntryId=");
		sb.append(licenseEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", type=");
		sb.append(type);
		sb.append(", portalVersionMin=");
		sb.append(portalVersionMin);
		sb.append(", portalVersionMax=");
		sb.append(portalVersionMax);
		sb.append("}");

		return sb.toString();
	}

	public LicenseEntry toEntityModel() {
		LicenseEntryImpl licenseEntryImpl = new LicenseEntryImpl();

		licenseEntryImpl.setLicenseEntryId(licenseEntryId);
		licenseEntryImpl.setUserId(userId);

		if (userName == null) {
			licenseEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			licenseEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			licenseEntryImpl.setCreateDate(null);
		}
		else {
			licenseEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			licenseEntryImpl.setModifiedDate(null);
		}
		else {
			licenseEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		licenseEntryImpl.setProductEntryId(productEntryId);

		if (name == null) {
			licenseEntryImpl.setName(StringPool.BLANK);
		}
		else {
			licenseEntryImpl.setName(name);
		}

		if (type == null) {
			licenseEntryImpl.setType(StringPool.BLANK);
		}
		else {
			licenseEntryImpl.setType(type);
		}

		licenseEntryImpl.setPortalVersionMin(portalVersionMin);
		licenseEntryImpl.setPortalVersionMax(portalVersionMax);

		licenseEntryImpl.resetOriginalValues();

		return licenseEntryImpl;
	}

	public long licenseEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long productEntryId;
	public String name;
	public String type;
	public int portalVersionMin;
	public int portalVersionMax;
}
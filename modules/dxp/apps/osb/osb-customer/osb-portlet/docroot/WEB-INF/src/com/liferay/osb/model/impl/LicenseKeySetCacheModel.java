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

import com.liferay.osb.model.LicenseKeySet;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing LicenseKeySet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySet
 * @generated
 */
public class LicenseKeySetCacheModel implements CacheModel<LicenseKeySet>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{licenseKeySetId=");
		sb.append(licenseKeySetId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	public LicenseKeySet toEntityModel() {
		LicenseKeySetImpl licenseKeySetImpl = new LicenseKeySetImpl();

		licenseKeySetImpl.setLicenseKeySetId(licenseKeySetId);
		licenseKeySetImpl.setUserId(userId);

		if (userName == null) {
			licenseKeySetImpl.setUserName(StringPool.BLANK);
		}
		else {
			licenseKeySetImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			licenseKeySetImpl.setCreateDate(null);
		}
		else {
			licenseKeySetImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			licenseKeySetImpl.setModifiedDate(null);
		}
		else {
			licenseKeySetImpl.setModifiedDate(new Date(modifiedDate));
		}

		licenseKeySetImpl.setAccountEntryId(accountEntryId);

		if (name == null) {
			licenseKeySetImpl.setName(StringPool.BLANK);
		}
		else {
			licenseKeySetImpl.setName(name);
		}

		licenseKeySetImpl.resetOriginalValues();

		return licenseKeySetImpl;
	}

	public long licenseKeySetId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountEntryId;
	public String name;
}
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.LicenseKeySet;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LicenseKeySet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySet
 * @generated
 */
@ProviderType
public class LicenseKeySetCacheModel implements CacheModel<LicenseKeySet>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LicenseKeySetCacheModel)) {
			return false;
		}

		LicenseKeySetCacheModel licenseKeySetCacheModel = (LicenseKeySetCacheModel)obj;

		if (licenseKeySetId == licenseKeySetCacheModel.licenseKeySetId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, licenseKeySetId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		licenseKeySetId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(licenseKeySetId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountEntryId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long licenseKeySetId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountEntryId;
	public String name;
}
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

import com.liferay.osb.model.LicenseEntry;

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
 * The cache model class for representing LicenseEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntry
 * @generated
 */
@ProviderType
public class LicenseEntryCacheModel implements CacheModel<LicenseEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LicenseEntryCacheModel)) {
			return false;
		}

		LicenseEntryCacheModel licenseEntryCacheModel = (LicenseEntryCacheModel)obj;

		if (licenseEntryId == licenseEntryCacheModel.licenseEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, licenseEntryId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		licenseEntryId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		productEntryId = objectInput.readLong();
		name = objectInput.readUTF();
		type = objectInput.readUTF();

		portalVersionMin = objectInput.readInt();

		portalVersionMax = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(licenseEntryId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(productEntryId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeInt(portalVersionMin);

		objectOutput.writeInt(portalVersionMax);
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
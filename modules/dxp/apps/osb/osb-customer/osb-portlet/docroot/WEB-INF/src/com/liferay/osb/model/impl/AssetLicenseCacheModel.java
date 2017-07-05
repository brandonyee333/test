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

import com.liferay.osb.model.AssetLicense;

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
 * The cache model class for representing AssetLicense in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetLicense
 * @generated
 */
@ProviderType
public class AssetLicenseCacheModel implements CacheModel<AssetLicense>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetLicenseCacheModel)) {
			return false;
		}

		AssetLicenseCacheModel assetLicenseCacheModel = (AssetLicenseCacheModel)obj;

		if (assetLicenseId == assetLicenseCacheModel.assetLicenseId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, assetLicenseId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		assetLicenseId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		licenseId = objectInput.readUTF();
		name = objectInput.readUTF();

		requiredVersion = objectInput.readDouble();

		usageType = objectInput.readInt();

		licenseType = objectInput.readInt();

		licenseTypeAllotment = objectInput.readLong();

		lifetime = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(assetLicenseId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (licenseId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(licenseId);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeDouble(requiredVersion);

		objectOutput.writeInt(usageType);

		objectOutput.writeInt(licenseType);

		objectOutput.writeLong(licenseTypeAllotment);

		objectOutput.writeLong(lifetime);

		objectOutput.writeInt(status);
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
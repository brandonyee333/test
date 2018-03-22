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

import com.liferay.osb.model.SupportRegion;

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
 * The cache model class for representing SupportRegion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegion
 * @generated
 */
@ProviderType
public class SupportRegionCacheModel implements CacheModel<SupportRegion>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportRegionCacheModel)) {
			return false;
		}

		SupportRegionCacheModel supportRegionCacheModel = (SupportRegionCacheModel)obj;

		if (supportRegionId == supportRegionCacheModel.supportRegionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, supportRegionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{supportRegionId=");
		sb.append(supportRegionId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", timeZoneId=");
		sb.append(timeZoneId);
		sb.append(", managerUserId=");
		sb.append(managerUserId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SupportRegion toEntityModel() {
		SupportRegionImpl supportRegionImpl = new SupportRegionImpl();

		supportRegionImpl.setSupportRegionId(supportRegionId);
		supportRegionImpl.setCompanyId(companyId);
		supportRegionImpl.setUserId(userId);

		if (userName == null) {
			supportRegionImpl.setUserName(StringPool.BLANK);
		}
		else {
			supportRegionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			supportRegionImpl.setCreateDate(null);
		}
		else {
			supportRegionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			supportRegionImpl.setModifiedDate(null);
		}
		else {
			supportRegionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			supportRegionImpl.setName(StringPool.BLANK);
		}
		else {
			supportRegionImpl.setName(name);
		}

		if (description == null) {
			supportRegionImpl.setDescription(StringPool.BLANK);
		}
		else {
			supportRegionImpl.setDescription(description);
		}

		if (timeZoneId == null) {
			supportRegionImpl.setTimeZoneId(StringPool.BLANK);
		}
		else {
			supportRegionImpl.setTimeZoneId(timeZoneId);
		}

		supportRegionImpl.setManagerUserId(managerUserId);

		supportRegionImpl.resetOriginalValues();

		return supportRegionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		supportRegionId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		timeZoneId = objectInput.readUTF();

		managerUserId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(supportRegionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (timeZoneId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(timeZoneId);
		}

		objectOutput.writeLong(managerUserId);
	}

	public long supportRegionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String timeZoneId;
	public long managerUserId;
}
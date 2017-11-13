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

package com.liferay.watson.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.watson.model.WatsonVehicle;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonVehicle in entity cache.
 *
 * @author Steven Smith
 * @see WatsonVehicle
 * @generated
 */
@ProviderType
public class WatsonVehicleCacheModel implements CacheModel<WatsonVehicle>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonVehicleCacheModel)) {
			return false;
		}

		WatsonVehicleCacheModel watsonVehicleCacheModel = (WatsonVehicleCacheModel)obj;

		if (watsonVehicleId == watsonVehicleCacheModel.watsonVehicleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonVehicleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{watsonVehicleId=");
		sb.append(watsonVehicleId);
		sb.append(", groupId=");
		sb.append(groupId);
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
		sb.append(", colorWatsonListTypeId=");
		sb.append(colorWatsonListTypeId);
		sb.append(", makeWatsonListTypeId=");
		sb.append(makeWatsonListTypeId);
		sb.append(", modelWatsonListTypeId=");
		sb.append(modelWatsonListTypeId);
		sb.append(", originalWatsonVehicleId=");
		sb.append(originalWatsonVehicleId);
		sb.append(", typeWatsonListTypeId=");
		sb.append(typeWatsonListTypeId);
		sb.append(", yearWatsonListTypeId=");
		sb.append(yearWatsonListTypeId);
		sb.append(", watsonIncidentId=");
		sb.append(watsonIncidentId);
		sb.append(", year=");
		sb.append(year);
		sb.append(", description=");
		sb.append(description);
		sb.append(", imagePayload=");
		sb.append(imagePayload);
		sb.append(", licensePlate=");
		sb.append(licensePlate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonVehicle toEntityModel() {
		WatsonVehicleImpl watsonVehicleImpl = new WatsonVehicleImpl();

		watsonVehicleImpl.setWatsonVehicleId(watsonVehicleId);
		watsonVehicleImpl.setGroupId(groupId);
		watsonVehicleImpl.setCompanyId(companyId);
		watsonVehicleImpl.setUserId(userId);

		if (userName == null) {
			watsonVehicleImpl.setUserName(StringPool.BLANK);
		}
		else {
			watsonVehicleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonVehicleImpl.setCreateDate(null);
		}
		else {
			watsonVehicleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonVehicleImpl.setModifiedDate(null);
		}
		else {
			watsonVehicleImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonVehicleImpl.setColorWatsonListTypeId(colorWatsonListTypeId);
		watsonVehicleImpl.setMakeWatsonListTypeId(makeWatsonListTypeId);
		watsonVehicleImpl.setModelWatsonListTypeId(modelWatsonListTypeId);
		watsonVehicleImpl.setOriginalWatsonVehicleId(originalWatsonVehicleId);
		watsonVehicleImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonVehicleImpl.setYearWatsonListTypeId(yearWatsonListTypeId);
		watsonVehicleImpl.setWatsonIncidentId(watsonIncidentId);
		watsonVehicleImpl.setYear(year);

		if (description == null) {
			watsonVehicleImpl.setDescription(StringPool.BLANK);
		}
		else {
			watsonVehicleImpl.setDescription(description);
		}

		if (imagePayload == null) {
			watsonVehicleImpl.setImagePayload(StringPool.BLANK);
		}
		else {
			watsonVehicleImpl.setImagePayload(imagePayload);
		}

		if (licensePlate == null) {
			watsonVehicleImpl.setLicensePlate(StringPool.BLANK);
		}
		else {
			watsonVehicleImpl.setLicensePlate(licensePlate);
		}

		watsonVehicleImpl.setStatus(status);

		watsonVehicleImpl.resetOriginalValues();

		return watsonVehicleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonVehicleId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		colorWatsonListTypeId = objectInput.readLong();

		makeWatsonListTypeId = objectInput.readLong();

		modelWatsonListTypeId = objectInput.readLong();

		originalWatsonVehicleId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		yearWatsonListTypeId = objectInput.readLong();

		watsonIncidentId = objectInput.readLong();

		year = objectInput.readInt();
		description = objectInput.readUTF();
		imagePayload = objectInput.readUTF();
		licensePlate = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonVehicleId);

		objectOutput.writeLong(groupId);

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

		objectOutput.writeLong(colorWatsonListTypeId);

		objectOutput.writeLong(makeWatsonListTypeId);

		objectOutput.writeLong(modelWatsonListTypeId);

		objectOutput.writeLong(originalWatsonVehicleId);

		objectOutput.writeLong(typeWatsonListTypeId);

		objectOutput.writeLong(yearWatsonListTypeId);

		objectOutput.writeLong(watsonIncidentId);

		objectOutput.writeInt(year);

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (imagePayload == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(imagePayload);
		}

		if (licensePlate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(licensePlate);
		}

		objectOutput.writeInt(status);
	}

	public long watsonVehicleId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long colorWatsonListTypeId;
	public long makeWatsonListTypeId;
	public long modelWatsonListTypeId;
	public long originalWatsonVehicleId;
	public long typeWatsonListTypeId;
	public long yearWatsonListTypeId;
	public long watsonIncidentId;
	public int year;
	public String description;
	public String imagePayload;
	public String licensePlate;
	public int status;
}
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

import com.liferay.watson.model.WatsonAddress;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonAddress in entity cache.
 *
 * @author Eddie Olson
 * @see WatsonAddress
 * @generated
 */
@ProviderType
public class WatsonAddressCacheModel implements CacheModel<WatsonAddress>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonAddressCacheModel)) {
			return false;
		}

		WatsonAddressCacheModel watsonAddressCacheModel = (WatsonAddressCacheModel)obj;

		if (watsonAddressId == watsonAddressCacheModel.watsonAddressId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonAddressId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(55);

		sb.append("{watsonAddressId=");
		sb.append(watsonAddressId);
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
		sb.append(", countryId=");
		sb.append(countryId);
		sb.append(", districtWatsonListTypeId=");
		sb.append(districtWatsonListTypeId);
		sb.append(", originalWatsonAddressId=");
		sb.append(originalWatsonAddressId);
		sb.append(", provinceWatsonListTypeId=");
		sb.append(provinceWatsonListTypeId);
		sb.append(", subDistrictWatsonListTypeId=");
		sb.append(subDistrictWatsonListTypeId);
		sb.append(", typeWatsonListTypeId=");
		sb.append(typeWatsonListTypeId);
		sb.append(", watsonIncidentId=");
		sb.append(watsonIncidentId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", postalCode=");
		sb.append(postalCode);
		sb.append(", region=");
		sb.append(region);
		sb.append(", street=");
		sb.append(street);
		sb.append(", number=");
		sb.append(number);
		sb.append(", building=");
		sb.append(building);
		sb.append(", floor=");
		sb.append(floor);
		sb.append(", room=");
		sb.append(room);
		sb.append(", description=");
		sb.append(description);
		sb.append(", imagePayload=");
		sb.append(imagePayload);
		sb.append(", lastSeenDate=");
		sb.append(lastSeenDate);
		sb.append(", latitude=");
		sb.append(latitude);
		sb.append(", longitude=");
		sb.append(longitude);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonAddress toEntityModel() {
		WatsonAddressImpl watsonAddressImpl = new WatsonAddressImpl();

		watsonAddressImpl.setWatsonAddressId(watsonAddressId);
		watsonAddressImpl.setCompanyId(companyId);
		watsonAddressImpl.setUserId(userId);

		if (userName == null) {
			watsonAddressImpl.setUserName(StringPool.BLANK);
		}
		else {
			watsonAddressImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonAddressImpl.setCreateDate(null);
		}
		else {
			watsonAddressImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonAddressImpl.setModifiedDate(null);
		}
		else {
			watsonAddressImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonAddressImpl.setCountryId(countryId);
		watsonAddressImpl.setDistrictWatsonListTypeId(districtWatsonListTypeId);
		watsonAddressImpl.setOriginalWatsonAddressId(originalWatsonAddressId);
		watsonAddressImpl.setProvinceWatsonListTypeId(provinceWatsonListTypeId);
		watsonAddressImpl.setSubDistrictWatsonListTypeId(subDistrictWatsonListTypeId);
		watsonAddressImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonAddressImpl.setWatsonIncidentId(watsonIncidentId);

		if (name == null) {
			watsonAddressImpl.setName(StringPool.BLANK);
		}
		else {
			watsonAddressImpl.setName(name);
		}

		if (postalCode == null) {
			watsonAddressImpl.setPostalCode(StringPool.BLANK);
		}
		else {
			watsonAddressImpl.setPostalCode(postalCode);
		}

		if (region == null) {
			watsonAddressImpl.setRegion(StringPool.BLANK);
		}
		else {
			watsonAddressImpl.setRegion(region);
		}

		if (street == null) {
			watsonAddressImpl.setStreet(StringPool.BLANK);
		}
		else {
			watsonAddressImpl.setStreet(street);
		}

		if (number == null) {
			watsonAddressImpl.setNumber(StringPool.BLANK);
		}
		else {
			watsonAddressImpl.setNumber(number);
		}

		if (building == null) {
			watsonAddressImpl.setBuilding(StringPool.BLANK);
		}
		else {
			watsonAddressImpl.setBuilding(building);
		}

		if (floor == null) {
			watsonAddressImpl.setFloor(StringPool.BLANK);
		}
		else {
			watsonAddressImpl.setFloor(floor);
		}

		if (room == null) {
			watsonAddressImpl.setRoom(StringPool.BLANK);
		}
		else {
			watsonAddressImpl.setRoom(room);
		}

		if (description == null) {
			watsonAddressImpl.setDescription(StringPool.BLANK);
		}
		else {
			watsonAddressImpl.setDescription(description);
		}

		if (imagePayload == null) {
			watsonAddressImpl.setImagePayload(StringPool.BLANK);
		}
		else {
			watsonAddressImpl.setImagePayload(imagePayload);
		}

		if (lastSeenDate == Long.MIN_VALUE) {
			watsonAddressImpl.setLastSeenDate(null);
		}
		else {
			watsonAddressImpl.setLastSeenDate(new Date(lastSeenDate));
		}

		watsonAddressImpl.setLatitude(latitude);
		watsonAddressImpl.setLongitude(longitude);
		watsonAddressImpl.setStatus(status);

		watsonAddressImpl.resetOriginalValues();

		return watsonAddressImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonAddressId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		countryId = objectInput.readLong();

		districtWatsonListTypeId = objectInput.readLong();

		originalWatsonAddressId = objectInput.readLong();

		provinceWatsonListTypeId = objectInput.readLong();

		subDistrictWatsonListTypeId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		watsonIncidentId = objectInput.readLong();
		name = objectInput.readUTF();
		postalCode = objectInput.readUTF();
		region = objectInput.readUTF();
		street = objectInput.readUTF();
		number = objectInput.readUTF();
		building = objectInput.readUTF();
		floor = objectInput.readUTF();
		room = objectInput.readUTF();
		description = objectInput.readUTF();
		imagePayload = objectInput.readUTF();
		lastSeenDate = objectInput.readLong();

		latitude = objectInput.readDouble();

		longitude = objectInput.readDouble();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonAddressId);

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

		objectOutput.writeLong(countryId);

		objectOutput.writeLong(districtWatsonListTypeId);

		objectOutput.writeLong(originalWatsonAddressId);

		objectOutput.writeLong(provinceWatsonListTypeId);

		objectOutput.writeLong(subDistrictWatsonListTypeId);

		objectOutput.writeLong(typeWatsonListTypeId);

		objectOutput.writeLong(watsonIncidentId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (postalCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(postalCode);
		}

		if (region == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(region);
		}

		if (street == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(street);
		}

		if (number == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(number);
		}

		if (building == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(building);
		}

		if (floor == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(floor);
		}

		if (room == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(room);
		}

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

		objectOutput.writeLong(lastSeenDate);

		objectOutput.writeDouble(latitude);

		objectOutput.writeDouble(longitude);

		objectOutput.writeInt(status);
	}

	public long watsonAddressId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long countryId;
	public long districtWatsonListTypeId;
	public long originalWatsonAddressId;
	public long provinceWatsonListTypeId;
	public long subDistrictWatsonListTypeId;
	public long typeWatsonListTypeId;
	public long watsonIncidentId;
	public String name;
	public String postalCode;
	public String region;
	public String street;
	public String number;
	public String building;
	public String floor;
	public String room;
	public String description;
	public String imagePayload;
	public long lastSeenDate;
	public double latitude;
	public double longitude;
	public int status;
}
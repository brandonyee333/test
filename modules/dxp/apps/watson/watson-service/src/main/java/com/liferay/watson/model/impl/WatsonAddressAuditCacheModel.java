/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.watson.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.watson.model.WatsonAddressAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonAddressAudit in entity cache.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonAddressAuditCacheModel
	implements CacheModel<WatsonAddressAudit>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonAddressAuditCacheModel)) {
			return false;
		}

		WatsonAddressAuditCacheModel watsonAddressAuditCacheModel =
			(WatsonAddressAuditCacheModel)obj;

		if (watsonAddressAuditId ==
				watsonAddressAuditCacheModel.watsonAddressAuditId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonAddressAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(61);

		sb.append("{watsonAddressAuditId=");
		sb.append(watsonAddressAuditId);
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
		sb.append(", watsonAddressId=");
		sb.append(watsonAddressId);
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
		sb.append(", otherType=");
		sb.append(otherType);
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
	public WatsonAddressAudit toEntityModel() {
		WatsonAddressAuditImpl watsonAddressAuditImpl =
			new WatsonAddressAuditImpl();

		watsonAddressAuditImpl.setWatsonAddressAuditId(watsonAddressAuditId);
		watsonAddressAuditImpl.setGroupId(groupId);
		watsonAddressAuditImpl.setCompanyId(companyId);
		watsonAddressAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonAddressAuditImpl.setUserName("");
		}
		else {
			watsonAddressAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonAddressAuditImpl.setCreateDate(null);
		}
		else {
			watsonAddressAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonAddressAuditImpl.setModifiedDate(null);
		}
		else {
			watsonAddressAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonAddressAuditImpl.setCountryId(countryId);
		watsonAddressAuditImpl.setDistrictWatsonListTypeId(
			districtWatsonListTypeId);
		watsonAddressAuditImpl.setOriginalWatsonAddressId(
			originalWatsonAddressId);
		watsonAddressAuditImpl.setProvinceWatsonListTypeId(
			provinceWatsonListTypeId);
		watsonAddressAuditImpl.setSubDistrictWatsonListTypeId(
			subDistrictWatsonListTypeId);
		watsonAddressAuditImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonAddressAuditImpl.setWatsonAddressId(watsonAddressId);
		watsonAddressAuditImpl.setWatsonIncidentId(watsonIncidentId);

		if (name == null) {
			watsonAddressAuditImpl.setName("");
		}
		else {
			watsonAddressAuditImpl.setName(name);
		}

		if (postalCode == null) {
			watsonAddressAuditImpl.setPostalCode("");
		}
		else {
			watsonAddressAuditImpl.setPostalCode(postalCode);
		}

		if (region == null) {
			watsonAddressAuditImpl.setRegion("");
		}
		else {
			watsonAddressAuditImpl.setRegion(region);
		}

		if (street == null) {
			watsonAddressAuditImpl.setStreet("");
		}
		else {
			watsonAddressAuditImpl.setStreet(street);
		}

		if (number == null) {
			watsonAddressAuditImpl.setNumber("");
		}
		else {
			watsonAddressAuditImpl.setNumber(number);
		}

		if (building == null) {
			watsonAddressAuditImpl.setBuilding("");
		}
		else {
			watsonAddressAuditImpl.setBuilding(building);
		}

		if (floor == null) {
			watsonAddressAuditImpl.setFloor("");
		}
		else {
			watsonAddressAuditImpl.setFloor(floor);
		}

		if (room == null) {
			watsonAddressAuditImpl.setRoom("");
		}
		else {
			watsonAddressAuditImpl.setRoom(room);
		}

		if (description == null) {
			watsonAddressAuditImpl.setDescription("");
		}
		else {
			watsonAddressAuditImpl.setDescription(description);
		}

		if (imagePayload == null) {
			watsonAddressAuditImpl.setImagePayload("");
		}
		else {
			watsonAddressAuditImpl.setImagePayload(imagePayload);
		}

		if (otherType == null) {
			watsonAddressAuditImpl.setOtherType("");
		}
		else {
			watsonAddressAuditImpl.setOtherType(otherType);
		}

		if (lastSeenDate == Long.MIN_VALUE) {
			watsonAddressAuditImpl.setLastSeenDate(null);
		}
		else {
			watsonAddressAuditImpl.setLastSeenDate(new Date(lastSeenDate));
		}

		watsonAddressAuditImpl.setLatitude(latitude);
		watsonAddressAuditImpl.setLongitude(longitude);
		watsonAddressAuditImpl.setStatus(status);

		watsonAddressAuditImpl.resetOriginalValues();

		return watsonAddressAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonAddressAuditId = objectInput.readLong();

		groupId = objectInput.readLong();

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

		watsonAddressId = objectInput.readLong();

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
		otherType = objectInput.readUTF();
		lastSeenDate = objectInput.readLong();

		latitude = objectInput.readDouble();

		longitude = objectInput.readDouble();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(watsonAddressAuditId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
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

		objectOutput.writeLong(watsonAddressId);

		objectOutput.writeLong(watsonIncidentId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (postalCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalCode);
		}

		if (region == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(region);
		}

		if (street == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(street);
		}

		if (number == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(number);
		}

		if (building == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(building);
		}

		if (floor == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(floor);
		}

		if (room == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(room);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (imagePayload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imagePayload);
		}

		if (otherType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(otherType);
		}

		objectOutput.writeLong(lastSeenDate);

		objectOutput.writeDouble(latitude);

		objectOutput.writeDouble(longitude);

		objectOutput.writeInt(status);
	}

	public long watsonAddressAuditId;
	public long groupId;
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
	public long watsonAddressId;
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
	public String otherType;
	public long lastSeenDate;
	public double latitude;
	public double longitude;
	public int status;

}
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

import com.liferay.watson.model.WatsonVehicleAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonVehicleAudit in entity cache.
 *
 * @author Steven Smith
 * @see WatsonVehicleAudit
 * @generated
 */
@ProviderType
public class WatsonVehicleAuditCacheModel implements CacheModel<WatsonVehicleAudit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonVehicleAuditCacheModel)) {
			return false;
		}

		WatsonVehicleAuditCacheModel watsonVehicleAuditCacheModel = (WatsonVehicleAuditCacheModel)obj;

		if (watsonVehicleAuditId == watsonVehicleAuditCacheModel.watsonVehicleAuditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonVehicleAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{watsonVehicleAuditId=");
		sb.append(watsonVehicleAuditId);
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
		sb.append(", watsonVehicleId=");
		sb.append(watsonVehicleId);
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
	public WatsonVehicleAudit toEntityModel() {
		WatsonVehicleAuditImpl watsonVehicleAuditImpl = new WatsonVehicleAuditImpl();

		watsonVehicleAuditImpl.setWatsonVehicleAuditId(watsonVehicleAuditId);
		watsonVehicleAuditImpl.setGroupId(groupId);
		watsonVehicleAuditImpl.setCompanyId(companyId);
		watsonVehicleAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonVehicleAuditImpl.setUserName("");
		}
		else {
			watsonVehicleAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonVehicleAuditImpl.setCreateDate(null);
		}
		else {
			watsonVehicleAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonVehicleAuditImpl.setModifiedDate(null);
		}
		else {
			watsonVehicleAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonVehicleAuditImpl.setColorWatsonListTypeId(colorWatsonListTypeId);
		watsonVehicleAuditImpl.setMakeWatsonListTypeId(makeWatsonListTypeId);
		watsonVehicleAuditImpl.setModelWatsonListTypeId(modelWatsonListTypeId);
		watsonVehicleAuditImpl.setOriginalWatsonVehicleId(originalWatsonVehicleId);
		watsonVehicleAuditImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonVehicleAuditImpl.setYearWatsonListTypeId(yearWatsonListTypeId);
		watsonVehicleAuditImpl.setWatsonIncidentId(watsonIncidentId);
		watsonVehicleAuditImpl.setWatsonVehicleId(watsonVehicleId);
		watsonVehicleAuditImpl.setYear(year);

		if (description == null) {
			watsonVehicleAuditImpl.setDescription("");
		}
		else {
			watsonVehicleAuditImpl.setDescription(description);
		}

		if (imagePayload == null) {
			watsonVehicleAuditImpl.setImagePayload("");
		}
		else {
			watsonVehicleAuditImpl.setImagePayload(imagePayload);
		}

		if (licensePlate == null) {
			watsonVehicleAuditImpl.setLicensePlate("");
		}
		else {
			watsonVehicleAuditImpl.setLicensePlate(licensePlate);
		}

		watsonVehicleAuditImpl.setStatus(status);

		watsonVehicleAuditImpl.resetOriginalValues();

		return watsonVehicleAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonVehicleAuditId = objectInput.readLong();

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

		watsonVehicleId = objectInput.readLong();

		year = objectInput.readInt();
		description = objectInput.readUTF();
		imagePayload = objectInput.readUTF();
		licensePlate = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonVehicleAuditId);

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

		objectOutput.writeLong(colorWatsonListTypeId);

		objectOutput.writeLong(makeWatsonListTypeId);

		objectOutput.writeLong(modelWatsonListTypeId);

		objectOutput.writeLong(originalWatsonVehicleId);

		objectOutput.writeLong(typeWatsonListTypeId);

		objectOutput.writeLong(yearWatsonListTypeId);

		objectOutput.writeLong(watsonIncidentId);

		objectOutput.writeLong(watsonVehicleId);

		objectOutput.writeInt(year);

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

		if (licensePlate == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(licensePlate);
		}

		objectOutput.writeInt(status);
	}

	public long watsonVehicleAuditId;
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
	public long watsonVehicleId;
	public int year;
	public String description;
	public String imagePayload;
	public String licensePlate;
	public int status;
}
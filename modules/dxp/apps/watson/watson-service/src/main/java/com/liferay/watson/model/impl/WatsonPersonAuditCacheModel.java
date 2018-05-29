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

import com.liferay.watson.model.WatsonPersonAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonPersonAudit in entity cache.
 *
 * @author Steven Smith
 * @see WatsonPersonAudit
 * @generated
 */
@ProviderType
public class WatsonPersonAuditCacheModel implements CacheModel<WatsonPersonAudit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonPersonAuditCacheModel)) {
			return false;
		}

		WatsonPersonAuditCacheModel watsonPersonAuditCacheModel = (WatsonPersonAuditCacheModel)obj;

		if (watsonPersonAuditId == watsonPersonAuditCacheModel.watsonPersonAuditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonPersonAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(63);

		sb.append("{watsonPersonAuditId=");
		sb.append(watsonPersonAuditId);
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
		sb.append(", birthCountryId=");
		sb.append(birthCountryId);
		sb.append(", citizenshipWatsonListTypeId=");
		sb.append(citizenshipWatsonListTypeId);
		sb.append(", countryWatsonListTypeId=");
		sb.append(countryWatsonListTypeId);
		sb.append(", ethnicityWatsonListTypeId=");
		sb.append(ethnicityWatsonListTypeId);
		sb.append(", eyesWatsonListTypeId=");
		sb.append(eyesWatsonListTypeId);
		sb.append(", hairWatsonListTypeId=");
		sb.append(hairWatsonListTypeId);
		sb.append(", originalWatsonPersonId=");
		sb.append(originalWatsonPersonId);
		sb.append(", sexWatsonListTypeId=");
		sb.append(sexWatsonListTypeId);
		sb.append(", typeWatsonListTypeId=");
		sb.append(typeWatsonListTypeId);
		sb.append(", watsonIncidentId=");
		sb.append(watsonIncidentId);
		sb.append(", watsonPersonId=");
		sb.append(watsonPersonId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", imagePayload=");
		sb.append(imagePayload);
		sb.append(", birthDate=");
		sb.append(birthDate);
		sb.append(", dateAccepted=");
		sb.append(dateAccepted);
		sb.append(", dateRescued=");
		sb.append(dateRescued);
		sb.append(", startAge=");
		sb.append(startAge);
		sb.append(", endAge=");
		sb.append(endAge);
		sb.append(", occupation=");
		sb.append(occupation);
		sb.append(", height=");
		sb.append(height);
		sb.append(", weight=");
		sb.append(weight);
		sb.append(", accepted=");
		sb.append(accepted);
		sb.append(", rescued=");
		sb.append(rescued);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonPersonAudit toEntityModel() {
		WatsonPersonAuditImpl watsonPersonAuditImpl = new WatsonPersonAuditImpl();

		watsonPersonAuditImpl.setWatsonPersonAuditId(watsonPersonAuditId);
		watsonPersonAuditImpl.setGroupId(groupId);
		watsonPersonAuditImpl.setCompanyId(companyId);
		watsonPersonAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonPersonAuditImpl.setUserName("");
		}
		else {
			watsonPersonAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonPersonAuditImpl.setCreateDate(null);
		}
		else {
			watsonPersonAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonPersonAuditImpl.setModifiedDate(null);
		}
		else {
			watsonPersonAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonPersonAuditImpl.setBirthCountryId(birthCountryId);
		watsonPersonAuditImpl.setCitizenshipWatsonListTypeId(citizenshipWatsonListTypeId);
		watsonPersonAuditImpl.setCountryWatsonListTypeId(countryWatsonListTypeId);
		watsonPersonAuditImpl.setEthnicityWatsonListTypeId(ethnicityWatsonListTypeId);
		watsonPersonAuditImpl.setEyesWatsonListTypeId(eyesWatsonListTypeId);
		watsonPersonAuditImpl.setHairWatsonListTypeId(hairWatsonListTypeId);
		watsonPersonAuditImpl.setOriginalWatsonPersonId(originalWatsonPersonId);
		watsonPersonAuditImpl.setSexWatsonListTypeId(sexWatsonListTypeId);
		watsonPersonAuditImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonPersonAuditImpl.setWatsonIncidentId(watsonIncidentId);
		watsonPersonAuditImpl.setWatsonPersonId(watsonPersonId);

		if (description == null) {
			watsonPersonAuditImpl.setDescription("");
		}
		else {
			watsonPersonAuditImpl.setDescription(description);
		}

		if (imagePayload == null) {
			watsonPersonAuditImpl.setImagePayload("");
		}
		else {
			watsonPersonAuditImpl.setImagePayload(imagePayload);
		}

		if (birthDate == Long.MIN_VALUE) {
			watsonPersonAuditImpl.setBirthDate(null);
		}
		else {
			watsonPersonAuditImpl.setBirthDate(new Date(birthDate));
		}

		if (dateAccepted == Long.MIN_VALUE) {
			watsonPersonAuditImpl.setDateAccepted(null);
		}
		else {
			watsonPersonAuditImpl.setDateAccepted(new Date(dateAccepted));
		}

		if (dateRescued == Long.MIN_VALUE) {
			watsonPersonAuditImpl.setDateRescued(null);
		}
		else {
			watsonPersonAuditImpl.setDateRescued(new Date(dateRescued));
		}

		if (startAge == null) {
			watsonPersonAuditImpl.setStartAge("");
		}
		else {
			watsonPersonAuditImpl.setStartAge(startAge);
		}

		if (endAge == null) {
			watsonPersonAuditImpl.setEndAge("");
		}
		else {
			watsonPersonAuditImpl.setEndAge(endAge);
		}

		if (occupation == null) {
			watsonPersonAuditImpl.setOccupation("");
		}
		else {
			watsonPersonAuditImpl.setOccupation(occupation);
		}

		if (height == null) {
			watsonPersonAuditImpl.setHeight("");
		}
		else {
			watsonPersonAuditImpl.setHeight(height);
		}

		if (weight == null) {
			watsonPersonAuditImpl.setWeight("");
		}
		else {
			watsonPersonAuditImpl.setWeight(weight);
		}

		watsonPersonAuditImpl.setAccepted(accepted);
		watsonPersonAuditImpl.setRescued(rescued);
		watsonPersonAuditImpl.setStatus(status);

		watsonPersonAuditImpl.resetOriginalValues();

		return watsonPersonAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonPersonAuditId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		birthCountryId = objectInput.readLong();

		citizenshipWatsonListTypeId = objectInput.readLong();

		countryWatsonListTypeId = objectInput.readLong();

		ethnicityWatsonListTypeId = objectInput.readLong();

		eyesWatsonListTypeId = objectInput.readLong();

		hairWatsonListTypeId = objectInput.readLong();

		originalWatsonPersonId = objectInput.readLong();

		sexWatsonListTypeId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		watsonIncidentId = objectInput.readLong();

		watsonPersonId = objectInput.readLong();
		description = objectInput.readUTF();
		imagePayload = objectInput.readUTF();
		birthDate = objectInput.readLong();
		dateAccepted = objectInput.readLong();
		dateRescued = objectInput.readLong();
		startAge = objectInput.readUTF();
		endAge = objectInput.readUTF();
		occupation = objectInput.readUTF();
		height = objectInput.readUTF();
		weight = objectInput.readUTF();

		accepted = objectInput.readBoolean();

		rescued = objectInput.readBoolean();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonPersonAuditId);

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

		objectOutput.writeLong(birthCountryId);

		objectOutput.writeLong(citizenshipWatsonListTypeId);

		objectOutput.writeLong(countryWatsonListTypeId);

		objectOutput.writeLong(ethnicityWatsonListTypeId);

		objectOutput.writeLong(eyesWatsonListTypeId);

		objectOutput.writeLong(hairWatsonListTypeId);

		objectOutput.writeLong(originalWatsonPersonId);

		objectOutput.writeLong(sexWatsonListTypeId);

		objectOutput.writeLong(typeWatsonListTypeId);

		objectOutput.writeLong(watsonIncidentId);

		objectOutput.writeLong(watsonPersonId);

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

		objectOutput.writeLong(birthDate);
		objectOutput.writeLong(dateAccepted);
		objectOutput.writeLong(dateRescued);

		if (startAge == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(startAge);
		}

		if (endAge == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(endAge);
		}

		if (occupation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(occupation);
		}

		if (height == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(height);
		}

		if (weight == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(weight);
		}

		objectOutput.writeBoolean(accepted);

		objectOutput.writeBoolean(rescued);

		objectOutput.writeInt(status);
	}

	public long watsonPersonAuditId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long birthCountryId;
	public long citizenshipWatsonListTypeId;
	public long countryWatsonListTypeId;
	public long ethnicityWatsonListTypeId;
	public long eyesWatsonListTypeId;
	public long hairWatsonListTypeId;
	public long originalWatsonPersonId;
	public long sexWatsonListTypeId;
	public long typeWatsonListTypeId;
	public long watsonIncidentId;
	public long watsonPersonId;
	public String description;
	public String imagePayload;
	public long birthDate;
	public long dateAccepted;
	public long dateRescued;
	public String startAge;
	public String endAge;
	public String occupation;
	public String height;
	public String weight;
	public boolean accepted;
	public boolean rescued;
	public int status;
}
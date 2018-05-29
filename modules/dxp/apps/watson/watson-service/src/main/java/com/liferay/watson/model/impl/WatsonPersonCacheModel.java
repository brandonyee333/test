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

import com.liferay.watson.model.WatsonPerson;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonPerson in entity cache.
 *
 * @author Steven Smith
 * @see WatsonPerson
 * @generated
 */
@ProviderType
public class WatsonPersonCacheModel implements CacheModel<WatsonPerson>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonPersonCacheModel)) {
			return false;
		}

		WatsonPersonCacheModel watsonPersonCacheModel = (WatsonPersonCacheModel)obj;

		if (watsonPersonId == watsonPersonCacheModel.watsonPersonId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonPersonId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(61);

		sb.append("{watsonPersonId=");
		sb.append(watsonPersonId);
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
	public WatsonPerson toEntityModel() {
		WatsonPersonImpl watsonPersonImpl = new WatsonPersonImpl();

		watsonPersonImpl.setWatsonPersonId(watsonPersonId);
		watsonPersonImpl.setGroupId(groupId);
		watsonPersonImpl.setCompanyId(companyId);
		watsonPersonImpl.setUserId(userId);

		if (userName == null) {
			watsonPersonImpl.setUserName("");
		}
		else {
			watsonPersonImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonPersonImpl.setCreateDate(null);
		}
		else {
			watsonPersonImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonPersonImpl.setModifiedDate(null);
		}
		else {
			watsonPersonImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonPersonImpl.setBirthCountryId(birthCountryId);
		watsonPersonImpl.setCitizenshipWatsonListTypeId(citizenshipWatsonListTypeId);
		watsonPersonImpl.setCountryWatsonListTypeId(countryWatsonListTypeId);
		watsonPersonImpl.setEthnicityWatsonListTypeId(ethnicityWatsonListTypeId);
		watsonPersonImpl.setEyesWatsonListTypeId(eyesWatsonListTypeId);
		watsonPersonImpl.setHairWatsonListTypeId(hairWatsonListTypeId);
		watsonPersonImpl.setOriginalWatsonPersonId(originalWatsonPersonId);
		watsonPersonImpl.setSexWatsonListTypeId(sexWatsonListTypeId);
		watsonPersonImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonPersonImpl.setWatsonIncidentId(watsonIncidentId);

		if (description == null) {
			watsonPersonImpl.setDescription("");
		}
		else {
			watsonPersonImpl.setDescription(description);
		}

		if (imagePayload == null) {
			watsonPersonImpl.setImagePayload("");
		}
		else {
			watsonPersonImpl.setImagePayload(imagePayload);
		}

		if (birthDate == Long.MIN_VALUE) {
			watsonPersonImpl.setBirthDate(null);
		}
		else {
			watsonPersonImpl.setBirthDate(new Date(birthDate));
		}

		if (dateAccepted == Long.MIN_VALUE) {
			watsonPersonImpl.setDateAccepted(null);
		}
		else {
			watsonPersonImpl.setDateAccepted(new Date(dateAccepted));
		}

		if (dateRescued == Long.MIN_VALUE) {
			watsonPersonImpl.setDateRescued(null);
		}
		else {
			watsonPersonImpl.setDateRescued(new Date(dateRescued));
		}

		if (startAge == null) {
			watsonPersonImpl.setStartAge("");
		}
		else {
			watsonPersonImpl.setStartAge(startAge);
		}

		if (endAge == null) {
			watsonPersonImpl.setEndAge("");
		}
		else {
			watsonPersonImpl.setEndAge(endAge);
		}

		if (occupation == null) {
			watsonPersonImpl.setOccupation("");
		}
		else {
			watsonPersonImpl.setOccupation(occupation);
		}

		if (height == null) {
			watsonPersonImpl.setHeight("");
		}
		else {
			watsonPersonImpl.setHeight(height);
		}

		if (weight == null) {
			watsonPersonImpl.setWeight("");
		}
		else {
			watsonPersonImpl.setWeight(weight);
		}

		watsonPersonImpl.setAccepted(accepted);
		watsonPersonImpl.setRescued(rescued);
		watsonPersonImpl.setStatus(status);

		watsonPersonImpl.resetOriginalValues();

		return watsonPersonImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonPersonId = objectInput.readLong();

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
		objectOutput.writeLong(watsonPersonId);

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

	public long watsonPersonId;
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
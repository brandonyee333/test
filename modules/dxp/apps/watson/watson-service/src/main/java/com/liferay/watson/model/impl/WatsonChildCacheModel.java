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

import com.liferay.watson.model.WatsonChild;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonChild in entity cache.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonChildCacheModel implements CacheModel<WatsonChild>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonChildCacheModel)) {
			return false;
		}

		WatsonChildCacheModel watsonChildCacheModel = (WatsonChildCacheModel)obj;

		if (watsonChildId == watsonChildCacheModel.watsonChildId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonChildId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{watsonChildId=");
		sb.append(watsonChildId);
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
		sb.append(", dischargeWatsonListTypeId=");
		sb.append(dischargeWatsonListTypeId);
		sb.append(", ethnicityWatsonListTypeId=");
		sb.append(ethnicityWatsonListTypeId);
		sb.append(", originalWatsonPersonId=");
		sb.append(originalWatsonPersonId);
		sb.append(", sexWatsonListTypeId=");
		sb.append(sexWatsonListTypeId);
		sb.append(", sourceSubtypeWatsonListTypeId=");
		sb.append(sourceSubtypeWatsonListTypeId);
		sb.append(", sourceWatsonListTypeId=");
		sb.append(sourceWatsonListTypeId);
		sb.append(", typeWatsonListTypeId=");
		sb.append(typeWatsonListTypeId);
		sb.append(", dateAccepted=");
		sb.append(dateAccepted);
		sb.append(", dateDischarged=");
		sb.append(dateDischarged);
		sb.append(", dateFollowUp=");
		sb.append(dateFollowUp);
		sb.append(", source=");
		sb.append(source);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonChild toEntityModel() {
		WatsonChildImpl watsonChildImpl = new WatsonChildImpl();

		watsonChildImpl.setWatsonChildId(watsonChildId);
		watsonChildImpl.setGroupId(groupId);
		watsonChildImpl.setCompanyId(companyId);
		watsonChildImpl.setUserId(userId);

		if (userName == null) {
			watsonChildImpl.setUserName("");
		}
		else {
			watsonChildImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonChildImpl.setCreateDate(null);
		}
		else {
			watsonChildImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonChildImpl.setModifiedDate(null);
		}
		else {
			watsonChildImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonChildImpl.setBirthCountryId(birthCountryId);
		watsonChildImpl.setCitizenshipWatsonListTypeId(citizenshipWatsonListTypeId);
		watsonChildImpl.setCountryWatsonListTypeId(countryWatsonListTypeId);
		watsonChildImpl.setDischargeWatsonListTypeId(dischargeWatsonListTypeId);
		watsonChildImpl.setEthnicityWatsonListTypeId(ethnicityWatsonListTypeId);
		watsonChildImpl.setOriginalWatsonPersonId(originalWatsonPersonId);
		watsonChildImpl.setSexWatsonListTypeId(sexWatsonListTypeId);
		watsonChildImpl.setSourceSubtypeWatsonListTypeId(sourceSubtypeWatsonListTypeId);
		watsonChildImpl.setSourceWatsonListTypeId(sourceWatsonListTypeId);
		watsonChildImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);

		if (dateAccepted == Long.MIN_VALUE) {
			watsonChildImpl.setDateAccepted(null);
		}
		else {
			watsonChildImpl.setDateAccepted(new Date(dateAccepted));
		}

		if (dateDischarged == Long.MIN_VALUE) {
			watsonChildImpl.setDateDischarged(null);
		}
		else {
			watsonChildImpl.setDateDischarged(new Date(dateDischarged));
		}

		if (dateFollowUp == Long.MIN_VALUE) {
			watsonChildImpl.setDateFollowUp(null);
		}
		else {
			watsonChildImpl.setDateFollowUp(new Date(dateFollowUp));
		}

		if (source == null) {
			watsonChildImpl.setSource("");
		}
		else {
			watsonChildImpl.setSource(source);
		}

		watsonChildImpl.setStatus(status);

		watsonChildImpl.resetOriginalValues();

		return watsonChildImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonChildId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		birthCountryId = objectInput.readLong();

		citizenshipWatsonListTypeId = objectInput.readLong();

		countryWatsonListTypeId = objectInput.readLong();

		dischargeWatsonListTypeId = objectInput.readLong();

		ethnicityWatsonListTypeId = objectInput.readLong();

		originalWatsonPersonId = objectInput.readLong();

		sexWatsonListTypeId = objectInput.readLong();

		sourceSubtypeWatsonListTypeId = objectInput.readLong();

		sourceWatsonListTypeId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();
		dateAccepted = objectInput.readLong();
		dateDischarged = objectInput.readLong();
		dateFollowUp = objectInput.readLong();
		source = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonChildId);

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

		objectOutput.writeLong(dischargeWatsonListTypeId);

		objectOutput.writeLong(ethnicityWatsonListTypeId);

		objectOutput.writeLong(originalWatsonPersonId);

		objectOutput.writeLong(sexWatsonListTypeId);

		objectOutput.writeLong(sourceSubtypeWatsonListTypeId);

		objectOutput.writeLong(sourceWatsonListTypeId);

		objectOutput.writeLong(typeWatsonListTypeId);
		objectOutput.writeLong(dateAccepted);
		objectOutput.writeLong(dateDischarged);
		objectOutput.writeLong(dateFollowUp);

		if (source == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeInt(status);
	}

	public long watsonChildId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long birthCountryId;
	public long citizenshipWatsonListTypeId;
	public long countryWatsonListTypeId;
	public long dischargeWatsonListTypeId;
	public long ethnicityWatsonListTypeId;
	public long originalWatsonPersonId;
	public long sexWatsonListTypeId;
	public long sourceSubtypeWatsonListTypeId;
	public long sourceWatsonListTypeId;
	public long typeWatsonListTypeId;
	public long dateAccepted;
	public long dateDischarged;
	public long dateFollowUp;
	public String source;
	public int status;
}
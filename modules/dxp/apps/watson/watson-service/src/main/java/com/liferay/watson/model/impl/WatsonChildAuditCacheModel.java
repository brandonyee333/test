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

import com.liferay.watson.model.WatsonChildAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonChildAudit in entity cache.
 *
 * @author Steven Smith
 * @see WatsonChildAudit
 * @generated
 */
@ProviderType
public class WatsonChildAuditCacheModel implements CacheModel<WatsonChildAudit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonChildAuditCacheModel)) {
			return false;
		}

		WatsonChildAuditCacheModel watsonChildAuditCacheModel = (WatsonChildAuditCacheModel)obj;

		if (watsonChildAuditId == watsonChildAuditCacheModel.watsonChildAuditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonChildAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(47);

		sb.append("{watsonChildAuditId=");
		sb.append(watsonChildAuditId);
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
		sb.append(", watsonChildId=");
		sb.append(watsonChildId);
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
	public WatsonChildAudit toEntityModel() {
		WatsonChildAuditImpl watsonChildAuditImpl = new WatsonChildAuditImpl();

		watsonChildAuditImpl.setWatsonChildAuditId(watsonChildAuditId);
		watsonChildAuditImpl.setGroupId(groupId);
		watsonChildAuditImpl.setCompanyId(companyId);
		watsonChildAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonChildAuditImpl.setUserName("");
		}
		else {
			watsonChildAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonChildAuditImpl.setCreateDate(null);
		}
		else {
			watsonChildAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonChildAuditImpl.setModifiedDate(null);
		}
		else {
			watsonChildAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonChildAuditImpl.setBirthCountryId(birthCountryId);
		watsonChildAuditImpl.setCitizenshipWatsonListTypeId(citizenshipWatsonListTypeId);
		watsonChildAuditImpl.setCountryWatsonListTypeId(countryWatsonListTypeId);
		watsonChildAuditImpl.setDischargeWatsonListTypeId(dischargeWatsonListTypeId);
		watsonChildAuditImpl.setEthnicityWatsonListTypeId(ethnicityWatsonListTypeId);
		watsonChildAuditImpl.setOriginalWatsonPersonId(originalWatsonPersonId);
		watsonChildAuditImpl.setSexWatsonListTypeId(sexWatsonListTypeId);
		watsonChildAuditImpl.setSourceSubtypeWatsonListTypeId(sourceSubtypeWatsonListTypeId);
		watsonChildAuditImpl.setSourceWatsonListTypeId(sourceWatsonListTypeId);
		watsonChildAuditImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonChildAuditImpl.setWatsonChildId(watsonChildId);

		if (dateAccepted == Long.MIN_VALUE) {
			watsonChildAuditImpl.setDateAccepted(null);
		}
		else {
			watsonChildAuditImpl.setDateAccepted(new Date(dateAccepted));
		}

		if (dateDischarged == Long.MIN_VALUE) {
			watsonChildAuditImpl.setDateDischarged(null);
		}
		else {
			watsonChildAuditImpl.setDateDischarged(new Date(dateDischarged));
		}

		if (dateFollowUp == Long.MIN_VALUE) {
			watsonChildAuditImpl.setDateFollowUp(null);
		}
		else {
			watsonChildAuditImpl.setDateFollowUp(new Date(dateFollowUp));
		}

		if (source == null) {
			watsonChildAuditImpl.setSource("");
		}
		else {
			watsonChildAuditImpl.setSource(source);
		}

		watsonChildAuditImpl.setStatus(status);

		watsonChildAuditImpl.resetOriginalValues();

		return watsonChildAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonChildAuditId = objectInput.readLong();

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

		watsonChildId = objectInput.readLong();
		dateAccepted = objectInput.readLong();
		dateDischarged = objectInput.readLong();
		dateFollowUp = objectInput.readLong();
		source = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonChildAuditId);

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

		objectOutput.writeLong(watsonChildId);
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

	public long watsonChildAuditId;
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
	public long watsonChildId;
	public long dateAccepted;
	public long dateDischarged;
	public long dateFollowUp;
	public String source;
	public int status;
}
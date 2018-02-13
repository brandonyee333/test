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

import com.liferay.watson.model.WatsonIncident;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonIncident in entity cache.
 *
 * @author Steven Smith
 * @see WatsonIncident
 * @generated
 */
@ProviderType
public class WatsonIncidentCacheModel implements CacheModel<WatsonIncident>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonIncidentCacheModel)) {
			return false;
		}

		WatsonIncidentCacheModel watsonIncidentCacheModel = (WatsonIncidentCacheModel)obj;

		if (watsonIncidentId == watsonIncidentCacheModel.watsonIncidentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonIncidentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{watsonIncidentId=");
		sb.append(watsonIncidentId);
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
		sb.append(", externalCaseWatsonListTypeId=");
		sb.append(externalCaseWatsonListTypeId);
		sb.append(", sourceWatsonListTypeId=");
		sb.append(sourceWatsonListTypeId);
		sb.append(", typeWatsonListTypeId=");
		sb.append(typeWatsonListTypeId);
		sb.append(", subtypeWatsonListTypeId=");
		sb.append(subtypeWatsonListTypeId);
		sb.append(", audienceAdultCount=");
		sb.append(audienceAdultCount);
		sb.append(", audienceChildCount=");
		sb.append(audienceChildCount);
		sb.append(", victimAdultCount=");
		sb.append(victimAdultCount);
		sb.append(", victimChildCount=");
		sb.append(victimChildCount);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", externalCaseId=");
		sb.append(externalCaseId);
		sb.append(", reportDate=");
		sb.append(reportDate);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", expenses=");
		sb.append(expenses);
		sb.append(", incidentStatus=");
		sb.append(incidentStatus);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonIncident toEntityModel() {
		WatsonIncidentImpl watsonIncidentImpl = new WatsonIncidentImpl();

		watsonIncidentImpl.setWatsonIncidentId(watsonIncidentId);
		watsonIncidentImpl.setGroupId(groupId);
		watsonIncidentImpl.setCompanyId(companyId);
		watsonIncidentImpl.setUserId(userId);

		if (userName == null) {
			watsonIncidentImpl.setUserName("");
		}
		else {
			watsonIncidentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonIncidentImpl.setCreateDate(null);
		}
		else {
			watsonIncidentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonIncidentImpl.setModifiedDate(null);
		}
		else {
			watsonIncidentImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonIncidentImpl.setExternalCaseWatsonListTypeId(externalCaseWatsonListTypeId);
		watsonIncidentImpl.setSourceWatsonListTypeId(sourceWatsonListTypeId);
		watsonIncidentImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonIncidentImpl.setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
		watsonIncidentImpl.setAudienceAdultCount(audienceAdultCount);
		watsonIncidentImpl.setAudienceChildCount(audienceChildCount);
		watsonIncidentImpl.setVictimAdultCount(victimAdultCount);
		watsonIncidentImpl.setVictimChildCount(victimChildCount);

		if (name == null) {
			watsonIncidentImpl.setName("");
		}
		else {
			watsonIncidentImpl.setName(name);
		}

		if (description == null) {
			watsonIncidentImpl.setDescription("");
		}
		else {
			watsonIncidentImpl.setDescription(description);
		}

		if (externalCaseId == null) {
			watsonIncidentImpl.setExternalCaseId("");
		}
		else {
			watsonIncidentImpl.setExternalCaseId(externalCaseId);
		}

		if (reportDate == Long.MIN_VALUE) {
			watsonIncidentImpl.setReportDate(null);
		}
		else {
			watsonIncidentImpl.setReportDate(new Date(reportDate));
		}

		if (startDate == Long.MIN_VALUE) {
			watsonIncidentImpl.setStartDate(null);
		}
		else {
			watsonIncidentImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			watsonIncidentImpl.setEndDate(null);
		}
		else {
			watsonIncidentImpl.setEndDate(new Date(endDate));
		}

		watsonIncidentImpl.setExpenses(expenses);
		watsonIncidentImpl.setIncidentStatus(incidentStatus);
		watsonIncidentImpl.setStatus(status);

		watsonIncidentImpl.resetOriginalValues();

		return watsonIncidentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonIncidentId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		externalCaseWatsonListTypeId = objectInput.readLong();

		sourceWatsonListTypeId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		subtypeWatsonListTypeId = objectInput.readLong();

		audienceAdultCount = objectInput.readLong();

		audienceChildCount = objectInput.readLong();

		victimAdultCount = objectInput.readLong();

		victimChildCount = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		externalCaseId = objectInput.readUTF();
		reportDate = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();

		expenses = objectInput.readDouble();

		incidentStatus = objectInput.readInt();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonIncidentId);

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

		objectOutput.writeLong(externalCaseWatsonListTypeId);

		objectOutput.writeLong(sourceWatsonListTypeId);

		objectOutput.writeLong(typeWatsonListTypeId);

		objectOutput.writeLong(subtypeWatsonListTypeId);

		objectOutput.writeLong(audienceAdultCount);

		objectOutput.writeLong(audienceChildCount);

		objectOutput.writeLong(victimAdultCount);

		objectOutput.writeLong(victimChildCount);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (externalCaseId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalCaseId);
		}

		objectOutput.writeLong(reportDate);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		objectOutput.writeDouble(expenses);

		objectOutput.writeInt(incidentStatus);

		objectOutput.writeInt(status);
	}

	public long watsonIncidentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long externalCaseWatsonListTypeId;
	public long sourceWatsonListTypeId;
	public long typeWatsonListTypeId;
	public long subtypeWatsonListTypeId;
	public long audienceAdultCount;
	public long audienceChildCount;
	public long victimAdultCount;
	public long victimChildCount;
	public String name;
	public String description;
	public String externalCaseId;
	public long reportDate;
	public long startDate;
	public long endDate;
	public double expenses;
	public int incidentStatus;
	public int status;
}
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

import com.liferay.watson.model.WatsonIncidentAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonIncidentAudit in entity cache.
 *
 * @author Steven Smith
 * @see WatsonIncidentAudit
 * @generated
 */
@ProviderType
public class WatsonIncidentAuditCacheModel implements CacheModel<WatsonIncidentAudit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonIncidentAuditCacheModel)) {
			return false;
		}

		WatsonIncidentAuditCacheModel watsonIncidentAuditCacheModel = (WatsonIncidentAuditCacheModel)obj;

		if (watsonIncidentAuditId == watsonIncidentAuditCacheModel.watsonIncidentAuditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonIncidentAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{watsonIncidentAuditId=");
		sb.append(watsonIncidentAuditId);
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
		sb.append(", watsonIncidentId=");
		sb.append(watsonIncidentId);
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
	public WatsonIncidentAudit toEntityModel() {
		WatsonIncidentAuditImpl watsonIncidentAuditImpl = new WatsonIncidentAuditImpl();

		watsonIncidentAuditImpl.setWatsonIncidentAuditId(watsonIncidentAuditId);
		watsonIncidentAuditImpl.setGroupId(groupId);
		watsonIncidentAuditImpl.setCompanyId(companyId);
		watsonIncidentAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonIncidentAuditImpl.setUserName("");
		}
		else {
			watsonIncidentAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonIncidentAuditImpl.setCreateDate(null);
		}
		else {
			watsonIncidentAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonIncidentAuditImpl.setModifiedDate(null);
		}
		else {
			watsonIncidentAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonIncidentAuditImpl.setExternalCaseWatsonListTypeId(externalCaseWatsonListTypeId);
		watsonIncidentAuditImpl.setSourceWatsonListTypeId(sourceWatsonListTypeId);
		watsonIncidentAuditImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonIncidentAuditImpl.setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
		watsonIncidentAuditImpl.setAudienceAdultCount(audienceAdultCount);
		watsonIncidentAuditImpl.setAudienceChildCount(audienceChildCount);
		watsonIncidentAuditImpl.setVictimAdultCount(victimAdultCount);
		watsonIncidentAuditImpl.setVictimChildCount(victimChildCount);
		watsonIncidentAuditImpl.setWatsonIncidentId(watsonIncidentId);

		if (name == null) {
			watsonIncidentAuditImpl.setName("");
		}
		else {
			watsonIncidentAuditImpl.setName(name);
		}

		if (description == null) {
			watsonIncidentAuditImpl.setDescription("");
		}
		else {
			watsonIncidentAuditImpl.setDescription(description);
		}

		if (externalCaseId == null) {
			watsonIncidentAuditImpl.setExternalCaseId("");
		}
		else {
			watsonIncidentAuditImpl.setExternalCaseId(externalCaseId);
		}

		if (reportDate == Long.MIN_VALUE) {
			watsonIncidentAuditImpl.setReportDate(null);
		}
		else {
			watsonIncidentAuditImpl.setReportDate(new Date(reportDate));
		}

		if (startDate == Long.MIN_VALUE) {
			watsonIncidentAuditImpl.setStartDate(null);
		}
		else {
			watsonIncidentAuditImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			watsonIncidentAuditImpl.setEndDate(null);
		}
		else {
			watsonIncidentAuditImpl.setEndDate(new Date(endDate));
		}

		watsonIncidentAuditImpl.setExpenses(expenses);
		watsonIncidentAuditImpl.setIncidentStatus(incidentStatus);
		watsonIncidentAuditImpl.setStatus(status);

		watsonIncidentAuditImpl.resetOriginalValues();

		return watsonIncidentAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonIncidentAuditId = objectInput.readLong();

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

		watsonIncidentId = objectInput.readLong();
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
		objectOutput.writeLong(watsonIncidentAuditId);

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

		objectOutput.writeLong(watsonIncidentId);

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

	public long watsonIncidentAuditId;
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
	public long watsonIncidentId;
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
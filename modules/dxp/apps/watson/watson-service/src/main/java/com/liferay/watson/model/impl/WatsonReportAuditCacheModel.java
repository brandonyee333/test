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
import com.liferay.watson.model.WatsonReportAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonReportAudit in entity cache.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonReportAuditCacheModel
	implements CacheModel<WatsonReportAudit>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonReportAuditCacheModel)) {
			return false;
		}

		WatsonReportAuditCacheModel watsonReportAuditCacheModel =
			(WatsonReportAuditCacheModel)obj;

		if (watsonReportAuditId ==
				watsonReportAuditCacheModel.watsonReportAuditId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonReportAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{watsonReportAuditId=");
		sb.append(watsonReportAuditId);
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
		sb.append(", originalWatsonReportId=");
		sb.append(originalWatsonReportId);
		sb.append(", typeWatsonListTypeId=");
		sb.append(typeWatsonListTypeId);
		sb.append(", watsonChildId=");
		sb.append(watsonChildId);
		sb.append(", watsonReportId=");
		sb.append(watsonReportId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", fullReport=");
		sb.append(fullReport);
		sb.append(", imagePayload=");
		sb.append(imagePayload);
		sb.append(", timeSpent=");
		sb.append(timeSpent);
		sb.append(", reportedUser=");
		sb.append(reportedUser);
		sb.append(", reportDate=");
		sb.append(reportDate);
		sb.append(", key=");
		sb.append(key);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonReportAudit toEntityModel() {
		WatsonReportAuditImpl watsonReportAuditImpl =
			new WatsonReportAuditImpl();

		watsonReportAuditImpl.setWatsonReportAuditId(watsonReportAuditId);
		watsonReportAuditImpl.setGroupId(groupId);
		watsonReportAuditImpl.setCompanyId(companyId);
		watsonReportAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonReportAuditImpl.setUserName("");
		}
		else {
			watsonReportAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonReportAuditImpl.setCreateDate(null);
		}
		else {
			watsonReportAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonReportAuditImpl.setModifiedDate(null);
		}
		else {
			watsonReportAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonReportAuditImpl.setOriginalWatsonReportId(originalWatsonReportId);
		watsonReportAuditImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonReportAuditImpl.setWatsonChildId(watsonChildId);
		watsonReportAuditImpl.setWatsonReportId(watsonReportId);

		if (name == null) {
			watsonReportAuditImpl.setName("");
		}
		else {
			watsonReportAuditImpl.setName(name);
		}

		if (description == null) {
			watsonReportAuditImpl.setDescription("");
		}
		else {
			watsonReportAuditImpl.setDescription(description);
		}

		if (fullReport == null) {
			watsonReportAuditImpl.setFullReport("");
		}
		else {
			watsonReportAuditImpl.setFullReport(fullReport);
		}

		if (imagePayload == null) {
			watsonReportAuditImpl.setImagePayload("");
		}
		else {
			watsonReportAuditImpl.setImagePayload(imagePayload);
		}

		if (timeSpent == null) {
			watsonReportAuditImpl.setTimeSpent("");
		}
		else {
			watsonReportAuditImpl.setTimeSpent(timeSpent);
		}

		if (reportedUser == null) {
			watsonReportAuditImpl.setReportedUser("");
		}
		else {
			watsonReportAuditImpl.setReportedUser(reportedUser);
		}

		if (reportDate == Long.MIN_VALUE) {
			watsonReportAuditImpl.setReportDate(null);
		}
		else {
			watsonReportAuditImpl.setReportDate(new Date(reportDate));
		}

		watsonReportAuditImpl.setKey(key);
		watsonReportAuditImpl.setStatus(status);

		watsonReportAuditImpl.resetOriginalValues();

		return watsonReportAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonReportAuditId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		originalWatsonReportId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		watsonChildId = objectInput.readLong();

		watsonReportId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		fullReport = objectInput.readUTF();
		imagePayload = objectInput.readUTF();
		timeSpent = objectInput.readUTF();
		reportedUser = objectInput.readUTF();
		reportDate = objectInput.readLong();

		key = objectInput.readInt();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(watsonReportAuditId);

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

		objectOutput.writeLong(originalWatsonReportId);

		objectOutput.writeLong(typeWatsonListTypeId);

		objectOutput.writeLong(watsonChildId);

		objectOutput.writeLong(watsonReportId);

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

		if (fullReport == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fullReport);
		}

		if (imagePayload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imagePayload);
		}

		if (timeSpent == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(timeSpent);
		}

		if (reportedUser == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reportedUser);
		}

		objectOutput.writeLong(reportDate);

		objectOutput.writeInt(key);

		objectOutput.writeInt(status);
	}

	public long watsonReportAuditId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long originalWatsonReportId;
	public long typeWatsonListTypeId;
	public long watsonChildId;
	public long watsonReportId;
	public String name;
	public String description;
	public String fullReport;
	public String imagePayload;
	public String timeSpent;
	public String reportedUser;
	public long reportDate;
	public int key;
	public int status;

}
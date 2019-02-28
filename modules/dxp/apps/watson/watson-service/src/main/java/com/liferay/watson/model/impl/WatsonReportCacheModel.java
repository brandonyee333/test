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
import com.liferay.watson.model.WatsonReport;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonReport in entity cache.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonReportCacheModel
	implements CacheModel<WatsonReport>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonReportCacheModel)) {
			return false;
		}

		WatsonReportCacheModel watsonReportCacheModel =
			(WatsonReportCacheModel)obj;

		if (watsonReportId == watsonReportCacheModel.watsonReportId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonReportId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{watsonReportId=");
		sb.append(watsonReportId);
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
	public WatsonReport toEntityModel() {
		WatsonReportImpl watsonReportImpl = new WatsonReportImpl();

		watsonReportImpl.setWatsonReportId(watsonReportId);
		watsonReportImpl.setGroupId(groupId);
		watsonReportImpl.setCompanyId(companyId);
		watsonReportImpl.setUserId(userId);

		if (userName == null) {
			watsonReportImpl.setUserName("");
		}
		else {
			watsonReportImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonReportImpl.setCreateDate(null);
		}
		else {
			watsonReportImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonReportImpl.setModifiedDate(null);
		}
		else {
			watsonReportImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonReportImpl.setOriginalWatsonReportId(originalWatsonReportId);
		watsonReportImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonReportImpl.setWatsonChildId(watsonChildId);

		if (name == null) {
			watsonReportImpl.setName("");
		}
		else {
			watsonReportImpl.setName(name);
		}

		if (description == null) {
			watsonReportImpl.setDescription("");
		}
		else {
			watsonReportImpl.setDescription(description);
		}

		if (fullReport == null) {
			watsonReportImpl.setFullReport("");
		}
		else {
			watsonReportImpl.setFullReport(fullReport);
		}

		if (imagePayload == null) {
			watsonReportImpl.setImagePayload("");
		}
		else {
			watsonReportImpl.setImagePayload(imagePayload);
		}

		if (timeSpent == null) {
			watsonReportImpl.setTimeSpent("");
		}
		else {
			watsonReportImpl.setTimeSpent(timeSpent);
		}

		if (reportedUser == null) {
			watsonReportImpl.setReportedUser("");
		}
		else {
			watsonReportImpl.setReportedUser(reportedUser);
		}

		if (reportDate == Long.MIN_VALUE) {
			watsonReportImpl.setReportDate(null);
		}
		else {
			watsonReportImpl.setReportDate(new Date(reportDate));
		}

		watsonReportImpl.setKey(key);
		watsonReportImpl.setStatus(status);

		watsonReportImpl.resetOriginalValues();

		return watsonReportImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonReportId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		originalWatsonReportId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		watsonChildId = objectInput.readLong();
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
		objectOutput.writeLong(watsonReportId);

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

	public long watsonReportId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long originalWatsonReportId;
	public long typeWatsonListTypeId;
	public long watsonChildId;
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
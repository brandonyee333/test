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

package com.liferay.osb.testray.model.impl;

import com.liferay.osb.testray.model.TestrayCaseResult;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayCaseResult in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestrayCaseResultCacheModel
	implements CacheModel<TestrayCaseResult>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayCaseResultCacheModel)) {
			return false;
		}

		TestrayCaseResultCacheModel testrayCaseResultCacheModel =
			(TestrayCaseResultCacheModel)obj;

		if (testrayCaseResultId ==
				testrayCaseResultCacheModel.testrayCaseResultId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayCaseResultId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{testrayCaseResultId=");
		sb.append(testrayCaseResultId);
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
		sb.append(", commentMBMessageId=");
		sb.append(commentMBMessageId);
		sb.append(", testrayBuildId=");
		sb.append(testrayBuildId);
		sb.append(", testrayCaseId=");
		sb.append(testrayCaseId);
		sb.append(", testrayComponentId=");
		sb.append(testrayComponentId);
		sb.append(", testrayRunId=");
		sb.append(testrayRunId);
		sb.append(", assignedUserId=");
		sb.append(assignedUserId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", closedDate=");
		sb.append(closedDate);
		sb.append(", attachments=");
		sb.append(attachments);
		sb.append(", errors=");
		sb.append(errors);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayCaseResult toEntityModel() {
		TestrayCaseResultImpl testrayCaseResultImpl =
			new TestrayCaseResultImpl();

		testrayCaseResultImpl.setTestrayCaseResultId(testrayCaseResultId);
		testrayCaseResultImpl.setGroupId(groupId);
		testrayCaseResultImpl.setCompanyId(companyId);
		testrayCaseResultImpl.setUserId(userId);

		if (userName == null) {
			testrayCaseResultImpl.setUserName("");
		}
		else {
			testrayCaseResultImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayCaseResultImpl.setCreateDate(null);
		}
		else {
			testrayCaseResultImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayCaseResultImpl.setModifiedDate(null);
		}
		else {
			testrayCaseResultImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayCaseResultImpl.setCommentMBMessageId(commentMBMessageId);
		testrayCaseResultImpl.setTestrayBuildId(testrayBuildId);
		testrayCaseResultImpl.setTestrayCaseId(testrayCaseId);
		testrayCaseResultImpl.setTestrayComponentId(testrayComponentId);
		testrayCaseResultImpl.setTestrayRunId(testrayRunId);
		testrayCaseResultImpl.setAssignedUserId(assignedUserId);

		if (startDate == Long.MIN_VALUE) {
			testrayCaseResultImpl.setStartDate(null);
		}
		else {
			testrayCaseResultImpl.setStartDate(new Date(startDate));
		}

		if (closedDate == Long.MIN_VALUE) {
			testrayCaseResultImpl.setClosedDate(null);
		}
		else {
			testrayCaseResultImpl.setClosedDate(new Date(closedDate));
		}

		if (attachments == null) {
			testrayCaseResultImpl.setAttachments("");
		}
		else {
			testrayCaseResultImpl.setAttachments(attachments);
		}

		if (errors == null) {
			testrayCaseResultImpl.setErrors("");
		}
		else {
			testrayCaseResultImpl.setErrors(errors);
		}

		testrayCaseResultImpl.setStatus(status);

		testrayCaseResultImpl.resetOriginalValues();

		return testrayCaseResultImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayCaseResultId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commentMBMessageId = objectInput.readLong();

		testrayBuildId = objectInput.readLong();

		testrayCaseId = objectInput.readLong();

		testrayComponentId = objectInput.readLong();

		testrayRunId = objectInput.readLong();

		assignedUserId = objectInput.readLong();
		startDate = objectInput.readLong();
		closedDate = objectInput.readLong();
		attachments = objectInput.readUTF();
		errors = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayCaseResultId);

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

		objectOutput.writeLong(commentMBMessageId);

		objectOutput.writeLong(testrayBuildId);

		objectOutput.writeLong(testrayCaseId);

		objectOutput.writeLong(testrayComponentId);

		objectOutput.writeLong(testrayRunId);

		objectOutput.writeLong(assignedUserId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(closedDate);

		if (attachments == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(attachments);
		}

		if (errors == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(errors);
		}

		objectOutput.writeInt(status);
	}

	public long testrayCaseResultId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commentMBMessageId;
	public long testrayBuildId;
	public long testrayCaseId;
	public long testrayComponentId;
	public long testrayRunId;
	public long assignedUserId;
	public long startDate;
	public long closedDate;
	public String attachments;
	public String errors;
	public int status;

}
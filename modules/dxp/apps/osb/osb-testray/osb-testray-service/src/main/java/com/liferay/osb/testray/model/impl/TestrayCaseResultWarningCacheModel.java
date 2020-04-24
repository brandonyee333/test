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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.model.TestrayCaseResultWarning;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayCaseResultWarning in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class TestrayCaseResultWarningCacheModel
	implements CacheModel<TestrayCaseResultWarning>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayCaseResultWarningCacheModel)) {
			return false;
		}

		TestrayCaseResultWarningCacheModel testrayCaseResultWarningCacheModel =
			(TestrayCaseResultWarningCacheModel)obj;

		if (testrayCaseResultWarningId ==
				testrayCaseResultWarningCacheModel.testrayCaseResultWarningId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayCaseResultWarningId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{testrayCaseResultWarningId=");
		sb.append(testrayCaseResultWarningId);
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
		sb.append(", testrayCaseResultId=");
		sb.append(testrayCaseResultId);
		sb.append(", content=");
		sb.append(content);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayCaseResultWarning toEntityModel() {
		TestrayCaseResultWarningImpl testrayCaseResultWarningImpl =
			new TestrayCaseResultWarningImpl();

		testrayCaseResultWarningImpl.setTestrayCaseResultWarningId(
			testrayCaseResultWarningId);
		testrayCaseResultWarningImpl.setGroupId(groupId);
		testrayCaseResultWarningImpl.setCompanyId(companyId);
		testrayCaseResultWarningImpl.setUserId(userId);

		if (userName == null) {
			testrayCaseResultWarningImpl.setUserName("");
		}
		else {
			testrayCaseResultWarningImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayCaseResultWarningImpl.setCreateDate(null);
		}
		else {
			testrayCaseResultWarningImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayCaseResultWarningImpl.setModifiedDate(null);
		}
		else {
			testrayCaseResultWarningImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		testrayCaseResultWarningImpl.setTestrayCaseResultId(
			testrayCaseResultId);

		if (content == null) {
			testrayCaseResultWarningImpl.setContent("");
		}
		else {
			testrayCaseResultWarningImpl.setContent(content);
		}

		testrayCaseResultWarningImpl.resetOriginalValues();

		return testrayCaseResultWarningImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayCaseResultWarningId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayCaseResultId = objectInput.readLong();
		content = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayCaseResultWarningId);

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

		objectOutput.writeLong(testrayCaseResultId);

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}
	}

	public long testrayCaseResultWarningId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayCaseResultId;
	public String content;

}
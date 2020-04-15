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

package com.liferay.osb.customer.github.model.impl;

import com.liferay.osb.customer.github.model.Collaborator;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Collaborator in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CollaboratorCacheModel
	implements CacheModel<Collaborator>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CollaboratorCacheModel)) {
			return false;
		}

		CollaboratorCacheModel collaboratorCacheModel =
			(CollaboratorCacheModel)obj;

		if (collaboratorId == collaboratorCacheModel.collaboratorId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, collaboratorId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{collaboratorId=");
		sb.append(collaboratorId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", fullName=");
		sb.append(fullName);
		sb.append(", gitHubUserName=");
		sb.append(gitHubUserName);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Collaborator toEntityModel() {
		CollaboratorImpl collaboratorImpl = new CollaboratorImpl();

		collaboratorImpl.setCollaboratorId(collaboratorId);
		collaboratorImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			collaboratorImpl.setCreateDate(null);
		}
		else {
			collaboratorImpl.setCreateDate(new Date(createDate));
		}

		collaboratorImpl.setAccountEntryId(accountEntryId);

		if (emailAddress == null) {
			collaboratorImpl.setEmailAddress("");
		}
		else {
			collaboratorImpl.setEmailAddress(emailAddress);
		}

		if (fullName == null) {
			collaboratorImpl.setFullName("");
		}
		else {
			collaboratorImpl.setFullName(fullName);
		}

		if (gitHubUserName == null) {
			collaboratorImpl.setGitHubUserName("");
		}
		else {
			collaboratorImpl.setGitHubUserName(gitHubUserName);
		}

		collaboratorImpl.setStatus(status);

		collaboratorImpl.resetOriginalValues();

		return collaboratorImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		collaboratorId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();
		emailAddress = objectInput.readUTF();
		fullName = objectInput.readUTF();
		gitHubUserName = objectInput.readUTF();

		status = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(collaboratorId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		objectOutput.writeLong(accountEntryId);

		if (emailAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}

		if (fullName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fullName);
		}

		if (gitHubUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(gitHubUserName);
		}

		objectOutput.writeBoolean(status);
	}

	public long collaboratorId;
	public long userId;
	public long createDate;
	public long accountEntryId;
	public String emailAddress;
	public String fullName;
	public String gitHubUserName;
	public boolean status;

}
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CollaboratorCacheModel)) {
			return false;
		}

		CollaboratorCacheModel collaboratorCacheModel =
			(CollaboratorCacheModel)object;

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

		status = objectInput.readInt();
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

		objectOutput.writeInt(status);
	}

	public long collaboratorId;
	public long userId;
	public long createDate;
	public long accountEntryId;
	public String emailAddress;
	public String fullName;
	public String gitHubUserName;
	public int status;

}
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

package com.liferay.osb.customer.release.notes.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.notes.model.ReleaseNotes;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ReleaseNotes in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ReleaseNotes
 * @generated
 */
@ProviderType
public class ReleaseNotesCacheModel implements CacheModel<ReleaseNotes>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReleaseNotesCacheModel)) {
			return false;
		}

		ReleaseNotesCacheModel releaseNotesCacheModel = (ReleaseNotesCacheModel)obj;

		if (releaseNotesId == releaseNotesCacheModel.releaseNotesId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, releaseNotesId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", releaseNotesId=");
		sb.append(releaseNotesId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", jiraIssueKeys=");
		sb.append(jiraIssueKeys);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ReleaseNotes toEntityModel() {
		ReleaseNotesImpl releaseNotesImpl = new ReleaseNotesImpl();

		if (uuid == null) {
			releaseNotesImpl.setUuid(StringPool.BLANK);
		}
		else {
			releaseNotesImpl.setUuid(uuid);
		}

		releaseNotesImpl.setReleaseNotesId(releaseNotesId);
		releaseNotesImpl.setUserId(userId);

		if (userName == null) {
			releaseNotesImpl.setUserName(StringPool.BLANK);
		}
		else {
			releaseNotesImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			releaseNotesImpl.setCreateDate(null);
		}
		else {
			releaseNotesImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			releaseNotesImpl.setModifiedDate(null);
		}
		else {
			releaseNotesImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			releaseNotesImpl.setName(StringPool.BLANK);
		}
		else {
			releaseNotesImpl.setName(name);
		}

		if (jiraIssueKeys == null) {
			releaseNotesImpl.setJiraIssueKeys(StringPool.BLANK);
		}
		else {
			releaseNotesImpl.setJiraIssueKeys(jiraIssueKeys);
		}

		releaseNotesImpl.resetOriginalValues();

		return releaseNotesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		releaseNotesId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		jiraIssueKeys = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(releaseNotesId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (jiraIssueKeys == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(jiraIssueKeys);
		}
	}

	public String uuid;
	public long releaseNotesId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String jiraIssueKeys;
}
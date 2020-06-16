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

package com.liferay.osb.customer.release.notes.model.impl;

import com.liferay.osb.customer.release.notes.model.ReleaseNotes;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ReleaseNotes in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReleaseNotesCacheModel
	implements CacheModel<ReleaseNotes>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReleaseNotesCacheModel)) {
			return false;
		}

		ReleaseNotesCacheModel releaseNotesCacheModel =
			(ReleaseNotesCacheModel)obj;

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
			releaseNotesImpl.setUuid("");
		}
		else {
			releaseNotesImpl.setUuid(uuid);
		}

		releaseNotesImpl.setReleaseNotesId(releaseNotesId);
		releaseNotesImpl.setUserId(userId);

		if (userName == null) {
			releaseNotesImpl.setUserName("");
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
			releaseNotesImpl.setName("");
		}
		else {
			releaseNotesImpl.setName(name);
		}

		if (jiraIssueKeys == null) {
			releaseNotesImpl.setJiraIssueKeys("");
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(releaseNotesId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (jiraIssueKeys == null) {
			objectOutput.writeUTF("");
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
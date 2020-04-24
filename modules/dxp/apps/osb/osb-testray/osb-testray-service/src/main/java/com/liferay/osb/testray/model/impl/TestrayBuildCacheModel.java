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

import com.liferay.osb.testray.model.TestrayBuild;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayBuild in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class TestrayBuildCacheModel
	implements CacheModel<TestrayBuild>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayBuildCacheModel)) {
			return false;
		}

		TestrayBuildCacheModel testrayBuildCacheModel =
			(TestrayBuildCacheModel)obj;

		if (testrayBuildId == testrayBuildCacheModel.testrayBuildId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayBuildId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{testrayBuildId=");
		sb.append(testrayBuildId);
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
		sb.append(", templateTestrayBuildId=");
		sb.append(templateTestrayBuildId);
		sb.append(", testrayRoutineId=");
		sb.append(testrayRoutineId);
		sb.append(", testrayProductVersionId=");
		sb.append(testrayProductVersionId);
		sb.append(", testrayProjectId=");
		sb.append(testrayProjectId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", descriptionType=");
		sb.append(descriptionType);
		sb.append(", template=");
		sb.append(template);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", gitHash=");
		sb.append(gitHash);
		sb.append(", githubCompareURLs=");
		sb.append(githubCompareURLs);
		sb.append(", promoted=");
		sb.append(promoted);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayBuild toEntityModel() {
		TestrayBuildImpl testrayBuildImpl = new TestrayBuildImpl();

		testrayBuildImpl.setTestrayBuildId(testrayBuildId);
		testrayBuildImpl.setGroupId(groupId);
		testrayBuildImpl.setCompanyId(companyId);
		testrayBuildImpl.setUserId(userId);

		if (userName == null) {
			testrayBuildImpl.setUserName("");
		}
		else {
			testrayBuildImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayBuildImpl.setCreateDate(null);
		}
		else {
			testrayBuildImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayBuildImpl.setModifiedDate(null);
		}
		else {
			testrayBuildImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayBuildImpl.setTemplateTestrayBuildId(templateTestrayBuildId);
		testrayBuildImpl.setTestrayRoutineId(testrayRoutineId);
		testrayBuildImpl.setTestrayProductVersionId(testrayProductVersionId);
		testrayBuildImpl.setTestrayProjectId(testrayProjectId);

		if (name == null) {
			testrayBuildImpl.setName("");
		}
		else {
			testrayBuildImpl.setName(name);
		}

		if (description == null) {
			testrayBuildImpl.setDescription("");
		}
		else {
			testrayBuildImpl.setDescription(description);
		}

		if (descriptionType == null) {
			testrayBuildImpl.setDescriptionType("");
		}
		else {
			testrayBuildImpl.setDescriptionType(descriptionType);
		}

		testrayBuildImpl.setTemplate(template);

		if (dueDate == Long.MIN_VALUE) {
			testrayBuildImpl.setDueDate(null);
		}
		else {
			testrayBuildImpl.setDueDate(new Date(dueDate));
		}

		if (gitHash == null) {
			testrayBuildImpl.setGitHash("");
		}
		else {
			testrayBuildImpl.setGitHash(gitHash);
		}

		if (githubCompareURLs == null) {
			testrayBuildImpl.setGithubCompareURLs("");
		}
		else {
			testrayBuildImpl.setGithubCompareURLs(githubCompareURLs);
		}

		testrayBuildImpl.setPromoted(promoted);
		testrayBuildImpl.setStatus(status);

		testrayBuildImpl.resetOriginalValues();

		return testrayBuildImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayBuildId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		templateTestrayBuildId = objectInput.readLong();

		testrayRoutineId = objectInput.readLong();

		testrayProductVersionId = objectInput.readLong();

		testrayProjectId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		descriptionType = objectInput.readUTF();

		template = objectInput.readBoolean();
		dueDate = objectInput.readLong();
		gitHash = objectInput.readUTF();
		githubCompareURLs = objectInput.readUTF();

		promoted = objectInput.readBoolean();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayBuildId);

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

		objectOutput.writeLong(templateTestrayBuildId);

		objectOutput.writeLong(testrayRoutineId);

		objectOutput.writeLong(testrayProductVersionId);

		objectOutput.writeLong(testrayProjectId);

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

		if (descriptionType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(descriptionType);
		}

		objectOutput.writeBoolean(template);
		objectOutput.writeLong(dueDate);

		if (gitHash == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(gitHash);
		}

		if (githubCompareURLs == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(githubCompareURLs);
		}

		objectOutput.writeBoolean(promoted);

		objectOutput.writeInt(status);
	}

	public long testrayBuildId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long templateTestrayBuildId;
	public long testrayRoutineId;
	public long testrayProductVersionId;
	public long testrayProjectId;
	public String name;
	public String description;
	public String descriptionType;
	public boolean template;
	public long dueDate;
	public String gitHash;
	public String githubCompareURLs;
	public boolean promoted;
	public int status;

}
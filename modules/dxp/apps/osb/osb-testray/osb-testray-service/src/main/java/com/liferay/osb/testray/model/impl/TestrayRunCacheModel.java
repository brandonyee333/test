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

import com.liferay.osb.testray.model.TestrayRun;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayRun in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestrayRunCacheModel
	implements CacheModel<TestrayRun>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayRunCacheModel)) {
			return false;
		}

		TestrayRunCacheModel testrayRunCacheModel = (TestrayRunCacheModel)obj;

		if (testrayRunId == testrayRunCacheModel.testrayRunId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayRunId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{testrayRunId=");
		sb.append(testrayRunId);
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
		sb.append(", testrayBuildId=");
		sb.append(testrayBuildId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", externalReferencePK=");
		sb.append(externalReferencePK);
		sb.append(", externalReferenceType=");
		sb.append(externalReferenceType);
		sb.append(", jenkinsJobKey=");
		sb.append(jenkinsJobKey);
		sb.append(", number=");
		sb.append(number);
		sb.append(", environmentHash=");
		sb.append(environmentHash);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayRun toEntityModel() {
		TestrayRunImpl testrayRunImpl = new TestrayRunImpl();

		testrayRunImpl.setTestrayRunId(testrayRunId);
		testrayRunImpl.setGroupId(groupId);
		testrayRunImpl.setCompanyId(companyId);
		testrayRunImpl.setUserId(userId);

		if (userName == null) {
			testrayRunImpl.setUserName("");
		}
		else {
			testrayRunImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayRunImpl.setCreateDate(null);
		}
		else {
			testrayRunImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayRunImpl.setModifiedDate(null);
		}
		else {
			testrayRunImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayRunImpl.setTestrayBuildId(testrayBuildId);

		if (name == null) {
			testrayRunImpl.setName("");
		}
		else {
			testrayRunImpl.setName(name);
		}

		if (description == null) {
			testrayRunImpl.setDescription("");
		}
		else {
			testrayRunImpl.setDescription(description);
		}

		if (externalReferencePK == null) {
			testrayRunImpl.setExternalReferencePK("");
		}
		else {
			testrayRunImpl.setExternalReferencePK(externalReferencePK);
		}

		testrayRunImpl.setExternalReferenceType(externalReferenceType);
		testrayRunImpl.setJenkinsJobKey(jenkinsJobKey);
		testrayRunImpl.setNumber(number);

		if (environmentHash == null) {
			testrayRunImpl.setEnvironmentHash("");
		}
		else {
			testrayRunImpl.setEnvironmentHash(environmentHash);
		}

		testrayRunImpl.resetOriginalValues();

		return testrayRunImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayRunId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayBuildId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		externalReferencePK = objectInput.readUTF();

		externalReferenceType = objectInput.readInt();

		jenkinsJobKey = objectInput.readLong();

		number = objectInput.readLong();
		environmentHash = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayRunId);

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

		objectOutput.writeLong(testrayBuildId);

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

		if (externalReferencePK == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferencePK);
		}

		objectOutput.writeInt(externalReferenceType);

		objectOutput.writeLong(jenkinsJobKey);

		objectOutput.writeLong(number);

		if (environmentHash == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(environmentHash);
		}
	}

	public long testrayRunId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayBuildId;
	public String name;
	public String description;
	public String externalReferencePK;
	public int externalReferenceType;
	public long jenkinsJobKey;
	public long number;
	public String environmentHash;

}
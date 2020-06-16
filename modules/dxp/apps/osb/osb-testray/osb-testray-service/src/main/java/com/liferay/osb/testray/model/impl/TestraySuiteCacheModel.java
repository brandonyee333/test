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

import com.liferay.osb.testray.model.TestraySuite;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestraySuite in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestraySuiteCacheModel
	implements CacheModel<TestraySuite>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestraySuiteCacheModel)) {
			return false;
		}

		TestraySuiteCacheModel testraySuiteCacheModel =
			(TestraySuiteCacheModel)obj;

		if (testraySuiteId == testraySuiteCacheModel.testraySuiteId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testraySuiteId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{testraySuiteId=");
		sb.append(testraySuiteId);
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
		sb.append(", testrayProjectId=");
		sb.append(testrayProjectId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", caseParameters=");
		sb.append(caseParameters);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestraySuite toEntityModel() {
		TestraySuiteImpl testraySuiteImpl = new TestraySuiteImpl();

		testraySuiteImpl.setTestraySuiteId(testraySuiteId);
		testraySuiteImpl.setGroupId(groupId);
		testraySuiteImpl.setCompanyId(companyId);
		testraySuiteImpl.setUserId(userId);

		if (userName == null) {
			testraySuiteImpl.setUserName("");
		}
		else {
			testraySuiteImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testraySuiteImpl.setCreateDate(null);
		}
		else {
			testraySuiteImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testraySuiteImpl.setModifiedDate(null);
		}
		else {
			testraySuiteImpl.setModifiedDate(new Date(modifiedDate));
		}

		testraySuiteImpl.setTestrayProjectId(testrayProjectId);

		if (name == null) {
			testraySuiteImpl.setName("");
		}
		else {
			testraySuiteImpl.setName(name);
		}

		if (description == null) {
			testraySuiteImpl.setDescription("");
		}
		else {
			testraySuiteImpl.setDescription(description);
		}

		if (caseParameters == null) {
			testraySuiteImpl.setCaseParameters("");
		}
		else {
			testraySuiteImpl.setCaseParameters(caseParameters);
		}

		testraySuiteImpl.resetOriginalValues();

		return testraySuiteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testraySuiteId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayProjectId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		caseParameters = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testraySuiteId);

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

		if (caseParameters == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(caseParameters);
		}
	}

	public long testraySuiteId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayProjectId;
	public String name;
	public String description;
	public String caseParameters;

}
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

import com.liferay.osb.testray.model.TestrayCaseType;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayCaseType in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class TestrayCaseTypeCacheModel
	implements CacheModel<TestrayCaseType>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayCaseTypeCacheModel)) {
			return false;
		}

		TestrayCaseTypeCacheModel testrayCaseTypeCacheModel =
			(TestrayCaseTypeCacheModel)obj;

		if (testrayCaseTypeId == testrayCaseTypeCacheModel.testrayCaseTypeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayCaseTypeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{testrayCaseTypeId=");
		sb.append(testrayCaseTypeId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayCaseType toEntityModel() {
		TestrayCaseTypeImpl testrayCaseTypeImpl = new TestrayCaseTypeImpl();

		testrayCaseTypeImpl.setTestrayCaseTypeId(testrayCaseTypeId);
		testrayCaseTypeImpl.setGroupId(groupId);
		testrayCaseTypeImpl.setCompanyId(companyId);
		testrayCaseTypeImpl.setUserId(userId);

		if (userName == null) {
			testrayCaseTypeImpl.setUserName("");
		}
		else {
			testrayCaseTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayCaseTypeImpl.setCreateDate(null);
		}
		else {
			testrayCaseTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayCaseTypeImpl.setModifiedDate(null);
		}
		else {
			testrayCaseTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			testrayCaseTypeImpl.setName("");
		}
		else {
			testrayCaseTypeImpl.setName(name);
		}

		testrayCaseTypeImpl.resetOriginalValues();

		return testrayCaseTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayCaseTypeId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayCaseTypeId);

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

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long testrayCaseTypeId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;

}
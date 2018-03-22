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

package com.liferay.osb.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.CorpProject;

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
 * The cache model class for representing CorpProject in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CorpProject
 * @generated
 */
@ProviderType
public class CorpProjectCacheModel implements CacheModel<CorpProject>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CorpProjectCacheModel)) {
			return false;
		}

		CorpProjectCacheModel corpProjectCacheModel = (CorpProjectCacheModel)obj;

		if (corpProjectId == corpProjectCacheModel.corpProjectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, corpProjectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", corpProjectId=");
		sb.append(corpProjectId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", dossieraProjectKey=");
		sb.append(dossieraProjectKey);
		sb.append(", salesforceProjectKey=");
		sb.append(salesforceProjectKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", organizationId=");
		sb.append(organizationId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CorpProject toEntityModel() {
		CorpProjectImpl corpProjectImpl = new CorpProjectImpl();

		if (uuid == null) {
			corpProjectImpl.setUuid(StringPool.BLANK);
		}
		else {
			corpProjectImpl.setUuid(uuid);
		}

		corpProjectImpl.setCorpProjectId(corpProjectId);
		corpProjectImpl.setUserId(userId);

		if (userName == null) {
			corpProjectImpl.setUserName(StringPool.BLANK);
		}
		else {
			corpProjectImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			corpProjectImpl.setCreateDate(null);
		}
		else {
			corpProjectImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			corpProjectImpl.setModifiedDate(null);
		}
		else {
			corpProjectImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (dossieraProjectKey == null) {
			corpProjectImpl.setDossieraProjectKey(StringPool.BLANK);
		}
		else {
			corpProjectImpl.setDossieraProjectKey(dossieraProjectKey);
		}

		if (salesforceProjectKey == null) {
			corpProjectImpl.setSalesforceProjectKey(StringPool.BLANK);
		}
		else {
			corpProjectImpl.setSalesforceProjectKey(salesforceProjectKey);
		}

		if (name == null) {
			corpProjectImpl.setName(StringPool.BLANK);
		}
		else {
			corpProjectImpl.setName(name);
		}

		corpProjectImpl.setOrganizationId(organizationId);

		corpProjectImpl.resetOriginalValues();

		return corpProjectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		corpProjectId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		dossieraProjectKey = objectInput.readUTF();
		salesforceProjectKey = objectInput.readUTF();
		name = objectInput.readUTF();

		organizationId = objectInput.readLong();
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

		objectOutput.writeLong(corpProjectId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (dossieraProjectKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dossieraProjectKey);
		}

		if (salesforceProjectKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(salesforceProjectKey);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(organizationId);
	}

	public String uuid;
	public long corpProjectId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String dossieraProjectKey;
	public String salesforceProjectKey;
	public String name;
	public long organizationId;
}
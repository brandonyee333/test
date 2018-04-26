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

import com.liferay.osb.model.SecurityPatch;

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
 * The cache model class for representing SecurityPatch in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SecurityPatch
 * @generated
 */
@ProviderType
public class SecurityPatchCacheModel implements CacheModel<SecurityPatch>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SecurityPatchCacheModel)) {
			return false;
		}

		SecurityPatchCacheModel securityPatchCacheModel = (SecurityPatchCacheModel)obj;

		if (securityPatchId == securityPatchCacheModel.securityPatchId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, securityPatchId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{securityPatchId=");
		sb.append(securityPatchId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", portletId=");
		sb.append(portletId);
		sb.append(", envLFR=");
		sb.append(envLFR);
		sb.append(", name=");
		sb.append(name);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SecurityPatch toEntityModel() {
		SecurityPatchImpl securityPatchImpl = new SecurityPatchImpl();

		securityPatchImpl.setSecurityPatchId(securityPatchId);
		securityPatchImpl.setUserId(userId);

		if (userName == null) {
			securityPatchImpl.setUserName(StringPool.BLANK);
		}
		else {
			securityPatchImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			securityPatchImpl.setCreateDate(null);
		}
		else {
			securityPatchImpl.setCreateDate(new Date(createDate));
		}

		securityPatchImpl.setAccountEntryId(accountEntryId);

		if (portletId == null) {
			securityPatchImpl.setPortletId(StringPool.BLANK);
		}
		else {
			securityPatchImpl.setPortletId(portletId);
		}

		securityPatchImpl.setEnvLFR(envLFR);

		if (name == null) {
			securityPatchImpl.setName(StringPool.BLANK);
		}
		else {
			securityPatchImpl.setName(name);
		}

		if (fileName == null) {
			securityPatchImpl.setFileName(StringPool.BLANK);
		}
		else {
			securityPatchImpl.setFileName(fileName);
		}

		securityPatchImpl.resetOriginalValues();

		return securityPatchImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		securityPatchId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();
		portletId = objectInput.readUTF();

		envLFR = objectInput.readInt();
		name = objectInput.readUTF();
		fileName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(securityPatchId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(accountEntryId);

		if (portletId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(portletId);
		}

		objectOutput.writeInt(envLFR);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (fileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileName);
		}
	}

	public long securityPatchId;
	public long userId;
	public String userName;
	public long createDate;
	public long accountEntryId;
	public String portletId;
	public int envLFR;
	public String name;
	public String fileName;
}
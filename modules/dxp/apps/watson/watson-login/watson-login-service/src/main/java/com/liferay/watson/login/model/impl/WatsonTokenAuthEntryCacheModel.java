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

package com.liferay.watson.login.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.watson.login.model.WatsonTokenAuthEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonTokenAuthEntry in entity cache.
 *
 * @author Steven Smith
 * @see WatsonTokenAuthEntry
 * @generated
 */
@ProviderType
public class WatsonTokenAuthEntryCacheModel implements CacheModel<WatsonTokenAuthEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonTokenAuthEntryCacheModel)) {
			return false;
		}

		WatsonTokenAuthEntryCacheModel watsonTokenAuthEntryCacheModel = (WatsonTokenAuthEntryCacheModel)obj;

		if (watsonTokenAuthEntryId == watsonTokenAuthEntryCacheModel.watsonTokenAuthEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonTokenAuthEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{watsonTokenAuthEntryId=");
		sb.append(watsonTokenAuthEntryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", active=");
		sb.append(active);
		sb.append(", loginIP=");
		sb.append(loginIP);
		sb.append(", token=");
		sb.append(token);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", loginDate=");
		sb.append(loginDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonTokenAuthEntry toEntityModel() {
		WatsonTokenAuthEntryImpl watsonTokenAuthEntryImpl = new WatsonTokenAuthEntryImpl();

		watsonTokenAuthEntryImpl.setWatsonTokenAuthEntryId(watsonTokenAuthEntryId);
		watsonTokenAuthEntryImpl.setCompanyId(companyId);
		watsonTokenAuthEntryImpl.setUserId(userId);

		if (userName == null) {
			watsonTokenAuthEntryImpl.setUserName("");
		}
		else {
			watsonTokenAuthEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonTokenAuthEntryImpl.setCreateDate(null);
		}
		else {
			watsonTokenAuthEntryImpl.setCreateDate(new Date(createDate));
		}

		watsonTokenAuthEntryImpl.setActive(active);

		if (loginIP == null) {
			watsonTokenAuthEntryImpl.setLoginIP("");
		}
		else {
			watsonTokenAuthEntryImpl.setLoginIP(loginIP);
		}

		if (token == null) {
			watsonTokenAuthEntryImpl.setToken("");
		}
		else {
			watsonTokenAuthEntryImpl.setToken(token);
		}

		if (expirationDate == Long.MIN_VALUE) {
			watsonTokenAuthEntryImpl.setExpirationDate(null);
		}
		else {
			watsonTokenAuthEntryImpl.setExpirationDate(new Date(expirationDate));
		}

		if (loginDate == Long.MIN_VALUE) {
			watsonTokenAuthEntryImpl.setLoginDate(null);
		}
		else {
			watsonTokenAuthEntryImpl.setLoginDate(new Date(loginDate));
		}

		watsonTokenAuthEntryImpl.resetOriginalValues();

		return watsonTokenAuthEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonTokenAuthEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		active = objectInput.readBoolean();
		loginIP = objectInput.readUTF();
		token = objectInput.readUTF();
		expirationDate = objectInput.readLong();
		loginDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonTokenAuthEntryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeBoolean(active);

		if (loginIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(loginIP);
		}

		if (token == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(token);
		}

		objectOutput.writeLong(expirationDate);
		objectOutput.writeLong(loginDate);
	}

	public long watsonTokenAuthEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public boolean active;
	public String loginIP;
	public String token;
	public long expirationDate;
	public long loginDate;
}
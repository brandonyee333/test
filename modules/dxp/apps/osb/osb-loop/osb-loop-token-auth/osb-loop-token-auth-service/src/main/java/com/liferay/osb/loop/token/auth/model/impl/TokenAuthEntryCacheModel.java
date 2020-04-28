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

package com.liferay.osb.loop.token.auth.model.impl;

import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TokenAuthEntry in entity cache.
 *
 * @author Bruno Farache
 * @generated
 */
public class TokenAuthEntryCacheModel
	implements CacheModel<TokenAuthEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TokenAuthEntryCacheModel)) {
			return false;
		}

		TokenAuthEntryCacheModel tokenAuthEntryCacheModel =
			(TokenAuthEntryCacheModel)obj;

		if (tokenAuthEntryId == tokenAuthEntryCacheModel.tokenAuthEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, tokenAuthEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{tokenAuthEntryId=");
		sb.append(tokenAuthEntryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", device=");
		sb.append(device);
		sb.append(", token=");
		sb.append(token);
		sb.append(", loginDate=");
		sb.append(loginDate);
		sb.append(", loginIP=");
		sb.append(loginIP);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TokenAuthEntry toEntityModel() {
		TokenAuthEntryImpl tokenAuthEntryImpl = new TokenAuthEntryImpl();

		tokenAuthEntryImpl.setTokenAuthEntryId(tokenAuthEntryId);
		tokenAuthEntryImpl.setCompanyId(companyId);
		tokenAuthEntryImpl.setUserId(userId);

		if (userName == null) {
			tokenAuthEntryImpl.setUserName("");
		}
		else {
			tokenAuthEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			tokenAuthEntryImpl.setCreateDate(null);
		}
		else {
			tokenAuthEntryImpl.setCreateDate(new Date(createDate));
		}

		if (device == null) {
			tokenAuthEntryImpl.setDevice("");
		}
		else {
			tokenAuthEntryImpl.setDevice(device);
		}

		if (token == null) {
			tokenAuthEntryImpl.setToken("");
		}
		else {
			tokenAuthEntryImpl.setToken(token);
		}

		if (loginDate == Long.MIN_VALUE) {
			tokenAuthEntryImpl.setLoginDate(null);
		}
		else {
			tokenAuthEntryImpl.setLoginDate(new Date(loginDate));
		}

		if (loginIP == null) {
			tokenAuthEntryImpl.setLoginIP("");
		}
		else {
			tokenAuthEntryImpl.setLoginIP(loginIP);
		}

		tokenAuthEntryImpl.resetOriginalValues();

		return tokenAuthEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		tokenAuthEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		device = objectInput.readUTF();
		token = objectInput.readUTF();
		loginDate = objectInput.readLong();
		loginIP = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(tokenAuthEntryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		if (device == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(device);
		}

		if (token == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(token);
		}

		objectOutput.writeLong(loginDate);

		if (loginIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(loginIP);
		}
	}

	public long tokenAuthEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public String device;
	public String token;
	public long loginDate;
	public String loginIP;

}
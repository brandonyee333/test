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

package com.liferay.osb.email.blacklist.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.email.blacklist.model.BlacklistEntry;

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
 * The cache model class for representing BlacklistEntry in entity cache.
 *
 * @author Jamie Sammons
 * @see BlacklistEntry
 * @generated
 */
@ProviderType
public class BlacklistEntryCacheModel implements CacheModel<BlacklistEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BlacklistEntryCacheModel)) {
			return false;
		}

		BlacklistEntryCacheModel blacklistEntryCacheModel = (BlacklistEntryCacheModel)obj;

		if (blacklistEntryId == blacklistEntryCacheModel.blacklistEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, blacklistEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{blacklistEntryId=");
		sb.append(blacklistEntryId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BlacklistEntry toEntityModel() {
		BlacklistEntryImpl blacklistEntryImpl = new BlacklistEntryImpl();

		blacklistEntryImpl.setBlacklistEntryId(blacklistEntryId);

		if (createDate == Long.MIN_VALUE) {
			blacklistEntryImpl.setCreateDate(null);
		}
		else {
			blacklistEntryImpl.setCreateDate(new Date(createDate));
		}

		if (emailAddress == null) {
			blacklistEntryImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			blacklistEntryImpl.setEmailAddress(emailAddress);
		}

		blacklistEntryImpl.resetOriginalValues();

		return blacklistEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		blacklistEntryId = objectInput.readLong();
		createDate = objectInput.readLong();
		emailAddress = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(blacklistEntryId);
		objectOutput.writeLong(createDate);

		if (emailAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}
	}

	public long blacklistEntryId;
	public long createDate;
	public String emailAddress;
}
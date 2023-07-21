/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.model.impl;

import com.liferay.osb.email.blacklist.model.BlacklistEntry;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BlacklistEntry in entity cache.
 *
 * @author Jamie Sammons
 * @generated
 */
public class BlacklistEntryCacheModel
	implements CacheModel<BlacklistEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof BlacklistEntryCacheModel)) {
			return false;
		}

		BlacklistEntryCacheModel blacklistEntryCacheModel =
			(BlacklistEntryCacheModel)object;

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
			blacklistEntryImpl.setEmailAddress("");
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(blacklistEntryId);
		objectOutput.writeLong(createDate);

		if (emailAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}
	}

	public long blacklistEntryId;
	public long createDate;
	public String emailAddress;

}
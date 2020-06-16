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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BlacklistEntryCacheModel)) {
			return false;
		}

		BlacklistEntryCacheModel blacklistEntryCacheModel =
			(BlacklistEntryCacheModel)obj;

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
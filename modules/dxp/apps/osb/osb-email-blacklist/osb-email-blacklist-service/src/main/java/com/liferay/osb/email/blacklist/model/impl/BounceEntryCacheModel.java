/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.model.impl;

import com.liferay.osb.email.blacklist.model.BounceEntry;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BounceEntry in entity cache.
 *
 * @author Jamie Sammons
 * @generated
 */
public class BounceEntryCacheModel
	implements CacheModel<BounceEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof BounceEntryCacheModel)) {
			return false;
		}

		BounceEntryCacheModel bounceEntryCacheModel =
			(BounceEntryCacheModel)object;

		if (bounceEntryId == bounceEntryCacheModel.bounceEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, bounceEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{bounceEntryId=");
		sb.append(bounceEntryId);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", bounceDate=");
		sb.append(bounceDate);
		sb.append(", bounceType=");
		sb.append(bounceType);
		sb.append(", bounceSubtype=");
		sb.append(bounceSubtype);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BounceEntry toEntityModel() {
		BounceEntryImpl bounceEntryImpl = new BounceEntryImpl();

		bounceEntryImpl.setBounceEntryId(bounceEntryId);

		if (emailAddress == null) {
			bounceEntryImpl.setEmailAddress("");
		}
		else {
			bounceEntryImpl.setEmailAddress(emailAddress);
		}

		if (bounceDate == Long.MIN_VALUE) {
			bounceEntryImpl.setBounceDate(null);
		}
		else {
			bounceEntryImpl.setBounceDate(new Date(bounceDate));
		}

		if (bounceType == null) {
			bounceEntryImpl.setBounceType("");
		}
		else {
			bounceEntryImpl.setBounceType(bounceType);
		}

		if (bounceSubtype == null) {
			bounceEntryImpl.setBounceSubtype("");
		}
		else {
			bounceEntryImpl.setBounceSubtype(bounceSubtype);
		}

		bounceEntryImpl.resetOriginalValues();

		return bounceEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		bounceEntryId = objectInput.readLong();
		emailAddress = objectInput.readUTF();
		bounceDate = objectInput.readLong();
		bounceType = objectInput.readUTF();
		bounceSubtype = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(bounceEntryId);

		if (emailAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}

		objectOutput.writeLong(bounceDate);

		if (bounceType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(bounceType);
		}

		if (bounceSubtype == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(bounceSubtype);
		}
	}

	public long bounceEntryId;
	public String emailAddress;
	public long bounceDate;
	public String bounceType;
	public String bounceSubtype;

}
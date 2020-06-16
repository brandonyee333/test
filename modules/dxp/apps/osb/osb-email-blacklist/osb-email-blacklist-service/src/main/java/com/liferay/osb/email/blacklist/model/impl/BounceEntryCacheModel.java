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

import com.liferay.osb.email.blacklist.model.BounceEntry;

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
 * The cache model class for representing BounceEntry in entity cache.
 *
 * @author Jamie Sammons
 * @see BounceEntry
 * @generated
 */
@ProviderType
public class BounceEntryCacheModel implements CacheModel<BounceEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BounceEntryCacheModel)) {
			return false;
		}

		BounceEntryCacheModel bounceEntryCacheModel = (BounceEntryCacheModel)obj;

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
			bounceEntryImpl.setEmailAddress(StringPool.BLANK);
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
			bounceEntryImpl.setBounceType(StringPool.BLANK);
		}
		else {
			bounceEntryImpl.setBounceType(bounceType);
		}

		if (bounceSubtype == null) {
			bounceEntryImpl.setBounceSubtype(StringPool.BLANK);
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
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(bounceEntryId);

		if (emailAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}

		objectOutput.writeLong(bounceDate);

		if (bounceType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(bounceType);
		}

		if (bounceSubtype == null) {
			objectOutput.writeUTF(StringPool.BLANK);
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
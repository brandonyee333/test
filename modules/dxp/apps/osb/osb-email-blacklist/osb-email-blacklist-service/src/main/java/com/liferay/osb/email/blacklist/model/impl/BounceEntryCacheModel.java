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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BounceEntryCacheModel)) {
			return false;
		}

		BounceEntryCacheModel bounceEntryCacheModel =
			(BounceEntryCacheModel)obj;

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
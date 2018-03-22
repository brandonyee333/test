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

import com.liferay.osb.model.OfferingBundle;

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
 * The cache model class for representing OfferingBundle in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingBundle
 * @generated
 */
@ProviderType
public class OfferingBundleCacheModel implements CacheModel<OfferingBundle>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfferingBundleCacheModel)) {
			return false;
		}

		OfferingBundleCacheModel offeringBundleCacheModel = (OfferingBundleCacheModel)obj;

		if (offeringBundleId == offeringBundleCacheModel.offeringBundleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, offeringBundleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{offeringBundleId=");
		sb.append(offeringBundleId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OfferingBundle toEntityModel() {
		OfferingBundleImpl offeringBundleImpl = new OfferingBundleImpl();

		offeringBundleImpl.setOfferingBundleId(offeringBundleId);
		offeringBundleImpl.setCompanyId(companyId);
		offeringBundleImpl.setUserId(userId);

		if (userName == null) {
			offeringBundleImpl.setUserName(StringPool.BLANK);
		}
		else {
			offeringBundleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			offeringBundleImpl.setCreateDate(null);
		}
		else {
			offeringBundleImpl.setCreateDate(new Date(createDate));
		}

		if (name == null) {
			offeringBundleImpl.setName(StringPool.BLANK);
		}
		else {
			offeringBundleImpl.setName(name);
		}

		offeringBundleImpl.resetOriginalValues();

		return offeringBundleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		offeringBundleId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(offeringBundleId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long offeringBundleId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public String name;
}
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

import com.liferay.osb.model.PartnerWorker;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PartnerWorker in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorker
 * @generated
 */
@ProviderType
public class PartnerWorkerCacheModel implements CacheModel<PartnerWorker>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PartnerWorkerCacheModel)) {
			return false;
		}

		PartnerWorkerCacheModel partnerWorkerCacheModel = (PartnerWorkerCacheModel)obj;

		if (partnerWorkerId == partnerWorkerCacheModel.partnerWorkerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, partnerWorkerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{partnerWorkerId=");
		sb.append(partnerWorkerId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", partnerEntryId=");
		sb.append(partnerEntryId);
		sb.append(", role=");
		sb.append(role);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PartnerWorker toEntityModel() {
		PartnerWorkerImpl partnerWorkerImpl = new PartnerWorkerImpl();

		partnerWorkerImpl.setPartnerWorkerId(partnerWorkerId);
		partnerWorkerImpl.setUserId(userId);
		partnerWorkerImpl.setPartnerEntryId(partnerEntryId);
		partnerWorkerImpl.setRole(role);

		partnerWorkerImpl.resetOriginalValues();

		return partnerWorkerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		partnerWorkerId = objectInput.readLong();

		userId = objectInput.readLong();

		partnerEntryId = objectInput.readLong();

		role = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(partnerWorkerId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(partnerEntryId);

		objectOutput.writeInt(role);
	}

	public long partnerWorkerId;
	public long userId;
	public long partnerEntryId;
	public int role;
}
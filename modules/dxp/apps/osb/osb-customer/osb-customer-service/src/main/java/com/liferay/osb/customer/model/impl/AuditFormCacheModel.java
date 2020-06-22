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

package com.liferay.osb.customer.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.model.AuditForm;

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
 * The cache model class for representing AuditForm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AuditForm
 * @generated
 */
@ProviderType
public class AuditFormCacheModel implements CacheModel<AuditForm>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditFormCacheModel)) {
			return false;
		}

		AuditFormCacheModel auditFormCacheModel = (AuditFormCacheModel)obj;

		if (auditFormId == auditFormCacheModel.auditFormId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, auditFormId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{auditFormId=");
		sb.append(auditFormId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", endUserName=");
		sb.append(endUserName);
		sb.append(", endUserEmailAddress=");
		sb.append(endUserEmailAddress);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", agreement=");
		sb.append(agreement);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AuditForm toEntityModel() {
		AuditFormImpl auditFormImpl = new AuditFormImpl();

		auditFormImpl.setAuditFormId(auditFormId);
		auditFormImpl.setUserId(userId);

		if (userName == null) {
			auditFormImpl.setUserName(StringPool.BLANK);
		}
		else {
			auditFormImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			auditFormImpl.setCreateDate(null);
		}
		else {
			auditFormImpl.setCreateDate(new Date(createDate));
		}

		if (endUserName == null) {
			auditFormImpl.setEndUserName(StringPool.BLANK);
		}
		else {
			auditFormImpl.setEndUserName(endUserName);
		}

		if (endUserEmailAddress == null) {
			auditFormImpl.setEndUserEmailAddress(StringPool.BLANK);
		}
		else {
			auditFormImpl.setEndUserEmailAddress(endUserEmailAddress);
		}

		if (companyName == null) {
			auditFormImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			auditFormImpl.setCompanyName(companyName);
		}

		auditFormImpl.setAgreement(agreement);

		auditFormImpl.resetOriginalValues();

		return auditFormImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		auditFormId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		endUserName = objectInput.readUTF();
		endUserEmailAddress = objectInput.readUTF();
		companyName = objectInput.readUTF();

		agreement = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(auditFormId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		if (endUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(endUserName);
		}

		if (endUserEmailAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(endUserEmailAddress);
		}

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		objectOutput.writeBoolean(agreement);
	}

	public long auditFormId;
	public long userId;
	public String userName;
	public long createDate;
	public String endUserName;
	public String endUserEmailAddress;
	public String companyName;
	public boolean agreement;
}
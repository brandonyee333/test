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

import com.liferay.osb.model.ContractAudit;

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
 * The cache model class for representing ContractAudit in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContractAudit
 * @generated
 */
@ProviderType
public class ContractAuditCacheModel implements CacheModel<ContractAudit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContractAuditCacheModel)) {
			return false;
		}

		ContractAuditCacheModel contractAuditCacheModel = (ContractAuditCacheModel)obj;

		if (contractAuditId == contractAuditCacheModel.contractAuditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contractAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{contractAuditId=");
		sb.append(contractAuditId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", userEmailAddress=");
		sb.append(userEmailAddress);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", contractEntryId=");
		sb.append(contractEntryId);
		sb.append(", signatoryClassNameId=");
		sb.append(signatoryClassNameId);
		sb.append(", signatoryClassPK=");
		sb.append(signatoryClassPK);
		sb.append(", productClassNameId=");
		sb.append(productClassNameId);
		sb.append(", productClassPK=");
		sb.append(productClassPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", version=");
		sb.append(version);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContractAudit toEntityModel() {
		ContractAuditImpl contractAuditImpl = new ContractAuditImpl();

		contractAuditImpl.setContractAuditId(contractAuditId);
		contractAuditImpl.setUserId(userId);

		if (userName == null) {
			contractAuditImpl.setUserName(StringPool.BLANK);
		}
		else {
			contractAuditImpl.setUserName(userName);
		}

		if (userEmailAddress == null) {
			contractAuditImpl.setUserEmailAddress(StringPool.BLANK);
		}
		else {
			contractAuditImpl.setUserEmailAddress(userEmailAddress);
		}

		if (createDate == Long.MIN_VALUE) {
			contractAuditImpl.setCreateDate(null);
		}
		else {
			contractAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			contractAuditImpl.setModifiedDate(null);
		}
		else {
			contractAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		contractAuditImpl.setContractEntryId(contractEntryId);
		contractAuditImpl.setSignatoryClassNameId(signatoryClassNameId);
		contractAuditImpl.setSignatoryClassPK(signatoryClassPK);
		contractAuditImpl.setProductClassNameId(productClassNameId);
		contractAuditImpl.setProductClassPK(productClassPK);

		if (type == null) {
			contractAuditImpl.setType(StringPool.BLANK);
		}
		else {
			contractAuditImpl.setType(type);
		}

		if (languageId == null) {
			contractAuditImpl.setLanguageId(StringPool.BLANK);
		}
		else {
			contractAuditImpl.setLanguageId(languageId);
		}

		contractAuditImpl.setVersion(version);

		contractAuditImpl.resetOriginalValues();

		return contractAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		contractAuditId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		userEmailAddress = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		contractEntryId = objectInput.readLong();

		signatoryClassNameId = objectInput.readLong();

		signatoryClassPK = objectInput.readLong();

		productClassNameId = objectInput.readLong();

		productClassPK = objectInput.readLong();
		type = objectInput.readUTF();
		languageId = objectInput.readUTF();

		version = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(contractAuditId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		if (userEmailAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userEmailAddress);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(contractEntryId);

		objectOutput.writeLong(signatoryClassNameId);

		objectOutput.writeLong(signatoryClassPK);

		objectOutput.writeLong(productClassNameId);

		objectOutput.writeLong(productClassPK);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (languageId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(languageId);
		}

		objectOutput.writeInt(version);
	}

	public long contractAuditId;
	public long userId;
	public String userName;
	public String userEmailAddress;
	public long createDate;
	public long modifiedDate;
	public long contractEntryId;
	public long signatoryClassNameId;
	public long signatoryClassPK;
	public long productClassNameId;
	public long productClassPK;
	public String type;
	public String languageId;
	public int version;
}
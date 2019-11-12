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

package com.liferay.osb.customer.model.impl;

import com.liferay.osb.customer.model.AuditForm;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AuditForm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AuditFormCacheModel
	implements CacheModel<AuditForm>, Externalizable {

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
		StringBundler sb = new StringBundler(15);

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
			auditFormImpl.setUserName("");
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
			auditFormImpl.setEndUserName("");
		}
		else {
			auditFormImpl.setEndUserName(endUserName);
		}

		if (endUserEmailAddress == null) {
			auditFormImpl.setEndUserEmailAddress("");
		}
		else {
			auditFormImpl.setEndUserEmailAddress(endUserEmailAddress);
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

		agreement = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(auditFormId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		if (endUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(endUserName);
		}

		if (endUserEmailAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(endUserEmailAddress);
		}

		objectOutput.writeBoolean(agreement);
	}

	public long auditFormId;
	public long userId;
	public String userName;
	public long createDate;
	public String endUserName;
	public String endUserEmailAddress;
	public boolean agreement;

}
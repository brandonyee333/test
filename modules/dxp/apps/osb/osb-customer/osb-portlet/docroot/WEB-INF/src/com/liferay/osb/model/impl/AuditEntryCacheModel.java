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

import com.liferay.osb.model.AuditEntry;

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
 * The cache model class for representing AuditEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntry
 * @generated
 */
@ProviderType
public class AuditEntryCacheModel implements CacheModel<AuditEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditEntryCacheModel)) {
			return false;
		}

		AuditEntryCacheModel auditEntryCacheModel = (AuditEntryCacheModel)obj;

		if (auditEntryId == auditEntryCacheModel.auditEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, auditEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{auditEntryId=");
		sb.append(auditEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", previousAuditEntryId=");
		sb.append(previousAuditEntryId);
		sb.append(", auditSetId=");
		sb.append(auditSetId);
		sb.append(", fieldClassNameId=");
		sb.append(fieldClassNameId);
		sb.append(", fieldClassPK=");
		sb.append(fieldClassPK);
		sb.append(", action=");
		sb.append(action);
		sb.append(", field=");
		sb.append(field);
		sb.append(", visibility=");
		sb.append(visibility);
		sb.append(", oldLabel=");
		sb.append(oldLabel);
		sb.append(", oldValue=");
		sb.append(oldValue);
		sb.append(", newLabel=");
		sb.append(newLabel);
		sb.append(", newValue=");
		sb.append(newValue);
		sb.append(", description=");
		sb.append(description);
		sb.append(", i18n=");
		sb.append(i18n);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AuditEntry toEntityModel() {
		AuditEntryImpl auditEntryImpl = new AuditEntryImpl();

		auditEntryImpl.setAuditEntryId(auditEntryId);
		auditEntryImpl.setUserId(userId);

		if (userName == null) {
			auditEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			auditEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			auditEntryImpl.setCreateDate(null);
		}
		else {
			auditEntryImpl.setCreateDate(new Date(createDate));
		}

		auditEntryImpl.setClassNameId(classNameId);
		auditEntryImpl.setClassPK(classPK);
		auditEntryImpl.setPreviousAuditEntryId(previousAuditEntryId);
		auditEntryImpl.setAuditSetId(auditSetId);
		auditEntryImpl.setFieldClassNameId(fieldClassNameId);
		auditEntryImpl.setFieldClassPK(fieldClassPK);
		auditEntryImpl.setAction(action);
		auditEntryImpl.setField(field);
		auditEntryImpl.setVisibility(visibility);

		if (oldLabel == null) {
			auditEntryImpl.setOldLabel(StringPool.BLANK);
		}
		else {
			auditEntryImpl.setOldLabel(oldLabel);
		}

		if (oldValue == null) {
			auditEntryImpl.setOldValue(StringPool.BLANK);
		}
		else {
			auditEntryImpl.setOldValue(oldValue);
		}

		if (newLabel == null) {
			auditEntryImpl.setNewLabel(StringPool.BLANK);
		}
		else {
			auditEntryImpl.setNewLabel(newLabel);
		}

		if (newValue == null) {
			auditEntryImpl.setNewValue(StringPool.BLANK);
		}
		else {
			auditEntryImpl.setNewValue(newValue);
		}

		if (description == null) {
			auditEntryImpl.setDescription(StringPool.BLANK);
		}
		else {
			auditEntryImpl.setDescription(description);
		}

		auditEntryImpl.setI18n(i18n);

		auditEntryImpl.resetOriginalValues();

		return auditEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		auditEntryId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		previousAuditEntryId = objectInput.readLong();

		auditSetId = objectInput.readLong();

		fieldClassNameId = objectInput.readLong();

		fieldClassPK = objectInput.readLong();

		action = objectInput.readInt();

		field = objectInput.readInt();

		visibility = objectInput.readInt();
		oldLabel = objectInput.readUTF();
		oldValue = objectInput.readUTF();
		newLabel = objectInput.readUTF();
		newValue = objectInput.readUTF();
		description = objectInput.readUTF();

		i18n = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(auditEntryId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(previousAuditEntryId);

		objectOutput.writeLong(auditSetId);

		objectOutput.writeLong(fieldClassNameId);

		objectOutput.writeLong(fieldClassPK);

		objectOutput.writeInt(action);

		objectOutput.writeInt(field);

		objectOutput.writeInt(visibility);

		if (oldLabel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(oldLabel);
		}

		if (oldValue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(oldValue);
		}

		if (newLabel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(newLabel);
		}

		if (newValue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(newValue);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeBoolean(i18n);
	}

	public long auditEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long classNameId;
	public long classPK;
	public long previousAuditEntryId;
	public long auditSetId;
	public long fieldClassNameId;
	public long fieldClassPK;
	public int action;
	public int field;
	public int visibility;
	public String oldLabel;
	public String oldValue;
	public String newLabel;
	public String newValue;
	public String description;
	public boolean i18n;
}
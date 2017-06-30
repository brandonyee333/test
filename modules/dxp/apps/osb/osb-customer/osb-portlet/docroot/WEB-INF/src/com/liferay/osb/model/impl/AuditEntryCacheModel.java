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

import com.liferay.osb.model.AuditEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AuditEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntry
 * @generated
 */
public class AuditEntryCacheModel implements CacheModel<AuditEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

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
		sb.append(", i18n=");
		sb.append(i18n);
		sb.append("}");

		return sb.toString();
	}

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

		auditEntryImpl.setI18n(i18n);

		auditEntryImpl.resetOriginalValues();

		return auditEntryImpl;
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
	public boolean i18n;
}
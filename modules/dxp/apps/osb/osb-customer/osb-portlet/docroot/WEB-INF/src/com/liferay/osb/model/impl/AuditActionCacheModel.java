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

import com.liferay.osb.model.AuditAction;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AuditAction in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AuditAction
 * @generated
 */
@ProviderType
public class AuditActionCacheModel implements CacheModel<AuditAction>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditActionCacheModel)) {
			return false;
		}

		AuditActionCacheModel auditActionCacheModel = (AuditActionCacheModel)obj;

		if (auditActionId == auditActionCacheModel.auditActionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, auditActionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{auditActionId=");
		sb.append(auditActionId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", mappingClassPK=");
		sb.append(mappingClassPK);
		sb.append(", action=");
		sb.append(action);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AuditAction toEntityModel() {
		AuditActionImpl auditActionImpl = new AuditActionImpl();

		auditActionImpl.setAuditActionId(auditActionId);

		if (modifiedDate == Long.MIN_VALUE) {
			auditActionImpl.setModifiedDate(null);
		}
		else {
			auditActionImpl.setModifiedDate(new Date(modifiedDate));
		}

		auditActionImpl.setClassNameId(classNameId);
		auditActionImpl.setClassPK(classPK);
		auditActionImpl.setMappingClassPK(mappingClassPK);
		auditActionImpl.setAction(action);

		auditActionImpl.resetOriginalValues();

		return auditActionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		auditActionId = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		mappingClassPK = objectInput.readLong();

		action = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(auditActionId);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(mappingClassPK);

		objectOutput.writeInt(action);
	}

	public long auditActionId;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public long mappingClassPK;
	public int action;
}
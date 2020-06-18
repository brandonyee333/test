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

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.osb.customer.admin.model.ExternalIdMapper;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ExternalIdMapper in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ExternalIdMapperCacheModel
	implements CacheModel<ExternalIdMapper>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ExternalIdMapperCacheModel)) {
			return false;
		}

		ExternalIdMapperCacheModel externalIdMapperCacheModel =
			(ExternalIdMapperCacheModel)object;

		if (externalIdMapperId ==
				externalIdMapperCacheModel.externalIdMapperId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, externalIdMapperId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{externalIdMapperId=");
		sb.append(externalIdMapperId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", externalId=");
		sb.append(externalId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ExternalIdMapper toEntityModel() {
		ExternalIdMapperImpl externalIdMapperImpl = new ExternalIdMapperImpl();

		externalIdMapperImpl.setExternalIdMapperId(externalIdMapperId);

		if (createDate == Long.MIN_VALUE) {
			externalIdMapperImpl.setCreateDate(null);
		}
		else {
			externalIdMapperImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			externalIdMapperImpl.setModifiedDate(null);
		}
		else {
			externalIdMapperImpl.setModifiedDate(new Date(modifiedDate));
		}

		externalIdMapperImpl.setClassNameId(classNameId);
		externalIdMapperImpl.setClassPK(classPK);
		externalIdMapperImpl.setType(type);

		if (externalId == null) {
			externalIdMapperImpl.setExternalId("");
		}
		else {
			externalIdMapperImpl.setExternalId(externalId);
		}

		externalIdMapperImpl.resetOriginalValues();

		return externalIdMapperImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		externalIdMapperId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		type = objectInput.readInt();
		externalId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(externalIdMapperId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeInt(type);

		if (externalId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalId);
		}
	}

	public long externalIdMapperId;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public int type;
	public String externalId;

}
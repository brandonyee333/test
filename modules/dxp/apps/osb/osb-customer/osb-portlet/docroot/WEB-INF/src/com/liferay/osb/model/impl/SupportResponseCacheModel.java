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

import com.liferay.osb.model.SupportResponse;

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
 * The cache model class for representing SupportResponse in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SupportResponse
 * @generated
 */
@ProviderType
public class SupportResponseCacheModel implements CacheModel<SupportResponse>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportResponseCacheModel)) {
			return false;
		}

		SupportResponseCacheModel supportResponseCacheModel = (SupportResponseCacheModel)obj;

		if (supportResponseId == supportResponseCacheModel.supportResponseId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, supportResponseId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{supportResponseId=");
		sb.append(supportResponseId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", supportLevel=");
		sb.append(supportLevel);
		sb.append(", severity1Response=");
		sb.append(severity1Response);
		sb.append(", severity1Resolution=");
		sb.append(severity1Resolution);
		sb.append(", severity2Response=");
		sb.append(severity2Response);
		sb.append(", severity2Resolution=");
		sb.append(severity2Resolution);
		sb.append(", severity3Response=");
		sb.append(severity3Response);
		sb.append(", severity3Resolution=");
		sb.append(severity3Resolution);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SupportResponse toEntityModel() {
		SupportResponseImpl supportResponseImpl = new SupportResponseImpl();

		supportResponseImpl.setSupportResponseId(supportResponseId);
		supportResponseImpl.setUserId(userId);

		if (userName == null) {
			supportResponseImpl.setUserName(StringPool.BLANK);
		}
		else {
			supportResponseImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			supportResponseImpl.setCreateDate(null);
		}
		else {
			supportResponseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			supportResponseImpl.setModifiedDate(null);
		}
		else {
			supportResponseImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			supportResponseImpl.setName(StringPool.BLANK);
		}
		else {
			supportResponseImpl.setName(name);
		}

		supportResponseImpl.setSupportLevel(supportLevel);
		supportResponseImpl.setSeverity1Response(severity1Response);
		supportResponseImpl.setSeverity1Resolution(severity1Resolution);
		supportResponseImpl.setSeverity2Response(severity2Response);
		supportResponseImpl.setSeverity2Resolution(severity2Resolution);
		supportResponseImpl.setSeverity3Response(severity3Response);
		supportResponseImpl.setSeverity3Resolution(severity3Resolution);

		supportResponseImpl.resetOriginalValues();

		return supportResponseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		supportResponseId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();

		supportLevel = objectInput.readInt();

		severity1Response = objectInput.readInt();

		severity1Resolution = objectInput.readInt();

		severity2Response = objectInput.readInt();

		severity2Resolution = objectInput.readInt();

		severity3Response = objectInput.readInt();

		severity3Resolution = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(supportResponseId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeInt(supportLevel);

		objectOutput.writeInt(severity1Response);

		objectOutput.writeInt(severity1Resolution);

		objectOutput.writeInt(severity2Response);

		objectOutput.writeInt(severity2Resolution);

		objectOutput.writeInt(severity3Response);

		objectOutput.writeInt(severity3Resolution);
	}

	public long supportResponseId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public int supportLevel;
	public int severity1Response;
	public int severity1Resolution;
	public int severity2Response;
	public int severity2Resolution;
	public int severity3Response;
	public int severity3Resolution;
}
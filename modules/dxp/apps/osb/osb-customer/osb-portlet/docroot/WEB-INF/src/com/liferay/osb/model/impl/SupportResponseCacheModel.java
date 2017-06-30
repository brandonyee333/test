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

import com.liferay.osb.model.SupportResponse;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing SupportResponse in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SupportResponse
 * @generated
 */
public class SupportResponseCacheModel implements CacheModel<SupportResponse>,
	Serializable {
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
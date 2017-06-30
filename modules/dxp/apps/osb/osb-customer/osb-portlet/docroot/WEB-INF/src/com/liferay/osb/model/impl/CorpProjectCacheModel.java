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

import com.liferay.osb.model.CorpProject;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing CorpProject in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CorpProject
 * @generated
 */
public class CorpProjectCacheModel implements CacheModel<CorpProject>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{corpProjectId=");
		sb.append(corpProjectId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", dossieraProjectKey=");
		sb.append(dossieraProjectKey);
		sb.append(", salesforceProjectKey=");
		sb.append(salesforceProjectKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", organizationId=");
		sb.append(organizationId);
		sb.append("}");

		return sb.toString();
	}

	public CorpProject toEntityModel() {
		CorpProjectImpl corpProjectImpl = new CorpProjectImpl();

		corpProjectImpl.setCorpProjectId(corpProjectId);
		corpProjectImpl.setUserId(userId);

		if (userName == null) {
			corpProjectImpl.setUserName(StringPool.BLANK);
		}
		else {
			corpProjectImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			corpProjectImpl.setCreateDate(null);
		}
		else {
			corpProjectImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			corpProjectImpl.setModifiedDate(null);
		}
		else {
			corpProjectImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (dossieraProjectKey == null) {
			corpProjectImpl.setDossieraProjectKey(StringPool.BLANK);
		}
		else {
			corpProjectImpl.setDossieraProjectKey(dossieraProjectKey);
		}

		if (salesforceProjectKey == null) {
			corpProjectImpl.setSalesforceProjectKey(StringPool.BLANK);
		}
		else {
			corpProjectImpl.setSalesforceProjectKey(salesforceProjectKey);
		}

		if (name == null) {
			corpProjectImpl.setName(StringPool.BLANK);
		}
		else {
			corpProjectImpl.setName(name);
		}

		corpProjectImpl.setOrganizationId(organizationId);

		corpProjectImpl.resetOriginalValues();

		return corpProjectImpl;
	}

	public long corpProjectId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String dossieraProjectKey;
	public String salesforceProjectKey;
	public String name;
	public long organizationId;
}
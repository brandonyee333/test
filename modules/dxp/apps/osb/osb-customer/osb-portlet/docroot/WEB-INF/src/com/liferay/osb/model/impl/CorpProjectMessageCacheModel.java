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

import com.liferay.osb.model.CorpProjectMessage;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing CorpProjectMessage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessage
 * @generated
 */
public class CorpProjectMessageCacheModel implements CacheModel<CorpProjectMessage>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{corpProjectMessageId=");
		sb.append(corpProjectMessageId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", corpProjectId=");
		sb.append(corpProjectId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", severityLevel=");
		sb.append(severityLevel);
		sb.append(", title=");
		sb.append(title);
		sb.append(", content=");
		sb.append(content);
		sb.append(", displayCP=");
		sb.append(displayCP);
		sb.append(", displayLCS=");
		sb.append(displayLCS);
		sb.append(", displayLESA=");
		sb.append(displayLESA);
		sb.append("}");

		return sb.toString();
	}

	public CorpProjectMessage toEntityModel() {
		CorpProjectMessageImpl corpProjectMessageImpl = new CorpProjectMessageImpl();

		corpProjectMessageImpl.setCorpProjectMessageId(corpProjectMessageId);
		corpProjectMessageImpl.setUserId(userId);

		if (userName == null) {
			corpProjectMessageImpl.setUserName(StringPool.BLANK);
		}
		else {
			corpProjectMessageImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			corpProjectMessageImpl.setCreateDate(null);
		}
		else {
			corpProjectMessageImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			corpProjectMessageImpl.setModifiedDate(null);
		}
		else {
			corpProjectMessageImpl.setModifiedDate(new Date(modifiedDate));
		}

		corpProjectMessageImpl.setCorpProjectId(corpProjectId);
		corpProjectMessageImpl.setType(type);
		corpProjectMessageImpl.setSeverityLevel(severityLevel);

		if (title == null) {
			corpProjectMessageImpl.setTitle(StringPool.BLANK);
		}
		else {
			corpProjectMessageImpl.setTitle(title);
		}

		if (content == null) {
			corpProjectMessageImpl.setContent(StringPool.BLANK);
		}
		else {
			corpProjectMessageImpl.setContent(content);
		}

		corpProjectMessageImpl.setDisplayCP(displayCP);
		corpProjectMessageImpl.setDisplayLCS(displayLCS);
		corpProjectMessageImpl.setDisplayLESA(displayLESA);

		corpProjectMessageImpl.resetOriginalValues();

		return corpProjectMessageImpl;
	}

	public long corpProjectMessageId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long corpProjectId;
	public int type;
	public int severityLevel;
	public String title;
	public String content;
	public boolean displayCP;
	public boolean displayLCS;
	public boolean displayLESA;
}
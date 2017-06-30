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

import com.liferay.osb.model.SecurityPatch;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing SecurityPatch in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SecurityPatch
 * @generated
 */
public class SecurityPatchCacheModel implements CacheModel<SecurityPatch>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{securityPatchId=");
		sb.append(securityPatchId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", ticketAttachmentId=");
		sb.append(ticketAttachmentId);
		sb.append(", portletId=");
		sb.append(portletId);
		sb.append(", envLFR=");
		sb.append(envLFR);
		sb.append(", name=");
		sb.append(name);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append("}");

		return sb.toString();
	}

	public SecurityPatch toEntityModel() {
		SecurityPatchImpl securityPatchImpl = new SecurityPatchImpl();

		securityPatchImpl.setSecurityPatchId(securityPatchId);
		securityPatchImpl.setUserId(userId);

		if (userName == null) {
			securityPatchImpl.setUserName(StringPool.BLANK);
		}
		else {
			securityPatchImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			securityPatchImpl.setCreateDate(null);
		}
		else {
			securityPatchImpl.setCreateDate(new Date(createDate));
		}

		securityPatchImpl.setAccountEntryId(accountEntryId);
		securityPatchImpl.setTicketAttachmentId(ticketAttachmentId);

		if (portletId == null) {
			securityPatchImpl.setPortletId(StringPool.BLANK);
		}
		else {
			securityPatchImpl.setPortletId(portletId);
		}

		securityPatchImpl.setEnvLFR(envLFR);

		if (name == null) {
			securityPatchImpl.setName(StringPool.BLANK);
		}
		else {
			securityPatchImpl.setName(name);
		}

		if (fileName == null) {
			securityPatchImpl.setFileName(StringPool.BLANK);
		}
		else {
			securityPatchImpl.setFileName(fileName);
		}

		securityPatchImpl.resetOriginalValues();

		return securityPatchImpl;
	}

	public long securityPatchId;
	public long userId;
	public String userName;
	public long createDate;
	public long accountEntryId;
	public long ticketAttachmentId;
	public String portletId;
	public int envLFR;
	public String name;
	public String fileName;
}
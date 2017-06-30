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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.service.base.AccountEnvironmentAttachmentServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEnvironmentPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

/**
 * @author Lin Cui
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountEnvironmentAttachmentServiceImpl
	extends AccountEnvironmentAttachmentServiceBaseImpl {

	public AccountEnvironmentAttachment getAccountEnvironmentAttachment(
			long accountEnvironmentAttachmentId)
		throws PortalException, SystemException {

		AccountEnvironmentAttachment accountEnvironmentAttachment =
			accountEnvironmentAttachmentLocalService.
				getAccountEnvironmentAttachment(accountEnvironmentAttachmentId);

		AccountEnvironment accountEnvironment =
			accountEnvironmentLocalService.getAccountEnvironment(
				accountEnvironmentAttachment.getAccountEnvironmentId());

		OSBAccountEnvironmentPermission.check(
			getPermissionChecker(), accountEnvironment.getAccountEntryId(),
			OSBActionKeys.VIEW);

		return
			accountEnvironmentAttachmentLocalService.
				getAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

}
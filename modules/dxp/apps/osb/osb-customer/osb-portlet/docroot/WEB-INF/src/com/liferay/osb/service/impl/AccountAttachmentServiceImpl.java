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

import com.liferay.osb.model.AccountAttachment;
import com.liferay.osb.service.base.AccountAttachmentServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.security.auth.PrincipalException;

import java.io.File;

import java.util.List;

/**
 * @author Brent Krone-Schmidt
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountAttachmentServiceImpl
	extends AccountAttachmentServiceBaseImpl {

	public List<AccountAttachment> addAccountAttachments(
			long accountEntryId, long accountProjectId,
			List<ObjectValuePair<String, File>> files, List<Integer> types)
		throws PortalException, SystemException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);

		return accountAttachmentLocalService.addAccountAttachments(
			getUserId(), accountEntryId, accountProjectId, files, types);
	}

	public AccountAttachment deleteAccountAttachment(long accountAttachmentId)
		throws PortalException, SystemException {

		AccountAttachment accountAttachment =
			accountAttachmentLocalService.getAccountAttachment(
				accountAttachmentId);

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountAttachment.getAccountEntryId(),
			OSBActionKeys.UPDATE_ACCOUNT_INFO);

		return accountAttachmentLocalService.deleteAccountAttachment(
			accountAttachmentId);
	}

	public AccountAttachment getAccountAttachment(long accountAttachmentId)
		throws PortalException, SystemException {

		checkPermissions();

		return accountAttachmentLocalService.getAccountAttachment(
			accountAttachmentId);
	}

	public List<AccountAttachment> getAccountAttachments(
			long accountEntryId, long accountProjectId, int type)
		throws PortalException, SystemException {

		checkPermissions();

		return accountAttachmentLocalService.getAccountAttachments(
			accountEntryId, accountProjectId, type);
	}

	protected void checkPermissions() throws PortalException, SystemException {
		if (!organizationLocalService.hasUserOrganization(
				getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			throw new PrincipalException();
		}
	}

}
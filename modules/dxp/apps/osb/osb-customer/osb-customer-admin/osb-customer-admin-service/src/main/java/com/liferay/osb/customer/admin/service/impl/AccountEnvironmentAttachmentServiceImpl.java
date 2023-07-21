/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service.impl;

import com.liferay.osb.customer.admin.model.AccountEnvironment;
import com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment;
import com.liferay.osb.customer.admin.service.base.AccountEnvironmentAttachmentServiceBaseImpl;
import com.liferay.osb.customer.admin.service.permission.AccountEnvironmentPermission;
import com.liferay.osb.customer.constants.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
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
		throws PortalException {

		AccountEnvironmentAttachment accountEnvironmentAttachment =
			accountEnvironmentAttachmentLocalService.
				getAccountEnvironmentAttachment(accountEnvironmentAttachmentId);

		AccountEnvironment accountEnvironment =
			accountEnvironmentLocalService.getAccountEnvironment(
				accountEnvironmentAttachment.getAccountEnvironmentId());

		AccountEnvironmentPermission.check(
			getPermissionChecker(), accountEnvironment.getAccountEntryId(),
			OSBActionKeys.VIEW);

		return accountEnvironmentAttachmentLocalService.
			getAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

}
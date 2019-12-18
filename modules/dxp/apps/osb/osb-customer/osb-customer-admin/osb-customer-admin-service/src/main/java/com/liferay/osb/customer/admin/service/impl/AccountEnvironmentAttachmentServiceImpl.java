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
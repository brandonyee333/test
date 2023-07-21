/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountEnvironmentAttachmentService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentService
 * @generated
 */
public class AccountEnvironmentAttachmentServiceWrapper
	implements AccountEnvironmentAttachmentService,
			   ServiceWrapper<AccountEnvironmentAttachmentService> {

	public AccountEnvironmentAttachmentServiceWrapper(
		AccountEnvironmentAttachmentService
			accountEnvironmentAttachmentService) {

		_accountEnvironmentAttachmentService =
			accountEnvironmentAttachmentService;
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
			getAccountEnvironmentAttachment(long accountEnvironmentAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEnvironmentAttachmentService.
			getAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountEnvironmentAttachmentService.getOSGiServiceIdentifier();
	}

	@Override
	public AccountEnvironmentAttachmentService getWrappedService() {
		return _accountEnvironmentAttachmentService;
	}

	@Override
	public void setWrappedService(
		AccountEnvironmentAttachmentService
			accountEnvironmentAttachmentService) {

		_accountEnvironmentAttachmentService =
			accountEnvironmentAttachmentService;
	}

	private AccountEnvironmentAttachmentService
		_accountEnvironmentAttachmentService;

}
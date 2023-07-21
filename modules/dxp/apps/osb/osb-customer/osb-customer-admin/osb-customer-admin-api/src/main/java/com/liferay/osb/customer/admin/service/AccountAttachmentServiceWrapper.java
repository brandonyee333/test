/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountAttachmentService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountAttachmentService
 * @generated
 */
public class AccountAttachmentServiceWrapper
	implements AccountAttachmentService,
			   ServiceWrapper<AccountAttachmentService> {

	public AccountAttachmentServiceWrapper(
		AccountAttachmentService accountAttachmentService) {

		_accountAttachmentService = accountAttachmentService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountAttachmentService.getOSGiServiceIdentifier();
	}

	@Override
	public AccountAttachmentService getWrappedService() {
		return _accountAttachmentService;
	}

	@Override
	public void setWrappedService(
		AccountAttachmentService accountAttachmentService) {

		_accountAttachmentService = accountAttachmentService;
	}

	private AccountAttachmentService _accountAttachmentService;

}
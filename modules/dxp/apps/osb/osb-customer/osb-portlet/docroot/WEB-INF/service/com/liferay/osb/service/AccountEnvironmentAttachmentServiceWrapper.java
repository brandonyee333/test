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

package com.liferay.osb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AccountEnvironmentAttachmentService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountEnvironmentAttachmentService
 * @generated
 */
public class AccountEnvironmentAttachmentServiceWrapper
	implements AccountEnvironmentAttachmentService,
		ServiceWrapper<AccountEnvironmentAttachmentService> {
	public AccountEnvironmentAttachmentServiceWrapper(
		AccountEnvironmentAttachmentService accountEnvironmentAttachmentService) {
		_accountEnvironmentAttachmentService = accountEnvironmentAttachmentService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountEnvironmentAttachmentService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountEnvironmentAttachmentService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountEnvironmentAttachmentService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.AccountEnvironmentAttachment getAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentService.getAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountEnvironmentAttachmentService getWrappedAccountEnvironmentAttachmentService() {
		return _accountEnvironmentAttachmentService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountEnvironmentAttachmentService(
		AccountEnvironmentAttachmentService accountEnvironmentAttachmentService) {
		_accountEnvironmentAttachmentService = accountEnvironmentAttachmentService;
	}

	public AccountEnvironmentAttachmentService getWrappedService() {
		return _accountEnvironmentAttachmentService;
	}

	public void setWrappedService(
		AccountEnvironmentAttachmentService accountEnvironmentAttachmentService) {
		_accountEnvironmentAttachmentService = accountEnvironmentAttachmentService;
	}

	private AccountEnvironmentAttachmentService _accountEnvironmentAttachmentService;
}
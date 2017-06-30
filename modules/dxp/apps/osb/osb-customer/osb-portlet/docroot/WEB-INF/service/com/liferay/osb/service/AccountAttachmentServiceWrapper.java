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
 * This class is a wrapper for {@link AccountAttachmentService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountAttachmentService
 * @generated
 */
public class AccountAttachmentServiceWrapper implements AccountAttachmentService,
	ServiceWrapper<AccountAttachmentService> {
	public AccountAttachmentServiceWrapper(
		AccountAttachmentService accountAttachmentService) {
		_accountAttachmentService = accountAttachmentService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountAttachmentService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountAttachmentService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountAttachmentService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public java.util.List<com.liferay.osb.model.AccountAttachment> addAccountAttachments(
		long accountEntryId, long accountProjectId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentService.addAccountAttachments(accountEntryId,
			accountProjectId, files, types);
	}

	public com.liferay.osb.model.AccountAttachment deleteAccountAttachment(
		long accountAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentService.deleteAccountAttachment(accountAttachmentId);
	}

	public com.liferay.osb.model.AccountAttachment getAccountAttachment(
		long accountAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentService.getAccountAttachment(accountAttachmentId);
	}

	public java.util.List<com.liferay.osb.model.AccountAttachment> getAccountAttachments(
		long accountEntryId, long accountProjectId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentService.getAccountAttachments(accountEntryId,
			accountProjectId, type);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountAttachmentService getWrappedAccountAttachmentService() {
		return _accountAttachmentService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountAttachmentService(
		AccountAttachmentService accountAttachmentService) {
		_accountAttachmentService = accountAttachmentService;
	}

	public AccountAttachmentService getWrappedService() {
		return _accountAttachmentService;
	}

	public void setWrappedService(
		AccountAttachmentService accountAttachmentService) {
		_accountAttachmentService = accountAttachmentService;
	}

	private AccountAttachmentService _accountAttachmentService;
}
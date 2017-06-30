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
 * This class is a wrapper for {@link AccountEnvironmentAttachmentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountEnvironmentAttachmentLocalService
 * @generated
 */
public class AccountEnvironmentAttachmentLocalServiceWrapper
	implements AccountEnvironmentAttachmentLocalService,
		ServiceWrapper<AccountEnvironmentAttachmentLocalService> {
	public AccountEnvironmentAttachmentLocalServiceWrapper(
		AccountEnvironmentAttachmentLocalService accountEnvironmentAttachmentLocalService) {
		_accountEnvironmentAttachmentLocalService = accountEnvironmentAttachmentLocalService;
	}

	/**
	* Adds the account environment attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachment the account environment attachment
	* @return the account environment attachment that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment addAccountEnvironmentAttachment(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.addAccountEnvironmentAttachment(accountEnvironmentAttachment);
	}

	/**
	* Creates a new account environment attachment with the primary key. Does not add the account environment attachment to the database.
	*
	* @param accountEnvironmentAttachmentId the primary key for the new account environment attachment
	* @return the new account environment attachment
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment createAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId) {
		return _accountEnvironmentAttachmentLocalService.createAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

	/**
	* Deletes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	* @return the account environment attachment that was removed
	* @throws PortalException if a account environment attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment deleteAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.deleteAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

	/**
	* Deletes the account environment attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachment the account environment attachment
	* @return the account environment attachment that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment deleteAccountEnvironmentAttachment(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.deleteAccountEnvironmentAttachment(accountEnvironmentAttachment);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountEnvironmentAttachmentLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AccountEnvironmentAttachment fetchAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.fetchAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

	/**
	* Returns the account environment attachment with the primary key.
	*
	* @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	* @return the account environment attachment
	* @throws PortalException if a account environment attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment getAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.getAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the account environment attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @return the range of account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironmentAttachment> getAccountEnvironmentAttachments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.getAccountEnvironmentAttachments(start,
			end);
	}

	/**
	* Returns the number of account environment attachments.
	*
	* @return the number of account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountEnvironmentAttachmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.getAccountEnvironmentAttachmentsCount();
	}

	/**
	* Updates the account environment attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachment the account environment attachment
	* @return the account environment attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment updateAccountEnvironmentAttachment(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.updateAccountEnvironmentAttachment(accountEnvironmentAttachment);
	}

	/**
	* Updates the account environment attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachment the account environment attachment
	* @param merge whether to merge the account environment attachment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account environment attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment updateAccountEnvironmentAttachment(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.updateAccountEnvironmentAttachment(accountEnvironmentAttachment,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountEnvironmentAttachmentLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountEnvironmentAttachmentLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountEnvironmentAttachmentLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.AccountEnvironmentAttachment addAccountEnvironmentAttachment(
		long userId, long accountEnvironmentId,
		com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File> fileOVP,
		int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.addAccountEnvironmentAttachment(userId,
			accountEnvironmentId, fileOVP, type);
	}

	public void addAccountEnvironmentAttachments(long userId,
		long accountEnvironmentId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountEnvironmentAttachmentLocalService.addAccountEnvironmentAttachments(userId,
			accountEnvironmentId, files, types);
	}

	public com.liferay.osb.model.AccountEnvironmentAttachment fetchAccountEnvironmentAttachment(
		long accountEnvironmentId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.fetchAccountEnvironmentAttachment(accountEnvironmentId,
			type);
	}

	public java.util.List<com.liferay.osb.model.AccountEnvironmentAttachment> getAccountEnvironmentAttachments(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.getAccountEnvironmentAttachments(accountEnvironmentId);
	}

	public java.io.File getFile(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.getFile(accountEnvironmentAttachment);
	}

	public java.io.InputStream getFileAsStream(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.getFileAsStream(accountEnvironmentAttachment);
	}

	public com.liferay.osb.model.AccountEnvironmentAttachment updateAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId, long accountEnvironmentId,
		com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File> fileOVP,
		int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachmentLocalService.updateAccountEnvironmentAttachment(accountEnvironmentAttachmentId,
			accountEnvironmentId, fileOVP, type);
	}

	public void updateAccountEnvironmentAttachments(long userId,
		long accountEnvironmentId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountEnvironmentAttachmentLocalService.updateAccountEnvironmentAttachments(userId,
			accountEnvironmentId, files, types);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountEnvironmentAttachmentLocalService getWrappedAccountEnvironmentAttachmentLocalService() {
		return _accountEnvironmentAttachmentLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountEnvironmentAttachmentLocalService(
		AccountEnvironmentAttachmentLocalService accountEnvironmentAttachmentLocalService) {
		_accountEnvironmentAttachmentLocalService = accountEnvironmentAttachmentLocalService;
	}

	public AccountEnvironmentAttachmentLocalService getWrappedService() {
		return _accountEnvironmentAttachmentLocalService;
	}

	public void setWrappedService(
		AccountEnvironmentAttachmentLocalService accountEnvironmentAttachmentLocalService) {
		_accountEnvironmentAttachmentLocalService = accountEnvironmentAttachmentLocalService;
	}

	private AccountEnvironmentAttachmentLocalService _accountEnvironmentAttachmentLocalService;
}
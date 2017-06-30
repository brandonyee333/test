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
 * This class is a wrapper for {@link AccountAttachmentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountAttachmentLocalService
 * @generated
 */
public class AccountAttachmentLocalServiceWrapper
	implements AccountAttachmentLocalService,
		ServiceWrapper<AccountAttachmentLocalService> {
	public AccountAttachmentLocalServiceWrapper(
		AccountAttachmentLocalService accountAttachmentLocalService) {
		_accountAttachmentLocalService = accountAttachmentLocalService;
	}

	/**
	* Adds the account attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param accountAttachment the account attachment
	* @return the account attachment that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountAttachment addAccountAttachment(
		com.liferay.osb.model.AccountAttachment accountAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.addAccountAttachment(accountAttachment);
	}

	/**
	* Creates a new account attachment with the primary key. Does not add the account attachment to the database.
	*
	* @param accountAttachmentId the primary key for the new account attachment
	* @return the new account attachment
	*/
	public com.liferay.osb.model.AccountAttachment createAccountAttachment(
		long accountAttachmentId) {
		return _accountAttachmentLocalService.createAccountAttachment(accountAttachmentId);
	}

	/**
	* Deletes the account attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountAttachmentId the primary key of the account attachment
	* @return the account attachment that was removed
	* @throws PortalException if a account attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountAttachment deleteAccountAttachment(
		long accountAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.deleteAccountAttachment(accountAttachmentId);
	}

	/**
	* Deletes the account attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param accountAttachment the account attachment
	* @return the account attachment that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountAttachment deleteAccountAttachment(
		com.liferay.osb.model.AccountAttachment accountAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.deleteAccountAttachment(accountAttachment);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountAttachmentLocalService.dynamicQuery();
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
		return _accountAttachmentLocalService.dynamicQuery(dynamicQuery);
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
		return _accountAttachmentLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _accountAttachmentLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _accountAttachmentLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AccountAttachment fetchAccountAttachment(
		long accountAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.fetchAccountAttachment(accountAttachmentId);
	}

	/**
	* Returns the account attachment with the primary key.
	*
	* @param accountAttachmentId the primary key of the account attachment
	* @return the account attachment
	* @throws PortalException if a account attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountAttachment getAccountAttachment(
		long accountAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.getAccountAttachment(accountAttachmentId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the account attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account attachments
	* @param end the upper bound of the range of account attachments (not inclusive)
	* @return the range of account attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountAttachment> getAccountAttachments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.getAccountAttachments(start, end);
	}

	/**
	* Returns the number of account attachments.
	*
	* @return the number of account attachments
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountAttachmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.getAccountAttachmentsCount();
	}

	/**
	* Updates the account attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountAttachment the account attachment
	* @return the account attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountAttachment updateAccountAttachment(
		com.liferay.osb.model.AccountAttachment accountAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.updateAccountAttachment(accountAttachment);
	}

	/**
	* Updates the account attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountAttachment the account attachment
	* @param merge whether to merge the account attachment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountAttachment updateAccountAttachment(
		com.liferay.osb.model.AccountAttachment accountAttachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.updateAccountAttachment(accountAttachment,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountAttachmentLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountAttachmentLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountAttachmentLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.AccountAttachment addAccountAttachment(
		long userId, long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File> fileOVP,
		int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.addAccountAttachment(userId,
			accountEntryId, accountProjectId, fileOVP, type);
	}

	public java.util.List<com.liferay.osb.model.AccountAttachment> addAccountAttachments(
		long userId, long accountEntryId, long accountProjectId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.addAccountAttachments(userId,
			accountEntryId, accountProjectId, files, types);
	}

	public void deleteAccountAttachments(long accountEntryId,
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountAttachmentLocalService.deleteAccountAttachments(accountEntryId,
			accountProjectId);
	}

	public void deleteAccountAttachments(long accountEntryId,
		long accountProjectId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountAttachmentLocalService.deleteAccountAttachments(accountEntryId,
			accountProjectId, type);
	}

	public java.util.List<com.liferay.osb.model.AccountAttachment> getAccountAttachments(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.getAccountAttachments(accountEntryId);
	}

	public java.util.List<com.liferay.osb.model.AccountAttachment> getAccountAttachments(
		long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.getAccountAttachments(accountEntryId,
			accountProjectId);
	}

	public java.util.List<com.liferay.osb.model.AccountAttachment> getAccountAttachments(
		long accountEntryId, long accountProjectId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.getAccountAttachments(accountEntryId,
			accountProjectId, type);
	}

	public java.io.InputStream getFileAsStream(
		com.liferay.osb.model.AccountAttachment accountAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachmentLocalService.getFileAsStream(accountAttachment);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountAttachmentLocalService getWrappedAccountAttachmentLocalService() {
		return _accountAttachmentLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountAttachmentLocalService(
		AccountAttachmentLocalService accountAttachmentLocalService) {
		_accountAttachmentLocalService = accountAttachmentLocalService;
	}

	public AccountAttachmentLocalService getWrappedService() {
		return _accountAttachmentLocalService;
	}

	public void setWrappedService(
		AccountAttachmentLocalService accountAttachmentLocalService) {
		_accountAttachmentLocalService = accountAttachmentLocalService;
	}

	private AccountAttachmentLocalService _accountAttachmentLocalService;
}
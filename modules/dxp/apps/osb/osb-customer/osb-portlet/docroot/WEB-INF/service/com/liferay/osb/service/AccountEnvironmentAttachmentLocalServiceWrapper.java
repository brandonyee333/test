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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountEnvironmentAttachmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironmentAttachment addAccountEnvironmentAttachment(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment) {
		return _accountEnvironmentAttachmentLocalService.addAccountEnvironmentAttachment(accountEnvironmentAttachment);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironmentAttachment addAccountEnvironmentAttachment(
		long userId, long accountEnvironmentId,
		com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File> fileOVP,
		int type) throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentAttachmentLocalService.addAccountEnvironmentAttachment(userId,
			accountEnvironmentId, fileOVP, type);
	}

	/**
	* Creates a new account environment attachment with the primary key. Does not add the account environment attachment to the database.
	*
	* @param accountEnvironmentAttachmentId the primary key for the new account environment attachment
	* @return the new account environment attachment
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironmentAttachment createAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId) {
		return _accountEnvironmentAttachmentLocalService.createAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

	/**
	* Deletes the account environment attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachment the account environment attachment
	* @return the account environment attachment that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironmentAttachment deleteAccountEnvironmentAttachment(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentAttachmentLocalService.deleteAccountEnvironmentAttachment(accountEnvironmentAttachment);
	}

	/**
	* Deletes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	* @return the account environment attachment that was removed
	* @throws PortalException if a account environment attachment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironmentAttachment deleteAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentAttachmentLocalService.deleteAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironmentAttachment fetchAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId) {
		return _accountEnvironmentAttachmentLocalService.fetchAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironmentAttachment fetchAccountEnvironmentAttachment(
		long accountEnvironmentId, int type) {
		return _accountEnvironmentAttachmentLocalService.fetchAccountEnvironmentAttachment(accountEnvironmentId,
			type);
	}

	/**
	* Returns the account environment attachment with the primary key.
	*
	* @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	* @return the account environment attachment
	* @throws PortalException if a account environment attachment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironmentAttachment getAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentAttachmentLocalService.getAccountEnvironmentAttachment(accountEnvironmentAttachmentId);
	}

	/**
	* Updates the account environment attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachment the account environment attachment
	* @return the account environment attachment that was updated
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironmentAttachment updateAccountEnvironmentAttachment(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment) {
		return _accountEnvironmentAttachmentLocalService.updateAccountEnvironmentAttachment(accountEnvironmentAttachment);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironmentAttachment updateAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId, long accountEnvironmentId,
		com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File> fileOVP,
		int type) throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentAttachmentLocalService.updateAccountEnvironmentAttachment(accountEnvironmentAttachmentId,
			accountEnvironmentId, fileOVP, type);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accountEnvironmentAttachmentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountEnvironmentAttachmentLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accountEnvironmentAttachmentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentAttachmentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentAttachmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of account environment attachments.
	*
	* @return the number of account environment attachments
	*/
	@Override
	public int getAccountEnvironmentAttachmentsCount() {
		return _accountEnvironmentAttachmentLocalService.getAccountEnvironmentAttachmentsCount();
	}

	@Override
	public java.io.File getFile(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentAttachmentLocalService.getFile(accountEnvironmentAttachment);
	}

	@Override
	public java.io.InputStream getFileAsStream(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentAttachmentLocalService.getFileAsStream(accountEnvironmentAttachment);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountEnvironmentAttachmentLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountEnvironmentAttachmentLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _accountEnvironmentAttachmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _accountEnvironmentAttachmentLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _accountEnvironmentAttachmentLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the account environment attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @return the range of account environment attachments
	*/
	@Override
	public java.util.List<com.liferay.osb.model.AccountEnvironmentAttachment> getAccountEnvironmentAttachments(
		int start, int end) {
		return _accountEnvironmentAttachmentLocalService.getAccountEnvironmentAttachments(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEnvironmentAttachment> getAccountEnvironmentAttachments(
		long accountEnvironmentId) {
		return _accountEnvironmentAttachmentLocalService.getAccountEnvironmentAttachments(accountEnvironmentId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _accountEnvironmentAttachmentLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _accountEnvironmentAttachmentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void addAccountEnvironmentAttachments(long userId,
		long accountEnvironmentId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountEnvironmentAttachmentLocalService.addAccountEnvironmentAttachments(userId,
			accountEnvironmentId, files, types);
	}

	@Override
	public void updateAccountEnvironmentAttachments(long userId,
		long accountEnvironmentId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountEnvironmentAttachmentLocalService.updateAccountEnvironmentAttachments(userId,
			accountEnvironmentId, files, types);
	}

	@Override
	public AccountEnvironmentAttachmentLocalService getWrappedService() {
		return _accountEnvironmentAttachmentLocalService;
	}

	@Override
	public void setWrappedService(
		AccountEnvironmentAttachmentLocalService accountEnvironmentAttachmentLocalService) {
		_accountEnvironmentAttachmentLocalService = accountEnvironmentAttachmentLocalService;
	}

	private AccountEnvironmentAttachmentLocalService _accountEnvironmentAttachmentLocalService;
}
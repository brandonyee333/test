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
 * Provides a wrapper for {@link AccountEntryLanguageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLanguageLocalService
 * @generated
 */
@ProviderType
public class AccountEntryLanguageLocalServiceWrapper
	implements AccountEntryLanguageLocalService,
		ServiceWrapper<AccountEntryLanguageLocalService> {
	public AccountEntryLanguageLocalServiceWrapper(
		AccountEntryLanguageLocalService accountEntryLanguageLocalService) {
		_accountEntryLanguageLocalService = accountEntryLanguageLocalService;
	}

	/**
	* Adds the account entry language to the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguage the account entry language
	* @return the account entry language that was added
	*/
	@Override
	public com.liferay.osb.model.AccountEntryLanguage addAccountEntryLanguage(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage) {
		return _accountEntryLanguageLocalService.addAccountEntryLanguage(accountEntryLanguage);
	}

	/**
	* Creates a new account entry language with the primary key. Does not add the account entry language to the database.
	*
	* @param accountEntryLanguageId the primary key for the new account entry language
	* @return the new account entry language
	*/
	@Override
	public com.liferay.osb.model.AccountEntryLanguage createAccountEntryLanguage(
		long accountEntryLanguageId) {
		return _accountEntryLanguageLocalService.createAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	* Deletes the account entry language from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguage the account entry language
	* @return the account entry language that was removed
	*/
	@Override
	public com.liferay.osb.model.AccountEntryLanguage deleteAccountEntryLanguage(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage) {
		return _accountEntryLanguageLocalService.deleteAccountEntryLanguage(accountEntryLanguage);
	}

	/**
	* Deletes the account entry language with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language that was removed
	* @throws PortalException if a account entry language with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountEntryLanguage deleteAccountEntryLanguage(
		long accountEntryLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLanguageLocalService.deleteAccountEntryLanguage(accountEntryLanguageId);
	}

	@Override
	public com.liferay.osb.model.AccountEntryLanguage fetchAccountEntryLanguage(
		long accountEntryLanguageId) {
		return _accountEntryLanguageLocalService.fetchAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	* Returns the account entry language with the primary key.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language
	* @throws PortalException if a account entry language with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountEntryLanguage getAccountEntryLanguage(
		long accountEntryLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLanguageLocalService.getAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	* Updates the account entry language in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguage the account entry language
	* @return the account entry language that was updated
	*/
	@Override
	public com.liferay.osb.model.AccountEntryLanguage updateAccountEntryLanguage(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage) {
		return _accountEntryLanguageLocalService.updateAccountEntryLanguage(accountEntryLanguage);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accountEntryLanguageLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountEntryLanguageLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accountEntryLanguageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLanguageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLanguageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of account entry languages.
	*
	* @return the number of account entry languages
	*/
	@Override
	public int getAccountEntryLanguagesCount() {
		return _accountEntryLanguageLocalService.getAccountEntryLanguagesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountEntryLanguageLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountEntryLanguageLocalService.getOSGiServiceIdentifier();
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
		return _accountEntryLanguageLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accountEntryLanguageLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accountEntryLanguageLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the account entry languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @return the range of account entry languages
	*/
	@Override
	public java.util.List<com.liferay.osb.model.AccountEntryLanguage> getAccountEntryLanguages(
		int start, int end) {
		return _accountEntryLanguageLocalService.getAccountEntryLanguages(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntryLanguage> getAccountEntryLanguages(
		long accountEntryId) {
		return _accountEntryLanguageLocalService.getAccountEntryLanguages(accountEntryId);
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
		return _accountEntryLanguageLocalService.dynamicQueryCount(dynamicQuery);
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
		return _accountEntryLanguageLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void setAccountEntryLanguageIds(long accountEntryId,
		java.lang.String[] languageIds) {
		_accountEntryLanguageLocalService.setAccountEntryLanguageIds(accountEntryId,
			languageIds);
	}

	@Override
	public AccountEntryLanguageLocalService getWrappedService() {
		return _accountEntryLanguageLocalService;
	}

	@Override
	public void setWrappedService(
		AccountEntryLanguageLocalService accountEntryLanguageLocalService) {
		_accountEntryLanguageLocalService = accountEntryLanguageLocalService;
	}

	private AccountEntryLanguageLocalService _accountEntryLanguageLocalService;
}
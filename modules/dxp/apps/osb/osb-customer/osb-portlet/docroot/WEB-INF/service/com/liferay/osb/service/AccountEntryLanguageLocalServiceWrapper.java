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
 * This class is a wrapper for {@link AccountEntryLanguageLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountEntryLanguageLocalService
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage addAccountEntryLanguage(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntryLanguageLocalService.addAccountEntryLanguage(accountEntryLanguage);
	}

	/**
	* Creates a new account entry language with the primary key. Does not add the account entry language to the database.
	*
	* @param accountEntryLanguageId the primary key for the new account entry language
	* @return the new account entry language
	*/
	public com.liferay.osb.model.AccountEntryLanguage createAccountEntryLanguage(
		long accountEntryLanguageId) {
		return _accountEntryLanguageLocalService.createAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	* Deletes the account entry language with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language that was removed
	* @throws PortalException if a account entry language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage deleteAccountEntryLanguage(
		long accountEntryLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEntryLanguageLocalService.deleteAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	* Deletes the account entry language from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguage the account entry language
	* @return the account entry language that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage deleteAccountEntryLanguage(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntryLanguageLocalService.deleteAccountEntryLanguage(accountEntryLanguage);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountEntryLanguageLocalService.dynamicQuery();
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
		return _accountEntryLanguageLocalService.dynamicQuery(dynamicQuery);
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
		return _accountEntryLanguageLocalService.dynamicQuery(dynamicQuery,
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
		return _accountEntryLanguageLocalService.dynamicQuery(dynamicQuery,
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
		return _accountEntryLanguageLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AccountEntryLanguage fetchAccountEntryLanguage(
		long accountEntryLanguageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntryLanguageLocalService.fetchAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	* Returns the account entry language with the primary key.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language
	* @throws PortalException if a account entry language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage getAccountEntryLanguage(
		long accountEntryLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEntryLanguageLocalService.getAccountEntryLanguage(accountEntryLanguageId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEntryLanguageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the account entry languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @return the range of account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEntryLanguage> getAccountEntryLanguages(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntryLanguageLocalService.getAccountEntryLanguages(start,
			end);
	}

	/**
	* Returns the number of account entry languages.
	*
	* @return the number of account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountEntryLanguagesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntryLanguageLocalService.getAccountEntryLanguagesCount();
	}

	/**
	* Updates the account entry language in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguage the account entry language
	* @return the account entry language that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage updateAccountEntryLanguage(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntryLanguageLocalService.updateAccountEntryLanguage(accountEntryLanguage);
	}

	/**
	* Updates the account entry language in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguage the account entry language
	* @param merge whether to merge the account entry language with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account entry language that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage updateAccountEntryLanguage(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntryLanguageLocalService.updateAccountEntryLanguage(accountEntryLanguage,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountEntryLanguageLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountEntryLanguageLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountEntryLanguageLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public java.util.List<com.liferay.osb.model.AccountEntryLanguage> getAccountEntryLanguages(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEntryLanguageLocalService.getAccountEntryLanguages(accountEntryId);
	}

	public void setAccountEntryLanguageIds(long accountEntryId,
		java.lang.String[] languageIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountEntryLanguageLocalService.setAccountEntryLanguageIds(accountEntryId,
			languageIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountEntryLanguageLocalService getWrappedAccountEntryLanguageLocalService() {
		return _accountEntryLanguageLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountEntryLanguageLocalService(
		AccountEntryLanguageLocalService accountEntryLanguageLocalService) {
		_accountEntryLanguageLocalService = accountEntryLanguageLocalService;
	}

	public AccountEntryLanguageLocalService getWrappedService() {
		return _accountEntryLanguageLocalService;
	}

	public void setWrappedService(
		AccountEntryLanguageLocalService accountEntryLanguageLocalService) {
		_accountEntryLanguageLocalService = accountEntryLanguageLocalService;
	}

	private AccountEntryLanguageLocalService _accountEntryLanguageLocalService;
}
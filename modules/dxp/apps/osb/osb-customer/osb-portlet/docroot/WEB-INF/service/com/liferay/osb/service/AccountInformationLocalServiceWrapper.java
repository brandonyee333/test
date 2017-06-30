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
 * This class is a wrapper for {@link AccountInformationLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountInformationLocalService
 * @generated
 */
public class AccountInformationLocalServiceWrapper
	implements AccountInformationLocalService,
		ServiceWrapper<AccountInformationLocalService> {
	public AccountInformationLocalServiceWrapper(
		AccountInformationLocalService accountInformationLocalService) {
		_accountInformationLocalService = accountInformationLocalService;
	}

	/**
	* Adds the account information to the database. Also notifies the appropriate model listeners.
	*
	* @param accountInformation the account information
	* @return the account information that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation addAccountInformation(
		com.liferay.osb.model.AccountInformation accountInformation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.addAccountInformation(accountInformation);
	}

	/**
	* Creates a new account information with the primary key. Does not add the account information to the database.
	*
	* @param accountInformationId the primary key for the new account information
	* @return the new account information
	*/
	public com.liferay.osb.model.AccountInformation createAccountInformation(
		long accountInformationId) {
		return _accountInformationLocalService.createAccountInformation(accountInformationId);
	}

	/**
	* Deletes the account information with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information that was removed
	* @throws PortalException if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation deleteAccountInformation(
		long accountInformationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.deleteAccountInformation(accountInformationId);
	}

	/**
	* Deletes the account information from the database. Also notifies the appropriate model listeners.
	*
	* @param accountInformation the account information
	* @return the account information that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation deleteAccountInformation(
		com.liferay.osb.model.AccountInformation accountInformation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.deleteAccountInformation(accountInformation);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountInformationLocalService.dynamicQuery();
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
		return _accountInformationLocalService.dynamicQuery(dynamicQuery);
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
		return _accountInformationLocalService.dynamicQuery(dynamicQuery,
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
		return _accountInformationLocalService.dynamicQuery(dynamicQuery,
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
		return _accountInformationLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AccountInformation fetchAccountInformation(
		long accountInformationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.fetchAccountInformation(accountInformationId);
	}

	/**
	* Returns the account information with the primary key.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information
	* @throws PortalException if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation getAccountInformation(
		long accountInformationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.getAccountInformation(accountInformationId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of account informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountInformation> getAccountInformations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.getAccountInformations(start, end);
	}

	/**
	* Returns the number of account informations.
	*
	* @return the number of account informations
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountInformationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.getAccountInformationsCount();
	}

	/**
	* Updates the account information in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountInformation the account information
	* @return the account information that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation updateAccountInformation(
		com.liferay.osb.model.AccountInformation accountInformation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.updateAccountInformation(accountInformation);
	}

	/**
	* Updates the account information in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountInformation the account information
	* @param merge whether to merge the account information with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account information that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountInformation updateAccountInformation(
		com.liferay.osb.model.AccountInformation accountInformation,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.updateAccountInformation(accountInformation,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountInformationLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountInformationLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountInformationLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public void deleteAccountInformation(long accountEntryId,
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountInformationLocalService.deleteAccountInformation(accountEntryId,
			accountProjectId);
	}

	public java.util.List<com.liferay.osb.model.AccountInformation> getAccountEntryAccountInformation(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.getAccountEntryAccountInformation(accountEntryId);
	}

	public java.util.List<com.liferay.osb.model.AccountInformation> getAccountInformation(
		long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.getAccountInformation(accountEntryId,
			accountProjectId);
	}

	public com.liferay.osb.model.AccountInformationDisplay getAccountInformationDisplay(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.getAccountInformationDisplay(accountEntryId);
	}

	public java.util.List<com.liferay.osb.model.AccountInformation> updateAccountInformation(
		long userId, long accountEntryId, long accountProjectId,
		java.util.Map<java.lang.Integer, java.lang.String> data)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountInformationLocalService.updateAccountInformation(userId,
			accountEntryId, accountProjectId, data);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountInformationLocalService getWrappedAccountInformationLocalService() {
		return _accountInformationLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountInformationLocalService(
		AccountInformationLocalService accountInformationLocalService) {
		_accountInformationLocalService = accountInformationLocalService;
	}

	public AccountInformationLocalService getWrappedService() {
		return _accountInformationLocalService;
	}

	public void setWrappedService(
		AccountInformationLocalService accountInformationLocalService) {
		_accountInformationLocalService = accountInformationLocalService;
	}

	private AccountInformationLocalService _accountInformationLocalService;
}
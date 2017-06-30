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
 * This class is a wrapper for {@link AccountCustomerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountCustomerLocalService
 * @generated
 */
public class AccountCustomerLocalServiceWrapper
	implements AccountCustomerLocalService,
		ServiceWrapper<AccountCustomerLocalService> {
	public AccountCustomerLocalServiceWrapper(
		AccountCustomerLocalService accountCustomerLocalService) {
		_accountCustomerLocalService = accountCustomerLocalService;
	}

	/**
	* Adds the account customer to the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomer the account customer
	* @return the account customer that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer addAccountCustomer(
		com.liferay.osb.model.AccountCustomer accountCustomer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.addAccountCustomer(accountCustomer);
	}

	/**
	* Creates a new account customer with the primary key. Does not add the account customer to the database.
	*
	* @param accountCustomerId the primary key for the new account customer
	* @return the new account customer
	*/
	public com.liferay.osb.model.AccountCustomer createAccountCustomer(
		long accountCustomerId) {
		return _accountCustomerLocalService.createAccountCustomer(accountCustomerId);
	}

	/**
	* Deletes the account customer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer that was removed
	* @throws PortalException if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer deleteAccountCustomer(
		long accountCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.deleteAccountCustomer(accountCustomerId);
	}

	/**
	* Deletes the account customer from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomer the account customer
	* @return the account customer that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer deleteAccountCustomer(
		com.liferay.osb.model.AccountCustomer accountCustomer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.deleteAccountCustomer(accountCustomer);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountCustomerLocalService.dynamicQuery();
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
		return _accountCustomerLocalService.dynamicQuery(dynamicQuery);
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
		return _accountCustomerLocalService.dynamicQuery(dynamicQuery, start,
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
		return _accountCustomerLocalService.dynamicQuery(dynamicQuery, start,
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
		return _accountCustomerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AccountCustomer fetchAccountCustomer(
		long accountCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.fetchAccountCustomer(accountCustomerId);
	}

	/**
	* Returns the account customer with the primary key.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer
	* @throws PortalException if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer getAccountCustomer(
		long accountCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.getAccountCustomer(accountCustomerId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the account customers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @return the range of account customers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCustomer> getAccountCustomers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.getAccountCustomers(start, end);
	}

	/**
	* Returns the number of account customers.
	*
	* @return the number of account customers
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountCustomersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.getAccountCustomersCount();
	}

	/**
	* Updates the account customer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountCustomer the account customer
	* @return the account customer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer updateAccountCustomer(
		com.liferay.osb.model.AccountCustomer accountCustomer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.updateAccountCustomer(accountCustomer);
	}

	/**
	* Updates the account customer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountCustomer the account customer
	* @param merge whether to merge the account customer with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account customer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer updateAccountCustomer(
		com.liferay.osb.model.AccountCustomer accountCustomer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.updateAccountCustomer(accountCustomer,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountCustomerLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountCustomerLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountCustomerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void addAccountCustomers(long userId, long[] userIds,
		long accountEntryId, int[] roles, int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountCustomerLocalService.addAccountCustomers(userId, userIds,
			accountEntryId, roles, notifications);
	}

	public com.liferay.osb.model.AccountCustomer deleteAccountCustomer(
		long userId, com.liferay.osb.model.AccountCustomer accountCustomer)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.deleteAccountCustomer(userId,
			accountCustomer);
	}

	public void deleteAccountCustomers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountCustomerLocalService.deleteAccountCustomers(userId);
	}

	public void deleteAccountCustomers(long userId, long[] userIds,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountCustomerLocalService.deleteAccountCustomers(userId, userIds,
			accountEntryId);
	}

	public void deleteAccountEntryAccountCustomers(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountCustomerLocalService.deleteAccountEntryAccountCustomers(accountEntryId);
	}

	public com.liferay.osb.model.AccountCustomer fetchAccountCustomer(
		long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.fetchAccountCustomer(userId,
			accountEntryId);
	}

	public com.liferay.osb.model.AccountCustomer getAccountCustomer(
		long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.getAccountCustomer(userId,
			accountEntryId);
	}

	public java.util.List<com.liferay.osb.model.AccountCustomer> getAccountCustomers(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.getAccountCustomers(accountEntryId);
	}

	public java.util.List<com.liferay.osb.model.AccountCustomer> getAccountCustomers(
		long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.getAccountCustomers(accountEntryId,
			role);
	}

	public java.util.List<com.liferay.osb.model.AccountCustomer> getUserAccountCustomers(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.getUserAccountCustomers(userId);
	}

	public java.util.List<com.liferay.osb.model.AccountCustomer> getUserAccountCustomers(
		long userId, int[] roles)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.getUserAccountCustomers(userId,
			roles);
	}

	public boolean hasAccountCustomer(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomerLocalService.hasAccountCustomer(userId,
			accountEntryId);
	}

	public void toggleNotifications(long accountCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_accountCustomerLocalService.toggleNotifications(accountCustomerId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountCustomerLocalService getWrappedAccountCustomerLocalService() {
		return _accountCustomerLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountCustomerLocalService(
		AccountCustomerLocalService accountCustomerLocalService) {
		_accountCustomerLocalService = accountCustomerLocalService;
	}

	public AccountCustomerLocalService getWrappedService() {
		return _accountCustomerLocalService;
	}

	public void setWrappedService(
		AccountCustomerLocalService accountCustomerLocalService) {
		_accountCustomerLocalService = accountCustomerLocalService;
	}

	private AccountCustomerLocalService _accountCustomerLocalService;
}
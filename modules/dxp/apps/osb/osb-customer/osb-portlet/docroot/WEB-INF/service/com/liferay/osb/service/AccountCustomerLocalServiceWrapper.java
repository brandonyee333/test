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
 * Provides a wrapper for {@link AccountCustomerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomerLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.osb.model.AccountCustomer addAccountCustomer(
		com.liferay.osb.model.AccountCustomer accountCustomer) {
		return _accountCustomerLocalService.addAccountCustomer(accountCustomer);
	}

	@Override
	public com.liferay.osb.model.AccountCustomer addAccountCustomer(
		long userId, long customerUserId, long accountEntryId, int role,
		int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCustomerLocalService.addAccountCustomer(userId,
			customerUserId, accountEntryId, role, notifications);
	}

	@Override
	public com.liferay.osb.model.AccountCustomer addAccountCustomer(
		long userId, java.lang.String emailAddress, long accountEntryId,
		int role, int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCustomerLocalService.addAccountCustomer(userId,
			emailAddress, accountEntryId, role, notifications);
	}

	/**
	* Creates a new account customer with the primary key. Does not add the account customer to the database.
	*
	* @param accountCustomerId the primary key for the new account customer
	* @return the new account customer
	*/
	@Override
	public com.liferay.osb.model.AccountCustomer createAccountCustomer(
		long accountCustomerId) {
		return _accountCustomerLocalService.createAccountCustomer(accountCustomerId);
	}

	/**
	* Deletes the account customer from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomer the account customer
	* @return the account customer that was removed
	*/
	@Override
	public com.liferay.osb.model.AccountCustomer deleteAccountCustomer(
		com.liferay.osb.model.AccountCustomer accountCustomer) {
		return _accountCustomerLocalService.deleteAccountCustomer(accountCustomer);
	}

	/**
	* Deletes the account customer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer that was removed
	* @throws PortalException if a account customer with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountCustomer deleteAccountCustomer(
		long accountCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCustomerLocalService.deleteAccountCustomer(accountCustomerId);
	}

	@Override
	public com.liferay.osb.model.AccountCustomer deleteAccountCustomer(
		long userId, com.liferay.osb.model.AccountCustomer accountCustomer)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCustomerLocalService.deleteAccountCustomer(userId,
			accountCustomer);
	}

	@Override
	public com.liferay.osb.model.AccountCustomer deleteAccountCustomer(
		long userId, long accountCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCustomerLocalService.deleteAccountCustomer(userId,
			accountCustomerId);
	}

	@Override
	public void deleteAccountCustomers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountCustomerLocalService.deleteAccountCustomers(userId);
	}

	@Override
	public void deleteAccountEntryAccountCustomers(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountCustomerLocalService.deleteAccountEntryAccountCustomers(accountEntryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCustomerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountCustomerLocalService.dynamicQuery();
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
		return _accountCustomerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accountCustomerLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accountCustomerLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _accountCustomerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _accountCustomerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.model.AccountCustomer fetchAccountCustomer(
		long accountCustomerId) {
		return _accountCustomerLocalService.fetchAccountCustomer(accountCustomerId);
	}

	@Override
	public com.liferay.osb.model.AccountCustomer fetchAccountCustomer(
		long userId, long accountEntryId) {
		return _accountCustomerLocalService.fetchAccountCustomer(userId,
			accountEntryId);
	}

	/**
	* Returns the account customer with the primary key.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer
	* @throws PortalException if a account customer with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountCustomer getAccountCustomer(
		long accountCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCustomerLocalService.getAccountCustomer(accountCustomerId);
	}

	@Override
	public com.liferay.osb.model.AccountCustomer getAccountCustomer(
		long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCustomerLocalService.getAccountCustomer(userId,
			accountEntryId);
	}

	/**
	* Returns a range of all the account customers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @return the range of account customers
	*/
	@Override
	public java.util.List<com.liferay.osb.model.AccountCustomer> getAccountCustomers(
		int start, int end) {
		return _accountCustomerLocalService.getAccountCustomers(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountCustomer> getAccountCustomers(
		long accountEntryId) {
		return _accountCustomerLocalService.getAccountCustomers(accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountCustomer> getAccountCustomers(
		long accountEntryId, int role) {
		return _accountCustomerLocalService.getAccountCustomers(accountEntryId,
			role);
	}

	/**
	* Returns the number of account customers.
	*
	* @return the number of account customers
	*/
	@Override
	public int getAccountCustomersCount() {
		return _accountCustomerLocalService.getAccountCustomersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accountCustomerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accountCustomerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountCustomerLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCustomerLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountCustomer> getUserAccountCustomers(
		long userId) {
		return _accountCustomerLocalService.getUserAccountCustomers(userId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountCustomer> getUserAccountCustomers(
		long userId, int[] roles) {
		return _accountCustomerLocalService.getUserAccountCustomers(userId,
			roles);
	}

	@Override
	public boolean hasAccountCustomer(long userId, long accountEntryId) {
		return _accountCustomerLocalService.hasAccountCustomer(userId,
			accountEntryId);
	}

	@Override
	public void toggleNotifications(long accountCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountCustomerLocalService.toggleNotifications(accountCustomerId);
	}

	/**
	* Updates the account customer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountCustomer the account customer
	* @return the account customer that was updated
	*/
	@Override
	public com.liferay.osb.model.AccountCustomer updateAccountCustomer(
		com.liferay.osb.model.AccountCustomer accountCustomer) {
		return _accountCustomerLocalService.updateAccountCustomer(accountCustomer);
	}

	@Override
	public com.liferay.osb.model.AccountCustomer updateAccountCustomer(
		long userId, long accountCustomerId, int role, int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCustomerLocalService.updateAccountCustomer(userId,
			accountCustomerId, role, notifications);
	}

	@Override
	public AccountCustomerLocalService getWrappedService() {
		return _accountCustomerLocalService;
	}

	@Override
	public void setWrappedService(
		AccountCustomerLocalService accountCustomerLocalService) {
		_accountCustomerLocalService = accountCustomerLocalService;
	}

	private AccountCustomerLocalService _accountCustomerLocalService;
}
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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for AccountCustomer. This utility wraps
 * {@link com.liferay.osb.service.impl.AccountCustomerLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomerLocalService
 * @see com.liferay.osb.service.base.AccountCustomerLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountCustomerLocalServiceImpl
 * @generated
 */
@ProviderType
public class AccountCustomerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AccountCustomerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasAccountCustomer(long userId, long accountEntryId) {
		return getService().hasAccountCustomer(userId, accountEntryId);
	}

	/**
	* Adds the account customer to the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomer the account customer
	* @return the account customer that was added
	*/
	public static com.liferay.osb.model.AccountCustomer addAccountCustomer(
		com.liferay.osb.model.AccountCustomer accountCustomer) {
		return getService().addAccountCustomer(accountCustomer);
	}

	public static com.liferay.osb.model.AccountCustomer addAccountCustomer(
		long userId, java.lang.String emailAddress, long accountEntryId,
		int role, int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addAccountCustomer(userId, emailAddress, accountEntryId,
			role, notifications);
	}

	public static com.liferay.osb.model.AccountCustomer addAccountCustomer(
		long userId, long customerUserId, long accountEntryId, int role,
		int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addAccountCustomer(userId, customerUserId, accountEntryId,
			role, notifications);
	}

	/**
	* Creates a new account customer with the primary key. Does not add the account customer to the database.
	*
	* @param accountCustomerId the primary key for the new account customer
	* @return the new account customer
	*/
	public static com.liferay.osb.model.AccountCustomer createAccountCustomer(
		long accountCustomerId) {
		return getService().createAccountCustomer(accountCustomerId);
	}

	/**
	* Deletes the account customer from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomer the account customer
	* @return the account customer that was removed
	*/
	public static com.liferay.osb.model.AccountCustomer deleteAccountCustomer(
		com.liferay.osb.model.AccountCustomer accountCustomer) {
		return getService().deleteAccountCustomer(accountCustomer);
	}

	/**
	* Deletes the account customer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer that was removed
	* @throws PortalException if a account customer with the primary key could not be found
	*/
	public static com.liferay.osb.model.AccountCustomer deleteAccountCustomer(
		long accountCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAccountCustomer(accountCustomerId);
	}

	public static com.liferay.osb.model.AccountCustomer deleteAccountCustomer(
		long userId, com.liferay.osb.model.AccountCustomer accountCustomer)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAccountCustomer(userId, accountCustomer);
	}

	public static com.liferay.osb.model.AccountCustomer deleteAccountCustomer(
		long userId, long accountCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAccountCustomer(userId, accountCustomerId);
	}

	public static com.liferay.osb.model.AccountCustomer fetchAccountCustomer(
		long accountCustomerId) {
		return getService().fetchAccountCustomer(accountCustomerId);
	}

	public static com.liferay.osb.model.AccountCustomer fetchAccountCustomer(
		long userId, long accountEntryId) {
		return getService().fetchAccountCustomer(userId, accountEntryId);
	}

	/**
	* Returns the account customer with the primary key.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer
	* @throws PortalException if a account customer with the primary key could not be found
	*/
	public static com.liferay.osb.model.AccountCustomer getAccountCustomer(
		long accountCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountCustomer(accountCustomerId);
	}

	public static com.liferay.osb.model.AccountCustomer getAccountCustomer(
		long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountCustomer(userId, accountEntryId);
	}

	/**
	* Updates the account customer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountCustomer the account customer
	* @return the account customer that was updated
	*/
	public static com.liferay.osb.model.AccountCustomer updateAccountCustomer(
		com.liferay.osb.model.AccountCustomer accountCustomer) {
		return getService().updateAccountCustomer(accountCustomer);
	}

	public static com.liferay.osb.model.AccountCustomer updateAccountCustomer(
		long userId, long accountCustomerId, int role, int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateAccountCustomer(userId, accountCustomerId, role,
			notifications);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of account customers.
	*
	* @return the number of account customers
	*/
	public static int getAccountCustomersCount() {
		return getService().getAccountCustomersCount();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<com.liferay.osb.model.AccountCustomer> getAccountCustomers(
		int start, int end) {
		return getService().getAccountCustomers(start, end);
	}

	public static java.util.List<com.liferay.osb.model.AccountCustomer> getAccountCustomers(
		long accountEntryId) {
		return getService().getAccountCustomers(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.AccountCustomer> getAccountCustomers(
		long accountEntryId, int role) {
		return getService().getAccountCustomers(accountEntryId, role);
	}

	public static java.util.List<com.liferay.osb.model.AccountCustomer> getUserAccountCustomers(
		long userId) {
		return getService().getUserAccountCustomers(userId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void deleteAccountCustomers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteAccountCustomers(userId);
	}

	public static void deleteAccountEntryAccountCustomers(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteAccountEntryAccountCustomers(accountEntryId);
	}

	public static void clearService() {
		_service = null;
	}

	public static AccountCustomerLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AccountCustomerLocalService.class.getName());

			if (invokableLocalService instanceof AccountCustomerLocalService) {
				_service = (AccountCustomerLocalService)invokableLocalService;
			}
			else {
				_service = new AccountCustomerLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AccountCustomerLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static AccountCustomerLocalService _service;
}
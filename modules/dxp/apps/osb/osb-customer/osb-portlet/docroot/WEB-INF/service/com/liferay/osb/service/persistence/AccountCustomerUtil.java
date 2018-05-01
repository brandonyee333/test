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

package com.liferay.osb.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.AccountCustomer;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the account customer service. This utility wraps {@link com.liferay.osb.service.persistence.impl.AccountCustomerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomerPersistence
 * @see com.liferay.osb.service.persistence.impl.AccountCustomerPersistenceImpl
 * @generated
 */
@ProviderType
public class AccountCustomerUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AccountCustomer accountCustomer) {
		getPersistence().clearCache(accountCustomer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccountCustomer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountCustomer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountCustomer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountCustomer> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountCustomer update(AccountCustomer accountCustomer) {
		return getPersistence().update(accountCustomer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountCustomer update(AccountCustomer accountCustomer,
		ServiceContext serviceContext) {
		return getPersistence().update(accountCustomer, serviceContext);
	}

	/**
	* Returns all the account customers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching account customers
	*/
	public static List<AccountCustomer> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the account customers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @return the range of matching account customers
	*/
	public static List<AccountCustomer> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the account customers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account customers
	*/
	public static List<AccountCustomer> findByUserId(long userId, int start,
		int end, OrderByComparator<AccountCustomer> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account customers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account customers
	*/
	public static List<AccountCustomer> findByUserId(long userId, int start,
		int end, OrderByComparator<AccountCustomer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer
	* @throws NoSuchAccountCustomerException if a matching account customer could not be found
	*/
	public static AccountCustomer findByUserId_First(long userId,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountCustomerException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public static AccountCustomer fetchByUserId_First(long userId,
		OrderByComparator<AccountCustomer> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer
	* @throws NoSuchAccountCustomerException if a matching account customer could not be found
	*/
	public static AccountCustomer findByUserId_Last(long userId,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountCustomerException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public static AccountCustomer fetchByUserId_Last(long userId,
		OrderByComparator<AccountCustomer> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the account customers before and after the current account customer in the ordered set where userId = &#63;.
	*
	* @param accountCustomerId the primary key of the current account customer
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account customer
	* @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	*/
	public static AccountCustomer[] findByUserId_PrevAndNext(
		long accountCustomerId, long userId,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountCustomerException {
		return getPersistence()
				   .findByUserId_PrevAndNext(accountCustomerId, userId,
			orderByComparator);
	}

	/**
	* Removes all the account customers where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of account customers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching account customers
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the account customers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account customers
	*/
	public static List<AccountCustomer> findByAccountEntryId(
		long accountEntryId) {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the account customers where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @return the range of matching account customers
	*/
	public static List<AccountCustomer> findByAccountEntryId(
		long accountEntryId, int start, int end) {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the account customers where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account customers
	*/
	public static List<AccountCustomer> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<AccountCustomer> orderByComparator) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the account customers where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account customers
	*/
	public static List<AccountCustomer> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<AccountCustomer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer
	* @throws NoSuchAccountCustomerException if a matching account customer could not be found
	*/
	public static AccountCustomer findByAccountEntryId_First(
		long accountEntryId,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountCustomerException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public static AccountCustomer fetchByAccountEntryId_First(
		long accountEntryId,
		OrderByComparator<AccountCustomer> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_First(accountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer
	* @throws NoSuchAccountCustomerException if a matching account customer could not be found
	*/
	public static AccountCustomer findByAccountEntryId_Last(
		long accountEntryId,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountCustomerException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public static AccountCustomer fetchByAccountEntryId_Last(
		long accountEntryId,
		OrderByComparator<AccountCustomer> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the account customers before and after the current account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountCustomerId the primary key of the current account customer
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account customer
	* @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	*/
	public static AccountCustomer[] findByAccountEntryId_PrevAndNext(
		long accountCustomerId, long accountEntryId,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountCustomerException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(accountCustomerId,
			accountEntryId, orderByComparator);
	}

	/**
	* Removes all the account customers where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of account customers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account customers
	*/
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or throws a {@link NoSuchAccountCustomerException} if it could not be found.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account customer
	* @throws NoSuchAccountCustomerException if a matching account customer could not be found
	*/
	public static AccountCustomer findByU_AEI(long userId, long accountEntryId)
		throws com.liferay.osb.exception.NoSuchAccountCustomerException {
		return getPersistence().findByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public static AccountCustomer fetchByU_AEI(long userId, long accountEntryId) {
		return getPersistence().fetchByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public static AccountCustomer fetchByU_AEI(long userId,
		long accountEntryId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_AEI(userId, accountEntryId, retrieveFromCache);
	}

	/**
	* Removes the account customer where userId = &#63; and accountEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the account customer that was removed
	*/
	public static AccountCustomer removeByU_AEI(long userId, long accountEntryId)
		throws com.liferay.osb.exception.NoSuchAccountCustomerException {
		return getPersistence().removeByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the number of account customers where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the number of matching account customers
	*/
	public static int countByU_AEI(long userId, long accountEntryId) {
		return getPersistence().countByU_AEI(userId, accountEntryId);
	}

	/**
	* Caches the account customer in the entity cache if it is enabled.
	*
	* @param accountCustomer the account customer
	*/
	public static void cacheResult(AccountCustomer accountCustomer) {
		getPersistence().cacheResult(accountCustomer);
	}

	/**
	* Caches the account customers in the entity cache if it is enabled.
	*
	* @param accountCustomers the account customers
	*/
	public static void cacheResult(List<AccountCustomer> accountCustomers) {
		getPersistence().cacheResult(accountCustomers);
	}

	/**
	* Creates a new account customer with the primary key. Does not add the account customer to the database.
	*
	* @param accountCustomerId the primary key for the new account customer
	* @return the new account customer
	*/
	public static AccountCustomer create(long accountCustomerId) {
		return getPersistence().create(accountCustomerId);
	}

	/**
	* Removes the account customer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer that was removed
	* @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	*/
	public static AccountCustomer remove(long accountCustomerId)
		throws com.liferay.osb.exception.NoSuchAccountCustomerException {
		return getPersistence().remove(accountCustomerId);
	}

	public static AccountCustomer updateImpl(AccountCustomer accountCustomer) {
		return getPersistence().updateImpl(accountCustomer);
	}

	/**
	* Returns the account customer with the primary key or throws a {@link NoSuchAccountCustomerException} if it could not be found.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer
	* @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	*/
	public static AccountCustomer findByPrimaryKey(long accountCustomerId)
		throws com.liferay.osb.exception.NoSuchAccountCustomerException {
		return getPersistence().findByPrimaryKey(accountCustomerId);
	}

	/**
	* Returns the account customer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer, or <code>null</code> if a account customer with the primary key could not be found
	*/
	public static AccountCustomer fetchByPrimaryKey(long accountCustomerId) {
		return getPersistence().fetchByPrimaryKey(accountCustomerId);
	}

	public static java.util.Map<java.io.Serializable, AccountCustomer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the account customers.
	*
	* @return the account customers
	*/
	public static List<AccountCustomer> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account customers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @return the range of account customers
	*/
	public static List<AccountCustomer> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account customers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account customers
	*/
	public static List<AccountCustomer> findAll(int start, int end,
		OrderByComparator<AccountCustomer> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account customers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account customers
	*/
	public static List<AccountCustomer> findAll(int start, int end,
		OrderByComparator<AccountCustomer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the account customers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account customers.
	*
	* @return the number of account customers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AccountCustomerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AccountCustomerPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AccountCustomerPersistence.class.getName());

			ReferenceRegistry.registerReference(AccountCustomerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static AccountCustomerPersistence _persistence;
}
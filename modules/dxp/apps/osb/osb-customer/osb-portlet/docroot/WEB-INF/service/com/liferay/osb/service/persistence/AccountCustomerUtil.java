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

import com.liferay.osb.model.AccountCustomer;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the account customer service. This utility wraps {@link AccountCustomerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomerPersistence
 * @see AccountCustomerPersistenceImpl
 * @generated
 */
public class AccountCustomerUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(AccountCustomer accountCustomer) {
		getPersistence().clearCache(accountCustomer);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccountCustomer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountCustomer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountCustomer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AccountCustomer update(AccountCustomer accountCustomer,
		boolean merge) throws SystemException {
		return getPersistence().update(accountCustomer, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AccountCustomer update(AccountCustomer accountCustomer,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(accountCustomer, merge, serviceContext);
	}

	/**
	* Caches the account customer in the entity cache if it is enabled.
	*
	* @param accountCustomer the account customer
	*/
	public static void cacheResult(
		com.liferay.osb.model.AccountCustomer accountCustomer) {
		getPersistence().cacheResult(accountCustomer);
	}

	/**
	* Caches the account customers in the entity cache if it is enabled.
	*
	* @param accountCustomers the account customers
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AccountCustomer> accountCustomers) {
		getPersistence().cacheResult(accountCustomers);
	}

	/**
	* Creates a new account customer with the primary key. Does not add the account customer to the database.
	*
	* @param accountCustomerId the primary key for the new account customer
	* @return the new account customer
	*/
	public static com.liferay.osb.model.AccountCustomer create(
		long accountCustomerId) {
		return getPersistence().create(accountCustomerId);
	}

	/**
	* Removes the account customer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer that was removed
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer remove(
		long accountCustomerId)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(accountCustomerId);
	}

	public static com.liferay.osb.model.AccountCustomer updateImpl(
		com.liferay.osb.model.AccountCustomer accountCustomer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(accountCustomer, merge);
	}

	/**
	* Returns the account customer with the primary key or throws a {@link com.liferay.osb.NoSuchAccountCustomerException} if it could not be found.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByPrimaryKey(
		long accountCustomerId)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(accountCustomerId);
	}

	/**
	* Returns the account customer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer, or <code>null</code> if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByPrimaryKey(
		long accountCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(accountCustomerId);
	}

	/**
	* Returns all the account customers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the account customers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @return the range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the account customers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the account customers before and after the current account customer in the ordered set where userId = &#63;.
	*
	* @param accountCustomerId the primary key of the current account customer
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer[] findByUserId_PrevAndNext(
		long accountCustomerId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(accountCustomerId, userId,
			orderByComparator);
	}

	/**
	* Returns all the account customers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the account customers where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @return the range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the account customers where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer[] findByAccountEntryId_PrevAndNext(
		long accountCustomerId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(accountCustomerId,
			accountEntryId, orderByComparator);
	}

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchAccountCustomerException} if it could not be found.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByU_AEI(
		long userId, long accountEntryId)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByU_AEI(
		long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByU_AEI(
		long userId, long accountEntryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_AEI(userId, accountEntryId, retrieveFromCache);
	}

	/**
	* Returns all the account customers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_R(userId, role);
	}

	/**
	* Returns a range of all the account customers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @return the range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int role, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_R(userId, role, start, end);
	}

	/**
	* Returns an ordered range of all the account customers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_R(userId, role, start, end, orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByU_R_First(
		long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_R_First(userId, role, orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByU_R_First(
		long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_R_First(userId, role, orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByU_R_Last(
		long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_R_Last(userId, role, orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByU_R_Last(
		long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_R_Last(userId, role, orderByComparator);
	}

	/**
	* Returns the account customers before and after the current account customer in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param accountCustomerId the primary key of the current account customer
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer[] findByU_R_PrevAndNext(
		long accountCustomerId, long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_R_PrevAndNext(accountCustomerId, userId, role,
			orderByComparator);
	}

	/**
	* Returns all the account customers where userId = &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param roles the roles
	* @return the matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int[] roles)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_R(userId, roles);
	}

	/**
	* Returns a range of all the account customers where userId = &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param roles the roles
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @return the range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int[] roles, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_R(userId, roles, start, end);
	}

	/**
	* Returns an ordered range of all the account customers where userId = &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param roles the roles
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int[] roles, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_R(userId, roles, start, end, orderByComparator);
	}

	/**
	* Returns all the account customers where accountEntryId = &#63; and role &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @return the matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotR(
		long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAEI_NotR(accountEntryId, role);
	}

	/**
	* Returns a range of all the account customers where accountEntryId = &#63; and role &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @return the range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotR(
		long accountEntryId, int role, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAEI_NotR(accountEntryId, role, start, end);
	}

	/**
	* Returns an ordered range of all the account customers where accountEntryId = &#63; and role &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotR(
		long accountEntryId, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotR(accountEntryId, role, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByAEI_NotR_First(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotR_First(accountEntryId, role, orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByAEI_NotR_First(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_NotR_First(accountEntryId, role,
			orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByAEI_NotR_Last(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotR_Last(accountEntryId, role, orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByAEI_NotR_Last(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_NotR_Last(accountEntryId, role, orderByComparator);
	}

	/**
	* Returns the account customers before and after the current account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	*
	* @param accountCustomerId the primary key of the current account customer
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer[] findByAEI_NotR_PrevAndNext(
		long accountCustomerId, long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotR_PrevAndNext(accountCustomerId,
			accountEntryId, role, orderByComparator);
	}

	/**
	* Returns all the account customers where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @return the matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotN(
		long accountEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAEI_NotN(accountEntryId, notifications);
	}

	/**
	* Returns a range of all the account customers where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @return the range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotN(
		long accountEntryId, int notifications, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotN(accountEntryId, notifications, start, end);
	}

	/**
	* Returns an ordered range of all the account customers where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotN(
		long accountEntryId, int notifications, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotN(accountEntryId, notifications, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByAEI_NotN_First(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotN_First(accountEntryId, notifications,
			orderByComparator);
	}

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByAEI_NotN_First(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_NotN_First(accountEntryId, notifications,
			orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer findByAEI_NotN_Last(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotN_Last(accountEntryId, notifications,
			orderByComparator);
	}

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer fetchByAEI_NotN_Last(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_NotN_Last(accountEntryId, notifications,
			orderByComparator);
	}

	/**
	* Returns the account customers before and after the current account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountCustomerId the primary key of the current account customer
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer[] findByAEI_NotN_PrevAndNext(
		long accountCustomerId, long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotN_PrevAndNext(accountCustomerId,
			accountEntryId, notifications, orderByComparator);
	}

	/**
	* Returns all the account customers.
	*
	* @return the account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account customers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account customers
	* @param end the upper bound of the range of account customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCustomer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the account customers where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the account customers where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Removes the account customer where userId = &#63; and accountEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the account customer that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCustomer removeByU_AEI(
		long userId, long accountEntryId)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByU_AEI(userId, accountEntryId);
	}

	/**
	* Removes all the account customers where userId = &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param role the role
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_R(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_R(userId, role);
	}

	/**
	* Removes all the account customers where accountEntryId = &#63; and role &ne; &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAEI_NotR(long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAEI_NotR(accountEntryId, role);
	}

	/**
	* Removes all the account customers where accountEntryId = &#63; and notifications &ne; &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAEI_NotN(long accountEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAEI_NotN(accountEntryId, notifications);
	}

	/**
	* Removes all the account customers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account customers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of account customers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of account customers where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_AEI(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the number of account customers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_R(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_R(userId, role);
	}

	/**
	* Returns the number of account customers where userId = &#63; and role = any &#63;.
	*
	* @param userId the user ID
	* @param roles the roles
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_R(long userId, int[] roles)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_R(userId, roles);
	}

	/**
	* Returns the number of account customers where accountEntryId = &#63; and role &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAEI_NotR(long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAEI_NotR(accountEntryId, role);
	}

	/**
	* Returns the number of account customers where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAEI_NotN(long accountEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAEI_NotN(accountEntryId, notifications);
	}

	/**
	* Returns the number of account customers.
	*
	* @return the number of account customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
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

	/**
	 * @deprecated
	 */
	public void setPersistence(AccountCustomerPersistence persistence) {
	}

	private static AccountCustomerPersistence _persistence;
}
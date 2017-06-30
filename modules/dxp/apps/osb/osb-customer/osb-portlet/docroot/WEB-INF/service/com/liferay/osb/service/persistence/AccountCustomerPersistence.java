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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the account customer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomerPersistenceImpl
 * @see AccountCustomerUtil
 * @generated
 */
public interface AccountCustomerPersistence extends BasePersistence<AccountCustomer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountCustomerUtil} to access the account customer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the account customer in the entity cache if it is enabled.
	*
	* @param accountCustomer the account customer
	*/
	public void cacheResult(
		com.liferay.osb.model.AccountCustomer accountCustomer);

	/**
	* Caches the account customers in the entity cache if it is enabled.
	*
	* @param accountCustomers the account customers
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AccountCustomer> accountCustomers);

	/**
	* Creates a new account customer with the primary key. Does not add the account customer to the database.
	*
	* @param accountCustomerId the primary key for the new account customer
	* @return the new account customer
	*/
	public com.liferay.osb.model.AccountCustomer create(long accountCustomerId);

	/**
	* Removes the account customer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer that was removed
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer remove(long accountCustomerId)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AccountCustomer updateImpl(
		com.liferay.osb.model.AccountCustomer accountCustomer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account customer with the primary key or throws a {@link com.liferay.osb.NoSuchAccountCustomerException} if it could not be found.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer findByPrimaryKey(
		long accountCustomerId)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account customer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer, or <code>null</code> if a account customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByPrimaryKey(
		long accountCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account customers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountCustomer[] findByUserId_PrevAndNext(
		long accountCustomerId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account customers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountCustomer[] findByAccountEntryId_PrevAndNext(
		long accountCustomerId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchAccountCustomerException} if it could not be found.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account customer
	* @throws com.liferay.osb.NoSuchAccountCustomerException if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer findByU_AEI(long userId,
		long accountEntryId)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByU_AEI(long userId,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByU_AEI(long userId,
		long accountEntryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account customers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int role, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountCustomer findByU_R_First(long userId,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account customer in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByU_R_First(long userId,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountCustomer findByU_R_Last(long userId,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account customer in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByU_R_Last(long userId,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountCustomer[] findByU_R_PrevAndNext(
		long accountCustomerId, long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int[] roles)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int[] roles, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByU_R(
		long userId, int[] roles, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account customers where accountEntryId = &#63; and role &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @return the matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotR(
		long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotR(
		long accountEntryId, int role, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotR(
		long accountEntryId, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountCustomer findByAEI_NotR_First(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByAEI_NotR_First(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountCustomer findByAEI_NotR_Last(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63; and role &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByAEI_NotR_Last(
		long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountCustomer[] findByAEI_NotR_PrevAndNext(
		long accountCustomerId, long accountEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account customers where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @return the matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotN(
		long accountEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotN(
		long accountEntryId, int notifications, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findByAEI_NotN(
		long accountEntryId, int notifications, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountCustomer findByAEI_NotN_First(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByAEI_NotN_First(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountCustomer findByAEI_NotN_Last(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer fetchByAEI_NotN_Last(
		long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountCustomer[] findByAEI_NotN_PrevAndNext(
		long accountCustomerId, long accountEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account customers.
	*
	* @return the account customers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCustomer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountCustomer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account customers where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account customers where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the account customer where userId = &#63; and accountEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the account customer that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCustomer removeByU_AEI(long userId,
		long accountEntryId)
		throws com.liferay.osb.NoSuchAccountCustomerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account customers where userId = &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param role the role
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_R(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account customers where accountEntryId = &#63; and role &ne; &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI_NotR(long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account customers where accountEntryId = &#63; and notifications &ne; &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI_NotN(long accountEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account customers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account customers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account customers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account customers where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_AEI(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account customers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_R(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account customers where userId = &#63; and role = any &#63;.
	*
	* @param userId the user ID
	* @param roles the roles
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_R(long userId, int[] roles)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account customers where accountEntryId = &#63; and role &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param role the role
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_NotR(long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account customers where accountEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param notifications the notifications
	* @return the number of matching account customers
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_NotN(long accountEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account customers.
	*
	* @return the number of account customers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
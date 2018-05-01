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

import com.liferay.osb.exception.NoSuchAccountCustomerException;
import com.liferay.osb.model.AccountCustomer;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the account customer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.AccountCustomerPersistenceImpl
 * @see AccountCustomerUtil
 * @generated
 */
@ProviderType
public interface AccountCustomerPersistence extends BasePersistence<AccountCustomer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountCustomerUtil} to access the account customer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the account customers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching account customers
	*/
	public java.util.List<AccountCustomer> findByUserId(long userId);

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
	public java.util.List<AccountCustomer> findByUserId(long userId, int start,
		int end);

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
	public java.util.List<AccountCustomer> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator);

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
	public java.util.List<AccountCustomer> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer
	* @throws NoSuchAccountCustomerException if a matching account customer could not be found
	*/
	public AccountCustomer findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException;

	/**
	* Returns the first account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public AccountCustomer fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator);

	/**
	* Returns the last account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer
	* @throws NoSuchAccountCustomerException if a matching account customer could not be found
	*/
	public AccountCustomer findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException;

	/**
	* Returns the last account customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public AccountCustomer fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator);

	/**
	* Returns the account customers before and after the current account customer in the ordered set where userId = &#63;.
	*
	* @param accountCustomerId the primary key of the current account customer
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account customer
	* @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	*/
	public AccountCustomer[] findByUserId_PrevAndNext(long accountCustomerId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException;

	/**
	* Removes all the account customers where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of account customers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching account customers
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the account customers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account customers
	*/
	public java.util.List<AccountCustomer> findByAccountEntryId(
		long accountEntryId);

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
	public java.util.List<AccountCustomer> findByAccountEntryId(
		long accountEntryId, int start, int end);

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
	public java.util.List<AccountCustomer> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator);

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
	public java.util.List<AccountCustomer> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer
	* @throws NoSuchAccountCustomerException if a matching account customer could not be found
	*/
	public AccountCustomer findByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException;

	/**
	* Returns the first account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public AccountCustomer fetchByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator);

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer
	* @throws NoSuchAccountCustomerException if a matching account customer could not be found
	*/
	public AccountCustomer findByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException;

	/**
	* Returns the last account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public AccountCustomer fetchByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator);

	/**
	* Returns the account customers before and after the current account customer in the ordered set where accountEntryId = &#63;.
	*
	* @param accountCustomerId the primary key of the current account customer
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account customer
	* @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	*/
	public AccountCustomer[] findByAccountEntryId_PrevAndNext(
		long accountCustomerId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException;

	/**
	* Removes all the account customers where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public void removeByAccountEntryId(long accountEntryId);

	/**
	* Returns the number of account customers where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account customers
	*/
	public int countByAccountEntryId(long accountEntryId);

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or throws a {@link NoSuchAccountCustomerException} if it could not be found.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account customer
	* @throws NoSuchAccountCustomerException if a matching account customer could not be found
	*/
	public AccountCustomer findByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountCustomerException;

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public AccountCustomer fetchByU_AEI(long userId, long accountEntryId);

	/**
	* Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	*/
	public AccountCustomer fetchByU_AEI(long userId, long accountEntryId,
		boolean retrieveFromCache);

	/**
	* Removes the account customer where userId = &#63; and accountEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the account customer that was removed
	*/
	public AccountCustomer removeByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountCustomerException;

	/**
	* Returns the number of account customers where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the number of matching account customers
	*/
	public int countByU_AEI(long userId, long accountEntryId);

	/**
	* Caches the account customer in the entity cache if it is enabled.
	*
	* @param accountCustomer the account customer
	*/
	public void cacheResult(AccountCustomer accountCustomer);

	/**
	* Caches the account customers in the entity cache if it is enabled.
	*
	* @param accountCustomers the account customers
	*/
	public void cacheResult(java.util.List<AccountCustomer> accountCustomers);

	/**
	* Creates a new account customer with the primary key. Does not add the account customer to the database.
	*
	* @param accountCustomerId the primary key for the new account customer
	* @return the new account customer
	*/
	public AccountCustomer create(long accountCustomerId);

	/**
	* Removes the account customer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer that was removed
	* @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	*/
	public AccountCustomer remove(long accountCustomerId)
		throws NoSuchAccountCustomerException;

	public AccountCustomer updateImpl(AccountCustomer accountCustomer);

	/**
	* Returns the account customer with the primary key or throws a {@link NoSuchAccountCustomerException} if it could not be found.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer
	* @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	*/
	public AccountCustomer findByPrimaryKey(long accountCustomerId)
		throws NoSuchAccountCustomerException;

	/**
	* Returns the account customer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountCustomerId the primary key of the account customer
	* @return the account customer, or <code>null</code> if a account customer with the primary key could not be found
	*/
	public AccountCustomer fetchByPrimaryKey(long accountCustomerId);

	@Override
	public java.util.Map<java.io.Serializable, AccountCustomer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the account customers.
	*
	* @return the account customers
	*/
	public java.util.List<AccountCustomer> findAll();

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
	public java.util.List<AccountCustomer> findAll(int start, int end);

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
	public java.util.List<AccountCustomer> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator);

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
	public java.util.List<AccountCustomer> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountCustomer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the account customers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of account customers.
	*
	* @return the number of account customers
	*/
	public int countAll();
}
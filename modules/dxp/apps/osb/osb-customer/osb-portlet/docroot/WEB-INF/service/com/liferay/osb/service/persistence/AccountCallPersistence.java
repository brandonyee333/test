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

import com.liferay.osb.model.AccountCall;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the account call service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCallPersistenceImpl
 * @see AccountCallUtil
 * @generated
 */
public interface AccountCallPersistence extends BasePersistence<AccountCall> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountCallUtil} to access the account call persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the account call in the entity cache if it is enabled.
	*
	* @param accountCall the account call
	*/
	public void cacheResult(com.liferay.osb.model.AccountCall accountCall);

	/**
	* Caches the account calls in the entity cache if it is enabled.
	*
	* @param accountCalls the account calls
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AccountCall> accountCalls);

	/**
	* Creates a new account call with the primary key. Does not add the account call to the database.
	*
	* @param accountCallId the primary key for the new account call
	* @return the new account call
	*/
	public com.liferay.osb.model.AccountCall create(long accountCallId);

	/**
	* Removes the account call with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call that was removed
	* @throws com.liferay.osb.NoSuchAccountCallException if a account call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall remove(long accountCallId)
		throws com.liferay.osb.NoSuchAccountCallException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AccountCall updateImpl(
		com.liferay.osb.model.AccountCall accountCall, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account call with the primary key or throws a {@link com.liferay.osb.NoSuchAccountCallException} if it could not be found.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call
	* @throws com.liferay.osb.NoSuchAccountCallException if a account call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall findByPrimaryKey(
		long accountCallId)
		throws com.liferay.osb.NoSuchAccountCallException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account call with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call, or <code>null</code> if a account call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall fetchByPrimaryKey(
		long accountCallId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account calls where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account calls
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCall> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account calls where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @return the range of matching account calls
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCall> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account calls where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account calls
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCall> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account call
	* @throws com.liferay.osb.NoSuchAccountCallException if a matching account call could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCallException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account call, or <code>null</code> if a matching account call could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account call
	* @throws com.liferay.osb.NoSuchAccountCallException if a matching account call could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCallException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account call, or <code>null</code> if a matching account call could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account calls before and after the current account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountCallId the primary key of the current account call
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account call
	* @throws com.liferay.osb.NoSuchAccountCallException if a account call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountCall[] findByAccountEntryId_PrevAndNext(
		long accountCallId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCallException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account calls.
	*
	* @return the account calls
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCall> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @return the range of account calls
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCall> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account calls
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountCall> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account calls where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account calls from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account calls where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account calls
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account calls.
	*
	* @return the number of account calls
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
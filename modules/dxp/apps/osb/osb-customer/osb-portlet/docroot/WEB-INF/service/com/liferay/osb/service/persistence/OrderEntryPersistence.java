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

import com.liferay.osb.model.OrderEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the order entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrderEntryPersistenceImpl
 * @see OrderEntryUtil
 * @generated
 */
public interface OrderEntryPersistence extends BasePersistence<OrderEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OrderEntryUtil} to access the order entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the order entry in the entity cache if it is enabled.
	*
	* @param orderEntry the order entry
	*/
	public void cacheResult(com.liferay.osb.model.OrderEntry orderEntry);

	/**
	* Caches the order entries in the entity cache if it is enabled.
	*
	* @param orderEntries the order entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.OrderEntry> orderEntries);

	/**
	* Creates a new order entry with the primary key. Does not add the order entry to the database.
	*
	* @param orderEntryId the primary key for the new order entry
	* @return the new order entry
	*/
	public com.liferay.osb.model.OrderEntry create(long orderEntryId);

	/**
	* Removes the order entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orderEntryId the primary key of the order entry
	* @return the order entry that was removed
	* @throws com.liferay.osb.NoSuchOrderEntryException if a order entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry remove(long orderEntryId)
		throws com.liferay.osb.NoSuchOrderEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.OrderEntry updateImpl(
		com.liferay.osb.model.OrderEntry orderEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the order entry with the primary key or throws a {@link com.liferay.osb.NoSuchOrderEntryException} if it could not be found.
	*
	* @param orderEntryId the primary key of the order entry
	* @return the order entry
	* @throws com.liferay.osb.NoSuchOrderEntryException if a order entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry findByPrimaryKey(long orderEntryId)
		throws com.liferay.osb.NoSuchOrderEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the order entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param orderEntryId the primary key of the order entry
	* @return the order entry, or <code>null</code> if a order entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry fetchByPrimaryKey(long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the order entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching order entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OrderEntry> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the order entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of order entries
	* @param end the upper bound of the range of order entries (not inclusive)
	* @return the range of matching order entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OrderEntry> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the order entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of order entries
	* @param end the upper bound of the range of order entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching order entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OrderEntry> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first order entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching order entry
	* @throws com.liferay.osb.NoSuchOrderEntryException if a matching order entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOrderEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first order entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching order entry, or <code>null</code> if a matching order entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last order entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching order entry
	* @throws com.liferay.osb.NoSuchOrderEntryException if a matching order entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOrderEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last order entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching order entry, or <code>null</code> if a matching order entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the order entries before and after the current order entry in the ordered set where uuid = &#63;.
	*
	* @param orderEntryId the primary key of the current order entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next order entry
	* @throws com.liferay.osb.NoSuchOrderEntryException if a order entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry[] findByUuid_PrevAndNext(
		long orderEntryId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOrderEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the order entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching order entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OrderEntry> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the order entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of order entries
	* @param end the upper bound of the range of order entries (not inclusive)
	* @return the range of matching order entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OrderEntry> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the order entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of order entries
	* @param end the upper bound of the range of order entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching order entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OrderEntry> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first order entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching order entry
	* @throws com.liferay.osb.NoSuchOrderEntryException if a matching order entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOrderEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first order entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching order entry, or <code>null</code> if a matching order entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last order entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching order entry
	* @throws com.liferay.osb.NoSuchOrderEntryException if a matching order entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOrderEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last order entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching order entry, or <code>null</code> if a matching order entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the order entries before and after the current order entry in the ordered set where accountEntryId = &#63;.
	*
	* @param orderEntryId the primary key of the current order entry
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next order entry
	* @throws com.liferay.osb.NoSuchOrderEntryException if a order entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry[] findByAccountEntryId_PrevAndNext(
		long orderEntryId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOrderEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the order entries.
	*
	* @return the order entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OrderEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the order entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of order entries
	* @param end the upper bound of the range of order entries (not inclusive)
	* @return the range of order entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OrderEntry> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the order entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of order entries
	* @param end the upper bound of the range of order entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of order entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OrderEntry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the order entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the order entries where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the order entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of order entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching order entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of order entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching order entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of order entries.
	*
	* @return the number of order entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
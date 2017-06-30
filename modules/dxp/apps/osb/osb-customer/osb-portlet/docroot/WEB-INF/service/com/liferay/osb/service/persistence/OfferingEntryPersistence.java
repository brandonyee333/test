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

import com.liferay.osb.model.OfferingEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the offering entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryPersistenceImpl
 * @see OfferingEntryUtil
 * @generated
 */
public interface OfferingEntryPersistence extends BasePersistence<OfferingEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OfferingEntryUtil} to access the offering entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the offering entry in the entity cache if it is enabled.
	*
	* @param offeringEntry the offering entry
	*/
	public void cacheResult(com.liferay.osb.model.OfferingEntry offeringEntry);

	/**
	* Caches the offering entries in the entity cache if it is enabled.
	*
	* @param offeringEntries the offering entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.OfferingEntry> offeringEntries);

	/**
	* Creates a new offering entry with the primary key. Does not add the offering entry to the database.
	*
	* @param offeringEntryId the primary key for the new offering entry
	* @return the new offering entry
	*/
	public com.liferay.osb.model.OfferingEntry create(long offeringEntryId);

	/**
	* Removes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry that was removed
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry remove(long offeringEntryId)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.OfferingEntry updateImpl(
		com.liferay.osb.model.OfferingEntry offeringEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the offering entry with the primary key or throws a {@link com.liferay.osb.NoSuchOfferingEntryException} if it could not be found.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByPrimaryKey(
		long offeringEntryId)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the offering entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry, or <code>null</code> if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByPrimaryKey(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the offering entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the offering entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the offering entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry[] findByAccountEntryId_PrevAndNext(
		long offeringEntryId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the offering entries where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByOrderEntryId(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the offering entries where orderEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByOrderEntryId(
		long orderEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the offering entries where orderEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByOrderEntryId(
		long orderEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByOrderEntryId_First(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByOrderEntryId_First(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByOrderEntryId_Last(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByOrderEntryId_Last(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry[] findByOrderEntryId_PrevAndNext(
		long offeringEntryId, long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the offering entries where version = &#63;.
	*
	* @param version the version
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByVersion(
		int version) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the offering entries where version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param version the version
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByVersion(
		int version, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the offering entries where version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param version the version
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByVersion(
		int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where version = &#63;.
	*
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByVersion_First(
		int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where version = &#63;.
	*
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByVersion_First(
		int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where version = &#63;.
	*
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByVersion_Last(int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where version = &#63;.
	*
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByVersion_Last(
		int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where version = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry[] findByVersion_PrevAndNext(
		long offeringEntryId, int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the offering entries where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findBySupportEndDate(
		java.util.Date supportEndDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the offering entries where supportEndDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportEndDate the support end date
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findBySupportEndDate(
		java.util.Date supportEndDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the offering entries where supportEndDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportEndDate the support end date
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findBySupportEndDate(
		java.util.Date supportEndDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findBySupportEndDate_First(
		java.util.Date supportEndDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchBySupportEndDate_First(
		java.util.Date supportEndDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findBySupportEndDate_Last(
		java.util.Date supportEndDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchBySupportEndDate_Last(
		java.util.Date supportEndDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where supportEndDate = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param supportEndDate the support end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry[] findBySupportEndDate_PrevAndNext(
		long offeringEntryId, java.util.Date supportEndDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the offering entries where status = &#63;.
	*
	* @param status the status
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the offering entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByStatus(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the offering entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where status = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry[] findByStatus_PrevAndNext(
		long offeringEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the offering entries where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByAEI_S(
		long accountEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the offering entries where accountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByAEI_S(
		long accountEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the offering entries where accountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByAEI_S(
		long accountEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByAEI_S_First(
		long accountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByAEI_S_First(
		long accountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByAEI_S_Last(
		long accountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByAEI_S_Last(
		long accountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry[] findByAEI_S_PrevAndNext(
		long offeringEntryId, long accountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByU_AEI_OEI_T(
		long userId, long accountEntryId, long orderEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByU_AEI_OEI_T(
		long userId, long accountEntryId, long orderEntryId, int type,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findByU_AEI_OEI_T(
		long userId, long accountEntryId, long orderEntryId, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByU_AEI_OEI_T_First(
		long userId, long accountEntryId, long orderEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByU_AEI_OEI_T_First(
		long userId, long accountEntryId, long orderEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry findByU_AEI_OEI_T_Last(
		long userId, long accountEntryId, long orderEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry fetchByU_AEI_OEI_T_Last(
		long userId, long accountEntryId, long orderEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry[] findByU_AEI_OEI_T_PrevAndNext(
		long offeringEntryId, long userId, long accountEntryId,
		long orderEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the offering entries.
	*
	* @return the offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the offering entries where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the offering entries where orderEntryId = &#63; from the database.
	*
	* @param orderEntryId the order entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOrderEntryId(long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the offering entries where version = &#63; from the database.
	*
	* @param version the version
	* @throws SystemException if a system exception occurred
	*/
	public void removeByVersion(int version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the offering entries where supportEndDate = &#63; from the database.
	*
	* @param supportEndDate the support end date
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySupportEndDate(java.util.Date supportEndDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the offering entries where status = &#63; from the database.
	*
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the offering entries where accountEntryId = &#63; and status = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI_S(long accountEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_AEI_OEI_T(long userId, long accountEntryId,
		long orderEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the offering entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of offering entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of offering entries where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByOrderEntryId(long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of offering entries where version = &#63;.
	*
	* @param version the version
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByVersion(int version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of offering entries where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public int countBySupportEndDate(java.util.Date supportEndDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of offering entries where status = &#63;.
	*
	* @param status the status
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of offering entries where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_S(long accountEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_AEI_OEI_T(long userId, long accountEntryId,
		long orderEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of offering entries.
	*
	* @return the number of offering entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
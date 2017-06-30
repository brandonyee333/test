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

import com.liferay.osb.model.TicketEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryPersistenceImpl
 * @see TicketEntryUtil
 * @generated
 */
public interface TicketEntryPersistence extends BasePersistence<TicketEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketEntryUtil} to access the ticket entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ticket entry in the entity cache if it is enabled.
	*
	* @param ticketEntry the ticket entry
	*/
	public void cacheResult(com.liferay.osb.model.TicketEntry ticketEntry);

	/**
	* Caches the ticket entries in the entity cache if it is enabled.
	*
	* @param ticketEntries the ticket entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TicketEntry> ticketEntries);

	/**
	* Creates a new ticket entry with the primary key. Does not add the ticket entry to the database.
	*
	* @param ticketEntryId the primary key for the new ticket entry
	* @return the new ticket entry
	*/
	public com.liferay.osb.model.TicketEntry create(long ticketEntryId);

	/**
	* Removes the ticket entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry that was removed
	* @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry remove(long ticketEntryId)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketEntry updateImpl(
		com.liferay.osb.model.TicketEntry ticketEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket entry with the primary key or throws a {@link com.liferay.osb.NoSuchTicketEntryException} if it could not be found.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry findByPrimaryKey(
		long ticketEntryId)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry, or <code>null</code> if a ticket entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry fetchByPrimaryKey(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket entries where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByGtModifiedDate(
		java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket entries where modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByGtModifiedDate(
		java.util.Date modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket entries where modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByGtModifiedDate(
		java.util.Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry findByGtModifiedDate_First(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry fetchByGtModifiedDate_First(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry findByGtModifiedDate_Last(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry fetchByGtModifiedDate_Last(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where modifiedDate &ge; &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry[] findByGtModifiedDate_PrevAndNext(
		long ticketEntryId, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where accountEntryId = &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry[] findByAccountEntryId_PrevAndNext(
		long ticketEntryId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket entries where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByOfferingEntryId(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket entries where offeringEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByOfferingEntryId(
		long offeringEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket entries where offeringEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry findByOfferingEntryId_First(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry fetchByOfferingEntryId_First(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry findByOfferingEntryId_Last(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry fetchByOfferingEntryId_Last(
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where offeringEntryId = &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry[] findByOfferingEntryId_PrevAndNext(
		long ticketEntryId, long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or throws a {@link com.liferay.osb.NoSuchTicketEntryException} if it could not be found.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the matching ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry findByAEI_TI(long accountEntryId,
		long ticketId)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry fetchByAEI_TI(
		long accountEntryId, long ticketId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry fetchByAEI_TI(
		long accountEntryId, long ticketId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @return the matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByOEI_NotR(
		long offeringEntryId, int resolution)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByOEI_NotR(
		long offeringEntryId, int resolution, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findByOEI_NotR(
		long offeringEntryId, int resolution, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry findByOEI_NotR_First(
		long offeringEntryId, int resolution,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry fetchByOEI_NotR_First(
		long offeringEntryId, int resolution,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry findByOEI_NotR_Last(
		long offeringEntryId, int resolution,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry fetchByOEI_NotR_Last(
		long offeringEntryId, int resolution,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket entries before and after the current ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param ticketEntryId the primary key of the current ticket entry
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket entry
	* @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry[] findByOEI_NotR_PrevAndNext(
		long ticketEntryId, long offeringEntryId, int resolution,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket entries.
	*
	* @return the ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket entries where modifiedDate &ge; &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGtModifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket entries where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket entries where offeringEntryId = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOfferingEntryId(long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the ticket entry where accountEntryId = &#63; and ticketId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the ticket entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketEntry removeByAEI_TI(
		long accountEntryId, long ticketId)
		throws com.liferay.osb.NoSuchTicketEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOEI_NotR(long offeringEntryId, int resolution)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket entries where modifiedDate &ge; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByGtModifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket entries where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the number of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByOfferingEntryId(long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket entries where accountEntryId = &#63; and ticketId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param ticketId the ticket ID
	* @return the number of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_TI(long accountEntryId, long ticketId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param resolution the resolution
	* @return the number of matching ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByOEI_NotR(long offeringEntryId, int resolution)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket entries.
	*
	* @return the number of ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
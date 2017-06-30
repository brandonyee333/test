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

import com.liferay.osb.model.TicketInformation;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket information service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketInformationPersistenceImpl
 * @see TicketInformationUtil
 * @generated
 */
public interface TicketInformationPersistence extends BasePersistence<TicketInformation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketInformationUtil} to access the ticket information persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ticket information in the entity cache if it is enabled.
	*
	* @param ticketInformation the ticket information
	*/
	public void cacheResult(
		com.liferay.osb.model.TicketInformation ticketInformation);

	/**
	* Caches the ticket informations in the entity cache if it is enabled.
	*
	* @param ticketInformations the ticket informations
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TicketInformation> ticketInformations);

	/**
	* Creates a new ticket information with the primary key. Does not add the ticket information to the database.
	*
	* @param ticketInformationId the primary key for the new ticket information
	* @return the new ticket information
	*/
	public com.liferay.osb.model.TicketInformation create(
		long ticketInformationId);

	/**
	* Removes the ticket information with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information that was removed
	* @throws com.liferay.osb.NoSuchTicketInformationException if a ticket information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation remove(
		long ticketInformationId)
		throws com.liferay.osb.NoSuchTicketInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketInformation updateImpl(
		com.liferay.osb.model.TicketInformation ticketInformation, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket information with the primary key or throws a {@link com.liferay.osb.NoSuchTicketInformationException} if it could not be found.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information
	* @throws com.liferay.osb.NoSuchTicketInformationException if a ticket information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation findByPrimaryKey(
		long ticketInformationId)
		throws com.liferay.osb.NoSuchTicketInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket information with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information, or <code>null</code> if a ticket information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation fetchByPrimaryKey(
		long ticketInformationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket informations where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketInformation> findByTicketEntryId(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket informations where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @return the range of matching ticket informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketInformation> findByTicketEntryId(
		long ticketEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket informations where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketInformation> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket information
	* @throws com.liferay.osb.NoSuchTicketInformationException if a matching ticket information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation findByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket information, or <code>null</code> if a matching ticket information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation fetchByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket information
	* @throws com.liferay.osb.NoSuchTicketInformationException if a matching ticket information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation findByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket information, or <code>null</code> if a matching ticket information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation fetchByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket informations before and after the current ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketInformationId the primary key of the current ticket information
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket information
	* @throws com.liferay.osb.NoSuchTicketInformationException if a ticket information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation[] findByTicketEntryId_PrevAndNext(
		long ticketInformationId, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or throws a {@link com.liferay.osb.NoSuchTicketInformationException} if it could not be found.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the matching ticket information
	* @throws com.liferay.osb.NoSuchTicketInformationException if a matching ticket information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation findByTEI_FI(
		long ticketEntryId, long fieldId)
		throws com.liferay.osb.NoSuchTicketInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the matching ticket information, or <code>null</code> if a matching ticket information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation fetchByTEI_FI(
		long ticketEntryId, long fieldId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching ticket information, or <code>null</code> if a matching ticket information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation fetchByTEI_FI(
		long ticketEntryId, long fieldId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket informations.
	*
	* @return the ticket informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketInformation> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @return the range of ticket informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketInformation> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket informations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketInformation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket informations where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the ticket information where ticketEntryId = &#63; and fieldId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the ticket information that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketInformation removeByTEI_FI(
		long ticketEntryId, long fieldId)
		throws com.liferay.osb.NoSuchTicketInformationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket informations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket informations where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket informations
	* @throws SystemException if a system exception occurred
	*/
	public int countByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket informations where ticketEntryId = &#63; and fieldId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the number of matching ticket informations
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_FI(long ticketEntryId, long fieldId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket informations.
	*
	* @return the number of ticket informations
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
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

import com.liferay.osb.model.TicketLink;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketLinkPersistenceImpl
 * @see TicketLinkUtil
 * @generated
 */
public interface TicketLinkPersistence extends BasePersistence<TicketLink> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketLinkUtil} to access the ticket link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ticket link in the entity cache if it is enabled.
	*
	* @param ticketLink the ticket link
	*/
	public void cacheResult(com.liferay.osb.model.TicketLink ticketLink);

	/**
	* Caches the ticket links in the entity cache if it is enabled.
	*
	* @param ticketLinks the ticket links
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TicketLink> ticketLinks);

	/**
	* Creates a new ticket link with the primary key. Does not add the ticket link to the database.
	*
	* @param ticketLinkId the primary key for the new ticket link
	* @return the new ticket link
	*/
	public com.liferay.osb.model.TicketLink create(long ticketLinkId);

	/**
	* Removes the ticket link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link that was removed
	* @throws com.liferay.osb.NoSuchTicketLinkException if a ticket link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink remove(long ticketLinkId)
		throws com.liferay.osb.NoSuchTicketLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketLink updateImpl(
		com.liferay.osb.model.TicketLink ticketLink, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket link with the primary key or throws a {@link com.liferay.osb.NoSuchTicketLinkException} if it could not be found.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link
	* @throws com.liferay.osb.NoSuchTicketLinkException if a ticket link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink findByPrimaryKey(long ticketLinkId)
		throws com.liferay.osb.NoSuchTicketLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link, or <code>null</code> if a ticket link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink fetchByPrimaryKey(long ticketLinkId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket links where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTicketEntryId(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTicketEntryId(
		long ticketEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link
	* @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink findByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink fetchByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link
	* @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink findByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink fetchByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketLinkId the primary key of the current ticket link
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket link
	* @throws com.liferay.osb.NoSuchTicketLinkException if a ticket link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink[] findByTicketEntryId_PrevAndNext(
		long ticketLinkId, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTEI_TSI(
		long ticketEntryId, long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTEI_TSI(
		long ticketEntryId, long ticketSolutionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTEI_TSI(
		long ticketEntryId, long ticketSolutionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link
	* @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink findByTEI_TSI_First(
		long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink fetchByTEI_TSI_First(
		long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link
	* @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink findByTEI_TSI_Last(
		long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink fetchByTEI_TSI_Last(
		long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketLinkId the primary key of the current ticket link
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket link
	* @throws com.liferay.osb.NoSuchTicketLinkException if a ticket link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink[] findByTEI_TSI_PrevAndNext(
		long ticketLinkId, long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @return the matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTEI_V(
		long ticketEntryId, int visibility)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTEI_V(
		long ticketEntryId, int visibility, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTEI_V(
		long ticketEntryId, int visibility, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link
	* @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink findByTEI_V_First(
		long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink fetchByTEI_V_First(
		long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link
	* @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink findByTEI_V_Last(
		long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink fetchByTEI_V_Last(
		long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketLinkId the primary key of the current ticket link
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket link
	* @throws com.liferay.osb.NoSuchTicketLinkException if a ticket link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketLink[] findByTEI_V_PrevAndNext(
		long ticketLinkId, long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @return the matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTEI_V(
		long ticketEntryId, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTEI_V(
		long ticketEntryId, int[] visibilities, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findByTEI_V(
		long ticketEntryId, int[] visibilities, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket links.
	*
	* @return the ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketLink> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket links where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTEI_TSI(long ticketEntryId, long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket links where ticketEntryId = &#63; and visibility = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTEI_V(long ticketEntryId, int visibility)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket links from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket links where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public int countByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the number of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_TSI(long ticketEntryId, long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @return the number of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_V(long ticketEntryId, int visibility)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @return the number of matching ticket links
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_V(long ticketEntryId, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket links.
	*
	* @return the number of ticket links
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
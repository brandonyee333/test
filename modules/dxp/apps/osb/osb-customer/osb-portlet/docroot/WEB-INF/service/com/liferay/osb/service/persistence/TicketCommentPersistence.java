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

import com.liferay.osb.model.TicketComment;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket comment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCommentPersistenceImpl
 * @see TicketCommentUtil
 * @generated
 */
public interface TicketCommentPersistence extends BasePersistence<TicketComment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketCommentUtil} to access the ticket comment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ticket comment in the entity cache if it is enabled.
	*
	* @param ticketComment the ticket comment
	*/
	public void cacheResult(com.liferay.osb.model.TicketComment ticketComment);

	/**
	* Caches the ticket comments in the entity cache if it is enabled.
	*
	* @param ticketComments the ticket comments
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TicketComment> ticketComments);

	/**
	* Creates a new ticket comment with the primary key. Does not add the ticket comment to the database.
	*
	* @param ticketCommentId the primary key for the new ticket comment
	* @return the new ticket comment
	*/
	public com.liferay.osb.model.TicketComment create(long ticketCommentId);

	/**
	* Removes the ticket comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment that was removed
	* @throws com.liferay.osb.NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment remove(long ticketCommentId)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketComment updateImpl(
		com.liferay.osb.model.TicketComment ticketComment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket comment with the primary key or throws a {@link com.liferay.osb.NoSuchTicketCommentException} if it could not be found.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment findByPrimaryKey(
		long ticketCommentId)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket comment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment, or <code>null</code> if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment fetchByPrimaryKey(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket comments where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTicketEntryId(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket comments where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTicketEntryId(
		long ticketEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment findByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment fetchByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment findByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment fetchByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket comments before and after the current ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketCommentId the primary key of the current ticket comment
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment[] findByTicketEntryId_PrevAndNext(
		long ticketCommentId, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTEI_T(
		long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTEI_T(
		long ticketEntryId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTEI_T(
		long ticketEntryId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment findByTEI_T_First(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment fetchByTEI_T_First(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment findByTEI_T_Last(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment fetchByTEI_T_Last(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket comments before and after the current ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketCommentId the primary key of the current ticket comment
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment[] findByTEI_T_PrevAndNext(
		long ticketCommentId, long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int visibility, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int visibility, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment findByTEI_V_S_First(
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment fetchByTEI_V_S_First(
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket comment in the ordered set where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment findByTEI_V_S_Last(
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket comment in the ordered set where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment fetchByTEI_V_S_Last(
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket comments before and after the current ticket comment in the ordered set where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketCommentId the primary key of the current ticket comment
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment[] findByTEI_V_S_PrevAndNext(
		long ticketCommentId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @return the matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int[] visibilities, int[] statuses, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int[] visibilities, int[] statuses, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int visibility, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int visibility, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment findByU_TEI_V_S_First(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment fetchByU_TEI_V_S_First(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment findByU_TEI_V_S_Last(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment fetchByU_TEI_V_S_Last(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket comments before and after the current ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketCommentId the primary key of the current ticket comment
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment[] findByU_TEI_V_S_PrevAndNext(
		long ticketCommentId, long userId, long ticketEntryId, int visibility,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @return the matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param type the type
	* @return the matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S_T(
		long userId, long ticketEntryId, int visibility, int status, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param type the type
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S_T(
		long userId, long ticketEntryId, int visibility, int status, int type,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param type the type
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S_T(
		long userId, long ticketEntryId, int visibility, int status, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment findByU_TEI_V_S_T_First(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment fetchByU_TEI_V_S_T_First(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment findByU_TEI_V_S_T_Last(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment fetchByU_TEI_V_S_T_Last(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket comments before and after the current ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* @param ticketCommentId the primary key of the current ticket comment
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment[] findByU_TEI_V_S_T_PrevAndNext(
		long ticketCommentId, long userId, long ticketEntryId, int visibility,
		int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket comments.
	*
	* @return the ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket comments where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket comments where ticketEntryId = &#63; and type = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTEI_T(long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTEI_V_S(long ticketEntryId, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_TEI_V_S_T(long userId, long ticketEntryId,
		int visibility, int status, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket comments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the number of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_T(long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_V_S(long ticketEntryId, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @return the number of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_V_S(long ticketEntryId, int[] visibilities,
		int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @return the number of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_TEI_V_S(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param type the type
	* @return the number of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_TEI_V_S_T(long userId, long ticketEntryId,
		int visibility, int status, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket comments.
	*
	* @return the number of ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
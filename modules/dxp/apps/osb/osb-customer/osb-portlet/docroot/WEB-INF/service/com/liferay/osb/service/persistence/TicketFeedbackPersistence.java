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

import com.liferay.osb.model.TicketFeedback;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket feedback service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedbackPersistenceImpl
 * @see TicketFeedbackUtil
 * @generated
 */
public interface TicketFeedbackPersistence extends BasePersistence<TicketFeedback> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketFeedbackUtil} to access the ticket feedback persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ticket feedback in the entity cache if it is enabled.
	*
	* @param ticketFeedback the ticket feedback
	*/
	public void cacheResult(com.liferay.osb.model.TicketFeedback ticketFeedback);

	/**
	* Caches the ticket feedbacks in the entity cache if it is enabled.
	*
	* @param ticketFeedbacks the ticket feedbacks
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TicketFeedback> ticketFeedbacks);

	/**
	* Creates a new ticket feedback with the primary key. Does not add the ticket feedback to the database.
	*
	* @param ticketFeedbackId the primary key for the new ticket feedback
	* @return the new ticket feedback
	*/
	public com.liferay.osb.model.TicketFeedback create(long ticketFeedbackId);

	/**
	* Removes the ticket feedback with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback that was removed
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback remove(long ticketFeedbackId)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketFeedback updateImpl(
		com.liferay.osb.model.TicketFeedback ticketFeedback, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket feedback with the primary key or throws a {@link com.liferay.osb.NoSuchTicketFeedbackException} if it could not be found.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback findByPrimaryKey(
		long ticketFeedbackId)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket feedback with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback, or <code>null</code> if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback fetchByPrimaryKey(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket feedbacks where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTicketEntryId(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket feedbacks where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @return the range of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTicketEntryId(
		long ticketEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket feedback in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback findByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket feedback in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback fetchByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket feedback in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback findByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket feedback in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback fetchByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketFeedbackId the primary key of the current ticket feedback
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback[] findByTicketEntryId_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @return the matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S(
		long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @return the range of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S(
		long ticketEntryId, int subject, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S(
		long ticketEntryId, int subject, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback findByTEI_S_First(
		long ticketEntryId, int subject,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback fetchByTEI_S_First(
		long ticketEntryId, int subject,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback findByTEI_S_Last(
		long ticketEntryId, int subject,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback fetchByTEI_S_Last(
		long ticketEntryId, int subject,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	*
	* @param ticketFeedbackId the primary key of the current ticket feedback
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback[] findByTEI_S_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId, int subject,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @return the matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_NotS(
		long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @return the range of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_NotS(
		long ticketEntryId, int subject, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_NotS(
		long ticketEntryId, int subject, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback findByTEI_S_NotS_First(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback fetchByTEI_S_NotS_First(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback findByTEI_S_NotS_Last(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback fetchByTEI_S_NotS_Last(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param ticketFeedbackId the primary key of the current ticket feedback
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback[] findByTEI_S_NotS_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @return the matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_S(
		long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @return the range of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_S(
		long ticketEntryId, int subject, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_S(
		long ticketEntryId, int subject, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback findByTEI_S_S_First(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback fetchByTEI_S_S_First(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback findByTEI_S_S_Last(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback fetchByTEI_S_S_Last(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	*
	* @param ticketFeedbackId the primary key of the current ticket feedback
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback[] findByTEI_S_S_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @return the matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByU_TEI_S_NotS(
		long userId, long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @return the range of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByU_TEI_S_NotS(
		long userId, long ticketEntryId, int subject, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findByU_TEI_S_NotS(
		long userId, long ticketEntryId, int subject, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback findByU_TEI_S_NotS_First(
		long userId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback fetchByU_TEI_S_NotS_First(
		long userId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback findByU_TEI_S_NotS_Last(
		long userId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback fetchByU_TEI_S_NotS_Last(
		long userId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param ticketFeedbackId the primary key of the current ticket feedback
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFeedback[] findByU_TEI_S_NotS_PrevAndNext(
		long ticketFeedbackId, long userId, long ticketEntryId, int subject,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket feedbacks.
	*
	* @return the ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket feedbacks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @return the range of ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket feedbacks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket feedbacks
	* @param end the upper bound of the range of ticket feedbacks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFeedback> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket feedbacks where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTEI_S(long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTEI_S_NotS(long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTEI_S_S(long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_TEI_S_NotS(long userId, long ticketEntryId,
		int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket feedbacks from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket feedbacks where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public int countByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @return the number of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_S(long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @return the number of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_S_NotS(long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @return the number of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_S_S(long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @return the number of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_TEI_S_NotS(long userId, long ticketEntryId,
		int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket feedbacks.
	*
	* @return the number of ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
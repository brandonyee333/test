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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ticket feedback service. This utility wraps {@link TicketFeedbackPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedbackPersistence
 * @see TicketFeedbackPersistenceImpl
 * @generated
 */
public class TicketFeedbackUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(TicketFeedback ticketFeedback) {
		getPersistence().clearCache(ticketFeedback);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TicketFeedback> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketFeedback> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketFeedback> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TicketFeedback update(TicketFeedback ticketFeedback,
		boolean merge) throws SystemException {
		return getPersistence().update(ticketFeedback, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TicketFeedback update(TicketFeedback ticketFeedback,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(ticketFeedback, merge, serviceContext);
	}

	/**
	* Caches the ticket feedback in the entity cache if it is enabled.
	*
	* @param ticketFeedback the ticket feedback
	*/
	public static void cacheResult(
		com.liferay.osb.model.TicketFeedback ticketFeedback) {
		getPersistence().cacheResult(ticketFeedback);
	}

	/**
	* Caches the ticket feedbacks in the entity cache if it is enabled.
	*
	* @param ticketFeedbacks the ticket feedbacks
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TicketFeedback> ticketFeedbacks) {
		getPersistence().cacheResult(ticketFeedbacks);
	}

	/**
	* Creates a new ticket feedback with the primary key. Does not add the ticket feedback to the database.
	*
	* @param ticketFeedbackId the primary key for the new ticket feedback
	* @return the new ticket feedback
	*/
	public static com.liferay.osb.model.TicketFeedback create(
		long ticketFeedbackId) {
		return getPersistence().create(ticketFeedbackId);
	}

	/**
	* Removes the ticket feedback with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback that was removed
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback remove(
		long ticketFeedbackId)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(ticketFeedbackId);
	}

	public static com.liferay.osb.model.TicketFeedback updateImpl(
		com.liferay.osb.model.TicketFeedback ticketFeedback, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(ticketFeedback, merge);
	}

	/**
	* Returns the ticket feedback with the primary key or throws a {@link com.liferay.osb.NoSuchTicketFeedbackException} if it could not be found.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback findByPrimaryKey(
		long ticketFeedbackId)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(ticketFeedbackId);
	}

	/**
	* Returns the ticket feedback with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketFeedbackId the primary key of the ticket feedback
	* @return the ticket feedback, or <code>null</code> if a ticket feedback with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback fetchByPrimaryKey(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(ticketFeedbackId);
	}

	/**
	* Returns all the ticket feedbacks where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTicketEntryId(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTicketEntryId(ticketEntryId);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTicketEntryId(
		long ticketEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTicketEntryId(ticketEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first ticket feedback in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback findByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the first ticket feedback in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback fetchByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket feedback in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback
	* @throws com.liferay.osb.NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback findByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTicketEntryId_Last(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket feedback in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback fetchByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTicketEntryId_Last(ticketEntryId, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback[] findByTicketEntryId_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTicketEntryId_PrevAndNext(ticketFeedbackId,
			ticketEntryId, orderByComparator);
	}

	/**
	* Returns all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @return the matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S(
		long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTEI_S(ticketEntryId, subject);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S(
		long ticketEntryId, int subject, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTEI_S(ticketEntryId, subject, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S(
		long ticketEntryId, int subject, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S(ticketEntryId, subject, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback findByTEI_S_First(
		long ticketEntryId, int subject,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_First(ticketEntryId, subject, orderByComparator);
	}

	/**
	* Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback fetchByTEI_S_First(
		long ticketEntryId, int subject,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTEI_S_First(ticketEntryId, subject, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback findByTEI_S_Last(
		long ticketEntryId, int subject,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_Last(ticketEntryId, subject, orderByComparator);
	}

	/**
	* Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketFeedback fetchByTEI_S_Last(
		long ticketEntryId, int subject,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTEI_S_Last(ticketEntryId, subject, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback[] findByTEI_S_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId, int subject,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_PrevAndNext(ticketFeedbackId, ticketEntryId,
			subject, orderByComparator);
	}

	/**
	* Returns all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @return the matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_NotS(
		long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTEI_S_NotS(ticketEntryId, subject, status);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_NotS(
		long ticketEntryId, int subject, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_NotS(ticketEntryId, subject, status, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_NotS(
		long ticketEntryId, int subject, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_NotS(ticketEntryId, subject, status, start,
			end, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback findByTEI_S_NotS_First(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_NotS_First(ticketEntryId, subject, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback fetchByTEI_S_NotS_First(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTEI_S_NotS_First(ticketEntryId, subject, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback findByTEI_S_NotS_Last(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_NotS_Last(ticketEntryId, subject, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback fetchByTEI_S_NotS_Last(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTEI_S_NotS_Last(ticketEntryId, subject, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback[] findByTEI_S_NotS_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_NotS_PrevAndNext(ticketFeedbackId,
			ticketEntryId, subject, status, orderByComparator);
	}

	/**
	* Returns all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @return the matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_S(
		long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTEI_S_S(ticketEntryId, subject, status);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_S(
		long ticketEntryId, int subject, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_S(ticketEntryId, subject, status, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByTEI_S_S(
		long ticketEntryId, int subject, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_S(ticketEntryId, subject, status, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback findByTEI_S_S_First(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_S_First(ticketEntryId, subject, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback fetchByTEI_S_S_First(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTEI_S_S_First(ticketEntryId, subject, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback findByTEI_S_S_Last(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_S_Last(ticketEntryId, subject, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback fetchByTEI_S_S_Last(
		long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTEI_S_S_Last(ticketEntryId, subject, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback[] findByTEI_S_S_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_S_S_PrevAndNext(ticketFeedbackId, ticketEntryId,
			subject, status, orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByU_TEI_S_NotS(
		long userId, long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_S_NotS(userId, ticketEntryId, subject, status);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByU_TEI_S_NotS(
		long userId, long ticketEntryId, int subject, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_S_NotS(userId, ticketEntryId, subject, status,
			start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByU_TEI_S_NotS(
		long userId, long ticketEntryId, int subject, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_S_NotS(userId, ticketEntryId, subject, status,
			start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback findByU_TEI_S_NotS_First(
		long userId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_S_NotS_First(userId, ticketEntryId, subject,
			status, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback fetchByU_TEI_S_NotS_First(
		long userId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_TEI_S_NotS_First(userId, ticketEntryId, subject,
			status, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback findByU_TEI_S_NotS_Last(
		long userId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_S_NotS_Last(userId, ticketEntryId, subject,
			status, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback fetchByU_TEI_S_NotS_Last(
		long userId, long ticketEntryId, int subject, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_TEI_S_NotS_Last(userId, ticketEntryId, subject,
			status, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketFeedback[] findByU_TEI_S_NotS_PrevAndNext(
		long ticketFeedbackId, long userId, long ticketEntryId, int subject,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFeedbackException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_S_NotS_PrevAndNext(ticketFeedbackId, userId,
			ticketEntryId, subject, status, orderByComparator);
	}

	/**
	* Returns all the ticket feedbacks.
	*
	* @return the ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketFeedback> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the ticket feedbacks where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTicketEntryId(ticketEntryId);
	}

	/**
	* Removes all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTEI_S(long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTEI_S(ticketEntryId, subject);
	}

	/**
	* Removes all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTEI_S_NotS(long ticketEntryId, int subject,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTEI_S_NotS(ticketEntryId, subject, status);
	}

	/**
	* Removes all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTEI_S_S(long ticketEntryId, int subject,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTEI_S_S(ticketEntryId, subject, status);
	}

	/**
	* Removes all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_TEI_S_NotS(long userId, long ticketEntryId,
		int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByU_TEI_S_NotS(userId, ticketEntryId, subject, status);
	}

	/**
	* Removes all the ticket feedbacks from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket feedbacks where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the number of ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @return the number of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_S(long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTEI_S(ticketEntryId, subject);
	}

	/**
	* Returns the number of ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @return the number of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_S_NotS(long ticketEntryId, int subject,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTEI_S_NotS(ticketEntryId, subject, status);
	}

	/**
	* Returns the number of ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param subject the subject
	* @param status the status
	* @return the number of matching ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_S_S(long ticketEntryId, int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTEI_S_S(ticketEntryId, subject, status);
	}

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
	public static int countByU_TEI_S_NotS(long userId, long ticketEntryId,
		int subject, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByU_TEI_S_NotS(userId, ticketEntryId, subject, status);
	}

	/**
	* Returns the number of ticket feedbacks.
	*
	* @return the number of ticket feedbacks
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TicketFeedbackPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TicketFeedbackPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketFeedbackPersistence.class.getName());

			ReferenceRegistry.registerReference(TicketFeedbackUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TicketFeedbackPersistence persistence) {
	}

	private static TicketFeedbackPersistence _persistence;
}
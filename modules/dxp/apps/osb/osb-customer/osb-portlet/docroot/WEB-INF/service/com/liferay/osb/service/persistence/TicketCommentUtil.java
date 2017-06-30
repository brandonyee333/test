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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ticket comment service. This utility wraps {@link TicketCommentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCommentPersistence
 * @see TicketCommentPersistenceImpl
 * @generated
 */
public class TicketCommentUtil {
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
	public static void clearCache(TicketComment ticketComment) {
		getPersistence().clearCache(ticketComment);
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
	public static List<TicketComment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketComment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketComment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TicketComment update(TicketComment ticketComment,
		boolean merge) throws SystemException {
		return getPersistence().update(ticketComment, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TicketComment update(TicketComment ticketComment,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(ticketComment, merge, serviceContext);
	}

	/**
	* Caches the ticket comment in the entity cache if it is enabled.
	*
	* @param ticketComment the ticket comment
	*/
	public static void cacheResult(
		com.liferay.osb.model.TicketComment ticketComment) {
		getPersistence().cacheResult(ticketComment);
	}

	/**
	* Caches the ticket comments in the entity cache if it is enabled.
	*
	* @param ticketComments the ticket comments
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TicketComment> ticketComments) {
		getPersistence().cacheResult(ticketComments);
	}

	/**
	* Creates a new ticket comment with the primary key. Does not add the ticket comment to the database.
	*
	* @param ticketCommentId the primary key for the new ticket comment
	* @return the new ticket comment
	*/
	public static com.liferay.osb.model.TicketComment create(
		long ticketCommentId) {
		return getPersistence().create(ticketCommentId);
	}

	/**
	* Removes the ticket comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment that was removed
	* @throws com.liferay.osb.NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment remove(
		long ticketCommentId)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(ticketCommentId);
	}

	public static com.liferay.osb.model.TicketComment updateImpl(
		com.liferay.osb.model.TicketComment ticketComment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(ticketComment, merge);
	}

	/**
	* Returns the ticket comment with the primary key or throws a {@link com.liferay.osb.NoSuchTicketCommentException} if it could not be found.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment findByPrimaryKey(
		long ticketCommentId)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(ticketCommentId);
	}

	/**
	* Returns the ticket comment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment, or <code>null</code> if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment fetchByPrimaryKey(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(ticketCommentId);
	}

	/**
	* Returns all the ticket comments where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTicketEntryId(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTicketEntryId(ticketEntryId);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTicketEntryId(
		long ticketEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTicketEntryId(ticketEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment findByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment fetchByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment
	* @throws com.liferay.osb.NoSuchTicketCommentException if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment findByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTicketEntryId_Last(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment fetchByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTicketEntryId_Last(ticketEntryId, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment[] findByTicketEntryId_PrevAndNext(
		long ticketCommentId, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTicketEntryId_PrevAndNext(ticketCommentId,
			ticketEntryId, orderByComparator);
	}

	/**
	* Returns all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTEI_T(
		long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTEI_T(ticketEntryId, type);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTEI_T(
		long ticketEntryId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTEI_T(ticketEntryId, type, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTEI_T(
		long ticketEntryId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T(ticketEntryId, type, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment findByTEI_T_First(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_First(ticketEntryId, type, orderByComparator);
	}

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment fetchByTEI_T_First(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTEI_T_First(ticketEntryId, type, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment findByTEI_T_Last(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_Last(ticketEntryId, type, orderByComparator);
	}

	/**
	* Returns the last ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketComment fetchByTEI_T_Last(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTEI_T_Last(ticketEntryId, type, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment[] findByTEI_T_PrevAndNext(
		long ticketCommentId, long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_T_PrevAndNext(ticketCommentId, ticketEntryId,
			type, orderByComparator);
	}

	/**
	* Returns all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTEI_V_S(ticketEntryId, visibility, status);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int visibility, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibility, status, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int visibility, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibility, status, start,
			end, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment findByTEI_V_S_First(
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_V_S_First(ticketEntryId, visibility, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment fetchByTEI_V_S_First(
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTEI_V_S_First(ticketEntryId, visibility, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment findByTEI_V_S_Last(
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_V_S_Last(ticketEntryId, visibility, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment fetchByTEI_V_S_Last(
		long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTEI_V_S_Last(ticketEntryId, visibility, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment[] findByTEI_V_S_PrevAndNext(
		long ticketCommentId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_V_S_PrevAndNext(ticketCommentId, ticketEntryId,
			visibility, status, orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibilities, statuses);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int[] visibilities, int[] statuses, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibilities, statuses, start,
			end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByTEI_V_S(
		long ticketEntryId, int[] visibilities, int[] statuses, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibilities, statuses, start,
			end, orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int visibility, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int visibility, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment findByU_TEI_V_S_First(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S_First(userId, ticketEntryId, visibility,
			status, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment fetchByU_TEI_V_S_First(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_TEI_V_S_First(userId, ticketEntryId, visibility,
			status, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment findByU_TEI_V_S_Last(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S_Last(userId, ticketEntryId, visibility,
			status, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment fetchByU_TEI_V_S_Last(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_TEI_V_S_Last(userId, ticketEntryId, visibility,
			status, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment[] findByU_TEI_V_S_PrevAndNext(
		long ticketCommentId, long userId, long ticketEntryId, int visibility,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S_PrevAndNext(ticketCommentId, userId,
			ticketEntryId, visibility, status, orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibilities,
			statuses);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibilities,
			statuses, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibilities,
			statuses, start, end, orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S_T(
		long userId, long ticketEntryId, int visibility, int status, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S_T(userId, ticketEntryId, visibility,
			status, type);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S_T(
		long userId, long ticketEntryId, int visibility, int status, int type,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S_T(userId, ticketEntryId, visibility,
			status, type, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findByU_TEI_V_S_T(
		long userId, long ticketEntryId, int visibility, int status, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S_T(userId, ticketEntryId, visibility,
			status, type, start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment findByU_TEI_V_S_T_First(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S_T_First(userId, ticketEntryId, visibility,
			status, type, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment fetchByU_TEI_V_S_T_First(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_TEI_V_S_T_First(userId, ticketEntryId, visibility,
			status, type, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment findByU_TEI_V_S_T_Last(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S_T_Last(userId, ticketEntryId, visibility,
			status, type, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment fetchByU_TEI_V_S_T_Last(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_TEI_V_S_T_Last(userId, ticketEntryId, visibility,
			status, type, orderByComparator);
	}

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
	public static com.liferay.osb.model.TicketComment[] findByU_TEI_V_S_T_PrevAndNext(
		long ticketCommentId, long userId, long ticketEntryId, int visibility,
		int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketCommentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_TEI_V_S_T_PrevAndNext(ticketCommentId, userId,
			ticketEntryId, visibility, status, type, orderByComparator);
	}

	/**
	* Returns all the ticket comments.
	*
	* @return the ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketComment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TicketComment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the ticket comments where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTicketEntryId(ticketEntryId);
	}

	/**
	* Removes all the ticket comments where ticketEntryId = &#63; and type = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTEI_T(long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTEI_T(ticketEntryId, type);
	}

	/**
	* Removes all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTEI_V_S(long ticketEntryId, int visibility,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTEI_V_S(ticketEntryId, visibility, status);
	}

	/**
	* Removes all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByU_TEI_V_S(userId, ticketEntryId, visibility, status);
	}

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
	public static void removeByU_TEI_V_S_T(long userId, long ticketEntryId,
		int visibility, int status, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByU_TEI_V_S_T(userId, ticketEntryId, visibility, status, type);
	}

	/**
	* Removes all the ticket comments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the number of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_T(long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTEI_T(ticketEntryId, type);
	}

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_V_S(long ticketEntryId, int visibility,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTEI_V_S(ticketEntryId, visibility, status);
	}

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @return the number of matching ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTEI_V_S(long ticketEntryId, int[] visibilities,
		int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByTEI_V_S(ticketEntryId, visibilities, statuses);
	}

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
	public static int countByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByU_TEI_V_S(userId, ticketEntryId, visibility, status);
	}

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
	public static int countByU_TEI_V_S(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByU_TEI_V_S(userId, ticketEntryId, visibilities,
			statuses);
	}

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
	public static int countByU_TEI_V_S_T(long userId, long ticketEntryId,
		int visibility, int status, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByU_TEI_V_S_T(userId, ticketEntryId, visibility,
			status, type);
	}

	/**
	* Returns the number of ticket comments.
	*
	* @return the number of ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TicketCommentPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TicketCommentPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketCommentPersistence.class.getName());

			ReferenceRegistry.registerReference(TicketCommentUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TicketCommentPersistence persistence) {
	}

	private static TicketCommentPersistence _persistence;
}
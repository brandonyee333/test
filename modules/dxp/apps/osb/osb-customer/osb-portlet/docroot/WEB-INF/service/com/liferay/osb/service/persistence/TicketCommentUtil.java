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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.TicketComment;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the ticket comment service. This utility wraps {@link com.liferay.osb.service.persistence.impl.TicketCommentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCommentPersistence
 * @see com.liferay.osb.service.persistence.impl.TicketCommentPersistenceImpl
 * @generated
 */
@ProviderType
public class TicketCommentUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(TicketComment ticketComment) {
		getPersistence().clearCache(ticketComment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TicketComment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketComment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketComment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TicketComment> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TicketComment update(TicketComment ticketComment) {
		return getPersistence().update(ticketComment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TicketComment update(TicketComment ticketComment,
		ServiceContext serviceContext) {
		return getPersistence().update(ticketComment, serviceContext);
	}

	/**
	* Returns all the ticket comments where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket comments
	*/
	public static List<TicketComment> findByTicketEntryId(long ticketEntryId) {
		return getPersistence().findByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns a range of all the ticket comments where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	*/
	public static List<TicketComment> findByTicketEntryId(long ticketEntryId,
		int start, int end) {
		return getPersistence().findByTicketEntryId(ticketEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket comments
	*/
	public static List<TicketComment> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketComment> orderByComparator) {
		return getPersistence()
				   .findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket comments
	*/
	public static List<TicketComment> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment
	* @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	*/
	public static TicketComment findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
		return getPersistence()
				   .findByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	*/
	public static TicketComment fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketComment> orderByComparator) {
		return getPersistence()
				   .fetchByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment
	* @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	*/
	public static TicketComment findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
		return getPersistence()
				   .findByTicketEntryId_Last(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket comment in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	*/
	public static TicketComment fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketComment> orderByComparator) {
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
	* @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	*/
	public static TicketComment[] findByTicketEntryId_PrevAndNext(
		long ticketCommentId, long ticketEntryId,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
		return getPersistence()
				   .findByTicketEntryId_PrevAndNext(ticketCommentId,
			ticketEntryId, orderByComparator);
	}

	/**
	* Removes all the ticket comments where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	*/
	public static void removeByTicketEntryId(long ticketEntryId) {
		getPersistence().removeByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket comments
	*/
	public static int countByTicketEntryId(long ticketEntryId) {
		return getPersistence().countByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the matching ticket comments
	*/
	public static List<TicketComment> findByTEI_T(long ticketEntryId, int type) {
		return getPersistence().findByTEI_T(ticketEntryId, type);
	}

	/**
	* Returns a range of all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	*/
	public static List<TicketComment> findByTEI_T(long ticketEntryId, int type,
		int start, int end) {
		return getPersistence().findByTEI_T(ticketEntryId, type, start, end);
	}

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket comments
	*/
	public static List<TicketComment> findByTEI_T(long ticketEntryId, int type,
		int start, int end, OrderByComparator<TicketComment> orderByComparator) {
		return getPersistence()
				   .findByTEI_T(ticketEntryId, type, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket comments
	*/
	public static List<TicketComment> findByTEI_T(long ticketEntryId, int type,
		int start, int end, OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_T(ticketEntryId, type, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment
	* @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	*/
	public static TicketComment findByTEI_T_First(long ticketEntryId, int type,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
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
	*/
	public static TicketComment fetchByTEI_T_First(long ticketEntryId,
		int type, OrderByComparator<TicketComment> orderByComparator) {
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
	* @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	*/
	public static TicketComment findByTEI_T_Last(long ticketEntryId, int type,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
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
	*/
	public static TicketComment fetchByTEI_T_Last(long ticketEntryId, int type,
		OrderByComparator<TicketComment> orderByComparator) {
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
	* @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	*/
	public static TicketComment[] findByTEI_T_PrevAndNext(
		long ticketCommentId, long ticketEntryId, int type,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
		return getPersistence()
				   .findByTEI_T_PrevAndNext(ticketCommentId, ticketEntryId,
			type, orderByComparator);
	}

	/**
	* Removes all the ticket comments where ticketEntryId = &#63; and type = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	*/
	public static void removeByTEI_T(long ticketEntryId, int type) {
		getPersistence().removeByTEI_T(ticketEntryId, type);
	}

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the number of matching ticket comments
	*/
	public static int countByTEI_T(long ticketEntryId, int type) {
		return getPersistence().countByTEI_T(ticketEntryId, type);
	}

	/**
	* Returns all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket comments
	*/
	public static List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int visibility, int status) {
		return getPersistence().findByTEI_V_S(ticketEntryId, visibility, status);
	}

	/**
	* Returns a range of all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	*/
	public static List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int visibility, int status, int start, int end) {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibility, status, start, end);
	}

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket comments
	*/
	public static List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int visibility, int status, int start, int end,
		OrderByComparator<TicketComment> orderByComparator) {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibility, status, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket comments
	*/
	public static List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int visibility, int status, int start, int end,
		OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibility, status, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket comment in the ordered set where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket comment
	* @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	*/
	public static TicketComment findByTEI_V_S_First(long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
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
	*/
	public static TicketComment fetchByTEI_V_S_First(long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator) {
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
	* @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	*/
	public static TicketComment findByTEI_V_S_Last(long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
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
	*/
	public static TicketComment fetchByTEI_V_S_Last(long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator) {
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
	* @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	*/
	public static TicketComment[] findByTEI_V_S_PrevAndNext(
		long ticketCommentId, long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
		return getPersistence()
				   .findByTEI_V_S_PrevAndNext(ticketCommentId, ticketEntryId,
			visibility, status, orderByComparator);
	}

	/**
	* Returns all the ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @return the matching ticket comments
	*/
	public static List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int[] visibilities, int[] statuses) {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibilities, statuses);
	}

	/**
	* Returns a range of all the ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	*/
	public static List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int[] visibilities, int[] statuses, int start, int end) {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibilities, statuses, start,
			end);
	}

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket comments
	*/
	public static List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int[] visibilities, int[] statuses, int start, int end,
		OrderByComparator<TicketComment> orderByComparator) {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibilities, statuses, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket comments
	*/
	public static List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int[] visibilities, int[] statuses, int start, int end,
		OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_V_S(ticketEntryId, visibilities, statuses, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	*/
	public static void removeByTEI_V_S(long ticketEntryId, int visibility,
		int status) {
		getPersistence().removeByTEI_V_S(ticketEntryId, visibility, status);
	}

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket comments
	*/
	public static int countByTEI_V_S(long ticketEntryId, int visibility,
		int status) {
		return getPersistence().countByTEI_V_S(ticketEntryId, visibility, status);
	}

	/**
	* Returns the number of ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @return the number of matching ticket comments
	*/
	public static int countByTEI_V_S(long ticketEntryId, int[] visibilities,
		int[] statuses) {
		return getPersistence()
				   .countByTEI_V_S(ticketEntryId, visibilities, statuses);
	}

	/**
	* Returns all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the matching ticket comments
	*/
	public static List<TicketComment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status);
	}

	/**
	* Returns a range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	*/
	public static List<TicketComment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public static List<TicketComment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end,
		OrderByComparator<TicketComment> orderByComparator) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket comments
	*/
	public static List<TicketComment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end,
		OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end, orderByComparator, retrieveFromCache);
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
	* @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	*/
	public static TicketComment findByU_TEI_V_S_First(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
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
	*/
	public static TicketComment fetchByU_TEI_V_S_First(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator) {
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
	* @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	*/
	public static TicketComment findByU_TEI_V_S_Last(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
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
	*/
	public static TicketComment fetchByU_TEI_V_S_Last(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator) {
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
	* @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	*/
	public static TicketComment[] findByU_TEI_V_S_PrevAndNext(
		long ticketCommentId, long userId, long ticketEntryId, int visibility,
		int status, OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
		return getPersistence()
				   .findByU_TEI_V_S_PrevAndNext(ticketCommentId, userId,
			ticketEntryId, visibility, status, orderByComparator);
	}

	/**
	* Returns all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @return the matching ticket comments
	*/
	public static List<TicketComment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int[] visibilities, int[] statuses) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibilities,
			statuses);
	}

	/**
	* Returns a range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param statuses the statuses
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of matching ticket comments
	*/
	public static List<TicketComment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int[] visibilities, int[] statuses, int start,
		int end) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibilities,
			statuses, start, end);
	}

	/**
	* Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public static List<TicketComment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int[] visibilities, int[] statuses, int start,
		int end, OrderByComparator<TicketComment> orderByComparator) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibilities,
			statuses, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket comments
	*/
	public static List<TicketComment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int[] visibilities, int[] statuses, int start,
		int end, OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_TEI_V_S(userId, ticketEntryId, visibilities,
			statuses, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	*/
	public static void removeByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status) {
		getPersistence()
			.removeByU_TEI_V_S(userId, ticketEntryId, visibility, status);
	}

	/**
	* Returns the number of ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @return the number of matching ticket comments
	*/
	public static int countByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status) {
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
	*/
	public static int countByU_TEI_V_S(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses) {
		return getPersistence()
				   .countByU_TEI_V_S(userId, ticketEntryId, visibilities,
			statuses);
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
	*/
	public static List<TicketComment> findByU_TEI_V_S_T(long userId,
		long ticketEntryId, int visibility, int status, int type) {
		return getPersistence()
				   .findByU_TEI_V_S_T(userId, ticketEntryId, visibility,
			status, type);
	}

	/**
	* Returns a range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public static List<TicketComment> findByU_TEI_V_S_T(long userId,
		long ticketEntryId, int visibility, int status, int type, int start,
		int end) {
		return getPersistence()
				   .findByU_TEI_V_S_T(userId, ticketEntryId, visibility,
			status, type, start, end);
	}

	/**
	* Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public static List<TicketComment> findByU_TEI_V_S_T(long userId,
		long ticketEntryId, int visibility, int status, int type, int start,
		int end, OrderByComparator<TicketComment> orderByComparator) {
		return getPersistence()
				   .findByU_TEI_V_S_T(userId, ticketEntryId, visibility,
			status, type, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket comments
	*/
	public static List<TicketComment> findByU_TEI_V_S_T(long userId,
		long ticketEntryId, int visibility, int status, int type, int start,
		int end, OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_TEI_V_S_T(userId, ticketEntryId, visibility,
			status, type, start, end, orderByComparator, retrieveFromCache);
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
	* @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	*/
	public static TicketComment findByU_TEI_V_S_T_First(long userId,
		long ticketEntryId, int visibility, int status, int type,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
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
	*/
	public static TicketComment fetchByU_TEI_V_S_T_First(long userId,
		long ticketEntryId, int visibility, int status, int type,
		OrderByComparator<TicketComment> orderByComparator) {
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
	* @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	*/
	public static TicketComment findByU_TEI_V_S_T_Last(long userId,
		long ticketEntryId, int visibility, int status, int type,
		OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
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
	*/
	public static TicketComment fetchByU_TEI_V_S_T_Last(long userId,
		long ticketEntryId, int visibility, int status, int type,
		OrderByComparator<TicketComment> orderByComparator) {
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
	* @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	*/
	public static TicketComment[] findByU_TEI_V_S_T_PrevAndNext(
		long ticketCommentId, long userId, long ticketEntryId, int visibility,
		int status, int type, OrderByComparator<TicketComment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
		return getPersistence()
				   .findByU_TEI_V_S_T_PrevAndNext(ticketCommentId, userId,
			ticketEntryId, visibility, status, type, orderByComparator);
	}

	/**
	* Removes all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param status the status
	* @param type the type
	*/
	public static void removeByU_TEI_V_S_T(long userId, long ticketEntryId,
		int visibility, int status, int type) {
		getPersistence()
			.removeByU_TEI_V_S_T(userId, ticketEntryId, visibility, status, type);
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
	*/
	public static int countByU_TEI_V_S_T(long userId, long ticketEntryId,
		int visibility, int status, int type) {
		return getPersistence()
				   .countByU_TEI_V_S_T(userId, ticketEntryId, visibility,
			status, type);
	}

	/**
	* Caches the ticket comment in the entity cache if it is enabled.
	*
	* @param ticketComment the ticket comment
	*/
	public static void cacheResult(TicketComment ticketComment) {
		getPersistence().cacheResult(ticketComment);
	}

	/**
	* Caches the ticket comments in the entity cache if it is enabled.
	*
	* @param ticketComments the ticket comments
	*/
	public static void cacheResult(List<TicketComment> ticketComments) {
		getPersistence().cacheResult(ticketComments);
	}

	/**
	* Creates a new ticket comment with the primary key. Does not add the ticket comment to the database.
	*
	* @param ticketCommentId the primary key for the new ticket comment
	* @return the new ticket comment
	*/
	public static TicketComment create(long ticketCommentId) {
		return getPersistence().create(ticketCommentId);
	}

	/**
	* Removes the ticket comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment that was removed
	* @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	*/
	public static TicketComment remove(long ticketCommentId)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
		return getPersistence().remove(ticketCommentId);
	}

	public static TicketComment updateImpl(TicketComment ticketComment) {
		return getPersistence().updateImpl(ticketComment);
	}

	/**
	* Returns the ticket comment with the primary key or throws a {@link NoSuchTicketCommentException} if it could not be found.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment
	* @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	*/
	public static TicketComment findByPrimaryKey(long ticketCommentId)
		throws com.liferay.osb.exception.NoSuchTicketCommentException {
		return getPersistence().findByPrimaryKey(ticketCommentId);
	}

	/**
	* Returns the ticket comment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment, or <code>null</code> if a ticket comment with the primary key could not be found
	*/
	public static TicketComment fetchByPrimaryKey(long ticketCommentId) {
		return getPersistence().fetchByPrimaryKey(ticketCommentId);
	}

	public static java.util.Map<java.io.Serializable, TicketComment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ticket comments.
	*
	* @return the ticket comments
	*/
	public static List<TicketComment> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ticket comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of ticket comments
	*/
	public static List<TicketComment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ticket comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket comments
	*/
	public static List<TicketComment> findAll(int start, int end,
		OrderByComparator<TicketComment> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket comments
	*/
	public static List<TicketComment> findAll(int start, int end,
		OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket comments from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket comments.
	*
	* @return the number of ticket comments
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
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

	private static TicketCommentPersistence _persistence;
}
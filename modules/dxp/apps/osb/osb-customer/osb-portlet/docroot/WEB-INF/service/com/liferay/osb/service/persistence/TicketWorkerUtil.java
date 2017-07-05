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

import com.liferay.osb.model.TicketWorker;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the ticket worker service. This utility wraps {@link com.liferay.osb.service.persistence.impl.TicketWorkerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketWorkerPersistence
 * @see com.liferay.osb.service.persistence.impl.TicketWorkerPersistenceImpl
 * @generated
 */
@ProviderType
public class TicketWorkerUtil {
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
	public static void clearCache(TicketWorker ticketWorker) {
		getPersistence().clearCache(ticketWorker);
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
	public static List<TicketWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TicketWorker> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TicketWorker update(TicketWorker ticketWorker) {
		return getPersistence().update(ticketWorker);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TicketWorker update(TicketWorker ticketWorker,
		ServiceContext serviceContext) {
		return getPersistence().update(ticketWorker, serviceContext);
	}

	/**
	* Returns all the ticket workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching ticket workers
	*/
	public static List<TicketWorker> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the ticket workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @return the range of matching ticket workers
	*/
	public static List<TicketWorker> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the ticket workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket workers
	*/
	public static List<TicketWorker> findByUserId(long userId, int start,
		int end, OrderByComparator<TicketWorker> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket workers
	*/
	public static List<TicketWorker> findByUserId(long userId, int start,
		int end, OrderByComparator<TicketWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ticket worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker
	* @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	*/
	public static TicketWorker findByUserId_First(long userId,
		OrderByComparator<TicketWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first ticket worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	*/
	public static TicketWorker fetchByUserId_First(long userId,
		OrderByComparator<TicketWorker> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last ticket worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker
	* @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	*/
	public static TicketWorker findByUserId_Last(long userId,
		OrderByComparator<TicketWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last ticket worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	*/
	public static TicketWorker fetchByUserId_Last(long userId,
		OrderByComparator<TicketWorker> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the ticket workers before and after the current ticket worker in the ordered set where userId = &#63;.
	*
	* @param ticketWorkerId the primary key of the current ticket worker
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket worker
	* @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	*/
	public static TicketWorker[] findByUserId_PrevAndNext(long ticketWorkerId,
		long userId, OrderByComparator<TicketWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence()
				   .findByUserId_PrevAndNext(ticketWorkerId, userId,
			orderByComparator);
	}

	/**
	* Removes all the ticket workers where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of ticket workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching ticket workers
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the ticket workers where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket workers
	*/
	public static List<TicketWorker> findByTicketEntryId(long ticketEntryId) {
		return getPersistence().findByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns a range of all the ticket workers where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @return the range of matching ticket workers
	*/
	public static List<TicketWorker> findByTicketEntryId(long ticketEntryId,
		int start, int end) {
		return getPersistence().findByTicketEntryId(ticketEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the ticket workers where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket workers
	*/
	public static List<TicketWorker> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketWorker> orderByComparator) {
		return getPersistence()
				   .findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket workers where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket workers
	*/
	public static List<TicketWorker> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket worker in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker
	* @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	*/
	public static TicketWorker findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence()
				   .findByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the first ticket worker in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	*/
	public static TicketWorker fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketWorker> orderByComparator) {
		return getPersistence()
				   .fetchByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket worker in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker
	* @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	*/
	public static TicketWorker findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence()
				   .findByTicketEntryId_Last(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket worker in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	*/
	public static TicketWorker fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketWorker> orderByComparator) {
		return getPersistence()
				   .fetchByTicketEntryId_Last(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the ticket workers before and after the current ticket worker in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketWorkerId the primary key of the current ticket worker
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket worker
	* @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	*/
	public static TicketWorker[] findByTicketEntryId_PrevAndNext(
		long ticketWorkerId, long ticketEntryId,
		OrderByComparator<TicketWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence()
				   .findByTicketEntryId_PrevAndNext(ticketWorkerId,
			ticketEntryId, orderByComparator);
	}

	/**
	* Removes all the ticket workers where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	*/
	public static void removeByTicketEntryId(long ticketEntryId) {
		getPersistence().removeByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the number of ticket workers where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket workers
	*/
	public static int countByTicketEntryId(long ticketEntryId) {
		return getPersistence().countByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or throws a {@link NoSuchTicketWorkerException} if it could not be found.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket worker
	* @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	*/
	public static TicketWorker findByU_TEI(long userId, long ticketEntryId)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence().findByU_TEI(userId, ticketEntryId);
	}

	/**
	* Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	*/
	public static TicketWorker fetchByU_TEI(long userId, long ticketEntryId) {
		return getPersistence().fetchByU_TEI(userId, ticketEntryId);
	}

	/**
	* Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	*/
	public static TicketWorker fetchByU_TEI(long userId, long ticketEntryId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_TEI(userId, ticketEntryId, retrieveFromCache);
	}

	/**
	* Removes the ticket worker where userId = &#63; and ticketEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @return the ticket worker that was removed
	*/
	public static TicketWorker removeByU_TEI(long userId, long ticketEntryId)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence().removeByU_TEI(userId, ticketEntryId);
	}

	/**
	* Returns the number of ticket workers where userId = &#63; and ticketEntryId = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket workers
	*/
	public static int countByU_TEI(long userId, long ticketEntryId) {
		return getPersistence().countByU_TEI(userId, ticketEntryId);
	}

	/**
	* Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or throws a {@link NoSuchTicketWorkerException} if it could not be found.
	*
	* @param ticketEntryId the ticket entry ID
	* @param primary the primary
	* @return the matching ticket worker
	* @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	*/
	public static TicketWorker findByTEI_P(long ticketEntryId, boolean primary)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence().findByTEI_P(ticketEntryId, primary);
	}

	/**
	* Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param primary the primary
	* @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	*/
	public static TicketWorker fetchByTEI_P(long ticketEntryId, boolean primary) {
		return getPersistence().fetchByTEI_P(ticketEntryId, primary);
	}

	/**
	* Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param primary the primary
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	*/
	public static TicketWorker fetchByTEI_P(long ticketEntryId,
		boolean primary, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByTEI_P(ticketEntryId, primary, retrieveFromCache);
	}

	/**
	* Removes the ticket worker where ticketEntryId = &#63; and primary = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param primary the primary
	* @return the ticket worker that was removed
	*/
	public static TicketWorker removeByTEI_P(long ticketEntryId, boolean primary)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence().removeByTEI_P(ticketEntryId, primary);
	}

	/**
	* Returns the number of ticket workers where ticketEntryId = &#63; and primary = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param primary the primary
	* @return the number of matching ticket workers
	*/
	public static int countByTEI_P(long ticketEntryId, boolean primary) {
		return getPersistence().countByTEI_P(ticketEntryId, primary);
	}

	/**
	* Returns all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class pk
	* @return the matching ticket workers
	*/
	public static List<TicketWorker> findBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK) {
		return getPersistence().findBySCNI_SCPK(sourceClassNameId, sourceClassPK);
	}

	/**
	* Returns a range of all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class pk
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @return the range of matching ticket workers
	*/
	public static List<TicketWorker> findBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK, int start, int end) {
		return getPersistence()
				   .findBySCNI_SCPK(sourceClassNameId, sourceClassPK, start, end);
	}

	/**
	* Returns an ordered range of all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class pk
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket workers
	*/
	public static List<TicketWorker> findBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK, int start, int end,
		OrderByComparator<TicketWorker> orderByComparator) {
		return getPersistence()
				   .findBySCNI_SCPK(sourceClassNameId, sourceClassPK, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class pk
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket workers
	*/
	public static List<TicketWorker> findBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK, int start, int end,
		OrderByComparator<TicketWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySCNI_SCPK(sourceClassNameId, sourceClassPK, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker
	* @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	*/
	public static TicketWorker findBySCNI_SCPK_First(long sourceClassNameId,
		long sourceClassPK, OrderByComparator<TicketWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence()
				   .findBySCNI_SCPK_First(sourceClassNameId, sourceClassPK,
			orderByComparator);
	}

	/**
	* Returns the first ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	*/
	public static TicketWorker fetchBySCNI_SCPK_First(long sourceClassNameId,
		long sourceClassPK, OrderByComparator<TicketWorker> orderByComparator) {
		return getPersistence()
				   .fetchBySCNI_SCPK_First(sourceClassNameId, sourceClassPK,
			orderByComparator);
	}

	/**
	* Returns the last ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker
	* @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	*/
	public static TicketWorker findBySCNI_SCPK_Last(long sourceClassNameId,
		long sourceClassPK, OrderByComparator<TicketWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence()
				   .findBySCNI_SCPK_Last(sourceClassNameId, sourceClassPK,
			orderByComparator);
	}

	/**
	* Returns the last ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	*/
	public static TicketWorker fetchBySCNI_SCPK_Last(long sourceClassNameId,
		long sourceClassPK, OrderByComparator<TicketWorker> orderByComparator) {
		return getPersistence()
				   .fetchBySCNI_SCPK_Last(sourceClassNameId, sourceClassPK,
			orderByComparator);
	}

	/**
	* Returns the ticket workers before and after the current ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param ticketWorkerId the primary key of the current ticket worker
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket worker
	* @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	*/
	public static TicketWorker[] findBySCNI_SCPK_PrevAndNext(
		long ticketWorkerId, long sourceClassNameId, long sourceClassPK,
		OrderByComparator<TicketWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence()
				   .findBySCNI_SCPK_PrevAndNext(ticketWorkerId,
			sourceClassNameId, sourceClassPK, orderByComparator);
	}

	/**
	* Removes all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63; from the database.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class pk
	*/
	public static void removeBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK) {
		getPersistence().removeBySCNI_SCPK(sourceClassNameId, sourceClassPK);
	}

	/**
	* Returns the number of ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class pk
	* @return the number of matching ticket workers
	*/
	public static int countBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK) {
		return getPersistence()
				   .countBySCNI_SCPK(sourceClassNameId, sourceClassPK);
	}

	/**
	* Caches the ticket worker in the entity cache if it is enabled.
	*
	* @param ticketWorker the ticket worker
	*/
	public static void cacheResult(TicketWorker ticketWorker) {
		getPersistence().cacheResult(ticketWorker);
	}

	/**
	* Caches the ticket workers in the entity cache if it is enabled.
	*
	* @param ticketWorkers the ticket workers
	*/
	public static void cacheResult(List<TicketWorker> ticketWorkers) {
		getPersistence().cacheResult(ticketWorkers);
	}

	/**
	* Creates a new ticket worker with the primary key. Does not add the ticket worker to the database.
	*
	* @param ticketWorkerId the primary key for the new ticket worker
	* @return the new ticket worker
	*/
	public static TicketWorker create(long ticketWorkerId) {
		return getPersistence().create(ticketWorkerId);
	}

	/**
	* Removes the ticket worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketWorkerId the primary key of the ticket worker
	* @return the ticket worker that was removed
	* @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	*/
	public static TicketWorker remove(long ticketWorkerId)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence().remove(ticketWorkerId);
	}

	public static TicketWorker updateImpl(TicketWorker ticketWorker) {
		return getPersistence().updateImpl(ticketWorker);
	}

	/**
	* Returns the ticket worker with the primary key or throws a {@link NoSuchTicketWorkerException} if it could not be found.
	*
	* @param ticketWorkerId the primary key of the ticket worker
	* @return the ticket worker
	* @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	*/
	public static TicketWorker findByPrimaryKey(long ticketWorkerId)
		throws com.liferay.osb.exception.NoSuchTicketWorkerException {
		return getPersistence().findByPrimaryKey(ticketWorkerId);
	}

	/**
	* Returns the ticket worker with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketWorkerId the primary key of the ticket worker
	* @return the ticket worker, or <code>null</code> if a ticket worker with the primary key could not be found
	*/
	public static TicketWorker fetchByPrimaryKey(long ticketWorkerId) {
		return getPersistence().fetchByPrimaryKey(ticketWorkerId);
	}

	public static java.util.Map<java.io.Serializable, TicketWorker> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ticket workers.
	*
	* @return the ticket workers
	*/
	public static List<TicketWorker> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ticket workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @return the range of ticket workers
	*/
	public static List<TicketWorker> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ticket workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket workers
	*/
	public static List<TicketWorker> findAll(int start, int end,
		OrderByComparator<TicketWorker> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket workers
	*/
	public static List<TicketWorker> findAll(int start, int end,
		OrderByComparator<TicketWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket workers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket workers.
	*
	* @return the number of ticket workers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TicketWorkerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TicketWorkerPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketWorkerPersistence.class.getName());

			ReferenceRegistry.registerReference(TicketWorkerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static TicketWorkerPersistence _persistence;
}
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

import com.liferay.osb.model.PartnerWorker;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the partner worker service. This utility wraps {@link com.liferay.osb.service.persistence.impl.PartnerWorkerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorkerPersistence
 * @see com.liferay.osb.service.persistence.impl.PartnerWorkerPersistenceImpl
 * @generated
 */
@ProviderType
public class PartnerWorkerUtil {
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
	public static void clearCache(PartnerWorker partnerWorker) {
		getPersistence().clearCache(partnerWorker);
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
	public static List<PartnerWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PartnerWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PartnerWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PartnerWorker update(PartnerWorker partnerWorker) {
		return getPersistence().update(partnerWorker);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PartnerWorker update(PartnerWorker partnerWorker,
		ServiceContext serviceContext) {
		return getPersistence().update(partnerWorker, serviceContext);
	}

	/**
	* Returns all the partner workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching partner workers
	*/
	public static List<PartnerWorker> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the partner workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of matching partner workers
	*/
	public static List<PartnerWorker> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the partner workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner workers
	*/
	public static List<PartnerWorker> findByUserId(long userId, int start,
		int end, OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the partner workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching partner workers
	*/
	public static List<PartnerWorker> findByUserId(long userId, int start,
		int end, OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public static PartnerWorker findByUserId_First(long userId,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public static PartnerWorker fetchByUserId_First(long userId,
		OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public static PartnerWorker findByUserId_Last(long userId,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public static PartnerWorker fetchByUserId_Last(long userId,
		OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the partner workers before and after the current partner worker in the ordered set where userId = &#63;.
	*
	* @param partnerWorkerId the primary key of the current partner worker
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner worker
	* @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	*/
	public static PartnerWorker[] findByUserId_PrevAndNext(
		long partnerWorkerId, long userId,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence()
				   .findByUserId_PrevAndNext(partnerWorkerId, userId,
			orderByComparator);
	}

	/**
	* Removes all the partner workers where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of partner workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching partner workers
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the partner workers where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the matching partner workers
	*/
	public static List<PartnerWorker> findByPartnerEntryId(long partnerEntryId) {
		return getPersistence().findByPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns a range of all the partner workers where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of matching partner workers
	*/
	public static List<PartnerWorker> findByPartnerEntryId(
		long partnerEntryId, int start, int end) {
		return getPersistence().findByPartnerEntryId(partnerEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the partner workers where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner workers
	*/
	public static List<PartnerWorker> findByPartnerEntryId(
		long partnerEntryId, int start, int end,
		OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence()
				   .findByPartnerEntryId(partnerEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the partner workers where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching partner workers
	*/
	public static List<PartnerWorker> findByPartnerEntryId(
		long partnerEntryId, int start, int end,
		OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPartnerEntryId(partnerEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public static PartnerWorker findByPartnerEntryId_First(
		long partnerEntryId, OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence()
				   .findByPartnerEntryId_First(partnerEntryId, orderByComparator);
	}

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public static PartnerWorker fetchByPartnerEntryId_First(
		long partnerEntryId, OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence()
				   .fetchByPartnerEntryId_First(partnerEntryId,
			orderByComparator);
	}

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public static PartnerWorker findByPartnerEntryId_Last(long partnerEntryId,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence()
				   .findByPartnerEntryId_Last(partnerEntryId, orderByComparator);
	}

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public static PartnerWorker fetchByPartnerEntryId_Last(
		long partnerEntryId, OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence()
				   .fetchByPartnerEntryId_Last(partnerEntryId, orderByComparator);
	}

	/**
	* Returns the partner workers before and after the current partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerWorkerId the primary key of the current partner worker
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner worker
	* @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	*/
	public static PartnerWorker[] findByPartnerEntryId_PrevAndNext(
		long partnerWorkerId, long partnerEntryId,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence()
				   .findByPartnerEntryId_PrevAndNext(partnerWorkerId,
			partnerEntryId, orderByComparator);
	}

	/**
	* Removes all the partner workers where partnerEntryId = &#63; from the database.
	*
	* @param partnerEntryId the partner entry ID
	*/
	public static void removeByPartnerEntryId(long partnerEntryId) {
		getPersistence().removeByPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns the number of partner workers where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the number of matching partner workers
	*/
	public static int countByPartnerEntryId(long partnerEntryId) {
		return getPersistence().countByPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or throws a {@link NoSuchPartnerWorkerException} if it could not be found.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public static PartnerWorker findByU_PEI(long userId, long partnerEntryId)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence().findByU_PEI(userId, partnerEntryId);
	}

	/**
	* Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public static PartnerWorker fetchByU_PEI(long userId, long partnerEntryId) {
		return getPersistence().fetchByU_PEI(userId, partnerEntryId);
	}

	/**
	* Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public static PartnerWorker fetchByU_PEI(long userId, long partnerEntryId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_PEI(userId, partnerEntryId, retrieveFromCache);
	}

	/**
	* Removes the partner worker where userId = &#63; and partnerEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the partner worker that was removed
	*/
	public static PartnerWorker removeByU_PEI(long userId, long partnerEntryId)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence().removeByU_PEI(userId, partnerEntryId);
	}

	/**
	* Returns the number of partner workers where userId = &#63; and partnerEntryId = &#63;.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the number of matching partner workers
	*/
	public static int countByU_PEI(long userId, long partnerEntryId) {
		return getPersistence().countByU_PEI(userId, partnerEntryId);
	}

	/**
	* Returns all the partner workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the matching partner workers
	*/
	public static List<PartnerWorker> findByU_R(long userId, int role) {
		return getPersistence().findByU_R(userId, role);
	}

	/**
	* Returns a range of all the partner workers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of matching partner workers
	*/
	public static List<PartnerWorker> findByU_R(long userId, int role,
		int start, int end) {
		return getPersistence().findByU_R(userId, role, start, end);
	}

	/**
	* Returns an ordered range of all the partner workers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner workers
	*/
	public static List<PartnerWorker> findByU_R(long userId, int role,
		int start, int end, OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence()
				   .findByU_R(userId, role, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the partner workers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching partner workers
	*/
	public static List<PartnerWorker> findByU_R(long userId, int role,
		int start, int end, OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_R(userId, role, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public static PartnerWorker findByU_R_First(long userId, int role,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence().findByU_R_First(userId, role, orderByComparator);
	}

	/**
	* Returns the first partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public static PartnerWorker fetchByU_R_First(long userId, int role,
		OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence().fetchByU_R_First(userId, role, orderByComparator);
	}

	/**
	* Returns the last partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public static PartnerWorker findByU_R_Last(long userId, int role,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence().findByU_R_Last(userId, role, orderByComparator);
	}

	/**
	* Returns the last partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public static PartnerWorker fetchByU_R_Last(long userId, int role,
		OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence().fetchByU_R_Last(userId, role, orderByComparator);
	}

	/**
	* Returns the partner workers before and after the current partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param partnerWorkerId the primary key of the current partner worker
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner worker
	* @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	*/
	public static PartnerWorker[] findByU_R_PrevAndNext(long partnerWorkerId,
		long userId, int role,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence()
				   .findByU_R_PrevAndNext(partnerWorkerId, userId, role,
			orderByComparator);
	}

	/**
	* Returns all the partner workers where userId = any &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userIds the user IDs
	* @param roles the roles
	* @return the matching partner workers
	*/
	public static List<PartnerWorker> findByU_R(long[] userIds, int[] roles) {
		return getPersistence().findByU_R(userIds, roles);
	}

	/**
	* Returns a range of all the partner workers where userId = any &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userIds the user IDs
	* @param roles the roles
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of matching partner workers
	*/
	public static List<PartnerWorker> findByU_R(long[] userIds, int[] roles,
		int start, int end) {
		return getPersistence().findByU_R(userIds, roles, start, end);
	}

	/**
	* Returns an ordered range of all the partner workers where userId = any &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userIds the user IDs
	* @param roles the roles
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner workers
	*/
	public static List<PartnerWorker> findByU_R(long[] userIds, int[] roles,
		int start, int end, OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence()
				   .findByU_R(userIds, roles, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the partner workers where userId = &#63; and role = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching partner workers
	*/
	public static List<PartnerWorker> findByU_R(long[] userIds, int[] roles,
		int start, int end, OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_R(userIds, roles, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Removes all the partner workers where userId = &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param role the role
	*/
	public static void removeByU_R(long userId, int role) {
		getPersistence().removeByU_R(userId, role);
	}

	/**
	* Returns the number of partner workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the number of matching partner workers
	*/
	public static int countByU_R(long userId, int role) {
		return getPersistence().countByU_R(userId, role);
	}

	/**
	* Returns the number of partner workers where userId = any &#63; and role = any &#63;.
	*
	* @param userIds the user IDs
	* @param roles the roles
	* @return the number of matching partner workers
	*/
	public static int countByU_R(long[] userIds, int[] roles) {
		return getPersistence().countByU_R(userIds, roles);
	}

	/**
	* Returns all the partner workers where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @return the matching partner workers
	*/
	public static List<PartnerWorker> findByPEI_R(long partnerEntryId, int role) {
		return getPersistence().findByPEI_R(partnerEntryId, role);
	}

	/**
	* Returns a range of all the partner workers where partnerEntryId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of matching partner workers
	*/
	public static List<PartnerWorker> findByPEI_R(long partnerEntryId,
		int role, int start, int end) {
		return getPersistence().findByPEI_R(partnerEntryId, role, start, end);
	}

	/**
	* Returns an ordered range of all the partner workers where partnerEntryId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner workers
	*/
	public static List<PartnerWorker> findByPEI_R(long partnerEntryId,
		int role, int start, int end,
		OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence()
				   .findByPEI_R(partnerEntryId, role, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the partner workers where partnerEntryId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching partner workers
	*/
	public static List<PartnerWorker> findByPEI_R(long partnerEntryId,
		int role, int start, int end,
		OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPEI_R(partnerEntryId, role, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public static PartnerWorker findByPEI_R_First(long partnerEntryId,
		int role, OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence()
				   .findByPEI_R_First(partnerEntryId, role, orderByComparator);
	}

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public static PartnerWorker fetchByPEI_R_First(long partnerEntryId,
		int role, OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence()
				   .fetchByPEI_R_First(partnerEntryId, role, orderByComparator);
	}

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public static PartnerWorker findByPEI_R_Last(long partnerEntryId, int role,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence()
				   .findByPEI_R_Last(partnerEntryId, role, orderByComparator);
	}

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public static PartnerWorker fetchByPEI_R_Last(long partnerEntryId,
		int role, OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence()
				   .fetchByPEI_R_Last(partnerEntryId, role, orderByComparator);
	}

	/**
	* Returns the partner workers before and after the current partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerWorkerId the primary key of the current partner worker
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner worker
	* @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	*/
	public static PartnerWorker[] findByPEI_R_PrevAndNext(
		long partnerWorkerId, long partnerEntryId, int role,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence()
				   .findByPEI_R_PrevAndNext(partnerWorkerId, partnerEntryId,
			role, orderByComparator);
	}

	/**
	* Removes all the partner workers where partnerEntryId = &#63; and role = &#63; from the database.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	*/
	public static void removeByPEI_R(long partnerEntryId, int role) {
		getPersistence().removeByPEI_R(partnerEntryId, role);
	}

	/**
	* Returns the number of partner workers where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @return the number of matching partner workers
	*/
	public static int countByPEI_R(long partnerEntryId, int role) {
		return getPersistence().countByPEI_R(partnerEntryId, role);
	}

	/**
	* Caches the partner worker in the entity cache if it is enabled.
	*
	* @param partnerWorker the partner worker
	*/
	public static void cacheResult(PartnerWorker partnerWorker) {
		getPersistence().cacheResult(partnerWorker);
	}

	/**
	* Caches the partner workers in the entity cache if it is enabled.
	*
	* @param partnerWorkers the partner workers
	*/
	public static void cacheResult(List<PartnerWorker> partnerWorkers) {
		getPersistence().cacheResult(partnerWorkers);
	}

	/**
	* Creates a new partner worker with the primary key. Does not add the partner worker to the database.
	*
	* @param partnerWorkerId the primary key for the new partner worker
	* @return the new partner worker
	*/
	public static PartnerWorker create(long partnerWorkerId) {
		return getPersistence().create(partnerWorkerId);
	}

	/**
	* Removes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker that was removed
	* @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	*/
	public static PartnerWorker remove(long partnerWorkerId)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence().remove(partnerWorkerId);
	}

	public static PartnerWorker updateImpl(PartnerWorker partnerWorker) {
		return getPersistence().updateImpl(partnerWorker);
	}

	/**
	* Returns the partner worker with the primary key or throws a {@link NoSuchPartnerWorkerException} if it could not be found.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker
	* @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	*/
	public static PartnerWorker findByPrimaryKey(long partnerWorkerId)
		throws com.liferay.osb.exception.NoSuchPartnerWorkerException {
		return getPersistence().findByPrimaryKey(partnerWorkerId);
	}

	/**
	* Returns the partner worker with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker, or <code>null</code> if a partner worker with the primary key could not be found
	*/
	public static PartnerWorker fetchByPrimaryKey(long partnerWorkerId) {
		return getPersistence().fetchByPrimaryKey(partnerWorkerId);
	}

	public static java.util.Map<java.io.Serializable, PartnerWorker> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the partner workers.
	*
	* @return the partner workers
	*/
	public static List<PartnerWorker> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the partner workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of partner workers
	*/
	public static List<PartnerWorker> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the partner workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of partner workers
	*/
	public static List<PartnerWorker> findAll(int start, int end,
		OrderByComparator<PartnerWorker> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the partner workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of partner workers
	*/
	public static List<PartnerWorker> findAll(int start, int end,
		OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the partner workers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of partner workers.
	*
	* @return the number of partner workers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PartnerWorkerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PartnerWorkerPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					PartnerWorkerPersistence.class.getName());

			ReferenceRegistry.registerReference(PartnerWorkerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static PartnerWorkerPersistence _persistence;
}
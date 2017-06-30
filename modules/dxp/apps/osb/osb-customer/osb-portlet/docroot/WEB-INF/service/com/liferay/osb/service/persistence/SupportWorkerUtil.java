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

import com.liferay.osb.model.SupportWorker;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the support worker service. This utility wraps {@link SupportWorkerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerPersistence
 * @see SupportWorkerPersistenceImpl
 * @generated
 */
public class SupportWorkerUtil {
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
	public static void clearCache(SupportWorker supportWorker) {
		getPersistence().clearCache(supportWorker);
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
	public static List<SupportWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportWorker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SupportWorker update(SupportWorker supportWorker,
		boolean merge) throws SystemException {
		return getPersistence().update(supportWorker, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SupportWorker update(SupportWorker supportWorker,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(supportWorker, merge, serviceContext);
	}

	/**
	* Caches the support worker in the entity cache if it is enabled.
	*
	* @param supportWorker the support worker
	*/
	public static void cacheResult(
		com.liferay.osb.model.SupportWorker supportWorker) {
		getPersistence().cacheResult(supportWorker);
	}

	/**
	* Caches the support workers in the entity cache if it is enabled.
	*
	* @param supportWorkers the support workers
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.SupportWorker> supportWorkers) {
		getPersistence().cacheResult(supportWorkers);
	}

	/**
	* Creates a new support worker with the primary key. Does not add the support worker to the database.
	*
	* @param supportWorkerId the primary key for the new support worker
	* @return the new support worker
	*/
	public static com.liferay.osb.model.SupportWorker create(
		long supportWorkerId) {
		return getPersistence().create(supportWorkerId);
	}

	/**
	* Removes the support worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker that was removed
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker remove(
		long supportWorkerId)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(supportWorkerId);
	}

	public static com.liferay.osb.model.SupportWorker updateImpl(
		com.liferay.osb.model.SupportWorker supportWorker, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(supportWorker, merge);
	}

	/**
	* Returns the support worker with the primary key or throws a {@link com.liferay.osb.NoSuchSupportWorkerException} if it could not be found.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker findByPrimaryKey(
		long supportWorkerId)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(supportWorkerId);
	}

	/**
	* Returns the support worker with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker, or <code>null</code> if a support worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker fetchByPrimaryKey(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(supportWorkerId);
	}

	/**
	* Returns all the support workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the support workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the support workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first support worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first support worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last support worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last support worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the support workers before and after the current support worker in the ordered set where userId = &#63;.
	*
	* @param supportWorkerId the primary key of the current support worker
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker[] findByUserId_PrevAndNext(
		long supportWorkerId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(supportWorkerId, userId,
			orderByComparator);
	}

	/**
	* Returns all the support workers where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @return the matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findBySupportTeamId(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySupportTeamId(supportTeamId);
	}

	/**
	* Returns a range of all the support workers where supportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportTeamId the support team ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findBySupportTeamId(
		long supportTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySupportTeamId(supportTeamId, start, end);
	}

	/**
	* Returns an ordered range of all the support workers where supportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportTeamId the support team ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findBySupportTeamId(
		long supportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportTeamId(supportTeamId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first support worker in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker findBySupportTeamId_First(
		long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportTeamId_First(supportTeamId, orderByComparator);
	}

	/**
	* Returns the first support worker in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker fetchBySupportTeamId_First(
		long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportTeamId_First(supportTeamId, orderByComparator);
	}

	/**
	* Returns the last support worker in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker findBySupportTeamId_Last(
		long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportTeamId_Last(supportTeamId, orderByComparator);
	}

	/**
	* Returns the last support worker in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker fetchBySupportTeamId_Last(
		long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportTeamId_Last(supportTeamId, orderByComparator);
	}

	/**
	* Returns the support workers before and after the current support worker in the ordered set where supportTeamId = &#63;.
	*
	* @param supportWorkerId the primary key of the current support worker
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker[] findBySupportTeamId_PrevAndNext(
		long supportWorkerId, long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportTeamId_PrevAndNext(supportWorkerId,
			supportTeamId, orderByComparator);
	}

	/**
	* Returns all the support workers where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @return the matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findBySupportLaborId(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySupportLaborId(supportLaborId);
	}

	/**
	* Returns a range of all the support workers where supportLaborId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportLaborId the support labor ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findBySupportLaborId(
		long supportLaborId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySupportLaborId(supportLaborId, start, end);
	}

	/**
	* Returns an ordered range of all the support workers where supportLaborId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportLaborId the support labor ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findBySupportLaborId(
		long supportLaborId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportLaborId(supportLaborId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first support worker in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker findBySupportLaborId_First(
		long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportLaborId_First(supportLaborId, orderByComparator);
	}

	/**
	* Returns the first support worker in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker fetchBySupportLaborId_First(
		long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportLaborId_First(supportLaborId,
			orderByComparator);
	}

	/**
	* Returns the last support worker in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker findBySupportLaborId_Last(
		long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportLaborId_Last(supportLaborId, orderByComparator);
	}

	/**
	* Returns the last support worker in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker fetchBySupportLaborId_Last(
		long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportLaborId_Last(supportLaborId, orderByComparator);
	}

	/**
	* Returns the support workers before and after the current support worker in the ordered set where supportLaborId = &#63;.
	*
	* @param supportWorkerId the primary key of the current support worker
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker[] findBySupportLaborId_PrevAndNext(
		long supportWorkerId, long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportLaborId_PrevAndNext(supportWorkerId,
			supportLaborId, orderByComparator);
	}

	/**
	* Returns the support worker where userId = &#63; and supportTeamId = &#63; or throws a {@link com.liferay.osb.NoSuchSupportWorkerException} if it could not be found.
	*
	* @param userId the user ID
	* @param supportTeamId the support team ID
	* @return the matching support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker findByU_STI(long userId,
		long supportTeamId)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_STI(userId, supportTeamId);
	}

	/**
	* Returns the support worker where userId = &#63; and supportTeamId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param supportTeamId the support team ID
	* @return the matching support worker, or <code>null</code> if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker fetchByU_STI(
		long userId, long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_STI(userId, supportTeamId);
	}

	/**
	* Returns the support worker where userId = &#63; and supportTeamId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param supportTeamId the support team ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching support worker, or <code>null</code> if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker fetchByU_STI(
		long userId, long supportTeamId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_STI(userId, supportTeamId, retrieveFromCache);
	}

	/**
	* Returns all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @return the matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findByU_MW_R(
		long userId, double maxWork, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_MW_R(userId, maxWork, role);
	}

	/**
	* Returns a range of all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findByU_MW_R(
		long userId, double maxWork, int role, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_MW_R(userId, maxWork, role, start, end);
	}

	/**
	* Returns an ordered range of all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findByU_MW_R(
		long userId, double maxWork, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_MW_R(userId, maxWork, role, start, end,
			orderByComparator);
	}

	/**
	* Returns the first support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker findByU_MW_R_First(
		long userId, double maxWork, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_MW_R_First(userId, maxWork, role, orderByComparator);
	}

	/**
	* Returns the first support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker fetchByU_MW_R_First(
		long userId, double maxWork, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_MW_R_First(userId, maxWork, role, orderByComparator);
	}

	/**
	* Returns the last support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker findByU_MW_R_Last(
		long userId, double maxWork, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_MW_R_Last(userId, maxWork, role, orderByComparator);
	}

	/**
	* Returns the last support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker fetchByU_MW_R_Last(
		long userId, double maxWork, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_MW_R_Last(userId, maxWork, role, orderByComparator);
	}

	/**
	* Returns the support workers before and after the current support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param supportWorkerId the primary key of the current support worker
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker
	* @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker[] findByU_MW_R_PrevAndNext(
		long supportWorkerId, long userId, double maxWork, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_MW_R_PrevAndNext(supportWorkerId, userId, maxWork,
			role, orderByComparator);
	}

	/**
	* Returns all the support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userIds the user IDs
	* @param maxWork the max work
	* @param roles the roles
	* @return the matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findByU_MW_R(
		long[] userIds, double maxWork, int[] roles)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_MW_R(userIds, maxWork, roles);
	}

	/**
	* Returns a range of all the support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userIds the user IDs
	* @param maxWork the max work
	* @param roles the roles
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findByU_MW_R(
		long[] userIds, double maxWork, int[] roles, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_MW_R(userIds, maxWork, roles, start, end);
	}

	/**
	* Returns an ordered range of all the support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userIds the user IDs
	* @param maxWork the max work
	* @param roles the roles
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findByU_MW_R(
		long[] userIds, double maxWork, int[] roles, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_MW_R(userIds, maxWork, roles, start, end,
			orderByComparator);
	}

	/**
	* Returns all the support workers.
	*
	* @return the support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the support workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the support workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support workers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorker> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the support workers where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the support workers where supportTeamId = &#63; from the database.
	*
	* @param supportTeamId the support team ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySupportTeamId(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySupportTeamId(supportTeamId);
	}

	/**
	* Removes all the support workers where supportLaborId = &#63; from the database.
	*
	* @param supportLaborId the support labor ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySupportLaborId(long supportLaborId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySupportLaborId(supportLaborId);
	}

	/**
	* Removes the support worker where userId = &#63; and supportTeamId = &#63; from the database.
	*
	* @param userId the user ID
	* @param supportTeamId the support team ID
	* @return the support worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorker removeByU_STI(
		long userId, long supportTeamId)
		throws com.liferay.osb.NoSuchSupportWorkerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByU_STI(userId, supportTeamId);
	}

	/**
	* Removes all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_MW_R(long userId, double maxWork, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_MW_R(userId, maxWork, role);
	}

	/**
	* Removes all the support workers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of support workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of support workers where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @return the number of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySupportTeamId(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySupportTeamId(supportTeamId);
	}

	/**
	* Returns the number of support workers where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @return the number of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySupportLaborId(long supportLaborId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySupportLaborId(supportLaborId);
	}

	/**
	* Returns the number of support workers where userId = &#63; and supportTeamId = &#63;.
	*
	* @param userId the user ID
	* @param supportTeamId the support team ID
	* @return the number of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_STI(long userId, long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_STI(userId, supportTeamId);
	}

	/**
	* Returns the number of support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @return the number of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_MW_R(long userId, double maxWork, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_MW_R(userId, maxWork, role);
	}

	/**
	* Returns the number of support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	*
	* @param userIds the user IDs
	* @param maxWork the max work
	* @param roles the roles
	* @return the number of matching support workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_MW_R(long[] userIds, double maxWork, int[] roles)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_MW_R(userIds, maxWork, roles);
	}

	/**
	* Returns the number of support workers.
	*
	* @return the number of support workers
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SupportWorkerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SupportWorkerPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportWorkerPersistence.class.getName());

			ReferenceRegistry.registerReference(SupportWorkerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(SupportWorkerPersistence persistence) {
	}

	private static SupportWorkerPersistence _persistence;
}
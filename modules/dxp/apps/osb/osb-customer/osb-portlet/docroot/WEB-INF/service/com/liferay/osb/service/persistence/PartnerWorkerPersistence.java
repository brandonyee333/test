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

import com.liferay.osb.model.PartnerWorker;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the partner worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorkerPersistenceImpl
 * @see PartnerWorkerUtil
 * @generated
 */
public interface PartnerWorkerPersistence extends BasePersistence<PartnerWorker> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PartnerWorkerUtil} to access the partner worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the partner worker in the entity cache if it is enabled.
	*
	* @param partnerWorker the partner worker
	*/
	public void cacheResult(com.liferay.osb.model.PartnerWorker partnerWorker);

	/**
	* Caches the partner workers in the entity cache if it is enabled.
	*
	* @param partnerWorkers the partner workers
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.PartnerWorker> partnerWorkers);

	/**
	* Creates a new partner worker with the primary key. Does not add the partner worker to the database.
	*
	* @param partnerWorkerId the primary key for the new partner worker
	* @return the new partner worker
	*/
	public com.liferay.osb.model.PartnerWorker create(long partnerWorkerId);

	/**
	* Removes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker that was removed
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker remove(long partnerWorkerId)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.PartnerWorker updateImpl(
		com.liferay.osb.model.PartnerWorker partnerWorker, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner worker with the primary key or throws a {@link com.liferay.osb.NoSuchPartnerWorkerException} if it could not be found.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByPrimaryKey(
		long partnerWorkerId)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner worker with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker, or <code>null</code> if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByPrimaryKey(
		long partnerWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the partner workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the partner workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the partner workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner workers before and after the current partner worker in the ordered set where userId = &#63;.
	*
	* @param partnerWorkerId the primary key of the current partner worker
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker[] findByUserId_PrevAndNext(
		long partnerWorkerId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the partner workers where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByPartnerEntryId(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the partner workers where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByPartnerEntryId(
		long partnerEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the partner workers where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByPartnerEntryId(
		long partnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByPartnerEntryId_First(
		long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByPartnerEntryId_First(
		long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByPartnerEntryId_Last(
		long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByPartnerEntryId_Last(
		long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner workers before and after the current partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerWorkerId the primary key of the current partner worker
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker[] findByPartnerEntryId_PrevAndNext(
		long partnerWorkerId, long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchPartnerWorkerException} if it could not be found.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the matching partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByU_PEI(long userId,
		long partnerEntryId)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByU_PEI(long userId,
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByU_PEI(long userId,
		long partnerEntryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the partner workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByU_R(
		long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the partner workers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByU_R(
		long userId, int role, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the partner workers where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByU_R(
		long userId, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByU_R_First(long userId,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByU_R_First(long userId,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByU_R_Last(long userId,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByU_R_Last(long userId,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner workers before and after the current partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param partnerWorkerId the primary key of the current partner worker
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker[] findByU_R_PrevAndNext(
		long partnerWorkerId, long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the partner workers where userId = any &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userIds the user IDs
	* @param roles the roles
	* @return the matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByU_R(
		long[] userIds, int[] roles)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the partner workers where userId = any &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userIds the user IDs
	* @param roles the roles
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByU_R(
		long[] userIds, int[] roles, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the partner workers where userId = any &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userIds the user IDs
	* @param roles the roles
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByU_R(
		long[] userIds, int[] roles, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the partner workers where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @return the matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByPEI_R(
		long partnerEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the partner workers where partnerEntryId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByPEI_R(
		long partnerEntryId, int role, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the partner workers where partnerEntryId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByPEI_R(
		long partnerEntryId, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByPEI_R_First(
		long partnerEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByPEI_R_First(
		long partnerEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByPEI_R_Last(
		long partnerEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByPEI_R_Last(
		long partnerEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner workers before and after the current partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerWorkerId the primary key of the current partner worker
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker[] findByPEI_R_PrevAndNext(
		long partnerWorkerId, long partnerEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the partner workers where partnerEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param notifications the notifications
	* @return the matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByPEI_NotN(
		long partnerEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the partner workers where partnerEntryId = &#63; and notifications &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param notifications the notifications
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByPEI_NotN(
		long partnerEntryId, int notifications, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the partner workers where partnerEntryId = &#63; and notifications &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param notifications the notifications
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findByPEI_NotN(
		long partnerEntryId, int notifications, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByPEI_NotN_First(
		long partnerEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByPEI_NotN_First(
		long partnerEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker findByPEI_NotN_Last(
		long partnerEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker fetchByPEI_NotN_Last(
		long partnerEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner workers before and after the current partner worker in the ordered set where partnerEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param partnerWorkerId the primary key of the current partner worker
	* @param partnerEntryId the partner entry ID
	* @param notifications the notifications
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner worker
	* @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker[] findByPEI_NotN_PrevAndNext(
		long partnerWorkerId, long partnerEntryId, int notifications,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the partner workers.
	*
	* @return the partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the partner workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the partner workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the partner workers where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the partner workers where partnerEntryId = &#63; from the database.
	*
	* @param partnerEntryId the partner entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPartnerEntryId(long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the partner worker where userId = &#63; and partnerEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the partner worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker removeByU_PEI(long userId,
		long partnerEntryId)
		throws com.liferay.osb.NoSuchPartnerWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the partner workers where userId = &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param role the role
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_R(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the partner workers where partnerEntryId = &#63; and role = &#63; from the database.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPEI_R(long partnerEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the partner workers where partnerEntryId = &#63; and notifications &ne; &#63; from the database.
	*
	* @param partnerEntryId the partner entry ID
	* @param notifications the notifications
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPEI_NotN(long partnerEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the partner workers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner workers where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the number of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByPartnerEntryId(long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner workers where userId = &#63; and partnerEntryId = &#63;.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the number of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_PEI(long userId, long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the number of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_R(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner workers where userId = any &#63; and role = any &#63;.
	*
	* @param userIds the user IDs
	* @param roles the roles
	* @return the number of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_R(long[] userIds, int[] roles)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner workers where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @return the number of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByPEI_R(long partnerEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner workers where partnerEntryId = &#63; and notifications &ne; &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param notifications the notifications
	* @return the number of matching partner workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByPEI_NotN(long partnerEntryId, int notifications)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner workers.
	*
	* @return the number of partner workers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
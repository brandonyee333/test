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

import com.liferay.osb.exception.NoSuchSupportWorkerException;
import com.liferay.osb.model.SupportWorker;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the support worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.SupportWorkerPersistenceImpl
 * @see SupportWorkerUtil
 * @generated
 */
@ProviderType
public interface SupportWorkerPersistence extends BasePersistence<SupportWorker> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportWorkerUtil} to access the support worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the support workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching support workers
	*/
	public java.util.List<SupportWorker> findByUserId(long userId);

	/**
	* Returns a range of all the support workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of matching support workers
	*/
	public java.util.List<SupportWorker> findByUserId(long userId, int start,
		int end);

	/**
	* Returns an ordered range of all the support workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support workers
	*/
	public java.util.List<SupportWorker> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns an ordered range of all the support workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support workers
	*/
	public java.util.List<SupportWorker> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first support worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker
	* @throws NoSuchSupportWorkerException if a matching support worker could not be found
	*/
	public SupportWorker findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Returns the first support worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	*/
	public SupportWorker fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns the last support worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker
	* @throws NoSuchSupportWorkerException if a matching support worker could not be found
	*/
	public SupportWorker findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Returns the last support worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	*/
	public SupportWorker fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns the support workers before and after the current support worker in the ordered set where userId = &#63;.
	*
	* @param supportWorkerId the primary key of the current support worker
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker
	* @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	*/
	public SupportWorker[] findByUserId_PrevAndNext(long supportWorkerId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Removes all the support workers where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of support workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching support workers
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the support workers where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @return the matching support workers
	*/
	public java.util.List<SupportWorker> findBySupportTeamId(long supportTeamId);

	/**
	* Returns a range of all the support workers where supportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportTeamId the support team ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of matching support workers
	*/
	public java.util.List<SupportWorker> findBySupportTeamId(
		long supportTeamId, int start, int end);

	/**
	* Returns an ordered range of all the support workers where supportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportTeamId the support team ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support workers
	*/
	public java.util.List<SupportWorker> findBySupportTeamId(
		long supportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns an ordered range of all the support workers where supportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportTeamId the support team ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support workers
	*/
	public java.util.List<SupportWorker> findBySupportTeamId(
		long supportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first support worker in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker
	* @throws NoSuchSupportWorkerException if a matching support worker could not be found
	*/
	public SupportWorker findBySupportTeamId_First(long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Returns the first support worker in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	*/
	public SupportWorker fetchBySupportTeamId_First(long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns the last support worker in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker
	* @throws NoSuchSupportWorkerException if a matching support worker could not be found
	*/
	public SupportWorker findBySupportTeamId_Last(long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Returns the last support worker in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	*/
	public SupportWorker fetchBySupportTeamId_Last(long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns the support workers before and after the current support worker in the ordered set where supportTeamId = &#63;.
	*
	* @param supportWorkerId the primary key of the current support worker
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker
	* @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	*/
	public SupportWorker[] findBySupportTeamId_PrevAndNext(
		long supportWorkerId, long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Removes all the support workers where supportTeamId = &#63; from the database.
	*
	* @param supportTeamId the support team ID
	*/
	public void removeBySupportTeamId(long supportTeamId);

	/**
	* Returns the number of support workers where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @return the number of matching support workers
	*/
	public int countBySupportTeamId(long supportTeamId);

	/**
	* Returns all the support workers where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @return the matching support workers
	*/
	public java.util.List<SupportWorker> findBySupportLaborId(
		long supportLaborId);

	/**
	* Returns a range of all the support workers where supportLaborId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportLaborId the support labor ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of matching support workers
	*/
	public java.util.List<SupportWorker> findBySupportLaborId(
		long supportLaborId, int start, int end);

	/**
	* Returns an ordered range of all the support workers where supportLaborId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportLaborId the support labor ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support workers
	*/
	public java.util.List<SupportWorker> findBySupportLaborId(
		long supportLaborId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns an ordered range of all the support workers where supportLaborId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportLaborId the support labor ID
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support workers
	*/
	public java.util.List<SupportWorker> findBySupportLaborId(
		long supportLaborId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first support worker in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker
	* @throws NoSuchSupportWorkerException if a matching support worker could not be found
	*/
	public SupportWorker findBySupportLaborId_First(long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Returns the first support worker in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	*/
	public SupportWorker fetchBySupportLaborId_First(long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns the last support worker in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker
	* @throws NoSuchSupportWorkerException if a matching support worker could not be found
	*/
	public SupportWorker findBySupportLaborId_Last(long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Returns the last support worker in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	*/
	public SupportWorker fetchBySupportLaborId_Last(long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns the support workers before and after the current support worker in the ordered set where supportLaborId = &#63;.
	*
	* @param supportWorkerId the primary key of the current support worker
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker
	* @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	*/
	public SupportWorker[] findBySupportLaborId_PrevAndNext(
		long supportWorkerId, long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Removes all the support workers where supportLaborId = &#63; from the database.
	*
	* @param supportLaborId the support labor ID
	*/
	public void removeBySupportLaborId(long supportLaborId);

	/**
	* Returns the number of support workers where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @return the number of matching support workers
	*/
	public int countBySupportLaborId(long supportLaborId);

	/**
	* Returns the support worker where userId = &#63; and supportTeamId = &#63; or throws a {@link NoSuchSupportWorkerException} if it could not be found.
	*
	* @param userId the user ID
	* @param supportTeamId the support team ID
	* @return the matching support worker
	* @throws NoSuchSupportWorkerException if a matching support worker could not be found
	*/
	public SupportWorker findByU_STI(long userId, long supportTeamId)
		throws NoSuchSupportWorkerException;

	/**
	* Returns the support worker where userId = &#63; and supportTeamId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param supportTeamId the support team ID
	* @return the matching support worker, or <code>null</code> if a matching support worker could not be found
	*/
	public SupportWorker fetchByU_STI(long userId, long supportTeamId);

	/**
	* Returns the support worker where userId = &#63; and supportTeamId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param supportTeamId the support team ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching support worker, or <code>null</code> if a matching support worker could not be found
	*/
	public SupportWorker fetchByU_STI(long userId, long supportTeamId,
		boolean retrieveFromCache);

	/**
	* Removes the support worker where userId = &#63; and supportTeamId = &#63; from the database.
	*
	* @param userId the user ID
	* @param supportTeamId the support team ID
	* @return the support worker that was removed
	*/
	public SupportWorker removeByU_STI(long userId, long supportTeamId)
		throws NoSuchSupportWorkerException;

	/**
	* Returns the number of support workers where userId = &#63; and supportTeamId = &#63;.
	*
	* @param userId the user ID
	* @param supportTeamId the support team ID
	* @return the number of matching support workers
	*/
	public int countByU_STI(long userId, long supportTeamId);

	/**
	* Returns all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @return the matching support workers
	*/
	public java.util.List<SupportWorker> findByU_MW_R(long userId,
		double maxWork, int role);

	/**
	* Returns a range of all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of matching support workers
	*/
	public java.util.List<SupportWorker> findByU_MW_R(long userId,
		double maxWork, int role, int start, int end);

	/**
	* Returns an ordered range of all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support workers
	*/
	public java.util.List<SupportWorker> findByU_MW_R(long userId,
		double maxWork, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns an ordered range of all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support workers
	*/
	public java.util.List<SupportWorker> findByU_MW_R(long userId,
		double maxWork, int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker
	* @throws NoSuchSupportWorkerException if a matching support worker could not be found
	*/
	public SupportWorker findByU_MW_R_First(long userId, double maxWork,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Returns the first support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	*/
	public SupportWorker fetchByU_MW_R_First(long userId, double maxWork,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns the last support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker
	* @throws NoSuchSupportWorkerException if a matching support worker could not be found
	*/
	public SupportWorker findByU_MW_R_Last(long userId, double maxWork,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Returns the last support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	*/
	public SupportWorker fetchByU_MW_R_Last(long userId, double maxWork,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns the support workers before and after the current support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param supportWorkerId the primary key of the current support worker
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker
	* @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	*/
	public SupportWorker[] findByU_MW_R_PrevAndNext(long supportWorkerId,
		long userId, double maxWork, int role,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException;

	/**
	* Returns all the support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userIds the user IDs
	* @param maxWork the max work
	* @param roles the roles
	* @return the matching support workers
	*/
	public java.util.List<SupportWorker> findByU_MW_R(long[] userIds,
		double maxWork, int[] roles);

	/**
	* Returns a range of all the support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userIds the user IDs
	* @param maxWork the max work
	* @param roles the roles
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of matching support workers
	*/
	public java.util.List<SupportWorker> findByU_MW_R(long[] userIds,
		double maxWork, int[] roles, int start, int end);

	/**
	* Returns an ordered range of all the support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userIds the user IDs
	* @param maxWork the max work
	* @param roles the roles
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support workers
	*/
	public java.util.List<SupportWorker> findByU_MW_R(long[] userIds,
		double maxWork, int[] roles, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns an ordered range of all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support workers
	*/
	public java.util.List<SupportWorker> findByU_MW_R(long[] userIds,
		double maxWork, int[] roles, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	*/
	public void removeByU_MW_R(long userId, double maxWork, int role);

	/**
	* Returns the number of support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param maxWork the max work
	* @param role the role
	* @return the number of matching support workers
	*/
	public int countByU_MW_R(long userId, double maxWork, int role);

	/**
	* Returns the number of support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	*
	* @param userIds the user IDs
	* @param maxWork the max work
	* @param roles the roles
	* @return the number of matching support workers
	*/
	public int countByU_MW_R(long[] userIds, double maxWork, int[] roles);

	/**
	* Caches the support worker in the entity cache if it is enabled.
	*
	* @param supportWorker the support worker
	*/
	public void cacheResult(SupportWorker supportWorker);

	/**
	* Caches the support workers in the entity cache if it is enabled.
	*
	* @param supportWorkers the support workers
	*/
	public void cacheResult(java.util.List<SupportWorker> supportWorkers);

	/**
	* Creates a new support worker with the primary key. Does not add the support worker to the database.
	*
	* @param supportWorkerId the primary key for the new support worker
	* @return the new support worker
	*/
	public SupportWorker create(long supportWorkerId);

	/**
	* Removes the support worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker that was removed
	* @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	*/
	public SupportWorker remove(long supportWorkerId)
		throws NoSuchSupportWorkerException;

	public SupportWorker updateImpl(SupportWorker supportWorker);

	/**
	* Returns the support worker with the primary key or throws a {@link NoSuchSupportWorkerException} if it could not be found.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker
	* @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	*/
	public SupportWorker findByPrimaryKey(long supportWorkerId)
		throws NoSuchSupportWorkerException;

	/**
	* Returns the support worker with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker, or <code>null</code> if a support worker with the primary key could not be found
	*/
	public SupportWorker fetchByPrimaryKey(long supportWorkerId);

	@Override
	public java.util.Map<java.io.Serializable, SupportWorker> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the support workers.
	*
	* @return the support workers
	*/
	public java.util.List<SupportWorker> findAll();

	/**
	* Returns a range of all the support workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of support workers
	*/
	public java.util.List<SupportWorker> findAll(int start, int end);

	/**
	* Returns an ordered range of all the support workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support workers
	*/
	public java.util.List<SupportWorker> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator);

	/**
	* Returns an ordered range of all the support workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of support workers
	*/
	public java.util.List<SupportWorker> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the support workers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of support workers.
	*
	* @return the number of support workers
	*/
	public int countAll();
}
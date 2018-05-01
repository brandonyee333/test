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

import com.liferay.osb.exception.NoSuchPartnerWorkerException;
import com.liferay.osb.model.PartnerWorker;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the partner worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.PartnerWorkerPersistenceImpl
 * @see PartnerWorkerUtil
 * @generated
 */
@ProviderType
public interface PartnerWorkerPersistence extends BasePersistence<PartnerWorker> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PartnerWorkerUtil} to access the partner worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the partner workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching partner workers
	*/
	public java.util.List<PartnerWorker> findByUserId(long userId);

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
	public java.util.List<PartnerWorker> findByUserId(long userId, int start,
		int end);

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
	public java.util.List<PartnerWorker> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

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
	public java.util.List<PartnerWorker> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public PartnerWorker findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

	/**
	* Returns the first partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public PartnerWorker fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

	/**
	* Returns the last partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public PartnerWorker findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

	/**
	* Returns the last partner worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public PartnerWorker fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

	/**
	* Returns the partner workers before and after the current partner worker in the ordered set where userId = &#63;.
	*
	* @param partnerWorkerId the primary key of the current partner worker
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner worker
	* @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	*/
	public PartnerWorker[] findByUserId_PrevAndNext(long partnerWorkerId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

	/**
	* Removes all the partner workers where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of partner workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching partner workers
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the partner workers where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the matching partner workers
	*/
	public java.util.List<PartnerWorker> findByPartnerEntryId(
		long partnerEntryId);

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
	public java.util.List<PartnerWorker> findByPartnerEntryId(
		long partnerEntryId, int start, int end);

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
	public java.util.List<PartnerWorker> findByPartnerEntryId(
		long partnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

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
	public java.util.List<PartnerWorker> findByPartnerEntryId(
		long partnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public PartnerWorker findByPartnerEntryId_First(long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public PartnerWorker fetchByPartnerEntryId_First(long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public PartnerWorker findByPartnerEntryId_Last(long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public PartnerWorker fetchByPartnerEntryId_Last(long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

	/**
	* Returns the partner workers before and after the current partner worker in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerWorkerId the primary key of the current partner worker
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner worker
	* @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	*/
	public PartnerWorker[] findByPartnerEntryId_PrevAndNext(
		long partnerWorkerId, long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

	/**
	* Removes all the partner workers where partnerEntryId = &#63; from the database.
	*
	* @param partnerEntryId the partner entry ID
	*/
	public void removeByPartnerEntryId(long partnerEntryId);

	/**
	* Returns the number of partner workers where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the number of matching partner workers
	*/
	public int countByPartnerEntryId(long partnerEntryId);

	/**
	* Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or throws a {@link NoSuchPartnerWorkerException} if it could not be found.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public PartnerWorker findByU_PEI(long userId, long partnerEntryId)
		throws NoSuchPartnerWorkerException;

	/**
	* Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public PartnerWorker fetchByU_PEI(long userId, long partnerEntryId);

	/**
	* Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public PartnerWorker fetchByU_PEI(long userId, long partnerEntryId,
		boolean retrieveFromCache);

	/**
	* Removes the partner worker where userId = &#63; and partnerEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the partner worker that was removed
	*/
	public PartnerWorker removeByU_PEI(long userId, long partnerEntryId)
		throws NoSuchPartnerWorkerException;

	/**
	* Returns the number of partner workers where userId = &#63; and partnerEntryId = &#63;.
	*
	* @param userId the user ID
	* @param partnerEntryId the partner entry ID
	* @return the number of matching partner workers
	*/
	public int countByU_PEI(long userId, long partnerEntryId);

	/**
	* Returns all the partner workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the matching partner workers
	*/
	public java.util.List<PartnerWorker> findByU_R(long userId, int role);

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
	public java.util.List<PartnerWorker> findByU_R(long userId, int role,
		int start, int end);

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
	public java.util.List<PartnerWorker> findByU_R(long userId, int role,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

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
	public java.util.List<PartnerWorker> findByU_R(long userId, int role,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public PartnerWorker findByU_R_First(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

	/**
	* Returns the first partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public PartnerWorker fetchByU_R_First(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

	/**
	* Returns the last partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public PartnerWorker findByU_R_Last(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

	/**
	* Returns the last partner worker in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public PartnerWorker fetchByU_R_Last(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

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
	public PartnerWorker[] findByU_R_PrevAndNext(long partnerWorkerId,
		long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

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
	public java.util.List<PartnerWorker> findByU_R(long[] userIds, int[] roles);

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
	public java.util.List<PartnerWorker> findByU_R(long[] userIds, int[] roles,
		int start, int end);

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
	public java.util.List<PartnerWorker> findByU_R(long[] userIds, int[] roles,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

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
	public java.util.List<PartnerWorker> findByU_R(long[] userIds, int[] roles,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the partner workers where userId = &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param role the role
	*/
	public void removeByU_R(long userId, int role);

	/**
	* Returns the number of partner workers where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the number of matching partner workers
	*/
	public int countByU_R(long userId, int role);

	/**
	* Returns the number of partner workers where userId = any &#63; and role = any &#63;.
	*
	* @param userIds the user IDs
	* @param roles the roles
	* @return the number of matching partner workers
	*/
	public int countByU_R(long[] userIds, int[] roles);

	/**
	* Returns all the partner workers where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @return the matching partner workers
	*/
	public java.util.List<PartnerWorker> findByPEI_R(long partnerEntryId,
		int role);

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
	public java.util.List<PartnerWorker> findByPEI_R(long partnerEntryId,
		int role, int start, int end);

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
	public java.util.List<PartnerWorker> findByPEI_R(long partnerEntryId,
		int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

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
	public java.util.List<PartnerWorker> findByPEI_R(long partnerEntryId,
		int role, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public PartnerWorker findByPEI_R_First(long partnerEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

	/**
	* Returns the first partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public PartnerWorker fetchByPEI_R_First(long partnerEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker
	* @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	*/
	public PartnerWorker findByPEI_R_Last(long partnerEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

	/**
	* Returns the last partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	*/
	public PartnerWorker fetchByPEI_R_Last(long partnerEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

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
	public PartnerWorker[] findByPEI_R_PrevAndNext(long partnerWorkerId,
		long partnerEntryId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException;

	/**
	* Removes all the partner workers where partnerEntryId = &#63; and role = &#63; from the database.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	*/
	public void removeByPEI_R(long partnerEntryId, int role);

	/**
	* Returns the number of partner workers where partnerEntryId = &#63; and role = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param role the role
	* @return the number of matching partner workers
	*/
	public int countByPEI_R(long partnerEntryId, int role);

	/**
	* Caches the partner worker in the entity cache if it is enabled.
	*
	* @param partnerWorker the partner worker
	*/
	public void cacheResult(PartnerWorker partnerWorker);

	/**
	* Caches the partner workers in the entity cache if it is enabled.
	*
	* @param partnerWorkers the partner workers
	*/
	public void cacheResult(java.util.List<PartnerWorker> partnerWorkers);

	/**
	* Creates a new partner worker with the primary key. Does not add the partner worker to the database.
	*
	* @param partnerWorkerId the primary key for the new partner worker
	* @return the new partner worker
	*/
	public PartnerWorker create(long partnerWorkerId);

	/**
	* Removes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker that was removed
	* @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	*/
	public PartnerWorker remove(long partnerWorkerId)
		throws NoSuchPartnerWorkerException;

	public PartnerWorker updateImpl(PartnerWorker partnerWorker);

	/**
	* Returns the partner worker with the primary key or throws a {@link NoSuchPartnerWorkerException} if it could not be found.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker
	* @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	*/
	public PartnerWorker findByPrimaryKey(long partnerWorkerId)
		throws NoSuchPartnerWorkerException;

	/**
	* Returns the partner worker with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker, or <code>null</code> if a partner worker with the primary key could not be found
	*/
	public PartnerWorker fetchByPrimaryKey(long partnerWorkerId);

	@Override
	public java.util.Map<java.io.Serializable, PartnerWorker> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the partner workers.
	*
	* @return the partner workers
	*/
	public java.util.List<PartnerWorker> findAll();

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
	public java.util.List<PartnerWorker> findAll(int start, int end);

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
	public java.util.List<PartnerWorker> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator);

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
	public java.util.List<PartnerWorker> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the partner workers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of partner workers.
	*
	* @return the number of partner workers
	*/
	public int countAll();
}
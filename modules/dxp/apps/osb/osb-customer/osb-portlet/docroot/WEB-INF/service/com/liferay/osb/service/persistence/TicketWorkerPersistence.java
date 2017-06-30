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

import com.liferay.osb.model.TicketWorker;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketWorkerPersistenceImpl
 * @see TicketWorkerUtil
 * @generated
 */
public interface TicketWorkerPersistence extends BasePersistence<TicketWorker> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketWorkerUtil} to access the ticket worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ticket worker in the entity cache if it is enabled.
	*
	* @param ticketWorker the ticket worker
	*/
	public void cacheResult(com.liferay.osb.model.TicketWorker ticketWorker);

	/**
	* Caches the ticket workers in the entity cache if it is enabled.
	*
	* @param ticketWorkers the ticket workers
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TicketWorker> ticketWorkers);

	/**
	* Creates a new ticket worker with the primary key. Does not add the ticket worker to the database.
	*
	* @param ticketWorkerId the primary key for the new ticket worker
	* @return the new ticket worker
	*/
	public com.liferay.osb.model.TicketWorker create(long ticketWorkerId);

	/**
	* Removes the ticket worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketWorkerId the primary key of the ticket worker
	* @return the ticket worker that was removed
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker remove(long ticketWorkerId)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketWorker updateImpl(
		com.liferay.osb.model.TicketWorker ticketWorker, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket worker with the primary key or throws a {@link com.liferay.osb.NoSuchTicketWorkerException} if it could not be found.
	*
	* @param ticketWorkerId the primary key of the ticket worker
	* @return the ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker findByPrimaryKey(
		long ticketWorkerId)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket worker with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketWorkerId the primary key of the ticket worker
	* @return the ticket worker, or <code>null</code> if a ticket worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker fetchByPrimaryKey(
		long ticketWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @return the range of matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket workers before and after the current ticket worker in the ordered set where userId = &#63;.
	*
	* @param ticketWorkerId the primary key of the current ticket worker
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker[] findByUserId_PrevAndNext(
		long ticketWorkerId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket workers where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findByTicketEntryId(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket workers where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @return the range of matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findByTicketEntryId(
		long ticketEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket workers where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket worker in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker findByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket worker in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker fetchByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket worker in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker findByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket worker in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker fetchByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket workers before and after the current ticket worker in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketWorkerId the primary key of the current ticket worker
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker[] findByTicketEntryId_PrevAndNext(
		long ticketWorkerId, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchTicketWorkerException} if it could not be found.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker findByU_TEI(long userId,
		long ticketEntryId)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker fetchByU_TEI(long userId,
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker fetchByU_TEI(long userId,
		long ticketEntryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or throws a {@link com.liferay.osb.NoSuchTicketWorkerException} if it could not be found.
	*
	* @param ticketEntryId the ticket entry ID
	* @param primary the primary
	* @return the matching ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker findByTEI_P(long ticketEntryId,
		boolean primary)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param primary the primary
	* @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker fetchByTEI_P(long ticketEntryId,
		boolean primary)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param primary the primary
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker fetchByTEI_P(long ticketEntryId,
		boolean primary, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class p k
	* @return the matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findBySCNI_SCPK(
		long sourceClassNameId, long sourceClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class p k
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @return the range of matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findBySCNI_SCPK(
		long sourceClassNameId, long sourceClassPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class p k
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findBySCNI_SCPK(
		long sourceClassNameId, long sourceClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker findBySCNI_SCPK_First(
		long sourceClassNameId, long sourceClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker fetchBySCNI_SCPK_First(
		long sourceClassNameId, long sourceClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker findBySCNI_SCPK_Last(
		long sourceClassNameId, long sourceClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker fetchBySCNI_SCPK_Last(
		long sourceClassNameId, long sourceClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket workers before and after the current ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param ticketWorkerId the primary key of the current ticket worker
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket worker
	* @throws com.liferay.osb.NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker[] findBySCNI_SCPK_PrevAndNext(
		long ticketWorkerId, long sourceClassNameId, long sourceClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket workers.
	*
	* @return the ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @return the range of ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket workers
	* @param end the upper bound of the range of ticket workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketWorker> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket workers where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket workers where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the ticket worker where userId = &#63; and ticketEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @return the ticket worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker removeByU_TEI(long userId,
		long ticketEntryId)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the ticket worker where ticketEntryId = &#63; and primary = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param primary the primary
	* @return the ticket worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketWorker removeByTEI_P(
		long ticketEntryId, boolean primary)
		throws com.liferay.osb.NoSuchTicketWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63; from the database.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySCNI_SCPK(long sourceClassNameId, long sourceClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket workers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket workers where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket workers where userId = &#63; and ticketEntryId = &#63;.
	*
	* @param userId the user ID
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_TEI(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket workers where ticketEntryId = &#63; and primary = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param primary the primary
	* @return the number of matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_P(long ticketEntryId, boolean primary)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	*
	* @param sourceClassNameId the source class name ID
	* @param sourceClassPK the source class p k
	* @return the number of matching ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public int countBySCNI_SCPK(long sourceClassNameId, long sourceClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket workers.
	*
	* @return the number of ticket workers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
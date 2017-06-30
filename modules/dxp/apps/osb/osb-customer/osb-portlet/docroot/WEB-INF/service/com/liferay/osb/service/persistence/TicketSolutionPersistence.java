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

import com.liferay.osb.model.TicketSolution;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket solution service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketSolutionPersistenceImpl
 * @see TicketSolutionUtil
 * @generated
 */
public interface TicketSolutionPersistence extends BasePersistence<TicketSolution> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketSolutionUtil} to access the ticket solution persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ticket solution in the entity cache if it is enabled.
	*
	* @param ticketSolution the ticket solution
	*/
	public void cacheResult(com.liferay.osb.model.TicketSolution ticketSolution);

	/**
	* Caches the ticket solutions in the entity cache if it is enabled.
	*
	* @param ticketSolutions the ticket solutions
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TicketSolution> ticketSolutions);

	/**
	* Creates a new ticket solution with the primary key. Does not add the ticket solution to the database.
	*
	* @param ticketSolutionId the primary key for the new ticket solution
	* @return the new ticket solution
	*/
	public com.liferay.osb.model.TicketSolution create(long ticketSolutionId);

	/**
	* Removes the ticket solution with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution that was removed
	* @throws com.liferay.osb.NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketSolution remove(long ticketSolutionId)
		throws com.liferay.osb.NoSuchTicketSolutionException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketSolution updateImpl(
		com.liferay.osb.model.TicketSolution ticketSolution, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket solution with the primary key or throws a {@link com.liferay.osb.NoSuchTicketSolutionException} if it could not be found.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution
	* @throws com.liferay.osb.NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketSolution findByPrimaryKey(
		long ticketSolutionId)
		throws com.liferay.osb.NoSuchTicketSolutionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket solution with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution, or <code>null</code> if a ticket solution with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketSolution fetchByPrimaryKey(
		long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket solutions where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket solutions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketSolution> findByTicketEntryId(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket solutions where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @return the range of matching ticket solutions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketSolution> findByTicketEntryId(
		long ticketEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket solutions where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket solutions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketSolution> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket solution in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket solution
	* @throws com.liferay.osb.NoSuchTicketSolutionException if a matching ticket solution could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketSolution findByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketSolutionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket solution in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket solution, or <code>null</code> if a matching ticket solution could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketSolution fetchByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket solution in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket solution
	* @throws com.liferay.osb.NoSuchTicketSolutionException if a matching ticket solution could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketSolution findByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketSolutionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket solution in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket solution, or <code>null</code> if a matching ticket solution could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketSolution fetchByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket solutions before and after the current ticket solution in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketSolutionId the primary key of the current ticket solution
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket solution
	* @throws com.liferay.osb.NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketSolution[] findByTicketEntryId_PrevAndNext(
		long ticketSolutionId, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketSolutionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket solutions.
	*
	* @return the ticket solutions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketSolution> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket solutions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @return the range of ticket solutions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketSolution> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket solutions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket solutions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketSolution> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket solutions where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket solutions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket solutions where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket solutions
	* @throws SystemException if a system exception occurred
	*/
	public int countByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket solutions.
	*
	* @return the number of ticket solutions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
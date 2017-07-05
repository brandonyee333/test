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

import com.liferay.osb.exception.NoSuchTicketSolutionException;
import com.liferay.osb.model.TicketSolution;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket solution service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.TicketSolutionPersistenceImpl
 * @see TicketSolutionUtil
 * @generated
 */
@ProviderType
public interface TicketSolutionPersistence extends BasePersistence<TicketSolution> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketSolutionUtil} to access the ticket solution persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the ticket solutions where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket solutions
	*/
	public java.util.List<TicketSolution> findByTicketEntryId(
		long ticketEntryId);

	/**
	* Returns a range of all the ticket solutions where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @return the range of matching ticket solutions
	*/
	public java.util.List<TicketSolution> findByTicketEntryId(
		long ticketEntryId, int start, int end);

	/**
	* Returns an ordered range of all the ticket solutions where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket solutions
	*/
	public java.util.List<TicketSolution> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketSolution> orderByComparator);

	/**
	* Returns an ordered range of all the ticket solutions where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket solutions
	*/
	public java.util.List<TicketSolution> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketSolution> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket solution in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket solution
	* @throws NoSuchTicketSolutionException if a matching ticket solution could not be found
	*/
	public TicketSolution findByTicketEntryId_First(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketSolution> orderByComparator)
		throws NoSuchTicketSolutionException;

	/**
	* Returns the first ticket solution in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket solution, or <code>null</code> if a matching ticket solution could not be found
	*/
	public TicketSolution fetchByTicketEntryId_First(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketSolution> orderByComparator);

	/**
	* Returns the last ticket solution in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket solution
	* @throws NoSuchTicketSolutionException if a matching ticket solution could not be found
	*/
	public TicketSolution findByTicketEntryId_Last(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketSolution> orderByComparator)
		throws NoSuchTicketSolutionException;

	/**
	* Returns the last ticket solution in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket solution, or <code>null</code> if a matching ticket solution could not be found
	*/
	public TicketSolution fetchByTicketEntryId_Last(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketSolution> orderByComparator);

	/**
	* Returns the ticket solutions before and after the current ticket solution in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketSolutionId the primary key of the current ticket solution
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket solution
	* @throws NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	*/
	public TicketSolution[] findByTicketEntryId_PrevAndNext(
		long ticketSolutionId, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketSolution> orderByComparator)
		throws NoSuchTicketSolutionException;

	/**
	* Removes all the ticket solutions where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	*/
	public void removeByTicketEntryId(long ticketEntryId);

	/**
	* Returns the number of ticket solutions where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket solutions
	*/
	public int countByTicketEntryId(long ticketEntryId);

	/**
	* Caches the ticket solution in the entity cache if it is enabled.
	*
	* @param ticketSolution the ticket solution
	*/
	public void cacheResult(TicketSolution ticketSolution);

	/**
	* Caches the ticket solutions in the entity cache if it is enabled.
	*
	* @param ticketSolutions the ticket solutions
	*/
	public void cacheResult(java.util.List<TicketSolution> ticketSolutions);

	/**
	* Creates a new ticket solution with the primary key. Does not add the ticket solution to the database.
	*
	* @param ticketSolutionId the primary key for the new ticket solution
	* @return the new ticket solution
	*/
	public TicketSolution create(long ticketSolutionId);

	/**
	* Removes the ticket solution with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution that was removed
	* @throws NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	*/
	public TicketSolution remove(long ticketSolutionId)
		throws NoSuchTicketSolutionException;

	public TicketSolution updateImpl(TicketSolution ticketSolution);

	/**
	* Returns the ticket solution with the primary key or throws a {@link NoSuchTicketSolutionException} if it could not be found.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution
	* @throws NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	*/
	public TicketSolution findByPrimaryKey(long ticketSolutionId)
		throws NoSuchTicketSolutionException;

	/**
	* Returns the ticket solution with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution, or <code>null</code> if a ticket solution with the primary key could not be found
	*/
	public TicketSolution fetchByPrimaryKey(long ticketSolutionId);

	@Override
	public java.util.Map<java.io.Serializable, TicketSolution> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ticket solutions.
	*
	* @return the ticket solutions
	*/
	public java.util.List<TicketSolution> findAll();

	/**
	* Returns a range of all the ticket solutions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @return the range of ticket solutions
	*/
	public java.util.List<TicketSolution> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ticket solutions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket solutions
	*/
	public java.util.List<TicketSolution> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketSolution> orderByComparator);

	/**
	* Returns an ordered range of all the ticket solutions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket solutions
	*/
	public java.util.List<TicketSolution> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketSolution> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket solutions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ticket solutions.
	*
	* @return the number of ticket solutions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}
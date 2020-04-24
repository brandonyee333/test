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

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopParticipantAssignmentException;
import com.liferay.osb.loop.model.LoopParticipantAssignment;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the loop participant assignment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.loop.service.persistence.impl.LoopParticipantAssignmentPersistenceImpl
 * @see LoopParticipantAssignmentUtil
 * @generated
 */
@ProviderType
public interface LoopParticipantAssignmentPersistence extends BasePersistence<LoopParticipantAssignment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopParticipantAssignmentUtil} to access the loop participant assignment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; or throws a {@link NoSuchLoopParticipantAssignmentException} if it could not be found.
	*
	* @param loopDivisionId the loop division ID
	* @param loopPersonId the loop person ID
	* @return the matching loop participant assignment
	* @throws NoSuchLoopParticipantAssignmentException if a matching loop participant assignment could not be found
	*/
	public LoopParticipantAssignment findByLDI_LPI(long loopDivisionId,
		long loopPersonId) throws NoSuchLoopParticipantAssignmentException;

	/**
	* Returns the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param loopDivisionId the loop division ID
	* @param loopPersonId the loop person ID
	* @return the matching loop participant assignment, or <code>null</code> if a matching loop participant assignment could not be found
	*/
	public LoopParticipantAssignment fetchByLDI_LPI(long loopDivisionId,
		long loopPersonId);

	/**
	* Returns the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param loopDivisionId the loop division ID
	* @param loopPersonId the loop person ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop participant assignment, or <code>null</code> if a matching loop participant assignment could not be found
	*/
	public LoopParticipantAssignment fetchByLDI_LPI(long loopDivisionId,
		long loopPersonId, boolean retrieveFromCache);

	/**
	* Removes the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; from the database.
	*
	* @param loopDivisionId the loop division ID
	* @param loopPersonId the loop person ID
	* @return the loop participant assignment that was removed
	*/
	public LoopParticipantAssignment removeByLDI_LPI(long loopDivisionId,
		long loopPersonId) throws NoSuchLoopParticipantAssignmentException;

	/**
	* Returns the number of loop participant assignments where loopDivisionId = &#63; and loopPersonId = &#63;.
	*
	* @param loopDivisionId the loop division ID
	* @param loopPersonId the loop person ID
	* @return the number of matching loop participant assignments
	*/
	public int countByLDI_LPI(long loopDivisionId, long loopPersonId);

	/**
	* Caches the loop participant assignment in the entity cache if it is enabled.
	*
	* @param loopParticipantAssignment the loop participant assignment
	*/
	public void cacheResult(LoopParticipantAssignment loopParticipantAssignment);

	/**
	* Caches the loop participant assignments in the entity cache if it is enabled.
	*
	* @param loopParticipantAssignments the loop participant assignments
	*/
	public void cacheResult(
		java.util.List<LoopParticipantAssignment> loopParticipantAssignments);

	/**
	* Creates a new loop participant assignment with the primary key. Does not add the loop participant assignment to the database.
	*
	* @param loopParticipantAssignmentId the primary key for the new loop participant assignment
	* @return the new loop participant assignment
	*/
	public LoopParticipantAssignment create(long loopParticipantAssignmentId);

	/**
	* Removes the loop participant assignment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopParticipantAssignmentId the primary key of the loop participant assignment
	* @return the loop participant assignment that was removed
	* @throws NoSuchLoopParticipantAssignmentException if a loop participant assignment with the primary key could not be found
	*/
	public LoopParticipantAssignment remove(long loopParticipantAssignmentId)
		throws NoSuchLoopParticipantAssignmentException;

	public LoopParticipantAssignment updateImpl(
		LoopParticipantAssignment loopParticipantAssignment);

	/**
	* Returns the loop participant assignment with the primary key or throws a {@link NoSuchLoopParticipantAssignmentException} if it could not be found.
	*
	* @param loopParticipantAssignmentId the primary key of the loop participant assignment
	* @return the loop participant assignment
	* @throws NoSuchLoopParticipantAssignmentException if a loop participant assignment with the primary key could not be found
	*/
	public LoopParticipantAssignment findByPrimaryKey(
		long loopParticipantAssignmentId)
		throws NoSuchLoopParticipantAssignmentException;

	/**
	* Returns the loop participant assignment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopParticipantAssignmentId the primary key of the loop participant assignment
	* @return the loop participant assignment, or <code>null</code> if a loop participant assignment with the primary key could not be found
	*/
	public LoopParticipantAssignment fetchByPrimaryKey(
		long loopParticipantAssignmentId);

	@Override
	public java.util.Map<java.io.Serializable, LoopParticipantAssignment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the loop participant assignments.
	*
	* @return the loop participant assignments
	*/
	public java.util.List<LoopParticipantAssignment> findAll();

	/**
	* Returns a range of all the loop participant assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopParticipantAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop participant assignments
	* @param end the upper bound of the range of loop participant assignments (not inclusive)
	* @return the range of loop participant assignments
	*/
	public java.util.List<LoopParticipantAssignment> findAll(int start, int end);

	/**
	* Returns an ordered range of all the loop participant assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopParticipantAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop participant assignments
	* @param end the upper bound of the range of loop participant assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop participant assignments
	*/
	public java.util.List<LoopParticipantAssignment> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopParticipantAssignment> orderByComparator);

	/**
	* Returns an ordered range of all the loop participant assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopParticipantAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop participant assignments
	* @param end the upper bound of the range of loop participant assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop participant assignments
	*/
	public java.util.List<LoopParticipantAssignment> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopParticipantAssignment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the loop participant assignments from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of loop participant assignments.
	*
	* @return the number of loop participant assignments
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}
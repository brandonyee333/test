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

import com.liferay.osb.loop.exception.NoSuchLoopTopicAssignmentException;
import com.liferay.osb.loop.model.LoopTopicAssignment;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the loop topic assignment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.loop.service.persistence.impl.LoopTopicAssignmentPersistenceImpl
 * @see LoopTopicAssignmentUtil
 * @generated
 */
@ProviderType
public interface LoopTopicAssignmentPersistence extends BasePersistence<LoopTopicAssignment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopTopicAssignmentUtil} to access the loop topic assignment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the loop topic assignment in the entity cache if it is enabled.
	*
	* @param loopTopicAssignment the loop topic assignment
	*/
	public void cacheResult(LoopTopicAssignment loopTopicAssignment);

	/**
	* Caches the loop topic assignments in the entity cache if it is enabled.
	*
	* @param loopTopicAssignments the loop topic assignments
	*/
	public void cacheResult(
		java.util.List<LoopTopicAssignment> loopTopicAssignments);

	/**
	* Creates a new loop topic assignment with the primary key. Does not add the loop topic assignment to the database.
	*
	* @param loopTopicAssignmentId the primary key for the new loop topic assignment
	* @return the new loop topic assignment
	*/
	public LoopTopicAssignment create(long loopTopicAssignmentId);

	/**
	* Removes the loop topic assignment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopTopicAssignmentId the primary key of the loop topic assignment
	* @return the loop topic assignment that was removed
	* @throws NoSuchLoopTopicAssignmentException if a loop topic assignment with the primary key could not be found
	*/
	public LoopTopicAssignment remove(long loopTopicAssignmentId)
		throws NoSuchLoopTopicAssignmentException;

	public LoopTopicAssignment updateImpl(
		LoopTopicAssignment loopTopicAssignment);

	/**
	* Returns the loop topic assignment with the primary key or throws a {@link NoSuchLoopTopicAssignmentException} if it could not be found.
	*
	* @param loopTopicAssignmentId the primary key of the loop topic assignment
	* @return the loop topic assignment
	* @throws NoSuchLoopTopicAssignmentException if a loop topic assignment with the primary key could not be found
	*/
	public LoopTopicAssignment findByPrimaryKey(long loopTopicAssignmentId)
		throws NoSuchLoopTopicAssignmentException;

	/**
	* Returns the loop topic assignment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopTopicAssignmentId the primary key of the loop topic assignment
	* @return the loop topic assignment, or <code>null</code> if a loop topic assignment with the primary key could not be found
	*/
	public LoopTopicAssignment fetchByPrimaryKey(long loopTopicAssignmentId);

	@Override
	public java.util.Map<java.io.Serializable, LoopTopicAssignment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the loop topic assignments.
	*
	* @return the loop topic assignments
	*/
	public java.util.List<LoopTopicAssignment> findAll();

	/**
	* Returns a range of all the loop topic assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop topic assignments
	* @param end the upper bound of the range of loop topic assignments (not inclusive)
	* @return the range of loop topic assignments
	*/
	public java.util.List<LoopTopicAssignment> findAll(int start, int end);

	/**
	* Returns an ordered range of all the loop topic assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop topic assignments
	* @param end the upper bound of the range of loop topic assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop topic assignments
	*/
	public java.util.List<LoopTopicAssignment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopTopicAssignment> orderByComparator);

	/**
	* Returns an ordered range of all the loop topic assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop topic assignments
	* @param end the upper bound of the range of loop topic assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop topic assignments
	*/
	public java.util.List<LoopTopicAssignment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopTopicAssignment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the loop topic assignments from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of loop topic assignments.
	*
	* @return the number of loop topic assignments
	*/
	public int countAll();
}
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

package com.liferay.osb.testray.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.exception.NoSuchTestrayAssignmentException;
import com.liferay.osb.testray.model.TestrayAssignment;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the testray assignment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayAssignmentPersistenceImpl
 * @see TestrayAssignmentUtil
 * @generated
 */
@ProviderType
public interface TestrayAssignmentPersistence extends BasePersistence<TestrayAssignment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayAssignmentUtil} to access the testray assignment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the testray assignment in the entity cache if it is enabled.
	*
	* @param testrayAssignment the testray assignment
	*/
	public void cacheResult(TestrayAssignment testrayAssignment);

	/**
	* Caches the testray assignments in the entity cache if it is enabled.
	*
	* @param testrayAssignments the testray assignments
	*/
	public void cacheResult(
		java.util.List<TestrayAssignment> testrayAssignments);

	/**
	* Creates a new testray assignment with the primary key. Does not add the testray assignment to the database.
	*
	* @param testrayAssignmentId the primary key for the new testray assignment
	* @return the new testray assignment
	*/
	public TestrayAssignment create(long testrayAssignmentId);

	/**
	* Removes the testray assignment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayAssignmentId the primary key of the testray assignment
	* @return the testray assignment that was removed
	* @throws NoSuchTestrayAssignmentException if a testray assignment with the primary key could not be found
	*/
	public TestrayAssignment remove(long testrayAssignmentId)
		throws NoSuchTestrayAssignmentException;

	public TestrayAssignment updateImpl(TestrayAssignment testrayAssignment);

	/**
	* Returns the testray assignment with the primary key or throws a {@link NoSuchTestrayAssignmentException} if it could not be found.
	*
	* @param testrayAssignmentId the primary key of the testray assignment
	* @return the testray assignment
	* @throws NoSuchTestrayAssignmentException if a testray assignment with the primary key could not be found
	*/
	public TestrayAssignment findByPrimaryKey(long testrayAssignmentId)
		throws NoSuchTestrayAssignmentException;

	/**
	* Returns the testray assignment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayAssignmentId the primary key of the testray assignment
	* @return the testray assignment, or <code>null</code> if a testray assignment with the primary key could not be found
	*/
	public TestrayAssignment fetchByPrimaryKey(long testrayAssignmentId);

	@Override
	public java.util.Map<java.io.Serializable, TestrayAssignment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the testray assignments.
	*
	* @return the testray assignments
	*/
	public java.util.List<TestrayAssignment> findAll();

	/**
	* Returns a range of all the testray assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray assignments
	* @param end the upper bound of the range of testray assignments (not inclusive)
	* @return the range of testray assignments
	*/
	public java.util.List<TestrayAssignment> findAll(int start, int end);

	/**
	* Returns an ordered range of all the testray assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray assignments
	* @param end the upper bound of the range of testray assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray assignments
	*/
	public java.util.List<TestrayAssignment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayAssignment> orderByComparator);

	/**
	* Returns an ordered range of all the testray assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray assignments
	* @param end the upper bound of the range of testray assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray assignments
	*/
	public java.util.List<TestrayAssignment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayAssignment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the testray assignments from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of testray assignments.
	*
	* @return the number of testray assignments
	*/
	public int countAll();
}
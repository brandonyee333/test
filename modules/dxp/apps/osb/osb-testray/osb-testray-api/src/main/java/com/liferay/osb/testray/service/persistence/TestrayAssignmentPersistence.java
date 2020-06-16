/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.testray.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.exception.NoSuchTestrayAssignmentException;
import com.liferay.osb.testray.model.TestrayAssignment;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray assignment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayAssignmentUtil
 * @generated
 */
@ProviderType
public interface TestrayAssignmentPersistence
	extends BasePersistence<TestrayAssignment> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayAssignmentUtil} to access the testray assignment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayAssignment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

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
	 * Returns the testray assignment with the primary key or throws a <code>NoSuchTestrayAssignmentException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayAssignmentModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray assignments
	 * @param end the upper bound of the range of testray assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray assignments
	 */
	public java.util.List<TestrayAssignment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayAssignment>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray assignments
	 * @param end the upper bound of the range of testray assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray assignments
	 */
	public java.util.List<TestrayAssignment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayAssignment>
			orderByComparator,
		boolean useFinderCache);

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
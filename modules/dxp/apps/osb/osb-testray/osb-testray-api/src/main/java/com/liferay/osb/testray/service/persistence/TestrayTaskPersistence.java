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

import com.liferay.osb.testray.exception.NoSuchTestrayTaskException;
import com.liferay.osb.testray.model.TestrayTask;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayTaskUtil
 * @generated
 */
@ProviderType
public interface TestrayTaskPersistence extends BasePersistence<TestrayTask> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayTaskUtil} to access the testray task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayTask> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the testray task in the entity cache if it is enabled.
	 *
	 * @param testrayTask the testray task
	 */
	public void cacheResult(TestrayTask testrayTask);

	/**
	 * Caches the testray tasks in the entity cache if it is enabled.
	 *
	 * @param testrayTasks the testray tasks
	 */
	public void cacheResult(java.util.List<TestrayTask> testrayTasks);

	/**
	 * Creates a new testray task with the primary key. Does not add the testray task to the database.
	 *
	 * @param testrayTaskId the primary key for the new testray task
	 * @return the new testray task
	 */
	public TestrayTask create(long testrayTaskId);

	/**
	 * Removes the testray task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTaskId the primary key of the testray task
	 * @return the testray task that was removed
	 * @throws NoSuchTestrayTaskException if a testray task with the primary key could not be found
	 */
	public TestrayTask remove(long testrayTaskId)
		throws NoSuchTestrayTaskException;

	public TestrayTask updateImpl(TestrayTask testrayTask);

	/**
	 * Returns the testray task with the primary key or throws a <code>NoSuchTestrayTaskException</code> if it could not be found.
	 *
	 * @param testrayTaskId the primary key of the testray task
	 * @return the testray task
	 * @throws NoSuchTestrayTaskException if a testray task with the primary key could not be found
	 */
	public TestrayTask findByPrimaryKey(long testrayTaskId)
		throws NoSuchTestrayTaskException;

	/**
	 * Returns the testray task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayTaskId the primary key of the testray task
	 * @return the testray task, or <code>null</code> if a testray task with the primary key could not be found
	 */
	public TestrayTask fetchByPrimaryKey(long testrayTaskId);

	/**
	 * Returns all the testray tasks.
	 *
	 * @return the testray tasks
	 */
	public java.util.List<TestrayTask> findAll();

	/**
	 * Returns a range of all the testray tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayTaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray tasks
	 * @param end the upper bound of the range of testray tasks (not inclusive)
	 * @return the range of testray tasks
	 */
	public java.util.List<TestrayTask> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the testray tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayTaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray tasks
	 * @param end the upper bound of the range of testray tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray tasks
	 */
	public java.util.List<TestrayTask> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayTask>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayTaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray tasks
	 * @param end the upper bound of the range of testray tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray tasks
	 */
	public java.util.List<TestrayTask> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayTask>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the testray tasks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray tasks.
	 *
	 * @return the number of testray tasks
	 */
	public int countAll();

	/**
	 * Returns the primaryKeys of testray case types associated with the testray task.
	 *
	 * @param pk the primary key of the testray task
	 * @return long[] of the primaryKeys of testray case types associated with the testray task
	 */
	public long[] getTestrayCaseTypePrimaryKeys(long pk);

	/**
	 * Returns all the testray case types associated with the testray task.
	 *
	 * @param pk the primary key of the testray task
	 * @return the testray case types associated with the testray task
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
		getTestrayCaseTypes(long pk);

	/**
	 * Returns a range of all the testray case types associated with the testray task.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayTaskModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray task
	 * @param start the lower bound of the range of testray tasks
	 * @param end the upper bound of the range of testray tasks (not inclusive)
	 * @return the range of testray case types associated with the testray task
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
		getTestrayCaseTypes(long pk, int start, int end);

	/**
	 * Returns an ordered range of all the testray case types associated with the testray task.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayTaskModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray task
	 * @param start the lower bound of the range of testray tasks
	 * @param end the upper bound of the range of testray tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray case types associated with the testray task
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
		getTestrayCaseTypes(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCaseType>
					orderByComparator);

	/**
	 * Returns the number of testray case types associated with the testray task.
	 *
	 * @param pk the primary key of the testray task
	 * @return the number of testray case types associated with the testray task
	 */
	public int getTestrayCaseTypesSize(long pk);

	/**
	 * Returns <code>true</code> if the testray case type is associated with the testray task.
	 *
	 * @param pk the primary key of the testray task
	 * @param testrayCaseTypePK the primary key of the testray case type
	 * @return <code>true</code> if the testray case type is associated with the testray task; <code>false</code> otherwise
	 */
	public boolean containsTestrayCaseType(long pk, long testrayCaseTypePK);

	/**
	 * Returns <code>true</code> if the testray task has any testray case types associated with it.
	 *
	 * @param pk the primary key of the testray task to check for associations with testray case types
	 * @return <code>true</code> if the testray task has any testray case types associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayCaseTypes(long pk);

	/**
	 * Adds an association between the testray task and the testray case type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray task
	 * @param testrayCaseTypePK the primary key of the testray case type
	 */
	public void addTestrayCaseType(long pk, long testrayCaseTypePK);

	/**
	 * Adds an association between the testray task and the testray case type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray task
	 * @param testrayCaseType the testray case type
	 */
	public void addTestrayCaseType(
		long pk, com.liferay.osb.testray.model.TestrayCaseType testrayCaseType);

	/**
	 * Adds an association between the testray task and the testray case types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray task
	 * @param testrayCaseTypePKs the primary keys of the testray case types
	 */
	public void addTestrayCaseTypes(long pk, long[] testrayCaseTypePKs);

	/**
	 * Adds an association between the testray task and the testray case types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray task
	 * @param testrayCaseTypes the testray case types
	 */
	public void addTestrayCaseTypes(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
			testrayCaseTypes);

	/**
	 * Clears all associations between the testray task and its testray case types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray task to clear the associated testray case types from
	 */
	public void clearTestrayCaseTypes(long pk);

	/**
	 * Removes the association between the testray task and the testray case type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray task
	 * @param testrayCaseTypePK the primary key of the testray case type
	 */
	public void removeTestrayCaseType(long pk, long testrayCaseTypePK);

	/**
	 * Removes the association between the testray task and the testray case type. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray task
	 * @param testrayCaseType the testray case type
	 */
	public void removeTestrayCaseType(
		long pk, com.liferay.osb.testray.model.TestrayCaseType testrayCaseType);

	/**
	 * Removes the association between the testray task and the testray case types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray task
	 * @param testrayCaseTypePKs the primary keys of the testray case types
	 */
	public void removeTestrayCaseTypes(long pk, long[] testrayCaseTypePKs);

	/**
	 * Removes the association between the testray task and the testray case types. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray task
	 * @param testrayCaseTypes the testray case types
	 */
	public void removeTestrayCaseTypes(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
			testrayCaseTypes);

	/**
	 * Sets the testray case types associated with the testray task, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray task
	 * @param testrayCaseTypePKs the primary keys of the testray case types to be associated with the testray task
	 */
	public void setTestrayCaseTypes(long pk, long[] testrayCaseTypePKs);

	/**
	 * Sets the testray case types associated with the testray task, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray task
	 * @param testrayCaseTypes the testray case types to be associated with the testray task
	 */
	public void setTestrayCaseTypes(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
			testrayCaseTypes);

}
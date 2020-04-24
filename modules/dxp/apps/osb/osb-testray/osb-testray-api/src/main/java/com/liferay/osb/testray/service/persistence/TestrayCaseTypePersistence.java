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

import com.liferay.osb.testray.exception.NoSuchTestrayCaseTypeException;
import com.liferay.osb.testray.model.TestrayCaseType;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray case type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCaseTypeUtil
 * @generated
 */
@ProviderType
public interface TestrayCaseTypePersistence
	extends BasePersistence<TestrayCaseType> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayCaseTypeUtil} to access the testray case type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayCaseType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the testray case type where groupId = &#63; and name = &#63; or throws a <code>NoSuchTestrayCaseTypeException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching testray case type
	 * @throws NoSuchTestrayCaseTypeException if a matching testray case type could not be found
	 */
	public TestrayCaseType findByGI_N(long groupId, String name)
		throws NoSuchTestrayCaseTypeException;

	/**
	 * Returns the testray case type where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching testray case type, or <code>null</code> if a matching testray case type could not be found
	 */
	public TestrayCaseType fetchByGI_N(long groupId, String name);

	/**
	 * Returns the testray case type where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray case type, or <code>null</code> if a matching testray case type could not be found
	 */
	public TestrayCaseType fetchByGI_N(
		long groupId, String name, boolean retrieveFromCache);

	/**
	 * Removes the testray case type where groupId = &#63; and name = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the testray case type that was removed
	 */
	public TestrayCaseType removeByGI_N(long groupId, String name)
		throws NoSuchTestrayCaseTypeException;

	/**
	 * Returns the number of testray case types where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching testray case types
	 */
	public int countByGI_N(long groupId, String name);

	/**
	 * Caches the testray case type in the entity cache if it is enabled.
	 *
	 * @param testrayCaseType the testray case type
	 */
	public void cacheResult(TestrayCaseType testrayCaseType);

	/**
	 * Caches the testray case types in the entity cache if it is enabled.
	 *
	 * @param testrayCaseTypes the testray case types
	 */
	public void cacheResult(java.util.List<TestrayCaseType> testrayCaseTypes);

	/**
	 * Creates a new testray case type with the primary key. Does not add the testray case type to the database.
	 *
	 * @param testrayCaseTypeId the primary key for the new testray case type
	 * @return the new testray case type
	 */
	public TestrayCaseType create(long testrayCaseTypeId);

	/**
	 * Removes the testray case type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type that was removed
	 * @throws NoSuchTestrayCaseTypeException if a testray case type with the primary key could not be found
	 */
	public TestrayCaseType remove(long testrayCaseTypeId)
		throws NoSuchTestrayCaseTypeException;

	public TestrayCaseType updateImpl(TestrayCaseType testrayCaseType);

	/**
	 * Returns the testray case type with the primary key or throws a <code>NoSuchTestrayCaseTypeException</code> if it could not be found.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type
	 * @throws NoSuchTestrayCaseTypeException if a testray case type with the primary key could not be found
	 */
	public TestrayCaseType findByPrimaryKey(long testrayCaseTypeId)
		throws NoSuchTestrayCaseTypeException;

	/**
	 * Returns the testray case type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type, or <code>null</code> if a testray case type with the primary key could not be found
	 */
	public TestrayCaseType fetchByPrimaryKey(long testrayCaseTypeId);

	/**
	 * Returns all the testray case types.
	 *
	 * @return the testray case types
	 */
	public java.util.List<TestrayCaseType> findAll();

	/**
	 * Returns a range of all the testray case types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @return the range of testray case types
	 */
	public java.util.List<TestrayCaseType> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the testray case types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray case types
	 */
	public java.util.List<TestrayCaseType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseType>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray case types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray case types
	 */
	public java.util.List<TestrayCaseType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseType>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the testray case types from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray case types.
	 *
	 * @return the number of testray case types
	 */
	public int countAll();

	/**
	 * Returns the primaryKeys of testray tasks associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @return long[] of the primaryKeys of testray tasks associated with the testray case type
	 */
	public long[] getTestrayTaskPrimaryKeys(long pk);

	/**
	 * Returns all the testray tasks associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @return the testray tasks associated with the testray case type
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayTasks(long pk);

	/**
	 * Returns a range of all the testray tasks associated with the testray case type.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case type
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @return the range of testray tasks associated with the testray case type
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayTasks(long pk, int start, int end);

	/**
	 * Returns an ordered range of all the testray tasks associated with the testray case type.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case type
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray tasks associated with the testray case type
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayTasks(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayTask> orderByComparator);

	/**
	 * Returns the number of testray tasks associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @return the number of testray tasks associated with the testray case type
	 */
	public int getTestrayTasksSize(long pk);

	/**
	 * Returns <code>true</code> if the testray task is associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPK the primary key of the testray task
	 * @return <code>true</code> if the testray task is associated with the testray case type; <code>false</code> otherwise
	 */
	public boolean containsTestrayTask(long pk, long testrayTaskPK);

	/**
	 * Returns <code>true</code> if the testray case type has any testray tasks associated with it.
	 *
	 * @param pk the primary key of the testray case type to check for associations with testray tasks
	 * @return <code>true</code> if the testray case type has any testray tasks associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayTasks(long pk);

	/**
	 * Adds an association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPK the primary key of the testray task
	 */
	public void addTestrayTask(long pk, long testrayTaskPK);

	/**
	 * Adds an association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTask the testray task
	 */
	public void addTestrayTask(
		long pk, com.liferay.osb.testray.model.TestrayTask testrayTask);

	/**
	 * Adds an association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPKs the primary keys of the testray tasks
	 */
	public void addTestrayTasks(long pk, long[] testrayTaskPKs);

	/**
	 * Adds an association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTasks the testray tasks
	 */
	public void addTestrayTasks(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayTask> testrayTasks);

	/**
	 * Clears all associations between the testray case type and its testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type to clear the associated testray tasks from
	 */
	public void clearTestrayTasks(long pk);

	/**
	 * Removes the association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPK the primary key of the testray task
	 */
	public void removeTestrayTask(long pk, long testrayTaskPK);

	/**
	 * Removes the association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTask the testray task
	 */
	public void removeTestrayTask(
		long pk, com.liferay.osb.testray.model.TestrayTask testrayTask);

	/**
	 * Removes the association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPKs the primary keys of the testray tasks
	 */
	public void removeTestrayTasks(long pk, long[] testrayTaskPKs);

	/**
	 * Removes the association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTasks the testray tasks
	 */
	public void removeTestrayTasks(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayTask> testrayTasks);

	/**
	 * Sets the testray tasks associated with the testray case type, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPKs the primary keys of the testray tasks to be associated with the testray case type
	 */
	public void setTestrayTasks(long pk, long[] testrayTaskPKs);

	/**
	 * Sets the testray tasks associated with the testray case type, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTasks the testray tasks to be associated with the testray case type
	 */
	public void setTestrayTasks(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayTask> testrayTasks);

}
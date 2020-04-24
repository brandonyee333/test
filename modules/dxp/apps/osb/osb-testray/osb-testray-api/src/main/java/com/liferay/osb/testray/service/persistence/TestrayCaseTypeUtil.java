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

import com.liferay.osb.testray.model.TestrayCaseType;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the testray case type service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayCaseTypePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCaseTypePersistence
 * @generated
 */
@ProviderType
public class TestrayCaseTypeUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(TestrayCaseType testrayCaseType) {
		getPersistence().clearCache(testrayCaseType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, TestrayCaseType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayCaseType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayCaseType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayCaseType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayCaseType> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayCaseType update(TestrayCaseType testrayCaseType) {
		return getPersistence().update(testrayCaseType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayCaseType update(
		TestrayCaseType testrayCaseType, ServiceContext serviceContext) {

		return getPersistence().update(testrayCaseType, serviceContext);
	}

	/**
	 * Returns the testray case type where groupId = &#63; and name = &#63; or throws a <code>NoSuchTestrayCaseTypeException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching testray case type
	 * @throws NoSuchTestrayCaseTypeException if a matching testray case type could not be found
	 */
	public static TestrayCaseType findByGI_N(long groupId, String name)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseTypeException {

		return getPersistence().findByGI_N(groupId, name);
	}

	/**
	 * Returns the testray case type where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching testray case type, or <code>null</code> if a matching testray case type could not be found
	 */
	public static TestrayCaseType fetchByGI_N(long groupId, String name) {
		return getPersistence().fetchByGI_N(groupId, name);
	}

	/**
	 * Returns the testray case type where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray case type, or <code>null</code> if a matching testray case type could not be found
	 */
	public static TestrayCaseType fetchByGI_N(
		long groupId, String name, boolean retrieveFromCache) {

		return getPersistence().fetchByGI_N(groupId, name, retrieveFromCache);
	}

	/**
	 * Removes the testray case type where groupId = &#63; and name = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the testray case type that was removed
	 */
	public static TestrayCaseType removeByGI_N(long groupId, String name)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseTypeException {

		return getPersistence().removeByGI_N(groupId, name);
	}

	/**
	 * Returns the number of testray case types where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching testray case types
	 */
	public static int countByGI_N(long groupId, String name) {
		return getPersistence().countByGI_N(groupId, name);
	}

	/**
	 * Caches the testray case type in the entity cache if it is enabled.
	 *
	 * @param testrayCaseType the testray case type
	 */
	public static void cacheResult(TestrayCaseType testrayCaseType) {
		getPersistence().cacheResult(testrayCaseType);
	}

	/**
	 * Caches the testray case types in the entity cache if it is enabled.
	 *
	 * @param testrayCaseTypes the testray case types
	 */
	public static void cacheResult(List<TestrayCaseType> testrayCaseTypes) {
		getPersistence().cacheResult(testrayCaseTypes);
	}

	/**
	 * Creates a new testray case type with the primary key. Does not add the testray case type to the database.
	 *
	 * @param testrayCaseTypeId the primary key for the new testray case type
	 * @return the new testray case type
	 */
	public static TestrayCaseType create(long testrayCaseTypeId) {
		return getPersistence().create(testrayCaseTypeId);
	}

	/**
	 * Removes the testray case type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type that was removed
	 * @throws NoSuchTestrayCaseTypeException if a testray case type with the primary key could not be found
	 */
	public static TestrayCaseType remove(long testrayCaseTypeId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseTypeException {

		return getPersistence().remove(testrayCaseTypeId);
	}

	public static TestrayCaseType updateImpl(TestrayCaseType testrayCaseType) {
		return getPersistence().updateImpl(testrayCaseType);
	}

	/**
	 * Returns the testray case type with the primary key or throws a <code>NoSuchTestrayCaseTypeException</code> if it could not be found.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type
	 * @throws NoSuchTestrayCaseTypeException if a testray case type with the primary key could not be found
	 */
	public static TestrayCaseType findByPrimaryKey(long testrayCaseTypeId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseTypeException {

		return getPersistence().findByPrimaryKey(testrayCaseTypeId);
	}

	/**
	 * Returns the testray case type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type, or <code>null</code> if a testray case type with the primary key could not be found
	 */
	public static TestrayCaseType fetchByPrimaryKey(long testrayCaseTypeId) {
		return getPersistence().fetchByPrimaryKey(testrayCaseTypeId);
	}

	/**
	 * Returns all the testray case types.
	 *
	 * @return the testray case types
	 */
	public static List<TestrayCaseType> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<TestrayCaseType> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<TestrayCaseType> findAll(
		int start, int end,
		OrderByComparator<TestrayCaseType> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<TestrayCaseType> findAll(
		int start, int end,
		OrderByComparator<TestrayCaseType> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the testray case types from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray case types.
	 *
	 * @return the number of testray case types
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of testray tasks associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @return long[] of the primaryKeys of testray tasks associated with the testray case type
	 */
	public static long[] getTestrayTaskPrimaryKeys(long pk) {
		return getPersistence().getTestrayTaskPrimaryKeys(pk);
	}

	/**
	 * Returns all the testray tasks associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @return the testray tasks associated with the testray case type
	 */
	public static List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayTasks(long pk) {

		return getPersistence().getTestrayTasks(pk);
	}

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
	public static List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayTasks(long pk, int start, int end) {

		return getPersistence().getTestrayTasks(pk, start, end);
	}

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
	public static List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayTasks(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestrayTask>
				orderByComparator) {

		return getPersistence().getTestrayTasks(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray tasks associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @return the number of testray tasks associated with the testray case type
	 */
	public static int getTestrayTasksSize(long pk) {
		return getPersistence().getTestrayTasksSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray task is associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPK the primary key of the testray task
	 * @return <code>true</code> if the testray task is associated with the testray case type; <code>false</code> otherwise
	 */
	public static boolean containsTestrayTask(long pk, long testrayTaskPK) {
		return getPersistence().containsTestrayTask(pk, testrayTaskPK);
	}

	/**
	 * Returns <code>true</code> if the testray case type has any testray tasks associated with it.
	 *
	 * @param pk the primary key of the testray case type to check for associations with testray tasks
	 * @return <code>true</code> if the testray case type has any testray tasks associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestrayTasks(long pk) {
		return getPersistence().containsTestrayTasks(pk);
	}

	/**
	 * Adds an association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPK the primary key of the testray task
	 */
	public static void addTestrayTask(long pk, long testrayTaskPK) {
		getPersistence().addTestrayTask(pk, testrayTaskPK);
	}

	/**
	 * Adds an association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTask the testray task
	 */
	public static void addTestrayTask(
		long pk, com.liferay.osb.testray.model.TestrayTask testrayTask) {

		getPersistence().addTestrayTask(pk, testrayTask);
	}

	/**
	 * Adds an association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPKs the primary keys of the testray tasks
	 */
	public static void addTestrayTasks(long pk, long[] testrayTaskPKs) {
		getPersistence().addTestrayTasks(pk, testrayTaskPKs);
	}

	/**
	 * Adds an association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTasks the testray tasks
	 */
	public static void addTestrayTasks(
		long pk, List<com.liferay.osb.testray.model.TestrayTask> testrayTasks) {

		getPersistence().addTestrayTasks(pk, testrayTasks);
	}

	/**
	 * Clears all associations between the testray case type and its testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type to clear the associated testray tasks from
	 */
	public static void clearTestrayTasks(long pk) {
		getPersistence().clearTestrayTasks(pk);
	}

	/**
	 * Removes the association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPK the primary key of the testray task
	 */
	public static void removeTestrayTask(long pk, long testrayTaskPK) {
		getPersistence().removeTestrayTask(pk, testrayTaskPK);
	}

	/**
	 * Removes the association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTask the testray task
	 */
	public static void removeTestrayTask(
		long pk, com.liferay.osb.testray.model.TestrayTask testrayTask) {

		getPersistence().removeTestrayTask(pk, testrayTask);
	}

	/**
	 * Removes the association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPKs the primary keys of the testray tasks
	 */
	public static void removeTestrayTasks(long pk, long[] testrayTaskPKs) {
		getPersistence().removeTestrayTasks(pk, testrayTaskPKs);
	}

	/**
	 * Removes the association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTasks the testray tasks
	 */
	public static void removeTestrayTasks(
		long pk, List<com.liferay.osb.testray.model.TestrayTask> testrayTasks) {

		getPersistence().removeTestrayTasks(pk, testrayTasks);
	}

	/**
	 * Sets the testray tasks associated with the testray case type, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPKs the primary keys of the testray tasks to be associated with the testray case type
	 */
	public static void setTestrayTasks(long pk, long[] testrayTaskPKs) {
		getPersistence().setTestrayTasks(pk, testrayTaskPKs);
	}

	/**
	 * Sets the testray tasks associated with the testray case type, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTasks the testray tasks to be associated with the testray case type
	 */
	public static void setTestrayTasks(
		long pk, List<com.liferay.osb.testray.model.TestrayTask> testrayTasks) {

		getPersistence().setTestrayTasks(pk, testrayTasks);
	}

	public static TestrayCaseTypePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayCaseTypePersistence, TestrayCaseTypePersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayCaseTypePersistence.class);

		ServiceTracker<TestrayCaseTypePersistence, TestrayCaseTypePersistence>
			serviceTracker =
				new ServiceTracker
					<TestrayCaseTypePersistence, TestrayCaseTypePersistence>(
						bundle.getBundleContext(),
						TestrayCaseTypePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
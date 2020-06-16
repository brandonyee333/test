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

import com.liferay.osb.testray.model.TestrayProductVersion;
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
 * The persistence utility for the testray product version service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayProductVersionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayProductVersionPersistence
 * @generated
 */
public class TestrayProductVersionUtil {

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
	public static void clearCache(TestrayProductVersion testrayProductVersion) {
		getPersistence().clearCache(testrayProductVersion);
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
	public static Map<Serializable, TestrayProductVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayProductVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayProductVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayProductVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayProductVersion> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayProductVersion update(
		TestrayProductVersion testrayProductVersion) {

		return getPersistence().update(testrayProductVersion);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayProductVersion update(
		TestrayProductVersion testrayProductVersion,
		ServiceContext serviceContext) {

		return getPersistence().update(testrayProductVersion, serviceContext);
	}

	/**
	 * Returns the testray product version where testrayProjectId = &#63; and name = &#63; or throws a <code>NoSuchTestrayProductVersionException</code> if it could not be found.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray product version
	 * @throws NoSuchTestrayProductVersionException if a matching testray product version could not be found
	 */
	public static TestrayProductVersion findByT_N(
			long testrayProjectId, String name)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayProductVersionException {

		return getPersistence().findByT_N(testrayProjectId, name);
	}

	/**
	 * Returns the testray product version where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray product version, or <code>null</code> if a matching testray product version could not be found
	 */
	public static TestrayProductVersion fetchByT_N(
		long testrayProjectId, String name) {

		return getPersistence().fetchByT_N(testrayProjectId, name);
	}

	/**
	 * Returns the testray product version where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching testray product version, or <code>null</code> if a matching testray product version could not be found
	 */
	public static TestrayProductVersion fetchByT_N(
		long testrayProjectId, String name, boolean useFinderCache) {

		return getPersistence().fetchByT_N(
			testrayProjectId, name, useFinderCache);
	}

	/**
	 * Removes the testray product version where testrayProjectId = &#63; and name = &#63; from the database.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the testray product version that was removed
	 */
	public static TestrayProductVersion removeByT_N(
			long testrayProjectId, String name)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayProductVersionException {

		return getPersistence().removeByT_N(testrayProjectId, name);
	}

	/**
	 * Returns the number of testray product versions where testrayProjectId = &#63; and name = &#63;.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the number of matching testray product versions
	 */
	public static int countByT_N(long testrayProjectId, String name) {
		return getPersistence().countByT_N(testrayProjectId, name);
	}

	/**
	 * Caches the testray product version in the entity cache if it is enabled.
	 *
	 * @param testrayProductVersion the testray product version
	 */
	public static void cacheResult(
		TestrayProductVersion testrayProductVersion) {

		getPersistence().cacheResult(testrayProductVersion);
	}

	/**
	 * Caches the testray product versions in the entity cache if it is enabled.
	 *
	 * @param testrayProductVersions the testray product versions
	 */
	public static void cacheResult(
		List<TestrayProductVersion> testrayProductVersions) {

		getPersistence().cacheResult(testrayProductVersions);
	}

	/**
	 * Creates a new testray product version with the primary key. Does not add the testray product version to the database.
	 *
	 * @param testrayProductVersionId the primary key for the new testray product version
	 * @return the new testray product version
	 */
	public static TestrayProductVersion create(long testrayProductVersionId) {
		return getPersistence().create(testrayProductVersionId);
	}

	/**
	 * Removes the testray product version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayProductVersionId the primary key of the testray product version
	 * @return the testray product version that was removed
	 * @throws NoSuchTestrayProductVersionException if a testray product version with the primary key could not be found
	 */
	public static TestrayProductVersion remove(long testrayProductVersionId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayProductVersionException {

		return getPersistence().remove(testrayProductVersionId);
	}

	public static TestrayProductVersion updateImpl(
		TestrayProductVersion testrayProductVersion) {

		return getPersistence().updateImpl(testrayProductVersion);
	}

	/**
	 * Returns the testray product version with the primary key or throws a <code>NoSuchTestrayProductVersionException</code> if it could not be found.
	 *
	 * @param testrayProductVersionId the primary key of the testray product version
	 * @return the testray product version
	 * @throws NoSuchTestrayProductVersionException if a testray product version with the primary key could not be found
	 */
	public static TestrayProductVersion findByPrimaryKey(
			long testrayProductVersionId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayProductVersionException {

		return getPersistence().findByPrimaryKey(testrayProductVersionId);
	}

	/**
	 * Returns the testray product version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayProductVersionId the primary key of the testray product version
	 * @return the testray product version, or <code>null</code> if a testray product version with the primary key could not be found
	 */
	public static TestrayProductVersion fetchByPrimaryKey(
		long testrayProductVersionId) {

		return getPersistence().fetchByPrimaryKey(testrayProductVersionId);
	}

	/**
	 * Returns all the testray product versions.
	 *
	 * @return the testray product versions
	 */
	public static List<TestrayProductVersion> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray product versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayProductVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray product versions
	 * @param end the upper bound of the range of testray product versions (not inclusive)
	 * @return the range of testray product versions
	 */
	public static List<TestrayProductVersion> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray product versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayProductVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray product versions
	 * @param end the upper bound of the range of testray product versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray product versions
	 */
	public static List<TestrayProductVersion> findAll(
		int start, int end,
		OrderByComparator<TestrayProductVersion> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray product versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayProductVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray product versions
	 * @param end the upper bound of the range of testray product versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray product versions
	 */
	public static List<TestrayProductVersion> findAll(
		int start, int end,
		OrderByComparator<TestrayProductVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the testray product versions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray product versions.
	 *
	 * @return the number of testray product versions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayProductVersionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayProductVersionPersistence, TestrayProductVersionPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayProductVersionPersistence.class);

		ServiceTracker
			<TestrayProductVersionPersistence, TestrayProductVersionPersistence>
				serviceTracker =
					new ServiceTracker
						<TestrayProductVersionPersistence,
						 TestrayProductVersionPersistence>(
							 bundle.getBundleContext(),
							 TestrayProductVersionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
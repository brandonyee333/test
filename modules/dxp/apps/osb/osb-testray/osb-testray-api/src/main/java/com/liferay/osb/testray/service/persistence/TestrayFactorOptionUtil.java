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

import com.liferay.osb.testray.model.TestrayFactorOption;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the testray factor option service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayFactorOptionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayFactorOptionPersistence
 * @generated
 */
public class TestrayFactorOptionUtil {

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
	public static void clearCache(TestrayFactorOption testrayFactorOption) {
		getPersistence().clearCache(testrayFactorOption);
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
	public static Map<Serializable, TestrayFactorOption> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayFactorOption> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayFactorOption> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayFactorOption> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayFactorOption> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayFactorOption update(
		TestrayFactorOption testrayFactorOption) {

		return getPersistence().update(testrayFactorOption);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayFactorOption update(
		TestrayFactorOption testrayFactorOption,
		ServiceContext serviceContext) {

		return getPersistence().update(testrayFactorOption, serviceContext);
	}

	/**
	 * Returns the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; or throws a <code>NoSuchTestrayFactorOptionException</code> if it could not be found.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID
	 * @param name the name
	 * @return the matching testray factor option
	 * @throws NoSuchTestrayFactorOptionException if a matching testray factor option could not be found
	 */
	public static TestrayFactorOption findByT_N(
			long testrayFactorCategoryId, String name)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayFactorOptionException {

		return getPersistence().findByT_N(testrayFactorCategoryId, name);
	}

	/**
	 * Returns the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID
	 * @param name the name
	 * @return the matching testray factor option, or <code>null</code> if a matching testray factor option could not be found
	 */
	public static TestrayFactorOption fetchByT_N(
		long testrayFactorCategoryId, String name) {

		return getPersistence().fetchByT_N(testrayFactorCategoryId, name);
	}

	/**
	 * Returns the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching testray factor option, or <code>null</code> if a matching testray factor option could not be found
	 */
	public static TestrayFactorOption fetchByT_N(
		long testrayFactorCategoryId, String name, boolean useFinderCache) {

		return getPersistence().fetchByT_N(
			testrayFactorCategoryId, name, useFinderCache);
	}

	/**
	 * Removes the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; from the database.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID
	 * @param name the name
	 * @return the testray factor option that was removed
	 */
	public static TestrayFactorOption removeByT_N(
			long testrayFactorCategoryId, String name)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayFactorOptionException {

		return getPersistence().removeByT_N(testrayFactorCategoryId, name);
	}

	/**
	 * Returns the number of testray factor options where testrayFactorCategoryId = &#63; and name = &#63;.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID
	 * @param name the name
	 * @return the number of matching testray factor options
	 */
	public static int countByT_N(long testrayFactorCategoryId, String name) {
		return getPersistence().countByT_N(testrayFactorCategoryId, name);
	}

	/**
	 * Caches the testray factor option in the entity cache if it is enabled.
	 *
	 * @param testrayFactorOption the testray factor option
	 */
	public static void cacheResult(TestrayFactorOption testrayFactorOption) {
		getPersistence().cacheResult(testrayFactorOption);
	}

	/**
	 * Caches the testray factor options in the entity cache if it is enabled.
	 *
	 * @param testrayFactorOptions the testray factor options
	 */
	public static void cacheResult(
		List<TestrayFactorOption> testrayFactorOptions) {

		getPersistence().cacheResult(testrayFactorOptions);
	}

	/**
	 * Creates a new testray factor option with the primary key. Does not add the testray factor option to the database.
	 *
	 * @param testrayFactorOptionId the primary key for the new testray factor option
	 * @return the new testray factor option
	 */
	public static TestrayFactorOption create(long testrayFactorOptionId) {
		return getPersistence().create(testrayFactorOptionId);
	}

	/**
	 * Removes the testray factor option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorOptionId the primary key of the testray factor option
	 * @return the testray factor option that was removed
	 * @throws NoSuchTestrayFactorOptionException if a testray factor option with the primary key could not be found
	 */
	public static TestrayFactorOption remove(long testrayFactorOptionId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayFactorOptionException {

		return getPersistence().remove(testrayFactorOptionId);
	}

	public static TestrayFactorOption updateImpl(
		TestrayFactorOption testrayFactorOption) {

		return getPersistence().updateImpl(testrayFactorOption);
	}

	/**
	 * Returns the testray factor option with the primary key or throws a <code>NoSuchTestrayFactorOptionException</code> if it could not be found.
	 *
	 * @param testrayFactorOptionId the primary key of the testray factor option
	 * @return the testray factor option
	 * @throws NoSuchTestrayFactorOptionException if a testray factor option with the primary key could not be found
	 */
	public static TestrayFactorOption findByPrimaryKey(
			long testrayFactorOptionId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayFactorOptionException {

		return getPersistence().findByPrimaryKey(testrayFactorOptionId);
	}

	/**
	 * Returns the testray factor option with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayFactorOptionId the primary key of the testray factor option
	 * @return the testray factor option, or <code>null</code> if a testray factor option with the primary key could not be found
	 */
	public static TestrayFactorOption fetchByPrimaryKey(
		long testrayFactorOptionId) {

		return getPersistence().fetchByPrimaryKey(testrayFactorOptionId);
	}

	/**
	 * Returns all the testray factor options.
	 *
	 * @return the testray factor options
	 */
	public static List<TestrayFactorOption> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray factor options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayFactorOptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor options
	 * @param end the upper bound of the range of testray factor options (not inclusive)
	 * @return the range of testray factor options
	 */
	public static List<TestrayFactorOption> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray factor options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayFactorOptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor options
	 * @param end the upper bound of the range of testray factor options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray factor options
	 */
	public static List<TestrayFactorOption> findAll(
		int start, int end,
		OrderByComparator<TestrayFactorOption> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray factor options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayFactorOptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor options
	 * @param end the upper bound of the range of testray factor options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray factor options
	 */
	public static List<TestrayFactorOption> findAll(
		int start, int end,
		OrderByComparator<TestrayFactorOption> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the testray factor options from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray factor options.
	 *
	 * @return the number of testray factor options
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayFactorOptionPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		TestrayFactorOptionPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile TestrayFactorOptionPersistence _persistence;

}
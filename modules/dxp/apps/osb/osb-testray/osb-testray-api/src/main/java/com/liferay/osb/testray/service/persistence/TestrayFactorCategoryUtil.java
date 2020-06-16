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

import com.liferay.osb.testray.model.TestrayFactorCategory;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the testray factor category service. This utility wraps {@link com.liferay.osb.testray.service.persistence.impl.TestrayFactorCategoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayFactorCategoryPersistence
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayFactorCategoryPersistenceImpl
 * @generated
 */
@ProviderType
public class TestrayFactorCategoryUtil {
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
	public static void clearCache(TestrayFactorCategory testrayFactorCategory) {
		getPersistence().clearCache(testrayFactorCategory);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayFactorCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayFactorCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayFactorCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayFactorCategory> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayFactorCategory update(
		TestrayFactorCategory testrayFactorCategory) {
		return getPersistence().update(testrayFactorCategory);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayFactorCategory update(
		TestrayFactorCategory testrayFactorCategory,
		ServiceContext serviceContext) {
		return getPersistence().update(testrayFactorCategory, serviceContext);
	}

	/**
	* Returns the testray factor category where groupId = &#63; and name = &#63; or throws a {@link NoSuchTestrayFactorCategoryException} if it could not be found.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching testray factor category
	* @throws NoSuchTestrayFactorCategoryException if a matching testray factor category could not be found
	*/
	public static TestrayFactorCategory findByG_N(long groupId,
		java.lang.String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayFactorCategoryException {
		return getPersistence().findByG_N(groupId, name);
	}

	/**
	* Returns the testray factor category where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching testray factor category, or <code>null</code> if a matching testray factor category could not be found
	*/
	public static TestrayFactorCategory fetchByG_N(long groupId,
		java.lang.String name) {
		return getPersistence().fetchByG_N(groupId, name);
	}

	/**
	* Returns the testray factor category where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching testray factor category, or <code>null</code> if a matching testray factor category could not be found
	*/
	public static TestrayFactorCategory fetchByG_N(long groupId,
		java.lang.String name, boolean retrieveFromCache) {
		return getPersistence().fetchByG_N(groupId, name, retrieveFromCache);
	}

	/**
	* Removes the testray factor category where groupId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the testray factor category that was removed
	*/
	public static TestrayFactorCategory removeByG_N(long groupId,
		java.lang.String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayFactorCategoryException {
		return getPersistence().removeByG_N(groupId, name);
	}

	/**
	* Returns the number of testray factor categories where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching testray factor categories
	*/
	public static int countByG_N(long groupId, java.lang.String name) {
		return getPersistence().countByG_N(groupId, name);
	}

	/**
	* Caches the testray factor category in the entity cache if it is enabled.
	*
	* @param testrayFactorCategory the testray factor category
	*/
	public static void cacheResult(TestrayFactorCategory testrayFactorCategory) {
		getPersistence().cacheResult(testrayFactorCategory);
	}

	/**
	* Caches the testray factor categories in the entity cache if it is enabled.
	*
	* @param testrayFactorCategories the testray factor categories
	*/
	public static void cacheResult(
		List<TestrayFactorCategory> testrayFactorCategories) {
		getPersistence().cacheResult(testrayFactorCategories);
	}

	/**
	* Creates a new testray factor category with the primary key. Does not add the testray factor category to the database.
	*
	* @param testrayFactorCategoryId the primary key for the new testray factor category
	* @return the new testray factor category
	*/
	public static TestrayFactorCategory create(long testrayFactorCategoryId) {
		return getPersistence().create(testrayFactorCategoryId);
	}

	/**
	* Removes the testray factor category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayFactorCategoryId the primary key of the testray factor category
	* @return the testray factor category that was removed
	* @throws NoSuchTestrayFactorCategoryException if a testray factor category with the primary key could not be found
	*/
	public static TestrayFactorCategory remove(long testrayFactorCategoryId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayFactorCategoryException {
		return getPersistence().remove(testrayFactorCategoryId);
	}

	public static TestrayFactorCategory updateImpl(
		TestrayFactorCategory testrayFactorCategory) {
		return getPersistence().updateImpl(testrayFactorCategory);
	}

	/**
	* Returns the testray factor category with the primary key or throws a {@link NoSuchTestrayFactorCategoryException} if it could not be found.
	*
	* @param testrayFactorCategoryId the primary key of the testray factor category
	* @return the testray factor category
	* @throws NoSuchTestrayFactorCategoryException if a testray factor category with the primary key could not be found
	*/
	public static TestrayFactorCategory findByPrimaryKey(
		long testrayFactorCategoryId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayFactorCategoryException {
		return getPersistence().findByPrimaryKey(testrayFactorCategoryId);
	}

	/**
	* Returns the testray factor category with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayFactorCategoryId the primary key of the testray factor category
	* @return the testray factor category, or <code>null</code> if a testray factor category with the primary key could not be found
	*/
	public static TestrayFactorCategory fetchByPrimaryKey(
		long testrayFactorCategoryId) {
		return getPersistence().fetchByPrimaryKey(testrayFactorCategoryId);
	}

	public static java.util.Map<java.io.Serializable, TestrayFactorCategory> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the testray factor categories.
	*
	* @return the testray factor categories
	*/
	public static List<TestrayFactorCategory> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the testray factor categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray factor categories
	* @param end the upper bound of the range of testray factor categories (not inclusive)
	* @return the range of testray factor categories
	*/
	public static List<TestrayFactorCategory> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the testray factor categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray factor categories
	* @param end the upper bound of the range of testray factor categories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray factor categories
	*/
	public static List<TestrayFactorCategory> findAll(int start, int end,
		OrderByComparator<TestrayFactorCategory> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the testray factor categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray factor categories
	* @param end the upper bound of the range of testray factor categories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray factor categories
	*/
	public static List<TestrayFactorCategory> findAll(int start, int end,
		OrderByComparator<TestrayFactorCategory> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the testray factor categories from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of testray factor categories.
	*
	* @return the number of testray factor categories
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayFactorCategoryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayFactorCategoryPersistence, TestrayFactorCategoryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(TestrayFactorCategoryPersistence.class);
}
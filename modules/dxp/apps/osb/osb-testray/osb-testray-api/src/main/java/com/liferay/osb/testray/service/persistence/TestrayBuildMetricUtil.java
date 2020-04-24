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

import com.liferay.osb.testray.model.TestrayBuildMetric;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the testray build metric service. This utility wraps {@link com.liferay.osb.testray.service.persistence.impl.TestrayBuildMetricPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayBuildMetricPersistence
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayBuildMetricPersistenceImpl
 * @generated
 */
@ProviderType
public class TestrayBuildMetricUtil {
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
	public static void clearCache(TestrayBuildMetric testrayBuildMetric) {
		getPersistence().clearCache(testrayBuildMetric);
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
	public static List<TestrayBuildMetric> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayBuildMetric> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayBuildMetric> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayBuildMetric> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayBuildMetric update(
		TestrayBuildMetric testrayBuildMetric) {
		return getPersistence().update(testrayBuildMetric);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayBuildMetric update(
		TestrayBuildMetric testrayBuildMetric, ServiceContext serviceContext) {
		return getPersistence().update(testrayBuildMetric, serviceContext);
	}

	/**
	* Returns the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; or throws a {@link NoSuchTestrayBuildMetricException} if it could not be found.
	*
	* @param testrayBuildId the testray build ID
	* @param testrayCaseTypeId the testray case type ID
	* @param status the status
	* @return the matching testray build metric
	* @throws NoSuchTestrayBuildMetricException if a matching testray build metric could not be found
	*/
	public static TestrayBuildMetric findByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status)
		throws com.liferay.osb.testray.exception.NoSuchTestrayBuildMetricException {
		return getPersistence()
				   .findByTBI_TCTI_S(testrayBuildId, testrayCaseTypeId, status);
	}

	/**
	* Returns the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param testrayBuildId the testray build ID
	* @param testrayCaseTypeId the testray case type ID
	* @param status the status
	* @return the matching testray build metric, or <code>null</code> if a matching testray build metric could not be found
	*/
	public static TestrayBuildMetric fetchByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status) {
		return getPersistence()
				   .fetchByTBI_TCTI_S(testrayBuildId, testrayCaseTypeId, status);
	}

	/**
	* Returns the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param testrayBuildId the testray build ID
	* @param testrayCaseTypeId the testray case type ID
	* @param status the status
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching testray build metric, or <code>null</code> if a matching testray build metric could not be found
	*/
	public static TestrayBuildMetric fetchByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByTBI_TCTI_S(testrayBuildId, testrayCaseTypeId,
			status, retrieveFromCache);
	}

	/**
	* Removes the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; from the database.
	*
	* @param testrayBuildId the testray build ID
	* @param testrayCaseTypeId the testray case type ID
	* @param status the status
	* @return the testray build metric that was removed
	*/
	public static TestrayBuildMetric removeByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status)
		throws com.liferay.osb.testray.exception.NoSuchTestrayBuildMetricException {
		return getPersistence()
				   .removeByTBI_TCTI_S(testrayBuildId, testrayCaseTypeId, status);
	}

	/**
	* Returns the number of testray build metrics where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63;.
	*
	* @param testrayBuildId the testray build ID
	* @param testrayCaseTypeId the testray case type ID
	* @param status the status
	* @return the number of matching testray build metrics
	*/
	public static int countByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status) {
		return getPersistence()
				   .countByTBI_TCTI_S(testrayBuildId, testrayCaseTypeId, status);
	}

	/**
	* Caches the testray build metric in the entity cache if it is enabled.
	*
	* @param testrayBuildMetric the testray build metric
	*/
	public static void cacheResult(TestrayBuildMetric testrayBuildMetric) {
		getPersistence().cacheResult(testrayBuildMetric);
	}

	/**
	* Caches the testray build metrics in the entity cache if it is enabled.
	*
	* @param testrayBuildMetrics the testray build metrics
	*/
	public static void cacheResult(List<TestrayBuildMetric> testrayBuildMetrics) {
		getPersistence().cacheResult(testrayBuildMetrics);
	}

	/**
	* Creates a new testray build metric with the primary key. Does not add the testray build metric to the database.
	*
	* @param testrayBuildMetricId the primary key for the new testray build metric
	* @return the new testray build metric
	*/
	public static TestrayBuildMetric create(long testrayBuildMetricId) {
		return getPersistence().create(testrayBuildMetricId);
	}

	/**
	* Removes the testray build metric with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuildMetricId the primary key of the testray build metric
	* @return the testray build metric that was removed
	* @throws NoSuchTestrayBuildMetricException if a testray build metric with the primary key could not be found
	*/
	public static TestrayBuildMetric remove(long testrayBuildMetricId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayBuildMetricException {
		return getPersistence().remove(testrayBuildMetricId);
	}

	public static TestrayBuildMetric updateImpl(
		TestrayBuildMetric testrayBuildMetric) {
		return getPersistence().updateImpl(testrayBuildMetric);
	}

	/**
	* Returns the testray build metric with the primary key or throws a {@link NoSuchTestrayBuildMetricException} if it could not be found.
	*
	* @param testrayBuildMetricId the primary key of the testray build metric
	* @return the testray build metric
	* @throws NoSuchTestrayBuildMetricException if a testray build metric with the primary key could not be found
	*/
	public static TestrayBuildMetric findByPrimaryKey(long testrayBuildMetricId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayBuildMetricException {
		return getPersistence().findByPrimaryKey(testrayBuildMetricId);
	}

	/**
	* Returns the testray build metric with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayBuildMetricId the primary key of the testray build metric
	* @return the testray build metric, or <code>null</code> if a testray build metric with the primary key could not be found
	*/
	public static TestrayBuildMetric fetchByPrimaryKey(
		long testrayBuildMetricId) {
		return getPersistence().fetchByPrimaryKey(testrayBuildMetricId);
	}

	public static java.util.Map<java.io.Serializable, TestrayBuildMetric> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the testray build metrics.
	*
	* @return the testray build metrics
	*/
	public static List<TestrayBuildMetric> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the testray build metrics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayBuildMetricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray build metrics
	* @param end the upper bound of the range of testray build metrics (not inclusive)
	* @return the range of testray build metrics
	*/
	public static List<TestrayBuildMetric> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the testray build metrics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayBuildMetricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray build metrics
	* @param end the upper bound of the range of testray build metrics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray build metrics
	*/
	public static List<TestrayBuildMetric> findAll(int start, int end,
		OrderByComparator<TestrayBuildMetric> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the testray build metrics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayBuildMetricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray build metrics
	* @param end the upper bound of the range of testray build metrics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray build metrics
	*/
	public static List<TestrayBuildMetric> findAll(int start, int end,
		OrderByComparator<TestrayBuildMetric> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the testray build metrics from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of testray build metrics.
	*
	* @return the number of testray build metrics
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayBuildMetricPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayBuildMetricPersistence, TestrayBuildMetricPersistence> _serviceTracker =
		ServiceTrackerFactory.open(TestrayBuildMetricPersistence.class);
}
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

import com.liferay.osb.testray.exception.NoSuchTestrayBuildMetricException;
import com.liferay.osb.testray.model.TestrayBuildMetric;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the testray build metric service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayBuildMetricPersistenceImpl
 * @see TestrayBuildMetricUtil
 * @generated
 */
@ProviderType
public interface TestrayBuildMetricPersistence extends BasePersistence<TestrayBuildMetric> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayBuildMetricUtil} to access the testray build metric persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; or throws a {@link NoSuchTestrayBuildMetricException} if it could not be found.
	*
	* @param testrayBuildId the testray build ID
	* @param testrayCaseTypeId the testray case type ID
	* @param status the status
	* @return the matching testray build metric
	* @throws NoSuchTestrayBuildMetricException if a matching testray build metric could not be found
	*/
	public TestrayBuildMetric findByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status)
		throws NoSuchTestrayBuildMetricException;

	/**
	* Returns the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param testrayBuildId the testray build ID
	* @param testrayCaseTypeId the testray case type ID
	* @param status the status
	* @return the matching testray build metric, or <code>null</code> if a matching testray build metric could not be found
	*/
	public TestrayBuildMetric fetchByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status);

	/**
	* Returns the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param testrayBuildId the testray build ID
	* @param testrayCaseTypeId the testray case type ID
	* @param status the status
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching testray build metric, or <code>null</code> if a matching testray build metric could not be found
	*/
	public TestrayBuildMetric fetchByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status, boolean retrieveFromCache);

	/**
	* Removes the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; from the database.
	*
	* @param testrayBuildId the testray build ID
	* @param testrayCaseTypeId the testray case type ID
	* @param status the status
	* @return the testray build metric that was removed
	*/
	public TestrayBuildMetric removeByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status)
		throws NoSuchTestrayBuildMetricException;

	/**
	* Returns the number of testray build metrics where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63;.
	*
	* @param testrayBuildId the testray build ID
	* @param testrayCaseTypeId the testray case type ID
	* @param status the status
	* @return the number of matching testray build metrics
	*/
	public int countByTBI_TCTI_S(long testrayBuildId, long testrayCaseTypeId,
		int status);

	/**
	* Caches the testray build metric in the entity cache if it is enabled.
	*
	* @param testrayBuildMetric the testray build metric
	*/
	public void cacheResult(TestrayBuildMetric testrayBuildMetric);

	/**
	* Caches the testray build metrics in the entity cache if it is enabled.
	*
	* @param testrayBuildMetrics the testray build metrics
	*/
	public void cacheResult(
		java.util.List<TestrayBuildMetric> testrayBuildMetrics);

	/**
	* Creates a new testray build metric with the primary key. Does not add the testray build metric to the database.
	*
	* @param testrayBuildMetricId the primary key for the new testray build metric
	* @return the new testray build metric
	*/
	public TestrayBuildMetric create(long testrayBuildMetricId);

	/**
	* Removes the testray build metric with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuildMetricId the primary key of the testray build metric
	* @return the testray build metric that was removed
	* @throws NoSuchTestrayBuildMetricException if a testray build metric with the primary key could not be found
	*/
	public TestrayBuildMetric remove(long testrayBuildMetricId)
		throws NoSuchTestrayBuildMetricException;

	public TestrayBuildMetric updateImpl(TestrayBuildMetric testrayBuildMetric);

	/**
	* Returns the testray build metric with the primary key or throws a {@link NoSuchTestrayBuildMetricException} if it could not be found.
	*
	* @param testrayBuildMetricId the primary key of the testray build metric
	* @return the testray build metric
	* @throws NoSuchTestrayBuildMetricException if a testray build metric with the primary key could not be found
	*/
	public TestrayBuildMetric findByPrimaryKey(long testrayBuildMetricId)
		throws NoSuchTestrayBuildMetricException;

	/**
	* Returns the testray build metric with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayBuildMetricId the primary key of the testray build metric
	* @return the testray build metric, or <code>null</code> if a testray build metric with the primary key could not be found
	*/
	public TestrayBuildMetric fetchByPrimaryKey(long testrayBuildMetricId);

	@Override
	public java.util.Map<java.io.Serializable, TestrayBuildMetric> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the testray build metrics.
	*
	* @return the testray build metrics
	*/
	public java.util.List<TestrayBuildMetric> findAll();

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
	public java.util.List<TestrayBuildMetric> findAll(int start, int end);

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
	public java.util.List<TestrayBuildMetric> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayBuildMetric> orderByComparator);

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
	public java.util.List<TestrayBuildMetric> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayBuildMetric> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the testray build metrics from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of testray build metrics.
	*
	* @return the number of testray build metrics
	*/
	public int countAll();
}
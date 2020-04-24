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

package com.liferay.osb.testray.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TestrayBuildMetricLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayBuildMetricLocalService
 * @generated
 */
@ProviderType
public class TestrayBuildMetricLocalServiceWrapper
	implements TestrayBuildMetricLocalService,
		ServiceWrapper<TestrayBuildMetricLocalService> {
	public TestrayBuildMetricLocalServiceWrapper(
		TestrayBuildMetricLocalService testrayBuildMetricLocalService) {
		_testrayBuildMetricLocalService = testrayBuildMetricLocalService;
	}

	/**
	* Adds the testray build metric to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuildMetric the testray build metric
	* @return the testray build metric that was added
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuildMetric addTestrayBuildMetric(
		com.liferay.osb.testray.model.TestrayBuildMetric testrayBuildMetric) {
		return _testrayBuildMetricLocalService.addTestrayBuildMetric(testrayBuildMetric);
	}

	/**
	* Creates a new testray build metric with the primary key. Does not add the testray build metric to the database.
	*
	* @param testrayBuildMetricId the primary key for the new testray build metric
	* @return the new testray build metric
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuildMetric createTestrayBuildMetric(
		long testrayBuildMetricId) {
		return _testrayBuildMetricLocalService.createTestrayBuildMetric(testrayBuildMetricId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayBuildMetricLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the testray build metric with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuildMetricId the primary key of the testray build metric
	* @return the testray build metric that was removed
	* @throws PortalException if a testray build metric with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuildMetric deleteTestrayBuildMetric(
		long testrayBuildMetricId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayBuildMetricLocalService.deleteTestrayBuildMetric(testrayBuildMetricId);
	}

	/**
	* Deletes the testray build metric from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuildMetric the testray build metric
	* @return the testray build metric that was removed
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuildMetric deleteTestrayBuildMetric(
		com.liferay.osb.testray.model.TestrayBuildMetric testrayBuildMetric) {
		return _testrayBuildMetricLocalService.deleteTestrayBuildMetric(testrayBuildMetric);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayBuildMetricLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _testrayBuildMetricLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildMetricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _testrayBuildMetricLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildMetricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _testrayBuildMetricLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _testrayBuildMetricLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _testrayBuildMetricLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayBuildMetric fetchTestrayBuildMetric(
		long testrayBuildMetricId) {
		return _testrayBuildMetricLocalService.fetchTestrayBuildMetric(testrayBuildMetricId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _testrayBuildMetricLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _testrayBuildMetricLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _testrayBuildMetricLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayBuildMetricLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the testray build metric with the primary key.
	*
	* @param testrayBuildMetricId the primary key of the testray build metric
	* @return the testray build metric
	* @throws PortalException if a testray build metric with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuildMetric getTestrayBuildMetric(
		long testrayBuildMetricId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayBuildMetricLocalService.getTestrayBuildMetric(testrayBuildMetricId);
	}

	/**
	* Returns a range of all the testray build metrics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildMetricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray build metrics
	* @param end the upper bound of the range of testray build metrics (not inclusive)
	* @return the range of testray build metrics
	*/
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayBuildMetric> getTestrayBuildMetrics(
		int start, int end) {
		return _testrayBuildMetricLocalService.getTestrayBuildMetrics(start, end);
	}

	/**
	* Returns the number of testray build metrics.
	*
	* @return the number of testray build metrics
	*/
	@Override
	public int getTestrayBuildMetricsCount() {
		return _testrayBuildMetricLocalService.getTestrayBuildMetricsCount();
	}

	/**
	* Updates the testray build metric in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayBuildMetric the testray build metric
	* @return the testray build metric that was updated
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuildMetric updateTestrayBuildMetric(
		com.liferay.osb.testray.model.TestrayBuildMetric testrayBuildMetric) {
		return _testrayBuildMetricLocalService.updateTestrayBuildMetric(testrayBuildMetric);
	}

	@Override
	public TestrayBuildMetricLocalService getWrappedService() {
		return _testrayBuildMetricLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayBuildMetricLocalService testrayBuildMetricLocalService) {
		_testrayBuildMetricLocalService = testrayBuildMetricLocalService;
	}

	private TestrayBuildMetricLocalService _testrayBuildMetricLocalService;
}
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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TestrayRun. This utility wraps
 * {@link com.liferay.osb.testray.service.impl.TestrayRunLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayRunLocalService
 * @see com.liferay.osb.testray.service.base.TestrayRunLocalServiceBaseImpl
 * @see com.liferay.osb.testray.service.impl.TestrayRunLocalServiceImpl
 * @generated
 */
@ProviderType
public class TestrayRunLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.testray.service.impl.TestrayRunLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the testray run to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayRun the testray run
	* @return the testray run that was added
	*/
	public static com.liferay.osb.testray.model.TestrayRun addTestrayRun(
		com.liferay.osb.testray.model.TestrayRun testrayRun) {
		return getService().addTestrayRun(testrayRun);
	}

	/**
	* Creates a new testray run with the primary key. Does not add the testray run to the database.
	*
	* @param testrayRunId the primary key for the new testray run
	* @return the new testray run
	*/
	public static com.liferay.osb.testray.model.TestrayRun createTestrayRun(
		long testrayRunId) {
		return getService().createTestrayRun(testrayRunId);
	}

	/**
	* Deletes the testray run from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayRun the testray run
	* @return the testray run that was removed
	*/
	public static com.liferay.osb.testray.model.TestrayRun deleteTestrayRun(
		com.liferay.osb.testray.model.TestrayRun testrayRun) {
		return getService().deleteTestrayRun(testrayRun);
	}

	/**
	* Deletes the testray run with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayRunId the primary key of the testray run
	* @return the testray run that was removed
	* @throws PortalException if a testray run with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestrayRun deleteTestrayRun(
		long testrayRunId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTestrayRun(testrayRunId);
	}

	public static com.liferay.osb.testray.model.TestrayRun fetchTestrayRun(
		long testrayRunId) {
		return getService().fetchTestrayRun(testrayRunId);
	}

	/**
	* Returns the testray run with the primary key.
	*
	* @param testrayRunId the primary key of the testray run
	* @return the testray run
	* @throws PortalException if a testray run with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestrayRun getTestrayRun(
		long testrayRunId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTestrayRun(testrayRunId);
	}

	/**
	* Updates the testray run in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayRun the testray run
	* @return the testray run that was updated
	*/
	public static com.liferay.osb.testray.model.TestrayRun updateTestrayRun(
		com.liferay.osb.testray.model.TestrayRun testrayRun) {
		return getService().updateTestrayRun(testrayRun);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of testray runs.
	*
	* @return the number of testray runs
	*/
	public static int getTestrayRunsCount() {
		return getService().getTestrayRunsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the testray runs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray runs
	* @param end the upper bound of the range of testray runs (not inclusive)
	* @return the range of testray runs
	*/
	public static java.util.List<com.liferay.osb.testray.model.TestrayRun> getTestrayRuns(
		int start, int end) {
		return getService().getTestrayRuns(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static TestrayRunLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayRunLocalService, TestrayRunLocalService> _serviceTracker =
		ServiceTrackerFactory.open(TestrayRunLocalService.class);
}
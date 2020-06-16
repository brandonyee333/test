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
 * Provides the local service utility for TestrayBuild. This utility wraps
 * {@link com.liferay.osb.testray.service.impl.TestrayBuildLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayBuildLocalService
 * @see com.liferay.osb.testray.service.base.TestrayBuildLocalServiceBaseImpl
 * @see com.liferay.osb.testray.service.impl.TestrayBuildLocalServiceImpl
 * @generated
 */
@ProviderType
public class TestrayBuildLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.testray.service.impl.TestrayBuildLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasTestrayCaseTestrayBuild(long testrayCaseId,
		long testrayBuildId) {
		return getService()
				   .hasTestrayCaseTestrayBuild(testrayCaseId, testrayBuildId);
	}

	public static boolean hasTestrayCaseTestrayBuilds(long testrayCaseId) {
		return getService().hasTestrayCaseTestrayBuilds(testrayCaseId);
	}

	/**
	* Adds the testray build to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuild the testray build
	* @return the testray build that was added
	*/
	public static com.liferay.osb.testray.model.TestrayBuild addTestrayBuild(
		com.liferay.osb.testray.model.TestrayBuild testrayBuild) {
		return getService().addTestrayBuild(testrayBuild);
	}

	/**
	* Creates a new testray build with the primary key. Does not add the testray build to the database.
	*
	* @param testrayBuildId the primary key for the new testray build
	* @return the new testray build
	*/
	public static com.liferay.osb.testray.model.TestrayBuild createTestrayBuild(
		long testrayBuildId) {
		return getService().createTestrayBuild(testrayBuildId);
	}

	/**
	* Deletes the testray build from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuild the testray build
	* @return the testray build that was removed
	*/
	public static com.liferay.osb.testray.model.TestrayBuild deleteTestrayBuild(
		com.liferay.osb.testray.model.TestrayBuild testrayBuild) {
		return getService().deleteTestrayBuild(testrayBuild);
	}

	/**
	* Deletes the testray build with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuildId the primary key of the testray build
	* @return the testray build that was removed
	* @throws PortalException if a testray build with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestrayBuild deleteTestrayBuild(
		long testrayBuildId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTestrayBuild(testrayBuildId);
	}

	public static com.liferay.osb.testray.model.TestrayBuild fetchTestrayBuild(
		long testrayBuildId) {
		return getService().fetchTestrayBuild(testrayBuildId);
	}

	/**
	* Returns the testray build with the primary key.
	*
	* @param testrayBuildId the primary key of the testray build
	* @return the testray build
	* @throws PortalException if a testray build with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestrayBuild getTestrayBuild(
		long testrayBuildId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTestrayBuild(testrayBuildId);
	}

	/**
	* Updates the testray build in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayBuild the testray build
	* @return the testray build that was updated
	*/
	public static com.liferay.osb.testray.model.TestrayBuild updateTestrayBuild(
		com.liferay.osb.testray.model.TestrayBuild testrayBuild) {
		return getService().updateTestrayBuild(testrayBuild);
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
	* Returns the number of testray builds.
	*
	* @return the number of testray builds
	*/
	public static int getTestrayBuildsCount() {
		return getService().getTestrayBuildsCount();
	}

	public static int getTestrayCaseTestrayBuildsCount(long testrayCaseId) {
		return getService().getTestrayCaseTestrayBuildsCount(testrayCaseId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the testray builds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray builds
	* @param end the upper bound of the range of testray builds (not inclusive)
	* @return the range of testray builds
	*/
	public static java.util.List<com.liferay.osb.testray.model.TestrayBuild> getTestrayBuilds(
		int start, int end) {
		return getService().getTestrayBuilds(start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayBuild> getTestrayCaseTestrayBuilds(
		long testrayCaseId) {
		return getService().getTestrayCaseTestrayBuilds(testrayCaseId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayBuild> getTestrayCaseTestrayBuilds(
		long testrayCaseId, int start, int end) {
		return getService()
				   .getTestrayCaseTestrayBuilds(testrayCaseId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayBuild> getTestrayCaseTestrayBuilds(
		long testrayCaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.testray.model.TestrayBuild> orderByComparator) {
		return getService()
				   .getTestrayCaseTestrayBuilds(testrayCaseId, start, end,
			orderByComparator);
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

	/**
	* Returns the testrayCaseIds of the testray cases associated with the testray build.
	*
	* @param testrayBuildId the testrayBuildId of the testray build
	* @return long[] the testrayCaseIds of testray cases associated with the testray build
	*/
	public static long[] getTestrayCasePrimaryKeys(long testrayBuildId) {
		return getService().getTestrayCasePrimaryKeys(testrayBuildId);
	}

	public static void addTestrayCaseTestrayBuild(long testrayCaseId,
		com.liferay.osb.testray.model.TestrayBuild testrayBuild) {
		getService().addTestrayCaseTestrayBuild(testrayCaseId, testrayBuild);
	}

	public static void addTestrayCaseTestrayBuild(long testrayCaseId,
		long testrayBuildId) {
		getService().addTestrayCaseTestrayBuild(testrayCaseId, testrayBuildId);
	}

	public static void addTestrayCaseTestrayBuilds(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayBuild> testrayBuilds) {
		getService().addTestrayCaseTestrayBuilds(testrayCaseId, testrayBuilds);
	}

	public static void addTestrayCaseTestrayBuilds(long testrayCaseId,
		long[] testrayBuildIds) {
		getService().addTestrayCaseTestrayBuilds(testrayCaseId, testrayBuildIds);
	}

	public static void clearTestrayCaseTestrayBuilds(long testrayCaseId) {
		getService().clearTestrayCaseTestrayBuilds(testrayCaseId);
	}

	public static void deleteTestrayCaseTestrayBuild(long testrayCaseId,
		com.liferay.osb.testray.model.TestrayBuild testrayBuild) {
		getService().deleteTestrayCaseTestrayBuild(testrayCaseId, testrayBuild);
	}

	public static void deleteTestrayCaseTestrayBuild(long testrayCaseId,
		long testrayBuildId) {
		getService().deleteTestrayCaseTestrayBuild(testrayCaseId, testrayBuildId);
	}

	public static void deleteTestrayCaseTestrayBuilds(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayBuild> testrayBuilds) {
		getService().deleteTestrayCaseTestrayBuilds(testrayCaseId, testrayBuilds);
	}

	public static void deleteTestrayCaseTestrayBuilds(long testrayCaseId,
		long[] testrayBuildIds) {
		getService()
			.deleteTestrayCaseTestrayBuilds(testrayCaseId, testrayBuildIds);
	}

	public static void setTestrayCaseTestrayBuilds(long testrayCaseId,
		long[] testrayBuildIds) {
		getService().setTestrayCaseTestrayBuilds(testrayCaseId, testrayBuildIds);
	}

	public static TestrayBuildLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayBuildLocalService, TestrayBuildLocalService> _serviceTracker =
		ServiceTrackerFactory.open(TestrayBuildLocalService.class);
}
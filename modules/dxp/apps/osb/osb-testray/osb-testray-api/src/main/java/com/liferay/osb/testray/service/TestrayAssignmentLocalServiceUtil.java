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
 * Provides the local service utility for TestrayAssignment. This utility wraps
 * {@link com.liferay.osb.testray.service.impl.TestrayAssignmentLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayAssignmentLocalService
 * @see com.liferay.osb.testray.service.base.TestrayAssignmentLocalServiceBaseImpl
 * @see com.liferay.osb.testray.service.impl.TestrayAssignmentLocalServiceImpl
 * @generated
 */
@ProviderType
public class TestrayAssignmentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.testray.service.impl.TestrayAssignmentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the testray assignment to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayAssignment the testray assignment
	* @return the testray assignment that was added
	*/
	public static com.liferay.osb.testray.model.TestrayAssignment addTestrayAssignment(
		com.liferay.osb.testray.model.TestrayAssignment testrayAssignment) {
		return getService().addTestrayAssignment(testrayAssignment);
	}

	/**
	* Creates a new testray assignment with the primary key. Does not add the testray assignment to the database.
	*
	* @param testrayAssignmentId the primary key for the new testray assignment
	* @return the new testray assignment
	*/
	public static com.liferay.osb.testray.model.TestrayAssignment createTestrayAssignment(
		long testrayAssignmentId) {
		return getService().createTestrayAssignment(testrayAssignmentId);
	}

	/**
	* Deletes the testray assignment from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayAssignment the testray assignment
	* @return the testray assignment that was removed
	*/
	public static com.liferay.osb.testray.model.TestrayAssignment deleteTestrayAssignment(
		com.liferay.osb.testray.model.TestrayAssignment testrayAssignment) {
		return getService().deleteTestrayAssignment(testrayAssignment);
	}

	/**
	* Deletes the testray assignment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayAssignmentId the primary key of the testray assignment
	* @return the testray assignment that was removed
	* @throws PortalException if a testray assignment with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestrayAssignment deleteTestrayAssignment(
		long testrayAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTestrayAssignment(testrayAssignmentId);
	}

	public static com.liferay.osb.testray.model.TestrayAssignment fetchTestrayAssignment(
		long testrayAssignmentId) {
		return getService().fetchTestrayAssignment(testrayAssignmentId);
	}

	/**
	* Returns the testray assignment with the primary key.
	*
	* @param testrayAssignmentId the primary key of the testray assignment
	* @return the testray assignment
	* @throws PortalException if a testray assignment with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestrayAssignment getTestrayAssignment(
		long testrayAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTestrayAssignment(testrayAssignmentId);
	}

	/**
	* Updates the testray assignment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayAssignment the testray assignment
	* @return the testray assignment that was updated
	*/
	public static com.liferay.osb.testray.model.TestrayAssignment updateTestrayAssignment(
		com.liferay.osb.testray.model.TestrayAssignment testrayAssignment) {
		return getService().updateTestrayAssignment(testrayAssignment);
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
	* Returns the number of testray assignments.
	*
	* @return the number of testray assignments
	*/
	public static int getTestrayAssignmentsCount() {
		return getService().getTestrayAssignmentsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the testray assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray assignments
	* @param end the upper bound of the range of testray assignments (not inclusive)
	* @return the range of testray assignments
	*/
	public static java.util.List<com.liferay.osb.testray.model.TestrayAssignment> getTestrayAssignments(
		int start, int end) {
		return getService().getTestrayAssignments(start, end);
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

	public static TestrayAssignmentLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayAssignmentLocalService, TestrayAssignmentLocalService> _serviceTracker =
		ServiceTrackerFactory.open(TestrayAssignmentLocalService.class);
}
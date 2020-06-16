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
 * Provides the local service utility for TestrayComponent. This utility wraps
 * {@link com.liferay.osb.testray.service.impl.TestrayComponentLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayComponentLocalService
 * @see com.liferay.osb.testray.service.base.TestrayComponentLocalServiceBaseImpl
 * @see com.liferay.osb.testray.service.impl.TestrayComponentLocalServiceImpl
 * @generated
 */
@ProviderType
public class TestrayComponentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.testray.service.impl.TestrayComponentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasTestrayCaseTestrayComponent(long testrayCaseId,
		long testrayComponentId) {
		return getService()
				   .hasTestrayCaseTestrayComponent(testrayCaseId,
			testrayComponentId);
	}

	public static boolean hasTestrayCaseTestrayComponents(long testrayCaseId) {
		return getService().hasTestrayCaseTestrayComponents(testrayCaseId);
	}

	/**
	* Adds the testray component to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayComponent the testray component
	* @return the testray component that was added
	*/
	public static com.liferay.osb.testray.model.TestrayComponent addTestrayComponent(
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {
		return getService().addTestrayComponent(testrayComponent);
	}

	/**
	* Creates a new testray component with the primary key. Does not add the testray component to the database.
	*
	* @param testrayComponentId the primary key for the new testray component
	* @return the new testray component
	*/
	public static com.liferay.osb.testray.model.TestrayComponent createTestrayComponent(
		long testrayComponentId) {
		return getService().createTestrayComponent(testrayComponentId);
	}

	/**
	* Deletes the testray component from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayComponent the testray component
	* @return the testray component that was removed
	*/
	public static com.liferay.osb.testray.model.TestrayComponent deleteTestrayComponent(
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {
		return getService().deleteTestrayComponent(testrayComponent);
	}

	/**
	* Deletes the testray component with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayComponentId the primary key of the testray component
	* @return the testray component that was removed
	* @throws PortalException if a testray component with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestrayComponent deleteTestrayComponent(
		long testrayComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTestrayComponent(testrayComponentId);
	}

	public static com.liferay.osb.testray.model.TestrayComponent fetchTestrayComponent(
		long testrayComponentId) {
		return getService().fetchTestrayComponent(testrayComponentId);
	}

	/**
	* Returns the testray component with the primary key.
	*
	* @param testrayComponentId the primary key of the testray component
	* @return the testray component
	* @throws PortalException if a testray component with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestrayComponent getTestrayComponent(
		long testrayComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTestrayComponent(testrayComponentId);
	}

	/**
	* Updates the testray component in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayComponent the testray component
	* @return the testray component that was updated
	*/
	public static com.liferay.osb.testray.model.TestrayComponent updateTestrayComponent(
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {
		return getService().updateTestrayComponent(testrayComponent);
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

	public static int getTestrayCaseTestrayComponentsCount(long testrayCaseId) {
		return getService().getTestrayCaseTestrayComponentsCount(testrayCaseId);
	}

	/**
	* Returns the number of testray components.
	*
	* @return the number of testray components
	*/
	public static int getTestrayComponentsCount() {
		return getService().getTestrayComponentsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.osb.testray.model.TestrayComponent> getTestrayCaseTestrayComponents(
		long testrayCaseId) {
		return getService().getTestrayCaseTestrayComponents(testrayCaseId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayComponent> getTestrayCaseTestrayComponents(
		long testrayCaseId, int start, int end) {
		return getService()
				   .getTestrayCaseTestrayComponents(testrayCaseId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayComponent> getTestrayCaseTestrayComponents(
		long testrayCaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.testray.model.TestrayComponent> orderByComparator) {
		return getService()
				   .getTestrayCaseTestrayComponents(testrayCaseId, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the testray components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray components
	* @param end the upper bound of the range of testray components (not inclusive)
	* @return the range of testray components
	*/
	public static java.util.List<com.liferay.osb.testray.model.TestrayComponent> getTestrayComponents(
		int start, int end) {
		return getService().getTestrayComponents(start, end);
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
	* Returns the testrayCaseIds of the testray cases associated with the testray component.
	*
	* @param testrayComponentId the testrayComponentId of the testray component
	* @return long[] the testrayCaseIds of testray cases associated with the testray component
	*/
	public static long[] getTestrayCasePrimaryKeys(long testrayComponentId) {
		return getService().getTestrayCasePrimaryKeys(testrayComponentId);
	}

	public static void addTestrayCaseTestrayComponent(long testrayCaseId,
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {
		getService()
			.addTestrayCaseTestrayComponent(testrayCaseId, testrayComponent);
	}

	public static void addTestrayCaseTestrayComponent(long testrayCaseId,
		long testrayComponentId) {
		getService()
			.addTestrayCaseTestrayComponent(testrayCaseId, testrayComponentId);
	}

	public static void addTestrayCaseTestrayComponents(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayComponent> testrayComponents) {
		getService()
			.addTestrayCaseTestrayComponents(testrayCaseId, testrayComponents);
	}

	public static void addTestrayCaseTestrayComponents(long testrayCaseId,
		long[] testrayComponentIds) {
		getService()
			.addTestrayCaseTestrayComponents(testrayCaseId, testrayComponentIds);
	}

	public static void clearTestrayCaseTestrayComponents(long testrayCaseId) {
		getService().clearTestrayCaseTestrayComponents(testrayCaseId);
	}

	public static void deleteTestrayCaseTestrayComponent(long testrayCaseId,
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {
		getService()
			.deleteTestrayCaseTestrayComponent(testrayCaseId, testrayComponent);
	}

	public static void deleteTestrayCaseTestrayComponent(long testrayCaseId,
		long testrayComponentId) {
		getService()
			.deleteTestrayCaseTestrayComponent(testrayCaseId, testrayComponentId);
	}

	public static void deleteTestrayCaseTestrayComponents(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayComponent> testrayComponents) {
		getService()
			.deleteTestrayCaseTestrayComponents(testrayCaseId, testrayComponents);
	}

	public static void deleteTestrayCaseTestrayComponents(long testrayCaseId,
		long[] testrayComponentIds) {
		getService()
			.deleteTestrayCaseTestrayComponents(testrayCaseId,
			testrayComponentIds);
	}

	public static void setTestrayCaseTestrayComponents(long testrayCaseId,
		long[] testrayComponentIds) {
		getService()
			.setTestrayCaseTestrayComponents(testrayCaseId, testrayComponentIds);
	}

	public static TestrayComponentLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayComponentLocalService, TestrayComponentLocalService> _serviceTracker =
		ServiceTrackerFactory.open(TestrayComponentLocalService.class);
}
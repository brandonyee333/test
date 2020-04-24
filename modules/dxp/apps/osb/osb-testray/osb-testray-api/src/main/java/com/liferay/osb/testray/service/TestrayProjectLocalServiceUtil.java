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

package com.liferay.osb.testray.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TestrayProject. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayProjectLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayProjectLocalService
 * @generated
 */
@ProviderType
public class TestrayProjectLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayProjectLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the testray project to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayProject the testray project
	 * @return the testray project that was added
	 */
	public static com.liferay.osb.testray.model.TestrayProject
		addTestrayProject(
			com.liferay.osb.testray.model.TestrayProject testrayProject) {

		return getService().addTestrayProject(testrayProject);
	}

	/**
	 * Creates a new testray project with the primary key. Does not add the testray project to the database.
	 *
	 * @param testrayProjectId the primary key for the new testray project
	 * @return the new testray project
	 */
	public static com.liferay.osb.testray.model.TestrayProject
		createTestrayProject(long testrayProjectId) {

		return getService().createTestrayProject(testrayProjectId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the testray project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayProjectId the primary key of the testray project
	 * @return the testray project that was removed
	 * @throws PortalException if a testray project with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayProject
			deleteTestrayProject(long testrayProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTestrayProject(testrayProjectId);
	}

	/**
	 * Deletes the testray project from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayProject the testray project
	 * @return the testray project that was removed
	 */
	public static com.liferay.osb.testray.model.TestrayProject
		deleteTestrayProject(
			com.liferay.osb.testray.model.TestrayProject testrayProject) {

		return getService().deleteTestrayProject(testrayProject);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayProjectModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayProjectModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.osb.testray.model.TestrayProject
		fetchTestrayProject(long testrayProjectId) {

		return getService().fetchTestrayProject(testrayProjectId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testray project with the primary key.
	 *
	 * @param testrayProjectId the primary key of the testray project
	 * @return the testray project
	 * @throws PortalException if a testray project with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayProject
			getTestrayProject(long testrayProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTestrayProject(testrayProjectId);
	}

	/**
	 * Returns a range of all the testray projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayProjectModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray projects
	 * @param end the upper bound of the range of testray projects (not inclusive)
	 * @return the range of testray projects
	 */
	public static java.util.List<com.liferay.osb.testray.model.TestrayProject>
		getTestrayProjects(int start, int end) {

		return getService().getTestrayProjects(start, end);
	}

	/**
	 * Returns the number of testray projects.
	 *
	 * @return the number of testray projects
	 */
	public static int getTestrayProjectsCount() {
		return getService().getTestrayProjectsCount();
	}

	/**
	 * Updates the testray project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayProject the testray project
	 * @return the testray project that was updated
	 */
	public static com.liferay.osb.testray.model.TestrayProject
		updateTestrayProject(
			com.liferay.osb.testray.model.TestrayProject testrayProject) {

		return getService().updateTestrayProject(testrayProject);
	}

	public static TestrayProjectLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayProjectLocalService, TestrayProjectLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayProjectLocalService.class);

		ServiceTracker<TestrayProjectLocalService, TestrayProjectLocalService>
			serviceTracker =
				new ServiceTracker
					<TestrayProjectLocalService, TestrayProjectLocalService>(
						bundle.getBundleContext(),
						TestrayProjectLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
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
 * Provides the local service utility for TestrayTask. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayTaskLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayTaskLocalService
 * @generated
 */
@ProviderType
public class TestrayTaskLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayTaskLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addTestrayCaseTypeTestrayTask(
		long testrayCaseTypeId, long testrayTaskId) {

		getService().addTestrayCaseTypeTestrayTask(
			testrayCaseTypeId, testrayTaskId);
	}

	public static void addTestrayCaseTypeTestrayTask(
		long testrayCaseTypeId,
		com.liferay.osb.testray.model.TestrayTask testrayTask) {

		getService().addTestrayCaseTypeTestrayTask(
			testrayCaseTypeId, testrayTask);
	}

	public static void addTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId,
		java.util.List<com.liferay.osb.testray.model.TestrayTask>
			testrayTasks) {

		getService().addTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, testrayTasks);
	}

	public static void addTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId, long[] testrayTaskIds) {

		getService().addTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, testrayTaskIds);
	}

	/**
	 * Adds the testray task to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTask the testray task
	 * @return the testray task that was added
	 */
	public static com.liferay.osb.testray.model.TestrayTask addTestrayTask(
		com.liferay.osb.testray.model.TestrayTask testrayTask) {

		return getService().addTestrayTask(testrayTask);
	}

	public static void clearTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId) {

		getService().clearTestrayCaseTypeTestrayTasks(testrayCaseTypeId);
	}

	/**
	 * Creates a new testray task with the primary key. Does not add the testray task to the database.
	 *
	 * @param testrayTaskId the primary key for the new testray task
	 * @return the new testray task
	 */
	public static com.liferay.osb.testray.model.TestrayTask createTestrayTask(
		long testrayTaskId) {

		return getService().createTestrayTask(testrayTaskId);
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

	public static void deleteTestrayCaseTypeTestrayTask(
		long testrayCaseTypeId, long testrayTaskId) {

		getService().deleteTestrayCaseTypeTestrayTask(
			testrayCaseTypeId, testrayTaskId);
	}

	public static void deleteTestrayCaseTypeTestrayTask(
		long testrayCaseTypeId,
		com.liferay.osb.testray.model.TestrayTask testrayTask) {

		getService().deleteTestrayCaseTypeTestrayTask(
			testrayCaseTypeId, testrayTask);
	}

	public static void deleteTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId,
		java.util.List<com.liferay.osb.testray.model.TestrayTask>
			testrayTasks) {

		getService().deleteTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, testrayTasks);
	}

	public static void deleteTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId, long[] testrayTaskIds) {

		getService().deleteTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, testrayTaskIds);
	}

	/**
	 * Deletes the testray task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTaskId the primary key of the testray task
	 * @return the testray task that was removed
	 * @throws PortalException if a testray task with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayTask deleteTestrayTask(
			long testrayTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTestrayTask(testrayTaskId);
	}

	/**
	 * Deletes the testray task from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTask the testray task
	 * @return the testray task that was removed
	 */
	public static com.liferay.osb.testray.model.TestrayTask deleteTestrayTask(
		com.liferay.osb.testray.model.TestrayTask testrayTask) {

		return getService().deleteTestrayTask(testrayTask);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayTaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayTaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.testray.model.TestrayTask fetchTestrayTask(
		long testrayTaskId) {

		return getService().fetchTestrayTask(testrayTaskId);
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
	 * Returns the testrayCaseTypeIds of the testray case types associated with the testray task.
	 *
	 * @param testrayTaskId the testrayTaskId of the testray task
	 * @return long[] the testrayCaseTypeIds of testray case types associated with the testray task
	 */
	public static long[] getTestrayCaseTypePrimaryKeys(long testrayTaskId) {
		return getService().getTestrayCaseTypePrimaryKeys(testrayTaskId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayCaseTypeTestrayTasks(long testrayCaseTypeId) {

		return getService().getTestrayCaseTypeTestrayTasks(testrayCaseTypeId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayCaseTypeTestrayTasks(
			long testrayCaseTypeId, int start, int end) {

		return getService().getTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayCaseTypeTestrayTasks(
			long testrayCaseTypeId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayTask> orderByComparator) {

		return getService().getTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, start, end, orderByComparator);
	}

	public static int getTestrayCaseTypeTestrayTasksCount(
		long testrayCaseTypeId) {

		return getService().getTestrayCaseTypeTestrayTasksCount(
			testrayCaseTypeId);
	}

	/**
	 * Returns the testray task with the primary key.
	 *
	 * @param testrayTaskId the primary key of the testray task
	 * @return the testray task
	 * @throws PortalException if a testray task with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayTask getTestrayTask(
			long testrayTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTestrayTask(testrayTaskId);
	}

	/**
	 * Returns a range of all the testray tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayTaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray tasks
	 * @param end the upper bound of the range of testray tasks (not inclusive)
	 * @return the range of testray tasks
	 */
	public static java.util.List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayTasks(int start, int end) {

		return getService().getTestrayTasks(start, end);
	}

	/**
	 * Returns the number of testray tasks.
	 *
	 * @return the number of testray tasks
	 */
	public static int getTestrayTasksCount() {
		return getService().getTestrayTasksCount();
	}

	public static boolean hasTestrayCaseTypeTestrayTask(
		long testrayCaseTypeId, long testrayTaskId) {

		return getService().hasTestrayCaseTypeTestrayTask(
			testrayCaseTypeId, testrayTaskId);
	}

	public static boolean hasTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId) {

		return getService().hasTestrayCaseTypeTestrayTasks(testrayCaseTypeId);
	}

	public static void setTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId, long[] testrayTaskIds) {

		getService().setTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, testrayTaskIds);
	}

	/**
	 * Updates the testray task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTask the testray task
	 * @return the testray task that was updated
	 */
	public static com.liferay.osb.testray.model.TestrayTask updateTestrayTask(
		com.liferay.osb.testray.model.TestrayTask testrayTask) {

		return getService().updateTestrayTask(testrayTask);
	}

	public static TestrayTaskLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayTaskLocalService, TestrayTaskLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TestrayTaskLocalService.class);

		ServiceTracker<TestrayTaskLocalService, TestrayTaskLocalService>
			serviceTracker =
				new ServiceTracker
					<TestrayTaskLocalService, TestrayTaskLocalService>(
						bundle.getBundleContext(),
						TestrayTaskLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
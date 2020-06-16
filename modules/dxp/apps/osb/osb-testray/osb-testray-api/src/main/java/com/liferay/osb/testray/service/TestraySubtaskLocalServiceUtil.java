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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TestraySubtask. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestraySubtaskLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestraySubtaskLocalService
 * @generated
 */
public class TestraySubtaskLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestraySubtaskLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addTestrayCaseResultTestraySubtask(
		long testrayCaseResultId, long testraySubtaskId) {

		getService().addTestrayCaseResultTestraySubtask(
			testrayCaseResultId, testraySubtaskId);
	}

	public static void addTestrayCaseResultTestraySubtask(
		long testrayCaseResultId,
		com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		getService().addTestrayCaseResultTestraySubtask(
			testrayCaseResultId, testraySubtask);
	}

	public static void addTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks) {

		getService().addTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, testraySubtasks);
	}

	public static void addTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, long[] testraySubtaskIds) {

		getService().addTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, testraySubtaskIds);
	}

	public static void addTestrayIssueTestraySubtask(
		long testrayIssueId, long testraySubtaskId) {

		getService().addTestrayIssueTestraySubtask(
			testrayIssueId, testraySubtaskId);
	}

	public static void addTestrayIssueTestraySubtask(
		long testrayIssueId,
		com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		getService().addTestrayIssueTestraySubtask(
			testrayIssueId, testraySubtask);
	}

	public static void addTestrayIssueTestraySubtasks(
		long testrayIssueId,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks) {

		getService().addTestrayIssueTestraySubtasks(
			testrayIssueId, testraySubtasks);
	}

	public static void addTestrayIssueTestraySubtasks(
		long testrayIssueId, long[] testraySubtaskIds) {

		getService().addTestrayIssueTestraySubtasks(
			testrayIssueId, testraySubtaskIds);
	}

	/**
	 * Adds the testray subtask to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtask the testray subtask
	 * @return the testray subtask that was added
	 */
	public static com.liferay.osb.testray.model.TestraySubtask
		addTestraySubtask(
			com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		return getService().addTestraySubtask(testraySubtask);
	}

	public static void clearTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId) {

		getService().clearTestrayCaseResultTestraySubtasks(testrayCaseResultId);
	}

	public static void clearTestrayIssueTestraySubtasks(long testrayIssueId) {
		getService().clearTestrayIssueTestraySubtasks(testrayIssueId);
	}

	/**
	 * Creates a new testray subtask with the primary key. Does not add the testray subtask to the database.
	 *
	 * @param testraySubtaskId the primary key for the new testray subtask
	 * @return the new testray subtask
	 */
	public static com.liferay.osb.testray.model.TestraySubtask
		createTestraySubtask(long testraySubtaskId) {

		return getService().createTestraySubtask(testraySubtaskId);
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

	public static void deleteTestrayCaseResultTestraySubtask(
		long testrayCaseResultId, long testraySubtaskId) {

		getService().deleteTestrayCaseResultTestraySubtask(
			testrayCaseResultId, testraySubtaskId);
	}

	public static void deleteTestrayCaseResultTestraySubtask(
		long testrayCaseResultId,
		com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		getService().deleteTestrayCaseResultTestraySubtask(
			testrayCaseResultId, testraySubtask);
	}

	public static void deleteTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks) {

		getService().deleteTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, testraySubtasks);
	}

	public static void deleteTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, long[] testraySubtaskIds) {

		getService().deleteTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, testraySubtaskIds);
	}

	public static void deleteTestrayIssueTestraySubtask(
		long testrayIssueId, long testraySubtaskId) {

		getService().deleteTestrayIssueTestraySubtask(
			testrayIssueId, testraySubtaskId);
	}

	public static void deleteTestrayIssueTestraySubtask(
		long testrayIssueId,
		com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		getService().deleteTestrayIssueTestraySubtask(
			testrayIssueId, testraySubtask);
	}

	public static void deleteTestrayIssueTestraySubtasks(
		long testrayIssueId,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks) {

		getService().deleteTestrayIssueTestraySubtasks(
			testrayIssueId, testraySubtasks);
	}

	public static void deleteTestrayIssueTestraySubtasks(
		long testrayIssueId, long[] testraySubtaskIds) {

		getService().deleteTestrayIssueTestraySubtasks(
			testrayIssueId, testraySubtaskIds);
	}

	/**
	 * Deletes the testray subtask with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask that was removed
	 * @throws PortalException if a testray subtask with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestraySubtask
			deleteTestraySubtask(long testraySubtaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTestraySubtask(testraySubtaskId);
	}

	/**
	 * Deletes the testray subtask from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtask the testray subtask
	 * @return the testray subtask that was removed
	 */
	public static com.liferay.osb.testray.model.TestraySubtask
		deleteTestraySubtask(
			com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		return getService().deleteTestraySubtask(testraySubtask);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySubtaskModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySubtaskModelImpl</code>.
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

	public static com.liferay.osb.testray.model.TestraySubtask
		fetchTestraySubtask(long testraySubtaskId) {

		return getService().fetchTestraySubtask(testraySubtaskId);
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

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testrayCaseResultIds of the testray case results associated with the testray subtask.
	 *
	 * @param testraySubtaskId the testraySubtaskId of the testray subtask
	 * @return long[] the testrayCaseResultIds of testray case results associated with the testray subtask
	 */
	public static long[] getTestrayCaseResultPrimaryKeys(
		long testraySubtaskId) {

		return getService().getTestrayCaseResultPrimaryKeys(testraySubtaskId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayCaseResultTestraySubtasks(long testrayCaseResultId) {

		return getService().getTestrayCaseResultTestraySubtasks(
			testrayCaseResultId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayCaseResultTestraySubtasks(
			long testrayCaseResultId, int start, int end) {

		return getService().getTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayCaseResultTestraySubtasks(
			long testrayCaseResultId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestraySubtask>
					orderByComparator) {

		return getService().getTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, start, end, orderByComparator);
	}

	public static int getTestrayCaseResultTestraySubtasksCount(
		long testrayCaseResultId) {

		return getService().getTestrayCaseResultTestraySubtasksCount(
			testrayCaseResultId);
	}

	/**
	 * Returns the testrayIssueIds of the testray issues associated with the testray subtask.
	 *
	 * @param testraySubtaskId the testraySubtaskId of the testray subtask
	 * @return long[] the testrayIssueIds of testray issues associated with the testray subtask
	 */
	public static long[] getTestrayIssuePrimaryKeys(long testraySubtaskId) {
		return getService().getTestrayIssuePrimaryKeys(testraySubtaskId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayIssueTestraySubtasks(long testrayIssueId) {

		return getService().getTestrayIssueTestraySubtasks(testrayIssueId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayIssueTestraySubtasks(
			long testrayIssueId, int start, int end) {

		return getService().getTestrayIssueTestraySubtasks(
			testrayIssueId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayIssueTestraySubtasks(
			long testrayIssueId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestraySubtask>
					orderByComparator) {

		return getService().getTestrayIssueTestraySubtasks(
			testrayIssueId, start, end, orderByComparator);
	}

	public static int getTestrayIssueTestraySubtasksCount(long testrayIssueId) {
		return getService().getTestrayIssueTestraySubtasksCount(testrayIssueId);
	}

	/**
	 * Returns the testray subtask with the primary key.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask
	 * @throws PortalException if a testray subtask with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestraySubtask
			getTestraySubtask(long testraySubtaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTestraySubtask(testraySubtaskId);
	}

	/**
	 * Returns a range of all the testray subtasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySubtaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @return the range of testray subtasks
	 */
	public static java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(int start, int end) {

		return getService().getTestraySubtasks(start, end);
	}

	/**
	 * Returns the number of testray subtasks.
	 *
	 * @return the number of testray subtasks
	 */
	public static int getTestraySubtasksCount() {
		return getService().getTestraySubtasksCount();
	}

	public static boolean hasTestrayCaseResultTestraySubtask(
		long testrayCaseResultId, long testraySubtaskId) {

		return getService().hasTestrayCaseResultTestraySubtask(
			testrayCaseResultId, testraySubtaskId);
	}

	public static boolean hasTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId) {

		return getService().hasTestrayCaseResultTestraySubtasks(
			testrayCaseResultId);
	}

	public static boolean hasTestrayIssueTestraySubtask(
		long testrayIssueId, long testraySubtaskId) {

		return getService().hasTestrayIssueTestraySubtask(
			testrayIssueId, testraySubtaskId);
	}

	public static boolean hasTestrayIssueTestraySubtasks(long testrayIssueId) {
		return getService().hasTestrayIssueTestraySubtasks(testrayIssueId);
	}

	public static void setTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, long[] testraySubtaskIds) {

		getService().setTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, testraySubtaskIds);
	}

	public static void setTestrayIssueTestraySubtasks(
		long testrayIssueId, long[] testraySubtaskIds) {

		getService().setTestrayIssueTestraySubtasks(
			testrayIssueId, testraySubtaskIds);
	}

	/**
	 * Updates the testray subtask in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtask the testray subtask
	 * @return the testray subtask that was updated
	 */
	public static com.liferay.osb.testray.model.TestraySubtask
		updateTestraySubtask(
			com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		return getService().updateTestraySubtask(testraySubtask);
	}

	public static TestraySubtaskLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestraySubtaskLocalService, TestraySubtaskLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestraySubtaskLocalService.class);

		ServiceTracker<TestraySubtaskLocalService, TestraySubtaskLocalService>
			serviceTracker =
				new ServiceTracker
					<TestraySubtaskLocalService, TestraySubtaskLocalService>(
						bundle.getBundleContext(),
						TestraySubtaskLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
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
 * Provides the local service utility for TestrayIssue. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayIssueLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayIssueLocalService
 * @generated
 */
public class TestrayIssueLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayIssueLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addTestrayCaseResultTestrayIssue(
		long testrayCaseResultId, long testrayIssueId) {

		getService().addTestrayCaseResultTestrayIssue(
			testrayCaseResultId, testrayIssueId);
	}

	public static void addTestrayCaseResultTestrayIssue(
		long testrayCaseResultId,
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		getService().addTestrayCaseResultTestrayIssue(
			testrayCaseResultId, testrayIssue);
	}

	public static void addTestrayCaseResultTestrayIssues(
		long testrayCaseResultId,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues) {

		getService().addTestrayCaseResultTestrayIssues(
			testrayCaseResultId, testrayIssues);
	}

	public static void addTestrayCaseResultTestrayIssues(
		long testrayCaseResultId, long[] testrayIssueIds) {

		getService().addTestrayCaseResultTestrayIssues(
			testrayCaseResultId, testrayIssueIds);
	}

	/**
	 * Adds the testray issue to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayIssue the testray issue
	 * @return the testray issue that was added
	 */
	public static com.liferay.osb.testray.model.TestrayIssue addTestrayIssue(
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		return getService().addTestrayIssue(testrayIssue);
	}

	public static void addTestraySubtaskTestrayIssue(
		long testraySubtaskId, long testrayIssueId) {

		getService().addTestraySubtaskTestrayIssue(
			testraySubtaskId, testrayIssueId);
	}

	public static void addTestraySubtaskTestrayIssue(
		long testraySubtaskId,
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		getService().addTestraySubtaskTestrayIssue(
			testraySubtaskId, testrayIssue);
	}

	public static void addTestraySubtaskTestrayIssues(
		long testraySubtaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues) {

		getService().addTestraySubtaskTestrayIssues(
			testraySubtaskId, testrayIssues);
	}

	public static void addTestraySubtaskTestrayIssues(
		long testraySubtaskId, long[] testrayIssueIds) {

		getService().addTestraySubtaskTestrayIssues(
			testraySubtaskId, testrayIssueIds);
	}

	public static void clearTestrayCaseResultTestrayIssues(
		long testrayCaseResultId) {

		getService().clearTestrayCaseResultTestrayIssues(testrayCaseResultId);
	}

	public static void clearTestraySubtaskTestrayIssues(long testraySubtaskId) {
		getService().clearTestraySubtaskTestrayIssues(testraySubtaskId);
	}

	/**
	 * Creates a new testray issue with the primary key. Does not add the testray issue to the database.
	 *
	 * @param testrayIssueId the primary key for the new testray issue
	 * @return the new testray issue
	 */
	public static com.liferay.osb.testray.model.TestrayIssue createTestrayIssue(
		long testrayIssueId) {

		return getService().createTestrayIssue(testrayIssueId);
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

	public static void deleteTestrayCaseResultTestrayIssue(
		long testrayCaseResultId, long testrayIssueId) {

		getService().deleteTestrayCaseResultTestrayIssue(
			testrayCaseResultId, testrayIssueId);
	}

	public static void deleteTestrayCaseResultTestrayIssue(
		long testrayCaseResultId,
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		getService().deleteTestrayCaseResultTestrayIssue(
			testrayCaseResultId, testrayIssue);
	}

	public static void deleteTestrayCaseResultTestrayIssues(
		long testrayCaseResultId,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues) {

		getService().deleteTestrayCaseResultTestrayIssues(
			testrayCaseResultId, testrayIssues);
	}

	public static void deleteTestrayCaseResultTestrayIssues(
		long testrayCaseResultId, long[] testrayIssueIds) {

		getService().deleteTestrayCaseResultTestrayIssues(
			testrayCaseResultId, testrayIssueIds);
	}

	/**
	 * Deletes the testray issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayIssueId the primary key of the testray issue
	 * @return the testray issue that was removed
	 * @throws PortalException if a testray issue with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayIssue deleteTestrayIssue(
			long testrayIssueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTestrayIssue(testrayIssueId);
	}

	/**
	 * Deletes the testray issue from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayIssue the testray issue
	 * @return the testray issue that was removed
	 */
	public static com.liferay.osb.testray.model.TestrayIssue deleteTestrayIssue(
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		return getService().deleteTestrayIssue(testrayIssue);
	}

	public static void deleteTestraySubtaskTestrayIssue(
		long testraySubtaskId, long testrayIssueId) {

		getService().deleteTestraySubtaskTestrayIssue(
			testraySubtaskId, testrayIssueId);
	}

	public static void deleteTestraySubtaskTestrayIssue(
		long testraySubtaskId,
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		getService().deleteTestraySubtaskTestrayIssue(
			testraySubtaskId, testrayIssue);
	}

	public static void deleteTestraySubtaskTestrayIssues(
		long testraySubtaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues) {

		getService().deleteTestraySubtaskTestrayIssues(
			testraySubtaskId, testrayIssues);
	}

	public static void deleteTestraySubtaskTestrayIssues(
		long testraySubtaskId, long[] testrayIssueIds) {

		getService().deleteTestraySubtaskTestrayIssues(
			testraySubtaskId, testrayIssueIds);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayIssueModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayIssueModelImpl</code>.
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

	public static com.liferay.osb.testray.model.TestrayIssue fetchTestrayIssue(
		long testrayIssueId) {

		return getService().fetchTestrayIssue(testrayIssueId);
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
	 * Returns the testrayCaseResultIds of the testray case results associated with the testray issue.
	 *
	 * @param testrayIssueId the testrayIssueId of the testray issue
	 * @return long[] the testrayCaseResultIds of testray case results associated with the testray issue
	 */
	public static long[] getTestrayCaseResultPrimaryKeys(long testrayIssueId) {
		return getService().getTestrayCaseResultPrimaryKeys(testrayIssueId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayCaseResultTestrayIssues(long testrayCaseResultId) {

		return getService().getTestrayCaseResultTestrayIssues(
			testrayCaseResultId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayCaseResultTestrayIssues(
			long testrayCaseResultId, int start, int end) {

		return getService().getTestrayCaseResultTestrayIssues(
			testrayCaseResultId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayCaseResultTestrayIssues(
			long testrayCaseResultId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayIssue>
					orderByComparator) {

		return getService().getTestrayCaseResultTestrayIssues(
			testrayCaseResultId, start, end, orderByComparator);
	}

	public static int getTestrayCaseResultTestrayIssuesCount(
		long testrayCaseResultId) {

		return getService().getTestrayCaseResultTestrayIssuesCount(
			testrayCaseResultId);
	}

	/**
	 * Returns the testray issue with the primary key.
	 *
	 * @param testrayIssueId the primary key of the testray issue
	 * @return the testray issue
	 * @throws PortalException if a testray issue with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayIssue getTestrayIssue(
			long testrayIssueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTestrayIssue(testrayIssueId);
	}

	/**
	 * Returns a range of all the testray issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray issues
	 * @param end the upper bound of the range of testray issues (not inclusive)
	 * @return the range of testray issues
	 */
	public static java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(int start, int end) {

		return getService().getTestrayIssues(start, end);
	}

	/**
	 * Returns the number of testray issues.
	 *
	 * @return the number of testray issues
	 */
	public static int getTestrayIssuesCount() {
		return getService().getTestrayIssuesCount();
	}

	/**
	 * Returns the testraySubtaskIds of the testray subtasks associated with the testray issue.
	 *
	 * @param testrayIssueId the testrayIssueId of the testray issue
	 * @return long[] the testraySubtaskIds of testray subtasks associated with the testray issue
	 */
	public static long[] getTestraySubtaskPrimaryKeys(long testrayIssueId) {
		return getService().getTestraySubtaskPrimaryKeys(testrayIssueId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestraySubtaskTestrayIssues(long testraySubtaskId) {

		return getService().getTestraySubtaskTestrayIssues(testraySubtaskId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestraySubtaskTestrayIssues(
			long testraySubtaskId, int start, int end) {

		return getService().getTestraySubtaskTestrayIssues(
			testraySubtaskId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestraySubtaskTestrayIssues(
			long testraySubtaskId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayIssue>
					orderByComparator) {

		return getService().getTestraySubtaskTestrayIssues(
			testraySubtaskId, start, end, orderByComparator);
	}

	public static int getTestraySubtaskTestrayIssuesCount(
		long testraySubtaskId) {

		return getService().getTestraySubtaskTestrayIssuesCount(
			testraySubtaskId);
	}

	public static boolean hasTestrayCaseResultTestrayIssue(
		long testrayCaseResultId, long testrayIssueId) {

		return getService().hasTestrayCaseResultTestrayIssue(
			testrayCaseResultId, testrayIssueId);
	}

	public static boolean hasTestrayCaseResultTestrayIssues(
		long testrayCaseResultId) {

		return getService().hasTestrayCaseResultTestrayIssues(
			testrayCaseResultId);
	}

	public static boolean hasTestraySubtaskTestrayIssue(
		long testraySubtaskId, long testrayIssueId) {

		return getService().hasTestraySubtaskTestrayIssue(
			testraySubtaskId, testrayIssueId);
	}

	public static boolean hasTestraySubtaskTestrayIssues(
		long testraySubtaskId) {

		return getService().hasTestraySubtaskTestrayIssues(testraySubtaskId);
	}

	public static void setTestrayCaseResultTestrayIssues(
		long testrayCaseResultId, long[] testrayIssueIds) {

		getService().setTestrayCaseResultTestrayIssues(
			testrayCaseResultId, testrayIssueIds);
	}

	public static void setTestraySubtaskTestrayIssues(
		long testraySubtaskId, long[] testrayIssueIds) {

		getService().setTestraySubtaskTestrayIssues(
			testraySubtaskId, testrayIssueIds);
	}

	/**
	 * Updates the testray issue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayIssue the testray issue
	 * @return the testray issue that was updated
	 */
	public static com.liferay.osb.testray.model.TestrayIssue updateTestrayIssue(
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		return getService().updateTestrayIssue(testrayIssue);
	}

	public static TestrayIssueLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayIssueLocalService, TestrayIssueLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TestrayIssueLocalService.class);

		ServiceTracker<TestrayIssueLocalService, TestrayIssueLocalService>
			serviceTracker =
				new ServiceTracker
					<TestrayIssueLocalService, TestrayIssueLocalService>(
						bundle.getBundleContext(),
						TestrayIssueLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
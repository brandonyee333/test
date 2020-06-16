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

package com.liferay.osb.customer.release.notes.jira.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for JIRAIssue. This utility wraps
 * <code>com.liferay.osb.customer.release.notes.jira.service.impl.JIRAIssueLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssueLocalService
 * @generated
 */
public class JIRAIssueLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.release.notes.jira.service.impl.JIRAIssueLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the jira issue to the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraIssue the jira issue
	 * @return the jira issue that was added
	 */
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
		addJIRAIssue(
			com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
				jiraIssue) {

		return getService().addJIRAIssue(jiraIssue);
	}

	/**
	 * Creates a new jira issue with the primary key. Does not add the jira issue to the database.
	 *
	 * @param jiraIssueId the primary key for the new jira issue
	 * @return the new jira issue
	 */
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
		createJIRAIssue(long jiraIssueId) {

		return getService().createJIRAIssue(jiraIssueId);
	}

	/**
	 * Deletes the jira issue from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraIssue the jira issue
	 * @return the jira issue that was removed
	 */
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
		deleteJIRAIssue(
			com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
				jiraIssue) {

		return getService().deleteJIRAIssue(jiraIssue);
	}

	/**
	 * Deletes the jira issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraIssueId the primary key of the jira issue
	 * @return the jira issue that was removed
	 * @throws PortalException if a jira issue with the primary key could not be found
	 */
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
			deleteJIRAIssue(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteJIRAIssue(jiraIssueId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAIssueModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAIssueModelImpl</code>.
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

	public static com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
		fetchJIRAIssue(long jiraIssueId) {

		return getService().fetchJIRAIssue(jiraIssueId);
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

	public static java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue>
			getIsRelatedToJIRAIssues(long jiraIssueId) {

		return getService().getIsRelatedToJIRAIssues(jiraIssueId);
	}

	/**
	 * Returns the jira issue with the primary key.
	 *
	 * @param jiraIssueId the primary key of the jira issue
	 * @return the jira issue
	 * @throws PortalException if a jira issue with the primary key could not be found
	 */
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
			getJIRAIssue(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getJIRAIssue(jiraIssueId);
	}

	public static com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
		getJIRAIssue(String jiraIssueKey) {

		return getService().getJIRAIssue(jiraIssueKey);
	}

	/**
	 * Returns a range of all the jira issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira issues
	 * @param end the upper bound of the range of jira issues (not inclusive)
	 * @return the range of jira issues
	 */
	public static java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue>
			getJIRAIssues(int start, int end) {

		return getService().getJIRAIssues(start, end);
	}

	public static java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue>
				getJIRAIssues(String[] jiraIssueKeys)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getJIRAIssues(jiraIssueKeys);
	}

	/**
	 * Returns the number of jira issues.
	 *
	 * @return the number of jira issues
	 */
	public static int getJIRAIssuesCount() {
		return getService().getJIRAIssuesCount();
	}

	public static java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue>
			getJIRALabelJIRAIssues(String label, String jiraProjectKey) {

		return getService().getJIRALabelJIRAIssues(label, jiraProjectKey);
	}

	public static java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue>
			getJIRAProjectVersionJIRAIssues(long jiraProjectVersionId) {

		return getService().getJIRAProjectVersionJIRAIssues(
			jiraProjectVersionId);
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
	 * Updates the jira issue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param jiraIssue the jira issue
	 * @return the jira issue that was updated
	 */
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
		updateJIRAIssue(
			com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
				jiraIssue) {

		return getService().updateJIRAIssue(jiraIssue);
	}

	public static JIRAIssueLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<JIRAIssueLocalService, JIRAIssueLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(JIRAIssueLocalService.class);

		ServiceTracker<JIRAIssueLocalService, JIRAIssueLocalService>
			serviceTracker =
				new ServiceTracker
					<JIRAIssueLocalService, JIRAIssueLocalService>(
						bundle.getBundleContext(), JIRAIssueLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
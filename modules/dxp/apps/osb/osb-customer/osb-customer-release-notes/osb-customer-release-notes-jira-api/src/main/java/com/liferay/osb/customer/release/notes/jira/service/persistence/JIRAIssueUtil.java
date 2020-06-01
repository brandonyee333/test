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

package com.liferay.osb.customer.release.notes.jira.service.persistence;

import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the jira issue service. This utility wraps <code>com.liferay.osb.customer.release.notes.jira.service.persistence.impl.JIRAIssuePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssuePersistence
 * @generated
 */
public class JIRAIssueUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(JIRAIssue jiraIssue) {
		getPersistence().clearCache(jiraIssue);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, JIRAIssue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<JIRAIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<JIRAIssue> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static JIRAIssue update(JIRAIssue jiraIssue) {
		return getPersistence().update(jiraIssue);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static JIRAIssue update(
		JIRAIssue jiraIssue, ServiceContext serviceContext) {

		return getPersistence().update(jiraIssue, serviceContext);
	}

	/**
	 * Caches the jira issue in the entity cache if it is enabled.
	 *
	 * @param jiraIssue the jira issue
	 */
	public static void cacheResult(JIRAIssue jiraIssue) {
		getPersistence().cacheResult(jiraIssue);
	}

	/**
	 * Caches the jira issues in the entity cache if it is enabled.
	 *
	 * @param jiraIssues the jira issues
	 */
	public static void cacheResult(List<JIRAIssue> jiraIssues) {
		getPersistence().cacheResult(jiraIssues);
	}

	/**
	 * Creates a new jira issue with the primary key. Does not add the jira issue to the database.
	 *
	 * @param jiraIssueId the primary key for the new jira issue
	 * @return the new jira issue
	 */
	public static JIRAIssue create(long jiraIssueId) {
		return getPersistence().create(jiraIssueId);
	}

	/**
	 * Removes the jira issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraIssueId the primary key of the jira issue
	 * @return the jira issue that was removed
	 * @throws NoSuchJIRAIssueException if a jira issue with the primary key could not be found
	 */
	public static JIRAIssue remove(long jiraIssueId)
		throws com.liferay.osb.customer.release.notes.jira.exception.
			NoSuchJIRAIssueException {

		return getPersistence().remove(jiraIssueId);
	}

	public static JIRAIssue updateImpl(JIRAIssue jiraIssue) {
		return getPersistence().updateImpl(jiraIssue);
	}

	/**
	 * Returns the jira issue with the primary key or throws a <code>NoSuchJIRAIssueException</code> if it could not be found.
	 *
	 * @param jiraIssueId the primary key of the jira issue
	 * @return the jira issue
	 * @throws NoSuchJIRAIssueException if a jira issue with the primary key could not be found
	 */
	public static JIRAIssue findByPrimaryKey(long jiraIssueId)
		throws com.liferay.osb.customer.release.notes.jira.exception.
			NoSuchJIRAIssueException {

		return getPersistence().findByPrimaryKey(jiraIssueId);
	}

	/**
	 * Returns the jira issue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraIssueId the primary key of the jira issue
	 * @return the jira issue, or <code>null</code> if a jira issue with the primary key could not be found
	 */
	public static JIRAIssue fetchByPrimaryKey(long jiraIssueId) {
		return getPersistence().fetchByPrimaryKey(jiraIssueId);
	}

	/**
	 * Returns all the jira issues.
	 *
	 * @return the jira issues
	 */
	public static List<JIRAIssue> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the jira issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira issues
	 * @param end the upper bound of the range of jira issues (not inclusive)
	 * @return the range of jira issues
	 */
	public static List<JIRAIssue> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the jira issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira issues
	 * @param end the upper bound of the range of jira issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jira issues
	 */
	public static List<JIRAIssue> findAll(
		int start, int end, OrderByComparator<JIRAIssue> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the jira issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira issues
	 * @param end the upper bound of the range of jira issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of jira issues
	 */
	public static List<JIRAIssue> findAll(
		int start, int end, OrderByComparator<JIRAIssue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the jira issues from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of jira issues.
	 *
	 * @return the number of jira issues
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static JIRAIssuePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<JIRAIssuePersistence, JIRAIssuePersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(JIRAIssuePersistence.class);

		ServiceTracker<JIRAIssuePersistence, JIRAIssuePersistence>
			serviceTracker =
				new ServiceTracker<JIRAIssuePersistence, JIRAIssuePersistence>(
					bundle.getBundleContext(), JIRAIssuePersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
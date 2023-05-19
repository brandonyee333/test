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

import com.liferay.osb.customer.release.notes.jira.model.JIRAProject;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the jira project service. This utility wraps <code>com.liferay.osb.customer.release.notes.jira.service.persistence.impl.JIRAProjectPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProjectPersistence
 * @generated
 */
public class JIRAProjectUtil {

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
	public static void clearCache(JIRAProject jiraProject) {
		getPersistence().clearCache(jiraProject);
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
	public static Map<Serializable, JIRAProject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<JIRAProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<JIRAProject> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static JIRAProject update(JIRAProject jiraProject) {
		return getPersistence().update(jiraProject);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static JIRAProject update(
		JIRAProject jiraProject, ServiceContext serviceContext) {

		return getPersistence().update(jiraProject, serviceContext);
	}

	/**
	 * Returns the jira project where key = &#63; or throws a <code>NoSuchJIRAProjectException</code> if it could not be found.
	 *
	 * @param key the key
	 * @return the matching jira project
	 * @throws NoSuchJIRAProjectException if a matching jira project could not be found
	 */
	public static JIRAProject findByKey(String key)
		throws com.liferay.osb.customer.release.notes.jira.exception.
			NoSuchJIRAProjectException {

		return getPersistence().findByKey(key);
	}

	/**
	 * Returns the jira project where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param key the key
	 * @return the matching jira project, or <code>null</code> if a matching jira project could not be found
	 */
	public static JIRAProject fetchByKey(String key) {
		return getPersistence().fetchByKey(key);
	}

	/**
	 * Returns the jira project where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param key the key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching jira project, or <code>null</code> if a matching jira project could not be found
	 */
	public static JIRAProject fetchByKey(String key, boolean useFinderCache) {
		return getPersistence().fetchByKey(key, useFinderCache);
	}

	/**
	 * Removes the jira project where key = &#63; from the database.
	 *
	 * @param key the key
	 * @return the jira project that was removed
	 */
	public static JIRAProject removeByKey(String key)
		throws com.liferay.osb.customer.release.notes.jira.exception.
			NoSuchJIRAProjectException {

		return getPersistence().removeByKey(key);
	}

	/**
	 * Returns the number of jira projects where key = &#63;.
	 *
	 * @param key the key
	 * @return the number of matching jira projects
	 */
	public static int countByKey(String key) {
		return getPersistence().countByKey(key);
	}

	/**
	 * Caches the jira project in the entity cache if it is enabled.
	 *
	 * @param jiraProject the jira project
	 */
	public static void cacheResult(JIRAProject jiraProject) {
		getPersistence().cacheResult(jiraProject);
	}

	/**
	 * Caches the jira projects in the entity cache if it is enabled.
	 *
	 * @param jiraProjects the jira projects
	 */
	public static void cacheResult(List<JIRAProject> jiraProjects) {
		getPersistence().cacheResult(jiraProjects);
	}

	/**
	 * Creates a new jira project with the primary key. Does not add the jira project to the database.
	 *
	 * @param jiraProjectId the primary key for the new jira project
	 * @return the new jira project
	 */
	public static JIRAProject create(long jiraProjectId) {
		return getPersistence().create(jiraProjectId);
	}

	/**
	 * Removes the jira project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraProjectId the primary key of the jira project
	 * @return the jira project that was removed
	 * @throws NoSuchJIRAProjectException if a jira project with the primary key could not be found
	 */
	public static JIRAProject remove(long jiraProjectId)
		throws com.liferay.osb.customer.release.notes.jira.exception.
			NoSuchJIRAProjectException {

		return getPersistence().remove(jiraProjectId);
	}

	public static JIRAProject updateImpl(JIRAProject jiraProject) {
		return getPersistence().updateImpl(jiraProject);
	}

	/**
	 * Returns the jira project with the primary key or throws a <code>NoSuchJIRAProjectException</code> if it could not be found.
	 *
	 * @param jiraProjectId the primary key of the jira project
	 * @return the jira project
	 * @throws NoSuchJIRAProjectException if a jira project with the primary key could not be found
	 */
	public static JIRAProject findByPrimaryKey(long jiraProjectId)
		throws com.liferay.osb.customer.release.notes.jira.exception.
			NoSuchJIRAProjectException {

		return getPersistence().findByPrimaryKey(jiraProjectId);
	}

	/**
	 * Returns the jira project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraProjectId the primary key of the jira project
	 * @return the jira project, or <code>null</code> if a jira project with the primary key could not be found
	 */
	public static JIRAProject fetchByPrimaryKey(long jiraProjectId) {
		return getPersistence().fetchByPrimaryKey(jiraProjectId);
	}

	/**
	 * Returns all the jira projects.
	 *
	 * @return the jira projects
	 */
	public static List<JIRAProject> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the jira projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira projects
	 * @param end the upper bound of the range of jira projects (not inclusive)
	 * @return the range of jira projects
	 */
	public static List<JIRAProject> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the jira projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira projects
	 * @param end the upper bound of the range of jira projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jira projects
	 */
	public static List<JIRAProject> findAll(
		int start, int end, OrderByComparator<JIRAProject> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the jira projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira projects
	 * @param end the upper bound of the range of jira projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of jira projects
	 */
	public static List<JIRAProject> findAll(
		int start, int end, OrderByComparator<JIRAProject> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the jira projects from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of jira projects.
	 *
	 * @return the number of jira projects
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static JIRAProjectPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(JIRAProjectPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile JIRAProjectPersistence _persistence;

}
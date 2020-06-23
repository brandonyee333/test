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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAProjectException;
import com.liferay.osb.customer.release.notes.jira.model.JIRAProject;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the jira project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProjectUtil
 * @generated
 */
@ProviderType
public interface JIRAProjectPersistence extends BasePersistence<JIRAProject> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAProjectUtil} to access the jira project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, JIRAProject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the jira project where key = &#63; or throws a <code>NoSuchJIRAProjectException</code> if it could not be found.
	 *
	 * @param key the key
	 * @return the matching jira project
	 * @throws NoSuchJIRAProjectException if a matching jira project could not be found
	 */
	public JIRAProject findByKey(String key) throws NoSuchJIRAProjectException;

	/**
	 * Returns the jira project where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param key the key
	 * @return the matching jira project, or <code>null</code> if a matching jira project could not be found
	 */
	public JIRAProject fetchByKey(String key);

	/**
	 * Returns the jira project where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param key the key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching jira project, or <code>null</code> if a matching jira project could not be found
	 */
	public JIRAProject fetchByKey(String key, boolean useFinderCache);

	/**
	 * Removes the jira project where key = &#63; from the database.
	 *
	 * @param key the key
	 * @return the jira project that was removed
	 */
	public JIRAProject removeByKey(String key)
		throws NoSuchJIRAProjectException;

	/**
	 * Returns the number of jira projects where key = &#63;.
	 *
	 * @param key the key
	 * @return the number of matching jira projects
	 */
	public int countByKey(String key);

	/**
	 * Caches the jira project in the entity cache if it is enabled.
	 *
	 * @param jiraProject the jira project
	 */
	public void cacheResult(JIRAProject jiraProject);

	/**
	 * Caches the jira projects in the entity cache if it is enabled.
	 *
	 * @param jiraProjects the jira projects
	 */
	public void cacheResult(java.util.List<JIRAProject> jiraProjects);

	/**
	 * Creates a new jira project with the primary key. Does not add the jira project to the database.
	 *
	 * @param jiraProjectId the primary key for the new jira project
	 * @return the new jira project
	 */
	public JIRAProject create(long jiraProjectId);

	/**
	 * Removes the jira project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraProjectId the primary key of the jira project
	 * @return the jira project that was removed
	 * @throws NoSuchJIRAProjectException if a jira project with the primary key could not be found
	 */
	public JIRAProject remove(long jiraProjectId)
		throws NoSuchJIRAProjectException;

	public JIRAProject updateImpl(JIRAProject jiraProject);

	/**
	 * Returns the jira project with the primary key or throws a <code>NoSuchJIRAProjectException</code> if it could not be found.
	 *
	 * @param jiraProjectId the primary key of the jira project
	 * @return the jira project
	 * @throws NoSuchJIRAProjectException if a jira project with the primary key could not be found
	 */
	public JIRAProject findByPrimaryKey(long jiraProjectId)
		throws NoSuchJIRAProjectException;

	/**
	 * Returns the jira project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraProjectId the primary key of the jira project
	 * @return the jira project, or <code>null</code> if a jira project with the primary key could not be found
	 */
	public JIRAProject fetchByPrimaryKey(long jiraProjectId);

	/**
	 * Returns all the jira projects.
	 *
	 * @return the jira projects
	 */
	public java.util.List<JIRAProject> findAll();

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
	public java.util.List<JIRAProject> findAll(int start, int end);

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
	public java.util.List<JIRAProject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAProject>
			orderByComparator);

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
	public java.util.List<JIRAProject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAProject>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the jira projects from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of jira projects.
	 *
	 * @return the number of jira projects
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}
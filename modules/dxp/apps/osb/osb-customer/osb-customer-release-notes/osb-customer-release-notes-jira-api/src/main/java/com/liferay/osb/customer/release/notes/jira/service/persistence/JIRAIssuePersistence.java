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

import com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAIssueException;
import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the jira issue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssueUtil
 * @generated
 */
@ProviderType
public interface JIRAIssuePersistence extends BasePersistence<JIRAIssue> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAIssueUtil} to access the jira issue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, JIRAIssue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the jira issue in the entity cache if it is enabled.
	 *
	 * @param jiraIssue the jira issue
	 */
	public void cacheResult(JIRAIssue jiraIssue);

	/**
	 * Caches the jira issues in the entity cache if it is enabled.
	 *
	 * @param jiraIssues the jira issues
	 */
	public void cacheResult(java.util.List<JIRAIssue> jiraIssues);

	/**
	 * Creates a new jira issue with the primary key. Does not add the jira issue to the database.
	 *
	 * @param jiraIssueId the primary key for the new jira issue
	 * @return the new jira issue
	 */
	public JIRAIssue create(long jiraIssueId);

	/**
	 * Removes the jira issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraIssueId the primary key of the jira issue
	 * @return the jira issue that was removed
	 * @throws NoSuchJIRAIssueException if a jira issue with the primary key could not be found
	 */
	public JIRAIssue remove(long jiraIssueId) throws NoSuchJIRAIssueException;

	public JIRAIssue updateImpl(JIRAIssue jiraIssue);

	/**
	 * Returns the jira issue with the primary key or throws a <code>NoSuchJIRAIssueException</code> if it could not be found.
	 *
	 * @param jiraIssueId the primary key of the jira issue
	 * @return the jira issue
	 * @throws NoSuchJIRAIssueException if a jira issue with the primary key could not be found
	 */
	public JIRAIssue findByPrimaryKey(long jiraIssueId)
		throws NoSuchJIRAIssueException;

	/**
	 * Returns the jira issue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraIssueId the primary key of the jira issue
	 * @return the jira issue, or <code>null</code> if a jira issue with the primary key could not be found
	 */
	public JIRAIssue fetchByPrimaryKey(long jiraIssueId);

	/**
	 * Returns all the jira issues.
	 *
	 * @return the jira issues
	 */
	public java.util.List<JIRAIssue> findAll();

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
	public java.util.List<JIRAIssue> findAll(int start, int end);

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
	public java.util.List<JIRAIssue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue>
			orderByComparator);

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
	public java.util.List<JIRAIssue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the jira issues from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of jira issues.
	 *
	 * @return the number of jira issues
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}
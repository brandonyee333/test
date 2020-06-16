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

package com.liferay.osb.customer.release.notes.jira.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAIssueException;
import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the jira issue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.release.notes.jira.service.persistence.impl.JIRAIssuePersistenceImpl
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
	* Returns the jira issue with the primary key or throws a {@link NoSuchJIRAIssueException} if it could not be found.
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

	@Override
	public java.util.Map<java.io.Serializable, JIRAIssue> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira issues
	* @param end the upper bound of the range of jira issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of jira issues
	*/
	public java.util.List<JIRAIssue> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns an ordered range of all the jira issues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira issues
	* @param end the upper bound of the range of jira issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of jira issues
	*/
	public java.util.List<JIRAIssue> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator,
		boolean retrieveFromCache);

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
	public java.util.Set<java.lang.String> getBadColumnNames();
}
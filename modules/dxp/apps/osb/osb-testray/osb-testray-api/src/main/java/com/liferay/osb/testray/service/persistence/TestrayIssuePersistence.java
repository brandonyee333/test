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

package com.liferay.osb.testray.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.exception.NoSuchTestrayIssueException;
import com.liferay.osb.testray.model.TestrayIssue;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray issue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayIssueUtil
 * @generated
 */
@ProviderType
public interface TestrayIssuePersistence extends BasePersistence<TestrayIssue> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayIssueUtil} to access the testray issue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayIssue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the testray issue where name = &#63; or throws a <code>NoSuchTestrayIssueException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching testray issue
	 * @throws NoSuchTestrayIssueException if a matching testray issue could not be found
	 */
	public TestrayIssue findByName(String name)
		throws NoSuchTestrayIssueException;

	/**
	 * Returns the testray issue where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching testray issue, or <code>null</code> if a matching testray issue could not be found
	 */
	public TestrayIssue fetchByName(String name);

	/**
	 * Returns the testray issue where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching testray issue, or <code>null</code> if a matching testray issue could not be found
	 */
	public TestrayIssue fetchByName(String name, boolean useFinderCache);

	/**
	 * Removes the testray issue where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the testray issue that was removed
	 */
	public TestrayIssue removeByName(String name)
		throws NoSuchTestrayIssueException;

	/**
	 * Returns the number of testray issues where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching testray issues
	 */
	public int countByName(String name);

	/**
	 * Caches the testray issue in the entity cache if it is enabled.
	 *
	 * @param testrayIssue the testray issue
	 */
	public void cacheResult(TestrayIssue testrayIssue);

	/**
	 * Caches the testray issues in the entity cache if it is enabled.
	 *
	 * @param testrayIssues the testray issues
	 */
	public void cacheResult(java.util.List<TestrayIssue> testrayIssues);

	/**
	 * Creates a new testray issue with the primary key. Does not add the testray issue to the database.
	 *
	 * @param testrayIssueId the primary key for the new testray issue
	 * @return the new testray issue
	 */
	public TestrayIssue create(long testrayIssueId);

	/**
	 * Removes the testray issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayIssueId the primary key of the testray issue
	 * @return the testray issue that was removed
	 * @throws NoSuchTestrayIssueException if a testray issue with the primary key could not be found
	 */
	public TestrayIssue remove(long testrayIssueId)
		throws NoSuchTestrayIssueException;

	public TestrayIssue updateImpl(TestrayIssue testrayIssue);

	/**
	 * Returns the testray issue with the primary key or throws a <code>NoSuchTestrayIssueException</code> if it could not be found.
	 *
	 * @param testrayIssueId the primary key of the testray issue
	 * @return the testray issue
	 * @throws NoSuchTestrayIssueException if a testray issue with the primary key could not be found
	 */
	public TestrayIssue findByPrimaryKey(long testrayIssueId)
		throws NoSuchTestrayIssueException;

	/**
	 * Returns the testray issue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayIssueId the primary key of the testray issue
	 * @return the testray issue, or <code>null</code> if a testray issue with the primary key could not be found
	 */
	public TestrayIssue fetchByPrimaryKey(long testrayIssueId);

	/**
	 * Returns all the testray issues.
	 *
	 * @return the testray issues
	 */
	public java.util.List<TestrayIssue> findAll();

	/**
	 * Returns a range of all the testray issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray issues
	 * @param end the upper bound of the range of testray issues (not inclusive)
	 * @return the range of testray issues
	 */
	public java.util.List<TestrayIssue> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the testray issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray issues
	 * @param end the upper bound of the range of testray issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray issues
	 */
	public java.util.List<TestrayIssue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayIssue>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray issues
	 * @param end the upper bound of the range of testray issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray issues
	 */
	public java.util.List<TestrayIssue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayIssue>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the testray issues from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray issues.
	 *
	 * @return the number of testray issues
	 */
	public int countAll();

	/**
	 * Returns the primaryKeys of testray case results associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return long[] of the primaryKeys of testray case results associated with the testray issue
	 */
	public long[] getTestrayCaseResultPrimaryKeys(long pk);

	/**
	 * Returns all the testray case results associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return the testray case results associated with the testray issue
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(long pk);

	/**
	 * Returns a range of all the testray case results associated with the testray issue.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayIssueModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray issue
	 * @param start the lower bound of the range of testray issues
	 * @param end the upper bound of the range of testray issues (not inclusive)
	 * @return the range of testray case results associated with the testray issue
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(long pk, int start, int end);

	/**
	 * Returns an ordered range of all the testray case results associated with the testray issue.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayIssueModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray issue
	 * @param start the lower bound of the range of testray issues
	 * @param end the upper bound of the range of testray issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray case results associated with the testray issue
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCaseResult>
					orderByComparator);

	/**
	 * Returns the number of testray case results associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return the number of testray case results associated with the testray issue
	 */
	public int getTestrayCaseResultsSize(long pk);

	/**
	 * Returns <code>true</code> if the testray case result is associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPK the primary key of the testray case result
	 * @return <code>true</code> if the testray case result is associated with the testray issue; <code>false</code> otherwise
	 */
	public boolean containsTestrayCaseResult(long pk, long testrayCaseResultPK);

	/**
	 * Returns <code>true</code> if the testray issue has any testray case results associated with it.
	 *
	 * @param pk the primary key of the testray issue to check for associations with testray case results
	 * @return <code>true</code> if the testray issue has any testray case results associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayCaseResults(long pk);

	/**
	 * Adds an association between the testray issue and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPK the primary key of the testray case result
	 */
	public void addTestrayCaseResult(long pk, long testrayCaseResultPK);

	/**
	 * Adds an association between the testray issue and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResult the testray case result
	 */
	public void addTestrayCaseResult(
		long pk,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult);

	/**
	 * Adds an association between the testray issue and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPKs the primary keys of the testray case results
	 */
	public void addTestrayCaseResults(long pk, long[] testrayCaseResultPKs);

	/**
	 * Adds an association between the testray issue and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResults the testray case results
	 */
	public void addTestrayCaseResults(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults);

	/**
	 * Clears all associations between the testray issue and its testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue to clear the associated testray case results from
	 */
	public void clearTestrayCaseResults(long pk);

	/**
	 * Removes the association between the testray issue and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPK the primary key of the testray case result
	 */
	public void removeTestrayCaseResult(long pk, long testrayCaseResultPK);

	/**
	 * Removes the association between the testray issue and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResult the testray case result
	 */
	public void removeTestrayCaseResult(
		long pk,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult);

	/**
	 * Removes the association between the testray issue and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPKs the primary keys of the testray case results
	 */
	public void removeTestrayCaseResults(long pk, long[] testrayCaseResultPKs);

	/**
	 * Removes the association between the testray issue and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResults the testray case results
	 */
	public void removeTestrayCaseResults(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults);

	/**
	 * Sets the testray case results associated with the testray issue, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPKs the primary keys of the testray case results to be associated with the testray issue
	 */
	public void setTestrayCaseResults(long pk, long[] testrayCaseResultPKs);

	/**
	 * Sets the testray case results associated with the testray issue, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResults the testray case results to be associated with the testray issue
	 */
	public void setTestrayCaseResults(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults);

	/**
	 * Returns the primaryKeys of testray subtasks associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return long[] of the primaryKeys of testray subtasks associated with the testray issue
	 */
	public long[] getTestraySubtaskPrimaryKeys(long pk);

	/**
	 * Returns all the testray subtasks associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return the testray subtasks associated with the testray issue
	 */
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(long pk);

	/**
	 * Returns a range of all the testray subtasks associated with the testray issue.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayIssueModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray issue
	 * @param start the lower bound of the range of testray issues
	 * @param end the upper bound of the range of testray issues (not inclusive)
	 * @return the range of testray subtasks associated with the testray issue
	 */
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(long pk, int start, int end);

	/**
	 * Returns an ordered range of all the testray subtasks associated with the testray issue.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayIssueModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray issue
	 * @param start the lower bound of the range of testray issues
	 * @param end the upper bound of the range of testray issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray subtasks associated with the testray issue
	 */
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestraySubtask>
					orderByComparator);

	/**
	 * Returns the number of testray subtasks associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return the number of testray subtasks associated with the testray issue
	 */
	public int getTestraySubtasksSize(long pk);

	/**
	 * Returns <code>true</code> if the testray subtask is associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPK the primary key of the testray subtask
	 * @return <code>true</code> if the testray subtask is associated with the testray issue; <code>false</code> otherwise
	 */
	public boolean containsTestraySubtask(long pk, long testraySubtaskPK);

	/**
	 * Returns <code>true</code> if the testray issue has any testray subtasks associated with it.
	 *
	 * @param pk the primary key of the testray issue to check for associations with testray subtasks
	 * @return <code>true</code> if the testray issue has any testray subtasks associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestraySubtasks(long pk);

	/**
	 * Adds an association between the testray issue and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPK the primary key of the testray subtask
	 */
	public void addTestraySubtask(long pk, long testraySubtaskPK);

	/**
	 * Adds an association between the testray issue and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtask the testray subtask
	 */
	public void addTestraySubtask(
		long pk, com.liferay.osb.testray.model.TestraySubtask testraySubtask);

	/**
	 * Adds an association between the testray issue and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPKs the primary keys of the testray subtasks
	 */
	public void addTestraySubtasks(long pk, long[] testraySubtaskPKs);

	/**
	 * Adds an association between the testray issue and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtasks the testray subtasks
	 */
	public void addTestraySubtasks(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks);

	/**
	 * Clears all associations between the testray issue and its testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue to clear the associated testray subtasks from
	 */
	public void clearTestraySubtasks(long pk);

	/**
	 * Removes the association between the testray issue and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPK the primary key of the testray subtask
	 */
	public void removeTestraySubtask(long pk, long testraySubtaskPK);

	/**
	 * Removes the association between the testray issue and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtask the testray subtask
	 */
	public void removeTestraySubtask(
		long pk, com.liferay.osb.testray.model.TestraySubtask testraySubtask);

	/**
	 * Removes the association between the testray issue and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPKs the primary keys of the testray subtasks
	 */
	public void removeTestraySubtasks(long pk, long[] testraySubtaskPKs);

	/**
	 * Removes the association between the testray issue and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtasks the testray subtasks
	 */
	public void removeTestraySubtasks(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks);

	/**
	 * Sets the testray subtasks associated with the testray issue, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPKs the primary keys of the testray subtasks to be associated with the testray issue
	 */
	public void setTestraySubtasks(long pk, long[] testraySubtaskPKs);

	/**
	 * Sets the testray subtasks associated with the testray issue, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtasks the testray subtasks to be associated with the testray issue
	 */
	public void setTestraySubtasks(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks);

}
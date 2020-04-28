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

import com.liferay.osb.testray.exception.NoSuchTestraySubtaskException;
import com.liferay.osb.testray.model.TestraySubtask;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray subtask service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestraySubtaskUtil
 * @generated
 */
@ProviderType
public interface TestraySubtaskPersistence
	extends BasePersistence<TestraySubtask> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestraySubtaskUtil} to access the testray subtask persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestraySubtask> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the testray subtask in the entity cache if it is enabled.
	 *
	 * @param testraySubtask the testray subtask
	 */
	public void cacheResult(TestraySubtask testraySubtask);

	/**
	 * Caches the testray subtasks in the entity cache if it is enabled.
	 *
	 * @param testraySubtasks the testray subtasks
	 */
	public void cacheResult(java.util.List<TestraySubtask> testraySubtasks);

	/**
	 * Creates a new testray subtask with the primary key. Does not add the testray subtask to the database.
	 *
	 * @param testraySubtaskId the primary key for the new testray subtask
	 * @return the new testray subtask
	 */
	public TestraySubtask create(long testraySubtaskId);

	/**
	 * Removes the testray subtask with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask that was removed
	 * @throws NoSuchTestraySubtaskException if a testray subtask with the primary key could not be found
	 */
	public TestraySubtask remove(long testraySubtaskId)
		throws NoSuchTestraySubtaskException;

	public TestraySubtask updateImpl(TestraySubtask testraySubtask);

	/**
	 * Returns the testray subtask with the primary key or throws a <code>NoSuchTestraySubtaskException</code> if it could not be found.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask
	 * @throws NoSuchTestraySubtaskException if a testray subtask with the primary key could not be found
	 */
	public TestraySubtask findByPrimaryKey(long testraySubtaskId)
		throws NoSuchTestraySubtaskException;

	/**
	 * Returns the testray subtask with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask, or <code>null</code> if a testray subtask with the primary key could not be found
	 */
	public TestraySubtask fetchByPrimaryKey(long testraySubtaskId);

	/**
	 * Returns all the testray subtasks.
	 *
	 * @return the testray subtasks
	 */
	public java.util.List<TestraySubtask> findAll();

	/**
	 * Returns a range of all the testray subtasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @return the range of testray subtasks
	 */
	public java.util.List<TestraySubtask> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the testray subtasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray subtasks
	 */
	public java.util.List<TestraySubtask> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestraySubtask>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray subtasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray subtasks
	 */
	public java.util.List<TestraySubtask> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestraySubtask>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the testray subtasks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray subtasks.
	 *
	 * @return the number of testray subtasks
	 */
	public int countAll();

	/**
	 * Returns the primaryKeys of testray case results associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return long[] of the primaryKeys of testray case results associated with the testray subtask
	 */
	public long[] getTestrayCaseResultPrimaryKeys(long pk);

	/**
	 * Returns all the testray case results associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return the testray case results associated with the testray subtask
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(long pk);

	/**
	 * Returns a range of all the testray case results associated with the testray subtask.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray subtask
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @return the range of testray case results associated with the testray subtask
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(long pk, int start, int end);

	/**
	 * Returns an ordered range of all the testray case results associated with the testray subtask.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray subtask
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray case results associated with the testray subtask
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCaseResult>
					orderByComparator);

	/**
	 * Returns the number of testray case results associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return the number of testray case results associated with the testray subtask
	 */
	public int getTestrayCaseResultsSize(long pk);

	/**
	 * Returns <code>true</code> if the testray case result is associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPK the primary key of the testray case result
	 * @return <code>true</code> if the testray case result is associated with the testray subtask; <code>false</code> otherwise
	 */
	public boolean containsTestrayCaseResult(long pk, long testrayCaseResultPK);

	/**
	 * Returns <code>true</code> if the testray subtask has any testray case results associated with it.
	 *
	 * @param pk the primary key of the testray subtask to check for associations with testray case results
	 * @return <code>true</code> if the testray subtask has any testray case results associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayCaseResults(long pk);

	/**
	 * Adds an association between the testray subtask and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPK the primary key of the testray case result
	 */
	public void addTestrayCaseResult(long pk, long testrayCaseResultPK);

	/**
	 * Adds an association between the testray subtask and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResult the testray case result
	 */
	public void addTestrayCaseResult(
		long pk,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult);

	/**
	 * Adds an association between the testray subtask and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPKs the primary keys of the testray case results
	 */
	public void addTestrayCaseResults(long pk, long[] testrayCaseResultPKs);

	/**
	 * Adds an association between the testray subtask and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResults the testray case results
	 */
	public void addTestrayCaseResults(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults);

	/**
	 * Clears all associations between the testray subtask and its testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask to clear the associated testray case results from
	 */
	public void clearTestrayCaseResults(long pk);

	/**
	 * Removes the association between the testray subtask and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPK the primary key of the testray case result
	 */
	public void removeTestrayCaseResult(long pk, long testrayCaseResultPK);

	/**
	 * Removes the association between the testray subtask and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResult the testray case result
	 */
	public void removeTestrayCaseResult(
		long pk,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult);

	/**
	 * Removes the association between the testray subtask and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPKs the primary keys of the testray case results
	 */
	public void removeTestrayCaseResults(long pk, long[] testrayCaseResultPKs);

	/**
	 * Removes the association between the testray subtask and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResults the testray case results
	 */
	public void removeTestrayCaseResults(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults);

	/**
	 * Sets the testray case results associated with the testray subtask, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPKs the primary keys of the testray case results to be associated with the testray subtask
	 */
	public void setTestrayCaseResults(long pk, long[] testrayCaseResultPKs);

	/**
	 * Sets the testray case results associated with the testray subtask, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResults the testray case results to be associated with the testray subtask
	 */
	public void setTestrayCaseResults(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults);

	/**
	 * Returns the primaryKeys of testray issues associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return long[] of the primaryKeys of testray issues associated with the testray subtask
	 */
	public long[] getTestrayIssuePrimaryKeys(long pk);

	/**
	 * Returns all the testray issues associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return the testray issues associated with the testray subtask
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(long pk);

	/**
	 * Returns a range of all the testray issues associated with the testray subtask.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray subtask
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @return the range of testray issues associated with the testray subtask
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(long pk, int start, int end);

	/**
	 * Returns an ordered range of all the testray issues associated with the testray subtask.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray subtask
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray issues associated with the testray subtask
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayIssue> orderByComparator);

	/**
	 * Returns the number of testray issues associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return the number of testray issues associated with the testray subtask
	 */
	public int getTestrayIssuesSize(long pk);

	/**
	 * Returns <code>true</code> if the testray issue is associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePK the primary key of the testray issue
	 * @return <code>true</code> if the testray issue is associated with the testray subtask; <code>false</code> otherwise
	 */
	public boolean containsTestrayIssue(long pk, long testrayIssuePK);

	/**
	 * Returns <code>true</code> if the testray subtask has any testray issues associated with it.
	 *
	 * @param pk the primary key of the testray subtask to check for associations with testray issues
	 * @return <code>true</code> if the testray subtask has any testray issues associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayIssues(long pk);

	/**
	 * Adds an association between the testray subtask and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePK the primary key of the testray issue
	 */
	public void addTestrayIssue(long pk, long testrayIssuePK);

	/**
	 * Adds an association between the testray subtask and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssue the testray issue
	 */
	public void addTestrayIssue(
		long pk, com.liferay.osb.testray.model.TestrayIssue testrayIssue);

	/**
	 * Adds an association between the testray subtask and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePKs the primary keys of the testray issues
	 */
	public void addTestrayIssues(long pk, long[] testrayIssuePKs);

	/**
	 * Adds an association between the testray subtask and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssues the testray issues
	 */
	public void addTestrayIssues(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues);

	/**
	 * Clears all associations between the testray subtask and its testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask to clear the associated testray issues from
	 */
	public void clearTestrayIssues(long pk);

	/**
	 * Removes the association between the testray subtask and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePK the primary key of the testray issue
	 */
	public void removeTestrayIssue(long pk, long testrayIssuePK);

	/**
	 * Removes the association between the testray subtask and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssue the testray issue
	 */
	public void removeTestrayIssue(
		long pk, com.liferay.osb.testray.model.TestrayIssue testrayIssue);

	/**
	 * Removes the association between the testray subtask and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePKs the primary keys of the testray issues
	 */
	public void removeTestrayIssues(long pk, long[] testrayIssuePKs);

	/**
	 * Removes the association between the testray subtask and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssues the testray issues
	 */
	public void removeTestrayIssues(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues);

	/**
	 * Sets the testray issues associated with the testray subtask, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePKs the primary keys of the testray issues to be associated with the testray subtask
	 */
	public void setTestrayIssues(long pk, long[] testrayIssuePKs);

	/**
	 * Sets the testray issues associated with the testray subtask, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssues the testray issues to be associated with the testray subtask
	 */
	public void setTestrayIssues(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues);

}
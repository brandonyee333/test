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

import com.liferay.osb.testray.model.TestrayIssue;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the testray issue service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayIssuePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayIssuePersistence
 * @generated
 */
public class TestrayIssueUtil {

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
	public static void clearCache(TestrayIssue testrayIssue) {
		getPersistence().clearCache(testrayIssue);
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
	public static Map<Serializable, TestrayIssue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayIssue> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayIssue update(TestrayIssue testrayIssue) {
		return getPersistence().update(testrayIssue);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayIssue update(
		TestrayIssue testrayIssue, ServiceContext serviceContext) {

		return getPersistence().update(testrayIssue, serviceContext);
	}

	/**
	 * Returns the testray issue where name = &#63; or throws a <code>NoSuchTestrayIssueException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching testray issue
	 * @throws NoSuchTestrayIssueException if a matching testray issue could not be found
	 */
	public static TestrayIssue findByName(String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayIssueException {

		return getPersistence().findByName(name);
	}

	/**
	 * Returns the testray issue where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching testray issue, or <code>null</code> if a matching testray issue could not be found
	 */
	public static TestrayIssue fetchByName(String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	 * Returns the testray issue where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching testray issue, or <code>null</code> if a matching testray issue could not be found
	 */
	public static TestrayIssue fetchByName(
		String name, boolean useFinderCache) {

		return getPersistence().fetchByName(name, useFinderCache);
	}

	/**
	 * Removes the testray issue where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the testray issue that was removed
	 */
	public static TestrayIssue removeByName(String name)
		throws com.liferay.osb.testray.exception.NoSuchTestrayIssueException {

		return getPersistence().removeByName(name);
	}

	/**
	 * Returns the number of testray issues where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching testray issues
	 */
	public static int countByName(String name) {
		return getPersistence().countByName(name);
	}

	/**
	 * Caches the testray issue in the entity cache if it is enabled.
	 *
	 * @param testrayIssue the testray issue
	 */
	public static void cacheResult(TestrayIssue testrayIssue) {
		getPersistence().cacheResult(testrayIssue);
	}

	/**
	 * Caches the testray issues in the entity cache if it is enabled.
	 *
	 * @param testrayIssues the testray issues
	 */
	public static void cacheResult(List<TestrayIssue> testrayIssues) {
		getPersistence().cacheResult(testrayIssues);
	}

	/**
	 * Creates a new testray issue with the primary key. Does not add the testray issue to the database.
	 *
	 * @param testrayIssueId the primary key for the new testray issue
	 * @return the new testray issue
	 */
	public static TestrayIssue create(long testrayIssueId) {
		return getPersistence().create(testrayIssueId);
	}

	/**
	 * Removes the testray issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayIssueId the primary key of the testray issue
	 * @return the testray issue that was removed
	 * @throws NoSuchTestrayIssueException if a testray issue with the primary key could not be found
	 */
	public static TestrayIssue remove(long testrayIssueId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayIssueException {

		return getPersistence().remove(testrayIssueId);
	}

	public static TestrayIssue updateImpl(TestrayIssue testrayIssue) {
		return getPersistence().updateImpl(testrayIssue);
	}

	/**
	 * Returns the testray issue with the primary key or throws a <code>NoSuchTestrayIssueException</code> if it could not be found.
	 *
	 * @param testrayIssueId the primary key of the testray issue
	 * @return the testray issue
	 * @throws NoSuchTestrayIssueException if a testray issue with the primary key could not be found
	 */
	public static TestrayIssue findByPrimaryKey(long testrayIssueId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayIssueException {

		return getPersistence().findByPrimaryKey(testrayIssueId);
	}

	/**
	 * Returns the testray issue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayIssueId the primary key of the testray issue
	 * @return the testray issue, or <code>null</code> if a testray issue with the primary key could not be found
	 */
	public static TestrayIssue fetchByPrimaryKey(long testrayIssueId) {
		return getPersistence().fetchByPrimaryKey(testrayIssueId);
	}

	/**
	 * Returns all the testray issues.
	 *
	 * @return the testray issues
	 */
	public static List<TestrayIssue> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<TestrayIssue> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<TestrayIssue> findAll(
		int start, int end, OrderByComparator<TestrayIssue> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<TestrayIssue> findAll(
		int start, int end, OrderByComparator<TestrayIssue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the testray issues from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray issues.
	 *
	 * @return the number of testray issues
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of testray case results associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return long[] of the primaryKeys of testray case results associated with the testray issue
	 */
	public static long[] getTestrayCaseResultPrimaryKeys(long pk) {
		return getPersistence().getTestrayCaseResultPrimaryKeys(pk);
	}

	/**
	 * Returns all the testray case results associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return the testray case results associated with the testray issue
	 */
	public static List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(long pk) {

		return getPersistence().getTestrayCaseResults(pk);
	}

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
	public static List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(long pk, int start, int end) {

		return getPersistence().getTestrayCaseResults(pk, start, end);
	}

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
	public static List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestrayCaseResult>
				orderByComparator) {

		return getPersistence().getTestrayCaseResults(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray case results associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return the number of testray case results associated with the testray issue
	 */
	public static int getTestrayCaseResultsSize(long pk) {
		return getPersistence().getTestrayCaseResultsSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray case result is associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPK the primary key of the testray case result
	 * @return <code>true</code> if the testray case result is associated with the testray issue; <code>false</code> otherwise
	 */
	public static boolean containsTestrayCaseResult(
		long pk, long testrayCaseResultPK) {

		return getPersistence().containsTestrayCaseResult(
			pk, testrayCaseResultPK);
	}

	/**
	 * Returns <code>true</code> if the testray issue has any testray case results associated with it.
	 *
	 * @param pk the primary key of the testray issue to check for associations with testray case results
	 * @return <code>true</code> if the testray issue has any testray case results associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestrayCaseResults(long pk) {
		return getPersistence().containsTestrayCaseResults(pk);
	}

	/**
	 * Adds an association between the testray issue and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPK the primary key of the testray case result
	 */
	public static void addTestrayCaseResult(long pk, long testrayCaseResultPK) {
		getPersistence().addTestrayCaseResult(pk, testrayCaseResultPK);
	}

	/**
	 * Adds an association between the testray issue and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResult the testray case result
	 */
	public static void addTestrayCaseResult(
		long pk,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		getPersistence().addTestrayCaseResult(pk, testrayCaseResult);
	}

	/**
	 * Adds an association between the testray issue and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPKs the primary keys of the testray case results
	 */
	public static void addTestrayCaseResults(
		long pk, long[] testrayCaseResultPKs) {

		getPersistence().addTestrayCaseResults(pk, testrayCaseResultPKs);
	}

	/**
	 * Adds an association between the testray issue and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResults the testray case results
	 */
	public static void addTestrayCaseResults(
		long pk,
		List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		getPersistence().addTestrayCaseResults(pk, testrayCaseResults);
	}

	/**
	 * Clears all associations between the testray issue and its testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue to clear the associated testray case results from
	 */
	public static void clearTestrayCaseResults(long pk) {
		getPersistence().clearTestrayCaseResults(pk);
	}

	/**
	 * Removes the association between the testray issue and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPK the primary key of the testray case result
	 */
	public static void removeTestrayCaseResult(
		long pk, long testrayCaseResultPK) {

		getPersistence().removeTestrayCaseResult(pk, testrayCaseResultPK);
	}

	/**
	 * Removes the association between the testray issue and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResult the testray case result
	 */
	public static void removeTestrayCaseResult(
		long pk,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		getPersistence().removeTestrayCaseResult(pk, testrayCaseResult);
	}

	/**
	 * Removes the association between the testray issue and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPKs the primary keys of the testray case results
	 */
	public static void removeTestrayCaseResults(
		long pk, long[] testrayCaseResultPKs) {

		getPersistence().removeTestrayCaseResults(pk, testrayCaseResultPKs);
	}

	/**
	 * Removes the association between the testray issue and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResults the testray case results
	 */
	public static void removeTestrayCaseResults(
		long pk,
		List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		getPersistence().removeTestrayCaseResults(pk, testrayCaseResults);
	}

	/**
	 * Sets the testray case results associated with the testray issue, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResultPKs the primary keys of the testray case results to be associated with the testray issue
	 */
	public static void setTestrayCaseResults(
		long pk, long[] testrayCaseResultPKs) {

		getPersistence().setTestrayCaseResults(pk, testrayCaseResultPKs);
	}

	/**
	 * Sets the testray case results associated with the testray issue, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testrayCaseResults the testray case results to be associated with the testray issue
	 */
	public static void setTestrayCaseResults(
		long pk,
		List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		getPersistence().setTestrayCaseResults(pk, testrayCaseResults);
	}

	/**
	 * Returns the primaryKeys of testray subtasks associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return long[] of the primaryKeys of testray subtasks associated with the testray issue
	 */
	public static long[] getTestraySubtaskPrimaryKeys(long pk) {
		return getPersistence().getTestraySubtaskPrimaryKeys(pk);
	}

	/**
	 * Returns all the testray subtasks associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return the testray subtasks associated with the testray issue
	 */
	public static List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(long pk) {

		return getPersistence().getTestraySubtasks(pk);
	}

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
	public static List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(long pk, int start, int end) {

		return getPersistence().getTestraySubtasks(pk, start, end);
	}

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
	public static List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestraySubtask>
				orderByComparator) {

		return getPersistence().getTestraySubtasks(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray subtasks associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @return the number of testray subtasks associated with the testray issue
	 */
	public static int getTestraySubtasksSize(long pk) {
		return getPersistence().getTestraySubtasksSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray subtask is associated with the testray issue.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPK the primary key of the testray subtask
	 * @return <code>true</code> if the testray subtask is associated with the testray issue; <code>false</code> otherwise
	 */
	public static boolean containsTestraySubtask(
		long pk, long testraySubtaskPK) {

		return getPersistence().containsTestraySubtask(pk, testraySubtaskPK);
	}

	/**
	 * Returns <code>true</code> if the testray issue has any testray subtasks associated with it.
	 *
	 * @param pk the primary key of the testray issue to check for associations with testray subtasks
	 * @return <code>true</code> if the testray issue has any testray subtasks associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestraySubtasks(long pk) {
		return getPersistence().containsTestraySubtasks(pk);
	}

	/**
	 * Adds an association between the testray issue and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPK the primary key of the testray subtask
	 */
	public static void addTestraySubtask(long pk, long testraySubtaskPK) {
		getPersistence().addTestraySubtask(pk, testraySubtaskPK);
	}

	/**
	 * Adds an association between the testray issue and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtask the testray subtask
	 */
	public static void addTestraySubtask(
		long pk, com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		getPersistence().addTestraySubtask(pk, testraySubtask);
	}

	/**
	 * Adds an association between the testray issue and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPKs the primary keys of the testray subtasks
	 */
	public static void addTestraySubtasks(long pk, long[] testraySubtaskPKs) {
		getPersistence().addTestraySubtasks(pk, testraySubtaskPKs);
	}

	/**
	 * Adds an association between the testray issue and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtasks the testray subtasks
	 */
	public static void addTestraySubtasks(
		long pk,
		List<com.liferay.osb.testray.model.TestraySubtask> testraySubtasks) {

		getPersistence().addTestraySubtasks(pk, testraySubtasks);
	}

	/**
	 * Clears all associations between the testray issue and its testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue to clear the associated testray subtasks from
	 */
	public static void clearTestraySubtasks(long pk) {
		getPersistence().clearTestraySubtasks(pk);
	}

	/**
	 * Removes the association between the testray issue and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPK the primary key of the testray subtask
	 */
	public static void removeTestraySubtask(long pk, long testraySubtaskPK) {
		getPersistence().removeTestraySubtask(pk, testraySubtaskPK);
	}

	/**
	 * Removes the association between the testray issue and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtask the testray subtask
	 */
	public static void removeTestraySubtask(
		long pk, com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		getPersistence().removeTestraySubtask(pk, testraySubtask);
	}

	/**
	 * Removes the association between the testray issue and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPKs the primary keys of the testray subtasks
	 */
	public static void removeTestraySubtasks(
		long pk, long[] testraySubtaskPKs) {

		getPersistence().removeTestraySubtasks(pk, testraySubtaskPKs);
	}

	/**
	 * Removes the association between the testray issue and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtasks the testray subtasks
	 */
	public static void removeTestraySubtasks(
		long pk,
		List<com.liferay.osb.testray.model.TestraySubtask> testraySubtasks) {

		getPersistence().removeTestraySubtasks(pk, testraySubtasks);
	}

	/**
	 * Sets the testray subtasks associated with the testray issue, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtaskPKs the primary keys of the testray subtasks to be associated with the testray issue
	 */
	public static void setTestraySubtasks(long pk, long[] testraySubtaskPKs) {
		getPersistence().setTestraySubtasks(pk, testraySubtaskPKs);
	}

	/**
	 * Sets the testray subtasks associated with the testray issue, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray issue
	 * @param testraySubtasks the testray subtasks to be associated with the testray issue
	 */
	public static void setTestraySubtasks(
		long pk,
		List<com.liferay.osb.testray.model.TestraySubtask> testraySubtasks) {

		getPersistence().setTestraySubtasks(pk, testraySubtasks);
	}

	public static TestrayIssuePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(TestrayIssuePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile TestrayIssuePersistence _persistence;

}
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

import com.liferay.osb.testray.model.TestraySubtask;
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
 * The persistence utility for the testray subtask service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestraySubtaskPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestraySubtaskPersistence
 * @generated
 */
@ProviderType
public class TestraySubtaskUtil {

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
	public static void clearCache(TestraySubtask testraySubtask) {
		getPersistence().clearCache(testraySubtask);
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
	public static Map<Serializable, TestraySubtask> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestraySubtask> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestraySubtask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestraySubtask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestraySubtask> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestraySubtask update(TestraySubtask testraySubtask) {
		return getPersistence().update(testraySubtask);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestraySubtask update(
		TestraySubtask testraySubtask, ServiceContext serviceContext) {

		return getPersistence().update(testraySubtask, serviceContext);
	}

	/**
	 * Caches the testray subtask in the entity cache if it is enabled.
	 *
	 * @param testraySubtask the testray subtask
	 */
	public static void cacheResult(TestraySubtask testraySubtask) {
		getPersistence().cacheResult(testraySubtask);
	}

	/**
	 * Caches the testray subtasks in the entity cache if it is enabled.
	 *
	 * @param testraySubtasks the testray subtasks
	 */
	public static void cacheResult(List<TestraySubtask> testraySubtasks) {
		getPersistence().cacheResult(testraySubtasks);
	}

	/**
	 * Creates a new testray subtask with the primary key. Does not add the testray subtask to the database.
	 *
	 * @param testraySubtaskId the primary key for the new testray subtask
	 * @return the new testray subtask
	 */
	public static TestraySubtask create(long testraySubtaskId) {
		return getPersistence().create(testraySubtaskId);
	}

	/**
	 * Removes the testray subtask with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask that was removed
	 * @throws NoSuchTestraySubtaskException if a testray subtask with the primary key could not be found
	 */
	public static TestraySubtask remove(long testraySubtaskId)
		throws com.liferay.osb.testray.exception.NoSuchTestraySubtaskException {

		return getPersistence().remove(testraySubtaskId);
	}

	public static TestraySubtask updateImpl(TestraySubtask testraySubtask) {
		return getPersistence().updateImpl(testraySubtask);
	}

	/**
	 * Returns the testray subtask with the primary key or throws a <code>NoSuchTestraySubtaskException</code> if it could not be found.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask
	 * @throws NoSuchTestraySubtaskException if a testray subtask with the primary key could not be found
	 */
	public static TestraySubtask findByPrimaryKey(long testraySubtaskId)
		throws com.liferay.osb.testray.exception.NoSuchTestraySubtaskException {

		return getPersistence().findByPrimaryKey(testraySubtaskId);
	}

	/**
	 * Returns the testray subtask with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask, or <code>null</code> if a testray subtask with the primary key could not be found
	 */
	public static TestraySubtask fetchByPrimaryKey(long testraySubtaskId) {
		return getPersistence().fetchByPrimaryKey(testraySubtaskId);
	}

	/**
	 * Returns all the testray subtasks.
	 *
	 * @return the testray subtasks
	 */
	public static List<TestraySubtask> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray subtasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @return the range of testray subtasks
	 */
	public static List<TestraySubtask> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray subtasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray subtasks
	 */
	public static List<TestraySubtask> findAll(
		int start, int end,
		OrderByComparator<TestraySubtask> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray subtasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray subtasks
	 */
	public static List<TestraySubtask> findAll(
		int start, int end, OrderByComparator<TestraySubtask> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the testray subtasks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray subtasks.
	 *
	 * @return the number of testray subtasks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of testray case results associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return long[] of the primaryKeys of testray case results associated with the testray subtask
	 */
	public static long[] getTestrayCaseResultPrimaryKeys(long pk) {
		return getPersistence().getTestrayCaseResultPrimaryKeys(pk);
	}

	/**
	 * Returns all the testray case results associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return the testray case results associated with the testray subtask
	 */
	public static List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(long pk) {

		return getPersistence().getTestrayCaseResults(pk);
	}

	/**
	 * Returns a range of all the testray case results associated with the testray subtask.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray subtask
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @return the range of testray case results associated with the testray subtask
	 */
	public static List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(long pk, int start, int end) {

		return getPersistence().getTestrayCaseResults(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the testray case results associated with the testray subtask.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray subtask
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray case results associated with the testray subtask
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
	 * Returns the number of testray case results associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return the number of testray case results associated with the testray subtask
	 */
	public static int getTestrayCaseResultsSize(long pk) {
		return getPersistence().getTestrayCaseResultsSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray case result is associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPK the primary key of the testray case result
	 * @return <code>true</code> if the testray case result is associated with the testray subtask; <code>false</code> otherwise
	 */
	public static boolean containsTestrayCaseResult(
		long pk, long testrayCaseResultPK) {

		return getPersistence().containsTestrayCaseResult(
			pk, testrayCaseResultPK);
	}

	/**
	 * Returns <code>true</code> if the testray subtask has any testray case results associated with it.
	 *
	 * @param pk the primary key of the testray subtask to check for associations with testray case results
	 * @return <code>true</code> if the testray subtask has any testray case results associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestrayCaseResults(long pk) {
		return getPersistence().containsTestrayCaseResults(pk);
	}

	/**
	 * Adds an association between the testray subtask and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPK the primary key of the testray case result
	 */
	public static void addTestrayCaseResult(long pk, long testrayCaseResultPK) {
		getPersistence().addTestrayCaseResult(pk, testrayCaseResultPK);
	}

	/**
	 * Adds an association between the testray subtask and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResult the testray case result
	 */
	public static void addTestrayCaseResult(
		long pk,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		getPersistence().addTestrayCaseResult(pk, testrayCaseResult);
	}

	/**
	 * Adds an association between the testray subtask and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPKs the primary keys of the testray case results
	 */
	public static void addTestrayCaseResults(
		long pk, long[] testrayCaseResultPKs) {

		getPersistence().addTestrayCaseResults(pk, testrayCaseResultPKs);
	}

	/**
	 * Adds an association between the testray subtask and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResults the testray case results
	 */
	public static void addTestrayCaseResults(
		long pk,
		List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		getPersistence().addTestrayCaseResults(pk, testrayCaseResults);
	}

	/**
	 * Clears all associations between the testray subtask and its testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask to clear the associated testray case results from
	 */
	public static void clearTestrayCaseResults(long pk) {
		getPersistence().clearTestrayCaseResults(pk);
	}

	/**
	 * Removes the association between the testray subtask and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPK the primary key of the testray case result
	 */
	public static void removeTestrayCaseResult(
		long pk, long testrayCaseResultPK) {

		getPersistence().removeTestrayCaseResult(pk, testrayCaseResultPK);
	}

	/**
	 * Removes the association between the testray subtask and the testray case result. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResult the testray case result
	 */
	public static void removeTestrayCaseResult(
		long pk,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		getPersistence().removeTestrayCaseResult(pk, testrayCaseResult);
	}

	/**
	 * Removes the association between the testray subtask and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPKs the primary keys of the testray case results
	 */
	public static void removeTestrayCaseResults(
		long pk, long[] testrayCaseResultPKs) {

		getPersistence().removeTestrayCaseResults(pk, testrayCaseResultPKs);
	}

	/**
	 * Removes the association between the testray subtask and the testray case results. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResults the testray case results
	 */
	public static void removeTestrayCaseResults(
		long pk,
		List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		getPersistence().removeTestrayCaseResults(pk, testrayCaseResults);
	}

	/**
	 * Sets the testray case results associated with the testray subtask, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResultPKs the primary keys of the testray case results to be associated with the testray subtask
	 */
	public static void setTestrayCaseResults(
		long pk, long[] testrayCaseResultPKs) {

		getPersistence().setTestrayCaseResults(pk, testrayCaseResultPKs);
	}

	/**
	 * Sets the testray case results associated with the testray subtask, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayCaseResults the testray case results to be associated with the testray subtask
	 */
	public static void setTestrayCaseResults(
		long pk,
		List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		getPersistence().setTestrayCaseResults(pk, testrayCaseResults);
	}

	/**
	 * Returns the primaryKeys of testray issues associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return long[] of the primaryKeys of testray issues associated with the testray subtask
	 */
	public static long[] getTestrayIssuePrimaryKeys(long pk) {
		return getPersistence().getTestrayIssuePrimaryKeys(pk);
	}

	/**
	 * Returns all the testray issues associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return the testray issues associated with the testray subtask
	 */
	public static List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(long pk) {

		return getPersistence().getTestrayIssues(pk);
	}

	/**
	 * Returns a range of all the testray issues associated with the testray subtask.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray subtask
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @return the range of testray issues associated with the testray subtask
	 */
	public static List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(long pk, int start, int end) {

		return getPersistence().getTestrayIssues(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the testray issues associated with the testray subtask.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray subtask
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray issues associated with the testray subtask
	 */
	public static List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestrayIssue>
				orderByComparator) {

		return getPersistence().getTestrayIssues(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray issues associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @return the number of testray issues associated with the testray subtask
	 */
	public static int getTestrayIssuesSize(long pk) {
		return getPersistence().getTestrayIssuesSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray issue is associated with the testray subtask.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePK the primary key of the testray issue
	 * @return <code>true</code> if the testray issue is associated with the testray subtask; <code>false</code> otherwise
	 */
	public static boolean containsTestrayIssue(long pk, long testrayIssuePK) {
		return getPersistence().containsTestrayIssue(pk, testrayIssuePK);
	}

	/**
	 * Returns <code>true</code> if the testray subtask has any testray issues associated with it.
	 *
	 * @param pk the primary key of the testray subtask to check for associations with testray issues
	 * @return <code>true</code> if the testray subtask has any testray issues associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestrayIssues(long pk) {
		return getPersistence().containsTestrayIssues(pk);
	}

	/**
	 * Adds an association between the testray subtask and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePK the primary key of the testray issue
	 */
	public static void addTestrayIssue(long pk, long testrayIssuePK) {
		getPersistence().addTestrayIssue(pk, testrayIssuePK);
	}

	/**
	 * Adds an association between the testray subtask and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssue the testray issue
	 */
	public static void addTestrayIssue(
		long pk, com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		getPersistence().addTestrayIssue(pk, testrayIssue);
	}

	/**
	 * Adds an association between the testray subtask and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePKs the primary keys of the testray issues
	 */
	public static void addTestrayIssues(long pk, long[] testrayIssuePKs) {
		getPersistence().addTestrayIssues(pk, testrayIssuePKs);
	}

	/**
	 * Adds an association between the testray subtask and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssues the testray issues
	 */
	public static void addTestrayIssues(
		long pk,
		List<com.liferay.osb.testray.model.TestrayIssue> testrayIssues) {

		getPersistence().addTestrayIssues(pk, testrayIssues);
	}

	/**
	 * Clears all associations between the testray subtask and its testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask to clear the associated testray issues from
	 */
	public static void clearTestrayIssues(long pk) {
		getPersistence().clearTestrayIssues(pk);
	}

	/**
	 * Removes the association between the testray subtask and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePK the primary key of the testray issue
	 */
	public static void removeTestrayIssue(long pk, long testrayIssuePK) {
		getPersistence().removeTestrayIssue(pk, testrayIssuePK);
	}

	/**
	 * Removes the association between the testray subtask and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssue the testray issue
	 */
	public static void removeTestrayIssue(
		long pk, com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		getPersistence().removeTestrayIssue(pk, testrayIssue);
	}

	/**
	 * Removes the association between the testray subtask and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePKs the primary keys of the testray issues
	 */
	public static void removeTestrayIssues(long pk, long[] testrayIssuePKs) {
		getPersistence().removeTestrayIssues(pk, testrayIssuePKs);
	}

	/**
	 * Removes the association between the testray subtask and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssues the testray issues
	 */
	public static void removeTestrayIssues(
		long pk,
		List<com.liferay.osb.testray.model.TestrayIssue> testrayIssues) {

		getPersistence().removeTestrayIssues(pk, testrayIssues);
	}

	/**
	 * Sets the testray issues associated with the testray subtask, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssuePKs the primary keys of the testray issues to be associated with the testray subtask
	 */
	public static void setTestrayIssues(long pk, long[] testrayIssuePKs) {
		getPersistence().setTestrayIssues(pk, testrayIssuePKs);
	}

	/**
	 * Sets the testray issues associated with the testray subtask, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray subtask
	 * @param testrayIssues the testray issues to be associated with the testray subtask
	 */
	public static void setTestrayIssues(
		long pk,
		List<com.liferay.osb.testray.model.TestrayIssue> testrayIssues) {

		getPersistence().setTestrayIssues(pk, testrayIssues);
	}

	public static TestraySubtaskPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestraySubtaskPersistence, TestraySubtaskPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestraySubtaskPersistence.class);

		ServiceTracker<TestraySubtaskPersistence, TestraySubtaskPersistence>
			serviceTracker =
				new ServiceTracker
					<TestraySubtaskPersistence, TestraySubtaskPersistence>(
						bundle.getBundleContext(),
						TestraySubtaskPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
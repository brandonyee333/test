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

import com.liferay.osb.testray.model.TestrayRun;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the testray run service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayRunPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayRunPersistence
 * @generated
 */
public class TestrayRunUtil {

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
	public static void clearCache(TestrayRun testrayRun) {
		getPersistence().clearCache(testrayRun);
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
	public static Map<Serializable, TestrayRun> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayRun> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayRun> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayRun> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayRun> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayRun update(TestrayRun testrayRun) {
		return getPersistence().update(testrayRun);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayRun update(
		TestrayRun testrayRun, ServiceContext serviceContext) {

		return getPersistence().update(testrayRun, serviceContext);
	}

	/**
	 * Returns the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; or throws a <code>NoSuchTestrayRunException</code> if it could not be found.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param externalReferencePK the external reference pk
	 * @param externalReferenceType the external reference type
	 * @return the matching testray run
	 * @throws NoSuchTestrayRunException if a matching testray run could not be found
	 */
	public static TestrayRun findByT_E_E(
			long testrayBuildId, String externalReferencePK,
			int externalReferenceType)
		throws com.liferay.osb.testray.exception.NoSuchTestrayRunException {

		return getPersistence().findByT_E_E(
			testrayBuildId, externalReferencePK, externalReferenceType);
	}

	/**
	 * Returns the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param externalReferencePK the external reference pk
	 * @param externalReferenceType the external reference type
	 * @return the matching testray run, or <code>null</code> if a matching testray run could not be found
	 */
	public static TestrayRun fetchByT_E_E(
		long testrayBuildId, String externalReferencePK,
		int externalReferenceType) {

		return getPersistence().fetchByT_E_E(
			testrayBuildId, externalReferencePK, externalReferenceType);
	}

	/**
	 * Returns the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param externalReferencePK the external reference pk
	 * @param externalReferenceType the external reference type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching testray run, or <code>null</code> if a matching testray run could not be found
	 */
	public static TestrayRun fetchByT_E_E(
		long testrayBuildId, String externalReferencePK,
		int externalReferenceType, boolean useFinderCache) {

		return getPersistence().fetchByT_E_E(
			testrayBuildId, externalReferencePK, externalReferenceType,
			useFinderCache);
	}

	/**
	 * Removes the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; from the database.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param externalReferencePK the external reference pk
	 * @param externalReferenceType the external reference type
	 * @return the testray run that was removed
	 */
	public static TestrayRun removeByT_E_E(
			long testrayBuildId, String externalReferencePK,
			int externalReferenceType)
		throws com.liferay.osb.testray.exception.NoSuchTestrayRunException {

		return getPersistence().removeByT_E_E(
			testrayBuildId, externalReferencePK, externalReferenceType);
	}

	/**
	 * Returns the number of testray runs where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param externalReferencePK the external reference pk
	 * @param externalReferenceType the external reference type
	 * @return the number of matching testray runs
	 */
	public static int countByT_E_E(
		long testrayBuildId, String externalReferencePK,
		int externalReferenceType) {

		return getPersistence().countByT_E_E(
			testrayBuildId, externalReferencePK, externalReferenceType);
	}

	/**
	 * Caches the testray run in the entity cache if it is enabled.
	 *
	 * @param testrayRun the testray run
	 */
	public static void cacheResult(TestrayRun testrayRun) {
		getPersistence().cacheResult(testrayRun);
	}

	/**
	 * Caches the testray runs in the entity cache if it is enabled.
	 *
	 * @param testrayRuns the testray runs
	 */
	public static void cacheResult(List<TestrayRun> testrayRuns) {
		getPersistence().cacheResult(testrayRuns);
	}

	/**
	 * Creates a new testray run with the primary key. Does not add the testray run to the database.
	 *
	 * @param testrayRunId the primary key for the new testray run
	 * @return the new testray run
	 */
	public static TestrayRun create(long testrayRunId) {
		return getPersistence().create(testrayRunId);
	}

	/**
	 * Removes the testray run with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRunId the primary key of the testray run
	 * @return the testray run that was removed
	 * @throws NoSuchTestrayRunException if a testray run with the primary key could not be found
	 */
	public static TestrayRun remove(long testrayRunId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayRunException {

		return getPersistence().remove(testrayRunId);
	}

	public static TestrayRun updateImpl(TestrayRun testrayRun) {
		return getPersistence().updateImpl(testrayRun);
	}

	/**
	 * Returns the testray run with the primary key or throws a <code>NoSuchTestrayRunException</code> if it could not be found.
	 *
	 * @param testrayRunId the primary key of the testray run
	 * @return the testray run
	 * @throws NoSuchTestrayRunException if a testray run with the primary key could not be found
	 */
	public static TestrayRun findByPrimaryKey(long testrayRunId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayRunException {

		return getPersistence().findByPrimaryKey(testrayRunId);
	}

	/**
	 * Returns the testray run with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayRunId the primary key of the testray run
	 * @return the testray run, or <code>null</code> if a testray run with the primary key could not be found
	 */
	public static TestrayRun fetchByPrimaryKey(long testrayRunId) {
		return getPersistence().fetchByPrimaryKey(testrayRunId);
	}

	/**
	 * Returns all the testray runs.
	 *
	 * @return the testray runs
	 */
	public static List<TestrayRun> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray runs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayRunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray runs
	 * @param end the upper bound of the range of testray runs (not inclusive)
	 * @return the range of testray runs
	 */
	public static List<TestrayRun> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray runs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayRunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray runs
	 * @param end the upper bound of the range of testray runs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray runs
	 */
	public static List<TestrayRun> findAll(
		int start, int end, OrderByComparator<TestrayRun> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray runs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayRunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray runs
	 * @param end the upper bound of the range of testray runs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray runs
	 */
	public static List<TestrayRun> findAll(
		int start, int end, OrderByComparator<TestrayRun> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the testray runs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray runs.
	 *
	 * @return the number of testray runs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TestrayRunPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(TestrayRunPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile TestrayRunPersistence _persistence;

}
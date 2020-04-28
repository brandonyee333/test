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

import com.liferay.osb.testray.model.TestrayFactor;
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
 * The persistence utility for the testray factor service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayFactorPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayFactorPersistence
 * @generated
 */
public class TestrayFactorUtil {

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
	public static void clearCache(TestrayFactor testrayFactor) {
		getPersistence().clearCache(testrayFactor);
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
	public static Map<Serializable, TestrayFactor> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayFactor> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayFactor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayFactor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayFactor> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayFactor update(TestrayFactor testrayFactor) {
		return getPersistence().update(testrayFactor);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayFactor update(
		TestrayFactor testrayFactor, ServiceContext serviceContext) {

		return getPersistence().update(testrayFactor, serviceContext);
	}

	/**
	 * Returns all the testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching testray factors
	 */
	public static List<TestrayFactor> findByCNI_CP(
		long classNameId, long classPK) {

		return getPersistence().findByCNI_CP(classNameId, classPK);
	}

	/**
	 * Returns a range of all the testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayFactorModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @return the range of matching testray factors
	 */
	public static List<TestrayFactor> findByCNI_CP(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByCNI_CP(classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayFactorModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray factors
	 */
	public static List<TestrayFactor> findByCNI_CP(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<TestrayFactor> orderByComparator) {

		return getPersistence().findByCNI_CP(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayFactorModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching testray factors
	 */
	public static List<TestrayFactor> findByCNI_CP(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<TestrayFactor> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCNI_CP(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray factor
	 * @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	 */
	public static TestrayFactor findByCNI_CP_First(
			long classNameId, long classPK,
			OrderByComparator<TestrayFactor> orderByComparator)
		throws com.liferay.osb.testray.exception.NoSuchTestrayFactorException {

		return getPersistence().findByCNI_CP_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	public static TestrayFactor fetchByCNI_CP_First(
		long classNameId, long classPK,
		OrderByComparator<TestrayFactor> orderByComparator) {

		return getPersistence().fetchByCNI_CP_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray factor
	 * @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	 */
	public static TestrayFactor findByCNI_CP_Last(
			long classNameId, long classPK,
			OrderByComparator<TestrayFactor> orderByComparator)
		throws com.liferay.osb.testray.exception.NoSuchTestrayFactorException {

		return getPersistence().findByCNI_CP_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	public static TestrayFactor fetchByCNI_CP_Last(
		long classNameId, long classPK,
		OrderByComparator<TestrayFactor> orderByComparator) {

		return getPersistence().fetchByCNI_CP_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the testray factors before and after the current testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param testrayFactorId the primary key of the current testray factor
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray factor
	 * @throws NoSuchTestrayFactorException if a testray factor with the primary key could not be found
	 */
	public static TestrayFactor[] findByCNI_CP_PrevAndNext(
			long testrayFactorId, long classNameId, long classPK,
			OrderByComparator<TestrayFactor> orderByComparator)
		throws com.liferay.osb.testray.exception.NoSuchTestrayFactorException {

		return getPersistence().findByCNI_CP_PrevAndNext(
			testrayFactorId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the testray factors where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByCNI_CP(long classNameId, long classPK) {
		getPersistence().removeByCNI_CP(classNameId, classPK);
	}

	/**
	 * Returns the number of testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching testray factors
	 */
	public static int countByCNI_CP(long classNameId, long classPK) {
		return getPersistence().countByCNI_CP(classNameId, classPK);
	}

	/**
	 * Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or throws a <code>NoSuchTestrayFactorException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the matching testray factor
	 * @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	 */
	public static TestrayFactor findByC_C_T(
			long classNameId, long classPK, long testrayFactorOptionId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayFactorException {

		return getPersistence().findByC_C_T(
			classNameId, classPK, testrayFactorOptionId);
	}

	/**
	 * Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	public static TestrayFactor fetchByC_C_T(
		long classNameId, long classPK, long testrayFactorOptionId) {

		return getPersistence().fetchByC_C_T(
			classNameId, classPK, testrayFactorOptionId);
	}

	/**
	 * Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	public static TestrayFactor fetchByC_C_T(
		long classNameId, long classPK, long testrayFactorOptionId,
		boolean useFinderCache) {

		return getPersistence().fetchByC_C_T(
			classNameId, classPK, testrayFactorOptionId, useFinderCache);
	}

	/**
	 * Removes the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the testray factor that was removed
	 */
	public static TestrayFactor removeByC_C_T(
			long classNameId, long classPK, long testrayFactorOptionId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayFactorException {

		return getPersistence().removeByC_C_T(
			classNameId, classPK, testrayFactorOptionId);
	}

	/**
	 * Returns the number of testray factors where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the number of matching testray factors
	 */
	public static int countByC_C_T(
		long classNameId, long classPK, long testrayFactorOptionId) {

		return getPersistence().countByC_C_T(
			classNameId, classPK, testrayFactorOptionId);
	}

	/**
	 * Caches the testray factor in the entity cache if it is enabled.
	 *
	 * @param testrayFactor the testray factor
	 */
	public static void cacheResult(TestrayFactor testrayFactor) {
		getPersistence().cacheResult(testrayFactor);
	}

	/**
	 * Caches the testray factors in the entity cache if it is enabled.
	 *
	 * @param testrayFactors the testray factors
	 */
	public static void cacheResult(List<TestrayFactor> testrayFactors) {
		getPersistence().cacheResult(testrayFactors);
	}

	/**
	 * Creates a new testray factor with the primary key. Does not add the testray factor to the database.
	 *
	 * @param testrayFactorId the primary key for the new testray factor
	 * @return the new testray factor
	 */
	public static TestrayFactor create(long testrayFactorId) {
		return getPersistence().create(testrayFactorId);
	}

	/**
	 * Removes the testray factor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorId the primary key of the testray factor
	 * @return the testray factor that was removed
	 * @throws NoSuchTestrayFactorException if a testray factor with the primary key could not be found
	 */
	public static TestrayFactor remove(long testrayFactorId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayFactorException {

		return getPersistence().remove(testrayFactorId);
	}

	public static TestrayFactor updateImpl(TestrayFactor testrayFactor) {
		return getPersistence().updateImpl(testrayFactor);
	}

	/**
	 * Returns the testray factor with the primary key or throws a <code>NoSuchTestrayFactorException</code> if it could not be found.
	 *
	 * @param testrayFactorId the primary key of the testray factor
	 * @return the testray factor
	 * @throws NoSuchTestrayFactorException if a testray factor with the primary key could not be found
	 */
	public static TestrayFactor findByPrimaryKey(long testrayFactorId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayFactorException {

		return getPersistence().findByPrimaryKey(testrayFactorId);
	}

	/**
	 * Returns the testray factor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayFactorId the primary key of the testray factor
	 * @return the testray factor, or <code>null</code> if a testray factor with the primary key could not be found
	 */
	public static TestrayFactor fetchByPrimaryKey(long testrayFactorId) {
		return getPersistence().fetchByPrimaryKey(testrayFactorId);
	}

	/**
	 * Returns all the testray factors.
	 *
	 * @return the testray factors
	 */
	public static List<TestrayFactor> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray factors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayFactorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @return the range of testray factors
	 */
	public static List<TestrayFactor> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray factors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayFactorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray factors
	 */
	public static List<TestrayFactor> findAll(
		int start, int end,
		OrderByComparator<TestrayFactor> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray factors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayFactorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factors
	 * @param end the upper bound of the range of testray factors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray factors
	 */
	public static List<TestrayFactor> findAll(
		int start, int end, OrderByComparator<TestrayFactor> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the testray factors from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray factors.
	 *
	 * @return the number of testray factors
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayFactorPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayFactorPersistence, TestrayFactorPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TestrayFactorPersistence.class);

		ServiceTracker<TestrayFactorPersistence, TestrayFactorPersistence>
			serviceTracker =
				new ServiceTracker
					<TestrayFactorPersistence, TestrayFactorPersistence>(
						bundle.getBundleContext(),
						TestrayFactorPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
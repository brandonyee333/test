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

import com.liferay.osb.testray.model.TestrayComponent;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the testray component service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayComponentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayComponentPersistence
 * @generated
 */
public class TestrayComponentUtil {

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
	public static void clearCache(TestrayComponent testrayComponent) {
		getPersistence().clearCache(testrayComponent);
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
	public static Map<Serializable, TestrayComponent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayComponent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayComponent> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayComponent update(TestrayComponent testrayComponent) {
		return getPersistence().update(testrayComponent);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayComponent update(
		TestrayComponent testrayComponent, ServiceContext serviceContext) {

		return getPersistence().update(testrayComponent, serviceContext);
	}

	/**
	 * Returns the testray component where testrayProjectId = &#63; and name = &#63; or throws a <code>NoSuchTestrayComponentException</code> if it could not be found.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray component
	 * @throws NoSuchTestrayComponentException if a matching testray component could not be found
	 */
	public static TestrayComponent findByTPI_N(
			long testrayProjectId, String name)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayComponentException {

		return getPersistence().findByTPI_N(testrayProjectId, name);
	}

	/**
	 * Returns the testray component where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray component, or <code>null</code> if a matching testray component could not be found
	 */
	public static TestrayComponent fetchByTPI_N(
		long testrayProjectId, String name) {

		return getPersistence().fetchByTPI_N(testrayProjectId, name);
	}

	/**
	 * Returns the testray component where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching testray component, or <code>null</code> if a matching testray component could not be found
	 */
	public static TestrayComponent fetchByTPI_N(
		long testrayProjectId, String name, boolean useFinderCache) {

		return getPersistence().fetchByTPI_N(
			testrayProjectId, name, useFinderCache);
	}

	/**
	 * Removes the testray component where testrayProjectId = &#63; and name = &#63; from the database.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the testray component that was removed
	 */
	public static TestrayComponent removeByTPI_N(
			long testrayProjectId, String name)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayComponentException {

		return getPersistence().removeByTPI_N(testrayProjectId, name);
	}

	/**
	 * Returns the number of testray components where testrayProjectId = &#63; and name = &#63;.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the number of matching testray components
	 */
	public static int countByTPI_N(long testrayProjectId, String name) {
		return getPersistence().countByTPI_N(testrayProjectId, name);
	}

	/**
	 * Caches the testray component in the entity cache if it is enabled.
	 *
	 * @param testrayComponent the testray component
	 */
	public static void cacheResult(TestrayComponent testrayComponent) {
		getPersistence().cacheResult(testrayComponent);
	}

	/**
	 * Caches the testray components in the entity cache if it is enabled.
	 *
	 * @param testrayComponents the testray components
	 */
	public static void cacheResult(List<TestrayComponent> testrayComponents) {
		getPersistence().cacheResult(testrayComponents);
	}

	/**
	 * Creates a new testray component with the primary key. Does not add the testray component to the database.
	 *
	 * @param testrayComponentId the primary key for the new testray component
	 * @return the new testray component
	 */
	public static TestrayComponent create(long testrayComponentId) {
		return getPersistence().create(testrayComponentId);
	}

	/**
	 * Removes the testray component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayComponentId the primary key of the testray component
	 * @return the testray component that was removed
	 * @throws NoSuchTestrayComponentException if a testray component with the primary key could not be found
	 */
	public static TestrayComponent remove(long testrayComponentId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayComponentException {

		return getPersistence().remove(testrayComponentId);
	}

	public static TestrayComponent updateImpl(
		TestrayComponent testrayComponent) {

		return getPersistence().updateImpl(testrayComponent);
	}

	/**
	 * Returns the testray component with the primary key or throws a <code>NoSuchTestrayComponentException</code> if it could not be found.
	 *
	 * @param testrayComponentId the primary key of the testray component
	 * @return the testray component
	 * @throws NoSuchTestrayComponentException if a testray component with the primary key could not be found
	 */
	public static TestrayComponent findByPrimaryKey(long testrayComponentId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayComponentException {

		return getPersistence().findByPrimaryKey(testrayComponentId);
	}

	/**
	 * Returns the testray component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayComponentId the primary key of the testray component
	 * @return the testray component, or <code>null</code> if a testray component with the primary key could not be found
	 */
	public static TestrayComponent fetchByPrimaryKey(long testrayComponentId) {
		return getPersistence().fetchByPrimaryKey(testrayComponentId);
	}

	/**
	 * Returns all the testray components.
	 *
	 * @return the testray components
	 */
	public static List<TestrayComponent> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayComponentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray components
	 * @param end the upper bound of the range of testray components (not inclusive)
	 * @return the range of testray components
	 */
	public static List<TestrayComponent> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayComponentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray components
	 * @param end the upper bound of the range of testray components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray components
	 */
	public static List<TestrayComponent> findAll(
		int start, int end,
		OrderByComparator<TestrayComponent> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayComponentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray components
	 * @param end the upper bound of the range of testray components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray components
	 */
	public static List<TestrayComponent> findAll(
		int start, int end,
		OrderByComparator<TestrayComponent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the testray components from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray components.
	 *
	 * @return the number of testray components
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of testray cases associated with the testray component.
	 *
	 * @param pk the primary key of the testray component
	 * @return long[] of the primaryKeys of testray cases associated with the testray component
	 */
	public static long[] getTestrayCasePrimaryKeys(long pk) {
		return getPersistence().getTestrayCasePrimaryKeys(pk);
	}

	/**
	 * Returns all the testray cases associated with the testray component.
	 *
	 * @param pk the primary key of the testray component
	 * @return the testray cases associated with the testray component
	 */
	public static List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk) {

		return getPersistence().getTestrayCases(pk);
	}

	/**
	 * Returns a range of all the testray cases associated with the testray component.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayComponentModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray component
	 * @param start the lower bound of the range of testray components
	 * @param end the upper bound of the range of testray components (not inclusive)
	 * @return the range of testray cases associated with the testray component
	 */
	public static List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk, int start, int end) {

		return getPersistence().getTestrayCases(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the testray cases associated with the testray component.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayComponentModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the testray component
	 * @param start the lower bound of the range of testray components
	 * @param end the upper bound of the range of testray components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray cases associated with the testray component
	 */
	public static List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestrayCase>
				orderByComparator) {

		return getPersistence().getTestrayCases(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray cases associated with the testray component.
	 *
	 * @param pk the primary key of the testray component
	 * @return the number of testray cases associated with the testray component
	 */
	public static int getTestrayCasesSize(long pk) {
		return getPersistence().getTestrayCasesSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray case is associated with the testray component.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePK the primary key of the testray case
	 * @return <code>true</code> if the testray case is associated with the testray component; <code>false</code> otherwise
	 */
	public static boolean containsTestrayCase(long pk, long testrayCasePK) {
		return getPersistence().containsTestrayCase(pk, testrayCasePK);
	}

	/**
	 * Returns <code>true</code> if the testray component has any testray cases associated with it.
	 *
	 * @param pk the primary key of the testray component to check for associations with testray cases
	 * @return <code>true</code> if the testray component has any testray cases associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestrayCases(long pk) {
		return getPersistence().containsTestrayCases(pk);
	}

	/**
	 * Adds an association between the testray component and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePK the primary key of the testray case
	 */
	public static void addTestrayCase(long pk, long testrayCasePK) {
		getPersistence().addTestrayCase(pk, testrayCasePK);
	}

	/**
	 * Adds an association between the testray component and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCase the testray case
	 */
	public static void addTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getPersistence().addTestrayCase(pk, testrayCase);
	}

	/**
	 * Adds an association between the testray component and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public static void addTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().addTestrayCases(pk, testrayCasePKs);
	}

	/**
	 * Adds an association between the testray component and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCases the testray cases
	 */
	public static void addTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		getPersistence().addTestrayCases(pk, testrayCases);
	}

	/**
	 * Clears all associations between the testray component and its testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component to clear the associated testray cases from
	 */
	public static void clearTestrayCases(long pk) {
		getPersistence().clearTestrayCases(pk);
	}

	/**
	 * Removes the association between the testray component and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePK the primary key of the testray case
	 */
	public static void removeTestrayCase(long pk, long testrayCasePK) {
		getPersistence().removeTestrayCase(pk, testrayCasePK);
	}

	/**
	 * Removes the association between the testray component and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCase the testray case
	 */
	public static void removeTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getPersistence().removeTestrayCase(pk, testrayCase);
	}

	/**
	 * Removes the association between the testray component and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public static void removeTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().removeTestrayCases(pk, testrayCasePKs);
	}

	/**
	 * Removes the association between the testray component and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCases the testray cases
	 */
	public static void removeTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		getPersistence().removeTestrayCases(pk, testrayCases);
	}

	/**
	 * Sets the testray cases associated with the testray component, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePKs the primary keys of the testray cases to be associated with the testray component
	 */
	public static void setTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().setTestrayCases(pk, testrayCasePKs);
	}

	/**
	 * Sets the testray cases associated with the testray component, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCases the testray cases to be associated with the testray component
	 */
	public static void setTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		getPersistence().setTestrayCases(pk, testrayCases);
	}

	public static TestrayComponentPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(TestrayComponentPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile TestrayComponentPersistence _persistence;

}
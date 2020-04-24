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

import com.liferay.osb.testray.model.TestrayRequirement;
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
 * The persistence utility for the testray requirement service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayRequirementPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayRequirementPersistence
 * @generated
 */
@ProviderType
public class TestrayRequirementUtil {

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
	public static void clearCache(TestrayRequirement testrayRequirement) {
		getPersistence().clearCache(testrayRequirement);
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
	public static Map<Serializable, TestrayRequirement> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayRequirement> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayRequirement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayRequirement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayRequirement> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayRequirement update(
		TestrayRequirement testrayRequirement) {

		return getPersistence().update(testrayRequirement);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayRequirement update(
		TestrayRequirement testrayRequirement, ServiceContext serviceContext) {

		return getPersistence().update(testrayRequirement, serviceContext);
	}

	/**
	 * Returns the testray requirement where testrayProjectId = &#63; and key = &#63; or throws a <code>NoSuchTestrayRequirementException</code> if it could not be found.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the matching testray requirement
	 * @throws NoSuchTestrayRequirementException if a matching testray requirement could not be found
	 */
	public static TestrayRequirement findByTPI_K(
			long testrayProjectId, String key)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayRequirementException {

		return getPersistence().findByTPI_K(testrayProjectId, key);
	}

	/**
	 * Returns the testray requirement where testrayProjectId = &#63; and key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the matching testray requirement, or <code>null</code> if a matching testray requirement could not be found
	 */
	public static TestrayRequirement fetchByTPI_K(
		long testrayProjectId, String key) {

		return getPersistence().fetchByTPI_K(testrayProjectId, key);
	}

	/**
	 * Returns the testray requirement where testrayProjectId = &#63; and key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray requirement, or <code>null</code> if a matching testray requirement could not be found
	 */
	public static TestrayRequirement fetchByTPI_K(
		long testrayProjectId, String key, boolean retrieveFromCache) {

		return getPersistence().fetchByTPI_K(
			testrayProjectId, key, retrieveFromCache);
	}

	/**
	 * Removes the testray requirement where testrayProjectId = &#63; and key = &#63; from the database.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the testray requirement that was removed
	 */
	public static TestrayRequirement removeByTPI_K(
			long testrayProjectId, String key)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayRequirementException {

		return getPersistence().removeByTPI_K(testrayProjectId, key);
	}

	/**
	 * Returns the number of testray requirements where testrayProjectId = &#63; and key = &#63;.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the number of matching testray requirements
	 */
	public static int countByTPI_K(long testrayProjectId, String key) {
		return getPersistence().countByTPI_K(testrayProjectId, key);
	}

	/**
	 * Caches the testray requirement in the entity cache if it is enabled.
	 *
	 * @param testrayRequirement the testray requirement
	 */
	public static void cacheResult(TestrayRequirement testrayRequirement) {
		getPersistence().cacheResult(testrayRequirement);
	}

	/**
	 * Caches the testray requirements in the entity cache if it is enabled.
	 *
	 * @param testrayRequirements the testray requirements
	 */
	public static void cacheResult(
		List<TestrayRequirement> testrayRequirements) {

		getPersistence().cacheResult(testrayRequirements);
	}

	/**
	 * Creates a new testray requirement with the primary key. Does not add the testray requirement to the database.
	 *
	 * @param testrayRequirementId the primary key for the new testray requirement
	 * @return the new testray requirement
	 */
	public static TestrayRequirement create(long testrayRequirementId) {
		return getPersistence().create(testrayRequirementId);
	}

	/**
	 * Removes the testray requirement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement that was removed
	 * @throws NoSuchTestrayRequirementException if a testray requirement with the primary key could not be found
	 */
	public static TestrayRequirement remove(long testrayRequirementId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayRequirementException {

		return getPersistence().remove(testrayRequirementId);
	}

	public static TestrayRequirement updateImpl(
		TestrayRequirement testrayRequirement) {

		return getPersistence().updateImpl(testrayRequirement);
	}

	/**
	 * Returns the testray requirement with the primary key or throws a <code>NoSuchTestrayRequirementException</code> if it could not be found.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement
	 * @throws NoSuchTestrayRequirementException if a testray requirement with the primary key could not be found
	 */
	public static TestrayRequirement findByPrimaryKey(long testrayRequirementId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayRequirementException {

		return getPersistence().findByPrimaryKey(testrayRequirementId);
	}

	/**
	 * Returns the testray requirement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement, or <code>null</code> if a testray requirement with the primary key could not be found
	 */
	public static TestrayRequirement fetchByPrimaryKey(
		long testrayRequirementId) {

		return getPersistence().fetchByPrimaryKey(testrayRequirementId);
	}

	/**
	 * Returns all the testray requirements.
	 *
	 * @return the testray requirements
	 */
	public static List<TestrayRequirement> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray requirements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @return the range of testray requirements
	 */
	public static List<TestrayRequirement> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray requirements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray requirements
	 */
	public static List<TestrayRequirement> findAll(
		int start, int end,
		OrderByComparator<TestrayRequirement> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray requirements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray requirements
	 */
	public static List<TestrayRequirement> findAll(
		int start, int end,
		OrderByComparator<TestrayRequirement> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the testray requirements from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray requirements.
	 *
	 * @return the number of testray requirements
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of testray cases associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @return long[] of the primaryKeys of testray cases associated with the testray requirement
	 */
	public static long[] getTestrayCasePrimaryKeys(long pk) {
		return getPersistence().getTestrayCasePrimaryKeys(pk);
	}

	/**
	 * Returns all the testray cases associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @return the testray cases associated with the testray requirement
	 */
	public static List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk) {

		return getPersistence().getTestrayCases(pk);
	}

	/**
	 * Returns a range of all the testray cases associated with the testray requirement.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray requirement
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @return the range of testray cases associated with the testray requirement
	 */
	public static List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk, int start, int end) {

		return getPersistence().getTestrayCases(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the testray cases associated with the testray requirement.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray requirement
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray cases associated with the testray requirement
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
	 * Returns the number of testray cases associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @return the number of testray cases associated with the testray requirement
	 */
	public static int getTestrayCasesSize(long pk) {
		return getPersistence().getTestrayCasesSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray case is associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePK the primary key of the testray case
	 * @return <code>true</code> if the testray case is associated with the testray requirement; <code>false</code> otherwise
	 */
	public static boolean containsTestrayCase(long pk, long testrayCasePK) {
		return getPersistence().containsTestrayCase(pk, testrayCasePK);
	}

	/**
	 * Returns <code>true</code> if the testray requirement has any testray cases associated with it.
	 *
	 * @param pk the primary key of the testray requirement to check for associations with testray cases
	 * @return <code>true</code> if the testray requirement has any testray cases associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestrayCases(long pk) {
		return getPersistence().containsTestrayCases(pk);
	}

	/**
	 * Adds an association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePK the primary key of the testray case
	 */
	public static void addTestrayCase(long pk, long testrayCasePK) {
		getPersistence().addTestrayCase(pk, testrayCasePK);
	}

	/**
	 * Adds an association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCase the testray case
	 */
	public static void addTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getPersistence().addTestrayCase(pk, testrayCase);
	}

	/**
	 * Adds an association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public static void addTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().addTestrayCases(pk, testrayCasePKs);
	}

	/**
	 * Adds an association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCases the testray cases
	 */
	public static void addTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		getPersistence().addTestrayCases(pk, testrayCases);
	}

	/**
	 * Clears all associations between the testray requirement and its testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement to clear the associated testray cases from
	 */
	public static void clearTestrayCases(long pk) {
		getPersistence().clearTestrayCases(pk);
	}

	/**
	 * Removes the association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePK the primary key of the testray case
	 */
	public static void removeTestrayCase(long pk, long testrayCasePK) {
		getPersistence().removeTestrayCase(pk, testrayCasePK);
	}

	/**
	 * Removes the association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCase the testray case
	 */
	public static void removeTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getPersistence().removeTestrayCase(pk, testrayCase);
	}

	/**
	 * Removes the association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public static void removeTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().removeTestrayCases(pk, testrayCasePKs);
	}

	/**
	 * Removes the association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCases the testray cases
	 */
	public static void removeTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		getPersistence().removeTestrayCases(pk, testrayCases);
	}

	/**
	 * Sets the testray cases associated with the testray requirement, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePKs the primary keys of the testray cases to be associated with the testray requirement
	 */
	public static void setTestrayCases(long pk, long[] testrayCasePKs) {
		getPersistence().setTestrayCases(pk, testrayCasePKs);
	}

	/**
	 * Sets the testray cases associated with the testray requirement, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCases the testray cases to be associated with the testray requirement
	 */
	public static void setTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		getPersistence().setTestrayCases(pk, testrayCases);
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TestrayRequirementPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayRequirementPersistence, TestrayRequirementPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayRequirementPersistence.class);

		ServiceTracker
			<TestrayRequirementPersistence, TestrayRequirementPersistence>
				serviceTracker =
					new ServiceTracker
						<TestrayRequirementPersistence,
						 TestrayRequirementPersistence>(
							 bundle.getBundleContext(),
							 TestrayRequirementPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
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

import com.liferay.osb.testray.exception.NoSuchTestraySuiteException;
import com.liferay.osb.testray.model.TestraySuite;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray suite service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestraySuiteUtil
 * @generated
 */
@ProviderType
public interface TestraySuitePersistence extends BasePersistence<TestraySuite> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestraySuiteUtil} to access the testray suite persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestraySuite> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the testray suite in the entity cache if it is enabled.
	 *
	 * @param testraySuite the testray suite
	 */
	public void cacheResult(TestraySuite testraySuite);

	/**
	 * Caches the testray suites in the entity cache if it is enabled.
	 *
	 * @param testraySuites the testray suites
	 */
	public void cacheResult(java.util.List<TestraySuite> testraySuites);

	/**
	 * Creates a new testray suite with the primary key. Does not add the testray suite to the database.
	 *
	 * @param testraySuiteId the primary key for the new testray suite
	 * @return the new testray suite
	 */
	public TestraySuite create(long testraySuiteId);

	/**
	 * Removes the testray suite with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySuiteId the primary key of the testray suite
	 * @return the testray suite that was removed
	 * @throws NoSuchTestraySuiteException if a testray suite with the primary key could not be found
	 */
	public TestraySuite remove(long testraySuiteId)
		throws NoSuchTestraySuiteException;

	public TestraySuite updateImpl(TestraySuite testraySuite);

	/**
	 * Returns the testray suite with the primary key or throws a <code>NoSuchTestraySuiteException</code> if it could not be found.
	 *
	 * @param testraySuiteId the primary key of the testray suite
	 * @return the testray suite
	 * @throws NoSuchTestraySuiteException if a testray suite with the primary key could not be found
	 */
	public TestraySuite findByPrimaryKey(long testraySuiteId)
		throws NoSuchTestraySuiteException;

	/**
	 * Returns the testray suite with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testraySuiteId the primary key of the testray suite
	 * @return the testray suite, or <code>null</code> if a testray suite with the primary key could not be found
	 */
	public TestraySuite fetchByPrimaryKey(long testraySuiteId);

	/**
	 * Returns all the testray suites.
	 *
	 * @return the testray suites
	 */
	public java.util.List<TestraySuite> findAll();

	/**
	 * Returns a range of all the testray suites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySuiteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray suites
	 * @param end the upper bound of the range of testray suites (not inclusive)
	 * @return the range of testray suites
	 */
	public java.util.List<TestraySuite> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the testray suites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySuiteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray suites
	 * @param end the upper bound of the range of testray suites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray suites
	 */
	public java.util.List<TestraySuite> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestraySuite>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray suites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySuiteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray suites
	 * @param end the upper bound of the range of testray suites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray suites
	 */
	public java.util.List<TestraySuite> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestraySuite>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the testray suites from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray suites.
	 *
	 * @return the number of testray suites
	 */
	public int countAll();

	/**
	 * Returns the primaryKeys of testray cases associated with the testray suite.
	 *
	 * @param pk the primary key of the testray suite
	 * @return long[] of the primaryKeys of testray cases associated with the testray suite
	 */
	public long[] getTestrayCasePrimaryKeys(long pk);

	/**
	 * Returns all the testray cases associated with the testray suite.
	 *
	 * @param pk the primary key of the testray suite
	 * @return the testray cases associated with the testray suite
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk);

	/**
	 * Returns a range of all the testray cases associated with the testray suite.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySuiteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray suite
	 * @param start the lower bound of the range of testray suites
	 * @param end the upper bound of the range of testray suites (not inclusive)
	 * @return the range of testray cases associated with the testray suite
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk, int start, int end);

	/**
	 * Returns an ordered range of all the testray cases associated with the testray suite.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestraySuiteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray suite
	 * @param start the lower bound of the range of testray suites
	 * @param end the upper bound of the range of testray suites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray cases associated with the testray suite
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator);

	/**
	 * Returns the number of testray cases associated with the testray suite.
	 *
	 * @param pk the primary key of the testray suite
	 * @return the number of testray cases associated with the testray suite
	 */
	public int getTestrayCasesSize(long pk);

	/**
	 * Returns <code>true</code> if the testray case is associated with the testray suite.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePK the primary key of the testray case
	 * @return <code>true</code> if the testray case is associated with the testray suite; <code>false</code> otherwise
	 */
	public boolean containsTestrayCase(long pk, long testrayCasePK);

	/**
	 * Returns <code>true</code> if the testray suite has any testray cases associated with it.
	 *
	 * @param pk the primary key of the testray suite to check for associations with testray cases
	 * @return <code>true</code> if the testray suite has any testray cases associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayCases(long pk);

	/**
	 * Adds an association between the testray suite and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePK the primary key of the testray case
	 */
	public void addTestrayCase(long pk, long testrayCasePK);

	/**
	 * Adds an association between the testray suite and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCase the testray case
	 */
	public void addTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase);

	/**
	 * Adds an association between the testray suite and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public void addTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Adds an association between the testray suite and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCases the testray cases
	 */
	public void addTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

	/**
	 * Clears all associations between the testray suite and its testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite to clear the associated testray cases from
	 */
	public void clearTestrayCases(long pk);

	/**
	 * Removes the association between the testray suite and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePK the primary key of the testray case
	 */
	public void removeTestrayCase(long pk, long testrayCasePK);

	/**
	 * Removes the association between the testray suite and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCase the testray case
	 */
	public void removeTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase);

	/**
	 * Removes the association between the testray suite and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public void removeTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Removes the association between the testray suite and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCases the testray cases
	 */
	public void removeTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

	/**
	 * Sets the testray cases associated with the testray suite, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCasePKs the primary keys of the testray cases to be associated with the testray suite
	 */
	public void setTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Sets the testray cases associated with the testray suite, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray suite
	 * @param testrayCases the testray cases to be associated with the testray suite
	 */
	public void setTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

}
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

import com.liferay.osb.testray.exception.NoSuchTestrayRequirementException;
import com.liferay.osb.testray.model.TestrayRequirement;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray requirement service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayRequirementUtil
 * @generated
 */
@ProviderType
public interface TestrayRequirementPersistence
	extends BasePersistence<TestrayRequirement> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayRequirementUtil} to access the testray requirement persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayRequirement> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the testray requirement where testrayProjectId = &#63; and key = &#63; or throws a <code>NoSuchTestrayRequirementException</code> if it could not be found.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the matching testray requirement
	 * @throws NoSuchTestrayRequirementException if a matching testray requirement could not be found
	 */
	public TestrayRequirement findByTPI_K(long testrayProjectId, String key)
		throws NoSuchTestrayRequirementException;

	/**
	 * Returns the testray requirement where testrayProjectId = &#63; and key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the matching testray requirement, or <code>null</code> if a matching testray requirement could not be found
	 */
	public TestrayRequirement fetchByTPI_K(long testrayProjectId, String key);

	/**
	 * Returns the testray requirement where testrayProjectId = &#63; and key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray requirement, or <code>null</code> if a matching testray requirement could not be found
	 */
	public TestrayRequirement fetchByTPI_K(
		long testrayProjectId, String key, boolean retrieveFromCache);

	/**
	 * Removes the testray requirement where testrayProjectId = &#63; and key = &#63; from the database.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the testray requirement that was removed
	 */
	public TestrayRequirement removeByTPI_K(long testrayProjectId, String key)
		throws NoSuchTestrayRequirementException;

	/**
	 * Returns the number of testray requirements where testrayProjectId = &#63; and key = &#63;.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the number of matching testray requirements
	 */
	public int countByTPI_K(long testrayProjectId, String key);

	/**
	 * Caches the testray requirement in the entity cache if it is enabled.
	 *
	 * @param testrayRequirement the testray requirement
	 */
	public void cacheResult(TestrayRequirement testrayRequirement);

	/**
	 * Caches the testray requirements in the entity cache if it is enabled.
	 *
	 * @param testrayRequirements the testray requirements
	 */
	public void cacheResult(
		java.util.List<TestrayRequirement> testrayRequirements);

	/**
	 * Creates a new testray requirement with the primary key. Does not add the testray requirement to the database.
	 *
	 * @param testrayRequirementId the primary key for the new testray requirement
	 * @return the new testray requirement
	 */
	public TestrayRequirement create(long testrayRequirementId);

	/**
	 * Removes the testray requirement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement that was removed
	 * @throws NoSuchTestrayRequirementException if a testray requirement with the primary key could not be found
	 */
	public TestrayRequirement remove(long testrayRequirementId)
		throws NoSuchTestrayRequirementException;

	public TestrayRequirement updateImpl(TestrayRequirement testrayRequirement);

	/**
	 * Returns the testray requirement with the primary key or throws a <code>NoSuchTestrayRequirementException</code> if it could not be found.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement
	 * @throws NoSuchTestrayRequirementException if a testray requirement with the primary key could not be found
	 */
	public TestrayRequirement findByPrimaryKey(long testrayRequirementId)
		throws NoSuchTestrayRequirementException;

	/**
	 * Returns the testray requirement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement, or <code>null</code> if a testray requirement with the primary key could not be found
	 */
	public TestrayRequirement fetchByPrimaryKey(long testrayRequirementId);

	/**
	 * Returns all the testray requirements.
	 *
	 * @return the testray requirements
	 */
	public java.util.List<TestrayRequirement> findAll();

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
	public java.util.List<TestrayRequirement> findAll(int start, int end);

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
	public java.util.List<TestrayRequirement> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayRequirement>
			orderByComparator);

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
	public java.util.List<TestrayRequirement> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayRequirement>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the testray requirements from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray requirements.
	 *
	 * @return the number of testray requirements
	 */
	public int countAll();

	/**
	 * Returns the primaryKeys of testray cases associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @return long[] of the primaryKeys of testray cases associated with the testray requirement
	 */
	public long[] getTestrayCasePrimaryKeys(long pk);

	/**
	 * Returns all the testray cases associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @return the testray cases associated with the testray requirement
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk);

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
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk, int start, int end);

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
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator);

	/**
	 * Returns the number of testray cases associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @return the number of testray cases associated with the testray requirement
	 */
	public int getTestrayCasesSize(long pk);

	/**
	 * Returns <code>true</code> if the testray case is associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePK the primary key of the testray case
	 * @return <code>true</code> if the testray case is associated with the testray requirement; <code>false</code> otherwise
	 */
	public boolean containsTestrayCase(long pk, long testrayCasePK);

	/**
	 * Returns <code>true</code> if the testray requirement has any testray cases associated with it.
	 *
	 * @param pk the primary key of the testray requirement to check for associations with testray cases
	 * @return <code>true</code> if the testray requirement has any testray cases associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayCases(long pk);

	/**
	 * Adds an association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePK the primary key of the testray case
	 */
	public void addTestrayCase(long pk, long testrayCasePK);

	/**
	 * Adds an association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCase the testray case
	 */
	public void addTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase);

	/**
	 * Adds an association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public void addTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Adds an association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCases the testray cases
	 */
	public void addTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

	/**
	 * Clears all associations between the testray requirement and its testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement to clear the associated testray cases from
	 */
	public void clearTestrayCases(long pk);

	/**
	 * Removes the association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePK the primary key of the testray case
	 */
	public void removeTestrayCase(long pk, long testrayCasePK);

	/**
	 * Removes the association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCase the testray case
	 */
	public void removeTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase);

	/**
	 * Removes the association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public void removeTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Removes the association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCases the testray cases
	 */
	public void removeTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

	/**
	 * Sets the testray cases associated with the testray requirement, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePKs the primary keys of the testray cases to be associated with the testray requirement
	 */
	public void setTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Sets the testray cases associated with the testray requirement, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCases the testray cases to be associated with the testray requirement
	 */
	public void setTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

	@Override
	public Set<String> getBadColumnNames();

}
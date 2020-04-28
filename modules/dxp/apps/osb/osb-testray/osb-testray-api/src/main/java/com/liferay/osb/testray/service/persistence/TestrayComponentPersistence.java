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

import com.liferay.osb.testray.exception.NoSuchTestrayComponentException;
import com.liferay.osb.testray.model.TestrayComponent;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray component service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayComponentUtil
 * @generated
 */
@ProviderType
public interface TestrayComponentPersistence
	extends BasePersistence<TestrayComponent> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayComponentUtil} to access the testray component persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayComponent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the testray component where testrayProjectId = &#63; and name = &#63; or throws a <code>NoSuchTestrayComponentException</code> if it could not be found.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray component
	 * @throws NoSuchTestrayComponentException if a matching testray component could not be found
	 */
	public TestrayComponent findByTPI_N(long testrayProjectId, String name)
		throws NoSuchTestrayComponentException;

	/**
	 * Returns the testray component where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the matching testray component, or <code>null</code> if a matching testray component could not be found
	 */
	public TestrayComponent fetchByTPI_N(long testrayProjectId, String name);

	/**
	 * Returns the testray component where testrayProjectId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching testray component, or <code>null</code> if a matching testray component could not be found
	 */
	public TestrayComponent fetchByTPI_N(
		long testrayProjectId, String name, boolean useFinderCache);

	/**
	 * Removes the testray component where testrayProjectId = &#63; and name = &#63; from the database.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the testray component that was removed
	 */
	public TestrayComponent removeByTPI_N(long testrayProjectId, String name)
		throws NoSuchTestrayComponentException;

	/**
	 * Returns the number of testray components where testrayProjectId = &#63; and name = &#63;.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param name the name
	 * @return the number of matching testray components
	 */
	public int countByTPI_N(long testrayProjectId, String name);

	/**
	 * Caches the testray component in the entity cache if it is enabled.
	 *
	 * @param testrayComponent the testray component
	 */
	public void cacheResult(TestrayComponent testrayComponent);

	/**
	 * Caches the testray components in the entity cache if it is enabled.
	 *
	 * @param testrayComponents the testray components
	 */
	public void cacheResult(java.util.List<TestrayComponent> testrayComponents);

	/**
	 * Creates a new testray component with the primary key. Does not add the testray component to the database.
	 *
	 * @param testrayComponentId the primary key for the new testray component
	 * @return the new testray component
	 */
	public TestrayComponent create(long testrayComponentId);

	/**
	 * Removes the testray component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayComponentId the primary key of the testray component
	 * @return the testray component that was removed
	 * @throws NoSuchTestrayComponentException if a testray component with the primary key could not be found
	 */
	public TestrayComponent remove(long testrayComponentId)
		throws NoSuchTestrayComponentException;

	public TestrayComponent updateImpl(TestrayComponent testrayComponent);

	/**
	 * Returns the testray component with the primary key or throws a <code>NoSuchTestrayComponentException</code> if it could not be found.
	 *
	 * @param testrayComponentId the primary key of the testray component
	 * @return the testray component
	 * @throws NoSuchTestrayComponentException if a testray component with the primary key could not be found
	 */
	public TestrayComponent findByPrimaryKey(long testrayComponentId)
		throws NoSuchTestrayComponentException;

	/**
	 * Returns the testray component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayComponentId the primary key of the testray component
	 * @return the testray component, or <code>null</code> if a testray component with the primary key could not be found
	 */
	public TestrayComponent fetchByPrimaryKey(long testrayComponentId);

	/**
	 * Returns all the testray components.
	 *
	 * @return the testray components
	 */
	public java.util.List<TestrayComponent> findAll();

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
	public java.util.List<TestrayComponent> findAll(int start, int end);

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
	public java.util.List<TestrayComponent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayComponent>
			orderByComparator);

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
	public java.util.List<TestrayComponent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayComponent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the testray components from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray components.
	 *
	 * @return the number of testray components
	 */
	public int countAll();

	/**
	 * Returns the primaryKeys of testray cases associated with the testray component.
	 *
	 * @param pk the primary key of the testray component
	 * @return long[] of the primaryKeys of testray cases associated with the testray component
	 */
	public long[] getTestrayCasePrimaryKeys(long pk);

	/**
	 * Returns all the testray cases associated with the testray component.
	 *
	 * @param pk the primary key of the testray component
	 * @return the testray cases associated with the testray component
	 */
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk);

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
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(long pk, int start, int end);

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
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator);

	/**
	 * Returns the number of testray cases associated with the testray component.
	 *
	 * @param pk the primary key of the testray component
	 * @return the number of testray cases associated with the testray component
	 */
	public int getTestrayCasesSize(long pk);

	/**
	 * Returns <code>true</code> if the testray case is associated with the testray component.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePK the primary key of the testray case
	 * @return <code>true</code> if the testray case is associated with the testray component; <code>false</code> otherwise
	 */
	public boolean containsTestrayCase(long pk, long testrayCasePK);

	/**
	 * Returns <code>true</code> if the testray component has any testray cases associated with it.
	 *
	 * @param pk the primary key of the testray component to check for associations with testray cases
	 * @return <code>true</code> if the testray component has any testray cases associated with it; <code>false</code> otherwise
	 */
	public boolean containsTestrayCases(long pk);

	/**
	 * Adds an association between the testray component and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePK the primary key of the testray case
	 */
	public void addTestrayCase(long pk, long testrayCasePK);

	/**
	 * Adds an association between the testray component and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCase the testray case
	 */
	public void addTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase);

	/**
	 * Adds an association between the testray component and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public void addTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Adds an association between the testray component and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCases the testray cases
	 */
	public void addTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

	/**
	 * Clears all associations between the testray component and its testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component to clear the associated testray cases from
	 */
	public void clearTestrayCases(long pk);

	/**
	 * Removes the association between the testray component and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePK the primary key of the testray case
	 */
	public void removeTestrayCase(long pk, long testrayCasePK);

	/**
	 * Removes the association between the testray component and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCase the testray case
	 */
	public void removeTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase);

	/**
	 * Removes the association between the testray component and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	public void removeTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Removes the association between the testray component and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCases the testray cases
	 */
	public void removeTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

	/**
	 * Sets the testray cases associated with the testray component, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCasePKs the primary keys of the testray cases to be associated with the testray component
	 */
	public void setTestrayCases(long pk, long[] testrayCasePKs);

	/**
	 * Sets the testray cases associated with the testray component, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray component
	 * @param testrayCases the testray cases to be associated with the testray component
	 */
	public void setTestrayCases(
		long pk,
		java.util.List<com.liferay.osb.testray.model.TestrayCase> testrayCases);

}
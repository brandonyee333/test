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

import com.liferay.osb.testray.exception.NoSuchTestrayFactorOptionException;
import com.liferay.osb.testray.model.TestrayFactorOption;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray factor option service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayFactorOptionUtil
 * @generated
 */
@ProviderType
public interface TestrayFactorOptionPersistence
	extends BasePersistence<TestrayFactorOption> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayFactorOptionUtil} to access the testray factor option persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayFactorOption> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; or throws a <code>NoSuchTestrayFactorOptionException</code> if it could not be found.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID
	 * @param name the name
	 * @return the matching testray factor option
	 * @throws NoSuchTestrayFactorOptionException if a matching testray factor option could not be found
	 */
	public TestrayFactorOption findByT_N(
			long testrayFactorCategoryId, String name)
		throws NoSuchTestrayFactorOptionException;

	/**
	 * Returns the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID
	 * @param name the name
	 * @return the matching testray factor option, or <code>null</code> if a matching testray factor option could not be found
	 */
	public TestrayFactorOption fetchByT_N(
		long testrayFactorCategoryId, String name);

	/**
	 * Returns the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray factor option, or <code>null</code> if a matching testray factor option could not be found
	 */
	public TestrayFactorOption fetchByT_N(
		long testrayFactorCategoryId, String name, boolean retrieveFromCache);

	/**
	 * Removes the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; from the database.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID
	 * @param name the name
	 * @return the testray factor option that was removed
	 */
	public TestrayFactorOption removeByT_N(
			long testrayFactorCategoryId, String name)
		throws NoSuchTestrayFactorOptionException;

	/**
	 * Returns the number of testray factor options where testrayFactorCategoryId = &#63; and name = &#63;.
	 *
	 * @param testrayFactorCategoryId the testray factor category ID
	 * @param name the name
	 * @return the number of matching testray factor options
	 */
	public int countByT_N(long testrayFactorCategoryId, String name);

	/**
	 * Caches the testray factor option in the entity cache if it is enabled.
	 *
	 * @param testrayFactorOption the testray factor option
	 */
	public void cacheResult(TestrayFactorOption testrayFactorOption);

	/**
	 * Caches the testray factor options in the entity cache if it is enabled.
	 *
	 * @param testrayFactorOptions the testray factor options
	 */
	public void cacheResult(
		java.util.List<TestrayFactorOption> testrayFactorOptions);

	/**
	 * Creates a new testray factor option with the primary key. Does not add the testray factor option to the database.
	 *
	 * @param testrayFactorOptionId the primary key for the new testray factor option
	 * @return the new testray factor option
	 */
	public TestrayFactorOption create(long testrayFactorOptionId);

	/**
	 * Removes the testray factor option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorOptionId the primary key of the testray factor option
	 * @return the testray factor option that was removed
	 * @throws NoSuchTestrayFactorOptionException if a testray factor option with the primary key could not be found
	 */
	public TestrayFactorOption remove(long testrayFactorOptionId)
		throws NoSuchTestrayFactorOptionException;

	public TestrayFactorOption updateImpl(
		TestrayFactorOption testrayFactorOption);

	/**
	 * Returns the testray factor option with the primary key or throws a <code>NoSuchTestrayFactorOptionException</code> if it could not be found.
	 *
	 * @param testrayFactorOptionId the primary key of the testray factor option
	 * @return the testray factor option
	 * @throws NoSuchTestrayFactorOptionException if a testray factor option with the primary key could not be found
	 */
	public TestrayFactorOption findByPrimaryKey(long testrayFactorOptionId)
		throws NoSuchTestrayFactorOptionException;

	/**
	 * Returns the testray factor option with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayFactorOptionId the primary key of the testray factor option
	 * @return the testray factor option, or <code>null</code> if a testray factor option with the primary key could not be found
	 */
	public TestrayFactorOption fetchByPrimaryKey(long testrayFactorOptionId);

	/**
	 * Returns all the testray factor options.
	 *
	 * @return the testray factor options
	 */
	public java.util.List<TestrayFactorOption> findAll();

	/**
	 * Returns a range of all the testray factor options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayFactorOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor options
	 * @param end the upper bound of the range of testray factor options (not inclusive)
	 * @return the range of testray factor options
	 */
	public java.util.List<TestrayFactorOption> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the testray factor options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayFactorOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor options
	 * @param end the upper bound of the range of testray factor options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray factor options
	 */
	public java.util.List<TestrayFactorOption> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactorOption>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray factor options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayFactorOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor options
	 * @param end the upper bound of the range of testray factor options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray factor options
	 */
	public java.util.List<TestrayFactorOption> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactorOption>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the testray factor options from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray factor options.
	 *
	 * @return the number of testray factor options
	 */
	public int countAll();

}
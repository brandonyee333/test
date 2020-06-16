/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.testray.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.exception.NoSuchTestrayFactorOptionException;
import com.liferay.osb.testray.model.TestrayFactorOption;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the testray factor option service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayFactorOptionPersistenceImpl
 * @see TestrayFactorOptionUtil
 * @generated
 */
@ProviderType
public interface TestrayFactorOptionPersistence extends BasePersistence<TestrayFactorOption> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayFactorOptionUtil} to access the testray factor option persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; or throws a {@link NoSuchTestrayFactorOptionException} if it could not be found.
	*
	* @param testrayFactorCategoryId the testray factor category ID
	* @param name the name
	* @return the matching testray factor option
	* @throws NoSuchTestrayFactorOptionException if a matching testray factor option could not be found
	*/
	public TestrayFactorOption findByT_N(long testrayFactorCategoryId,
		java.lang.String name) throws NoSuchTestrayFactorOptionException;

	/**
	* Returns the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param testrayFactorCategoryId the testray factor category ID
	* @param name the name
	* @return the matching testray factor option, or <code>null</code> if a matching testray factor option could not be found
	*/
	public TestrayFactorOption fetchByT_N(long testrayFactorCategoryId,
		java.lang.String name);

	/**
	* Returns the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param testrayFactorCategoryId the testray factor category ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching testray factor option, or <code>null</code> if a matching testray factor option could not be found
	*/
	public TestrayFactorOption fetchByT_N(long testrayFactorCategoryId,
		java.lang.String name, boolean retrieveFromCache);

	/**
	* Removes the testray factor option where testrayFactorCategoryId = &#63; and name = &#63; from the database.
	*
	* @param testrayFactorCategoryId the testray factor category ID
	* @param name the name
	* @return the testray factor option that was removed
	*/
	public TestrayFactorOption removeByT_N(long testrayFactorCategoryId,
		java.lang.String name) throws NoSuchTestrayFactorOptionException;

	/**
	* Returns the number of testray factor options where testrayFactorCategoryId = &#63; and name = &#63;.
	*
	* @param testrayFactorCategoryId the testray factor category ID
	* @param name the name
	* @return the number of matching testray factor options
	*/
	public int countByT_N(long testrayFactorCategoryId, java.lang.String name);

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
	* Returns the testray factor option with the primary key or throws a {@link NoSuchTestrayFactorOptionException} if it could not be found.
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

	@Override
	public java.util.Map<java.io.Serializable, TestrayFactorOption> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray factor options
	* @param end the upper bound of the range of testray factor options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray factor options
	*/
	public java.util.List<TestrayFactorOption> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactorOption> orderByComparator);

	/**
	* Returns an ordered range of all the testray factor options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray factor options
	* @param end the upper bound of the range of testray factor options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray factor options
	*/
	public java.util.List<TestrayFactorOption> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactorOption> orderByComparator,
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
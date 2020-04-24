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

import com.liferay.osb.testray.exception.NoSuchTestrayFactorCategoryException;
import com.liferay.osb.testray.model.TestrayFactorCategory;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray factor category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayFactorCategoryUtil
 * @generated
 */
@ProviderType
public interface TestrayFactorCategoryPersistence
	extends BasePersistence<TestrayFactorCategory> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayFactorCategoryUtil} to access the testray factor category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayFactorCategory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the testray factor category where groupId = &#63; and name = &#63; or throws a <code>NoSuchTestrayFactorCategoryException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching testray factor category
	 * @throws NoSuchTestrayFactorCategoryException if a matching testray factor category could not be found
	 */
	public TestrayFactorCategory findByG_N(long groupId, String name)
		throws NoSuchTestrayFactorCategoryException;

	/**
	 * Returns the testray factor category where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching testray factor category, or <code>null</code> if a matching testray factor category could not be found
	 */
	public TestrayFactorCategory fetchByG_N(long groupId, String name);

	/**
	 * Returns the testray factor category where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray factor category, or <code>null</code> if a matching testray factor category could not be found
	 */
	public TestrayFactorCategory fetchByG_N(
		long groupId, String name, boolean retrieveFromCache);

	/**
	 * Removes the testray factor category where groupId = &#63; and name = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the testray factor category that was removed
	 */
	public TestrayFactorCategory removeByG_N(long groupId, String name)
		throws NoSuchTestrayFactorCategoryException;

	/**
	 * Returns the number of testray factor categories where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching testray factor categories
	 */
	public int countByG_N(long groupId, String name);

	/**
	 * Caches the testray factor category in the entity cache if it is enabled.
	 *
	 * @param testrayFactorCategory the testray factor category
	 */
	public void cacheResult(TestrayFactorCategory testrayFactorCategory);

	/**
	 * Caches the testray factor categories in the entity cache if it is enabled.
	 *
	 * @param testrayFactorCategories the testray factor categories
	 */
	public void cacheResult(
		java.util.List<TestrayFactorCategory> testrayFactorCategories);

	/**
	 * Creates a new testray factor category with the primary key. Does not add the testray factor category to the database.
	 *
	 * @param testrayFactorCategoryId the primary key for the new testray factor category
	 * @return the new testray factor category
	 */
	public TestrayFactorCategory create(long testrayFactorCategoryId);

	/**
	 * Removes the testray factor category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorCategoryId the primary key of the testray factor category
	 * @return the testray factor category that was removed
	 * @throws NoSuchTestrayFactorCategoryException if a testray factor category with the primary key could not be found
	 */
	public TestrayFactorCategory remove(long testrayFactorCategoryId)
		throws NoSuchTestrayFactorCategoryException;

	public TestrayFactorCategory updateImpl(
		TestrayFactorCategory testrayFactorCategory);

	/**
	 * Returns the testray factor category with the primary key or throws a <code>NoSuchTestrayFactorCategoryException</code> if it could not be found.
	 *
	 * @param testrayFactorCategoryId the primary key of the testray factor category
	 * @return the testray factor category
	 * @throws NoSuchTestrayFactorCategoryException if a testray factor category with the primary key could not be found
	 */
	public TestrayFactorCategory findByPrimaryKey(long testrayFactorCategoryId)
		throws NoSuchTestrayFactorCategoryException;

	/**
	 * Returns the testray factor category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayFactorCategoryId the primary key of the testray factor category
	 * @return the testray factor category, or <code>null</code> if a testray factor category with the primary key could not be found
	 */
	public TestrayFactorCategory fetchByPrimaryKey(
		long testrayFactorCategoryId);

	/**
	 * Returns all the testray factor categories.
	 *
	 * @return the testray factor categories
	 */
	public java.util.List<TestrayFactorCategory> findAll();

	/**
	 * Returns a range of all the testray factor categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayFactorCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor categories
	 * @param end the upper bound of the range of testray factor categories (not inclusive)
	 * @return the range of testray factor categories
	 */
	public java.util.List<TestrayFactorCategory> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the testray factor categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayFactorCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor categories
	 * @param end the upper bound of the range of testray factor categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray factor categories
	 */
	public java.util.List<TestrayFactorCategory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactorCategory>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray factor categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayFactorCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor categories
	 * @param end the upper bound of the range of testray factor categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray factor categories
	 */
	public java.util.List<TestrayFactorCategory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactorCategory>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the testray factor categories from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray factor categories.
	 *
	 * @return the number of testray factor categories
	 */
	public int countAll();

}
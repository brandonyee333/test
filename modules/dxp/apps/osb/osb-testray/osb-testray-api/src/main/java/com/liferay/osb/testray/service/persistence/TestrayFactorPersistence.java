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

import com.liferay.osb.testray.exception.NoSuchTestrayFactorException;
import com.liferay.osb.testray.model.TestrayFactor;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray factor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayFactorUtil
 * @generated
 */
@ProviderType
public interface TestrayFactorPersistence
	extends BasePersistence<TestrayFactor> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayFactorUtil} to access the testray factor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayFactor> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching testray factors
	 */
	public java.util.List<TestrayFactor> findByCNI_CP(
		long classNameId, long classPK);

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
	public java.util.List<TestrayFactor> findByCNI_CP(
		long classNameId, long classPK, int start, int end);

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
	public java.util.List<TestrayFactor> findByCNI_CP(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor>
			orderByComparator);

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
	public java.util.List<TestrayFactor> findByCNI_CP(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray factor
	 * @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	 */
	public TestrayFactor findByCNI_CP_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor>
				orderByComparator)
		throws NoSuchTestrayFactorException;

	/**
	 * Returns the first testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	public TestrayFactor fetchByCNI_CP_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor>
			orderByComparator);

	/**
	 * Returns the last testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray factor
	 * @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	 */
	public TestrayFactor findByCNI_CP_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor>
				orderByComparator)
		throws NoSuchTestrayFactorException;

	/**
	 * Returns the last testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	public TestrayFactor fetchByCNI_CP_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor>
			orderByComparator);

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
	public TestrayFactor[] findByCNI_CP_PrevAndNext(
			long testrayFactorId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor>
				orderByComparator)
		throws NoSuchTestrayFactorException;

	/**
	 * Removes all the testray factors where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByCNI_CP(long classNameId, long classPK);

	/**
	 * Returns the number of testray factors where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching testray factors
	 */
	public int countByCNI_CP(long classNameId, long classPK);

	/**
	 * Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or throws a <code>NoSuchTestrayFactorException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the matching testray factor
	 * @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	 */
	public TestrayFactor findByC_C_T(
			long classNameId, long classPK, long testrayFactorOptionId)
		throws NoSuchTestrayFactorException;

	/**
	 * Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	public TestrayFactor fetchByC_C_T(
		long classNameId, long classPK, long testrayFactorOptionId);

	/**
	 * Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching testray factor, or <code>null</code> if a matching testray factor could not be found
	 */
	public TestrayFactor fetchByC_C_T(
		long classNameId, long classPK, long testrayFactorOptionId,
		boolean useFinderCache);

	/**
	 * Removes the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the testray factor that was removed
	 */
	public TestrayFactor removeByC_C_T(
			long classNameId, long classPK, long testrayFactorOptionId)
		throws NoSuchTestrayFactorException;

	/**
	 * Returns the number of testray factors where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param testrayFactorOptionId the testray factor option ID
	 * @return the number of matching testray factors
	 */
	public int countByC_C_T(
		long classNameId, long classPK, long testrayFactorOptionId);

	/**
	 * Caches the testray factor in the entity cache if it is enabled.
	 *
	 * @param testrayFactor the testray factor
	 */
	public void cacheResult(TestrayFactor testrayFactor);

	/**
	 * Caches the testray factors in the entity cache if it is enabled.
	 *
	 * @param testrayFactors the testray factors
	 */
	public void cacheResult(java.util.List<TestrayFactor> testrayFactors);

	/**
	 * Creates a new testray factor with the primary key. Does not add the testray factor to the database.
	 *
	 * @param testrayFactorId the primary key for the new testray factor
	 * @return the new testray factor
	 */
	public TestrayFactor create(long testrayFactorId);

	/**
	 * Removes the testray factor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorId the primary key of the testray factor
	 * @return the testray factor that was removed
	 * @throws NoSuchTestrayFactorException if a testray factor with the primary key could not be found
	 */
	public TestrayFactor remove(long testrayFactorId)
		throws NoSuchTestrayFactorException;

	public TestrayFactor updateImpl(TestrayFactor testrayFactor);

	/**
	 * Returns the testray factor with the primary key or throws a <code>NoSuchTestrayFactorException</code> if it could not be found.
	 *
	 * @param testrayFactorId the primary key of the testray factor
	 * @return the testray factor
	 * @throws NoSuchTestrayFactorException if a testray factor with the primary key could not be found
	 */
	public TestrayFactor findByPrimaryKey(long testrayFactorId)
		throws NoSuchTestrayFactorException;

	/**
	 * Returns the testray factor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayFactorId the primary key of the testray factor
	 * @return the testray factor, or <code>null</code> if a testray factor with the primary key could not be found
	 */
	public TestrayFactor fetchByPrimaryKey(long testrayFactorId);

	/**
	 * Returns all the testray factors.
	 *
	 * @return the testray factors
	 */
	public java.util.List<TestrayFactor> findAll();

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
	public java.util.List<TestrayFactor> findAll(int start, int end);

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
	public java.util.List<TestrayFactor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor>
			orderByComparator);

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
	public java.util.List<TestrayFactor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the testray factors from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of testray factors.
	 *
	 * @return the number of testray factors
	 */
	public int countAll();

}
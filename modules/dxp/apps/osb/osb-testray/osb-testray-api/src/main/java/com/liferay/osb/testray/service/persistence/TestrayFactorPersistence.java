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

import com.liferay.osb.testray.exception.NoSuchTestrayFactorException;
import com.liferay.osb.testray.model.TestrayFactor;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the testray factor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayFactorPersistenceImpl
 * @see TestrayFactorUtil
 * @generated
 */
@ProviderType
public interface TestrayFactorPersistence extends BasePersistence<TestrayFactor> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayFactorUtil} to access the testray factor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the testray factors where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching testray factors
	*/
	public java.util.List<TestrayFactor> findByCNI_CP(long classNameId,
		long classPK);

	/**
	* Returns a range of all the testray factors where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of testray factors
	* @param end the upper bound of the range of testray factors (not inclusive)
	* @return the range of matching testray factors
	*/
	public java.util.List<TestrayFactor> findByCNI_CP(long classNameId,
		long classPK, int start, int end);

	/**
	* Returns an ordered range of all the testray factors where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of testray factors
	* @param end the upper bound of the range of testray factors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching testray factors
	*/
	public java.util.List<TestrayFactor> findByCNI_CP(long classNameId,
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor> orderByComparator);

	/**
	* Returns an ordered range of all the testray factors where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of testray factors
	* @param end the upper bound of the range of testray factors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching testray factors
	*/
	public java.util.List<TestrayFactor> findByCNI_CP(long classNameId,
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching testray factor
	* @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	*/
	public TestrayFactor findByCNI_CP_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor> orderByComparator)
		throws NoSuchTestrayFactorException;

	/**
	* Returns the first testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching testray factor, or <code>null</code> if a matching testray factor could not be found
	*/
	public TestrayFactor fetchByCNI_CP_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor> orderByComparator);

	/**
	* Returns the last testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching testray factor
	* @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	*/
	public TestrayFactor findByCNI_CP_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor> orderByComparator)
		throws NoSuchTestrayFactorException;

	/**
	* Returns the last testray factor in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching testray factor, or <code>null</code> if a matching testray factor could not be found
	*/
	public TestrayFactor fetchByCNI_CP_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor> orderByComparator);

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
	public TestrayFactor[] findByCNI_CP_PrevAndNext(long testrayFactorId,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor> orderByComparator)
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
	* Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or throws a {@link NoSuchTestrayFactorException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param testrayFactorOptionId the testray factor option ID
	* @return the matching testray factor
	* @throws NoSuchTestrayFactorException if a matching testray factor could not be found
	*/
	public TestrayFactor findByC_C_T(long classNameId, long classPK,
		long testrayFactorOptionId) throws NoSuchTestrayFactorException;

	/**
	* Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param testrayFactorOptionId the testray factor option ID
	* @return the matching testray factor, or <code>null</code> if a matching testray factor could not be found
	*/
	public TestrayFactor fetchByC_C_T(long classNameId, long classPK,
		long testrayFactorOptionId);

	/**
	* Returns the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param testrayFactorOptionId the testray factor option ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching testray factor, or <code>null</code> if a matching testray factor could not be found
	*/
	public TestrayFactor fetchByC_C_T(long classNameId, long classPK,
		long testrayFactorOptionId, boolean retrieveFromCache);

	/**
	* Removes the testray factor where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param testrayFactorOptionId the testray factor option ID
	* @return the testray factor that was removed
	*/
	public TestrayFactor removeByC_C_T(long classNameId, long classPK,
		long testrayFactorOptionId) throws NoSuchTestrayFactorException;

	/**
	* Returns the number of testray factors where classNameId = &#63; and classPK = &#63; and testrayFactorOptionId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param testrayFactorOptionId the testray factor option ID
	* @return the number of matching testray factors
	*/
	public int countByC_C_T(long classNameId, long classPK,
		long testrayFactorOptionId);

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
	* Returns the testray factor with the primary key or throws a {@link NoSuchTestrayFactorException} if it could not be found.
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

	@Override
	public java.util.Map<java.io.Serializable, TestrayFactor> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray factors
	* @param end the upper bound of the range of testray factors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray factors
	*/
	public java.util.List<TestrayFactor> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor> orderByComparator);

	/**
	* Returns an ordered range of all the testray factors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayFactorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray factors
	* @param end the upper bound of the range of testray factors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray factors
	*/
	public java.util.List<TestrayFactor> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayFactor> orderByComparator,
		boolean retrieveFromCache);

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
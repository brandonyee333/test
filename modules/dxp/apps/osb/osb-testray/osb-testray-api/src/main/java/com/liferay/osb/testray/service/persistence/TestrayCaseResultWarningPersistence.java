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

import com.liferay.osb.testray.exception.NoSuchTestrayCaseResultWarningException;
import com.liferay.osb.testray.model.TestrayCaseResultWarning;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the testray case result warning service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayCaseResultWarningPersistenceImpl
 * @see TestrayCaseResultWarningUtil
 * @generated
 */
@ProviderType
public interface TestrayCaseResultWarningPersistence extends BasePersistence<TestrayCaseResultWarning> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayCaseResultWarningUtil} to access the testray case result warning persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the testray case result warnings where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @return the matching testray case result warnings
	*/
	public java.util.List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId);

	/**
	* Returns a range of all the testray case result warnings where testrayCaseResultId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param testrayCaseResultId the testray case result ID
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @return the range of matching testray case result warnings
	*/
	public java.util.List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId, int start, int end);

	/**
	* Returns an ordered range of all the testray case result warnings where testrayCaseResultId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param testrayCaseResultId the testray case result ID
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching testray case result warnings
	*/
	public java.util.List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResultWarning> orderByComparator);

	/**
	* Returns an ordered range of all the testray case result warnings where testrayCaseResultId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param testrayCaseResultId the testray case result ID
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching testray case result warnings
	*/
	public java.util.List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResultWarning> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching testray case result warning
	* @throws NoSuchTestrayCaseResultWarningException if a matching testray case result warning could not be found
	*/
	public TestrayCaseResultWarning findByTestrayCaseResultId_First(
		long testrayCaseResultId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResultWarning> orderByComparator)
		throws NoSuchTestrayCaseResultWarningException;

	/**
	* Returns the first testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching testray case result warning, or <code>null</code> if a matching testray case result warning could not be found
	*/
	public TestrayCaseResultWarning fetchByTestrayCaseResultId_First(
		long testrayCaseResultId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResultWarning> orderByComparator);

	/**
	* Returns the last testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching testray case result warning
	* @throws NoSuchTestrayCaseResultWarningException if a matching testray case result warning could not be found
	*/
	public TestrayCaseResultWarning findByTestrayCaseResultId_Last(
		long testrayCaseResultId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResultWarning> orderByComparator)
		throws NoSuchTestrayCaseResultWarningException;

	/**
	* Returns the last testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching testray case result warning, or <code>null</code> if a matching testray case result warning could not be found
	*/
	public TestrayCaseResultWarning fetchByTestrayCaseResultId_Last(
		long testrayCaseResultId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResultWarning> orderByComparator);

	/**
	* Returns the testray case result warnings before and after the current testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultWarningId the primary key of the current testray case result warning
	* @param testrayCaseResultId the testray case result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next testray case result warning
	* @throws NoSuchTestrayCaseResultWarningException if a testray case result warning with the primary key could not be found
	*/
	public TestrayCaseResultWarning[] findByTestrayCaseResultId_PrevAndNext(
		long testrayCaseResultWarningId, long testrayCaseResultId,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResultWarning> orderByComparator)
		throws NoSuchTestrayCaseResultWarningException;

	/**
	* Removes all the testray case result warnings where testrayCaseResultId = &#63; from the database.
	*
	* @param testrayCaseResultId the testray case result ID
	*/
	public void removeByTestrayCaseResultId(long testrayCaseResultId);

	/**
	* Returns the number of testray case result warnings where testrayCaseResultId = &#63;.
	*
	* @param testrayCaseResultId the testray case result ID
	* @return the number of matching testray case result warnings
	*/
	public int countByTestrayCaseResultId(long testrayCaseResultId);

	/**
	* Caches the testray case result warning in the entity cache if it is enabled.
	*
	* @param testrayCaseResultWarning the testray case result warning
	*/
	public void cacheResult(TestrayCaseResultWarning testrayCaseResultWarning);

	/**
	* Caches the testray case result warnings in the entity cache if it is enabled.
	*
	* @param testrayCaseResultWarnings the testray case result warnings
	*/
	public void cacheResult(
		java.util.List<TestrayCaseResultWarning> testrayCaseResultWarnings);

	/**
	* Creates a new testray case result warning with the primary key. Does not add the testray case result warning to the database.
	*
	* @param testrayCaseResultWarningId the primary key for the new testray case result warning
	* @return the new testray case result warning
	*/
	public TestrayCaseResultWarning create(long testrayCaseResultWarningId);

	/**
	* Removes the testray case result warning with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayCaseResultWarningId the primary key of the testray case result warning
	* @return the testray case result warning that was removed
	* @throws NoSuchTestrayCaseResultWarningException if a testray case result warning with the primary key could not be found
	*/
	public TestrayCaseResultWarning remove(long testrayCaseResultWarningId)
		throws NoSuchTestrayCaseResultWarningException;

	public TestrayCaseResultWarning updateImpl(
		TestrayCaseResultWarning testrayCaseResultWarning);

	/**
	* Returns the testray case result warning with the primary key or throws a {@link NoSuchTestrayCaseResultWarningException} if it could not be found.
	*
	* @param testrayCaseResultWarningId the primary key of the testray case result warning
	* @return the testray case result warning
	* @throws NoSuchTestrayCaseResultWarningException if a testray case result warning with the primary key could not be found
	*/
	public TestrayCaseResultWarning findByPrimaryKey(
		long testrayCaseResultWarningId)
		throws NoSuchTestrayCaseResultWarningException;

	/**
	* Returns the testray case result warning with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayCaseResultWarningId the primary key of the testray case result warning
	* @return the testray case result warning, or <code>null</code> if a testray case result warning with the primary key could not be found
	*/
	public TestrayCaseResultWarning fetchByPrimaryKey(
		long testrayCaseResultWarningId);

	@Override
	public java.util.Map<java.io.Serializable, TestrayCaseResultWarning> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the testray case result warnings.
	*
	* @return the testray case result warnings
	*/
	public java.util.List<TestrayCaseResultWarning> findAll();

	/**
	* Returns a range of all the testray case result warnings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @return the range of testray case result warnings
	*/
	public java.util.List<TestrayCaseResultWarning> findAll(int start, int end);

	/**
	* Returns an ordered range of all the testray case result warnings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray case result warnings
	*/
	public java.util.List<TestrayCaseResultWarning> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResultWarning> orderByComparator);

	/**
	* Returns an ordered range of all the testray case result warnings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseResultWarningModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray case result warnings
	* @param end the upper bound of the range of testray case result warnings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray case result warnings
	*/
	public java.util.List<TestrayCaseResultWarning> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayCaseResultWarning> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the testray case result warnings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of testray case result warnings.
	*
	* @return the number of testray case result warnings
	*/
	public int countAll();
}
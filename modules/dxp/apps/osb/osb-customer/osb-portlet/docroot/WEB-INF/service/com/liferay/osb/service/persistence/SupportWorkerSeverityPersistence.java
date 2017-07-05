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

package com.liferay.osb.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchSupportWorkerSeverityException;
import com.liferay.osb.model.SupportWorkerSeverity;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the support worker severity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.SupportWorkerSeverityPersistenceImpl
 * @see SupportWorkerSeverityUtil
 * @generated
 */
@ProviderType
public interface SupportWorkerSeverityPersistence extends BasePersistence<SupportWorkerSeverity> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportWorkerSeverityUtil} to access the support worker severity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the support worker severities where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the matching support worker severities
	*/
	public java.util.List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId);

	/**
	* Returns a range of all the support worker severities where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @return the range of matching support worker severities
	*/
	public java.util.List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId, int start, int end);

	/**
	* Returns an ordered range of all the support worker severities where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support worker severities
	*/
	public java.util.List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerSeverity> orderByComparator);

	/**
	* Returns an ordered range of all the support worker severities where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support worker severities
	*/
	public java.util.List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerSeverity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker severity
	* @throws NoSuchSupportWorkerSeverityException if a matching support worker severity could not be found
	*/
	public SupportWorkerSeverity findBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerSeverity> orderByComparator)
		throws NoSuchSupportWorkerSeverityException;

	/**
	* Returns the first support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker severity, or <code>null</code> if a matching support worker severity could not be found
	*/
	public SupportWorkerSeverity fetchBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerSeverity> orderByComparator);

	/**
	* Returns the last support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker severity
	* @throws NoSuchSupportWorkerSeverityException if a matching support worker severity could not be found
	*/
	public SupportWorkerSeverity findBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerSeverity> orderByComparator)
		throws NoSuchSupportWorkerSeverityException;

	/**
	* Returns the last support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker severity, or <code>null</code> if a matching support worker severity could not be found
	*/
	public SupportWorkerSeverity fetchBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerSeverity> orderByComparator);

	/**
	* Returns the support worker severities before and after the current support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerSeverityId the primary key of the current support worker severity
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker severity
	* @throws NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	*/
	public SupportWorkerSeverity[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerSeverityId, long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerSeverity> orderByComparator)
		throws NoSuchSupportWorkerSeverityException;

	/**
	* Removes all the support worker severities where supportWorkerId = &#63; from the database.
	*
	* @param supportWorkerId the support worker ID
	*/
	public void removeBySupportWorkerId(long supportWorkerId);

	/**
	* Returns the number of support worker severities where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the number of matching support worker severities
	*/
	public int countBySupportWorkerId(long supportWorkerId);

	/**
	* Caches the support worker severity in the entity cache if it is enabled.
	*
	* @param supportWorkerSeverity the support worker severity
	*/
	public void cacheResult(SupportWorkerSeverity supportWorkerSeverity);

	/**
	* Caches the support worker severities in the entity cache if it is enabled.
	*
	* @param supportWorkerSeverities the support worker severities
	*/
	public void cacheResult(
		java.util.List<SupportWorkerSeverity> supportWorkerSeverities);

	/**
	* Creates a new support worker severity with the primary key. Does not add the support worker severity to the database.
	*
	* @param supportWorkerSeverityId the primary key for the new support worker severity
	* @return the new support worker severity
	*/
	public SupportWorkerSeverity create(long supportWorkerSeverityId);

	/**
	* Removes the support worker severity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity that was removed
	* @throws NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	*/
	public SupportWorkerSeverity remove(long supportWorkerSeverityId)
		throws NoSuchSupportWorkerSeverityException;

	public SupportWorkerSeverity updateImpl(
		SupportWorkerSeverity supportWorkerSeverity);

	/**
	* Returns the support worker severity with the primary key or throws a {@link NoSuchSupportWorkerSeverityException} if it could not be found.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity
	* @throws NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	*/
	public SupportWorkerSeverity findByPrimaryKey(long supportWorkerSeverityId)
		throws NoSuchSupportWorkerSeverityException;

	/**
	* Returns the support worker severity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity, or <code>null</code> if a support worker severity with the primary key could not be found
	*/
	public SupportWorkerSeverity fetchByPrimaryKey(long supportWorkerSeverityId);

	@Override
	public java.util.Map<java.io.Serializable, SupportWorkerSeverity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the support worker severities.
	*
	* @return the support worker severities
	*/
	public java.util.List<SupportWorkerSeverity> findAll();

	/**
	* Returns a range of all the support worker severities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @return the range of support worker severities
	*/
	public java.util.List<SupportWorkerSeverity> findAll(int start, int end);

	/**
	* Returns an ordered range of all the support worker severities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support worker severities
	*/
	public java.util.List<SupportWorkerSeverity> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerSeverity> orderByComparator);

	/**
	* Returns an ordered range of all the support worker severities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of support worker severities
	*/
	public java.util.List<SupportWorkerSeverity> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerSeverity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the support worker severities from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of support worker severities.
	*
	* @return the number of support worker severities
	*/
	public int countAll();
}
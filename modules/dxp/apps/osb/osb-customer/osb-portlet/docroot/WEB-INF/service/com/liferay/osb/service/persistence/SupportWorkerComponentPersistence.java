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

import com.liferay.osb.model.SupportWorkerComponent;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the support worker component service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerComponentPersistenceImpl
 * @see SupportWorkerComponentUtil
 * @generated
 */
public interface SupportWorkerComponentPersistence extends BasePersistence<SupportWorkerComponent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportWorkerComponentUtil} to access the support worker component persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the support worker component in the entity cache if it is enabled.
	*
	* @param supportWorkerComponent the support worker component
	*/
	public void cacheResult(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent);

	/**
	* Caches the support worker components in the entity cache if it is enabled.
	*
	* @param supportWorkerComponents the support worker components
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.SupportWorkerComponent> supportWorkerComponents);

	/**
	* Creates a new support worker component with the primary key. Does not add the support worker component to the database.
	*
	* @param supportWorkerComponentId the primary key for the new support worker component
	* @return the new support worker component
	*/
	public com.liferay.osb.model.SupportWorkerComponent create(
		long supportWorkerComponentId);

	/**
	* Removes the support worker component with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component that was removed
	* @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerComponent remove(
		long supportWorkerComponentId)
		throws com.liferay.osb.NoSuchSupportWorkerComponentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.SupportWorkerComponent updateImpl(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support worker component with the primary key or throws a {@link com.liferay.osb.NoSuchSupportWorkerComponentException} if it could not be found.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component
	* @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerComponent findByPrimaryKey(
		long supportWorkerComponentId)
		throws com.liferay.osb.NoSuchSupportWorkerComponentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support worker component with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component, or <code>null</code> if a support worker component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerComponent fetchByPrimaryKey(
		long supportWorkerComponentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support worker components where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the matching support worker components
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the support worker components where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @return the range of matching support worker components
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the support worker components where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support worker components
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker component
	* @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerComponent findBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerComponentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker component, or <code>null</code> if a matching support worker component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerComponent fetchBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker component
	* @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerComponent findBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerComponentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker component, or <code>null</code> if a matching support worker component could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerComponent fetchBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support worker components before and after the current support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerComponentId the primary key of the current support worker component
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker component
	* @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerComponent[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerComponentId, long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerComponentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support worker components.
	*
	* @return the support worker components
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerComponent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @return the range of support worker components
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerComponent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support worker components
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerComponent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the support worker components where supportWorkerId = &#63; from the database.
	*
	* @param supportWorkerId the support worker ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySupportWorkerId(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the support worker components from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support worker components where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the number of matching support worker components
	* @throws SystemException if a system exception occurred
	*/
	public int countBySupportWorkerId(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support worker components.
	*
	* @return the number of support worker components
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
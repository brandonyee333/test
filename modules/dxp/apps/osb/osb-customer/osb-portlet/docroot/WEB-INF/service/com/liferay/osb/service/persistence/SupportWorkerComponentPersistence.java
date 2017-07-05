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

import com.liferay.osb.exception.NoSuchSupportWorkerComponentException;
import com.liferay.osb.model.SupportWorkerComponent;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the support worker component service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.SupportWorkerComponentPersistenceImpl
 * @see SupportWorkerComponentUtil
 * @generated
 */
@ProviderType
public interface SupportWorkerComponentPersistence extends BasePersistence<SupportWorkerComponent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportWorkerComponentUtil} to access the support worker component persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the support worker components where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the matching support worker components
	*/
	public java.util.List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId);

	/**
	* Returns a range of all the support worker components where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @return the range of matching support worker components
	*/
	public java.util.List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end);

	/**
	* Returns an ordered range of all the support worker components where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support worker components
	*/
	public java.util.List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerComponent> orderByComparator);

	/**
	* Returns an ordered range of all the support worker components where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support worker components
	*/
	public java.util.List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerComponent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker component
	* @throws NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	*/
	public SupportWorkerComponent findBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerComponent> orderByComparator)
		throws NoSuchSupportWorkerComponentException;

	/**
	* Returns the first support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker component, or <code>null</code> if a matching support worker component could not be found
	*/
	public SupportWorkerComponent fetchBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerComponent> orderByComparator);

	/**
	* Returns the last support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker component
	* @throws NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	*/
	public SupportWorkerComponent findBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerComponent> orderByComparator)
		throws NoSuchSupportWorkerComponentException;

	/**
	* Returns the last support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker component, or <code>null</code> if a matching support worker component could not be found
	*/
	public SupportWorkerComponent fetchBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerComponent> orderByComparator);

	/**
	* Returns the support worker components before and after the current support worker component in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerComponentId the primary key of the current support worker component
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker component
	* @throws NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	*/
	public SupportWorkerComponent[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerComponentId, long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerComponent> orderByComparator)
		throws NoSuchSupportWorkerComponentException;

	/**
	* Removes all the support worker components where supportWorkerId = &#63; from the database.
	*
	* @param supportWorkerId the support worker ID
	*/
	public void removeBySupportWorkerId(long supportWorkerId);

	/**
	* Returns the number of support worker components where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the number of matching support worker components
	*/
	public int countBySupportWorkerId(long supportWorkerId);

	/**
	* Caches the support worker component in the entity cache if it is enabled.
	*
	* @param supportWorkerComponent the support worker component
	*/
	public void cacheResult(SupportWorkerComponent supportWorkerComponent);

	/**
	* Caches the support worker components in the entity cache if it is enabled.
	*
	* @param supportWorkerComponents the support worker components
	*/
	public void cacheResult(
		java.util.List<SupportWorkerComponent> supportWorkerComponents);

	/**
	* Creates a new support worker component with the primary key. Does not add the support worker component to the database.
	*
	* @param supportWorkerComponentId the primary key for the new support worker component
	* @return the new support worker component
	*/
	public SupportWorkerComponent create(long supportWorkerComponentId);

	/**
	* Removes the support worker component with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component that was removed
	* @throws NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	*/
	public SupportWorkerComponent remove(long supportWorkerComponentId)
		throws NoSuchSupportWorkerComponentException;

	public SupportWorkerComponent updateImpl(
		SupportWorkerComponent supportWorkerComponent);

	/**
	* Returns the support worker component with the primary key or throws a {@link NoSuchSupportWorkerComponentException} if it could not be found.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component
	* @throws NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	*/
	public SupportWorkerComponent findByPrimaryKey(
		long supportWorkerComponentId)
		throws NoSuchSupportWorkerComponentException;

	/**
	* Returns the support worker component with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component, or <code>null</code> if a support worker component with the primary key could not be found
	*/
	public SupportWorkerComponent fetchByPrimaryKey(
		long supportWorkerComponentId);

	@Override
	public java.util.Map<java.io.Serializable, SupportWorkerComponent> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the support worker components.
	*
	* @return the support worker components
	*/
	public java.util.List<SupportWorkerComponent> findAll();

	/**
	* Returns a range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @return the range of support worker components
	*/
	public java.util.List<SupportWorkerComponent> findAll(int start, int end);

	/**
	* Returns an ordered range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support worker components
	*/
	public java.util.List<SupportWorkerComponent> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerComponent> orderByComparator);

	/**
	* Returns an ordered range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of support worker components
	*/
	public java.util.List<SupportWorkerComponent> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerComponent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the support worker components from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of support worker components.
	*
	* @return the number of support worker components
	*/
	public int countAll();
}
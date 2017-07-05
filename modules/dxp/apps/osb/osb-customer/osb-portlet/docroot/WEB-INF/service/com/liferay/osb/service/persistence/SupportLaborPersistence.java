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

import com.liferay.osb.exception.NoSuchSupportLaborException;
import com.liferay.osb.model.SupportLabor;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the support labor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.SupportLaborPersistenceImpl
 * @see SupportLaborUtil
 * @generated
 */
@ProviderType
public interface SupportLaborPersistence extends BasePersistence<SupportLabor> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportLaborUtil} to access the support labor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the support labor in the entity cache if it is enabled.
	*
	* @param supportLabor the support labor
	*/
	public void cacheResult(SupportLabor supportLabor);

	/**
	* Caches the support labors in the entity cache if it is enabled.
	*
	* @param supportLabors the support labors
	*/
	public void cacheResult(java.util.List<SupportLabor> supportLabors);

	/**
	* Creates a new support labor with the primary key. Does not add the support labor to the database.
	*
	* @param supportLaborId the primary key for the new support labor
	* @return the new support labor
	*/
	public SupportLabor create(long supportLaborId);

	/**
	* Removes the support labor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportLaborId the primary key of the support labor
	* @return the support labor that was removed
	* @throws NoSuchSupportLaborException if a support labor with the primary key could not be found
	*/
	public SupportLabor remove(long supportLaborId)
		throws NoSuchSupportLaborException;

	public SupportLabor updateImpl(SupportLabor supportLabor);

	/**
	* Returns the support labor with the primary key or throws a {@link NoSuchSupportLaborException} if it could not be found.
	*
	* @param supportLaborId the primary key of the support labor
	* @return the support labor
	* @throws NoSuchSupportLaborException if a support labor with the primary key could not be found
	*/
	public SupportLabor findByPrimaryKey(long supportLaborId)
		throws NoSuchSupportLaborException;

	/**
	* Returns the support labor with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportLaborId the primary key of the support labor
	* @return the support labor, or <code>null</code> if a support labor with the primary key could not be found
	*/
	public SupportLabor fetchByPrimaryKey(long supportLaborId);

	@Override
	public java.util.Map<java.io.Serializable, SupportLabor> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the support labors.
	*
	* @return the support labors
	*/
	public java.util.List<SupportLabor> findAll();

	/**
	* Returns a range of all the support labors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support labors
	* @param end the upper bound of the range of support labors (not inclusive)
	* @return the range of support labors
	*/
	public java.util.List<SupportLabor> findAll(int start, int end);

	/**
	* Returns an ordered range of all the support labors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support labors
	* @param end the upper bound of the range of support labors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support labors
	*/
	public java.util.List<SupportLabor> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportLabor> orderByComparator);

	/**
	* Returns an ordered range of all the support labors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support labors
	* @param end the upper bound of the range of support labors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of support labors
	*/
	public java.util.List<SupportLabor> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportLabor> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the support labors from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of support labors.
	*
	* @return the number of support labors
	*/
	public int countAll();
}
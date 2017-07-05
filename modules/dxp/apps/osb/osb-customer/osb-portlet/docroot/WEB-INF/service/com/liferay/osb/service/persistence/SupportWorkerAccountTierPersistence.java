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

import com.liferay.osb.exception.NoSuchSupportWorkerAccountTierException;
import com.liferay.osb.model.SupportWorkerAccountTier;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the support worker account tier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.SupportWorkerAccountTierPersistenceImpl
 * @see SupportWorkerAccountTierUtil
 * @generated
 */
@ProviderType
public interface SupportWorkerAccountTierPersistence extends BasePersistence<SupportWorkerAccountTier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportWorkerAccountTierUtil} to access the support worker account tier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the support worker account tiers where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the matching support worker account tiers
	*/
	public java.util.List<SupportWorkerAccountTier> findBySupportWorkerId(
		long supportWorkerId);

	/**
	* Returns a range of all the support worker account tiers where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @return the range of matching support worker account tiers
	*/
	public java.util.List<SupportWorkerAccountTier> findBySupportWorkerId(
		long supportWorkerId, int start, int end);

	/**
	* Returns an ordered range of all the support worker account tiers where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support worker account tiers
	*/
	public java.util.List<SupportWorkerAccountTier> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerAccountTier> orderByComparator);

	/**
	* Returns an ordered range of all the support worker account tiers where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support worker account tiers
	*/
	public java.util.List<SupportWorkerAccountTier> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerAccountTier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first support worker account tier in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker account tier
	* @throws NoSuchSupportWorkerAccountTierException if a matching support worker account tier could not be found
	*/
	public SupportWorkerAccountTier findBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerAccountTier> orderByComparator)
		throws NoSuchSupportWorkerAccountTierException;

	/**
	* Returns the first support worker account tier in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker account tier, or <code>null</code> if a matching support worker account tier could not be found
	*/
	public SupportWorkerAccountTier fetchBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerAccountTier> orderByComparator);

	/**
	* Returns the last support worker account tier in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker account tier
	* @throws NoSuchSupportWorkerAccountTierException if a matching support worker account tier could not be found
	*/
	public SupportWorkerAccountTier findBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerAccountTier> orderByComparator)
		throws NoSuchSupportWorkerAccountTierException;

	/**
	* Returns the last support worker account tier in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker account tier, or <code>null</code> if a matching support worker account tier could not be found
	*/
	public SupportWorkerAccountTier fetchBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerAccountTier> orderByComparator);

	/**
	* Returns the support worker account tiers before and after the current support worker account tier in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerAccountTierId the primary key of the current support worker account tier
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker account tier
	* @throws NoSuchSupportWorkerAccountTierException if a support worker account tier with the primary key could not be found
	*/
	public SupportWorkerAccountTier[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerAccountTierId, long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerAccountTier> orderByComparator)
		throws NoSuchSupportWorkerAccountTierException;

	/**
	* Removes all the support worker account tiers where supportWorkerId = &#63; from the database.
	*
	* @param supportWorkerId the support worker ID
	*/
	public void removeBySupportWorkerId(long supportWorkerId);

	/**
	* Returns the number of support worker account tiers where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the number of matching support worker account tiers
	*/
	public int countBySupportWorkerId(long supportWorkerId);

	/**
	* Caches the support worker account tier in the entity cache if it is enabled.
	*
	* @param supportWorkerAccountTier the support worker account tier
	*/
	public void cacheResult(SupportWorkerAccountTier supportWorkerAccountTier);

	/**
	* Caches the support worker account tiers in the entity cache if it is enabled.
	*
	* @param supportWorkerAccountTiers the support worker account tiers
	*/
	public void cacheResult(
		java.util.List<SupportWorkerAccountTier> supportWorkerAccountTiers);

	/**
	* Creates a new support worker account tier with the primary key. Does not add the support worker account tier to the database.
	*
	* @param supportWorkerAccountTierId the primary key for the new support worker account tier
	* @return the new support worker account tier
	*/
	public SupportWorkerAccountTier create(long supportWorkerAccountTierId);

	/**
	* Removes the support worker account tier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerAccountTierId the primary key of the support worker account tier
	* @return the support worker account tier that was removed
	* @throws NoSuchSupportWorkerAccountTierException if a support worker account tier with the primary key could not be found
	*/
	public SupportWorkerAccountTier remove(long supportWorkerAccountTierId)
		throws NoSuchSupportWorkerAccountTierException;

	public SupportWorkerAccountTier updateImpl(
		SupportWorkerAccountTier supportWorkerAccountTier);

	/**
	* Returns the support worker account tier with the primary key or throws a {@link NoSuchSupportWorkerAccountTierException} if it could not be found.
	*
	* @param supportWorkerAccountTierId the primary key of the support worker account tier
	* @return the support worker account tier
	* @throws NoSuchSupportWorkerAccountTierException if a support worker account tier with the primary key could not be found
	*/
	public SupportWorkerAccountTier findByPrimaryKey(
		long supportWorkerAccountTierId)
		throws NoSuchSupportWorkerAccountTierException;

	/**
	* Returns the support worker account tier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportWorkerAccountTierId the primary key of the support worker account tier
	* @return the support worker account tier, or <code>null</code> if a support worker account tier with the primary key could not be found
	*/
	public SupportWorkerAccountTier fetchByPrimaryKey(
		long supportWorkerAccountTierId);

	@Override
	public java.util.Map<java.io.Serializable, SupportWorkerAccountTier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the support worker account tiers.
	*
	* @return the support worker account tiers
	*/
	public java.util.List<SupportWorkerAccountTier> findAll();

	/**
	* Returns a range of all the support worker account tiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @return the range of support worker account tiers
	*/
	public java.util.List<SupportWorkerAccountTier> findAll(int start, int end);

	/**
	* Returns an ordered range of all the support worker account tiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support worker account tiers
	*/
	public java.util.List<SupportWorkerAccountTier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerAccountTier> orderByComparator);

	/**
	* Returns an ordered range of all the support worker account tiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of support worker account tiers
	*/
	public java.util.List<SupportWorkerAccountTier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportWorkerAccountTier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the support worker account tiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of support worker account tiers.
	*
	* @return the number of support worker account tiers
	*/
	public int countAll();
}
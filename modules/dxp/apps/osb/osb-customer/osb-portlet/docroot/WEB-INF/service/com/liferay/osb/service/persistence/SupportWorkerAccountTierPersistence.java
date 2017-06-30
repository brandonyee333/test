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

import com.liferay.osb.model.SupportWorkerAccountTier;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the support worker account tier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerAccountTierPersistenceImpl
 * @see SupportWorkerAccountTierUtil
 * @generated
 */
public interface SupportWorkerAccountTierPersistence extends BasePersistence<SupportWorkerAccountTier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportWorkerAccountTierUtil} to access the support worker account tier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the support worker account tier in the entity cache if it is enabled.
	*
	* @param supportWorkerAccountTier the support worker account tier
	*/
	public void cacheResult(
		com.liferay.osb.model.SupportWorkerAccountTier supportWorkerAccountTier);

	/**
	* Caches the support worker account tiers in the entity cache if it is enabled.
	*
	* @param supportWorkerAccountTiers the support worker account tiers
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.SupportWorkerAccountTier> supportWorkerAccountTiers);

	/**
	* Creates a new support worker account tier with the primary key. Does not add the support worker account tier to the database.
	*
	* @param supportWorkerAccountTierId the primary key for the new support worker account tier
	* @return the new support worker account tier
	*/
	public com.liferay.osb.model.SupportWorkerAccountTier create(
		long supportWorkerAccountTierId);

	/**
	* Removes the support worker account tier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerAccountTierId the primary key of the support worker account tier
	* @return the support worker account tier that was removed
	* @throws com.liferay.osb.NoSuchSupportWorkerAccountTierException if a support worker account tier with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerAccountTier remove(
		long supportWorkerAccountTierId)
		throws com.liferay.osb.NoSuchSupportWorkerAccountTierException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.SupportWorkerAccountTier updateImpl(
		com.liferay.osb.model.SupportWorkerAccountTier supportWorkerAccountTier,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support worker account tier with the primary key or throws a {@link com.liferay.osb.NoSuchSupportWorkerAccountTierException} if it could not be found.
	*
	* @param supportWorkerAccountTierId the primary key of the support worker account tier
	* @return the support worker account tier
	* @throws com.liferay.osb.NoSuchSupportWorkerAccountTierException if a support worker account tier with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerAccountTier findByPrimaryKey(
		long supportWorkerAccountTierId)
		throws com.liferay.osb.NoSuchSupportWorkerAccountTierException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support worker account tier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportWorkerAccountTierId the primary key of the support worker account tier
	* @return the support worker account tier, or <code>null</code> if a support worker account tier with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerAccountTier fetchByPrimaryKey(
		long supportWorkerAccountTierId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support worker account tiers where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the matching support worker account tiers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerAccountTier> findBySupportWorkerId(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the support worker account tiers where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @return the range of matching support worker account tiers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerAccountTier> findBySupportWorkerId(
		long supportWorkerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the support worker account tiers where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support worker account tiers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerAccountTier> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support worker account tier in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker account tier
	* @throws com.liferay.osb.NoSuchSupportWorkerAccountTierException if a matching support worker account tier could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerAccountTier findBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerAccountTierException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support worker account tier in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker account tier, or <code>null</code> if a matching support worker account tier could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerAccountTier fetchBySupportWorkerId_First(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support worker account tier in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker account tier
	* @throws com.liferay.osb.NoSuchSupportWorkerAccountTierException if a matching support worker account tier could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerAccountTier findBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerAccountTierException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support worker account tier in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker account tier, or <code>null</code> if a matching support worker account tier could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerAccountTier fetchBySupportWorkerId_Last(
		long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support worker account tiers before and after the current support worker account tier in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerAccountTierId the primary key of the current support worker account tier
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker account tier
	* @throws com.liferay.osb.NoSuchSupportWorkerAccountTierException if a support worker account tier with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportWorkerAccountTier[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerAccountTierId, long supportWorkerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportWorkerAccountTierException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support worker account tiers.
	*
	* @return the support worker account tiers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerAccountTier> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the support worker account tiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @return the range of support worker account tiers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerAccountTier> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the support worker account tiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support worker account tiers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportWorkerAccountTier> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the support worker account tiers where supportWorkerId = &#63; from the database.
	*
	* @param supportWorkerId the support worker ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySupportWorkerId(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the support worker account tiers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support worker account tiers where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the number of matching support worker account tiers
	* @throws SystemException if a system exception occurred
	*/
	public int countBySupportWorkerId(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support worker account tiers.
	*
	* @return the number of support worker account tiers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
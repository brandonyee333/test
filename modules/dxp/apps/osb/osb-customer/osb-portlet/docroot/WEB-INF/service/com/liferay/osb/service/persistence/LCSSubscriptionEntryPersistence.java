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

import com.liferay.osb.model.LCSSubscriptionEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LCSSubscriptionEntryPersistenceImpl
 * @see LCSSubscriptionEntryUtil
 * @generated
 */
public interface LCSSubscriptionEntryPersistence extends BasePersistence<LCSSubscriptionEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSSubscriptionEntryUtil} to access the l c s subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the l c s subscription entry in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntry the l c s subscription entry
	*/
	public void cacheResult(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry);

	/**
	* Caches the l c s subscription entries in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntries the l c s subscription entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> lcsSubscriptionEntries);

	/**
	* Creates a new l c s subscription entry with the primary key. Does not add the l c s subscription entry to the database.
	*
	* @param lcsSubscriptionEntryId the primary key for the new l c s subscription entry
	* @return the new l c s subscription entry
	*/
	public com.liferay.osb.model.LCSSubscriptionEntry create(
		long lcsSubscriptionEntryId);

	/**
	* Removes the l c s subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry that was removed
	* @throws com.liferay.osb.NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LCSSubscriptionEntry remove(
		long lcsSubscriptionEntryId)
		throws com.liferay.osb.NoSuchLCSSubscriptionEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.LCSSubscriptionEntry updateImpl(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the l c s subscription entry with the primary key or throws a {@link com.liferay.osb.NoSuchLCSSubscriptionEntryException} if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry
	* @throws com.liferay.osb.NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LCSSubscriptionEntry findByPrimaryKey(
		long lcsSubscriptionEntryId)
		throws com.liferay.osb.NoSuchLCSSubscriptionEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the l c s subscription entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry, or <code>null</code> if a l c s subscription entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LCSSubscriptionEntry fetchByPrimaryKey(
		long lcsSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the l c s subscription entries.
	*
	* @return the l c s subscription entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the l c s subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @return the range of l c s subscription entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the l c s subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s subscription entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the l c s subscription entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of l c s subscription entries.
	*
	* @return the number of l c s subscription entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}
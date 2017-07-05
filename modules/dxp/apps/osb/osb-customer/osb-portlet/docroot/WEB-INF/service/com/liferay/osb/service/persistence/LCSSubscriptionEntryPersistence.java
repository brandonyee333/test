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

import com.liferay.osb.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.osb.model.LCSSubscriptionEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the lcs subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.LCSSubscriptionEntryPersistenceImpl
 * @see LCSSubscriptionEntryUtil
 * @generated
 */
@ProviderType
public interface LCSSubscriptionEntryPersistence extends BasePersistence<LCSSubscriptionEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSSubscriptionEntryUtil} to access the lcs subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the lcs subscription entry in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntry the lcs subscription entry
	*/
	public void cacheResult(LCSSubscriptionEntry lcsSubscriptionEntry);

	/**
	* Caches the lcs subscription entries in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntries the lcs subscription entries
	*/
	public void cacheResult(
		java.util.List<LCSSubscriptionEntry> lcsSubscriptionEntries);

	/**
	* Creates a new lcs subscription entry with the primary key. Does not add the lcs subscription entry to the database.
	*
	* @param lcsSubscriptionEntryId the primary key for the new lcs subscription entry
	* @return the new lcs subscription entry
	*/
	public LCSSubscriptionEntry create(long lcsSubscriptionEntryId);

	/**
	* Removes the lcs subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntryId the primary key of the lcs subscription entry
	* @return the lcs subscription entry that was removed
	* @throws NoSuchLCSSubscriptionEntryException if a lcs subscription entry with the primary key could not be found
	*/
	public LCSSubscriptionEntry remove(long lcsSubscriptionEntryId)
		throws NoSuchLCSSubscriptionEntryException;

	public LCSSubscriptionEntry updateImpl(
		LCSSubscriptionEntry lcsSubscriptionEntry);

	/**
	* Returns the lcs subscription entry with the primary key or throws a {@link NoSuchLCSSubscriptionEntryException} if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the lcs subscription entry
	* @return the lcs subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a lcs subscription entry with the primary key could not be found
	*/
	public LCSSubscriptionEntry findByPrimaryKey(long lcsSubscriptionEntryId)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Returns the lcs subscription entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the lcs subscription entry
	* @return the lcs subscription entry, or <code>null</code> if a lcs subscription entry with the primary key could not be found
	*/
	public LCSSubscriptionEntry fetchByPrimaryKey(long lcsSubscriptionEntryId);

	@Override
	public java.util.Map<java.io.Serializable, LCSSubscriptionEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the lcs subscription entries.
	*
	* @return the lcs subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findAll();

	/**
	* Returns a range of all the lcs subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lcs subscription entries
	* @param end the upper bound of the range of lcs subscription entries (not inclusive)
	* @return the range of lcs subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the lcs subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lcs subscription entries
	* @param end the upper bound of the range of lcs subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of lcs subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the lcs subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lcs subscription entries
	* @param end the upper bound of the range of lcs subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of lcs subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the lcs subscription entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of lcs subscription entries.
	*
	* @return the number of lcs subscription entries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}
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

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopStatsEntryException;
import com.liferay.osb.loop.model.LoopStatsEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the loop stats entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.loop.service.persistence.impl.LoopStatsEntryPersistenceImpl
 * @see LoopStatsEntryUtil
 * @generated
 */
@ProviderType
public interface LoopStatsEntryPersistence extends BasePersistence<LoopStatsEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopStatsEntryUtil} to access the loop stats entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the loop stats entry in the entity cache if it is enabled.
	*
	* @param loopStatsEntry the loop stats entry
	*/
	public void cacheResult(LoopStatsEntry loopStatsEntry);

	/**
	* Caches the loop stats entries in the entity cache if it is enabled.
	*
	* @param loopStatsEntries the loop stats entries
	*/
	public void cacheResult(java.util.List<LoopStatsEntry> loopStatsEntries);

	/**
	* Creates a new loop stats entry with the primary key. Does not add the loop stats entry to the database.
	*
	* @param loopStatsEntryId the primary key for the new loop stats entry
	* @return the new loop stats entry
	*/
	public LoopStatsEntry create(long loopStatsEntryId);

	/**
	* Removes the loop stats entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopStatsEntryId the primary key of the loop stats entry
	* @return the loop stats entry that was removed
	* @throws NoSuchLoopStatsEntryException if a loop stats entry with the primary key could not be found
	*/
	public LoopStatsEntry remove(long loopStatsEntryId)
		throws NoSuchLoopStatsEntryException;

	public LoopStatsEntry updateImpl(LoopStatsEntry loopStatsEntry);

	/**
	* Returns the loop stats entry with the primary key or throws a {@link NoSuchLoopStatsEntryException} if it could not be found.
	*
	* @param loopStatsEntryId the primary key of the loop stats entry
	* @return the loop stats entry
	* @throws NoSuchLoopStatsEntryException if a loop stats entry with the primary key could not be found
	*/
	public LoopStatsEntry findByPrimaryKey(long loopStatsEntryId)
		throws NoSuchLoopStatsEntryException;

	/**
	* Returns the loop stats entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopStatsEntryId the primary key of the loop stats entry
	* @return the loop stats entry, or <code>null</code> if a loop stats entry with the primary key could not be found
	*/
	public LoopStatsEntry fetchByPrimaryKey(long loopStatsEntryId);

	@Override
	public java.util.Map<java.io.Serializable, LoopStatsEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the loop stats entries.
	*
	* @return the loop stats entries
	*/
	public java.util.List<LoopStatsEntry> findAll();

	/**
	* Returns a range of all the loop stats entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStatsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop stats entries
	* @param end the upper bound of the range of loop stats entries (not inclusive)
	* @return the range of loop stats entries
	*/
	public java.util.List<LoopStatsEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the loop stats entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStatsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop stats entries
	* @param end the upper bound of the range of loop stats entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop stats entries
	*/
	public java.util.List<LoopStatsEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopStatsEntry> orderByComparator);

	/**
	* Returns an ordered range of all the loop stats entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStatsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop stats entries
	* @param end the upper bound of the range of loop stats entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop stats entries
	*/
	public java.util.List<LoopStatsEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopStatsEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the loop stats entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of loop stats entries.
	*
	* @return the number of loop stats entries
	*/
	public int countAll();
}
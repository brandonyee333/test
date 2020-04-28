/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopStatsEntryException;
import com.liferay.osb.loop.model.LoopStatsEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the loop stats entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStatsEntryUtil
 * @generated
 */
@ProviderType
public interface LoopStatsEntryPersistence
	extends BasePersistence<LoopStatsEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopStatsEntryUtil} to access the loop stats entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, LoopStatsEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

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
	 * Returns the loop stats entry with the primary key or throws a <code>NoSuchLoopStatsEntryException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStatsEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStatsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop stats entries
	 * @param end the upper bound of the range of loop stats entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop stats entries
	 */
	public java.util.List<LoopStatsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopStatsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the loop stats entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStatsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop stats entries
	 * @param end the upper bound of the range of loop stats entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop stats entries
	 */
	public java.util.List<LoopStatsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopStatsEntry>
			orderByComparator,
		boolean useFinderCache);

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
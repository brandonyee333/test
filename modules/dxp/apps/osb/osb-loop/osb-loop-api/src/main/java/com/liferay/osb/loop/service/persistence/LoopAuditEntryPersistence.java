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

import com.liferay.osb.loop.exception.NoSuchLoopAuditEntryException;
import com.liferay.osb.loop.model.LoopAuditEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the loop audit entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopAuditEntryUtil
 * @generated
 */
@ProviderType
public interface LoopAuditEntryPersistence
	extends BasePersistence<LoopAuditEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopAuditEntryUtil} to access the loop audit entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, LoopAuditEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the loop audit entry in the entity cache if it is enabled.
	 *
	 * @param loopAuditEntry the loop audit entry
	 */
	public void cacheResult(LoopAuditEntry loopAuditEntry);

	/**
	 * Caches the loop audit entries in the entity cache if it is enabled.
	 *
	 * @param loopAuditEntries the loop audit entries
	 */
	public void cacheResult(java.util.List<LoopAuditEntry> loopAuditEntries);

	/**
	 * Creates a new loop audit entry with the primary key. Does not add the loop audit entry to the database.
	 *
	 * @param loopAuditEntryId the primary key for the new loop audit entry
	 * @return the new loop audit entry
	 */
	public LoopAuditEntry create(long loopAuditEntryId);

	/**
	 * Removes the loop audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopAuditEntryId the primary key of the loop audit entry
	 * @return the loop audit entry that was removed
	 * @throws NoSuchLoopAuditEntryException if a loop audit entry with the primary key could not be found
	 */
	public LoopAuditEntry remove(long loopAuditEntryId)
		throws NoSuchLoopAuditEntryException;

	public LoopAuditEntry updateImpl(LoopAuditEntry loopAuditEntry);

	/**
	 * Returns the loop audit entry with the primary key or throws a <code>NoSuchLoopAuditEntryException</code> if it could not be found.
	 *
	 * @param loopAuditEntryId the primary key of the loop audit entry
	 * @return the loop audit entry
	 * @throws NoSuchLoopAuditEntryException if a loop audit entry with the primary key could not be found
	 */
	public LoopAuditEntry findByPrimaryKey(long loopAuditEntryId)
		throws NoSuchLoopAuditEntryException;

	/**
	 * Returns the loop audit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopAuditEntryId the primary key of the loop audit entry
	 * @return the loop audit entry, or <code>null</code> if a loop audit entry with the primary key could not be found
	 */
	public LoopAuditEntry fetchByPrimaryKey(long loopAuditEntryId);

	/**
	 * Returns all the loop audit entries.
	 *
	 * @return the loop audit entries
	 */
	public java.util.List<LoopAuditEntry> findAll();

	/**
	 * Returns a range of all the loop audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopAuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop audit entries
	 * @param end the upper bound of the range of loop audit entries (not inclusive)
	 * @return the range of loop audit entries
	 */
	public java.util.List<LoopAuditEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the loop audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopAuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop audit entries
	 * @param end the upper bound of the range of loop audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop audit entries
	 */
	public java.util.List<LoopAuditEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopAuditEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the loop audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopAuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop audit entries
	 * @param end the upper bound of the range of loop audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop audit entries
	 */
	public java.util.List<LoopAuditEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopAuditEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the loop audit entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of loop audit entries.
	 *
	 * @return the number of loop audit entries
	 */
	public int countAll();

}
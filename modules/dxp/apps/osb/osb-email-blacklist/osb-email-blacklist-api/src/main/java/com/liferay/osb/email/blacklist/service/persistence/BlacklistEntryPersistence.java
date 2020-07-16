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

package com.liferay.osb.email.blacklist.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.email.blacklist.exception.NoSuchBlacklistEntryException;
import com.liferay.osb.email.blacklist.model.BlacklistEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the blacklist entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see BlacklistEntryUtil
 * @generated
 */
@ProviderType
public interface BlacklistEntryPersistence
	extends BasePersistence<BlacklistEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BlacklistEntryUtil} to access the blacklist entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, BlacklistEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the blacklist entry where emailAddress = &#63; or throws a <code>NoSuchBlacklistEntryException</code> if it could not be found.
	 *
	 * @param emailAddress the email address
	 * @return the matching blacklist entry
	 * @throws NoSuchBlacklistEntryException if a matching blacklist entry could not be found
	 */
	public BlacklistEntry findByEmailAddress(String emailAddress)
		throws NoSuchBlacklistEntryException;

	/**
	 * Returns the blacklist entry where emailAddress = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param emailAddress the email address
	 * @return the matching blacklist entry, or <code>null</code> if a matching blacklist entry could not be found
	 */
	public BlacklistEntry fetchByEmailAddress(String emailAddress);

	/**
	 * Returns the blacklist entry where emailAddress = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param emailAddress the email address
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching blacklist entry, or <code>null</code> if a matching blacklist entry could not be found
	 */
	public BlacklistEntry fetchByEmailAddress(
		String emailAddress, boolean useFinderCache);

	/**
	 * Removes the blacklist entry where emailAddress = &#63; from the database.
	 *
	 * @param emailAddress the email address
	 * @return the blacklist entry that was removed
	 */
	public BlacklistEntry removeByEmailAddress(String emailAddress)
		throws NoSuchBlacklistEntryException;

	/**
	 * Returns the number of blacklist entries where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @return the number of matching blacklist entries
	 */
	public int countByEmailAddress(String emailAddress);

	/**
	 * Caches the blacklist entry in the entity cache if it is enabled.
	 *
	 * @param blacklistEntry the blacklist entry
	 */
	public void cacheResult(BlacklistEntry blacklistEntry);

	/**
	 * Caches the blacklist entries in the entity cache if it is enabled.
	 *
	 * @param blacklistEntries the blacklist entries
	 */
	public void cacheResult(java.util.List<BlacklistEntry> blacklistEntries);

	/**
	 * Creates a new blacklist entry with the primary key. Does not add the blacklist entry to the database.
	 *
	 * @param blacklistEntryId the primary key for the new blacklist entry
	 * @return the new blacklist entry
	 */
	public BlacklistEntry create(long blacklistEntryId);

	/**
	 * Removes the blacklist entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param blacklistEntryId the primary key of the blacklist entry
	 * @return the blacklist entry that was removed
	 * @throws NoSuchBlacklistEntryException if a blacklist entry with the primary key could not be found
	 */
	public BlacklistEntry remove(long blacklistEntryId)
		throws NoSuchBlacklistEntryException;

	public BlacklistEntry updateImpl(BlacklistEntry blacklistEntry);

	/**
	 * Returns the blacklist entry with the primary key or throws a <code>NoSuchBlacklistEntryException</code> if it could not be found.
	 *
	 * @param blacklistEntryId the primary key of the blacklist entry
	 * @return the blacklist entry
	 * @throws NoSuchBlacklistEntryException if a blacklist entry with the primary key could not be found
	 */
	public BlacklistEntry findByPrimaryKey(long blacklistEntryId)
		throws NoSuchBlacklistEntryException;

	/**
	 * Returns the blacklist entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param blacklistEntryId the primary key of the blacklist entry
	 * @return the blacklist entry, or <code>null</code> if a blacklist entry with the primary key could not be found
	 */
	public BlacklistEntry fetchByPrimaryKey(long blacklistEntryId);

	/**
	 * Returns all the blacklist entries.
	 *
	 * @return the blacklist entries
	 */
	public java.util.List<BlacklistEntry> findAll();

	/**
	 * Returns a range of all the blacklist entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlacklistEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of blacklist entries
	 * @param end the upper bound of the range of blacklist entries (not inclusive)
	 * @return the range of blacklist entries
	 */
	public java.util.List<BlacklistEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the blacklist entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlacklistEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of blacklist entries
	 * @param end the upper bound of the range of blacklist entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of blacklist entries
	 */
	public java.util.List<BlacklistEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlacklistEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the blacklist entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlacklistEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of blacklist entries
	 * @param end the upper bound of the range of blacklist entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of blacklist entries
	 */
	public java.util.List<BlacklistEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlacklistEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the blacklist entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of blacklist entries.
	 *
	 * @return the number of blacklist entries
	 */
	public int countAll();

}
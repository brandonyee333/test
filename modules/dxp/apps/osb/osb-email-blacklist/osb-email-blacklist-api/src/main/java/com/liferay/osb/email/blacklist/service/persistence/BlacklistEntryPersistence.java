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

package com.liferay.osb.email.blacklist.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.email.blacklist.exception.NoSuchBlacklistEntryException;
import com.liferay.osb.email.blacklist.model.BlacklistEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the blacklist entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see com.liferay.osb.email.blacklist.service.persistence.impl.BlacklistEntryPersistenceImpl
 * @see BlacklistEntryUtil
 * @generated
 */
@ProviderType
public interface BlacklistEntryPersistence extends BasePersistence<BlacklistEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BlacklistEntryUtil} to access the blacklist entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the blacklist entry where emailAddress = &#63; or throws a {@link NoSuchBlacklistEntryException} if it could not be found.
	*
	* @param emailAddress the email address
	* @return the matching blacklist entry
	* @throws NoSuchBlacklistEntryException if a matching blacklist entry could not be found
	*/
	public BlacklistEntry findByEmailAddress(java.lang.String emailAddress)
		throws NoSuchBlacklistEntryException;

	/**
	* Returns the blacklist entry where emailAddress = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param emailAddress the email address
	* @return the matching blacklist entry, or <code>null</code> if a matching blacklist entry could not be found
	*/
	public BlacklistEntry fetchByEmailAddress(java.lang.String emailAddress);

	/**
	* Returns the blacklist entry where emailAddress = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param emailAddress the email address
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching blacklist entry, or <code>null</code> if a matching blacklist entry could not be found
	*/
	public BlacklistEntry fetchByEmailAddress(java.lang.String emailAddress,
		boolean retrieveFromCache);

	/**
	* Removes the blacklist entry where emailAddress = &#63; from the database.
	*
	* @param emailAddress the email address
	* @return the blacklist entry that was removed
	*/
	public BlacklistEntry removeByEmailAddress(java.lang.String emailAddress)
		throws NoSuchBlacklistEntryException;

	/**
	* Returns the number of blacklist entries where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @return the number of matching blacklist entries
	*/
	public int countByEmailAddress(java.lang.String emailAddress);

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
	* Returns the blacklist entry with the primary key or throws a {@link NoSuchBlacklistEntryException} if it could not be found.
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

	@Override
	public java.util.Map<java.io.Serializable, BlacklistEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BlacklistEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BlacklistEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of blacklist entries
	* @param end the upper bound of the range of blacklist entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of blacklist entries
	*/
	public java.util.List<BlacklistEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlacklistEntry> orderByComparator);

	/**
	* Returns an ordered range of all the blacklist entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BlacklistEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of blacklist entries
	* @param end the upper bound of the range of blacklist entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of blacklist entries
	*/
	public java.util.List<BlacklistEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlacklistEntry> orderByComparator,
		boolean retrieveFromCache);

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
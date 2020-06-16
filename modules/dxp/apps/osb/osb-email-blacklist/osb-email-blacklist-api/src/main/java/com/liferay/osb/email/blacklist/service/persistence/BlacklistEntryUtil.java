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

import com.liferay.osb.email.blacklist.model.BlacklistEntry;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the blacklist entry service. This utility wraps {@link com.liferay.osb.email.blacklist.service.persistence.impl.BlacklistEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see BlacklistEntryPersistence
 * @see com.liferay.osb.email.blacklist.service.persistence.impl.BlacklistEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class BlacklistEntryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(BlacklistEntry blacklistEntry) {
		getPersistence().clearCache(blacklistEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<BlacklistEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BlacklistEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BlacklistEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BlacklistEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BlacklistEntry update(BlacklistEntry blacklistEntry) {
		return getPersistence().update(blacklistEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BlacklistEntry update(BlacklistEntry blacklistEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(blacklistEntry, serviceContext);
	}

	/**
	* Returns the blacklist entry where emailAddress = &#63; or throws a {@link NoSuchBlacklistEntryException} if it could not be found.
	*
	* @param emailAddress the email address
	* @return the matching blacklist entry
	* @throws NoSuchBlacklistEntryException if a matching blacklist entry could not be found
	*/
	public static BlacklistEntry findByEmailAddress(
		java.lang.String emailAddress)
		throws com.liferay.osb.email.blacklist.exception.NoSuchBlacklistEntryException {
		return getPersistence().findByEmailAddress(emailAddress);
	}

	/**
	* Returns the blacklist entry where emailAddress = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param emailAddress the email address
	* @return the matching blacklist entry, or <code>null</code> if a matching blacklist entry could not be found
	*/
	public static BlacklistEntry fetchByEmailAddress(
		java.lang.String emailAddress) {
		return getPersistence().fetchByEmailAddress(emailAddress);
	}

	/**
	* Returns the blacklist entry where emailAddress = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param emailAddress the email address
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching blacklist entry, or <code>null</code> if a matching blacklist entry could not be found
	*/
	public static BlacklistEntry fetchByEmailAddress(
		java.lang.String emailAddress, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByEmailAddress(emailAddress, retrieveFromCache);
	}

	/**
	* Removes the blacklist entry where emailAddress = &#63; from the database.
	*
	* @param emailAddress the email address
	* @return the blacklist entry that was removed
	*/
	public static BlacklistEntry removeByEmailAddress(
		java.lang.String emailAddress)
		throws com.liferay.osb.email.blacklist.exception.NoSuchBlacklistEntryException {
		return getPersistence().removeByEmailAddress(emailAddress);
	}

	/**
	* Returns the number of blacklist entries where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @return the number of matching blacklist entries
	*/
	public static int countByEmailAddress(java.lang.String emailAddress) {
		return getPersistence().countByEmailAddress(emailAddress);
	}

	/**
	* Caches the blacklist entry in the entity cache if it is enabled.
	*
	* @param blacklistEntry the blacklist entry
	*/
	public static void cacheResult(BlacklistEntry blacklistEntry) {
		getPersistence().cacheResult(blacklistEntry);
	}

	/**
	* Caches the blacklist entries in the entity cache if it is enabled.
	*
	* @param blacklistEntries the blacklist entries
	*/
	public static void cacheResult(List<BlacklistEntry> blacklistEntries) {
		getPersistence().cacheResult(blacklistEntries);
	}

	/**
	* Creates a new blacklist entry with the primary key. Does not add the blacklist entry to the database.
	*
	* @param blacklistEntryId the primary key for the new blacklist entry
	* @return the new blacklist entry
	*/
	public static BlacklistEntry create(long blacklistEntryId) {
		return getPersistence().create(blacklistEntryId);
	}

	/**
	* Removes the blacklist entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param blacklistEntryId the primary key of the blacklist entry
	* @return the blacklist entry that was removed
	* @throws NoSuchBlacklistEntryException if a blacklist entry with the primary key could not be found
	*/
	public static BlacklistEntry remove(long blacklistEntryId)
		throws com.liferay.osb.email.blacklist.exception.NoSuchBlacklistEntryException {
		return getPersistence().remove(blacklistEntryId);
	}

	public static BlacklistEntry updateImpl(BlacklistEntry blacklistEntry) {
		return getPersistence().updateImpl(blacklistEntry);
	}

	/**
	* Returns the blacklist entry with the primary key or throws a {@link NoSuchBlacklistEntryException} if it could not be found.
	*
	* @param blacklistEntryId the primary key of the blacklist entry
	* @return the blacklist entry
	* @throws NoSuchBlacklistEntryException if a blacklist entry with the primary key could not be found
	*/
	public static BlacklistEntry findByPrimaryKey(long blacklistEntryId)
		throws com.liferay.osb.email.blacklist.exception.NoSuchBlacklistEntryException {
		return getPersistence().findByPrimaryKey(blacklistEntryId);
	}

	/**
	* Returns the blacklist entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param blacklistEntryId the primary key of the blacklist entry
	* @return the blacklist entry, or <code>null</code> if a blacklist entry with the primary key could not be found
	*/
	public static BlacklistEntry fetchByPrimaryKey(long blacklistEntryId) {
		return getPersistence().fetchByPrimaryKey(blacklistEntryId);
	}

	public static java.util.Map<java.io.Serializable, BlacklistEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the blacklist entries.
	*
	* @return the blacklist entries
	*/
	public static List<BlacklistEntry> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<BlacklistEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<BlacklistEntry> findAll(int start, int end,
		OrderByComparator<BlacklistEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<BlacklistEntry> findAll(int start, int end,
		OrderByComparator<BlacklistEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the blacklist entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of blacklist entries.
	*
	* @return the number of blacklist entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static BlacklistEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BlacklistEntryPersistence, BlacklistEntryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(BlacklistEntryPersistence.class);
}
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

package com.liferay.osb.loop.token.auth.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the token auth entry service. This utility wraps {@link com.liferay.osb.loop.token.auth.service.persistence.impl.TokenAuthEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bruno Farache
 * @see TokenAuthEntryPersistence
 * @see com.liferay.osb.loop.token.auth.service.persistence.impl.TokenAuthEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class TokenAuthEntryUtil {
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
	public static void clearCache(TokenAuthEntry tokenAuthEntry) {
		getPersistence().clearCache(tokenAuthEntry);
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
	public static List<TokenAuthEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TokenAuthEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TokenAuthEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TokenAuthEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TokenAuthEntry update(TokenAuthEntry tokenAuthEntry) {
		return getPersistence().update(tokenAuthEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TokenAuthEntry update(TokenAuthEntry tokenAuthEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(tokenAuthEntry, serviceContext);
	}

	/**
	* Returns all the token auth entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching token auth entries
	*/
	public static List<TokenAuthEntry> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the token auth entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of token auth entries
	* @param end the upper bound of the range of token auth entries (not inclusive)
	* @return the range of matching token auth entries
	*/
	public static List<TokenAuthEntry> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the token auth entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of token auth entries
	* @param end the upper bound of the range of token auth entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching token auth entries
	*/
	public static List<TokenAuthEntry> findByUserId(long userId, int start,
		int end, OrderByComparator<TokenAuthEntry> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the token auth entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of token auth entries
	* @param end the upper bound of the range of token auth entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching token auth entries
	*/
	public static List<TokenAuthEntry> findByUserId(long userId, int start,
		int end, OrderByComparator<TokenAuthEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first token auth entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching token auth entry
	* @throws NoSuchEntryException if a matching token auth entry could not be found
	*/
	public static TokenAuthEntry findByUserId_First(long userId,
		OrderByComparator<TokenAuthEntry> orderByComparator)
		throws com.liferay.osb.loop.token.auth.exception.NoSuchEntryException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first token auth entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching token auth entry, or <code>null</code> if a matching token auth entry could not be found
	*/
	public static TokenAuthEntry fetchByUserId_First(long userId,
		OrderByComparator<TokenAuthEntry> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last token auth entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching token auth entry
	* @throws NoSuchEntryException if a matching token auth entry could not be found
	*/
	public static TokenAuthEntry findByUserId_Last(long userId,
		OrderByComparator<TokenAuthEntry> orderByComparator)
		throws com.liferay.osb.loop.token.auth.exception.NoSuchEntryException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last token auth entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching token auth entry, or <code>null</code> if a matching token auth entry could not be found
	*/
	public static TokenAuthEntry fetchByUserId_Last(long userId,
		OrderByComparator<TokenAuthEntry> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the token auth entries before and after the current token auth entry in the ordered set where userId = &#63;.
	*
	* @param tokenAuthEntryId the primary key of the current token auth entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next token auth entry
	* @throws NoSuchEntryException if a token auth entry with the primary key could not be found
	*/
	public static TokenAuthEntry[] findByUserId_PrevAndNext(
		long tokenAuthEntryId, long userId,
		OrderByComparator<TokenAuthEntry> orderByComparator)
		throws com.liferay.osb.loop.token.auth.exception.NoSuchEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(tokenAuthEntryId, userId,
			orderByComparator);
	}

	/**
	* Removes all the token auth entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of token auth entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching token auth entries
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the token auth entry where token = &#63; or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param token the token
	* @return the matching token auth entry
	* @throws NoSuchEntryException if a matching token auth entry could not be found
	*/
	public static TokenAuthEntry findByToken(java.lang.String token)
		throws com.liferay.osb.loop.token.auth.exception.NoSuchEntryException {
		return getPersistence().findByToken(token);
	}

	/**
	* Returns the token auth entry where token = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param token the token
	* @return the matching token auth entry, or <code>null</code> if a matching token auth entry could not be found
	*/
	public static TokenAuthEntry fetchByToken(java.lang.String token) {
		return getPersistence().fetchByToken(token);
	}

	/**
	* Returns the token auth entry where token = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param token the token
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching token auth entry, or <code>null</code> if a matching token auth entry could not be found
	*/
	public static TokenAuthEntry fetchByToken(java.lang.String token,
		boolean retrieveFromCache) {
		return getPersistence().fetchByToken(token, retrieveFromCache);
	}

	/**
	* Removes the token auth entry where token = &#63; from the database.
	*
	* @param token the token
	* @return the token auth entry that was removed
	*/
	public static TokenAuthEntry removeByToken(java.lang.String token)
		throws com.liferay.osb.loop.token.auth.exception.NoSuchEntryException {
		return getPersistence().removeByToken(token);
	}

	/**
	* Returns the number of token auth entries where token = &#63;.
	*
	* @param token the token
	* @return the number of matching token auth entries
	*/
	public static int countByToken(java.lang.String token) {
		return getPersistence().countByToken(token);
	}

	/**
	* Caches the token auth entry in the entity cache if it is enabled.
	*
	* @param tokenAuthEntry the token auth entry
	*/
	public static void cacheResult(TokenAuthEntry tokenAuthEntry) {
		getPersistence().cacheResult(tokenAuthEntry);
	}

	/**
	* Caches the token auth entries in the entity cache if it is enabled.
	*
	* @param tokenAuthEntries the token auth entries
	*/
	public static void cacheResult(List<TokenAuthEntry> tokenAuthEntries) {
		getPersistence().cacheResult(tokenAuthEntries);
	}

	/**
	* Creates a new token auth entry with the primary key. Does not add the token auth entry to the database.
	*
	* @param tokenAuthEntryId the primary key for the new token auth entry
	* @return the new token auth entry
	*/
	public static TokenAuthEntry create(long tokenAuthEntryId) {
		return getPersistence().create(tokenAuthEntryId);
	}

	/**
	* Removes the token auth entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tokenAuthEntryId the primary key of the token auth entry
	* @return the token auth entry that was removed
	* @throws NoSuchEntryException if a token auth entry with the primary key could not be found
	*/
	public static TokenAuthEntry remove(long tokenAuthEntryId)
		throws com.liferay.osb.loop.token.auth.exception.NoSuchEntryException {
		return getPersistence().remove(tokenAuthEntryId);
	}

	public static TokenAuthEntry updateImpl(TokenAuthEntry tokenAuthEntry) {
		return getPersistence().updateImpl(tokenAuthEntry);
	}

	/**
	* Returns the token auth entry with the primary key or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param tokenAuthEntryId the primary key of the token auth entry
	* @return the token auth entry
	* @throws NoSuchEntryException if a token auth entry with the primary key could not be found
	*/
	public static TokenAuthEntry findByPrimaryKey(long tokenAuthEntryId)
		throws com.liferay.osb.loop.token.auth.exception.NoSuchEntryException {
		return getPersistence().findByPrimaryKey(tokenAuthEntryId);
	}

	/**
	* Returns the token auth entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param tokenAuthEntryId the primary key of the token auth entry
	* @return the token auth entry, or <code>null</code> if a token auth entry with the primary key could not be found
	*/
	public static TokenAuthEntry fetchByPrimaryKey(long tokenAuthEntryId) {
		return getPersistence().fetchByPrimaryKey(tokenAuthEntryId);
	}

	public static java.util.Map<java.io.Serializable, TokenAuthEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the token auth entries.
	*
	* @return the token auth entries
	*/
	public static List<TokenAuthEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the token auth entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of token auth entries
	* @param end the upper bound of the range of token auth entries (not inclusive)
	* @return the range of token auth entries
	*/
	public static List<TokenAuthEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the token auth entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of token auth entries
	* @param end the upper bound of the range of token auth entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of token auth entries
	*/
	public static List<TokenAuthEntry> findAll(int start, int end,
		OrderByComparator<TokenAuthEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the token auth entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TokenAuthEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of token auth entries
	* @param end the upper bound of the range of token auth entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of token auth entries
	*/
	public static List<TokenAuthEntry> findAll(int start, int end,
		OrderByComparator<TokenAuthEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the token auth entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of token auth entries.
	*
	* @return the number of token auth entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TokenAuthEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TokenAuthEntryPersistence, TokenAuthEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TokenAuthEntryPersistence.class);

		ServiceTracker<TokenAuthEntryPersistence, TokenAuthEntryPersistence> serviceTracker =
			new ServiceTracker<TokenAuthEntryPersistence, TokenAuthEntryPersistence>(bundle.getBundleContext(),
				TokenAuthEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}
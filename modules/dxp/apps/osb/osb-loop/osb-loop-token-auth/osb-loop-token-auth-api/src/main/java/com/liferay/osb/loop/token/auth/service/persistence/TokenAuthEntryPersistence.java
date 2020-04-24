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

import com.liferay.osb.loop.token.auth.exception.NoSuchEntryException;
import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the token auth entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bruno Farache
 * @see com.liferay.osb.loop.token.auth.service.persistence.impl.TokenAuthEntryPersistenceImpl
 * @see TokenAuthEntryUtil
 * @generated
 */
@ProviderType
public interface TokenAuthEntryPersistence extends BasePersistence<TokenAuthEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TokenAuthEntryUtil} to access the token auth entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the token auth entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching token auth entries
	*/
	public java.util.List<TokenAuthEntry> findByUserId(long userId);

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
	public java.util.List<TokenAuthEntry> findByUserId(long userId, int start,
		int end);

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
	public java.util.List<TokenAuthEntry> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TokenAuthEntry> orderByComparator);

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
	public java.util.List<TokenAuthEntry> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TokenAuthEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first token auth entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching token auth entry
	* @throws NoSuchEntryException if a matching token auth entry could not be found
	*/
	public TokenAuthEntry findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TokenAuthEntry> orderByComparator)
		throws NoSuchEntryException;

	/**
	* Returns the first token auth entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching token auth entry, or <code>null</code> if a matching token auth entry could not be found
	*/
	public TokenAuthEntry fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TokenAuthEntry> orderByComparator);

	/**
	* Returns the last token auth entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching token auth entry
	* @throws NoSuchEntryException if a matching token auth entry could not be found
	*/
	public TokenAuthEntry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TokenAuthEntry> orderByComparator)
		throws NoSuchEntryException;

	/**
	* Returns the last token auth entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching token auth entry, or <code>null</code> if a matching token auth entry could not be found
	*/
	public TokenAuthEntry fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TokenAuthEntry> orderByComparator);

	/**
	* Returns the token auth entries before and after the current token auth entry in the ordered set where userId = &#63;.
	*
	* @param tokenAuthEntryId the primary key of the current token auth entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next token auth entry
	* @throws NoSuchEntryException if a token auth entry with the primary key could not be found
	*/
	public TokenAuthEntry[] findByUserId_PrevAndNext(long tokenAuthEntryId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TokenAuthEntry> orderByComparator)
		throws NoSuchEntryException;

	/**
	* Removes all the token auth entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of token auth entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching token auth entries
	*/
	public int countByUserId(long userId);

	/**
	* Returns the token auth entry where token = &#63; or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param token the token
	* @return the matching token auth entry
	* @throws NoSuchEntryException if a matching token auth entry could not be found
	*/
	public TokenAuthEntry findByToken(java.lang.String token)
		throws NoSuchEntryException;

	/**
	* Returns the token auth entry where token = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param token the token
	* @return the matching token auth entry, or <code>null</code> if a matching token auth entry could not be found
	*/
	public TokenAuthEntry fetchByToken(java.lang.String token);

	/**
	* Returns the token auth entry where token = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param token the token
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching token auth entry, or <code>null</code> if a matching token auth entry could not be found
	*/
	public TokenAuthEntry fetchByToken(java.lang.String token,
		boolean retrieveFromCache);

	/**
	* Removes the token auth entry where token = &#63; from the database.
	*
	* @param token the token
	* @return the token auth entry that was removed
	*/
	public TokenAuthEntry removeByToken(java.lang.String token)
		throws NoSuchEntryException;

	/**
	* Returns the number of token auth entries where token = &#63;.
	*
	* @param token the token
	* @return the number of matching token auth entries
	*/
	public int countByToken(java.lang.String token);

	/**
	* Caches the token auth entry in the entity cache if it is enabled.
	*
	* @param tokenAuthEntry the token auth entry
	*/
	public void cacheResult(TokenAuthEntry tokenAuthEntry);

	/**
	* Caches the token auth entries in the entity cache if it is enabled.
	*
	* @param tokenAuthEntries the token auth entries
	*/
	public void cacheResult(java.util.List<TokenAuthEntry> tokenAuthEntries);

	/**
	* Creates a new token auth entry with the primary key. Does not add the token auth entry to the database.
	*
	* @param tokenAuthEntryId the primary key for the new token auth entry
	* @return the new token auth entry
	*/
	public TokenAuthEntry create(long tokenAuthEntryId);

	/**
	* Removes the token auth entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tokenAuthEntryId the primary key of the token auth entry
	* @return the token auth entry that was removed
	* @throws NoSuchEntryException if a token auth entry with the primary key could not be found
	*/
	public TokenAuthEntry remove(long tokenAuthEntryId)
		throws NoSuchEntryException;

	public TokenAuthEntry updateImpl(TokenAuthEntry tokenAuthEntry);

	/**
	* Returns the token auth entry with the primary key or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param tokenAuthEntryId the primary key of the token auth entry
	* @return the token auth entry
	* @throws NoSuchEntryException if a token auth entry with the primary key could not be found
	*/
	public TokenAuthEntry findByPrimaryKey(long tokenAuthEntryId)
		throws NoSuchEntryException;

	/**
	* Returns the token auth entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param tokenAuthEntryId the primary key of the token auth entry
	* @return the token auth entry, or <code>null</code> if a token auth entry with the primary key could not be found
	*/
	public TokenAuthEntry fetchByPrimaryKey(long tokenAuthEntryId);

	@Override
	public java.util.Map<java.io.Serializable, TokenAuthEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the token auth entries.
	*
	* @return the token auth entries
	*/
	public java.util.List<TokenAuthEntry> findAll();

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
	public java.util.List<TokenAuthEntry> findAll(int start, int end);

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
	public java.util.List<TokenAuthEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TokenAuthEntry> orderByComparator);

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
	public java.util.List<TokenAuthEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TokenAuthEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the token auth entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of token auth entries.
	*
	* @return the number of token auth entries
	*/
	public int countAll();
}
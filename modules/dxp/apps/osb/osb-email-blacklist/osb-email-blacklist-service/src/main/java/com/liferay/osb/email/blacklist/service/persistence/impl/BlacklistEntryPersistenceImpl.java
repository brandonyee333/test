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

package com.liferay.osb.email.blacklist.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.email.blacklist.exception.NoSuchBlacklistEntryException;
import com.liferay.osb.email.blacklist.model.BlacklistEntry;
import com.liferay.osb.email.blacklist.model.impl.BlacklistEntryImpl;
import com.liferay.osb.email.blacklist.model.impl.BlacklistEntryModelImpl;
import com.liferay.osb.email.blacklist.service.persistence.BlacklistEntryPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the blacklist entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see BlacklistEntryPersistence
 * @see com.liferay.osb.email.blacklist.service.persistence.BlacklistEntryUtil
 * @generated
 */
@ProviderType
public class BlacklistEntryPersistenceImpl extends BasePersistenceImpl<BlacklistEntry>
	implements BlacklistEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BlacklistEntryUtil} to access the blacklist entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BlacklistEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlacklistEntryModelImpl.FINDER_CACHE_ENABLED,
			BlacklistEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlacklistEntryModelImpl.FINDER_CACHE_ENABLED,
			BlacklistEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlacklistEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_EMAILADDRESS = new FinderPath(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlacklistEntryModelImpl.FINDER_CACHE_ENABLED,
			BlacklistEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByEmailAddress", new String[] { String.class.getName() },
			BlacklistEntryModelImpl.EMAILADDRESS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EMAILADDRESS = new FinderPath(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlacklistEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEmailAddress",
			new String[] { String.class.getName() });

	/**
	 * Returns the blacklist entry where emailAddress = &#63; or throws a {@link NoSuchBlacklistEntryException} if it could not be found.
	 *
	 * @param emailAddress the email address
	 * @return the matching blacklist entry
	 * @throws NoSuchBlacklistEntryException if a matching blacklist entry could not be found
	 */
	@Override
	public BlacklistEntry findByEmailAddress(String emailAddress)
		throws NoSuchBlacklistEntryException {
		BlacklistEntry blacklistEntry = fetchByEmailAddress(emailAddress);

		if (blacklistEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("emailAddress=");
			msg.append(emailAddress);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchBlacklistEntryException(msg.toString());
		}

		return blacklistEntry;
	}

	/**
	 * Returns the blacklist entry where emailAddress = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param emailAddress the email address
	 * @return the matching blacklist entry, or <code>null</code> if a matching blacklist entry could not be found
	 */
	@Override
	public BlacklistEntry fetchByEmailAddress(String emailAddress) {
		return fetchByEmailAddress(emailAddress, true);
	}

	/**
	 * Returns the blacklist entry where emailAddress = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param emailAddress the email address
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching blacklist entry, or <code>null</code> if a matching blacklist entry could not be found
	 */
	@Override
	public BlacklistEntry fetchByEmailAddress(String emailAddress,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { emailAddress };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_EMAILADDRESS,
					finderArgs, this);
		}

		if (result instanceof BlacklistEntry) {
			BlacklistEntry blacklistEntry = (BlacklistEntry)result;

			if (!Objects.equals(emailAddress, blacklistEntry.getEmailAddress())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_BLACKLISTENTRY_WHERE);

			boolean bindEmailAddress = false;

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1);
			}
			else if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3);
			}
			else {
				bindEmailAddress = true;

				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEmailAddress) {
					qPos.add(emailAddress);
				}

				List<BlacklistEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_EMAILADDRESS,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"BlacklistEntryPersistenceImpl.fetchByEmailAddress(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					BlacklistEntry blacklistEntry = list.get(0);

					result = blacklistEntry;

					cacheResult(blacklistEntry);

					if ((blacklistEntry.getEmailAddress() == null) ||
							!blacklistEntry.getEmailAddress()
											   .equals(emailAddress)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_EMAILADDRESS,
							finderArgs, blacklistEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_EMAILADDRESS,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (BlacklistEntry)result;
		}
	}

	/**
	 * Removes the blacklist entry where emailAddress = &#63; from the database.
	 *
	 * @param emailAddress the email address
	 * @return the blacklist entry that was removed
	 */
	@Override
	public BlacklistEntry removeByEmailAddress(String emailAddress)
		throws NoSuchBlacklistEntryException {
		BlacklistEntry blacklistEntry = findByEmailAddress(emailAddress);

		return remove(blacklistEntry);
	}

	/**
	 * Returns the number of blacklist entries where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @return the number of matching blacklist entries
	 */
	@Override
	public int countByEmailAddress(String emailAddress) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EMAILADDRESS;

		Object[] finderArgs = new Object[] { emailAddress };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BLACKLISTENTRY_WHERE);

			boolean bindEmailAddress = false;

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1);
			}
			else if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3);
			}
			else {
				bindEmailAddress = true;

				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEmailAddress) {
					qPos.add(emailAddress);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1 = "blacklistEntry.emailAddress IS NULL";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2 = "blacklistEntry.emailAddress = ?";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3 = "(blacklistEntry.emailAddress IS NULL OR blacklistEntry.emailAddress = '')";

	public BlacklistEntryPersistenceImpl() {
		setModelClass(BlacklistEntry.class);
	}

	/**
	 * Caches the blacklist entry in the entity cache if it is enabled.
	 *
	 * @param blacklistEntry the blacklist entry
	 */
	@Override
	public void cacheResult(BlacklistEntry blacklistEntry) {
		entityCache.putResult(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlacklistEntryImpl.class, blacklistEntry.getPrimaryKey(),
			blacklistEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_EMAILADDRESS,
			new Object[] { blacklistEntry.getEmailAddress() }, blacklistEntry);

		blacklistEntry.resetOriginalValues();
	}

	/**
	 * Caches the blacklist entries in the entity cache if it is enabled.
	 *
	 * @param blacklistEntries the blacklist entries
	 */
	@Override
	public void cacheResult(List<BlacklistEntry> blacklistEntries) {
		for (BlacklistEntry blacklistEntry : blacklistEntries) {
			if (entityCache.getResult(
						BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
						BlacklistEntryImpl.class, blacklistEntry.getPrimaryKey()) == null) {
				cacheResult(blacklistEntry);
			}
			else {
				blacklistEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all blacklist entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BlacklistEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the blacklist entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BlacklistEntry blacklistEntry) {
		entityCache.removeResult(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlacklistEntryImpl.class, blacklistEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((BlacklistEntryModelImpl)blacklistEntry, true);
	}

	@Override
	public void clearCache(List<BlacklistEntry> blacklistEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BlacklistEntry blacklistEntry : blacklistEntries) {
			entityCache.removeResult(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
				BlacklistEntryImpl.class, blacklistEntry.getPrimaryKey());

			clearUniqueFindersCache((BlacklistEntryModelImpl)blacklistEntry,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		BlacklistEntryModelImpl blacklistEntryModelImpl) {
		Object[] args = new Object[] { blacklistEntryModelImpl.getEmailAddress() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_EMAILADDRESS, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_EMAILADDRESS, args,
			blacklistEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		BlacklistEntryModelImpl blacklistEntryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					blacklistEntryModelImpl.getEmailAddress()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_EMAILADDRESS, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_EMAILADDRESS, args);
		}

		if ((blacklistEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_EMAILADDRESS.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					blacklistEntryModelImpl.getOriginalEmailAddress()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_EMAILADDRESS, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_EMAILADDRESS, args);
		}
	}

	/**
	 * Creates a new blacklist entry with the primary key. Does not add the blacklist entry to the database.
	 *
	 * @param blacklistEntryId the primary key for the new blacklist entry
	 * @return the new blacklist entry
	 */
	@Override
	public BlacklistEntry create(long blacklistEntryId) {
		BlacklistEntry blacklistEntry = new BlacklistEntryImpl();

		blacklistEntry.setNew(true);
		blacklistEntry.setPrimaryKey(blacklistEntryId);

		return blacklistEntry;
	}

	/**
	 * Removes the blacklist entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param blacklistEntryId the primary key of the blacklist entry
	 * @return the blacklist entry that was removed
	 * @throws NoSuchBlacklistEntryException if a blacklist entry with the primary key could not be found
	 */
	@Override
	public BlacklistEntry remove(long blacklistEntryId)
		throws NoSuchBlacklistEntryException {
		return remove((Serializable)blacklistEntryId);
	}

	/**
	 * Removes the blacklist entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the blacklist entry
	 * @return the blacklist entry that was removed
	 * @throws NoSuchBlacklistEntryException if a blacklist entry with the primary key could not be found
	 */
	@Override
	public BlacklistEntry remove(Serializable primaryKey)
		throws NoSuchBlacklistEntryException {
		Session session = null;

		try {
			session = openSession();

			BlacklistEntry blacklistEntry = (BlacklistEntry)session.get(BlacklistEntryImpl.class,
					primaryKey);

			if (blacklistEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBlacklistEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(blacklistEntry);
		}
		catch (NoSuchBlacklistEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected BlacklistEntry removeImpl(BlacklistEntry blacklistEntry) {
		blacklistEntry = toUnwrappedModel(blacklistEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(blacklistEntry)) {
				blacklistEntry = (BlacklistEntry)session.get(BlacklistEntryImpl.class,
						blacklistEntry.getPrimaryKeyObj());
			}

			if (blacklistEntry != null) {
				session.delete(blacklistEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (blacklistEntry != null) {
			clearCache(blacklistEntry);
		}

		return blacklistEntry;
	}

	@Override
	public BlacklistEntry updateImpl(BlacklistEntry blacklistEntry) {
		blacklistEntry = toUnwrappedModel(blacklistEntry);

		boolean isNew = blacklistEntry.isNew();

		BlacklistEntryModelImpl blacklistEntryModelImpl = (BlacklistEntryModelImpl)blacklistEntry;

		Session session = null;

		try {
			session = openSession();

			if (blacklistEntry.isNew()) {
				session.save(blacklistEntry);

				blacklistEntry.setNew(false);
			}
			else {
				blacklistEntry = (BlacklistEntry)session.merge(blacklistEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!BlacklistEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlacklistEntryImpl.class, blacklistEntry.getPrimaryKey(),
			blacklistEntry, false);

		clearUniqueFindersCache(blacklistEntryModelImpl, false);
		cacheUniqueFindersCache(blacklistEntryModelImpl);

		blacklistEntry.resetOriginalValues();

		return blacklistEntry;
	}

	protected BlacklistEntry toUnwrappedModel(BlacklistEntry blacklistEntry) {
		if (blacklistEntry instanceof BlacklistEntryImpl) {
			return blacklistEntry;
		}

		BlacklistEntryImpl blacklistEntryImpl = new BlacklistEntryImpl();

		blacklistEntryImpl.setNew(blacklistEntry.isNew());
		blacklistEntryImpl.setPrimaryKey(blacklistEntry.getPrimaryKey());

		blacklistEntryImpl.setBlacklistEntryId(blacklistEntry.getBlacklistEntryId());
		blacklistEntryImpl.setCreateDate(blacklistEntry.getCreateDate());
		blacklistEntryImpl.setEmailAddress(blacklistEntry.getEmailAddress());

		return blacklistEntryImpl;
	}

	/**
	 * Returns the blacklist entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the blacklist entry
	 * @return the blacklist entry
	 * @throws NoSuchBlacklistEntryException if a blacklist entry with the primary key could not be found
	 */
	@Override
	public BlacklistEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBlacklistEntryException {
		BlacklistEntry blacklistEntry = fetchByPrimaryKey(primaryKey);

		if (blacklistEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBlacklistEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return blacklistEntry;
	}

	/**
	 * Returns the blacklist entry with the primary key or throws a {@link NoSuchBlacklistEntryException} if it could not be found.
	 *
	 * @param blacklistEntryId the primary key of the blacklist entry
	 * @return the blacklist entry
	 * @throws NoSuchBlacklistEntryException if a blacklist entry with the primary key could not be found
	 */
	@Override
	public BlacklistEntry findByPrimaryKey(long blacklistEntryId)
		throws NoSuchBlacklistEntryException {
		return findByPrimaryKey((Serializable)blacklistEntryId);
	}

	/**
	 * Returns the blacklist entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the blacklist entry
	 * @return the blacklist entry, or <code>null</code> if a blacklist entry with the primary key could not be found
	 */
	@Override
	public BlacklistEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
				BlacklistEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BlacklistEntry blacklistEntry = (BlacklistEntry)serializable;

		if (blacklistEntry == null) {
			Session session = null;

			try {
				session = openSession();

				blacklistEntry = (BlacklistEntry)session.get(BlacklistEntryImpl.class,
						primaryKey);

				if (blacklistEntry != null) {
					cacheResult(blacklistEntry);
				}
				else {
					entityCache.putResult(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
						BlacklistEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
					BlacklistEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return blacklistEntry;
	}

	/**
	 * Returns the blacklist entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param blacklistEntryId the primary key of the blacklist entry
	 * @return the blacklist entry, or <code>null</code> if a blacklist entry with the primary key could not be found
	 */
	@Override
	public BlacklistEntry fetchByPrimaryKey(long blacklistEntryId) {
		return fetchByPrimaryKey((Serializable)blacklistEntryId);
	}

	@Override
	public Map<Serializable, BlacklistEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BlacklistEntry> map = new HashMap<Serializable, BlacklistEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BlacklistEntry blacklistEntry = fetchByPrimaryKey(primaryKey);

			if (blacklistEntry != null) {
				map.put(primaryKey, blacklistEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
					BlacklistEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (BlacklistEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BLACKLISTENTRY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (BlacklistEntry blacklistEntry : (List<BlacklistEntry>)q.list()) {
				map.put(blacklistEntry.getPrimaryKeyObj(), blacklistEntry);

				cacheResult(blacklistEntry);

				uncachedPrimaryKeys.remove(blacklistEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(BlacklistEntryModelImpl.ENTITY_CACHE_ENABLED,
					BlacklistEntryImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the blacklist entries.
	 *
	 * @return the blacklist entries
	 */
	@Override
	public List<BlacklistEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<BlacklistEntry> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<BlacklistEntry> findAll(int start, int end,
		OrderByComparator<BlacklistEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<BlacklistEntry> findAll(int start, int end,
		OrderByComparator<BlacklistEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<BlacklistEntry> list = null;

		if (retrieveFromCache) {
			list = (List<BlacklistEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_BLACKLISTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BLACKLISTENTRY;

				if (pagination) {
					sql = sql.concat(BlacklistEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BlacklistEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BlacklistEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the blacklist entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BlacklistEntry blacklistEntry : findAll()) {
			remove(blacklistEntry);
		}
	}

	/**
	 * Returns the number of blacklist entries.
	 *
	 * @return the number of blacklist entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BLACKLISTENTRY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return BlacklistEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the blacklist entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(BlacklistEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_BLACKLISTENTRY = "SELECT blacklistEntry FROM BlacklistEntry blacklistEntry";
	private static final String _SQL_SELECT_BLACKLISTENTRY_WHERE_PKS_IN = "SELECT blacklistEntry FROM BlacklistEntry blacklistEntry WHERE blacklistEntryId IN (";
	private static final String _SQL_SELECT_BLACKLISTENTRY_WHERE = "SELECT blacklistEntry FROM BlacklistEntry blacklistEntry WHERE ";
	private static final String _SQL_COUNT_BLACKLISTENTRY = "SELECT COUNT(blacklistEntry) FROM BlacklistEntry blacklistEntry";
	private static final String _SQL_COUNT_BLACKLISTENTRY_WHERE = "SELECT COUNT(blacklistEntry) FROM BlacklistEntry blacklistEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "blacklistEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BlacklistEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BlacklistEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(BlacklistEntryPersistenceImpl.class);
}
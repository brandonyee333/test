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

package com.liferay.osb.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.osb.model.LCSSubscriptionEntry;
import com.liferay.osb.model.impl.LCSSubscriptionEntryImpl;
import com.liferay.osb.model.impl.LCSSubscriptionEntryModelImpl;
import com.liferay.osb.service.persistence.LCSSubscriptionEntryPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the lcs subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LCSSubscriptionEntryPersistence
 * @see com.liferay.osb.service.persistence.LCSSubscriptionEntryUtil
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryPersistenceImpl extends BasePersistenceImpl<LCSSubscriptionEntry>
	implements LCSSubscriptionEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSSubscriptionEntryUtil} to access the lcs subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSSubscriptionEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public LCSSubscriptionEntryPersistenceImpl() {
		setModelClass(LCSSubscriptionEntry.class);
	}

	/**
	 * Caches the lcs subscription entry in the entity cache if it is enabled.
	 *
	 * @param lcsSubscriptionEntry the lcs subscription entry
	 */
	@Override
	public void cacheResult(LCSSubscriptionEntry lcsSubscriptionEntry) {
		entityCache.putResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			lcsSubscriptionEntry.getPrimaryKey(), lcsSubscriptionEntry);

		lcsSubscriptionEntry.resetOriginalValues();
	}

	/**
	 * Caches the lcs subscription entries in the entity cache if it is enabled.
	 *
	 * @param lcsSubscriptionEntries the lcs subscription entries
	 */
	@Override
	public void cacheResult(List<LCSSubscriptionEntry> lcsSubscriptionEntries) {
		for (LCSSubscriptionEntry lcsSubscriptionEntry : lcsSubscriptionEntries) {
			if (entityCache.getResult(
						LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSSubscriptionEntryImpl.class,
						lcsSubscriptionEntry.getPrimaryKey()) == null) {
				cacheResult(lcsSubscriptionEntry);
			}
			else {
				lcsSubscriptionEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all lcs subscription entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LCSSubscriptionEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the lcs subscription entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSSubscriptionEntry lcsSubscriptionEntry) {
		entityCache.removeResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class, lcsSubscriptionEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LCSSubscriptionEntry> lcsSubscriptionEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSSubscriptionEntry lcsSubscriptionEntry : lcsSubscriptionEntries) {
			entityCache.removeResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSSubscriptionEntryImpl.class,
				lcsSubscriptionEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new lcs subscription entry with the primary key. Does not add the lcs subscription entry to the database.
	 *
	 * @param lcsSubscriptionEntryId the primary key for the new lcs subscription entry
	 * @return the new lcs subscription entry
	 */
	@Override
	public LCSSubscriptionEntry create(long lcsSubscriptionEntryId) {
		LCSSubscriptionEntry lcsSubscriptionEntry = new LCSSubscriptionEntryImpl();

		lcsSubscriptionEntry.setNew(true);
		lcsSubscriptionEntry.setPrimaryKey(lcsSubscriptionEntryId);

		return lcsSubscriptionEntry;
	}

	/**
	 * Removes the lcs subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the lcs subscription entry
	 * @return the lcs subscription entry that was removed
	 * @throws NoSuchLCSSubscriptionEntryException if a lcs subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry remove(long lcsSubscriptionEntryId)
		throws NoSuchLCSSubscriptionEntryException {
		return remove((Serializable)lcsSubscriptionEntryId);
	}

	/**
	 * Removes the lcs subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the lcs subscription entry
	 * @return the lcs subscription entry that was removed
	 * @throws NoSuchLCSSubscriptionEntryException if a lcs subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry remove(Serializable primaryKey)
		throws NoSuchLCSSubscriptionEntryException {
		Session session = null;

		try {
			session = openSession();

			LCSSubscriptionEntry lcsSubscriptionEntry = (LCSSubscriptionEntry)session.get(LCSSubscriptionEntryImpl.class,
					primaryKey);

			if (lcsSubscriptionEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSSubscriptionEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsSubscriptionEntry);
		}
		catch (NoSuchLCSSubscriptionEntryException nsee) {
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
	protected LCSSubscriptionEntry removeImpl(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		lcsSubscriptionEntry = toUnwrappedModel(lcsSubscriptionEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lcsSubscriptionEntry)) {
				lcsSubscriptionEntry = (LCSSubscriptionEntry)session.get(LCSSubscriptionEntryImpl.class,
						lcsSubscriptionEntry.getPrimaryKeyObj());
			}

			if (lcsSubscriptionEntry != null) {
				session.delete(lcsSubscriptionEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lcsSubscriptionEntry != null) {
			clearCache(lcsSubscriptionEntry);
		}

		return lcsSubscriptionEntry;
	}

	@Override
	public LCSSubscriptionEntry updateImpl(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		lcsSubscriptionEntry = toUnwrappedModel(lcsSubscriptionEntry);

		boolean isNew = lcsSubscriptionEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (lcsSubscriptionEntry.isNew()) {
				session.save(lcsSubscriptionEntry);

				lcsSubscriptionEntry.setNew(false);
			}
			else {
				lcsSubscriptionEntry = (LCSSubscriptionEntry)session.merge(lcsSubscriptionEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			lcsSubscriptionEntry.getPrimaryKey(), lcsSubscriptionEntry, false);

		lcsSubscriptionEntry.resetOriginalValues();

		return lcsSubscriptionEntry;
	}

	protected LCSSubscriptionEntry toUnwrappedModel(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		if (lcsSubscriptionEntry instanceof LCSSubscriptionEntryImpl) {
			return lcsSubscriptionEntry;
		}

		LCSSubscriptionEntryImpl lcsSubscriptionEntryImpl = new LCSSubscriptionEntryImpl();

		lcsSubscriptionEntryImpl.setNew(lcsSubscriptionEntry.isNew());
		lcsSubscriptionEntryImpl.setPrimaryKey(lcsSubscriptionEntry.getPrimaryKey());

		lcsSubscriptionEntryImpl.setLcsSubscriptionEntryId(lcsSubscriptionEntry.getLcsSubscriptionEntryId());
		lcsSubscriptionEntryImpl.setLcsProjectId(lcsSubscriptionEntry.getLcsProjectId());
		lcsSubscriptionEntryImpl.setProduct(lcsSubscriptionEntry.getProduct());
		lcsSubscriptionEntryImpl.setProductVersion(lcsSubscriptionEntry.getProductVersion());
		lcsSubscriptionEntryImpl.setType(lcsSubscriptionEntry.getType());
		lcsSubscriptionEntryImpl.setPlatform(lcsSubscriptionEntry.getPlatform());
		lcsSubscriptionEntryImpl.setPlatformVersion(lcsSubscriptionEntry.getPlatformVersion());
		lcsSubscriptionEntryImpl.setServersAllowed(lcsSubscriptionEntry.getServersAllowed());
		lcsSubscriptionEntryImpl.setServersUsed(lcsSubscriptionEntry.getServersUsed());
		lcsSubscriptionEntryImpl.setQuantity(lcsSubscriptionEntry.getQuantity());
		lcsSubscriptionEntryImpl.setInstanceSize(lcsSubscriptionEntry.getInstanceSize());
		lcsSubscriptionEntryImpl.setStartDate(lcsSubscriptionEntry.getStartDate());
		lcsSubscriptionEntryImpl.setEndDate(lcsSubscriptionEntry.getEndDate());
		lcsSubscriptionEntryImpl.setSupportStartDate(lcsSubscriptionEntry.getSupportStartDate());
		lcsSubscriptionEntryImpl.setSupportEndDate(lcsSubscriptionEntry.getSupportEndDate());
		lcsSubscriptionEntryImpl.setActualPrice(lcsSubscriptionEntry.getActualPrice());
		lcsSubscriptionEntryImpl.setCurrencyCode(lcsSubscriptionEntry.getCurrencyCode());
		lcsSubscriptionEntryImpl.setActive(lcsSubscriptionEntry.isActive());

		return lcsSubscriptionEntryImpl;
	}

	/**
	 * Returns the lcs subscription entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the lcs subscription entry
	 * @return the lcs subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a lcs subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLCSSubscriptionEntryException {
		LCSSubscriptionEntry lcsSubscriptionEntry = fetchByPrimaryKey(primaryKey);

		if (lcsSubscriptionEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLCSSubscriptionEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lcsSubscriptionEntry;
	}

	/**
	 * Returns the lcs subscription entry with the primary key or throws a {@link NoSuchLCSSubscriptionEntryException} if it could not be found.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the lcs subscription entry
	 * @return the lcs subscription entry
	 * @throws NoSuchLCSSubscriptionEntryException if a lcs subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry findByPrimaryKey(long lcsSubscriptionEntryId)
		throws NoSuchLCSSubscriptionEntryException {
		return findByPrimaryKey((Serializable)lcsSubscriptionEntryId);
	}

	/**
	 * Returns the lcs subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the lcs subscription entry
	 * @return the lcs subscription entry, or <code>null</code> if a lcs subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSSubscriptionEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LCSSubscriptionEntry lcsSubscriptionEntry = (LCSSubscriptionEntry)serializable;

		if (lcsSubscriptionEntry == null) {
			Session session = null;

			try {
				session = openSession();

				lcsSubscriptionEntry = (LCSSubscriptionEntry)session.get(LCSSubscriptionEntryImpl.class,
						primaryKey);

				if (lcsSubscriptionEntry != null) {
					cacheResult(lcsSubscriptionEntry);
				}
				else {
					entityCache.putResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSSubscriptionEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSSubscriptionEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lcsSubscriptionEntry;
	}

	/**
	 * Returns the lcs subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the lcs subscription entry
	 * @return the lcs subscription entry, or <code>null</code> if a lcs subscription entry with the primary key could not be found
	 */
	@Override
	public LCSSubscriptionEntry fetchByPrimaryKey(long lcsSubscriptionEntryId) {
		return fetchByPrimaryKey((Serializable)lcsSubscriptionEntryId);
	}

	@Override
	public Map<Serializable, LCSSubscriptionEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LCSSubscriptionEntry> map = new HashMap<Serializable, LCSSubscriptionEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LCSSubscriptionEntry lcsSubscriptionEntry = fetchByPrimaryKey(primaryKey);

			if (lcsSubscriptionEntry != null) {
				map.put(primaryKey, lcsSubscriptionEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSSubscriptionEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LCSSubscriptionEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE_PKS_IN);

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

			for (LCSSubscriptionEntry lcsSubscriptionEntry : (List<LCSSubscriptionEntry>)q.list()) {
				map.put(lcsSubscriptionEntry.getPrimaryKeyObj(),
					lcsSubscriptionEntry);

				cacheResult(lcsSubscriptionEntry);

				uncachedPrimaryKeys.remove(lcsSubscriptionEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					LCSSubscriptionEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the lcs subscription entries.
	 *
	 * @return the lcs subscription entries
	 */
	@Override
	public List<LCSSubscriptionEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<LCSSubscriptionEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<LCSSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<LCSSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
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

		List<LCSSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LCSSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSSUBSCRIPTIONENTRY;

				if (pagination) {
					sql = sql.concat(LCSSubscriptionEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
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
	 * Removes all the lcs subscription entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LCSSubscriptionEntry lcsSubscriptionEntry : findAll()) {
			remove(lcsSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of lcs subscription entries.
	 *
	 * @return the number of lcs subscription entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSSUBSCRIPTIONENTRY);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LCSSubscriptionEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the lcs subscription entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LCSSubscriptionEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_LCSSUBSCRIPTIONENTRY = "SELECT lcsSubscriptionEntry FROM LCSSubscriptionEntry lcsSubscriptionEntry";
	private static final String _SQL_SELECT_LCSSUBSCRIPTIONENTRY_WHERE_PKS_IN = "SELECT lcsSubscriptionEntry FROM LCSSubscriptionEntry lcsSubscriptionEntry WHERE lcsSubscriptionEntryId IN (";
	private static final String _SQL_COUNT_LCSSUBSCRIPTIONENTRY = "SELECT COUNT(lcsSubscriptionEntry) FROM LCSSubscriptionEntry lcsSubscriptionEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsSubscriptionEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSSubscriptionEntry exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(LCSSubscriptionEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type", "active"
			});
}
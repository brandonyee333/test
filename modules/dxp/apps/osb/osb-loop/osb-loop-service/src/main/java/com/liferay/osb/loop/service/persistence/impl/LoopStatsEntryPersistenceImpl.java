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

package com.liferay.osb.loop.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopStatsEntryException;
import com.liferay.osb.loop.model.LoopStatsEntry;
import com.liferay.osb.loop.model.impl.LoopStatsEntryImpl;
import com.liferay.osb.loop.model.impl.LoopStatsEntryModelImpl;
import com.liferay.osb.loop.service.persistence.LoopStatsEntryPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the loop stats entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStatsEntryPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopStatsEntryUtil
 * @generated
 */
@ProviderType
public class LoopStatsEntryPersistenceImpl extends BasePersistenceImpl<LoopStatsEntry>
	implements LoopStatsEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopStatsEntryUtil} to access the loop stats entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopStatsEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStatsEntryModelImpl.FINDER_CACHE_ENABLED,
			LoopStatsEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStatsEntryModelImpl.FINDER_CACHE_ENABLED,
			LoopStatsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStatsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public LoopStatsEntryPersistenceImpl() {
		setModelClass(LoopStatsEntry.class);
	}

	/**
	 * Caches the loop stats entry in the entity cache if it is enabled.
	 *
	 * @param loopStatsEntry the loop stats entry
	 */
	@Override
	public void cacheResult(LoopStatsEntry loopStatsEntry) {
		entityCache.putResult(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStatsEntryImpl.class, loopStatsEntry.getPrimaryKey(),
			loopStatsEntry);

		loopStatsEntry.resetOriginalValues();
	}

	/**
	 * Caches the loop stats entries in the entity cache if it is enabled.
	 *
	 * @param loopStatsEntries the loop stats entries
	 */
	@Override
	public void cacheResult(List<LoopStatsEntry> loopStatsEntries) {
		for (LoopStatsEntry loopStatsEntry : loopStatsEntries) {
			if (entityCache.getResult(
						LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
						LoopStatsEntryImpl.class, loopStatsEntry.getPrimaryKey()) == null) {
				cacheResult(loopStatsEntry);
			}
			else {
				loopStatsEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop stats entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopStatsEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop stats entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopStatsEntry loopStatsEntry) {
		entityCache.removeResult(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStatsEntryImpl.class, loopStatsEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LoopStatsEntry> loopStatsEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopStatsEntry loopStatsEntry : loopStatsEntries) {
			entityCache.removeResult(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
				LoopStatsEntryImpl.class, loopStatsEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new loop stats entry with the primary key. Does not add the loop stats entry to the database.
	 *
	 * @param loopStatsEntryId the primary key for the new loop stats entry
	 * @return the new loop stats entry
	 */
	@Override
	public LoopStatsEntry create(long loopStatsEntryId) {
		LoopStatsEntry loopStatsEntry = new LoopStatsEntryImpl();

		loopStatsEntry.setNew(true);
		loopStatsEntry.setPrimaryKey(loopStatsEntryId);

		return loopStatsEntry;
	}

	/**
	 * Removes the loop stats entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStatsEntryId the primary key of the loop stats entry
	 * @return the loop stats entry that was removed
	 * @throws NoSuchLoopStatsEntryException if a loop stats entry with the primary key could not be found
	 */
	@Override
	public LoopStatsEntry remove(long loopStatsEntryId)
		throws NoSuchLoopStatsEntryException {
		return remove((Serializable)loopStatsEntryId);
	}

	/**
	 * Removes the loop stats entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop stats entry
	 * @return the loop stats entry that was removed
	 * @throws NoSuchLoopStatsEntryException if a loop stats entry with the primary key could not be found
	 */
	@Override
	public LoopStatsEntry remove(Serializable primaryKey)
		throws NoSuchLoopStatsEntryException {
		Session session = null;

		try {
			session = openSession();

			LoopStatsEntry loopStatsEntry = (LoopStatsEntry)session.get(LoopStatsEntryImpl.class,
					primaryKey);

			if (loopStatsEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopStatsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopStatsEntry);
		}
		catch (NoSuchLoopStatsEntryException nsee) {
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
	protected LoopStatsEntry removeImpl(LoopStatsEntry loopStatsEntry) {
		loopStatsEntry = toUnwrappedModel(loopStatsEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopStatsEntry)) {
				loopStatsEntry = (LoopStatsEntry)session.get(LoopStatsEntryImpl.class,
						loopStatsEntry.getPrimaryKeyObj());
			}

			if (loopStatsEntry != null) {
				session.delete(loopStatsEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopStatsEntry != null) {
			clearCache(loopStatsEntry);
		}

		return loopStatsEntry;
	}

	@Override
	public LoopStatsEntry updateImpl(LoopStatsEntry loopStatsEntry) {
		loopStatsEntry = toUnwrappedModel(loopStatsEntry);

		boolean isNew = loopStatsEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (loopStatsEntry.isNew()) {
				session.save(loopStatsEntry);

				loopStatsEntry.setNew(false);
			}
			else {
				loopStatsEntry = (LoopStatsEntry)session.merge(loopStatsEntry);
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

		entityCache.putResult(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStatsEntryImpl.class, loopStatsEntry.getPrimaryKey(),
			loopStatsEntry, false);

		loopStatsEntry.resetOriginalValues();

		return loopStatsEntry;
	}

	protected LoopStatsEntry toUnwrappedModel(LoopStatsEntry loopStatsEntry) {
		if (loopStatsEntry instanceof LoopStatsEntryImpl) {
			return loopStatsEntry;
		}

		LoopStatsEntryImpl loopStatsEntryImpl = new LoopStatsEntryImpl();

		loopStatsEntryImpl.setNew(loopStatsEntry.isNew());
		loopStatsEntryImpl.setPrimaryKey(loopStatsEntry.getPrimaryKey());

		loopStatsEntryImpl.setLoopStatsEntryId(loopStatsEntry.getLoopStatsEntryId());
		loopStatsEntryImpl.setModifiedTime(loopStatsEntry.getModifiedTime());
		loopStatsEntryImpl.setClassNameId(loopStatsEntry.getClassNameId());
		loopStatsEntryImpl.setClassPK(loopStatsEntry.getClassPK());
		loopStatsEntryImpl.setScore(loopStatsEntry.getScore());

		return loopStatsEntryImpl;
	}

	/**
	 * Returns the loop stats entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop stats entry
	 * @return the loop stats entry
	 * @throws NoSuchLoopStatsEntryException if a loop stats entry with the primary key could not be found
	 */
	@Override
	public LoopStatsEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopStatsEntryException {
		LoopStatsEntry loopStatsEntry = fetchByPrimaryKey(primaryKey);

		if (loopStatsEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopStatsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopStatsEntry;
	}

	/**
	 * Returns the loop stats entry with the primary key or throws a {@link NoSuchLoopStatsEntryException} if it could not be found.
	 *
	 * @param loopStatsEntryId the primary key of the loop stats entry
	 * @return the loop stats entry
	 * @throws NoSuchLoopStatsEntryException if a loop stats entry with the primary key could not be found
	 */
	@Override
	public LoopStatsEntry findByPrimaryKey(long loopStatsEntryId)
		throws NoSuchLoopStatsEntryException {
		return findByPrimaryKey((Serializable)loopStatsEntryId);
	}

	/**
	 * Returns the loop stats entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop stats entry
	 * @return the loop stats entry, or <code>null</code> if a loop stats entry with the primary key could not be found
	 */
	@Override
	public LoopStatsEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
				LoopStatsEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopStatsEntry loopStatsEntry = (LoopStatsEntry)serializable;

		if (loopStatsEntry == null) {
			Session session = null;

			try {
				session = openSession();

				loopStatsEntry = (LoopStatsEntry)session.get(LoopStatsEntryImpl.class,
						primaryKey);

				if (loopStatsEntry != null) {
					cacheResult(loopStatsEntry);
				}
				else {
					entityCache.putResult(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
						LoopStatsEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
					LoopStatsEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopStatsEntry;
	}

	/**
	 * Returns the loop stats entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopStatsEntryId the primary key of the loop stats entry
	 * @return the loop stats entry, or <code>null</code> if a loop stats entry with the primary key could not be found
	 */
	@Override
	public LoopStatsEntry fetchByPrimaryKey(long loopStatsEntryId) {
		return fetchByPrimaryKey((Serializable)loopStatsEntryId);
	}

	@Override
	public Map<Serializable, LoopStatsEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopStatsEntry> map = new HashMap<Serializable, LoopStatsEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopStatsEntry loopStatsEntry = fetchByPrimaryKey(primaryKey);

			if (loopStatsEntry != null) {
				map.put(primaryKey, loopStatsEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
					LoopStatsEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopStatsEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPSTATSENTRY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (LoopStatsEntry loopStatsEntry : (List<LoopStatsEntry>)q.list()) {
				map.put(loopStatsEntry.getPrimaryKeyObj(), loopStatsEntry);

				cacheResult(loopStatsEntry);

				uncachedPrimaryKeys.remove(loopStatsEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
					LoopStatsEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop stats entries.
	 *
	 * @return the loop stats entries
	 */
	@Override
	public List<LoopStatsEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<LoopStatsEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<LoopStatsEntry> findAll(int start, int end,
		OrderByComparator<LoopStatsEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<LoopStatsEntry> findAll(int start, int end,
		OrderByComparator<LoopStatsEntry> orderByComparator,
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

		List<LoopStatsEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LoopStatsEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPSTATSENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPSTATSENTRY;

				if (pagination) {
					sql = sql.concat(LoopStatsEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopStatsEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopStatsEntry>)QueryUtil.list(q,
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
	 * Removes all the loop stats entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopStatsEntry loopStatsEntry : findAll()) {
			remove(loopStatsEntry);
		}
	}

	/**
	 * Returns the number of loop stats entries.
	 *
	 * @return the number of loop stats entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPSTATSENTRY);

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
		return LoopStatsEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop stats entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopStatsEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPSTATSENTRY = "SELECT loopStatsEntry FROM LoopStatsEntry loopStatsEntry";
	private static final String _SQL_SELECT_LOOPSTATSENTRY_WHERE_PKS_IN = "SELECT loopStatsEntry FROM LoopStatsEntry loopStatsEntry WHERE loopStatsEntryId IN (";
	private static final String _SQL_COUNT_LOOPSTATSENTRY = "SELECT COUNT(loopStatsEntry) FROM LoopStatsEntry loopStatsEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopStatsEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopStatsEntry exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(LoopStatsEntryPersistenceImpl.class);
}
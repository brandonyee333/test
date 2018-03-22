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

import com.liferay.osb.exception.NoSuchFeedbackEntryException;
import com.liferay.osb.model.FeedbackEntry;
import com.liferay.osb.model.impl.FeedbackEntryImpl;
import com.liferay.osb.model.impl.FeedbackEntryModelImpl;
import com.liferay.osb.service.persistence.FeedbackEntryPersistence;

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
 * The persistence implementation for the feedback entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedbackEntryPersistence
 * @see com.liferay.osb.service.persistence.FeedbackEntryUtil
 * @generated
 */
@ProviderType
public class FeedbackEntryPersistenceImpl extends BasePersistenceImpl<FeedbackEntry>
	implements FeedbackEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FeedbackEntryUtil} to access the feedback entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FeedbackEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
			FeedbackEntryModelImpl.FINDER_CACHE_ENABLED,
			FeedbackEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
			FeedbackEntryModelImpl.FINDER_CACHE_ENABLED,
			FeedbackEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
			FeedbackEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public FeedbackEntryPersistenceImpl() {
		setModelClass(FeedbackEntry.class);
	}

	/**
	 * Caches the feedback entry in the entity cache if it is enabled.
	 *
	 * @param feedbackEntry the feedback entry
	 */
	@Override
	public void cacheResult(FeedbackEntry feedbackEntry) {
		entityCache.putResult(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
			FeedbackEntryImpl.class, feedbackEntry.getPrimaryKey(),
			feedbackEntry);

		feedbackEntry.resetOriginalValues();
	}

	/**
	 * Caches the feedback entries in the entity cache if it is enabled.
	 *
	 * @param feedbackEntries the feedback entries
	 */
	@Override
	public void cacheResult(List<FeedbackEntry> feedbackEntries) {
		for (FeedbackEntry feedbackEntry : feedbackEntries) {
			if (entityCache.getResult(
						FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
						FeedbackEntryImpl.class, feedbackEntry.getPrimaryKey()) == null) {
				cacheResult(feedbackEntry);
			}
			else {
				feedbackEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all feedback entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FeedbackEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the feedback entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FeedbackEntry feedbackEntry) {
		entityCache.removeResult(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
			FeedbackEntryImpl.class, feedbackEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<FeedbackEntry> feedbackEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FeedbackEntry feedbackEntry : feedbackEntries) {
			entityCache.removeResult(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
				FeedbackEntryImpl.class, feedbackEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new feedback entry with the primary key. Does not add the feedback entry to the database.
	 *
	 * @param feedbackEntryId the primary key for the new feedback entry
	 * @return the new feedback entry
	 */
	@Override
	public FeedbackEntry create(long feedbackEntryId) {
		FeedbackEntry feedbackEntry = new FeedbackEntryImpl();

		feedbackEntry.setNew(true);
		feedbackEntry.setPrimaryKey(feedbackEntryId);

		return feedbackEntry;
	}

	/**
	 * Removes the feedback entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param feedbackEntryId the primary key of the feedback entry
	 * @return the feedback entry that was removed
	 * @throws NoSuchFeedbackEntryException if a feedback entry with the primary key could not be found
	 */
	@Override
	public FeedbackEntry remove(long feedbackEntryId)
		throws NoSuchFeedbackEntryException {
		return remove((Serializable)feedbackEntryId);
	}

	/**
	 * Removes the feedback entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the feedback entry
	 * @return the feedback entry that was removed
	 * @throws NoSuchFeedbackEntryException if a feedback entry with the primary key could not be found
	 */
	@Override
	public FeedbackEntry remove(Serializable primaryKey)
		throws NoSuchFeedbackEntryException {
		Session session = null;

		try {
			session = openSession();

			FeedbackEntry feedbackEntry = (FeedbackEntry)session.get(FeedbackEntryImpl.class,
					primaryKey);

			if (feedbackEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFeedbackEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(feedbackEntry);
		}
		catch (NoSuchFeedbackEntryException nsee) {
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
	protected FeedbackEntry removeImpl(FeedbackEntry feedbackEntry) {
		feedbackEntry = toUnwrappedModel(feedbackEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(feedbackEntry)) {
				feedbackEntry = (FeedbackEntry)session.get(FeedbackEntryImpl.class,
						feedbackEntry.getPrimaryKeyObj());
			}

			if (feedbackEntry != null) {
				session.delete(feedbackEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (feedbackEntry != null) {
			clearCache(feedbackEntry);
		}

		return feedbackEntry;
	}

	@Override
	public FeedbackEntry updateImpl(FeedbackEntry feedbackEntry) {
		feedbackEntry = toUnwrappedModel(feedbackEntry);

		boolean isNew = feedbackEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (feedbackEntry.isNew()) {
				session.save(feedbackEntry);

				feedbackEntry.setNew(false);
			}
			else {
				feedbackEntry = (FeedbackEntry)session.merge(feedbackEntry);
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

		entityCache.putResult(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
			FeedbackEntryImpl.class, feedbackEntry.getPrimaryKey(),
			feedbackEntry, false);

		feedbackEntry.resetOriginalValues();

		return feedbackEntry;
	}

	protected FeedbackEntry toUnwrappedModel(FeedbackEntry feedbackEntry) {
		if (feedbackEntry instanceof FeedbackEntryImpl) {
			return feedbackEntry;
		}

		FeedbackEntryImpl feedbackEntryImpl = new FeedbackEntryImpl();

		feedbackEntryImpl.setNew(feedbackEntry.isNew());
		feedbackEntryImpl.setPrimaryKey(feedbackEntry.getPrimaryKey());

		feedbackEntryImpl.setFeedbackEntryId(feedbackEntry.getFeedbackEntryId());
		feedbackEntryImpl.setUserId(feedbackEntry.getUserId());
		feedbackEntryImpl.setUserName(feedbackEntry.getUserName());
		feedbackEntryImpl.setCreateDate(feedbackEntry.getCreateDate());
		feedbackEntryImpl.setClassNameId(feedbackEntry.getClassNameId());
		feedbackEntryImpl.setClassPK(feedbackEntry.getClassPK());
		feedbackEntryImpl.setSatisfied(feedbackEntry.getSatisfied());
		feedbackEntryImpl.setComments(feedbackEntry.getComments());
		feedbackEntryImpl.setPageURL(feedbackEntry.getPageURL());

		return feedbackEntryImpl;
	}

	/**
	 * Returns the feedback entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the feedback entry
	 * @return the feedback entry
	 * @throws NoSuchFeedbackEntryException if a feedback entry with the primary key could not be found
	 */
	@Override
	public FeedbackEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFeedbackEntryException {
		FeedbackEntry feedbackEntry = fetchByPrimaryKey(primaryKey);

		if (feedbackEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFeedbackEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return feedbackEntry;
	}

	/**
	 * Returns the feedback entry with the primary key or throws a {@link NoSuchFeedbackEntryException} if it could not be found.
	 *
	 * @param feedbackEntryId the primary key of the feedback entry
	 * @return the feedback entry
	 * @throws NoSuchFeedbackEntryException if a feedback entry with the primary key could not be found
	 */
	@Override
	public FeedbackEntry findByPrimaryKey(long feedbackEntryId)
		throws NoSuchFeedbackEntryException {
		return findByPrimaryKey((Serializable)feedbackEntryId);
	}

	/**
	 * Returns the feedback entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the feedback entry
	 * @return the feedback entry, or <code>null</code> if a feedback entry with the primary key could not be found
	 */
	@Override
	public FeedbackEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
				FeedbackEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FeedbackEntry feedbackEntry = (FeedbackEntry)serializable;

		if (feedbackEntry == null) {
			Session session = null;

			try {
				session = openSession();

				feedbackEntry = (FeedbackEntry)session.get(FeedbackEntryImpl.class,
						primaryKey);

				if (feedbackEntry != null) {
					cacheResult(feedbackEntry);
				}
				else {
					entityCache.putResult(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
						FeedbackEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
					FeedbackEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return feedbackEntry;
	}

	/**
	 * Returns the feedback entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param feedbackEntryId the primary key of the feedback entry
	 * @return the feedback entry, or <code>null</code> if a feedback entry with the primary key could not be found
	 */
	@Override
	public FeedbackEntry fetchByPrimaryKey(long feedbackEntryId) {
		return fetchByPrimaryKey((Serializable)feedbackEntryId);
	}

	@Override
	public Map<Serializable, FeedbackEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FeedbackEntry> map = new HashMap<Serializable, FeedbackEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			FeedbackEntry feedbackEntry = fetchByPrimaryKey(primaryKey);

			if (feedbackEntry != null) {
				map.put(primaryKey, feedbackEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
					FeedbackEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (FeedbackEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FEEDBACKENTRY_WHERE_PKS_IN);

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

			for (FeedbackEntry feedbackEntry : (List<FeedbackEntry>)q.list()) {
				map.put(feedbackEntry.getPrimaryKeyObj(), feedbackEntry);

				cacheResult(feedbackEntry);

				uncachedPrimaryKeys.remove(feedbackEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(FeedbackEntryModelImpl.ENTITY_CACHE_ENABLED,
					FeedbackEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the feedback entries.
	 *
	 * @return the feedback entries
	 */
	@Override
	public List<FeedbackEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the feedback entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of feedback entries
	 * @param end the upper bound of the range of feedback entries (not inclusive)
	 * @return the range of feedback entries
	 */
	@Override
	public List<FeedbackEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the feedback entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of feedback entries
	 * @param end the upper bound of the range of feedback entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of feedback entries
	 */
	@Override
	public List<FeedbackEntry> findAll(int start, int end,
		OrderByComparator<FeedbackEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the feedback entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of feedback entries
	 * @param end the upper bound of the range of feedback entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of feedback entries
	 */
	@Override
	public List<FeedbackEntry> findAll(int start, int end,
		OrderByComparator<FeedbackEntry> orderByComparator,
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

		List<FeedbackEntry> list = null;

		if (retrieveFromCache) {
			list = (List<FeedbackEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FEEDBACKENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FEEDBACKENTRY;

				if (pagination) {
					sql = sql.concat(FeedbackEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FeedbackEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FeedbackEntry>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the feedback entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FeedbackEntry feedbackEntry : findAll()) {
			remove(feedbackEntry);
		}
	}

	/**
	 * Returns the number of feedback entries.
	 *
	 * @return the number of feedback entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FEEDBACKENTRY);

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
		return FeedbackEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the feedback entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(FeedbackEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_FEEDBACKENTRY = "SELECT feedbackEntry FROM FeedbackEntry feedbackEntry";
	private static final String _SQL_SELECT_FEEDBACKENTRY_WHERE_PKS_IN = "SELECT feedbackEntry FROM FeedbackEntry feedbackEntry WHERE feedbackEntryId IN (";
	private static final String _SQL_COUNT_FEEDBACKENTRY = "SELECT COUNT(feedbackEntry) FROM FeedbackEntry feedbackEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "feedbackEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FeedbackEntry exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(FeedbackEntryPersistenceImpl.class);
}
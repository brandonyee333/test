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

import com.liferay.osb.loop.exception.NoSuchLoopStreamException;
import com.liferay.osb.loop.model.LoopStream;
import com.liferay.osb.loop.model.impl.LoopStreamImpl;
import com.liferay.osb.loop.model.impl.LoopStreamModelImpl;
import com.liferay.osb.loop.service.persistence.LoopStreamPersistence;

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
 * The persistence implementation for the loop stream service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStreamPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopStreamUtil
 * @generated
 */
@ProviderType
public class LoopStreamPersistenceImpl extends BasePersistenceImpl<LoopStream>
	implements LoopStreamPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopStreamUtil} to access the loop stream persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopStreamImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamModelImpl.FINDER_CACHE_ENABLED, LoopStreamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamModelImpl.FINDER_CACHE_ENABLED, LoopStreamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public LoopStreamPersistenceImpl() {
		setModelClass(LoopStream.class);
	}

	/**
	 * Caches the loop stream in the entity cache if it is enabled.
	 *
	 * @param loopStream the loop stream
	 */
	@Override
	public void cacheResult(LoopStream loopStream) {
		entityCache.putResult(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamImpl.class, loopStream.getPrimaryKey(), loopStream);

		loopStream.resetOriginalValues();
	}

	/**
	 * Caches the loop streams in the entity cache if it is enabled.
	 *
	 * @param loopStreams the loop streams
	 */
	@Override
	public void cacheResult(List<LoopStream> loopStreams) {
		for (LoopStream loopStream : loopStreams) {
			if (entityCache.getResult(
						LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
						LoopStreamImpl.class, loopStream.getPrimaryKey()) == null) {
				cacheResult(loopStream);
			}
			else {
				loopStream.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop streams.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopStreamImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop stream.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopStream loopStream) {
		entityCache.removeResult(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamImpl.class, loopStream.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LoopStream> loopStreams) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopStream loopStream : loopStreams) {
			entityCache.removeResult(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
				LoopStreamImpl.class, loopStream.getPrimaryKey());
		}
	}

	/**
	 * Creates a new loop stream with the primary key. Does not add the loop stream to the database.
	 *
	 * @param loopStreamId the primary key for the new loop stream
	 * @return the new loop stream
	 */
	@Override
	public LoopStream create(long loopStreamId) {
		LoopStream loopStream = new LoopStreamImpl();

		loopStream.setNew(true);
		loopStream.setPrimaryKey(loopStreamId);

		return loopStream;
	}

	/**
	 * Removes the loop stream with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamId the primary key of the loop stream
	 * @return the loop stream that was removed
	 * @throws NoSuchLoopStreamException if a loop stream with the primary key could not be found
	 */
	@Override
	public LoopStream remove(long loopStreamId)
		throws NoSuchLoopStreamException {
		return remove((Serializable)loopStreamId);
	}

	/**
	 * Removes the loop stream with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop stream
	 * @return the loop stream that was removed
	 * @throws NoSuchLoopStreamException if a loop stream with the primary key could not be found
	 */
	@Override
	public LoopStream remove(Serializable primaryKey)
		throws NoSuchLoopStreamException {
		Session session = null;

		try {
			session = openSession();

			LoopStream loopStream = (LoopStream)session.get(LoopStreamImpl.class,
					primaryKey);

			if (loopStream == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopStreamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopStream);
		}
		catch (NoSuchLoopStreamException nsee) {
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
	protected LoopStream removeImpl(LoopStream loopStream) {
		loopStream = toUnwrappedModel(loopStream);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopStream)) {
				loopStream = (LoopStream)session.get(LoopStreamImpl.class,
						loopStream.getPrimaryKeyObj());
			}

			if (loopStream != null) {
				session.delete(loopStream);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopStream != null) {
			clearCache(loopStream);
		}

		return loopStream;
	}

	@Override
	public LoopStream updateImpl(LoopStream loopStream) {
		loopStream = toUnwrappedModel(loopStream);

		boolean isNew = loopStream.isNew();

		Session session = null;

		try {
			session = openSession();

			if (loopStream.isNew()) {
				session.save(loopStream);

				loopStream.setNew(false);
			}
			else {
				loopStream = (LoopStream)session.merge(loopStream);
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

		entityCache.putResult(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamImpl.class, loopStream.getPrimaryKey(), loopStream, false);

		loopStream.resetOriginalValues();

		return loopStream;
	}

	protected LoopStream toUnwrappedModel(LoopStream loopStream) {
		if (loopStream instanceof LoopStreamImpl) {
			return loopStream;
		}

		LoopStreamImpl loopStreamImpl = new LoopStreamImpl();

		loopStreamImpl.setNew(loopStream.isNew());
		loopStreamImpl.setPrimaryKey(loopStream.getPrimaryKey());

		loopStreamImpl.setLoopStreamId(loopStream.getLoopStreamId());
		loopStreamImpl.setLoopPersonId(loopStream.getLoopPersonId());
		loopStreamImpl.setName(loopStream.getName());
		loopStreamImpl.setDescription(loopStream.getDescription());

		return loopStreamImpl;
	}

	/**
	 * Returns the loop stream with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop stream
	 * @return the loop stream
	 * @throws NoSuchLoopStreamException if a loop stream with the primary key could not be found
	 */
	@Override
	public LoopStream findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopStreamException {
		LoopStream loopStream = fetchByPrimaryKey(primaryKey);

		if (loopStream == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopStreamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopStream;
	}

	/**
	 * Returns the loop stream with the primary key or throws a {@link NoSuchLoopStreamException} if it could not be found.
	 *
	 * @param loopStreamId the primary key of the loop stream
	 * @return the loop stream
	 * @throws NoSuchLoopStreamException if a loop stream with the primary key could not be found
	 */
	@Override
	public LoopStream findByPrimaryKey(long loopStreamId)
		throws NoSuchLoopStreamException {
		return findByPrimaryKey((Serializable)loopStreamId);
	}

	/**
	 * Returns the loop stream with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop stream
	 * @return the loop stream, or <code>null</code> if a loop stream with the primary key could not be found
	 */
	@Override
	public LoopStream fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
				LoopStreamImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopStream loopStream = (LoopStream)serializable;

		if (loopStream == null) {
			Session session = null;

			try {
				session = openSession();

				loopStream = (LoopStream)session.get(LoopStreamImpl.class,
						primaryKey);

				if (loopStream != null) {
					cacheResult(loopStream);
				}
				else {
					entityCache.putResult(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
						LoopStreamImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
					LoopStreamImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopStream;
	}

	/**
	 * Returns the loop stream with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopStreamId the primary key of the loop stream
	 * @return the loop stream, or <code>null</code> if a loop stream with the primary key could not be found
	 */
	@Override
	public LoopStream fetchByPrimaryKey(long loopStreamId) {
		return fetchByPrimaryKey((Serializable)loopStreamId);
	}

	@Override
	public Map<Serializable, LoopStream> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopStream> map = new HashMap<Serializable, LoopStream>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopStream loopStream = fetchByPrimaryKey(primaryKey);

			if (loopStream != null) {
				map.put(primaryKey, loopStream);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
					LoopStreamImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopStream)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPSTREAM_WHERE_PKS_IN);

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

			for (LoopStream loopStream : (List<LoopStream>)q.list()) {
				map.put(loopStream.getPrimaryKeyObj(), loopStream);

				cacheResult(loopStream);

				uncachedPrimaryKeys.remove(loopStream.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopStreamModelImpl.ENTITY_CACHE_ENABLED,
					LoopStreamImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop streams.
	 *
	 * @return the loop streams
	 */
	@Override
	public List<LoopStream> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop streams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop streams
	 * @param end the upper bound of the range of loop streams (not inclusive)
	 * @return the range of loop streams
	 */
	@Override
	public List<LoopStream> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop streams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop streams
	 * @param end the upper bound of the range of loop streams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop streams
	 */
	@Override
	public List<LoopStream> findAll(int start, int end,
		OrderByComparator<LoopStream> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop streams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop streams
	 * @param end the upper bound of the range of loop streams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop streams
	 */
	@Override
	public List<LoopStream> findAll(int start, int end,
		OrderByComparator<LoopStream> orderByComparator,
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

		List<LoopStream> list = null;

		if (retrieveFromCache) {
			list = (List<LoopStream>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPSTREAM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPSTREAM;

				if (pagination) {
					sql = sql.concat(LoopStreamModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopStream>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopStream>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the loop streams from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopStream loopStream : findAll()) {
			remove(loopStream);
		}
	}

	/**
	 * Returns the number of loop streams.
	 *
	 * @return the number of loop streams
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPSTREAM);

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
		return LoopStreamModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop stream persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopStreamImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPSTREAM = "SELECT loopStream FROM LoopStream loopStream";
	private static final String _SQL_SELECT_LOOPSTREAM_WHERE_PKS_IN = "SELECT loopStream FROM LoopStream loopStream WHERE loopStreamId IN (";
	private static final String _SQL_COUNT_LOOPSTREAM = "SELECT COUNT(loopStream) FROM LoopStream loopStream";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopStream.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopStream exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(LoopStreamPersistenceImpl.class);
}
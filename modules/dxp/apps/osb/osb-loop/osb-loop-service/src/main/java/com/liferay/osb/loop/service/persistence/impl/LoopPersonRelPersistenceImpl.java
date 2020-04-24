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

import com.liferay.osb.loop.exception.NoSuchLoopPersonRelException;
import com.liferay.osb.loop.model.LoopPersonRel;
import com.liferay.osb.loop.model.impl.LoopPersonRelImpl;
import com.liferay.osb.loop.model.impl.LoopPersonRelModelImpl;
import com.liferay.osb.loop.service.persistence.LoopPersonRelPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the loop person rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopPersonRelPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopPersonRelUtil
 * @generated
 */
@ProviderType
public class LoopPersonRelPersistenceImpl extends BasePersistenceImpl<LoopPersonRel>
	implements LoopPersonRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopPersonRelUtil} to access the loop person rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopPersonRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonRelModelImpl.FINDER_CACHE_ENABLED,
			LoopPersonRelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonRelModelImpl.FINDER_CACHE_ENABLED,
			LoopPersonRelImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public LoopPersonRelPersistenceImpl() {
		setModelClass(LoopPersonRel.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the loop person rel in the entity cache if it is enabled.
	 *
	 * @param loopPersonRel the loop person rel
	 */
	@Override
	public void cacheResult(LoopPersonRel loopPersonRel) {
		entityCache.putResult(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonRelImpl.class, loopPersonRel.getPrimaryKey(),
			loopPersonRel);

		loopPersonRel.resetOriginalValues();
	}

	/**
	 * Caches the loop person rels in the entity cache if it is enabled.
	 *
	 * @param loopPersonRels the loop person rels
	 */
	@Override
	public void cacheResult(List<LoopPersonRel> loopPersonRels) {
		for (LoopPersonRel loopPersonRel : loopPersonRels) {
			if (entityCache.getResult(
						LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
						LoopPersonRelImpl.class, loopPersonRel.getPrimaryKey()) == null) {
				cacheResult(loopPersonRel);
			}
			else {
				loopPersonRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop person rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopPersonRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop person rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopPersonRel loopPersonRel) {
		entityCache.removeResult(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonRelImpl.class, loopPersonRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LoopPersonRel> loopPersonRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopPersonRel loopPersonRel : loopPersonRels) {
			entityCache.removeResult(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
				LoopPersonRelImpl.class, loopPersonRel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new loop person rel with the primary key. Does not add the loop person rel to the database.
	 *
	 * @param loopPersonRelId the primary key for the new loop person rel
	 * @return the new loop person rel
	 */
	@Override
	public LoopPersonRel create(long loopPersonRelId) {
		LoopPersonRel loopPersonRel = new LoopPersonRelImpl();

		loopPersonRel.setNew(true);
		loopPersonRel.setPrimaryKey(loopPersonRelId);

		return loopPersonRel;
	}

	/**
	 * Removes the loop person rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopPersonRelId the primary key of the loop person rel
	 * @return the loop person rel that was removed
	 * @throws NoSuchLoopPersonRelException if a loop person rel with the primary key could not be found
	 */
	@Override
	public LoopPersonRel remove(long loopPersonRelId)
		throws NoSuchLoopPersonRelException {
		return remove((Serializable)loopPersonRelId);
	}

	/**
	 * Removes the loop person rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop person rel
	 * @return the loop person rel that was removed
	 * @throws NoSuchLoopPersonRelException if a loop person rel with the primary key could not be found
	 */
	@Override
	public LoopPersonRel remove(Serializable primaryKey)
		throws NoSuchLoopPersonRelException {
		Session session = null;

		try {
			session = openSession();

			LoopPersonRel loopPersonRel = (LoopPersonRel)session.get(LoopPersonRelImpl.class,
					primaryKey);

			if (loopPersonRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopPersonRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopPersonRel);
		}
		catch (NoSuchLoopPersonRelException nsee) {
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
	protected LoopPersonRel removeImpl(LoopPersonRel loopPersonRel) {
		loopPersonRel = toUnwrappedModel(loopPersonRel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopPersonRel)) {
				loopPersonRel = (LoopPersonRel)session.get(LoopPersonRelImpl.class,
						loopPersonRel.getPrimaryKeyObj());
			}

			if (loopPersonRel != null) {
				session.delete(loopPersonRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopPersonRel != null) {
			clearCache(loopPersonRel);
		}

		return loopPersonRel;
	}

	@Override
	public LoopPersonRel updateImpl(LoopPersonRel loopPersonRel) {
		loopPersonRel = toUnwrappedModel(loopPersonRel);

		boolean isNew = loopPersonRel.isNew();

		Session session = null;

		try {
			session = openSession();

			if (loopPersonRel.isNew()) {
				session.save(loopPersonRel);

				loopPersonRel.setNew(false);
			}
			else {
				loopPersonRel = (LoopPersonRel)session.merge(loopPersonRel);
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

		entityCache.putResult(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonRelImpl.class, loopPersonRel.getPrimaryKey(),
			loopPersonRel, false);

		loopPersonRel.resetOriginalValues();

		return loopPersonRel;
	}

	protected LoopPersonRel toUnwrappedModel(LoopPersonRel loopPersonRel) {
		if (loopPersonRel instanceof LoopPersonRelImpl) {
			return loopPersonRel;
		}

		LoopPersonRelImpl loopPersonRelImpl = new LoopPersonRelImpl();

		loopPersonRelImpl.setNew(loopPersonRel.isNew());
		loopPersonRelImpl.setPrimaryKey(loopPersonRel.getPrimaryKey());

		loopPersonRelImpl.setLoopPersonRelId(loopPersonRel.getLoopPersonRelId());
		loopPersonRelImpl.setChildLoopPersonId(loopPersonRel.getChildLoopPersonId());
		loopPersonRelImpl.setParentLoopPersonId(loopPersonRel.getParentLoopPersonId());
		loopPersonRelImpl.setType(loopPersonRel.getType());

		return loopPersonRelImpl;
	}

	/**
	 * Returns the loop person rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop person rel
	 * @return the loop person rel
	 * @throws NoSuchLoopPersonRelException if a loop person rel with the primary key could not be found
	 */
	@Override
	public LoopPersonRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopPersonRelException {
		LoopPersonRel loopPersonRel = fetchByPrimaryKey(primaryKey);

		if (loopPersonRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopPersonRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopPersonRel;
	}

	/**
	 * Returns the loop person rel with the primary key or throws a {@link NoSuchLoopPersonRelException} if it could not be found.
	 *
	 * @param loopPersonRelId the primary key of the loop person rel
	 * @return the loop person rel
	 * @throws NoSuchLoopPersonRelException if a loop person rel with the primary key could not be found
	 */
	@Override
	public LoopPersonRel findByPrimaryKey(long loopPersonRelId)
		throws NoSuchLoopPersonRelException {
		return findByPrimaryKey((Serializable)loopPersonRelId);
	}

	/**
	 * Returns the loop person rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop person rel
	 * @return the loop person rel, or <code>null</code> if a loop person rel with the primary key could not be found
	 */
	@Override
	public LoopPersonRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
				LoopPersonRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopPersonRel loopPersonRel = (LoopPersonRel)serializable;

		if (loopPersonRel == null) {
			Session session = null;

			try {
				session = openSession();

				loopPersonRel = (LoopPersonRel)session.get(LoopPersonRelImpl.class,
						primaryKey);

				if (loopPersonRel != null) {
					cacheResult(loopPersonRel);
				}
				else {
					entityCache.putResult(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
						LoopPersonRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
					LoopPersonRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopPersonRel;
	}

	/**
	 * Returns the loop person rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopPersonRelId the primary key of the loop person rel
	 * @return the loop person rel, or <code>null</code> if a loop person rel with the primary key could not be found
	 */
	@Override
	public LoopPersonRel fetchByPrimaryKey(long loopPersonRelId) {
		return fetchByPrimaryKey((Serializable)loopPersonRelId);
	}

	@Override
	public Map<Serializable, LoopPersonRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopPersonRel> map = new HashMap<Serializable, LoopPersonRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopPersonRel loopPersonRel = fetchByPrimaryKey(primaryKey);

			if (loopPersonRel != null) {
				map.put(primaryKey, loopPersonRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
					LoopPersonRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopPersonRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPPERSONREL_WHERE_PKS_IN);

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

			for (LoopPersonRel loopPersonRel : (List<LoopPersonRel>)q.list()) {
				map.put(loopPersonRel.getPrimaryKeyObj(), loopPersonRel);

				cacheResult(loopPersonRel);

				uncachedPrimaryKeys.remove(loopPersonRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopPersonRelModelImpl.ENTITY_CACHE_ENABLED,
					LoopPersonRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop person rels.
	 *
	 * @return the loop person rels
	 */
	@Override
	public List<LoopPersonRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop person rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop person rels
	 * @param end the upper bound of the range of loop person rels (not inclusive)
	 * @return the range of loop person rels
	 */
	@Override
	public List<LoopPersonRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop person rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop person rels
	 * @param end the upper bound of the range of loop person rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop person rels
	 */
	@Override
	public List<LoopPersonRel> findAll(int start, int end,
		OrderByComparator<LoopPersonRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop person rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop person rels
	 * @param end the upper bound of the range of loop person rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop person rels
	 */
	@Override
	public List<LoopPersonRel> findAll(int start, int end,
		OrderByComparator<LoopPersonRel> orderByComparator,
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

		List<LoopPersonRel> list = null;

		if (retrieveFromCache) {
			list = (List<LoopPersonRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPPERSONREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPPERSONREL;

				if (pagination) {
					sql = sql.concat(LoopPersonRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopPersonRel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopPersonRel>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the loop person rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopPersonRel loopPersonRel : findAll()) {
			remove(loopPersonRel);
		}
	}

	/**
	 * Returns the number of loop person rels.
	 *
	 * @return the number of loop person rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPPERSONREL);

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
		return LoopPersonRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop person rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopPersonRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPPERSONREL = "SELECT loopPersonRel FROM LoopPersonRel loopPersonRel";
	private static final String _SQL_SELECT_LOOPPERSONREL_WHERE_PKS_IN = "SELECT loopPersonRel FROM LoopPersonRel loopPersonRel WHERE loopPersonRelId IN (";
	private static final String _SQL_COUNT_LOOPPERSONREL = "SELECT COUNT(loopPersonRel) FROM LoopPersonRel loopPersonRel";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopPersonRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopPersonRel exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(LoopPersonRelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
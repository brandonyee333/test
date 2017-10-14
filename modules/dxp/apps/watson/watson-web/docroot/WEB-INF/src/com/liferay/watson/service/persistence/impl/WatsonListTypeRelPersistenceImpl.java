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

package com.liferay.watson.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.watson.exception.NoSuchListTypeRelException;
import com.liferay.watson.model.WatsonListTypeRel;
import com.liferay.watson.model.impl.WatsonListTypeRelImpl;
import com.liferay.watson.model.impl.WatsonListTypeRelModelImpl;
import com.liferay.watson.service.persistence.WatsonListTypeRelPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the watson list type rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eddie Olson
 * @see WatsonListTypeRelPersistence
 * @see com.liferay.watson.service.persistence.WatsonListTypeRelUtil
 * @generated
 */
@ProviderType
public class WatsonListTypeRelPersistenceImpl extends BasePersistenceImpl<WatsonListTypeRel>
	implements WatsonListTypeRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WatsonListTypeRelUtil} to access the watson list type rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WatsonListTypeRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WatsonListTypeRelPersistenceImpl() {
		setModelClass(WatsonListTypeRel.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("primary", "primary_");
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
	 * Caches the watson list type rel in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeRel the watson list type rel
	 */
	@Override
	public void cacheResult(WatsonListTypeRel watsonListTypeRel) {
		entityCache.putResult(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelImpl.class, watsonListTypeRel.getPrimaryKey(),
			watsonListTypeRel);

		watsonListTypeRel.resetOriginalValues();
	}

	/**
	 * Caches the watson list type rels in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeRels the watson list type rels
	 */
	@Override
	public void cacheResult(List<WatsonListTypeRel> watsonListTypeRels) {
		for (WatsonListTypeRel watsonListTypeRel : watsonListTypeRels) {
			if (entityCache.getResult(
						WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
						WatsonListTypeRelImpl.class,
						watsonListTypeRel.getPrimaryKey()) == null) {
				cacheResult(watsonListTypeRel);
			}
			else {
				watsonListTypeRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson list type rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonListTypeRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson list type rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonListTypeRel watsonListTypeRel) {
		entityCache.removeResult(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelImpl.class, watsonListTypeRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonListTypeRel> watsonListTypeRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonListTypeRel watsonListTypeRel : watsonListTypeRels) {
			entityCache.removeResult(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeRelImpl.class, watsonListTypeRel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new watson list type rel with the primary key. Does not add the watson list type rel to the database.
	 *
	 * @param watsonListTypeRelId the primary key for the new watson list type rel
	 * @return the new watson list type rel
	 */
	@Override
	public WatsonListTypeRel create(long watsonListTypeRelId) {
		WatsonListTypeRel watsonListTypeRel = new WatsonListTypeRelImpl();

		watsonListTypeRel.setNew(true);
		watsonListTypeRel.setPrimaryKey(watsonListTypeRelId);

		watsonListTypeRel.setCompanyId(companyProvider.getCompanyId());

		return watsonListTypeRel;
	}

	/**
	 * Removes the watson list type rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelId the primary key of the watson list type rel
	 * @return the watson list type rel that was removed
	 * @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel remove(long watsonListTypeRelId)
		throws NoSuchListTypeRelException {
		return remove((Serializable)watsonListTypeRelId);
	}

	/**
	 * Removes the watson list type rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson list type rel
	 * @return the watson list type rel that was removed
	 * @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel remove(Serializable primaryKey)
		throws NoSuchListTypeRelException {
		Session session = null;

		try {
			session = openSession();

			WatsonListTypeRel watsonListTypeRel = (WatsonListTypeRel)session.get(WatsonListTypeRelImpl.class,
					primaryKey);

			if (watsonListTypeRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchListTypeRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(watsonListTypeRel);
		}
		catch (NoSuchListTypeRelException nsee) {
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
	protected WatsonListTypeRel removeImpl(WatsonListTypeRel watsonListTypeRel) {
		watsonListTypeRel = toUnwrappedModel(watsonListTypeRel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonListTypeRel)) {
				watsonListTypeRel = (WatsonListTypeRel)session.get(WatsonListTypeRelImpl.class,
						watsonListTypeRel.getPrimaryKeyObj());
			}

			if (watsonListTypeRel != null) {
				session.delete(watsonListTypeRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (watsonListTypeRel != null) {
			clearCache(watsonListTypeRel);
		}

		return watsonListTypeRel;
	}

	@Override
	public WatsonListTypeRel updateImpl(WatsonListTypeRel watsonListTypeRel) {
		watsonListTypeRel = toUnwrappedModel(watsonListTypeRel);

		boolean isNew = watsonListTypeRel.isNew();

		WatsonListTypeRelModelImpl watsonListTypeRelModelImpl = (WatsonListTypeRelModelImpl)watsonListTypeRel;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonListTypeRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonListTypeRel.setCreateDate(now);
			}
			else {
				watsonListTypeRel.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!watsonListTypeRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonListTypeRel.setModifiedDate(now);
			}
			else {
				watsonListTypeRel.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonListTypeRel.isNew()) {
				session.save(watsonListTypeRel);

				watsonListTypeRel.setNew(false);
			}
			else {
				watsonListTypeRel = (WatsonListTypeRel)session.merge(watsonListTypeRel);
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

		entityCache.putResult(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelImpl.class, watsonListTypeRel.getPrimaryKey(),
			watsonListTypeRel, false);

		watsonListTypeRel.resetOriginalValues();

		return watsonListTypeRel;
	}

	protected WatsonListTypeRel toUnwrappedModel(
		WatsonListTypeRel watsonListTypeRel) {
		if (watsonListTypeRel instanceof WatsonListTypeRelImpl) {
			return watsonListTypeRel;
		}

		WatsonListTypeRelImpl watsonListTypeRelImpl = new WatsonListTypeRelImpl();

		watsonListTypeRelImpl.setNew(watsonListTypeRel.isNew());
		watsonListTypeRelImpl.setPrimaryKey(watsonListTypeRel.getPrimaryKey());

		watsonListTypeRelImpl.setWatsonListTypeRelId(watsonListTypeRel.getWatsonListTypeRelId());
		watsonListTypeRelImpl.setCompanyId(watsonListTypeRel.getCompanyId());
		watsonListTypeRelImpl.setUserId(watsonListTypeRel.getUserId());
		watsonListTypeRelImpl.setUserName(watsonListTypeRel.getUserName());
		watsonListTypeRelImpl.setCreateDate(watsonListTypeRel.getCreateDate());
		watsonListTypeRelImpl.setModifiedDate(watsonListTypeRel.getModifiedDate());
		watsonListTypeRelImpl.setWatsonListTypeId(watsonListTypeRel.getWatsonListTypeId());
		watsonListTypeRelImpl.setClassNameId(watsonListTypeRel.getClassNameId());
		watsonListTypeRelImpl.setClassPK(watsonListTypeRel.getClassPK());
		watsonListTypeRelImpl.setPrimary(watsonListTypeRel.isPrimary());
		watsonListTypeRelImpl.setValue(watsonListTypeRel.getValue());
		watsonListTypeRelImpl.setType(watsonListTypeRel.getType());
		watsonListTypeRelImpl.setStatus(watsonListTypeRel.getStatus());

		return watsonListTypeRelImpl;
	}

	/**
	 * Returns the watson list type rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type rel
	 * @return the watson list type rel
	 * @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchListTypeRelException {
		WatsonListTypeRel watsonListTypeRel = fetchByPrimaryKey(primaryKey);

		if (watsonListTypeRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchListTypeRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return watsonListTypeRel;
	}

	/**
	 * Returns the watson list type rel with the primary key or throws a {@link NoSuchListTypeRelException} if it could not be found.
	 *
	 * @param watsonListTypeRelId the primary key of the watson list type rel
	 * @return the watson list type rel
	 * @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel findByPrimaryKey(long watsonListTypeRelId)
		throws NoSuchListTypeRelException {
		return findByPrimaryKey((Serializable)watsonListTypeRelId);
	}

	/**
	 * Returns the watson list type rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type rel
	 * @return the watson list type rel, or <code>null</code> if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonListTypeRel watsonListTypeRel = (WatsonListTypeRel)serializable;

		if (watsonListTypeRel == null) {
			Session session = null;

			try {
				session = openSession();

				watsonListTypeRel = (WatsonListTypeRel)session.get(WatsonListTypeRelImpl.class,
						primaryKey);

				if (watsonListTypeRel != null) {
					cacheResult(watsonListTypeRel);
				}
				else {
					entityCache.putResult(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
						WatsonListTypeRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonListTypeRel;
	}

	/**
	 * Returns the watson list type rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonListTypeRelId the primary key of the watson list type rel
	 * @return the watson list type rel, or <code>null</code> if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel fetchByPrimaryKey(long watsonListTypeRelId) {
		return fetchByPrimaryKey((Serializable)watsonListTypeRelId);
	}

	@Override
	public Map<Serializable, WatsonListTypeRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonListTypeRel> map = new HashMap<Serializable, WatsonListTypeRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonListTypeRel watsonListTypeRel = fetchByPrimaryKey(primaryKey);

			if (watsonListTypeRel != null) {
				map.put(primaryKey, watsonListTypeRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonListTypeRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WATSONLISTTYPEREL_WHERE_PKS_IN);

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

			for (WatsonListTypeRel watsonListTypeRel : (List<WatsonListTypeRel>)q.list()) {
				map.put(watsonListTypeRel.getPrimaryKeyObj(), watsonListTypeRel);

				cacheResult(watsonListTypeRel);

				uncachedPrimaryKeys.remove(watsonListTypeRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson list type rels.
	 *
	 * @return the watson list type rels
	 */
	@Override
	public List<WatsonListTypeRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson list type rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rels
	 * @param end the upper bound of the range of watson list type rels (not inclusive)
	 * @return the range of watson list type rels
	 */
	@Override
	public List<WatsonListTypeRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson list type rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rels
	 * @param end the upper bound of the range of watson list type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson list type rels
	 */
	@Override
	public List<WatsonListTypeRel> findAll(int start, int end,
		OrderByComparator<WatsonListTypeRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson list type rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rels
	 * @param end the upper bound of the range of watson list type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson list type rels
	 */
	@Override
	public List<WatsonListTypeRel> findAll(int start, int end,
		OrderByComparator<WatsonListTypeRel> orderByComparator,
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

		List<WatsonListTypeRel> list = null;

		if (retrieveFromCache) {
			list = (List<WatsonListTypeRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WATSONLISTTYPEREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONLISTTYPEREL;

				if (pagination) {
					sql = sql.concat(WatsonListTypeRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WatsonListTypeRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WatsonListTypeRel>)QueryUtil.list(q,
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
	 * Removes all the watson list type rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonListTypeRel watsonListTypeRel : findAll()) {
			remove(watsonListTypeRel);
		}
	}

	/**
	 * Returns the number of watson list type rels.
	 *
	 * @return the number of watson list type rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WATSONLISTTYPEREL);

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
		return WatsonListTypeRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson list type rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WatsonListTypeRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_WATSONLISTTYPEREL = "SELECT watsonListTypeRel FROM WatsonListTypeRel watsonListTypeRel";
	private static final String _SQL_SELECT_WATSONLISTTYPEREL_WHERE_PKS_IN = "SELECT watsonListTypeRel FROM WatsonListTypeRel watsonListTypeRel WHERE watsonListTypeRelId IN (";
	private static final String _SQL_COUNT_WATSONLISTTYPEREL = "SELECT COUNT(watsonListTypeRel) FROM WatsonListTypeRel watsonListTypeRel";
	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonListTypeRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WatsonListTypeRel exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WatsonListTypeRelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"primary", "type"
			});
}
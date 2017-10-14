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

import com.liferay.watson.exception.NoSuchListTypeException;
import com.liferay.watson.model.WatsonListType;
import com.liferay.watson.model.impl.WatsonListTypeImpl;
import com.liferay.watson.model.impl.WatsonListTypeModelImpl;
import com.liferay.watson.service.persistence.WatsonListTypePersistence;

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
 * The persistence implementation for the watson list type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eddie Olson
 * @see WatsonListTypePersistence
 * @see com.liferay.watson.service.persistence.WatsonListTypeUtil
 * @generated
 */
@ProviderType
public class WatsonListTypePersistenceImpl extends BasePersistenceImpl<WatsonListType>
	implements WatsonListTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WatsonListTypeUtil} to access the watson list type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WatsonListTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WatsonListTypePersistenceImpl() {
		setModelClass(WatsonListType.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

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
	 * Caches the watson list type in the entity cache if it is enabled.
	 *
	 * @param watsonListType the watson list type
	 */
	@Override
	public void cacheResult(WatsonListType watsonListType) {
		entityCache.putResult(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeImpl.class, watsonListType.getPrimaryKey(),
			watsonListType);

		watsonListType.resetOriginalValues();
	}

	/**
	 * Caches the watson list types in the entity cache if it is enabled.
	 *
	 * @param watsonListTypes the watson list types
	 */
	@Override
	public void cacheResult(List<WatsonListType> watsonListTypes) {
		for (WatsonListType watsonListType : watsonListTypes) {
			if (entityCache.getResult(
						WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
						WatsonListTypeImpl.class, watsonListType.getPrimaryKey()) == null) {
				cacheResult(watsonListType);
			}
			else {
				watsonListType.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson list types.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonListTypeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson list type.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonListType watsonListType) {
		entityCache.removeResult(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeImpl.class, watsonListType.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonListType> watsonListTypes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonListType watsonListType : watsonListTypes) {
			entityCache.removeResult(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeImpl.class, watsonListType.getPrimaryKey());
		}
	}

	/**
	 * Creates a new watson list type with the primary key. Does not add the watson list type to the database.
	 *
	 * @param watsonListTypeId the primary key for the new watson list type
	 * @return the new watson list type
	 */
	@Override
	public WatsonListType create(long watsonListTypeId) {
		WatsonListType watsonListType = new WatsonListTypeImpl();

		watsonListType.setNew(true);
		watsonListType.setPrimaryKey(watsonListTypeId);

		watsonListType.setCompanyId(companyProvider.getCompanyId());

		return watsonListType;
	}

	/**
	 * Removes the watson list type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeId the primary key of the watson list type
	 * @return the watson list type that was removed
	 * @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType remove(long watsonListTypeId)
		throws NoSuchListTypeException {
		return remove((Serializable)watsonListTypeId);
	}

	/**
	 * Removes the watson list type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson list type
	 * @return the watson list type that was removed
	 * @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType remove(Serializable primaryKey)
		throws NoSuchListTypeException {
		Session session = null;

		try {
			session = openSession();

			WatsonListType watsonListType = (WatsonListType)session.get(WatsonListTypeImpl.class,
					primaryKey);

			if (watsonListType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchListTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(watsonListType);
		}
		catch (NoSuchListTypeException nsee) {
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
	protected WatsonListType removeImpl(WatsonListType watsonListType) {
		watsonListType = toUnwrappedModel(watsonListType);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonListType)) {
				watsonListType = (WatsonListType)session.get(WatsonListTypeImpl.class,
						watsonListType.getPrimaryKeyObj());
			}

			if (watsonListType != null) {
				session.delete(watsonListType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (watsonListType != null) {
			clearCache(watsonListType);
		}

		return watsonListType;
	}

	@Override
	public WatsonListType updateImpl(WatsonListType watsonListType) {
		watsonListType = toUnwrappedModel(watsonListType);

		boolean isNew = watsonListType.isNew();

		WatsonListTypeModelImpl watsonListTypeModelImpl = (WatsonListTypeModelImpl)watsonListType;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonListType.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonListType.setCreateDate(now);
			}
			else {
				watsonListType.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonListTypeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonListType.setModifiedDate(now);
			}
			else {
				watsonListType.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonListType.isNew()) {
				session.save(watsonListType);

				watsonListType.setNew(false);
			}
			else {
				watsonListType = (WatsonListType)session.merge(watsonListType);
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

		entityCache.putResult(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeImpl.class, watsonListType.getPrimaryKey(),
			watsonListType, false);

		watsonListType.resetOriginalValues();

		return watsonListType;
	}

	protected WatsonListType toUnwrappedModel(WatsonListType watsonListType) {
		if (watsonListType instanceof WatsonListTypeImpl) {
			return watsonListType;
		}

		WatsonListTypeImpl watsonListTypeImpl = new WatsonListTypeImpl();

		watsonListTypeImpl.setNew(watsonListType.isNew());
		watsonListTypeImpl.setPrimaryKey(watsonListType.getPrimaryKey());

		watsonListTypeImpl.setWatsonListTypeId(watsonListType.getWatsonListTypeId());
		watsonListTypeImpl.setCompanyId(watsonListType.getCompanyId());
		watsonListTypeImpl.setUserId(watsonListType.getUserId());
		watsonListTypeImpl.setUserName(watsonListType.getUserName());
		watsonListTypeImpl.setCreateDate(watsonListType.getCreateDate());
		watsonListTypeImpl.setModifiedDate(watsonListType.getModifiedDate());
		watsonListTypeImpl.setParentWatsonListTypeId(watsonListType.getParentWatsonListTypeId());
		watsonListTypeImpl.setName(watsonListType.getName());
		watsonListTypeImpl.setType(watsonListType.getType());
		watsonListTypeImpl.setStatus(watsonListType.getStatus());

		return watsonListTypeImpl;
	}

	/**
	 * Returns the watson list type with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type
	 * @return the watson list type
	 * @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchListTypeException {
		WatsonListType watsonListType = fetchByPrimaryKey(primaryKey);

		if (watsonListType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchListTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return watsonListType;
	}

	/**
	 * Returns the watson list type with the primary key or throws a {@link NoSuchListTypeException} if it could not be found.
	 *
	 * @param watsonListTypeId the primary key of the watson list type
	 * @return the watson list type
	 * @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType findByPrimaryKey(long watsonListTypeId)
		throws NoSuchListTypeException {
		return findByPrimaryKey((Serializable)watsonListTypeId);
	}

	/**
	 * Returns the watson list type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type
	 * @return the watson list type, or <code>null</code> if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonListType watsonListType = (WatsonListType)serializable;

		if (watsonListType == null) {
			Session session = null;

			try {
				session = openSession();

				watsonListType = (WatsonListType)session.get(WatsonListTypeImpl.class,
						primaryKey);

				if (watsonListType != null) {
					cacheResult(watsonListType);
				}
				else {
					entityCache.putResult(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
						WatsonListTypeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonListType;
	}

	/**
	 * Returns the watson list type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonListTypeId the primary key of the watson list type
	 * @return the watson list type, or <code>null</code> if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType fetchByPrimaryKey(long watsonListTypeId) {
		return fetchByPrimaryKey((Serializable)watsonListTypeId);
	}

	@Override
	public Map<Serializable, WatsonListType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonListType> map = new HashMap<Serializable, WatsonListType>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonListType watsonListType = fetchByPrimaryKey(primaryKey);

			if (watsonListType != null) {
				map.put(primaryKey, watsonListType);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonListType)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WATSONLISTTYPE_WHERE_PKS_IN);

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

			for (WatsonListType watsonListType : (List<WatsonListType>)q.list()) {
				map.put(watsonListType.getPrimaryKeyObj(), watsonListType);

				cacheResult(watsonListType);

				uncachedPrimaryKeys.remove(watsonListType.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson list types.
	 *
	 * @return the watson list types
	 */
	@Override
	public List<WatsonListType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson list types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list types
	 * @param end the upper bound of the range of watson list types (not inclusive)
	 * @return the range of watson list types
	 */
	@Override
	public List<WatsonListType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson list types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list types
	 * @param end the upper bound of the range of watson list types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson list types
	 */
	@Override
	public List<WatsonListType> findAll(int start, int end,
		OrderByComparator<WatsonListType> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson list types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list types
	 * @param end the upper bound of the range of watson list types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson list types
	 */
	@Override
	public List<WatsonListType> findAll(int start, int end,
		OrderByComparator<WatsonListType> orderByComparator,
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

		List<WatsonListType> list = null;

		if (retrieveFromCache) {
			list = (List<WatsonListType>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WATSONLISTTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONLISTTYPE;

				if (pagination) {
					sql = sql.concat(WatsonListTypeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WatsonListType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WatsonListType>)QueryUtil.list(q,
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
	 * Removes all the watson list types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonListType watsonListType : findAll()) {
			remove(watsonListType);
		}
	}

	/**
	 * Returns the number of watson list types.
	 *
	 * @return the number of watson list types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WATSONLISTTYPE);

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
		return WatsonListTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson list type persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WatsonListTypeImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_WATSONLISTTYPE = "SELECT watsonListType FROM WatsonListType watsonListType";
	private static final String _SQL_SELECT_WATSONLISTTYPE_WHERE_PKS_IN = "SELECT watsonListType FROM WatsonListType watsonListType WHERE watsonListTypeId IN (";
	private static final String _SQL_COUNT_WATSONLISTTYPE = "SELECT COUNT(watsonListType) FROM WatsonListType watsonListType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonListType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WatsonListType exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WatsonListTypePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
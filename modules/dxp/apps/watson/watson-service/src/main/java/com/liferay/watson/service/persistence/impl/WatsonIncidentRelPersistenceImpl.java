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

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
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
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.liferay.watson.exception.NoSuchIncidentRelException;
import com.liferay.watson.model.WatsonIncidentRel;
import com.liferay.watson.model.impl.WatsonIncidentRelImpl;
import com.liferay.watson.model.impl.WatsonIncidentRelModelImpl;
import com.liferay.watson.service.persistence.WatsonIncidentRelPersistence;

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
 * The persistence implementation for the watson incident rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonIncidentRelPersistence
 * @see com.liferay.watson.service.persistence.WatsonIncidentRelUtil
 * @generated
 */
@ProviderType
public class WatsonIncidentRelPersistenceImpl extends BasePersistenceImpl<WatsonIncidentRel>
	implements WatsonIncidentRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WatsonIncidentRelUtil} to access the watson incident rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WatsonIncidentRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelModelImpl.FINDER_CACHE_ENABLED,
			WatsonIncidentRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelModelImpl.FINDER_CACHE_ENABLED,
			WatsonIncidentRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WatsonIncidentRelPersistenceImpl() {
		setModelClass(WatsonIncidentRel.class);

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
	 * Caches the watson incident rel in the entity cache if it is enabled.
	 *
	 * @param watsonIncidentRel the watson incident rel
	 */
	@Override
	public void cacheResult(WatsonIncidentRel watsonIncidentRel) {
		entityCache.putResult(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelImpl.class, watsonIncidentRel.getPrimaryKey(),
			watsonIncidentRel);

		watsonIncidentRel.resetOriginalValues();
	}

	/**
	 * Caches the watson incident rels in the entity cache if it is enabled.
	 *
	 * @param watsonIncidentRels the watson incident rels
	 */
	@Override
	public void cacheResult(List<WatsonIncidentRel> watsonIncidentRels) {
		for (WatsonIncidentRel watsonIncidentRel : watsonIncidentRels) {
			if (entityCache.getResult(
						WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
						WatsonIncidentRelImpl.class,
						watsonIncidentRel.getPrimaryKey()) == null) {
				cacheResult(watsonIncidentRel);
			}
			else {
				watsonIncidentRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson incident rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonIncidentRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson incident rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonIncidentRel watsonIncidentRel) {
		entityCache.removeResult(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelImpl.class, watsonIncidentRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonIncidentRel> watsonIncidentRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonIncidentRel watsonIncidentRel : watsonIncidentRels) {
			entityCache.removeResult(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentRelImpl.class, watsonIncidentRel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new watson incident rel with the primary key. Does not add the watson incident rel to the database.
	 *
	 * @param watsonIncidentRelId the primary key for the new watson incident rel
	 * @return the new watson incident rel
	 */
	@Override
	public WatsonIncidentRel create(long watsonIncidentRelId) {
		WatsonIncidentRel watsonIncidentRel = new WatsonIncidentRelImpl();

		watsonIncidentRel.setNew(true);
		watsonIncidentRel.setPrimaryKey(watsonIncidentRelId);

		watsonIncidentRel.setCompanyId(companyProvider.getCompanyId());

		return watsonIncidentRel;
	}

	/**
	 * Removes the watson incident rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel that was removed
	 * @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel remove(long watsonIncidentRelId)
		throws NoSuchIncidentRelException {
		return remove((Serializable)watsonIncidentRelId);
	}

	/**
	 * Removes the watson incident rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson incident rel
	 * @return the watson incident rel that was removed
	 * @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel remove(Serializable primaryKey)
		throws NoSuchIncidentRelException {
		Session session = null;

		try {
			session = openSession();

			WatsonIncidentRel watsonIncidentRel = (WatsonIncidentRel)session.get(WatsonIncidentRelImpl.class,
					primaryKey);

			if (watsonIncidentRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIncidentRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(watsonIncidentRel);
		}
		catch (NoSuchIncidentRelException nsee) {
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
	protected WatsonIncidentRel removeImpl(WatsonIncidentRel watsonIncidentRel) {
		watsonIncidentRel = toUnwrappedModel(watsonIncidentRel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonIncidentRel)) {
				watsonIncidentRel = (WatsonIncidentRel)session.get(WatsonIncidentRelImpl.class,
						watsonIncidentRel.getPrimaryKeyObj());
			}

			if (watsonIncidentRel != null) {
				session.delete(watsonIncidentRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (watsonIncidentRel != null) {
			clearCache(watsonIncidentRel);
		}

		return watsonIncidentRel;
	}

	@Override
	public WatsonIncidentRel updateImpl(WatsonIncidentRel watsonIncidentRel) {
		watsonIncidentRel = toUnwrappedModel(watsonIncidentRel);

		boolean isNew = watsonIncidentRel.isNew();

		WatsonIncidentRelModelImpl watsonIncidentRelModelImpl = (WatsonIncidentRelModelImpl)watsonIncidentRel;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonIncidentRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonIncidentRel.setCreateDate(now);
			}
			else {
				watsonIncidentRel.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!watsonIncidentRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonIncidentRel.setModifiedDate(now);
			}
			else {
				watsonIncidentRel.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonIncidentRel.isNew()) {
				session.save(watsonIncidentRel);

				watsonIncidentRel.setNew(false);
			}
			else {
				watsonIncidentRel = (WatsonIncidentRel)session.merge(watsonIncidentRel);
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

		entityCache.putResult(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelImpl.class, watsonIncidentRel.getPrimaryKey(),
			watsonIncidentRel, false);

		watsonIncidentRel.resetOriginalValues();

		return watsonIncidentRel;
	}

	protected WatsonIncidentRel toUnwrappedModel(
		WatsonIncidentRel watsonIncidentRel) {
		if (watsonIncidentRel instanceof WatsonIncidentRelImpl) {
			return watsonIncidentRel;
		}

		WatsonIncidentRelImpl watsonIncidentRelImpl = new WatsonIncidentRelImpl();

		watsonIncidentRelImpl.setNew(watsonIncidentRel.isNew());
		watsonIncidentRelImpl.setPrimaryKey(watsonIncidentRel.getPrimaryKey());

		watsonIncidentRelImpl.setWatsonIncidentRelId(watsonIncidentRel.getWatsonIncidentRelId());
		watsonIncidentRelImpl.setCompanyId(watsonIncidentRel.getCompanyId());
		watsonIncidentRelImpl.setUserId(watsonIncidentRel.getUserId());
		watsonIncidentRelImpl.setUserName(watsonIncidentRel.getUserName());
		watsonIncidentRelImpl.setCreateDate(watsonIncidentRel.getCreateDate());
		watsonIncidentRelImpl.setModifiedDate(watsonIncidentRel.getModifiedDate());
		watsonIncidentRelImpl.setWatsonIncidentId1(watsonIncidentRel.getWatsonIncidentId1());
		watsonIncidentRelImpl.setWatsonIncidentId2(watsonIncidentRel.getWatsonIncidentId2());
		watsonIncidentRelImpl.setType(watsonIncidentRel.getType());
		watsonIncidentRelImpl.setStatus(watsonIncidentRel.getStatus());

		return watsonIncidentRelImpl;
	}

	/**
	 * Returns the watson incident rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson incident rel
	 * @return the watson incident rel
	 * @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIncidentRelException {
		WatsonIncidentRel watsonIncidentRel = fetchByPrimaryKey(primaryKey);

		if (watsonIncidentRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIncidentRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return watsonIncidentRel;
	}

	/**
	 * Returns the watson incident rel with the primary key or throws a {@link NoSuchIncidentRelException} if it could not be found.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel
	 * @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel findByPrimaryKey(long watsonIncidentRelId)
		throws NoSuchIncidentRelException {
		return findByPrimaryKey((Serializable)watsonIncidentRelId);
	}

	/**
	 * Returns the watson incident rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson incident rel
	 * @return the watson incident rel, or <code>null</code> if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonIncidentRel watsonIncidentRel = (WatsonIncidentRel)serializable;

		if (watsonIncidentRel == null) {
			Session session = null;

			try {
				session = openSession();

				watsonIncidentRel = (WatsonIncidentRel)session.get(WatsonIncidentRelImpl.class,
						primaryKey);

				if (watsonIncidentRel != null) {
					cacheResult(watsonIncidentRel);
				}
				else {
					entityCache.putResult(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
						WatsonIncidentRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonIncidentRel;
	}

	/**
	 * Returns the watson incident rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel, or <code>null</code> if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel fetchByPrimaryKey(long watsonIncidentRelId) {
		return fetchByPrimaryKey((Serializable)watsonIncidentRelId);
	}

	@Override
	public Map<Serializable, WatsonIncidentRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonIncidentRel> map = new HashMap<Serializable, WatsonIncidentRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonIncidentRel watsonIncidentRel = fetchByPrimaryKey(primaryKey);

			if (watsonIncidentRel != null) {
				map.put(primaryKey, watsonIncidentRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonIncidentRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WATSONINCIDENTREL_WHERE_PKS_IN);

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

			for (WatsonIncidentRel watsonIncidentRel : (List<WatsonIncidentRel>)q.list()) {
				map.put(watsonIncidentRel.getPrimaryKeyObj(), watsonIncidentRel);

				cacheResult(watsonIncidentRel);

				uncachedPrimaryKeys.remove(watsonIncidentRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson incident rels.
	 *
	 * @return the watson incident rels
	 */
	@Override
	public List<WatsonIncidentRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson incident rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rels
	 * @param end the upper bound of the range of watson incident rels (not inclusive)
	 * @return the range of watson incident rels
	 */
	@Override
	public List<WatsonIncidentRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson incident rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rels
	 * @param end the upper bound of the range of watson incident rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson incident rels
	 */
	@Override
	public List<WatsonIncidentRel> findAll(int start, int end,
		OrderByComparator<WatsonIncidentRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson incident rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rels
	 * @param end the upper bound of the range of watson incident rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson incident rels
	 */
	@Override
	public List<WatsonIncidentRel> findAll(int start, int end,
		OrderByComparator<WatsonIncidentRel> orderByComparator,
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

		List<WatsonIncidentRel> list = null;

		if (retrieveFromCache) {
			list = (List<WatsonIncidentRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WATSONINCIDENTREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONINCIDENTREL;

				if (pagination) {
					sql = sql.concat(WatsonIncidentRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WatsonIncidentRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WatsonIncidentRel>)QueryUtil.list(q,
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
	 * Removes all the watson incident rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonIncidentRel watsonIncidentRel : findAll()) {
			remove(watsonIncidentRel);
		}
	}

	/**
	 * Returns the number of watson incident rels.
	 *
	 * @return the number of watson incident rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WATSONINCIDENTREL);

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
		return WatsonIncidentRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson incident rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WatsonIncidentRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_WATSONINCIDENTREL = "SELECT watsonIncidentRel FROM WatsonIncidentRel watsonIncidentRel";
	private static final String _SQL_SELECT_WATSONINCIDENTREL_WHERE_PKS_IN = "SELECT watsonIncidentRel FROM WatsonIncidentRel watsonIncidentRel WHERE watsonIncidentRelId IN (";
	private static final String _SQL_COUNT_WATSONINCIDENTREL = "SELECT COUNT(watsonIncidentRel) FROM WatsonIncidentRel watsonIncidentRel";
	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonIncidentRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WatsonIncidentRel exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WatsonIncidentRelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
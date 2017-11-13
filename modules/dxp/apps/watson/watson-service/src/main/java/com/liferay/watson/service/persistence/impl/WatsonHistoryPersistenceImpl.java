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

import com.liferay.watson.exception.NoSuchHistoryException;
import com.liferay.watson.model.WatsonHistory;
import com.liferay.watson.model.impl.WatsonHistoryImpl;
import com.liferay.watson.model.impl.WatsonHistoryModelImpl;
import com.liferay.watson.service.persistence.WatsonHistoryPersistence;

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
 * The persistence implementation for the watson history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonHistoryPersistence
 * @see com.liferay.watson.service.persistence.WatsonHistoryUtil
 * @generated
 */
@ProviderType
public class WatsonHistoryPersistenceImpl extends BasePersistenceImpl<WatsonHistory>
	implements WatsonHistoryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WatsonHistoryUtil} to access the watson history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WatsonHistoryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryModelImpl.FINDER_CACHE_ENABLED,
			WatsonHistoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryModelImpl.FINDER_CACHE_ENABLED,
			WatsonHistoryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WatsonHistoryPersistenceImpl() {
		setModelClass(WatsonHistory.class);

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
	 * Caches the watson history in the entity cache if it is enabled.
	 *
	 * @param watsonHistory the watson history
	 */
	@Override
	public void cacheResult(WatsonHistory watsonHistory) {
		entityCache.putResult(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryImpl.class, watsonHistory.getPrimaryKey(),
			watsonHistory);

		watsonHistory.resetOriginalValues();
	}

	/**
	 * Caches the watson histories in the entity cache if it is enabled.
	 *
	 * @param watsonHistories the watson histories
	 */
	@Override
	public void cacheResult(List<WatsonHistory> watsonHistories) {
		for (WatsonHistory watsonHistory : watsonHistories) {
			if (entityCache.getResult(
						WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
						WatsonHistoryImpl.class, watsonHistory.getPrimaryKey()) == null) {
				cacheResult(watsonHistory);
			}
			else {
				watsonHistory.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson histories.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonHistoryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson history.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonHistory watsonHistory) {
		entityCache.removeResult(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryImpl.class, watsonHistory.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonHistory> watsonHistories) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonHistory watsonHistory : watsonHistories) {
			entityCache.removeResult(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
				WatsonHistoryImpl.class, watsonHistory.getPrimaryKey());
		}
	}

	/**
	 * Creates a new watson history with the primary key. Does not add the watson history to the database.
	 *
	 * @param watsonHistoryId the primary key for the new watson history
	 * @return the new watson history
	 */
	@Override
	public WatsonHistory create(long watsonHistoryId) {
		WatsonHistory watsonHistory = new WatsonHistoryImpl();

		watsonHistory.setNew(true);
		watsonHistory.setPrimaryKey(watsonHistoryId);

		watsonHistory.setCompanyId(companyProvider.getCompanyId());

		return watsonHistory;
	}

	/**
	 * Removes the watson history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonHistoryId the primary key of the watson history
	 * @return the watson history that was removed
	 * @throws NoSuchHistoryException if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory remove(long watsonHistoryId)
		throws NoSuchHistoryException {
		return remove((Serializable)watsonHistoryId);
	}

	/**
	 * Removes the watson history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson history
	 * @return the watson history that was removed
	 * @throws NoSuchHistoryException if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory remove(Serializable primaryKey)
		throws NoSuchHistoryException {
		Session session = null;

		try {
			session = openSession();

			WatsonHistory watsonHistory = (WatsonHistory)session.get(WatsonHistoryImpl.class,
					primaryKey);

			if (watsonHistory == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(watsonHistory);
		}
		catch (NoSuchHistoryException nsee) {
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
	protected WatsonHistory removeImpl(WatsonHistory watsonHistory) {
		watsonHistory = toUnwrappedModel(watsonHistory);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonHistory)) {
				watsonHistory = (WatsonHistory)session.get(WatsonHistoryImpl.class,
						watsonHistory.getPrimaryKeyObj());
			}

			if (watsonHistory != null) {
				session.delete(watsonHistory);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (watsonHistory != null) {
			clearCache(watsonHistory);
		}

		return watsonHistory;
	}

	@Override
	public WatsonHistory updateImpl(WatsonHistory watsonHistory) {
		watsonHistory = toUnwrappedModel(watsonHistory);

		boolean isNew = watsonHistory.isNew();

		WatsonHistoryModelImpl watsonHistoryModelImpl = (WatsonHistoryModelImpl)watsonHistory;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonHistory.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonHistory.setCreateDate(now);
			}
			else {
				watsonHistory.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonHistoryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonHistory.setModifiedDate(now);
			}
			else {
				watsonHistory.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonHistory.isNew()) {
				session.save(watsonHistory);

				watsonHistory.setNew(false);
			}
			else {
				watsonHistory = (WatsonHistory)session.merge(watsonHistory);
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

		entityCache.putResult(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryImpl.class, watsonHistory.getPrimaryKey(),
			watsonHistory, false);

		watsonHistory.resetOriginalValues();

		return watsonHistory;
	}

	protected WatsonHistory toUnwrappedModel(WatsonHistory watsonHistory) {
		if (watsonHistory instanceof WatsonHistoryImpl) {
			return watsonHistory;
		}

		WatsonHistoryImpl watsonHistoryImpl = new WatsonHistoryImpl();

		watsonHistoryImpl.setNew(watsonHistory.isNew());
		watsonHistoryImpl.setPrimaryKey(watsonHistory.getPrimaryKey());

		watsonHistoryImpl.setWatsonHistoryId(watsonHistory.getWatsonHistoryId());
		watsonHistoryImpl.setGroupId(watsonHistory.getGroupId());
		watsonHistoryImpl.setCompanyId(watsonHistory.getCompanyId());
		watsonHistoryImpl.setUserId(watsonHistory.getUserId());
		watsonHistoryImpl.setUserName(watsonHistory.getUserName());
		watsonHistoryImpl.setCreateDate(watsonHistory.getCreateDate());
		watsonHistoryImpl.setModifiedDate(watsonHistory.getModifiedDate());
		watsonHistoryImpl.setWatsonIncidentId(watsonHistory.getWatsonIncidentId());
		watsonHistoryImpl.setClassNameId(watsonHistory.getClassNameId());
		watsonHistoryImpl.setClassPK(watsonHistory.getClassPK());
		watsonHistoryImpl.setType(watsonHistory.getType());
		watsonHistoryImpl.setStatus(watsonHistory.getStatus());

		return watsonHistoryImpl;
	}

	/**
	 * Returns the watson history with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson history
	 * @return the watson history
	 * @throws NoSuchHistoryException if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistoryException {
		WatsonHistory watsonHistory = fetchByPrimaryKey(primaryKey);

		if (watsonHistory == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return watsonHistory;
	}

	/**
	 * Returns the watson history with the primary key or throws a {@link NoSuchHistoryException} if it could not be found.
	 *
	 * @param watsonHistoryId the primary key of the watson history
	 * @return the watson history
	 * @throws NoSuchHistoryException if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory findByPrimaryKey(long watsonHistoryId)
		throws NoSuchHistoryException {
		return findByPrimaryKey((Serializable)watsonHistoryId);
	}

	/**
	 * Returns the watson history with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson history
	 * @return the watson history, or <code>null</code> if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
				WatsonHistoryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonHistory watsonHistory = (WatsonHistory)serializable;

		if (watsonHistory == null) {
			Session session = null;

			try {
				session = openSession();

				watsonHistory = (WatsonHistory)session.get(WatsonHistoryImpl.class,
						primaryKey);

				if (watsonHistory != null) {
					cacheResult(watsonHistory);
				}
				else {
					entityCache.putResult(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
						WatsonHistoryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
					WatsonHistoryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonHistory;
	}

	/**
	 * Returns the watson history with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonHistoryId the primary key of the watson history
	 * @return the watson history, or <code>null</code> if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory fetchByPrimaryKey(long watsonHistoryId) {
		return fetchByPrimaryKey((Serializable)watsonHistoryId);
	}

	@Override
	public Map<Serializable, WatsonHistory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonHistory> map = new HashMap<Serializable, WatsonHistory>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonHistory watsonHistory = fetchByPrimaryKey(primaryKey);

			if (watsonHistory != null) {
				map.put(primaryKey, watsonHistory);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
					WatsonHistoryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonHistory)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WATSONHISTORY_WHERE_PKS_IN);

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

			for (WatsonHistory watsonHistory : (List<WatsonHistory>)q.list()) {
				map.put(watsonHistory.getPrimaryKeyObj(), watsonHistory);

				cacheResult(watsonHistory);

				uncachedPrimaryKeys.remove(watsonHistory.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
					WatsonHistoryImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson histories.
	 *
	 * @return the watson histories
	 */
	@Override
	public List<WatsonHistory> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson histories
	 * @param end the upper bound of the range of watson histories (not inclusive)
	 * @return the range of watson histories
	 */
	@Override
	public List<WatsonHistory> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson histories
	 * @param end the upper bound of the range of watson histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson histories
	 */
	@Override
	public List<WatsonHistory> findAll(int start, int end,
		OrderByComparator<WatsonHistory> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson histories
	 * @param end the upper bound of the range of watson histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson histories
	 */
	@Override
	public List<WatsonHistory> findAll(int start, int end,
		OrderByComparator<WatsonHistory> orderByComparator,
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

		List<WatsonHistory> list = null;

		if (retrieveFromCache) {
			list = (List<WatsonHistory>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WATSONHISTORY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONHISTORY;

				if (pagination) {
					sql = sql.concat(WatsonHistoryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WatsonHistory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WatsonHistory>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the watson histories from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonHistory watsonHistory : findAll()) {
			remove(watsonHistory);
		}
	}

	/**
	 * Returns the number of watson histories.
	 *
	 * @return the number of watson histories
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WATSONHISTORY);

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
		return WatsonHistoryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson history persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WatsonHistoryImpl.class.getName());
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
	private static final String _SQL_SELECT_WATSONHISTORY = "SELECT watsonHistory FROM WatsonHistory watsonHistory";
	private static final String _SQL_SELECT_WATSONHISTORY_WHERE_PKS_IN = "SELECT watsonHistory FROM WatsonHistory watsonHistory WHERE watsonHistoryId IN (";
	private static final String _SQL_COUNT_WATSONHISTORY = "SELECT COUNT(watsonHistory) FROM WatsonHistory watsonHistory";
	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonHistory.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WatsonHistory exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WatsonHistoryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
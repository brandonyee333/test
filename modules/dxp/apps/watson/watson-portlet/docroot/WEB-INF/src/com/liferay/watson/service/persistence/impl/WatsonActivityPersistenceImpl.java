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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.watson.exception.NoSuchActivityException;
import com.liferay.watson.model.WatsonActivity;
import com.liferay.watson.model.impl.WatsonActivityImpl;
import com.liferay.watson.model.impl.WatsonActivityModelImpl;
import com.liferay.watson.service.persistence.WatsonActivityPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the watson activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eddie Olson
 * @see WatsonActivityPersistence
 * @see com.liferay.watson.service.persistence.WatsonActivityUtil
 * @generated
 */
@ProviderType
public class WatsonActivityPersistenceImpl extends BasePersistenceImpl<WatsonActivity>
	implements WatsonActivityPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WatsonActivityUtil} to access the watson activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WatsonActivityImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityModelImpl.FINDER_CACHE_ENABLED,
			WatsonActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityModelImpl.FINDER_CACHE_ENABLED,
			WatsonActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WatsonActivityPersistenceImpl() {
		setModelClass(WatsonActivity.class);
	}

	/**
	 * Caches the watson activity in the entity cache if it is enabled.
	 *
	 * @param watsonActivity the watson activity
	 */
	@Override
	public void cacheResult(WatsonActivity watsonActivity) {
		entityCache.putResult(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityImpl.class, watsonActivity.getPrimaryKey(),
			watsonActivity);

		watsonActivity.resetOriginalValues();
	}

	/**
	 * Caches the watson activities in the entity cache if it is enabled.
	 *
	 * @param watsonActivities the watson activities
	 */
	@Override
	public void cacheResult(List<WatsonActivity> watsonActivities) {
		for (WatsonActivity watsonActivity : watsonActivities) {
			if (entityCache.getResult(
						WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
						WatsonActivityImpl.class, watsonActivity.getPrimaryKey()) == null) {
				cacheResult(watsonActivity);
			}
			else {
				watsonActivity.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson activities.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonActivityImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson activity.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonActivity watsonActivity) {
		entityCache.removeResult(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityImpl.class, watsonActivity.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonActivity> watsonActivities) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonActivity watsonActivity : watsonActivities) {
			entityCache.removeResult(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
				WatsonActivityImpl.class, watsonActivity.getPrimaryKey());
		}
	}

	/**
	 * Creates a new watson activity with the primary key. Does not add the watson activity to the database.
	 *
	 * @param watsonActivityId the primary key for the new watson activity
	 * @return the new watson activity
	 */
	@Override
	public WatsonActivity create(long watsonActivityId) {
		WatsonActivity watsonActivity = new WatsonActivityImpl();

		watsonActivity.setNew(true);
		watsonActivity.setPrimaryKey(watsonActivityId);

		watsonActivity.setCompanyId(companyProvider.getCompanyId());

		return watsonActivity;
	}

	/**
	 * Removes the watson activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonActivityId the primary key of the watson activity
	 * @return the watson activity that was removed
	 * @throws NoSuchActivityException if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity remove(long watsonActivityId)
		throws NoSuchActivityException {
		return remove((Serializable)watsonActivityId);
	}

	/**
	 * Removes the watson activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson activity
	 * @return the watson activity that was removed
	 * @throws NoSuchActivityException if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity remove(Serializable primaryKey)
		throws NoSuchActivityException {
		Session session = null;

		try {
			session = openSession();

			WatsonActivity watsonActivity = (WatsonActivity)session.get(WatsonActivityImpl.class,
					primaryKey);

			if (watsonActivity == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActivityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(watsonActivity);
		}
		catch (NoSuchActivityException nsee) {
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
	protected WatsonActivity removeImpl(WatsonActivity watsonActivity) {
		watsonActivity = toUnwrappedModel(watsonActivity);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonActivity)) {
				watsonActivity = (WatsonActivity)session.get(WatsonActivityImpl.class,
						watsonActivity.getPrimaryKeyObj());
			}

			if (watsonActivity != null) {
				session.delete(watsonActivity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (watsonActivity != null) {
			clearCache(watsonActivity);
		}

		return watsonActivity;
	}

	@Override
	public WatsonActivity updateImpl(WatsonActivity watsonActivity) {
		watsonActivity = toUnwrappedModel(watsonActivity);

		boolean isNew = watsonActivity.isNew();

		WatsonActivityModelImpl watsonActivityModelImpl = (WatsonActivityModelImpl)watsonActivity;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonActivity.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonActivity.setCreateDate(now);
			}
			else {
				watsonActivity.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonActivityModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonActivity.setModifiedDate(now);
			}
			else {
				watsonActivity.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonActivity.isNew()) {
				session.save(watsonActivity);

				watsonActivity.setNew(false);
			}
			else {
				watsonActivity = (WatsonActivity)session.merge(watsonActivity);
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

		entityCache.putResult(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityImpl.class, watsonActivity.getPrimaryKey(),
			watsonActivity, false);

		watsonActivity.resetOriginalValues();

		return watsonActivity;
	}

	protected WatsonActivity toUnwrappedModel(WatsonActivity watsonActivity) {
		if (watsonActivity instanceof WatsonActivityImpl) {
			return watsonActivity;
		}

		WatsonActivityImpl watsonActivityImpl = new WatsonActivityImpl();

		watsonActivityImpl.setNew(watsonActivity.isNew());
		watsonActivityImpl.setPrimaryKey(watsonActivity.getPrimaryKey());

		watsonActivityImpl.setWatsonActivityId(watsonActivity.getWatsonActivityId());
		watsonActivityImpl.setCompanyId(watsonActivity.getCompanyId());
		watsonActivityImpl.setUserId(watsonActivity.getUserId());
		watsonActivityImpl.setUserName(watsonActivity.getUserName());
		watsonActivityImpl.setCreateDate(watsonActivity.getCreateDate());
		watsonActivityImpl.setModifiedDate(watsonActivity.getModifiedDate());
		watsonActivityImpl.setTypeWatsonListTypeId(watsonActivity.getTypeWatsonListTypeId());
		watsonActivityImpl.setSubtypeWatsonListTypeId(watsonActivity.getSubtypeWatsonListTypeId());
		watsonActivityImpl.setWatsonIncidentId(watsonActivity.getWatsonIncidentId());
		watsonActivityImpl.setNarrative(watsonActivity.getNarrative());
		watsonActivityImpl.setReportDate(watsonActivity.getReportDate());
		watsonActivityImpl.setStartDate(watsonActivity.getStartDate());
		watsonActivityImpl.setStatus(watsonActivity.getStatus());

		return watsonActivityImpl;
	}

	/**
	 * Returns the watson activity with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson activity
	 * @return the watson activity
	 * @throws NoSuchActivityException if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActivityException {
		WatsonActivity watsonActivity = fetchByPrimaryKey(primaryKey);

		if (watsonActivity == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActivityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return watsonActivity;
	}

	/**
	 * Returns the watson activity with the primary key or throws a {@link NoSuchActivityException} if it could not be found.
	 *
	 * @param watsonActivityId the primary key of the watson activity
	 * @return the watson activity
	 * @throws NoSuchActivityException if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity findByPrimaryKey(long watsonActivityId)
		throws NoSuchActivityException {
		return findByPrimaryKey((Serializable)watsonActivityId);
	}

	/**
	 * Returns the watson activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson activity
	 * @return the watson activity, or <code>null</code> if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
				WatsonActivityImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonActivity watsonActivity = (WatsonActivity)serializable;

		if (watsonActivity == null) {
			Session session = null;

			try {
				session = openSession();

				watsonActivity = (WatsonActivity)session.get(WatsonActivityImpl.class,
						primaryKey);

				if (watsonActivity != null) {
					cacheResult(watsonActivity);
				}
				else {
					entityCache.putResult(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
						WatsonActivityImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
					WatsonActivityImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonActivity;
	}

	/**
	 * Returns the watson activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonActivityId the primary key of the watson activity
	 * @return the watson activity, or <code>null</code> if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity fetchByPrimaryKey(long watsonActivityId) {
		return fetchByPrimaryKey((Serializable)watsonActivityId);
	}

	@Override
	public Map<Serializable, WatsonActivity> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonActivity> map = new HashMap<Serializable, WatsonActivity>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonActivity watsonActivity = fetchByPrimaryKey(primaryKey);

			if (watsonActivity != null) {
				map.put(primaryKey, watsonActivity);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
					WatsonActivityImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonActivity)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WATSONACTIVITY_WHERE_PKS_IN);

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

			for (WatsonActivity watsonActivity : (List<WatsonActivity>)q.list()) {
				map.put(watsonActivity.getPrimaryKeyObj(), watsonActivity);

				cacheResult(watsonActivity);

				uncachedPrimaryKeys.remove(watsonActivity.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
					WatsonActivityImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson activities.
	 *
	 * @return the watson activities
	 */
	@Override
	public List<WatsonActivity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson activities
	 * @param end the upper bound of the range of watson activities (not inclusive)
	 * @return the range of watson activities
	 */
	@Override
	public List<WatsonActivity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson activities
	 * @param end the upper bound of the range of watson activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson activities
	 */
	@Override
	public List<WatsonActivity> findAll(int start, int end,
		OrderByComparator<WatsonActivity> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson activities
	 * @param end the upper bound of the range of watson activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson activities
	 */
	@Override
	public List<WatsonActivity> findAll(int start, int end,
		OrderByComparator<WatsonActivity> orderByComparator,
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

		List<WatsonActivity> list = null;

		if (retrieveFromCache) {
			list = (List<WatsonActivity>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WATSONACTIVITY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONACTIVITY;

				if (pagination) {
					sql = sql.concat(WatsonActivityModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WatsonActivity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WatsonActivity>)QueryUtil.list(q,
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
	 * Removes all the watson activities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonActivity watsonActivity : findAll()) {
			remove(watsonActivity);
		}
	}

	/**
	 * Returns the number of watson activities.
	 *
	 * @return the number of watson activities
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WATSONACTIVITY);

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
		return WatsonActivityModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson activity persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WatsonActivityImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_WATSONACTIVITY = "SELECT watsonActivity FROM WatsonActivity watsonActivity";
	private static final String _SQL_SELECT_WATSONACTIVITY_WHERE_PKS_IN = "SELECT watsonActivity FROM WatsonActivity watsonActivity WHERE watsonActivityId IN (";
	private static final String _SQL_COUNT_WATSONACTIVITY = "SELECT COUNT(watsonActivity) FROM WatsonActivity watsonActivity";
	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonActivity.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WatsonActivity exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WatsonActivityPersistenceImpl.class);
}
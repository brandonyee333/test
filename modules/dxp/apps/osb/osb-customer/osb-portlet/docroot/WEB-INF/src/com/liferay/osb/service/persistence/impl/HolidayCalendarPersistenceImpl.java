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

import com.liferay.osb.exception.NoSuchHolidayCalendarException;
import com.liferay.osb.model.HolidayCalendar;
import com.liferay.osb.model.impl.HolidayCalendarImpl;
import com.liferay.osb.model.impl.HolidayCalendarModelImpl;
import com.liferay.osb.service.persistence.HolidayCalendarPersistence;

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
 * The persistence implementation for the holiday calendar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarPersistence
 * @see com.liferay.osb.service.persistence.HolidayCalendarUtil
 * @generated
 */
@ProviderType
public class HolidayCalendarPersistenceImpl extends BasePersistenceImpl<HolidayCalendar>
	implements HolidayCalendarPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HolidayCalendarUtil} to access the holiday calendar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HolidayCalendarImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HolidayCalendarPersistenceImpl() {
		setModelClass(HolidayCalendar.class);
	}

	/**
	 * Caches the holiday calendar in the entity cache if it is enabled.
	 *
	 * @param holidayCalendar the holiday calendar
	 */
	@Override
	public void cacheResult(HolidayCalendar holidayCalendar) {
		entityCache.putResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarImpl.class, holidayCalendar.getPrimaryKey(),
			holidayCalendar);

		holidayCalendar.resetOriginalValues();
	}

	/**
	 * Caches the holiday calendars in the entity cache if it is enabled.
	 *
	 * @param holidayCalendars the holiday calendars
	 */
	@Override
	public void cacheResult(List<HolidayCalendar> holidayCalendars) {
		for (HolidayCalendar holidayCalendar : holidayCalendars) {
			if (entityCache.getResult(
						HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
						HolidayCalendarImpl.class,
						holidayCalendar.getPrimaryKey()) == null) {
				cacheResult(holidayCalendar);
			}
			else {
				holidayCalendar.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all holiday calendars.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HolidayCalendarImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the holiday calendar.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HolidayCalendar holidayCalendar) {
		entityCache.removeResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarImpl.class, holidayCalendar.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<HolidayCalendar> holidayCalendars) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HolidayCalendar holidayCalendar : holidayCalendars) {
			entityCache.removeResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
				HolidayCalendarImpl.class, holidayCalendar.getPrimaryKey());
		}
	}

	/**
	 * Creates a new holiday calendar with the primary key. Does not add the holiday calendar to the database.
	 *
	 * @param holidayCalendarId the primary key for the new holiday calendar
	 * @return the new holiday calendar
	 */
	@Override
	public HolidayCalendar create(long holidayCalendarId) {
		HolidayCalendar holidayCalendar = new HolidayCalendarImpl();

		holidayCalendar.setNew(true);
		holidayCalendar.setPrimaryKey(holidayCalendarId);

		return holidayCalendar;
	}

	/**
	 * Removes the holiday calendar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param holidayCalendarId the primary key of the holiday calendar
	 * @return the holiday calendar that was removed
	 * @throws NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	 */
	@Override
	public HolidayCalendar remove(long holidayCalendarId)
		throws NoSuchHolidayCalendarException {
		return remove((Serializable)holidayCalendarId);
	}

	/**
	 * Removes the holiday calendar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the holiday calendar
	 * @return the holiday calendar that was removed
	 * @throws NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	 */
	@Override
	public HolidayCalendar remove(Serializable primaryKey)
		throws NoSuchHolidayCalendarException {
		Session session = null;

		try {
			session = openSession();

			HolidayCalendar holidayCalendar = (HolidayCalendar)session.get(HolidayCalendarImpl.class,
					primaryKey);

			if (holidayCalendar == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHolidayCalendarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(holidayCalendar);
		}
		catch (NoSuchHolidayCalendarException nsee) {
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
	protected HolidayCalendar removeImpl(HolidayCalendar holidayCalendar) {
		holidayCalendar = toUnwrappedModel(holidayCalendar);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(holidayCalendar)) {
				holidayCalendar = (HolidayCalendar)session.get(HolidayCalendarImpl.class,
						holidayCalendar.getPrimaryKeyObj());
			}

			if (holidayCalendar != null) {
				session.delete(holidayCalendar);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (holidayCalendar != null) {
			clearCache(holidayCalendar);
		}

		return holidayCalendar;
	}

	@Override
	public HolidayCalendar updateImpl(HolidayCalendar holidayCalendar) {
		holidayCalendar = toUnwrappedModel(holidayCalendar);

		boolean isNew = holidayCalendar.isNew();

		Session session = null;

		try {
			session = openSession();

			if (holidayCalendar.isNew()) {
				session.save(holidayCalendar);

				holidayCalendar.setNew(false);
			}
			else {
				holidayCalendar = (HolidayCalendar)session.merge(holidayCalendar);
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

		entityCache.putResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarImpl.class, holidayCalendar.getPrimaryKey(),
			holidayCalendar, false);

		holidayCalendar.resetOriginalValues();

		return holidayCalendar;
	}

	protected HolidayCalendar toUnwrappedModel(HolidayCalendar holidayCalendar) {
		if (holidayCalendar instanceof HolidayCalendarImpl) {
			return holidayCalendar;
		}

		HolidayCalendarImpl holidayCalendarImpl = new HolidayCalendarImpl();

		holidayCalendarImpl.setNew(holidayCalendar.isNew());
		holidayCalendarImpl.setPrimaryKey(holidayCalendar.getPrimaryKey());

		holidayCalendarImpl.setHolidayCalendarId(holidayCalendar.getHolidayCalendarId());
		holidayCalendarImpl.setName(holidayCalendar.getName());
		holidayCalendarImpl.setDescription(holidayCalendar.getDescription());

		return holidayCalendarImpl;
	}

	/**
	 * Returns the holiday calendar with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday calendar
	 * @return the holiday calendar
	 * @throws NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	 */
	@Override
	public HolidayCalendar findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHolidayCalendarException {
		HolidayCalendar holidayCalendar = fetchByPrimaryKey(primaryKey);

		if (holidayCalendar == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHolidayCalendarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return holidayCalendar;
	}

	/**
	 * Returns the holiday calendar with the primary key or throws a {@link NoSuchHolidayCalendarException} if it could not be found.
	 *
	 * @param holidayCalendarId the primary key of the holiday calendar
	 * @return the holiday calendar
	 * @throws NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	 */
	@Override
	public HolidayCalendar findByPrimaryKey(long holidayCalendarId)
		throws NoSuchHolidayCalendarException {
		return findByPrimaryKey((Serializable)holidayCalendarId);
	}

	/**
	 * Returns the holiday calendar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday calendar
	 * @return the holiday calendar, or <code>null</code> if a holiday calendar with the primary key could not be found
	 */
	@Override
	public HolidayCalendar fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
				HolidayCalendarImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HolidayCalendar holidayCalendar = (HolidayCalendar)serializable;

		if (holidayCalendar == null) {
			Session session = null;

			try {
				session = openSession();

				holidayCalendar = (HolidayCalendar)session.get(HolidayCalendarImpl.class,
						primaryKey);

				if (holidayCalendar != null) {
					cacheResult(holidayCalendar);
				}
				else {
					entityCache.putResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
						HolidayCalendarImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
					HolidayCalendarImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return holidayCalendar;
	}

	/**
	 * Returns the holiday calendar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param holidayCalendarId the primary key of the holiday calendar
	 * @return the holiday calendar, or <code>null</code> if a holiday calendar with the primary key could not be found
	 */
	@Override
	public HolidayCalendar fetchByPrimaryKey(long holidayCalendarId) {
		return fetchByPrimaryKey((Serializable)holidayCalendarId);
	}

	@Override
	public Map<Serializable, HolidayCalendar> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HolidayCalendar> map = new HashMap<Serializable, HolidayCalendar>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			HolidayCalendar holidayCalendar = fetchByPrimaryKey(primaryKey);

			if (holidayCalendar != null) {
				map.put(primaryKey, holidayCalendar);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
					HolidayCalendarImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (HolidayCalendar)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_HOLIDAYCALENDAR_WHERE_PKS_IN);

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

			for (HolidayCalendar holidayCalendar : (List<HolidayCalendar>)q.list()) {
				map.put(holidayCalendar.getPrimaryKeyObj(), holidayCalendar);

				cacheResult(holidayCalendar);

				uncachedPrimaryKeys.remove(holidayCalendar.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
					HolidayCalendarImpl.class, primaryKey, nullModel);
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
	 * Returns all the holiday calendars.
	 *
	 * @return the holiday calendars
	 */
	@Override
	public List<HolidayCalendar> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the holiday calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday calendars
	 * @param end the upper bound of the range of holiday calendars (not inclusive)
	 * @return the range of holiday calendars
	 */
	@Override
	public List<HolidayCalendar> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the holiday calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday calendars
	 * @param end the upper bound of the range of holiday calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of holiday calendars
	 */
	@Override
	public List<HolidayCalendar> findAll(int start, int end,
		OrderByComparator<HolidayCalendar> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the holiday calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday calendars
	 * @param end the upper bound of the range of holiday calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of holiday calendars
	 */
	@Override
	public List<HolidayCalendar> findAll(int start, int end,
		OrderByComparator<HolidayCalendar> orderByComparator,
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

		List<HolidayCalendar> list = null;

		if (retrieveFromCache) {
			list = (List<HolidayCalendar>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HOLIDAYCALENDAR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HOLIDAYCALENDAR;

				if (pagination) {
					sql = sql.concat(HolidayCalendarModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HolidayCalendar>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HolidayCalendar>)QueryUtil.list(q,
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
	 * Removes all the holiday calendars from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HolidayCalendar holidayCalendar : findAll()) {
			remove(holidayCalendar);
		}
	}

	/**
	 * Returns the number of holiday calendars.
	 *
	 * @return the number of holiday calendars
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HOLIDAYCALENDAR);

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
		return HolidayCalendarModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the holiday calendar persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HolidayCalendarImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_HOLIDAYCALENDAR = "SELECT holidayCalendar FROM HolidayCalendar holidayCalendar";
	private static final String _SQL_SELECT_HOLIDAYCALENDAR_WHERE_PKS_IN = "SELECT holidayCalendar FROM HolidayCalendar holidayCalendar WHERE holidayCalendarId IN (";
	private static final String _SQL_COUNT_HOLIDAYCALENDAR = "SELECT COUNT(holidayCalendar) FROM HolidayCalendar holidayCalendar";
	private static final String _ORDER_BY_ENTITY_ALIAS = "holidayCalendar.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HolidayCalendar exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HolidayCalendarPersistenceImpl.class);
}
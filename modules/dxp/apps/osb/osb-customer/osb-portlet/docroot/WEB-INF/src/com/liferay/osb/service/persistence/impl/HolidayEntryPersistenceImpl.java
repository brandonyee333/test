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

import com.liferay.osb.exception.NoSuchHolidayEntryException;
import com.liferay.osb.model.HolidayEntry;
import com.liferay.osb.model.impl.HolidayEntryImpl;
import com.liferay.osb.model.impl.HolidayEntryModelImpl;
import com.liferay.osb.service.persistence.HolidayEntryPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
 * The persistence implementation for the holiday entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayEntryPersistence
 * @see com.liferay.osb.service.persistence.HolidayEntryUtil
 * @generated
 */
@ProviderType
public class HolidayEntryPersistenceImpl extends BasePersistenceImpl<HolidayEntry>
	implements HolidayEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HolidayEntryUtil} to access the holiday entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HolidayEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
			HolidayEntryModelImpl.FINDER_CACHE_ENABLED, HolidayEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
			HolidayEntryModelImpl.FINDER_CACHE_ENABLED, HolidayEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
			HolidayEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_HOLIDAYCALENDAR =
		new FinderPath(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
			HolidayEntryModelImpl.FINDER_CACHE_ENABLED, HolidayEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByHolidayCalendar",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDAR =
		new FinderPath(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
			HolidayEntryModelImpl.FINDER_CACHE_ENABLED, HolidayEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByHolidayCalendar",
			new String[] { Long.class.getName() },
			HolidayEntryModelImpl.HOLIDAYCALENDARID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_HOLIDAYCALENDAR = new FinderPath(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
			HolidayEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByHolidayCalendar", new String[] { Long.class.getName() });

	/**
	 * Returns all the holiday entries where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @return the matching holiday entries
	 */
	@Override
	public List<HolidayEntry> findByHolidayCalendar(long holidayCalendarId) {
		return findByHolidayCalendar(holidayCalendarId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the holiday entries where holidayCalendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param start the lower bound of the range of holiday entries
	 * @param end the upper bound of the range of holiday entries (not inclusive)
	 * @return the range of matching holiday entries
	 */
	@Override
	public List<HolidayEntry> findByHolidayCalendar(long holidayCalendarId,
		int start, int end) {
		return findByHolidayCalendar(holidayCalendarId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the holiday entries where holidayCalendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param start the lower bound of the range of holiday entries
	 * @param end the upper bound of the range of holiday entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching holiday entries
	 */
	@Override
	public List<HolidayEntry> findByHolidayCalendar(long holidayCalendarId,
		int start, int end, OrderByComparator<HolidayEntry> orderByComparator) {
		return findByHolidayCalendar(holidayCalendarId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the holiday entries where holidayCalendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param start the lower bound of the range of holiday entries
	 * @param end the upper bound of the range of holiday entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching holiday entries
	 */
	@Override
	public List<HolidayEntry> findByHolidayCalendar(long holidayCalendarId,
		int start, int end, OrderByComparator<HolidayEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDAR;
			finderArgs = new Object[] { holidayCalendarId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_HOLIDAYCALENDAR;
			finderArgs = new Object[] {
					holidayCalendarId,
					
					start, end, orderByComparator
				};
		}

		List<HolidayEntry> list = null;

		if (retrieveFromCache) {
			list = (List<HolidayEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HolidayEntry holidayEntry : list) {
					if ((holidayCalendarId != holidayEntry.getHolidayCalendarId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_HOLIDAYENTRY_WHERE);

			query.append(_FINDER_COLUMN_HOLIDAYCALENDAR_HOLIDAYCALENDARID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(HolidayEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(holidayCalendarId);

				if (!pagination) {
					list = (List<HolidayEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HolidayEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first holiday entry in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday entry
	 * @throws NoSuchHolidayEntryException if a matching holiday entry could not be found
	 */
	@Override
	public HolidayEntry findByHolidayCalendar_First(long holidayCalendarId,
		OrderByComparator<HolidayEntry> orderByComparator)
		throws NoSuchHolidayEntryException {
		HolidayEntry holidayEntry = fetchByHolidayCalendar_First(holidayCalendarId,
				orderByComparator);

		if (holidayEntry != null) {
			return holidayEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("holidayCalendarId=");
		msg.append(holidayCalendarId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHolidayEntryException(msg.toString());
	}

	/**
	 * Returns the first holiday entry in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday entry, or <code>null</code> if a matching holiday entry could not be found
	 */
	@Override
	public HolidayEntry fetchByHolidayCalendar_First(long holidayCalendarId,
		OrderByComparator<HolidayEntry> orderByComparator) {
		List<HolidayEntry> list = findByHolidayCalendar(holidayCalendarId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last holiday entry in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday entry
	 * @throws NoSuchHolidayEntryException if a matching holiday entry could not be found
	 */
	@Override
	public HolidayEntry findByHolidayCalendar_Last(long holidayCalendarId,
		OrderByComparator<HolidayEntry> orderByComparator)
		throws NoSuchHolidayEntryException {
		HolidayEntry holidayEntry = fetchByHolidayCalendar_Last(holidayCalendarId,
				orderByComparator);

		if (holidayEntry != null) {
			return holidayEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("holidayCalendarId=");
		msg.append(holidayCalendarId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHolidayEntryException(msg.toString());
	}

	/**
	 * Returns the last holiday entry in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday entry, or <code>null</code> if a matching holiday entry could not be found
	 */
	@Override
	public HolidayEntry fetchByHolidayCalendar_Last(long holidayCalendarId,
		OrderByComparator<HolidayEntry> orderByComparator) {
		int count = countByHolidayCalendar(holidayCalendarId);

		if (count == 0) {
			return null;
		}

		List<HolidayEntry> list = findByHolidayCalendar(holidayCalendarId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the holiday entries before and after the current holiday entry in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayEntryId the primary key of the current holiday entry
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next holiday entry
	 * @throws NoSuchHolidayEntryException if a holiday entry with the primary key could not be found
	 */
	@Override
	public HolidayEntry[] findByHolidayCalendar_PrevAndNext(
		long holidayEntryId, long holidayCalendarId,
		OrderByComparator<HolidayEntry> orderByComparator)
		throws NoSuchHolidayEntryException {
		HolidayEntry holidayEntry = findByPrimaryKey(holidayEntryId);

		Session session = null;

		try {
			session = openSession();

			HolidayEntry[] array = new HolidayEntryImpl[3];

			array[0] = getByHolidayCalendar_PrevAndNext(session, holidayEntry,
					holidayCalendarId, orderByComparator, true);

			array[1] = holidayEntry;

			array[2] = getByHolidayCalendar_PrevAndNext(session, holidayEntry,
					holidayCalendarId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected HolidayEntry getByHolidayCalendar_PrevAndNext(Session session,
		HolidayEntry holidayEntry, long holidayCalendarId,
		OrderByComparator<HolidayEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_HOLIDAYENTRY_WHERE);

		query.append(_FINDER_COLUMN_HOLIDAYCALENDAR_HOLIDAYCALENDARID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(HolidayEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(holidayCalendarId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(holidayEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HolidayEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the holiday entries where holidayCalendarId = &#63; from the database.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 */
	@Override
	public void removeByHolidayCalendar(long holidayCalendarId) {
		for (HolidayEntry holidayEntry : findByHolidayCalendar(
				holidayCalendarId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(holidayEntry);
		}
	}

	/**
	 * Returns the number of holiday entries where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @return the number of matching holiday entries
	 */
	@Override
	public int countByHolidayCalendar(long holidayCalendarId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_HOLIDAYCALENDAR;

		Object[] finderArgs = new Object[] { holidayCalendarId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HOLIDAYENTRY_WHERE);

			query.append(_FINDER_COLUMN_HOLIDAYCALENDAR_HOLIDAYCALENDARID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(holidayCalendarId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_HOLIDAYCALENDAR_HOLIDAYCALENDARID_2 =
		"holidayEntry.holidayCalendarId = ?";

	public HolidayEntryPersistenceImpl() {
		setModelClass(HolidayEntry.class);
	}

	/**
	 * Caches the holiday entry in the entity cache if it is enabled.
	 *
	 * @param holidayEntry the holiday entry
	 */
	@Override
	public void cacheResult(HolidayEntry holidayEntry) {
		entityCache.putResult(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
			HolidayEntryImpl.class, holidayEntry.getPrimaryKey(), holidayEntry);

		holidayEntry.resetOriginalValues();
	}

	/**
	 * Caches the holiday entries in the entity cache if it is enabled.
	 *
	 * @param holidayEntries the holiday entries
	 */
	@Override
	public void cacheResult(List<HolidayEntry> holidayEntries) {
		for (HolidayEntry holidayEntry : holidayEntries) {
			if (entityCache.getResult(
						HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
						HolidayEntryImpl.class, holidayEntry.getPrimaryKey()) == null) {
				cacheResult(holidayEntry);
			}
			else {
				holidayEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all holiday entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HolidayEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the holiday entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HolidayEntry holidayEntry) {
		entityCache.removeResult(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
			HolidayEntryImpl.class, holidayEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<HolidayEntry> holidayEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HolidayEntry holidayEntry : holidayEntries) {
			entityCache.removeResult(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
				HolidayEntryImpl.class, holidayEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new holiday entry with the primary key. Does not add the holiday entry to the database.
	 *
	 * @param holidayEntryId the primary key for the new holiday entry
	 * @return the new holiday entry
	 */
	@Override
	public HolidayEntry create(long holidayEntryId) {
		HolidayEntry holidayEntry = new HolidayEntryImpl();

		holidayEntry.setNew(true);
		holidayEntry.setPrimaryKey(holidayEntryId);

		return holidayEntry;
	}

	/**
	 * Removes the holiday entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param holidayEntryId the primary key of the holiday entry
	 * @return the holiday entry that was removed
	 * @throws NoSuchHolidayEntryException if a holiday entry with the primary key could not be found
	 */
	@Override
	public HolidayEntry remove(long holidayEntryId)
		throws NoSuchHolidayEntryException {
		return remove((Serializable)holidayEntryId);
	}

	/**
	 * Removes the holiday entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the holiday entry
	 * @return the holiday entry that was removed
	 * @throws NoSuchHolidayEntryException if a holiday entry with the primary key could not be found
	 */
	@Override
	public HolidayEntry remove(Serializable primaryKey)
		throws NoSuchHolidayEntryException {
		Session session = null;

		try {
			session = openSession();

			HolidayEntry holidayEntry = (HolidayEntry)session.get(HolidayEntryImpl.class,
					primaryKey);

			if (holidayEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHolidayEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(holidayEntry);
		}
		catch (NoSuchHolidayEntryException nsee) {
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
	protected HolidayEntry removeImpl(HolidayEntry holidayEntry) {
		holidayEntry = toUnwrappedModel(holidayEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(holidayEntry)) {
				holidayEntry = (HolidayEntry)session.get(HolidayEntryImpl.class,
						holidayEntry.getPrimaryKeyObj());
			}

			if (holidayEntry != null) {
				session.delete(holidayEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (holidayEntry != null) {
			clearCache(holidayEntry);
		}

		return holidayEntry;
	}

	@Override
	public HolidayEntry updateImpl(HolidayEntry holidayEntry) {
		holidayEntry = toUnwrappedModel(holidayEntry);

		boolean isNew = holidayEntry.isNew();

		HolidayEntryModelImpl holidayEntryModelImpl = (HolidayEntryModelImpl)holidayEntry;

		Session session = null;

		try {
			session = openSession();

			if (holidayEntry.isNew()) {
				session.save(holidayEntry);

				holidayEntry.setNew(false);
			}
			else {
				holidayEntry = (HolidayEntry)session.merge(holidayEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!HolidayEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					holidayEntryModelImpl.getHolidayCalendarId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_HOLIDAYCALENDAR, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDAR,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((holidayEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDAR.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						holidayEntryModelImpl.getOriginalHolidayCalendarId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_HOLIDAYCALENDAR,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDAR,
					args);

				args = new Object[] { holidayEntryModelImpl.getHolidayCalendarId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_HOLIDAYCALENDAR,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDAR,
					args);
			}
		}

		entityCache.putResult(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
			HolidayEntryImpl.class, holidayEntry.getPrimaryKey(), holidayEntry,
			false);

		holidayEntry.resetOriginalValues();

		return holidayEntry;
	}

	protected HolidayEntry toUnwrappedModel(HolidayEntry holidayEntry) {
		if (holidayEntry instanceof HolidayEntryImpl) {
			return holidayEntry;
		}

		HolidayEntryImpl holidayEntryImpl = new HolidayEntryImpl();

		holidayEntryImpl.setNew(holidayEntry.isNew());
		holidayEntryImpl.setPrimaryKey(holidayEntry.getPrimaryKey());

		holidayEntryImpl.setHolidayEntryId(holidayEntry.getHolidayEntryId());
		holidayEntryImpl.setHolidayCalendarId(holidayEntry.getHolidayCalendarId());
		holidayEntryImpl.setName(holidayEntry.getName());
		holidayEntryImpl.setDescription(holidayEntry.getDescription());
		holidayEntryImpl.setStartDate(holidayEntry.getStartDate());
		holidayEntryImpl.setEndDate(holidayEntry.getEndDate());
		holidayEntryImpl.setRepeatYearly(holidayEntry.isRepeatYearly());

		return holidayEntryImpl;
	}

	/**
	 * Returns the holiday entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday entry
	 * @return the holiday entry
	 * @throws NoSuchHolidayEntryException if a holiday entry with the primary key could not be found
	 */
	@Override
	public HolidayEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHolidayEntryException {
		HolidayEntry holidayEntry = fetchByPrimaryKey(primaryKey);

		if (holidayEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHolidayEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return holidayEntry;
	}

	/**
	 * Returns the holiday entry with the primary key or throws a {@link NoSuchHolidayEntryException} if it could not be found.
	 *
	 * @param holidayEntryId the primary key of the holiday entry
	 * @return the holiday entry
	 * @throws NoSuchHolidayEntryException if a holiday entry with the primary key could not be found
	 */
	@Override
	public HolidayEntry findByPrimaryKey(long holidayEntryId)
		throws NoSuchHolidayEntryException {
		return findByPrimaryKey((Serializable)holidayEntryId);
	}

	/**
	 * Returns the holiday entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday entry
	 * @return the holiday entry, or <code>null</code> if a holiday entry with the primary key could not be found
	 */
	@Override
	public HolidayEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
				HolidayEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HolidayEntry holidayEntry = (HolidayEntry)serializable;

		if (holidayEntry == null) {
			Session session = null;

			try {
				session = openSession();

				holidayEntry = (HolidayEntry)session.get(HolidayEntryImpl.class,
						primaryKey);

				if (holidayEntry != null) {
					cacheResult(holidayEntry);
				}
				else {
					entityCache.putResult(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
						HolidayEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
					HolidayEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return holidayEntry;
	}

	/**
	 * Returns the holiday entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param holidayEntryId the primary key of the holiday entry
	 * @return the holiday entry, or <code>null</code> if a holiday entry with the primary key could not be found
	 */
	@Override
	public HolidayEntry fetchByPrimaryKey(long holidayEntryId) {
		return fetchByPrimaryKey((Serializable)holidayEntryId);
	}

	@Override
	public Map<Serializable, HolidayEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HolidayEntry> map = new HashMap<Serializable, HolidayEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			HolidayEntry holidayEntry = fetchByPrimaryKey(primaryKey);

			if (holidayEntry != null) {
				map.put(primaryKey, holidayEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
					HolidayEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (HolidayEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_HOLIDAYENTRY_WHERE_PKS_IN);

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

			for (HolidayEntry holidayEntry : (List<HolidayEntry>)q.list()) {
				map.put(holidayEntry.getPrimaryKeyObj(), holidayEntry);

				cacheResult(holidayEntry);

				uncachedPrimaryKeys.remove(holidayEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(HolidayEntryModelImpl.ENTITY_CACHE_ENABLED,
					HolidayEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the holiday entries.
	 *
	 * @return the holiday entries
	 */
	@Override
	public List<HolidayEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the holiday entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday entries
	 * @param end the upper bound of the range of holiday entries (not inclusive)
	 * @return the range of holiday entries
	 */
	@Override
	public List<HolidayEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the holiday entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday entries
	 * @param end the upper bound of the range of holiday entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of holiday entries
	 */
	@Override
	public List<HolidayEntry> findAll(int start, int end,
		OrderByComparator<HolidayEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the holiday entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday entries
	 * @param end the upper bound of the range of holiday entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of holiday entries
	 */
	@Override
	public List<HolidayEntry> findAll(int start, int end,
		OrderByComparator<HolidayEntry> orderByComparator,
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

		List<HolidayEntry> list = null;

		if (retrieveFromCache) {
			list = (List<HolidayEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HOLIDAYENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HOLIDAYENTRY;

				if (pagination) {
					sql = sql.concat(HolidayEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HolidayEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HolidayEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the holiday entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HolidayEntry holidayEntry : findAll()) {
			remove(holidayEntry);
		}
	}

	/**
	 * Returns the number of holiday entries.
	 *
	 * @return the number of holiday entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HOLIDAYENTRY);

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
		return HolidayEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the holiday entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HolidayEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_HOLIDAYENTRY = "SELECT holidayEntry FROM HolidayEntry holidayEntry";
	private static final String _SQL_SELECT_HOLIDAYENTRY_WHERE_PKS_IN = "SELECT holidayEntry FROM HolidayEntry holidayEntry WHERE holidayEntryId IN (";
	private static final String _SQL_SELECT_HOLIDAYENTRY_WHERE = "SELECT holidayEntry FROM HolidayEntry holidayEntry WHERE ";
	private static final String _SQL_COUNT_HOLIDAYENTRY = "SELECT COUNT(holidayEntry) FROM HolidayEntry holidayEntry";
	private static final String _SQL_COUNT_HOLIDAYENTRY_WHERE = "SELECT COUNT(holidayEntry) FROM HolidayEntry holidayEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "holidayEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HolidayEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HolidayEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(HolidayEntryPersistenceImpl.class);
}
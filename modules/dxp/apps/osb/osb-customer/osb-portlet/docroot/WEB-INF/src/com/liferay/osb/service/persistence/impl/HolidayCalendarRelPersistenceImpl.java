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

import com.liferay.osb.exception.NoSuchHolidayCalendarRelException;
import com.liferay.osb.model.HolidayCalendarRel;
import com.liferay.osb.model.impl.HolidayCalendarRelImpl;
import com.liferay.osb.model.impl.HolidayCalendarRelModelImpl;
import com.liferay.osb.service.persistence.HolidayCalendarRelPersistence;

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
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the holiday calendar rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarRelPersistence
 * @see com.liferay.osb.service.persistence.HolidayCalendarRelUtil
 * @generated
 */
@ProviderType
public class HolidayCalendarRelPersistenceImpl extends BasePersistenceImpl<HolidayCalendarRel>
	implements HolidayCalendarRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HolidayCalendarRelUtil} to access the holiday calendar rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HolidayCalendarRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_HOLIDAYCALENDARID =
		new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByHolidayCalendarId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDARID =
		new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByHolidayCalendarId", new String[] { Long.class.getName() },
			HolidayCalendarRelModelImpl.HOLIDAYCALENDARID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_HOLIDAYCALENDARID = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByHolidayCalendarId", new String[] { Long.class.getName() });

	/**
	 * Returns all the holiday calendar rels where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @return the matching holiday calendar rels
	 */
	@Override
	public List<HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId) {
		return findByHolidayCalendarId(holidayCalendarId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the holiday calendar rels where holidayCalendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @return the range of matching holiday calendar rels
	 */
	@Override
	public List<HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId, int start, int end) {
		return findByHolidayCalendarId(holidayCalendarId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the holiday calendar rels where holidayCalendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching holiday calendar rels
	 */
	@Override
	public List<HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId, int start, int end,
		OrderByComparator<HolidayCalendarRel> orderByComparator) {
		return findByHolidayCalendarId(holidayCalendarId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the holiday calendar rels where holidayCalendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching holiday calendar rels
	 */
	@Override
	public List<HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId, int start, int end,
		OrderByComparator<HolidayCalendarRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDARID;
			finderArgs = new Object[] { holidayCalendarId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_HOLIDAYCALENDARID;
			finderArgs = new Object[] {
					holidayCalendarId,
					
					start, end, orderByComparator
				};
		}

		List<HolidayCalendarRel> list = null;

		if (retrieveFromCache) {
			list = (List<HolidayCalendarRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HolidayCalendarRel holidayCalendarRel : list) {
					if ((holidayCalendarId != holidayCalendarRel.getHolidayCalendarId())) {
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

			query.append(_SQL_SELECT_HOLIDAYCALENDARREL_WHERE);

			query.append(_FINDER_COLUMN_HOLIDAYCALENDARID_HOLIDAYCALENDARID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(HolidayCalendarRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(holidayCalendarId);

				if (!pagination) {
					list = (List<HolidayCalendarRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HolidayCalendarRel>)QueryUtil.list(q,
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
	 * Returns the first holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday calendar rel
	 * @throws NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	 */
	@Override
	public HolidayCalendarRel findByHolidayCalendarId_First(
		long holidayCalendarId,
		OrderByComparator<HolidayCalendarRel> orderByComparator)
		throws NoSuchHolidayCalendarRelException {
		HolidayCalendarRel holidayCalendarRel = fetchByHolidayCalendarId_First(holidayCalendarId,
				orderByComparator);

		if (holidayCalendarRel != null) {
			return holidayCalendarRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("holidayCalendarId=");
		msg.append(holidayCalendarId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHolidayCalendarRelException(msg.toString());
	}

	/**
	 * Returns the first holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	 */
	@Override
	public HolidayCalendarRel fetchByHolidayCalendarId_First(
		long holidayCalendarId,
		OrderByComparator<HolidayCalendarRel> orderByComparator) {
		List<HolidayCalendarRel> list = findByHolidayCalendarId(holidayCalendarId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday calendar rel
	 * @throws NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	 */
	@Override
	public HolidayCalendarRel findByHolidayCalendarId_Last(
		long holidayCalendarId,
		OrderByComparator<HolidayCalendarRel> orderByComparator)
		throws NoSuchHolidayCalendarRelException {
		HolidayCalendarRel holidayCalendarRel = fetchByHolidayCalendarId_Last(holidayCalendarId,
				orderByComparator);

		if (holidayCalendarRel != null) {
			return holidayCalendarRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("holidayCalendarId=");
		msg.append(holidayCalendarId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHolidayCalendarRelException(msg.toString());
	}

	/**
	 * Returns the last holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	 */
	@Override
	public HolidayCalendarRel fetchByHolidayCalendarId_Last(
		long holidayCalendarId,
		OrderByComparator<HolidayCalendarRel> orderByComparator) {
		int count = countByHolidayCalendarId(holidayCalendarId);

		if (count == 0) {
			return null;
		}

		List<HolidayCalendarRel> list = findByHolidayCalendarId(holidayCalendarId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the holiday calendar rels before and after the current holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarRelId the primary key of the current holiday calendar rel
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next holiday calendar rel
	 * @throws NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	 */
	@Override
	public HolidayCalendarRel[] findByHolidayCalendarId_PrevAndNext(
		long holidayCalendarRelId, long holidayCalendarId,
		OrderByComparator<HolidayCalendarRel> orderByComparator)
		throws NoSuchHolidayCalendarRelException {
		HolidayCalendarRel holidayCalendarRel = findByPrimaryKey(holidayCalendarRelId);

		Session session = null;

		try {
			session = openSession();

			HolidayCalendarRel[] array = new HolidayCalendarRelImpl[3];

			array[0] = getByHolidayCalendarId_PrevAndNext(session,
					holidayCalendarRel, holidayCalendarId, orderByComparator,
					true);

			array[1] = holidayCalendarRel;

			array[2] = getByHolidayCalendarId_PrevAndNext(session,
					holidayCalendarRel, holidayCalendarId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected HolidayCalendarRel getByHolidayCalendarId_PrevAndNext(
		Session session, HolidayCalendarRel holidayCalendarRel,
		long holidayCalendarId,
		OrderByComparator<HolidayCalendarRel> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_HOLIDAYCALENDARREL_WHERE);

		query.append(_FINDER_COLUMN_HOLIDAYCALENDARID_HOLIDAYCALENDARID_2);

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
			query.append(HolidayCalendarRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(holidayCalendarId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(holidayCalendarRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HolidayCalendarRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the holiday calendar rels where holidayCalendarId = &#63; from the database.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 */
	@Override
	public void removeByHolidayCalendarId(long holidayCalendarId) {
		for (HolidayCalendarRel holidayCalendarRel : findByHolidayCalendarId(
				holidayCalendarId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(holidayCalendarRel);
		}
	}

	/**
	 * Returns the number of holiday calendar rels where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @return the number of matching holiday calendar rels
	 */
	@Override
	public int countByHolidayCalendarId(long holidayCalendarId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_HOLIDAYCALENDARID;

		Object[] finderArgs = new Object[] { holidayCalendarId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HOLIDAYCALENDARREL_WHERE);

			query.append(_FINDER_COLUMN_HOLIDAYCALENDARID_HOLIDAYCALENDARID_2);

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

	private static final String _FINDER_COLUMN_HOLIDAYCALENDARID_HOLIDAYCALENDARID_2 =
		"holidayCalendarRel.holidayCalendarId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_HC_U = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByHC_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			HolidayCalendarRelModelImpl.HOLIDAYCALENDARID_COLUMN_BITMASK |
			HolidayCalendarRelModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_HC_U = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByHC_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or throws a {@link NoSuchHolidayCalendarRelException} if it could not be found.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param userId the user ID
	 * @return the matching holiday calendar rel
	 * @throws NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	 */
	@Override
	public HolidayCalendarRel findByHC_U(long holidayCalendarId, long userId)
		throws NoSuchHolidayCalendarRelException {
		HolidayCalendarRel holidayCalendarRel = fetchByHC_U(holidayCalendarId,
				userId);

		if (holidayCalendarRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("holidayCalendarId=");
			msg.append(holidayCalendarId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchHolidayCalendarRelException(msg.toString());
		}

		return holidayCalendarRel;
	}

	/**
	 * Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param userId the user ID
	 * @return the matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	 */
	@Override
	public HolidayCalendarRel fetchByHC_U(long holidayCalendarId, long userId) {
		return fetchByHC_U(holidayCalendarId, userId, true);
	}

	/**
	 * Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	 */
	@Override
	public HolidayCalendarRel fetchByHC_U(long holidayCalendarId, long userId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { holidayCalendarId, userId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_HC_U,
					finderArgs, this);
		}

		if (result instanceof HolidayCalendarRel) {
			HolidayCalendarRel holidayCalendarRel = (HolidayCalendarRel)result;

			if ((holidayCalendarId != holidayCalendarRel.getHolidayCalendarId()) ||
					(userId != holidayCalendarRel.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_HOLIDAYCALENDARREL_WHERE);

			query.append(_FINDER_COLUMN_HC_U_HOLIDAYCALENDARID_2);

			query.append(_FINDER_COLUMN_HC_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(holidayCalendarId);

				qPos.add(userId);

				List<HolidayCalendarRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_HC_U,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"HolidayCalendarRelPersistenceImpl.fetchByHC_U(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					HolidayCalendarRel holidayCalendarRel = list.get(0);

					result = holidayCalendarRel;

					cacheResult(holidayCalendarRel);

					if ((holidayCalendarRel.getHolidayCalendarId() != holidayCalendarId) ||
							(holidayCalendarRel.getUserId() != userId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_HC_U,
							finderArgs, holidayCalendarRel);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_HC_U, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (HolidayCalendarRel)result;
		}
	}

	/**
	 * Removes the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; from the database.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param userId the user ID
	 * @return the holiday calendar rel that was removed
	 */
	@Override
	public HolidayCalendarRel removeByHC_U(long holidayCalendarId, long userId)
		throws NoSuchHolidayCalendarRelException {
		HolidayCalendarRel holidayCalendarRel = findByHC_U(holidayCalendarId,
				userId);

		return remove(holidayCalendarRel);
	}

	/**
	 * Returns the number of holiday calendar rels where holidayCalendarId = &#63; and userId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param userId the user ID
	 * @return the number of matching holiday calendar rels
	 */
	@Override
	public int countByHC_U(long holidayCalendarId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_HC_U;

		Object[] finderArgs = new Object[] { holidayCalendarId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HOLIDAYCALENDARREL_WHERE);

			query.append(_FINDER_COLUMN_HC_U_HOLIDAYCALENDARID_2);

			query.append(_FINDER_COLUMN_HC_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(holidayCalendarId);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_HC_U_HOLIDAYCALENDARID_2 = "holidayCalendarRel.holidayCalendarId = ? AND ";
	private static final String _FINDER_COLUMN_HC_U_USERID_2 = "holidayCalendarRel.userId = ?";

	public HolidayCalendarRelPersistenceImpl() {
		setModelClass(HolidayCalendarRel.class);
	}

	/**
	 * Caches the holiday calendar rel in the entity cache if it is enabled.
	 *
	 * @param holidayCalendarRel the holiday calendar rel
	 */
	@Override
	public void cacheResult(HolidayCalendarRel holidayCalendarRel) {
		entityCache.putResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelImpl.class, holidayCalendarRel.getPrimaryKey(),
			holidayCalendarRel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_HC_U,
			new Object[] {
				holidayCalendarRel.getHolidayCalendarId(),
				holidayCalendarRel.getUserId()
			}, holidayCalendarRel);

		holidayCalendarRel.resetOriginalValues();
	}

	/**
	 * Caches the holiday calendar rels in the entity cache if it is enabled.
	 *
	 * @param holidayCalendarRels the holiday calendar rels
	 */
	@Override
	public void cacheResult(List<HolidayCalendarRel> holidayCalendarRels) {
		for (HolidayCalendarRel holidayCalendarRel : holidayCalendarRels) {
			if (entityCache.getResult(
						HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
						HolidayCalendarRelImpl.class,
						holidayCalendarRel.getPrimaryKey()) == null) {
				cacheResult(holidayCalendarRel);
			}
			else {
				holidayCalendarRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all holiday calendar rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HolidayCalendarRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the holiday calendar rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HolidayCalendarRel holidayCalendarRel) {
		entityCache.removeResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelImpl.class, holidayCalendarRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((HolidayCalendarRelModelImpl)holidayCalendarRel,
			true);
	}

	@Override
	public void clearCache(List<HolidayCalendarRel> holidayCalendarRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HolidayCalendarRel holidayCalendarRel : holidayCalendarRels) {
			entityCache.removeResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
				HolidayCalendarRelImpl.class, holidayCalendarRel.getPrimaryKey());

			clearUniqueFindersCache((HolidayCalendarRelModelImpl)holidayCalendarRel,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		HolidayCalendarRelModelImpl holidayCalendarRelModelImpl) {
		Object[] args = new Object[] {
				holidayCalendarRelModelImpl.getHolidayCalendarId(),
				holidayCalendarRelModelImpl.getUserId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_HC_U, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_HC_U, args,
			holidayCalendarRelModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		HolidayCalendarRelModelImpl holidayCalendarRelModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					holidayCalendarRelModelImpl.getHolidayCalendarId(),
					holidayCalendarRelModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_HC_U, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_HC_U, args);
		}

		if ((holidayCalendarRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_HC_U.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					holidayCalendarRelModelImpl.getOriginalHolidayCalendarId(),
					holidayCalendarRelModelImpl.getOriginalUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_HC_U, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_HC_U, args);
		}
	}

	/**
	 * Creates a new holiday calendar rel with the primary key. Does not add the holiday calendar rel to the database.
	 *
	 * @param holidayCalendarRelId the primary key for the new holiday calendar rel
	 * @return the new holiday calendar rel
	 */
	@Override
	public HolidayCalendarRel create(long holidayCalendarRelId) {
		HolidayCalendarRel holidayCalendarRel = new HolidayCalendarRelImpl();

		holidayCalendarRel.setNew(true);
		holidayCalendarRel.setPrimaryKey(holidayCalendarRelId);

		return holidayCalendarRel;
	}

	/**
	 * Removes the holiday calendar rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param holidayCalendarRelId the primary key of the holiday calendar rel
	 * @return the holiday calendar rel that was removed
	 * @throws NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	 */
	@Override
	public HolidayCalendarRel remove(long holidayCalendarRelId)
		throws NoSuchHolidayCalendarRelException {
		return remove((Serializable)holidayCalendarRelId);
	}

	/**
	 * Removes the holiday calendar rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the holiday calendar rel
	 * @return the holiday calendar rel that was removed
	 * @throws NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	 */
	@Override
	public HolidayCalendarRel remove(Serializable primaryKey)
		throws NoSuchHolidayCalendarRelException {
		Session session = null;

		try {
			session = openSession();

			HolidayCalendarRel holidayCalendarRel = (HolidayCalendarRel)session.get(HolidayCalendarRelImpl.class,
					primaryKey);

			if (holidayCalendarRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHolidayCalendarRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(holidayCalendarRel);
		}
		catch (NoSuchHolidayCalendarRelException nsee) {
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
	protected HolidayCalendarRel removeImpl(
		HolidayCalendarRel holidayCalendarRel) {
		holidayCalendarRel = toUnwrappedModel(holidayCalendarRel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(holidayCalendarRel)) {
				holidayCalendarRel = (HolidayCalendarRel)session.get(HolidayCalendarRelImpl.class,
						holidayCalendarRel.getPrimaryKeyObj());
			}

			if (holidayCalendarRel != null) {
				session.delete(holidayCalendarRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (holidayCalendarRel != null) {
			clearCache(holidayCalendarRel);
		}

		return holidayCalendarRel;
	}

	@Override
	public HolidayCalendarRel updateImpl(HolidayCalendarRel holidayCalendarRel) {
		holidayCalendarRel = toUnwrappedModel(holidayCalendarRel);

		boolean isNew = holidayCalendarRel.isNew();

		HolidayCalendarRelModelImpl holidayCalendarRelModelImpl = (HolidayCalendarRelModelImpl)holidayCalendarRel;

		Session session = null;

		try {
			session = openSession();

			if (holidayCalendarRel.isNew()) {
				session.save(holidayCalendarRel);

				holidayCalendarRel.setNew(false);
			}
			else {
				holidayCalendarRel = (HolidayCalendarRel)session.merge(holidayCalendarRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!HolidayCalendarRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					holidayCalendarRelModelImpl.getHolidayCalendarId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_HOLIDAYCALENDARID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDARID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((holidayCalendarRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDARID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						holidayCalendarRelModelImpl.getOriginalHolidayCalendarId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_HOLIDAYCALENDARID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDARID,
					args);

				args = new Object[] {
						holidayCalendarRelModelImpl.getHolidayCalendarId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_HOLIDAYCALENDARID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDARID,
					args);
			}
		}

		entityCache.putResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelImpl.class, holidayCalendarRel.getPrimaryKey(),
			holidayCalendarRel, false);

		clearUniqueFindersCache(holidayCalendarRelModelImpl, false);
		cacheUniqueFindersCache(holidayCalendarRelModelImpl);

		holidayCalendarRel.resetOriginalValues();

		return holidayCalendarRel;
	}

	protected HolidayCalendarRel toUnwrappedModel(
		HolidayCalendarRel holidayCalendarRel) {
		if (holidayCalendarRel instanceof HolidayCalendarRelImpl) {
			return holidayCalendarRel;
		}

		HolidayCalendarRelImpl holidayCalendarRelImpl = new HolidayCalendarRelImpl();

		holidayCalendarRelImpl.setNew(holidayCalendarRel.isNew());
		holidayCalendarRelImpl.setPrimaryKey(holidayCalendarRel.getPrimaryKey());

		holidayCalendarRelImpl.setHolidayCalendarRelId(holidayCalendarRel.getHolidayCalendarRelId());
		holidayCalendarRelImpl.setHolidayCalendarId(holidayCalendarRel.getHolidayCalendarId());
		holidayCalendarRelImpl.setUserId(holidayCalendarRel.getUserId());

		return holidayCalendarRelImpl;
	}

	/**
	 * Returns the holiday calendar rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday calendar rel
	 * @return the holiday calendar rel
	 * @throws NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	 */
	@Override
	public HolidayCalendarRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHolidayCalendarRelException {
		HolidayCalendarRel holidayCalendarRel = fetchByPrimaryKey(primaryKey);

		if (holidayCalendarRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHolidayCalendarRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return holidayCalendarRel;
	}

	/**
	 * Returns the holiday calendar rel with the primary key or throws a {@link NoSuchHolidayCalendarRelException} if it could not be found.
	 *
	 * @param holidayCalendarRelId the primary key of the holiday calendar rel
	 * @return the holiday calendar rel
	 * @throws NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	 */
	@Override
	public HolidayCalendarRel findByPrimaryKey(long holidayCalendarRelId)
		throws NoSuchHolidayCalendarRelException {
		return findByPrimaryKey((Serializable)holidayCalendarRelId);
	}

	/**
	 * Returns the holiday calendar rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday calendar rel
	 * @return the holiday calendar rel, or <code>null</code> if a holiday calendar rel with the primary key could not be found
	 */
	@Override
	public HolidayCalendarRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
				HolidayCalendarRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HolidayCalendarRel holidayCalendarRel = (HolidayCalendarRel)serializable;

		if (holidayCalendarRel == null) {
			Session session = null;

			try {
				session = openSession();

				holidayCalendarRel = (HolidayCalendarRel)session.get(HolidayCalendarRelImpl.class,
						primaryKey);

				if (holidayCalendarRel != null) {
					cacheResult(holidayCalendarRel);
				}
				else {
					entityCache.putResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
						HolidayCalendarRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
					HolidayCalendarRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return holidayCalendarRel;
	}

	/**
	 * Returns the holiday calendar rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param holidayCalendarRelId the primary key of the holiday calendar rel
	 * @return the holiday calendar rel, or <code>null</code> if a holiday calendar rel with the primary key could not be found
	 */
	@Override
	public HolidayCalendarRel fetchByPrimaryKey(long holidayCalendarRelId) {
		return fetchByPrimaryKey((Serializable)holidayCalendarRelId);
	}

	@Override
	public Map<Serializable, HolidayCalendarRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HolidayCalendarRel> map = new HashMap<Serializable, HolidayCalendarRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			HolidayCalendarRel holidayCalendarRel = fetchByPrimaryKey(primaryKey);

			if (holidayCalendarRel != null) {
				map.put(primaryKey, holidayCalendarRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
					HolidayCalendarRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (HolidayCalendarRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_HOLIDAYCALENDARREL_WHERE_PKS_IN);

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

			for (HolidayCalendarRel holidayCalendarRel : (List<HolidayCalendarRel>)q.list()) {
				map.put(holidayCalendarRel.getPrimaryKeyObj(),
					holidayCalendarRel);

				cacheResult(holidayCalendarRel);

				uncachedPrimaryKeys.remove(holidayCalendarRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
					HolidayCalendarRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the holiday calendar rels.
	 *
	 * @return the holiday calendar rels
	 */
	@Override
	public List<HolidayCalendarRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the holiday calendar rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @return the range of holiday calendar rels
	 */
	@Override
	public List<HolidayCalendarRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the holiday calendar rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of holiday calendar rels
	 */
	@Override
	public List<HolidayCalendarRel> findAll(int start, int end,
		OrderByComparator<HolidayCalendarRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the holiday calendar rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of holiday calendar rels
	 */
	@Override
	public List<HolidayCalendarRel> findAll(int start, int end,
		OrderByComparator<HolidayCalendarRel> orderByComparator,
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

		List<HolidayCalendarRel> list = null;

		if (retrieveFromCache) {
			list = (List<HolidayCalendarRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HOLIDAYCALENDARREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HOLIDAYCALENDARREL;

				if (pagination) {
					sql = sql.concat(HolidayCalendarRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HolidayCalendarRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HolidayCalendarRel>)QueryUtil.list(q,
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
	 * Removes all the holiday calendar rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HolidayCalendarRel holidayCalendarRel : findAll()) {
			remove(holidayCalendarRel);
		}
	}

	/**
	 * Returns the number of holiday calendar rels.
	 *
	 * @return the number of holiday calendar rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HOLIDAYCALENDARREL);

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
		return HolidayCalendarRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the holiday calendar rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HolidayCalendarRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_HOLIDAYCALENDARREL = "SELECT holidayCalendarRel FROM HolidayCalendarRel holidayCalendarRel";
	private static final String _SQL_SELECT_HOLIDAYCALENDARREL_WHERE_PKS_IN = "SELECT holidayCalendarRel FROM HolidayCalendarRel holidayCalendarRel WHERE holidayCalendarRelId IN (";
	private static final String _SQL_SELECT_HOLIDAYCALENDARREL_WHERE = "SELECT holidayCalendarRel FROM HolidayCalendarRel holidayCalendarRel WHERE ";
	private static final String _SQL_COUNT_HOLIDAYCALENDARREL = "SELECT COUNT(holidayCalendarRel) FROM HolidayCalendarRel holidayCalendarRel";
	private static final String _SQL_COUNT_HOLIDAYCALENDARREL_WHERE = "SELECT COUNT(holidayCalendarRel) FROM HolidayCalendarRel holidayCalendarRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "holidayCalendarRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HolidayCalendarRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HolidayCalendarRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(HolidayCalendarRelPersistenceImpl.class);
}
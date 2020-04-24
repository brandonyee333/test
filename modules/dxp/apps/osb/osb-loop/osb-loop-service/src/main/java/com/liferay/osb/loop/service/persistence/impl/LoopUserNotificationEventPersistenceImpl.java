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

import com.liferay.osb.loop.exception.NoSuchLoopUserNotificationEventException;
import com.liferay.osb.loop.model.LoopUserNotificationEvent;
import com.liferay.osb.loop.model.impl.LoopUserNotificationEventImpl;
import com.liferay.osb.loop.model.impl.LoopUserNotificationEventModelImpl;
import com.liferay.osb.loop.service.persistence.LoopUserNotificationEventPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
 * The persistence implementation for the loop user notification event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationEventPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopUserNotificationEventUtil
 * @generated
 */
@ProviderType
public class LoopUserNotificationEventPersistenceImpl
	extends BasePersistenceImpl<LoopUserNotificationEvent>
	implements LoopUserNotificationEventPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopUserNotificationEventUtil} to access the loop user notification event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopUserNotificationEventImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			LoopUserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			LoopUserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPKEY = new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			LoopUserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupKey",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPKEY =
		new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			LoopUserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupKey",
			new String[] { Long.class.getName() },
			LoopUserNotificationEventModelImpl.GROUPKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPKEY = new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupKey", new String[] { Long.class.getName() });

	/**
	 * Returns all the loop user notification events where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @return the matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGroupKey(long groupKey) {
		return findByGroupKey(groupKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the loop user notification events where groupKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupKey the group key
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @return the range of matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGroupKey(long groupKey,
		int start, int end) {
		return findByGroupKey(groupKey, start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupKey the group key
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGroupKey(long groupKey,
		int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {
		return findByGroupKey(groupKey, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupKey the group key
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGroupKey(long groupKey,
		int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPKEY;
			finderArgs = new Object[] { groupKey };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPKEY;
			finderArgs = new Object[] { groupKey, start, end, orderByComparator };
		}

		List<LoopUserNotificationEvent> list = null;

		if (retrieveFromCache) {
			list = (List<LoopUserNotificationEvent>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LoopUserNotificationEvent loopUserNotificationEvent : list) {
					if ((groupKey != loopUserNotificationEvent.getGroupKey())) {
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

			query.append(_SQL_SELECT_LOOPUSERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPKEY_GROUPKEY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LoopUserNotificationEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupKey);

				if (!pagination) {
					list = (List<LoopUserNotificationEvent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopUserNotificationEvent>)QueryUtil.list(q,
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
	 * Returns the first loop user notification event in the ordered set where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent findByGroupKey_First(long groupKey,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException {
		LoopUserNotificationEvent loopUserNotificationEvent = fetchByGroupKey_First(groupKey,
				orderByComparator);

		if (loopUserNotificationEvent != null) {
			return loopUserNotificationEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupKey=");
		msg.append(groupKey);

		msg.append("}");

		throw new NoSuchLoopUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the first loop user notification event in the ordered set where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent fetchByGroupKey_First(long groupKey,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {
		List<LoopUserNotificationEvent> list = findByGroupKey(groupKey, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last loop user notification event in the ordered set where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent findByGroupKey_Last(long groupKey,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException {
		LoopUserNotificationEvent loopUserNotificationEvent = fetchByGroupKey_Last(groupKey,
				orderByComparator);

		if (loopUserNotificationEvent != null) {
			return loopUserNotificationEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupKey=");
		msg.append(groupKey);

		msg.append("}");

		throw new NoSuchLoopUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the last loop user notification event in the ordered set where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent fetchByGroupKey_Last(long groupKey,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {
		int count = countByGroupKey(groupKey);

		if (count == 0) {
			return null;
		}

		List<LoopUserNotificationEvent> list = findByGroupKey(groupKey,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the loop user notification events before and after the current loop user notification event in the ordered set where groupKey = &#63;.
	 *
	 * @param loopUserNotificationEventId the primary key of the current loop user notification event
	 * @param groupKey the group key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationEvent[] findByGroupKey_PrevAndNext(
		long loopUserNotificationEventId, long groupKey,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException {
		LoopUserNotificationEvent loopUserNotificationEvent = findByPrimaryKey(loopUserNotificationEventId);

		Session session = null;

		try {
			session = openSession();

			LoopUserNotificationEvent[] array = new LoopUserNotificationEventImpl[3];

			array[0] = getByGroupKey_PrevAndNext(session,
					loopUserNotificationEvent, groupKey, orderByComparator, true);

			array[1] = loopUserNotificationEvent;

			array[2] = getByGroupKey_PrevAndNext(session,
					loopUserNotificationEvent, groupKey, orderByComparator,
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

	protected LoopUserNotificationEvent getByGroupKey_PrevAndNext(
		Session session, LoopUserNotificationEvent loopUserNotificationEvent,
		long groupKey,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator,
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

		query.append(_SQL_SELECT_LOOPUSERNOTIFICATIONEVENT_WHERE);

		query.append(_FINDER_COLUMN_GROUPKEY_GROUPKEY_2);

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
			query.append(LoopUserNotificationEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupKey);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(loopUserNotificationEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LoopUserNotificationEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the loop user notification events where groupKey = &#63; from the database.
	 *
	 * @param groupKey the group key
	 */
	@Override
	public void removeByGroupKey(long groupKey) {
		for (LoopUserNotificationEvent loopUserNotificationEvent : findByGroupKey(
				groupKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(loopUserNotificationEvent);
		}
	}

	/**
	 * Returns the number of loop user notification events where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @return the number of matching loop user notification events
	 */
	@Override
	public int countByGroupKey(long groupKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPKEY;

		Object[] finderArgs = new Object[] { groupKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LOOPUSERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPKEY_GROUPKEY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupKey);

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

	private static final String _FINDER_COLUMN_GROUPKEY_GROUPKEY_2 = "loopUserNotificationEvent.groupKey = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GCNI_GCP = new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			LoopUserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGCNI_GCP",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP =
		new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			LoopUserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGCNI_GCP",
			new String[] { Long.class.getName(), Long.class.getName() },
			LoopUserNotificationEventModelImpl.GROUPCLASSNAMEID_COLUMN_BITMASK |
			LoopUserNotificationEventModelImpl.GROUPCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GCNI_GCP = new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGCNI_GCP",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @return the matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK) {
		return findByGCNI_GCP(groupClassNameId, groupClassPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @return the range of matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK, int start, int end) {
		return findByGCNI_GCP(groupClassNameId, groupClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK, int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {
		return findByGCNI_GCP(groupClassNameId, groupClassPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK, int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP;
			finderArgs = new Object[] { groupClassNameId, groupClassPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GCNI_GCP;
			finderArgs = new Object[] {
					groupClassNameId, groupClassPK,
					
					start, end, orderByComparator
				};
		}

		List<LoopUserNotificationEvent> list = null;

		if (retrieveFromCache) {
			list = (List<LoopUserNotificationEvent>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LoopUserNotificationEvent loopUserNotificationEvent : list) {
					if ((groupClassNameId != loopUserNotificationEvent.getGroupClassNameId()) ||
							(groupClassPK != loopUserNotificationEvent.getGroupClassPK())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_LOOPUSERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_GCNI_GCP_GROUPCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GCNI_GCP_GROUPCLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LoopUserNotificationEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupClassNameId);

				qPos.add(groupClassPK);

				if (!pagination) {
					list = (List<LoopUserNotificationEvent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopUserNotificationEvent>)QueryUtil.list(q,
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
	 * Returns the first loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent findByGCNI_GCP_First(
		long groupClassNameId, long groupClassPK,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException {
		LoopUserNotificationEvent loopUserNotificationEvent = fetchByGCNI_GCP_First(groupClassNameId,
				groupClassPK, orderByComparator);

		if (loopUserNotificationEvent != null) {
			return loopUserNotificationEvent;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupClassNameId=");
		msg.append(groupClassNameId);

		msg.append(", groupClassPK=");
		msg.append(groupClassPK);

		msg.append("}");

		throw new NoSuchLoopUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the first loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent fetchByGCNI_GCP_First(
		long groupClassNameId, long groupClassPK,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {
		List<LoopUserNotificationEvent> list = findByGCNI_GCP(groupClassNameId,
				groupClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent findByGCNI_GCP_Last(
		long groupClassNameId, long groupClassPK,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException {
		LoopUserNotificationEvent loopUserNotificationEvent = fetchByGCNI_GCP_Last(groupClassNameId,
				groupClassPK, orderByComparator);

		if (loopUserNotificationEvent != null) {
			return loopUserNotificationEvent;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupClassNameId=");
		msg.append(groupClassNameId);

		msg.append(", groupClassPK=");
		msg.append(groupClassPK);

		msg.append("}");

		throw new NoSuchLoopUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the last loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent fetchByGCNI_GCP_Last(
		long groupClassNameId, long groupClassPK,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {
		int count = countByGCNI_GCP(groupClassNameId, groupClassPK);

		if (count == 0) {
			return null;
		}

		List<LoopUserNotificationEvent> list = findByGCNI_GCP(groupClassNameId,
				groupClassPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the loop user notification events before and after the current loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param loopUserNotificationEventId the primary key of the current loop user notification event
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationEvent[] findByGCNI_GCP_PrevAndNext(
		long loopUserNotificationEventId, long groupClassNameId,
		long groupClassPK,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException {
		LoopUserNotificationEvent loopUserNotificationEvent = findByPrimaryKey(loopUserNotificationEventId);

		Session session = null;

		try {
			session = openSession();

			LoopUserNotificationEvent[] array = new LoopUserNotificationEventImpl[3];

			array[0] = getByGCNI_GCP_PrevAndNext(session,
					loopUserNotificationEvent, groupClassNameId, groupClassPK,
					orderByComparator, true);

			array[1] = loopUserNotificationEvent;

			array[2] = getByGCNI_GCP_PrevAndNext(session,
					loopUserNotificationEvent, groupClassNameId, groupClassPK,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LoopUserNotificationEvent getByGCNI_GCP_PrevAndNext(
		Session session, LoopUserNotificationEvent loopUserNotificationEvent,
		long groupClassNameId, long groupClassPK,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LOOPUSERNOTIFICATIONEVENT_WHERE);

		query.append(_FINDER_COLUMN_GCNI_GCP_GROUPCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_GCNI_GCP_GROUPCLASSPK_2);

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
			query.append(LoopUserNotificationEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupClassNameId);

		qPos.add(groupClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(loopUserNotificationEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LoopUserNotificationEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; from the database.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 */
	@Override
	public void removeByGCNI_GCP(long groupClassNameId, long groupClassPK) {
		for (LoopUserNotificationEvent loopUserNotificationEvent : findByGCNI_GCP(
				groupClassNameId, groupClassPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(loopUserNotificationEvent);
		}
	}

	/**
	 * Returns the number of loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @return the number of matching loop user notification events
	 */
	@Override
	public int countByGCNI_GCP(long groupClassNameId, long groupClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GCNI_GCP;

		Object[] finderArgs = new Object[] { groupClassNameId, groupClassPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LOOPUSERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_GCNI_GCP_GROUPCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GCNI_GCP_GROUPCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupClassNameId);

				qPos.add(groupClassPK);

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

	private static final String _FINDER_COLUMN_GCNI_GCP_GROUPCLASSNAMEID_2 = "loopUserNotificationEvent.groupClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_GCNI_GCP_GROUPCLASSPK_2 = "loopUserNotificationEvent.groupClassPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GCNI_GCP_T =
		new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			LoopUserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGCNI_GCP_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP_T =
		new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			LoopUserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGCNI_GCP_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			LoopUserNotificationEventModelImpl.GROUPCLASSNAMEID_COLUMN_BITMASK |
			LoopUserNotificationEventModelImpl.GROUPCLASSPK_COLUMN_BITMASK |
			LoopUserNotificationEventModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GCNI_GCP_T = new FinderPath(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGCNI_GCP_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @return the matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type) {
		return findByGCNI_GCP_T(groupClassNameId, groupClassPK, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @return the range of matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type, int start, int end) {
		return findByGCNI_GCP_T(groupClassNameId, groupClassPK, type, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type, int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {
		return findByGCNI_GCP_T(groupClassNameId, groupClassPK, type, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type, int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP_T;
			finderArgs = new Object[] { groupClassNameId, groupClassPK, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GCNI_GCP_T;
			finderArgs = new Object[] {
					groupClassNameId, groupClassPK, type,
					
					start, end, orderByComparator
				};
		}

		List<LoopUserNotificationEvent> list = null;

		if (retrieveFromCache) {
			list = (List<LoopUserNotificationEvent>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LoopUserNotificationEvent loopUserNotificationEvent : list) {
					if ((groupClassNameId != loopUserNotificationEvent.getGroupClassNameId()) ||
							(groupClassPK != loopUserNotificationEvent.getGroupClassPK()) ||
							(type != loopUserNotificationEvent.getType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_LOOPUSERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_GCNI_GCP_T_GROUPCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GCNI_GCP_T_GROUPCLASSPK_2);

			query.append(_FINDER_COLUMN_GCNI_GCP_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LoopUserNotificationEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupClassNameId);

				qPos.add(groupClassPK);

				qPos.add(type);

				if (!pagination) {
					list = (List<LoopUserNotificationEvent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopUserNotificationEvent>)QueryUtil.list(q,
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
	 * Returns the first loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent findByGCNI_GCP_T_First(
		long groupClassNameId, long groupClassPK, int type,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException {
		LoopUserNotificationEvent loopUserNotificationEvent = fetchByGCNI_GCP_T_First(groupClassNameId,
				groupClassPK, type, orderByComparator);

		if (loopUserNotificationEvent != null) {
			return loopUserNotificationEvent;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupClassNameId=");
		msg.append(groupClassNameId);

		msg.append(", groupClassPK=");
		msg.append(groupClassPK);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchLoopUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the first loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent fetchByGCNI_GCP_T_First(
		long groupClassNameId, long groupClassPK, int type,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {
		List<LoopUserNotificationEvent> list = findByGCNI_GCP_T(groupClassNameId,
				groupClassPK, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent findByGCNI_GCP_T_Last(
		long groupClassNameId, long groupClassPK, int type,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException {
		LoopUserNotificationEvent loopUserNotificationEvent = fetchByGCNI_GCP_T_Last(groupClassNameId,
				groupClassPK, type, orderByComparator);

		if (loopUserNotificationEvent != null) {
			return loopUserNotificationEvent;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupClassNameId=");
		msg.append(groupClassNameId);

		msg.append(", groupClassPK=");
		msg.append(groupClassPK);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchLoopUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the last loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	@Override
	public LoopUserNotificationEvent fetchByGCNI_GCP_T_Last(
		long groupClassNameId, long groupClassPK, int type,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {
		int count = countByGCNI_GCP_T(groupClassNameId, groupClassPK, type);

		if (count == 0) {
			return null;
		}

		List<LoopUserNotificationEvent> list = findByGCNI_GCP_T(groupClassNameId,
				groupClassPK, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the loop user notification events before and after the current loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * @param loopUserNotificationEventId the primary key of the current loop user notification event
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationEvent[] findByGCNI_GCP_T_PrevAndNext(
		long loopUserNotificationEventId, long groupClassNameId,
		long groupClassPK, int type,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException {
		LoopUserNotificationEvent loopUserNotificationEvent = findByPrimaryKey(loopUserNotificationEventId);

		Session session = null;

		try {
			session = openSession();

			LoopUserNotificationEvent[] array = new LoopUserNotificationEventImpl[3];

			array[0] = getByGCNI_GCP_T_PrevAndNext(session,
					loopUserNotificationEvent, groupClassNameId, groupClassPK,
					type, orderByComparator, true);

			array[1] = loopUserNotificationEvent;

			array[2] = getByGCNI_GCP_T_PrevAndNext(session,
					loopUserNotificationEvent, groupClassNameId, groupClassPK,
					type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LoopUserNotificationEvent getByGCNI_GCP_T_PrevAndNext(
		Session session, LoopUserNotificationEvent loopUserNotificationEvent,
		long groupClassNameId, long groupClassPK, int type,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_LOOPUSERNOTIFICATIONEVENT_WHERE);

		query.append(_FINDER_COLUMN_GCNI_GCP_T_GROUPCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_GCNI_GCP_T_GROUPCLASSPK_2);

		query.append(_FINDER_COLUMN_GCNI_GCP_T_TYPE_2);

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
			query.append(LoopUserNotificationEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupClassNameId);

		qPos.add(groupClassPK);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(loopUserNotificationEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LoopUserNotificationEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63; from the database.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 */
	@Override
	public void removeByGCNI_GCP_T(long groupClassNameId, long groupClassPK,
		int type) {
		for (LoopUserNotificationEvent loopUserNotificationEvent : findByGCNI_GCP_T(
				groupClassNameId, groupClassPK, type, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(loopUserNotificationEvent);
		}
	}

	/**
	 * Returns the number of loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @return the number of matching loop user notification events
	 */
	@Override
	public int countByGCNI_GCP_T(long groupClassNameId, long groupClassPK,
		int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GCNI_GCP_T;

		Object[] finderArgs = new Object[] { groupClassNameId, groupClassPK, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LOOPUSERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_GCNI_GCP_T_GROUPCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GCNI_GCP_T_GROUPCLASSPK_2);

			query.append(_FINDER_COLUMN_GCNI_GCP_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupClassNameId);

				qPos.add(groupClassPK);

				qPos.add(type);

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

	private static final String _FINDER_COLUMN_GCNI_GCP_T_GROUPCLASSNAMEID_2 = "loopUserNotificationEvent.groupClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_GCNI_GCP_T_GROUPCLASSPK_2 = "loopUserNotificationEvent.groupClassPK = ? AND ";
	private static final String _FINDER_COLUMN_GCNI_GCP_T_TYPE_2 = "loopUserNotificationEvent.type = ?";

	public LoopUserNotificationEventPersistenceImpl() {
		setModelClass(LoopUserNotificationEvent.class);

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
	 * Caches the loop user notification event in the entity cache if it is enabled.
	 *
	 * @param loopUserNotificationEvent the loop user notification event
	 */
	@Override
	public void cacheResult(LoopUserNotificationEvent loopUserNotificationEvent) {
		entityCache.putResult(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventImpl.class,
			loopUserNotificationEvent.getPrimaryKey(), loopUserNotificationEvent);

		loopUserNotificationEvent.resetOriginalValues();
	}

	/**
	 * Caches the loop user notification events in the entity cache if it is enabled.
	 *
	 * @param loopUserNotificationEvents the loop user notification events
	 */
	@Override
	public void cacheResult(
		List<LoopUserNotificationEvent> loopUserNotificationEvents) {
		for (LoopUserNotificationEvent loopUserNotificationEvent : loopUserNotificationEvents) {
			if (entityCache.getResult(
						LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
						LoopUserNotificationEventImpl.class,
						loopUserNotificationEvent.getPrimaryKey()) == null) {
				cacheResult(loopUserNotificationEvent);
			}
			else {
				loopUserNotificationEvent.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop user notification events.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopUserNotificationEventImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop user notification event.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopUserNotificationEvent loopUserNotificationEvent) {
		entityCache.removeResult(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventImpl.class,
			loopUserNotificationEvent.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<LoopUserNotificationEvent> loopUserNotificationEvents) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopUserNotificationEvent loopUserNotificationEvent : loopUserNotificationEvents) {
			entityCache.removeResult(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
				LoopUserNotificationEventImpl.class,
				loopUserNotificationEvent.getPrimaryKey());
		}
	}

	/**
	 * Creates a new loop user notification event with the primary key. Does not add the loop user notification event to the database.
	 *
	 * @param loopUserNotificationEventId the primary key for the new loop user notification event
	 * @return the new loop user notification event
	 */
	@Override
	public LoopUserNotificationEvent create(long loopUserNotificationEventId) {
		LoopUserNotificationEvent loopUserNotificationEvent = new LoopUserNotificationEventImpl();

		loopUserNotificationEvent.setNew(true);
		loopUserNotificationEvent.setPrimaryKey(loopUserNotificationEventId);

		return loopUserNotificationEvent;
	}

	/**
	 * Removes the loop user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationEventId the primary key of the loop user notification event
	 * @return the loop user notification event that was removed
	 * @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationEvent remove(long loopUserNotificationEventId)
		throws NoSuchLoopUserNotificationEventException {
		return remove((Serializable)loopUserNotificationEventId);
	}

	/**
	 * Removes the loop user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop user notification event
	 * @return the loop user notification event that was removed
	 * @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationEvent remove(Serializable primaryKey)
		throws NoSuchLoopUserNotificationEventException {
		Session session = null;

		try {
			session = openSession();

			LoopUserNotificationEvent loopUserNotificationEvent = (LoopUserNotificationEvent)session.get(LoopUserNotificationEventImpl.class,
					primaryKey);

			if (loopUserNotificationEvent == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopUserNotificationEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopUserNotificationEvent);
		}
		catch (NoSuchLoopUserNotificationEventException nsee) {
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
	protected LoopUserNotificationEvent removeImpl(
		LoopUserNotificationEvent loopUserNotificationEvent) {
		loopUserNotificationEvent = toUnwrappedModel(loopUserNotificationEvent);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopUserNotificationEvent)) {
				loopUserNotificationEvent = (LoopUserNotificationEvent)session.get(LoopUserNotificationEventImpl.class,
						loopUserNotificationEvent.getPrimaryKeyObj());
			}

			if (loopUserNotificationEvent != null) {
				session.delete(loopUserNotificationEvent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopUserNotificationEvent != null) {
			clearCache(loopUserNotificationEvent);
		}

		return loopUserNotificationEvent;
	}

	@Override
	public LoopUserNotificationEvent updateImpl(
		LoopUserNotificationEvent loopUserNotificationEvent) {
		loopUserNotificationEvent = toUnwrappedModel(loopUserNotificationEvent);

		boolean isNew = loopUserNotificationEvent.isNew();

		LoopUserNotificationEventModelImpl loopUserNotificationEventModelImpl = (LoopUserNotificationEventModelImpl)loopUserNotificationEvent;

		Session session = null;

		try {
			session = openSession();

			if (loopUserNotificationEvent.isNew()) {
				session.save(loopUserNotificationEvent);

				loopUserNotificationEvent.setNew(false);
			}
			else {
				loopUserNotificationEvent = (LoopUserNotificationEvent)session.merge(loopUserNotificationEvent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LoopUserNotificationEventModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					loopUserNotificationEventModelImpl.getGroupKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPKEY, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPKEY,
				args);

			args = new Object[] {
					loopUserNotificationEventModelImpl.getGroupClassNameId(),
					loopUserNotificationEventModelImpl.getGroupClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GCNI_GCP, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP,
				args);

			args = new Object[] {
					loopUserNotificationEventModelImpl.getGroupClassNameId(),
					loopUserNotificationEventModelImpl.getGroupClassPK(),
					loopUserNotificationEventModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GCNI_GCP_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP_T,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((loopUserNotificationEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						loopUserNotificationEventModelImpl.getOriginalGroupKey()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPKEY, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPKEY,
					args);

				args = new Object[] {
						loopUserNotificationEventModelImpl.getGroupKey()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPKEY, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPKEY,
					args);
			}

			if ((loopUserNotificationEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						loopUserNotificationEventModelImpl.getOriginalGroupClassNameId(),
						loopUserNotificationEventModelImpl.getOriginalGroupClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GCNI_GCP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP,
					args);

				args = new Object[] {
						loopUserNotificationEventModelImpl.getGroupClassNameId(),
						loopUserNotificationEventModelImpl.getGroupClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GCNI_GCP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP,
					args);
			}

			if ((loopUserNotificationEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						loopUserNotificationEventModelImpl.getOriginalGroupClassNameId(),
						loopUserNotificationEventModelImpl.getOriginalGroupClassPK(),
						loopUserNotificationEventModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GCNI_GCP_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP_T,
					args);

				args = new Object[] {
						loopUserNotificationEventModelImpl.getGroupClassNameId(),
						loopUserNotificationEventModelImpl.getGroupClassPK(),
						loopUserNotificationEventModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GCNI_GCP_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GCNI_GCP_T,
					args);
			}
		}

		entityCache.putResult(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationEventImpl.class,
			loopUserNotificationEvent.getPrimaryKey(),
			loopUserNotificationEvent, false);

		loopUserNotificationEvent.resetOriginalValues();

		return loopUserNotificationEvent;
	}

	protected LoopUserNotificationEvent toUnwrappedModel(
		LoopUserNotificationEvent loopUserNotificationEvent) {
		if (loopUserNotificationEvent instanceof LoopUserNotificationEventImpl) {
			return loopUserNotificationEvent;
		}

		LoopUserNotificationEventImpl loopUserNotificationEventImpl = new LoopUserNotificationEventImpl();

		loopUserNotificationEventImpl.setNew(loopUserNotificationEvent.isNew());
		loopUserNotificationEventImpl.setPrimaryKey(loopUserNotificationEvent.getPrimaryKey());

		loopUserNotificationEventImpl.setLoopUserNotificationEventId(loopUserNotificationEvent.getLoopUserNotificationEventId());
		loopUserNotificationEventImpl.setCreateTime(loopUserNotificationEvent.getCreateTime());
		loopUserNotificationEventImpl.setRecipientUserId(loopUserNotificationEvent.getRecipientUserId());
		loopUserNotificationEventImpl.setClassNameId(loopUserNotificationEvent.getClassNameId());
		loopUserNotificationEventImpl.setClassPK(loopUserNotificationEvent.getClassPK());
		loopUserNotificationEventImpl.setGroupClassNameId(loopUserNotificationEvent.getGroupClassNameId());
		loopUserNotificationEventImpl.setGroupClassPK(loopUserNotificationEvent.getGroupClassPK());
		loopUserNotificationEventImpl.setGroupKey(loopUserNotificationEvent.getGroupKey());
		loopUserNotificationEventImpl.setType(loopUserNotificationEvent.getType());
		loopUserNotificationEventImpl.setReceived(loopUserNotificationEvent.isReceived());
		loopUserNotificationEventImpl.setOpened(loopUserNotificationEvent.isOpened());

		return loopUserNotificationEventImpl;
	}

	/**
	 * Returns the loop user notification event with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop user notification event
	 * @return the loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationEvent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopUserNotificationEventException {
		LoopUserNotificationEvent loopUserNotificationEvent = fetchByPrimaryKey(primaryKey);

		if (loopUserNotificationEvent == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopUserNotificationEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopUserNotificationEvent;
	}

	/**
	 * Returns the loop user notification event with the primary key or throws a {@link NoSuchLoopUserNotificationEventException} if it could not be found.
	 *
	 * @param loopUserNotificationEventId the primary key of the loop user notification event
	 * @return the loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationEvent findByPrimaryKey(
		long loopUserNotificationEventId)
		throws NoSuchLoopUserNotificationEventException {
		return findByPrimaryKey((Serializable)loopUserNotificationEventId);
	}

	/**
	 * Returns the loop user notification event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop user notification event
	 * @return the loop user notification event, or <code>null</code> if a loop user notification event with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationEvent fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
				LoopUserNotificationEventImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopUserNotificationEvent loopUserNotificationEvent = (LoopUserNotificationEvent)serializable;

		if (loopUserNotificationEvent == null) {
			Session session = null;

			try {
				session = openSession();

				loopUserNotificationEvent = (LoopUserNotificationEvent)session.get(LoopUserNotificationEventImpl.class,
						primaryKey);

				if (loopUserNotificationEvent != null) {
					cacheResult(loopUserNotificationEvent);
				}
				else {
					entityCache.putResult(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
						LoopUserNotificationEventImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
					LoopUserNotificationEventImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopUserNotificationEvent;
	}

	/**
	 * Returns the loop user notification event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopUserNotificationEventId the primary key of the loop user notification event
	 * @return the loop user notification event, or <code>null</code> if a loop user notification event with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationEvent fetchByPrimaryKey(
		long loopUserNotificationEventId) {
		return fetchByPrimaryKey((Serializable)loopUserNotificationEventId);
	}

	@Override
	public Map<Serializable, LoopUserNotificationEvent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopUserNotificationEvent> map = new HashMap<Serializable, LoopUserNotificationEvent>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopUserNotificationEvent loopUserNotificationEvent = fetchByPrimaryKey(primaryKey);

			if (loopUserNotificationEvent != null) {
				map.put(primaryKey, loopUserNotificationEvent);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
					LoopUserNotificationEventImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopUserNotificationEvent)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPUSERNOTIFICATIONEVENT_WHERE_PKS_IN);

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

			for (LoopUserNotificationEvent loopUserNotificationEvent : (List<LoopUserNotificationEvent>)q.list()) {
				map.put(loopUserNotificationEvent.getPrimaryKeyObj(),
					loopUserNotificationEvent);

				cacheResult(loopUserNotificationEvent);

				uncachedPrimaryKeys.remove(loopUserNotificationEvent.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopUserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
					LoopUserNotificationEventImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop user notification events.
	 *
	 * @return the loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop user notification events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @return the range of loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop user notification events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findAll(int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop user notification events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop user notification events
	 */
	@Override
	public List<LoopUserNotificationEvent> findAll(int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator,
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

		List<LoopUserNotificationEvent> list = null;

		if (retrieveFromCache) {
			list = (List<LoopUserNotificationEvent>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPUSERNOTIFICATIONEVENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPUSERNOTIFICATIONEVENT;

				if (pagination) {
					sql = sql.concat(LoopUserNotificationEventModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopUserNotificationEvent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopUserNotificationEvent>)QueryUtil.list(q,
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
	 * Removes all the loop user notification events from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopUserNotificationEvent loopUserNotificationEvent : findAll()) {
			remove(loopUserNotificationEvent);
		}
	}

	/**
	 * Returns the number of loop user notification events.
	 *
	 * @return the number of loop user notification events
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPUSERNOTIFICATIONEVENT);

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
		return LoopUserNotificationEventModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop user notification event persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopUserNotificationEventImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPUSERNOTIFICATIONEVENT = "SELECT loopUserNotificationEvent FROM LoopUserNotificationEvent loopUserNotificationEvent";
	private static final String _SQL_SELECT_LOOPUSERNOTIFICATIONEVENT_WHERE_PKS_IN =
		"SELECT loopUserNotificationEvent FROM LoopUserNotificationEvent loopUserNotificationEvent WHERE loopUserNotificationEventId IN (";
	private static final String _SQL_SELECT_LOOPUSERNOTIFICATIONEVENT_WHERE = "SELECT loopUserNotificationEvent FROM LoopUserNotificationEvent loopUserNotificationEvent WHERE ";
	private static final String _SQL_COUNT_LOOPUSERNOTIFICATIONEVENT = "SELECT COUNT(loopUserNotificationEvent) FROM LoopUserNotificationEvent loopUserNotificationEvent";
	private static final String _SQL_COUNT_LOOPUSERNOTIFICATIONEVENT_WHERE = "SELECT COUNT(loopUserNotificationEvent) FROM LoopUserNotificationEvent loopUserNotificationEvent WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopUserNotificationEvent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopUserNotificationEvent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LoopUserNotificationEvent exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LoopUserNotificationEventPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
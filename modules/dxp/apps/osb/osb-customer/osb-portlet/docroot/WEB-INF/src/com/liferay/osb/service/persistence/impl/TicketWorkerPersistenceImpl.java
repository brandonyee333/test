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

import com.liferay.osb.exception.NoSuchTicketWorkerException;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.model.impl.TicketWorkerImpl;
import com.liferay.osb.model.impl.TicketWorkerModelImpl;
import com.liferay.osb.service.persistence.TicketWorkerPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
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
 * The persistence implementation for the ticket worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketWorkerPersistence
 * @see com.liferay.osb.service.persistence.TicketWorkerUtil
 * @generated
 */
@ProviderType
public class TicketWorkerPersistenceImpl extends BasePersistenceImpl<TicketWorker>
	implements TicketWorkerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketWorkerUtil} to access the ticket worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketWorkerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			TicketWorkerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the ticket workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching ticket workers
	 */
	@Override
	public List<TicketWorker> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @return the range of matching ticket workers
	 */
	@Override
	public List<TicketWorker> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket workers
	 */
	@Override
	public List<TicketWorker> findByUserId(long userId, int start, int end,
		OrderByComparator<TicketWorker> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket workers
	 */
	@Override
	public List<TicketWorker> findByUserId(long userId, int start, int end,
		OrderByComparator<TicketWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<TicketWorker> list = null;

		if (retrieveFromCache) {
			list = (List<TicketWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketWorker ticketWorker : list) {
					if ((userId != ticketWorker.getUserId())) {
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

			query.append(_SQL_SELECT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker
	 * @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker findByUserId_First(long userId,
		OrderByComparator<TicketWorker> orderByComparator)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = fetchByUserId_First(userId,
				orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the first ticket worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker fetchByUserId_First(long userId,
		OrderByComparator<TicketWorker> orderByComparator) {
		List<TicketWorker> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker
	 * @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker findByUserId_Last(long userId,
		OrderByComparator<TicketWorker> orderByComparator)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = fetchByUserId_Last(userId, orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the last ticket worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker fetchByUserId_Last(long userId,
		OrderByComparator<TicketWorker> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<TicketWorker> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket workers before and after the current ticket worker in the ordered set where userId = &#63;.
	 *
	 * @param ticketWorkerId the primary key of the current ticket worker
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket worker
	 * @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 */
	@Override
	public TicketWorker[] findByUserId_PrevAndNext(long ticketWorkerId,
		long userId, OrderByComparator<TicketWorker> orderByComparator)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = findByPrimaryKey(ticketWorkerId);

		Session session = null;

		try {
			session = openSession();

			TicketWorker[] array = new TicketWorkerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, ticketWorker, userId,
					orderByComparator, true);

			array[1] = ticketWorker;

			array[2] = getByUserId_PrevAndNext(session, ticketWorker, userId,
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

	protected TicketWorker getByUserId_PrevAndNext(Session session,
		TicketWorker ticketWorker, long userId,
		OrderByComparator<TicketWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETWORKER_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(TicketWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket workers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (TicketWorker ticketWorker : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketWorker);
		}
	}

	/**
	 * Returns the number of ticket workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching ticket workers
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "ticketWorker.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTicketEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTicketEntryId",
			new String[] { Long.class.getName() },
			TicketWorkerModelImpl.TICKETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TICKETENTRYID = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTicketEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the ticket workers where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket workers
	 */
	@Override
	public List<TicketWorker> findByTicketEntryId(long ticketEntryId) {
		return findByTicketEntryId(ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket workers where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @return the range of matching ticket workers
	 */
	@Override
	public List<TicketWorker> findByTicketEntryId(long ticketEntryId,
		int start, int end) {
		return findByTicketEntryId(ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket workers where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket workers
	 */
	@Override
	public List<TicketWorker> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketWorker> orderByComparator) {
		return findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket workers where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket workers
	 */
	@Override
	public List<TicketWorker> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID;
			finderArgs = new Object[] { ticketEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID;
			finderArgs = new Object[] {
					ticketEntryId,
					
					start, end, orderByComparator
				};
		}

		List<TicketWorker> list = null;

		if (retrieveFromCache) {
			list = (List<TicketWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketWorker ticketWorker : list) {
					if ((ticketEntryId != ticketWorker.getTicketEntryId())) {
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

			query.append(_SQL_SELECT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (!pagination) {
					list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket worker in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker
	 * @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketWorker> orderByComparator)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = fetchByTicketEntryId_First(ticketEntryId,
				orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the first ticket worker in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketWorker> orderByComparator) {
		List<TicketWorker> list = findByTicketEntryId(ticketEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket worker in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker
	 * @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketWorker> orderByComparator)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = fetchByTicketEntryId_Last(ticketEntryId,
				orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the last ticket worker in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketWorker> orderByComparator) {
		int count = countByTicketEntryId(ticketEntryId);

		if (count == 0) {
			return null;
		}

		List<TicketWorker> list = findByTicketEntryId(ticketEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket workers before and after the current ticket worker in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketWorkerId the primary key of the current ticket worker
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket worker
	 * @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 */
	@Override
	public TicketWorker[] findByTicketEntryId_PrevAndNext(long ticketWorkerId,
		long ticketEntryId, OrderByComparator<TicketWorker> orderByComparator)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = findByPrimaryKey(ticketWorkerId);

		Session session = null;

		try {
			session = openSession();

			TicketWorker[] array = new TicketWorkerImpl[3];

			array[0] = getByTicketEntryId_PrevAndNext(session, ticketWorker,
					ticketEntryId, orderByComparator, true);

			array[1] = ticketWorker;

			array[2] = getByTicketEntryId_PrevAndNext(session, ticketWorker,
					ticketEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketWorker getByTicketEntryId_PrevAndNext(Session session,
		TicketWorker ticketWorker, long ticketEntryId,
		OrderByComparator<TicketWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETWORKER_WHERE);

		query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

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
			query.append(TicketWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket workers where ticketEntryId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 */
	@Override
	public void removeByTicketEntryId(long ticketEntryId) {
		for (TicketWorker ticketWorker : findByTicketEntryId(ticketEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketWorker);
		}
	}

	/**
	 * Returns the number of ticket workers where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket workers
	 */
	@Override
	public int countByTicketEntryId(long ticketEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TICKETENTRYID;

		Object[] finderArgs = new Object[] { ticketEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

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

	private static final String _FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2 = "ticketWorker.ticketEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_TEI = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_TEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			TicketWorkerModelImpl.USERID_COLUMN_BITMASK |
			TicketWorkerModelImpl.TICKETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_TEI = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_TEI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or throws a {@link NoSuchTicketWorkerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket worker
	 * @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker findByU_TEI(long userId, long ticketEntryId)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = fetchByU_TEI(userId, ticketEntryId);

		if (ticketWorker == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", ticketEntryId=");
			msg.append(ticketEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTicketWorkerException(msg.toString());
		}

		return ticketWorker;
	}

	/**
	 * Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker fetchByU_TEI(long userId, long ticketEntryId) {
		return fetchByU_TEI(userId, ticketEntryId, true);
	}

	/**
	 * Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker fetchByU_TEI(long userId, long ticketEntryId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, ticketEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_TEI,
					finderArgs, this);
		}

		if (result instanceof TicketWorker) {
			TicketWorker ticketWorker = (TicketWorker)result;

			if ((userId != ticketWorker.getUserId()) ||
					(ticketEntryId != ticketWorker.getTicketEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_TICKETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				List<TicketWorker> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_TEI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"TicketWorkerPersistenceImpl.fetchByU_TEI(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					TicketWorker ticketWorker = list.get(0);

					result = ticketWorker;

					cacheResult(ticketWorker);

					if ((ticketWorker.getUserId() != userId) ||
							(ticketWorker.getTicketEntryId() != ticketEntryId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_TEI,
							finderArgs, ticketWorker);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_TEI, finderArgs);

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
			return (TicketWorker)result;
		}
	}

	/**
	 * Removes the ticket worker where userId = &#63; and ticketEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @return the ticket worker that was removed
	 */
	@Override
	public TicketWorker removeByU_TEI(long userId, long ticketEntryId)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = findByU_TEI(userId, ticketEntryId);

		return remove(ticketWorker);
	}

	/**
	 * Returns the number of ticket workers where userId = &#63; and ticketEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket workers
	 */
	@Override
	public int countByU_TEI(long userId, long ticketEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_TEI;

		Object[] finderArgs = new Object[] { userId, ticketEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_TICKETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

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

	private static final String _FINDER_COLUMN_U_TEI_USERID_2 = "ticketWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_TICKETENTRYID_2 = "ticketWorker.ticketEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_TEI_P = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByTEI_P",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			TicketWorkerModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketWorkerModelImpl.PRIMARY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_P = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_P",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or throws a {@link NoSuchTicketWorkerException} if it could not be found.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param primary the primary
	 * @return the matching ticket worker
	 * @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker findByTEI_P(long ticketEntryId, boolean primary)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = fetchByTEI_P(ticketEntryId, primary);

		if (ticketWorker == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ticketEntryId=");
			msg.append(ticketEntryId);

			msg.append(", primary=");
			msg.append(primary);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTicketWorkerException(msg.toString());
		}

		return ticketWorker;
	}

	/**
	 * Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param primary the primary
	 * @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker fetchByTEI_P(long ticketEntryId, boolean primary) {
		return fetchByTEI_P(ticketEntryId, primary, true);
	}

	/**
	 * Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param primary the primary
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker fetchByTEI_P(long ticketEntryId, boolean primary,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { ticketEntryId, primary };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_TEI_P,
					finderArgs, this);
		}

		if (result instanceof TicketWorker) {
			TicketWorker ticketWorker = (TicketWorker)result;

			if ((ticketEntryId != ticketWorker.getTicketEntryId()) ||
					(primary != ticketWorker.getPrimary())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_TEI_P_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_P_PRIMARY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(primary);

				List<TicketWorker> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_TEI_P,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"TicketWorkerPersistenceImpl.fetchByTEI_P(long, boolean, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					TicketWorker ticketWorker = list.get(0);

					result = ticketWorker;

					cacheResult(ticketWorker);

					if ((ticketWorker.getTicketEntryId() != ticketEntryId) ||
							(ticketWorker.getPrimary() != primary)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_TEI_P,
							finderArgs, ticketWorker);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_TEI_P, finderArgs);

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
			return (TicketWorker)result;
		}
	}

	/**
	 * Removes the ticket worker where ticketEntryId = &#63; and primary = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param primary the primary
	 * @return the ticket worker that was removed
	 */
	@Override
	public TicketWorker removeByTEI_P(long ticketEntryId, boolean primary)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = findByTEI_P(ticketEntryId, primary);

		return remove(ticketWorker);
	}

	/**
	 * Returns the number of ticket workers where ticketEntryId = &#63; and primary = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param primary the primary
	 * @return the number of matching ticket workers
	 */
	@Override
	public int countByTEI_P(long ticketEntryId, boolean primary) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEI_P;

		Object[] finderArgs = new Object[] { ticketEntryId, primary };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_TEI_P_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_P_PRIMARY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(primary);

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

	private static final String _FINDER_COLUMN_TEI_P_TICKETENTRYID_2 = "ticketWorker.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_P_PRIMARY_2 = "ticketWorker.primary = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCNI_SCPK =
		new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySCNI_SCPK",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCNI_SCPK =
		new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySCNI_SCPK",
			new String[] { Long.class.getName(), Long.class.getName() },
			TicketWorkerModelImpl.SOURCECLASSNAMEID_COLUMN_BITMASK |
			TicketWorkerModelImpl.SOURCECLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCNI_SCPK = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySCNI_SCPK",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @return the matching ticket workers
	 */
	@Override
	public List<TicketWorker> findBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK) {
		return findBySCNI_SCPK(sourceClassNameId, sourceClassPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @return the range of matching ticket workers
	 */
	@Override
	public List<TicketWorker> findBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK, int start, int end) {
		return findBySCNI_SCPK(sourceClassNameId, sourceClassPK, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket workers
	 */
	@Override
	public List<TicketWorker> findBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK, int start, int end,
		OrderByComparator<TicketWorker> orderByComparator) {
		return findBySCNI_SCPK(sourceClassNameId, sourceClassPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket workers
	 */
	@Override
	public List<TicketWorker> findBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK, int start, int end,
		OrderByComparator<TicketWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCNI_SCPK;
			finderArgs = new Object[] { sourceClassNameId, sourceClassPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCNI_SCPK;
			finderArgs = new Object[] {
					sourceClassNameId, sourceClassPK,
					
					start, end, orderByComparator
				};
		}

		List<TicketWorker> list = null;

		if (retrieveFromCache) {
			list = (List<TicketWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketWorker ticketWorker : list) {
					if ((sourceClassNameId != ticketWorker.getSourceClassNameId()) ||
							(sourceClassPK != ticketWorker.getSourceClassPK())) {
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

			query.append(_SQL_SELECT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSNAMEID_2);

			query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sourceClassNameId);

				qPos.add(sourceClassPK);

				if (!pagination) {
					list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker
	 * @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker findBySCNI_SCPK_First(long sourceClassNameId,
		long sourceClassPK, OrderByComparator<TicketWorker> orderByComparator)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = fetchBySCNI_SCPK_First(sourceClassNameId,
				sourceClassPK, orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sourceClassNameId=");
		msg.append(sourceClassNameId);

		msg.append(", sourceClassPK=");
		msg.append(sourceClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the first ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker fetchBySCNI_SCPK_First(long sourceClassNameId,
		long sourceClassPK, OrderByComparator<TicketWorker> orderByComparator) {
		List<TicketWorker> list = findBySCNI_SCPK(sourceClassNameId,
				sourceClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker
	 * @throws NoSuchTicketWorkerException if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker findBySCNI_SCPK_Last(long sourceClassNameId,
		long sourceClassPK, OrderByComparator<TicketWorker> orderByComparator)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = fetchBySCNI_SCPK_Last(sourceClassNameId,
				sourceClassPK, orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sourceClassNameId=");
		msg.append(sourceClassNameId);

		msg.append(", sourceClassPK=");
		msg.append(sourceClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the last ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 */
	@Override
	public TicketWorker fetchBySCNI_SCPK_Last(long sourceClassNameId,
		long sourceClassPK, OrderByComparator<TicketWorker> orderByComparator) {
		int count = countBySCNI_SCPK(sourceClassNameId, sourceClassPK);

		if (count == 0) {
			return null;
		}

		List<TicketWorker> list = findBySCNI_SCPK(sourceClassNameId,
				sourceClassPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket workers before and after the current ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param ticketWorkerId the primary key of the current ticket worker
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket worker
	 * @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 */
	@Override
	public TicketWorker[] findBySCNI_SCPK_PrevAndNext(long ticketWorkerId,
		long sourceClassNameId, long sourceClassPK,
		OrderByComparator<TicketWorker> orderByComparator)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = findByPrimaryKey(ticketWorkerId);

		Session session = null;

		try {
			session = openSession();

			TicketWorker[] array = new TicketWorkerImpl[3];

			array[0] = getBySCNI_SCPK_PrevAndNext(session, ticketWorker,
					sourceClassNameId, sourceClassPK, orderByComparator, true);

			array[1] = ticketWorker;

			array[2] = getBySCNI_SCPK_PrevAndNext(session, ticketWorker,
					sourceClassNameId, sourceClassPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketWorker getBySCNI_SCPK_PrevAndNext(Session session,
		TicketWorker ticketWorker, long sourceClassNameId, long sourceClassPK,
		OrderByComparator<TicketWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TICKETWORKER_WHERE);

		query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSNAMEID_2);

		query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSPK_2);

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
			query.append(TicketWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(sourceClassNameId);

		qPos.add(sourceClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63; from the database.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 */
	@Override
	public void removeBySCNI_SCPK(long sourceClassNameId, long sourceClassPK) {
		for (TicketWorker ticketWorker : findBySCNI_SCPK(sourceClassNameId,
				sourceClassPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketWorker);
		}
	}

	/**
	 * Returns the number of ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @return the number of matching ticket workers
	 */
	@Override
	public int countBySCNI_SCPK(long sourceClassNameId, long sourceClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCNI_SCPK;

		Object[] finderArgs = new Object[] { sourceClassNameId, sourceClassPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSNAMEID_2);

			query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sourceClassNameId);

				qPos.add(sourceClassPK);

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

	private static final String _FINDER_COLUMN_SCNI_SCPK_SOURCECLASSNAMEID_2 = "ticketWorker.sourceClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_SCNI_SCPK_SOURCECLASSPK_2 = "ticketWorker.sourceClassPK = ?";

	public TicketWorkerPersistenceImpl() {
		setModelClass(TicketWorker.class);
	}

	/**
	 * Caches the ticket worker in the entity cache if it is enabled.
	 *
	 * @param ticketWorker the ticket worker
	 */
	@Override
	public void cacheResult(TicketWorker ticketWorker) {
		entityCache.putResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerImpl.class, ticketWorker.getPrimaryKey(), ticketWorker);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_TEI,
			new Object[] {
				ticketWorker.getUserId(), ticketWorker.getTicketEntryId()
			}, ticketWorker);

		finderCache.putResult(FINDER_PATH_FETCH_BY_TEI_P,
			new Object[] {
				ticketWorker.getTicketEntryId(), ticketWorker.getPrimary()
			}, ticketWorker);

		ticketWorker.resetOriginalValues();
	}

	/**
	 * Caches the ticket workers in the entity cache if it is enabled.
	 *
	 * @param ticketWorkers the ticket workers
	 */
	@Override
	public void cacheResult(List<TicketWorker> ticketWorkers) {
		for (TicketWorker ticketWorker : ticketWorkers) {
			if (entityCache.getResult(
						TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
						TicketWorkerImpl.class, ticketWorker.getPrimaryKey()) == null) {
				cacheResult(ticketWorker);
			}
			else {
				ticketWorker.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket workers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TicketWorkerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket worker.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketWorker ticketWorker) {
		entityCache.removeResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerImpl.class, ticketWorker.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TicketWorkerModelImpl)ticketWorker, true);
	}

	@Override
	public void clearCache(List<TicketWorker> ticketWorkers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketWorker ticketWorker : ticketWorkers) {
			entityCache.removeResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
				TicketWorkerImpl.class, ticketWorker.getPrimaryKey());

			clearUniqueFindersCache((TicketWorkerModelImpl)ticketWorker, true);
		}
	}

	protected void cacheUniqueFindersCache(
		TicketWorkerModelImpl ticketWorkerModelImpl) {
		Object[] args = new Object[] {
				ticketWorkerModelImpl.getUserId(),
				ticketWorkerModelImpl.getTicketEntryId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_U_TEI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_U_TEI, args,
			ticketWorkerModelImpl, false);

		args = new Object[] {
				ticketWorkerModelImpl.getTicketEntryId(),
				ticketWorkerModelImpl.getPrimary()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_TEI_P, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_TEI_P, args,
			ticketWorkerModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		TicketWorkerModelImpl ticketWorkerModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					ticketWorkerModelImpl.getUserId(),
					ticketWorkerModelImpl.getTicketEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_TEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_TEI, args);
		}

		if ((ticketWorkerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_TEI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					ticketWorkerModelImpl.getOriginalUserId(),
					ticketWorkerModelImpl.getOriginalTicketEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_TEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_TEI, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					ticketWorkerModelImpl.getTicketEntryId(),
					ticketWorkerModelImpl.getPrimary()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_P, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_TEI_P, args);
		}

		if ((ticketWorkerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TEI_P.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					ticketWorkerModelImpl.getOriginalTicketEntryId(),
					ticketWorkerModelImpl.getOriginalPrimary()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_P, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_TEI_P, args);
		}
	}

	/**
	 * Creates a new ticket worker with the primary key. Does not add the ticket worker to the database.
	 *
	 * @param ticketWorkerId the primary key for the new ticket worker
	 * @return the new ticket worker
	 */
	@Override
	public TicketWorker create(long ticketWorkerId) {
		TicketWorker ticketWorker = new TicketWorkerImpl();

		ticketWorker.setNew(true);
		ticketWorker.setPrimaryKey(ticketWorkerId);

		return ticketWorker;
	}

	/**
	 * Removes the ticket worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketWorkerId the primary key of the ticket worker
	 * @return the ticket worker that was removed
	 * @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 */
	@Override
	public TicketWorker remove(long ticketWorkerId)
		throws NoSuchTicketWorkerException {
		return remove((Serializable)ticketWorkerId);
	}

	/**
	 * Removes the ticket worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket worker
	 * @return the ticket worker that was removed
	 * @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 */
	@Override
	public TicketWorker remove(Serializable primaryKey)
		throws NoSuchTicketWorkerException {
		Session session = null;

		try {
			session = openSession();

			TicketWorker ticketWorker = (TicketWorker)session.get(TicketWorkerImpl.class,
					primaryKey);

			if (ticketWorker == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketWorker);
		}
		catch (NoSuchTicketWorkerException nsee) {
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
	protected TicketWorker removeImpl(TicketWorker ticketWorker) {
		ticketWorker = toUnwrappedModel(ticketWorker);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ticketWorker)) {
				ticketWorker = (TicketWorker)session.get(TicketWorkerImpl.class,
						ticketWorker.getPrimaryKeyObj());
			}

			if (ticketWorker != null) {
				session.delete(ticketWorker);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ticketWorker != null) {
			clearCache(ticketWorker);
		}

		return ticketWorker;
	}

	@Override
	public TicketWorker updateImpl(TicketWorker ticketWorker) {
		ticketWorker = toUnwrappedModel(ticketWorker);

		boolean isNew = ticketWorker.isNew();

		TicketWorkerModelImpl ticketWorkerModelImpl = (TicketWorkerModelImpl)ticketWorker;

		Session session = null;

		try {
			session = openSession();

			if (ticketWorker.isNew()) {
				session.save(ticketWorker);

				ticketWorker.setNew(false);
			}
			else {
				ticketWorker = (TicketWorker)session.merge(ticketWorker);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TicketWorkerModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { ticketWorkerModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
				args);

			args = new Object[] { ticketWorkerModelImpl.getTicketEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
				args);

			args = new Object[] {
					ticketWorkerModelImpl.getSourceClassNameId(),
					ticketWorkerModelImpl.getSourceClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SCNI_SCPK, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCNI_SCPK,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ticketWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketWorkerModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { ticketWorkerModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((ticketWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketWorkerModelImpl.getOriginalTicketEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);

				args = new Object[] { ticketWorkerModelImpl.getTicketEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);
			}

			if ((ticketWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCNI_SCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketWorkerModelImpl.getOriginalSourceClassNameId(),
						ticketWorkerModelImpl.getOriginalSourceClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCNI_SCPK, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCNI_SCPK,
					args);

				args = new Object[] {
						ticketWorkerModelImpl.getSourceClassNameId(),
						ticketWorkerModelImpl.getSourceClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCNI_SCPK, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCNI_SCPK,
					args);
			}
		}

		entityCache.putResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerImpl.class, ticketWorker.getPrimaryKey(), ticketWorker,
			false);

		clearUniqueFindersCache(ticketWorkerModelImpl, false);
		cacheUniqueFindersCache(ticketWorkerModelImpl);

		ticketWorker.resetOriginalValues();

		return ticketWorker;
	}

	protected TicketWorker toUnwrappedModel(TicketWorker ticketWorker) {
		if (ticketWorker instanceof TicketWorkerImpl) {
			return ticketWorker;
		}

		TicketWorkerImpl ticketWorkerImpl = new TicketWorkerImpl();

		ticketWorkerImpl.setNew(ticketWorker.isNew());
		ticketWorkerImpl.setPrimaryKey(ticketWorker.getPrimaryKey());

		ticketWorkerImpl.setTicketWorkerId(ticketWorker.getTicketWorkerId());
		ticketWorkerImpl.setUserId(ticketWorker.getUserId());
		ticketWorkerImpl.setTicketEntryId(ticketWorker.getTicketEntryId());
		ticketWorkerImpl.setSourceClassNameId(ticketWorker.getSourceClassNameId());
		ticketWorkerImpl.setSourceClassPK(ticketWorker.getSourceClassPK());
		ticketWorkerImpl.setRole(ticketWorker.getRole());
		ticketWorkerImpl.setPrimary(ticketWorker.isPrimary());

		return ticketWorkerImpl;
	}

	/**
	 * Returns the ticket worker with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket worker
	 * @return the ticket worker
	 * @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 */
	@Override
	public TicketWorker findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTicketWorkerException {
		TicketWorker ticketWorker = fetchByPrimaryKey(primaryKey);

		if (ticketWorker == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTicketWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ticketWorker;
	}

	/**
	 * Returns the ticket worker with the primary key or throws a {@link NoSuchTicketWorkerException} if it could not be found.
	 *
	 * @param ticketWorkerId the primary key of the ticket worker
	 * @return the ticket worker
	 * @throws NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 */
	@Override
	public TicketWorker findByPrimaryKey(long ticketWorkerId)
		throws NoSuchTicketWorkerException {
		return findByPrimaryKey((Serializable)ticketWorkerId);
	}

	/**
	 * Returns the ticket worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket worker
	 * @return the ticket worker, or <code>null</code> if a ticket worker with the primary key could not be found
	 */
	@Override
	public TicketWorker fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
				TicketWorkerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TicketWorker ticketWorker = (TicketWorker)serializable;

		if (ticketWorker == null) {
			Session session = null;

			try {
				session = openSession();

				ticketWorker = (TicketWorker)session.get(TicketWorkerImpl.class,
						primaryKey);

				if (ticketWorker != null) {
					cacheResult(ticketWorker);
				}
				else {
					entityCache.putResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
						TicketWorkerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
					TicketWorkerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ticketWorker;
	}

	/**
	 * Returns the ticket worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketWorkerId the primary key of the ticket worker
	 * @return the ticket worker, or <code>null</code> if a ticket worker with the primary key could not be found
	 */
	@Override
	public TicketWorker fetchByPrimaryKey(long ticketWorkerId) {
		return fetchByPrimaryKey((Serializable)ticketWorkerId);
	}

	@Override
	public Map<Serializable, TicketWorker> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TicketWorker> map = new HashMap<Serializable, TicketWorker>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TicketWorker ticketWorker = fetchByPrimaryKey(primaryKey);

			if (ticketWorker != null) {
				map.put(primaryKey, ticketWorker);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
					TicketWorkerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TicketWorker)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TICKETWORKER_WHERE_PKS_IN);

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

			for (TicketWorker ticketWorker : (List<TicketWorker>)q.list()) {
				map.put(ticketWorker.getPrimaryKeyObj(), ticketWorker);

				cacheResult(ticketWorker);

				uncachedPrimaryKeys.remove(ticketWorker.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
					TicketWorkerImpl.class, primaryKey, nullModel);
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
	 * Returns all the ticket workers.
	 *
	 * @return the ticket workers
	 */
	@Override
	public List<TicketWorker> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @return the range of ticket workers
	 */
	@Override
	public List<TicketWorker> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket workers
	 */
	@Override
	public List<TicketWorker> findAll(int start, int end,
		OrderByComparator<TicketWorker> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ticket workers
	 */
	@Override
	public List<TicketWorker> findAll(int start, int end,
		OrderByComparator<TicketWorker> orderByComparator,
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

		List<TicketWorker> list = null;

		if (retrieveFromCache) {
			list = (List<TicketWorker>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TICKETWORKER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETWORKER;

				if (pagination) {
					sql = sql.concat(TicketWorkerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ticket workers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TicketWorker ticketWorker : findAll()) {
			remove(ticketWorker);
		}
	}

	/**
	 * Returns the number of ticket workers.
	 *
	 * @return the number of ticket workers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETWORKER);

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
		return TicketWorkerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ticket worker persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TicketWorkerImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_TICKETWORKER = "SELECT ticketWorker FROM TicketWorker ticketWorker";
	private static final String _SQL_SELECT_TICKETWORKER_WHERE_PKS_IN = "SELECT ticketWorker FROM TicketWorker ticketWorker WHERE ticketWorkerId IN (";
	private static final String _SQL_SELECT_TICKETWORKER_WHERE = "SELECT ticketWorker FROM TicketWorker ticketWorker WHERE ";
	private static final String _SQL_COUNT_TICKETWORKER = "SELECT COUNT(ticketWorker) FROM TicketWorker ticketWorker";
	private static final String _SQL_COUNT_TICKETWORKER_WHERE = "SELECT COUNT(ticketWorker) FROM TicketWorker ticketWorker WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketWorker.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketWorker exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketWorker exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TicketWorkerPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"primary"
			});
}
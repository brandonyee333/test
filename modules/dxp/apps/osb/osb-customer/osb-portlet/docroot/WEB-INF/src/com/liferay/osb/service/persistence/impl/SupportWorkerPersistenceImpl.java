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

import com.liferay.osb.exception.NoSuchSupportWorkerException;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.impl.SupportWorkerImpl;
import com.liferay.osb.model.impl.SupportWorkerModelImpl;
import com.liferay.osb.service.persistence.SupportWorkerPersistence;

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
 * The persistence implementation for the support worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerPersistence
 * @see com.liferay.osb.service.persistence.SupportWorkerUtil
 * @generated
 */
@ProviderType
public class SupportWorkerPersistenceImpl extends BasePersistenceImpl<SupportWorker>
	implements SupportWorkerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SupportWorkerUtil} to access the support worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SupportWorkerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId", new String[] { Long.class.getName() },
			SupportWorkerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the support workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching support workers
	 */
	@Override
	public List<SupportWorker> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @return the range of matching support workers
	 */
	@Override
	public List<SupportWorker> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support workers
	 */
	@Override
	public List<SupportWorker> findByUserId(long userId, int start, int end,
		OrderByComparator<SupportWorker> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching support workers
	 */
	@Override
	public List<SupportWorker> findByUserId(long userId, int start, int end,
		OrderByComparator<SupportWorker> orderByComparator,
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

		List<SupportWorker> list = null;

		if (retrieveFromCache) {
			list = (List<SupportWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SupportWorker supportWorker : list) {
					if ((userId != supportWorker.getUserId())) {
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

			query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SupportWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first support worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker
	 * @throws NoSuchSupportWorkerException if a matching support worker could not be found
	 */
	@Override
	public SupportWorker findByUserId_First(long userId,
		OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = fetchByUserId_First(userId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the first support worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	 */
	@Override
	public SupportWorker fetchByUserId_First(long userId,
		OrderByComparator<SupportWorker> orderByComparator) {
		List<SupportWorker> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker
	 * @throws NoSuchSupportWorkerException if a matching support worker could not be found
	 */
	@Override
	public SupportWorker findByUserId_Last(long userId,
		OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = fetchByUserId_Last(userId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the last support worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	 */
	@Override
	public SupportWorker fetchByUserId_Last(long userId,
		OrderByComparator<SupportWorker> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<SupportWorker> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support workers before and after the current support worker in the ordered set where userId = &#63;.
	 *
	 * @param supportWorkerId the primary key of the current support worker
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support worker
	 * @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 */
	@Override
	public SupportWorker[] findByUserId_PrevAndNext(long supportWorkerId,
		long userId, OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = findByPrimaryKey(supportWorkerId);

		Session session = null;

		try {
			session = openSession();

			SupportWorker[] array = new SupportWorkerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, supportWorker, userId,
					orderByComparator, true);

			array[1] = supportWorker;

			array[2] = getByUserId_PrevAndNext(session, supportWorker, userId,
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

	protected SupportWorker getByUserId_PrevAndNext(Session session,
		SupportWorker supportWorker, long userId,
		OrderByComparator<SupportWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

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
			query.append(SupportWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support workers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (SupportWorker supportWorker : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(supportWorker);
		}
	}

	/**
	 * Returns the number of support workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching support workers
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTWORKER_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "supportWorker.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTTEAMID =
		new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySupportTeamId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID =
		new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySupportTeamId", new String[] { Long.class.getName() },
			SupportWorkerModelImpl.SUPPORTTEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTTEAMID = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySupportTeamId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the support workers where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @return the matching support workers
	 */
	@Override
	public List<SupportWorker> findBySupportTeamId(long supportTeamId) {
		return findBySupportTeamId(supportTeamId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support workers where supportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportTeamId the support team ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @return the range of matching support workers
	 */
	@Override
	public List<SupportWorker> findBySupportTeamId(long supportTeamId,
		int start, int end) {
		return findBySupportTeamId(supportTeamId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support workers where supportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportTeamId the support team ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support workers
	 */
	@Override
	public List<SupportWorker> findBySupportTeamId(long supportTeamId,
		int start, int end, OrderByComparator<SupportWorker> orderByComparator) {
		return findBySupportTeamId(supportTeamId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support workers where supportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportTeamId the support team ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching support workers
	 */
	@Override
	public List<SupportWorker> findBySupportTeamId(long supportTeamId,
		int start, int end, OrderByComparator<SupportWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID;
			finderArgs = new Object[] { supportTeamId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTTEAMID;
			finderArgs = new Object[] {
					supportTeamId,
					
					start, end, orderByComparator
				};
		}

		List<SupportWorker> list = null;

		if (retrieveFromCache) {
			list = (List<SupportWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SupportWorker supportWorker : list) {
					if ((supportTeamId != supportWorker.getSupportTeamId())) {
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

			query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SupportWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportTeamId);

				if (!pagination) {
					list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first support worker in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker
	 * @throws NoSuchSupportWorkerException if a matching support worker could not be found
	 */
	@Override
	public SupportWorker findBySupportTeamId_First(long supportTeamId,
		OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = fetchBySupportTeamId_First(supportTeamId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportTeamId=");
		msg.append(supportTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the first support worker in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	 */
	@Override
	public SupportWorker fetchBySupportTeamId_First(long supportTeamId,
		OrderByComparator<SupportWorker> orderByComparator) {
		List<SupportWorker> list = findBySupportTeamId(supportTeamId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support worker in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker
	 * @throws NoSuchSupportWorkerException if a matching support worker could not be found
	 */
	@Override
	public SupportWorker findBySupportTeamId_Last(long supportTeamId,
		OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = fetchBySupportTeamId_Last(supportTeamId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportTeamId=");
		msg.append(supportTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the last support worker in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	 */
	@Override
	public SupportWorker fetchBySupportTeamId_Last(long supportTeamId,
		OrderByComparator<SupportWorker> orderByComparator) {
		int count = countBySupportTeamId(supportTeamId);

		if (count == 0) {
			return null;
		}

		List<SupportWorker> list = findBySupportTeamId(supportTeamId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support workers before and after the current support worker in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportWorkerId the primary key of the current support worker
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support worker
	 * @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 */
	@Override
	public SupportWorker[] findBySupportTeamId_PrevAndNext(
		long supportWorkerId, long supportTeamId,
		OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = findByPrimaryKey(supportWorkerId);

		Session session = null;

		try {
			session = openSession();

			SupportWorker[] array = new SupportWorkerImpl[3];

			array[0] = getBySupportTeamId_PrevAndNext(session, supportWorker,
					supportTeamId, orderByComparator, true);

			array[1] = supportWorker;

			array[2] = getBySupportTeamId_PrevAndNext(session, supportWorker,
					supportTeamId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportWorker getBySupportTeamId_PrevAndNext(Session session,
		SupportWorker supportWorker, long supportTeamId,
		OrderByComparator<SupportWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2);

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
			query.append(SupportWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support workers where supportTeamId = &#63; from the database.
	 *
	 * @param supportTeamId the support team ID
	 */
	@Override
	public void removeBySupportTeamId(long supportTeamId) {
		for (SupportWorker supportWorker : findBySupportTeamId(supportTeamId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(supportWorker);
		}
	}

	/**
	 * Returns the number of support workers where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @return the number of matching support workers
	 */
	@Override
	public int countBySupportTeamId(long supportTeamId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUPPORTTEAMID;

		Object[] finderArgs = new Object[] { supportTeamId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportTeamId);

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

	private static final String _FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2 = "supportWorker.supportTeamId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLABORID =
		new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySupportLaborId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID =
		new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySupportLaborId", new String[] { Long.class.getName() },
			SupportWorkerModelImpl.SUPPORTLABORID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTLABORID = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySupportLaborId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the support workers where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @return the matching support workers
	 */
	@Override
	public List<SupportWorker> findBySupportLaborId(long supportLaborId) {
		return findBySupportLaborId(supportLaborId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support workers where supportLaborId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportLaborId the support labor ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @return the range of matching support workers
	 */
	@Override
	public List<SupportWorker> findBySupportLaborId(long supportLaborId,
		int start, int end) {
		return findBySupportLaborId(supportLaborId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support workers where supportLaborId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportLaborId the support labor ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support workers
	 */
	@Override
	public List<SupportWorker> findBySupportLaborId(long supportLaborId,
		int start, int end, OrderByComparator<SupportWorker> orderByComparator) {
		return findBySupportLaborId(supportLaborId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support workers where supportLaborId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportLaborId the support labor ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching support workers
	 */
	@Override
	public List<SupportWorker> findBySupportLaborId(long supportLaborId,
		int start, int end, OrderByComparator<SupportWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID;
			finderArgs = new Object[] { supportLaborId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLABORID;
			finderArgs = new Object[] {
					supportLaborId,
					
					start, end, orderByComparator
				};
		}

		List<SupportWorker> list = null;

		if (retrieveFromCache) {
			list = (List<SupportWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SupportWorker supportWorker : list) {
					if ((supportLaborId != supportWorker.getSupportLaborId())) {
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

			query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SupportWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportLaborId);

				if (!pagination) {
					list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first support worker in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker
	 * @throws NoSuchSupportWorkerException if a matching support worker could not be found
	 */
	@Override
	public SupportWorker findBySupportLaborId_First(long supportLaborId,
		OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = fetchBySupportLaborId_First(supportLaborId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLaborId=");
		msg.append(supportLaborId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the first support worker in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	 */
	@Override
	public SupportWorker fetchBySupportLaborId_First(long supportLaborId,
		OrderByComparator<SupportWorker> orderByComparator) {
		List<SupportWorker> list = findBySupportLaborId(supportLaborId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support worker in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker
	 * @throws NoSuchSupportWorkerException if a matching support worker could not be found
	 */
	@Override
	public SupportWorker findBySupportLaborId_Last(long supportLaborId,
		OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = fetchBySupportLaborId_Last(supportLaborId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLaborId=");
		msg.append(supportLaborId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the last support worker in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	 */
	@Override
	public SupportWorker fetchBySupportLaborId_Last(long supportLaborId,
		OrderByComparator<SupportWorker> orderByComparator) {
		int count = countBySupportLaborId(supportLaborId);

		if (count == 0) {
			return null;
		}

		List<SupportWorker> list = findBySupportLaborId(supportLaborId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support workers before and after the current support worker in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportWorkerId the primary key of the current support worker
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support worker
	 * @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 */
	@Override
	public SupportWorker[] findBySupportLaborId_PrevAndNext(
		long supportWorkerId, long supportLaborId,
		OrderByComparator<SupportWorker> orderByComparator)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = findByPrimaryKey(supportWorkerId);

		Session session = null;

		try {
			session = openSession();

			SupportWorker[] array = new SupportWorkerImpl[3];

			array[0] = getBySupportLaborId_PrevAndNext(session, supportWorker,
					supportLaborId, orderByComparator, true);

			array[1] = supportWorker;

			array[2] = getBySupportLaborId_PrevAndNext(session, supportWorker,
					supportLaborId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportWorker getBySupportLaborId_PrevAndNext(Session session,
		SupportWorker supportWorker, long supportLaborId,
		OrderByComparator<SupportWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

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
			query.append(SupportWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportLaborId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support workers where supportLaborId = &#63; from the database.
	 *
	 * @param supportLaborId the support labor ID
	 */
	@Override
	public void removeBySupportLaborId(long supportLaborId) {
		for (SupportWorker supportWorker : findBySupportLaborId(
				supportLaborId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(supportWorker);
		}
	}

	/**
	 * Returns the number of support workers where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @return the number of matching support workers
	 */
	@Override
	public int countBySupportLaborId(long supportLaborId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUPPORTLABORID;

		Object[] finderArgs = new Object[] { supportLaborId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportLaborId);

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

	private static final String _FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2 = "supportWorker.supportLaborId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_STI = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByU_STI",
			new String[] { Long.class.getName(), Long.class.getName() },
			SupportWorkerModelImpl.USERID_COLUMN_BITMASK |
			SupportWorkerModelImpl.SUPPORTTEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_STI = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_STI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the support worker where userId = &#63; and supportTeamId = &#63; or throws a {@link NoSuchSupportWorkerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param supportTeamId the support team ID
	 * @return the matching support worker
	 * @throws NoSuchSupportWorkerException if a matching support worker could not be found
	 */
	@Override
	public SupportWorker findByU_STI(long userId, long supportTeamId)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = fetchByU_STI(userId, supportTeamId);

		if (supportWorker == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", supportTeamId=");
			msg.append(supportTeamId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSupportWorkerException(msg.toString());
		}

		return supportWorker;
	}

	/**
	 * Returns the support worker where userId = &#63; and supportTeamId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param supportTeamId the support team ID
	 * @return the matching support worker, or <code>null</code> if a matching support worker could not be found
	 */
	@Override
	public SupportWorker fetchByU_STI(long userId, long supportTeamId) {
		return fetchByU_STI(userId, supportTeamId, true);
	}

	/**
	 * Returns the support worker where userId = &#63; and supportTeamId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param supportTeamId the support team ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching support worker, or <code>null</code> if a matching support worker could not be found
	 */
	@Override
	public SupportWorker fetchByU_STI(long userId, long supportTeamId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, supportTeamId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_STI,
					finderArgs, this);
		}

		if (result instanceof SupportWorker) {
			SupportWorker supportWorker = (SupportWorker)result;

			if ((userId != supportWorker.getUserId()) ||
					(supportTeamId != supportWorker.getSupportTeamId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_STI_USERID_2);

			query.append(_FINDER_COLUMN_U_STI_SUPPORTTEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(supportTeamId);

				List<SupportWorker> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_STI,
						finderArgs, list);
				}
				else {
					SupportWorker supportWorker = list.get(0);

					result = supportWorker;

					cacheResult(supportWorker);

					if ((supportWorker.getUserId() != userId) ||
							(supportWorker.getSupportTeamId() != supportTeamId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_STI,
							finderArgs, supportWorker);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_STI, finderArgs);

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
			return (SupportWorker)result;
		}
	}

	/**
	 * Removes the support worker where userId = &#63; and supportTeamId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param supportTeamId the support team ID
	 * @return the support worker that was removed
	 */
	@Override
	public SupportWorker removeByU_STI(long userId, long supportTeamId)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = findByU_STI(userId, supportTeamId);

		return remove(supportWorker);
	}

	/**
	 * Returns the number of support workers where userId = &#63; and supportTeamId = &#63;.
	 *
	 * @param userId the user ID
	 * @param supportTeamId the support team ID
	 * @return the number of matching support workers
	 */
	@Override
	public int countByU_STI(long userId, long supportTeamId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_STI;

		Object[] finderArgs = new Object[] { userId, supportTeamId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_STI_USERID_2);

			query.append(_FINDER_COLUMN_U_STI_SUPPORTTEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(supportTeamId);

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

	private static final String _FINDER_COLUMN_U_STI_USERID_2 = "supportWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_STI_SUPPORTTEAMID_2 = "supportWorker.supportTeamId = ?";

	public SupportWorkerPersistenceImpl() {
		setModelClass(SupportWorker.class);
	}

	/**
	 * Caches the support worker in the entity cache if it is enabled.
	 *
	 * @param supportWorker the support worker
	 */
	@Override
	public void cacheResult(SupportWorker supportWorker) {
		entityCache.putResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerImpl.class, supportWorker.getPrimaryKey(),
			supportWorker);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_STI,
			new Object[] {
				supportWorker.getUserId(), supportWorker.getSupportTeamId()
			}, supportWorker);

		supportWorker.resetOriginalValues();
	}

	/**
	 * Caches the support workers in the entity cache if it is enabled.
	 *
	 * @param supportWorkers the support workers
	 */
	@Override
	public void cacheResult(List<SupportWorker> supportWorkers) {
		for (SupportWorker supportWorker : supportWorkers) {
			if (entityCache.getResult(
						SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
						SupportWorkerImpl.class, supportWorker.getPrimaryKey()) == null) {
				cacheResult(supportWorker);
			}
			else {
				supportWorker.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support workers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SupportWorkerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support worker.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportWorker supportWorker) {
		entityCache.removeResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerImpl.class, supportWorker.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SupportWorkerModelImpl)supportWorker, true);
	}

	@Override
	public void clearCache(List<SupportWorker> supportWorkers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportWorker supportWorker : supportWorkers) {
			entityCache.removeResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
				SupportWorkerImpl.class, supportWorker.getPrimaryKey());

			clearUniqueFindersCache((SupportWorkerModelImpl)supportWorker, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SupportWorkerModelImpl supportWorkerModelImpl) {
		Object[] args = new Object[] {
				supportWorkerModelImpl.getUserId(),
				supportWorkerModelImpl.getSupportTeamId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_U_STI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_U_STI, args,
			supportWorkerModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SupportWorkerModelImpl supportWorkerModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					supportWorkerModelImpl.getUserId(),
					supportWorkerModelImpl.getSupportTeamId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_STI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_STI, args);
		}

		if ((supportWorkerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_STI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					supportWorkerModelImpl.getOriginalUserId(),
					supportWorkerModelImpl.getOriginalSupportTeamId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_STI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_STI, args);
		}
	}

	/**
	 * Creates a new support worker with the primary key. Does not add the support worker to the database.
	 *
	 * @param supportWorkerId the primary key for the new support worker
	 * @return the new support worker
	 */
	@Override
	public SupportWorker create(long supportWorkerId) {
		SupportWorker supportWorker = new SupportWorkerImpl();

		supportWorker.setNew(true);
		supportWorker.setPrimaryKey(supportWorkerId);

		return supportWorker;
	}

	/**
	 * Removes the support worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportWorkerId the primary key of the support worker
	 * @return the support worker that was removed
	 * @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 */
	@Override
	public SupportWorker remove(long supportWorkerId)
		throws NoSuchSupportWorkerException {
		return remove((Serializable)supportWorkerId);
	}

	/**
	 * Removes the support worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support worker
	 * @return the support worker that was removed
	 * @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 */
	@Override
	public SupportWorker remove(Serializable primaryKey)
		throws NoSuchSupportWorkerException {
		Session session = null;

		try {
			session = openSession();

			SupportWorker supportWorker = (SupportWorker)session.get(SupportWorkerImpl.class,
					primaryKey);

			if (supportWorker == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(supportWorker);
		}
		catch (NoSuchSupportWorkerException nsee) {
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
	protected SupportWorker removeImpl(SupportWorker supportWorker) {
		supportWorker = toUnwrappedModel(supportWorker);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(supportWorker)) {
				supportWorker = (SupportWorker)session.get(SupportWorkerImpl.class,
						supportWorker.getPrimaryKeyObj());
			}

			if (supportWorker != null) {
				session.delete(supportWorker);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (supportWorker != null) {
			clearCache(supportWorker);
		}

		return supportWorker;
	}

	@Override
	public SupportWorker updateImpl(SupportWorker supportWorker) {
		supportWorker = toUnwrappedModel(supportWorker);

		boolean isNew = supportWorker.isNew();

		SupportWorkerModelImpl supportWorkerModelImpl = (SupportWorkerModelImpl)supportWorker;

		Session session = null;

		try {
			session = openSession();

			if (supportWorker.isNew()) {
				session.save(supportWorker);

				supportWorker.setNew(false);
			}
			else {
				supportWorker = (SupportWorker)session.merge(supportWorker);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SupportWorkerModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { supportWorkerModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
				args);

			args = new Object[] { supportWorkerModelImpl.getSupportTeamId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID,
				args);

			args = new Object[] { supportWorkerModelImpl.getSupportLaborId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((supportWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						supportWorkerModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { supportWorkerModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((supportWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						supportWorkerModelImpl.getOriginalSupportTeamId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID,
					args);

				args = new Object[] { supportWorkerModelImpl.getSupportTeamId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID,
					args);
			}

			if ((supportWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						supportWorkerModelImpl.getOriginalSupportLaborId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID,
					args);

				args = new Object[] { supportWorkerModelImpl.getSupportLaborId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID,
					args);
			}
		}

		entityCache.putResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerImpl.class, supportWorker.getPrimaryKey(),
			supportWorker, false);

		clearUniqueFindersCache(supportWorkerModelImpl, false);
		cacheUniqueFindersCache(supportWorkerModelImpl);

		supportWorker.resetOriginalValues();

		return supportWorker;
	}

	protected SupportWorker toUnwrappedModel(SupportWorker supportWorker) {
		if (supportWorker instanceof SupportWorkerImpl) {
			return supportWorker;
		}

		SupportWorkerImpl supportWorkerImpl = new SupportWorkerImpl();

		supportWorkerImpl.setNew(supportWorker.isNew());
		supportWorkerImpl.setPrimaryKey(supportWorker.getPrimaryKey());

		supportWorkerImpl.setSupportWorkerId(supportWorker.getSupportWorkerId());
		supportWorkerImpl.setUserId(supportWorker.getUserId());
		supportWorkerImpl.setSupportTeamId(supportWorker.getSupportTeamId());
		supportWorkerImpl.setSupportLaborId(supportWorker.getSupportLaborId());
		supportWorkerImpl.setAutoAssign(supportWorker.isAutoAssign());
		supportWorkerImpl.setAssignedWork(supportWorker.getAssignedWork());
		supportWorkerImpl.setMaxWork(supportWorker.getMaxWork());
		supportWorkerImpl.setEscalationLevel(supportWorker.getEscalationLevel());
		supportWorkerImpl.setRole(supportWorker.getRole());
		supportWorkerImpl.setEscalationLevel2Role(supportWorker.getEscalationLevel2Role());
		supportWorkerImpl.setNotifications(supportWorker.getNotifications());
		supportWorkerImpl.setClockedIn(supportWorker.isClockedIn());

		return supportWorkerImpl;
	}

	/**
	 * Returns the support worker with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker
	 * @return the support worker
	 * @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 */
	@Override
	public SupportWorker findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSupportWorkerException {
		SupportWorker supportWorker = fetchByPrimaryKey(primaryKey);

		if (supportWorker == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSupportWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return supportWorker;
	}

	/**
	 * Returns the support worker with the primary key or throws a {@link NoSuchSupportWorkerException} if it could not be found.
	 *
	 * @param supportWorkerId the primary key of the support worker
	 * @return the support worker
	 * @throws NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 */
	@Override
	public SupportWorker findByPrimaryKey(long supportWorkerId)
		throws NoSuchSupportWorkerException {
		return findByPrimaryKey((Serializable)supportWorkerId);
	}

	/**
	 * Returns the support worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker
	 * @return the support worker, or <code>null</code> if a support worker with the primary key could not be found
	 */
	@Override
	public SupportWorker fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
				SupportWorkerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SupportWorker supportWorker = (SupportWorker)serializable;

		if (supportWorker == null) {
			Session session = null;

			try {
				session = openSession();

				supportWorker = (SupportWorker)session.get(SupportWorkerImpl.class,
						primaryKey);

				if (supportWorker != null) {
					cacheResult(supportWorker);
				}
				else {
					entityCache.putResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
						SupportWorkerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return supportWorker;
	}

	/**
	 * Returns the support worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportWorkerId the primary key of the support worker
	 * @return the support worker, or <code>null</code> if a support worker with the primary key could not be found
	 */
	@Override
	public SupportWorker fetchByPrimaryKey(long supportWorkerId) {
		return fetchByPrimaryKey((Serializable)supportWorkerId);
	}

	@Override
	public Map<Serializable, SupportWorker> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SupportWorker> map = new HashMap<Serializable, SupportWorker>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SupportWorker supportWorker = fetchByPrimaryKey(primaryKey);

			if (supportWorker != null) {
				map.put(primaryKey, supportWorker);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SupportWorker)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SUPPORTWORKER_WHERE_PKS_IN);

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

			for (SupportWorker supportWorker : (List<SupportWorker>)q.list()) {
				map.put(supportWorker.getPrimaryKeyObj(), supportWorker);

				cacheResult(supportWorker);

				uncachedPrimaryKeys.remove(supportWorker.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerImpl.class, primaryKey, nullModel);
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
	 * Returns all the support workers.
	 *
	 * @return the support workers
	 */
	@Override
	public List<SupportWorker> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @return the range of support workers
	 */
	@Override
	public List<SupportWorker> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support workers
	 */
	@Override
	public List<SupportWorker> findAll(int start, int end,
		OrderByComparator<SupportWorker> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of support workers
	 */
	@Override
	public List<SupportWorker> findAll(int start, int end,
		OrderByComparator<SupportWorker> orderByComparator,
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

		List<SupportWorker> list = null;

		if (retrieveFromCache) {
			list = (List<SupportWorker>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SUPPORTWORKER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTWORKER;

				if (pagination) {
					sql = sql.concat(SupportWorkerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the support workers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SupportWorker supportWorker : findAll()) {
			remove(supportWorker);
		}
	}

	/**
	 * Returns the number of support workers.
	 *
	 * @return the number of support workers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTWORKER);

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
		return SupportWorkerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the support worker persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SupportWorkerImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SUPPORTWORKER = "SELECT supportWorker FROM SupportWorker supportWorker";
	private static final String _SQL_SELECT_SUPPORTWORKER_WHERE_PKS_IN = "SELECT supportWorker FROM SupportWorker supportWorker WHERE supportWorkerId IN (";
	private static final String _SQL_SELECT_SUPPORTWORKER_WHERE = "SELECT supportWorker FROM SupportWorker supportWorker WHERE ";
	private static final String _SQL_COUNT_SUPPORTWORKER = "SELECT COUNT(supportWorker) FROM SupportWorker supportWorker";
	private static final String _SQL_COUNT_SUPPORTWORKER_WHERE = "SELECT COUNT(supportWorker) FROM SupportWorker supportWorker WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportWorker.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportWorker exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportWorker exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SupportWorkerPersistenceImpl.class);
}
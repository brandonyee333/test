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

import com.liferay.osb.exception.NoSuchPartnerWorkerException;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.impl.PartnerWorkerImpl;
import com.liferay.osb.model.impl.PartnerWorkerModelImpl;
import com.liferay.osb.service.persistence.PartnerWorkerPersistence;

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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the partner worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorkerPersistence
 * @see com.liferay.osb.service.persistence.PartnerWorkerUtil
 * @generated
 */
@ProviderType
public class PartnerWorkerPersistenceImpl extends BasePersistenceImpl<PartnerWorker>
	implements PartnerWorkerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PartnerWorkerUtil} to access the partner worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PartnerWorkerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId", new String[] { Long.class.getName() },
			PartnerWorkerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the partner workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByUserId(long userId, int start, int end,
		OrderByComparator<PartnerWorker> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the partner workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByUserId(long userId, int start, int end,
		OrderByComparator<PartnerWorker> orderByComparator,
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

		List<PartnerWorker> list = null;

		if (retrieveFromCache) {
			list = (List<PartnerWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PartnerWorker partnerWorker : list) {
					if ((userId != partnerWorker.getUserId())) {
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

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PartnerWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first partner worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker
	 * @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker findByUserId_First(long userId,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = fetchByUserId_First(userId,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the first partner worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker fetchByUserId_First(long userId,
		OrderByComparator<PartnerWorker> orderByComparator) {
		List<PartnerWorker> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last partner worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker
	 * @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker findByUserId_Last(long userId,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = fetchByUserId_Last(userId,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the last partner worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker fetchByUserId_Last(long userId,
		OrderByComparator<PartnerWorker> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<PartnerWorker> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the partner workers before and after the current partner worker in the ordered set where userId = &#63;.
	 *
	 * @param partnerWorkerId the primary key of the current partner worker
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next partner worker
	 * @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 */
	@Override
	public PartnerWorker[] findByUserId_PrevAndNext(long partnerWorkerId,
		long userId, OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = findByPrimaryKey(partnerWorkerId);

		Session session = null;

		try {
			session = openSession();

			PartnerWorker[] array = new PartnerWorkerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, partnerWorker, userId,
					orderByComparator, true);

			array[1] = partnerWorker;

			array[2] = getByUserId_PrevAndNext(session, partnerWorker, userId,
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

	protected PartnerWorker getByUserId_PrevAndNext(Session session,
		PartnerWorker partnerWorker, long userId,
		OrderByComparator<PartnerWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

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
			query.append(PartnerWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(partnerWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PartnerWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the partner workers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (PartnerWorker partnerWorker : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(partnerWorker);
		}
	}

	/**
	 * Returns the number of partner workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching partner workers
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "partnerWorker.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARTNERENTRYID =
		new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPartnerEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID =
		new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPartnerEntryId", new String[] { Long.class.getName() },
			PartnerWorkerModelImpl.PARTNERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARTNERENTRYID = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPartnerEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the partner workers where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @return the matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByPartnerEntryId(long partnerEntryId) {
		return findByPartnerEntryId(partnerEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner workers where partnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByPartnerEntryId(long partnerEntryId,
		int start, int end) {
		return findByPartnerEntryId(partnerEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers where partnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByPartnerEntryId(long partnerEntryId,
		int start, int end, OrderByComparator<PartnerWorker> orderByComparator) {
		return findByPartnerEntryId(partnerEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the partner workers where partnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByPartnerEntryId(long partnerEntryId,
		int start, int end, OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID;
			finderArgs = new Object[] { partnerEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARTNERENTRYID;
			finderArgs = new Object[] {
					partnerEntryId,
					
					start, end, orderByComparator
				};
		}

		List<PartnerWorker> list = null;

		if (retrieveFromCache) {
			list = (List<PartnerWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PartnerWorker partnerWorker : list) {
					if ((partnerEntryId != partnerWorker.getPartnerEntryId())) {
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

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PartnerWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(partnerEntryId);

				if (!pagination) {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first partner worker in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker
	 * @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker findByPartnerEntryId_First(long partnerEntryId,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = fetchByPartnerEntryId_First(partnerEntryId,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the first partner worker in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker fetchByPartnerEntryId_First(long partnerEntryId,
		OrderByComparator<PartnerWorker> orderByComparator) {
		List<PartnerWorker> list = findByPartnerEntryId(partnerEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last partner worker in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker
	 * @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker findByPartnerEntryId_Last(long partnerEntryId,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = fetchByPartnerEntryId_Last(partnerEntryId,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the last partner worker in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker fetchByPartnerEntryId_Last(long partnerEntryId,
		OrderByComparator<PartnerWorker> orderByComparator) {
		int count = countByPartnerEntryId(partnerEntryId);

		if (count == 0) {
			return null;
		}

		List<PartnerWorker> list = findByPartnerEntryId(partnerEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the partner workers before and after the current partner worker in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerWorkerId the primary key of the current partner worker
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next partner worker
	 * @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 */
	@Override
	public PartnerWorker[] findByPartnerEntryId_PrevAndNext(
		long partnerWorkerId, long partnerEntryId,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = findByPrimaryKey(partnerWorkerId);

		Session session = null;

		try {
			session = openSession();

			PartnerWorker[] array = new PartnerWorkerImpl[3];

			array[0] = getByPartnerEntryId_PrevAndNext(session, partnerWorker,
					partnerEntryId, orderByComparator, true);

			array[1] = partnerWorker;

			array[2] = getByPartnerEntryId_PrevAndNext(session, partnerWorker,
					partnerEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PartnerWorker getByPartnerEntryId_PrevAndNext(Session session,
		PartnerWorker partnerWorker, long partnerEntryId,
		OrderByComparator<PartnerWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

		query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

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
			query.append(PartnerWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(partnerEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(partnerWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PartnerWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the partner workers where partnerEntryId = &#63; from the database.
	 *
	 * @param partnerEntryId the partner entry ID
	 */
	@Override
	public void removeByPartnerEntryId(long partnerEntryId) {
		for (PartnerWorker partnerWorker : findByPartnerEntryId(
				partnerEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(partnerWorker);
		}
	}

	/**
	 * Returns the number of partner workers where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @return the number of matching partner workers
	 */
	@Override
	public int countByPartnerEntryId(long partnerEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PARTNERENTRYID;

		Object[] finderArgs = new Object[] { partnerEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(partnerEntryId);

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

	private static final String _FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2 = "partnerWorker.partnerEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_PEI = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByU_PEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			PartnerWorkerModelImpl.USERID_COLUMN_BITMASK |
			PartnerWorkerModelImpl.PARTNERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_PEI = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_PEI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or throws a {@link NoSuchPartnerWorkerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param partnerEntryId the partner entry ID
	 * @return the matching partner worker
	 * @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker findByU_PEI(long userId, long partnerEntryId)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = fetchByU_PEI(userId, partnerEntryId);

		if (partnerWorker == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", partnerEntryId=");
			msg.append(partnerEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPartnerWorkerException(msg.toString());
		}

		return partnerWorker;
	}

	/**
	 * Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param partnerEntryId the partner entry ID
	 * @return the matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker fetchByU_PEI(long userId, long partnerEntryId) {
		return fetchByU_PEI(userId, partnerEntryId, true);
	}

	/**
	 * Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param partnerEntryId the partner entry ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker fetchByU_PEI(long userId, long partnerEntryId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, partnerEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_PEI,
					finderArgs, this);
		}

		if (result instanceof PartnerWorker) {
			PartnerWorker partnerWorker = (PartnerWorker)result;

			if ((userId != partnerWorker.getUserId()) ||
					(partnerEntryId != partnerWorker.getPartnerEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_PEI_USERID_2);

			query.append(_FINDER_COLUMN_U_PEI_PARTNERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(partnerEntryId);

				List<PartnerWorker> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_PEI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PartnerWorkerPersistenceImpl.fetchByU_PEI(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PartnerWorker partnerWorker = list.get(0);

					result = partnerWorker;

					cacheResult(partnerWorker);

					if ((partnerWorker.getUserId() != userId) ||
							(partnerWorker.getPartnerEntryId() != partnerEntryId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_PEI,
							finderArgs, partnerWorker);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_PEI, finderArgs);

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
			return (PartnerWorker)result;
		}
	}

	/**
	 * Removes the partner worker where userId = &#63; and partnerEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param partnerEntryId the partner entry ID
	 * @return the partner worker that was removed
	 */
	@Override
	public PartnerWorker removeByU_PEI(long userId, long partnerEntryId)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = findByU_PEI(userId, partnerEntryId);

		return remove(partnerWorker);
	}

	/**
	 * Returns the number of partner workers where userId = &#63; and partnerEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param partnerEntryId the partner entry ID
	 * @return the number of matching partner workers
	 */
	@Override
	public int countByU_PEI(long userId, long partnerEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_PEI;

		Object[] finderArgs = new Object[] { userId, partnerEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_PEI_USERID_2);

			query.append(_FINDER_COLUMN_U_PEI_PARTNERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(partnerEntryId);

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

	private static final String _FINDER_COLUMN_U_PEI_USERID_2 = "partnerWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_PEI_PARTNERENTRYID_2 = "partnerWorker.partnerEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			PartnerWorkerModelImpl.USERID_COLUMN_BITMASK |
			PartnerWorkerModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the partner workers where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByU_R(long userId, int role) {
		return findByU_R(userId, role, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the partner workers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByU_R(long userId, int role, int start,
		int end) {
		return findByU_R(userId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByU_R(long userId, int role, int start,
		int end, OrderByComparator<PartnerWorker> orderByComparator) {
		return findByU_R(userId, role, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the partner workers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByU_R(long userId, int role, int start,
		int end, OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] { userId, role };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] {
					userId, role,
					
					start, end, orderByComparator
				};
		}

		List<PartnerWorker> list = null;

		if (retrieveFromCache) {
			list = (List<PartnerWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PartnerWorker partnerWorker : list) {
					if ((userId != partnerWorker.getUserId()) ||
							(role != partnerWorker.getRole())) {
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

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_ROLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PartnerWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(role);

				if (!pagination) {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first partner worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker
	 * @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker findByU_R_First(long userId, int role,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = fetchByU_R_First(userId, role,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the first partner worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker fetchByU_R_First(long userId, int role,
		OrderByComparator<PartnerWorker> orderByComparator) {
		List<PartnerWorker> list = findByU_R(userId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last partner worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker
	 * @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker findByU_R_Last(long userId, int role,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = fetchByU_R_Last(userId, role,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the last partner worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker fetchByU_R_Last(long userId, int role,
		OrderByComparator<PartnerWorker> orderByComparator) {
		int count = countByU_R(userId, role);

		if (count == 0) {
			return null;
		}

		List<PartnerWorker> list = findByU_R(userId, role, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the partner workers before and after the current partner worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param partnerWorkerId the primary key of the current partner worker
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next partner worker
	 * @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 */
	@Override
	public PartnerWorker[] findByU_R_PrevAndNext(long partnerWorkerId,
		long userId, int role,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = findByPrimaryKey(partnerWorkerId);

		Session session = null;

		try {
			session = openSession();

			PartnerWorker[] array = new PartnerWorkerImpl[3];

			array[0] = getByU_R_PrevAndNext(session, partnerWorker, userId,
					role, orderByComparator, true);

			array[1] = partnerWorker;

			array[2] = getByU_R_PrevAndNext(session, partnerWorker, userId,
					role, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PartnerWorker getByU_R_PrevAndNext(Session session,
		PartnerWorker partnerWorker, long userId, int role,
		OrderByComparator<PartnerWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

		query.append(_FINDER_COLUMN_U_R_USERID_2);

		query.append(_FINDER_COLUMN_U_R_ROLE_2);

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
			query.append(PartnerWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(partnerWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PartnerWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the partner workers where userId = any &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param roles the roles
	 * @return the matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByU_R(long[] userIds, int[] roles) {
		return findByU_R(userIds, roles, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the partner workers where userId = any &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param roles the roles
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByU_R(long[] userIds, int[] roles,
		int start, int end) {
		return findByU_R(userIds, roles, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers where userId = any &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param roles the roles
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByU_R(long[] userIds, int[] roles,
		int start, int end, OrderByComparator<PartnerWorker> orderByComparator) {
		return findByU_R(userIds, roles, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the partner workers where userId = &#63; and role = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByU_R(long[] userIds, int[] roles,
		int start, int end, OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache) {
		if (userIds == null) {
			userIds = new long[0];
		}
		else if (userIds.length > 1) {
			userIds = ArrayUtil.unique(userIds);

			Arrays.sort(userIds);
		}

		if (roles == null) {
			roles = new int[0];
		}
		else if (roles.length > 1) {
			roles = ArrayUtil.unique(roles);

			Arrays.sort(roles);
		}

		if ((userIds.length == 1) && (roles.length == 1)) {
			return findByU_R(userIds[0], roles[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(userIds), StringUtil.merge(roles)
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(userIds), StringUtil.merge(roles),
					
					start, end, orderByComparator
				};
		}

		List<PartnerWorker> list = null;

		if (retrieveFromCache) {
			list = (List<PartnerWorker>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PartnerWorker partnerWorker : list) {
					if (!ArrayUtil.contains(userIds, partnerWorker.getUserId()) ||
							!ArrayUtil.contains(roles, partnerWorker.getRole())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			if (userIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_R_USERID_7);

				query.append(StringUtil.merge(userIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (roles.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_R_ROLE_7);

				query.append(StringUtil.merge(roles));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PartnerWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the partner workers where userId = &#63; and role = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param role the role
	 */
	@Override
	public void removeByU_R(long userId, int role) {
		for (PartnerWorker partnerWorker : findByU_R(userId, role,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(partnerWorker);
		}
	}

	/**
	 * Returns the number of partner workers where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the number of matching partner workers
	 */
	@Override
	public int countByU_R(long userId, int role) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_R;

		Object[] finderArgs = new Object[] { userId, role };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_ROLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(role);

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

	/**
	 * Returns the number of partner workers where userId = any &#63; and role = any &#63;.
	 *
	 * @param userIds the user IDs
	 * @param roles the roles
	 * @return the number of matching partner workers
	 */
	@Override
	public int countByU_R(long[] userIds, int[] roles) {
		if (userIds == null) {
			userIds = new long[0];
		}
		else if (userIds.length > 1) {
			userIds = ArrayUtil.unique(userIds);

			Arrays.sort(userIds);
		}

		if (roles == null) {
			roles = new int[0];
		}
		else if (roles.length > 1) {
			roles = ArrayUtil.unique(roles);

			Arrays.sort(roles);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(userIds), StringUtil.merge(roles)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

			if (userIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_R_USERID_7);

				query.append(StringUtil.merge(userIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (roles.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_R_ROLE_7);

				query.append(StringUtil.merge(roles));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_R,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_R,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_U_R_USERID_2 = "partnerWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_R_USERID_7 = "partnerWorker.userId IN (";
	private static final String _FINDER_COLUMN_U_R_ROLE_2 = "partnerWorker.role = ?";
	private static final String _FINDER_COLUMN_U_R_ROLE_7 = "partnerWorker.role IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPEI_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPEI_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			PartnerWorkerModelImpl.PARTNERENTRYID_COLUMN_BITMASK |
			PartnerWorkerModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PEI_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPEI_R",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the partner workers where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @return the matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByPEI_R(long partnerEntryId, int role) {
		return findByPEI_R(partnerEntryId, role, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner workers where partnerEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByPEI_R(long partnerEntryId, int role,
		int start, int end) {
		return findByPEI_R(partnerEntryId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers where partnerEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByPEI_R(long partnerEntryId, int role,
		int start, int end, OrderByComparator<PartnerWorker> orderByComparator) {
		return findByPEI_R(partnerEntryId, role, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the partner workers where partnerEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching partner workers
	 */
	@Override
	public List<PartnerWorker> findByPEI_R(long partnerEntryId, int role,
		int start, int end, OrderByComparator<PartnerWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_R;
			finderArgs = new Object[] { partnerEntryId, role };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_R;
			finderArgs = new Object[] {
					partnerEntryId, role,
					
					start, end, orderByComparator
				};
		}

		List<PartnerWorker> list = null;

		if (retrieveFromCache) {
			list = (List<PartnerWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PartnerWorker partnerWorker : list) {
					if ((partnerEntryId != partnerWorker.getPartnerEntryId()) ||
							(role != partnerWorker.getRole())) {
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

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_PEI_R_PARTNERENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_R_ROLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PartnerWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(partnerEntryId);

				qPos.add(role);

				if (!pagination) {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker
	 * @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker findByPEI_R_First(long partnerEntryId, int role,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = fetchByPEI_R_First(partnerEntryId, role,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the first partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker fetchByPEI_R_First(long partnerEntryId, int role,
		OrderByComparator<PartnerWorker> orderByComparator) {
		List<PartnerWorker> list = findByPEI_R(partnerEntryId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker
	 * @throws NoSuchPartnerWorkerException if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker findByPEI_R_Last(long partnerEntryId, int role,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = fetchByPEI_R_Last(partnerEntryId, role,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the last partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 */
	@Override
	public PartnerWorker fetchByPEI_R_Last(long partnerEntryId, int role,
		OrderByComparator<PartnerWorker> orderByComparator) {
		int count = countByPEI_R(partnerEntryId, role);

		if (count == 0) {
			return null;
		}

		List<PartnerWorker> list = findByPEI_R(partnerEntryId, role, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the partner workers before and after the current partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerWorkerId the primary key of the current partner worker
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next partner worker
	 * @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 */
	@Override
	public PartnerWorker[] findByPEI_R_PrevAndNext(long partnerWorkerId,
		long partnerEntryId, int role,
		OrderByComparator<PartnerWorker> orderByComparator)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = findByPrimaryKey(partnerWorkerId);

		Session session = null;

		try {
			session = openSession();

			PartnerWorker[] array = new PartnerWorkerImpl[3];

			array[0] = getByPEI_R_PrevAndNext(session, partnerWorker,
					partnerEntryId, role, orderByComparator, true);

			array[1] = partnerWorker;

			array[2] = getByPEI_R_PrevAndNext(session, partnerWorker,
					partnerEntryId, role, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PartnerWorker getByPEI_R_PrevAndNext(Session session,
		PartnerWorker partnerWorker, long partnerEntryId, int role,
		OrderByComparator<PartnerWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

		query.append(_FINDER_COLUMN_PEI_R_PARTNERENTRYID_2);

		query.append(_FINDER_COLUMN_PEI_R_ROLE_2);

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
			query.append(PartnerWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(partnerEntryId);

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(partnerWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PartnerWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the partner workers where partnerEntryId = &#63; and role = &#63; from the database.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 */
	@Override
	public void removeByPEI_R(long partnerEntryId, int role) {
		for (PartnerWorker partnerWorker : findByPEI_R(partnerEntryId, role,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(partnerWorker);
		}
	}

	/**
	 * Returns the number of partner workers where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @return the number of matching partner workers
	 */
	@Override
	public int countByPEI_R(long partnerEntryId, int role) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PEI_R;

		Object[] finderArgs = new Object[] { partnerEntryId, role };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_PEI_R_PARTNERENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_R_ROLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(partnerEntryId);

				qPos.add(role);

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

	private static final String _FINDER_COLUMN_PEI_R_PARTNERENTRYID_2 = "partnerWorker.partnerEntryId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_R_ROLE_2 = "partnerWorker.role = ?";

	public PartnerWorkerPersistenceImpl() {
		setModelClass(PartnerWorker.class);
	}

	/**
	 * Caches the partner worker in the entity cache if it is enabled.
	 *
	 * @param partnerWorker the partner worker
	 */
	@Override
	public void cacheResult(PartnerWorker partnerWorker) {
		entityCache.putResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerImpl.class, partnerWorker.getPrimaryKey(),
			partnerWorker);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_PEI,
			new Object[] {
				partnerWorker.getUserId(), partnerWorker.getPartnerEntryId()
			}, partnerWorker);

		partnerWorker.resetOriginalValues();
	}

	/**
	 * Caches the partner workers in the entity cache if it is enabled.
	 *
	 * @param partnerWorkers the partner workers
	 */
	@Override
	public void cacheResult(List<PartnerWorker> partnerWorkers) {
		for (PartnerWorker partnerWorker : partnerWorkers) {
			if (entityCache.getResult(
						PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
						PartnerWorkerImpl.class, partnerWorker.getPrimaryKey()) == null) {
				cacheResult(partnerWorker);
			}
			else {
				partnerWorker.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all partner workers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PartnerWorkerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the partner worker.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PartnerWorker partnerWorker) {
		entityCache.removeResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerImpl.class, partnerWorker.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PartnerWorkerModelImpl)partnerWorker, true);
	}

	@Override
	public void clearCache(List<PartnerWorker> partnerWorkers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PartnerWorker partnerWorker : partnerWorkers) {
			entityCache.removeResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
				PartnerWorkerImpl.class, partnerWorker.getPrimaryKey());

			clearUniqueFindersCache((PartnerWorkerModelImpl)partnerWorker, true);
		}
	}

	protected void cacheUniqueFindersCache(
		PartnerWorkerModelImpl partnerWorkerModelImpl) {
		Object[] args = new Object[] {
				partnerWorkerModelImpl.getUserId(),
				partnerWorkerModelImpl.getPartnerEntryId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_U_PEI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_U_PEI, args,
			partnerWorkerModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PartnerWorkerModelImpl partnerWorkerModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					partnerWorkerModelImpl.getUserId(),
					partnerWorkerModelImpl.getPartnerEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_PEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_PEI, args);
		}

		if ((partnerWorkerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_PEI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					partnerWorkerModelImpl.getOriginalUserId(),
					partnerWorkerModelImpl.getOriginalPartnerEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_PEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_PEI, args);
		}
	}

	/**
	 * Creates a new partner worker with the primary key. Does not add the partner worker to the database.
	 *
	 * @param partnerWorkerId the primary key for the new partner worker
	 * @return the new partner worker
	 */
	@Override
	public PartnerWorker create(long partnerWorkerId) {
		PartnerWorker partnerWorker = new PartnerWorkerImpl();

		partnerWorker.setNew(true);
		partnerWorker.setPrimaryKey(partnerWorkerId);

		return partnerWorker;
	}

	/**
	 * Removes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param partnerWorkerId the primary key of the partner worker
	 * @return the partner worker that was removed
	 * @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 */
	@Override
	public PartnerWorker remove(long partnerWorkerId)
		throws NoSuchPartnerWorkerException {
		return remove((Serializable)partnerWorkerId);
	}

	/**
	 * Removes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the partner worker
	 * @return the partner worker that was removed
	 * @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 */
	@Override
	public PartnerWorker remove(Serializable primaryKey)
		throws NoSuchPartnerWorkerException {
		Session session = null;

		try {
			session = openSession();

			PartnerWorker partnerWorker = (PartnerWorker)session.get(PartnerWorkerImpl.class,
					primaryKey);

			if (partnerWorker == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPartnerWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(partnerWorker);
		}
		catch (NoSuchPartnerWorkerException nsee) {
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
	protected PartnerWorker removeImpl(PartnerWorker partnerWorker) {
		partnerWorker = toUnwrappedModel(partnerWorker);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(partnerWorker)) {
				partnerWorker = (PartnerWorker)session.get(PartnerWorkerImpl.class,
						partnerWorker.getPrimaryKeyObj());
			}

			if (partnerWorker != null) {
				session.delete(partnerWorker);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (partnerWorker != null) {
			clearCache(partnerWorker);
		}

		return partnerWorker;
	}

	@Override
	public PartnerWorker updateImpl(PartnerWorker partnerWorker) {
		partnerWorker = toUnwrappedModel(partnerWorker);

		boolean isNew = partnerWorker.isNew();

		PartnerWorkerModelImpl partnerWorkerModelImpl = (PartnerWorkerModelImpl)partnerWorker;

		Session session = null;

		try {
			session = openSession();

			if (partnerWorker.isNew()) {
				session.save(partnerWorker);

				partnerWorker.setNew(false);
			}
			else {
				partnerWorker = (PartnerWorker)session.merge(partnerWorker);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PartnerWorkerModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { partnerWorkerModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
				args);

			args = new Object[] { partnerWorkerModelImpl.getPartnerEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID,
				args);

			args = new Object[] {
					partnerWorkerModelImpl.getUserId(),
					partnerWorkerModelImpl.getRole()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
				args);

			args = new Object[] {
					partnerWorkerModelImpl.getPartnerEntryId(),
					partnerWorkerModelImpl.getRole()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PEI_R, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_R,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((partnerWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						partnerWorkerModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { partnerWorkerModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((partnerWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						partnerWorkerModelImpl.getOriginalPartnerEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID,
					args);

				args = new Object[] { partnerWorkerModelImpl.getPartnerEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID,
					args);
			}

			if ((partnerWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						partnerWorkerModelImpl.getOriginalUserId(),
						partnerWorkerModelImpl.getOriginalRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);

				args = new Object[] {
						partnerWorkerModelImpl.getUserId(),
						partnerWorkerModelImpl.getRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);
			}

			if ((partnerWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						partnerWorkerModelImpl.getOriginalPartnerEntryId(),
						partnerWorkerModelImpl.getOriginalRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PEI_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_R,
					args);

				args = new Object[] {
						partnerWorkerModelImpl.getPartnerEntryId(),
						partnerWorkerModelImpl.getRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PEI_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_R,
					args);
			}
		}

		entityCache.putResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerImpl.class, partnerWorker.getPrimaryKey(),
			partnerWorker, false);

		clearUniqueFindersCache(partnerWorkerModelImpl, false);
		cacheUniqueFindersCache(partnerWorkerModelImpl);

		partnerWorker.resetOriginalValues();

		return partnerWorker;
	}

	protected PartnerWorker toUnwrappedModel(PartnerWorker partnerWorker) {
		if (partnerWorker instanceof PartnerWorkerImpl) {
			return partnerWorker;
		}

		PartnerWorkerImpl partnerWorkerImpl = new PartnerWorkerImpl();

		partnerWorkerImpl.setNew(partnerWorker.isNew());
		partnerWorkerImpl.setPrimaryKey(partnerWorker.getPrimaryKey());

		partnerWorkerImpl.setPartnerWorkerId(partnerWorker.getPartnerWorkerId());
		partnerWorkerImpl.setUserId(partnerWorker.getUserId());
		partnerWorkerImpl.setPartnerEntryId(partnerWorker.getPartnerEntryId());
		partnerWorkerImpl.setRole(partnerWorker.getRole());
		partnerWorkerImpl.setNotifications(partnerWorker.getNotifications());

		return partnerWorkerImpl;
	}

	/**
	 * Returns the partner worker with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the partner worker
	 * @return the partner worker
	 * @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 */
	@Override
	public PartnerWorker findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPartnerWorkerException {
		PartnerWorker partnerWorker = fetchByPrimaryKey(primaryKey);

		if (partnerWorker == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPartnerWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return partnerWorker;
	}

	/**
	 * Returns the partner worker with the primary key or throws a {@link NoSuchPartnerWorkerException} if it could not be found.
	 *
	 * @param partnerWorkerId the primary key of the partner worker
	 * @return the partner worker
	 * @throws NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 */
	@Override
	public PartnerWorker findByPrimaryKey(long partnerWorkerId)
		throws NoSuchPartnerWorkerException {
		return findByPrimaryKey((Serializable)partnerWorkerId);
	}

	/**
	 * Returns the partner worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the partner worker
	 * @return the partner worker, or <code>null</code> if a partner worker with the primary key could not be found
	 */
	@Override
	public PartnerWorker fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
				PartnerWorkerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PartnerWorker partnerWorker = (PartnerWorker)serializable;

		if (partnerWorker == null) {
			Session session = null;

			try {
				session = openSession();

				partnerWorker = (PartnerWorker)session.get(PartnerWorkerImpl.class,
						primaryKey);

				if (partnerWorker != null) {
					cacheResult(partnerWorker);
				}
				else {
					entityCache.putResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
						PartnerWorkerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
					PartnerWorkerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return partnerWorker;
	}

	/**
	 * Returns the partner worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param partnerWorkerId the primary key of the partner worker
	 * @return the partner worker, or <code>null</code> if a partner worker with the primary key could not be found
	 */
	@Override
	public PartnerWorker fetchByPrimaryKey(long partnerWorkerId) {
		return fetchByPrimaryKey((Serializable)partnerWorkerId);
	}

	@Override
	public Map<Serializable, PartnerWorker> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PartnerWorker> map = new HashMap<Serializable, PartnerWorker>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PartnerWorker partnerWorker = fetchByPrimaryKey(primaryKey);

			if (partnerWorker != null) {
				map.put(primaryKey, partnerWorker);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
					PartnerWorkerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PartnerWorker)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PARTNERWORKER_WHERE_PKS_IN);

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

			for (PartnerWorker partnerWorker : (List<PartnerWorker>)q.list()) {
				map.put(partnerWorker.getPrimaryKeyObj(), partnerWorker);

				cacheResult(partnerWorker);

				uncachedPrimaryKeys.remove(partnerWorker.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
					PartnerWorkerImpl.class, primaryKey, nullModel);
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
	 * Returns all the partner workers.
	 *
	 * @return the partner workers
	 */
	@Override
	public List<PartnerWorker> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of partner workers
	 */
	@Override
	public List<PartnerWorker> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of partner workers
	 */
	@Override
	public List<PartnerWorker> findAll(int start, int end,
		OrderByComparator<PartnerWorker> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the partner workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of partner workers
	 */
	@Override
	public List<PartnerWorker> findAll(int start, int end,
		OrderByComparator<PartnerWorker> orderByComparator,
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

		List<PartnerWorker> list = null;

		if (retrieveFromCache) {
			list = (List<PartnerWorker>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PARTNERWORKER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PARTNERWORKER;

				if (pagination) {
					sql = sql.concat(PartnerWorkerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the partner workers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PartnerWorker partnerWorker : findAll()) {
			remove(partnerWorker);
		}
	}

	/**
	 * Returns the number of partner workers.
	 *
	 * @return the number of partner workers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PARTNERWORKER);

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
		return PartnerWorkerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the partner worker persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PartnerWorkerImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_PARTNERWORKER = "SELECT partnerWorker FROM PartnerWorker partnerWorker";
	private static final String _SQL_SELECT_PARTNERWORKER_WHERE_PKS_IN = "SELECT partnerWorker FROM PartnerWorker partnerWorker WHERE partnerWorkerId IN (";
	private static final String _SQL_SELECT_PARTNERWORKER_WHERE = "SELECT partnerWorker FROM PartnerWorker partnerWorker WHERE ";
	private static final String _SQL_COUNT_PARTNERWORKER = "SELECT COUNT(partnerWorker) FROM PartnerWorker partnerWorker";
	private static final String _SQL_COUNT_PARTNERWORKER_WHERE = "SELECT COUNT(partnerWorker) FROM PartnerWorker partnerWorker WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "partnerWorker.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PartnerWorker exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PartnerWorker exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PartnerWorkerPersistenceImpl.class);
}
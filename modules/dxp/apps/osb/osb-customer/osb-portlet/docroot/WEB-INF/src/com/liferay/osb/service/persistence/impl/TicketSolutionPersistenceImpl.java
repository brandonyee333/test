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

import com.liferay.osb.exception.NoSuchTicketSolutionException;
import com.liferay.osb.model.TicketSolution;
import com.liferay.osb.model.impl.TicketSolutionImpl;
import com.liferay.osb.model.impl.TicketSolutionModelImpl;
import com.liferay.osb.service.persistence.TicketSolutionPersistence;

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

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the ticket solution service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketSolutionPersistence
 * @see com.liferay.osb.service.persistence.TicketSolutionUtil
 * @generated
 */
@ProviderType
public class TicketSolutionPersistenceImpl extends BasePersistenceImpl<TicketSolution>
	implements TicketSolutionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketSolutionUtil} to access the ticket solution persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketSolutionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED,
			TicketSolutionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED,
			TicketSolutionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED,
			TicketSolutionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTicketEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED,
			TicketSolutionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTicketEntryId",
			new String[] { Long.class.getName() },
			TicketSolutionModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketSolutionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TICKETENTRYID = new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTicketEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the ticket solutions where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket solutions
	 */
	@Override
	public List<TicketSolution> findByTicketEntryId(long ticketEntryId) {
		return findByTicketEntryId(ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket solutions where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket solutions
	 * @param end the upper bound of the range of ticket solutions (not inclusive)
	 * @return the range of matching ticket solutions
	 */
	@Override
	public List<TicketSolution> findByTicketEntryId(long ticketEntryId,
		int start, int end) {
		return findByTicketEntryId(ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket solutions where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket solutions
	 * @param end the upper bound of the range of ticket solutions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket solutions
	 */
	@Override
	public List<TicketSolution> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketSolution> orderByComparator) {
		return findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket solutions where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket solutions
	 * @param end the upper bound of the range of ticket solutions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket solutions
	 */
	@Override
	public List<TicketSolution> findByTicketEntryId(long ticketEntryId,
		int start, int end,
		OrderByComparator<TicketSolution> orderByComparator,
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

		List<TicketSolution> list = null;

		if (retrieveFromCache) {
			list = (List<TicketSolution>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketSolution ticketSolution : list) {
					if ((ticketEntryId != ticketSolution.getTicketEntryId())) {
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

			query.append(_SQL_SELECT_TICKETSOLUTION_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketSolutionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (!pagination) {
					list = (List<TicketSolution>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketSolution>)QueryUtil.list(q,
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
	 * Returns the first ticket solution in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket solution
	 * @throws NoSuchTicketSolutionException if a matching ticket solution could not be found
	 */
	@Override
	public TicketSolution findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketSolution> orderByComparator)
		throws NoSuchTicketSolutionException {
		TicketSolution ticketSolution = fetchByTicketEntryId_First(ticketEntryId,
				orderByComparator);

		if (ticketSolution != null) {
			return ticketSolution;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketSolutionException(msg.toString());
	}

	/**
	 * Returns the first ticket solution in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket solution, or <code>null</code> if a matching ticket solution could not be found
	 */
	@Override
	public TicketSolution fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketSolution> orderByComparator) {
		List<TicketSolution> list = findByTicketEntryId(ticketEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket solution in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket solution
	 * @throws NoSuchTicketSolutionException if a matching ticket solution could not be found
	 */
	@Override
	public TicketSolution findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketSolution> orderByComparator)
		throws NoSuchTicketSolutionException {
		TicketSolution ticketSolution = fetchByTicketEntryId_Last(ticketEntryId,
				orderByComparator);

		if (ticketSolution != null) {
			return ticketSolution;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketSolutionException(msg.toString());
	}

	/**
	 * Returns the last ticket solution in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket solution, or <code>null</code> if a matching ticket solution could not be found
	 */
	@Override
	public TicketSolution fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketSolution> orderByComparator) {
		int count = countByTicketEntryId(ticketEntryId);

		if (count == 0) {
			return null;
		}

		List<TicketSolution> list = findByTicketEntryId(ticketEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket solutions before and after the current ticket solution in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketSolutionId the primary key of the current ticket solution
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket solution
	 * @throws NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	 */
	@Override
	public TicketSolution[] findByTicketEntryId_PrevAndNext(
		long ticketSolutionId, long ticketEntryId,
		OrderByComparator<TicketSolution> orderByComparator)
		throws NoSuchTicketSolutionException {
		TicketSolution ticketSolution = findByPrimaryKey(ticketSolutionId);

		Session session = null;

		try {
			session = openSession();

			TicketSolution[] array = new TicketSolutionImpl[3];

			array[0] = getByTicketEntryId_PrevAndNext(session, ticketSolution,
					ticketEntryId, orderByComparator, true);

			array[1] = ticketSolution;

			array[2] = getByTicketEntryId_PrevAndNext(session, ticketSolution,
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

	protected TicketSolution getByTicketEntryId_PrevAndNext(Session session,
		TicketSolution ticketSolution, long ticketEntryId,
		OrderByComparator<TicketSolution> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETSOLUTION_WHERE);

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
			query.append(TicketSolutionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketSolution);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketSolution> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket solutions where ticketEntryId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 */
	@Override
	public void removeByTicketEntryId(long ticketEntryId) {
		for (TicketSolution ticketSolution : findByTicketEntryId(
				ticketEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketSolution);
		}
	}

	/**
	 * Returns the number of ticket solutions where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket solutions
	 */
	@Override
	public int countByTicketEntryId(long ticketEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TICKETENTRYID;

		Object[] finderArgs = new Object[] { ticketEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETSOLUTION_WHERE);

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

	private static final String _FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2 = "ticketSolution.ticketEntryId = ?";

	public TicketSolutionPersistenceImpl() {
		setModelClass(TicketSolution.class);
	}

	/**
	 * Caches the ticket solution in the entity cache if it is enabled.
	 *
	 * @param ticketSolution the ticket solution
	 */
	@Override
	public void cacheResult(TicketSolution ticketSolution) {
		entityCache.putResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionImpl.class, ticketSolution.getPrimaryKey(),
			ticketSolution);

		ticketSolution.resetOriginalValues();
	}

	/**
	 * Caches the ticket solutions in the entity cache if it is enabled.
	 *
	 * @param ticketSolutions the ticket solutions
	 */
	@Override
	public void cacheResult(List<TicketSolution> ticketSolutions) {
		for (TicketSolution ticketSolution : ticketSolutions) {
			if (entityCache.getResult(
						TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
						TicketSolutionImpl.class, ticketSolution.getPrimaryKey()) == null) {
				cacheResult(ticketSolution);
			}
			else {
				ticketSolution.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket solutions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TicketSolutionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket solution.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketSolution ticketSolution) {
		entityCache.removeResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionImpl.class, ticketSolution.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TicketSolution> ticketSolutions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketSolution ticketSolution : ticketSolutions) {
			entityCache.removeResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
				TicketSolutionImpl.class, ticketSolution.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ticket solution with the primary key. Does not add the ticket solution to the database.
	 *
	 * @param ticketSolutionId the primary key for the new ticket solution
	 * @return the new ticket solution
	 */
	@Override
	public TicketSolution create(long ticketSolutionId) {
		TicketSolution ticketSolution = new TicketSolutionImpl();

		ticketSolution.setNew(true);
		ticketSolution.setPrimaryKey(ticketSolutionId);

		return ticketSolution;
	}

	/**
	 * Removes the ticket solution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketSolutionId the primary key of the ticket solution
	 * @return the ticket solution that was removed
	 * @throws NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	 */
	@Override
	public TicketSolution remove(long ticketSolutionId)
		throws NoSuchTicketSolutionException {
		return remove((Serializable)ticketSolutionId);
	}

	/**
	 * Removes the ticket solution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket solution
	 * @return the ticket solution that was removed
	 * @throws NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	 */
	@Override
	public TicketSolution remove(Serializable primaryKey)
		throws NoSuchTicketSolutionException {
		Session session = null;

		try {
			session = openSession();

			TicketSolution ticketSolution = (TicketSolution)session.get(TicketSolutionImpl.class,
					primaryKey);

			if (ticketSolution == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketSolutionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketSolution);
		}
		catch (NoSuchTicketSolutionException nsee) {
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
	protected TicketSolution removeImpl(TicketSolution ticketSolution) {
		ticketSolution = toUnwrappedModel(ticketSolution);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ticketSolution)) {
				ticketSolution = (TicketSolution)session.get(TicketSolutionImpl.class,
						ticketSolution.getPrimaryKeyObj());
			}

			if (ticketSolution != null) {
				session.delete(ticketSolution);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ticketSolution != null) {
			clearCache(ticketSolution);
		}

		return ticketSolution;
	}

	@Override
	public TicketSolution updateImpl(TicketSolution ticketSolution) {
		ticketSolution = toUnwrappedModel(ticketSolution);

		boolean isNew = ticketSolution.isNew();

		TicketSolutionModelImpl ticketSolutionModelImpl = (TicketSolutionModelImpl)ticketSolution;

		Session session = null;

		try {
			session = openSession();

			if (ticketSolution.isNew()) {
				session.save(ticketSolution);

				ticketSolution.setNew(false);
			}
			else {
				ticketSolution = (TicketSolution)session.merge(ticketSolution);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TicketSolutionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					ticketSolutionModelImpl.getTicketEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ticketSolutionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketSolutionModelImpl.getOriginalTicketEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);

				args = new Object[] { ticketSolutionModelImpl.getTicketEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);
			}
		}

		entityCache.putResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionImpl.class, ticketSolution.getPrimaryKey(),
			ticketSolution, false);

		ticketSolution.resetOriginalValues();

		return ticketSolution;
	}

	protected TicketSolution toUnwrappedModel(TicketSolution ticketSolution) {
		if (ticketSolution instanceof TicketSolutionImpl) {
			return ticketSolution;
		}

		TicketSolutionImpl ticketSolutionImpl = new TicketSolutionImpl();

		ticketSolutionImpl.setNew(ticketSolution.isNew());
		ticketSolutionImpl.setPrimaryKey(ticketSolution.getPrimaryKey());

		ticketSolutionImpl.setTicketSolutionId(ticketSolution.getTicketSolutionId());
		ticketSolutionImpl.setUserId(ticketSolution.getUserId());
		ticketSolutionImpl.setUserName(ticketSolution.getUserName());
		ticketSolutionImpl.setCreateDate(ticketSolution.getCreateDate());
		ticketSolutionImpl.setTicketEntryId(ticketSolution.getTicketEntryId());
		ticketSolutionImpl.setSummary(ticketSolution.getSummary());
		ticketSolutionImpl.setUseCustomerSummary(ticketSolution.isUseCustomerSummary());
		ticketSolutionImpl.setIssueType(ticketSolution.getIssueType());
		ticketSolutionImpl.setSolution(ticketSolution.getSolution());
		ticketSolutionImpl.setType(ticketSolution.getType());
		ticketSolutionImpl.setCustomerSpecific(ticketSolution.isCustomerSpecific());
		ticketSolutionImpl.setEnvironmentSpecific(ticketSolution.isEnvironmentSpecific());
		ticketSolutionImpl.setVersionSpecific(ticketSolution.isVersionSpecific());
		ticketSolutionImpl.setReviewForKB(ticketSolution.isReviewForKB());
		ticketSolutionImpl.setStatus(ticketSolution.getStatus());
		ticketSolutionImpl.setStatusByUserId(ticketSolution.getStatusByUserId());
		ticketSolutionImpl.setStatusByUserName(ticketSolution.getStatusByUserName());
		ticketSolutionImpl.setStatusDate(ticketSolution.getStatusDate());
		ticketSolutionImpl.setStatusMessage(ticketSolution.getStatusMessage());
		ticketSolutionImpl.setStatusReason(ticketSolution.getStatusReason());

		return ticketSolutionImpl;
	}

	/**
	 * Returns the ticket solution with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket solution
	 * @return the ticket solution
	 * @throws NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	 */
	@Override
	public TicketSolution findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTicketSolutionException {
		TicketSolution ticketSolution = fetchByPrimaryKey(primaryKey);

		if (ticketSolution == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTicketSolutionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ticketSolution;
	}

	/**
	 * Returns the ticket solution with the primary key or throws a {@link NoSuchTicketSolutionException} if it could not be found.
	 *
	 * @param ticketSolutionId the primary key of the ticket solution
	 * @return the ticket solution
	 * @throws NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	 */
	@Override
	public TicketSolution findByPrimaryKey(long ticketSolutionId)
		throws NoSuchTicketSolutionException {
		return findByPrimaryKey((Serializable)ticketSolutionId);
	}

	/**
	 * Returns the ticket solution with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket solution
	 * @return the ticket solution, or <code>null</code> if a ticket solution with the primary key could not be found
	 */
	@Override
	public TicketSolution fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
				TicketSolutionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TicketSolution ticketSolution = (TicketSolution)serializable;

		if (ticketSolution == null) {
			Session session = null;

			try {
				session = openSession();

				ticketSolution = (TicketSolution)session.get(TicketSolutionImpl.class,
						primaryKey);

				if (ticketSolution != null) {
					cacheResult(ticketSolution);
				}
				else {
					entityCache.putResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
						TicketSolutionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
					TicketSolutionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ticketSolution;
	}

	/**
	 * Returns the ticket solution with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketSolutionId the primary key of the ticket solution
	 * @return the ticket solution, or <code>null</code> if a ticket solution with the primary key could not be found
	 */
	@Override
	public TicketSolution fetchByPrimaryKey(long ticketSolutionId) {
		return fetchByPrimaryKey((Serializable)ticketSolutionId);
	}

	@Override
	public Map<Serializable, TicketSolution> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TicketSolution> map = new HashMap<Serializable, TicketSolution>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TicketSolution ticketSolution = fetchByPrimaryKey(primaryKey);

			if (ticketSolution != null) {
				map.put(primaryKey, ticketSolution);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
					TicketSolutionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TicketSolution)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TICKETSOLUTION_WHERE_PKS_IN);

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

			for (TicketSolution ticketSolution : (List<TicketSolution>)q.list()) {
				map.put(ticketSolution.getPrimaryKeyObj(), ticketSolution);

				cacheResult(ticketSolution);

				uncachedPrimaryKeys.remove(ticketSolution.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
					TicketSolutionImpl.class, primaryKey, nullModel);
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
	 * Returns all the ticket solutions.
	 *
	 * @return the ticket solutions
	 */
	@Override
	public List<TicketSolution> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket solutions
	 * @param end the upper bound of the range of ticket solutions (not inclusive)
	 * @return the range of ticket solutions
	 */
	@Override
	public List<TicketSolution> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket solutions
	 * @param end the upper bound of the range of ticket solutions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket solutions
	 */
	@Override
	public List<TicketSolution> findAll(int start, int end,
		OrderByComparator<TicketSolution> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket solutions
	 * @param end the upper bound of the range of ticket solutions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ticket solutions
	 */
	@Override
	public List<TicketSolution> findAll(int start, int end,
		OrderByComparator<TicketSolution> orderByComparator,
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

		List<TicketSolution> list = null;

		if (retrieveFromCache) {
			list = (List<TicketSolution>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TICKETSOLUTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETSOLUTION;

				if (pagination) {
					sql = sql.concat(TicketSolutionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TicketSolution>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketSolution>)QueryUtil.list(q,
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
	 * Removes all the ticket solutions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TicketSolution ticketSolution : findAll()) {
			remove(ticketSolution);
		}
	}

	/**
	 * Returns the number of ticket solutions.
	 *
	 * @return the number of ticket solutions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETSOLUTION);

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
		return TicketSolutionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ticket solution persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TicketSolutionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_TICKETSOLUTION = "SELECT ticketSolution FROM TicketSolution ticketSolution";
	private static final String _SQL_SELECT_TICKETSOLUTION_WHERE_PKS_IN = "SELECT ticketSolution FROM TicketSolution ticketSolution WHERE ticketSolutionId IN (";
	private static final String _SQL_SELECT_TICKETSOLUTION_WHERE = "SELECT ticketSolution FROM TicketSolution ticketSolution WHERE ";
	private static final String _SQL_COUNT_TICKETSOLUTION = "SELECT COUNT(ticketSolution) FROM TicketSolution ticketSolution";
	private static final String _SQL_COUNT_TICKETSOLUTION_WHERE = "SELECT COUNT(ticketSolution) FROM TicketSolution ticketSolution WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketSolution.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketSolution exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketSolution exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TicketSolutionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
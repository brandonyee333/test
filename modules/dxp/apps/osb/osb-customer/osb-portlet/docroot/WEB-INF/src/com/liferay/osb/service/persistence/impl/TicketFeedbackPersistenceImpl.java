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

import com.liferay.osb.exception.NoSuchTicketFeedbackException;
import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.impl.TicketFeedbackImpl;
import com.liferay.osb.model.impl.TicketFeedbackModelImpl;
import com.liferay.osb.service.persistence.TicketFeedbackPersistence;

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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

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
 * The persistence implementation for the ticket feedback service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedbackPersistence
 * @see com.liferay.osb.service.persistence.TicketFeedbackUtil
 * @generated
 */
@ProviderType
public class TicketFeedbackPersistenceImpl extends BasePersistenceImpl<TicketFeedback>
	implements TicketFeedbackPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketFeedbackUtil} to access the ticket feedback persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketFeedbackImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTicketEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTicketEntryId",
			new String[] { Long.class.getName() },
			TicketFeedbackModelImpl.TICKETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TICKETENTRYID = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTicketEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the ticket feedbacks where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTicketEntryId(long ticketEntryId) {
		return findByTicketEntryId(ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket feedbacks where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @return the range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTicketEntryId(long ticketEntryId,
		int start, int end) {
		return findByTicketEntryId(ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketFeedback> orderByComparator) {
		return findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTicketEntryId(long ticketEntryId,
		int start, int end,
		OrderByComparator<TicketFeedback> orderByComparator,
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

		List<TicketFeedback> list = null;

		if (retrieveFromCache) {
			list = (List<TicketFeedback>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketFeedback ticketFeedback : list) {
					if ((ticketEntryId != ticketFeedback.getTicketEntryId())) {
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

			query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketFeedbackModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (!pagination) {
					list = (List<TicketFeedback>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketFeedback>)QueryUtil.list(q,
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
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback
	 * @throws NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = fetchByTicketEntryId_First(ticketEntryId,
				orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketFeedback> orderByComparator) {
		List<TicketFeedback> list = findByTicketEntryId(ticketEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback
	 * @throws NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = fetchByTicketEntryId_Last(ticketEntryId,
				orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketFeedback> orderByComparator) {
		int count = countByTicketEntryId(ticketEntryId);

		if (count == 0) {
			return null;
		}

		List<TicketFeedback> list = findByTicketEntryId(ticketEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketFeedbackId the primary key of the current ticket feedback
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket feedback
	 * @throws NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 */
	@Override
	public TicketFeedback[] findByTicketEntryId_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId,
		OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = findByPrimaryKey(ticketFeedbackId);

		Session session = null;

		try {
			session = openSession();

			TicketFeedback[] array = new TicketFeedbackImpl[3];

			array[0] = getByTicketEntryId_PrevAndNext(session, ticketFeedback,
					ticketEntryId, orderByComparator, true);

			array[1] = ticketFeedback;

			array[2] = getByTicketEntryId_PrevAndNext(session, ticketFeedback,
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

	protected TicketFeedback getByTicketEntryId_PrevAndNext(Session session,
		TicketFeedback ticketFeedback, long ticketEntryId,
		OrderByComparator<TicketFeedback> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

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
			query.append(TicketFeedbackModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFeedback);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFeedback> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket feedbacks where ticketEntryId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 */
	@Override
	public void removeByTicketEntryId(long ticketEntryId) {
		for (TicketFeedback ticketFeedback : findByTicketEntryId(
				ticketEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketFeedback);
		}
	}

	/**
	 * Returns the number of ticket feedbacks where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket feedbacks
	 */
	@Override
	public int countByTicketEntryId(long ticketEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TICKETENTRYID;

		Object[] finderArgs = new Object[] { ticketEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETFEEDBACK_WHERE);

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

	private static final String _FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2 = "ticketFeedback.ticketEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TicketFeedbackModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketFeedbackModelImpl.SUBJECT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_S = new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @return the matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTEI_S(long ticketEntryId, int subject) {
		return findByTEI_S(ticketEntryId, subject, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @return the range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTEI_S(long ticketEntryId, int subject,
		int start, int end) {
		return findByTEI_S(ticketEntryId, subject, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTEI_S(long ticketEntryId, int subject,
		int start, int end, OrderByComparator<TicketFeedback> orderByComparator) {
		return findByTEI_S(ticketEntryId, subject, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTEI_S(long ticketEntryId, int subject,
		int start, int end,
		OrderByComparator<TicketFeedback> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S;
			finderArgs = new Object[] { ticketEntryId, subject };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S;
			finderArgs = new Object[] {
					ticketEntryId, subject,
					
					start, end, orderByComparator
				};
		}

		List<TicketFeedback> list = null;

		if (retrieveFromCache) {
			list = (List<TicketFeedback>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketFeedback ticketFeedback : list) {
					if ((ticketEntryId != ticketFeedback.getTicketEntryId()) ||
							(subject != ticketFeedback.getSubject())) {
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

			query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_SUBJECT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketFeedbackModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(subject);

				if (!pagination) {
					list = (List<TicketFeedback>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketFeedback>)QueryUtil.list(q,
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
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback
	 * @throws NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback findByTEI_S_First(long ticketEntryId, int subject,
		OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = fetchByTEI_S_First(ticketEntryId,
				subject, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback fetchByTEI_S_First(long ticketEntryId, int subject,
		OrderByComparator<TicketFeedback> orderByComparator) {
		List<TicketFeedback> list = findByTEI_S(ticketEntryId, subject, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback
	 * @throws NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback findByTEI_S_Last(long ticketEntryId, int subject,
		OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = fetchByTEI_S_Last(ticketEntryId,
				subject, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback fetchByTEI_S_Last(long ticketEntryId, int subject,
		OrderByComparator<TicketFeedback> orderByComparator) {
		int count = countByTEI_S(ticketEntryId, subject);

		if (count == 0) {
			return null;
		}

		List<TicketFeedback> list = findByTEI_S(ticketEntryId, subject,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketFeedbackId the primary key of the current ticket feedback
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket feedback
	 * @throws NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 */
	@Override
	public TicketFeedback[] findByTEI_S_PrevAndNext(long ticketFeedbackId,
		long ticketEntryId, int subject,
		OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = findByPrimaryKey(ticketFeedbackId);

		Session session = null;

		try {
			session = openSession();

			TicketFeedback[] array = new TicketFeedbackImpl[3];

			array[0] = getByTEI_S_PrevAndNext(session, ticketFeedback,
					ticketEntryId, subject, orderByComparator, true);

			array[1] = ticketFeedback;

			array[2] = getByTEI_S_PrevAndNext(session, ticketFeedback,
					ticketEntryId, subject, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketFeedback getByTEI_S_PrevAndNext(Session session,
		TicketFeedback ticketFeedback, long ticketEntryId, int subject,
		OrderByComparator<TicketFeedback> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

		query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_S_SUBJECT_2);

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
			query.append(TicketFeedbackModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(subject);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFeedback);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFeedback> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 */
	@Override
	public void removeByTEI_S(long ticketEntryId, int subject) {
		for (TicketFeedback ticketFeedback : findByTEI_S(ticketEntryId,
				subject, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketFeedback);
		}
	}

	/**
	 * Returns the number of ticket feedbacks where ticketEntryId = &#63; and subject = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @return the number of matching ticket feedbacks
	 */
	@Override
	public int countByTEI_S(long ticketEntryId, int subject) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEI_S;

		Object[] finderArgs = new Object[] { ticketEntryId, subject };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_SUBJECT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(subject);

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

	private static final String _FINDER_COLUMN_TEI_S_TICKETENTRYID_2 = "ticketFeedback.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_S_SUBJECT_2 = "ticketFeedback.subject = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S_NOTS =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_S_NotS",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_S_NOTS =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTEI_S_NotS",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @return the matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTEI_S_NotS(long ticketEntryId,
		int subject, int status) {
		return findByTEI_S_NotS(ticketEntryId, subject, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @return the range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTEI_S_NotS(long ticketEntryId,
		int subject, int status, int start, int end) {
		return findByTEI_S_NotS(ticketEntryId, subject, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTEI_S_NotS(long ticketEntryId,
		int subject, int status, int start, int end,
		OrderByComparator<TicketFeedback> orderByComparator) {
		return findByTEI_S_NotS(ticketEntryId, subject, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByTEI_S_NotS(long ticketEntryId,
		int subject, int status, int start, int end,
		OrderByComparator<TicketFeedback> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S_NOTS;
		finderArgs = new Object[] {
				ticketEntryId, subject, status,
				
				start, end, orderByComparator
			};

		List<TicketFeedback> list = null;

		if (retrieveFromCache) {
			list = (List<TicketFeedback>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketFeedback ticketFeedback : list) {
					if ((ticketEntryId != ticketFeedback.getTicketEntryId()) ||
							(subject != ticketFeedback.getSubject()) ||
							(status == ticketFeedback.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_SUBJECT_2);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketFeedbackModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(subject);

				qPos.add(status);

				if (!pagination) {
					list = (List<TicketFeedback>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketFeedback>)QueryUtil.list(q,
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
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback
	 * @throws NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback findByTEI_S_NotS_First(long ticketEntryId,
		int subject, int status,
		OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = fetchByTEI_S_NotS_First(ticketEntryId,
				subject, status, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the first ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback fetchByTEI_S_NotS_First(long ticketEntryId,
		int subject, int status,
		OrderByComparator<TicketFeedback> orderByComparator) {
		List<TicketFeedback> list = findByTEI_S_NotS(ticketEntryId, subject,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback
	 * @throws NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback findByTEI_S_NotS_Last(long ticketEntryId,
		int subject, int status,
		OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = fetchByTEI_S_NotS_Last(ticketEntryId,
				subject, status, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the last ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback fetchByTEI_S_NotS_Last(long ticketEntryId,
		int subject, int status,
		OrderByComparator<TicketFeedback> orderByComparator) {
		int count = countByTEI_S_NotS(ticketEntryId, subject, status);

		if (count == 0) {
			return null;
		}

		List<TicketFeedback> list = findByTEI_S_NotS(ticketEntryId, subject,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketFeedbackId the primary key of the current ticket feedback
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket feedback
	 * @throws NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 */
	@Override
	public TicketFeedback[] findByTEI_S_NotS_PrevAndNext(
		long ticketFeedbackId, long ticketEntryId, int subject, int status,
		OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = findByPrimaryKey(ticketFeedbackId);

		Session session = null;

		try {
			session = openSession();

			TicketFeedback[] array = new TicketFeedbackImpl[3];

			array[0] = getByTEI_S_NotS_PrevAndNext(session, ticketFeedback,
					ticketEntryId, subject, status, orderByComparator, true);

			array[1] = ticketFeedback;

			array[2] = getByTEI_S_NotS_PrevAndNext(session, ticketFeedback,
					ticketEntryId, subject, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketFeedback getByTEI_S_NotS_PrevAndNext(Session session,
		TicketFeedback ticketFeedback, long ticketEntryId, int subject,
		int status, OrderByComparator<TicketFeedback> orderByComparator,
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

		query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

		query.append(_FINDER_COLUMN_TEI_S_NOTS_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_S_NOTS_SUBJECT_2);

		query.append(_FINDER_COLUMN_TEI_S_NOTS_STATUS_2);

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
			query.append(TicketFeedbackModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(subject);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFeedback);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFeedback> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 */
	@Override
	public void removeByTEI_S_NotS(long ticketEntryId, int subject, int status) {
		for (TicketFeedback ticketFeedback : findByTEI_S_NotS(ticketEntryId,
				subject, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketFeedback);
		}
	}

	/**
	 * Returns the number of ticket feedbacks where ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @return the number of matching ticket feedbacks
	 */
	@Override
	public int countByTEI_S_NotS(long ticketEntryId, int subject, int status) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_S_NOTS;

		Object[] finderArgs = new Object[] { ticketEntryId, subject, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_SUBJECT_2);

			query.append(_FINDER_COLUMN_TEI_S_NOTS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(subject);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_TEI_S_NOTS_TICKETENTRYID_2 = "ticketFeedback.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_S_NOTS_SUBJECT_2 = "ticketFeedback.subject = ? AND ";
	private static final String _FINDER_COLUMN_TEI_S_NOTS_STATUS_2 = "ticketFeedback.status != ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_S_NOTS =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED,
			TicketFeedbackImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_TEI_S_NotS",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_TEI_S_NOTS =
		new FinderPath(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_TEI_S_NotS",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @return the matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByU_TEI_S_NotS(long userId,
		long ticketEntryId, int subject, int status) {
		return findByU_TEI_S_NotS(userId, ticketEntryId, subject, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @return the range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByU_TEI_S_NotS(long userId,
		long ticketEntryId, int subject, int status, int start, int end) {
		return findByU_TEI_S_NotS(userId, ticketEntryId, subject, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByU_TEI_S_NotS(long userId,
		long ticketEntryId, int subject, int status, int start, int end,
		OrderByComparator<TicketFeedback> orderByComparator) {
		return findByU_TEI_S_NotS(userId, ticketEntryId, subject, status,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findByU_TEI_S_NotS(long userId,
		long ticketEntryId, int subject, int status, int start, int end,
		OrderByComparator<TicketFeedback> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_S_NOTS;
		finderArgs = new Object[] {
				userId, ticketEntryId, subject, status,
				
				start, end, orderByComparator
			};

		List<TicketFeedback> list = null;

		if (retrieveFromCache) {
			list = (List<TicketFeedback>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketFeedback ticketFeedback : list) {
					if ((userId != ticketFeedback.getUserId()) ||
							(ticketEntryId != ticketFeedback.getTicketEntryId()) ||
							(subject != ticketFeedback.getSubject()) ||
							(status == ticketFeedback.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_SUBJECT_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketFeedbackModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				qPos.add(subject);

				qPos.add(status);

				if (!pagination) {
					list = (List<TicketFeedback>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketFeedback>)QueryUtil.list(q,
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
	 * Returns the first ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback
	 * @throws NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback findByU_TEI_S_NotS_First(long userId,
		long ticketEntryId, int subject, int status,
		OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = fetchByU_TEI_S_NotS_First(userId,
				ticketEntryId, subject, status, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the first ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback fetchByU_TEI_S_NotS_First(long userId,
		long ticketEntryId, int subject, int status,
		OrderByComparator<TicketFeedback> orderByComparator) {
		List<TicketFeedback> list = findByU_TEI_S_NotS(userId, ticketEntryId,
				subject, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback
	 * @throws NoSuchTicketFeedbackException if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback findByU_TEI_S_NotS_Last(long userId,
		long ticketEntryId, int subject, int status,
		OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = fetchByU_TEI_S_NotS_Last(userId,
				ticketEntryId, subject, status, orderByComparator);

		if (ticketFeedback != null) {
			return ticketFeedback;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", subject=");
		msg.append(subject);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFeedbackException(msg.toString());
	}

	/**
	 * Returns the last ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket feedback, or <code>null</code> if a matching ticket feedback could not be found
	 */
	@Override
	public TicketFeedback fetchByU_TEI_S_NotS_Last(long userId,
		long ticketEntryId, int subject, int status,
		OrderByComparator<TicketFeedback> orderByComparator) {
		int count = countByU_TEI_S_NotS(userId, ticketEntryId, subject, status);

		if (count == 0) {
			return null;
		}

		List<TicketFeedback> list = findByU_TEI_S_NotS(userId, ticketEntryId,
				subject, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket feedbacks before and after the current ticket feedback in the ordered set where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param ticketFeedbackId the primary key of the current ticket feedback
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket feedback
	 * @throws NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 */
	@Override
	public TicketFeedback[] findByU_TEI_S_NotS_PrevAndNext(
		long ticketFeedbackId, long userId, long ticketEntryId, int subject,
		int status, OrderByComparator<TicketFeedback> orderByComparator)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = findByPrimaryKey(ticketFeedbackId);

		Session session = null;

		try {
			session = openSession();

			TicketFeedback[] array = new TicketFeedbackImpl[3];

			array[0] = getByU_TEI_S_NotS_PrevAndNext(session, ticketFeedback,
					userId, ticketEntryId, subject, status, orderByComparator,
					true);

			array[1] = ticketFeedback;

			array[2] = getByU_TEI_S_NotS_PrevAndNext(session, ticketFeedback,
					userId, ticketEntryId, subject, status, orderByComparator,
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

	protected TicketFeedback getByU_TEI_S_NotS_PrevAndNext(Session session,
		TicketFeedback ticketFeedback, long userId, long ticketEntryId,
		int subject, int status,
		OrderByComparator<TicketFeedback> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE);

		query.append(_FINDER_COLUMN_U_TEI_S_NOTS_USERID_2);

		query.append(_FINDER_COLUMN_U_TEI_S_NOTS_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_U_TEI_S_NOTS_SUBJECT_2);

		query.append(_FINDER_COLUMN_U_TEI_S_NOTS_STATUS_2);

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
			query.append(TicketFeedbackModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(ticketEntryId);

		qPos.add(subject);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFeedback);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFeedback> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 */
	@Override
	public void removeByU_TEI_S_NotS(long userId, long ticketEntryId,
		int subject, int status) {
		for (TicketFeedback ticketFeedback : findByU_TEI_S_NotS(userId,
				ticketEntryId, subject, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ticketFeedback);
		}
	}

	/**
	 * Returns the number of ticket feedbacks where userId = &#63; and ticketEntryId = &#63; and subject = &#63; and status &ne; &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param subject the subject
	 * @param status the status
	 * @return the number of matching ticket feedbacks
	 */
	@Override
	public int countByU_TEI_S_NotS(long userId, long ticketEntryId,
		int subject, int status) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_TEI_S_NOTS;

		Object[] finderArgs = new Object[] {
				userId, ticketEntryId, subject, status
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TICKETFEEDBACK_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_SUBJECT_2);

			query.append(_FINDER_COLUMN_U_TEI_S_NOTS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				qPos.add(subject);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_U_TEI_S_NOTS_USERID_2 = "ticketFeedback.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_S_NOTS_TICKETENTRYID_2 = "ticketFeedback.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_S_NOTS_SUBJECT_2 = "ticketFeedback.subject = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_S_NOTS_STATUS_2 = "ticketFeedback.status != ?";

	public TicketFeedbackPersistenceImpl() {
		setModelClass(TicketFeedback.class);
	}

	/**
	 * Caches the ticket feedback in the entity cache if it is enabled.
	 *
	 * @param ticketFeedback the ticket feedback
	 */
	@Override
	public void cacheResult(TicketFeedback ticketFeedback) {
		entityCache.putResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackImpl.class, ticketFeedback.getPrimaryKey(),
			ticketFeedback);

		ticketFeedback.resetOriginalValues();
	}

	/**
	 * Caches the ticket feedbacks in the entity cache if it is enabled.
	 *
	 * @param ticketFeedbacks the ticket feedbacks
	 */
	@Override
	public void cacheResult(List<TicketFeedback> ticketFeedbacks) {
		for (TicketFeedback ticketFeedback : ticketFeedbacks) {
			if (entityCache.getResult(
						TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
						TicketFeedbackImpl.class, ticketFeedback.getPrimaryKey()) == null) {
				cacheResult(ticketFeedback);
			}
			else {
				ticketFeedback.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket feedbacks.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TicketFeedbackImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket feedback.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketFeedback ticketFeedback) {
		entityCache.removeResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackImpl.class, ticketFeedback.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TicketFeedback> ticketFeedbacks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketFeedback ticketFeedback : ticketFeedbacks) {
			entityCache.removeResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
				TicketFeedbackImpl.class, ticketFeedback.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ticket feedback with the primary key. Does not add the ticket feedback to the database.
	 *
	 * @param ticketFeedbackId the primary key for the new ticket feedback
	 * @return the new ticket feedback
	 */
	@Override
	public TicketFeedback create(long ticketFeedbackId) {
		TicketFeedback ticketFeedback = new TicketFeedbackImpl();

		ticketFeedback.setNew(true);
		ticketFeedback.setPrimaryKey(ticketFeedbackId);

		return ticketFeedback;
	}

	/**
	 * Removes the ticket feedback with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketFeedbackId the primary key of the ticket feedback
	 * @return the ticket feedback that was removed
	 * @throws NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 */
	@Override
	public TicketFeedback remove(long ticketFeedbackId)
		throws NoSuchTicketFeedbackException {
		return remove((Serializable)ticketFeedbackId);
	}

	/**
	 * Removes the ticket feedback with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket feedback
	 * @return the ticket feedback that was removed
	 * @throws NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 */
	@Override
	public TicketFeedback remove(Serializable primaryKey)
		throws NoSuchTicketFeedbackException {
		Session session = null;

		try {
			session = openSession();

			TicketFeedback ticketFeedback = (TicketFeedback)session.get(TicketFeedbackImpl.class,
					primaryKey);

			if (ticketFeedback == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketFeedbackException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketFeedback);
		}
		catch (NoSuchTicketFeedbackException nsee) {
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
	protected TicketFeedback removeImpl(TicketFeedback ticketFeedback) {
		ticketFeedback = toUnwrappedModel(ticketFeedback);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ticketFeedback)) {
				ticketFeedback = (TicketFeedback)session.get(TicketFeedbackImpl.class,
						ticketFeedback.getPrimaryKeyObj());
			}

			if (ticketFeedback != null) {
				session.delete(ticketFeedback);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ticketFeedback != null) {
			clearCache(ticketFeedback);
		}

		return ticketFeedback;
	}

	@Override
	public TicketFeedback updateImpl(TicketFeedback ticketFeedback) {
		ticketFeedback = toUnwrappedModel(ticketFeedback);

		boolean isNew = ticketFeedback.isNew();

		TicketFeedbackModelImpl ticketFeedbackModelImpl = (TicketFeedbackModelImpl)ticketFeedback;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (ticketFeedback.getCreateDate() == null)) {
			if (serviceContext == null) {
				ticketFeedback.setCreateDate(now);
			}
			else {
				ticketFeedback.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!ticketFeedbackModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				ticketFeedback.setModifiedDate(now);
			}
			else {
				ticketFeedback.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (ticketFeedback.isNew()) {
				session.save(ticketFeedback);

				ticketFeedback.setNew(false);
			}
			else {
				ticketFeedback = (TicketFeedback)session.merge(ticketFeedback);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TicketFeedbackModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					ticketFeedbackModelImpl.getTicketEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
				args);

			args = new Object[] {
					ticketFeedbackModelImpl.getTicketEntryId(),
					ticketFeedbackModelImpl.getSubject()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ticketFeedbackModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketFeedbackModelImpl.getOriginalTicketEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);

				args = new Object[] { ticketFeedbackModelImpl.getTicketEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);
			}

			if ((ticketFeedbackModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketFeedbackModelImpl.getOriginalTicketEntryId(),
						ticketFeedbackModelImpl.getOriginalSubject()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S,
					args);

				args = new Object[] {
						ticketFeedbackModelImpl.getTicketEntryId(),
						ticketFeedbackModelImpl.getSubject()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S,
					args);
			}
		}

		entityCache.putResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
			TicketFeedbackImpl.class, ticketFeedback.getPrimaryKey(),
			ticketFeedback, false);

		ticketFeedback.resetOriginalValues();

		return ticketFeedback;
	}

	protected TicketFeedback toUnwrappedModel(TicketFeedback ticketFeedback) {
		if (ticketFeedback instanceof TicketFeedbackImpl) {
			return ticketFeedback;
		}

		TicketFeedbackImpl ticketFeedbackImpl = new TicketFeedbackImpl();

		ticketFeedbackImpl.setNew(ticketFeedback.isNew());
		ticketFeedbackImpl.setPrimaryKey(ticketFeedback.getPrimaryKey());

		ticketFeedbackImpl.setTicketFeedbackId(ticketFeedback.getTicketFeedbackId());
		ticketFeedbackImpl.setUserId(ticketFeedback.getUserId());
		ticketFeedbackImpl.setUserName(ticketFeedback.getUserName());
		ticketFeedbackImpl.setCreateDate(ticketFeedback.getCreateDate());
		ticketFeedbackImpl.setModifiedDate(ticketFeedback.getModifiedDate());
		ticketFeedbackImpl.setAccountEntryId(ticketFeedback.getAccountEntryId());
		ticketFeedbackImpl.setTicketEntryId(ticketFeedback.getTicketEntryId());
		ticketFeedbackImpl.setSubject(ticketFeedback.getSubject());
		ticketFeedbackImpl.setSatisfied(ticketFeedback.getSatisfied());
		ticketFeedbackImpl.setAnswer1(ticketFeedback.getAnswer1());
		ticketFeedbackImpl.setAnswer2(ticketFeedback.getAnswer2());
		ticketFeedbackImpl.setAnswer3(ticketFeedback.getAnswer3());
		ticketFeedbackImpl.setRating1(ticketFeedback.getRating1());
		ticketFeedbackImpl.setRating2(ticketFeedback.getRating2());
		ticketFeedbackImpl.setRating3(ticketFeedback.getRating3());
		ticketFeedbackImpl.setRating4(ticketFeedback.getRating4());
		ticketFeedbackImpl.setComments(ticketFeedback.getComments());
		ticketFeedbackImpl.setStatus(ticketFeedback.getStatus());

		return ticketFeedbackImpl;
	}

	/**
	 * Returns the ticket feedback with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket feedback
	 * @return the ticket feedback
	 * @throws NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 */
	@Override
	public TicketFeedback findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTicketFeedbackException {
		TicketFeedback ticketFeedback = fetchByPrimaryKey(primaryKey);

		if (ticketFeedback == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTicketFeedbackException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ticketFeedback;
	}

	/**
	 * Returns the ticket feedback with the primary key or throws a {@link NoSuchTicketFeedbackException} if it could not be found.
	 *
	 * @param ticketFeedbackId the primary key of the ticket feedback
	 * @return the ticket feedback
	 * @throws NoSuchTicketFeedbackException if a ticket feedback with the primary key could not be found
	 */
	@Override
	public TicketFeedback findByPrimaryKey(long ticketFeedbackId)
		throws NoSuchTicketFeedbackException {
		return findByPrimaryKey((Serializable)ticketFeedbackId);
	}

	/**
	 * Returns the ticket feedback with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket feedback
	 * @return the ticket feedback, or <code>null</code> if a ticket feedback with the primary key could not be found
	 */
	@Override
	public TicketFeedback fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
				TicketFeedbackImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TicketFeedback ticketFeedback = (TicketFeedback)serializable;

		if (ticketFeedback == null) {
			Session session = null;

			try {
				session = openSession();

				ticketFeedback = (TicketFeedback)session.get(TicketFeedbackImpl.class,
						primaryKey);

				if (ticketFeedback != null) {
					cacheResult(ticketFeedback);
				}
				else {
					entityCache.putResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
						TicketFeedbackImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
					TicketFeedbackImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ticketFeedback;
	}

	/**
	 * Returns the ticket feedback with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketFeedbackId the primary key of the ticket feedback
	 * @return the ticket feedback, or <code>null</code> if a ticket feedback with the primary key could not be found
	 */
	@Override
	public TicketFeedback fetchByPrimaryKey(long ticketFeedbackId) {
		return fetchByPrimaryKey((Serializable)ticketFeedbackId);
	}

	@Override
	public Map<Serializable, TicketFeedback> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TicketFeedback> map = new HashMap<Serializable, TicketFeedback>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TicketFeedback ticketFeedback = fetchByPrimaryKey(primaryKey);

			if (ticketFeedback != null) {
				map.put(primaryKey, ticketFeedback);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
					TicketFeedbackImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TicketFeedback)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TICKETFEEDBACK_WHERE_PKS_IN);

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

			for (TicketFeedback ticketFeedback : (List<TicketFeedback>)q.list()) {
				map.put(ticketFeedback.getPrimaryKeyObj(), ticketFeedback);

				cacheResult(ticketFeedback);

				uncachedPrimaryKeys.remove(ticketFeedback.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TicketFeedbackModelImpl.ENTITY_CACHE_ENABLED,
					TicketFeedbackImpl.class, primaryKey, nullModel);
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
	 * Returns all the ticket feedbacks.
	 *
	 * @return the ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket feedbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @return the range of ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findAll(int start, int end,
		OrderByComparator<TicketFeedback> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket feedbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFeedbackModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket feedbacks
	 * @param end the upper bound of the range of ticket feedbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ticket feedbacks
	 */
	@Override
	public List<TicketFeedback> findAll(int start, int end,
		OrderByComparator<TicketFeedback> orderByComparator,
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

		List<TicketFeedback> list = null;

		if (retrieveFromCache) {
			list = (List<TicketFeedback>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TICKETFEEDBACK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETFEEDBACK;

				if (pagination) {
					sql = sql.concat(TicketFeedbackModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TicketFeedback>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketFeedback>)QueryUtil.list(q,
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
	 * Removes all the ticket feedbacks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TicketFeedback ticketFeedback : findAll()) {
			remove(ticketFeedback);
		}
	}

	/**
	 * Returns the number of ticket feedbacks.
	 *
	 * @return the number of ticket feedbacks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETFEEDBACK);

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
		return TicketFeedbackModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ticket feedback persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TicketFeedbackImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_TICKETFEEDBACK = "SELECT ticketFeedback FROM TicketFeedback ticketFeedback";
	private static final String _SQL_SELECT_TICKETFEEDBACK_WHERE_PKS_IN = "SELECT ticketFeedback FROM TicketFeedback ticketFeedback WHERE ticketFeedbackId IN (";
	private static final String _SQL_SELECT_TICKETFEEDBACK_WHERE = "SELECT ticketFeedback FROM TicketFeedback ticketFeedback WHERE ";
	private static final String _SQL_COUNT_TICKETFEEDBACK = "SELECT COUNT(ticketFeedback) FROM TicketFeedback ticketFeedback";
	private static final String _SQL_COUNT_TICKETFEEDBACK_WHERE = "SELECT COUNT(ticketFeedback) FROM TicketFeedback ticketFeedback WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketFeedback.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketFeedback exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketFeedback exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TicketFeedbackPersistenceImpl.class);
}
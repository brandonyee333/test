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

import com.liferay.osb.exception.NoSuchTicketCommentException;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.impl.TicketCommentImpl;
import com.liferay.osb.model.impl.TicketCommentModelImpl;
import com.liferay.osb.service.persistence.TicketCommentPersistence;

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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the ticket comment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCommentPersistence
 * @see com.liferay.osb.service.persistence.TicketCommentUtil
 * @generated
 */
@ProviderType
public class TicketCommentPersistenceImpl extends BasePersistenceImpl<TicketComment>
	implements TicketCommentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketCommentUtil} to access the ticket comment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketCommentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTicketEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTicketEntryId", new String[] { Long.class.getName() },
			TicketCommentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketCommentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TICKETENTRYID = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTicketEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the ticket comments where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTicketEntryId(long ticketEntryId) {
		return findByTicketEntryId(ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket comments where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @return the range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTicketEntryId(long ticketEntryId,
		int start, int end) {
		return findByTicketEntryId(ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket comments where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketComment> orderByComparator) {
		return findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket comments where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator<TicketComment> orderByComparator,
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

		List<TicketComment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketComment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketComment ticketComment : list) {
					if ((ticketEntryId != ticketComment.getTicketEntryId())) {
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

			query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (!pagination) {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket comment in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket comment
	 * @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = fetchByTicketEntryId_First(ticketEntryId,
				orderByComparator);

		if (ticketComment != null) {
			return ticketComment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketCommentException(msg.toString());
	}

	/**
	 * Returns the first ticket comment in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator<TicketComment> orderByComparator) {
		List<TicketComment> list = findByTicketEntryId(ticketEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket comment in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket comment
	 * @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = fetchByTicketEntryId_Last(ticketEntryId,
				orderByComparator);

		if (ticketComment != null) {
			return ticketComment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketCommentException(msg.toString());
	}

	/**
	 * Returns the last ticket comment in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator<TicketComment> orderByComparator) {
		int count = countByTicketEntryId(ticketEntryId);

		if (count == 0) {
			return null;
		}

		List<TicketComment> list = findByTicketEntryId(ticketEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket comments before and after the current ticket comment in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketCommentId the primary key of the current ticket comment
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket comment
	 * @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	 */
	@Override
	public TicketComment[] findByTicketEntryId_PrevAndNext(
		long ticketCommentId, long ticketEntryId,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = findByPrimaryKey(ticketCommentId);

		Session session = null;

		try {
			session = openSession();

			TicketComment[] array = new TicketCommentImpl[3];

			array[0] = getByTicketEntryId_PrevAndNext(session, ticketComment,
					ticketEntryId, orderByComparator, true);

			array[1] = ticketComment;

			array[2] = getByTicketEntryId_PrevAndNext(session, ticketComment,
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

	protected TicketComment getByTicketEntryId_PrevAndNext(Session session,
		TicketComment ticketComment, long ticketEntryId,
		OrderByComparator<TicketComment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

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
			query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketComment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketComment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket comments where ticketEntryId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 */
	@Override
	public void removeByTicketEntryId(long ticketEntryId) {
		for (TicketComment ticketComment : findByTicketEntryId(ticketEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketComment);
		}
	}

	/**
	 * Returns the number of ticket comments where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket comments
	 */
	@Override
	public int countByTicketEntryId(long ticketEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TICKETENTRYID;

		Object[] finderArgs = new Object[] { ticketEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETCOMMENT_WHERE);

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

	private static final String _FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2 = "ticketComment.ticketEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TicketCommentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketCommentModelImpl.TYPE_COLUMN_BITMASK |
			TicketCommentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_T = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_T(long ticketEntryId, int type) {
		return findByTEI_T(ticketEntryId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @return the range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_T(long ticketEntryId, int type,
		int start, int end) {
		return findByTEI_T(ticketEntryId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_T(long ticketEntryId, int type,
		int start, int end, OrderByComparator<TicketComment> orderByComparator) {
		return findByTEI_T(ticketEntryId, type, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_T(long ticketEntryId, int type,
		int start, int end, OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T;
			finderArgs = new Object[] { ticketEntryId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T;
			finderArgs = new Object[] {
					ticketEntryId, type,
					
					start, end, orderByComparator
				};
		}

		List<TicketComment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketComment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketComment ticketComment : list) {
					if ((ticketEntryId != ticketComment.getTicketEntryId()) ||
							(type != ticketComment.getType())) {
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

			query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

				if (!pagination) {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket comment
	 * @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment findByTEI_T_First(long ticketEntryId, int type,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = fetchByTEI_T_First(ticketEntryId, type,
				orderByComparator);

		if (ticketComment != null) {
			return ticketComment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketCommentException(msg.toString());
	}

	/**
	 * Returns the first ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment fetchByTEI_T_First(long ticketEntryId, int type,
		OrderByComparator<TicketComment> orderByComparator) {
		List<TicketComment> list = findByTEI_T(ticketEntryId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket comment
	 * @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment findByTEI_T_Last(long ticketEntryId, int type,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = fetchByTEI_T_Last(ticketEntryId, type,
				orderByComparator);

		if (ticketComment != null) {
			return ticketComment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketCommentException(msg.toString());
	}

	/**
	 * Returns the last ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment fetchByTEI_T_Last(long ticketEntryId, int type,
		OrderByComparator<TicketComment> orderByComparator) {
		int count = countByTEI_T(ticketEntryId, type);

		if (count == 0) {
			return null;
		}

		List<TicketComment> list = findByTEI_T(ticketEntryId, type, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket comments before and after the current ticket comment in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketCommentId the primary key of the current ticket comment
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket comment
	 * @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	 */
	@Override
	public TicketComment[] findByTEI_T_PrevAndNext(long ticketCommentId,
		long ticketEntryId, int type,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = findByPrimaryKey(ticketCommentId);

		Session session = null;

		try {
			session = openSession();

			TicketComment[] array = new TicketCommentImpl[3];

			array[0] = getByTEI_T_PrevAndNext(session, ticketComment,
					ticketEntryId, type, orderByComparator, true);

			array[1] = ticketComment;

			array[2] = getByTEI_T_PrevAndNext(session, ticketComment,
					ticketEntryId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketComment getByTEI_T_PrevAndNext(Session session,
		TicketComment ticketComment, long ticketEntryId, int type,
		OrderByComparator<TicketComment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

		query.append(_FINDER_COLUMN_TEI_T_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_T_TYPE_2);

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
			query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketComment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketComment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket comments where ticketEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 */
	@Override
	public void removeByTEI_T(long ticketEntryId, int type) {
		for (TicketComment ticketComment : findByTEI_T(ticketEntryId, type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketComment);
		}
	}

	/**
	 * Returns the number of ticket comments where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the number of matching ticket comments
	 */
	@Override
	public int countByTEI_T(long ticketEntryId, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEI_T;

		Object[] finderArgs = new Object[] { ticketEntryId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

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

	private static final String _FINDER_COLUMN_TEI_T_TICKETENTRYID_2 = "ticketComment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_TYPE_2 = "ticketComment.type = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_V_S = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_V_S =
		new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTEI_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			TicketCommentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketCommentModelImpl.VISIBILITY_COLUMN_BITMASK |
			TicketCommentModelImpl.STATUS_COLUMN_BITMASK |
			TicketCommentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_V_S = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_V_S = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTEI_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @return the matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int visibility, int status) {
		return findByTEI_V_S(ticketEntryId, visibility, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @return the range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int visibility, int status, int start, int end) {
		return findByTEI_V_S(ticketEntryId, visibility, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int visibility, int status, int start, int end,
		OrderByComparator<TicketComment> orderByComparator) {
		return findByTEI_V_S(ticketEntryId, visibility, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int visibility, int status, int start, int end,
		OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_V_S;
			finderArgs = new Object[] { ticketEntryId, visibility, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_V_S;
			finderArgs = new Object[] {
					ticketEntryId, visibility, status,
					
					start, end, orderByComparator
				};
		}

		List<TicketComment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketComment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketComment ticketComment : list) {
					if ((ticketEntryId != ticketComment.getTicketEntryId()) ||
							(visibility != ticketComment.getVisibility()) ||
							(status != ticketComment.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_V_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_TEI_V_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(visibility);

				qPos.add(status);

				if (!pagination) {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket comment in the ordered set where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket comment
	 * @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment findByTEI_V_S_First(long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = fetchByTEI_V_S_First(ticketEntryId,
				visibility, status, orderByComparator);

		if (ticketComment != null) {
			return ticketComment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketCommentException(msg.toString());
	}

	/**
	 * Returns the first ticket comment in the ordered set where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment fetchByTEI_V_S_First(long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator) {
		List<TicketComment> list = findByTEI_V_S(ticketEntryId, visibility,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket comment in the ordered set where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket comment
	 * @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment findByTEI_V_S_Last(long ticketEntryId, int visibility,
		int status, OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = fetchByTEI_V_S_Last(ticketEntryId,
				visibility, status, orderByComparator);

		if (ticketComment != null) {
			return ticketComment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketCommentException(msg.toString());
	}

	/**
	 * Returns the last ticket comment in the ordered set where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment fetchByTEI_V_S_Last(long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator) {
		int count = countByTEI_V_S(ticketEntryId, visibility, status);

		if (count == 0) {
			return null;
		}

		List<TicketComment> list = findByTEI_V_S(ticketEntryId, visibility,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket comments before and after the current ticket comment in the ordered set where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketCommentId the primary key of the current ticket comment
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket comment
	 * @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	 */
	@Override
	public TicketComment[] findByTEI_V_S_PrevAndNext(long ticketCommentId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = findByPrimaryKey(ticketCommentId);

		Session session = null;

		try {
			session = openSession();

			TicketComment[] array = new TicketCommentImpl[3];

			array[0] = getByTEI_V_S_PrevAndNext(session, ticketComment,
					ticketEntryId, visibility, status, orderByComparator, true);

			array[1] = ticketComment;

			array[2] = getByTEI_V_S_PrevAndNext(session, ticketComment,
					ticketEntryId, visibility, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketComment getByTEI_V_S_PrevAndNext(Session session,
		TicketComment ticketComment, long ticketEntryId, int visibility,
		int status, OrderByComparator<TicketComment> orderByComparator,
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

		query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

		query.append(_FINDER_COLUMN_TEI_V_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_V_S_VISIBILITY_2);

		query.append(_FINDER_COLUMN_TEI_V_S_STATUS_2);

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
			query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(visibility);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketComment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketComment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @param statuses the statuses
	 * @return the matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int[] visibilities, int[] statuses) {
		return findByTEI_V_S(ticketEntryId, visibilities, statuses,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @param statuses the statuses
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @return the range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int[] visibilities, int[] statuses, int start, int end) {
		return findByTEI_V_S(ticketEntryId, visibilities, statuses, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @param statuses the statuses
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int[] visibilities, int[] statuses, int start, int end,
		OrderByComparator<TicketComment> orderByComparator) {
		return findByTEI_V_S(ticketEntryId, visibilities, statuses, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByTEI_V_S(long ticketEntryId,
		int[] visibilities, int[] statuses, int start, int end,
		OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		if (visibilities == null) {
			visibilities = new int[0];
		}
		else if (visibilities.length > 1) {
			visibilities = ArrayUtil.unique(visibilities);

			Arrays.sort(visibilities);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		if ((visibilities.length == 1) && (statuses.length == 1)) {
			return findByTEI_V_S(ticketEntryId, visibilities[0], statuses[0],
				start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(visibilities),
					StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(visibilities),
					StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<TicketComment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketComment>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_V_S,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketComment ticketComment : list) {
					if ((ticketEntryId != ticketComment.getTicketEntryId()) ||
							!ArrayUtil.contains(visibilities,
								ticketComment.getVisibility()) ||
							!ArrayUtil.contains(statuses,
								ticketComment.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_V_S_TICKETENTRYID_2);

			if (visibilities.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_TEI_V_S_VISIBILITY_7);

				query.append(StringUtil.merge(visibilities));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_TEI_V_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

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
				query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (!pagination) {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_V_S,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_V_S,
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
	 * Removes all the ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 */
	@Override
	public void removeByTEI_V_S(long ticketEntryId, int visibility, int status) {
		for (TicketComment ticketComment : findByTEI_V_S(ticketEntryId,
				visibility, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketComment);
		}
	}

	/**
	 * Returns the number of ticket comments where ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @return the number of matching ticket comments
	 */
	@Override
	public int countByTEI_V_S(long ticketEntryId, int visibility, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEI_V_S;

		Object[] finderArgs = new Object[] { ticketEntryId, visibility, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_V_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_TEI_V_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(visibility);

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

	/**
	 * Returns the number of ticket comments where ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @param statuses the statuses
	 * @return the number of matching ticket comments
	 */
	@Override
	public int countByTEI_V_S(long ticketEntryId, int[] visibilities,
		int[] statuses) {
		if (visibilities == null) {
			visibilities = new int[0];
		}
		else if (visibilities.length > 1) {
			visibilities = ArrayUtil.unique(visibilities);

			Arrays.sort(visibilities);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		Object[] finderArgs = new Object[] {
				ticketEntryId, StringUtil.merge(visibilities),
				StringUtil.merge(statuses)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_V_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_V_S_TICKETENTRYID_2);

			if (visibilities.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_TEI_V_S_VISIBILITY_7);

				query.append(StringUtil.merge(visibilities));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_TEI_V_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

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

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_V_S,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_V_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TEI_V_S_TICKETENTRYID_2 = "ticketComment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_V_S_VISIBILITY_2 = "ticketComment.visibility = ? AND ";
	private static final String _FINDER_COLUMN_TEI_V_S_VISIBILITY_7 = "ticketComment.visibility IN (";
	private static final String _FINDER_COLUMN_TEI_V_S_STATUS_2 = "ticketComment.status = ?";
	private static final String _FINDER_COLUMN_TEI_V_S_STATUS_7 = "ticketComment.status IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_V_S =
		new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_TEI_V_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S =
		new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_TEI_V_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			TicketCommentModelImpl.USERID_COLUMN_BITMASK |
			TicketCommentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketCommentModelImpl.VISIBILITY_COLUMN_BITMASK |
			TicketCommentModelImpl.STATUS_COLUMN_BITMASK |
			TicketCommentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_TEI_V_S = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_TEI_V_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_TEI_V_S =
		new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_TEI_V_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @return the matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status) {
		return findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @return the range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status, int start, int end) {
		return findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status, int start, int end,
		OrderByComparator<TicketComment> orderByComparator) {
		return findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status, int start, int end,
		OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S;
			finderArgs = new Object[] { userId, ticketEntryId, visibility, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_V_S;
			finderArgs = new Object[] {
					userId, ticketEntryId, visibility, status,
					
					start, end, orderByComparator
				};
		}

		List<TicketComment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketComment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketComment ticketComment : list) {
					if ((userId != ticketComment.getUserId()) ||
							(ticketEntryId != ticketComment.getTicketEntryId()) ||
							(visibility != ticketComment.getVisibility()) ||
							(status != ticketComment.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_V_S_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				qPos.add(visibility);

				qPos.add(status);

				if (!pagination) {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket comment
	 * @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment findByU_TEI_V_S_First(long userId, long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = fetchByU_TEI_V_S_First(userId,
				ticketEntryId, visibility, status, orderByComparator);

		if (ticketComment != null) {
			return ticketComment;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketCommentException(msg.toString());
	}

	/**
	 * Returns the first ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment fetchByU_TEI_V_S_First(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator) {
		List<TicketComment> list = findByU_TEI_V_S(userId, ticketEntryId,
				visibility, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket comment
	 * @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment findByU_TEI_V_S_Last(long userId, long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = fetchByU_TEI_V_S_Last(userId,
				ticketEntryId, visibility, status, orderByComparator);

		if (ticketComment != null) {
			return ticketComment;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketCommentException(msg.toString());
	}

	/**
	 * Returns the last ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment fetchByU_TEI_V_S_Last(long userId, long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator) {
		int count = countByU_TEI_V_S(userId, ticketEntryId, visibility, status);

		if (count == 0) {
			return null;
		}

		List<TicketComment> list = findByU_TEI_V_S(userId, ticketEntryId,
				visibility, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket comments before and after the current ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketCommentId the primary key of the current ticket comment
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket comment
	 * @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	 */
	@Override
	public TicketComment[] findByU_TEI_V_S_PrevAndNext(long ticketCommentId,
		long userId, long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = findByPrimaryKey(ticketCommentId);

		Session session = null;

		try {
			session = openSession();

			TicketComment[] array = new TicketCommentImpl[3];

			array[0] = getByU_TEI_V_S_PrevAndNext(session, ticketComment,
					userId, ticketEntryId, visibility, status,
					orderByComparator, true);

			array[1] = ticketComment;

			array[2] = getByU_TEI_V_S_PrevAndNext(session, ticketComment,
					userId, ticketEntryId, visibility, status,
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

	protected TicketComment getByU_TEI_V_S_PrevAndNext(Session session,
		TicketComment ticketComment, long userId, long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketComment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

		query.append(_FINDER_COLUMN_U_TEI_V_S_USERID_2);

		query.append(_FINDER_COLUMN_U_TEI_V_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_U_TEI_V_S_VISIBILITY_2);

		query.append(_FINDER_COLUMN_U_TEI_V_S_STATUS_2);

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
			query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(ticketEntryId);

		qPos.add(visibility);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketComment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketComment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @param statuses the statuses
	 * @return the matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses) {
		return findByU_TEI_V_S(userId, ticketEntryId, visibilities, statuses,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @param statuses the statuses
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @return the range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses, int start, int end) {
		return findByU_TEI_V_S(userId, ticketEntryId, visibilities, statuses,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @param statuses the statuses
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses, int start, int end,
		OrderByComparator<TicketComment> orderByComparator) {
		return findByU_TEI_V_S(userId, ticketEntryId, visibilities, statuses,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses, int start, int end,
		OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		if (visibilities == null) {
			visibilities = new int[0];
		}
		else if (visibilities.length > 1) {
			visibilities = ArrayUtil.unique(visibilities);

			Arrays.sort(visibilities);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		if ((visibilities.length == 1) && (statuses.length == 1)) {
			return findByU_TEI_V_S(userId, ticketEntryId, visibilities[0],
				statuses[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					userId, ticketEntryId, StringUtil.merge(visibilities),
					StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					userId, ticketEntryId, StringUtil.merge(visibilities),
					StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<TicketComment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketComment>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_V_S,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketComment ticketComment : list) {
					if ((userId != ticketComment.getUserId()) ||
							(ticketEntryId != ticketComment.getTicketEntryId()) ||
							!ArrayUtil.contains(visibilities,
								ticketComment.getVisibility()) ||
							!ArrayUtil.contains(statuses,
								ticketComment.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_V_S_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_TICKETENTRYID_2);

			if (visibilities.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_TEI_V_S_VISIBILITY_7);

				query.append(StringUtil.merge(visibilities));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_TEI_V_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

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
				query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				if (!pagination) {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_V_S,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_V_S,
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
	 * Removes all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 */
	@Override
	public void removeByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status) {
		for (TicketComment ticketComment : findByU_TEI_V_S(userId,
				ticketEntryId, visibility, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ticketComment);
		}
	}

	/**
	 * Returns the number of ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @return the number of matching ticket comments
	 */
	@Override
	public int countByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_TEI_V_S;

		Object[] finderArgs = new Object[] {
				userId, ticketEntryId, visibility, status
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_V_S_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				qPos.add(visibility);

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

	/**
	 * Returns the number of ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = any &#63; and status = any &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @param statuses the statuses
	 * @return the number of matching ticket comments
	 */
	@Override
	public int countByU_TEI_V_S(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses) {
		if (visibilities == null) {
			visibilities = new int[0];
		}
		else if (visibilities.length > 1) {
			visibilities = ArrayUtil.unique(visibilities);

			Arrays.sort(visibilities);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		Object[] finderArgs = new Object[] {
				userId, ticketEntryId, StringUtil.merge(visibilities),
				StringUtil.merge(statuses)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_TEI_V_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_V_S_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_TICKETENTRYID_2);

			if (visibilities.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_TEI_V_S_VISIBILITY_7);

				query.append(StringUtil.merge(visibilities));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_TEI_V_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

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

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_TEI_V_S,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_TEI_V_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_U_TEI_V_S_USERID_2 = "ticketComment.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_TICKETENTRYID_2 = "ticketComment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_VISIBILITY_2 = "ticketComment.visibility = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_VISIBILITY_7 = "ticketComment.visibility IN (";
	private static final String _FINDER_COLUMN_U_TEI_V_S_STATUS_2 = "ticketComment.status = ?";
	private static final String _FINDER_COLUMN_U_TEI_V_S_STATUS_7 = "ticketComment.status IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_V_S_T =
		new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_TEI_V_S_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S_T =
		new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED,
			TicketCommentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_TEI_V_S_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			TicketCommentModelImpl.USERID_COLUMN_BITMASK |
			TicketCommentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketCommentModelImpl.VISIBILITY_COLUMN_BITMASK |
			TicketCommentModelImpl.STATUS_COLUMN_BITMASK |
			TicketCommentModelImpl.TYPE_COLUMN_BITMASK |
			TicketCommentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_TEI_V_S_T = new FinderPath(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_TEI_V_S_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param type the type
	 * @return the matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S_T(long userId,
		long ticketEntryId, int visibility, int status, int type) {
		return findByU_TEI_V_S_T(userId, ticketEntryId, visibility, status,
			type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param type the type
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @return the range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S_T(long userId,
		long ticketEntryId, int visibility, int status, int type, int start,
		int end) {
		return findByU_TEI_V_S_T(userId, ticketEntryId, visibility, status,
			type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param type the type
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S_T(long userId,
		long ticketEntryId, int visibility, int status, int type, int start,
		int end, OrderByComparator<TicketComment> orderByComparator) {
		return findByU_TEI_V_S_T(userId, ticketEntryId, visibility, status,
			type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param type the type
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket comments
	 */
	@Override
	public List<TicketComment> findByU_TEI_V_S_T(long userId,
		long ticketEntryId, int visibility, int status, int type, int start,
		int end, OrderByComparator<TicketComment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S_T;
			finderArgs = new Object[] {
					userId, ticketEntryId, visibility, status, type
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_V_S_T;
			finderArgs = new Object[] {
					userId, ticketEntryId, visibility, status, type,
					
					start, end, orderByComparator
				};
		}

		List<TicketComment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketComment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketComment ticketComment : list) {
					if ((userId != ticketComment.getUserId()) ||
							(ticketEntryId != ticketComment.getTicketEntryId()) ||
							(visibility != ticketComment.getVisibility()) ||
							(status != ticketComment.getStatus()) ||
							(type != ticketComment.getType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_V_S_T_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_T_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_T_VISIBILITY_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_T_STATUS_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				qPos.add(visibility);

				qPos.add(status);

				qPos.add(type);

				if (!pagination) {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket comment
	 * @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment findByU_TEI_V_S_T_First(long userId,
		long ticketEntryId, int visibility, int status, int type,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = fetchByU_TEI_V_S_T_First(userId,
				ticketEntryId, visibility, status, type, orderByComparator);

		if (ticketComment != null) {
			return ticketComment;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketCommentException(msg.toString());
	}

	/**
	 * Returns the first ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment fetchByU_TEI_V_S_T_First(long userId,
		long ticketEntryId, int visibility, int status, int type,
		OrderByComparator<TicketComment> orderByComparator) {
		List<TicketComment> list = findByU_TEI_V_S_T(userId, ticketEntryId,
				visibility, status, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket comment
	 * @throws NoSuchTicketCommentException if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment findByU_TEI_V_S_T_Last(long userId,
		long ticketEntryId, int visibility, int status, int type,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = fetchByU_TEI_V_S_T_Last(userId,
				ticketEntryId, visibility, status, type, orderByComparator);

		if (ticketComment != null) {
			return ticketComment;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketCommentException(msg.toString());
	}

	/**
	 * Returns the last ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket comment, or <code>null</code> if a matching ticket comment could not be found
	 */
	@Override
	public TicketComment fetchByU_TEI_V_S_T_Last(long userId,
		long ticketEntryId, int visibility, int status, int type,
		OrderByComparator<TicketComment> orderByComparator) {
		int count = countByU_TEI_V_S_T(userId, ticketEntryId, visibility,
				status, type);

		if (count == 0) {
			return null;
		}

		List<TicketComment> list = findByU_TEI_V_S_T(userId, ticketEntryId,
				visibility, status, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket comments before and after the current ticket comment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	 *
	 * @param ticketCommentId the primary key of the current ticket comment
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket comment
	 * @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	 */
	@Override
	public TicketComment[] findByU_TEI_V_S_T_PrevAndNext(long ticketCommentId,
		long userId, long ticketEntryId, int visibility, int status, int type,
		OrderByComparator<TicketComment> orderByComparator)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = findByPrimaryKey(ticketCommentId);

		Session session = null;

		try {
			session = openSession();

			TicketComment[] array = new TicketCommentImpl[3];

			array[0] = getByU_TEI_V_S_T_PrevAndNext(session, ticketComment,
					userId, ticketEntryId, visibility, status, type,
					orderByComparator, true);

			array[1] = ticketComment;

			array[2] = getByU_TEI_V_S_T_PrevAndNext(session, ticketComment,
					userId, ticketEntryId, visibility, status, type,
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

	protected TicketComment getByU_TEI_V_S_T_PrevAndNext(Session session,
		TicketComment ticketComment, long userId, long ticketEntryId,
		int visibility, int status, int type,
		OrderByComparator<TicketComment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_TICKETCOMMENT_WHERE);

		query.append(_FINDER_COLUMN_U_TEI_V_S_T_USERID_2);

		query.append(_FINDER_COLUMN_U_TEI_V_S_T_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_U_TEI_V_S_T_VISIBILITY_2);

		query.append(_FINDER_COLUMN_U_TEI_V_S_T_STATUS_2);

		query.append(_FINDER_COLUMN_U_TEI_V_S_T_TYPE_2);

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
			query.append(TicketCommentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(ticketEntryId);

		qPos.add(visibility);

		qPos.add(status);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketComment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketComment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param type the type
	 */
	@Override
	public void removeByU_TEI_V_S_T(long userId, long ticketEntryId,
		int visibility, int status, int type) {
		for (TicketComment ticketComment : findByU_TEI_V_S_T(userId,
				ticketEntryId, visibility, status, type, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ticketComment);
		}
	}

	/**
	 * Returns the number of ticket comments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param type the type
	 * @return the number of matching ticket comments
	 */
	@Override
	public int countByU_TEI_V_S_T(long userId, long ticketEntryId,
		int visibility, int status, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_TEI_V_S_T;

		Object[] finderArgs = new Object[] {
				userId, ticketEntryId, visibility, status, type
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_TICKETCOMMENT_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_V_S_T_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_T_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_T_VISIBILITY_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_T_STATUS_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				qPos.add(visibility);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_U_TEI_V_S_T_USERID_2 = "ticketComment.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_T_TICKETENTRYID_2 = "ticketComment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_T_VISIBILITY_2 = "ticketComment.visibility = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_T_STATUS_2 = "ticketComment.status = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_T_TYPE_2 = "ticketComment.type = ?";

	public TicketCommentPersistenceImpl() {
		setModelClass(TicketComment.class);
	}

	/**
	 * Caches the ticket comment in the entity cache if it is enabled.
	 *
	 * @param ticketComment the ticket comment
	 */
	@Override
	public void cacheResult(TicketComment ticketComment) {
		entityCache.putResult(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentImpl.class, ticketComment.getPrimaryKey(),
			ticketComment);

		ticketComment.resetOriginalValues();
	}

	/**
	 * Caches the ticket comments in the entity cache if it is enabled.
	 *
	 * @param ticketComments the ticket comments
	 */
	@Override
	public void cacheResult(List<TicketComment> ticketComments) {
		for (TicketComment ticketComment : ticketComments) {
			if (entityCache.getResult(
						TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
						TicketCommentImpl.class, ticketComment.getPrimaryKey()) == null) {
				cacheResult(ticketComment);
			}
			else {
				ticketComment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket comments.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TicketCommentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket comment.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketComment ticketComment) {
		entityCache.removeResult(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentImpl.class, ticketComment.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TicketComment> ticketComments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketComment ticketComment : ticketComments) {
			entityCache.removeResult(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
				TicketCommentImpl.class, ticketComment.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ticket comment with the primary key. Does not add the ticket comment to the database.
	 *
	 * @param ticketCommentId the primary key for the new ticket comment
	 * @return the new ticket comment
	 */
	@Override
	public TicketComment create(long ticketCommentId) {
		TicketComment ticketComment = new TicketCommentImpl();

		ticketComment.setNew(true);
		ticketComment.setPrimaryKey(ticketCommentId);

		return ticketComment;
	}

	/**
	 * Removes the ticket comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketCommentId the primary key of the ticket comment
	 * @return the ticket comment that was removed
	 * @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	 */
	@Override
	public TicketComment remove(long ticketCommentId)
		throws NoSuchTicketCommentException {
		return remove((Serializable)ticketCommentId);
	}

	/**
	 * Removes the ticket comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket comment
	 * @return the ticket comment that was removed
	 * @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	 */
	@Override
	public TicketComment remove(Serializable primaryKey)
		throws NoSuchTicketCommentException {
		Session session = null;

		try {
			session = openSession();

			TicketComment ticketComment = (TicketComment)session.get(TicketCommentImpl.class,
					primaryKey);

			if (ticketComment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketCommentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketComment);
		}
		catch (NoSuchTicketCommentException nsee) {
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
	protected TicketComment removeImpl(TicketComment ticketComment) {
		ticketComment = toUnwrappedModel(ticketComment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ticketComment)) {
				ticketComment = (TicketComment)session.get(TicketCommentImpl.class,
						ticketComment.getPrimaryKeyObj());
			}

			if (ticketComment != null) {
				session.delete(ticketComment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ticketComment != null) {
			clearCache(ticketComment);
		}

		return ticketComment;
	}

	@Override
	public TicketComment updateImpl(TicketComment ticketComment) {
		ticketComment = toUnwrappedModel(ticketComment);

		boolean isNew = ticketComment.isNew();

		TicketCommentModelImpl ticketCommentModelImpl = (TicketCommentModelImpl)ticketComment;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (ticketComment.getCreateDate() == null)) {
			if (serviceContext == null) {
				ticketComment.setCreateDate(now);
			}
			else {
				ticketComment.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!ticketCommentModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				ticketComment.setModifiedDate(now);
			}
			else {
				ticketComment.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (ticketComment.isNew()) {
				session.save(ticketComment);

				ticketComment.setNew(false);
			}
			else {
				ticketComment = (TicketComment)session.merge(ticketComment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TicketCommentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					ticketCommentModelImpl.getTicketEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
				args);

			args = new Object[] {
					ticketCommentModelImpl.getTicketEntryId(),
					ticketCommentModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T,
				args);

			args = new Object[] {
					ticketCommentModelImpl.getTicketEntryId(),
					ticketCommentModelImpl.getVisibility(),
					ticketCommentModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_V_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_V_S,
				args);

			args = new Object[] {
					ticketCommentModelImpl.getUserId(),
					ticketCommentModelImpl.getTicketEntryId(),
					ticketCommentModelImpl.getVisibility(),
					ticketCommentModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_TEI_V_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S,
				args);

			args = new Object[] {
					ticketCommentModelImpl.getUserId(),
					ticketCommentModelImpl.getTicketEntryId(),
					ticketCommentModelImpl.getVisibility(),
					ticketCommentModelImpl.getStatus(),
					ticketCommentModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_TEI_V_S_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S_T,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ticketCommentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketCommentModelImpl.getOriginalTicketEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);

				args = new Object[] { ticketCommentModelImpl.getTicketEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);
			}

			if ((ticketCommentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketCommentModelImpl.getOriginalTicketEntryId(),
						ticketCommentModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T,
					args);

				args = new Object[] {
						ticketCommentModelImpl.getTicketEntryId(),
						ticketCommentModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T,
					args);
			}

			if ((ticketCommentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_V_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketCommentModelImpl.getOriginalTicketEntryId(),
						ticketCommentModelImpl.getOriginalVisibility(),
						ticketCommentModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_V_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_V_S,
					args);

				args = new Object[] {
						ticketCommentModelImpl.getTicketEntryId(),
						ticketCommentModelImpl.getVisibility(),
						ticketCommentModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_V_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_V_S,
					args);
			}

			if ((ticketCommentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketCommentModelImpl.getOriginalUserId(),
						ticketCommentModelImpl.getOriginalTicketEntryId(),
						ticketCommentModelImpl.getOriginalVisibility(),
						ticketCommentModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_TEI_V_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S,
					args);

				args = new Object[] {
						ticketCommentModelImpl.getUserId(),
						ticketCommentModelImpl.getTicketEntryId(),
						ticketCommentModelImpl.getVisibility(),
						ticketCommentModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_TEI_V_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S,
					args);
			}

			if ((ticketCommentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketCommentModelImpl.getOriginalUserId(),
						ticketCommentModelImpl.getOriginalTicketEntryId(),
						ticketCommentModelImpl.getOriginalVisibility(),
						ticketCommentModelImpl.getOriginalStatus(),
						ticketCommentModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_TEI_V_S_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S_T,
					args);

				args = new Object[] {
						ticketCommentModelImpl.getUserId(),
						ticketCommentModelImpl.getTicketEntryId(),
						ticketCommentModelImpl.getVisibility(),
						ticketCommentModelImpl.getStatus(),
						ticketCommentModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_TEI_V_S_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S_T,
					args);
			}
		}

		entityCache.putResult(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
			TicketCommentImpl.class, ticketComment.getPrimaryKey(),
			ticketComment, false);

		ticketComment.resetOriginalValues();

		return ticketComment;
	}

	protected TicketComment toUnwrappedModel(TicketComment ticketComment) {
		if (ticketComment instanceof TicketCommentImpl) {
			return ticketComment;
		}

		TicketCommentImpl ticketCommentImpl = new TicketCommentImpl();

		ticketCommentImpl.setNew(ticketComment.isNew());
		ticketCommentImpl.setPrimaryKey(ticketComment.getPrimaryKey());

		ticketCommentImpl.setTicketCommentId(ticketComment.getTicketCommentId());
		ticketCommentImpl.setUserId(ticketComment.getUserId());
		ticketCommentImpl.setUserName(ticketComment.getUserName());
		ticketCommentImpl.setCreateDate(ticketComment.getCreateDate());
		ticketCommentImpl.setModifiedDate(ticketComment.getModifiedDate());
		ticketCommentImpl.setTicketEntryId(ticketComment.getTicketEntryId());
		ticketCommentImpl.setBody(ticketComment.getBody());
		ticketCommentImpl.setType(ticketComment.getType());
		ticketCommentImpl.setFormat(ticketComment.getFormat());
		ticketCommentImpl.setVisibility(ticketComment.getVisibility());
		ticketCommentImpl.setSettings(ticketComment.getSettings());
		ticketCommentImpl.setStatus(ticketComment.getStatus());

		return ticketCommentImpl;
	}

	/**
	 * Returns the ticket comment with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket comment
	 * @return the ticket comment
	 * @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	 */
	@Override
	public TicketComment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTicketCommentException {
		TicketComment ticketComment = fetchByPrimaryKey(primaryKey);

		if (ticketComment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTicketCommentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ticketComment;
	}

	/**
	 * Returns the ticket comment with the primary key or throws a {@link NoSuchTicketCommentException} if it could not be found.
	 *
	 * @param ticketCommentId the primary key of the ticket comment
	 * @return the ticket comment
	 * @throws NoSuchTicketCommentException if a ticket comment with the primary key could not be found
	 */
	@Override
	public TicketComment findByPrimaryKey(long ticketCommentId)
		throws NoSuchTicketCommentException {
		return findByPrimaryKey((Serializable)ticketCommentId);
	}

	/**
	 * Returns the ticket comment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket comment
	 * @return the ticket comment, or <code>null</code> if a ticket comment with the primary key could not be found
	 */
	@Override
	public TicketComment fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
				TicketCommentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TicketComment ticketComment = (TicketComment)serializable;

		if (ticketComment == null) {
			Session session = null;

			try {
				session = openSession();

				ticketComment = (TicketComment)session.get(TicketCommentImpl.class,
						primaryKey);

				if (ticketComment != null) {
					cacheResult(ticketComment);
				}
				else {
					entityCache.putResult(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
						TicketCommentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
					TicketCommentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ticketComment;
	}

	/**
	 * Returns the ticket comment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketCommentId the primary key of the ticket comment
	 * @return the ticket comment, or <code>null</code> if a ticket comment with the primary key could not be found
	 */
	@Override
	public TicketComment fetchByPrimaryKey(long ticketCommentId) {
		return fetchByPrimaryKey((Serializable)ticketCommentId);
	}

	@Override
	public Map<Serializable, TicketComment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TicketComment> map = new HashMap<Serializable, TicketComment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TicketComment ticketComment = fetchByPrimaryKey(primaryKey);

			if (ticketComment != null) {
				map.put(primaryKey, ticketComment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
					TicketCommentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TicketComment)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TICKETCOMMENT_WHERE_PKS_IN);

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

			for (TicketComment ticketComment : (List<TicketComment>)q.list()) {
				map.put(ticketComment.getPrimaryKeyObj(), ticketComment);

				cacheResult(ticketComment);

				uncachedPrimaryKeys.remove(ticketComment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TicketCommentModelImpl.ENTITY_CACHE_ENABLED,
					TicketCommentImpl.class, primaryKey, nullModel);
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
	 * Returns all the ticket comments.
	 *
	 * @return the ticket comments
	 */
	@Override
	public List<TicketComment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @return the range of ticket comments
	 */
	@Override
	public List<TicketComment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket comments
	 */
	@Override
	public List<TicketComment> findAll(int start, int end,
		OrderByComparator<TicketComment> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket comments
	 * @param end the upper bound of the range of ticket comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ticket comments
	 */
	@Override
	public List<TicketComment> findAll(int start, int end,
		OrderByComparator<TicketComment> orderByComparator,
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

		List<TicketComment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketComment>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TICKETCOMMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETCOMMENT;

				if (pagination) {
					sql = sql.concat(TicketCommentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketComment>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ticket comments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TicketComment ticketComment : findAll()) {
			remove(ticketComment);
		}
	}

	/**
	 * Returns the number of ticket comments.
	 *
	 * @return the number of ticket comments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETCOMMENT);

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
		return TicketCommentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ticket comment persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TicketCommentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_TICKETCOMMENT = "SELECT ticketComment FROM TicketComment ticketComment";
	private static final String _SQL_SELECT_TICKETCOMMENT_WHERE_PKS_IN = "SELECT ticketComment FROM TicketComment ticketComment WHERE ticketCommentId IN (";
	private static final String _SQL_SELECT_TICKETCOMMENT_WHERE = "SELECT ticketComment FROM TicketComment ticketComment WHERE ";
	private static final String _SQL_COUNT_TICKETCOMMENT = "SELECT COUNT(ticketComment) FROM TicketComment ticketComment";
	private static final String _SQL_COUNT_TICKETCOMMENT_WHERE = "SELECT COUNT(ticketComment) FROM TicketComment ticketComment WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketComment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketComment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketComment exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TicketCommentPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type", "settings"
			});
}
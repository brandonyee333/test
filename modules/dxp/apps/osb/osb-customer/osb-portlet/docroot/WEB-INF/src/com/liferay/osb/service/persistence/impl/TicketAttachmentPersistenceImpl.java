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

import com.liferay.osb.exception.NoSuchTicketAttachmentException;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.impl.TicketAttachmentImpl;
import com.liferay.osb.model.impl.TicketAttachmentModelImpl;
import com.liferay.osb.service.persistence.TicketAttachmentPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the ticket attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentPersistence
 * @see com.liferay.osb.service.persistence.TicketAttachmentUtil
 * @generated
 */
@ProviderType
public class TicketAttachmentPersistenceImpl extends BasePersistenceImpl<TicketAttachment>
	implements TicketAttachmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketAttachmentUtil} to access the ticket attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketAttachmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CD_TEI = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCD_TEI",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_CD_TEI = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByCD_TEI",
			new String[] { Date.class.getName(), Long.class.getName() });

	/**
	 * Returns all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId) {
		return findByCD_TEI(createDate, ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId, int start, int end) {
		return findByCD_TEI(createDate, ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByCD_TEI(createDate, ticketEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CD_TEI;
		finderArgs = new Object[] {
				createDate, ticketEntryId,
				
				start, end, orderByComparator
			};

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if ((createDate.getTime() <= ticketAttachment.getCreateDate()
																	 .getTime()) ||
							(ticketEntryId != ticketAttachment.getTicketEntryId())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_CD_TEI_TICKETENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				qPos.add(ticketEntryId);

				if (!pagination) {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketAttachment>)QueryUtil.list(q,
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
	 * Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByCD_TEI_First(Date createDate,
		long ticketEntryId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByCD_TEI_First(createDate,
				ticketEntryId, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByCD_TEI_First(Date createDate,
		long ticketEntryId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		List<TicketAttachment> list = findByCD_TEI(createDate, ticketEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByCD_TEI_Last(Date createDate,
		long ticketEntryId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByCD_TEI_Last(createDate,
				ticketEntryId, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByCD_TEI_Last(Date createDate,
		long ticketEntryId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		int count = countByCD_TEI(createDate, ticketEntryId);

		if (count == 0) {
			return null;
		}

		List<TicketAttachment> list = findByCD_TEI(createDate, ticketEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment[] findByCD_TEI_PrevAndNext(
		long ticketAttachmentId, Date createDate, long ticketEntryId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByCD_TEI_PrevAndNext(session, ticketAttachment,
					createDate, ticketEntryId, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByCD_TEI_PrevAndNext(session, ticketAttachment,
					createDate, ticketEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByCD_TEI_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, Date createDate, long ticketEntryId,
		OrderByComparator<TicketAttachment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_2);
		}

		query.append(_FINDER_COLUMN_CD_TEI_TICKETENTRYID_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		qPos.add(ticketEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 */
	@Override
	public void removeByCD_TEI(Date createDate, long ticketEntryId) {
		for (TicketAttachment ticketAttachment : findByCD_TEI(createDate,
				ticketEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Returns the number of ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByCD_TEI(Date createDate, long ticketEntryId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_CD_TEI;

		Object[] finderArgs = new Object[] { createDate, ticketEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_CD_TEI_TICKETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

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

	private static final String _FINDER_COLUMN_CD_TEI_CREATEDATE_1 = "ticketAttachment.createDate IS NULL AND ";
	private static final String _FINDER_COLUMN_CD_TEI_CREATEDATE_2 = "ticketAttachment.createDate < ? AND ";
	private static final String _FINDER_COLUMN_CD_TEI_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_TSI = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_TSI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_TSI",
			new String[] { Long.class.getName(), Long.class.getName() },
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.TICKETSOLUTIONID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_TSI = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_TSI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId) {
		return findByTEI_TSI(ticketEntryId, ticketSolutionId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end) {
		return findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI;
			finderArgs = new Object[] { ticketEntryId, ticketSolutionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_TSI;
			finderArgs = new Object[] {
					ticketEntryId, ticketSolutionId,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
							(ticketSolutionId != ticketAttachment.getTicketSolutionId())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(ticketSolutionId);

				if (!pagination) {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketAttachment>)QueryUtil.list(q,
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
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByTEI_TSI_First(ticketEntryId,
				ticketSolutionId, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", ticketSolutionId=");
		msg.append(ticketSolutionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		List<TicketAttachment> list = findByTEI_TSI(ticketEntryId,
				ticketSolutionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByTEI_TSI_Last(ticketEntryId,
				ticketSolutionId, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", ticketSolutionId=");
		msg.append(ticketSolutionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		int count = countByTEI_TSI(ticketEntryId, ticketSolutionId);

		if (count == 0) {
			return null;
		}

		List<TicketAttachment> list = findByTEI_TSI(ticketEntryId,
				ticketSolutionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment[] findByTEI_TSI_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, long ticketSolutionId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByTEI_TSI_PrevAndNext(session, ticketAttachment,
					ticketEntryId, ticketSolutionId, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByTEI_TSI_PrevAndNext(session, ticketAttachment,
					ticketEntryId, ticketSolutionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByTEI_TSI_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, long ticketEntryId,
		long ticketSolutionId,
		OrderByComparator<TicketAttachment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(ticketSolutionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 */
	@Override
	public void removeByTEI_TSI(long ticketEntryId, long ticketSolutionId) {
		for (TicketAttachment ticketAttachment : findByTEI_TSI(ticketEntryId,
				ticketSolutionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByTEI_TSI(long ticketEntryId, long ticketSolutionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEI_TSI;

		Object[] finderArgs = new Object[] { ticketEntryId, ticketSolutionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(ticketSolutionId);

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

	private static final String _FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2 = "ticketAttachment.ticketSolutionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.STATUS_COLUMN_BITMASK |
			TicketAttachmentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_S(long ticketEntryId, int status) {
		return findByTEI_S(ticketEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_S(long ticketEntryId, int status,
		int start, int end) {
		return findByTEI_S(ticketEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_S(long ticketEntryId, int status,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByTEI_S(ticketEntryId, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_S(long ticketEntryId, int status,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S;
			finderArgs = new Object[] { ticketEntryId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S;
			finderArgs = new Object[] {
					ticketEntryId, status,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
							(status != ticketAttachment.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(status);

				if (!pagination) {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketAttachment>)QueryUtil.list(q,
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
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByTEI_S_First(long ticketEntryId, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByTEI_S_First(ticketEntryId,
				status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByTEI_S_First(long ticketEntryId, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		List<TicketAttachment> list = findByTEI_S(ticketEntryId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByTEI_S_Last(long ticketEntryId, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByTEI_S_Last(ticketEntryId,
				status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByTEI_S_Last(long ticketEntryId, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		int count = countByTEI_S(ticketEntryId, status);

		if (count == 0) {
			return null;
		}

		List<TicketAttachment> list = findByTEI_S(ticketEntryId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment[] findByTEI_S_PrevAndNext(long ticketAttachmentId,
		long ticketEntryId, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByTEI_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, status, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByTEI_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByTEI_S_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, long ticketEntryId, int status,
		OrderByComparator<TicketAttachment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_S_STATUS_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket attachments where ticketEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 */
	@Override
	public void removeByTEI_S(long ticketEntryId, int status) {
		for (TicketAttachment ticketAttachment : findByTEI_S(ticketEntryId,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByTEI_S(long ticketEntryId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEI_S;

		Object[] finderArgs = new Object[] { ticketEntryId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

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

	private static final String _FINDER_COLUMN_TEI_S_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_S_STATUS_2 = "ticketAttachment.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_T_DD = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByT_DD",
			new String[] {
				Integer.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_DD = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByT_DD",
			new String[] { Integer.class.getName(), Date.class.getName() });

	/**
	 * Returns all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByT_DD(int type, Date deleteDate) {
		return findByT_DD(type, deleteDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByT_DD(int type, Date deleteDate,
		int start, int end) {
		return findByT_DD(type, deleteDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByT_DD(int type, Date deleteDate,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByT_DD(type, deleteDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByT_DD(int type, Date deleteDate,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_T_DD;
		finderArgs = new Object[] {
				type, deleteDate,
				
				start, end, orderByComparator
			};

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if ((type != ticketAttachment.getType()) ||
							(deleteDate.getTime() <= ticketAttachment.getDeleteDate()
																		 .getTime())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_T_DD_TYPE_2);

			boolean bindDeleteDate = false;

			if (deleteDate == null) {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_1);
			}
			else {
				bindDeleteDate = true;

				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				if (bindDeleteDate) {
					qPos.add(new Timestamp(deleteDate.getTime()));
				}

				if (!pagination) {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketAttachment>)QueryUtil.list(q,
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
	 * Returns the first ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByT_DD_First(int type, Date deleteDate,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByT_DD_First(type, deleteDate,
				orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(", deleteDate=");
		msg.append(deleteDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByT_DD_First(int type, Date deleteDate,
		OrderByComparator<TicketAttachment> orderByComparator) {
		List<TicketAttachment> list = findByT_DD(type, deleteDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByT_DD_Last(int type, Date deleteDate,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByT_DD_Last(type, deleteDate,
				orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(", deleteDate=");
		msg.append(deleteDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByT_DD_Last(int type, Date deleteDate,
		OrderByComparator<TicketAttachment> orderByComparator) {
		int count = countByT_DD(type, deleteDate);

		if (count == 0) {
			return null;
		}

		List<TicketAttachment> list = findByT_DD(type, deleteDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment[] findByT_DD_PrevAndNext(long ticketAttachmentId,
		int type, Date deleteDate,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByT_DD_PrevAndNext(session, ticketAttachment, type,
					deleteDate, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByT_DD_PrevAndNext(session, ticketAttachment, type,
					deleteDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByT_DD_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, int type, Date deleteDate,
		OrderByComparator<TicketAttachment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_T_DD_TYPE_2);

		boolean bindDeleteDate = false;

		if (deleteDate == null) {
			query.append(_FINDER_COLUMN_T_DD_DELETEDATE_1);
		}
		else {
			bindDeleteDate = true;

			query.append(_FINDER_COLUMN_T_DD_DELETEDATE_2);
		}

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		if (bindDeleteDate) {
			qPos.add(new Timestamp(deleteDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param types the types
	 * @param deleteDate the delete date
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByT_DD(int[] types, Date deleteDate) {
		return findByT_DD(types, deleteDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param types the types
	 * @param deleteDate the delete date
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByT_DD(int[] types, Date deleteDate,
		int start, int end) {
		return findByT_DD(types, deleteDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param types the types
	 * @param deleteDate the delete date
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByT_DD(int[] types, Date deleteDate,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByT_DD(types, deleteDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByT_DD(int[] types, Date deleteDate,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		if (types == null) {
			types = new int[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.unique(types);

			Arrays.sort(types);
		}

		if (types.length == 1) {
			return findByT_DD(types[0], deleteDate, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(types), deleteDate };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(types), deleteDate,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_T_DD,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if (!ArrayUtil.contains(types, ticketAttachment.getType()) ||
							(deleteDate.getTime() <= ticketAttachment.getDeleteDate()
																		 .getTime())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			if (types.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_T_DD_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			boolean bindDeleteDate = false;

			if (deleteDate == null) {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_1);
			}
			else {
				bindDeleteDate = true;

				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDeleteDate) {
					qPos.add(new Timestamp(deleteDate.getTime()));
				}

				if (!pagination) {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_T_DD,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_T_DD,
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
	 * Removes all the ticket attachments where type = &#63; and deleteDate &lt; &#63; from the database.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 */
	@Override
	public void removeByT_DD(int type, Date deleteDate) {
		for (TicketAttachment ticketAttachment : findByT_DD(type, deleteDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Returns the number of ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByT_DD(int type, Date deleteDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_DD;

		Object[] finderArgs = new Object[] { type, deleteDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_T_DD_TYPE_2);

			boolean bindDeleteDate = false;

			if (deleteDate == null) {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_1);
			}
			else {
				bindDeleteDate = true;

				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				if (bindDeleteDate) {
					qPos.add(new Timestamp(deleteDate.getTime()));
				}

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
	 * Returns the number of ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	 *
	 * @param types the types
	 * @param deleteDate the delete date
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByT_DD(int[] types, Date deleteDate) {
		if (types == null) {
			types = new int[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.unique(types);

			Arrays.sort(types);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(types), deleteDate };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_DD,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			if (types.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_T_DD_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			boolean bindDeleteDate = false;

			if (deleteDate == null) {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_1);
			}
			else {
				bindDeleteDate = true;

				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDeleteDate) {
					qPos.add(new Timestamp(deleteDate.getTime()));
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_DD,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_DD,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_T_DD_TYPE_2 = "ticketAttachment.type = ? AND ";
	private static final String _FINDER_COLUMN_T_DD_TYPE_7 = "ticketAttachment.type IN (";
	private static final String _FINDER_COLUMN_T_DD_DELETEDATE_1 = "ticketAttachment.deleteDate IS NULL";
	private static final String _FINDER_COLUMN_T_DD_DELETEDATE_2 = "ticketAttachment.deleteDate < ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_T_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_T_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.TYPE_COLUMN_BITMASK |
			TicketAttachmentModelImpl.STATUS_COLUMN_BITMASK |
			TicketAttachmentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_T_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_T_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_S(long ticketEntryId, int type,
		int status) {
		return findByTEI_T_S(ticketEntryId, type, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_S(long ticketEntryId, int type,
		int status, int start, int end) {
		return findByTEI_T_S(ticketEntryId, type, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_S(long ticketEntryId, int type,
		int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByTEI_T_S(ticketEntryId, type, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_S(long ticketEntryId, int type,
		int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_S;
			finderArgs = new Object[] { ticketEntryId, type, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_S;
			finderArgs = new Object[] {
					ticketEntryId, type, status,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
							(type != ticketAttachment.getType()) ||
							(status != ticketAttachment.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_S_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

				qPos.add(status);

				if (!pagination) {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketAttachment>)QueryUtil.list(q,
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
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByTEI_T_S_First(long ticketEntryId, int type,
		int status, OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByTEI_T_S_First(ticketEntryId,
				type, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByTEI_T_S_First(long ticketEntryId, int type,
		int status, OrderByComparator<TicketAttachment> orderByComparator) {
		List<TicketAttachment> list = findByTEI_T_S(ticketEntryId, type,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByTEI_T_S_Last(long ticketEntryId, int type,
		int status, OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByTEI_T_S_Last(ticketEntryId,
				type, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByTEI_T_S_Last(long ticketEntryId, int type,
		int status, OrderByComparator<TicketAttachment> orderByComparator) {
		int count = countByTEI_T_S(ticketEntryId, type, status);

		if (count == 0) {
			return null;
		}

		List<TicketAttachment> list = findByTEI_T_S(ticketEntryId, type,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment[] findByTEI_T_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int type, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByTEI_T_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, type, status, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByTEI_T_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, type, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByTEI_T_S_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, long ticketEntryId, int type,
		int status, OrderByComparator<TicketAttachment> orderByComparator,
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

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_TEI_T_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_T_S_TYPE_2);

		query.append(_FINDER_COLUMN_TEI_T_S_STATUS_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(type);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 */
	@Override
	public void removeByTEI_T_S(long ticketEntryId, int type, int status) {
		for (TicketAttachment ticketAttachment : findByTEI_T_S(ticketEntryId,
				type, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByTEI_T_S(long ticketEntryId, int type, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEI_T_S;

		Object[] finderArgs = new Object[] { ticketEntryId, type, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_S_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

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

	private static final String _FINDER_COLUMN_TEI_T_S_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_S_TYPE_2 = "ticketAttachment.type = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_S_STATUS_2 = "ticketAttachment.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_V_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_TEI_V_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_TEI_V_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			TicketAttachmentModelImpl.USERID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.VISIBILITY_COLUMN_BITMASK |
			TicketAttachmentModelImpl.STATUS_COLUMN_BITMASK |
			TicketAttachmentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_TEI_V_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_TEI_V_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status) {
		return findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end) {
		return findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
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

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if ((userId != ticketAttachment.getUserId()) ||
							(ticketEntryId != ticketAttachment.getTicketEntryId()) ||
							(visibility != ticketAttachment.getVisibility()) ||
							(status != ticketAttachment.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

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
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
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
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketAttachment>)QueryUtil.list(q,
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
	 * Returns the first ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByU_TEI_V_S_First(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByU_TEI_V_S_First(userId,
				ticketEntryId, visibility, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
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

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByU_TEI_V_S_First(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		List<TicketAttachment> list = findByU_TEI_V_S(userId, ticketEntryId,
				visibility, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByU_TEI_V_S_Last(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByU_TEI_V_S_Last(userId,
				ticketEntryId, visibility, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
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

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByU_TEI_V_S_Last(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		int count = countByU_TEI_V_S(userId, ticketEntryId, visibility, status);

		if (count == 0) {
			return null;
		}

		List<TicketAttachment> list = findByU_TEI_V_S(userId, ticketEntryId,
				visibility, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment[] findByU_TEI_V_S_PrevAndNext(
		long ticketAttachmentId, long userId, long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByU_TEI_V_S_PrevAndNext(session, ticketAttachment,
					userId, ticketEntryId, visibility, status,
					orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByU_TEI_V_S_PrevAndNext(session, ticketAttachment,
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

	protected TicketAttachment getByU_TEI_V_S_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, long userId, long ticketEntryId,
		int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 */
	@Override
	public void removeByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status) {
		for (TicketAttachment ticketAttachment : findByU_TEI_V_S(userId,
				ticketEntryId, visibility, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Returns the number of ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @return the number of matching ticket attachments
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

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

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

	private static final String _FINDER_COLUMN_U_TEI_V_S_USERID_2 = "ticketAttachment.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_VISIBILITY_2 = "ticketAttachment.visibility = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_STATUS_2 = "ticketAttachment.status = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_TEI_FN_V_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByTEI_FN_V_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.FILENAME_COLUMN_BITMASK |
			TicketAttachmentModelImpl.VISIBILITY_COLUMN_BITMASK |
			TicketAttachmentModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_FN_V_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_FN_V_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; or throws a {@link NoSuchTicketAttachmentException} if it could not be found.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fileName the file name
	 * @param visibility the visibility
	 * @param status the status
	 * @return the matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByTEI_FN_V_S(long ticketEntryId,
		String fileName, int visibility, int status)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByTEI_FN_V_S(ticketEntryId,
				fileName, visibility, status);

		if (ticketAttachment == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ticketEntryId=");
			msg.append(ticketEntryId);

			msg.append(", fileName=");
			msg.append(fileName);

			msg.append(", visibility=");
			msg.append(visibility);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTicketAttachmentException(msg.toString());
		}

		return ticketAttachment;
	}

	/**
	 * Returns the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fileName the file name
	 * @param visibility the visibility
	 * @param status the status
	 * @return the matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByTEI_FN_V_S(long ticketEntryId,
		String fileName, int visibility, int status) {
		return fetchByTEI_FN_V_S(ticketEntryId, fileName, visibility, status,
			true);
	}

	/**
	 * Returns the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fileName the file name
	 * @param visibility the visibility
	 * @param status the status
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByTEI_FN_V_S(long ticketEntryId,
		String fileName, int visibility, int status, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				ticketEntryId, fileName, visibility, status
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S,
					finderArgs, this);
		}

		if (result instanceof TicketAttachment) {
			TicketAttachment ticketAttachment = (TicketAttachment)result;

			if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
					!Objects.equals(fileName, ticketAttachment.getFileName()) ||
					(visibility != ticketAttachment.getVisibility()) ||
					(status != ticketAttachment.getStatus())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_FN_V_S_TICKETENTRYID_2);

			boolean bindFileName = false;

			if (fileName == null) {
				query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_1);
			}
			else if (fileName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_3);
			}
			else {
				bindFileName = true;

				query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_2);
			}

			query.append(_FINDER_COLUMN_TEI_FN_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_TEI_FN_V_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (bindFileName) {
					qPos.add(fileName);
				}

				qPos.add(visibility);

				qPos.add(status);

				List<TicketAttachment> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"TicketAttachmentPersistenceImpl.fetchByTEI_FN_V_S(long, String, int, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					TicketAttachment ticketAttachment = list.get(0);

					result = ticketAttachment;

					cacheResult(ticketAttachment);

					if ((ticketAttachment.getTicketEntryId() != ticketEntryId) ||
							(ticketAttachment.getFileName() == null) ||
							!ticketAttachment.getFileName().equals(fileName) ||
							(ticketAttachment.getVisibility() != visibility) ||
							(ticketAttachment.getStatus() != status)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S,
							finderArgs, ticketAttachment);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S,
					finderArgs);

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
			return (TicketAttachment)result;
		}
	}

	/**
	 * Removes the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fileName the file name
	 * @param visibility the visibility
	 * @param status the status
	 * @return the ticket attachment that was removed
	 */
	@Override
	public TicketAttachment removeByTEI_FN_V_S(long ticketEntryId,
		String fileName, int visibility, int status)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = findByTEI_FN_V_S(ticketEntryId,
				fileName, visibility, status);

		return remove(ticketAttachment);
	}

	/**
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fileName the file name
	 * @param visibility the visibility
	 * @param status the status
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByTEI_FN_V_S(long ticketEntryId, String fileName,
		int visibility, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEI_FN_V_S;

		Object[] finderArgs = new Object[] {
				ticketEntryId, fileName, visibility, status
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_FN_V_S_TICKETENTRYID_2);

			boolean bindFileName = false;

			if (fileName == null) {
				query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_1);
			}
			else if (fileName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_3);
			}
			else {
				bindFileName = true;

				query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_2);
			}

			query.append(_FINDER_COLUMN_TEI_FN_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_TEI_FN_V_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (bindFileName) {
					qPos.add(fileName);
				}

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

	private static final String _FINDER_COLUMN_TEI_FN_V_S_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_FN_V_S_FILENAME_1 = "ticketAttachment.fileName IS NULL AND ";
	private static final String _FINDER_COLUMN_TEI_FN_V_S_FILENAME_2 = "ticketAttachment.fileName = ? AND ";
	private static final String _FINDER_COLUMN_TEI_FN_V_S_FILENAME_3 = "(ticketAttachment.fileName IS NULL OR ticketAttachment.fileName = '') AND ";
	private static final String _FINDER_COLUMN_TEI_FN_V_S_VISIBILITY_2 = "ticketAttachment.visibility = ? AND ";
	private static final String _FINDER_COLUMN_TEI_FN_V_S_STATUS_2 = "ticketAttachment.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_V_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_T_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_V_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_T_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.TYPE_COLUMN_BITMASK |
			TicketAttachmentModelImpl.VISIBILITY_COLUMN_BITMASK |
			TicketAttachmentModelImpl.STATUS_COLUMN_BITMASK |
			TicketAttachmentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_T_V_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_T_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_V_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTEI_T_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId, int type,
		int visibility, int status) {
		return findByTEI_T_V_S(ticketEntryId, type, visibility, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId, int type,
		int visibility, int status, int start, int end) {
		return findByTEI_T_V_S(ticketEntryId, type, visibility, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId, int type,
		int visibility, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByTEI_T_V_S(ticketEntryId, type, visibility, status, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId, int type,
		int visibility, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_V_S;
			finderArgs = new Object[] { ticketEntryId, type, visibility, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_V_S;
			finderArgs = new Object[] {
					ticketEntryId, type, visibility, status,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
							(type != ticketAttachment.getType()) ||
							(visibility != ticketAttachment.getVisibility()) ||
							(status != ticketAttachment.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

				qPos.add(visibility);

				qPos.add(status);

				if (!pagination) {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketAttachment>)QueryUtil.list(q,
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
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByTEI_T_V_S_First(long ticketEntryId, int type,
		int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByTEI_T_V_S_First(ticketEntryId,
				type, visibility, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByTEI_T_V_S_First(long ticketEntryId,
		int type, int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		List<TicketAttachment> list = findByTEI_T_V_S(ticketEntryId, type,
				visibility, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByTEI_T_V_S_Last(long ticketEntryId, int type,
		int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByTEI_T_V_S_Last(ticketEntryId,
				type, visibility, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByTEI_T_V_S_Last(long ticketEntryId, int type,
		int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator) {
		int count = countByTEI_T_V_S(ticketEntryId, type, visibility, status);

		if (count == 0) {
			return null;
		}

		List<TicketAttachment> list = findByTEI_T_V_S(ticketEntryId, type,
				visibility, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment[] findByTEI_T_V_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility,
		int status, OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByTEI_T_V_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, type, visibility, status, orderByComparator,
					true);

			array[1] = ticketAttachment;

			array[2] = getByTEI_T_V_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, type, visibility, status, orderByComparator,
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

	protected TicketAttachment getByTEI_T_V_S_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, long ticketEntryId, int type,
		int visibility, int status,
		OrderByComparator<TicketAttachment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_T_V_S_TYPE_2);

		query.append(_FINDER_COLUMN_TEI_T_V_S_VISIBILITY_2);

		query.append(_FINDER_COLUMN_TEI_T_V_S_STATUS_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(type);

		qPos.add(visibility);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param visibilities the visibilities
	 * @param status the status
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int[] types, int[] visibilities, int status) {
		return findByTEI_T_V_S(ticketEntryId, types, visibilities, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param visibilities the visibilities
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int[] types, int[] visibilities, int status, int start, int end) {
		return findByTEI_T_V_S(ticketEntryId, types, visibilities, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param visibilities the visibilities
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int[] types, int[] visibilities, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByTEI_T_V_S(ticketEntryId, types, visibilities, status,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int[] types, int[] visibilities, int status, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		if (types == null) {
			types = new int[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.unique(types);

			Arrays.sort(types);
		}

		if (visibilities == null) {
			visibilities = new int[0];
		}
		else if (visibilities.length > 1) {
			visibilities = ArrayUtil.unique(visibilities);

			Arrays.sort(visibilities);
		}

		if ((types.length == 1) && (visibilities.length == 1)) {
			return findByTEI_T_V_S(ticketEntryId, types[0], visibilities[0],
				status, start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(types),
					StringUtil.merge(visibilities), status
				};
		}
		else {
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(types),
					StringUtil.merge(visibilities), status,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_V_S,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
							!ArrayUtil.contains(types,
								ticketAttachment.getType()) ||
							!ArrayUtil.contains(visibilities,
								ticketAttachment.getVisibility()) ||
							(status != ticketAttachment.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_2);

			if (types.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_TEI_T_V_S_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (visibilities.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_TEI_T_V_S_VISIBILITY_7);

				query.append(StringUtil.merge(visibilities));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_V_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(status);

				if (!pagination) {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_V_S,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_V_S,
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
	 * Removes all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 */
	@Override
	public void removeByTEI_T_V_S(long ticketEntryId, int type, int visibility,
		int status) {
		for (TicketAttachment ticketAttachment : findByTEI_T_V_S(
				ticketEntryId, type, visibility, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByTEI_T_V_S(long ticketEntryId, int type, int visibility,
		int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEI_T_V_S;

		Object[] finderArgs = new Object[] {
				ticketEntryId, type, visibility, status
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

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
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param visibilities the visibilities
	 * @param status the status
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByTEI_T_V_S(long ticketEntryId, int[] types,
		int[] visibilities, int status) {
		if (types == null) {
			types = new int[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.unique(types);

			Arrays.sort(types);
		}

		if (visibilities == null) {
			visibilities = new int[0];
		}
		else if (visibilities.length > 1) {
			visibilities = ArrayUtil.unique(visibilities);

			Arrays.sort(visibilities);
		}

		Object[] finderArgs = new Object[] {
				ticketEntryId, StringUtil.merge(types),
				StringUtil.merge(visibilities), status
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_V_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_2);

			if (types.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_TEI_T_V_S_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (visibilities.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_TEI_T_V_S_VISIBILITY_7);

				query.append(StringUtil.merge(visibilities));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_V_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_V_S,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_V_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_V_S_TYPE_2 = "ticketAttachment.type = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_V_S_TYPE_7 = "ticketAttachment.type IN (";
	private static final String _FINDER_COLUMN_TEI_T_V_S_VISIBILITY_2 = "ticketAttachment.visibility = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_V_S_VISIBILITY_7 = "ticketAttachment.visibility IN (";
	private static final String _FINDER_COLUMN_TEI_T_V_S_STATUS_2 = "ticketAttachment.status = ?";

	public TicketAttachmentPersistenceImpl() {
		setModelClass(TicketAttachment.class);
	}

	/**
	 * Caches the ticket attachment in the entity cache if it is enabled.
	 *
	 * @param ticketAttachment the ticket attachment
	 */
	@Override
	public void cacheResult(TicketAttachment ticketAttachment) {
		entityCache.putResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentImpl.class, ticketAttachment.getPrimaryKey(),
			ticketAttachment);

		finderCache.putResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S,
			new Object[] {
				ticketAttachment.getTicketEntryId(),
				ticketAttachment.getFileName(), ticketAttachment.getVisibility(),
				ticketAttachment.getStatus()
			}, ticketAttachment);

		ticketAttachment.resetOriginalValues();
	}

	/**
	 * Caches the ticket attachments in the entity cache if it is enabled.
	 *
	 * @param ticketAttachments the ticket attachments
	 */
	@Override
	public void cacheResult(List<TicketAttachment> ticketAttachments) {
		for (TicketAttachment ticketAttachment : ticketAttachments) {
			if (entityCache.getResult(
						TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						TicketAttachmentImpl.class,
						ticketAttachment.getPrimaryKey()) == null) {
				cacheResult(ticketAttachment);
			}
			else {
				ticketAttachment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket attachments.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TicketAttachmentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket attachment.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketAttachment ticketAttachment) {
		entityCache.removeResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentImpl.class, ticketAttachment.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TicketAttachmentModelImpl)ticketAttachment,
			true);
	}

	@Override
	public void clearCache(List<TicketAttachment> ticketAttachments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			entityCache.removeResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				TicketAttachmentImpl.class, ticketAttachment.getPrimaryKey());

			clearUniqueFindersCache((TicketAttachmentModelImpl)ticketAttachment,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		TicketAttachmentModelImpl ticketAttachmentModelImpl) {
		Object[] args = new Object[] {
				ticketAttachmentModelImpl.getTicketEntryId(),
				ticketAttachmentModelImpl.getFileName(),
				ticketAttachmentModelImpl.getVisibility(),
				ticketAttachmentModelImpl.getStatus()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_TEI_FN_V_S, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S, args,
			ticketAttachmentModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		TicketAttachmentModelImpl ticketAttachmentModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					ticketAttachmentModelImpl.getTicketEntryId(),
					ticketAttachmentModelImpl.getFileName(),
					ticketAttachmentModelImpl.getVisibility(),
					ticketAttachmentModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_FN_V_S, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S, args);
		}

		if ((ticketAttachmentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TEI_FN_V_S.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					ticketAttachmentModelImpl.getOriginalTicketEntryId(),
					ticketAttachmentModelImpl.getOriginalFileName(),
					ticketAttachmentModelImpl.getOriginalVisibility(),
					ticketAttachmentModelImpl.getOriginalStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_FN_V_S, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S, args);
		}
	}

	/**
	 * Creates a new ticket attachment with the primary key. Does not add the ticket attachment to the database.
	 *
	 * @param ticketAttachmentId the primary key for the new ticket attachment
	 * @return the new ticket attachment
	 */
	@Override
	public TicketAttachment create(long ticketAttachmentId) {
		TicketAttachment ticketAttachment = new TicketAttachmentImpl();

		ticketAttachment.setNew(true);
		ticketAttachment.setPrimaryKey(ticketAttachmentId);

		return ticketAttachment;
	}

	/**
	 * Removes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketAttachmentId the primary key of the ticket attachment
	 * @return the ticket attachment that was removed
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment remove(long ticketAttachmentId)
		throws NoSuchTicketAttachmentException {
		return remove((Serializable)ticketAttachmentId);
	}

	/**
	 * Removes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket attachment
	 * @return the ticket attachment that was removed
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment remove(Serializable primaryKey)
		throws NoSuchTicketAttachmentException {
		Session session = null;

		try {
			session = openSession();

			TicketAttachment ticketAttachment = (TicketAttachment)session.get(TicketAttachmentImpl.class,
					primaryKey);

			if (ticketAttachment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketAttachment);
		}
		catch (NoSuchTicketAttachmentException nsee) {
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
	protected TicketAttachment removeImpl(TicketAttachment ticketAttachment) {
		ticketAttachment = toUnwrappedModel(ticketAttachment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ticketAttachment)) {
				ticketAttachment = (TicketAttachment)session.get(TicketAttachmentImpl.class,
						ticketAttachment.getPrimaryKeyObj());
			}

			if (ticketAttachment != null) {
				session.delete(ticketAttachment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ticketAttachment != null) {
			clearCache(ticketAttachment);
		}

		return ticketAttachment;
	}

	@Override
	public TicketAttachment updateImpl(TicketAttachment ticketAttachment) {
		ticketAttachment = toUnwrappedModel(ticketAttachment);

		boolean isNew = ticketAttachment.isNew();

		TicketAttachmentModelImpl ticketAttachmentModelImpl = (TicketAttachmentModelImpl)ticketAttachment;

		Session session = null;

		try {
			session = openSession();

			if (ticketAttachment.isNew()) {
				session.save(ticketAttachment);

				ticketAttachment.setNew(false);
			}
			else {
				ticketAttachment = (TicketAttachment)session.merge(ticketAttachment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TicketAttachmentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					ticketAttachmentModelImpl.getTicketEntryId(),
					ticketAttachmentModelImpl.getTicketSolutionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_TSI, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI,
				args);

			args = new Object[] {
					ticketAttachmentModelImpl.getTicketEntryId(),
					ticketAttachmentModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S,
				args);

			args = new Object[] {
					ticketAttachmentModelImpl.getTicketEntryId(),
					ticketAttachmentModelImpl.getType(),
					ticketAttachmentModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_S,
				args);

			args = new Object[] {
					ticketAttachmentModelImpl.getUserId(),
					ticketAttachmentModelImpl.getTicketEntryId(),
					ticketAttachmentModelImpl.getVisibility(),
					ticketAttachmentModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_TEI_V_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S,
				args);

			args = new Object[] {
					ticketAttachmentModelImpl.getTicketEntryId(),
					ticketAttachmentModelImpl.getType(),
					ticketAttachmentModelImpl.getVisibility(),
					ticketAttachmentModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T_V_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_V_S,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketAttachmentModelImpl.getOriginalTicketEntryId(),
						ticketAttachmentModelImpl.getOriginalTicketSolutionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_TSI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI,
					args);

				args = new Object[] {
						ticketAttachmentModelImpl.getTicketEntryId(),
						ticketAttachmentModelImpl.getTicketSolutionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_TSI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI,
					args);
			}

			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketAttachmentModelImpl.getOriginalTicketEntryId(),
						ticketAttachmentModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S,
					args);

				args = new Object[] {
						ticketAttachmentModelImpl.getTicketEntryId(),
						ticketAttachmentModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S,
					args);
			}

			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketAttachmentModelImpl.getOriginalTicketEntryId(),
						ticketAttachmentModelImpl.getOriginalType(),
						ticketAttachmentModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_S,
					args);

				args = new Object[] {
						ticketAttachmentModelImpl.getTicketEntryId(),
						ticketAttachmentModelImpl.getType(),
						ticketAttachmentModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_S,
					args);
			}

			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketAttachmentModelImpl.getOriginalUserId(),
						ticketAttachmentModelImpl.getOriginalTicketEntryId(),
						ticketAttachmentModelImpl.getOriginalVisibility(),
						ticketAttachmentModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_TEI_V_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S,
					args);

				args = new Object[] {
						ticketAttachmentModelImpl.getUserId(),
						ticketAttachmentModelImpl.getTicketEntryId(),
						ticketAttachmentModelImpl.getVisibility(),
						ticketAttachmentModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_TEI_V_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S,
					args);
			}

			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_V_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketAttachmentModelImpl.getOriginalTicketEntryId(),
						ticketAttachmentModelImpl.getOriginalType(),
						ticketAttachmentModelImpl.getOriginalVisibility(),
						ticketAttachmentModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T_V_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_V_S,
					args);

				args = new Object[] {
						ticketAttachmentModelImpl.getTicketEntryId(),
						ticketAttachmentModelImpl.getType(),
						ticketAttachmentModelImpl.getVisibility(),
						ticketAttachmentModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T_V_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_V_S,
					args);
			}
		}

		entityCache.putResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentImpl.class, ticketAttachment.getPrimaryKey(),
			ticketAttachment, false);

		clearUniqueFindersCache(ticketAttachmentModelImpl, false);
		cacheUniqueFindersCache(ticketAttachmentModelImpl);

		ticketAttachment.resetOriginalValues();

		return ticketAttachment;
	}

	protected TicketAttachment toUnwrappedModel(
		TicketAttachment ticketAttachment) {
		if (ticketAttachment instanceof TicketAttachmentImpl) {
			return ticketAttachment;
		}

		TicketAttachmentImpl ticketAttachmentImpl = new TicketAttachmentImpl();

		ticketAttachmentImpl.setNew(ticketAttachment.isNew());
		ticketAttachmentImpl.setPrimaryKey(ticketAttachment.getPrimaryKey());

		ticketAttachmentImpl.setTicketAttachmentId(ticketAttachment.getTicketAttachmentId());
		ticketAttachmentImpl.setUserId(ticketAttachment.getUserId());
		ticketAttachmentImpl.setUserName(ticketAttachment.getUserName());
		ticketAttachmentImpl.setCreateDate(ticketAttachment.getCreateDate());
		ticketAttachmentImpl.setTicketEntryId(ticketAttachment.getTicketEntryId());
		ticketAttachmentImpl.setTicketSolutionId(ticketAttachment.getTicketSolutionId());
		ticketAttachmentImpl.setReleaseNotesId(ticketAttachment.getReleaseNotesId());
		ticketAttachmentImpl.setFileName(ticketAttachment.getFileName());
		ticketAttachmentImpl.setFileSize(ticketAttachment.getFileSize());
		ticketAttachmentImpl.setType(ticketAttachment.getType());
		ticketAttachmentImpl.setVisibility(ticketAttachment.getVisibility());
		ticketAttachmentImpl.setExtractedText(ticketAttachment.getExtractedText());
		ticketAttachmentImpl.setAvailableFileRepositoryIds(ticketAttachment.getAvailableFileRepositoryIds());
		ticketAttachmentImpl.setReplicate(ticketAttachment.isReplicate());
		ticketAttachmentImpl.setDeleteDate(ticketAttachment.getDeleteDate());
		ticketAttachmentImpl.setStatus(ticketAttachment.getStatus());

		return ticketAttachmentImpl;
	}

	/**
	 * Returns the ticket attachment with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket attachment
	 * @return the ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByPrimaryKey(primaryKey);

		if (ticketAttachment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTicketAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ticketAttachment;
	}

	/**
	 * Returns the ticket attachment with the primary key or throws a {@link NoSuchTicketAttachmentException} if it could not be found.
	 *
	 * @param ticketAttachmentId the primary key of the ticket attachment
	 * @return the ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment findByPrimaryKey(long ticketAttachmentId)
		throws NoSuchTicketAttachmentException {
		return findByPrimaryKey((Serializable)ticketAttachmentId);
	}

	/**
	 * Returns the ticket attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket attachment
	 * @return the ticket attachment, or <code>null</code> if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				TicketAttachmentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TicketAttachment ticketAttachment = (TicketAttachment)serializable;

		if (ticketAttachment == null) {
			Session session = null;

			try {
				session = openSession();

				ticketAttachment = (TicketAttachment)session.get(TicketAttachmentImpl.class,
						primaryKey);

				if (ticketAttachment != null) {
					cacheResult(ticketAttachment);
				}
				else {
					entityCache.putResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						TicketAttachmentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
					TicketAttachmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ticketAttachment;
	}

	/**
	 * Returns the ticket attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketAttachmentId the primary key of the ticket attachment
	 * @return the ticket attachment, or <code>null</code> if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment fetchByPrimaryKey(long ticketAttachmentId) {
		return fetchByPrimaryKey((Serializable)ticketAttachmentId);
	}

	@Override
	public Map<Serializable, TicketAttachment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TicketAttachment> map = new HashMap<Serializable, TicketAttachment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TicketAttachment ticketAttachment = fetchByPrimaryKey(primaryKey);

			if (ticketAttachment != null) {
				map.put(primaryKey, ticketAttachment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
					TicketAttachmentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TicketAttachment)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE_PKS_IN);

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

			for (TicketAttachment ticketAttachment : (List<TicketAttachment>)q.list()) {
				map.put(ticketAttachment.getPrimaryKeyObj(), ticketAttachment);

				cacheResult(ticketAttachment);

				uncachedPrimaryKeys.remove(ticketAttachment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
					TicketAttachmentImpl.class, primaryKey, nullModel);
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
	 * Returns all the ticket attachments.
	 *
	 * @return the ticket attachments
	 */
	@Override
	public List<TicketAttachment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of ticket attachments
	 */
	@Override
	public List<TicketAttachment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket attachments
	 */
	@Override
	public List<TicketAttachment> findAll(int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ticket attachments
	 */
	@Override
	public List<TicketAttachment> findAll(int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
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

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TICKETATTACHMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETATTACHMENT;

				if (pagination) {
					sql = sql.concat(TicketAttachmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketAttachment>)QueryUtil.list(q,
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
	 * Removes all the ticket attachments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TicketAttachment ticketAttachment : findAll()) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Returns the number of ticket attachments.
	 *
	 * @return the number of ticket attachments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETATTACHMENT);

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
		return TicketAttachmentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ticket attachment persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TicketAttachmentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_TICKETATTACHMENT = "SELECT ticketAttachment FROM TicketAttachment ticketAttachment";
	private static final String _SQL_SELECT_TICKETATTACHMENT_WHERE_PKS_IN = "SELECT ticketAttachment FROM TicketAttachment ticketAttachment WHERE ticketAttachmentId IN (";
	private static final String _SQL_SELECT_TICKETATTACHMENT_WHERE = "SELECT ticketAttachment FROM TicketAttachment ticketAttachment WHERE ";
	private static final String _SQL_COUNT_TICKETATTACHMENT = "SELECT COUNT(ticketAttachment) FROM TicketAttachment ticketAttachment";
	private static final String _SQL_COUNT_TICKETATTACHMENT_WHERE = "SELECT COUNT(ticketAttachment) FROM TicketAttachment ticketAttachment WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketAttachment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketAttachment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketAttachment exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TicketAttachmentPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
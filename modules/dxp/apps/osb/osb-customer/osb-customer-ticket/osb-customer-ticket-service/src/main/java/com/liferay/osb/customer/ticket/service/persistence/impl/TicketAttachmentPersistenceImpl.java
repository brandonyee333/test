/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.ticket.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.ticket.exception.NoSuchTicketAttachmentException;
import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.osb.customer.ticket.model.impl.TicketAttachmentImpl;
import com.liferay.osb.customer.ticket.model.impl.TicketAttachmentModelImpl;
import com.liferay.osb.customer.ticket.service.persistence.TicketAttachmentPersistence;

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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
 * @see com.liferay.osb.customer.ticket.service.persistence.TicketAttachmentUtil
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ZENDESKTICKETID =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByZendeskTicketId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZENDESKTICKETID =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByZendeskTicketId",
			new String[] { Long.class.getName() },
			TicketAttachmentModelImpl.ZENDESKTICKETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ZENDESKTICKETID = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByZendeskTicketId", new String[] { Long.class.getName() });

	/**
	 * Returns all the ticket attachments where zendeskTicketId = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZendeskTicketId(long zendeskTicketId) {
		return findByZendeskTicketId(zendeskTicketId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where zendeskTicketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZendeskTicketId(long zendeskTicketId,
		int start, int end) {
		return findByZendeskTicketId(zendeskTicketId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZendeskTicketId(long zendeskTicketId,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByZendeskTicketId(zendeskTicketId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZendeskTicketId(long zendeskTicketId,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZENDESKTICKETID;
			finderArgs = new Object[] { zendeskTicketId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ZENDESKTICKETID;
			finderArgs = new Object[] {
					zendeskTicketId,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if ((zendeskTicketId != ticketAttachment.getZendeskTicketId())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_ZENDESKTICKETID_ZENDESKTICKETID_2);

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

				qPos.add(zendeskTicketId);

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
	 * Returns the first ticket attachment in the ordered set where zendeskTicketId = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByZendeskTicketId_First(long zendeskTicketId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByZendeskTicketId_First(zendeskTicketId,
				orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("zendeskTicketId=");
		msg.append(zendeskTicketId);

		msg.append("}");

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where zendeskTicketId = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByZendeskTicketId_First(long zendeskTicketId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		List<TicketAttachment> list = findByZendeskTicketId(zendeskTicketId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where zendeskTicketId = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByZendeskTicketId_Last(long zendeskTicketId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByZendeskTicketId_Last(zendeskTicketId,
				orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("zendeskTicketId=");
		msg.append(zendeskTicketId);

		msg.append("}");

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where zendeskTicketId = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByZendeskTicketId_Last(long zendeskTicketId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		int count = countByZendeskTicketId(zendeskTicketId);

		if (count == 0) {
			return null;
		}

		List<TicketAttachment> list = findByZendeskTicketId(zendeskTicketId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where zendeskTicketId = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment[] findByZendeskTicketId_PrevAndNext(
		long ticketAttachmentId, long zendeskTicketId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByZendeskTicketId_PrevAndNext(session,
					ticketAttachment, zendeskTicketId, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByZendeskTicketId_PrevAndNext(session,
					ticketAttachment, zendeskTicketId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByZendeskTicketId_PrevAndNext(
		Session session, TicketAttachment ticketAttachment,
		long zendeskTicketId,
		OrderByComparator<TicketAttachment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_ZENDESKTICKETID_ZENDESKTICKETID_2);

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

		qPos.add(zendeskTicketId);

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
	 * Removes all the ticket attachments where zendeskTicketId = &#63; from the database.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 */
	@Override
	public void removeByZendeskTicketId(long zendeskTicketId) {
		for (TicketAttachment ticketAttachment : findByZendeskTicketId(
				zendeskTicketId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Returns the number of ticket attachments where zendeskTicketId = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByZendeskTicketId(long zendeskTicketId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ZENDESKTICKETID;

		Object[] finderArgs = new Object[] { zendeskTicketId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_ZENDESKTICKETID_ZENDESKTICKETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(zendeskTicketId);

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

	private static final String _FINDER_COLUMN_ZENDESKTICKETID_ZENDESKTICKETID_2 =
		"ticketAttachment.zendeskTicketId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ZTI_T = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByZTI_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZTI_T = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByZTI_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TicketAttachmentModelImpl.ZENDESKTICKETID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ZTI_T = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByZTI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_ZTI_T = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByZTI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the ticket attachments where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZTI_T(long zendeskTicketId, int type) {
		return findByZTI_T(zendeskTicketId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZTI_T(long zendeskTicketId, int type,
		int start, int end) {
		return findByZTI_T(zendeskTicketId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZTI_T(long zendeskTicketId, int type,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByZTI_T(zendeskTicketId, type, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZTI_T(long zendeskTicketId, int type,
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZTI_T;
			finderArgs = new Object[] { zendeskTicketId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ZTI_T;
			finderArgs = new Object[] {
					zendeskTicketId, type,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if ((zendeskTicketId != ticketAttachment.getZendeskTicketId()) ||
							(type != ticketAttachment.getType())) {
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

			query.append(_FINDER_COLUMN_ZTI_T_ZENDESKTICKETID_2);

			query.append(_FINDER_COLUMN_ZTI_T_TYPE_2);

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

				qPos.add(zendeskTicketId);

				qPos.add(type);

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
	 * Returns the first ticket attachment in the ordered set where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByZTI_T_First(long zendeskTicketId, int type,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByZTI_T_First(zendeskTicketId,
				type, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("zendeskTicketId=");
		msg.append(zendeskTicketId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByZTI_T_First(long zendeskTicketId, int type,
		OrderByComparator<TicketAttachment> orderByComparator) {
		List<TicketAttachment> list = findByZTI_T(zendeskTicketId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment findByZTI_T_Last(long zendeskTicketId, int type,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = fetchByZTI_T_Last(zendeskTicketId,
				type, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("zendeskTicketId=");
		msg.append(zendeskTicketId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	@Override
	public TicketAttachment fetchByZTI_T_Last(long zendeskTicketId, int type,
		OrderByComparator<TicketAttachment> orderByComparator) {
		int count = countByZTI_T(zendeskTicketId, type);

		if (count == 0) {
			return null;
		}

		List<TicketAttachment> list = findByZTI_T(zendeskTicketId, type,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public TicketAttachment[] findByZTI_T_PrevAndNext(long ticketAttachmentId,
		long zendeskTicketId, int type,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws NoSuchTicketAttachmentException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByZTI_T_PrevAndNext(session, ticketAttachment,
					zendeskTicketId, type, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByZTI_T_PrevAndNext(session, ticketAttachment,
					zendeskTicketId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByZTI_T_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, long zendeskTicketId, int type,
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

		query.append(_FINDER_COLUMN_ZTI_T_ZENDESKTICKETID_2);

		query.append(_FINDER_COLUMN_ZTI_T_TYPE_2);

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

		qPos.add(zendeskTicketId);

		qPos.add(type);

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
	 * Returns all the ticket attachments where zendeskTicketId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param types the types
	 * @return the matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZTI_T(long zendeskTicketId, int[] types) {
		return findByZTI_T(zendeskTicketId, types, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where zendeskTicketId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param types the types
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZTI_T(long zendeskTicketId,
		int[] types, int start, int end) {
		return findByZTI_T(zendeskTicketId, types, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param types the types
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZTI_T(long zendeskTicketId,
		int[] types, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return findByZTI_T(zendeskTicketId, types, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63; and type = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	@Override
	public List<TicketAttachment> findByZTI_T(long zendeskTicketId,
		int[] types, int start, int end,
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
			return findByZTI_T(zendeskTicketId, types[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { zendeskTicketId, StringUtil.merge(types) };
		}
		else {
			finderArgs = new Object[] {
					zendeskTicketId, StringUtil.merge(types),
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<TicketAttachment>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ZTI_T,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketAttachment ticketAttachment : list) {
					if ((zendeskTicketId != ticketAttachment.getZendeskTicketId()) ||
							!ArrayUtil.contains(types,
								ticketAttachment.getType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_ZTI_T_ZENDESKTICKETID_2);

			if (types.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_ZTI_T_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(")");

				query.append(")");
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

				qPos.add(zendeskTicketId);

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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ZTI_T,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ZTI_T,
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
	 * Removes all the ticket attachments where zendeskTicketId = &#63; and type = &#63; from the database.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 */
	@Override
	public void removeByZTI_T(long zendeskTicketId, int type) {
		for (TicketAttachment ticketAttachment : findByZTI_T(zendeskTicketId,
				type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Returns the number of ticket attachments where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByZTI_T(long zendeskTicketId, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ZTI_T;

		Object[] finderArgs = new Object[] { zendeskTicketId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_ZTI_T_ZENDESKTICKETID_2);

			query.append(_FINDER_COLUMN_ZTI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(zendeskTicketId);

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

	/**
	 * Returns the number of ticket attachments where zendeskTicketId = &#63; and type = any &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param types the types
	 * @return the number of matching ticket attachments
	 */
	@Override
	public int countByZTI_T(long zendeskTicketId, int[] types) {
		if (types == null) {
			types = new int[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.unique(types);

			Arrays.sort(types);
		}

		Object[] finderArgs = new Object[] {
				zendeskTicketId, StringUtil.merge(types)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ZTI_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_ZTI_T_ZENDESKTICKETID_2);

			if (types.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_ZTI_T_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(zendeskTicketId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ZTI_T,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ZTI_T,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ZTI_T_ZENDESKTICKETID_2 = "ticketAttachment.zendeskTicketId = ? AND ";
	private static final String _FINDER_COLUMN_ZTI_T_TYPE_2 = "ticketAttachment.type = ?";
	private static final String _FINDER_COLUMN_ZTI_T_TYPE_7 = "ticketAttachment.type IN (";

	public TicketAttachmentPersistenceImpl() {
		setModelClass(TicketAttachment.class);

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
	 * Caches the ticket attachment in the entity cache if it is enabled.
	 *
	 * @param ticketAttachment the ticket attachment
	 */
	@Override
	public void cacheResult(TicketAttachment ticketAttachment) {
		entityCache.putResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentImpl.class, ticketAttachment.getPrimaryKey(),
			ticketAttachment);

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
	}

	@Override
	public void clearCache(List<TicketAttachment> ticketAttachments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			entityCache.removeResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				TicketAttachmentImpl.class, ticketAttachment.getPrimaryKey());
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
		boolean isNew = ticketAttachment.isNew();

		if (!(ticketAttachment instanceof TicketAttachmentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ticketAttachment.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(ticketAttachment);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ticketAttachment proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TicketAttachment implementation " +
				ticketAttachment.getClass());
		}

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
					ticketAttachmentModelImpl.getZendeskTicketId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ZENDESKTICKETID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZENDESKTICKETID,
				args);

			args = new Object[] {
					ticketAttachmentModelImpl.getZendeskTicketId(),
					ticketAttachmentModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ZTI_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZTI_T,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZENDESKTICKETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketAttachmentModelImpl.getOriginalZendeskTicketId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ZENDESKTICKETID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZENDESKTICKETID,
					args);

				args = new Object[] {
						ticketAttachmentModelImpl.getZendeskTicketId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ZENDESKTICKETID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZENDESKTICKETID,
					args);
			}

			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZTI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketAttachmentModelImpl.getOriginalZendeskTicketId(),
						ticketAttachmentModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ZTI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZTI_T,
					args);

				args = new Object[] {
						ticketAttachmentModelImpl.getZendeskTicketId(),
						ticketAttachmentModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ZTI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ZTI_T,
					args);
			}
		}

		entityCache.putResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentImpl.class, ticketAttachment.getPrimaryKey(),
			ticketAttachment, false);

		ticketAttachment.resetOriginalValues();

		return ticketAttachment;
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

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

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

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
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
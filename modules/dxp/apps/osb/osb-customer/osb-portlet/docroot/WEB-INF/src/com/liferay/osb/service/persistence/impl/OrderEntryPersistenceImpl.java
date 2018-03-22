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

import com.liferay.osb.exception.NoSuchOrderEntryException;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.impl.OrderEntryImpl;
import com.liferay.osb.model.impl.OrderEntryModelImpl;
import com.liferay.osb.service.persistence.OrderEntryPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

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
 * The persistence implementation for the order entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrderEntryPersistence
 * @see com.liferay.osb.service.persistence.OrderEntryUtil
 * @generated
 */
@ProviderType
public class OrderEntryPersistenceImpl extends BasePersistenceImpl<OrderEntry>
	implements OrderEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OrderEntryUtil} to access the order entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OrderEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryModelImpl.FINDER_CACHE_ENABLED, OrderEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryModelImpl.FINDER_CACHE_ENABLED, OrderEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryModelImpl.FINDER_CACHE_ENABLED, OrderEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryModelImpl.FINDER_CACHE_ENABLED, OrderEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OrderEntryModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the order entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching order entries
	 */
	@Override
	public List<OrderEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the order entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of order entries
	 * @param end the upper bound of the range of order entries (not inclusive)
	 * @return the range of matching order entries
	 */
	@Override
	public List<OrderEntry> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the order entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of order entries
	 * @param end the upper bound of the range of order entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching order entries
	 */
	@Override
	public List<OrderEntry> findByUuid(String uuid, int start, int end,
		OrderByComparator<OrderEntry> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the order entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of order entries
	 * @param end the upper bound of the range of order entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching order entries
	 */
	@Override
	public List<OrderEntry> findByUuid(String uuid, int start, int end,
		OrderByComparator<OrderEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<OrderEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OrderEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OrderEntry orderEntry : list) {
					if (!Objects.equals(uuid, orderEntry.getUuid())) {
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

			query.append(_SQL_SELECT_ORDERENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OrderEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<OrderEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OrderEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first order entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching order entry
	 * @throws NoSuchOrderEntryException if a matching order entry could not be found
	 */
	@Override
	public OrderEntry findByUuid_First(String uuid,
		OrderByComparator<OrderEntry> orderByComparator)
		throws NoSuchOrderEntryException {
		OrderEntry orderEntry = fetchByUuid_First(uuid, orderByComparator);

		if (orderEntry != null) {
			return orderEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrderEntryException(msg.toString());
	}

	/**
	 * Returns the first order entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching order entry, or <code>null</code> if a matching order entry could not be found
	 */
	@Override
	public OrderEntry fetchByUuid_First(String uuid,
		OrderByComparator<OrderEntry> orderByComparator) {
		List<OrderEntry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last order entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching order entry
	 * @throws NoSuchOrderEntryException if a matching order entry could not be found
	 */
	@Override
	public OrderEntry findByUuid_Last(String uuid,
		OrderByComparator<OrderEntry> orderByComparator)
		throws NoSuchOrderEntryException {
		OrderEntry orderEntry = fetchByUuid_Last(uuid, orderByComparator);

		if (orderEntry != null) {
			return orderEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrderEntryException(msg.toString());
	}

	/**
	 * Returns the last order entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching order entry, or <code>null</code> if a matching order entry could not be found
	 */
	@Override
	public OrderEntry fetchByUuid_Last(String uuid,
		OrderByComparator<OrderEntry> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OrderEntry> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the order entries before and after the current order entry in the ordered set where uuid = &#63;.
	 *
	 * @param orderEntryId the primary key of the current order entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next order entry
	 * @throws NoSuchOrderEntryException if a order entry with the primary key could not be found
	 */
	@Override
	public OrderEntry[] findByUuid_PrevAndNext(long orderEntryId, String uuid,
		OrderByComparator<OrderEntry> orderByComparator)
		throws NoSuchOrderEntryException {
		OrderEntry orderEntry = findByPrimaryKey(orderEntryId);

		Session session = null;

		try {
			session = openSession();

			OrderEntry[] array = new OrderEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, orderEntry, uuid,
					orderByComparator, true);

			array[1] = orderEntry;

			array[2] = getByUuid_PrevAndNext(session, orderEntry, uuid,
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

	protected OrderEntry getByUuid_PrevAndNext(Session session,
		OrderEntry orderEntry, String uuid,
		OrderByComparator<OrderEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ORDERENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(OrderEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(orderEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OrderEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the order entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OrderEntry orderEntry : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(orderEntry);
		}
	}

	/**
	 * Returns the number of order entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching order entries
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ORDERENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "orderEntry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "orderEntry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(orderEntry.uuid IS NULL OR orderEntry.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryModelImpl.FINDER_CACHE_ENABLED, OrderEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryModelImpl.FINDER_CACHE_ENABLED, OrderEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			OrderEntryModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the order entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching order entries
	 */
	@Override
	public List<OrderEntry> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the order entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of order entries
	 * @param end the upper bound of the range of order entries (not inclusive)
	 * @return the range of matching order entries
	 */
	@Override
	public List<OrderEntry> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the order entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of order entries
	 * @param end the upper bound of the range of order entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching order entries
	 */
	@Override
	public List<OrderEntry> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<OrderEntry> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the order entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of order entries
	 * @param end the upper bound of the range of order entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching order entries
	 */
	@Override
	public List<OrderEntry> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<OrderEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID;
			finderArgs = new Object[] { accountEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID;
			finderArgs = new Object[] {
					accountEntryId,
					
					start, end, orderByComparator
				};
		}

		List<OrderEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OrderEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OrderEntry orderEntry : list) {
					if ((accountEntryId != orderEntry.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ORDERENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OrderEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<OrderEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OrderEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first order entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching order entry
	 * @throws NoSuchOrderEntryException if a matching order entry could not be found
	 */
	@Override
	public OrderEntry findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<OrderEntry> orderByComparator)
		throws NoSuchOrderEntryException {
		OrderEntry orderEntry = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (orderEntry != null) {
			return orderEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrderEntryException(msg.toString());
	}

	/**
	 * Returns the first order entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching order entry, or <code>null</code> if a matching order entry could not be found
	 */
	@Override
	public OrderEntry fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<OrderEntry> orderByComparator) {
		List<OrderEntry> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last order entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching order entry
	 * @throws NoSuchOrderEntryException if a matching order entry could not be found
	 */
	@Override
	public OrderEntry findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<OrderEntry> orderByComparator)
		throws NoSuchOrderEntryException {
		OrderEntry orderEntry = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (orderEntry != null) {
			return orderEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrderEntryException(msg.toString());
	}

	/**
	 * Returns the last order entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching order entry, or <code>null</code> if a matching order entry could not be found
	 */
	@Override
	public OrderEntry fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<OrderEntry> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<OrderEntry> list = findByAccountEntryId(accountEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the order entries before and after the current order entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param orderEntryId the primary key of the current order entry
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next order entry
	 * @throws NoSuchOrderEntryException if a order entry with the primary key could not be found
	 */
	@Override
	public OrderEntry[] findByAccountEntryId_PrevAndNext(long orderEntryId,
		long accountEntryId, OrderByComparator<OrderEntry> orderByComparator)
		throws NoSuchOrderEntryException {
		OrderEntry orderEntry = findByPrimaryKey(orderEntryId);

		Session session = null;

		try {
			session = openSession();

			OrderEntry[] array = new OrderEntryImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, orderEntry,
					accountEntryId, orderByComparator, true);

			array[1] = orderEntry;

			array[2] = getByAccountEntryId_PrevAndNext(session, orderEntry,
					accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OrderEntry getByAccountEntryId_PrevAndNext(Session session,
		OrderEntry orderEntry, long accountEntryId,
		OrderByComparator<OrderEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ORDERENTRY_WHERE);

		query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

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
			query.append(OrderEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(orderEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OrderEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the order entries where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (OrderEntry orderEntry : findByAccountEntryId(accountEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(orderEntry);
		}
	}

	/**
	 * Returns the number of order entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching order entries
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ORDERENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "orderEntry.accountEntryId = ?";

	public OrderEntryPersistenceImpl() {
		setModelClass(OrderEntry.class);
	}

	/**
	 * Caches the order entry in the entity cache if it is enabled.
	 *
	 * @param orderEntry the order entry
	 */
	@Override
	public void cacheResult(OrderEntry orderEntry) {
		entityCache.putResult(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryImpl.class, orderEntry.getPrimaryKey(), orderEntry);

		orderEntry.resetOriginalValues();
	}

	/**
	 * Caches the order entries in the entity cache if it is enabled.
	 *
	 * @param orderEntries the order entries
	 */
	@Override
	public void cacheResult(List<OrderEntry> orderEntries) {
		for (OrderEntry orderEntry : orderEntries) {
			if (entityCache.getResult(
						OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
						OrderEntryImpl.class, orderEntry.getPrimaryKey()) == null) {
				cacheResult(orderEntry);
			}
			else {
				orderEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all order entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OrderEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the order entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OrderEntry orderEntry) {
		entityCache.removeResult(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryImpl.class, orderEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<OrderEntry> orderEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OrderEntry orderEntry : orderEntries) {
			entityCache.removeResult(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
				OrderEntryImpl.class, orderEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new order entry with the primary key. Does not add the order entry to the database.
	 *
	 * @param orderEntryId the primary key for the new order entry
	 * @return the new order entry
	 */
	@Override
	public OrderEntry create(long orderEntryId) {
		OrderEntry orderEntry = new OrderEntryImpl();

		orderEntry.setNew(true);
		orderEntry.setPrimaryKey(orderEntryId);

		String uuid = PortalUUIDUtil.generate();

		orderEntry.setUuid(uuid);

		return orderEntry;
	}

	/**
	 * Removes the order entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orderEntryId the primary key of the order entry
	 * @return the order entry that was removed
	 * @throws NoSuchOrderEntryException if a order entry with the primary key could not be found
	 */
	@Override
	public OrderEntry remove(long orderEntryId)
		throws NoSuchOrderEntryException {
		return remove((Serializable)orderEntryId);
	}

	/**
	 * Removes the order entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the order entry
	 * @return the order entry that was removed
	 * @throws NoSuchOrderEntryException if a order entry with the primary key could not be found
	 */
	@Override
	public OrderEntry remove(Serializable primaryKey)
		throws NoSuchOrderEntryException {
		Session session = null;

		try {
			session = openSession();

			OrderEntry orderEntry = (OrderEntry)session.get(OrderEntryImpl.class,
					primaryKey);

			if (orderEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOrderEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(orderEntry);
		}
		catch (NoSuchOrderEntryException nsee) {
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
	protected OrderEntry removeImpl(OrderEntry orderEntry) {
		orderEntry = toUnwrappedModel(orderEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(orderEntry)) {
				orderEntry = (OrderEntry)session.get(OrderEntryImpl.class,
						orderEntry.getPrimaryKeyObj());
			}

			if (orderEntry != null) {
				session.delete(orderEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (orderEntry != null) {
			clearCache(orderEntry);
		}

		return orderEntry;
	}

	@Override
	public OrderEntry updateImpl(OrderEntry orderEntry) {
		orderEntry = toUnwrappedModel(orderEntry);

		boolean isNew = orderEntry.isNew();

		OrderEntryModelImpl orderEntryModelImpl = (OrderEntryModelImpl)orderEntry;

		if (Validator.isNull(orderEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			orderEntry.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (orderEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				orderEntry.setCreateDate(now);
			}
			else {
				orderEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!orderEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				orderEntry.setModifiedDate(now);
			}
			else {
				orderEntry.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (orderEntry.isNew()) {
				session.save(orderEntry);

				orderEntry.setNew(false);
			}
			else {
				orderEntry = (OrderEntry)session.merge(orderEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OrderEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { orderEntryModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { orderEntryModelImpl.getAccountEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((orderEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						orderEntryModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { orderEntryModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((orderEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						orderEntryModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] { orderEntryModelImpl.getAccountEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}
		}

		entityCache.putResult(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
			OrderEntryImpl.class, orderEntry.getPrimaryKey(), orderEntry, false);

		orderEntry.resetOriginalValues();

		return orderEntry;
	}

	protected OrderEntry toUnwrappedModel(OrderEntry orderEntry) {
		if (orderEntry instanceof OrderEntryImpl) {
			return orderEntry;
		}

		OrderEntryImpl orderEntryImpl = new OrderEntryImpl();

		orderEntryImpl.setNew(orderEntry.isNew());
		orderEntryImpl.setPrimaryKey(orderEntry.getPrimaryKey());

		orderEntryImpl.setUuid(orderEntry.getUuid());
		orderEntryImpl.setOrderEntryId(orderEntry.getOrderEntryId());
		orderEntryImpl.setUserId(orderEntry.getUserId());
		orderEntryImpl.setUserName(orderEntry.getUserName());
		orderEntryImpl.setCreateDate(orderEntry.getCreateDate());
		orderEntryImpl.setModifiedUserId(orderEntry.getModifiedUserId());
		orderEntryImpl.setModifiedUserName(orderEntry.getModifiedUserName());
		orderEntryImpl.setModifiedDate(orderEntry.getModifiedDate());
		orderEntryImpl.setAccountEntryId(orderEntry.getAccountEntryId());
		orderEntryImpl.setPurchaseOrderKey(orderEntry.getPurchaseOrderKey());
		orderEntryImpl.setStartDate(orderEntry.getStartDate());
		orderEntryImpl.setProrated(orderEntry.isProrated());
		orderEntryImpl.setActualStartDate(orderEntry.getActualStartDate());
		orderEntryImpl.setRenewCount(orderEntry.getRenewCount());
		orderEntryImpl.setStatus(orderEntry.getStatus());
		orderEntryImpl.setStatusByUserId(orderEntry.getStatusByUserId());
		orderEntryImpl.setStatusByUserName(orderEntry.getStatusByUserName());
		orderEntryImpl.setStatusDate(orderEntry.getStatusDate());
		orderEntryImpl.setStatusMessage(orderEntry.getStatusMessage());

		return orderEntryImpl;
	}

	/**
	 * Returns the order entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the order entry
	 * @return the order entry
	 * @throws NoSuchOrderEntryException if a order entry with the primary key could not be found
	 */
	@Override
	public OrderEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOrderEntryException {
		OrderEntry orderEntry = fetchByPrimaryKey(primaryKey);

		if (orderEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOrderEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return orderEntry;
	}

	/**
	 * Returns the order entry with the primary key or throws a {@link NoSuchOrderEntryException} if it could not be found.
	 *
	 * @param orderEntryId the primary key of the order entry
	 * @return the order entry
	 * @throws NoSuchOrderEntryException if a order entry with the primary key could not be found
	 */
	@Override
	public OrderEntry findByPrimaryKey(long orderEntryId)
		throws NoSuchOrderEntryException {
		return findByPrimaryKey((Serializable)orderEntryId);
	}

	/**
	 * Returns the order entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the order entry
	 * @return the order entry, or <code>null</code> if a order entry with the primary key could not be found
	 */
	@Override
	public OrderEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
				OrderEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OrderEntry orderEntry = (OrderEntry)serializable;

		if (orderEntry == null) {
			Session session = null;

			try {
				session = openSession();

				orderEntry = (OrderEntry)session.get(OrderEntryImpl.class,
						primaryKey);

				if (orderEntry != null) {
					cacheResult(orderEntry);
				}
				else {
					entityCache.putResult(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
						OrderEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
					OrderEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return orderEntry;
	}

	/**
	 * Returns the order entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orderEntryId the primary key of the order entry
	 * @return the order entry, or <code>null</code> if a order entry with the primary key could not be found
	 */
	@Override
	public OrderEntry fetchByPrimaryKey(long orderEntryId) {
		return fetchByPrimaryKey((Serializable)orderEntryId);
	}

	@Override
	public Map<Serializable, OrderEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OrderEntry> map = new HashMap<Serializable, OrderEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OrderEntry orderEntry = fetchByPrimaryKey(primaryKey);

			if (orderEntry != null) {
				map.put(primaryKey, orderEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
					OrderEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OrderEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ORDERENTRY_WHERE_PKS_IN);

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

			for (OrderEntry orderEntry : (List<OrderEntry>)q.list()) {
				map.put(orderEntry.getPrimaryKeyObj(), orderEntry);

				cacheResult(orderEntry);

				uncachedPrimaryKeys.remove(orderEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OrderEntryModelImpl.ENTITY_CACHE_ENABLED,
					OrderEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the order entries.
	 *
	 * @return the order entries
	 */
	@Override
	public List<OrderEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the order entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of order entries
	 * @param end the upper bound of the range of order entries (not inclusive)
	 * @return the range of order entries
	 */
	@Override
	public List<OrderEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the order entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of order entries
	 * @param end the upper bound of the range of order entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of order entries
	 */
	@Override
	public List<OrderEntry> findAll(int start, int end,
		OrderByComparator<OrderEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the order entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of order entries
	 * @param end the upper bound of the range of order entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of order entries
	 */
	@Override
	public List<OrderEntry> findAll(int start, int end,
		OrderByComparator<OrderEntry> orderByComparator,
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

		List<OrderEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OrderEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ORDERENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ORDERENTRY;

				if (pagination) {
					sql = sql.concat(OrderEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OrderEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OrderEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the order entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OrderEntry orderEntry : findAll()) {
			remove(orderEntry);
		}
	}

	/**
	 * Returns the number of order entries.
	 *
	 * @return the number of order entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ORDERENTRY);

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
		return OrderEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the order entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OrderEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ORDERENTRY = "SELECT orderEntry FROM OrderEntry orderEntry";
	private static final String _SQL_SELECT_ORDERENTRY_WHERE_PKS_IN = "SELECT orderEntry FROM OrderEntry orderEntry WHERE orderEntryId IN (";
	private static final String _SQL_SELECT_ORDERENTRY_WHERE = "SELECT orderEntry FROM OrderEntry orderEntry WHERE ";
	private static final String _SQL_COUNT_ORDERENTRY = "SELECT COUNT(orderEntry) FROM OrderEntry orderEntry";
	private static final String _SQL_COUNT_ORDERENTRY_WHERE = "SELECT COUNT(orderEntry) FROM OrderEntry orderEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "orderEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OrderEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OrderEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OrderEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}
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

import com.liferay.osb.exception.NoSuchOfferingEntryException;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.impl.OfferingEntryImpl;
import com.liferay.osb.model.impl.OfferingEntryModelImpl;
import com.liferay.osb.service.persistence.OfferingEntryPersistence;

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
 * The persistence implementation for the offering entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryPersistence
 * @see com.liferay.osb.service.persistence.OfferingEntryUtil
 * @generated
 */
@ProviderType
public class OfferingEntryPersistenceImpl extends BasePersistenceImpl<OfferingEntry>
	implements OfferingEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OfferingEntryUtil} to access the offering entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OfferingEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAccountEntryId", new String[] { Long.class.getName() },
			OfferingEntryModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the offering entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<OfferingEntry> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offering entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<OfferingEntry> orderByComparator,
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

		List<OfferingEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OfferingEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfferingEntry offeringEntry : list) {
					if ((accountEntryId != offeringEntry.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first offering entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry
	 * @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws NoSuchOfferingEntryException {
		OfferingEntry offeringEntry = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the first offering entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<OfferingEntry> orderByComparator) {
		List<OfferingEntry> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry
	 * @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws NoSuchOfferingEntryException {
		OfferingEntry offeringEntry = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the last offering entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<OfferingEntry> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<OfferingEntry> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering entries before and after the current offering entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param offeringEntryId the primary key of the current offering entry
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering entry
	 * @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 */
	@Override
	public OfferingEntry[] findByAccountEntryId_PrevAndNext(
		long offeringEntryId, long accountEntryId,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws NoSuchOfferingEntryException {
		OfferingEntry offeringEntry = findByPrimaryKey(offeringEntryId);

		Session session = null;

		try {
			session = openSession();

			OfferingEntry[] array = new OfferingEntryImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, offeringEntry,
					accountEntryId, orderByComparator, true);

			array[1] = offeringEntry;

			array[2] = getByAccountEntryId_PrevAndNext(session, offeringEntry,
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

	protected OfferingEntry getByAccountEntryId_PrevAndNext(Session session,
		OfferingEntry offeringEntry, long accountEntryId,
		OrderByComparator<OfferingEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

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
			query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the offering entries where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (OfferingEntry offeringEntry : findByAccountEntryId(
				accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(offeringEntry);
		}
	}

	/**
	 * Returns the number of offering entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching offering entries
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "offeringEntry.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERENTRYID =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByOrderEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByOrderEntryId", new String[] { Long.class.getName() },
			OfferingEntryModelImpl.ORDERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORDERENTRYID = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOrderEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the offering entries where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @return the matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByOrderEntryId(long orderEntryId) {
		return findByOrderEntryId(orderEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries where orderEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByOrderEntryId(long orderEntryId, int start,
		int end) {
		return findByOrderEntryId(orderEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries where orderEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByOrderEntryId(long orderEntryId, int start,
		int end, OrderByComparator<OfferingEntry> orderByComparator) {
		return findByOrderEntryId(orderEntryId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the offering entries where orderEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByOrderEntryId(long orderEntryId, int start,
		int end, OrderByComparator<OfferingEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID;
			finderArgs = new Object[] { orderEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERENTRYID;
			finderArgs = new Object[] {
					orderEntryId,
					
					start, end, orderByComparator
				};
		}

		List<OfferingEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OfferingEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfferingEntry offeringEntry : list) {
					if ((orderEntryId != offeringEntry.getOrderEntryId())) {
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

			query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderEntryId);

				if (!pagination) {
					list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first offering entry in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry
	 * @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry findByOrderEntryId_First(long orderEntryId,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws NoSuchOfferingEntryException {
		OfferingEntry offeringEntry = fetchByOrderEntryId_First(orderEntryId,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("orderEntryId=");
		msg.append(orderEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the first offering entry in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry fetchByOrderEntryId_First(long orderEntryId,
		OrderByComparator<OfferingEntry> orderByComparator) {
		List<OfferingEntry> list = findByOrderEntryId(orderEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering entry in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry
	 * @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry findByOrderEntryId_Last(long orderEntryId,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws NoSuchOfferingEntryException {
		OfferingEntry offeringEntry = fetchByOrderEntryId_Last(orderEntryId,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("orderEntryId=");
		msg.append(orderEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the last offering entry in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry fetchByOrderEntryId_Last(long orderEntryId,
		OrderByComparator<OfferingEntry> orderByComparator) {
		int count = countByOrderEntryId(orderEntryId);

		if (count == 0) {
			return null;
		}

		List<OfferingEntry> list = findByOrderEntryId(orderEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering entries before and after the current offering entry in the ordered set where orderEntryId = &#63;.
	 *
	 * @param offeringEntryId the primary key of the current offering entry
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering entry
	 * @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 */
	@Override
	public OfferingEntry[] findByOrderEntryId_PrevAndNext(
		long offeringEntryId, long orderEntryId,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws NoSuchOfferingEntryException {
		OfferingEntry offeringEntry = findByPrimaryKey(offeringEntryId);

		Session session = null;

		try {
			session = openSession();

			OfferingEntry[] array = new OfferingEntryImpl[3];

			array[0] = getByOrderEntryId_PrevAndNext(session, offeringEntry,
					orderEntryId, orderByComparator, true);

			array[1] = offeringEntry;

			array[2] = getByOrderEntryId_PrevAndNext(session, offeringEntry,
					orderEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfferingEntry getByOrderEntryId_PrevAndNext(Session session,
		OfferingEntry offeringEntry, long orderEntryId,
		OrderByComparator<OfferingEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2);

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
			query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(orderEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the offering entries where orderEntryId = &#63; from the database.
	 *
	 * @param orderEntryId the order entry ID
	 */
	@Override
	public void removeByOrderEntryId(long orderEntryId) {
		for (OfferingEntry offeringEntry : findByOrderEntryId(orderEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(offeringEntry);
		}
	}

	/**
	 * Returns the number of offering entries where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @return the number of matching offering entries
	 */
	@Override
	public int countByOrderEntryId(long orderEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ORDERENTRYID;

		Object[] finderArgs = new Object[] { orderEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderEntryId);

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

	private static final String _FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2 = "offeringEntry.orderEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI_OEI_T =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_AEI_OEI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_OEI_T =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_AEI_OEI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			OfferingEntryModelImpl.USERID_COLUMN_BITMASK |
			OfferingEntryModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			OfferingEntryModelImpl.ORDERENTRYID_COLUMN_BITMASK |
			OfferingEntryModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI_OEI_T = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI_OEI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @return the matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByU_AEI_OEI_T(long userId,
		long accountEntryId, long orderEntryId, int type) {
		return findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByU_AEI_OEI_T(long userId,
		long accountEntryId, long orderEntryId, int type, int start, int end) {
		return findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId, type,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByU_AEI_OEI_T(long userId,
		long accountEntryId, long orderEntryId, int type, int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId, type,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching offering entries
	 */
	@Override
	public List<OfferingEntry> findByU_AEI_OEI_T(long userId,
		long accountEntryId, long orderEntryId, int type, int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_OEI_T;
			finderArgs = new Object[] { userId, accountEntryId, orderEntryId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI_OEI_T;
			finderArgs = new Object[] {
					userId, accountEntryId, orderEntryId, type,
					
					start, end, orderByComparator
				};
		}

		List<OfferingEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OfferingEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfferingEntry offeringEntry : list) {
					if ((userId != offeringEntry.getUserId()) ||
							(accountEntryId != offeringEntry.getAccountEntryId()) ||
							(orderEntryId != offeringEntry.getOrderEntryId()) ||
							(type != offeringEntry.getType())) {
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

			query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_ORDERENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				qPos.add(orderEntryId);

				qPos.add(type);

				if (!pagination) {
					list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry
	 * @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry findByU_AEI_OEI_T_First(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws NoSuchOfferingEntryException {
		OfferingEntry offeringEntry = fetchByU_AEI_OEI_T_First(userId,
				accountEntryId, orderEntryId, type, orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", orderEntryId=");
		msg.append(orderEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the first offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry fetchByU_AEI_OEI_T_First(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator) {
		List<OfferingEntry> list = findByU_AEI_OEI_T(userId, accountEntryId,
				orderEntryId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry
	 * @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry findByU_AEI_OEI_T_Last(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws NoSuchOfferingEntryException {
		OfferingEntry offeringEntry = fetchByU_AEI_OEI_T_Last(userId,
				accountEntryId, orderEntryId, type, orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", orderEntryId=");
		msg.append(orderEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the last offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 */
	@Override
	public OfferingEntry fetchByU_AEI_OEI_T_Last(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator) {
		int count = countByU_AEI_OEI_T(userId, accountEntryId, orderEntryId,
				type);

		if (count == 0) {
			return null;
		}

		List<OfferingEntry> list = findByU_AEI_OEI_T(userId, accountEntryId,
				orderEntryId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering entries before and after the current offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param offeringEntryId the primary key of the current offering entry
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering entry
	 * @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 */
	@Override
	public OfferingEntry[] findByU_AEI_OEI_T_PrevAndNext(long offeringEntryId,
		long userId, long accountEntryId, long orderEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws NoSuchOfferingEntryException {
		OfferingEntry offeringEntry = findByPrimaryKey(offeringEntryId);

		Session session = null;

		try {
			session = openSession();

			OfferingEntry[] array = new OfferingEntryImpl[3];

			array[0] = getByU_AEI_OEI_T_PrevAndNext(session, offeringEntry,
					userId, accountEntryId, orderEntryId, type,
					orderByComparator, true);

			array[1] = offeringEntry;

			array[2] = getByU_AEI_OEI_T_PrevAndNext(session, offeringEntry,
					userId, accountEntryId, orderEntryId, type,
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

	protected OfferingEntry getByU_AEI_OEI_T_PrevAndNext(Session session,
		OfferingEntry offeringEntry, long userId, long accountEntryId,
		long orderEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_U_AEI_OEI_T_USERID_2);

		query.append(_FINDER_COLUMN_U_AEI_OEI_T_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_U_AEI_OEI_T_ORDERENTRYID_2);

		query.append(_FINDER_COLUMN_U_AEI_OEI_T_TYPE_2);

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
			query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(accountEntryId);

		qPos.add(orderEntryId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 */
	@Override
	public void removeByU_AEI_OEI_T(long userId, long accountEntryId,
		long orderEntryId, int type) {
		for (OfferingEntry offeringEntry : findByU_AEI_OEI_T(userId,
				accountEntryId, orderEntryId, type, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(offeringEntry);
		}
	}

	/**
	 * Returns the number of offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @return the number of matching offering entries
	 */
	@Override
	public int countByU_AEI_OEI_T(long userId, long accountEntryId,
		long orderEntryId, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_AEI_OEI_T;

		Object[] finderArgs = new Object[] {
				userId, accountEntryId, orderEntryId, type
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_ORDERENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				qPos.add(orderEntryId);

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

	private static final String _FINDER_COLUMN_U_AEI_OEI_T_USERID_2 = "offeringEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_OEI_T_ACCOUNTENTRYID_2 = "offeringEntry.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_OEI_T_ORDERENTRYID_2 = "offeringEntry.orderEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_OEI_T_TYPE_2 = "offeringEntry.type = ?";

	public OfferingEntryPersistenceImpl() {
		setModelClass(OfferingEntry.class);
	}

	/**
	 * Caches the offering entry in the entity cache if it is enabled.
	 *
	 * @param offeringEntry the offering entry
	 */
	@Override
	public void cacheResult(OfferingEntry offeringEntry) {
		entityCache.putResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryImpl.class, offeringEntry.getPrimaryKey(),
			offeringEntry);

		offeringEntry.resetOriginalValues();
	}

	/**
	 * Caches the offering entries in the entity cache if it is enabled.
	 *
	 * @param offeringEntries the offering entries
	 */
	@Override
	public void cacheResult(List<OfferingEntry> offeringEntries) {
		for (OfferingEntry offeringEntry : offeringEntries) {
			if (entityCache.getResult(
						OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
						OfferingEntryImpl.class, offeringEntry.getPrimaryKey()) == null) {
				cacheResult(offeringEntry);
			}
			else {
				offeringEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all offering entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OfferingEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the offering entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OfferingEntry offeringEntry) {
		entityCache.removeResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryImpl.class, offeringEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<OfferingEntry> offeringEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OfferingEntry offeringEntry : offeringEntries) {
			entityCache.removeResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
				OfferingEntryImpl.class, offeringEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new offering entry with the primary key. Does not add the offering entry to the database.
	 *
	 * @param offeringEntryId the primary key for the new offering entry
	 * @return the new offering entry
	 */
	@Override
	public OfferingEntry create(long offeringEntryId) {
		OfferingEntry offeringEntry = new OfferingEntryImpl();

		offeringEntry.setNew(true);
		offeringEntry.setPrimaryKey(offeringEntryId);

		return offeringEntry;
	}

	/**
	 * Removes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offeringEntryId the primary key of the offering entry
	 * @return the offering entry that was removed
	 * @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 */
	@Override
	public OfferingEntry remove(long offeringEntryId)
		throws NoSuchOfferingEntryException {
		return remove((Serializable)offeringEntryId);
	}

	/**
	 * Removes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the offering entry
	 * @return the offering entry that was removed
	 * @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 */
	@Override
	public OfferingEntry remove(Serializable primaryKey)
		throws NoSuchOfferingEntryException {
		Session session = null;

		try {
			session = openSession();

			OfferingEntry offeringEntry = (OfferingEntry)session.get(OfferingEntryImpl.class,
					primaryKey);

			if (offeringEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOfferingEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(offeringEntry);
		}
		catch (NoSuchOfferingEntryException nsee) {
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
	protected OfferingEntry removeImpl(OfferingEntry offeringEntry) {
		offeringEntry = toUnwrappedModel(offeringEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(offeringEntry)) {
				offeringEntry = (OfferingEntry)session.get(OfferingEntryImpl.class,
						offeringEntry.getPrimaryKeyObj());
			}

			if (offeringEntry != null) {
				session.delete(offeringEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (offeringEntry != null) {
			clearCache(offeringEntry);
		}

		return offeringEntry;
	}

	@Override
	public OfferingEntry updateImpl(OfferingEntry offeringEntry) {
		offeringEntry = toUnwrappedModel(offeringEntry);

		boolean isNew = offeringEntry.isNew();

		OfferingEntryModelImpl offeringEntryModelImpl = (OfferingEntryModelImpl)offeringEntry;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (offeringEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				offeringEntry.setCreateDate(now);
			}
			else {
				offeringEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!offeringEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				offeringEntry.setModifiedDate(now);
			}
			else {
				offeringEntry.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (offeringEntry.isNew()) {
				session.save(offeringEntry);

				offeringEntry.setNew(false);
			}
			else {
				offeringEntry = (OfferingEntry)session.merge(offeringEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OfferingEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					offeringEntryModelImpl.getAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			args = new Object[] { offeringEntryModelImpl.getOrderEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ORDERENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID,
				args);

			args = new Object[] {
					offeringEntryModelImpl.getUserId(),
					offeringEntryModelImpl.getAccountEntryId(),
					offeringEntryModelImpl.getOrderEntryId(),
					offeringEntryModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI_OEI_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_OEI_T,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((offeringEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						offeringEntryModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] { offeringEntryModelImpl.getAccountEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((offeringEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						offeringEntryModelImpl.getOriginalOrderEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORDERENTRYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID,
					args);

				args = new Object[] { offeringEntryModelImpl.getOrderEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORDERENTRYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID,
					args);
			}

			if ((offeringEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_OEI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						offeringEntryModelImpl.getOriginalUserId(),
						offeringEntryModelImpl.getOriginalAccountEntryId(),
						offeringEntryModelImpl.getOriginalOrderEntryId(),
						offeringEntryModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI_OEI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_OEI_T,
					args);

				args = new Object[] {
						offeringEntryModelImpl.getUserId(),
						offeringEntryModelImpl.getAccountEntryId(),
						offeringEntryModelImpl.getOrderEntryId(),
						offeringEntryModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI_OEI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_OEI_T,
					args);
			}
		}

		entityCache.putResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryImpl.class, offeringEntry.getPrimaryKey(),
			offeringEntry, false);

		offeringEntry.resetOriginalValues();

		return offeringEntry;
	}

	protected OfferingEntry toUnwrappedModel(OfferingEntry offeringEntry) {
		if (offeringEntry instanceof OfferingEntryImpl) {
			return offeringEntry;
		}

		OfferingEntryImpl offeringEntryImpl = new OfferingEntryImpl();

		offeringEntryImpl.setNew(offeringEntry.isNew());
		offeringEntryImpl.setPrimaryKey(offeringEntry.getPrimaryKey());

		offeringEntryImpl.setOfferingEntryId(offeringEntry.getOfferingEntryId());
		offeringEntryImpl.setUserId(offeringEntry.getUserId());
		offeringEntryImpl.setUserName(offeringEntry.getUserName());
		offeringEntryImpl.setCreateDate(offeringEntry.getCreateDate());
		offeringEntryImpl.setModifiedDate(offeringEntry.getModifiedDate());
		offeringEntryImpl.setAccountEntryId(offeringEntry.getAccountEntryId());
		offeringEntryImpl.setOrderEntryId(offeringEntry.getOrderEntryId());
		offeringEntryImpl.setProductEntryId(offeringEntry.getProductEntryId());
		offeringEntryImpl.setSupportResponseId(offeringEntry.getSupportResponseId());
		offeringEntryImpl.setProductDescription(offeringEntry.getProductDescription());
		offeringEntryImpl.setType(offeringEntry.getType());
		offeringEntryImpl.setVersion(offeringEntry.getVersion());
		offeringEntryImpl.setPlatform(offeringEntry.getPlatform());
		offeringEntryImpl.setPlatformVersion(offeringEntry.getPlatformVersion());
		offeringEntryImpl.setLicenses(offeringEntry.isLicenses());
		offeringEntryImpl.setLicenseLifetime(offeringEntry.getLicenseLifetime());
		offeringEntryImpl.setMaxConcurrentUsers(offeringEntry.getMaxConcurrentUsers());
		offeringEntryImpl.setMaxUsers(offeringEntry.getMaxUsers());
		offeringEntryImpl.setSupportTickets(offeringEntry.isSupportTickets());
		offeringEntryImpl.setSupportLifetime(offeringEntry.getSupportLifetime());
		offeringEntryImpl.setSupportEndDate(offeringEntry.getSupportEndDate());
		offeringEntryImpl.setSizing(offeringEntry.getSizing());
		offeringEntryImpl.setQuantity(offeringEntry.getQuantity());
		offeringEntryImpl.setStatus(offeringEntry.getStatus());

		return offeringEntryImpl;
	}

	/**
	 * Returns the offering entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering entry
	 * @return the offering entry
	 * @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 */
	@Override
	public OfferingEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOfferingEntryException {
		OfferingEntry offeringEntry = fetchByPrimaryKey(primaryKey);

		if (offeringEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOfferingEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return offeringEntry;
	}

	/**
	 * Returns the offering entry with the primary key or throws a {@link NoSuchOfferingEntryException} if it could not be found.
	 *
	 * @param offeringEntryId the primary key of the offering entry
	 * @return the offering entry
	 * @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 */
	@Override
	public OfferingEntry findByPrimaryKey(long offeringEntryId)
		throws NoSuchOfferingEntryException {
		return findByPrimaryKey((Serializable)offeringEntryId);
	}

	/**
	 * Returns the offering entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering entry
	 * @return the offering entry, or <code>null</code> if a offering entry with the primary key could not be found
	 */
	@Override
	public OfferingEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
				OfferingEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OfferingEntry offeringEntry = (OfferingEntry)serializable;

		if (offeringEntry == null) {
			Session session = null;

			try {
				session = openSession();

				offeringEntry = (OfferingEntry)session.get(OfferingEntryImpl.class,
						primaryKey);

				if (offeringEntry != null) {
					cacheResult(offeringEntry);
				}
				else {
					entityCache.putResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
						OfferingEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
					OfferingEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return offeringEntry;
	}

	/**
	 * Returns the offering entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param offeringEntryId the primary key of the offering entry
	 * @return the offering entry, or <code>null</code> if a offering entry with the primary key could not be found
	 */
	@Override
	public OfferingEntry fetchByPrimaryKey(long offeringEntryId) {
		return fetchByPrimaryKey((Serializable)offeringEntryId);
	}

	@Override
	public Map<Serializable, OfferingEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OfferingEntry> map = new HashMap<Serializable, OfferingEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OfferingEntry offeringEntry = fetchByPrimaryKey(primaryKey);

			if (offeringEntry != null) {
				map.put(primaryKey, offeringEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
					OfferingEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OfferingEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OFFERINGENTRY_WHERE_PKS_IN);

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

			for (OfferingEntry offeringEntry : (List<OfferingEntry>)q.list()) {
				map.put(offeringEntry.getPrimaryKeyObj(), offeringEntry);

				cacheResult(offeringEntry);

				uncachedPrimaryKeys.remove(offeringEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
					OfferingEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the offering entries.
	 *
	 * @return the offering entries
	 */
	@Override
	public List<OfferingEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of offering entries
	 */
	@Override
	public List<OfferingEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offering entries
	 */
	@Override
	public List<OfferingEntry> findAll(int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offering entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of offering entries
	 */
	@Override
	public List<OfferingEntry> findAll(int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator,
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

		List<OfferingEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OfferingEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OFFERINGENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OFFERINGENTRY;

				if (pagination) {
					sql = sql.concat(OfferingEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the offering entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OfferingEntry offeringEntry : findAll()) {
			remove(offeringEntry);
		}
	}

	/**
	 * Returns the number of offering entries.
	 *
	 * @return the number of offering entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OFFERINGENTRY);

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
		return OfferingEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the offering entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OfferingEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_OFFERINGENTRY = "SELECT offeringEntry FROM OfferingEntry offeringEntry";
	private static final String _SQL_SELECT_OFFERINGENTRY_WHERE_PKS_IN = "SELECT offeringEntry FROM OfferingEntry offeringEntry WHERE offeringEntryId IN (";
	private static final String _SQL_SELECT_OFFERINGENTRY_WHERE = "SELECT offeringEntry FROM OfferingEntry offeringEntry WHERE ";
	private static final String _SQL_COUNT_OFFERINGENTRY = "SELECT COUNT(offeringEntry) FROM OfferingEntry offeringEntry";
	private static final String _SQL_COUNT_OFFERINGENTRY_WHERE = "SELECT COUNT(offeringEntry) FROM OfferingEntry offeringEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "offeringEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OfferingEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OfferingEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OfferingEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
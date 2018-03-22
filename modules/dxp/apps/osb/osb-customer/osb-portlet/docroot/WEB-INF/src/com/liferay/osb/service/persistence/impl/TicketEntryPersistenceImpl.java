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

import com.liferay.osb.exception.NoSuchTicketEntryException;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.impl.TicketEntryImpl;
import com.liferay.osb.model.impl.TicketEntryModelImpl;
import com.liferay.osb.service.persistence.TicketEntryPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the ticket entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryPersistence
 * @see com.liferay.osb.service.persistence.TicketEntryUtil
 * @generated
 */
@ProviderType
public class TicketEntryPersistenceImpl extends BasePersistenceImpl<TicketEntry>
	implements TicketEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketEntryUtil} to access the ticket entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			TicketEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			TicketEntryModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the ticket entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the ticket entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @return the range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByCompanyId(long companyId, int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<TicketEntry> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<TicketEntry> list = null;

		if (retrieveFromCache) {
			list = (List<TicketEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketEntry ticketEntry : list) {
					if ((companyId != ticketEntry.getCompanyId())) {
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

			query.append(_SQL_SELECT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry
	 * @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry findByCompanyId_First(long companyId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the first ticket entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByCompanyId_First(long companyId,
		OrderByComparator<TicketEntry> orderByComparator) {
		List<TicketEntry> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry
	 * @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry findByCompanyId_Last(long companyId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the last ticket entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByCompanyId_Last(long companyId,
		OrderByComparator<TicketEntry> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<TicketEntry> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket entries before and after the current ticket entry in the ordered set where companyId = &#63;.
	 *
	 * @param ticketEntryId the primary key of the current ticket entry
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket entry
	 * @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 */
	@Override
	public TicketEntry[] findByCompanyId_PrevAndNext(long ticketEntryId,
		long companyId, OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = findByPrimaryKey(ticketEntryId);

		Session session = null;

		try {
			session = openSession();

			TicketEntry[] array = new TicketEntryImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, ticketEntry,
					companyId, orderByComparator, true);

			array[1] = ticketEntry;

			array[2] = getByCompanyId_PrevAndNext(session, ticketEntry,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketEntry getByCompanyId_PrevAndNext(Session session,
		TicketEntry ticketEntry, long companyId,
		OrderByComparator<TicketEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETENTRY_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket entries where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (TicketEntry ticketEntry : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketEntry);
		}
	}

	/**
	 * Returns the number of ticket entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching ticket entries
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "ticketEntry.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMODIFIEDDATE =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGtModifiedDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMODIFIEDDATE =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGtModifiedDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the ticket entries where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByGtModifiedDate(Date modifiedDate) {
		return findByGtModifiedDate(modifiedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket entries where modifiedDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @return the range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByGtModifiedDate(Date modifiedDate, int start,
		int end) {
		return findByGtModifiedDate(modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket entries where modifiedDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByGtModifiedDate(Date modifiedDate, int start,
		int end, OrderByComparator<TicketEntry> orderByComparator) {
		return findByGtModifiedDate(modifiedDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket entries where modifiedDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByGtModifiedDate(Date modifiedDate, int start,
		int end, OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMODIFIEDDATE;
		finderArgs = new Object[] { modifiedDate, start, end, orderByComparator };

		List<TicketEntry> list = null;

		if (retrieveFromCache) {
			list = (List<TicketEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketEntry ticketEntry : list) {
					if ((modifiedDate.getTime() > ticketEntry.getModifiedDate()
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
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TICKETENTRY_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				if (!pagination) {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket entry in the ordered set where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry
	 * @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry findByGtModifiedDate_First(Date modifiedDate,
		OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByGtModifiedDate_First(modifiedDate,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the first ticket entry in the ordered set where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByGtModifiedDate_First(Date modifiedDate,
		OrderByComparator<TicketEntry> orderByComparator) {
		List<TicketEntry> list = findByGtModifiedDate(modifiedDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket entry in the ordered set where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry
	 * @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry findByGtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByGtModifiedDate_Last(modifiedDate,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the last ticket entry in the ordered set where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByGtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<TicketEntry> orderByComparator) {
		int count = countByGtModifiedDate(modifiedDate);

		if (count == 0) {
			return null;
		}

		List<TicketEntry> list = findByGtModifiedDate(modifiedDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket entries before and after the current ticket entry in the ordered set where modifiedDate &ge; &#63;.
	 *
	 * @param ticketEntryId the primary key of the current ticket entry
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket entry
	 * @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 */
	@Override
	public TicketEntry[] findByGtModifiedDate_PrevAndNext(long ticketEntryId,
		Date modifiedDate, OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = findByPrimaryKey(ticketEntryId);

		Session session = null;

		try {
			session = openSession();

			TicketEntry[] array = new TicketEntryImpl[3];

			array[0] = getByGtModifiedDate_PrevAndNext(session, ticketEntry,
					modifiedDate, orderByComparator, true);

			array[1] = ticketEntry;

			array[2] = getByGtModifiedDate_PrevAndNext(session, ticketEntry,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketEntry getByGtModifiedDate_PrevAndNext(Session session,
		TicketEntry ticketEntry, Date modifiedDate,
		OrderByComparator<TicketEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETENTRY_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_2);
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
			query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket entries where modifiedDate &ge; &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 */
	@Override
	public void removeByGtModifiedDate(Date modifiedDate) {
		for (TicketEntry ticketEntry : findByGtModifiedDate(modifiedDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketEntry);
		}
	}

	/**
	 * Returns the number of ticket entries where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the number of matching ticket entries
	 */
	@Override
	public int countByGtModifiedDate(Date modifiedDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMODIFIEDDATE;

		Object[] finderArgs = new Object[] { modifiedDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETENTRY_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
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

	private static final String _FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_1 = "ticketEntry.modifiedDate IS NULL";
	private static final String _FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_2 = "ticketEntry.modifiedDate >= ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			TicketEntryModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			TicketEntryModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the ticket entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @return the range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<TicketEntry> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<TicketEntry> orderByComparator,
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

		List<TicketEntry> list = null;

		if (retrieveFromCache) {
			list = (List<TicketEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketEntry ticketEntry : list) {
					if ((accountEntryId != ticketEntry.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry
	 * @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the first ticket entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<TicketEntry> orderByComparator) {
		List<TicketEntry> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry
	 * @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the last ticket entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<TicketEntry> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<TicketEntry> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket entries before and after the current ticket entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param ticketEntryId the primary key of the current ticket entry
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket entry
	 * @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 */
	@Override
	public TicketEntry[] findByAccountEntryId_PrevAndNext(long ticketEntryId,
		long accountEntryId, OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = findByPrimaryKey(ticketEntryId);

		Session session = null;

		try {
			session = openSession();

			TicketEntry[] array = new TicketEntryImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, ticketEntry,
					accountEntryId, orderByComparator, true);

			array[1] = ticketEntry;

			array[2] = getByAccountEntryId_PrevAndNext(session, ticketEntry,
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

	protected TicketEntry getByAccountEntryId_PrevAndNext(Session session,
		TicketEntry ticketEntry, long accountEntryId,
		OrderByComparator<TicketEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETENTRY_WHERE);

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
			query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket entries where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (TicketEntry ticketEntry : findByAccountEntryId(accountEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketEntry);
		}
	}

	/**
	 * Returns the number of ticket entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching ticket entries
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "ticketEntry.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OFFERINGENTRYID =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOfferingEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOfferingEntryId",
			new String[] { Long.class.getName() },
			TicketEntryModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			TicketEntryModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OFFERINGENTRYID = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByOfferingEntryId", new String[] { Long.class.getName() });

	/**
	 * Returns all the ticket entries where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByOfferingEntryId(long offeringEntryId) {
		return findByOfferingEntryId(offeringEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket entries where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @return the range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByOfferingEntryId(long offeringEntryId,
		int start, int end) {
		return findByOfferingEntryId(offeringEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket entries where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByOfferingEntryId(long offeringEntryId,
		int start, int end, OrderByComparator<TicketEntry> orderByComparator) {
		return findByOfferingEntryId(offeringEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket entries where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByOfferingEntryId(long offeringEntryId,
		int start, int end, OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID;
			finderArgs = new Object[] { offeringEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OFFERINGENTRYID;
			finderArgs = new Object[] {
					offeringEntryId,
					
					start, end, orderByComparator
				};
		}

		List<TicketEntry> list = null;

		if (retrieveFromCache) {
			list = (List<TicketEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketEntry ticketEntry : list) {
					if ((offeringEntryId != ticketEntry.getOfferingEntryId())) {
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

			query.append(_SQL_SELECT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				if (!pagination) {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket entry in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry
	 * @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry findByOfferingEntryId_First(long offeringEntryId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByOfferingEntryId_First(offeringEntryId,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the first ticket entry in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByOfferingEntryId_First(long offeringEntryId,
		OrderByComparator<TicketEntry> orderByComparator) {
		List<TicketEntry> list = findByOfferingEntryId(offeringEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket entry in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry
	 * @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry findByOfferingEntryId_Last(long offeringEntryId,
		OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByOfferingEntryId_Last(offeringEntryId,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the last ticket entry in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByOfferingEntryId_Last(long offeringEntryId,
		OrderByComparator<TicketEntry> orderByComparator) {
		int count = countByOfferingEntryId(offeringEntryId);

		if (count == 0) {
			return null;
		}

		List<TicketEntry> list = findByOfferingEntryId(offeringEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket entries before and after the current ticket entry in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param ticketEntryId the primary key of the current ticket entry
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket entry
	 * @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 */
	@Override
	public TicketEntry[] findByOfferingEntryId_PrevAndNext(long ticketEntryId,
		long offeringEntryId, OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = findByPrimaryKey(ticketEntryId);

		Session session = null;

		try {
			session = openSession();

			TicketEntry[] array = new TicketEntryImpl[3];

			array[0] = getByOfferingEntryId_PrevAndNext(session, ticketEntry,
					offeringEntryId, orderByComparator, true);

			array[1] = ticketEntry;

			array[2] = getByOfferingEntryId_PrevAndNext(session, ticketEntry,
					offeringEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketEntry getByOfferingEntryId_PrevAndNext(Session session,
		TicketEntry ticketEntry, long offeringEntryId,
		OrderByComparator<TicketEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETENTRY_WHERE);

		query.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

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
			query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket entries where offeringEntryId = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 */
	@Override
	public void removeByOfferingEntryId(long offeringEntryId) {
		for (TicketEntry ticketEntry : findByOfferingEntryId(offeringEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketEntry);
		}
	}

	/**
	 * Returns the number of ticket entries where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the number of matching ticket entries
	 */
	@Override
	public int countByOfferingEntryId(long offeringEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_OFFERINGENTRYID;

		Object[] finderArgs = new Object[] { offeringEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

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

	private static final String _FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2 =
		"ticketEntry.offeringEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_TI = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAEI_TI",
			new String[] { Long.class.getName(), Long.class.getName() },
			TicketEntryModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			TicketEntryModelImpl.TICKETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_TI = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_TI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or throws a {@link NoSuchTicketEntryException} if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param ticketId the ticket ID
	 * @return the matching ticket entry
	 * @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry findByAEI_TI(long accountEntryId, long ticketId)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByAEI_TI(accountEntryId, ticketId);

		if (ticketEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEntryId=");
			msg.append(accountEntryId);

			msg.append(", ticketId=");
			msg.append(ticketId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTicketEntryException(msg.toString());
		}

		return ticketEntry;
	}

	/**
	 * Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param ticketId the ticket ID
	 * @return the matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByAEI_TI(long accountEntryId, long ticketId) {
		return fetchByAEI_TI(accountEntryId, ticketId, true);
	}

	/**
	 * Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param ticketId the ticket ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByAEI_TI(long accountEntryId, long ticketId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { accountEntryId, ticketId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_AEI_TI,
					finderArgs, this);
		}

		if (result instanceof TicketEntry) {
			TicketEntry ticketEntry = (TicketEntry)result;

			if ((accountEntryId != ticketEntry.getAccountEntryId()) ||
					(ticketId != ticketEntry.getTicketId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_AEI_TI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_TI_TICKETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(ticketId);

				List<TicketEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_TI,
						finderArgs, list);
				}
				else {
					TicketEntry ticketEntry = list.get(0);

					result = ticketEntry;

					cacheResult(ticketEntry);

					if ((ticketEntry.getAccountEntryId() != accountEntryId) ||
							(ticketEntry.getTicketId() != ticketId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_TI,
							finderArgs, ticketEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_TI, finderArgs);

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
			return (TicketEntry)result;
		}
	}

	/**
	 * Removes the ticket entry where accountEntryId = &#63; and ticketId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param ticketId the ticket ID
	 * @return the ticket entry that was removed
	 */
	@Override
	public TicketEntry removeByAEI_TI(long accountEntryId, long ticketId)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = findByAEI_TI(accountEntryId, ticketId);

		return remove(ticketEntry);
	}

	/**
	 * Returns the number of ticket entries where accountEntryId = &#63; and ticketId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param ticketId the ticket ID
	 * @return the number of matching ticket entries
	 */
	@Override
	public int countByAEI_TI(long accountEntryId, long ticketId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_TI;

		Object[] finderArgs = new Object[] { accountEntryId, ticketId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_AEI_TI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_TI_TICKETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(ticketId);

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

	private static final String _FINDER_COLUMN_AEI_TI_ACCOUNTENTRYID_2 = "ticketEntry.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_TI_TICKETID_2 = "ticketEntry.ticketId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_NOTR = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_NotR",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_NOTR =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByOEI_NotR",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @return the matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByOEI_NotR(long offeringEntryId, int resolution) {
		return findByOEI_NotR(offeringEntryId, resolution, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @return the range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution, int start, int end) {
		return findByOEI_NotR(offeringEntryId, resolution, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution, int start, int end,
		OrderByComparator<TicketEntry> orderByComparator) {
		return findByOEI_NotR(offeringEntryId, resolution, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket entries
	 */
	@Override
	public List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution, int start, int end,
		OrderByComparator<TicketEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_NOTR;
		finderArgs = new Object[] {
				offeringEntryId, resolution,
				
				start, end, orderByComparator
			};

		List<TicketEntry> list = null;

		if (retrieveFromCache) {
			list = (List<TicketEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketEntry ticketEntry : list) {
					if ((offeringEntryId != ticketEntry.getOfferingEntryId()) ||
							(resolution == ticketEntry.getResolution())) {
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

			query.append(_SQL_SELECT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_OEI_NOTR_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_NOTR_RESOLUTION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(resolution);

				if (!pagination) {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry
	 * @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry findByOEI_NotR_First(long offeringEntryId,
		int resolution, OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByOEI_NotR_First(offeringEntryId,
				resolution, orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", resolution=");
		msg.append(resolution);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the first ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByOEI_NotR_First(long offeringEntryId,
		int resolution, OrderByComparator<TicketEntry> orderByComparator) {
		List<TicketEntry> list = findByOEI_NotR(offeringEntryId, resolution, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry
	 * @throws NoSuchTicketEntryException if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry findByOEI_NotR_Last(long offeringEntryId,
		int resolution, OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByOEI_NotR_Last(offeringEntryId,
				resolution, orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", resolution=");
		msg.append(resolution);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the last ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 */
	@Override
	public TicketEntry fetchByOEI_NotR_Last(long offeringEntryId,
		int resolution, OrderByComparator<TicketEntry> orderByComparator) {
		int count = countByOEI_NotR(offeringEntryId, resolution);

		if (count == 0) {
			return null;
		}

		List<TicketEntry> list = findByOEI_NotR(offeringEntryId, resolution,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket entries before and after the current ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param ticketEntryId the primary key of the current ticket entry
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket entry
	 * @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 */
	@Override
	public TicketEntry[] findByOEI_NotR_PrevAndNext(long ticketEntryId,
		long offeringEntryId, int resolution,
		OrderByComparator<TicketEntry> orderByComparator)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = findByPrimaryKey(ticketEntryId);

		Session session = null;

		try {
			session = openSession();

			TicketEntry[] array = new TicketEntryImpl[3];

			array[0] = getByOEI_NotR_PrevAndNext(session, ticketEntry,
					offeringEntryId, resolution, orderByComparator, true);

			array[1] = ticketEntry;

			array[2] = getByOEI_NotR_PrevAndNext(session, ticketEntry,
					offeringEntryId, resolution, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketEntry getByOEI_NotR_PrevAndNext(Session session,
		TicketEntry ticketEntry, long offeringEntryId, int resolution,
		OrderByComparator<TicketEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TICKETENTRY_WHERE);

		query.append(_FINDER_COLUMN_OEI_NOTR_OFFERINGENTRYID_2);

		query.append(_FINDER_COLUMN_OEI_NOTR_RESOLUTION_2);

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
			query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		qPos.add(resolution);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 */
	@Override
	public void removeByOEI_NotR(long offeringEntryId, int resolution) {
		for (TicketEntry ticketEntry : findByOEI_NotR(offeringEntryId,
				resolution, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketEntry);
		}
	}

	/**
	 * Returns the number of ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @return the number of matching ticket entries
	 */
	@Override
	public int countByOEI_NotR(long offeringEntryId, int resolution) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_NOTR;

		Object[] finderArgs = new Object[] { offeringEntryId, resolution };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_OEI_NOTR_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_NOTR_RESOLUTION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(resolution);

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

	private static final String _FINDER_COLUMN_OEI_NOTR_OFFERINGENTRYID_2 = "ticketEntry.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_NOTR_RESOLUTION_2 = "ticketEntry.resolution != ?";

	public TicketEntryPersistenceImpl() {
		setModelClass(TicketEntry.class);
	}

	/**
	 * Caches the ticket entry in the entity cache if it is enabled.
	 *
	 * @param ticketEntry the ticket entry
	 */
	@Override
	public void cacheResult(TicketEntry ticketEntry) {
		entityCache.putResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryImpl.class, ticketEntry.getPrimaryKey(), ticketEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_TI,
			new Object[] {
				ticketEntry.getAccountEntryId(), ticketEntry.getTicketId()
			}, ticketEntry);

		ticketEntry.resetOriginalValues();
	}

	/**
	 * Caches the ticket entries in the entity cache if it is enabled.
	 *
	 * @param ticketEntries the ticket entries
	 */
	@Override
	public void cacheResult(List<TicketEntry> ticketEntries) {
		for (TicketEntry ticketEntry : ticketEntries) {
			if (entityCache.getResult(
						TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
						TicketEntryImpl.class, ticketEntry.getPrimaryKey()) == null) {
				cacheResult(ticketEntry);
			}
			else {
				ticketEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TicketEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketEntry ticketEntry) {
		entityCache.removeResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryImpl.class, ticketEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TicketEntryModelImpl)ticketEntry, true);
	}

	@Override
	public void clearCache(List<TicketEntry> ticketEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketEntry ticketEntry : ticketEntries) {
			entityCache.removeResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
				TicketEntryImpl.class, ticketEntry.getPrimaryKey());

			clearUniqueFindersCache((TicketEntryModelImpl)ticketEntry, true);
		}
	}

	protected void cacheUniqueFindersCache(
		TicketEntryModelImpl ticketEntryModelImpl) {
		Object[] args = new Object[] {
				ticketEntryModelImpl.getAccountEntryId(),
				ticketEntryModelImpl.getTicketId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_AEI_TI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_TI, args,
			ticketEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		TicketEntryModelImpl ticketEntryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					ticketEntryModelImpl.getAccountEntryId(),
					ticketEntryModelImpl.getTicketId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_TI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_TI, args);
		}

		if ((ticketEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_TI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					ticketEntryModelImpl.getOriginalAccountEntryId(),
					ticketEntryModelImpl.getOriginalTicketId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_TI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_TI, args);
		}
	}

	/**
	 * Creates a new ticket entry with the primary key. Does not add the ticket entry to the database.
	 *
	 * @param ticketEntryId the primary key for the new ticket entry
	 * @return the new ticket entry
	 */
	@Override
	public TicketEntry create(long ticketEntryId) {
		TicketEntry ticketEntry = new TicketEntryImpl();

		ticketEntry.setNew(true);
		ticketEntry.setPrimaryKey(ticketEntryId);

		ticketEntry.setCompanyId(companyProvider.getCompanyId());

		return ticketEntry;
	}

	/**
	 * Removes the ticket entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketEntryId the primary key of the ticket entry
	 * @return the ticket entry that was removed
	 * @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 */
	@Override
	public TicketEntry remove(long ticketEntryId)
		throws NoSuchTicketEntryException {
		return remove((Serializable)ticketEntryId);
	}

	/**
	 * Removes the ticket entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket entry
	 * @return the ticket entry that was removed
	 * @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 */
	@Override
	public TicketEntry remove(Serializable primaryKey)
		throws NoSuchTicketEntryException {
		Session session = null;

		try {
			session = openSession();

			TicketEntry ticketEntry = (TicketEntry)session.get(TicketEntryImpl.class,
					primaryKey);

			if (ticketEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketEntry);
		}
		catch (NoSuchTicketEntryException nsee) {
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
	protected TicketEntry removeImpl(TicketEntry ticketEntry) {
		ticketEntry = toUnwrappedModel(ticketEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ticketEntry)) {
				ticketEntry = (TicketEntry)session.get(TicketEntryImpl.class,
						ticketEntry.getPrimaryKeyObj());
			}

			if (ticketEntry != null) {
				session.delete(ticketEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ticketEntry != null) {
			clearCache(ticketEntry);
		}

		return ticketEntry;
	}

	@Override
	public TicketEntry updateImpl(TicketEntry ticketEntry) {
		ticketEntry = toUnwrappedModel(ticketEntry);

		boolean isNew = ticketEntry.isNew();

		TicketEntryModelImpl ticketEntryModelImpl = (TicketEntryModelImpl)ticketEntry;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (ticketEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				ticketEntry.setCreateDate(now);
			}
			else {
				ticketEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!ticketEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				ticketEntry.setModifiedDate(now);
			}
			else {
				ticketEntry.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (ticketEntry.isNew()) {
				session.save(ticketEntry);

				ticketEntry.setNew(false);
			}
			else {
				ticketEntry = (TicketEntry)session.merge(ticketEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TicketEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { ticketEntryModelImpl.getCompanyId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
				args);

			args = new Object[] { ticketEntryModelImpl.getAccountEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			args = new Object[] { ticketEntryModelImpl.getOfferingEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ticketEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketEntryModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { ticketEntryModelImpl.getCompanyId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((ticketEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketEntryModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] { ticketEntryModelImpl.getAccountEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((ticketEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketEntryModelImpl.getOriginalOfferingEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID,
					args);

				args = new Object[] { ticketEntryModelImpl.getOfferingEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID,
					args);
			}
		}

		entityCache.putResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryImpl.class, ticketEntry.getPrimaryKey(), ticketEntry,
			false);

		clearUniqueFindersCache(ticketEntryModelImpl, false);
		cacheUniqueFindersCache(ticketEntryModelImpl);

		ticketEntry.resetOriginalValues();

		return ticketEntry;
	}

	protected TicketEntry toUnwrappedModel(TicketEntry ticketEntry) {
		if (ticketEntry instanceof TicketEntryImpl) {
			return ticketEntry;
		}

		TicketEntryImpl ticketEntryImpl = new TicketEntryImpl();

		ticketEntryImpl.setNew(ticketEntry.isNew());
		ticketEntryImpl.setPrimaryKey(ticketEntry.getPrimaryKey());

		ticketEntryImpl.setTicketEntryId(ticketEntry.getTicketEntryId());
		ticketEntryImpl.setCompanyId(ticketEntry.getCompanyId());
		ticketEntryImpl.setUserId(ticketEntry.getUserId());
		ticketEntryImpl.setUserName(ticketEntry.getUserName());
		ticketEntryImpl.setCreateDate(ticketEntry.getCreateDate());
		ticketEntryImpl.setModifiedDate(ticketEntry.getModifiedDate());
		ticketEntryImpl.setAccountEntryId(ticketEntry.getAccountEntryId());
		ticketEntryImpl.setOrderEntryId(ticketEntry.getOrderEntryId());
		ticketEntryImpl.setProductEntryId(ticketEntry.getProductEntryId());
		ticketEntryImpl.setSupportResponseId(ticketEntry.getSupportResponseId());
		ticketEntryImpl.setOfferingEntryId(ticketEntry.getOfferingEntryId());
		ticketEntryImpl.setSupportRegionId(ticketEntry.getSupportRegionId());
		ticketEntryImpl.setLanguageId(ticketEntry.getLanguageId());
		ticketEntryImpl.setTicketId(ticketEntry.getTicketId());
		ticketEntryImpl.setSubject(ticketEntry.getSubject());
		ticketEntryImpl.setDescription(ticketEntry.getDescription());
		ticketEntryImpl.setReproductionSteps(ticketEntry.getReproductionSteps());
		ticketEntryImpl.setSeverity(ticketEntry.getSeverity());
		ticketEntryImpl.setStatus(ticketEntry.getStatus());
		ticketEntryImpl.setWeight(ticketEntry.getWeight());
		ticketEntryImpl.setEscalationLevel(ticketEntry.getEscalationLevel());
		ticketEntryImpl.setEnvName(ticketEntry.getEnvName());
		ticketEntryImpl.setEnvOS(ticketEntry.getEnvOS());
		ticketEntryImpl.setEnvOSCustom(ticketEntry.getEnvOSCustom());
		ticketEntryImpl.setEnvDB(ticketEntry.getEnvDB());
		ticketEntryImpl.setEnvJVM(ticketEntry.getEnvJVM());
		ticketEntryImpl.setEnvAS(ticketEntry.getEnvAS());
		ticketEntryImpl.setEnvLFR(ticketEntry.getEnvLFR());
		ticketEntryImpl.setEnvBrowser(ticketEntry.getEnvBrowser());
		ticketEntryImpl.setEnvBrowserCustom(ticketEntry.getEnvBrowserCustom());
		ticketEntryImpl.setEnvCS(ticketEntry.getEnvCS());
		ticketEntryImpl.setEnvSearch(ticketEntry.getEnvSearch());
		ticketEntryImpl.setComponent(ticketEntry.getComponent());
		ticketEntryImpl.setSubcomponent(ticketEntry.getSubcomponent());
		ticketEntryImpl.setSubcomponentCustom(ticketEntry.getSubcomponentCustom());
		ticketEntryImpl.setResolution(ticketEntry.getResolution());
		ticketEntryImpl.setHoldDate(ticketEntry.getHoldDate());
		ticketEntryImpl.setClosedDate(ticketEntry.getClosedDate());
		ticketEntryImpl.setDueDate(ticketEntry.getDueDate());
		ticketEntryImpl.setIgnoreDueDate(ticketEntry.isIgnoreDueDate());
		ticketEntryImpl.setCustomerModifiedDate(ticketEntry.getCustomerModifiedDate());
		ticketEntryImpl.setWorkerModifiedDate(ticketEntry.getWorkerModifiedDate());

		return ticketEntryImpl;
	}

	/**
	 * Returns the ticket entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket entry
	 * @return the ticket entry
	 * @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 */
	@Override
	public TicketEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTicketEntryException {
		TicketEntry ticketEntry = fetchByPrimaryKey(primaryKey);

		if (ticketEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTicketEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ticketEntry;
	}

	/**
	 * Returns the ticket entry with the primary key or throws a {@link NoSuchTicketEntryException} if it could not be found.
	 *
	 * @param ticketEntryId the primary key of the ticket entry
	 * @return the ticket entry
	 * @throws NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 */
	@Override
	public TicketEntry findByPrimaryKey(long ticketEntryId)
		throws NoSuchTicketEntryException {
		return findByPrimaryKey((Serializable)ticketEntryId);
	}

	/**
	 * Returns the ticket entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket entry
	 * @return the ticket entry, or <code>null</code> if a ticket entry with the primary key could not be found
	 */
	@Override
	public TicketEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
				TicketEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TicketEntry ticketEntry = (TicketEntry)serializable;

		if (ticketEntry == null) {
			Session session = null;

			try {
				session = openSession();

				ticketEntry = (TicketEntry)session.get(TicketEntryImpl.class,
						primaryKey);

				if (ticketEntry != null) {
					cacheResult(ticketEntry);
				}
				else {
					entityCache.putResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
						TicketEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
					TicketEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ticketEntry;
	}

	/**
	 * Returns the ticket entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketEntryId the primary key of the ticket entry
	 * @return the ticket entry, or <code>null</code> if a ticket entry with the primary key could not be found
	 */
	@Override
	public TicketEntry fetchByPrimaryKey(long ticketEntryId) {
		return fetchByPrimaryKey((Serializable)ticketEntryId);
	}

	@Override
	public Map<Serializable, TicketEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TicketEntry> map = new HashMap<Serializable, TicketEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TicketEntry ticketEntry = fetchByPrimaryKey(primaryKey);

			if (ticketEntry != null) {
				map.put(primaryKey, ticketEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
					TicketEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TicketEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TICKETENTRY_WHERE_PKS_IN);

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

			for (TicketEntry ticketEntry : (List<TicketEntry>)q.list()) {
				map.put(ticketEntry.getPrimaryKeyObj(), ticketEntry);

				cacheResult(ticketEntry);

				uncachedPrimaryKeys.remove(ticketEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
					TicketEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the ticket entries.
	 *
	 * @return the ticket entries
	 */
	@Override
	public List<TicketEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @return the range of ticket entries
	 */
	@Override
	public List<TicketEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket entries
	 */
	@Override
	public List<TicketEntry> findAll(int start, int end,
		OrderByComparator<TicketEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ticket entries
	 */
	@Override
	public List<TicketEntry> findAll(int start, int end,
		OrderByComparator<TicketEntry> orderByComparator,
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

		List<TicketEntry> list = null;

		if (retrieveFromCache) {
			list = (List<TicketEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TICKETENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETENTRY;

				if (pagination) {
					sql = sql.concat(TicketEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ticket entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TicketEntry ticketEntry : findAll()) {
			remove(ticketEntry);
		}
	}

	/**
	 * Returns the number of ticket entries.
	 *
	 * @return the number of ticket entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETENTRY);

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
		return TicketEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ticket entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TicketEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_TICKETENTRY = "SELECT ticketEntry FROM TicketEntry ticketEntry";
	private static final String _SQL_SELECT_TICKETENTRY_WHERE_PKS_IN = "SELECT ticketEntry FROM TicketEntry ticketEntry WHERE ticketEntryId IN (";
	private static final String _SQL_SELECT_TICKETENTRY_WHERE = "SELECT ticketEntry FROM TicketEntry ticketEntry WHERE ";
	private static final String _SQL_COUNT_TICKETENTRY = "SELECT COUNT(ticketEntry) FROM TicketEntry ticketEntry";
	private static final String _SQL_COUNT_TICKETENTRY_WHERE = "SELECT COUNT(ticketEntry) FROM TicketEntry ticketEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TicketEntryPersistenceImpl.class);
}
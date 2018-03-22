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

import com.liferay.osb.exception.NoSuchTicketFlagException;
import com.liferay.osb.model.TicketFlag;
import com.liferay.osb.model.impl.TicketFlagImpl;
import com.liferay.osb.model.impl.TicketFlagModelImpl;
import com.liferay.osb.service.persistence.TicketFlagPersistence;

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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the ticket flag service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketFlagPersistence
 * @see com.liferay.osb.service.persistence.TicketFlagUtil
 * @generated
 */
@ProviderType
public class TicketFlagPersistenceImpl extends BasePersistenceImpl<TicketFlag>
	implements TicketFlagPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketFlagUtil} to access the ticket flag persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketFlagImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TicketFlagModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			TicketFlagModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the ticket flags where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @return the matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByAEI_T(long accountEntryId, int type) {
		return findByAEI_T(accountEntryId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket flags where accountEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @return the range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByAEI_T(long accountEntryId, int type,
		int start, int end) {
		return findByAEI_T(accountEntryId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket flags where accountEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByAEI_T(long accountEntryId, int type,
		int start, int end, OrderByComparator<TicketFlag> orderByComparator) {
		return findByAEI_T(accountEntryId, type, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the ticket flags where accountEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByAEI_T(long accountEntryId, int type,
		int start, int end, OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_T;
			finderArgs = new Object[] { accountEntryId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_T;
			finderArgs = new Object[] {
					accountEntryId, type,
					
					start, end, orderByComparator
				};
		}

		List<TicketFlag> list = null;

		if (retrieveFromCache) {
			list = (List<TicketFlag>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketFlag ticketFlag : list) {
					if ((accountEntryId != ticketFlag.getAccountEntryId()) ||
							(type != ticketFlag.getType())) {
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

			query.append(_SQL_SELECT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_AEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketFlagModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(type);

				if (!pagination) {
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag
	 * @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag findByAEI_T_First(long accountEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = fetchByAEI_T_First(accountEntryId, type,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the first ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag fetchByAEI_T_First(long accountEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator) {
		List<TicketFlag> list = findByAEI_T(accountEntryId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag
	 * @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag findByAEI_T_Last(long accountEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = fetchByAEI_T_Last(accountEntryId, type,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the last ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag fetchByAEI_T_Last(long accountEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator) {
		int count = countByAEI_T(accountEntryId, type);

		if (count == 0) {
			return null;
		}

		List<TicketFlag> list = findByAEI_T(accountEntryId, type, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket flags before and after the current ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketFlagId the primary key of the current ticket flag
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket flag
	 * @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 */
	@Override
	public TicketFlag[] findByAEI_T_PrevAndNext(long ticketFlagId,
		long accountEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = findByPrimaryKey(ticketFlagId);

		Session session = null;

		try {
			session = openSession();

			TicketFlag[] array = new TicketFlagImpl[3];

			array[0] = getByAEI_T_PrevAndNext(session, ticketFlag,
					accountEntryId, type, orderByComparator, true);

			array[1] = ticketFlag;

			array[2] = getByAEI_T_PrevAndNext(session, ticketFlag,
					accountEntryId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketFlag getByAEI_T_PrevAndNext(Session session,
		TicketFlag ticketFlag, long accountEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TICKETFLAG_WHERE);

		query.append(_FINDER_COLUMN_AEI_T_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_T_TYPE_2);

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
			query.append(TicketFlagModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFlag);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFlag> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket flags where accountEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 */
	@Override
	public void removeByAEI_T(long accountEntryId, int type) {
		for (TicketFlag ticketFlag : findByAEI_T(accountEntryId, type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketFlag);
		}
	}

	/**
	 * Returns the number of ticket flags where accountEntryId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param type the type
	 * @return the number of matching ticket flags
	 */
	@Override
	public int countByAEI_T(long accountEntryId, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_T;

		Object[] finderArgs = new Object[] { accountEntryId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_AEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

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

	private static final String _FINDER_COLUMN_AEI_T_ACCOUNTENTRYID_2 = "ticketFlag.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_T_TYPE_2 = "ticketFlag.type = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTEI_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TicketFlagModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketFlagModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T(long ticketEntryId, int type) {
		return findByTEI_T(ticketEntryId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @return the range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T(long ticketEntryId, int type,
		int start, int end) {
		return findByTEI_T(ticketEntryId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T(long ticketEntryId, int type,
		int start, int end, OrderByComparator<TicketFlag> orderByComparator) {
		return findByTEI_T(ticketEntryId, type, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T(long ticketEntryId, int type,
		int start, int end, OrderByComparator<TicketFlag> orderByComparator,
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

		List<TicketFlag> list = null;

		if (retrieveFromCache) {
			list = (List<TicketFlag>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketFlag ticketFlag : list) {
					if ((ticketEntryId != ticketFlag.getTicketEntryId()) ||
							(type != ticketFlag.getType())) {
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

			query.append(_SQL_SELECT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketFlagModelImpl.ORDER_BY_JPQL);
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
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag
	 * @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag findByTEI_T_First(long ticketEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = fetchByTEI_T_First(ticketEntryId, type,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag fetchByTEI_T_First(long ticketEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator) {
		List<TicketFlag> list = findByTEI_T(ticketEntryId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag
	 * @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag findByTEI_T_Last(long ticketEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = fetchByTEI_T_Last(ticketEntryId, type,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag fetchByTEI_T_Last(long ticketEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator) {
		int count = countByTEI_T(ticketEntryId, type);

		if (count == 0) {
			return null;
		}

		List<TicketFlag> list = findByTEI_T(ticketEntryId, type, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket flags before and after the current ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketFlagId the primary key of the current ticket flag
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket flag
	 * @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 */
	@Override
	public TicketFlag[] findByTEI_T_PrevAndNext(long ticketFlagId,
		long ticketEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = findByPrimaryKey(ticketFlagId);

		Session session = null;

		try {
			session = openSession();

			TicketFlag[] array = new TicketFlagImpl[3];

			array[0] = getByTEI_T_PrevAndNext(session, ticketFlag,
					ticketEntryId, type, orderByComparator, true);

			array[1] = ticketFlag;

			array[2] = getByTEI_T_PrevAndNext(session, ticketFlag,
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

	protected TicketFlag getByTEI_T_PrevAndNext(Session session,
		TicketFlag ticketFlag, long ticketEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TICKETFLAG_WHERE);

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
			query.append(TicketFlagModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFlag);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFlag> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ticket flags where ticketEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 */
	@Override
	public void removeByTEI_T(long ticketEntryId, int type) {
		for (TicketFlag ticketFlag : findByTEI_T(ticketEntryId, type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketFlag);
		}
	}

	/**
	 * Returns the number of ticket flags where ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the number of matching ticket flags
	 */
	@Override
	public int countByTEI_T(long ticketEntryId, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEI_T;

		Object[] finderArgs = new Object[] { ticketEntryId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETFLAG_WHERE);

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

	private static final String _FINDER_COLUMN_TEI_T_TICKETENTRYID_2 = "ticketFlag.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_TYPE_2 = "ticketFlag.type = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_F = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTEI_T_F",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_F =
		new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_T_F",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			TicketFlagModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketFlagModelImpl.TYPE_COLUMN_BITMASK |
			TicketFlagModelImpl.FLAG_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_T_F = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_T_F",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_F = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTEI_T_F",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @return the matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int type, int flag) {
		return findByTEI_T_F(ticketEntryId, type, flag, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @return the range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int type,
		int flag, int start, int end) {
		return findByTEI_T_F(ticketEntryId, type, flag, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int type,
		int flag, int start, int end,
		OrderByComparator<TicketFlag> orderByComparator) {
		return findByTEI_T_F(ticketEntryId, type, flag, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int type,
		int flag, int start, int end,
		OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_F;
			finderArgs = new Object[] { ticketEntryId, type, flag };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_F;
			finderArgs = new Object[] {
					ticketEntryId, type, flag,
					
					start, end, orderByComparator
				};
		}

		List<TicketFlag> list = null;

		if (retrieveFromCache) {
			list = (List<TicketFlag>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketFlag ticketFlag : list) {
					if ((ticketEntryId != ticketFlag.getTicketEntryId()) ||
							(type != ticketFlag.getType()) ||
							(flag != ticketFlag.getFlag())) {
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

			query.append(_SQL_SELECT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_F_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_F_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_F_FLAG_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketFlagModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

				qPos.add(flag);

				if (!pagination) {
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag
	 * @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag findByTEI_T_F_First(long ticketEntryId, int type,
		int flag, OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = fetchByTEI_T_F_First(ticketEntryId, type, flag,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", flag=");
		msg.append(flag);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag fetchByTEI_T_F_First(long ticketEntryId, int type,
		int flag, OrderByComparator<TicketFlag> orderByComparator) {
		List<TicketFlag> list = findByTEI_T_F(ticketEntryId, type, flag, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag
	 * @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag findByTEI_T_F_Last(long ticketEntryId, int type,
		int flag, OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = fetchByTEI_T_F_Last(ticketEntryId, type, flag,
				orderByComparator);

		if (ticketFlag != null) {
			return ticketFlag;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", flag=");
		msg.append(flag);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketFlagException(msg.toString());
	}

	/**
	 * Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag fetchByTEI_T_F_Last(long ticketEntryId, int type,
		int flag, OrderByComparator<TicketFlag> orderByComparator) {
		int count = countByTEI_T_F(ticketEntryId, type, flag);

		if (count == 0) {
			return null;
		}

		List<TicketFlag> list = findByTEI_T_F(ticketEntryId, type, flag,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket flags before and after the current ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketFlagId the primary key of the current ticket flag
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket flag
	 * @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 */
	@Override
	public TicketFlag[] findByTEI_T_F_PrevAndNext(long ticketFlagId,
		long ticketEntryId, int type, int flag,
		OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = findByPrimaryKey(ticketFlagId);

		Session session = null;

		try {
			session = openSession();

			TicketFlag[] array = new TicketFlagImpl[3];

			array[0] = getByTEI_T_F_PrevAndNext(session, ticketFlag,
					ticketEntryId, type, flag, orderByComparator, true);

			array[1] = ticketFlag;

			array[2] = getByTEI_T_F_PrevAndNext(session, ticketFlag,
					ticketEntryId, type, flag, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketFlag getByTEI_T_F_PrevAndNext(Session session,
		TicketFlag ticketFlag, long ticketEntryId, int type, int flag,
		OrderByComparator<TicketFlag> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_TICKETFLAG_WHERE);

		query.append(_FINDER_COLUMN_TEI_T_F_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_T_F_TYPE_2);

		query.append(_FINDER_COLUMN_TEI_T_F_FLAG_2);

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
			query.append(TicketFlagModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(type);

		qPos.add(flag);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketFlag);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketFlag> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param flag the flag
	 * @return the matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int[] types,
		int flag) {
		return findByTEI_T_F(ticketEntryId, types, flag, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param flag the flag
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @return the range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int[] types,
		int flag, int start, int end) {
		return findByTEI_T_F(ticketEntryId, types, flag, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param flag the flag
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int[] types,
		int flag, int start, int end,
		OrderByComparator<TicketFlag> orderByComparator) {
		return findByTEI_T_F(ticketEntryId, types, flag, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ticket flags
	 */
	@Override
	public List<TicketFlag> findByTEI_T_F(long ticketEntryId, int[] types,
		int flag, int start, int end,
		OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache) {
		if (types == null) {
			types = new int[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.unique(types);

			Arrays.sort(types);
		}

		if (types.length == 1) {
			return findByTEI_T_F(ticketEntryId, types[0], flag, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(types), flag
				};
		}
		else {
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(types), flag,
					
					start, end, orderByComparator
				};
		}

		List<TicketFlag> list = null;

		if (retrieveFromCache) {
			list = (List<TicketFlag>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_F,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TicketFlag ticketFlag : list) {
					if ((ticketEntryId != ticketFlag.getTicketEntryId()) ||
							!ArrayUtil.contains(types, ticketFlag.getType()) ||
							(flag != ticketFlag.getFlag())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_F_TICKETENTRYID_2);

			if (types.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_TEI_T_F_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_F_FLAG_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TicketFlagModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(flag);

				if (!pagination) {
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_F,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_F,
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
	 * Removes all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 */
	@Override
	public void removeByTEI_T_F(long ticketEntryId, int type, int flag) {
		for (TicketFlag ticketFlag : findByTEI_T_F(ticketEntryId, type, flag,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ticketFlag);
		}
	}

	/**
	 * Returns the number of ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param flag the flag
	 * @return the number of matching ticket flags
	 */
	@Override
	public int countByTEI_T_F(long ticketEntryId, int type, int flag) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEI_T_F;

		Object[] finderArgs = new Object[] { ticketEntryId, type, flag };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_F_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_F_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_F_FLAG_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

				qPos.add(flag);

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
	 * Returns the number of ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param flag the flag
	 * @return the number of matching ticket flags
	 */
	@Override
	public int countByTEI_T_F(long ticketEntryId, int[] types, int flag) {
		if (types == null) {
			types = new int[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.unique(types);

			Arrays.sort(types);
		}

		Object[] finderArgs = new Object[] {
				ticketEntryId, StringUtil.merge(types), flag
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_F,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_F_TICKETENTRYID_2);

			if (types.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_TEI_T_F_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_F_FLAG_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(flag);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_F,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_F,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TEI_T_F_TICKETENTRYID_2 = "ticketFlag.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_F_TYPE_2 = "ticketFlag.type = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_F_TYPE_7 = "ticketFlag.type IN (";
	private static final String _FINDER_COLUMN_TEI_T_F_FLAG_2 = "ticketFlag.flag = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_AEI_TEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, TicketFlagImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_AEI_TEI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			TicketFlagModelImpl.USERID_COLUMN_BITMASK |
			TicketFlagModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			TicketFlagModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketFlagModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI_TEI_T = new FinderPath(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI_TEI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or throws a {@link NoSuchTicketFlagException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the matching ticket flag
	 * @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag findByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type) throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = fetchByU_AEI_TEI_T(userId, accountEntryId,
				ticketEntryId, type);

		if (ticketFlag == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", accountEntryId=");
			msg.append(accountEntryId);

			msg.append(", ticketEntryId=");
			msg.append(ticketEntryId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTicketFlagException(msg.toString());
		}

		return ticketFlag;
	}

	/**
	 * Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag fetchByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type) {
		return fetchByU_AEI_TEI_T(userId, accountEntryId, ticketEntryId, type,
			true);
	}

	/**
	 * Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	 */
	@Override
	public TicketFlag fetchByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				userId, accountEntryId, ticketEntryId, type
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T,
					finderArgs, this);
		}

		if (result instanceof TicketFlag) {
			TicketFlag ticketFlag = (TicketFlag)result;

			if ((userId != ticketFlag.getUserId()) ||
					(accountEntryId != ticketFlag.getAccountEntryId()) ||
					(ticketEntryId != ticketFlag.getTicketEntryId()) ||
					(type != ticketFlag.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				qPos.add(ticketEntryId);

				qPos.add(type);

				List<TicketFlag> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T,
						finderArgs, list);
				}
				else {
					TicketFlag ticketFlag = list.get(0);

					result = ticketFlag;

					cacheResult(ticketFlag);

					if ((ticketFlag.getUserId() != userId) ||
							(ticketFlag.getAccountEntryId() != accountEntryId) ||
							(ticketFlag.getTicketEntryId() != ticketEntryId) ||
							(ticketFlag.getType() != type)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T,
							finderArgs, ticketFlag);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T,
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
			return (TicketFlag)result;
		}
	}

	/**
	 * Removes the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the ticket flag that was removed
	 */
	@Override
	public TicketFlag removeByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type) throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = findByU_AEI_TEI_T(userId, accountEntryId,
				ticketEntryId, type);

		return remove(ticketFlag);
	}

	/**
	 * Returns the number of ticket flags where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @return the number of matching ticket flags
	 */
	@Override
	public int countByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_AEI_TEI_T;

		Object[] finderArgs = new Object[] {
				userId, accountEntryId, ticketEntryId, type
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TICKETFLAG_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_TEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

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

	private static final String _FINDER_COLUMN_U_AEI_TEI_T_USERID_2 = "ticketFlag.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_TEI_T_ACCOUNTENTRYID_2 = "ticketFlag.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_TEI_T_TICKETENTRYID_2 = "ticketFlag.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_TEI_T_TYPE_2 = "ticketFlag.type = ?";

	public TicketFlagPersistenceImpl() {
		setModelClass(TicketFlag.class);
	}

	/**
	 * Caches the ticket flag in the entity cache if it is enabled.
	 *
	 * @param ticketFlag the ticket flag
	 */
	@Override
	public void cacheResult(TicketFlag ticketFlag) {
		entityCache.putResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagImpl.class, ticketFlag.getPrimaryKey(), ticketFlag);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T,
			new Object[] {
				ticketFlag.getUserId(), ticketFlag.getAccountEntryId(),
				ticketFlag.getTicketEntryId(), ticketFlag.getType()
			}, ticketFlag);

		ticketFlag.resetOriginalValues();
	}

	/**
	 * Caches the ticket flags in the entity cache if it is enabled.
	 *
	 * @param ticketFlags the ticket flags
	 */
	@Override
	public void cacheResult(List<TicketFlag> ticketFlags) {
		for (TicketFlag ticketFlag : ticketFlags) {
			if (entityCache.getResult(
						TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
						TicketFlagImpl.class, ticketFlag.getPrimaryKey()) == null) {
				cacheResult(ticketFlag);
			}
			else {
				ticketFlag.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket flags.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TicketFlagImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket flag.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketFlag ticketFlag) {
		entityCache.removeResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagImpl.class, ticketFlag.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TicketFlagModelImpl)ticketFlag, true);
	}

	@Override
	public void clearCache(List<TicketFlag> ticketFlags) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketFlag ticketFlag : ticketFlags) {
			entityCache.removeResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
				TicketFlagImpl.class, ticketFlag.getPrimaryKey());

			clearUniqueFindersCache((TicketFlagModelImpl)ticketFlag, true);
		}
	}

	protected void cacheUniqueFindersCache(
		TicketFlagModelImpl ticketFlagModelImpl) {
		Object[] args = new Object[] {
				ticketFlagModelImpl.getUserId(),
				ticketFlagModelImpl.getAccountEntryId(),
				ticketFlagModelImpl.getTicketEntryId(),
				ticketFlagModelImpl.getType()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_U_AEI_TEI_T, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T, args,
			ticketFlagModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		TicketFlagModelImpl ticketFlagModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					ticketFlagModelImpl.getUserId(),
					ticketFlagModelImpl.getAccountEntryId(),
					ticketFlagModelImpl.getTicketEntryId(),
					ticketFlagModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI_TEI_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T, args);
		}

		if ((ticketFlagModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_AEI_TEI_T.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					ticketFlagModelImpl.getOriginalUserId(),
					ticketFlagModelImpl.getOriginalAccountEntryId(),
					ticketFlagModelImpl.getOriginalTicketEntryId(),
					ticketFlagModelImpl.getOriginalType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI_TEI_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_AEI_TEI_T, args);
		}
	}

	/**
	 * Creates a new ticket flag with the primary key. Does not add the ticket flag to the database.
	 *
	 * @param ticketFlagId the primary key for the new ticket flag
	 * @return the new ticket flag
	 */
	@Override
	public TicketFlag create(long ticketFlagId) {
		TicketFlag ticketFlag = new TicketFlagImpl();

		ticketFlag.setNew(true);
		ticketFlag.setPrimaryKey(ticketFlagId);

		return ticketFlag;
	}

	/**
	 * Removes the ticket flag with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketFlagId the primary key of the ticket flag
	 * @return the ticket flag that was removed
	 * @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 */
	@Override
	public TicketFlag remove(long ticketFlagId)
		throws NoSuchTicketFlagException {
		return remove((Serializable)ticketFlagId);
	}

	/**
	 * Removes the ticket flag with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket flag
	 * @return the ticket flag that was removed
	 * @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 */
	@Override
	public TicketFlag remove(Serializable primaryKey)
		throws NoSuchTicketFlagException {
		Session session = null;

		try {
			session = openSession();

			TicketFlag ticketFlag = (TicketFlag)session.get(TicketFlagImpl.class,
					primaryKey);

			if (ticketFlag == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketFlagException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketFlag);
		}
		catch (NoSuchTicketFlagException nsee) {
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
	protected TicketFlag removeImpl(TicketFlag ticketFlag) {
		ticketFlag = toUnwrappedModel(ticketFlag);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ticketFlag)) {
				ticketFlag = (TicketFlag)session.get(TicketFlagImpl.class,
						ticketFlag.getPrimaryKeyObj());
			}

			if (ticketFlag != null) {
				session.delete(ticketFlag);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ticketFlag != null) {
			clearCache(ticketFlag);
		}

		return ticketFlag;
	}

	@Override
	public TicketFlag updateImpl(TicketFlag ticketFlag) {
		ticketFlag = toUnwrappedModel(ticketFlag);

		boolean isNew = ticketFlag.isNew();

		TicketFlagModelImpl ticketFlagModelImpl = (TicketFlagModelImpl)ticketFlag;

		Session session = null;

		try {
			session = openSession();

			if (ticketFlag.isNew()) {
				session.save(ticketFlag);

				ticketFlag.setNew(false);
			}
			else {
				ticketFlag = (TicketFlag)session.merge(ticketFlag);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TicketFlagModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					ticketFlagModelImpl.getAccountEntryId(),
					ticketFlagModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_T,
				args);

			args = new Object[] {
					ticketFlagModelImpl.getTicketEntryId(),
					ticketFlagModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T,
				args);

			args = new Object[] {
					ticketFlagModelImpl.getTicketEntryId(),
					ticketFlagModelImpl.getType(), ticketFlagModelImpl.getFlag()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T_F, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_F,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ticketFlagModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketFlagModelImpl.getOriginalAccountEntryId(),
						ticketFlagModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_T,
					args);

				args = new Object[] {
						ticketFlagModelImpl.getAccountEntryId(),
						ticketFlagModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_T,
					args);
			}

			if ((ticketFlagModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketFlagModelImpl.getOriginalTicketEntryId(),
						ticketFlagModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T,
					args);

				args = new Object[] {
						ticketFlagModelImpl.getTicketEntryId(),
						ticketFlagModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T,
					args);
			}

			if ((ticketFlagModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ticketFlagModelImpl.getOriginalTicketEntryId(),
						ticketFlagModelImpl.getOriginalType(),
						ticketFlagModelImpl.getOriginalFlag()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T_F, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_F,
					args);

				args = new Object[] {
						ticketFlagModelImpl.getTicketEntryId(),
						ticketFlagModelImpl.getType(),
						ticketFlagModelImpl.getFlag()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEI_T_F, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_F,
					args);
			}
		}

		entityCache.putResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
			TicketFlagImpl.class, ticketFlag.getPrimaryKey(), ticketFlag, false);

		clearUniqueFindersCache(ticketFlagModelImpl, false);
		cacheUniqueFindersCache(ticketFlagModelImpl);

		ticketFlag.resetOriginalValues();

		return ticketFlag;
	}

	protected TicketFlag toUnwrappedModel(TicketFlag ticketFlag) {
		if (ticketFlag instanceof TicketFlagImpl) {
			return ticketFlag;
		}

		TicketFlagImpl ticketFlagImpl = new TicketFlagImpl();

		ticketFlagImpl.setNew(ticketFlag.isNew());
		ticketFlagImpl.setPrimaryKey(ticketFlag.getPrimaryKey());

		ticketFlagImpl.setTicketFlagId(ticketFlag.getTicketFlagId());
		ticketFlagImpl.setUserId(ticketFlag.getUserId());
		ticketFlagImpl.setModifiedDate(ticketFlag.getModifiedDate());
		ticketFlagImpl.setAccountEntryId(ticketFlag.getAccountEntryId());
		ticketFlagImpl.setTicketEntryId(ticketFlag.getTicketEntryId());
		ticketFlagImpl.setType(ticketFlag.getType());
		ticketFlagImpl.setFlag(ticketFlag.getFlag());

		return ticketFlagImpl;
	}

	/**
	 * Returns the ticket flag with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket flag
	 * @return the ticket flag
	 * @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 */
	@Override
	public TicketFlag findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTicketFlagException {
		TicketFlag ticketFlag = fetchByPrimaryKey(primaryKey);

		if (ticketFlag == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTicketFlagException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ticketFlag;
	}

	/**
	 * Returns the ticket flag with the primary key or throws a {@link NoSuchTicketFlagException} if it could not be found.
	 *
	 * @param ticketFlagId the primary key of the ticket flag
	 * @return the ticket flag
	 * @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	 */
	@Override
	public TicketFlag findByPrimaryKey(long ticketFlagId)
		throws NoSuchTicketFlagException {
		return findByPrimaryKey((Serializable)ticketFlagId);
	}

	/**
	 * Returns the ticket flag with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket flag
	 * @return the ticket flag, or <code>null</code> if a ticket flag with the primary key could not be found
	 */
	@Override
	public TicketFlag fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
				TicketFlagImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TicketFlag ticketFlag = (TicketFlag)serializable;

		if (ticketFlag == null) {
			Session session = null;

			try {
				session = openSession();

				ticketFlag = (TicketFlag)session.get(TicketFlagImpl.class,
						primaryKey);

				if (ticketFlag != null) {
					cacheResult(ticketFlag);
				}
				else {
					entityCache.putResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
						TicketFlagImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
					TicketFlagImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ticketFlag;
	}

	/**
	 * Returns the ticket flag with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketFlagId the primary key of the ticket flag
	 * @return the ticket flag, or <code>null</code> if a ticket flag with the primary key could not be found
	 */
	@Override
	public TicketFlag fetchByPrimaryKey(long ticketFlagId) {
		return fetchByPrimaryKey((Serializable)ticketFlagId);
	}

	@Override
	public Map<Serializable, TicketFlag> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TicketFlag> map = new HashMap<Serializable, TicketFlag>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TicketFlag ticketFlag = fetchByPrimaryKey(primaryKey);

			if (ticketFlag != null) {
				map.put(primaryKey, ticketFlag);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
					TicketFlagImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TicketFlag)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TICKETFLAG_WHERE_PKS_IN);

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

			for (TicketFlag ticketFlag : (List<TicketFlag>)q.list()) {
				map.put(ticketFlag.getPrimaryKeyObj(), ticketFlag);

				cacheResult(ticketFlag);

				uncachedPrimaryKeys.remove(ticketFlag.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TicketFlagModelImpl.ENTITY_CACHE_ENABLED,
					TicketFlagImpl.class, primaryKey, nullModel);
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
	 * Returns all the ticket flags.
	 *
	 * @return the ticket flags
	 */
	@Override
	public List<TicketFlag> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket flags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @return the range of ticket flags
	 */
	@Override
	public List<TicketFlag> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket flags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket flags
	 */
	@Override
	public List<TicketFlag> findAll(int start, int end,
		OrderByComparator<TicketFlag> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket flags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket flags
	 * @param end the upper bound of the range of ticket flags (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ticket flags
	 */
	@Override
	public List<TicketFlag> findAll(int start, int end,
		OrderByComparator<TicketFlag> orderByComparator,
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

		List<TicketFlag> list = null;

		if (retrieveFromCache) {
			list = (List<TicketFlag>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TICKETFLAG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETFLAG;

				if (pagination) {
					sql = sql.concat(TicketFlagModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketFlag>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ticket flags from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TicketFlag ticketFlag : findAll()) {
			remove(ticketFlag);
		}
	}

	/**
	 * Returns the number of ticket flags.
	 *
	 * @return the number of ticket flags
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETFLAG);

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
		return TicketFlagModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ticket flag persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TicketFlagImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_TICKETFLAG = "SELECT ticketFlag FROM TicketFlag ticketFlag";
	private static final String _SQL_SELECT_TICKETFLAG_WHERE_PKS_IN = "SELECT ticketFlag FROM TicketFlag ticketFlag WHERE ticketFlagId IN (";
	private static final String _SQL_SELECT_TICKETFLAG_WHERE = "SELECT ticketFlag FROM TicketFlag ticketFlag WHERE ";
	private static final String _SQL_COUNT_TICKETFLAG = "SELECT COUNT(ticketFlag) FROM TicketFlag ticketFlag";
	private static final String _SQL_COUNT_TICKETFLAG_WHERE = "SELECT COUNT(ticketFlag) FROM TicketFlag ticketFlag WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketFlag.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketFlag exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketFlag exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TicketFlagPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
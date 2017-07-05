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

import com.liferay.osb.exception.NoSuchSupportWorkerSeverityException;
import com.liferay.osb.model.SupportWorkerSeverity;
import com.liferay.osb.model.impl.SupportWorkerSeverityImpl;
import com.liferay.osb.model.impl.SupportWorkerSeverityModelImpl;
import com.liferay.osb.service.persistence.SupportWorkerSeverityPersistence;

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
 * The persistence implementation for the support worker severity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerSeverityPersistence
 * @see com.liferay.osb.service.persistence.SupportWorkerSeverityUtil
 * @generated
 */
@ProviderType
public class SupportWorkerSeverityPersistenceImpl extends BasePersistenceImpl<SupportWorkerSeverity>
	implements SupportWorkerSeverityPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SupportWorkerSeverityUtil} to access the support worker severity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SupportWorkerSeverityImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerSeverityModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerSeverityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerSeverityModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerSeverityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerSeverityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTWORKERID =
		new FinderPath(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerSeverityModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerSeverityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySupportWorkerId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID =
		new FinderPath(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerSeverityModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerSeverityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySupportWorkerId",
			new String[] { Long.class.getName() },
			SupportWorkerSeverityModelImpl.SUPPORTWORKERID_COLUMN_BITMASK |
			SupportWorkerSeverityModelImpl.SEVERITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTWORKERID = new FinderPath(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerSeverityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySupportWorkerId", new String[] { Long.class.getName() });

	/**
	 * Returns all the support worker severities where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @return the matching support worker severities
	 */
	@Override
	public List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId) {
		return findBySupportWorkerId(supportWorkerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support worker severities where supportWorkerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportWorkerId the support worker ID
	 * @param start the lower bound of the range of support worker severities
	 * @param end the upper bound of the range of support worker severities (not inclusive)
	 * @return the range of matching support worker severities
	 */
	@Override
	public List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId, int start, int end) {
		return findBySupportWorkerId(supportWorkerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support worker severities where supportWorkerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportWorkerId the support worker ID
	 * @param start the lower bound of the range of support worker severities
	 * @param end the upper bound of the range of support worker severities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support worker severities
	 */
	@Override
	public List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		OrderByComparator<SupportWorkerSeverity> orderByComparator) {
		return findBySupportWorkerId(supportWorkerId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support worker severities where supportWorkerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportWorkerId the support worker ID
	 * @param start the lower bound of the range of support worker severities
	 * @param end the upper bound of the range of support worker severities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching support worker severities
	 */
	@Override
	public List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		OrderByComparator<SupportWorkerSeverity> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID;
			finderArgs = new Object[] { supportWorkerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTWORKERID;
			finderArgs = new Object[] {
					supportWorkerId,
					
					start, end, orderByComparator
				};
		}

		List<SupportWorkerSeverity> list = null;

		if (retrieveFromCache) {
			list = (List<SupportWorkerSeverity>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SupportWorkerSeverity supportWorkerSeverity : list) {
					if ((supportWorkerId != supportWorkerSeverity.getSupportWorkerId())) {
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

			query.append(_SQL_SELECT_SUPPORTWORKERSEVERITY_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTWORKERID_SUPPORTWORKERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SupportWorkerSeverityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportWorkerId);

				if (!pagination) {
					list = (List<SupportWorkerSeverity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportWorkerSeverity>)QueryUtil.list(q,
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
	 * Returns the first support worker severity in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker severity
	 * @throws NoSuchSupportWorkerSeverityException if a matching support worker severity could not be found
	 */
	@Override
	public SupportWorkerSeverity findBySupportWorkerId_First(
		long supportWorkerId,
		OrderByComparator<SupportWorkerSeverity> orderByComparator)
		throws NoSuchSupportWorkerSeverityException {
		SupportWorkerSeverity supportWorkerSeverity = fetchBySupportWorkerId_First(supportWorkerId,
				orderByComparator);

		if (supportWorkerSeverity != null) {
			return supportWorkerSeverity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportWorkerId=");
		msg.append(supportWorkerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerSeverityException(msg.toString());
	}

	/**
	 * Returns the first support worker severity in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker severity, or <code>null</code> if a matching support worker severity could not be found
	 */
	@Override
	public SupportWorkerSeverity fetchBySupportWorkerId_First(
		long supportWorkerId,
		OrderByComparator<SupportWorkerSeverity> orderByComparator) {
		List<SupportWorkerSeverity> list = findBySupportWorkerId(supportWorkerId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support worker severity in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker severity
	 * @throws NoSuchSupportWorkerSeverityException if a matching support worker severity could not be found
	 */
	@Override
	public SupportWorkerSeverity findBySupportWorkerId_Last(
		long supportWorkerId,
		OrderByComparator<SupportWorkerSeverity> orderByComparator)
		throws NoSuchSupportWorkerSeverityException {
		SupportWorkerSeverity supportWorkerSeverity = fetchBySupportWorkerId_Last(supportWorkerId,
				orderByComparator);

		if (supportWorkerSeverity != null) {
			return supportWorkerSeverity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportWorkerId=");
		msg.append(supportWorkerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerSeverityException(msg.toString());
	}

	/**
	 * Returns the last support worker severity in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker severity, or <code>null</code> if a matching support worker severity could not be found
	 */
	@Override
	public SupportWorkerSeverity fetchBySupportWorkerId_Last(
		long supportWorkerId,
		OrderByComparator<SupportWorkerSeverity> orderByComparator) {
		int count = countBySupportWorkerId(supportWorkerId);

		if (count == 0) {
			return null;
		}

		List<SupportWorkerSeverity> list = findBySupportWorkerId(supportWorkerId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support worker severities before and after the current support worker severity in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerSeverityId the primary key of the current support worker severity
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support worker severity
	 * @throws NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	 */
	@Override
	public SupportWorkerSeverity[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerSeverityId, long supportWorkerId,
		OrderByComparator<SupportWorkerSeverity> orderByComparator)
		throws NoSuchSupportWorkerSeverityException {
		SupportWorkerSeverity supportWorkerSeverity = findByPrimaryKey(supportWorkerSeverityId);

		Session session = null;

		try {
			session = openSession();

			SupportWorkerSeverity[] array = new SupportWorkerSeverityImpl[3];

			array[0] = getBySupportWorkerId_PrevAndNext(session,
					supportWorkerSeverity, supportWorkerId, orderByComparator,
					true);

			array[1] = supportWorkerSeverity;

			array[2] = getBySupportWorkerId_PrevAndNext(session,
					supportWorkerSeverity, supportWorkerId, orderByComparator,
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

	protected SupportWorkerSeverity getBySupportWorkerId_PrevAndNext(
		Session session, SupportWorkerSeverity supportWorkerSeverity,
		long supportWorkerId,
		OrderByComparator<SupportWorkerSeverity> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTWORKERSEVERITY_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTWORKERID_SUPPORTWORKERID_2);

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
			query.append(SupportWorkerSeverityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportWorkerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportWorkerSeverity);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportWorkerSeverity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support worker severities where supportWorkerId = &#63; from the database.
	 *
	 * @param supportWorkerId the support worker ID
	 */
	@Override
	public void removeBySupportWorkerId(long supportWorkerId) {
		for (SupportWorkerSeverity supportWorkerSeverity : findBySupportWorkerId(
				supportWorkerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(supportWorkerSeverity);
		}
	}

	/**
	 * Returns the number of support worker severities where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @return the number of matching support worker severities
	 */
	@Override
	public int countBySupportWorkerId(long supportWorkerId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUPPORTWORKERID;

		Object[] finderArgs = new Object[] { supportWorkerId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTWORKERSEVERITY_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTWORKERID_SUPPORTWORKERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportWorkerId);

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

	private static final String _FINDER_COLUMN_SUPPORTWORKERID_SUPPORTWORKERID_2 =
		"supportWorkerSeverity.supportWorkerId = ?";

	public SupportWorkerSeverityPersistenceImpl() {
		setModelClass(SupportWorkerSeverity.class);
	}

	/**
	 * Caches the support worker severity in the entity cache if it is enabled.
	 *
	 * @param supportWorkerSeverity the support worker severity
	 */
	@Override
	public void cacheResult(SupportWorkerSeverity supportWorkerSeverity) {
		entityCache.putResult(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerSeverityImpl.class,
			supportWorkerSeverity.getPrimaryKey(), supportWorkerSeverity);

		supportWorkerSeverity.resetOriginalValues();
	}

	/**
	 * Caches the support worker severities in the entity cache if it is enabled.
	 *
	 * @param supportWorkerSeverities the support worker severities
	 */
	@Override
	public void cacheResult(List<SupportWorkerSeverity> supportWorkerSeverities) {
		for (SupportWorkerSeverity supportWorkerSeverity : supportWorkerSeverities) {
			if (entityCache.getResult(
						SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
						SupportWorkerSeverityImpl.class,
						supportWorkerSeverity.getPrimaryKey()) == null) {
				cacheResult(supportWorkerSeverity);
			}
			else {
				supportWorkerSeverity.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support worker severities.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SupportWorkerSeverityImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support worker severity.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportWorkerSeverity supportWorkerSeverity) {
		entityCache.removeResult(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerSeverityImpl.class,
			supportWorkerSeverity.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SupportWorkerSeverity> supportWorkerSeverities) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportWorkerSeverity supportWorkerSeverity : supportWorkerSeverities) {
			entityCache.removeResult(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
				SupportWorkerSeverityImpl.class,
				supportWorkerSeverity.getPrimaryKey());
		}
	}

	/**
	 * Creates a new support worker severity with the primary key. Does not add the support worker severity to the database.
	 *
	 * @param supportWorkerSeverityId the primary key for the new support worker severity
	 * @return the new support worker severity
	 */
	@Override
	public SupportWorkerSeverity create(long supportWorkerSeverityId) {
		SupportWorkerSeverity supportWorkerSeverity = new SupportWorkerSeverityImpl();

		supportWorkerSeverity.setNew(true);
		supportWorkerSeverity.setPrimaryKey(supportWorkerSeverityId);

		return supportWorkerSeverity;
	}

	/**
	 * Removes the support worker severity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportWorkerSeverityId the primary key of the support worker severity
	 * @return the support worker severity that was removed
	 * @throws NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	 */
	@Override
	public SupportWorkerSeverity remove(long supportWorkerSeverityId)
		throws NoSuchSupportWorkerSeverityException {
		return remove((Serializable)supportWorkerSeverityId);
	}

	/**
	 * Removes the support worker severity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support worker severity
	 * @return the support worker severity that was removed
	 * @throws NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	 */
	@Override
	public SupportWorkerSeverity remove(Serializable primaryKey)
		throws NoSuchSupportWorkerSeverityException {
		Session session = null;

		try {
			session = openSession();

			SupportWorkerSeverity supportWorkerSeverity = (SupportWorkerSeverity)session.get(SupportWorkerSeverityImpl.class,
					primaryKey);

			if (supportWorkerSeverity == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportWorkerSeverityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(supportWorkerSeverity);
		}
		catch (NoSuchSupportWorkerSeverityException nsee) {
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
	protected SupportWorkerSeverity removeImpl(
		SupportWorkerSeverity supportWorkerSeverity) {
		supportWorkerSeverity = toUnwrappedModel(supportWorkerSeverity);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(supportWorkerSeverity)) {
				supportWorkerSeverity = (SupportWorkerSeverity)session.get(SupportWorkerSeverityImpl.class,
						supportWorkerSeverity.getPrimaryKeyObj());
			}

			if (supportWorkerSeverity != null) {
				session.delete(supportWorkerSeverity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (supportWorkerSeverity != null) {
			clearCache(supportWorkerSeverity);
		}

		return supportWorkerSeverity;
	}

	@Override
	public SupportWorkerSeverity updateImpl(
		SupportWorkerSeverity supportWorkerSeverity) {
		supportWorkerSeverity = toUnwrappedModel(supportWorkerSeverity);

		boolean isNew = supportWorkerSeverity.isNew();

		SupportWorkerSeverityModelImpl supportWorkerSeverityModelImpl = (SupportWorkerSeverityModelImpl)supportWorkerSeverity;

		Session session = null;

		try {
			session = openSession();

			if (supportWorkerSeverity.isNew()) {
				session.save(supportWorkerSeverity);

				supportWorkerSeverity.setNew(false);
			}
			else {
				supportWorkerSeverity = (SupportWorkerSeverity)session.merge(supportWorkerSeverity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SupportWorkerSeverityModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					supportWorkerSeverityModelImpl.getSupportWorkerId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((supportWorkerSeverityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						supportWorkerSeverityModelImpl.getOriginalSupportWorkerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID,
					args);

				args = new Object[] {
						supportWorkerSeverityModelImpl.getSupportWorkerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID,
					args);
			}
		}

		entityCache.putResult(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerSeverityImpl.class,
			supportWorkerSeverity.getPrimaryKey(), supportWorkerSeverity, false);

		supportWorkerSeverity.resetOriginalValues();

		return supportWorkerSeverity;
	}

	protected SupportWorkerSeverity toUnwrappedModel(
		SupportWorkerSeverity supportWorkerSeverity) {
		if (supportWorkerSeverity instanceof SupportWorkerSeverityImpl) {
			return supportWorkerSeverity;
		}

		SupportWorkerSeverityImpl supportWorkerSeverityImpl = new SupportWorkerSeverityImpl();

		supportWorkerSeverityImpl.setNew(supportWorkerSeverity.isNew());
		supportWorkerSeverityImpl.setPrimaryKey(supportWorkerSeverity.getPrimaryKey());

		supportWorkerSeverityImpl.setSupportWorkerSeverityId(supportWorkerSeverity.getSupportWorkerSeverityId());
		supportWorkerSeverityImpl.setSupportWorkerId(supportWorkerSeverity.getSupportWorkerId());
		supportWorkerSeverityImpl.setSeverity(supportWorkerSeverity.getSeverity());

		return supportWorkerSeverityImpl;
	}

	/**
	 * Returns the support worker severity with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker severity
	 * @return the support worker severity
	 * @throws NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	 */
	@Override
	public SupportWorkerSeverity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSupportWorkerSeverityException {
		SupportWorkerSeverity supportWorkerSeverity = fetchByPrimaryKey(primaryKey);

		if (supportWorkerSeverity == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSupportWorkerSeverityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return supportWorkerSeverity;
	}

	/**
	 * Returns the support worker severity with the primary key or throws a {@link NoSuchSupportWorkerSeverityException} if it could not be found.
	 *
	 * @param supportWorkerSeverityId the primary key of the support worker severity
	 * @return the support worker severity
	 * @throws NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	 */
	@Override
	public SupportWorkerSeverity findByPrimaryKey(long supportWorkerSeverityId)
		throws NoSuchSupportWorkerSeverityException {
		return findByPrimaryKey((Serializable)supportWorkerSeverityId);
	}

	/**
	 * Returns the support worker severity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker severity
	 * @return the support worker severity, or <code>null</code> if a support worker severity with the primary key could not be found
	 */
	@Override
	public SupportWorkerSeverity fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
				SupportWorkerSeverityImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SupportWorkerSeverity supportWorkerSeverity = (SupportWorkerSeverity)serializable;

		if (supportWorkerSeverity == null) {
			Session session = null;

			try {
				session = openSession();

				supportWorkerSeverity = (SupportWorkerSeverity)session.get(SupportWorkerSeverityImpl.class,
						primaryKey);

				if (supportWorkerSeverity != null) {
					cacheResult(supportWorkerSeverity);
				}
				else {
					entityCache.putResult(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
						SupportWorkerSeverityImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerSeverityImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return supportWorkerSeverity;
	}

	/**
	 * Returns the support worker severity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportWorkerSeverityId the primary key of the support worker severity
	 * @return the support worker severity, or <code>null</code> if a support worker severity with the primary key could not be found
	 */
	@Override
	public SupportWorkerSeverity fetchByPrimaryKey(long supportWorkerSeverityId) {
		return fetchByPrimaryKey((Serializable)supportWorkerSeverityId);
	}

	@Override
	public Map<Serializable, SupportWorkerSeverity> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SupportWorkerSeverity> map = new HashMap<Serializable, SupportWorkerSeverity>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SupportWorkerSeverity supportWorkerSeverity = fetchByPrimaryKey(primaryKey);

			if (supportWorkerSeverity != null) {
				map.put(primaryKey, supportWorkerSeverity);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerSeverityImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SupportWorkerSeverity)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SUPPORTWORKERSEVERITY_WHERE_PKS_IN);

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

			for (SupportWorkerSeverity supportWorkerSeverity : (List<SupportWorkerSeverity>)q.list()) {
				map.put(supportWorkerSeverity.getPrimaryKeyObj(),
					supportWorkerSeverity);

				cacheResult(supportWorkerSeverity);

				uncachedPrimaryKeys.remove(supportWorkerSeverity.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SupportWorkerSeverityModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerSeverityImpl.class, primaryKey, nullModel);
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
	 * Returns all the support worker severities.
	 *
	 * @return the support worker severities
	 */
	@Override
	public List<SupportWorkerSeverity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support worker severities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support worker severities
	 * @param end the upper bound of the range of support worker severities (not inclusive)
	 * @return the range of support worker severities
	 */
	@Override
	public List<SupportWorkerSeverity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support worker severities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support worker severities
	 * @param end the upper bound of the range of support worker severities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support worker severities
	 */
	@Override
	public List<SupportWorkerSeverity> findAll(int start, int end,
		OrderByComparator<SupportWorkerSeverity> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support worker severities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support worker severities
	 * @param end the upper bound of the range of support worker severities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of support worker severities
	 */
	@Override
	public List<SupportWorkerSeverity> findAll(int start, int end,
		OrderByComparator<SupportWorkerSeverity> orderByComparator,
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

		List<SupportWorkerSeverity> list = null;

		if (retrieveFromCache) {
			list = (List<SupportWorkerSeverity>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SUPPORTWORKERSEVERITY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTWORKERSEVERITY;

				if (pagination) {
					sql = sql.concat(SupportWorkerSeverityModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SupportWorkerSeverity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportWorkerSeverity>)QueryUtil.list(q,
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
	 * Removes all the support worker severities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SupportWorkerSeverity supportWorkerSeverity : findAll()) {
			remove(supportWorkerSeverity);
		}
	}

	/**
	 * Returns the number of support worker severities.
	 *
	 * @return the number of support worker severities
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTWORKERSEVERITY);

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
		return SupportWorkerSeverityModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the support worker severity persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SupportWorkerSeverityImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SUPPORTWORKERSEVERITY = "SELECT supportWorkerSeverity FROM SupportWorkerSeverity supportWorkerSeverity";
	private static final String _SQL_SELECT_SUPPORTWORKERSEVERITY_WHERE_PKS_IN = "SELECT supportWorkerSeverity FROM SupportWorkerSeverity supportWorkerSeverity WHERE supportWorkerSeverityId IN (";
	private static final String _SQL_SELECT_SUPPORTWORKERSEVERITY_WHERE = "SELECT supportWorkerSeverity FROM SupportWorkerSeverity supportWorkerSeverity WHERE ";
	private static final String _SQL_COUNT_SUPPORTWORKERSEVERITY = "SELECT COUNT(supportWorkerSeverity) FROM SupportWorkerSeverity supportWorkerSeverity";
	private static final String _SQL_COUNT_SUPPORTWORKERSEVERITY_WHERE = "SELECT COUNT(supportWorkerSeverity) FROM SupportWorkerSeverity supportWorkerSeverity WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportWorkerSeverity.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportWorkerSeverity exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportWorkerSeverity exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SupportWorkerSeverityPersistenceImpl.class);
}
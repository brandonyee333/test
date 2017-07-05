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

import com.liferay.osb.exception.NoSuchSupportWorkerAccountTierException;
import com.liferay.osb.model.SupportWorkerAccountTier;
import com.liferay.osb.model.impl.SupportWorkerAccountTierImpl;
import com.liferay.osb.model.impl.SupportWorkerAccountTierModelImpl;
import com.liferay.osb.service.persistence.SupportWorkerAccountTierPersistence;

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
 * The persistence implementation for the support worker account tier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerAccountTierPersistence
 * @see com.liferay.osb.service.persistence.SupportWorkerAccountTierUtil
 * @generated
 */
@ProviderType
public class SupportWorkerAccountTierPersistenceImpl extends BasePersistenceImpl<SupportWorkerAccountTier>
	implements SupportWorkerAccountTierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SupportWorkerAccountTierUtil} to access the support worker account tier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SupportWorkerAccountTierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerAccountTierModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerAccountTierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerAccountTierModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerAccountTierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerAccountTierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTWORKERID =
		new FinderPath(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerAccountTierModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerAccountTierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySupportWorkerId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID =
		new FinderPath(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerAccountTierModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerAccountTierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySupportWorkerId",
			new String[] { Long.class.getName() },
			SupportWorkerAccountTierModelImpl.SUPPORTWORKERID_COLUMN_BITMASK |
			SupportWorkerAccountTierModelImpl.ACCOUNTTIER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTWORKERID = new FinderPath(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerAccountTierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySupportWorkerId", new String[] { Long.class.getName() });

	/**
	 * Returns all the support worker account tiers where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @return the matching support worker account tiers
	 */
	@Override
	public List<SupportWorkerAccountTier> findBySupportWorkerId(
		long supportWorkerId) {
		return findBySupportWorkerId(supportWorkerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support worker account tiers where supportWorkerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportWorkerId the support worker ID
	 * @param start the lower bound of the range of support worker account tiers
	 * @param end the upper bound of the range of support worker account tiers (not inclusive)
	 * @return the range of matching support worker account tiers
	 */
	@Override
	public List<SupportWorkerAccountTier> findBySupportWorkerId(
		long supportWorkerId, int start, int end) {
		return findBySupportWorkerId(supportWorkerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support worker account tiers where supportWorkerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportWorkerId the support worker ID
	 * @param start the lower bound of the range of support worker account tiers
	 * @param end the upper bound of the range of support worker account tiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support worker account tiers
	 */
	@Override
	public List<SupportWorkerAccountTier> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		OrderByComparator<SupportWorkerAccountTier> orderByComparator) {
		return findBySupportWorkerId(supportWorkerId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support worker account tiers where supportWorkerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportWorkerId the support worker ID
	 * @param start the lower bound of the range of support worker account tiers
	 * @param end the upper bound of the range of support worker account tiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching support worker account tiers
	 */
	@Override
	public List<SupportWorkerAccountTier> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		OrderByComparator<SupportWorkerAccountTier> orderByComparator,
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

		List<SupportWorkerAccountTier> list = null;

		if (retrieveFromCache) {
			list = (List<SupportWorkerAccountTier>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SupportWorkerAccountTier supportWorkerAccountTier : list) {
					if ((supportWorkerId != supportWorkerAccountTier.getSupportWorkerId())) {
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

			query.append(_SQL_SELECT_SUPPORTWORKERACCOUNTTIER_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTWORKERID_SUPPORTWORKERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SupportWorkerAccountTierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportWorkerId);

				if (!pagination) {
					list = (List<SupportWorkerAccountTier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportWorkerAccountTier>)QueryUtil.list(q,
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
	 * Returns the first support worker account tier in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker account tier
	 * @throws NoSuchSupportWorkerAccountTierException if a matching support worker account tier could not be found
	 */
	@Override
	public SupportWorkerAccountTier findBySupportWorkerId_First(
		long supportWorkerId,
		OrderByComparator<SupportWorkerAccountTier> orderByComparator)
		throws NoSuchSupportWorkerAccountTierException {
		SupportWorkerAccountTier supportWorkerAccountTier = fetchBySupportWorkerId_First(supportWorkerId,
				orderByComparator);

		if (supportWorkerAccountTier != null) {
			return supportWorkerAccountTier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportWorkerId=");
		msg.append(supportWorkerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerAccountTierException(msg.toString());
	}

	/**
	 * Returns the first support worker account tier in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker account tier, or <code>null</code> if a matching support worker account tier could not be found
	 */
	@Override
	public SupportWorkerAccountTier fetchBySupportWorkerId_First(
		long supportWorkerId,
		OrderByComparator<SupportWorkerAccountTier> orderByComparator) {
		List<SupportWorkerAccountTier> list = findBySupportWorkerId(supportWorkerId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support worker account tier in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker account tier
	 * @throws NoSuchSupportWorkerAccountTierException if a matching support worker account tier could not be found
	 */
	@Override
	public SupportWorkerAccountTier findBySupportWorkerId_Last(
		long supportWorkerId,
		OrderByComparator<SupportWorkerAccountTier> orderByComparator)
		throws NoSuchSupportWorkerAccountTierException {
		SupportWorkerAccountTier supportWorkerAccountTier = fetchBySupportWorkerId_Last(supportWorkerId,
				orderByComparator);

		if (supportWorkerAccountTier != null) {
			return supportWorkerAccountTier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportWorkerId=");
		msg.append(supportWorkerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerAccountTierException(msg.toString());
	}

	/**
	 * Returns the last support worker account tier in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker account tier, or <code>null</code> if a matching support worker account tier could not be found
	 */
	@Override
	public SupportWorkerAccountTier fetchBySupportWorkerId_Last(
		long supportWorkerId,
		OrderByComparator<SupportWorkerAccountTier> orderByComparator) {
		int count = countBySupportWorkerId(supportWorkerId);

		if (count == 0) {
			return null;
		}

		List<SupportWorkerAccountTier> list = findBySupportWorkerId(supportWorkerId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support worker account tiers before and after the current support worker account tier in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerAccountTierId the primary key of the current support worker account tier
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support worker account tier
	 * @throws NoSuchSupportWorkerAccountTierException if a support worker account tier with the primary key could not be found
	 */
	@Override
	public SupportWorkerAccountTier[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerAccountTierId, long supportWorkerId,
		OrderByComparator<SupportWorkerAccountTier> orderByComparator)
		throws NoSuchSupportWorkerAccountTierException {
		SupportWorkerAccountTier supportWorkerAccountTier = findByPrimaryKey(supportWorkerAccountTierId);

		Session session = null;

		try {
			session = openSession();

			SupportWorkerAccountTier[] array = new SupportWorkerAccountTierImpl[3];

			array[0] = getBySupportWorkerId_PrevAndNext(session,
					supportWorkerAccountTier, supportWorkerId,
					orderByComparator, true);

			array[1] = supportWorkerAccountTier;

			array[2] = getBySupportWorkerId_PrevAndNext(session,
					supportWorkerAccountTier, supportWorkerId,
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

	protected SupportWorkerAccountTier getBySupportWorkerId_PrevAndNext(
		Session session, SupportWorkerAccountTier supportWorkerAccountTier,
		long supportWorkerId,
		OrderByComparator<SupportWorkerAccountTier> orderByComparator,
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

		query.append(_SQL_SELECT_SUPPORTWORKERACCOUNTTIER_WHERE);

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
			query.append(SupportWorkerAccountTierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportWorkerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportWorkerAccountTier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportWorkerAccountTier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support worker account tiers where supportWorkerId = &#63; from the database.
	 *
	 * @param supportWorkerId the support worker ID
	 */
	@Override
	public void removeBySupportWorkerId(long supportWorkerId) {
		for (SupportWorkerAccountTier supportWorkerAccountTier : findBySupportWorkerId(
				supportWorkerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(supportWorkerAccountTier);
		}
	}

	/**
	 * Returns the number of support worker account tiers where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @return the number of matching support worker account tiers
	 */
	@Override
	public int countBySupportWorkerId(long supportWorkerId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUPPORTWORKERID;

		Object[] finderArgs = new Object[] { supportWorkerId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTWORKERACCOUNTTIER_WHERE);

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
		"supportWorkerAccountTier.supportWorkerId = ?";

	public SupportWorkerAccountTierPersistenceImpl() {
		setModelClass(SupportWorkerAccountTier.class);
	}

	/**
	 * Caches the support worker account tier in the entity cache if it is enabled.
	 *
	 * @param supportWorkerAccountTier the support worker account tier
	 */
	@Override
	public void cacheResult(SupportWorkerAccountTier supportWorkerAccountTier) {
		entityCache.putResult(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerAccountTierImpl.class,
			supportWorkerAccountTier.getPrimaryKey(), supportWorkerAccountTier);

		supportWorkerAccountTier.resetOriginalValues();
	}

	/**
	 * Caches the support worker account tiers in the entity cache if it is enabled.
	 *
	 * @param supportWorkerAccountTiers the support worker account tiers
	 */
	@Override
	public void cacheResult(
		List<SupportWorkerAccountTier> supportWorkerAccountTiers) {
		for (SupportWorkerAccountTier supportWorkerAccountTier : supportWorkerAccountTiers) {
			if (entityCache.getResult(
						SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
						SupportWorkerAccountTierImpl.class,
						supportWorkerAccountTier.getPrimaryKey()) == null) {
				cacheResult(supportWorkerAccountTier);
			}
			else {
				supportWorkerAccountTier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support worker account tiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SupportWorkerAccountTierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support worker account tier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportWorkerAccountTier supportWorkerAccountTier) {
		entityCache.removeResult(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerAccountTierImpl.class,
			supportWorkerAccountTier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<SupportWorkerAccountTier> supportWorkerAccountTiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportWorkerAccountTier supportWorkerAccountTier : supportWorkerAccountTiers) {
			entityCache.removeResult(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
				SupportWorkerAccountTierImpl.class,
				supportWorkerAccountTier.getPrimaryKey());
		}
	}

	/**
	 * Creates a new support worker account tier with the primary key. Does not add the support worker account tier to the database.
	 *
	 * @param supportWorkerAccountTierId the primary key for the new support worker account tier
	 * @return the new support worker account tier
	 */
	@Override
	public SupportWorkerAccountTier create(long supportWorkerAccountTierId) {
		SupportWorkerAccountTier supportWorkerAccountTier = new SupportWorkerAccountTierImpl();

		supportWorkerAccountTier.setNew(true);
		supportWorkerAccountTier.setPrimaryKey(supportWorkerAccountTierId);

		return supportWorkerAccountTier;
	}

	/**
	 * Removes the support worker account tier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportWorkerAccountTierId the primary key of the support worker account tier
	 * @return the support worker account tier that was removed
	 * @throws NoSuchSupportWorkerAccountTierException if a support worker account tier with the primary key could not be found
	 */
	@Override
	public SupportWorkerAccountTier remove(long supportWorkerAccountTierId)
		throws NoSuchSupportWorkerAccountTierException {
		return remove((Serializable)supportWorkerAccountTierId);
	}

	/**
	 * Removes the support worker account tier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support worker account tier
	 * @return the support worker account tier that was removed
	 * @throws NoSuchSupportWorkerAccountTierException if a support worker account tier with the primary key could not be found
	 */
	@Override
	public SupportWorkerAccountTier remove(Serializable primaryKey)
		throws NoSuchSupportWorkerAccountTierException {
		Session session = null;

		try {
			session = openSession();

			SupportWorkerAccountTier supportWorkerAccountTier = (SupportWorkerAccountTier)session.get(SupportWorkerAccountTierImpl.class,
					primaryKey);

			if (supportWorkerAccountTier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportWorkerAccountTierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(supportWorkerAccountTier);
		}
		catch (NoSuchSupportWorkerAccountTierException nsee) {
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
	protected SupportWorkerAccountTier removeImpl(
		SupportWorkerAccountTier supportWorkerAccountTier) {
		supportWorkerAccountTier = toUnwrappedModel(supportWorkerAccountTier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(supportWorkerAccountTier)) {
				supportWorkerAccountTier = (SupportWorkerAccountTier)session.get(SupportWorkerAccountTierImpl.class,
						supportWorkerAccountTier.getPrimaryKeyObj());
			}

			if (supportWorkerAccountTier != null) {
				session.delete(supportWorkerAccountTier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (supportWorkerAccountTier != null) {
			clearCache(supportWorkerAccountTier);
		}

		return supportWorkerAccountTier;
	}

	@Override
	public SupportWorkerAccountTier updateImpl(
		SupportWorkerAccountTier supportWorkerAccountTier) {
		supportWorkerAccountTier = toUnwrappedModel(supportWorkerAccountTier);

		boolean isNew = supportWorkerAccountTier.isNew();

		SupportWorkerAccountTierModelImpl supportWorkerAccountTierModelImpl = (SupportWorkerAccountTierModelImpl)supportWorkerAccountTier;

		Session session = null;

		try {
			session = openSession();

			if (supportWorkerAccountTier.isNew()) {
				session.save(supportWorkerAccountTier);

				supportWorkerAccountTier.setNew(false);
			}
			else {
				supportWorkerAccountTier = (SupportWorkerAccountTier)session.merge(supportWorkerAccountTier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SupportWorkerAccountTierModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					supportWorkerAccountTierModelImpl.getSupportWorkerId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((supportWorkerAccountTierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						supportWorkerAccountTierModelImpl.getOriginalSupportWorkerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID,
					args);

				args = new Object[] {
						supportWorkerAccountTierModelImpl.getSupportWorkerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID,
					args);
			}
		}

		entityCache.putResult(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerAccountTierImpl.class,
			supportWorkerAccountTier.getPrimaryKey(), supportWorkerAccountTier,
			false);

		supportWorkerAccountTier.resetOriginalValues();

		return supportWorkerAccountTier;
	}

	protected SupportWorkerAccountTier toUnwrappedModel(
		SupportWorkerAccountTier supportWorkerAccountTier) {
		if (supportWorkerAccountTier instanceof SupportWorkerAccountTierImpl) {
			return supportWorkerAccountTier;
		}

		SupportWorkerAccountTierImpl supportWorkerAccountTierImpl = new SupportWorkerAccountTierImpl();

		supportWorkerAccountTierImpl.setNew(supportWorkerAccountTier.isNew());
		supportWorkerAccountTierImpl.setPrimaryKey(supportWorkerAccountTier.getPrimaryKey());

		supportWorkerAccountTierImpl.setSupportWorkerAccountTierId(supportWorkerAccountTier.getSupportWorkerAccountTierId());
		supportWorkerAccountTierImpl.setSupportWorkerId(supportWorkerAccountTier.getSupportWorkerId());
		supportWorkerAccountTierImpl.setAccountTier(supportWorkerAccountTier.getAccountTier());

		return supportWorkerAccountTierImpl;
	}

	/**
	 * Returns the support worker account tier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker account tier
	 * @return the support worker account tier
	 * @throws NoSuchSupportWorkerAccountTierException if a support worker account tier with the primary key could not be found
	 */
	@Override
	public SupportWorkerAccountTier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSupportWorkerAccountTierException {
		SupportWorkerAccountTier supportWorkerAccountTier = fetchByPrimaryKey(primaryKey);

		if (supportWorkerAccountTier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSupportWorkerAccountTierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return supportWorkerAccountTier;
	}

	/**
	 * Returns the support worker account tier with the primary key or throws a {@link NoSuchSupportWorkerAccountTierException} if it could not be found.
	 *
	 * @param supportWorkerAccountTierId the primary key of the support worker account tier
	 * @return the support worker account tier
	 * @throws NoSuchSupportWorkerAccountTierException if a support worker account tier with the primary key could not be found
	 */
	@Override
	public SupportWorkerAccountTier findByPrimaryKey(
		long supportWorkerAccountTierId)
		throws NoSuchSupportWorkerAccountTierException {
		return findByPrimaryKey((Serializable)supportWorkerAccountTierId);
	}

	/**
	 * Returns the support worker account tier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker account tier
	 * @return the support worker account tier, or <code>null</code> if a support worker account tier with the primary key could not be found
	 */
	@Override
	public SupportWorkerAccountTier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
				SupportWorkerAccountTierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SupportWorkerAccountTier supportWorkerAccountTier = (SupportWorkerAccountTier)serializable;

		if (supportWorkerAccountTier == null) {
			Session session = null;

			try {
				session = openSession();

				supportWorkerAccountTier = (SupportWorkerAccountTier)session.get(SupportWorkerAccountTierImpl.class,
						primaryKey);

				if (supportWorkerAccountTier != null) {
					cacheResult(supportWorkerAccountTier);
				}
				else {
					entityCache.putResult(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
						SupportWorkerAccountTierImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerAccountTierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return supportWorkerAccountTier;
	}

	/**
	 * Returns the support worker account tier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportWorkerAccountTierId the primary key of the support worker account tier
	 * @return the support worker account tier, or <code>null</code> if a support worker account tier with the primary key could not be found
	 */
	@Override
	public SupportWorkerAccountTier fetchByPrimaryKey(
		long supportWorkerAccountTierId) {
		return fetchByPrimaryKey((Serializable)supportWorkerAccountTierId);
	}

	@Override
	public Map<Serializable, SupportWorkerAccountTier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SupportWorkerAccountTier> map = new HashMap<Serializable, SupportWorkerAccountTier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SupportWorkerAccountTier supportWorkerAccountTier = fetchByPrimaryKey(primaryKey);

			if (supportWorkerAccountTier != null) {
				map.put(primaryKey, supportWorkerAccountTier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerAccountTierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SupportWorkerAccountTier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SUPPORTWORKERACCOUNTTIER_WHERE_PKS_IN);

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

			for (SupportWorkerAccountTier supportWorkerAccountTier : (List<SupportWorkerAccountTier>)q.list()) {
				map.put(supportWorkerAccountTier.getPrimaryKeyObj(),
					supportWorkerAccountTier);

				cacheResult(supportWorkerAccountTier);

				uncachedPrimaryKeys.remove(supportWorkerAccountTier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SupportWorkerAccountTierModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerAccountTierImpl.class, primaryKey, nullModel);
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
	 * Returns all the support worker account tiers.
	 *
	 * @return the support worker account tiers
	 */
	@Override
	public List<SupportWorkerAccountTier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support worker account tiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support worker account tiers
	 * @param end the upper bound of the range of support worker account tiers (not inclusive)
	 * @return the range of support worker account tiers
	 */
	@Override
	public List<SupportWorkerAccountTier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support worker account tiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support worker account tiers
	 * @param end the upper bound of the range of support worker account tiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support worker account tiers
	 */
	@Override
	public List<SupportWorkerAccountTier> findAll(int start, int end,
		OrderByComparator<SupportWorkerAccountTier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support worker account tiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support worker account tiers
	 * @param end the upper bound of the range of support worker account tiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of support worker account tiers
	 */
	@Override
	public List<SupportWorkerAccountTier> findAll(int start, int end,
		OrderByComparator<SupportWorkerAccountTier> orderByComparator,
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

		List<SupportWorkerAccountTier> list = null;

		if (retrieveFromCache) {
			list = (List<SupportWorkerAccountTier>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SUPPORTWORKERACCOUNTTIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTWORKERACCOUNTTIER;

				if (pagination) {
					sql = sql.concat(SupportWorkerAccountTierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SupportWorkerAccountTier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportWorkerAccountTier>)QueryUtil.list(q,
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
	 * Removes all the support worker account tiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SupportWorkerAccountTier supportWorkerAccountTier : findAll()) {
			remove(supportWorkerAccountTier);
		}
	}

	/**
	 * Returns the number of support worker account tiers.
	 *
	 * @return the number of support worker account tiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTWORKERACCOUNTTIER);

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
		return SupportWorkerAccountTierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the support worker account tier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SupportWorkerAccountTierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SUPPORTWORKERACCOUNTTIER = "SELECT supportWorkerAccountTier FROM SupportWorkerAccountTier supportWorkerAccountTier";
	private static final String _SQL_SELECT_SUPPORTWORKERACCOUNTTIER_WHERE_PKS_IN =
		"SELECT supportWorkerAccountTier FROM SupportWorkerAccountTier supportWorkerAccountTier WHERE supportWorkerAccountTierId IN (";
	private static final String _SQL_SELECT_SUPPORTWORKERACCOUNTTIER_WHERE = "SELECT supportWorkerAccountTier FROM SupportWorkerAccountTier supportWorkerAccountTier WHERE ";
	private static final String _SQL_COUNT_SUPPORTWORKERACCOUNTTIER = "SELECT COUNT(supportWorkerAccountTier) FROM SupportWorkerAccountTier supportWorkerAccountTier";
	private static final String _SQL_COUNT_SUPPORTWORKERACCOUNTTIER_WHERE = "SELECT COUNT(supportWorkerAccountTier) FROM SupportWorkerAccountTier supportWorkerAccountTier WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportWorkerAccountTier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportWorkerAccountTier exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportWorkerAccountTier exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SupportWorkerAccountTierPersistenceImpl.class);
}
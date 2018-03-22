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

import com.liferay.osb.exception.NoSuchLicenseKeySetException;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.model.impl.LicenseKeySetImpl;
import com.liferay.osb.model.impl.LicenseKeySetModelImpl;
import com.liferay.osb.service.persistence.LicenseKeySetPersistence;

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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the license key set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetPersistence
 * @see com.liferay.osb.service.persistence.LicenseKeySetUtil
 * @generated
 */
@ProviderType
public class LicenseKeySetPersistenceImpl extends BasePersistenceImpl<LicenseKeySet>
	implements LicenseKeySetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LicenseKeySetUtil} to access the license key set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LicenseKeySetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAccountEntryId", new String[] { Long.class.getName() },
			LicenseKeySetModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			LicenseKeySetModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the license key sets where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching license key sets
	 */
	@Override
	public List<LicenseKeySet> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license key sets where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @return the range of matching license key sets
	 */
	@Override
	public List<LicenseKeySet> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license key sets where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license key sets
	 */
	@Override
	public List<LicenseKeySet> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<LicenseKeySet> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license key sets where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license key sets
	 */
	@Override
	public List<LicenseKeySet> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<LicenseKeySet> orderByComparator,
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

		List<LicenseKeySet> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKeySet>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKeySet licenseKeySet : list) {
					if ((accountEntryId != licenseKeySet.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<LicenseKeySet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKeySet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {
		LicenseKeySet licenseKeySet = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeySetException(msg.toString());
	}

	/**
	 * Returns the first license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<LicenseKeySet> orderByComparator) {
		List<LicenseKeySet> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {
		LicenseKeySet licenseKeySet = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeySetException(msg.toString());
	}

	/**
	 * Returns the last license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<LicenseKeySet> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<LicenseKeySet> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license key sets before and after the current license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param licenseKeySetId the primary key of the current license key set
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key set
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet[] findByAccountEntryId_PrevAndNext(
		long licenseKeySetId, long accountEntryId,
		OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {
		LicenseKeySet licenseKeySet = findByPrimaryKey(licenseKeySetId);

		Session session = null;

		try {
			session = openSession();

			LicenseKeySet[] array = new LicenseKeySetImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, licenseKeySet,
					accountEntryId, orderByComparator, true);

			array[1] = licenseKeySet;

			array[2] = getByAccountEntryId_PrevAndNext(session, licenseKeySet,
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

	protected LicenseKeySet getByAccountEntryId_PrevAndNext(Session session,
		LicenseKeySet licenseKeySet, long accountEntryId,
		OrderByComparator<LicenseKeySet> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

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
			query.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKeySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKeySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license key sets where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (LicenseKeySet licenseKeySet : findByAccountEntryId(
				accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKeySet);
		}
	}

	/**
	 * Returns the number of license key sets where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license key sets
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEKEYSET_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "licenseKeySet.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI_N = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_AEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_N =
		new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_AEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			LicenseKeySetModelImpl.USERID_COLUMN_BITMASK |
			LicenseKeySetModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			LicenseKeySetModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI_N = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @return the matching license key sets
	 */
	@Override
	public List<LicenseKeySet> findByU_AEI_N(long userId, long accountEntryId,
		String name) {
		return findByU_AEI_N(userId, accountEntryId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @return the range of matching license key sets
	 */
	@Override
	public List<LicenseKeySet> findByU_AEI_N(long userId, long accountEntryId,
		String name, int start, int end) {
		return findByU_AEI_N(userId, accountEntryId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license key sets
	 */
	@Override
	public List<LicenseKeySet> findByU_AEI_N(long userId, long accountEntryId,
		String name, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator) {
		return findByU_AEI_N(userId, accountEntryId, name, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license key sets
	 */
	@Override
	public List<LicenseKeySet> findByU_AEI_N(long userId, long accountEntryId,
		String name, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_N;
			finderArgs = new Object[] { userId, accountEntryId, name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI_N;
			finderArgs = new Object[] {
					userId, accountEntryId, name,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKeySet> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKeySet>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKeySet licenseKeySet : list) {
					if ((userId != licenseKeySet.getUserId()) ||
							(accountEntryId != licenseKeySet.getAccountEntryId()) ||
							!Objects.equals(name, licenseKeySet.getName())) {
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

			query.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_N_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_U_AEI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_AEI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_U_AEI_N_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<LicenseKeySet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKeySet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet findByU_AEI_N_First(long userId, long accountEntryId,
		String name, OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {
		LicenseKeySet licenseKeySet = fetchByU_AEI_N_First(userId,
				accountEntryId, name, orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeySetException(msg.toString());
	}

	/**
	 * Returns the first license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet fetchByU_AEI_N_First(long userId, long accountEntryId,
		String name, OrderByComparator<LicenseKeySet> orderByComparator) {
		List<LicenseKeySet> list = findByU_AEI_N(userId, accountEntryId, name,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet findByU_AEI_N_Last(long userId, long accountEntryId,
		String name, OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {
		LicenseKeySet licenseKeySet = fetchByU_AEI_N_Last(userId,
				accountEntryId, name, orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeySetException(msg.toString());
	}

	/**
	 * Returns the last license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet fetchByU_AEI_N_Last(long userId, long accountEntryId,
		String name, OrderByComparator<LicenseKeySet> orderByComparator) {
		int count = countByU_AEI_N(userId, accountEntryId, name);

		if (count == 0) {
			return null;
		}

		List<LicenseKeySet> list = findByU_AEI_N(userId, accountEntryId, name,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license key sets before and after the current license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param licenseKeySetId the primary key of the current license key set
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key set
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet[] findByU_AEI_N_PrevAndNext(long licenseKeySetId,
		long userId, long accountEntryId, String name,
		OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {
		LicenseKeySet licenseKeySet = findByPrimaryKey(licenseKeySetId);

		Session session = null;

		try {
			session = openSession();

			LicenseKeySet[] array = new LicenseKeySetImpl[3];

			array[0] = getByU_AEI_N_PrevAndNext(session, licenseKeySet, userId,
					accountEntryId, name, orderByComparator, true);

			array[1] = licenseKeySet;

			array[2] = getByU_AEI_N_PrevAndNext(session, licenseKeySet, userId,
					accountEntryId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKeySet getByU_AEI_N_PrevAndNext(Session session,
		LicenseKeySet licenseKeySet, long userId, long accountEntryId,
		String name, OrderByComparator<LicenseKeySet> orderByComparator,
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

		query.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

		query.append(_FINDER_COLUMN_U_AEI_N_USERID_2);

		query.append(_FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_U_AEI_N_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_U_AEI_N_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_U_AEI_N_NAME_2);
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
			query.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(accountEntryId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKeySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKeySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 */
	@Override
	public void removeByU_AEI_N(long userId, long accountEntryId, String name) {
		for (LicenseKeySet licenseKeySet : findByU_AEI_N(userId,
				accountEntryId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKeySet);
		}
	}

	/**
	 * Returns the number of license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @return the number of matching license key sets
	 */
	@Override
	public int countByU_AEI_N(long userId, long accountEntryId, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_AEI_N;

		Object[] finderArgs = new Object[] { userId, accountEntryId, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEYSET_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_N_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_U_AEI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_AEI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_U_AEI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_U_AEI_N_USERID_2 = "licenseKeySet.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2 = "licenseKeySet.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_N_NAME_1 = "licenseKeySet.name IS NULL";
	private static final String _FINDER_COLUMN_U_AEI_N_NAME_2 = "licenseKeySet.name = ?";
	private static final String _FINDER_COLUMN_U_AEI_N_NAME_3 = "(licenseKeySet.name IS NULL OR licenseKeySet.name = '')";

	public LicenseKeySetPersistenceImpl() {
		setModelClass(LicenseKeySet.class);
	}

	/**
	 * Caches the license key set in the entity cache if it is enabled.
	 *
	 * @param licenseKeySet the license key set
	 */
	@Override
	public void cacheResult(LicenseKeySet licenseKeySet) {
		entityCache.putResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey(),
			licenseKeySet);

		licenseKeySet.resetOriginalValues();
	}

	/**
	 * Caches the license key sets in the entity cache if it is enabled.
	 *
	 * @param licenseKeySets the license key sets
	 */
	@Override
	public void cacheResult(List<LicenseKeySet> licenseKeySets) {
		for (LicenseKeySet licenseKeySet : licenseKeySets) {
			if (entityCache.getResult(
						LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
						LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey()) == null) {
				cacheResult(licenseKeySet);
			}
			else {
				licenseKeySet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all license key sets.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LicenseKeySetImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the license key set.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LicenseKeySet licenseKeySet) {
		entityCache.removeResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LicenseKeySet> licenseKeySets) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LicenseKeySet licenseKeySet : licenseKeySets) {
			entityCache.removeResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey());
		}
	}

	/**
	 * Creates a new license key set with the primary key. Does not add the license key set to the database.
	 *
	 * @param licenseKeySetId the primary key for the new license key set
	 * @return the new license key set
	 */
	@Override
	public LicenseKeySet create(long licenseKeySetId) {
		LicenseKeySet licenseKeySet = new LicenseKeySetImpl();

		licenseKeySet.setNew(true);
		licenseKeySet.setPrimaryKey(licenseKeySetId);

		return licenseKeySet;
	}

	/**
	 * Removes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set that was removed
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet remove(long licenseKeySetId)
		throws NoSuchLicenseKeySetException {
		return remove((Serializable)licenseKeySetId);
	}

	/**
	 * Removes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the license key set
	 * @return the license key set that was removed
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet remove(Serializable primaryKey)
		throws NoSuchLicenseKeySetException {
		Session session = null;

		try {
			session = openSession();

			LicenseKeySet licenseKeySet = (LicenseKeySet)session.get(LicenseKeySetImpl.class,
					primaryKey);

			if (licenseKeySet == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLicenseKeySetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(licenseKeySet);
		}
		catch (NoSuchLicenseKeySetException nsee) {
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
	protected LicenseKeySet removeImpl(LicenseKeySet licenseKeySet) {
		licenseKeySet = toUnwrappedModel(licenseKeySet);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(licenseKeySet)) {
				licenseKeySet = (LicenseKeySet)session.get(LicenseKeySetImpl.class,
						licenseKeySet.getPrimaryKeyObj());
			}

			if (licenseKeySet != null) {
				session.delete(licenseKeySet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (licenseKeySet != null) {
			clearCache(licenseKeySet);
		}

		return licenseKeySet;
	}

	@Override
	public LicenseKeySet updateImpl(LicenseKeySet licenseKeySet) {
		licenseKeySet = toUnwrappedModel(licenseKeySet);

		boolean isNew = licenseKeySet.isNew();

		LicenseKeySetModelImpl licenseKeySetModelImpl = (LicenseKeySetModelImpl)licenseKeySet;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (licenseKeySet.getCreateDate() == null)) {
			if (serviceContext == null) {
				licenseKeySet.setCreateDate(now);
			}
			else {
				licenseKeySet.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!licenseKeySetModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				licenseKeySet.setModifiedDate(now);
			}
			else {
				licenseKeySet.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (licenseKeySet.isNew()) {
				session.save(licenseKeySet);

				licenseKeySet.setNew(false);
			}
			else {
				licenseKeySet = (LicenseKeySet)session.merge(licenseKeySet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LicenseKeySetModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					licenseKeySetModelImpl.getAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			args = new Object[] {
					licenseKeySetModelImpl.getUserId(),
					licenseKeySetModelImpl.getAccountEntryId(),
					licenseKeySetModelImpl.getName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI_N, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_N,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((licenseKeySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeySetModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] { licenseKeySetModelImpl.getAccountEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((licenseKeySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeySetModelImpl.getOriginalUserId(),
						licenseKeySetModelImpl.getOriginalAccountEntryId(),
						licenseKeySetModelImpl.getOriginalName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI_N, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_N,
					args);

				args = new Object[] {
						licenseKeySetModelImpl.getUserId(),
						licenseKeySetModelImpl.getAccountEntryId(),
						licenseKeySetModelImpl.getName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI_N, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_N,
					args);
			}
		}

		entityCache.putResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey(),
			licenseKeySet, false);

		licenseKeySet.resetOriginalValues();

		return licenseKeySet;
	}

	protected LicenseKeySet toUnwrappedModel(LicenseKeySet licenseKeySet) {
		if (licenseKeySet instanceof LicenseKeySetImpl) {
			return licenseKeySet;
		}

		LicenseKeySetImpl licenseKeySetImpl = new LicenseKeySetImpl();

		licenseKeySetImpl.setNew(licenseKeySet.isNew());
		licenseKeySetImpl.setPrimaryKey(licenseKeySet.getPrimaryKey());

		licenseKeySetImpl.setLicenseKeySetId(licenseKeySet.getLicenseKeySetId());
		licenseKeySetImpl.setUserId(licenseKeySet.getUserId());
		licenseKeySetImpl.setUserName(licenseKeySet.getUserName());
		licenseKeySetImpl.setCreateDate(licenseKeySet.getCreateDate());
		licenseKeySetImpl.setModifiedDate(licenseKeySet.getModifiedDate());
		licenseKeySetImpl.setAccountEntryId(licenseKeySet.getAccountEntryId());
		licenseKeySetImpl.setName(licenseKeySet.getName());

		return licenseKeySetImpl;
	}

	/**
	 * Returns the license key set with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key set
	 * @return the license key set
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLicenseKeySetException {
		LicenseKeySet licenseKeySet = fetchByPrimaryKey(primaryKey);

		if (licenseKeySet == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLicenseKeySetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return licenseKeySet;
	}

	/**
	 * Returns the license key set with the primary key or throws a {@link NoSuchLicenseKeySetException} if it could not be found.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet findByPrimaryKey(long licenseKeySetId)
		throws NoSuchLicenseKeySetException {
		return findByPrimaryKey((Serializable)licenseKeySetId);
	}

	/**
	 * Returns the license key set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key set
	 * @return the license key set, or <code>null</code> if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeySetImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LicenseKeySet licenseKeySet = (LicenseKeySet)serializable;

		if (licenseKeySet == null) {
			Session session = null;

			try {
				session = openSession();

				licenseKeySet = (LicenseKeySet)session.get(LicenseKeySetImpl.class,
						primaryKey);

				if (licenseKeySet != null) {
					cacheResult(licenseKeySet);
				}
				else {
					entityCache.putResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
						LicenseKeySetImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeySetImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return licenseKeySet;
	}

	/**
	 * Returns the license key set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set, or <code>null</code> if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet fetchByPrimaryKey(long licenseKeySetId) {
		return fetchByPrimaryKey((Serializable)licenseKeySetId);
	}

	@Override
	public Map<Serializable, LicenseKeySet> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LicenseKeySet> map = new HashMap<Serializable, LicenseKeySet>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LicenseKeySet licenseKeySet = fetchByPrimaryKey(primaryKey);

			if (licenseKeySet != null) {
				map.put(primaryKey, licenseKeySet);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeySetImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LicenseKeySet)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LICENSEKEYSET_WHERE_PKS_IN);

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

			for (LicenseKeySet licenseKeySet : (List<LicenseKeySet>)q.list()) {
				map.put(licenseKeySet.getPrimaryKeyObj(), licenseKeySet);

				cacheResult(licenseKeySet);

				uncachedPrimaryKeys.remove(licenseKeySet.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeySetImpl.class, primaryKey, nullModel);
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
	 * Returns all the license key sets.
	 *
	 * @return the license key sets
	 */
	@Override
	public List<LicenseKeySet> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license key sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @return the range of license key sets
	 */
	@Override
	public List<LicenseKeySet> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the license key sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license key sets
	 */
	@Override
	public List<LicenseKeySet> findAll(int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license key sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of license key sets
	 */
	@Override
	public List<LicenseKeySet> findAll(int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator,
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

		List<LicenseKeySet> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKeySet>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LICENSEKEYSET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LICENSEKEYSET;

				if (pagination) {
					sql = sql.concat(LicenseKeySetModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LicenseKeySet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKeySet>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the license key sets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LicenseKeySet licenseKeySet : findAll()) {
			remove(licenseKeySet);
		}
	}

	/**
	 * Returns the number of license key sets.
	 *
	 * @return the number of license key sets
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LICENSEKEYSET);

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
		return LicenseKeySetModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the license key set persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LicenseKeySetImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_LICENSEKEYSET = "SELECT licenseKeySet FROM LicenseKeySet licenseKeySet";
	private static final String _SQL_SELECT_LICENSEKEYSET_WHERE_PKS_IN = "SELECT licenseKeySet FROM LicenseKeySet licenseKeySet WHERE licenseKeySetId IN (";
	private static final String _SQL_SELECT_LICENSEKEYSET_WHERE = "SELECT licenseKeySet FROM LicenseKeySet licenseKeySet WHERE ";
	private static final String _SQL_COUNT_LICENSEKEYSET = "SELECT COUNT(licenseKeySet) FROM LicenseKeySet licenseKeySet";
	private static final String _SQL_COUNT_LICENSEKEYSET_WHERE = "SELECT COUNT(licenseKeySet) FROM LicenseKeySet licenseKeySet WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "licenseKeySet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LicenseKeySet exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LicenseKeySet exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LicenseKeySetPersistenceImpl.class);
}
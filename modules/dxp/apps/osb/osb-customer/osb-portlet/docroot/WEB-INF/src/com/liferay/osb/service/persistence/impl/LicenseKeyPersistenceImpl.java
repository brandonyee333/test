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

import com.liferay.osb.exception.NoSuchLicenseKeyException;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.impl.LicenseKeyImpl;
import com.liferay.osb.model.impl.LicenseKeyModelImpl;
import com.liferay.osb.service.persistence.LicenseKeyPersistence;

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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the license key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyPersistence
 * @see com.liferay.osb.service.persistence.LicenseKeyUtil
 * @generated
 */
@ProviderType
public class LicenseKeyPersistenceImpl extends BasePersistenceImpl<LicenseKey>
	implements LicenseKeyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LicenseKeyUtil} to access the license key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LicenseKeyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LICENSEKEYSETID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLicenseKeySetId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LICENSEKEYSETID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLicenseKeySetId",
			new String[] { Long.class.getName() },
			LicenseKeyModelImpl.LICENSEKEYSETID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LICENSEKEYSETID = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLicenseKeySetId", new String[] { Long.class.getName() });

	/**
	 * Returns all the license keies where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId) {
		return findByLicenseKeySetId(licenseKeySetId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where licenseKeySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId,
		int start, int end) {
		return findByLicenseKeySetId(licenseKeySetId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where licenseKeySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return findByLicenseKeySetId(licenseKeySetId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where licenseKeySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LICENSEKEYSETID;
			finderArgs = new Object[] { licenseKeySetId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LICENSEKEYSETID;
			finderArgs = new Object[] {
					licenseKeySetId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((licenseKeySetId != licenseKey.getLicenseKeySetId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(licenseKeySetId);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByLicenseKeySetId_First(long licenseKeySetId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByLicenseKeySetId_First(licenseKeySetId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("licenseKeySetId=");
		msg.append(licenseKeySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByLicenseKeySetId_First(long licenseKeySetId,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByLicenseKeySetId(licenseKeySetId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByLicenseKeySetId_Last(long licenseKeySetId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByLicenseKeySetId_Last(licenseKeySetId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("licenseKeySetId=");
		msg.append(licenseKeySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByLicenseKeySetId_Last(long licenseKeySetId,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByLicenseKeySetId(licenseKeySetId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByLicenseKeySetId(licenseKeySetId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByLicenseKeySetId_PrevAndNext(long licenseKeyId,
		long licenseKeySetId, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByLicenseKeySetId_PrevAndNext(session, licenseKey,
					licenseKeySetId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByLicenseKeySetId_PrevAndNext(session, licenseKey,
					licenseKeySetId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByLicenseKeySetId_PrevAndNext(Session session,
		LicenseKey licenseKey, long licenseKeySetId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(licenseKeySetId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where licenseKeySetId = &#63; from the database.
	 *
	 * @param licenseKeySetId the license key set ID
	 */
	@Override
	public void removeByLicenseKeySetId(long licenseKeySetId) {
		for (LicenseKey licenseKey : findByLicenseKeySetId(licenseKeySetId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByLicenseKeySetId(long licenseKeySetId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LICENSEKEYSETID;

		Object[] finderArgs = new Object[] { licenseKeySetId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(licenseKeySetId);

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

	private static final String _FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2 =
		"licenseKey.licenseKeySetId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			LicenseKeyModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the license keies where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
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

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((accountEntryId != licenseKey.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByAccountEntryId(accountEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByAccountEntryId_PrevAndNext(long licenseKeyId,
		long accountEntryId, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, licenseKey,
					accountEntryId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByAccountEntryId_PrevAndNext(session, licenseKey,
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

	protected LicenseKey getByAccountEntryId_PrevAndNext(Session session,
		LicenseKey licenseKey, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (LicenseKey licenseKey : findByAccountEntryId(accountEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "licenseKey.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OFFERINGENTRYID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOfferingEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOfferingEntryId",
			new String[] { Long.class.getName() },
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OFFERINGENTRYID = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByOfferingEntryId", new String[] { Long.class.getName() });

	/**
	 * Returns all the license keies where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOfferingEntryId(long offeringEntryId) {
		return findByOfferingEntryId(offeringEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOfferingEntryId(long offeringEntryId,
		int start, int end) {
		return findByOfferingEntryId(offeringEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOfferingEntryId(long offeringEntryId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return findByOfferingEntryId(offeringEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOfferingEntryId(long offeringEntryId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
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

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((offeringEntryId != licenseKey.getOfferingEntryId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOfferingEntryId_First(long offeringEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOfferingEntryId_First(offeringEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOfferingEntryId_First(long offeringEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByOfferingEntryId(offeringEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOfferingEntryId_Last(long offeringEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOfferingEntryId_Last(offeringEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOfferingEntryId_Last(long offeringEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByOfferingEntryId(offeringEntryId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOfferingEntryId(offeringEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByOfferingEntryId_PrevAndNext(long licenseKeyId,
		long offeringEntryId, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOfferingEntryId_PrevAndNext(session, licenseKey,
					offeringEntryId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOfferingEntryId_PrevAndNext(session, licenseKey,
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

	protected LicenseKey getByOfferingEntryId_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 */
	@Override
	public void removeByOfferingEntryId(long offeringEntryId) {
		for (LicenseKey licenseKey : findByOfferingEntryId(offeringEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOfferingEntryId(long offeringEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_OFFERINGENTRYID;

		Object[] finderArgs = new Object[] { offeringEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

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
		"licenseKey.offeringEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_AEI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			LicenseKeyModelImpl.USERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_AEI(long userId, long accountEntryId) {
		return findByU_AEI(userId, accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_AEI(long userId, long accountEntryId,
		int start, int end) {
		return findByU_AEI(userId, accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_AEI(long userId, long accountEntryId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return findByU_AEI(userId, accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_AEI(long userId, long accountEntryId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI;
			finderArgs = new Object[] { userId, accountEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI;
			finderArgs = new Object[] {
					userId, accountEntryId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((userId != licenseKey.getUserId()) ||
							(accountEntryId != licenseKey.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByU_AEI_First(long userId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByU_AEI_First(userId, accountEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_AEI_First(long userId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByU_AEI(userId, accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByU_AEI_Last(long userId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByU_AEI_Last(userId, accountEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_AEI_Last(long userId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByU_AEI(userId, accountEntryId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByU_AEI(userId, accountEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByU_AEI_PrevAndNext(long licenseKeyId, long userId,
		long accountEntryId, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByU_AEI_PrevAndNext(session, licenseKey, userId,
					accountEntryId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByU_AEI_PrevAndNext(session, licenseKey, userId,
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

	protected LicenseKey getByU_AEI_PrevAndNext(Session session,
		LicenseKey licenseKey, long userId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_U_AEI_USERID_2);

		query.append(_FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where userId = &#63; and accountEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByU_AEI(long userId, long accountEntryId) {
		for (LicenseKey licenseKey : findByU_AEI(userId, accountEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByU_AEI(long userId, long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_AEI;

		Object[] finderArgs = new Object[] { userId, accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_U_AEI_USERID_2 = "licenseKey.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2 = "licenseKey.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLI_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLI_A",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ARLI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLI_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_A(long assetReceiptLicenseId,
		boolean active) {
		return findByARLI_A(assetReceiptLicenseId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_A(long assetReceiptLicenseId,
		boolean active, int start, int end) {
		return findByARLI_A(assetReceiptLicenseId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_A(long assetReceiptLicenseId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return findByARLI_A(assetReceiptLicenseId, active, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_A(long assetReceiptLicenseId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_A;
			finderArgs = new Object[] { assetReceiptLicenseId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_A;
			finderArgs = new Object[] {
					assetReceiptLicenseId, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((assetReceiptLicenseId != licenseKey.getAssetReceiptLicenseId()) ||
							(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_A_ASSETRECEIPTLICENSEID_2);

			query.append(_FINDER_COLUMN_ARLI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				qPos.add(active);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLI_A_First(long assetReceiptLicenseId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByARLI_A_First(assetReceiptLicenseId,
				active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLI_A_First(long assetReceiptLicenseId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByARLI_A(assetReceiptLicenseId, active, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLI_A_Last(long assetReceiptLicenseId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByARLI_A_Last(assetReceiptLicenseId,
				active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLI_A_Last(long assetReceiptLicenseId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByARLI_A(assetReceiptLicenseId, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByARLI_A(assetReceiptLicenseId, active,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByARLI_A_PrevAndNext(long licenseKeyId,
		long assetReceiptLicenseId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLI_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLI_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByARLI_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long assetReceiptLicenseId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_ARLI_A_ASSETRECEIPTLICENSEID_2);

		query.append(_FINDER_COLUMN_ARLI_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetReceiptLicenseId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseId = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 */
	@Override
	public void removeByARLI_A(long assetReceiptLicenseId, boolean active) {
		for (LicenseKey licenseKey : findByARLI_A(assetReceiptLicenseId,
				active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByARLI_A(long assetReceiptLicenseId, boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ARLI_A;

		Object[] finderArgs = new Object[] { assetReceiptLicenseId, active };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_A_ASSETRECEIPTLICENSEID_2);

			query.append(_FINDER_COLUMN_ARLI_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_ARLI_A_ASSETRECEIPTLICENSEID_2 = "licenseKey.assetReceiptLicenseId = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_A_ACTIVE_2 = "licenseKey.active = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_CI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_CI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_CI",
			new String[] { Long.class.getName(), Long.class.getName() },
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.CLUSTERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OEI_CI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_CI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_CI(long offeringEntryId, long clusterId) {
		return findByOEI_CI(offeringEntryId, clusterId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_CI(long offeringEntryId, long clusterId,
		int start, int end) {
		return findByOEI_CI(offeringEntryId, clusterId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_CI(long offeringEntryId, long clusterId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return findByOEI_CI(offeringEntryId, clusterId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_CI(long offeringEntryId, long clusterId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI;
			finderArgs = new Object[] { offeringEntryId, clusterId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_CI;
			finderArgs = new Object[] {
					offeringEntryId, clusterId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
							(clusterId != licenseKey.getClusterId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_CI_CLUSTERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(clusterId);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_CI_First(long offeringEntryId, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_CI_First(offeringEntryId, clusterId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", clusterId=");
		msg.append(clusterId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_CI_First(long offeringEntryId, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByOEI_CI(offeringEntryId, clusterId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_CI_Last(long offeringEntryId, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_CI_Last(offeringEntryId, clusterId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", clusterId=");
		msg.append(clusterId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_CI_Last(long offeringEntryId, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByOEI_CI(offeringEntryId, clusterId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOEI_CI(offeringEntryId, clusterId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByOEI_CI_PrevAndNext(long licenseKeyId,
		long offeringEntryId, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_CI_PrevAndNext(session, licenseKey,
					offeringEntryId, clusterId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_CI_PrevAndNext(session, licenseKey,
					offeringEntryId, clusterId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByOEI_CI_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2);

		query.append(_FINDER_COLUMN_OEI_CI_CLUSTERID_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		qPos.add(clusterId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 */
	@Override
	public void removeByOEI_CI(long offeringEntryId, long clusterId) {
		for (LicenseKey licenseKey : findByOEI_CI(offeringEntryId, clusterId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOEI_CI(long offeringEntryId, long clusterId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_OEI_CI;

		Object[] finderArgs = new Object[] { offeringEntryId, clusterId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_CI_CLUSTERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(clusterId);

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

	private static final String _FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2 = "licenseKey.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_CI_CLUSTERID_2 = "licenseKey.clusterId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_O = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_O",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_O = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_O",
			new String[] { Long.class.getName(), String.class.getName() },
			LicenseKeyModelImpl.ORDERENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.OWNER_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OEI_O = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_O",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the license keies where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_O(long orderEntryId, String owner) {
		return findByOEI_O(orderEntryId, owner, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where orderEntryId = &#63; and owner = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_O(long orderEntryId, String owner,
		int start, int end) {
		return findByOEI_O(orderEntryId, owner, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where orderEntryId = &#63; and owner = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_O(long orderEntryId, String owner,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return findByOEI_O(orderEntryId, owner, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the license keies where orderEntryId = &#63; and owner = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_O(long orderEntryId, String owner,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_O;
			finderArgs = new Object[] { orderEntryId, owner };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_O;
			finderArgs = new Object[] {
					orderEntryId, owner,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((orderEntryId != licenseKey.getOrderEntryId()) ||
							!Objects.equals(owner, licenseKey.getOwner())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_O_ORDERENTRYID_2);

			boolean bindOwner = false;

			if (owner == null) {
				query.append(_FINDER_COLUMN_OEI_O_OWNER_1);
			}
			else if (owner.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OEI_O_OWNER_3);
			}
			else {
				bindOwner = true;

				query.append(_FINDER_COLUMN_OEI_O_OWNER_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderEntryId);

				if (bindOwner) {
					qPos.add(owner);
				}

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_O_First(long orderEntryId, String owner,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_O_First(orderEntryId, owner,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("orderEntryId=");
		msg.append(orderEntryId);

		msg.append(", owner=");
		msg.append(owner);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_O_First(long orderEntryId, String owner,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByOEI_O(orderEntryId, owner, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_O_Last(long orderEntryId, String owner,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_O_Last(orderEntryId, owner,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("orderEntryId=");
		msg.append(orderEntryId);

		msg.append(", owner=");
		msg.append(owner);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_O_Last(long orderEntryId, String owner,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByOEI_O(orderEntryId, owner);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOEI_O(orderEntryId, owner, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByOEI_O_PrevAndNext(long licenseKeyId,
		long orderEntryId, String owner,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_O_PrevAndNext(session, licenseKey,
					orderEntryId, owner, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_O_PrevAndNext(session, licenseKey,
					orderEntryId, owner, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByOEI_O_PrevAndNext(Session session,
		LicenseKey licenseKey, long orderEntryId, String owner,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_O_ORDERENTRYID_2);

		boolean bindOwner = false;

		if (owner == null) {
			query.append(_FINDER_COLUMN_OEI_O_OWNER_1);
		}
		else if (owner.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_OEI_O_OWNER_3);
		}
		else {
			bindOwner = true;

			query.append(_FINDER_COLUMN_OEI_O_OWNER_2);
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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(orderEntryId);

		if (bindOwner) {
			qPos.add(owner);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where orderEntryId = &#63; and owner = &#63; from the database.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 */
	@Override
	public void removeByOEI_O(long orderEntryId, String owner) {
		for (LicenseKey licenseKey : findByOEI_O(orderEntryId, owner,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOEI_O(long orderEntryId, String owner) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_OEI_O;

		Object[] finderArgs = new Object[] { orderEntryId, owner };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_O_ORDERENTRYID_2);

			boolean bindOwner = false;

			if (owner == null) {
				query.append(_FINDER_COLUMN_OEI_O_OWNER_1);
			}
			else if (owner.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OEI_O_OWNER_3);
			}
			else {
				bindOwner = true;

				query.append(_FINDER_COLUMN_OEI_O_OWNER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderEntryId);

				if (bindOwner) {
					qPos.add(owner);
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

	private static final String _FINDER_COLUMN_OEI_O_ORDERENTRYID_2 = "licenseKey.orderEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_O_OWNER_1 = "licenseKey.owner IS NULL";
	private static final String _FINDER_COLUMN_OEI_O_OWNER_2 = "licenseKey.owner = ?";
	private static final String _FINDER_COLUMN_OEI_O_OWNER_3 = "(licenseKey.owner IS NULL OR licenseKey.owner = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PI_SI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPI_SI",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PI_SI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPI_SI",
			new String[] { String.class.getName(), String.class.getName() },
			LicenseKeyModelImpl.PRODUCTID_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PI_SI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPI_SI",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPI_SI(String productId, String serverId) {
		return findByPI_SI(productId, serverId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPI_SI(String productId, String serverId,
		int start, int end) {
		return findByPI_SI(productId, serverId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPI_SI(String productId, String serverId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return findByPI_SI(productId, serverId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPI_SI(String productId, String serverId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PI_SI;
			finderArgs = new Object[] { productId, serverId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PI_SI;
			finderArgs = new Object[] {
					productId, serverId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!Objects.equals(productId, licenseKey.getProductId()) ||
							!Objects.equals(serverId, licenseKey.getServerId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindProductId = false;

			if (productId == null) {
				query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_1);
			}
			else if (productId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId == null) {
				query.append(_FINDER_COLUMN_PI_SI_SERVERID_1);
			}
			else if (serverId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
			}
			else {
				bindServerId = true;

				query.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindProductId) {
					qPos.add(productId);
				}

				if (bindServerId) {
					qPos.add(serverId);
				}

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPI_SI_First(String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByPI_SI_First(productId, serverId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productId=");
		msg.append(productId);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPI_SI_First(String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByPI_SI(productId, serverId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPI_SI_Last(String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByPI_SI_Last(productId, serverId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productId=");
		msg.append(productId);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPI_SI_Last(String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByPI_SI(productId, serverId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPI_SI(productId, serverId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByPI_SI_PrevAndNext(long licenseKeyId,
		String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPI_SI_PrevAndNext(session, licenseKey, productId,
					serverId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPI_SI_PrevAndNext(session, licenseKey, productId,
					serverId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByPI_SI_PrevAndNext(Session session,
		LicenseKey licenseKey, String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindProductId = false;

		if (productId == null) {
			query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_1);
		}
		else if (productId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
		}
		else {
			bindProductId = true;

			query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
		}

		boolean bindServerId = false;

		if (serverId == null) {
			query.append(_FINDER_COLUMN_PI_SI_SERVERID_1);
		}
		else if (serverId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
		}
		else {
			bindServerId = true;

			query.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindProductId) {
			qPos.add(productId);
		}

		if (bindServerId) {
			qPos.add(serverId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where productId = &#63; and serverId = &#63; from the database.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 */
	@Override
	public void removeByPI_SI(String productId, String serverId) {
		for (LicenseKey licenseKey : findByPI_SI(productId, serverId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPI_SI(String productId, String serverId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PI_SI;

		Object[] finderArgs = new Object[] { productId, serverId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductId = false;

			if (productId == null) {
				query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_1);
			}
			else if (productId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId == null) {
				query.append(_FINDER_COLUMN_PI_SI_SERVERID_1);
			}
			else if (serverId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
			}
			else {
				bindServerId = true;

				query.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindProductId) {
					qPos.add(productId);
				}

				if (bindServerId) {
					qPos.add(serverId);
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

	private static final String _FINDER_COLUMN_PI_SI_PRODUCTID_1 = "licenseKey.productId IS NULL AND ";
	private static final String _FINDER_COLUMN_PI_SI_PRODUCTID_2 = "licenseKey.productId = ? AND ";
	private static final String _FINDER_COLUMN_PI_SI_PRODUCTID_3 = "(licenseKey.productId IS NULL OR licenseKey.productId = '') AND ";
	private static final String _FINDER_COLUMN_PI_SI_SERVERID_1 = "licenseKey.serverId IS NULL";
	private static final String _FINDER_COLUMN_PI_SI_SERVERID_2 = "licenseKey.serverId = ?";
	private static final String _FINDER_COLUMN_PI_SI_SERVERID_3 = "(licenseKey.serverId IS NULL OR licenseKey.serverId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_ARLI_PI =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_ARLI_PI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_ARLI_PI =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_ARLI_PI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, String productId) {
		return findByU_ARLI_PI(userId, assetReceiptLicenseId, productId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, String productId, int start, int end) {
		return findByU_ARLI_PI(userId, assetReceiptLicenseId, productId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, String productId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return findByU_ARLI_PI(userId, assetReceiptLicenseId, productId, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, String productId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_ARLI_PI;
		finderArgs = new Object[] {
				userId, assetReceiptLicenseId, productId,
				
				start, end, orderByComparator
			};

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((userId != licenseKey.getUserId()) ||
							(assetReceiptLicenseId >= licenseKey.getAssetReceiptLicenseId()) ||
							!Objects.equals(productId, licenseKey.getProductId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_U_ARLI_PI_USERID_2);

			query.append(_FINDER_COLUMN_U_ARLI_PI_ASSETRECEIPTLICENSEID_2);

			boolean bindProductId = false;

			if (productId == null) {
				query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_1);
			}
			else if (productId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(assetReceiptLicenseId);

				if (bindProductId) {
					qPos.add(productId);
				}

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByU_ARLI_PI_First(long userId,
		long assetReceiptLicenseId, String productId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByU_ARLI_PI_First(userId,
				assetReceiptLicenseId, productId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_ARLI_PI_First(long userId,
		long assetReceiptLicenseId, String productId,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByU_ARLI_PI(userId, assetReceiptLicenseId,
				productId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByU_ARLI_PI_Last(long userId,
		long assetReceiptLicenseId, String productId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByU_ARLI_PI_Last(userId,
				assetReceiptLicenseId, productId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_ARLI_PI_Last(long userId,
		long assetReceiptLicenseId, String productId,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByU_ARLI_PI(userId, assetReceiptLicenseId, productId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByU_ARLI_PI(userId, assetReceiptLicenseId,
				productId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByU_ARLI_PI_PrevAndNext(long licenseKeyId,
		long userId, long assetReceiptLicenseId, String productId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByU_ARLI_PI_PrevAndNext(session, licenseKey, userId,
					assetReceiptLicenseId, productId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByU_ARLI_PI_PrevAndNext(session, licenseKey, userId,
					assetReceiptLicenseId, productId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByU_ARLI_PI_PrevAndNext(Session session,
		LicenseKey licenseKey, long userId, long assetReceiptLicenseId,
		String productId, OrderByComparator<LicenseKey> orderByComparator,
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

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_U_ARLI_PI_USERID_2);

		query.append(_FINDER_COLUMN_U_ARLI_PI_ASSETRECEIPTLICENSEID_2);

		boolean bindProductId = false;

		if (productId == null) {
			query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_1);
		}
		else if (productId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_3);
		}
		else {
			bindProductId = true;

			query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_2);
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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(assetReceiptLicenseId);

		if (bindProductId) {
			qPos.add(productId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 */
	@Override
	public void removeByU_ARLI_PI(long userId, long assetReceiptLicenseId,
		String productId) {
		for (LicenseKey licenseKey : findByU_ARLI_PI(userId,
				assetReceiptLicenseId, productId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByU_ARLI_PI(long userId, long assetReceiptLicenseId,
		String productId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_ARLI_PI;

		Object[] finderArgs = new Object[] {
				userId, assetReceiptLicenseId, productId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_U_ARLI_PI_USERID_2);

			query.append(_FINDER_COLUMN_U_ARLI_PI_ASSETRECEIPTLICENSEID_2);

			boolean bindProductId = false;

			if (productId == null) {
				query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_1);
			}
			else if (productId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(assetReceiptLicenseId);

				if (bindProductId) {
					qPos.add(productId);
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

	private static final String _FINDER_COLUMN_U_ARLI_PI_USERID_2 = "licenseKey.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_ARLI_PI_ASSETRECEIPTLICENSEID_2 =
		"licenseKey.assetReceiptLicenseId > ? AND ";
	private static final String _FINDER_COLUMN_U_ARLI_PI_PRODUCTID_1 = "licenseKey.productId IS NULL";
	private static final String _FINDER_COLUMN_U_ARLI_PI_PRODUCTID_2 = "licenseKey.productId = ?";
	private static final String _FINDER_COLUMN_U_ARLI_PI_PRODUCTID_3 = "(licenseKey.productId IS NULL OR licenseKey.productId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEID_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ARLI_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active) {
		return findByARLI_C_A(assetReceiptLicenseId, complimentary, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active, int start, int end) {
		return findByARLI_C_A(assetReceiptLicenseId, complimentary, active,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return findByARLI_C_A(assetReceiptLicenseId, complimentary, active,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_C_A;
			finderArgs = new Object[] {
					assetReceiptLicenseId, complimentary, active
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_C_A;
			finderArgs = new Object[] {
					assetReceiptLicenseId, complimentary, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((assetReceiptLicenseId != licenseKey.getAssetReceiptLicenseId()) ||
							(complimentary != licenseKey.getComplimentary()) ||
							(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_C_A_ASSETRECEIPTLICENSEID_2);

			query.append(_FINDER_COLUMN_ARLI_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_ARLI_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				qPos.add(complimentary);

				qPos.add(active);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLI_C_A_First(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByARLI_C_A_First(assetReceiptLicenseId,
				complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLI_C_A_First(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByARLI_C_A(assetReceiptLicenseId,
				complimentary, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLI_C_A_Last(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByARLI_C_A_Last(assetReceiptLicenseId,
				complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLI_C_A_Last(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByARLI_C_A(assetReceiptLicenseId, complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByARLI_C_A(assetReceiptLicenseId,
				complimentary, active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByARLI_C_A_PrevAndNext(long licenseKeyId,
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLI_C_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, complimentary, active,
					orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLI_C_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, complimentary, active,
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

	protected LicenseKey getByARLI_C_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_ARLI_C_A_ASSETRECEIPTLICENSEID_2);

		query.append(_FINDER_COLUMN_ARLI_C_A_COMPLIMENTARY_2);

		query.append(_FINDER_COLUMN_ARLI_C_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetReceiptLicenseId);

		qPos.add(complimentary);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active) {
		for (LicenseKey licenseKey : findByARLI_C_A(assetReceiptLicenseId,
				complimentary, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ARLI_C_A;

		Object[] finderArgs = new Object[] {
				assetReceiptLicenseId, complimentary, active
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_C_A_ASSETRECEIPTLICENSEID_2);

			query.append(_FINDER_COLUMN_ARLI_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_ARLI_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				qPos.add(complimentary);

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_ARLI_C_A_ASSETRECEIPTLICENSEID_2 = "licenseKey.assetReceiptLicenseId = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_C_A_COMPLIMENTARY_2 = "licenseKey.complimentary = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_C_A_ACTIVE_2 = "licenseKey.active = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_CI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_CI_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_CI_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.CLUSTERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OEI_CI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_CI_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active) {
		return findByOEI_CI_A(offeringEntryId, clusterId, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active, int start, int end) {
		return findByOEI_CI_A(offeringEntryId, clusterId, active, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return findByOEI_CI_A(offeringEntryId, clusterId, active, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI_A;
			finderArgs = new Object[] { offeringEntryId, clusterId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_CI_A;
			finderArgs = new Object[] {
					offeringEntryId, clusterId, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
							(clusterId != licenseKey.getClusterId()) ||
							(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_CI_A_CLUSTERID_2);

			query.append(_FINDER_COLUMN_OEI_CI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(clusterId);

				qPos.add(active);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_CI_A_First(long offeringEntryId,
		long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_CI_A_First(offeringEntryId,
				clusterId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", clusterId=");
		msg.append(clusterId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_CI_A_First(long offeringEntryId,
		long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByOEI_CI_A(offeringEntryId, clusterId,
				active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_CI_A_Last(long offeringEntryId, long clusterId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_CI_A_Last(offeringEntryId,
				clusterId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", clusterId=");
		msg.append(clusterId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_CI_A_Last(long offeringEntryId,
		long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByOEI_CI_A(offeringEntryId, clusterId, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOEI_CI_A(offeringEntryId, clusterId,
				active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByOEI_CI_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_CI_A_PrevAndNext(session, licenseKey,
					offeringEntryId, clusterId, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_CI_A_PrevAndNext(session, licenseKey,
					offeringEntryId, clusterId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByOEI_CI_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId, long clusterId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator,
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

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2);

		query.append(_FINDER_COLUMN_OEI_CI_A_CLUSTERID_2);

		query.append(_FINDER_COLUMN_OEI_CI_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		qPos.add(clusterId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 */
	@Override
	public void removeByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active) {
		for (LicenseKey licenseKey : findByOEI_CI_A(offeringEntryId, clusterId,
				active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_OEI_CI_A;

		Object[] finderArgs = new Object[] { offeringEntryId, clusterId, active };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_CI_A_CLUSTERID_2);

			query.append(_FINDER_COLUMN_OEI_CI_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(clusterId);

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2 = "licenseKey.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_CI_A_CLUSTERID_2 = "licenseKey.clusterId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_CI_A_ACTIVE_2 = "licenseKey.active = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OEI_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active) {
		return findByOEI_C_A(offeringEntryId, complimentary, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active, int start, int end) {
		return findByOEI_C_A(offeringEntryId, complimentary, active, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return findByOEI_C_A(offeringEntryId, complimentary, active, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_C_A;
			finderArgs = new Object[] { offeringEntryId, complimentary, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_C_A;
			finderArgs = new Object[] {
					offeringEntryId, complimentary, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
							(complimentary != licenseKey.getComplimentary()) ||
							(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(complimentary);

				qPos.add(active);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_C_A_First(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_C_A_First(offeringEntryId,
				complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_C_A_First(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByOEI_C_A(offeringEntryId, complimentary,
				active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_C_A_Last(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_C_A_Last(offeringEntryId,
				complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_C_A_Last(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByOEI_C_A(offeringEntryId, complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOEI_C_A(offeringEntryId, complimentary,
				active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByOEI_C_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, complimentary, active, orderByComparator,
					true);

			array[1] = licenseKey;

			array[2] = getByOEI_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, complimentary, active, orderByComparator,
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

	protected LicenseKey getByOEI_C_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator,
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

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2);

		query.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

		query.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		qPos.add(complimentary);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active) {
		return findByOEI_C_A(offeringEntryIds, complimentary, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end) {
		return findByOEI_C_A(offeringEntryIds, complimentary, active, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return findByOEI_C_A(offeringEntryIds, complimentary, active, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		if (offeringEntryIds == null) {
			offeringEntryIds = new long[0];
		}
		else if (offeringEntryIds.length > 1) {
			offeringEntryIds = ArrayUtil.unique(offeringEntryIds);

			Arrays.sort(offeringEntryIds);
		}

		if (offeringEntryIds.length == 1) {
			return findByOEI_C_A(offeringEntryIds[0], complimentary, active,
				start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(offeringEntryIds), complimentary, active
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(offeringEntryIds), complimentary, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_C_A,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!ArrayUtil.contains(offeringEntryIds,
								licenseKey.getOfferingEntryId()) ||
							(complimentary != licenseKey.getComplimentary()) ||
							(active != licenseKey.getActive())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			if (offeringEntryIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_7);

				query.append(StringUtil.merge(offeringEntryIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(complimentary);

				qPos.add(active);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_C_A,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_C_A,
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
	 * Removes all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByOEI_C_A(long offeringEntryId, boolean complimentary,
		boolean active) {
		for (LicenseKey licenseKey : findByOEI_C_A(offeringEntryId,
				complimentary, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOEI_C_A(long offeringEntryId, boolean complimentary,
		boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_OEI_C_A;

		Object[] finderArgs = new Object[] {
				offeringEntryId, complimentary, active
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(complimentary);

				qPos.add(active);

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
	 * Returns the number of license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOEI_C_A(long[] offeringEntryIds, boolean complimentary,
		boolean active) {
		if (offeringEntryIds == null) {
			offeringEntryIds = new long[0];
		}
		else if (offeringEntryIds.length > 1) {
			offeringEntryIds = ArrayUtil.unique(offeringEntryIds);

			Arrays.sort(offeringEntryIds);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(offeringEntryIds), complimentary, active
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_C_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			if (offeringEntryIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_7);

				query.append(StringUtil.merge(offeringEntryIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(complimentary);

				qPos.add(active);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_C_A,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_C_A,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2 = "licenseKey.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_7 = "licenseKey.offeringEntryId IN (";
	private static final String _FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2 = "licenseKey.complimentary = ? AND ";
	private static final String _FINDER_COLUMN_OEI_C_A_ACTIVE_2 = "licenseKey.active = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PEN_SI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPEN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEN_SI_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPEN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.PRODUCTENTRYNAME_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PEN_SI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPEN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPEN_SI_A(String productEntryName,
		String serverId, boolean active) {
		return findByPEN_SI_A(productEntryName, serverId, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPEN_SI_A(String productEntryName,
		String serverId, boolean active, int start, int end) {
		return findByPEN_SI_A(productEntryName, serverId, active, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPEN_SI_A(String productEntryName,
		String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return findByPEN_SI_A(productEntryName, serverId, active, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPEN_SI_A(String productEntryName,
		String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEN_SI_A;
			finderArgs = new Object[] { productEntryName, serverId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PEN_SI_A;
			finderArgs = new Object[] {
					productEntryName, serverId, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!Objects.equals(productEntryName,
								licenseKey.getProductEntryName()) ||
							!Objects.equals(serverId, licenseKey.getServerId()) ||
							(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindProductEntryName = false;

			if (productEntryName == null) {
				query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_1);
			}
			else if (productEntryName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3);
			}
			else {
				bindProductEntryName = true;

				query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2);
			}

			boolean bindServerId = false;

			if (serverId == null) {
				query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_1);
			}
			else if (serverId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_2);
			}

			query.append(_FINDER_COLUMN_PEN_SI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindProductEntryName) {
					qPos.add(productEntryName);
				}

				if (bindServerId) {
					qPos.add(serverId);
				}

				qPos.add(active);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPEN_SI_A_First(String productEntryName,
		String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByPEN_SI_A_First(productEntryName,
				serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryName=");
		msg.append(productEntryName);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPEN_SI_A_First(String productEntryName,
		String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByPEN_SI_A(productEntryName, serverId,
				active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPEN_SI_A_Last(String productEntryName,
		String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByPEN_SI_A_Last(productEntryName,
				serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryName=");
		msg.append(productEntryName);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPEN_SI_A_Last(String productEntryName,
		String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByPEN_SI_A(productEntryName, serverId, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPEN_SI_A(productEntryName, serverId,
				active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByPEN_SI_A_PrevAndNext(long licenseKeyId,
		String productEntryName, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPEN_SI_A_PrevAndNext(session, licenseKey,
					productEntryName, serverId, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPEN_SI_A_PrevAndNext(session, licenseKey,
					productEntryName, serverId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByPEN_SI_A_PrevAndNext(Session session,
		LicenseKey licenseKey, String productEntryName, String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator,
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

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindProductEntryName = false;

		if (productEntryName == null) {
			query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_1);
		}
		else if (productEntryName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3);
		}
		else {
			bindProductEntryName = true;

			query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2);
		}

		boolean bindServerId = false;

		if (serverId == null) {
			query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_1);
		}
		else if (serverId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_3);
		}
		else {
			bindServerId = true;

			query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_2);
		}

		query.append(_FINDER_COLUMN_PEN_SI_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindProductEntryName) {
			qPos.add(productEntryName);
		}

		if (bindServerId) {
			qPos.add(serverId);
		}

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 */
	@Override
	public void removeByPEN_SI_A(String productEntryName, String serverId,
		boolean active) {
		for (LicenseKey licenseKey : findByPEN_SI_A(productEntryName, serverId,
				active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPEN_SI_A(String productEntryName, String serverId,
		boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PEN_SI_A;

		Object[] finderArgs = new Object[] { productEntryName, serverId, active };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductEntryName = false;

			if (productEntryName == null) {
				query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_1);
			}
			else if (productEntryName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3);
			}
			else {
				bindProductEntryName = true;

				query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2);
			}

			boolean bindServerId = false;

			if (serverId == null) {
				query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_1);
			}
			else if (serverId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_2);
			}

			query.append(_FINDER_COLUMN_PEN_SI_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindProductEntryName) {
					qPos.add(productEntryName);
				}

				if (bindServerId) {
					qPos.add(serverId);
				}

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_1 = "licenseKey.productEntryName IS NULL AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2 = "licenseKey.productEntryName = ? AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3 = "(licenseKey.productEntryName IS NULL OR licenseKey.productEntryName = '') AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_SERVERID_1 = "licenseKey.serverId IS NULL AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_SERVERID_2 = "licenseKey.serverId = ? AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_SERVERID_3 = "(licenseKey.serverId IS NULL OR licenseKey.serverId = '') AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_ACTIVE_2 = "licenseKey.active = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_PI_SI_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLI_PI_SI_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_PI_SI_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLI_PI_SI_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName()
			},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEID_COLUMN_BITMASK |
			LicenseKeyModelImpl.PRODUCTID_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ARLI_PI_SI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLI_PI_SI_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_PI_SI_A(long assetReceiptLicenseId,
		String productId, String serverId, boolean active) {
		return findByARLI_PI_SI_A(assetReceiptLicenseId, productId, serverId,
			active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_PI_SI_A(long assetReceiptLicenseId,
		String productId, String serverId, boolean active, int start, int end) {
		return findByARLI_PI_SI_A(assetReceiptLicenseId, productId, serverId,
			active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_PI_SI_A(long assetReceiptLicenseId,
		String productId, String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return findByARLI_PI_SI_A(assetReceiptLicenseId, productId, serverId,
			active, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLI_PI_SI_A(long assetReceiptLicenseId,
		String productId, String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_PI_SI_A;
			finderArgs = new Object[] {
					assetReceiptLicenseId, productId, serverId, active
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_PI_SI_A;
			finderArgs = new Object[] {
					assetReceiptLicenseId, productId, serverId, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((assetReceiptLicenseId != licenseKey.getAssetReceiptLicenseId()) ||
							!Objects.equals(productId, licenseKey.getProductId()) ||
							!Objects.equals(serverId, licenseKey.getServerId()) ||
							(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ASSETRECEIPTLICENSEID_2);

			boolean bindProductId = false;

			if (productId == null) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_1);
			}
			else if (productId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId == null) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_1);
			}
			else if (serverId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_2);
			}

			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				if (bindProductId) {
					qPos.add(productId);
				}

				if (bindServerId) {
					qPos.add(serverId);
				}

				qPos.add(active);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLI_PI_SI_A_First(long assetReceiptLicenseId,
		String productId, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByARLI_PI_SI_A_First(assetReceiptLicenseId,
				productId, serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLI_PI_SI_A_First(long assetReceiptLicenseId,
		String productId, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByARLI_PI_SI_A(assetReceiptLicenseId,
				productId, serverId, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLI_PI_SI_A_Last(long assetReceiptLicenseId,
		String productId, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByARLI_PI_SI_A_Last(assetReceiptLicenseId,
				productId, serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLI_PI_SI_A_Last(long assetReceiptLicenseId,
		String productId, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByARLI_PI_SI_A(assetReceiptLicenseId, productId,
				serverId, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByARLI_PI_SI_A(assetReceiptLicenseId,
				productId, serverId, active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByARLI_PI_SI_A_PrevAndNext(long licenseKeyId,
		long assetReceiptLicenseId, String productId, String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLI_PI_SI_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, productId, serverId, active,
					orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLI_PI_SI_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, productId, serverId, active,
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

	protected LicenseKey getByARLI_PI_SI_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long assetReceiptLicenseId, String productId,
		String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ASSETRECEIPTLICENSEID_2);

		boolean bindProductId = false;

		if (productId == null) {
			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_1);
		}
		else if (productId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_3);
		}
		else {
			bindProductId = true;

			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_2);
		}

		boolean bindServerId = false;

		if (serverId == null) {
			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_1);
		}
		else if (serverId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_3);
		}
		else {
			bindServerId = true;

			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_2);
		}

		query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetReceiptLicenseId);

		if (bindProductId) {
			qPos.add(productId);
		}

		if (bindServerId) {
			qPos.add(serverId);
		}

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 */
	@Override
	public void removeByARLI_PI_SI_A(long assetReceiptLicenseId,
		String productId, String serverId, boolean active) {
		for (LicenseKey licenseKey : findByARLI_PI_SI_A(assetReceiptLicenseId,
				productId, serverId, active, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByARLI_PI_SI_A(long assetReceiptLicenseId,
		String productId, String serverId, boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ARLI_PI_SI_A;

		Object[] finderArgs = new Object[] {
				assetReceiptLicenseId, productId, serverId, active
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ASSETRECEIPTLICENSEID_2);

			boolean bindProductId = false;

			if (productId == null) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_1);
			}
			else if (productId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId == null) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_1);
			}
			else if (serverId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_2);
			}

			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				if (bindProductId) {
					qPos.add(productId);
				}

				if (bindServerId) {
					qPos.add(serverId);
				}

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_ASSETRECEIPTLICENSEID_2 =
		"licenseKey.assetReceiptLicenseId = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_1 = "licenseKey.productId IS NULL AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_2 = "licenseKey.productId = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_3 = "(licenseKey.productId IS NULL OR licenseKey.productId = '') AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_1 = "licenseKey.serverId IS NULL AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_2 = "licenseKey.serverId = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_3 = "(licenseKey.serverId IS NULL OR licenseKey.serverId = '') AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_ACTIVE_2 = "licenseKey.active = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_LET_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_LET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_LET_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_LET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			},
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.LICENSEENTRYTYPE_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OEI_LET_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_LET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active) {
		return findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		int start, int end) {
		return findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_LET_C_A;
			finderArgs = new Object[] {
					offeringEntryId, licenseEntryType, complimentary, active
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_LET_C_A;
			finderArgs = new Object[] {
					offeringEntryId, licenseEntryType, complimentary, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
							!Objects.equals(licenseEntryType,
								licenseKey.getLicenseEntryType()) ||
							(complimentary != licenseKey.getComplimentary()) ||
							(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2);

			boolean bindLicenseEntryType = false;

			if (licenseEntryType == null) {
				query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_1);
			}
			else if (licenseEntryType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2);
			}

			query.append(_FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				if (bindLicenseEntryType) {
					qPos.add(licenseEntryType);
				}

				qPos.add(complimentary);

				qPos.add(active);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_LET_C_A_First(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_LET_C_A_First(offeringEntryId,
				licenseEntryType, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", licenseEntryType=");
		msg.append(licenseEntryType);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_LET_C_A_First(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByOEI_LET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_LET_C_A_Last(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_LET_C_A_Last(offeringEntryId,
				licenseEntryType, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", licenseEntryType=");
		msg.append(licenseEntryType);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_LET_C_A_Last(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByOEI_LET_C_A(offeringEntryId, licenseEntryType,
				complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOEI_LET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByOEI_LET_C_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_LET_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, licenseEntryType, complimentary, active,
					orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_LET_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, licenseEntryType, complimentary, active,
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

	protected LicenseKey getByOEI_LET_C_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2);

		boolean bindLicenseEntryType = false;

		if (licenseEntryType == null) {
			query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_1);
		}
		else if (licenseEntryType.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3);
		}
		else {
			bindLicenseEntryType = true;

			query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2);
		}

		query.append(_FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2);

		query.append(_FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		if (bindLicenseEntryType) {
			qPos.add(licenseEntryType);
		}

		qPos.add(complimentary);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByOEI_LET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active) {
		for (LicenseKey licenseKey : findByOEI_LET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOEI_LET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_OEI_LET_C_A;

		Object[] finderArgs = new Object[] {
				offeringEntryId, licenseEntryType, complimentary, active
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2);

			boolean bindLicenseEntryType = false;

			if (licenseEntryType == null) {
				query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_1);
			}
			else if (licenseEntryType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2);
			}

			query.append(_FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				if (bindLicenseEntryType) {
					qPos.add(licenseEntryType);
				}

				qPos.add(complimentary);

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2 = "licenseKey.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_1 = "licenseKey.licenseEntryType IS NULL AND ";
	private static final String _FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2 = "licenseKey.licenseEntryType = ? AND ";
	private static final String _FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3 = "(licenseKey.licenseEntryType IS NULL OR licenseKey.licenseEntryType = '') AND ";
	private static final String _FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2 = "licenseKey.complimentary = ? AND ";
	private static final String _FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2 = "licenseKey.active = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_NOTLET_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_NotLET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_NOTLET_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByOEI_NotLET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_NotLET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active) {
		return findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_NotLET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		int start, int end) {
		return findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_NotLET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_NotLET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_NOTLET_C_A;
		finderArgs = new Object[] {
				offeringEntryId, licenseEntryType, complimentary, active,
				
				start, end, orderByComparator
			};

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
							Objects.equals(licenseEntryType,
								licenseKey.getLicenseEntryType()) ||
							(complimentary != licenseKey.getComplimentary()) ||
							(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2);

			boolean bindLicenseEntryType = false;

			if (licenseEntryType == null) {
				query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_1);
			}
			else if (licenseEntryType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2);
			}

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				if (bindLicenseEntryType) {
					qPos.add(licenseEntryType);
				}

				qPos.add(complimentary);

				qPos.add(active);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_NotLET_C_A_First(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_NotLET_C_A_First(offeringEntryId,
				licenseEntryType, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", licenseEntryType=");
		msg.append(licenseEntryType);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_NotLET_C_A_First(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		List<LicenseKey> list = findByOEI_NotLET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_NotLET_C_A_Last(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByOEI_NotLET_C_A_Last(offeringEntryId,
				licenseEntryType, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", licenseEntryType=");
		msg.append(licenseEntryType);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_NotLET_C_A_Last(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		int count = countByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
				complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOEI_NotLET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByOEI_NotLET_C_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_NotLET_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, licenseEntryType, complimentary, active,
					orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_NotLET_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, licenseEntryType, complimentary, active,
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

	protected LicenseKey getByOEI_NotLET_C_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2);

		boolean bindLicenseEntryType = false;

		if (licenseEntryType == null) {
			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_1);
		}
		else if (licenseEntryType.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3);
		}
		else {
			bindLicenseEntryType = true;

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2);
		}

		query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2);

		query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		if (bindLicenseEntryType) {
			qPos.add(licenseEntryType);
		}

		qPos.add(complimentary);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByOEI_NotLET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active) {
		for (LicenseKey licenseKey : findByOEI_NotLET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOEI_NotLET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_NOTLET_C_A;

		Object[] finderArgs = new Object[] {
				offeringEntryId, licenseEntryType, complimentary, active
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2);

			boolean bindLicenseEntryType = false;

			if (licenseEntryType == null) {
				query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_1);
			}
			else if (licenseEntryType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2);
			}

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				if (bindLicenseEntryType) {
					qPos.add(licenseEntryType);
				}

				qPos.add(complimentary);

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2 = "licenseKey.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_1 =
		"licenseKey.licenseEntryType IS NOT NULL AND ";
	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2 =
		"licenseKey.licenseEntryType != ? AND ";
	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3 =
		"(licenseKey.licenseEntryType IS NULL OR licenseKey.licenseEntryType != '') AND ";
	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2 = "licenseKey.complimentary = ? AND ";
	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2 = "licenseKey.active = ?";

	public LicenseKeyPersistenceImpl() {
		setModelClass(LicenseKey.class);
	}

	/**
	 * Caches the license key in the entity cache if it is enabled.
	 *
	 * @param licenseKey the license key
	 */
	@Override
	public void cacheResult(LicenseKey licenseKey) {
		entityCache.putResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyImpl.class, licenseKey.getPrimaryKey(), licenseKey);

		licenseKey.resetOriginalValues();
	}

	/**
	 * Caches the license keies in the entity cache if it is enabled.
	 *
	 * @param licenseKeies the license keies
	 */
	@Override
	public void cacheResult(List<LicenseKey> licenseKeies) {
		for (LicenseKey licenseKey : licenseKeies) {
			if (entityCache.getResult(
						LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
						LicenseKeyImpl.class, licenseKey.getPrimaryKey()) == null) {
				cacheResult(licenseKey);
			}
			else {
				licenseKey.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all license keies.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LicenseKeyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the license key.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LicenseKey licenseKey) {
		entityCache.removeResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyImpl.class, licenseKey.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LicenseKey> licenseKeies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LicenseKey licenseKey : licenseKeies) {
			entityCache.removeResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeyImpl.class, licenseKey.getPrimaryKey());
		}
	}

	/**
	 * Creates a new license key with the primary key. Does not add the license key to the database.
	 *
	 * @param licenseKeyId the primary key for the new license key
	 * @return the new license key
	 */
	@Override
	public LicenseKey create(long licenseKeyId) {
		LicenseKey licenseKey = new LicenseKeyImpl();

		licenseKey.setNew(true);
		licenseKey.setPrimaryKey(licenseKeyId);

		return licenseKey;
	}

	/**
	 * Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key that was removed
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey remove(long licenseKeyId)
		throws NoSuchLicenseKeyException {
		return remove((Serializable)licenseKeyId);
	}

	/**
	 * Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the license key
	 * @return the license key that was removed
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey remove(Serializable primaryKey)
		throws NoSuchLicenseKeyException {
		Session session = null;

		try {
			session = openSession();

			LicenseKey licenseKey = (LicenseKey)session.get(LicenseKeyImpl.class,
					primaryKey);

			if (licenseKey == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLicenseKeyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(licenseKey);
		}
		catch (NoSuchLicenseKeyException nsee) {
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
	protected LicenseKey removeImpl(LicenseKey licenseKey) {
		licenseKey = toUnwrappedModel(licenseKey);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(licenseKey)) {
				licenseKey = (LicenseKey)session.get(LicenseKeyImpl.class,
						licenseKey.getPrimaryKeyObj());
			}

			if (licenseKey != null) {
				session.delete(licenseKey);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (licenseKey != null) {
			clearCache(licenseKey);
		}

		return licenseKey;
	}

	@Override
	public LicenseKey updateImpl(LicenseKey licenseKey) {
		licenseKey = toUnwrappedModel(licenseKey);

		boolean isNew = licenseKey.isNew();

		LicenseKeyModelImpl licenseKeyModelImpl = (LicenseKeyModelImpl)licenseKey;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (licenseKey.getCreateDate() == null)) {
			if (serviceContext == null) {
				licenseKey.setCreateDate(now);
			}
			else {
				licenseKey.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!licenseKeyModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				licenseKey.setModifiedDate(now);
			}
			else {
				licenseKey.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (licenseKey.isNew()) {
				session.save(licenseKey);

				licenseKey.setNew(false);
			}
			else {
				licenseKey = (LicenseKey)session.merge(licenseKey);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LicenseKeyModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					licenseKeyModelImpl.getLicenseKeySetId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LICENSEKEYSETID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LICENSEKEYSETID,
				args);

			args = new Object[] { licenseKeyModelImpl.getAccountEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			args = new Object[] { licenseKeyModelImpl.getOfferingEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID,
				args);

			args = new Object[] {
					licenseKeyModelImpl.getUserId(),
					licenseKeyModelImpl.getAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI,
				args);

			args = new Object[] {
					licenseKeyModelImpl.getAssetReceiptLicenseId(),
					licenseKeyModelImpl.getActive()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ARLI_A, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_A,
				args);

			args = new Object[] {
					licenseKeyModelImpl.getOfferingEntryId(),
					licenseKeyModelImpl.getClusterId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_CI, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI,
				args);

			args = new Object[] {
					licenseKeyModelImpl.getOrderEntryId(),
					licenseKeyModelImpl.getOwner()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_O, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_O,
				args);

			args = new Object[] {
					licenseKeyModelImpl.getProductId(),
					licenseKeyModelImpl.getServerId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PI_SI, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PI_SI,
				args);

			args = new Object[] {
					licenseKeyModelImpl.getAssetReceiptLicenseId(),
					licenseKeyModelImpl.getComplimentary(),
					licenseKeyModelImpl.getActive()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ARLI_C_A, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_C_A,
				args);

			args = new Object[] {
					licenseKeyModelImpl.getOfferingEntryId(),
					licenseKeyModelImpl.getClusterId(),
					licenseKeyModelImpl.getActive()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_CI_A, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI_A,
				args);

			args = new Object[] {
					licenseKeyModelImpl.getOfferingEntryId(),
					licenseKeyModelImpl.getComplimentary(),
					licenseKeyModelImpl.getActive()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_C_A, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_C_A,
				args);

			args = new Object[] {
					licenseKeyModelImpl.getProductEntryName(),
					licenseKeyModelImpl.getServerId(),
					licenseKeyModelImpl.getActive()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PEN_SI_A, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEN_SI_A,
				args);

			args = new Object[] {
					licenseKeyModelImpl.getAssetReceiptLicenseId(),
					licenseKeyModelImpl.getProductId(),
					licenseKeyModelImpl.getServerId(),
					licenseKeyModelImpl.getActive()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ARLI_PI_SI_A, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_PI_SI_A,
				args);

			args = new Object[] {
					licenseKeyModelImpl.getOfferingEntryId(),
					licenseKeyModelImpl.getLicenseEntryType(),
					licenseKeyModelImpl.getComplimentary(),
					licenseKeyModelImpl.getActive()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_LET_C_A, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_LET_C_A,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LICENSEKEYSETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalLicenseKeySetId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LICENSEKEYSETID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LICENSEKEYSETID,
					args);

				args = new Object[] { licenseKeyModelImpl.getLicenseKeySetId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LICENSEKEYSETID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LICENSEKEYSETID,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] { licenseKeyModelImpl.getAccountEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalOfferingEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID,
					args);

				args = new Object[] { licenseKeyModelImpl.getOfferingEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalUserId(),
						licenseKeyModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getUserId(),
						licenseKeyModelImpl.getAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalAssetReceiptLicenseId(),
						licenseKeyModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ARLI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_A,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getAssetReceiptLicenseId(),
						licenseKeyModelImpl.getActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ARLI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalOfferingEntryId(),
						licenseKeyModelImpl.getOriginalClusterId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_CI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getOfferingEntryId(),
						licenseKeyModelImpl.getClusterId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_CI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_O.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalOrderEntryId(),
						licenseKeyModelImpl.getOriginalOwner()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_O, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_O,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getOrderEntryId(),
						licenseKeyModelImpl.getOwner()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_O, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_O,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PI_SI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalProductId(),
						licenseKeyModelImpl.getOriginalServerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PI_SI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PI_SI,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getProductId(),
						licenseKeyModelImpl.getServerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PI_SI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PI_SI,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_C_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalAssetReceiptLicenseId(),
						licenseKeyModelImpl.getOriginalComplimentary(),
						licenseKeyModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ARLI_C_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_C_A,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getAssetReceiptLicenseId(),
						licenseKeyModelImpl.getComplimentary(),
						licenseKeyModelImpl.getActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ARLI_C_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_C_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalOfferingEntryId(),
						licenseKeyModelImpl.getOriginalClusterId(),
						licenseKeyModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_CI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI_A,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getOfferingEntryId(),
						licenseKeyModelImpl.getClusterId(),
						licenseKeyModelImpl.getActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_CI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_C_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalOfferingEntryId(),
						licenseKeyModelImpl.getOriginalComplimentary(),
						licenseKeyModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_C_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_C_A,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getOfferingEntryId(),
						licenseKeyModelImpl.getComplimentary(),
						licenseKeyModelImpl.getActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_C_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_C_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEN_SI_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalProductEntryName(),
						licenseKeyModelImpl.getOriginalServerId(),
						licenseKeyModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PEN_SI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEN_SI_A,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getProductEntryName(),
						licenseKeyModelImpl.getServerId(),
						licenseKeyModelImpl.getActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PEN_SI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEN_SI_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_PI_SI_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalAssetReceiptLicenseId(),
						licenseKeyModelImpl.getOriginalProductId(),
						licenseKeyModelImpl.getOriginalServerId(),
						licenseKeyModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ARLI_PI_SI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_PI_SI_A,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getAssetReceiptLicenseId(),
						licenseKeyModelImpl.getProductId(),
						licenseKeyModelImpl.getServerId(),
						licenseKeyModelImpl.getActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ARLI_PI_SI_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_PI_SI_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_LET_C_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalOfferingEntryId(),
						licenseKeyModelImpl.getOriginalLicenseEntryType(),
						licenseKeyModelImpl.getOriginalComplimentary(),
						licenseKeyModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_LET_C_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_LET_C_A,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getOfferingEntryId(),
						licenseKeyModelImpl.getLicenseEntryType(),
						licenseKeyModelImpl.getComplimentary(),
						licenseKeyModelImpl.getActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_OEI_LET_C_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_LET_C_A,
					args);
			}
		}

		entityCache.putResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyImpl.class, licenseKey.getPrimaryKey(), licenseKey, false);

		licenseKey.resetOriginalValues();

		return licenseKey;
	}

	protected LicenseKey toUnwrappedModel(LicenseKey licenseKey) {
		if (licenseKey instanceof LicenseKeyImpl) {
			return licenseKey;
		}

		LicenseKeyImpl licenseKeyImpl = new LicenseKeyImpl();

		licenseKeyImpl.setNew(licenseKey.isNew());
		licenseKeyImpl.setPrimaryKey(licenseKey.getPrimaryKey());

		licenseKeyImpl.setLicenseKeyId(licenseKey.getLicenseKeyId());
		licenseKeyImpl.setUserId(licenseKey.getUserId());
		licenseKeyImpl.setUserName(licenseKey.getUserName());
		licenseKeyImpl.setCreateDate(licenseKey.getCreateDate());
		licenseKeyImpl.setModifiedUserId(licenseKey.getModifiedUserId());
		licenseKeyImpl.setModifiedUserName(licenseKey.getModifiedUserName());
		licenseKeyImpl.setModifiedDate(licenseKey.getModifiedDate());
		licenseKeyImpl.setLicenseKeySetId(licenseKey.getLicenseKeySetId());
		licenseKeyImpl.setAssetReceiptLicenseId(licenseKey.getAssetReceiptLicenseId());
		licenseKeyImpl.setAccountEntryId(licenseKey.getAccountEntryId());
		licenseKeyImpl.setOrderEntryId(licenseKey.getOrderEntryId());
		licenseKeyImpl.setOfferingEntryId(licenseKey.getOfferingEntryId());
		licenseKeyImpl.setLicenseEntryId(licenseKey.getLicenseEntryId());
		licenseKeyImpl.setProductEntryId(licenseKey.getProductEntryId());
		licenseKeyImpl.setSupportResponseId(licenseKey.getSupportResponseId());
		licenseKeyImpl.setAccountEntryName(licenseKey.getAccountEntryName());
		licenseKeyImpl.setLicenseEntryName(licenseKey.getLicenseEntryName());
		licenseKeyImpl.setLicenseEntryType(licenseKey.getLicenseEntryType());
		licenseKeyImpl.setLicenseVersion(licenseKey.getLicenseVersion());
		licenseKeyImpl.setProductEntryName(licenseKey.getProductEntryName());
		licenseKeyImpl.setProductId(licenseKey.getProductId());
		licenseKeyImpl.setProductVersion(licenseKey.getProductVersion());
		licenseKeyImpl.setProductVersionLabel(licenseKey.getProductVersionLabel());
		licenseKeyImpl.setClusterId(licenseKey.getClusterId());
		licenseKeyImpl.setOwner(licenseKey.getOwner());
		licenseKeyImpl.setMaxServers(licenseKey.getMaxServers());
		licenseKeyImpl.setMaxConcurrentUsers(licenseKey.getMaxConcurrentUsers());
		licenseKeyImpl.setMaxUsers(licenseKey.getMaxUsers());
		licenseKeyImpl.setMaxHttpSessions(licenseKey.getMaxHttpSessions());
		licenseKeyImpl.setDescription(licenseKey.getDescription());
		licenseKeyImpl.setHostName(licenseKey.getHostName());
		licenseKeyImpl.setIpAddresses(licenseKey.getIpAddresses());
		licenseKeyImpl.setMacAddresses(licenseKey.getMacAddresses());
		licenseKeyImpl.setServerId(licenseKey.getServerId());
		licenseKeyImpl.setKey(licenseKey.getKey());
		licenseKeyImpl.setStartDate(licenseKey.getStartDate());
		licenseKeyImpl.setExpirationDate(licenseKey.getExpirationDate());
		licenseKeyImpl.setAdditionalInfo(licenseKey.getAdditionalInfo());
		licenseKeyImpl.setComplimentary(licenseKey.isComplimentary());
		licenseKeyImpl.setActive(licenseKey.isActive());

		return licenseKeyImpl;
	}

	/**
	 * Returns the license key with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key
	 * @return the license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLicenseKeyException {
		LicenseKey licenseKey = fetchByPrimaryKey(primaryKey);

		if (licenseKey == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLicenseKeyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return licenseKey;
	}

	/**
	 * Returns the license key with the primary key or throws a {@link NoSuchLicenseKeyException} if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey findByPrimaryKey(long licenseKeyId)
		throws NoSuchLicenseKeyException {
		return findByPrimaryKey((Serializable)licenseKeyId);
	}

	/**
	 * Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key
	 * @return the license key, or <code>null</code> if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LicenseKey licenseKey = (LicenseKey)serializable;

		if (licenseKey == null) {
			Session session = null;

			try {
				session = openSession();

				licenseKey = (LicenseKey)session.get(LicenseKeyImpl.class,
						primaryKey);

				if (licenseKey != null) {
					cacheResult(licenseKey);
				}
				else {
					entityCache.putResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
						LicenseKeyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return licenseKey;
	}

	/**
	 * Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key, or <code>null</code> if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey fetchByPrimaryKey(long licenseKeyId) {
		return fetchByPrimaryKey((Serializable)licenseKeyId);
	}

	@Override
	public Map<Serializable, LicenseKey> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LicenseKey> map = new HashMap<Serializable, LicenseKey>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LicenseKey licenseKey = fetchByPrimaryKey(primaryKey);

			if (licenseKey != null) {
				map.put(primaryKey, licenseKey);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeyImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LicenseKey)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LICENSEKEY_WHERE_PKS_IN);

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

			for (LicenseKey licenseKey : (List<LicenseKey>)q.list()) {
				map.put(licenseKey.getPrimaryKeyObj(), licenseKey);

				cacheResult(licenseKey);

				uncachedPrimaryKeys.remove(licenseKey.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeyImpl.class, primaryKey, nullModel);
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
	 * Returns all the license keies.
	 *
	 * @return the license keies
	 */
	@Override
	public List<LicenseKey> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of license keies
	 */
	@Override
	public List<LicenseKey> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license keies
	 */
	@Override
	public List<LicenseKey> findAll(int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of license keies
	 */
	@Override
	public List<LicenseKey> findAll(int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
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

		List<LicenseKey> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseKey>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LICENSEKEY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LICENSEKEY;

				if (pagination) {
					sql = sql.concat(LicenseKeyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the license keies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LicenseKey licenseKey : findAll()) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LICENSEKEY);

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
		return LicenseKeyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the license key persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LicenseKeyImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_LICENSEKEY = "SELECT licenseKey FROM LicenseKey licenseKey";
	private static final String _SQL_SELECT_LICENSEKEY_WHERE_PKS_IN = "SELECT licenseKey FROM LicenseKey licenseKey WHERE licenseKeyId IN (";
	private static final String _SQL_SELECT_LICENSEKEY_WHERE = "SELECT licenseKey FROM LicenseKey licenseKey WHERE ";
	private static final String _SQL_COUNT_LICENSEKEY = "SELECT COUNT(licenseKey) FROM LicenseKey licenseKey";
	private static final String _SQL_COUNT_LICENSEKEY_WHERE = "SELECT COUNT(licenseKey) FROM LicenseKey licenseKey WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "licenseKey.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LicenseKey exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LicenseKey exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LicenseKeyPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"key", "active"
			});
}
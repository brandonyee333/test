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

import com.liferay.osb.exception.NoSuchLicenseEntryException;
import com.liferay.osb.model.LicenseEntry;
import com.liferay.osb.model.impl.LicenseEntryImpl;
import com.liferay.osb.model.impl.LicenseEntryModelImpl;
import com.liferay.osb.service.persistence.LicenseEntryPersistence;

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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the license entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryPersistence
 * @see com.liferay.osb.service.persistence.LicenseEntryUtil
 * @generated
 */
@ProviderType
public class LicenseEntryPersistenceImpl extends BasePersistenceImpl<LicenseEntry>
	implements LicenseEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LicenseEntryUtil} to access the license entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LicenseEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRODUCTENTRYID =
		new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID =
		new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProductEntryId",
			new String[] { Long.class.getName() },
			LicenseEntryModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			LicenseEntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRODUCTENTRYID = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the license entries where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching license entries
	 */
	@Override
	public List<LicenseEntry> findByProductEntryId(long productEntryId) {
		return findByProductEntryId(productEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license entries where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByProductEntryId(long productEntryId,
		int start, int end) {
		return findByProductEntryId(productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByProductEntryId(long productEntryId,
		int start, int end, OrderByComparator<LicenseEntry> orderByComparator) {
		return findByProductEntryId(productEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByProductEntryId(long productEntryId,
		int start, int end, OrderByComparator<LicenseEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID;
			finderArgs = new Object[] { productEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRODUCTENTRYID;
			finderArgs = new Object[] {
					productEntryId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseEntry licenseEntry : list) {
					if ((productEntryId != licenseEntry.getProductEntryId())) {
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

			query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				if (!pagination) {
					list = (List<LicenseEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByProductEntryId_First(long productEntryId,
		OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {
		LicenseEntry licenseEntry = fetchByProductEntryId_First(productEntryId,
				orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseEntryException(msg.toString());
	}

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByProductEntryId_First(long productEntryId,
		OrderByComparator<LicenseEntry> orderByComparator) {
		List<LicenseEntry> list = findByProductEntryId(productEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByProductEntryId_Last(long productEntryId,
		OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {
		LicenseEntry licenseEntry = fetchByProductEntryId_Last(productEntryId,
				orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseEntryException(msg.toString());
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByProductEntryId_Last(long productEntryId,
		OrderByComparator<LicenseEntry> orderByComparator) {
		int count = countByProductEntryId(productEntryId);

		if (count == 0) {
			return null;
		}

		List<LicenseEntry> list = findByProductEntryId(productEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry[] findByProductEntryId_PrevAndNext(
		long licenseEntryId, long productEntryId,
		OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {
		LicenseEntry licenseEntry = findByPrimaryKey(licenseEntryId);

		Session session = null;

		try {
			session = openSession();

			LicenseEntry[] array = new LicenseEntryImpl[3];

			array[0] = getByProductEntryId_PrevAndNext(session, licenseEntry,
					productEntryId, orderByComparator, true);

			array[1] = licenseEntry;

			array[2] = getByProductEntryId_PrevAndNext(session, licenseEntry,
					productEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseEntry getByProductEntryId_PrevAndNext(Session session,
		LicenseEntry licenseEntry, long productEntryId,
		OrderByComparator<LicenseEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

		query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

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
			query.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(productEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license entries where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 */
	@Override
	public void removeByProductEntryId(long productEntryId) {
		for (LicenseEntry licenseEntry : findByProductEntryId(productEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseEntry);
		}
	}

	/**
	 * Returns the number of license entries where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching license entries
	 */
	@Override
	public int countByProductEntryId(long productEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRODUCTENTRYID;

		Object[] finderArgs = new Object[] { productEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

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

	private static final String _FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2 = "licenseEntry.productEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_PEI_T = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByPEI_T",
			new String[] { Long.class.getName(), String.class.getName() },
			LicenseEntryModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			LicenseEntryModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PEI_T = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPEI_T",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or throws a {@link NoSuchLicenseEntryException} if it could not be found.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByPEI_T(long productEntryId, String type)
		throws NoSuchLicenseEntryException {
		LicenseEntry licenseEntry = fetchByPEI_T(productEntryId, type);

		if (licenseEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("productEntryId=");
			msg.append(productEntryId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLicenseEntryException(msg.toString());
		}

		return licenseEntry;
	}

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByPEI_T(long productEntryId, String type) {
		return fetchByPEI_T(productEntryId, type, true);
	}

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByPEI_T(long productEntryId, String type,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { productEntryId, type };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_PEI_T,
					finderArgs, this);
		}

		if (result instanceof LicenseEntry) {
			LicenseEntry licenseEntry = (LicenseEntry)result;

			if ((productEntryId != licenseEntry.getProductEntryId()) ||
					!Objects.equals(type, licenseEntry.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PEI_T_PRODUCTENTRYID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_PEI_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PEI_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_PEI_T_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				if (bindType) {
					qPos.add(type);
				}

				List<LicenseEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_PEI_T,
						finderArgs, list);
				}
				else {
					LicenseEntry licenseEntry = list.get(0);

					result = licenseEntry;

					cacheResult(licenseEntry);

					if ((licenseEntry.getProductEntryId() != productEntryId) ||
							(licenseEntry.getType() == null) ||
							!licenseEntry.getType().equals(type)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_PEI_T,
							finderArgs, licenseEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_PEI_T, finderArgs);

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
			return (LicenseEntry)result;
		}
	}

	/**
	 * Removes the license entry where productEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the license entry that was removed
	 */
	@Override
	public LicenseEntry removeByPEI_T(long productEntryId, String type)
		throws NoSuchLicenseEntryException {
		LicenseEntry licenseEntry = findByPEI_T(productEntryId, type);

		return remove(licenseEntry);
	}

	/**
	 * Returns the number of license entries where productEntryId = &#63; and type = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the number of matching license entries
	 */
	@Override
	public int countByPEI_T(long productEntryId, String type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PEI_T;

		Object[] finderArgs = new Object[] { productEntryId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PEI_T_PRODUCTENTRYID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_PEI_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PEI_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_PEI_T_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				if (bindType) {
					qPos.add(type);
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

	private static final String _FINDER_COLUMN_PEI_T_PRODUCTENTRYID_2 = "licenseEntry.productEntryId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_T_TYPE_1 = "licenseEntry.type IS NULL";
	private static final String _FINDER_COLUMN_PEI_T_TYPE_2 = "licenseEntry.type = ?";
	private static final String _FINDER_COLUMN_PEI_T_TYPE_3 = "(licenseEntry.type IS NULL OR licenseEntry.type = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_PV = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPEI_PV",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_PV = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByPEI_PV",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @return the matching license entries
	 */
	@Override
	public List<LicenseEntry> findByPEI_PV(long productEntryId,
		int portalVersionMin) {
		return findByPEI_PV(productEntryId, portalVersionMin,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByPEI_PV(long productEntryId,
		int portalVersionMin, int start, int end) {
		return findByPEI_PV(productEntryId, portalVersionMin, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByPEI_PV(long productEntryId,
		int portalVersionMin, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {
		return findByPEI_PV(productEntryId, portalVersionMin, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByPEI_PV(long productEntryId,
		int portalVersionMin, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_PV;
		finderArgs = new Object[] {
				productEntryId, portalVersionMin,
				
				start, end, orderByComparator
			};

		List<LicenseEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseEntry licenseEntry : list) {
					if ((productEntryId != licenseEntry.getProductEntryId()) ||
							(portalVersionMin < licenseEntry.getPortalVersionMin())) {
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

			query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PEI_PV_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_PV_PORTALVERSIONMIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(portalVersionMin);

				if (!pagination) {
					list = (List<LicenseEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByPEI_PV_First(long productEntryId,
		int portalVersionMin, OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {
		LicenseEntry licenseEntry = fetchByPEI_PV_First(productEntryId,
				portalVersionMin, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(", portalVersionMin=");
		msg.append(portalVersionMin);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseEntryException(msg.toString());
	}

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByPEI_PV_First(long productEntryId,
		int portalVersionMin, OrderByComparator<LicenseEntry> orderByComparator) {
		List<LicenseEntry> list = findByPEI_PV(productEntryId,
				portalVersionMin, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByPEI_PV_Last(long productEntryId,
		int portalVersionMin, OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {
		LicenseEntry licenseEntry = fetchByPEI_PV_Last(productEntryId,
				portalVersionMin, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(", portalVersionMin=");
		msg.append(portalVersionMin);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseEntryException(msg.toString());
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByPEI_PV_Last(long productEntryId,
		int portalVersionMin, OrderByComparator<LicenseEntry> orderByComparator) {
		int count = countByPEI_PV(productEntryId, portalVersionMin);

		if (count == 0) {
			return null;
		}

		List<LicenseEntry> list = findByPEI_PV(productEntryId,
				portalVersionMin, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry[] findByPEI_PV_PrevAndNext(long licenseEntryId,
		long productEntryId, int portalVersionMin,
		OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {
		LicenseEntry licenseEntry = findByPrimaryKey(licenseEntryId);

		Session session = null;

		try {
			session = openSession();

			LicenseEntry[] array = new LicenseEntryImpl[3];

			array[0] = getByPEI_PV_PrevAndNext(session, licenseEntry,
					productEntryId, portalVersionMin, orderByComparator, true);

			array[1] = licenseEntry;

			array[2] = getByPEI_PV_PrevAndNext(session, licenseEntry,
					productEntryId, portalVersionMin, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseEntry getByPEI_PV_PrevAndNext(Session session,
		LicenseEntry licenseEntry, long productEntryId, int portalVersionMin,
		OrderByComparator<LicenseEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

		query.append(_FINDER_COLUMN_PEI_PV_PRODUCTENTRYID_2);

		query.append(_FINDER_COLUMN_PEI_PV_PORTALVERSIONMIN_2);

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
			query.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(productEntryId);

		qPos.add(portalVersionMin);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 */
	@Override
	public void removeByPEI_PV(long productEntryId, int portalVersionMin) {
		for (LicenseEntry licenseEntry : findByPEI_PV(productEntryId,
				portalVersionMin, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(licenseEntry);
		}
	}

	/**
	 * Returns the number of license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @return the number of matching license entries
	 */
	@Override
	public int countByPEI_PV(long productEntryId, int portalVersionMin) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_PV;

		Object[] finderArgs = new Object[] { productEntryId, portalVersionMin };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PEI_PV_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_PV_PORTALVERSIONMIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(portalVersionMin);

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

	private static final String _FINDER_COLUMN_PEI_PV_PRODUCTENTRYID_2 = "licenseEntry.productEntryId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_PV_PORTALVERSIONMIN_2 = "licenseEntry.portalVersionMin <= ?";

	public LicenseEntryPersistenceImpl() {
		setModelClass(LicenseEntry.class);
	}

	/**
	 * Caches the license entry in the entity cache if it is enabled.
	 *
	 * @param licenseEntry the license entry
	 */
	@Override
	public void cacheResult(LicenseEntry licenseEntry) {
		entityCache.putResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryImpl.class, licenseEntry.getPrimaryKey(), licenseEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_PEI_T,
			new Object[] {
				licenseEntry.getProductEntryId(), licenseEntry.getType()
			}, licenseEntry);

		licenseEntry.resetOriginalValues();
	}

	/**
	 * Caches the license entries in the entity cache if it is enabled.
	 *
	 * @param licenseEntries the license entries
	 */
	@Override
	public void cacheResult(List<LicenseEntry> licenseEntries) {
		for (LicenseEntry licenseEntry : licenseEntries) {
			if (entityCache.getResult(
						LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
						LicenseEntryImpl.class, licenseEntry.getPrimaryKey()) == null) {
				cacheResult(licenseEntry);
			}
			else {
				licenseEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all license entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LicenseEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the license entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LicenseEntry licenseEntry) {
		entityCache.removeResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryImpl.class, licenseEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LicenseEntryModelImpl)licenseEntry, true);
	}

	@Override
	public void clearCache(List<LicenseEntry> licenseEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LicenseEntry licenseEntry : licenseEntries) {
			entityCache.removeResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
				LicenseEntryImpl.class, licenseEntry.getPrimaryKey());

			clearUniqueFindersCache((LicenseEntryModelImpl)licenseEntry, true);
		}
	}

	protected void cacheUniqueFindersCache(
		LicenseEntryModelImpl licenseEntryModelImpl) {
		Object[] args = new Object[] {
				licenseEntryModelImpl.getProductEntryId(),
				licenseEntryModelImpl.getType()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_PEI_T, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_PEI_T, args,
			licenseEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LicenseEntryModelImpl licenseEntryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					licenseEntryModelImpl.getProductEntryId(),
					licenseEntryModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PEI_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PEI_T, args);
		}

		if ((licenseEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PEI_T.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					licenseEntryModelImpl.getOriginalProductEntryId(),
					licenseEntryModelImpl.getOriginalType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PEI_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PEI_T, args);
		}
	}

	/**
	 * Creates a new license entry with the primary key. Does not add the license entry to the database.
	 *
	 * @param licenseEntryId the primary key for the new license entry
	 * @return the new license entry
	 */
	@Override
	public LicenseEntry create(long licenseEntryId) {
		LicenseEntry licenseEntry = new LicenseEntryImpl();

		licenseEntry.setNew(true);
		licenseEntry.setPrimaryKey(licenseEntryId);

		return licenseEntry;
	}

	/**
	 * Removes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry remove(long licenseEntryId)
		throws NoSuchLicenseEntryException {
		return remove((Serializable)licenseEntryId);
	}

	/**
	 * Removes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry remove(Serializable primaryKey)
		throws NoSuchLicenseEntryException {
		Session session = null;

		try {
			session = openSession();

			LicenseEntry licenseEntry = (LicenseEntry)session.get(LicenseEntryImpl.class,
					primaryKey);

			if (licenseEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLicenseEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(licenseEntry);
		}
		catch (NoSuchLicenseEntryException nsee) {
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
	protected LicenseEntry removeImpl(LicenseEntry licenseEntry) {
		licenseEntry = toUnwrappedModel(licenseEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(licenseEntry)) {
				licenseEntry = (LicenseEntry)session.get(LicenseEntryImpl.class,
						licenseEntry.getPrimaryKeyObj());
			}

			if (licenseEntry != null) {
				session.delete(licenseEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (licenseEntry != null) {
			clearCache(licenseEntry);
		}

		return licenseEntry;
	}

	@Override
	public LicenseEntry updateImpl(LicenseEntry licenseEntry) {
		licenseEntry = toUnwrappedModel(licenseEntry);

		boolean isNew = licenseEntry.isNew();

		LicenseEntryModelImpl licenseEntryModelImpl = (LicenseEntryModelImpl)licenseEntry;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (licenseEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				licenseEntry.setCreateDate(now);
			}
			else {
				licenseEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!licenseEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				licenseEntry.setModifiedDate(now);
			}
			else {
				licenseEntry.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (licenseEntry.isNew()) {
				session.save(licenseEntry);

				licenseEntry.setNew(false);
			}
			else {
				licenseEntry = (LicenseEntry)session.merge(licenseEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LicenseEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					licenseEntryModelImpl.getProductEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((licenseEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseEntryModelImpl.getOriginalProductEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
					args);

				args = new Object[] { licenseEntryModelImpl.getProductEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
					args);
			}
		}

		entityCache.putResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryImpl.class, licenseEntry.getPrimaryKey(), licenseEntry,
			false);

		clearUniqueFindersCache(licenseEntryModelImpl, false);
		cacheUniqueFindersCache(licenseEntryModelImpl);

		licenseEntry.resetOriginalValues();

		return licenseEntry;
	}

	protected LicenseEntry toUnwrappedModel(LicenseEntry licenseEntry) {
		if (licenseEntry instanceof LicenseEntryImpl) {
			return licenseEntry;
		}

		LicenseEntryImpl licenseEntryImpl = new LicenseEntryImpl();

		licenseEntryImpl.setNew(licenseEntry.isNew());
		licenseEntryImpl.setPrimaryKey(licenseEntry.getPrimaryKey());

		licenseEntryImpl.setLicenseEntryId(licenseEntry.getLicenseEntryId());
		licenseEntryImpl.setUserId(licenseEntry.getUserId());
		licenseEntryImpl.setUserName(licenseEntry.getUserName());
		licenseEntryImpl.setCreateDate(licenseEntry.getCreateDate());
		licenseEntryImpl.setModifiedDate(licenseEntry.getModifiedDate());
		licenseEntryImpl.setProductEntryId(licenseEntry.getProductEntryId());
		licenseEntryImpl.setName(licenseEntry.getName());
		licenseEntryImpl.setType(licenseEntry.getType());
		licenseEntryImpl.setPortalVersionMin(licenseEntry.getPortalVersionMin());
		licenseEntryImpl.setPortalVersionMax(licenseEntry.getPortalVersionMax());

		return licenseEntryImpl;
	}

	/**
	 * Returns the license entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the license entry
	 * @return the license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLicenseEntryException {
		LicenseEntry licenseEntry = fetchByPrimaryKey(primaryKey);

		if (licenseEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLicenseEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return licenseEntry;
	}

	/**
	 * Returns the license entry with the primary key or throws a {@link NoSuchLicenseEntryException} if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry findByPrimaryKey(long licenseEntryId)
		throws NoSuchLicenseEntryException {
		return findByPrimaryKey((Serializable)licenseEntryId);
	}

	/**
	 * Returns the license entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license entry
	 * @return the license entry, or <code>null</code> if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
				LicenseEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LicenseEntry licenseEntry = (LicenseEntry)serializable;

		if (licenseEntry == null) {
			Session session = null;

			try {
				session = openSession();

				licenseEntry = (LicenseEntry)session.get(LicenseEntryImpl.class,
						primaryKey);

				if (licenseEntry != null) {
					cacheResult(licenseEntry);
				}
				else {
					entityCache.putResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
						LicenseEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
					LicenseEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return licenseEntry;
	}

	/**
	 * Returns the license entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry, or <code>null</code> if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry fetchByPrimaryKey(long licenseEntryId) {
		return fetchByPrimaryKey((Serializable)licenseEntryId);
	}

	@Override
	public Map<Serializable, LicenseEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LicenseEntry> map = new HashMap<Serializable, LicenseEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LicenseEntry licenseEntry = fetchByPrimaryKey(primaryKey);

			if (licenseEntry != null) {
				map.put(primaryKey, licenseEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
					LicenseEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LicenseEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LICENSEENTRY_WHERE_PKS_IN);

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

			for (LicenseEntry licenseEntry : (List<LicenseEntry>)q.list()) {
				map.put(licenseEntry.getPrimaryKeyObj(), licenseEntry);

				cacheResult(licenseEntry);

				uncachedPrimaryKeys.remove(licenseEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
					LicenseEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the license entries.
	 *
	 * @return the license entries
	 */
	@Override
	public List<LicenseEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of license entries
	 */
	@Override
	public List<LicenseEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license entries
	 */
	@Override
	public List<LicenseEntry> findAll(int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of license entries
	 */
	@Override
	public List<LicenseEntry> findAll(int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
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

		List<LicenseEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LicenseEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LICENSEENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LICENSEENTRY;

				if (pagination) {
					sql = sql.concat(LicenseEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LicenseEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LicenseEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the license entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LicenseEntry licenseEntry : findAll()) {
			remove(licenseEntry);
		}
	}

	/**
	 * Returns the number of license entries.
	 *
	 * @return the number of license entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LICENSEENTRY);

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
		return LicenseEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the license entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LicenseEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_LICENSEENTRY = "SELECT licenseEntry FROM LicenseEntry licenseEntry";
	private static final String _SQL_SELECT_LICENSEENTRY_WHERE_PKS_IN = "SELECT licenseEntry FROM LicenseEntry licenseEntry WHERE licenseEntryId IN (";
	private static final String _SQL_SELECT_LICENSEENTRY_WHERE = "SELECT licenseEntry FROM LicenseEntry licenseEntry WHERE ";
	private static final String _SQL_COUNT_LICENSEENTRY = "SELECT COUNT(licenseEntry) FROM LicenseEntry licenseEntry";
	private static final String _SQL_COUNT_LICENSEENTRY_WHERE = "SELECT COUNT(licenseEntry) FROM LicenseEntry licenseEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "licenseEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LicenseEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LicenseEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LicenseEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
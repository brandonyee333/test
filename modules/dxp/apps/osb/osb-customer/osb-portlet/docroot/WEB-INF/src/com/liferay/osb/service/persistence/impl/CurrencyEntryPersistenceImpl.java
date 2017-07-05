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

import com.liferay.osb.exception.NoSuchCurrencyEntryException;
import com.liferay.osb.model.CurrencyEntry;
import com.liferay.osb.model.impl.CurrencyEntryImpl;
import com.liferay.osb.model.impl.CurrencyEntryModelImpl;
import com.liferay.osb.service.persistence.CurrencyEntryPersistence;

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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the currency entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CurrencyEntryPersistence
 * @see com.liferay.osb.service.persistence.CurrencyEntryUtil
 * @generated
 */
@ProviderType
public class CurrencyEntryPersistenceImpl extends BasePersistenceImpl<CurrencyEntry>
	implements CurrencyEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CurrencyEntryUtil} to access the currency entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CurrencyEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED,
			CurrencyEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED,
			CurrencyEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_CURRENCYCODE = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED,
			CurrencyEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCurrencyCode", new String[] { String.class.getName() },
			CurrencyEntryModelImpl.CURRENCYCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CURRENCYCODE = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCurrencyCode",
			new String[] { String.class.getName() });

	/**
	 * Returns the currency entry where currencyCode = &#63; or throws a {@link NoSuchCurrencyEntryException} if it could not be found.
	 *
	 * @param currencyCode the currency code
	 * @return the matching currency entry
	 * @throws NoSuchCurrencyEntryException if a matching currency entry could not be found
	 */
	@Override
	public CurrencyEntry findByCurrencyCode(String currencyCode)
		throws NoSuchCurrencyEntryException {
		CurrencyEntry currencyEntry = fetchByCurrencyCode(currencyCode);

		if (currencyEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("currencyCode=");
			msg.append(currencyCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCurrencyEntryException(msg.toString());
		}

		return currencyEntry;
	}

	/**
	 * Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param currencyCode the currency code
	 * @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	 */
	@Override
	public CurrencyEntry fetchByCurrencyCode(String currencyCode) {
		return fetchByCurrencyCode(currencyCode, true);
	}

	/**
	 * Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param currencyCode the currency code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	 */
	@Override
	public CurrencyEntry fetchByCurrencyCode(String currencyCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { currencyCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CURRENCYCODE,
					finderArgs, this);
		}

		if (result instanceof CurrencyEntry) {
			CurrencyEntry currencyEntry = (CurrencyEntry)result;

			if (!Objects.equals(currencyCode, currencyEntry.getCurrencyCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CURRENCYENTRY_WHERE);

			boolean bindCurrencyCode = false;

			if (currencyCode == null) {
				query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_1);
			}
			else if (currencyCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_3);
			}
			else {
				bindCurrencyCode = true;

				query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCurrencyCode) {
					qPos.add(currencyCode);
				}

				List<CurrencyEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CURRENCYCODE,
						finderArgs, list);
				}
				else {
					CurrencyEntry currencyEntry = list.get(0);

					result = currencyEntry;

					cacheResult(currencyEntry);

					if ((currencyEntry.getCurrencyCode() == null) ||
							!currencyEntry.getCurrencyCode().equals(currencyCode)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CURRENCYCODE,
							finderArgs, currencyEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CURRENCYCODE,
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
			return (CurrencyEntry)result;
		}
	}

	/**
	 * Removes the currency entry where currencyCode = &#63; from the database.
	 *
	 * @param currencyCode the currency code
	 * @return the currency entry that was removed
	 */
	@Override
	public CurrencyEntry removeByCurrencyCode(String currencyCode)
		throws NoSuchCurrencyEntryException {
		CurrencyEntry currencyEntry = findByCurrencyCode(currencyCode);

		return remove(currencyEntry);
	}

	/**
	 * Returns the number of currency entries where currencyCode = &#63;.
	 *
	 * @param currencyCode the currency code
	 * @return the number of matching currency entries
	 */
	@Override
	public int countByCurrencyCode(String currencyCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CURRENCYCODE;

		Object[] finderArgs = new Object[] { currencyCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CURRENCYENTRY_WHERE);

			boolean bindCurrencyCode = false;

			if (currencyCode == null) {
				query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_1);
			}
			else if (currencyCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_3);
			}
			else {
				bindCurrencyCode = true;

				query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCurrencyCode) {
					qPos.add(currencyCode);
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

	private static final String _FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_1 = "currencyEntry.currencyCode IS NULL";
	private static final String _FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_2 = "currencyEntry.currencyCode = ?";
	private static final String _FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_3 = "(currencyEntry.currencyCode IS NULL OR currencyEntry.currencyCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETPLACEENABLED =
		new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED,
			CurrencyEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByMarketplaceEnabled",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACEENABLED =
		new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED,
			CurrencyEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByMarketplaceEnabled",
			new String[] { Boolean.class.getName() },
			CurrencyEntryModelImpl.MARKETPLACEENABLED_COLUMN_BITMASK |
			CurrencyEntryModelImpl.CURRENCYCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MARKETPLACEENABLED = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByMarketplaceEnabled",
			new String[] { Boolean.class.getName() });

	/**
	 * Returns all the currency entries where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @return the matching currency entries
	 */
	@Override
	public List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled) {
		return findByMarketplaceEnabled(marketplaceEnabled, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the currency entries where marketplaceEnabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param start the lower bound of the range of currency entries
	 * @param end the upper bound of the range of currency entries (not inclusive)
	 * @return the range of matching currency entries
	 */
	@Override
	public List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end) {
		return findByMarketplaceEnabled(marketplaceEnabled, start, end, null);
	}

	/**
	 * Returns an ordered range of all the currency entries where marketplaceEnabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param start the lower bound of the range of currency entries
	 * @param end the upper bound of the range of currency entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching currency entries
	 */
	@Override
	public List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end,
		OrderByComparator<CurrencyEntry> orderByComparator) {
		return findByMarketplaceEnabled(marketplaceEnabled, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the currency entries where marketplaceEnabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param start the lower bound of the range of currency entries
	 * @param end the upper bound of the range of currency entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching currency entries
	 */
	@Override
	public List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end,
		OrderByComparator<CurrencyEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACEENABLED;
			finderArgs = new Object[] { marketplaceEnabled };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETPLACEENABLED;
			finderArgs = new Object[] {
					marketplaceEnabled,
					
					start, end, orderByComparator
				};
		}

		List<CurrencyEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CurrencyEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CurrencyEntry currencyEntry : list) {
					if ((marketplaceEnabled != currencyEntry.getMarketplaceEnabled())) {
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

			query.append(_SQL_SELECT_CURRENCYENTRY_WHERE);

			query.append(_FINDER_COLUMN_MARKETPLACEENABLED_MARKETPLACEENABLED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CurrencyEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(marketplaceEnabled);

				if (!pagination) {
					list = (List<CurrencyEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CurrencyEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first currency entry in the ordered set where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching currency entry
	 * @throws NoSuchCurrencyEntryException if a matching currency entry could not be found
	 */
	@Override
	public CurrencyEntry findByMarketplaceEnabled_First(
		boolean marketplaceEnabled,
		OrderByComparator<CurrencyEntry> orderByComparator)
		throws NoSuchCurrencyEntryException {
		CurrencyEntry currencyEntry = fetchByMarketplaceEnabled_First(marketplaceEnabled,
				orderByComparator);

		if (currencyEntry != null) {
			return currencyEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("marketplaceEnabled=");
		msg.append(marketplaceEnabled);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCurrencyEntryException(msg.toString());
	}

	/**
	 * Returns the first currency entry in the ordered set where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching currency entry, or <code>null</code> if a matching currency entry could not be found
	 */
	@Override
	public CurrencyEntry fetchByMarketplaceEnabled_First(
		boolean marketplaceEnabled,
		OrderByComparator<CurrencyEntry> orderByComparator) {
		List<CurrencyEntry> list = findByMarketplaceEnabled(marketplaceEnabled,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last currency entry in the ordered set where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching currency entry
	 * @throws NoSuchCurrencyEntryException if a matching currency entry could not be found
	 */
	@Override
	public CurrencyEntry findByMarketplaceEnabled_Last(
		boolean marketplaceEnabled,
		OrderByComparator<CurrencyEntry> orderByComparator)
		throws NoSuchCurrencyEntryException {
		CurrencyEntry currencyEntry = fetchByMarketplaceEnabled_Last(marketplaceEnabled,
				orderByComparator);

		if (currencyEntry != null) {
			return currencyEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("marketplaceEnabled=");
		msg.append(marketplaceEnabled);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCurrencyEntryException(msg.toString());
	}

	/**
	 * Returns the last currency entry in the ordered set where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching currency entry, or <code>null</code> if a matching currency entry could not be found
	 */
	@Override
	public CurrencyEntry fetchByMarketplaceEnabled_Last(
		boolean marketplaceEnabled,
		OrderByComparator<CurrencyEntry> orderByComparator) {
		int count = countByMarketplaceEnabled(marketplaceEnabled);

		if (count == 0) {
			return null;
		}

		List<CurrencyEntry> list = findByMarketplaceEnabled(marketplaceEnabled,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the currency entries before and after the current currency entry in the ordered set where marketplaceEnabled = &#63;.
	 *
	 * @param currencyEntryId the primary key of the current currency entry
	 * @param marketplaceEnabled the marketplace enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next currency entry
	 * @throws NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	 */
	@Override
	public CurrencyEntry[] findByMarketplaceEnabled_PrevAndNext(
		long currencyEntryId, boolean marketplaceEnabled,
		OrderByComparator<CurrencyEntry> orderByComparator)
		throws NoSuchCurrencyEntryException {
		CurrencyEntry currencyEntry = findByPrimaryKey(currencyEntryId);

		Session session = null;

		try {
			session = openSession();

			CurrencyEntry[] array = new CurrencyEntryImpl[3];

			array[0] = getByMarketplaceEnabled_PrevAndNext(session,
					currencyEntry, marketplaceEnabled, orderByComparator, true);

			array[1] = currencyEntry;

			array[2] = getByMarketplaceEnabled_PrevAndNext(session,
					currencyEntry, marketplaceEnabled, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CurrencyEntry getByMarketplaceEnabled_PrevAndNext(
		Session session, CurrencyEntry currencyEntry,
		boolean marketplaceEnabled,
		OrderByComparator<CurrencyEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CURRENCYENTRY_WHERE);

		query.append(_FINDER_COLUMN_MARKETPLACEENABLED_MARKETPLACEENABLED_2);

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
			query.append(CurrencyEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(marketplaceEnabled);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(currencyEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CurrencyEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the currency entries where marketplaceEnabled = &#63; from the database.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 */
	@Override
	public void removeByMarketplaceEnabled(boolean marketplaceEnabled) {
		for (CurrencyEntry currencyEntry : findByMarketplaceEnabled(
				marketplaceEnabled, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(currencyEntry);
		}
	}

	/**
	 * Returns the number of currency entries where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @return the number of matching currency entries
	 */
	@Override
	public int countByMarketplaceEnabled(boolean marketplaceEnabled) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MARKETPLACEENABLED;

		Object[] finderArgs = new Object[] { marketplaceEnabled };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CURRENCYENTRY_WHERE);

			query.append(_FINDER_COLUMN_MARKETPLACEENABLED_MARKETPLACEENABLED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(marketplaceEnabled);

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

	private static final String _FINDER_COLUMN_MARKETPLACEENABLED_MARKETPLACEENABLED_2 =
		"currencyEntry.marketplaceEnabled = ?";

	public CurrencyEntryPersistenceImpl() {
		setModelClass(CurrencyEntry.class);
	}

	/**
	 * Caches the currency entry in the entity cache if it is enabled.
	 *
	 * @param currencyEntry the currency entry
	 */
	@Override
	public void cacheResult(CurrencyEntry currencyEntry) {
		entityCache.putResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryImpl.class, currencyEntry.getPrimaryKey(),
			currencyEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CURRENCYCODE,
			new Object[] { currencyEntry.getCurrencyCode() }, currencyEntry);

		currencyEntry.resetOriginalValues();
	}

	/**
	 * Caches the currency entries in the entity cache if it is enabled.
	 *
	 * @param currencyEntries the currency entries
	 */
	@Override
	public void cacheResult(List<CurrencyEntry> currencyEntries) {
		for (CurrencyEntry currencyEntry : currencyEntries) {
			if (entityCache.getResult(
						CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
						CurrencyEntryImpl.class, currencyEntry.getPrimaryKey()) == null) {
				cacheResult(currencyEntry);
			}
			else {
				currencyEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all currency entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CurrencyEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the currency entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CurrencyEntry currencyEntry) {
		entityCache.removeResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryImpl.class, currencyEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CurrencyEntryModelImpl)currencyEntry, true);
	}

	@Override
	public void clearCache(List<CurrencyEntry> currencyEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CurrencyEntry currencyEntry : currencyEntries) {
			entityCache.removeResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
				CurrencyEntryImpl.class, currencyEntry.getPrimaryKey());

			clearUniqueFindersCache((CurrencyEntryModelImpl)currencyEntry, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CurrencyEntryModelImpl currencyEntryModelImpl) {
		Object[] args = new Object[] { currencyEntryModelImpl.getCurrencyCode() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_CURRENCYCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CURRENCYCODE, args,
			currencyEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CurrencyEntryModelImpl currencyEntryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					currencyEntryModelImpl.getCurrencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CURRENCYCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CURRENCYCODE, args);
		}

		if ((currencyEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CURRENCYCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					currencyEntryModelImpl.getOriginalCurrencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CURRENCYCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CURRENCYCODE, args);
		}
	}

	/**
	 * Creates a new currency entry with the primary key. Does not add the currency entry to the database.
	 *
	 * @param currencyEntryId the primary key for the new currency entry
	 * @return the new currency entry
	 */
	@Override
	public CurrencyEntry create(long currencyEntryId) {
		CurrencyEntry currencyEntry = new CurrencyEntryImpl();

		currencyEntry.setNew(true);
		currencyEntry.setPrimaryKey(currencyEntryId);

		return currencyEntry;
	}

	/**
	 * Removes the currency entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param currencyEntryId the primary key of the currency entry
	 * @return the currency entry that was removed
	 * @throws NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	 */
	@Override
	public CurrencyEntry remove(long currencyEntryId)
		throws NoSuchCurrencyEntryException {
		return remove((Serializable)currencyEntryId);
	}

	/**
	 * Removes the currency entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the currency entry
	 * @return the currency entry that was removed
	 * @throws NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	 */
	@Override
	public CurrencyEntry remove(Serializable primaryKey)
		throws NoSuchCurrencyEntryException {
		Session session = null;

		try {
			session = openSession();

			CurrencyEntry currencyEntry = (CurrencyEntry)session.get(CurrencyEntryImpl.class,
					primaryKey);

			if (currencyEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCurrencyEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(currencyEntry);
		}
		catch (NoSuchCurrencyEntryException nsee) {
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
	protected CurrencyEntry removeImpl(CurrencyEntry currencyEntry) {
		currencyEntry = toUnwrappedModel(currencyEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(currencyEntry)) {
				currencyEntry = (CurrencyEntry)session.get(CurrencyEntryImpl.class,
						currencyEntry.getPrimaryKeyObj());
			}

			if (currencyEntry != null) {
				session.delete(currencyEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (currencyEntry != null) {
			clearCache(currencyEntry);
		}

		return currencyEntry;
	}

	@Override
	public CurrencyEntry updateImpl(CurrencyEntry currencyEntry) {
		currencyEntry = toUnwrappedModel(currencyEntry);

		boolean isNew = currencyEntry.isNew();

		CurrencyEntryModelImpl currencyEntryModelImpl = (CurrencyEntryModelImpl)currencyEntry;

		Session session = null;

		try {
			session = openSession();

			if (currencyEntry.isNew()) {
				session.save(currencyEntry);

				currencyEntry.setNew(false);
			}
			else {
				currencyEntry = (CurrencyEntry)session.merge(currencyEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CurrencyEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					currencyEntryModelImpl.getMarketplaceEnabled()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MARKETPLACEENABLED,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACEENABLED,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((currencyEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACEENABLED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						currencyEntryModelImpl.getOriginalMarketplaceEnabled()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MARKETPLACEENABLED,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACEENABLED,
					args);

				args = new Object[] {
						currencyEntryModelImpl.getMarketplaceEnabled()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MARKETPLACEENABLED,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACEENABLED,
					args);
			}
		}

		entityCache.putResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryImpl.class, currencyEntry.getPrimaryKey(),
			currencyEntry, false);

		clearUniqueFindersCache(currencyEntryModelImpl, false);
		cacheUniqueFindersCache(currencyEntryModelImpl);

		currencyEntry.resetOriginalValues();

		return currencyEntry;
	}

	protected CurrencyEntry toUnwrappedModel(CurrencyEntry currencyEntry) {
		if (currencyEntry instanceof CurrencyEntryImpl) {
			return currencyEntry;
		}

		CurrencyEntryImpl currencyEntryImpl = new CurrencyEntryImpl();

		currencyEntryImpl.setNew(currencyEntry.isNew());
		currencyEntryImpl.setPrimaryKey(currencyEntry.getPrimaryKey());

		currencyEntryImpl.setCurrencyEntryId(currencyEntry.getCurrencyEntryId());
		currencyEntryImpl.setCountryId(currencyEntry.getCountryId());
		currencyEntryImpl.setCurrencyCode(currencyEntry.getCurrencyCode());
		currencyEntryImpl.setMarketplaceEnabled(currencyEntry.isMarketplaceEnabled());
		currencyEntryImpl.setMarketplaceMinPrice(currencyEntry.getMarketplaceMinPrice());

		return currencyEntryImpl;
	}

	/**
	 * Returns the currency entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the currency entry
	 * @return the currency entry
	 * @throws NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	 */
	@Override
	public CurrencyEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCurrencyEntryException {
		CurrencyEntry currencyEntry = fetchByPrimaryKey(primaryKey);

		if (currencyEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCurrencyEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return currencyEntry;
	}

	/**
	 * Returns the currency entry with the primary key or throws a {@link NoSuchCurrencyEntryException} if it could not be found.
	 *
	 * @param currencyEntryId the primary key of the currency entry
	 * @return the currency entry
	 * @throws NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	 */
	@Override
	public CurrencyEntry findByPrimaryKey(long currencyEntryId)
		throws NoSuchCurrencyEntryException {
		return findByPrimaryKey((Serializable)currencyEntryId);
	}

	/**
	 * Returns the currency entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the currency entry
	 * @return the currency entry, or <code>null</code> if a currency entry with the primary key could not be found
	 */
	@Override
	public CurrencyEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
				CurrencyEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CurrencyEntry currencyEntry = (CurrencyEntry)serializable;

		if (currencyEntry == null) {
			Session session = null;

			try {
				session = openSession();

				currencyEntry = (CurrencyEntry)session.get(CurrencyEntryImpl.class,
						primaryKey);

				if (currencyEntry != null) {
					cacheResult(currencyEntry);
				}
				else {
					entityCache.putResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
						CurrencyEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
					CurrencyEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return currencyEntry;
	}

	/**
	 * Returns the currency entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param currencyEntryId the primary key of the currency entry
	 * @return the currency entry, or <code>null</code> if a currency entry with the primary key could not be found
	 */
	@Override
	public CurrencyEntry fetchByPrimaryKey(long currencyEntryId) {
		return fetchByPrimaryKey((Serializable)currencyEntryId);
	}

	@Override
	public Map<Serializable, CurrencyEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CurrencyEntry> map = new HashMap<Serializable, CurrencyEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CurrencyEntry currencyEntry = fetchByPrimaryKey(primaryKey);

			if (currencyEntry != null) {
				map.put(primaryKey, currencyEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
					CurrencyEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CurrencyEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CURRENCYENTRY_WHERE_PKS_IN);

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

			for (CurrencyEntry currencyEntry : (List<CurrencyEntry>)q.list()) {
				map.put(currencyEntry.getPrimaryKeyObj(), currencyEntry);

				cacheResult(currencyEntry);

				uncachedPrimaryKeys.remove(currencyEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
					CurrencyEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the currency entries.
	 *
	 * @return the currency entries
	 */
	@Override
	public List<CurrencyEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the currency entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of currency entries
	 * @param end the upper bound of the range of currency entries (not inclusive)
	 * @return the range of currency entries
	 */
	@Override
	public List<CurrencyEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the currency entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of currency entries
	 * @param end the upper bound of the range of currency entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of currency entries
	 */
	@Override
	public List<CurrencyEntry> findAll(int start, int end,
		OrderByComparator<CurrencyEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the currency entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of currency entries
	 * @param end the upper bound of the range of currency entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of currency entries
	 */
	@Override
	public List<CurrencyEntry> findAll(int start, int end,
		OrderByComparator<CurrencyEntry> orderByComparator,
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

		List<CurrencyEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CurrencyEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CURRENCYENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CURRENCYENTRY;

				if (pagination) {
					sql = sql.concat(CurrencyEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CurrencyEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CurrencyEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the currency entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CurrencyEntry currencyEntry : findAll()) {
			remove(currencyEntry);
		}
	}

	/**
	 * Returns the number of currency entries.
	 *
	 * @return the number of currency entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CURRENCYENTRY);

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
		return CurrencyEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the currency entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CurrencyEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_CURRENCYENTRY = "SELECT currencyEntry FROM CurrencyEntry currencyEntry";
	private static final String _SQL_SELECT_CURRENCYENTRY_WHERE_PKS_IN = "SELECT currencyEntry FROM CurrencyEntry currencyEntry WHERE currencyEntryId IN (";
	private static final String _SQL_SELECT_CURRENCYENTRY_WHERE = "SELECT currencyEntry FROM CurrencyEntry currencyEntry WHERE ";
	private static final String _SQL_COUNT_CURRENCYENTRY = "SELECT COUNT(currencyEntry) FROM CurrencyEntry currencyEntry";
	private static final String _SQL_COUNT_CURRENCYENTRY_WHERE = "SELECT COUNT(currencyEntry) FROM CurrencyEntry currencyEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "currencyEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CurrencyEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CurrencyEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CurrencyEntryPersistenceImpl.class);
}
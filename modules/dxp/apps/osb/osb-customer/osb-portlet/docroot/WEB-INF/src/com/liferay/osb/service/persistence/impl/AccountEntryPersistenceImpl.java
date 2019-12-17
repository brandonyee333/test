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

import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.impl.AccountEntryImpl;
import com.liferay.osb.model.impl.AccountEntryModelImpl;
import com.liferay.osb.service.persistence.AccountEntryPersistence;
import com.liferay.osb.service.persistence.SupportRegionPersistence;

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
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

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
 * The persistence implementation for the account entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryPersistence
 * @see com.liferay.osb.service.persistence.AccountEntryUtil
 * @generated
 */
@ProviderType
public class AccountEntryPersistenceImpl extends BasePersistenceImpl<AccountEntry>
	implements AccountEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountEntryUtil} to access the account entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_KORONEIKIACCOUNTKEY = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByKoroneikiAccountKey",
			new String[] { String.class.getName() },
			AccountEntryModelImpl.KORONEIKIACCOUNTKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KORONEIKIACCOUNTKEY = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKoroneikiAccountKey",
			new String[] { String.class.getName() });

	/**
	 * Returns the account entry where koroneikiAccountKey = &#63; or throws a {@link NoSuchAccountEntryException} if it could not be found.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByKoroneikiAccountKey(String koroneikiAccountKey)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByKoroneikiAccountKey(koroneikiAccountKey);

		if (accountEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("koroneikiAccountKey=");
			msg.append(koroneikiAccountKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountEntryException(msg.toString());
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry where koroneikiAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByKoroneikiAccountKey(String koroneikiAccountKey) {
		return fetchByKoroneikiAccountKey(koroneikiAccountKey, true);
	}

	/**
	 * Returns the account entry where koroneikiAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByKoroneikiAccountKey(String koroneikiAccountKey,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { koroneikiAccountKey };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_KORONEIKIACCOUNTKEY,
					finderArgs, this);
		}

		if (result instanceof AccountEntry) {
			AccountEntry accountEntry = (AccountEntry)result;

			if (!Objects.equals(koroneikiAccountKey,
						accountEntry.getKoroneikiAccountKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			boolean bindKoroneikiAccountKey = false;

			if (koroneikiAccountKey == null) {
				query.append(_FINDER_COLUMN_KORONEIKIACCOUNTKEY_KORONEIKIACCOUNTKEY_1);
			}
			else if (koroneikiAccountKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KORONEIKIACCOUNTKEY_KORONEIKIACCOUNTKEY_3);
			}
			else {
				bindKoroneikiAccountKey = true;

				query.append(_FINDER_COLUMN_KORONEIKIACCOUNTKEY_KORONEIKIACCOUNTKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindKoroneikiAccountKey) {
					qPos.add(koroneikiAccountKey);
				}

				List<AccountEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_KORONEIKIACCOUNTKEY,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AccountEntryPersistenceImpl.fetchByKoroneikiAccountKey(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AccountEntry accountEntry = list.get(0);

					result = accountEntry;

					cacheResult(accountEntry);

					if ((accountEntry.getKoroneikiAccountKey() == null) ||
							!accountEntry.getKoroneikiAccountKey()
											 .equals(koroneikiAccountKey)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_KORONEIKIACCOUNTKEY,
							finderArgs, accountEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_KORONEIKIACCOUNTKEY,
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
			return (AccountEntry)result;
		}
	}

	/**
	 * Removes the account entry where koroneikiAccountKey = &#63; from the database.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the account entry that was removed
	 */
	@Override
	public AccountEntry removeByKoroneikiAccountKey(String koroneikiAccountKey)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByKoroneikiAccountKey(koroneikiAccountKey);

		return remove(accountEntry);
	}

	/**
	 * Returns the number of account entries where koroneikiAccountKey = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the number of matching account entries
	 */
	@Override
	public int countByKoroneikiAccountKey(String koroneikiAccountKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KORONEIKIACCOUNTKEY;

		Object[] finderArgs = new Object[] { koroneikiAccountKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			boolean bindKoroneikiAccountKey = false;

			if (koroneikiAccountKey == null) {
				query.append(_FINDER_COLUMN_KORONEIKIACCOUNTKEY_KORONEIKIACCOUNTKEY_1);
			}
			else if (koroneikiAccountKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KORONEIKIACCOUNTKEY_KORONEIKIACCOUNTKEY_3);
			}
			else {
				bindKoroneikiAccountKey = true;

				query.append(_FINDER_COLUMN_KORONEIKIACCOUNTKEY_KORONEIKIACCOUNTKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindKoroneikiAccountKey) {
					qPos.add(koroneikiAccountKey);
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

	private static final String _FINDER_COLUMN_KORONEIKIACCOUNTKEY_KORONEIKIACCOUNTKEY_1 =
		"accountEntry.koroneikiAccountKey IS NULL";
	private static final String _FINDER_COLUMN_KORONEIKIACCOUNTKEY_KORONEIKIACCOUNTKEY_2 =
		"accountEntry.koroneikiAccountKey = ?";
	private static final String _FINDER_COLUMN_KORONEIKIACCOUNTKEY_KORONEIKIACCOUNTKEY_3 =
		"(accountEntry.koroneikiAccountKey IS NULL OR accountEntry.koroneikiAccountKey = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DOSSIERAACCOUNTKEY =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDossieraAccountKey",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERAACCOUNTKEY =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByDossieraAccountKey",
			new String[] { String.class.getName() },
			AccountEntryModelImpl.DOSSIERAACCOUNTKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDossieraAccountKey", new String[] { String.class.getName() });

	/**
	 * Returns all the account entries where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the matching account entries
	 */
	@Override
	public List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey) {
		return findByDossieraAccountKey(dossieraAccountKey, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where dossieraAccountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey, int start, int end) {
		return findByDossieraAccountKey(dossieraAccountKey, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where dossieraAccountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return findByDossieraAccountKey(dossieraAccountKey, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries where dossieraAccountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERAACCOUNTKEY;
			finderArgs = new Object[] { dossieraAccountKey };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DOSSIERAACCOUNTKEY;
			finderArgs = new Object[] {
					dossieraAccountKey,
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEntry accountEntry : list) {
					if (!Objects.equals(dossieraAccountKey,
								accountEntry.getDossieraAccountKey())) {
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

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			boolean bindDossieraAccountKey = false;

			if (dossieraAccountKey == null) {
				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1);
			}
			else if (dossieraAccountKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3);
			}
			else {
				bindDossieraAccountKey = true;

				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossieraAccountKey) {
					qPos.add(dossieraAccountKey);
				}

				if (!pagination) {
					list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByDossieraAccountKey_First(
		String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByDossieraAccountKey_First(dossieraAccountKey,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossieraAccountKey=");
		msg.append(dossieraAccountKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByDossieraAccountKey_First(
		String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator) {
		List<AccountEntry> list = findByDossieraAccountKey(dossieraAccountKey,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByDossieraAccountKey_Last(
		String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByDossieraAccountKey_Last(dossieraAccountKey,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dossieraAccountKey=");
		msg.append(dossieraAccountKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByDossieraAccountKey_Last(
		String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator) {
		int count = countByDossieraAccountKey(dossieraAccountKey);

		if (count == 0) {
			return null;
		}

		List<AccountEntry> list = findByDossieraAccountKey(dossieraAccountKey,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry[] findByDossieraAccountKey_PrevAndNext(
		long accountEntryId, String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByDossieraAccountKey_PrevAndNext(session,
					accountEntry, dossieraAccountKey, orderByComparator, true);

			array[1] = accountEntry;

			array[2] = getByDossieraAccountKey_PrevAndNext(session,
					accountEntry, dossieraAccountKey, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEntry getByDossieraAccountKey_PrevAndNext(
		Session session, AccountEntry accountEntry, String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		boolean bindDossieraAccountKey = false;

		if (dossieraAccountKey == null) {
			query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1);
		}
		else if (dossieraAccountKey.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3);
		}
		else {
			bindDossieraAccountKey = true;

			query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2);
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
			query.append(AccountEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDossieraAccountKey) {
			qPos.add(dossieraAccountKey);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account entries where dossieraAccountKey = &#63; from the database.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 */
	@Override
	public void removeByDossieraAccountKey(String dossieraAccountKey) {
		for (AccountEntry accountEntry : findByDossieraAccountKey(
				dossieraAccountKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountEntry);
		}
	}

	/**
	 * Returns the number of account entries where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the number of matching account entries
	 */
	@Override
	public int countByDossieraAccountKey(String dossieraAccountKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY;

		Object[] finderArgs = new Object[] { dossieraAccountKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			boolean bindDossieraAccountKey = false;

			if (dossieraAccountKey == null) {
				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1);
			}
			else if (dossieraAccountKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3);
			}
			else {
				bindDossieraAccountKey = true;

				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossieraAccountKey) {
					qPos.add(dossieraAccountKey);
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

	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1 =
		"accountEntry.dossieraAccountKey IS NULL";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2 =
		"accountEntry.dossieraAccountKey = ?";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3 =
		"(accountEntry.dossieraAccountKey IS NULL OR accountEntry.dossieraAccountKey = '')";

	public AccountEntryPersistenceImpl() {
		setModelClass(AccountEntry.class);
	}

	/**
	 * Caches the account entry in the entity cache if it is enabled.
	 *
	 * @param accountEntry the account entry
	 */
	@Override
	public void cacheResult(AccountEntry accountEntry) {
		entityCache.putResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryImpl.class, accountEntry.getPrimaryKey(), accountEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_KORONEIKIACCOUNTKEY,
			new Object[] { accountEntry.getKoroneikiAccountKey() }, accountEntry);

		accountEntry.resetOriginalValues();
	}

	/**
	 * Caches the account entries in the entity cache if it is enabled.
	 *
	 * @param accountEntries the account entries
	 */
	@Override
	public void cacheResult(List<AccountEntry> accountEntries) {
		for (AccountEntry accountEntry : accountEntries) {
			if (entityCache.getResult(
						AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
						AccountEntryImpl.class, accountEntry.getPrimaryKey()) == null) {
				cacheResult(accountEntry);
			}
			else {
				accountEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountEntry accountEntry) {
		entityCache.removeResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryImpl.class, accountEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AccountEntryModelImpl)accountEntry, true);
	}

	@Override
	public void clearCache(List<AccountEntry> accountEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountEntry accountEntry : accountEntries) {
			entityCache.removeResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
				AccountEntryImpl.class, accountEntry.getPrimaryKey());

			clearUniqueFindersCache((AccountEntryModelImpl)accountEntry, true);
		}
	}

	protected void cacheUniqueFindersCache(
		AccountEntryModelImpl accountEntryModelImpl) {
		Object[] args = new Object[] {
				accountEntryModelImpl.getKoroneikiAccountKey()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_KORONEIKIACCOUNTKEY, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_KORONEIKIACCOUNTKEY, args,
			accountEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AccountEntryModelImpl accountEntryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					accountEntryModelImpl.getKoroneikiAccountKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_KORONEIKIACCOUNTKEY,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_KORONEIKIACCOUNTKEY,
				args);
		}

		if ((accountEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KORONEIKIACCOUNTKEY.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					accountEntryModelImpl.getOriginalKoroneikiAccountKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_KORONEIKIACCOUNTKEY,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_KORONEIKIACCOUNTKEY,
				args);
		}
	}

	/**
	 * Creates a new account entry with the primary key. Does not add the account entry to the database.
	 *
	 * @param accountEntryId the primary key for the new account entry
	 * @return the new account entry
	 */
	@Override
	public AccountEntry create(long accountEntryId) {
		AccountEntry accountEntry = new AccountEntryImpl();

		accountEntry.setNew(true);
		accountEntry.setPrimaryKey(accountEntryId);

		accountEntry.setCompanyId(companyProvider.getCompanyId());

		return accountEntry;
	}

	/**
	 * Removes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry that was removed
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry remove(long accountEntryId)
		throws NoSuchAccountEntryException {
		return remove((Serializable)accountEntryId);
	}

	/**
	 * Removes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account entry
	 * @return the account entry that was removed
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry remove(Serializable primaryKey)
		throws NoSuchAccountEntryException {
		Session session = null;

		try {
			session = openSession();

			AccountEntry accountEntry = (AccountEntry)session.get(AccountEntryImpl.class,
					primaryKey);

			if (accountEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountEntry);
		}
		catch (NoSuchAccountEntryException nsee) {
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
	protected AccountEntry removeImpl(AccountEntry accountEntry) {
		accountEntry = toUnwrappedModel(accountEntry);

		accountEntryToSupportRegionTableMapper.deleteLeftPrimaryKeyTableMappings(accountEntry.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountEntry)) {
				accountEntry = (AccountEntry)session.get(AccountEntryImpl.class,
						accountEntry.getPrimaryKeyObj());
			}

			if (accountEntry != null) {
				session.delete(accountEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accountEntry != null) {
			clearCache(accountEntry);
		}

		return accountEntry;
	}

	@Override
	public AccountEntry updateImpl(AccountEntry accountEntry) {
		accountEntry = toUnwrappedModel(accountEntry);

		boolean isNew = accountEntry.isNew();

		AccountEntryModelImpl accountEntryModelImpl = (AccountEntryModelImpl)accountEntry;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (accountEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				accountEntry.setCreateDate(now);
			}
			else {
				accountEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!accountEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				accountEntry.setModifiedDate(now);
			}
			else {
				accountEntry.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (accountEntry.isNew()) {
				session.save(accountEntry);

				accountEntry.setNew(false);
			}
			else {
				accountEntry = (AccountEntry)session.merge(accountEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AccountEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					accountEntryModelImpl.getDossieraAccountKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERAACCOUNTKEY,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERAACCOUNTKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountEntryModelImpl.getOriginalDossieraAccountKey()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERAACCOUNTKEY,
					args);

				args = new Object[] {
						accountEntryModelImpl.getDossieraAccountKey()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOSSIERAACCOUNTKEY,
					args);
			}
		}

		entityCache.putResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryImpl.class, accountEntry.getPrimaryKey(), accountEntry,
			false);

		clearUniqueFindersCache(accountEntryModelImpl, false);
		cacheUniqueFindersCache(accountEntryModelImpl);

		accountEntry.resetOriginalValues();

		return accountEntry;
	}

	protected AccountEntry toUnwrappedModel(AccountEntry accountEntry) {
		if (accountEntry instanceof AccountEntryImpl) {
			return accountEntry;
		}

		AccountEntryImpl accountEntryImpl = new AccountEntryImpl();

		accountEntryImpl.setNew(accountEntry.isNew());
		accountEntryImpl.setPrimaryKey(accountEntry.getPrimaryKey());

		accountEntryImpl.setAccountEntryId(accountEntry.getAccountEntryId());
		accountEntryImpl.setCompanyId(accountEntry.getCompanyId());
		accountEntryImpl.setUserId(accountEntry.getUserId());
		accountEntryImpl.setUserName(accountEntry.getUserName());
		accountEntryImpl.setCreateDate(accountEntry.getCreateDate());
		accountEntryImpl.setModifiedUserId(accountEntry.getModifiedUserId());
		accountEntryImpl.setModifiedUserName(accountEntry.getModifiedUserName());
		accountEntryImpl.setModifiedDate(accountEntry.getModifiedDate());
		accountEntryImpl.setKoroneikiAccountKey(accountEntry.getKoroneikiAccountKey());
		accountEntryImpl.setDossieraAccountKey(accountEntry.getDossieraAccountKey());
		accountEntryImpl.setName(accountEntry.getName());
		accountEntryImpl.setCode(accountEntry.getCode());
		accountEntryImpl.setInstructions(accountEntry.getInstructions());
		accountEntryImpl.setActiveSupport(accountEntry.isActiveSupport());
		accountEntryImpl.setActiveTicketSupport(accountEntry.isActiveTicketSupport());
		accountEntryImpl.setLastZendeskAuditDate(accountEntry.getLastZendeskAuditDate());
		accountEntryImpl.setStatus(accountEntry.getStatus());

		return accountEntryImpl;
	}

	/**
	 * Returns the account entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account entry
	 * @return the account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByPrimaryKey(primaryKey);

		if (accountEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry with the primary key or throws a {@link NoSuchAccountEntryException} if it could not be found.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry findByPrimaryKey(long accountEntryId)
		throws NoSuchAccountEntryException {
		return findByPrimaryKey((Serializable)accountEntryId);
	}

	/**
	 * Returns the account entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account entry
	 * @return the account entry, or <code>null</code> if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
				AccountEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccountEntry accountEntry = (AccountEntry)serializable;

		if (accountEntry == null) {
			Session session = null;

			try {
				session = openSession();

				accountEntry = (AccountEntry)session.get(AccountEntryImpl.class,
						primaryKey);

				if (accountEntry != null) {
					cacheResult(accountEntry);
				}
				else {
					entityCache.putResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
						AccountEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
					AccountEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry, or <code>null</code> if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry fetchByPrimaryKey(long accountEntryId) {
		return fetchByPrimaryKey((Serializable)accountEntryId);
	}

	@Override
	public Map<Serializable, AccountEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccountEntry> map = new HashMap<Serializable, AccountEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccountEntry accountEntry = fetchByPrimaryKey(primaryKey);

			if (accountEntry != null) {
				map.put(primaryKey, accountEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
					AccountEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccountEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE_PKS_IN);

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

			for (AccountEntry accountEntry : (List<AccountEntry>)q.list()) {
				map.put(accountEntry.getPrimaryKeyObj(), accountEntry);

				cacheResult(accountEntry);

				uncachedPrimaryKeys.remove(accountEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
					AccountEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the account entries.
	 *
	 * @return the account entries
	 */
	@Override
	public List<AccountEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of account entries
	 */
	@Override
	public List<AccountEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entries
	 */
	@Override
	public List<AccountEntry> findAll(int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of account entries
	 */
	@Override
	public List<AccountEntry> findAll(int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
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

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCOUNTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTENTRY;

				if (pagination) {
					sql = sql.concat(AccountEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the account entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountEntry accountEntry : findAll()) {
			remove(accountEntry);
		}
	}

	/**
	 * Returns the number of account entries.
	 *
	 * @return the number of account entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTENTRY);

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

	/**
	 * Returns the primaryKeys of support regions associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @return long[] of the primaryKeys of support regions associated with the account entry
	 */
	@Override
	public long[] getSupportRegionPrimaryKeys(long pk) {
		long[] pks = accountEntryToSupportRegionTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the support regions associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @return the support regions associated with the account entry
	 */
	@Override
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(long pk) {
		return getSupportRegions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the support regions associated with the account entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the account entry
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of support regions associated with the account entry
	 */
	@Override
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end) {
		return getSupportRegions(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support regions associated with the account entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the account entry
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support regions associated with the account entry
	 */
	@Override
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.model.SupportRegion> orderByComparator) {
		return accountEntryToSupportRegionTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of support regions associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @return the number of support regions associated with the account entry
	 */
	@Override
	public int getSupportRegionsSize(long pk) {
		long[] pks = accountEntryToSupportRegionTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the support region is associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPK the primary key of the support region
	 * @return <code>true</code> if the support region is associated with the account entry; <code>false</code> otherwise
	 */
	@Override
	public boolean containsSupportRegion(long pk, long supportRegionPK) {
		return accountEntryToSupportRegionTableMapper.containsTableMapping(pk,
			supportRegionPK);
	}

	/**
	 * Returns <code>true</code> if the account entry has any support regions associated with it.
	 *
	 * @param pk the primary key of the account entry to check for associations with support regions
	 * @return <code>true</code> if the account entry has any support regions associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsSupportRegions(long pk) {
		if (getSupportRegionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPK the primary key of the support region
	 */
	@Override
	public void addSupportRegion(long pk, long supportRegionPK) {
		AccountEntry accountEntry = fetchByPrimaryKey(pk);

		if (accountEntry == null) {
			accountEntryToSupportRegionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, supportRegionPK);
		}
		else {
			accountEntryToSupportRegionTableMapper.addTableMapping(accountEntry.getCompanyId(),
				pk, supportRegionPK);
		}
	}

	/**
	 * Adds an association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegion the support region
	 */
	@Override
	public void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion) {
		AccountEntry accountEntry = fetchByPrimaryKey(pk);

		if (accountEntry == null) {
			accountEntryToSupportRegionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, supportRegion.getPrimaryKey());
		}
		else {
			accountEntryToSupportRegionTableMapper.addTableMapping(accountEntry.getCompanyId(),
				pk, supportRegion.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPKs the primary keys of the support regions
	 */
	@Override
	public void addSupportRegions(long pk, long[] supportRegionPKs) {
		long companyId = 0;

		AccountEntry accountEntry = fetchByPrimaryKey(pk);

		if (accountEntry == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = accountEntry.getCompanyId();
		}

		accountEntryToSupportRegionTableMapper.addTableMappings(companyId, pk,
			supportRegionPKs);
	}

	/**
	 * Adds an association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegions the support regions
	 */
	@Override
	public void addSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		addSupportRegions(pk,
			ListUtil.toLongArray(supportRegions,
				com.liferay.osb.model.SupportRegion.SUPPORT_REGION_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the account entry and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry to clear the associated support regions from
	 */
	@Override
	public void clearSupportRegions(long pk) {
		accountEntryToSupportRegionTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPK the primary key of the support region
	 */
	@Override
	public void removeSupportRegion(long pk, long supportRegionPK) {
		accountEntryToSupportRegionTableMapper.deleteTableMapping(pk,
			supportRegionPK);
	}

	/**
	 * Removes the association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegion the support region
	 */
	@Override
	public void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion) {
		accountEntryToSupportRegionTableMapper.deleteTableMapping(pk,
			supportRegion.getPrimaryKey());
	}

	/**
	 * Removes the association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPKs the primary keys of the support regions
	 */
	@Override
	public void removeSupportRegions(long pk, long[] supportRegionPKs) {
		accountEntryToSupportRegionTableMapper.deleteTableMappings(pk,
			supportRegionPKs);
	}

	/**
	 * Removes the association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegions the support regions
	 */
	@Override
	public void removeSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		removeSupportRegions(pk,
			ListUtil.toLongArray(supportRegions,
				com.liferay.osb.model.SupportRegion.SUPPORT_REGION_ID_ACCESSOR));
	}

	/**
	 * Sets the support regions associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegionPKs the primary keys of the support regions to be associated with the account entry
	 */
	@Override
	public void setSupportRegions(long pk, long[] supportRegionPKs) {
		Set<Long> newSupportRegionPKsSet = SetUtil.fromArray(supportRegionPKs);
		Set<Long> oldSupportRegionPKsSet = SetUtil.fromArray(accountEntryToSupportRegionTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeSupportRegionPKsSet = new HashSet<Long>(oldSupportRegionPKsSet);

		removeSupportRegionPKsSet.removeAll(newSupportRegionPKsSet);

		accountEntryToSupportRegionTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeSupportRegionPKsSet));

		newSupportRegionPKsSet.removeAll(oldSupportRegionPKsSet);

		long companyId = 0;

		AccountEntry accountEntry = fetchByPrimaryKey(pk);

		if (accountEntry == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = accountEntry.getCompanyId();
		}

		accountEntryToSupportRegionTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newSupportRegionPKsSet));
	}

	/**
	 * Sets the support regions associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportRegions the support regions to be associated with the account entry
	 */
	@Override
	public void setSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		try {
			long[] supportRegionPKs = new long[supportRegions.size()];

			for (int i = 0; i < supportRegions.size(); i++) {
				com.liferay.osb.model.SupportRegion supportRegion = supportRegions.get(i);

				supportRegionPKs[i] = supportRegion.getPrimaryKey();
			}

			setSupportRegions(pk, supportRegionPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AccountEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account entry persistence.
	 */
	public void afterPropertiesSet() {
		accountEntryToSupportRegionTableMapper = TableMapperFactory.getTableMapper("OSB_AccountEntries_SupportRegions",
				"companyId", "accountEntryId", "supportRegionId", this,
				supportRegionPersistence);
	}

	public void destroy() {
		entityCache.removeCache(AccountEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"OSB_AccountEntries_SupportRegions");
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	protected TableMapper<AccountEntry, com.liferay.osb.model.SupportRegion> accountEntryToSupportRegionTableMapper;
	private static final String _SQL_SELECT_ACCOUNTENTRY = "SELECT accountEntry FROM AccountEntry accountEntry";
	private static final String _SQL_SELECT_ACCOUNTENTRY_WHERE_PKS_IN = "SELECT accountEntry FROM AccountEntry accountEntry WHERE accountEntryId IN (";
	private static final String _SQL_SELECT_ACCOUNTENTRY_WHERE = "SELECT accountEntry FROM AccountEntry accountEntry WHERE ";
	private static final String _SQL_COUNT_ACCOUNTENTRY = "SELECT COUNT(accountEntry) FROM AccountEntry accountEntry";
	private static final String _SQL_COUNT_ACCOUNTENTRY_WHERE = "SELECT COUNT(accountEntry) FROM AccountEntry accountEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AccountEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"code"
			});
}
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
import com.liferay.osb.service.persistence.SupportTeamPersistence;

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
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ListUtil;
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
	public static final FinderPath FINDER_PATH_FETCH_BY_CORPPROJECTUUID = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCorpProjectUuid",
			new String[] { String.class.getName() },
			AccountEntryModelImpl.CORPPROJECTUUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CORPPROJECTUUID = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCorpProjectUuid", new String[] { String.class.getName() });

	/**
	 * Returns the account entry where corpProjectUuid = &#63; or throws a {@link NoSuchAccountEntryException} if it could not be found.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByCorpProjectUuid(String corpProjectUuid)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByCorpProjectUuid(corpProjectUuid);

		if (accountEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("corpProjectUuid=");
			msg.append(corpProjectUuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountEntryException(msg.toString());
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry where corpProjectUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByCorpProjectUuid(String corpProjectUuid) {
		return fetchByCorpProjectUuid(corpProjectUuid, true);
	}

	/**
	 * Returns the account entry where corpProjectUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByCorpProjectUuid(String corpProjectUuid,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { corpProjectUuid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CORPPROJECTUUID,
					finderArgs, this);
		}

		if (result instanceof AccountEntry) {
			AccountEntry accountEntry = (AccountEntry)result;

			if (!Objects.equals(corpProjectUuid,
						accountEntry.getCorpProjectUuid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			boolean bindCorpProjectUuid = false;

			if (corpProjectUuid == null) {
				query.append(_FINDER_COLUMN_CORPPROJECTUUID_CORPPROJECTUUID_1);
			}
			else if (corpProjectUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CORPPROJECTUUID_CORPPROJECTUUID_3);
			}
			else {
				bindCorpProjectUuid = true;

				query.append(_FINDER_COLUMN_CORPPROJECTUUID_CORPPROJECTUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCorpProjectUuid) {
					qPos.add(corpProjectUuid);
				}

				List<AccountEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTUUID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AccountEntryPersistenceImpl.fetchByCorpProjectUuid(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AccountEntry accountEntry = list.get(0);

					result = accountEntry;

					cacheResult(accountEntry);

					if ((accountEntry.getCorpProjectUuid() == null) ||
							!accountEntry.getCorpProjectUuid()
											 .equals(corpProjectUuid)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTUUID,
							finderArgs, accountEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTUUID,
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
	 * Removes the account entry where corpProjectUuid = &#63; from the database.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the account entry that was removed
	 */
	@Override
	public AccountEntry removeByCorpProjectUuid(String corpProjectUuid)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByCorpProjectUuid(corpProjectUuid);

		return remove(accountEntry);
	}

	/**
	 * Returns the number of account entries where corpProjectUuid = &#63;.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the number of matching account entries
	 */
	@Override
	public int countByCorpProjectUuid(String corpProjectUuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CORPPROJECTUUID;

		Object[] finderArgs = new Object[] { corpProjectUuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			boolean bindCorpProjectUuid = false;

			if (corpProjectUuid == null) {
				query.append(_FINDER_COLUMN_CORPPROJECTUUID_CORPPROJECTUUID_1);
			}
			else if (corpProjectUuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CORPPROJECTUUID_CORPPROJECTUUID_3);
			}
			else {
				bindCorpProjectUuid = true;

				query.append(_FINDER_COLUMN_CORPPROJECTUUID_CORPPROJECTUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCorpProjectUuid) {
					qPos.add(corpProjectUuid);
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

	private static final String _FINDER_COLUMN_CORPPROJECTUUID_CORPPROJECTUUID_1 =
		"accountEntry.corpProjectUuid IS NULL";
	private static final String _FINDER_COLUMN_CORPPROJECTUUID_CORPPROJECTUUID_2 =
		"accountEntry.corpProjectUuid = ?";
	private static final String _FINDER_COLUMN_CORPPROJECTUUID_CORPPROJECTUUID_3 =
		"(accountEntry.corpProjectUuid IS NULL OR accountEntry.corpProjectUuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_CORPPROJECTID = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCorpProjectId",
			new String[] { Long.class.getName() },
			AccountEntryModelImpl.CORPPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CORPPROJECTID = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCorpProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the account entry where corpProjectId = &#63; or throws a {@link NoSuchAccountEntryException} if it could not be found.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByCorpProjectId(long corpProjectId)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByCorpProjectId(corpProjectId);

		if (accountEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("corpProjectId=");
			msg.append(corpProjectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountEntryException(msg.toString());
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry where corpProjectId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByCorpProjectId(long corpProjectId) {
		return fetchByCorpProjectId(corpProjectId, true);
	}

	/**
	 * Returns the account entry where corpProjectId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param corpProjectId the corp project ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByCorpProjectId(long corpProjectId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { corpProjectId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
					finderArgs, this);
		}

		if (result instanceof AccountEntry) {
			AccountEntry accountEntry = (AccountEntry)result;

			if ((corpProjectId != accountEntry.getCorpProjectId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				List<AccountEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AccountEntryPersistenceImpl.fetchByCorpProjectId(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AccountEntry accountEntry = list.get(0);

					result = accountEntry;

					cacheResult(accountEntry);

					if ((accountEntry.getCorpProjectId() != corpProjectId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
							finderArgs, accountEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
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
	 * Removes the account entry where corpProjectId = &#63; from the database.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the account entry that was removed
	 */
	@Override
	public AccountEntry removeByCorpProjectId(long corpProjectId)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByCorpProjectId(corpProjectId);

		return remove(accountEntry);
	}

	/**
	 * Returns the number of account entries where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the number of matching account entries
	 */
	@Override
	public int countByCorpProjectId(long corpProjectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CORPPROJECTID;

		Object[] finderArgs = new Object[] { corpProjectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

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

	private static final String _FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2 = "accountEntry.corpProjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByName",
			new String[] { String.class.getName() },
			AccountEntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the account entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching account entries
	 */
	@Override
	public List<AccountEntry> findByName(String name) {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByName(String name, int start, int end) {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByName(String name, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return findByName(name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByName(String name, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name, start, end, orderByComparator };
		}

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEntry accountEntry : list) {
					if (!Objects.equals(name, accountEntry.getName())) {
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

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
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

				if (bindName) {
					qPos.add(name);
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
	 * Returns the first account entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByName_First(String name,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByName_First(name, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByName_First(String name,
		OrderByComparator<AccountEntry> orderByComparator) {
		List<AccountEntry> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByName_Last(String name,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByName_Last(name, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByName_Last(String name,
		OrderByComparator<AccountEntry> orderByComparator) {
		int count = countByName(name);

		if (count == 0) {
			return null;
		}

		List<AccountEntry> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where name = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry[] findByName_PrevAndNext(long accountEntryId,
		String name, OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByName_PrevAndNext(session, accountEntry, name,
					orderByComparator, true);

			array[1] = accountEntry;

			array[2] = getByName_PrevAndNext(session, accountEntry, name,
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

	protected AccountEntry getByName_PrevAndNext(Session session,
		AccountEntry accountEntry, String name,
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

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_NAME_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_NAME_NAME_2);
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

		if (bindName) {
			qPos.add(name);
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
	 * Removes all the account entries where name = &#63; from the database.
	 *
	 * @param name the name
	 */
	@Override
	public void removeByName(String name) {
		for (AccountEntry accountEntry : findByName(name, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(accountEntry);
		}
	}

	/**
	 * Returns the number of account entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching account entries
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "accountEntry.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "accountEntry.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(accountEntry.name IS NULL OR accountEntry.name = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_CODE = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCode",
			new String[] { String.class.getName() },
			AccountEntryModelImpl.CODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CODE = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCode",
			new String[] { String.class.getName() });

	/**
	 * Returns the account entry where code = &#63; or throws a {@link NoSuchAccountEntryException} if it could not be found.
	 *
	 * @param code the code
	 * @return the matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByCode(String code)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByCode(code);

		if (accountEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("code=");
			msg.append(code);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountEntryException(msg.toString());
		}

		return accountEntry;
	}

	/**
	 * Returns the account entry where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param code the code
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByCode(String code) {
		return fetchByCode(code, true);
	}

	/**
	 * Returns the account entry where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param code the code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByCode(String code, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { code };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CODE,
					finderArgs, this);
		}

		if (result instanceof AccountEntry) {
			AccountEntry accountEntry = (AccountEntry)result;

			if (!Objects.equals(code, accountEntry.getCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			boolean bindCode = false;

			if (code == null) {
				query.append(_FINDER_COLUMN_CODE_CODE_1);
			}
			else if (code.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CODE_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_CODE_CODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCode) {
					qPos.add(StringUtil.toLowerCase(code));
				}

				List<AccountEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CODE,
						finderArgs, list);
				}
				else {
					AccountEntry accountEntry = list.get(0);

					result = accountEntry;

					cacheResult(accountEntry);

					if ((accountEntry.getCode() == null) ||
							!accountEntry.getCode().equals(code)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CODE,
							finderArgs, accountEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CODE, finderArgs);

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
	 * Removes the account entry where code = &#63; from the database.
	 *
	 * @param code the code
	 * @return the account entry that was removed
	 */
	@Override
	public AccountEntry removeByCode(String code)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByCode(code);

		return remove(accountEntry);
	}

	/**
	 * Returns the number of account entries where code = &#63;.
	 *
	 * @param code the code
	 * @return the number of matching account entries
	 */
	@Override
	public int countByCode(String code) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CODE;

		Object[] finderArgs = new Object[] { code };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			boolean bindCode = false;

			if (code == null) {
				query.append(_FINDER_COLUMN_CODE_CODE_1);
			}
			else if (code.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CODE_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_CODE_CODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCode) {
					qPos.add(StringUtil.toLowerCase(code));
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

	private static final String _FINDER_COLUMN_CODE_CODE_1 = "accountEntry.code IS NULL";
	private static final String _FINDER_COLUMN_CODE_CODE_2 = "lower(accountEntry.code) = ?";
	private static final String _FINDER_COLUMN_CODE_CODE_3 = "(accountEntry.code IS NULL OR accountEntry.code = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByRedirectAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByRedirectAccountEntryId",
			new String[] { Long.class.getName() },
			AccountEntryModelImpl.REDIRECTACCOUNTENTRYID_COLUMN_BITMASK |
			AccountEntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REDIRECTACCOUNTENTRYID = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByRedirectAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the account entries where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @return the matching account entries
	 */
	@Override
	public List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId) {
		return findByRedirectAccountEntryId(redirectAccountEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where redirectAccountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end) {
		return findByRedirectAccountEntryId(redirectAccountEntryId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return findByRedirectAccountEntryId(redirectAccountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID;
			finderArgs = new Object[] { redirectAccountEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID;
			finderArgs = new Object[] {
					redirectAccountEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEntry accountEntry : list) {
					if ((redirectAccountEntryId != accountEntry.getRedirectAccountEntryId())) {
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

			query.append(_FINDER_COLUMN_REDIRECTACCOUNTENTRYID_REDIRECTACCOUNTENTRYID_2);

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

				qPos.add(redirectAccountEntryId);

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
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByRedirectAccountEntryId_First(
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByRedirectAccountEntryId_First(redirectAccountEntryId,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByRedirectAccountEntryId_First(
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator) {
		List<AccountEntry> list = findByRedirectAccountEntryId(redirectAccountEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByRedirectAccountEntryId_Last(
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByRedirectAccountEntryId_Last(redirectAccountEntryId,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByRedirectAccountEntryId_Last(
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator) {
		int count = countByRedirectAccountEntryId(redirectAccountEntryId);

		if (count == 0) {
			return null;
		}

		List<AccountEntry> list = findByRedirectAccountEntryId(redirectAccountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry[] findByRedirectAccountEntryId_PrevAndNext(
		long accountEntryId, long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByRedirectAccountEntryId_PrevAndNext(session,
					accountEntry, redirectAccountEntryId, orderByComparator,
					true);

			array[1] = accountEntry;

			array[2] = getByRedirectAccountEntryId_PrevAndNext(session,
					accountEntry, redirectAccountEntryId, orderByComparator,
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

	protected AccountEntry getByRedirectAccountEntryId_PrevAndNext(
		Session session, AccountEntry accountEntry,
		long redirectAccountEntryId,
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

		query.append(_FINDER_COLUMN_REDIRECTACCOUNTENTRYID_REDIRECTACCOUNTENTRYID_2);

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

		qPos.add(redirectAccountEntryId);

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
	 * Removes all the account entries where redirectAccountEntryId = &#63; from the database.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 */
	@Override
	public void removeByRedirectAccountEntryId(long redirectAccountEntryId) {
		for (AccountEntry accountEntry : findByRedirectAccountEntryId(
				redirectAccountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(accountEntry);
		}
	}

	/**
	 * Returns the number of account entries where redirectAccountEntryId = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @return the number of matching account entries
	 */
	@Override
	public int countByRedirectAccountEntryId(long redirectAccountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_REDIRECTACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { redirectAccountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_REDIRECTACCOUNTENTRYID_REDIRECTACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

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

	private static final String _FINDER_COLUMN_REDIRECTACCOUNTENTRYID_REDIRECTACCOUNTENTRYID_2 =
		"accountEntry.redirectAccountEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARTNERENTRYID =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPartnerEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPartnerEntryId",
			new String[] { Long.class.getName() },
			AccountEntryModelImpl.PARTNERENTRYID_COLUMN_BITMASK |
			AccountEntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARTNERENTRYID = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPartnerEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the account entries where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @return the matching account entries
	 */
	@Override
	public List<AccountEntry> findByPartnerEntryId(long partnerEntryId) {
		return findByPartnerEntryId(partnerEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where partnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByPartnerEntryId(long partnerEntryId,
		int start, int end) {
		return findByPartnerEntryId(partnerEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where partnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByPartnerEntryId(long partnerEntryId,
		int start, int end, OrderByComparator<AccountEntry> orderByComparator) {
		return findByPartnerEntryId(partnerEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries where partnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByPartnerEntryId(long partnerEntryId,
		int start, int end, OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID;
			finderArgs = new Object[] { partnerEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARTNERENTRYID;
			finderArgs = new Object[] {
					partnerEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEntry accountEntry : list) {
					if ((partnerEntryId != accountEntry.getPartnerEntryId())) {
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

			query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

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

				qPos.add(partnerEntryId);

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
	 * Returns the first account entry in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByPartnerEntryId_First(long partnerEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByPartnerEntryId_First(partnerEntryId,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByPartnerEntryId_First(long partnerEntryId,
		OrderByComparator<AccountEntry> orderByComparator) {
		List<AccountEntry> list = findByPartnerEntryId(partnerEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByPartnerEntryId_Last(long partnerEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByPartnerEntryId_Last(partnerEntryId,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByPartnerEntryId_Last(long partnerEntryId,
		OrderByComparator<AccountEntry> orderByComparator) {
		int count = countByPartnerEntryId(partnerEntryId);

		if (count == 0) {
			return null;
		}

		List<AccountEntry> list = findByPartnerEntryId(partnerEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry[] findByPartnerEntryId_PrevAndNext(
		long accountEntryId, long partnerEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByPartnerEntryId_PrevAndNext(session, accountEntry,
					partnerEntryId, orderByComparator, true);

			array[1] = accountEntry;

			array[2] = getByPartnerEntryId_PrevAndNext(session, accountEntry,
					partnerEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEntry getByPartnerEntryId_PrevAndNext(Session session,
		AccountEntry accountEntry, long partnerEntryId,
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

		query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

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

		qPos.add(partnerEntryId);

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
	 * Removes all the account entries where partnerEntryId = &#63; from the database.
	 *
	 * @param partnerEntryId the partner entry ID
	 */
	@Override
	public void removeByPartnerEntryId(long partnerEntryId) {
		for (AccountEntry accountEntry : findByPartnerEntryId(partnerEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountEntry);
		}
	}

	/**
	 * Returns the number of account entries where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @return the number of matching account entries
	 */
	@Override
	public int countByPartnerEntryId(long partnerEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PARTNERENTRYID;

		Object[] finderArgs = new Object[] { partnerEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(partnerEntryId);

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

	private static final String _FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2 = "accountEntry.partnerEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_T = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountEntryModelImpl.USERID_COLUMN_BITMASK |
			AccountEntryModelImpl.TYPE_COLUMN_BITMASK |
			AccountEntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_T = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_T",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the account entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the matching account entries
	 */
	@Override
	public List<AccountEntry> findByU_T(long userId, int type) {
		return findByU_T(userId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the account entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByU_T(long userId, int type, int start,
		int end) {
		return findByU_T(userId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByU_T(long userId, int type, int start,
		int end, OrderByComparator<AccountEntry> orderByComparator) {
		return findByU_T(userId, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByU_T(long userId, int type, int start,
		int end, OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T;
			finderArgs = new Object[] { userId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_T;
			finderArgs = new Object[] {
					userId, type,
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEntry accountEntry : list) {
					if ((userId != accountEntry.getUserId()) ||
							(type != accountEntry.getType())) {
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

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_T_USERID_2);

			query.append(_FINDER_COLUMN_U_T_TYPE_2);

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

				qPos.add(userId);

				qPos.add(type);

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
	 * Returns the first account entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByU_T_First(long userId, int type,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByU_T_First(userId, type,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByU_T_First(long userId, int type,
		OrderByComparator<AccountEntry> orderByComparator) {
		List<AccountEntry> list = findByU_T(userId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByU_T_Last(long userId, int type,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByU_T_Last(userId, type,
				orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByU_T_Last(long userId, int type,
		OrderByComparator<AccountEntry> orderByComparator) {
		int count = countByU_T(userId, type);

		if (count == 0) {
			return null;
		}

		List<AccountEntry> list = findByU_T(userId, type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry[] findByU_T_PrevAndNext(long accountEntryId,
		long userId, int type, OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByU_T_PrevAndNext(session, accountEntry, userId,
					type, orderByComparator, true);

			array[1] = accountEntry;

			array[2] = getByU_T_PrevAndNext(session, accountEntry, userId,
					type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEntry getByU_T_PrevAndNext(Session session,
		AccountEntry accountEntry, long userId, int type,
		OrderByComparator<AccountEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		query.append(_FINDER_COLUMN_U_T_USERID_2);

		query.append(_FINDER_COLUMN_U_T_TYPE_2);

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

		qPos.add(userId);

		qPos.add(type);

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
	 * Removes all the account entries where userId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param type the type
	 */
	@Override
	public void removeByU_T(long userId, int type) {
		for (AccountEntry accountEntry : findByU_T(userId, type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountEntry);
		}
	}

	/**
	 * Returns the number of account entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the number of matching account entries
	 */
	@Override
	public int countByU_T(long userId, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_T;

		Object[] finderArgs = new Object[] { userId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_T_USERID_2);

			query.append(_FINDER_COLUMN_U_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_U_T_USERID_2 = "accountEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_T_TYPE_2 = "accountEntry.type = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_S = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRAEI_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RAEI_S =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRAEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountEntryModelImpl.REDIRECTACCOUNTENTRYID_COLUMN_BITMASK |
			AccountEntryModelImpl.STATUS_COLUMN_BITMASK |
			AccountEntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RAEI_S = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRAEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_S = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByRAEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @return the matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int status) {
		return findByRAEI_S(redirectAccountEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int status, int start, int end) {
		return findByRAEI_S(redirectAccountEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int status, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return findByRAEI_S(redirectAccountEntryId, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int status, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RAEI_S;
			finderArgs = new Object[] { redirectAccountEntryId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_S;
			finderArgs = new Object[] {
					redirectAccountEntryId, status,
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEntry accountEntry : list) {
					if ((redirectAccountEntryId != accountEntry.getRedirectAccountEntryId()) ||
							(status != accountEntry.getStatus())) {
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

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_RAEI_S_STATUS_2);

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

				qPos.add(redirectAccountEntryId);

				qPos.add(status);

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
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByRAEI_S_First(long redirectAccountEntryId,
		int status, OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByRAEI_S_First(redirectAccountEntryId,
				status, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByRAEI_S_First(long redirectAccountEntryId,
		int status, OrderByComparator<AccountEntry> orderByComparator) {
		List<AccountEntry> list = findByRAEI_S(redirectAccountEntryId, status,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByRAEI_S_Last(long redirectAccountEntryId,
		int status, OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByRAEI_S_Last(redirectAccountEntryId,
				status, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByRAEI_S_Last(long redirectAccountEntryId,
		int status, OrderByComparator<AccountEntry> orderByComparator) {
		int count = countByRAEI_S(redirectAccountEntryId, status);

		if (count == 0) {
			return null;
		}

		List<AccountEntry> list = findByRAEI_S(redirectAccountEntryId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry[] findByRAEI_S_PrevAndNext(long accountEntryId,
		long redirectAccountEntryId, int status,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByRAEI_S_PrevAndNext(session, accountEntry,
					redirectAccountEntryId, status, orderByComparator, true);

			array[1] = accountEntry;

			array[2] = getByRAEI_S_PrevAndNext(session, accountEntry,
					redirectAccountEntryId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEntry getByRAEI_S_PrevAndNext(Session session,
		AccountEntry accountEntry, long redirectAccountEntryId, int status,
		OrderByComparator<AccountEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		query.append(_FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_RAEI_S_STATUS_2);

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

		qPos.add(redirectAccountEntryId);

		qPos.add(status);

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
	 * Returns all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param statuses the statuses
	 * @return the matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int[] statuses) {
		return findByRAEI_S(redirectAccountEntryId, statuses,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int[] statuses, int start, int end) {
		return findByRAEI_S(redirectAccountEntryId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int[] statuses, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return findByRAEI_S(redirectAccountEntryId, statuses, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int[] statuses, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		if (statuses.length == 1) {
			return findByRAEI_S(redirectAccountEntryId, statuses[0], start,
				end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					redirectAccountEntryId, StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					redirectAccountEntryId, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_S,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEntry accountEntry : list) {
					if ((redirectAccountEntryId != accountEntry.getRedirectAccountEntryId()) ||
							!ArrayUtil.contains(statuses,
								accountEntry.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_RAEI_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

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

				qPos.add(redirectAccountEntryId);

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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_S,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_S,
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
	 * Removes all the account entries where redirectAccountEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 */
	@Override
	public void removeByRAEI_S(long redirectAccountEntryId, int status) {
		for (AccountEntry accountEntry : findByRAEI_S(redirectAccountEntryId,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountEntry);
		}
	}

	/**
	 * Returns the number of account entries where redirectAccountEntryId = &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param status the status
	 * @return the number of matching account entries
	 */
	@Override
	public int countByRAEI_S(long redirectAccountEntryId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RAEI_S;

		Object[] finderArgs = new Object[] { redirectAccountEntryId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_RAEI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				qPos.add(status);

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
	 * Returns the number of account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param statuses the statuses
	 * @return the number of matching account entries
	 */
	@Override
	public int countByRAEI_S(long redirectAccountEntryId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		Object[] finderArgs = new Object[] {
				redirectAccountEntryId, StringUtil.merge(statuses)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_RAEI_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_S,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_RAEI_S_REDIRECTACCOUNTENTRYID_2 = "accountEntry.redirectAccountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_RAEI_S_STATUS_2 = "accountEntry.status = ?";
	private static final String _FINDER_COLUMN_RAEI_S_STATUS_7 = "accountEntry.status IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_NOTT_S =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRAEI_NotT_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_NOTT_S =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByRAEI_NotT_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @return the matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int type, int status) {
		return findByRAEI_NotT_S(redirectAccountEntryId, type, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int type, int status, int start, int end) {
		return findByRAEI_NotT_S(redirectAccountEntryId, type, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int type, int status, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return findByRAEI_NotT_S(redirectAccountEntryId, type, status, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int type, int status, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_NOTT_S;
		finderArgs = new Object[] {
				redirectAccountEntryId, type, status,
				
				start, end, orderByComparator
			};

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEntry accountEntry : list) {
					if ((redirectAccountEntryId != accountEntry.getRedirectAccountEntryId()) ||
							(type == accountEntry.getType()) ||
							(status != accountEntry.getStatus())) {
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

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_TYPE_2);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_STATUS_2);

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

				qPos.add(redirectAccountEntryId);

				qPos.add(type);

				qPos.add(status);

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
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByRAEI_NotT_S_First(long redirectAccountEntryId,
		int type, int status, OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByRAEI_NotT_S_First(redirectAccountEntryId,
				type, status, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByRAEI_NotT_S_First(long redirectAccountEntryId,
		int type, int status, OrderByComparator<AccountEntry> orderByComparator) {
		List<AccountEntry> list = findByRAEI_NotT_S(redirectAccountEntryId,
				type, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByRAEI_NotT_S_Last(long redirectAccountEntryId,
		int type, int status, OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByRAEI_NotT_S_Last(redirectAccountEntryId,
				type, status, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByRAEI_NotT_S_Last(long redirectAccountEntryId,
		int type, int status, OrderByComparator<AccountEntry> orderByComparator) {
		int count = countByRAEI_NotT_S(redirectAccountEntryId, type, status);

		if (count == 0) {
			return null;
		}

		List<AccountEntry> list = findByRAEI_NotT_S(redirectAccountEntryId,
				type, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry[] findByRAEI_NotT_S_PrevAndNext(long accountEntryId,
		long redirectAccountEntryId, int type, int status,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByRAEI_NotT_S_PrevAndNext(session, accountEntry,
					redirectAccountEntryId, type, status, orderByComparator,
					true);

			array[1] = accountEntry;

			array[2] = getByRAEI_NotT_S_PrevAndNext(session, accountEntry,
					redirectAccountEntryId, type, status, orderByComparator,
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

	protected AccountEntry getByRAEI_NotT_S_PrevAndNext(Session session,
		AccountEntry accountEntry, long redirectAccountEntryId, int type,
		int status, OrderByComparator<AccountEntry> orderByComparator,
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

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		query.append(_FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_RAEI_NOTT_S_TYPE_2);

		query.append(_FINDER_COLUMN_RAEI_NOTT_S_STATUS_2);

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

		qPos.add(redirectAccountEntryId);

		qPos.add(type);

		qPos.add(status);

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
	 * Returns all the account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param types the types
	 * @param statuses the statuses
	 * @return the matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int[] types, int[] statuses) {
		return findByRAEI_NotT_S(redirectAccountEntryId, types, statuses,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param types the types
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int[] types, int[] statuses, int start, int end) {
		return findByRAEI_NotT_S(redirectAccountEntryId, types, statuses,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param types the types
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int[] types, int[] statuses, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return findByRAEI_NotT_S(redirectAccountEntryId, types, statuses,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByRAEI_NotT_S(long redirectAccountEntryId,
		int[] types, int[] statuses, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		if (types == null) {
			types = new int[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.unique(types);

			Arrays.sort(types);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		if ((types.length == 1) && (statuses.length == 1)) {
			return findByRAEI_NotT_S(redirectAccountEntryId, types[0],
				statuses[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					redirectAccountEntryId, StringUtil.merge(types),
					StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					redirectAccountEntryId, StringUtil.merge(types),
					StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_NOTT_S,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEntry accountEntry : list) {
					if ((redirectAccountEntryId != accountEntry.getRedirectAccountEntryId()) ||
							!ArrayUtil.contains(types, accountEntry.getType()) ||
							!ArrayUtil.contains(statuses,
								accountEntry.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_2);

			if (types.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_RAEI_NOTT_S_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_RAEI_NOTT_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

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

				qPos.add(redirectAccountEntryId);

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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_NOTT_S,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_RAEI_NOTT_S,
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
	 * Removes all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63; from the database.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 */
	@Override
	public void removeByRAEI_NotT_S(long redirectAccountEntryId, int type,
		int status) {
		for (AccountEntry accountEntry : findByRAEI_NotT_S(
				redirectAccountEntryId, type, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(accountEntry);
		}
	}

	/**
	 * Returns the number of account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param type the type
	 * @param status the status
	 * @return the number of matching account entries
	 */
	@Override
	public int countByRAEI_NotT_S(long redirectAccountEntryId, int type,
		int status) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_NOTT_S;

		Object[] finderArgs = new Object[] { redirectAccountEntryId, type, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_TYPE_2);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				qPos.add(type);

				qPos.add(status);

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
	 * Returns the number of account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	 *
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param types the types
	 * @param statuses the statuses
	 * @return the number of matching account entries
	 */
	@Override
	public int countByRAEI_NotT_S(long redirectAccountEntryId, int[] types,
		int[] statuses) {
		if (types == null) {
			types = new int[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.unique(types);

			Arrays.sort(types);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		Object[] finderArgs = new Object[] {
				redirectAccountEntryId, StringUtil.merge(types),
				StringUtil.merge(statuses)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_NOTT_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_2);

			if (types.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_RAEI_NOTT_S_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_RAEI_NOTT_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(redirectAccountEntryId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_NOTT_S,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_RAEI_NOTT_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_RAEI_NOTT_S_REDIRECTACCOUNTENTRYID_2 =
		"accountEntry.redirectAccountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_RAEI_NOTT_S_TYPE_2 = "accountEntry.type != ? AND ";
	private static final String _FINDER_COLUMN_RAEI_NOTT_S_TYPE_7 = "accountEntry.type NOT IN (";
	private static final String _FINDER_COLUMN_RAEI_NOTT_S_STATUS_2 = "accountEntry.status = ?";
	private static final String _FINDER_COLUMN_RAEI_NOTT_S_STATUS_7 = "accountEntry.status IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_N_C_RAEI = new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, AccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByN_C_RAEI",
			new String[] {
				String.class.getName(), String.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_N_C_RAEI =
		new FinderPath(AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			AccountEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByN_C_RAEI",
			new String[] {
				String.class.getName(), String.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @return the matching account entries
	 */
	@Override
	public List<AccountEntry> findByN_C_RAEI(String name, String code,
		long redirectAccountEntryId) {
		return findByN_C_RAEI(name, code, redirectAccountEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByN_C_RAEI(String name, String code,
		long redirectAccountEntryId, int start, int end) {
		return findByN_C_RAEI(name, code, redirectAccountEntryId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByN_C_RAEI(String name, String code,
		long redirectAccountEntryId, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return findByN_C_RAEI(name, code, redirectAccountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account entries
	 */
	@Override
	public List<AccountEntry> findByN_C_RAEI(String name, String code,
		long redirectAccountEntryId, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_N_C_RAEI;
		finderArgs = new Object[] {
				name, code, redirectAccountEntryId,
				
				start, end, orderByComparator
			};

		List<AccountEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEntry accountEntry : list) {
					if (!StringUtil.wildcardMatches(accountEntry.getName(),
								name, CharPool.UNDERLINE, CharPool.PERCENT,
								CharPool.BACK_SLASH, false) ||
							!StringUtil.wildcardMatches(
								accountEntry.getCode(), code,
								CharPool.UNDERLINE, CharPool.PERCENT,
								CharPool.BACK_SLASH, false) ||
							(redirectAccountEntryId != accountEntry.getRedirectAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_N_C_RAEI_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_N_C_RAEI_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_N_C_RAEI_NAME_2);
			}

			boolean bindCode = false;

			if (code == null) {
				query.append(_FINDER_COLUMN_N_C_RAEI_CODE_1);
			}
			else if (code.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_N_C_RAEI_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_N_C_RAEI_CODE_2);
			}

			query.append(_FINDER_COLUMN_N_C_RAEI_REDIRECTACCOUNTENTRYID_2);

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

				if (bindName) {
					qPos.add(StringUtil.toLowerCase(name));
				}

				if (bindCode) {
					qPos.add(StringUtil.toLowerCase(code));
				}

				qPos.add(redirectAccountEntryId);

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
	 * Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByN_C_RAEI_First(String name, String code,
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByN_C_RAEI_First(name, code,
				redirectAccountEntryId, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", code=");
		msg.append(code);

		msg.append(", redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByN_C_RAEI_First(String name, String code,
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator) {
		List<AccountEntry> list = findByN_C_RAEI(name, code,
				redirectAccountEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	@Override
	public AccountEntry findByN_C_RAEI_Last(String name, String code,
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = fetchByN_C_RAEI_Last(name, code,
				redirectAccountEntryId, orderByComparator);

		if (accountEntry != null) {
			return accountEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", code=");
		msg.append(code);

		msg.append(", redirectAccountEntryId=");
		msg.append(redirectAccountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEntryException(msg.toString());
	}

	/**
	 * Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	@Override
	public AccountEntry fetchByN_C_RAEI_Last(String name, String code,
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator) {
		int count = countByN_C_RAEI(name, code, redirectAccountEntryId);

		if (count == 0) {
			return null;
		}

		List<AccountEntry> list = findByN_C_RAEI(name, code,
				redirectAccountEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	@Override
	public AccountEntry[] findByN_C_RAEI_PrevAndNext(long accountEntryId,
		String name, String code, long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws NoSuchAccountEntryException {
		AccountEntry accountEntry = findByPrimaryKey(accountEntryId);

		Session session = null;

		try {
			session = openSession();

			AccountEntry[] array = new AccountEntryImpl[3];

			array[0] = getByN_C_RAEI_PrevAndNext(session, accountEntry, name,
					code, redirectAccountEntryId, orderByComparator, true);

			array[1] = accountEntry;

			array[2] = getByN_C_RAEI_PrevAndNext(session, accountEntry, name,
					code, redirectAccountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEntry getByN_C_RAEI_PrevAndNext(Session session,
		AccountEntry accountEntry, String name, String code,
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ACCOUNTENTRY_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_N_C_RAEI_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_N_C_RAEI_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_N_C_RAEI_NAME_2);
		}

		boolean bindCode = false;

		if (code == null) {
			query.append(_FINDER_COLUMN_N_C_RAEI_CODE_1);
		}
		else if (code.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_N_C_RAEI_CODE_3);
		}
		else {
			bindCode = true;

			query.append(_FINDER_COLUMN_N_C_RAEI_CODE_2);
		}

		query.append(_FINDER_COLUMN_N_C_RAEI_REDIRECTACCOUNTENTRYID_2);

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

		if (bindName) {
			qPos.add(StringUtil.toLowerCase(name));
		}

		if (bindCode) {
			qPos.add(StringUtil.toLowerCase(code));
		}

		qPos.add(redirectAccountEntryId);

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
	 * Removes all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63; from the database.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 */
	@Override
	public void removeByN_C_RAEI(String name, String code,
		long redirectAccountEntryId) {
		for (AccountEntry accountEntry : findByN_C_RAEI(name, code,
				redirectAccountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(accountEntry);
		}
	}

	/**
	 * Returns the number of account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param redirectAccountEntryId the redirect account entry ID
	 * @return the number of matching account entries
	 */
	@Override
	public int countByN_C_RAEI(String name, String code,
		long redirectAccountEntryId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_N_C_RAEI;

		Object[] finderArgs = new Object[] { name, code, redirectAccountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACCOUNTENTRY_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_N_C_RAEI_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_N_C_RAEI_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_N_C_RAEI_NAME_2);
			}

			boolean bindCode = false;

			if (code == null) {
				query.append(_FINDER_COLUMN_N_C_RAEI_CODE_1);
			}
			else if (code.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_N_C_RAEI_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_N_C_RAEI_CODE_2);
			}

			query.append(_FINDER_COLUMN_N_C_RAEI_REDIRECTACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(StringUtil.toLowerCase(name));
				}

				if (bindCode) {
					qPos.add(StringUtil.toLowerCase(code));
				}

				qPos.add(redirectAccountEntryId);

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

	private static final String _FINDER_COLUMN_N_C_RAEI_NAME_1 = "accountEntry.name IS NULL AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_NAME_2 = "lower(accountEntry.name) LIKE ? AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_NAME_3 = "(accountEntry.name IS NULL OR accountEntry.name LIKE '') AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_CODE_1 = "accountEntry.code IS NULL AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_CODE_2 = "lower(accountEntry.code) LIKE ? AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_CODE_3 = "(accountEntry.code IS NULL OR accountEntry.code LIKE '') AND ";
	private static final String _FINDER_COLUMN_N_C_RAEI_REDIRECTACCOUNTENTRYID_2 =
		"accountEntry.redirectAccountEntryId = ?";

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

		finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTUUID,
			new Object[] { accountEntry.getCorpProjectUuid() }, accountEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID,
			new Object[] { accountEntry.getCorpProjectId() }, accountEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CODE,
			new Object[] { accountEntry.getCode() }, accountEntry);

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
		Object[] args = new Object[] { accountEntryModelImpl.getCorpProjectUuid() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_CORPPROJECTUUID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTUUID, args,
			accountEntryModelImpl, false);

		args = new Object[] { accountEntryModelImpl.getCorpProjectId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_CORPPROJECTID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CORPPROJECTID, args,
			accountEntryModelImpl, false);

		args = new Object[] { accountEntryModelImpl.getCode() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_CODE, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CODE, args,
			accountEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AccountEntryModelImpl accountEntryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					accountEntryModelImpl.getCorpProjectUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTUUID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTUUID, args);
		}

		if ((accountEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CORPPROJECTUUID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					accountEntryModelImpl.getOriginalCorpProjectUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTUUID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTUUID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					accountEntryModelImpl.getCorpProjectId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTID, args);
		}

		if ((accountEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CORPPROJECTID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					accountEntryModelImpl.getOriginalCorpProjectId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CORPPROJECTID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { accountEntryModelImpl.getCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CODE, args);
		}

		if ((accountEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { accountEntryModelImpl.getOriginalCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CODE, args);
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

		accountEntryToSupportTeamTableMapper.deleteLeftPrimaryKeyTableMappings(accountEntry.getPrimaryKey());

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
			Object[] args = new Object[] { accountEntryModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
				args);

			args = new Object[] {
					accountEntryModelImpl.getRedirectAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_REDIRECTACCOUNTENTRYID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID,
				args);

			args = new Object[] { accountEntryModelImpl.getPartnerEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID,
				args);

			args = new Object[] {
					accountEntryModelImpl.getUserId(),
					accountEntryModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T,
				args);

			args = new Object[] {
					accountEntryModelImpl.getRedirectAccountEntryId(),
					accountEntryModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RAEI_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RAEI_S,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountEntryModelImpl.getOriginalName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { accountEntryModelImpl.getName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}

			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountEntryModelImpl.getOriginalRedirectAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_REDIRECTACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID,
					args);

				args = new Object[] {
						accountEntryModelImpl.getRedirectAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_REDIRECTACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REDIRECTACCOUNTENTRYID,
					args);
			}

			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountEntryModelImpl.getOriginalPartnerEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID,
					args);

				args = new Object[] { accountEntryModelImpl.getPartnerEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID,
					args);
			}

			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountEntryModelImpl.getOriginalUserId(),
						accountEntryModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T,
					args);

				args = new Object[] {
						accountEntryModelImpl.getUserId(),
						accountEntryModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T,
					args);
			}

			if ((accountEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RAEI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountEntryModelImpl.getOriginalRedirectAccountEntryId(),
						accountEntryModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RAEI_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RAEI_S,
					args);

				args = new Object[] {
						accountEntryModelImpl.getRedirectAccountEntryId(),
						accountEntryModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RAEI_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RAEI_S,
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
		accountEntryImpl.setCorpProjectUuid(accountEntry.getCorpProjectUuid());
		accountEntryImpl.setCorpProjectId(accountEntry.getCorpProjectId());
		accountEntryImpl.setCorpEntryName(accountEntry.getCorpEntryName());
		accountEntryImpl.setName(accountEntry.getName());
		accountEntryImpl.setCode(accountEntry.getCode());
		accountEntryImpl.setRedirectAccountEntryId(accountEntry.getRedirectAccountEntryId());
		accountEntryImpl.setType(accountEntry.getType());
		accountEntryImpl.setIndustry(accountEntry.getIndustry());
		accountEntryImpl.setCountryId(accountEntry.getCountryId());
		accountEntryImpl.setPartnerEntryId(accountEntry.getPartnerEntryId());
		accountEntryImpl.setPartnerManagedSupport(accountEntry.isPartnerManagedSupport());
		accountEntryImpl.setTier(accountEntry.getTier());
		accountEntryImpl.setMaxCustomers(accountEntry.getMaxCustomers());
		accountEntryImpl.setInstructions(accountEntry.getInstructions());
		accountEntryImpl.setNotes(accountEntry.getNotes());
		accountEntryImpl.setHighestSupportResponseId(accountEntry.getHighestSupportResponseId());
		accountEntryImpl.setLastAuditDate(accountEntry.getLastAuditDate());
		accountEntryImpl.setStatus(accountEntry.getStatus());
		accountEntryImpl.setStatusByUserId(accountEntry.getStatusByUserId());
		accountEntryImpl.setStatusByUserName(accountEntry.getStatusByUserName());
		accountEntryImpl.setStatusDate(accountEntry.getStatusDate());
		accountEntryImpl.setStatusMessage(accountEntry.getStatusMessage());

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

	/**
	 * Returns the primaryKeys of support teams associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @return long[] of the primaryKeys of support teams associated with the account entry
	 */
	@Override
	public long[] getSupportTeamPrimaryKeys(long pk) {
		long[] pks = accountEntryToSupportTeamTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the support teams associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @return the support teams associated with the account entry
	 */
	@Override
	public List<com.liferay.osb.model.SupportTeam> getSupportTeams(long pk) {
		return getSupportTeams(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the support teams associated with the account entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the account entry
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of support teams associated with the account entry
	 */
	@Override
	public List<com.liferay.osb.model.SupportTeam> getSupportTeams(long pk,
		int start, int end) {
		return getSupportTeams(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support teams associated with the account entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the account entry
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support teams associated with the account entry
	 */
	@Override
	public List<com.liferay.osb.model.SupportTeam> getSupportTeams(long pk,
		int start, int end,
		OrderByComparator<com.liferay.osb.model.SupportTeam> orderByComparator) {
		return accountEntryToSupportTeamTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of support teams associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @return the number of support teams associated with the account entry
	 */
	@Override
	public int getSupportTeamsSize(long pk) {
		long[] pks = accountEntryToSupportTeamTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the support team is associated with the account entry.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPK the primary key of the support team
	 * @return <code>true</code> if the support team is associated with the account entry; <code>false</code> otherwise
	 */
	@Override
	public boolean containsSupportTeam(long pk, long supportTeamPK) {
		return accountEntryToSupportTeamTableMapper.containsTableMapping(pk,
			supportTeamPK);
	}

	/**
	 * Returns <code>true</code> if the account entry has any support teams associated with it.
	 *
	 * @param pk the primary key of the account entry to check for associations with support teams
	 * @return <code>true</code> if the account entry has any support teams associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsSupportTeams(long pk) {
		if (getSupportTeamsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPK the primary key of the support team
	 */
	@Override
	public void addSupportTeam(long pk, long supportTeamPK) {
		AccountEntry accountEntry = fetchByPrimaryKey(pk);

		if (accountEntry == null) {
			accountEntryToSupportTeamTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, supportTeamPK);
		}
		else {
			accountEntryToSupportTeamTableMapper.addTableMapping(accountEntry.getCompanyId(),
				pk, supportTeamPK);
		}
	}

	/**
	 * Adds an association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeam the support team
	 */
	@Override
	public void addSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam) {
		AccountEntry accountEntry = fetchByPrimaryKey(pk);

		if (accountEntry == null) {
			accountEntryToSupportTeamTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, supportTeam.getPrimaryKey());
		}
		else {
			accountEntryToSupportTeamTableMapper.addTableMapping(accountEntry.getCompanyId(),
				pk, supportTeam.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPKs the primary keys of the support teams
	 */
	@Override
	public void addSupportTeams(long pk, long[] supportTeamPKs) {
		long companyId = 0;

		AccountEntry accountEntry = fetchByPrimaryKey(pk);

		if (accountEntry == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = accountEntry.getCompanyId();
		}

		accountEntryToSupportTeamTableMapper.addTableMappings(companyId, pk,
			supportTeamPKs);
	}

	/**
	 * Adds an association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeams the support teams
	 */
	@Override
	public void addSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams) {
		addSupportTeams(pk,
			ListUtil.toLongArray(supportTeams,
				com.liferay.osb.model.SupportTeam.SUPPORT_TEAM_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the account entry and its support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry to clear the associated support teams from
	 */
	@Override
	public void clearSupportTeams(long pk) {
		accountEntryToSupportTeamTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPK the primary key of the support team
	 */
	@Override
	public void removeSupportTeam(long pk, long supportTeamPK) {
		accountEntryToSupportTeamTableMapper.deleteTableMapping(pk,
			supportTeamPK);
	}

	/**
	 * Removes the association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeam the support team
	 */
	@Override
	public void removeSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam) {
		accountEntryToSupportTeamTableMapper.deleteTableMapping(pk,
			supportTeam.getPrimaryKey());
	}

	/**
	 * Removes the association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPKs the primary keys of the support teams
	 */
	@Override
	public void removeSupportTeams(long pk, long[] supportTeamPKs) {
		accountEntryToSupportTeamTableMapper.deleteTableMappings(pk,
			supportTeamPKs);
	}

	/**
	 * Removes the association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeams the support teams
	 */
	@Override
	public void removeSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams) {
		removeSupportTeams(pk,
			ListUtil.toLongArray(supportTeams,
				com.liferay.osb.model.SupportTeam.SUPPORT_TEAM_ID_ACCESSOR));
	}

	/**
	 * Sets the support teams associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeamPKs the primary keys of the support teams to be associated with the account entry
	 */
	@Override
	public void setSupportTeams(long pk, long[] supportTeamPKs) {
		Set<Long> newSupportTeamPKsSet = SetUtil.fromArray(supportTeamPKs);
		Set<Long> oldSupportTeamPKsSet = SetUtil.fromArray(accountEntryToSupportTeamTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeSupportTeamPKsSet = new HashSet<Long>(oldSupportTeamPKsSet);

		removeSupportTeamPKsSet.removeAll(newSupportTeamPKsSet);

		accountEntryToSupportTeamTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeSupportTeamPKsSet));

		newSupportTeamPKsSet.removeAll(oldSupportTeamPKsSet);

		long companyId = 0;

		AccountEntry accountEntry = fetchByPrimaryKey(pk);

		if (accountEntry == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = accountEntry.getCompanyId();
		}

		accountEntryToSupportTeamTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newSupportTeamPKsSet));
	}

	/**
	 * Sets the support teams associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the account entry
	 * @param supportTeams the support teams to be associated with the account entry
	 */
	@Override
	public void setSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams) {
		try {
			long[] supportTeamPKs = new long[supportTeams.size()];

			for (int i = 0; i < supportTeams.size(); i++) {
				com.liferay.osb.model.SupportTeam supportTeam = supportTeams.get(i);

				supportTeamPKs[i] = supportTeam.getPrimaryKey();
			}

			setSupportTeams(pk, supportTeamPKs);
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

		accountEntryToSupportTeamTableMapper = TableMapperFactory.getTableMapper("OSB_AccountEntries_SupportTeams",
				"companyId", "accountEntryId", "supportTeamId", this,
				supportTeamPersistence);
	}

	public void destroy() {
		entityCache.removeCache(AccountEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"OSB_AccountEntries_SupportRegions");
		TableMapperFactory.removeTableMapper("OSB_AccountEntries_SupportTeams");
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	protected TableMapper<AccountEntry, com.liferay.osb.model.SupportRegion> accountEntryToSupportRegionTableMapper;
	@BeanReference(type = SupportTeamPersistence.class)
	protected SupportTeamPersistence supportTeamPersistence;
	protected TableMapper<AccountEntry, com.liferay.osb.model.SupportTeam> accountEntryToSupportTeamTableMapper;
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
				"code", "type"
			});
}
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

import com.liferay.osb.exception.NoSuchAccountEnvironmentException;
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.impl.AccountEnvironmentImpl;
import com.liferay.osb.model.impl.AccountEnvironmentModelImpl;
import com.liferay.osb.service.persistence.AccountEnvironmentPersistence;

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
 * The persistence implementation for the account environment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentPersistence
 * @see com.liferay.osb.service.persistence.AccountEnvironmentUtil
 * @generated
 */
@ProviderType
public class AccountEnvironmentPersistenceImpl extends BasePersistenceImpl<AccountEnvironment>
	implements AccountEnvironmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountEnvironmentUtil} to access the account environment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountEnvironmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			AccountEnvironmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountEnvironmentModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the account environments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account environments
	 */
	@Override
	public List<AccountEnvironment> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account environments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @return the range of matching account environments
	 */
	@Override
	public List<AccountEnvironment> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account environments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account environments
	 */
	@Override
	public List<AccountEnvironment> findByAccountEntryId(long accountEntryId,
		int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account environments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account environments
	 */
	@Override
	public List<AccountEnvironment> findByAccountEntryId(long accountEntryId,
		int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator,
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

		List<AccountEnvironment> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEnvironment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEnvironment accountEnvironment : list) {
					if ((accountEntryId != accountEnvironment.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENT_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountEnvironmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<AccountEnvironment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountEnvironment>)QueryUtil.list(q,
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
	 * Returns the first account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment
	 * @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	 */
	@Override
	public AccountEnvironment findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException {
		AccountEnvironment accountEnvironment = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountEnvironment != null) {
			return accountEnvironment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentException(msg.toString());
	}

	/**
	 * Returns the first account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	@Override
	public AccountEnvironment fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator) {
		List<AccountEnvironment> list = findByAccountEntryId(accountEntryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment
	 * @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	 */
	@Override
	public AccountEnvironment findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException {
		AccountEnvironment accountEnvironment = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountEnvironment != null) {
			return accountEnvironment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentException(msg.toString());
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	@Override
	public AccountEnvironment fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<AccountEnvironment> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account environments before and after the current account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEnvironmentId the primary key of the current account environment
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account environment
	 * @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 */
	@Override
	public AccountEnvironment[] findByAccountEntryId_PrevAndNext(
		long accountEnvironmentId, long accountEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException {
		AccountEnvironment accountEnvironment = findByPrimaryKey(accountEnvironmentId);

		Session session = null;

		try {
			session = openSession();

			AccountEnvironment[] array = new AccountEnvironmentImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session,
					accountEnvironment, accountEntryId, orderByComparator, true);

			array[1] = accountEnvironment;

			array[2] = getByAccountEntryId_PrevAndNext(session,
					accountEnvironment, accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEnvironment getByAccountEntryId_PrevAndNext(
		Session session, AccountEnvironment accountEnvironment,
		long accountEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator,
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

		query.append(_SQL_SELECT_ACCOUNTENVIRONMENT_WHERE);

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
			query.append(AccountEnvironmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEnvironment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEnvironment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account environments where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (AccountEnvironment accountEnvironment : findByAccountEntryId(
				accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountEnvironment);
		}
	}

	/**
	 * Returns the number of account environments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account environments
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENT_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountEnvironment.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_PEI = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_PEI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PEI =
		new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_PEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			AccountEnvironmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountEnvironmentModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			AccountEnvironmentModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_PEI = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_PEI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @return the matching account environments
	 */
	@Override
	public List<AccountEnvironment> findByAEI_PEI(long accountEntryId,
		long productEntryId) {
		return findByAEI_PEI(accountEntryId, productEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @return the range of matching account environments
	 */
	@Override
	public List<AccountEnvironment> findByAEI_PEI(long accountEntryId,
		long productEntryId, int start, int end) {
		return findByAEI_PEI(accountEntryId, productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account environments
	 */
	@Override
	public List<AccountEnvironment> findByAEI_PEI(long accountEntryId,
		long productEntryId, int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator) {
		return findByAEI_PEI(accountEntryId, productEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account environments
	 */
	@Override
	public List<AccountEnvironment> findByAEI_PEI(long accountEntryId,
		long productEntryId, int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PEI;
			finderArgs = new Object[] { accountEntryId, productEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_PEI;
			finderArgs = new Object[] {
					accountEntryId, productEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AccountEnvironment> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEnvironment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEnvironment accountEnvironment : list) {
					if ((accountEntryId != accountEnvironment.getAccountEntryId()) ||
							(productEntryId != accountEnvironment.getProductEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_PEI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_PEI_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountEnvironmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(productEntryId);

				if (!pagination) {
					list = (List<AccountEnvironment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountEnvironment>)QueryUtil.list(q,
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
	 * Returns the first account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment
	 * @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	 */
	@Override
	public AccountEnvironment findByAEI_PEI_First(long accountEntryId,
		long productEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException {
		AccountEnvironment accountEnvironment = fetchByAEI_PEI_First(accountEntryId,
				productEntryId, orderByComparator);

		if (accountEnvironment != null) {
			return accountEnvironment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentException(msg.toString());
	}

	/**
	 * Returns the first account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	@Override
	public AccountEnvironment fetchByAEI_PEI_First(long accountEntryId,
		long productEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator) {
		List<AccountEnvironment> list = findByAEI_PEI(accountEntryId,
				productEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment
	 * @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	 */
	@Override
	public AccountEnvironment findByAEI_PEI_Last(long accountEntryId,
		long productEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException {
		AccountEnvironment accountEnvironment = fetchByAEI_PEI_Last(accountEntryId,
				productEntryId, orderByComparator);

		if (accountEnvironment != null) {
			return accountEnvironment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentException(msg.toString());
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	@Override
	public AccountEnvironment fetchByAEI_PEI_Last(long accountEntryId,
		long productEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator) {
		int count = countByAEI_PEI(accountEntryId, productEntryId);

		if (count == 0) {
			return null;
		}

		List<AccountEnvironment> list = findByAEI_PEI(accountEntryId,
				productEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account environments before and after the current account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEnvironmentId the primary key of the current account environment
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account environment
	 * @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 */
	@Override
	public AccountEnvironment[] findByAEI_PEI_PrevAndNext(
		long accountEnvironmentId, long accountEntryId, long productEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException {
		AccountEnvironment accountEnvironment = findByPrimaryKey(accountEnvironmentId);

		Session session = null;

		try {
			session = openSession();

			AccountEnvironment[] array = new AccountEnvironmentImpl[3];

			array[0] = getByAEI_PEI_PrevAndNext(session, accountEnvironment,
					accountEntryId, productEntryId, orderByComparator, true);

			array[1] = accountEnvironment;

			array[2] = getByAEI_PEI_PrevAndNext(session, accountEnvironment,
					accountEntryId, productEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountEnvironment getByAEI_PEI_PrevAndNext(Session session,
		AccountEnvironment accountEnvironment, long accountEntryId,
		long productEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACCOUNTENVIRONMENT_WHERE);

		query.append(_FINDER_COLUMN_AEI_PEI_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_PEI_PRODUCTENTRYID_2);

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
			query.append(AccountEnvironmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(productEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEnvironment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEnvironment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account environments where accountEntryId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 */
	@Override
	public void removeByAEI_PEI(long accountEntryId, long productEntryId) {
		for (AccountEnvironment accountEnvironment : findByAEI_PEI(
				accountEntryId, productEntryId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(accountEnvironment);
		}
	}

	/**
	 * Returns the number of account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching account environments
	 */
	@Override
	public int countByAEI_PEI(long accountEntryId, long productEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_PEI;

		Object[] finderArgs = new Object[] { accountEntryId, productEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_PEI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_PEI_PRODUCTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

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

	private static final String _FINDER_COLUMN_AEI_PEI_ACCOUNTENTRYID_2 = "accountEnvironment.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_PEI_PRODUCTENTRYID_2 = "accountEnvironment.productEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_PEI_N = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAEI_PEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			AccountEnvironmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountEnvironmentModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			AccountEnvironmentModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_PEI_N = new FinderPath(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_PEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or throws a {@link NoSuchAccountEnvironmentException} if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the matching account environment
	 * @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	 */
	@Override
	public AccountEnvironment findByAEI_PEI_N(long accountEntryId,
		long productEntryId, String name)
		throws NoSuchAccountEnvironmentException {
		AccountEnvironment accountEnvironment = fetchByAEI_PEI_N(accountEntryId,
				productEntryId, name);

		if (accountEnvironment == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEntryId=");
			msg.append(accountEntryId);

			msg.append(", productEntryId=");
			msg.append(productEntryId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountEnvironmentException(msg.toString());
		}

		return accountEnvironment;
	}

	/**
	 * Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	@Override
	public AccountEnvironment fetchByAEI_PEI_N(long accountEntryId,
		long productEntryId, String name) {
		return fetchByAEI_PEI_N(accountEntryId, productEntryId, name, true);
	}

	/**
	 * Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	@Override
	public AccountEnvironment fetchByAEI_PEI_N(long accountEntryId,
		long productEntryId, String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { accountEntryId, productEntryId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_AEI_PEI_N,
					finderArgs, this);
		}

		if (result instanceof AccountEnvironment) {
			AccountEnvironment accountEnvironment = (AccountEnvironment)result;

			if ((accountEntryId != accountEnvironment.getAccountEntryId()) ||
					(productEntryId != accountEnvironment.getProductEntryId()) ||
					!Objects.equals(name, accountEnvironment.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_PEI_N_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_PEI_N_PRODUCTENTRYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(productEntryId);

				if (bindName) {
					qPos.add(name);
				}

				List<AccountEnvironment> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_PEI_N,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AccountEnvironmentPersistenceImpl.fetchByAEI_PEI_N(long, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AccountEnvironment accountEnvironment = list.get(0);

					result = accountEnvironment;

					cacheResult(accountEnvironment);

					if ((accountEnvironment.getAccountEntryId() != accountEntryId) ||
							(accountEnvironment.getProductEntryId() != productEntryId) ||
							(accountEnvironment.getName() == null) ||
							!accountEnvironment.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_PEI_N,
							finderArgs, accountEnvironment);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_PEI_N,
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
			return (AccountEnvironment)result;
		}
	}

	/**
	 * Removes the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the account environment that was removed
	 */
	@Override
	public AccountEnvironment removeByAEI_PEI_N(long accountEntryId,
		long productEntryId, String name)
		throws NoSuchAccountEnvironmentException {
		AccountEnvironment accountEnvironment = findByAEI_PEI_N(accountEntryId,
				productEntryId, name);

		return remove(accountEnvironment);
	}

	/**
	 * Returns the number of account environments where accountEntryId = &#63; and productEntryId = &#63; and name = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the number of matching account environments
	 */
	@Override
	public int countByAEI_PEI_N(long accountEntryId, long productEntryId,
		String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_PEI_N;

		Object[] finderArgs = new Object[] { accountEntryId, productEntryId, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_PEI_N_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_PEI_N_PRODUCTENTRYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_AEI_PEI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(productEntryId);

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

	private static final String _FINDER_COLUMN_AEI_PEI_N_ACCOUNTENTRYID_2 = "accountEnvironment.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_PEI_N_PRODUCTENTRYID_2 = "accountEnvironment.productEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_PEI_N_NAME_1 = "accountEnvironment.name IS NULL";
	private static final String _FINDER_COLUMN_AEI_PEI_N_NAME_2 = "accountEnvironment.name = ?";
	private static final String _FINDER_COLUMN_AEI_PEI_N_NAME_3 = "(accountEnvironment.name IS NULL OR accountEnvironment.name = '')";

	public AccountEnvironmentPersistenceImpl() {
		setModelClass(AccountEnvironment.class);
	}

	/**
	 * Caches the account environment in the entity cache if it is enabled.
	 *
	 * @param accountEnvironment the account environment
	 */
	@Override
	public void cacheResult(AccountEnvironment accountEnvironment) {
		entityCache.putResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentImpl.class, accountEnvironment.getPrimaryKey(),
			accountEnvironment);

		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_PEI_N,
			new Object[] {
				accountEnvironment.getAccountEntryId(),
				accountEnvironment.getProductEntryId(),
				accountEnvironment.getName()
			}, accountEnvironment);

		accountEnvironment.resetOriginalValues();
	}

	/**
	 * Caches the account environments in the entity cache if it is enabled.
	 *
	 * @param accountEnvironments the account environments
	 */
	@Override
	public void cacheResult(List<AccountEnvironment> accountEnvironments) {
		for (AccountEnvironment accountEnvironment : accountEnvironments) {
			if (entityCache.getResult(
						AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountEnvironmentImpl.class,
						accountEnvironment.getPrimaryKey()) == null) {
				cacheResult(accountEnvironment);
			}
			else {
				accountEnvironment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account environments.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountEnvironmentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account environment.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountEnvironment accountEnvironment) {
		entityCache.removeResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentImpl.class, accountEnvironment.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AccountEnvironmentModelImpl)accountEnvironment,
			true);
	}

	@Override
	public void clearCache(List<AccountEnvironment> accountEnvironments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountEnvironment accountEnvironment : accountEnvironments) {
			entityCache.removeResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountEnvironmentImpl.class, accountEnvironment.getPrimaryKey());

			clearUniqueFindersCache((AccountEnvironmentModelImpl)accountEnvironment,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		AccountEnvironmentModelImpl accountEnvironmentModelImpl) {
		Object[] args = new Object[] {
				accountEnvironmentModelImpl.getAccountEntryId(),
				accountEnvironmentModelImpl.getProductEntryId(),
				accountEnvironmentModelImpl.getName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_AEI_PEI_N, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_PEI_N, args,
			accountEnvironmentModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AccountEnvironmentModelImpl accountEnvironmentModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					accountEnvironmentModelImpl.getAccountEntryId(),
					accountEnvironmentModelImpl.getProductEntryId(),
					accountEnvironmentModelImpl.getName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_PEI_N, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_PEI_N, args);
		}

		if ((accountEnvironmentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_PEI_N.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					accountEnvironmentModelImpl.getOriginalAccountEntryId(),
					accountEnvironmentModelImpl.getOriginalProductEntryId(),
					accountEnvironmentModelImpl.getOriginalName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_PEI_N, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_PEI_N, args);
		}
	}

	/**
	 * Creates a new account environment with the primary key. Does not add the account environment to the database.
	 *
	 * @param accountEnvironmentId the primary key for the new account environment
	 * @return the new account environment
	 */
	@Override
	public AccountEnvironment create(long accountEnvironmentId) {
		AccountEnvironment accountEnvironment = new AccountEnvironmentImpl();

		accountEnvironment.setNew(true);
		accountEnvironment.setPrimaryKey(accountEnvironmentId);

		return accountEnvironment;
	}

	/**
	 * Removes the account environment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment that was removed
	 * @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 */
	@Override
	public AccountEnvironment remove(long accountEnvironmentId)
		throws NoSuchAccountEnvironmentException {
		return remove((Serializable)accountEnvironmentId);
	}

	/**
	 * Removes the account environment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account environment
	 * @return the account environment that was removed
	 * @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 */
	@Override
	public AccountEnvironment remove(Serializable primaryKey)
		throws NoSuchAccountEnvironmentException {
		Session session = null;

		try {
			session = openSession();

			AccountEnvironment accountEnvironment = (AccountEnvironment)session.get(AccountEnvironmentImpl.class,
					primaryKey);

			if (accountEnvironment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountEnvironmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountEnvironment);
		}
		catch (NoSuchAccountEnvironmentException nsee) {
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
	protected AccountEnvironment removeImpl(
		AccountEnvironment accountEnvironment) {
		accountEnvironment = toUnwrappedModel(accountEnvironment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountEnvironment)) {
				accountEnvironment = (AccountEnvironment)session.get(AccountEnvironmentImpl.class,
						accountEnvironment.getPrimaryKeyObj());
			}

			if (accountEnvironment != null) {
				session.delete(accountEnvironment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accountEnvironment != null) {
			clearCache(accountEnvironment);
		}

		return accountEnvironment;
	}

	@Override
	public AccountEnvironment updateImpl(AccountEnvironment accountEnvironment) {
		accountEnvironment = toUnwrappedModel(accountEnvironment);

		boolean isNew = accountEnvironment.isNew();

		AccountEnvironmentModelImpl accountEnvironmentModelImpl = (AccountEnvironmentModelImpl)accountEnvironment;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (accountEnvironment.getCreateDate() == null)) {
			if (serviceContext == null) {
				accountEnvironment.setCreateDate(now);
			}
			else {
				accountEnvironment.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!accountEnvironmentModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				accountEnvironment.setModifiedDate(now);
			}
			else {
				accountEnvironment.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (accountEnvironment.isNew()) {
				session.save(accountEnvironment);

				accountEnvironment.setNew(false);
			}
			else {
				accountEnvironment = (AccountEnvironment)session.merge(accountEnvironment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AccountEnvironmentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					accountEnvironmentModelImpl.getAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			args = new Object[] {
					accountEnvironmentModelImpl.getAccountEntryId(),
					accountEnvironmentModelImpl.getProductEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_PEI, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PEI,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accountEnvironmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountEnvironmentModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] {
						accountEnvironmentModelImpl.getAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((accountEnvironmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountEnvironmentModelImpl.getOriginalAccountEntryId(),
						accountEnvironmentModelImpl.getOriginalProductEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_PEI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PEI,
					args);

				args = new Object[] {
						accountEnvironmentModelImpl.getAccountEntryId(),
						accountEnvironmentModelImpl.getProductEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_PEI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PEI,
					args);
			}
		}

		entityCache.putResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentImpl.class, accountEnvironment.getPrimaryKey(),
			accountEnvironment, false);

		clearUniqueFindersCache(accountEnvironmentModelImpl, false);
		cacheUniqueFindersCache(accountEnvironmentModelImpl);

		accountEnvironment.resetOriginalValues();

		return accountEnvironment;
	}

	protected AccountEnvironment toUnwrappedModel(
		AccountEnvironment accountEnvironment) {
		if (accountEnvironment instanceof AccountEnvironmentImpl) {
			return accountEnvironment;
		}

		AccountEnvironmentImpl accountEnvironmentImpl = new AccountEnvironmentImpl();

		accountEnvironmentImpl.setNew(accountEnvironment.isNew());
		accountEnvironmentImpl.setPrimaryKey(accountEnvironment.getPrimaryKey());

		accountEnvironmentImpl.setAccountEnvironmentId(accountEnvironment.getAccountEnvironmentId());
		accountEnvironmentImpl.setUserId(accountEnvironment.getUserId());
		accountEnvironmentImpl.setUserName(accountEnvironment.getUserName());
		accountEnvironmentImpl.setCreateDate(accountEnvironment.getCreateDate());
		accountEnvironmentImpl.setModifiedDate(accountEnvironment.getModifiedDate());
		accountEnvironmentImpl.setAccountEntryId(accountEnvironment.getAccountEntryId());
		accountEnvironmentImpl.setProductEntryId(accountEnvironment.getProductEntryId());
		accountEnvironmentImpl.setName(accountEnvironment.getName());
		accountEnvironmentImpl.setEnvOS(accountEnvironment.getEnvOS());
		accountEnvironmentImpl.setEnvOSCustom(accountEnvironment.getEnvOSCustom());
		accountEnvironmentImpl.setEnvDB(accountEnvironment.getEnvDB());
		accountEnvironmentImpl.setEnvJVM(accountEnvironment.getEnvJVM());
		accountEnvironmentImpl.setEnvAS(accountEnvironment.getEnvAS());
		accountEnvironmentImpl.setEnvLFR(accountEnvironment.getEnvLFR());
		accountEnvironmentImpl.setEnvBrowser(accountEnvironment.getEnvBrowser());
		accountEnvironmentImpl.setEnvCS(accountEnvironment.getEnvCS());
		accountEnvironmentImpl.setEnvSearch(accountEnvironment.getEnvSearch());

		return accountEnvironmentImpl;
	}

	/**
	 * Returns the account environment with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account environment
	 * @return the account environment
	 * @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 */
	@Override
	public AccountEnvironment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountEnvironmentException {
		AccountEnvironment accountEnvironment = fetchByPrimaryKey(primaryKey);

		if (accountEnvironment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountEnvironmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accountEnvironment;
	}

	/**
	 * Returns the account environment with the primary key or throws a {@link NoSuchAccountEnvironmentException} if it could not be found.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment
	 * @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 */
	@Override
	public AccountEnvironment findByPrimaryKey(long accountEnvironmentId)
		throws NoSuchAccountEnvironmentException {
		return findByPrimaryKey((Serializable)accountEnvironmentId);
	}

	/**
	 * Returns the account environment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account environment
	 * @return the account environment, or <code>null</code> if a account environment with the primary key could not be found
	 */
	@Override
	public AccountEnvironment fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountEnvironmentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccountEnvironment accountEnvironment = (AccountEnvironment)serializable;

		if (accountEnvironment == null) {
			Session session = null;

			try {
				session = openSession();

				accountEnvironment = (AccountEnvironment)session.get(AccountEnvironmentImpl.class,
						primaryKey);

				if (accountEnvironment != null) {
					cacheResult(accountEnvironment);
				}
				else {
					entityCache.putResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountEnvironmentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
					AccountEnvironmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accountEnvironment;
	}

	/**
	 * Returns the account environment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment, or <code>null</code> if a account environment with the primary key could not be found
	 */
	@Override
	public AccountEnvironment fetchByPrimaryKey(long accountEnvironmentId) {
		return fetchByPrimaryKey((Serializable)accountEnvironmentId);
	}

	@Override
	public Map<Serializable, AccountEnvironment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccountEnvironment> map = new HashMap<Serializable, AccountEnvironment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccountEnvironment accountEnvironment = fetchByPrimaryKey(primaryKey);

			if (accountEnvironment != null) {
				map.put(primaryKey, accountEnvironment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
					AccountEnvironmentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccountEnvironment)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCOUNTENVIRONMENT_WHERE_PKS_IN);

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

			for (AccountEnvironment accountEnvironment : (List<AccountEnvironment>)q.list()) {
				map.put(accountEnvironment.getPrimaryKeyObj(),
					accountEnvironment);

				cacheResult(accountEnvironment);

				uncachedPrimaryKeys.remove(accountEnvironment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccountEnvironmentModelImpl.ENTITY_CACHE_ENABLED,
					AccountEnvironmentImpl.class, primaryKey, nullModel);
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
	 * Returns all the account environments.
	 *
	 * @return the account environments
	 */
	@Override
	public List<AccountEnvironment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account environments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @return the range of account environments
	 */
	@Override
	public List<AccountEnvironment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account environments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account environments
	 */
	@Override
	public List<AccountEnvironment> findAll(int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account environments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of account environments
	 */
	@Override
	public List<AccountEnvironment> findAll(int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator,
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

		List<AccountEnvironment> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEnvironment>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCOUNTENVIRONMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTENVIRONMENT;

				if (pagination) {
					sql = sql.concat(AccountEnvironmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccountEnvironment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountEnvironment>)QueryUtil.list(q,
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
	 * Removes all the account environments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountEnvironment accountEnvironment : findAll()) {
			remove(accountEnvironment);
		}
	}

	/**
	 * Returns the number of account environments.
	 *
	 * @return the number of account environments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTENVIRONMENT);

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
		return AccountEnvironmentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account environment persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccountEnvironmentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ACCOUNTENVIRONMENT = "SELECT accountEnvironment FROM AccountEnvironment accountEnvironment";
	private static final String _SQL_SELECT_ACCOUNTENVIRONMENT_WHERE_PKS_IN = "SELECT accountEnvironment FROM AccountEnvironment accountEnvironment WHERE accountEnvironmentId IN (";
	private static final String _SQL_SELECT_ACCOUNTENVIRONMENT_WHERE = "SELECT accountEnvironment FROM AccountEnvironment accountEnvironment WHERE ";
	private static final String _SQL_COUNT_ACCOUNTENVIRONMENT = "SELECT COUNT(accountEnvironment) FROM AccountEnvironment accountEnvironment";
	private static final String _SQL_COUNT_ACCOUNTENVIRONMENT_WHERE = "SELECT COUNT(accountEnvironment) FROM AccountEnvironment accountEnvironment WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountEnvironment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountEnvironment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountEnvironment exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AccountEnvironmentPersistenceImpl.class);
}
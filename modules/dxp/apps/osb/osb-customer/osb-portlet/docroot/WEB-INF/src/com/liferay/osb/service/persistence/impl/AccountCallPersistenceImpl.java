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

import com.liferay.osb.exception.NoSuchAccountCallException;
import com.liferay.osb.model.AccountCall;
import com.liferay.osb.model.impl.AccountCallImpl;
import com.liferay.osb.model.impl.AccountCallModelImpl;
import com.liferay.osb.service.persistence.AccountCallPersistence;

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
import java.util.Set;

/**
 * The persistence implementation for the account call service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCallPersistence
 * @see com.liferay.osb.service.persistence.AccountCallUtil
 * @generated
 */
@ProviderType
public class AccountCallPersistenceImpl extends BasePersistenceImpl<AccountCall>
	implements AccountCallPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountCallUtil} to access the account call persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountCallImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
			AccountCallModelImpl.FINDER_CACHE_ENABLED, AccountCallImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
			AccountCallModelImpl.FINDER_CACHE_ENABLED, AccountCallImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
			AccountCallModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
			AccountCallModelImpl.FINDER_CACHE_ENABLED, AccountCallImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
			AccountCallModelImpl.FINDER_CACHE_ENABLED, AccountCallImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			AccountCallModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountCallModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
			AccountCallModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the account calls where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account calls
	 */
	@Override
	public List<AccountCall> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account calls where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account calls
	 * @param end the upper bound of the range of account calls (not inclusive)
	 * @return the range of matching account calls
	 */
	@Override
	public List<AccountCall> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account calls where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account calls
	 * @param end the upper bound of the range of account calls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account calls
	 */
	@Override
	public List<AccountCall> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountCall> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account calls where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account calls
	 * @param end the upper bound of the range of account calls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account calls
	 */
	@Override
	public List<AccountCall> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountCall> orderByComparator,
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

		List<AccountCall> list = null;

		if (retrieveFromCache) {
			list = (List<AccountCall>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountCall accountCall : list) {
					if ((accountEntryId != accountCall.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTCALL_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountCallModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<AccountCall>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountCall>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account call in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account call
	 * @throws NoSuchAccountCallException if a matching account call could not be found
	 */
	@Override
	public AccountCall findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountCall> orderByComparator)
		throws NoSuchAccountCallException {
		AccountCall accountCall = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountCall != null) {
			return accountCall;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCallException(msg.toString());
	}

	/**
	 * Returns the first account call in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account call, or <code>null</code> if a matching account call could not be found
	 */
	@Override
	public AccountCall fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountCall> orderByComparator) {
		List<AccountCall> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account call in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account call
	 * @throws NoSuchAccountCallException if a matching account call could not be found
	 */
	@Override
	public AccountCall findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountCall> orderByComparator)
		throws NoSuchAccountCallException {
		AccountCall accountCall = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountCall != null) {
			return accountCall;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCallException(msg.toString());
	}

	/**
	 * Returns the last account call in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account call, or <code>null</code> if a matching account call could not be found
	 */
	@Override
	public AccountCall fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountCall> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<AccountCall> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account calls before and after the current account call in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountCallId the primary key of the current account call
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account call
	 * @throws NoSuchAccountCallException if a account call with the primary key could not be found
	 */
	@Override
	public AccountCall[] findByAccountEntryId_PrevAndNext(long accountCallId,
		long accountEntryId, OrderByComparator<AccountCall> orderByComparator)
		throws NoSuchAccountCallException {
		AccountCall accountCall = findByPrimaryKey(accountCallId);

		Session session = null;

		try {
			session = openSession();

			AccountCall[] array = new AccountCallImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, accountCall,
					accountEntryId, orderByComparator, true);

			array[1] = accountCall;

			array[2] = getByAccountEntryId_PrevAndNext(session, accountCall,
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

	protected AccountCall getByAccountEntryId_PrevAndNext(Session session,
		AccountCall accountCall, long accountEntryId,
		OrderByComparator<AccountCall> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTCALL_WHERE);

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
			query.append(AccountCallModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountCall);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountCall> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account calls where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (AccountCall accountCall : findByAccountEntryId(accountEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountCall);
		}
	}

	/**
	 * Returns the number of account calls where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account calls
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTCALL_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountCall.accountEntryId = ?";

	public AccountCallPersistenceImpl() {
		setModelClass(AccountCall.class);
	}

	/**
	 * Caches the account call in the entity cache if it is enabled.
	 *
	 * @param accountCall the account call
	 */
	@Override
	public void cacheResult(AccountCall accountCall) {
		entityCache.putResult(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
			AccountCallImpl.class, accountCall.getPrimaryKey(), accountCall);

		accountCall.resetOriginalValues();
	}

	/**
	 * Caches the account calls in the entity cache if it is enabled.
	 *
	 * @param accountCalls the account calls
	 */
	@Override
	public void cacheResult(List<AccountCall> accountCalls) {
		for (AccountCall accountCall : accountCalls) {
			if (entityCache.getResult(
						AccountCallModelImpl.ENTITY_CACHE_ENABLED,
						AccountCallImpl.class, accountCall.getPrimaryKey()) == null) {
				cacheResult(accountCall);
			}
			else {
				accountCall.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account calls.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountCallImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account call.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountCall accountCall) {
		entityCache.removeResult(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
			AccountCallImpl.class, accountCall.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccountCall> accountCalls) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountCall accountCall : accountCalls) {
			entityCache.removeResult(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
				AccountCallImpl.class, accountCall.getPrimaryKey());
		}
	}

	/**
	 * Creates a new account call with the primary key. Does not add the account call to the database.
	 *
	 * @param accountCallId the primary key for the new account call
	 * @return the new account call
	 */
	@Override
	public AccountCall create(long accountCallId) {
		AccountCall accountCall = new AccountCallImpl();

		accountCall.setNew(true);
		accountCall.setPrimaryKey(accountCallId);

		return accountCall;
	}

	/**
	 * Removes the account call with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountCallId the primary key of the account call
	 * @return the account call that was removed
	 * @throws NoSuchAccountCallException if a account call with the primary key could not be found
	 */
	@Override
	public AccountCall remove(long accountCallId)
		throws NoSuchAccountCallException {
		return remove((Serializable)accountCallId);
	}

	/**
	 * Removes the account call with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account call
	 * @return the account call that was removed
	 * @throws NoSuchAccountCallException if a account call with the primary key could not be found
	 */
	@Override
	public AccountCall remove(Serializable primaryKey)
		throws NoSuchAccountCallException {
		Session session = null;

		try {
			session = openSession();

			AccountCall accountCall = (AccountCall)session.get(AccountCallImpl.class,
					primaryKey);

			if (accountCall == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountCallException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountCall);
		}
		catch (NoSuchAccountCallException nsee) {
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
	protected AccountCall removeImpl(AccountCall accountCall) {
		accountCall = toUnwrappedModel(accountCall);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountCall)) {
				accountCall = (AccountCall)session.get(AccountCallImpl.class,
						accountCall.getPrimaryKeyObj());
			}

			if (accountCall != null) {
				session.delete(accountCall);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accountCall != null) {
			clearCache(accountCall);
		}

		return accountCall;
	}

	@Override
	public AccountCall updateImpl(AccountCall accountCall) {
		accountCall = toUnwrappedModel(accountCall);

		boolean isNew = accountCall.isNew();

		AccountCallModelImpl accountCallModelImpl = (AccountCallModelImpl)accountCall;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (accountCall.getCreateDate() == null)) {
			if (serviceContext == null) {
				accountCall.setCreateDate(now);
			}
			else {
				accountCall.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!accountCallModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				accountCall.setModifiedDate(now);
			}
			else {
				accountCall.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (accountCall.isNew()) {
				session.save(accountCall);

				accountCall.setNew(false);
			}
			else {
				accountCall = (AccountCall)session.merge(accountCall);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AccountCallModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					accountCallModelImpl.getAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accountCallModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountCallModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] { accountCallModelImpl.getAccountEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}
		}

		entityCache.putResult(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
			AccountCallImpl.class, accountCall.getPrimaryKey(), accountCall,
			false);

		accountCall.resetOriginalValues();

		return accountCall;
	}

	protected AccountCall toUnwrappedModel(AccountCall accountCall) {
		if (accountCall instanceof AccountCallImpl) {
			return accountCall;
		}

		AccountCallImpl accountCallImpl = new AccountCallImpl();

		accountCallImpl.setNew(accountCall.isNew());
		accountCallImpl.setPrimaryKey(accountCall.getPrimaryKey());

		accountCallImpl.setAccountCallId(accountCall.getAccountCallId());
		accountCallImpl.setCreateDate(accountCall.getCreateDate());
		accountCallImpl.setModifiedUserId(accountCall.getModifiedUserId());
		accountCallImpl.setModifiedUserName(accountCall.getModifiedUserName());
		accountCallImpl.setModifiedDate(accountCall.getModifiedDate());
		accountCallImpl.setAccountEntryId(accountCall.getAccountEntryId());
		accountCallImpl.setType(accountCall.getType());
		accountCallImpl.setCallDate(accountCall.getCallDate());
		accountCallImpl.setCallLength(accountCall.getCallLength());
		accountCallImpl.setSummary(accountCall.getSummary());
		accountCallImpl.setClientsPresent(accountCall.getClientsPresent());
		accountCallImpl.setNotes(accountCall.getNotes());
		accountCallImpl.setActionItems(accountCall.getActionItems());

		return accountCallImpl;
	}

	/**
	 * Returns the account call with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account call
	 * @return the account call
	 * @throws NoSuchAccountCallException if a account call with the primary key could not be found
	 */
	@Override
	public AccountCall findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountCallException {
		AccountCall accountCall = fetchByPrimaryKey(primaryKey);

		if (accountCall == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountCallException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accountCall;
	}

	/**
	 * Returns the account call with the primary key or throws a {@link NoSuchAccountCallException} if it could not be found.
	 *
	 * @param accountCallId the primary key of the account call
	 * @return the account call
	 * @throws NoSuchAccountCallException if a account call with the primary key could not be found
	 */
	@Override
	public AccountCall findByPrimaryKey(long accountCallId)
		throws NoSuchAccountCallException {
		return findByPrimaryKey((Serializable)accountCallId);
	}

	/**
	 * Returns the account call with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account call
	 * @return the account call, or <code>null</code> if a account call with the primary key could not be found
	 */
	@Override
	public AccountCall fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
				AccountCallImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccountCall accountCall = (AccountCall)serializable;

		if (accountCall == null) {
			Session session = null;

			try {
				session = openSession();

				accountCall = (AccountCall)session.get(AccountCallImpl.class,
						primaryKey);

				if (accountCall != null) {
					cacheResult(accountCall);
				}
				else {
					entityCache.putResult(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
						AccountCallImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
					AccountCallImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accountCall;
	}

	/**
	 * Returns the account call with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountCallId the primary key of the account call
	 * @return the account call, or <code>null</code> if a account call with the primary key could not be found
	 */
	@Override
	public AccountCall fetchByPrimaryKey(long accountCallId) {
		return fetchByPrimaryKey((Serializable)accountCallId);
	}

	@Override
	public Map<Serializable, AccountCall> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccountCall> map = new HashMap<Serializable, AccountCall>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccountCall accountCall = fetchByPrimaryKey(primaryKey);

			if (accountCall != null) {
				map.put(primaryKey, accountCall);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
					AccountCallImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccountCall)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCOUNTCALL_WHERE_PKS_IN);

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

			for (AccountCall accountCall : (List<AccountCall>)q.list()) {
				map.put(accountCall.getPrimaryKeyObj(), accountCall);

				cacheResult(accountCall);

				uncachedPrimaryKeys.remove(accountCall.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccountCallModelImpl.ENTITY_CACHE_ENABLED,
					AccountCallImpl.class, primaryKey, nullModel);
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
	 * Returns all the account calls.
	 *
	 * @return the account calls
	 */
	@Override
	public List<AccountCall> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account calls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account calls
	 * @param end the upper bound of the range of account calls (not inclusive)
	 * @return the range of account calls
	 */
	@Override
	public List<AccountCall> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account calls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account calls
	 * @param end the upper bound of the range of account calls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account calls
	 */
	@Override
	public List<AccountCall> findAll(int start, int end,
		OrderByComparator<AccountCall> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account calls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account calls
	 * @param end the upper bound of the range of account calls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of account calls
	 */
	@Override
	public List<AccountCall> findAll(int start, int end,
		OrderByComparator<AccountCall> orderByComparator,
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

		List<AccountCall> list = null;

		if (retrieveFromCache) {
			list = (List<AccountCall>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCOUNTCALL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTCALL;

				if (pagination) {
					sql = sql.concat(AccountCallModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccountCall>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountCall>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the account calls from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountCall accountCall : findAll()) {
			remove(accountCall);
		}
	}

	/**
	 * Returns the number of account calls.
	 *
	 * @return the number of account calls
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTCALL);

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
		return AccountCallModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account call persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccountCallImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ACCOUNTCALL = "SELECT accountCall FROM AccountCall accountCall";
	private static final String _SQL_SELECT_ACCOUNTCALL_WHERE_PKS_IN = "SELECT accountCall FROM AccountCall accountCall WHERE accountCallId IN (";
	private static final String _SQL_SELECT_ACCOUNTCALL_WHERE = "SELECT accountCall FROM AccountCall accountCall WHERE ";
	private static final String _SQL_COUNT_ACCOUNTCALL = "SELECT COUNT(accountCall) FROM AccountCall accountCall";
	private static final String _SQL_COUNT_ACCOUNTCALL_WHERE = "SELECT COUNT(accountCall) FROM AccountCall accountCall WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountCall.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountCall exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountCall exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AccountCallPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
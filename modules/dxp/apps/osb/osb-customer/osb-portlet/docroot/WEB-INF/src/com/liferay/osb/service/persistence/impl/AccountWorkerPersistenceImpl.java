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

import com.liferay.osb.exception.NoSuchAccountWorkerException;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.impl.AccountWorkerImpl;
import com.liferay.osb.model.impl.AccountWorkerModelImpl;
import com.liferay.osb.service.persistence.AccountWorkerPersistence;

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
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the account worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorkerPersistence
 * @see com.liferay.osb.service.persistence.AccountWorkerUtil
 * @generated
 */
@ProviderType
public class AccountWorkerPersistenceImpl extends BasePersistenceImpl<AccountWorker>
	implements AccountWorkerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountWorkerUtil} to access the account worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountWorkerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId", new String[] { Long.class.getName() },
			AccountWorkerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the account workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching account workers
	 */
	@Override
	public List<AccountWorker> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByUserId(long userId, int start, int end,
		OrderByComparator<AccountWorker> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByUserId(long userId, int start, int end,
		OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<AccountWorker> list = null;

		if (retrieveFromCache) {
			list = (List<AccountWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountWorker accountWorker : list) {
					if ((userId != accountWorker.getUserId())) {
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

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker
	 * @throws NoSuchAccountWorkerException if a matching account worker could not be found
	 */
	@Override
	public AccountWorker findByUserId_First(long userId,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByUserId_First(userId,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the first account worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByUserId_First(long userId,
		OrderByComparator<AccountWorker> orderByComparator) {
		List<AccountWorker> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker
	 * @throws NoSuchAccountWorkerException if a matching account worker could not be found
	 */
	@Override
	public AccountWorker findByUserId_Last(long userId,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByUserId_Last(userId,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the last account worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByUserId_Last(long userId,
		OrderByComparator<AccountWorker> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<AccountWorker> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account workers before and after the current account worker in the ordered set where userId = &#63;.
	 *
	 * @param accountWorkerId the primary key of the current account worker
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account worker
	 * @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 */
	@Override
	public AccountWorker[] findByUserId_PrevAndNext(long accountWorkerId,
		long userId, OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = findByPrimaryKey(accountWorkerId);

		Session session = null;

		try {
			session = openSession();

			AccountWorker[] array = new AccountWorkerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, accountWorker, userId,
					orderByComparator, true);

			array[1] = accountWorker;

			array[2] = getByUserId_PrevAndNext(session, accountWorker, userId,
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

	protected AccountWorker getByUserId_PrevAndNext(Session session,
		AccountWorker accountWorker, long userId,
		OrderByComparator<AccountWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(AccountWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account workers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (AccountWorker accountWorker : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountWorker);
		}
	}

	/**
	 * Returns the number of account workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching account workers
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "accountWorker.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAccountEntryId", new String[] { Long.class.getName() },
			AccountWorkerModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the account workers where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account workers
	 */
	@Override
	public List<AccountWorker> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account workers where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountWorker> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account workers where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountWorker> orderByComparator,
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

		List<AccountWorker> list = null;

		if (retrieveFromCache) {
			list = (List<AccountWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountWorker accountWorker : list) {
					if ((accountEntryId != accountWorker.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account worker in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker
	 * @throws NoSuchAccountWorkerException if a matching account worker could not be found
	 */
	@Override
	public AccountWorker findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the first account worker in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountWorker> orderByComparator) {
		List<AccountWorker> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker
	 * @throws NoSuchAccountWorkerException if a matching account worker could not be found
	 */
	@Override
	public AccountWorker findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountWorker> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<AccountWorker> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account workers before and after the current account worker in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountWorkerId the primary key of the current account worker
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account worker
	 * @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 */
	@Override
	public AccountWorker[] findByAccountEntryId_PrevAndNext(
		long accountWorkerId, long accountEntryId,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = findByPrimaryKey(accountWorkerId);

		Session session = null;

		try {
			session = openSession();

			AccountWorker[] array = new AccountWorkerImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, accountWorker,
					accountEntryId, orderByComparator, true);

			array[1] = accountWorker;

			array[2] = getByAccountEntryId_PrevAndNext(session, accountWorker,
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

	protected AccountWorker getByAccountEntryId_PrevAndNext(Session session,
		AccountWorker accountWorker, long accountEntryId,
		OrderByComparator<AccountWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

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
			query.append(AccountWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account workers where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (AccountWorker accountWorker : findByAccountEntryId(
				accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountWorker);
		}
	}

	/**
	 * Returns the number of account workers where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account workers
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountWorker.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_AEI = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			AccountWorkerModelImpl.USERID_COLUMN_BITMASK |
			AccountWorkerModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the account worker where userId = &#63; and accountEntryId = &#63; or throws a {@link NoSuchAccountWorkerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching account worker
	 * @throws NoSuchAccountWorkerException if a matching account worker could not be found
	 */
	@Override
	public AccountWorker findByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByU_AEI(userId, accountEntryId);

		if (accountWorker == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", accountEntryId=");
			msg.append(accountEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountWorkerException(msg.toString());
		}

		return accountWorker;
	}

	/**
	 * Returns the account worker where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByU_AEI(long userId, long accountEntryId) {
		return fetchByU_AEI(userId, accountEntryId, true);
	}

	/**
	 * Returns the account worker where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByU_AEI(long userId, long accountEntryId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, accountEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_AEI,
					finderArgs, this);
		}

		if (result instanceof AccountWorker) {
			AccountWorker accountWorker = (AccountWorker)result;

			if ((userId != accountWorker.getUserId()) ||
					(accountEntryId != accountWorker.getAccountEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

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

				List<AccountWorker> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AccountWorkerPersistenceImpl.fetchByU_AEI(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AccountWorker accountWorker = list.get(0);

					result = accountWorker;

					cacheResult(accountWorker);

					if ((accountWorker.getUserId() != userId) ||
							(accountWorker.getAccountEntryId() != accountEntryId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI,
							finderArgs, accountWorker);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_AEI, finderArgs);

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
			return (AccountWorker)result;
		}
	}

	/**
	 * Removes the account worker where userId = &#63; and accountEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the account worker that was removed
	 */
	@Override
	public AccountWorker removeByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = findByU_AEI(userId, accountEntryId);

		return remove(accountWorker);
	}

	/**
	 * Returns the number of account workers where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account workers
	 */
	@Override
	public int countByU_AEI(long userId, long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_AEI;

		Object[] finderArgs = new Object[] { userId, accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

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

	private static final String _FINDER_COLUMN_U_AEI_USERID_2 = "accountWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2 = "accountWorker.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountWorkerModelImpl.USERID_COLUMN_BITMASK |
			AccountWorkerModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the account workers where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the matching account workers
	 */
	@Override
	public List<AccountWorker> findByU_R(long userId, int role) {
		return findByU_R(userId, role, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the account workers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByU_R(long userId, int role, int start,
		int end) {
		return findByU_R(userId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByU_R(long userId, int role, int start,
		int end, OrderByComparator<AccountWorker> orderByComparator) {
		return findByU_R(userId, role, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account workers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByU_R(long userId, int role, int start,
		int end, OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] { userId, role };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] {
					userId, role,
					
					start, end, orderByComparator
				};
		}

		List<AccountWorker> list = null;

		if (retrieveFromCache) {
			list = (List<AccountWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountWorker accountWorker : list) {
					if ((userId != accountWorker.getUserId()) ||
							(role != accountWorker.getRole())) {
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

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_ROLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(role);

				if (!pagination) {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker
	 * @throws NoSuchAccountWorkerException if a matching account worker could not be found
	 */
	@Override
	public AccountWorker findByU_R_First(long userId, int role,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByU_R_First(userId, role,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the first account worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByU_R_First(long userId, int role,
		OrderByComparator<AccountWorker> orderByComparator) {
		List<AccountWorker> list = findByU_R(userId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker
	 * @throws NoSuchAccountWorkerException if a matching account worker could not be found
	 */
	@Override
	public AccountWorker findByU_R_Last(long userId, int role,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByU_R_Last(userId, role,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the last account worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByU_R_Last(long userId, int role,
		OrderByComparator<AccountWorker> orderByComparator) {
		int count = countByU_R(userId, role);

		if (count == 0) {
			return null;
		}

		List<AccountWorker> list = findByU_R(userId, role, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account workers before and after the current account worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param accountWorkerId the primary key of the current account worker
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account worker
	 * @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 */
	@Override
	public AccountWorker[] findByU_R_PrevAndNext(long accountWorkerId,
		long userId, int role,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = findByPrimaryKey(accountWorkerId);

		Session session = null;

		try {
			session = openSession();

			AccountWorker[] array = new AccountWorkerImpl[3];

			array[0] = getByU_R_PrevAndNext(session, accountWorker, userId,
					role, orderByComparator, true);

			array[1] = accountWorker;

			array[2] = getByU_R_PrevAndNext(session, accountWorker, userId,
					role, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountWorker getByU_R_PrevAndNext(Session session,
		AccountWorker accountWorker, long userId, int role,
		OrderByComparator<AccountWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

		query.append(_FINDER_COLUMN_U_R_USERID_2);

		query.append(_FINDER_COLUMN_U_R_ROLE_2);

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
			query.append(AccountWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account workers where userId = &#63; and role = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param role the role
	 */
	@Override
	public void removeByU_R(long userId, int role) {
		for (AccountWorker accountWorker : findByU_R(userId, role,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountWorker);
		}
	}

	/**
	 * Returns the number of account workers where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the number of matching account workers
	 */
	@Override
	public int countByU_R(long userId, int role) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_R;

		Object[] finderArgs = new Object[] { userId, role };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_ROLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(role);

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

	private static final String _FINDER_COLUMN_U_R_USERID_2 = "accountWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_R_ROLE_2 = "accountWorker.role = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAEI_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAEI_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountWorkerModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountWorkerModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_R = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_R",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the account workers where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @return the matching account workers
	 */
	@Override
	public List<AccountWorker> findByAEI_R(long accountEntryId, int role) {
		return findByAEI_R(accountEntryId, role, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account workers where accountEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByAEI_R(long accountEntryId, int role,
		int start, int end) {
		return findByAEI_R(accountEntryId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers where accountEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByAEI_R(long accountEntryId, int role,
		int start, int end, OrderByComparator<AccountWorker> orderByComparator) {
		return findByAEI_R(accountEntryId, role, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the account workers where accountEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByAEI_R(long accountEntryId, int role,
		int start, int end, OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R;
			finderArgs = new Object[] { accountEntryId, role };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_R;
			finderArgs = new Object[] {
					accountEntryId, role,
					
					start, end, orderByComparator
				};
		}

		List<AccountWorker> list = null;

		if (retrieveFromCache) {
			list = (List<AccountWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountWorker accountWorker : list) {
					if ((accountEntryId != accountWorker.getAccountEntryId()) ||
							(role != accountWorker.getRole())) {
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

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_AEI_R_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_R_ROLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(role);

				if (!pagination) {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker
	 * @throws NoSuchAccountWorkerException if a matching account worker could not be found
	 */
	@Override
	public AccountWorker findByAEI_R_First(long accountEntryId, int role,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByAEI_R_First(accountEntryId, role,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the first account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByAEI_R_First(long accountEntryId, int role,
		OrderByComparator<AccountWorker> orderByComparator) {
		List<AccountWorker> list = findByAEI_R(accountEntryId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker
	 * @throws NoSuchAccountWorkerException if a matching account worker could not be found
	 */
	@Override
	public AccountWorker findByAEI_R_Last(long accountEntryId, int role,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByAEI_R_Last(accountEntryId, role,
				orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByAEI_R_Last(long accountEntryId, int role,
		OrderByComparator<AccountWorker> orderByComparator) {
		int count = countByAEI_R(accountEntryId, role);

		if (count == 0) {
			return null;
		}

		List<AccountWorker> list = findByAEI_R(accountEntryId, role, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account workers before and after the current account worker in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountWorkerId the primary key of the current account worker
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account worker
	 * @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 */
	@Override
	public AccountWorker[] findByAEI_R_PrevAndNext(long accountWorkerId,
		long accountEntryId, int role,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = findByPrimaryKey(accountWorkerId);

		Session session = null;

		try {
			session = openSession();

			AccountWorker[] array = new AccountWorkerImpl[3];

			array[0] = getByAEI_R_PrevAndNext(session, accountWorker,
					accountEntryId, role, orderByComparator, true);

			array[1] = accountWorker;

			array[2] = getByAEI_R_PrevAndNext(session, accountWorker,
					accountEntryId, role, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountWorker getByAEI_R_PrevAndNext(Session session,
		AccountWorker accountWorker, long accountEntryId, int role,
		OrderByComparator<AccountWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

		query.append(_FINDER_COLUMN_AEI_R_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_R_ROLE_2);

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
			query.append(AccountWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account workers where accountEntryId = &#63; and role = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 */
	@Override
	public void removeByAEI_R(long accountEntryId, int role) {
		for (AccountWorker accountWorker : findByAEI_R(accountEntryId, role,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountWorker);
		}
	}

	/**
	 * Returns the number of account workers where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @return the number of matching account workers
	 */
	@Override
	public int countByAEI_R(long accountEntryId, int role) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_R;

		Object[] finderArgs = new Object[] { accountEntryId, role };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_AEI_R_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_R_ROLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(role);

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

	private static final String _FINDER_COLUMN_AEI_R_ACCOUNTENTRYID_2 = "accountWorker.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_R_ROLE_2 = "accountWorker.role = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTN = new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED,
			AccountWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAEI_NotN",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTN =
		new FinderPath(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByAEI_NotN",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @return the matching account workers
	 */
	@Override
	public List<AccountWorker> findByAEI_NotN(long accountEntryId,
		int notifications) {
		return findByAEI_NotN(accountEntryId, notifications, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByAEI_NotN(long accountEntryId,
		int notifications, int start, int end) {
		return findByAEI_NotN(accountEntryId, notifications, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByAEI_NotN(long accountEntryId,
		int notifications, int start, int end,
		OrderByComparator<AccountWorker> orderByComparator) {
		return findByAEI_NotN(accountEntryId, notifications, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account workers
	 */
	@Override
	public List<AccountWorker> findByAEI_NotN(long accountEntryId,
		int notifications, int start, int end,
		OrderByComparator<AccountWorker> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTN;
		finderArgs = new Object[] {
				accountEntryId, notifications,
				
				start, end, orderByComparator
			};

		List<AccountWorker> list = null;

		if (retrieveFromCache) {
			list = (List<AccountWorker>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountWorker accountWorker : list) {
					if ((accountEntryId != accountWorker.getAccountEntryId()) ||
							(notifications == accountWorker.getNotifications())) {
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

			query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_AEI_NOTN_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_NOTN_NOTIFICATIONS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountWorkerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(notifications);

				if (!pagination) {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker
	 * @throws NoSuchAccountWorkerException if a matching account worker could not be found
	 */
	@Override
	public AccountWorker findByAEI_NotN_First(long accountEntryId,
		int notifications, OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByAEI_NotN_First(accountEntryId,
				notifications, orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", notifications=");
		msg.append(notifications);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the first account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByAEI_NotN_First(long accountEntryId,
		int notifications, OrderByComparator<AccountWorker> orderByComparator) {
		List<AccountWorker> list = findByAEI_NotN(accountEntryId,
				notifications, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker
	 * @throws NoSuchAccountWorkerException if a matching account worker could not be found
	 */
	@Override
	public AccountWorker findByAEI_NotN_Last(long accountEntryId,
		int notifications, OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByAEI_NotN_Last(accountEntryId,
				notifications, orderByComparator);

		if (accountWorker != null) {
			return accountWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", notifications=");
		msg.append(notifications);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountWorkerException(msg.toString());
	}

	/**
	 * Returns the last account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account worker, or <code>null</code> if a matching account worker could not be found
	 */
	@Override
	public AccountWorker fetchByAEI_NotN_Last(long accountEntryId,
		int notifications, OrderByComparator<AccountWorker> orderByComparator) {
		int count = countByAEI_NotN(accountEntryId, notifications);

		if (count == 0) {
			return null;
		}

		List<AccountWorker> list = findByAEI_NotN(accountEntryId,
				notifications, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account workers before and after the current account worker in the ordered set where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountWorkerId the primary key of the current account worker
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account worker
	 * @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 */
	@Override
	public AccountWorker[] findByAEI_NotN_PrevAndNext(long accountWorkerId,
		long accountEntryId, int notifications,
		OrderByComparator<AccountWorker> orderByComparator)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = findByPrimaryKey(accountWorkerId);

		Session session = null;

		try {
			session = openSession();

			AccountWorker[] array = new AccountWorkerImpl[3];

			array[0] = getByAEI_NotN_PrevAndNext(session, accountWorker,
					accountEntryId, notifications, orderByComparator, true);

			array[1] = accountWorker;

			array[2] = getByAEI_NotN_PrevAndNext(session, accountWorker,
					accountEntryId, notifications, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountWorker getByAEI_NotN_PrevAndNext(Session session,
		AccountWorker accountWorker, long accountEntryId, int notifications,
		OrderByComparator<AccountWorker> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE);

		query.append(_FINDER_COLUMN_AEI_NOTN_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_NOTN_NOTIFICATIONS_2);

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
			query.append(AccountWorkerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(notifications);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account workers where accountEntryId = &#63; and notifications &ne; &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 */
	@Override
	public void removeByAEI_NotN(long accountEntryId, int notifications) {
		for (AccountWorker accountWorker : findByAEI_NotN(accountEntryId,
				notifications, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountWorker);
		}
	}

	/**
	 * Returns the number of account workers where accountEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param notifications the notifications
	 * @return the number of matching account workers
	 */
	@Override
	public int countByAEI_NotN(long accountEntryId, int notifications) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTN;

		Object[] finderArgs = new Object[] { accountEntryId, notifications };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTWORKER_WHERE);

			query.append(_FINDER_COLUMN_AEI_NOTN_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_NOTN_NOTIFICATIONS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(notifications);

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

	private static final String _FINDER_COLUMN_AEI_NOTN_ACCOUNTENTRYID_2 = "accountWorker.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_NOTN_NOTIFICATIONS_2 = "accountWorker.notifications != ?";

	public AccountWorkerPersistenceImpl() {
		setModelClass(AccountWorker.class);
	}

	/**
	 * Caches the account worker in the entity cache if it is enabled.
	 *
	 * @param accountWorker the account worker
	 */
	@Override
	public void cacheResult(AccountWorker accountWorker) {
		entityCache.putResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerImpl.class, accountWorker.getPrimaryKey(),
			accountWorker);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI,
			new Object[] {
				accountWorker.getUserId(), accountWorker.getAccountEntryId()
			}, accountWorker);

		accountWorker.resetOriginalValues();
	}

	/**
	 * Caches the account workers in the entity cache if it is enabled.
	 *
	 * @param accountWorkers the account workers
	 */
	@Override
	public void cacheResult(List<AccountWorker> accountWorkers) {
		for (AccountWorker accountWorker : accountWorkers) {
			if (entityCache.getResult(
						AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
						AccountWorkerImpl.class, accountWorker.getPrimaryKey()) == null) {
				cacheResult(accountWorker);
			}
			else {
				accountWorker.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account workers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountWorkerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account worker.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountWorker accountWorker) {
		entityCache.removeResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerImpl.class, accountWorker.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AccountWorkerModelImpl)accountWorker, true);
	}

	@Override
	public void clearCache(List<AccountWorker> accountWorkers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountWorker accountWorker : accountWorkers) {
			entityCache.removeResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
				AccountWorkerImpl.class, accountWorker.getPrimaryKey());

			clearUniqueFindersCache((AccountWorkerModelImpl)accountWorker, true);
		}
	}

	protected void cacheUniqueFindersCache(
		AccountWorkerModelImpl accountWorkerModelImpl) {
		Object[] args = new Object[] {
				accountWorkerModelImpl.getUserId(),
				accountWorkerModelImpl.getAccountEntryId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_U_AEI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI, args,
			accountWorkerModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AccountWorkerModelImpl accountWorkerModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					accountWorkerModelImpl.getUserId(),
					accountWorkerModelImpl.getAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_AEI, args);
		}

		if ((accountWorkerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_AEI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					accountWorkerModelImpl.getOriginalUserId(),
					accountWorkerModelImpl.getOriginalAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_AEI, args);
		}
	}

	/**
	 * Creates a new account worker with the primary key. Does not add the account worker to the database.
	 *
	 * @param accountWorkerId the primary key for the new account worker
	 * @return the new account worker
	 */
	@Override
	public AccountWorker create(long accountWorkerId) {
		AccountWorker accountWorker = new AccountWorkerImpl();

		accountWorker.setNew(true);
		accountWorker.setPrimaryKey(accountWorkerId);

		return accountWorker;
	}

	/**
	 * Removes the account worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountWorkerId the primary key of the account worker
	 * @return the account worker that was removed
	 * @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 */
	@Override
	public AccountWorker remove(long accountWorkerId)
		throws NoSuchAccountWorkerException {
		return remove((Serializable)accountWorkerId);
	}

	/**
	 * Removes the account worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account worker
	 * @return the account worker that was removed
	 * @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 */
	@Override
	public AccountWorker remove(Serializable primaryKey)
		throws NoSuchAccountWorkerException {
		Session session = null;

		try {
			session = openSession();

			AccountWorker accountWorker = (AccountWorker)session.get(AccountWorkerImpl.class,
					primaryKey);

			if (accountWorker == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountWorker);
		}
		catch (NoSuchAccountWorkerException nsee) {
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
	protected AccountWorker removeImpl(AccountWorker accountWorker) {
		accountWorker = toUnwrappedModel(accountWorker);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountWorker)) {
				accountWorker = (AccountWorker)session.get(AccountWorkerImpl.class,
						accountWorker.getPrimaryKeyObj());
			}

			if (accountWorker != null) {
				session.delete(accountWorker);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accountWorker != null) {
			clearCache(accountWorker);
		}

		return accountWorker;
	}

	@Override
	public AccountWorker updateImpl(AccountWorker accountWorker) {
		accountWorker = toUnwrappedModel(accountWorker);

		boolean isNew = accountWorker.isNew();

		AccountWorkerModelImpl accountWorkerModelImpl = (AccountWorkerModelImpl)accountWorker;

		Session session = null;

		try {
			session = openSession();

			if (accountWorker.isNew()) {
				session.save(accountWorker);

				accountWorker.setNew(false);
			}
			else {
				accountWorker = (AccountWorker)session.merge(accountWorker);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AccountWorkerModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { accountWorkerModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
				args);

			args = new Object[] { accountWorkerModelImpl.getAccountEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			args = new Object[] {
					accountWorkerModelImpl.getUserId(),
					accountWorkerModelImpl.getRole()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
				args);

			args = new Object[] {
					accountWorkerModelImpl.getAccountEntryId(),
					accountWorkerModelImpl.getRole()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_R, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accountWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountWorkerModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { accountWorkerModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((accountWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountWorkerModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] { accountWorkerModelImpl.getAccountEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((accountWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountWorkerModelImpl.getOriginalUserId(),
						accountWorkerModelImpl.getOriginalRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);

				args = new Object[] {
						accountWorkerModelImpl.getUserId(),
						accountWorkerModelImpl.getRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);
			}

			if ((accountWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountWorkerModelImpl.getOriginalAccountEntryId(),
						accountWorkerModelImpl.getOriginalRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R,
					args);

				args = new Object[] {
						accountWorkerModelImpl.getAccountEntryId(),
						accountWorkerModelImpl.getRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R,
					args);
			}
		}

		entityCache.putResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
			AccountWorkerImpl.class, accountWorker.getPrimaryKey(),
			accountWorker, false);

		clearUniqueFindersCache(accountWorkerModelImpl, false);
		cacheUniqueFindersCache(accountWorkerModelImpl);

		accountWorker.resetOriginalValues();

		return accountWorker;
	}

	protected AccountWorker toUnwrappedModel(AccountWorker accountWorker) {
		if (accountWorker instanceof AccountWorkerImpl) {
			return accountWorker;
		}

		AccountWorkerImpl accountWorkerImpl = new AccountWorkerImpl();

		accountWorkerImpl.setNew(accountWorker.isNew());
		accountWorkerImpl.setPrimaryKey(accountWorker.getPrimaryKey());

		accountWorkerImpl.setAccountWorkerId(accountWorker.getAccountWorkerId());
		accountWorkerImpl.setUserId(accountWorker.getUserId());
		accountWorkerImpl.setAccountEntryId(accountWorker.getAccountEntryId());
		accountWorkerImpl.setRole(accountWorker.getRole());
		accountWorkerImpl.setNotifications(accountWorker.getNotifications());

		return accountWorkerImpl;
	}

	/**
	 * Returns the account worker with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account worker
	 * @return the account worker
	 * @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 */
	@Override
	public AccountWorker findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountWorkerException {
		AccountWorker accountWorker = fetchByPrimaryKey(primaryKey);

		if (accountWorker == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accountWorker;
	}

	/**
	 * Returns the account worker with the primary key or throws a {@link NoSuchAccountWorkerException} if it could not be found.
	 *
	 * @param accountWorkerId the primary key of the account worker
	 * @return the account worker
	 * @throws NoSuchAccountWorkerException if a account worker with the primary key could not be found
	 */
	@Override
	public AccountWorker findByPrimaryKey(long accountWorkerId)
		throws NoSuchAccountWorkerException {
		return findByPrimaryKey((Serializable)accountWorkerId);
	}

	/**
	 * Returns the account worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account worker
	 * @return the account worker, or <code>null</code> if a account worker with the primary key could not be found
	 */
	@Override
	public AccountWorker fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
				AccountWorkerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccountWorker accountWorker = (AccountWorker)serializable;

		if (accountWorker == null) {
			Session session = null;

			try {
				session = openSession();

				accountWorker = (AccountWorker)session.get(AccountWorkerImpl.class,
						primaryKey);

				if (accountWorker != null) {
					cacheResult(accountWorker);
				}
				else {
					entityCache.putResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
						AccountWorkerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
					AccountWorkerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accountWorker;
	}

	/**
	 * Returns the account worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountWorkerId the primary key of the account worker
	 * @return the account worker, or <code>null</code> if a account worker with the primary key could not be found
	 */
	@Override
	public AccountWorker fetchByPrimaryKey(long accountWorkerId) {
		return fetchByPrimaryKey((Serializable)accountWorkerId);
	}

	@Override
	public Map<Serializable, AccountWorker> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccountWorker> map = new HashMap<Serializable, AccountWorker>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccountWorker accountWorker = fetchByPrimaryKey(primaryKey);

			if (accountWorker != null) {
				map.put(primaryKey, accountWorker);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
					AccountWorkerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccountWorker)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCOUNTWORKER_WHERE_PKS_IN);

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

			for (AccountWorker accountWorker : (List<AccountWorker>)q.list()) {
				map.put(accountWorker.getPrimaryKeyObj(), accountWorker);

				cacheResult(accountWorker);

				uncachedPrimaryKeys.remove(accountWorker.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccountWorkerModelImpl.ENTITY_CACHE_ENABLED,
					AccountWorkerImpl.class, primaryKey, nullModel);
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
	 * Returns all the account workers.
	 *
	 * @return the account workers
	 */
	@Override
	public List<AccountWorker> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @return the range of account workers
	 */
	@Override
	public List<AccountWorker> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account workers
	 */
	@Override
	public List<AccountWorker> findAll(int start, int end,
		OrderByComparator<AccountWorker> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account workers
	 * @param end the upper bound of the range of account workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of account workers
	 */
	@Override
	public List<AccountWorker> findAll(int start, int end,
		OrderByComparator<AccountWorker> orderByComparator,
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

		List<AccountWorker> list = null;

		if (retrieveFromCache) {
			list = (List<AccountWorker>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCOUNTWORKER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTWORKER;

				if (pagination) {
					sql = sql.concat(AccountWorkerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountWorker>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the account workers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountWorker accountWorker : findAll()) {
			remove(accountWorker);
		}
	}

	/**
	 * Returns the number of account workers.
	 *
	 * @return the number of account workers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTWORKER);

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
		return AccountWorkerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account worker persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccountWorkerImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ACCOUNTWORKER = "SELECT accountWorker FROM AccountWorker accountWorker";
	private static final String _SQL_SELECT_ACCOUNTWORKER_WHERE_PKS_IN = "SELECT accountWorker FROM AccountWorker accountWorker WHERE accountWorkerId IN (";
	private static final String _SQL_SELECT_ACCOUNTWORKER_WHERE = "SELECT accountWorker FROM AccountWorker accountWorker WHERE ";
	private static final String _SQL_COUNT_ACCOUNTWORKER = "SELECT COUNT(accountWorker) FROM AccountWorker accountWorker";
	private static final String _SQL_COUNT_ACCOUNTWORKER_WHERE = "SELECT COUNT(accountWorker) FROM AccountWorker accountWorker WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountWorker.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountWorker exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountWorker exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AccountWorkerPersistenceImpl.class);
}
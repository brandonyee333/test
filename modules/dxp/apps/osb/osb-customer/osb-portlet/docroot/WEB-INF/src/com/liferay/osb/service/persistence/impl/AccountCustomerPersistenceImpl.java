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

import com.liferay.osb.exception.NoSuchAccountCustomerException;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.impl.AccountCustomerImpl;
import com.liferay.osb.model.impl.AccountCustomerModelImpl;
import com.liferay.osb.service.persistence.AccountCustomerPersistence;

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
 * The persistence implementation for the account customer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomerPersistence
 * @see com.liferay.osb.service.persistence.AccountCustomerUtil
 * @generated
 */
@ProviderType
public class AccountCustomerPersistenceImpl extends BasePersistenceImpl<AccountCustomer>
	implements AccountCustomerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountCustomerUtil} to access the account customer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountCustomerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			AccountCustomerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the account customers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching account customers
	 */
	@Override
	public List<AccountCustomer> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account customers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @return the range of matching account customers
	 */
	@Override
	public List<AccountCustomer> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account customers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account customers
	 */
	@Override
	public List<AccountCustomer> findByUserId(long userId, int start, int end,
		OrderByComparator<AccountCustomer> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account customers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account customers
	 */
	@Override
	public List<AccountCustomer> findByUserId(long userId, int start, int end,
		OrderByComparator<AccountCustomer> orderByComparator,
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

		List<AccountCustomer> list = null;

		if (retrieveFromCache) {
			list = (List<AccountCustomer>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountCustomer accountCustomer : list) {
					if ((userId != accountCustomer.getUserId())) {
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

			query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountCustomerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<AccountCustomer>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountCustomer>)QueryUtil.list(q,
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
	 * Returns the first account customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer
	 * @throws NoSuchAccountCustomerException if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer findByUserId_First(long userId,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = fetchByUserId_First(userId,
				orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the first account customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer fetchByUserId_First(long userId,
		OrderByComparator<AccountCustomer> orderByComparator) {
		List<AccountCustomer> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer
	 * @throws NoSuchAccountCustomerException if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer findByUserId_Last(long userId,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = fetchByUserId_Last(userId,
				orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the last account customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer fetchByUserId_Last(long userId,
		OrderByComparator<AccountCustomer> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<AccountCustomer> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account customers before and after the current account customer in the ordered set where userId = &#63;.
	 *
	 * @param accountCustomerId the primary key of the current account customer
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account customer
	 * @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 */
	@Override
	public AccountCustomer[] findByUserId_PrevAndNext(long accountCustomerId,
		long userId, OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = findByPrimaryKey(accountCustomerId);

		Session session = null;

		try {
			session = openSession();

			AccountCustomer[] array = new AccountCustomerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, accountCustomer,
					userId, orderByComparator, true);

			array[1] = accountCustomer;

			array[2] = getByUserId_PrevAndNext(session, accountCustomer,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountCustomer getByUserId_PrevAndNext(Session session,
		AccountCustomer accountCustomer, long userId,
		OrderByComparator<AccountCustomer> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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
			query.append(AccountCustomerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account customers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (AccountCustomer accountCustomer : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountCustomer);
		}
	}

	/**
	 * Returns the number of account customers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching account customers
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTCUSTOMER_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "accountCustomer.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			AccountCustomerModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the account customers where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account customers
	 */
	@Override
	public List<AccountCustomer> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account customers where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @return the range of matching account customers
	 */
	@Override
	public List<AccountCustomer> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account customers where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account customers
	 */
	@Override
	public List<AccountCustomer> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountCustomer> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account customers where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account customers
	 */
	@Override
	public List<AccountCustomer> findByAccountEntryId(long accountEntryId,
		int start, int end,
		OrderByComparator<AccountCustomer> orderByComparator,
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

		List<AccountCustomer> list = null;

		if (retrieveFromCache) {
			list = (List<AccountCustomer>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountCustomer accountCustomer : list) {
					if ((accountEntryId != accountCustomer.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountCustomerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<AccountCustomer>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountCustomer>)QueryUtil.list(q,
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
	 * Returns the first account customer in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer
	 * @throws NoSuchAccountCustomerException if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the first account customer in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountCustomer> orderByComparator) {
		List<AccountCustomer> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account customer in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer
	 * @throws NoSuchAccountCustomerException if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the last account customer in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountCustomer> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<AccountCustomer> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account customers before and after the current account customer in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountCustomerId the primary key of the current account customer
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account customer
	 * @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 */
	@Override
	public AccountCustomer[] findByAccountEntryId_PrevAndNext(
		long accountCustomerId, long accountEntryId,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = findByPrimaryKey(accountCustomerId);

		Session session = null;

		try {
			session = openSession();

			AccountCustomer[] array = new AccountCustomerImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session,
					accountCustomer, accountEntryId, orderByComparator, true);

			array[1] = accountCustomer;

			array[2] = getByAccountEntryId_PrevAndNext(session,
					accountCustomer, accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountCustomer getByAccountEntryId_PrevAndNext(Session session,
		AccountCustomer accountCustomer, long accountEntryId,
		OrderByComparator<AccountCustomer> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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
			query.append(AccountCustomerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account customers where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (AccountCustomer accountCustomer : findByAccountEntryId(
				accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountCustomer);
		}
	}

	/**
	 * Returns the number of account customers where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account customers
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTCUSTOMER_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountCustomer.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_AEI = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			AccountCustomerModelImpl.USERID_COLUMN_BITMASK |
			AccountCustomerModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the account customer where userId = &#63; and accountEntryId = &#63; or throws a {@link NoSuchAccountCustomerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching account customer
	 * @throws NoSuchAccountCustomerException if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer findByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = fetchByU_AEI(userId, accountEntryId);

		if (accountCustomer == null) {
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

			throw new NoSuchAccountCustomerException(msg.toString());
		}

		return accountCustomer;
	}

	/**
	 * Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer fetchByU_AEI(long userId, long accountEntryId) {
		return fetchByU_AEI(userId, accountEntryId, true);
	}

	/**
	 * Returns the account customer where userId = &#63; and accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching account customer, or <code>null</code> if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer fetchByU_AEI(long userId, long accountEntryId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, accountEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_AEI,
					finderArgs, this);
		}

		if (result instanceof AccountCustomer) {
			AccountCustomer accountCustomer = (AccountCustomer)result;

			if ((userId != accountCustomer.getUserId()) ||
					(accountEntryId != accountCustomer.getAccountEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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

				List<AccountCustomer> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AccountCustomerPersistenceImpl.fetchByU_AEI(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AccountCustomer accountCustomer = list.get(0);

					result = accountCustomer;

					cacheResult(accountCustomer);

					if ((accountCustomer.getUserId() != userId) ||
							(accountCustomer.getAccountEntryId() != accountEntryId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI,
							finderArgs, accountCustomer);
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
			return (AccountCustomer)result;
		}
	}

	/**
	 * Removes the account customer where userId = &#63; and accountEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the account customer that was removed
	 */
	@Override
	public AccountCustomer removeByU_AEI(long userId, long accountEntryId)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = findByU_AEI(userId, accountEntryId);

		return remove(accountCustomer);
	}

	/**
	 * Returns the number of account customers where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account customers
	 */
	@Override
	public int countByU_AEI(long userId, long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_AEI;

		Object[] finderArgs = new Object[] { userId, accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTCUSTOMER_WHERE);

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

	private static final String _FINDER_COLUMN_U_AEI_USERID_2 = "accountCustomer.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2 = "accountCustomer.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_R = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAEI_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED,
			AccountCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountCustomerModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountCustomerModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_R = new FinderPath(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_R",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the account customers where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @return the matching account customers
	 */
	@Override
	public List<AccountCustomer> findByAEI_R(long accountEntryId, int role) {
		return findByAEI_R(accountEntryId, role, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account customers where accountEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @return the range of matching account customers
	 */
	@Override
	public List<AccountCustomer> findByAEI_R(long accountEntryId, int role,
		int start, int end) {
		return findByAEI_R(accountEntryId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account customers where accountEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account customers
	 */
	@Override
	public List<AccountCustomer> findByAEI_R(long accountEntryId, int role,
		int start, int end, OrderByComparator<AccountCustomer> orderByComparator) {
		return findByAEI_R(accountEntryId, role, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the account customers where accountEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account customers
	 */
	@Override
	public List<AccountCustomer> findByAEI_R(long accountEntryId, int role,
		int start, int end,
		OrderByComparator<AccountCustomer> orderByComparator,
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

		List<AccountCustomer> list = null;

		if (retrieveFromCache) {
			list = (List<AccountCustomer>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountCustomer accountCustomer : list) {
					if ((accountEntryId != accountCustomer.getAccountEntryId()) ||
							(role != accountCustomer.getRole())) {
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

			query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_AEI_R_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_R_ROLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountCustomerModelImpl.ORDER_BY_JPQL);
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
					list = (List<AccountCustomer>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountCustomer>)QueryUtil.list(q,
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
	 * Returns the first account customer in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer
	 * @throws NoSuchAccountCustomerException if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer findByAEI_R_First(long accountEntryId, int role,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = fetchByAEI_R_First(accountEntryId,
				role, orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the first account customer in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account customer, or <code>null</code> if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer fetchByAEI_R_First(long accountEntryId, int role,
		OrderByComparator<AccountCustomer> orderByComparator) {
		List<AccountCustomer> list = findByAEI_R(accountEntryId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account customer in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer
	 * @throws NoSuchAccountCustomerException if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer findByAEI_R_Last(long accountEntryId, int role,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = fetchByAEI_R_Last(accountEntryId,
				role, orderByComparator);

		if (accountCustomer != null) {
			return accountCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountCustomerException(msg.toString());
	}

	/**
	 * Returns the last account customer in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account customer, or <code>null</code> if a matching account customer could not be found
	 */
	@Override
	public AccountCustomer fetchByAEI_R_Last(long accountEntryId, int role,
		OrderByComparator<AccountCustomer> orderByComparator) {
		int count = countByAEI_R(accountEntryId, role);

		if (count == 0) {
			return null;
		}

		List<AccountCustomer> list = findByAEI_R(accountEntryId, role,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account customers before and after the current account customer in the ordered set where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountCustomerId the primary key of the current account customer
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account customer
	 * @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 */
	@Override
	public AccountCustomer[] findByAEI_R_PrevAndNext(long accountCustomerId,
		long accountEntryId, int role,
		OrderByComparator<AccountCustomer> orderByComparator)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = findByPrimaryKey(accountCustomerId);

		Session session = null;

		try {
			session = openSession();

			AccountCustomer[] array = new AccountCustomerImpl[3];

			array[0] = getByAEI_R_PrevAndNext(session, accountCustomer,
					accountEntryId, role, orderByComparator, true);

			array[1] = accountCustomer;

			array[2] = getByAEI_R_PrevAndNext(session, accountCustomer,
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

	protected AccountCustomer getByAEI_R_PrevAndNext(Session session,
		AccountCustomer accountCustomer, long accountEntryId, int role,
		OrderByComparator<AccountCustomer> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE);

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
			query.append(AccountCustomerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account customers where accountEntryId = &#63; and role = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 */
	@Override
	public void removeByAEI_R(long accountEntryId, int role) {
		for (AccountCustomer accountCustomer : findByAEI_R(accountEntryId,
				role, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountCustomer);
		}
	}

	/**
	 * Returns the number of account customers where accountEntryId = &#63; and role = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param role the role
	 * @return the number of matching account customers
	 */
	@Override
	public int countByAEI_R(long accountEntryId, int role) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_R;

		Object[] finderArgs = new Object[] { accountEntryId, role };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTCUSTOMER_WHERE);

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

	private static final String _FINDER_COLUMN_AEI_R_ACCOUNTENTRYID_2 = "accountCustomer.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_R_ROLE_2 = "accountCustomer.role = ?";

	public AccountCustomerPersistenceImpl() {
		setModelClass(AccountCustomer.class);
	}

	/**
	 * Caches the account customer in the entity cache if it is enabled.
	 *
	 * @param accountCustomer the account customer
	 */
	@Override
	public void cacheResult(AccountCustomer accountCustomer) {
		entityCache.putResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerImpl.class, accountCustomer.getPrimaryKey(),
			accountCustomer);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI,
			new Object[] {
				accountCustomer.getUserId(), accountCustomer.getAccountEntryId()
			}, accountCustomer);

		accountCustomer.resetOriginalValues();
	}

	/**
	 * Caches the account customers in the entity cache if it is enabled.
	 *
	 * @param accountCustomers the account customers
	 */
	@Override
	public void cacheResult(List<AccountCustomer> accountCustomers) {
		for (AccountCustomer accountCustomer : accountCustomers) {
			if (entityCache.getResult(
						AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
						AccountCustomerImpl.class,
						accountCustomer.getPrimaryKey()) == null) {
				cacheResult(accountCustomer);
			}
			else {
				accountCustomer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account customers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountCustomerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account customer.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountCustomer accountCustomer) {
		entityCache.removeResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerImpl.class, accountCustomer.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AccountCustomerModelImpl)accountCustomer, true);
	}

	@Override
	public void clearCache(List<AccountCustomer> accountCustomers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountCustomer accountCustomer : accountCustomers) {
			entityCache.removeResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
				AccountCustomerImpl.class, accountCustomer.getPrimaryKey());

			clearUniqueFindersCache((AccountCustomerModelImpl)accountCustomer,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		AccountCustomerModelImpl accountCustomerModelImpl) {
		Object[] args = new Object[] {
				accountCustomerModelImpl.getUserId(),
				accountCustomerModelImpl.getAccountEntryId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_U_AEI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_U_AEI, args,
			accountCustomerModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AccountCustomerModelImpl accountCustomerModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					accountCustomerModelImpl.getUserId(),
					accountCustomerModelImpl.getAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_AEI, args);
		}

		if ((accountCustomerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_AEI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					accountCustomerModelImpl.getOriginalUserId(),
					accountCustomerModelImpl.getOriginalAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_AEI, args);
		}
	}

	/**
	 * Creates a new account customer with the primary key. Does not add the account customer to the database.
	 *
	 * @param accountCustomerId the primary key for the new account customer
	 * @return the new account customer
	 */
	@Override
	public AccountCustomer create(long accountCustomerId) {
		AccountCustomer accountCustomer = new AccountCustomerImpl();

		accountCustomer.setNew(true);
		accountCustomer.setPrimaryKey(accountCustomerId);

		return accountCustomer;
	}

	/**
	 * Removes the account customer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountCustomerId the primary key of the account customer
	 * @return the account customer that was removed
	 * @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 */
	@Override
	public AccountCustomer remove(long accountCustomerId)
		throws NoSuchAccountCustomerException {
		return remove((Serializable)accountCustomerId);
	}

	/**
	 * Removes the account customer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account customer
	 * @return the account customer that was removed
	 * @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 */
	@Override
	public AccountCustomer remove(Serializable primaryKey)
		throws NoSuchAccountCustomerException {
		Session session = null;

		try {
			session = openSession();

			AccountCustomer accountCustomer = (AccountCustomer)session.get(AccountCustomerImpl.class,
					primaryKey);

			if (accountCustomer == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountCustomerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountCustomer);
		}
		catch (NoSuchAccountCustomerException nsee) {
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
	protected AccountCustomer removeImpl(AccountCustomer accountCustomer) {
		accountCustomer = toUnwrappedModel(accountCustomer);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountCustomer)) {
				accountCustomer = (AccountCustomer)session.get(AccountCustomerImpl.class,
						accountCustomer.getPrimaryKeyObj());
			}

			if (accountCustomer != null) {
				session.delete(accountCustomer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accountCustomer != null) {
			clearCache(accountCustomer);
		}

		return accountCustomer;
	}

	@Override
	public AccountCustomer updateImpl(AccountCustomer accountCustomer) {
		accountCustomer = toUnwrappedModel(accountCustomer);

		boolean isNew = accountCustomer.isNew();

		AccountCustomerModelImpl accountCustomerModelImpl = (AccountCustomerModelImpl)accountCustomer;

		Session session = null;

		try {
			session = openSession();

			if (accountCustomer.isNew()) {
				session.save(accountCustomer);

				accountCustomer.setNew(false);
			}
			else {
				accountCustomer = (AccountCustomer)session.merge(accountCustomer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AccountCustomerModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { accountCustomerModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
				args);

			args = new Object[] { accountCustomerModelImpl.getAccountEntryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			args = new Object[] {
					accountCustomerModelImpl.getAccountEntryId(),
					accountCustomerModelImpl.getRole()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_R, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accountCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountCustomerModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { accountCustomerModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((accountCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountCustomerModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] { accountCustomerModelImpl.getAccountEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((accountCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountCustomerModelImpl.getOriginalAccountEntryId(),
						accountCustomerModelImpl.getOriginalRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R,
					args);

				args = new Object[] {
						accountCustomerModelImpl.getAccountEntryId(),
						accountCustomerModelImpl.getRole()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_R,
					args);
			}
		}

		entityCache.putResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
			AccountCustomerImpl.class, accountCustomer.getPrimaryKey(),
			accountCustomer, false);

		clearUniqueFindersCache(accountCustomerModelImpl, false);
		cacheUniqueFindersCache(accountCustomerModelImpl);

		accountCustomer.resetOriginalValues();

		return accountCustomer;
	}

	protected AccountCustomer toUnwrappedModel(AccountCustomer accountCustomer) {
		if (accountCustomer instanceof AccountCustomerImpl) {
			return accountCustomer;
		}

		AccountCustomerImpl accountCustomerImpl = new AccountCustomerImpl();

		accountCustomerImpl.setNew(accountCustomer.isNew());
		accountCustomerImpl.setPrimaryKey(accountCustomer.getPrimaryKey());

		accountCustomerImpl.setAccountCustomerId(accountCustomer.getAccountCustomerId());
		accountCustomerImpl.setUserId(accountCustomer.getUserId());
		accountCustomerImpl.setAccountEntryId(accountCustomer.getAccountEntryId());
		accountCustomerImpl.setRole(accountCustomer.getRole());

		return accountCustomerImpl;
	}

	/**
	 * Returns the account customer with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account customer
	 * @return the account customer
	 * @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 */
	@Override
	public AccountCustomer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountCustomerException {
		AccountCustomer accountCustomer = fetchByPrimaryKey(primaryKey);

		if (accountCustomer == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountCustomerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accountCustomer;
	}

	/**
	 * Returns the account customer with the primary key or throws a {@link NoSuchAccountCustomerException} if it could not be found.
	 *
	 * @param accountCustomerId the primary key of the account customer
	 * @return the account customer
	 * @throws NoSuchAccountCustomerException if a account customer with the primary key could not be found
	 */
	@Override
	public AccountCustomer findByPrimaryKey(long accountCustomerId)
		throws NoSuchAccountCustomerException {
		return findByPrimaryKey((Serializable)accountCustomerId);
	}

	/**
	 * Returns the account customer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account customer
	 * @return the account customer, or <code>null</code> if a account customer with the primary key could not be found
	 */
	@Override
	public AccountCustomer fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
				AccountCustomerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccountCustomer accountCustomer = (AccountCustomer)serializable;

		if (accountCustomer == null) {
			Session session = null;

			try {
				session = openSession();

				accountCustomer = (AccountCustomer)session.get(AccountCustomerImpl.class,
						primaryKey);

				if (accountCustomer != null) {
					cacheResult(accountCustomer);
				}
				else {
					entityCache.putResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
						AccountCustomerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
					AccountCustomerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accountCustomer;
	}

	/**
	 * Returns the account customer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountCustomerId the primary key of the account customer
	 * @return the account customer, or <code>null</code> if a account customer with the primary key could not be found
	 */
	@Override
	public AccountCustomer fetchByPrimaryKey(long accountCustomerId) {
		return fetchByPrimaryKey((Serializable)accountCustomerId);
	}

	@Override
	public Map<Serializable, AccountCustomer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccountCustomer> map = new HashMap<Serializable, AccountCustomer>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccountCustomer accountCustomer = fetchByPrimaryKey(primaryKey);

			if (accountCustomer != null) {
				map.put(primaryKey, accountCustomer);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
					AccountCustomerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccountCustomer)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCOUNTCUSTOMER_WHERE_PKS_IN);

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

			for (AccountCustomer accountCustomer : (List<AccountCustomer>)q.list()) {
				map.put(accountCustomer.getPrimaryKeyObj(), accountCustomer);

				cacheResult(accountCustomer);

				uncachedPrimaryKeys.remove(accountCustomer.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccountCustomerModelImpl.ENTITY_CACHE_ENABLED,
					AccountCustomerImpl.class, primaryKey, nullModel);
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
	 * Returns all the account customers.
	 *
	 * @return the account customers
	 */
	@Override
	public List<AccountCustomer> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account customers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @return the range of account customers
	 */
	@Override
	public List<AccountCustomer> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account customers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account customers
	 */
	@Override
	public List<AccountCustomer> findAll(int start, int end,
		OrderByComparator<AccountCustomer> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account customers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCustomerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account customers
	 * @param end the upper bound of the range of account customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of account customers
	 */
	@Override
	public List<AccountCustomer> findAll(int start, int end,
		OrderByComparator<AccountCustomer> orderByComparator,
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

		List<AccountCustomer> list = null;

		if (retrieveFromCache) {
			list = (List<AccountCustomer>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCOUNTCUSTOMER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTCUSTOMER;

				if (pagination) {
					sql = sql.concat(AccountCustomerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccountCustomer>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountCustomer>)QueryUtil.list(q,
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
	 * Removes all the account customers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountCustomer accountCustomer : findAll()) {
			remove(accountCustomer);
		}
	}

	/**
	 * Returns the number of account customers.
	 *
	 * @return the number of account customers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTCUSTOMER);

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
		return AccountCustomerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account customer persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccountCustomerImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ACCOUNTCUSTOMER = "SELECT accountCustomer FROM AccountCustomer accountCustomer";
	private static final String _SQL_SELECT_ACCOUNTCUSTOMER_WHERE_PKS_IN = "SELECT accountCustomer FROM AccountCustomer accountCustomer WHERE accountCustomerId IN (";
	private static final String _SQL_SELECT_ACCOUNTCUSTOMER_WHERE = "SELECT accountCustomer FROM AccountCustomer accountCustomer WHERE ";
	private static final String _SQL_COUNT_ACCOUNTCUSTOMER = "SELECT COUNT(accountCustomer) FROM AccountCustomer accountCustomer";
	private static final String _SQL_COUNT_ACCOUNTCUSTOMER_WHERE = "SELECT COUNT(accountCustomer) FROM AccountCustomer accountCustomer WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountCustomer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountCustomer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountCustomer exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AccountCustomerPersistenceImpl.class);
}
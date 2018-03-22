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

import com.liferay.osb.exception.NoSuchAccountLinkException;
import com.liferay.osb.model.AccountLink;
import com.liferay.osb.model.impl.AccountLinkImpl;
import com.liferay.osb.model.impl.AccountLinkModelImpl;
import com.liferay.osb.service.persistence.AccountLinkPersistence;

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
import java.util.Set;

/**
 * The persistence implementation for the account link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountLinkPersistence
 * @see com.liferay.osb.service.persistence.AccountLinkUtil
 * @generated
 */
@ProviderType
public class AccountLinkPersistenceImpl extends BasePersistenceImpl<AccountLink>
	implements AccountLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountLinkUtil} to access the account link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
			AccountLinkModelImpl.FINDER_CACHE_ENABLED, AccountLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
			AccountLinkModelImpl.FINDER_CACHE_ENABLED, AccountLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
			AccountLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
			AccountLinkModelImpl.FINDER_CACHE_ENABLED, AccountLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
			AccountLinkModelImpl.FINDER_CACHE_ENABLED, AccountLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			AccountLinkModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
			AccountLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the account links where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account links
	 */
	@Override
	public List<AccountLink> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account links where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account links
	 * @param end the upper bound of the range of account links (not inclusive)
	 * @return the range of matching account links
	 */
	@Override
	public List<AccountLink> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account links where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account links
	 * @param end the upper bound of the range of account links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account links
	 */
	@Override
	public List<AccountLink> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountLink> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account links where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account links
	 * @param end the upper bound of the range of account links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account links
	 */
	@Override
	public List<AccountLink> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountLink> orderByComparator,
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

		List<AccountLink> list = null;

		if (retrieveFromCache) {
			list = (List<AccountLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountLink accountLink : list) {
					if ((accountEntryId != accountLink.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTLINK_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<AccountLink>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountLink>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first account link in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account link
	 * @throws NoSuchAccountLinkException if a matching account link could not be found
	 */
	@Override
	public AccountLink findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountLink> orderByComparator)
		throws NoSuchAccountLinkException {
		AccountLink accountLink = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountLink != null) {
			return accountLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountLinkException(msg.toString());
	}

	/**
	 * Returns the first account link in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account link, or <code>null</code> if a matching account link could not be found
	 */
	@Override
	public AccountLink fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountLink> orderByComparator) {
		List<AccountLink> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account link in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account link
	 * @throws NoSuchAccountLinkException if a matching account link could not be found
	 */
	@Override
	public AccountLink findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountLink> orderByComparator)
		throws NoSuchAccountLinkException {
		AccountLink accountLink = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountLink != null) {
			return accountLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountLinkException(msg.toString());
	}

	/**
	 * Returns the last account link in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account link, or <code>null</code> if a matching account link could not be found
	 */
	@Override
	public AccountLink fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountLink> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<AccountLink> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account links before and after the current account link in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountLinkId the primary key of the current account link
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account link
	 * @throws NoSuchAccountLinkException if a account link with the primary key could not be found
	 */
	@Override
	public AccountLink[] findByAccountEntryId_PrevAndNext(long accountLinkId,
		long accountEntryId, OrderByComparator<AccountLink> orderByComparator)
		throws NoSuchAccountLinkException {
		AccountLink accountLink = findByPrimaryKey(accountLinkId);

		Session session = null;

		try {
			session = openSession();

			AccountLink[] array = new AccountLinkImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, accountLink,
					accountEntryId, orderByComparator, true);

			array[1] = accountLink;

			array[2] = getByAccountEntryId_PrevAndNext(session, accountLink,
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

	protected AccountLink getByAccountEntryId_PrevAndNext(Session session,
		AccountLink accountLink, long accountEntryId,
		OrderByComparator<AccountLink> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTLINK_WHERE);

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
			query.append(AccountLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account links where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (AccountLink accountLink : findByAccountEntryId(accountEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountLink);
		}
	}

	/**
	 * Returns the number of account links where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account links
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTLINK_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountLink.accountEntryId = ?";

	public AccountLinkPersistenceImpl() {
		setModelClass(AccountLink.class);
	}

	/**
	 * Caches the account link in the entity cache if it is enabled.
	 *
	 * @param accountLink the account link
	 */
	@Override
	public void cacheResult(AccountLink accountLink) {
		entityCache.putResult(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
			AccountLinkImpl.class, accountLink.getPrimaryKey(), accountLink);

		accountLink.resetOriginalValues();
	}

	/**
	 * Caches the account links in the entity cache if it is enabled.
	 *
	 * @param accountLinks the account links
	 */
	@Override
	public void cacheResult(List<AccountLink> accountLinks) {
		for (AccountLink accountLink : accountLinks) {
			if (entityCache.getResult(
						AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
						AccountLinkImpl.class, accountLink.getPrimaryKey()) == null) {
				cacheResult(accountLink);
			}
			else {
				accountLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account links.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountLinkImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account link.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountLink accountLink) {
		entityCache.removeResult(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
			AccountLinkImpl.class, accountLink.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccountLink> accountLinks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountLink accountLink : accountLinks) {
			entityCache.removeResult(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
				AccountLinkImpl.class, accountLink.getPrimaryKey());
		}
	}

	/**
	 * Creates a new account link with the primary key. Does not add the account link to the database.
	 *
	 * @param accountLinkId the primary key for the new account link
	 * @return the new account link
	 */
	@Override
	public AccountLink create(long accountLinkId) {
		AccountLink accountLink = new AccountLinkImpl();

		accountLink.setNew(true);
		accountLink.setPrimaryKey(accountLinkId);

		return accountLink;
	}

	/**
	 * Removes the account link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountLinkId the primary key of the account link
	 * @return the account link that was removed
	 * @throws NoSuchAccountLinkException if a account link with the primary key could not be found
	 */
	@Override
	public AccountLink remove(long accountLinkId)
		throws NoSuchAccountLinkException {
		return remove((Serializable)accountLinkId);
	}

	/**
	 * Removes the account link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account link
	 * @return the account link that was removed
	 * @throws NoSuchAccountLinkException if a account link with the primary key could not be found
	 */
	@Override
	public AccountLink remove(Serializable primaryKey)
		throws NoSuchAccountLinkException {
		Session session = null;

		try {
			session = openSession();

			AccountLink accountLink = (AccountLink)session.get(AccountLinkImpl.class,
					primaryKey);

			if (accountLink == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountLink);
		}
		catch (NoSuchAccountLinkException nsee) {
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
	protected AccountLink removeImpl(AccountLink accountLink) {
		accountLink = toUnwrappedModel(accountLink);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountLink)) {
				accountLink = (AccountLink)session.get(AccountLinkImpl.class,
						accountLink.getPrimaryKeyObj());
			}

			if (accountLink != null) {
				session.delete(accountLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accountLink != null) {
			clearCache(accountLink);
		}

		return accountLink;
	}

	@Override
	public AccountLink updateImpl(AccountLink accountLink) {
		accountLink = toUnwrappedModel(accountLink);

		boolean isNew = accountLink.isNew();

		AccountLinkModelImpl accountLinkModelImpl = (AccountLinkModelImpl)accountLink;

		Session session = null;

		try {
			session = openSession();

			if (accountLink.isNew()) {
				session.save(accountLink);

				accountLink.setNew(false);
			}
			else {
				accountLink = (AccountLink)session.merge(accountLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AccountLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					accountLinkModelImpl.getAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accountLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountLinkModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] { accountLinkModelImpl.getAccountEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}
		}

		entityCache.putResult(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
			AccountLinkImpl.class, accountLink.getPrimaryKey(), accountLink,
			false);

		accountLink.resetOriginalValues();

		return accountLink;
	}

	protected AccountLink toUnwrappedModel(AccountLink accountLink) {
		if (accountLink instanceof AccountLinkImpl) {
			return accountLink;
		}

		AccountLinkImpl accountLinkImpl = new AccountLinkImpl();

		accountLinkImpl.setNew(accountLink.isNew());
		accountLinkImpl.setPrimaryKey(accountLink.getPrimaryKey());

		accountLinkImpl.setAccountLinkId(accountLink.getAccountLinkId());
		accountLinkImpl.setUserId(accountLink.getUserId());
		accountLinkImpl.setUserName(accountLink.getUserName());
		accountLinkImpl.setCreateDate(accountLink.getCreateDate());
		accountLinkImpl.setAccountEntryId(accountLink.getAccountEntryId());
		accountLinkImpl.setUrl(accountLink.getUrl());

		return accountLinkImpl;
	}

	/**
	 * Returns the account link with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account link
	 * @return the account link
	 * @throws NoSuchAccountLinkException if a account link with the primary key could not be found
	 */
	@Override
	public AccountLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountLinkException {
		AccountLink accountLink = fetchByPrimaryKey(primaryKey);

		if (accountLink == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accountLink;
	}

	/**
	 * Returns the account link with the primary key or throws a {@link NoSuchAccountLinkException} if it could not be found.
	 *
	 * @param accountLinkId the primary key of the account link
	 * @return the account link
	 * @throws NoSuchAccountLinkException if a account link with the primary key could not be found
	 */
	@Override
	public AccountLink findByPrimaryKey(long accountLinkId)
		throws NoSuchAccountLinkException {
		return findByPrimaryKey((Serializable)accountLinkId);
	}

	/**
	 * Returns the account link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account link
	 * @return the account link, or <code>null</code> if a account link with the primary key could not be found
	 */
	@Override
	public AccountLink fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
				AccountLinkImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccountLink accountLink = (AccountLink)serializable;

		if (accountLink == null) {
			Session session = null;

			try {
				session = openSession();

				accountLink = (AccountLink)session.get(AccountLinkImpl.class,
						primaryKey);

				if (accountLink != null) {
					cacheResult(accountLink);
				}
				else {
					entityCache.putResult(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
						AccountLinkImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
					AccountLinkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accountLink;
	}

	/**
	 * Returns the account link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountLinkId the primary key of the account link
	 * @return the account link, or <code>null</code> if a account link with the primary key could not be found
	 */
	@Override
	public AccountLink fetchByPrimaryKey(long accountLinkId) {
		return fetchByPrimaryKey((Serializable)accountLinkId);
	}

	@Override
	public Map<Serializable, AccountLink> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccountLink> map = new HashMap<Serializable, AccountLink>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccountLink accountLink = fetchByPrimaryKey(primaryKey);

			if (accountLink != null) {
				map.put(primaryKey, accountLink);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
					AccountLinkImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccountLink)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCOUNTLINK_WHERE_PKS_IN);

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

			for (AccountLink accountLink : (List<AccountLink>)q.list()) {
				map.put(accountLink.getPrimaryKeyObj(), accountLink);

				cacheResult(accountLink);

				uncachedPrimaryKeys.remove(accountLink.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccountLinkModelImpl.ENTITY_CACHE_ENABLED,
					AccountLinkImpl.class, primaryKey, nullModel);
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
	 * Returns all the account links.
	 *
	 * @return the account links
	 */
	@Override
	public List<AccountLink> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account links
	 * @param end the upper bound of the range of account links (not inclusive)
	 * @return the range of account links
	 */
	@Override
	public List<AccountLink> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account links
	 * @param end the upper bound of the range of account links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account links
	 */
	@Override
	public List<AccountLink> findAll(int start, int end,
		OrderByComparator<AccountLink> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account links
	 * @param end the upper bound of the range of account links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of account links
	 */
	@Override
	public List<AccountLink> findAll(int start, int end,
		OrderByComparator<AccountLink> orderByComparator,
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

		List<AccountLink> list = null;

		if (retrieveFromCache) {
			list = (List<AccountLink>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCOUNTLINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTLINK;

				if (pagination) {
					sql = sql.concat(AccountLinkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccountLink>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountLink>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the account links from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountLink accountLink : findAll()) {
			remove(accountLink);
		}
	}

	/**
	 * Returns the number of account links.
	 *
	 * @return the number of account links
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTLINK);

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
		return AccountLinkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account link persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccountLinkImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ACCOUNTLINK = "SELECT accountLink FROM AccountLink accountLink";
	private static final String _SQL_SELECT_ACCOUNTLINK_WHERE_PKS_IN = "SELECT accountLink FROM AccountLink accountLink WHERE accountLinkId IN (";
	private static final String _SQL_SELECT_ACCOUNTLINK_WHERE = "SELECT accountLink FROM AccountLink accountLink WHERE ";
	private static final String _SQL_COUNT_ACCOUNTLINK = "SELECT COUNT(accountLink) FROM AccountLink accountLink";
	private static final String _SQL_COUNT_ACCOUNTLINK_WHERE = "SELECT COUNT(accountLink) FROM AccountLink accountLink WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountLink exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountLink exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AccountLinkPersistenceImpl.class);
}
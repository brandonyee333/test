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

import com.liferay.osb.exception.NoSuchAccountInformationException;
import com.liferay.osb.model.AccountInformation;
import com.liferay.osb.model.impl.AccountInformationImpl;
import com.liferay.osb.model.impl.AccountInformationModelImpl;
import com.liferay.osb.service.persistence.AccountInformationPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
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
 * The persistence implementation for the account information service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountInformationPersistence
 * @see com.liferay.osb.service.persistence.AccountInformationUtil
 * @generated
 */
@ProviderType
public class AccountInformationPersistenceImpl extends BasePersistenceImpl<AccountInformation>
	implements AccountInformationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountInformationUtil} to access the account information persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountInformationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_API = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_API",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API =
		new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_API",
			new String[] { Long.class.getName(), Long.class.getName() },
			AccountInformationModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountInformationModelImpl.ACCOUNTPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_API = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_API",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the matching account informations
	 */
	@Override
	public List<AccountInformation> findByAEI_API(long accountEntryId,
		long accountProjectId) {
		return findByAEI_API(accountEntryId, accountProjectId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @return the range of matching account informations
	 */
	@Override
	public List<AccountInformation> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end) {
		return findByAEI_API(accountEntryId, accountProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account informations
	 */
	@Override
	public List<AccountInformation> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end,
		OrderByComparator<AccountInformation> orderByComparator) {
		return findByAEI_API(accountEntryId, accountProjectId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account informations
	 */
	@Override
	public List<AccountInformation> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end,
		OrderByComparator<AccountInformation> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API;
			finderArgs = new Object[] { accountEntryId, accountProjectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_API;
			finderArgs = new Object[] {
					accountEntryId, accountProjectId,
					
					start, end, orderByComparator
				};
		}

		List<AccountInformation> list = null;

		if (retrieveFromCache) {
			list = (List<AccountInformation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountInformation accountInformation : list) {
					if ((accountEntryId != accountInformation.getAccountEntryId()) ||
							(accountProjectId != accountInformation.getAccountProjectId())) {
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

			query.append(_SQL_SELECT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountInformationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				if (!pagination) {
					list = (List<AccountInformation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountInformation>)QueryUtil.list(q,
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
	 * Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account information
	 * @throws NoSuchAccountInformationException if a matching account information could not be found
	 */
	@Override
	public AccountInformation findByAEI_API_First(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException {
		AccountInformation accountInformation = fetchByAEI_API_First(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountInformation != null) {
			return accountInformation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountInformationException(msg.toString());
	}

	/**
	 * Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account information, or <code>null</code> if a matching account information could not be found
	 */
	@Override
	public AccountInformation fetchByAEI_API_First(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator) {
		List<AccountInformation> list = findByAEI_API(accountEntryId,
				accountProjectId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account information
	 * @throws NoSuchAccountInformationException if a matching account information could not be found
	 */
	@Override
	public AccountInformation findByAEI_API_Last(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException {
		AccountInformation accountInformation = fetchByAEI_API_Last(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountInformation != null) {
			return accountInformation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountInformationException(msg.toString());
	}

	/**
	 * Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account information, or <code>null</code> if a matching account information could not be found
	 */
	@Override
	public AccountInformation fetchByAEI_API_Last(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator) {
		int count = countByAEI_API(accountEntryId, accountProjectId);

		if (count == 0) {
			return null;
		}

		List<AccountInformation> list = findByAEI_API(accountEntryId,
				accountProjectId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account informations before and after the current account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountInformationId the primary key of the current account information
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account information
	 * @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	 */
	@Override
	public AccountInformation[] findByAEI_API_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException {
		AccountInformation accountInformation = findByPrimaryKey(accountInformationId);

		Session session = null;

		try {
			session = openSession();

			AccountInformation[] array = new AccountInformationImpl[3];

			array[0] = getByAEI_API_PrevAndNext(session, accountInformation,
					accountEntryId, accountProjectId, orderByComparator, true);

			array[1] = accountInformation;

			array[2] = getByAEI_API_PrevAndNext(session, accountInformation,
					accountEntryId, accountProjectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountInformation getByAEI_API_PrevAndNext(Session session,
		AccountInformation accountInformation, long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator,
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

		query.append(_SQL_SELECT_ACCOUNTINFORMATION_WHERE);

		query.append(_FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2);

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
			query.append(AccountInformationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(accountProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountInformation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountInformation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account informations where accountEntryId = &#63; and accountProjectId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 */
	@Override
	public void removeByAEI_API(long accountEntryId, long accountProjectId) {
		for (AccountInformation accountInformation : findByAEI_API(
				accountEntryId, accountProjectId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(accountInformation);
		}
	}

	/**
	 * Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the number of matching account informations
	 */
	@Override
	public int countByAEI_API(long accountEntryId, long accountProjectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_API;

		Object[] finderArgs = new Object[] { accountEntryId, accountProjectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

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

	private static final String _FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2 = "accountInformation.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2 = "accountInformation.accountProjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTAPI =
		new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_NotAPI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTAPI =
		new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByAEI_NotAPI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the matching account informations
	 */
	@Override
	public List<AccountInformation> findByAEI_NotAPI(long accountEntryId,
		long accountProjectId) {
		return findByAEI_NotAPI(accountEntryId, accountProjectId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @return the range of matching account informations
	 */
	@Override
	public List<AccountInformation> findByAEI_NotAPI(long accountEntryId,
		long accountProjectId, int start, int end) {
		return findByAEI_NotAPI(accountEntryId, accountProjectId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account informations
	 */
	@Override
	public List<AccountInformation> findByAEI_NotAPI(long accountEntryId,
		long accountProjectId, int start, int end,
		OrderByComparator<AccountInformation> orderByComparator) {
		return findByAEI_NotAPI(accountEntryId, accountProjectId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account informations
	 */
	@Override
	public List<AccountInformation> findByAEI_NotAPI(long accountEntryId,
		long accountProjectId, int start, int end,
		OrderByComparator<AccountInformation> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_NOTAPI;
		finderArgs = new Object[] {
				accountEntryId, accountProjectId,
				
				start, end, orderByComparator
			};

		List<AccountInformation> list = null;

		if (retrieveFromCache) {
			list = (List<AccountInformation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountInformation accountInformation : list) {
					if ((accountEntryId != accountInformation.getAccountEntryId()) ||
							(accountProjectId == accountInformation.getAccountProjectId())) {
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

			query.append(_SQL_SELECT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountInformationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				if (!pagination) {
					list = (List<AccountInformation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountInformation>)QueryUtil.list(q,
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
	 * Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account information
	 * @throws NoSuchAccountInformationException if a matching account information could not be found
	 */
	@Override
	public AccountInformation findByAEI_NotAPI_First(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException {
		AccountInformation accountInformation = fetchByAEI_NotAPI_First(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountInformation != null) {
			return accountInformation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountInformationException(msg.toString());
	}

	/**
	 * Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account information, or <code>null</code> if a matching account information could not be found
	 */
	@Override
	public AccountInformation fetchByAEI_NotAPI_First(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator) {
		List<AccountInformation> list = findByAEI_NotAPI(accountEntryId,
				accountProjectId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account information
	 * @throws NoSuchAccountInformationException if a matching account information could not be found
	 */
	@Override
	public AccountInformation findByAEI_NotAPI_Last(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException {
		AccountInformation accountInformation = fetchByAEI_NotAPI_Last(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountInformation != null) {
			return accountInformation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountInformationException(msg.toString());
	}

	/**
	 * Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account information, or <code>null</code> if a matching account information could not be found
	 */
	@Override
	public AccountInformation fetchByAEI_NotAPI_Last(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator) {
		int count = countByAEI_NotAPI(accountEntryId, accountProjectId);

		if (count == 0) {
			return null;
		}

		List<AccountInformation> list = findByAEI_NotAPI(accountEntryId,
				accountProjectId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account informations before and after the current account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountInformationId the primary key of the current account information
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account information
	 * @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	 */
	@Override
	public AccountInformation[] findByAEI_NotAPI_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws NoSuchAccountInformationException {
		AccountInformation accountInformation = findByPrimaryKey(accountInformationId);

		Session session = null;

		try {
			session = openSession();

			AccountInformation[] array = new AccountInformationImpl[3];

			array[0] = getByAEI_NotAPI_PrevAndNext(session, accountInformation,
					accountEntryId, accountProjectId, orderByComparator, true);

			array[1] = accountInformation;

			array[2] = getByAEI_NotAPI_PrevAndNext(session, accountInformation,
					accountEntryId, accountProjectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountInformation getByAEI_NotAPI_PrevAndNext(Session session,
		AccountInformation accountInformation, long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator,
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

		query.append(_SQL_SELECT_ACCOUNTINFORMATION_WHERE);

		query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTPROJECTID_2);

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
			query.append(AccountInformationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(accountProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountInformation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountInformation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 */
	@Override
	public void removeByAEI_NotAPI(long accountEntryId, long accountProjectId) {
		for (AccountInformation accountInformation : findByAEI_NotAPI(
				accountEntryId, accountProjectId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(accountInformation);
		}
	}

	/**
	 * Returns the number of account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the number of matching account informations
	 */
	@Override
	public int countByAEI_NotAPI(long accountEntryId, long accountProjectId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_NOTAPI;

		Object[] finderArgs = new Object[] { accountEntryId, accountProjectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_NOTAPI_ACCOUNTPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

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

	private static final String _FINDER_COLUMN_AEI_NOTAPI_ACCOUNTENTRYID_2 = "accountInformation.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_NOTAPI_ACCOUNTPROJECTID_2 = "accountInformation.accountProjectId != ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_API_FI = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED,
			AccountInformationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAEI_API_FI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AccountInformationModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountInformationModelImpl.ACCOUNTPROJECTID_COLUMN_BITMASK |
			AccountInformationModelImpl.FIELDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_API_FI = new FinderPath(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_API_FI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or throws a {@link NoSuchAccountInformationException} if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fieldId the field ID
	 * @return the matching account information
	 * @throws NoSuchAccountInformationException if a matching account information could not be found
	 */
	@Override
	public AccountInformation findByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId)
		throws NoSuchAccountInformationException {
		AccountInformation accountInformation = fetchByAEI_API_FI(accountEntryId,
				accountProjectId, fieldId);

		if (accountInformation == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEntryId=");
			msg.append(accountEntryId);

			msg.append(", accountProjectId=");
			msg.append(accountProjectId);

			msg.append(", fieldId=");
			msg.append(fieldId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountInformationException(msg.toString());
		}

		return accountInformation;
	}

	/**
	 * Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fieldId the field ID
	 * @return the matching account information, or <code>null</code> if a matching account information could not be found
	 */
	@Override
	public AccountInformation fetchByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId) {
		return fetchByAEI_API_FI(accountEntryId, accountProjectId, fieldId, true);
	}

	/**
	 * Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fieldId the field ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching account information, or <code>null</code> if a matching account information could not be found
	 */
	@Override
	public AccountInformation fetchByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				accountEntryId, accountProjectId, fieldId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_AEI_API_FI,
					finderArgs, this);
		}

		if (result instanceof AccountInformation) {
			AccountInformation accountInformation = (AccountInformation)result;

			if ((accountEntryId != accountInformation.getAccountEntryId()) ||
					(accountProjectId != accountInformation.getAccountProjectId()) ||
					(fieldId != accountInformation.getFieldId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_FI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_FI_ACCOUNTPROJECTID_2);

			query.append(_FINDER_COLUMN_AEI_API_FI_FIELDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				qPos.add(fieldId);

				List<AccountInformation> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_API_FI,
						finderArgs, list);
				}
				else {
					AccountInformation accountInformation = list.get(0);

					result = accountInformation;

					cacheResult(accountInformation);

					if ((accountInformation.getAccountEntryId() != accountEntryId) ||
							(accountInformation.getAccountProjectId() != accountProjectId) ||
							(accountInformation.getFieldId() != fieldId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_API_FI,
							finderArgs, accountInformation);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FI,
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
			return (AccountInformation)result;
		}
	}

	/**
	 * Removes the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fieldId the field ID
	 * @return the account information that was removed
	 */
	@Override
	public AccountInformation removeByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId)
		throws NoSuchAccountInformationException {
		AccountInformation accountInformation = findByAEI_API_FI(accountEntryId,
				accountProjectId, fieldId);

		return remove(accountInformation);
	}

	/**
	 * Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fieldId the field ID
	 * @return the number of matching account informations
	 */
	@Override
	public int countByAEI_API_FI(long accountEntryId, long accountProjectId,
		int fieldId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_API_FI;

		Object[] finderArgs = new Object[] {
				accountEntryId, accountProjectId, fieldId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACCOUNTINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_FI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_FI_ACCOUNTPROJECTID_2);

			query.append(_FINDER_COLUMN_AEI_API_FI_FIELDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				qPos.add(fieldId);

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

	private static final String _FINDER_COLUMN_AEI_API_FI_ACCOUNTENTRYID_2 = "accountInformation.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_FI_ACCOUNTPROJECTID_2 = "accountInformation.accountProjectId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_FI_FIELDID_2 = "accountInformation.fieldId = ?";

	public AccountInformationPersistenceImpl() {
		setModelClass(AccountInformation.class);
	}

	/**
	 * Caches the account information in the entity cache if it is enabled.
	 *
	 * @param accountInformation the account information
	 */
	@Override
	public void cacheResult(AccountInformation accountInformation) {
		entityCache.putResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationImpl.class, accountInformation.getPrimaryKey(),
			accountInformation);

		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_API_FI,
			new Object[] {
				accountInformation.getAccountEntryId(),
				accountInformation.getAccountProjectId(),
				accountInformation.getFieldId()
			}, accountInformation);

		accountInformation.resetOriginalValues();
	}

	/**
	 * Caches the account informations in the entity cache if it is enabled.
	 *
	 * @param accountInformations the account informations
	 */
	@Override
	public void cacheResult(List<AccountInformation> accountInformations) {
		for (AccountInformation accountInformation : accountInformations) {
			if (entityCache.getResult(
						AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
						AccountInformationImpl.class,
						accountInformation.getPrimaryKey()) == null) {
				cacheResult(accountInformation);
			}
			else {
				accountInformation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account informations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountInformationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account information.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountInformation accountInformation) {
		entityCache.removeResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationImpl.class, accountInformation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AccountInformationModelImpl)accountInformation,
			true);
	}

	@Override
	public void clearCache(List<AccountInformation> accountInformations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountInformation accountInformation : accountInformations) {
			entityCache.removeResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
				AccountInformationImpl.class, accountInformation.getPrimaryKey());

			clearUniqueFindersCache((AccountInformationModelImpl)accountInformation,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		AccountInformationModelImpl accountInformationModelImpl) {
		Object[] args = new Object[] {
				accountInformationModelImpl.getAccountEntryId(),
				accountInformationModelImpl.getAccountProjectId(),
				accountInformationModelImpl.getFieldId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_AEI_API_FI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_API_FI, args,
			accountInformationModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AccountInformationModelImpl accountInformationModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					accountInformationModelImpl.getAccountEntryId(),
					accountInformationModelImpl.getAccountProjectId(),
					accountInformationModelImpl.getFieldId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API_FI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FI, args);
		}

		if ((accountInformationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_API_FI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					accountInformationModelImpl.getOriginalAccountEntryId(),
					accountInformationModelImpl.getOriginalAccountProjectId(),
					accountInformationModelImpl.getOriginalFieldId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API_FI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FI, args);
		}
	}

	/**
	 * Creates a new account information with the primary key. Does not add the account information to the database.
	 *
	 * @param accountInformationId the primary key for the new account information
	 * @return the new account information
	 */
	@Override
	public AccountInformation create(long accountInformationId) {
		AccountInformation accountInformation = new AccountInformationImpl();

		accountInformation.setNew(true);
		accountInformation.setPrimaryKey(accountInformationId);

		return accountInformation;
	}

	/**
	 * Removes the account information with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountInformationId the primary key of the account information
	 * @return the account information that was removed
	 * @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	 */
	@Override
	public AccountInformation remove(long accountInformationId)
		throws NoSuchAccountInformationException {
		return remove((Serializable)accountInformationId);
	}

	/**
	 * Removes the account information with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account information
	 * @return the account information that was removed
	 * @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	 */
	@Override
	public AccountInformation remove(Serializable primaryKey)
		throws NoSuchAccountInformationException {
		Session session = null;

		try {
			session = openSession();

			AccountInformation accountInformation = (AccountInformation)session.get(AccountInformationImpl.class,
					primaryKey);

			if (accountInformation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountInformationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountInformation);
		}
		catch (NoSuchAccountInformationException nsee) {
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
	protected AccountInformation removeImpl(
		AccountInformation accountInformation) {
		accountInformation = toUnwrappedModel(accountInformation);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountInformation)) {
				accountInformation = (AccountInformation)session.get(AccountInformationImpl.class,
						accountInformation.getPrimaryKeyObj());
			}

			if (accountInformation != null) {
				session.delete(accountInformation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accountInformation != null) {
			clearCache(accountInformation);
		}

		return accountInformation;
	}

	@Override
	public AccountInformation updateImpl(AccountInformation accountInformation) {
		accountInformation = toUnwrappedModel(accountInformation);

		boolean isNew = accountInformation.isNew();

		AccountInformationModelImpl accountInformationModelImpl = (AccountInformationModelImpl)accountInformation;

		Session session = null;

		try {
			session = openSession();

			if (accountInformation.isNew()) {
				session.save(accountInformation);

				accountInformation.setNew(false);
			}
			else {
				accountInformation = (AccountInformation)session.merge(accountInformation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AccountInformationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					accountInformationModelImpl.getAccountEntryId(),
					accountInformationModelImpl.getAccountProjectId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accountInformationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountInformationModelImpl.getOriginalAccountEntryId(),
						accountInformationModelImpl.getOriginalAccountProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API,
					args);

				args = new Object[] {
						accountInformationModelImpl.getAccountEntryId(),
						accountInformationModelImpl.getAccountProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API,
					args);
			}
		}

		entityCache.putResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
			AccountInformationImpl.class, accountInformation.getPrimaryKey(),
			accountInformation, false);

		clearUniqueFindersCache(accountInformationModelImpl, false);
		cacheUniqueFindersCache(accountInformationModelImpl);

		accountInformation.resetOriginalValues();

		return accountInformation;
	}

	protected AccountInformation toUnwrappedModel(
		AccountInformation accountInformation) {
		if (accountInformation instanceof AccountInformationImpl) {
			return accountInformation;
		}

		AccountInformationImpl accountInformationImpl = new AccountInformationImpl();

		accountInformationImpl.setNew(accountInformation.isNew());
		accountInformationImpl.setPrimaryKey(accountInformation.getPrimaryKey());

		accountInformationImpl.setAccountInformationId(accountInformation.getAccountInformationId());
		accountInformationImpl.setModifiedUserId(accountInformation.getModifiedUserId());
		accountInformationImpl.setModifiedUserName(accountInformation.getModifiedUserName());
		accountInformationImpl.setModifiedDate(accountInformation.getModifiedDate());
		accountInformationImpl.setAccountEntryId(accountInformation.getAccountEntryId());
		accountInformationImpl.setAccountProjectId(accountInformation.getAccountProjectId());
		accountInformationImpl.setFieldId(accountInformation.getFieldId());
		accountInformationImpl.setData(accountInformation.getData());

		return accountInformationImpl;
	}

	/**
	 * Returns the account information with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account information
	 * @return the account information
	 * @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	 */
	@Override
	public AccountInformation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountInformationException {
		AccountInformation accountInformation = fetchByPrimaryKey(primaryKey);

		if (accountInformation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountInformationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accountInformation;
	}

	/**
	 * Returns the account information with the primary key or throws a {@link NoSuchAccountInformationException} if it could not be found.
	 *
	 * @param accountInformationId the primary key of the account information
	 * @return the account information
	 * @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	 */
	@Override
	public AccountInformation findByPrimaryKey(long accountInformationId)
		throws NoSuchAccountInformationException {
		return findByPrimaryKey((Serializable)accountInformationId);
	}

	/**
	 * Returns the account information with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account information
	 * @return the account information, or <code>null</code> if a account information with the primary key could not be found
	 */
	@Override
	public AccountInformation fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
				AccountInformationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccountInformation accountInformation = (AccountInformation)serializable;

		if (accountInformation == null) {
			Session session = null;

			try {
				session = openSession();

				accountInformation = (AccountInformation)session.get(AccountInformationImpl.class,
						primaryKey);

				if (accountInformation != null) {
					cacheResult(accountInformation);
				}
				else {
					entityCache.putResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
						AccountInformationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
					AccountInformationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accountInformation;
	}

	/**
	 * Returns the account information with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountInformationId the primary key of the account information
	 * @return the account information, or <code>null</code> if a account information with the primary key could not be found
	 */
	@Override
	public AccountInformation fetchByPrimaryKey(long accountInformationId) {
		return fetchByPrimaryKey((Serializable)accountInformationId);
	}

	@Override
	public Map<Serializable, AccountInformation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccountInformation> map = new HashMap<Serializable, AccountInformation>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccountInformation accountInformation = fetchByPrimaryKey(primaryKey);

			if (accountInformation != null) {
				map.put(primaryKey, accountInformation);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
					AccountInformationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccountInformation)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCOUNTINFORMATION_WHERE_PKS_IN);

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

			for (AccountInformation accountInformation : (List<AccountInformation>)q.list()) {
				map.put(accountInformation.getPrimaryKeyObj(),
					accountInformation);

				cacheResult(accountInformation);

				uncachedPrimaryKeys.remove(accountInformation.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccountInformationModelImpl.ENTITY_CACHE_ENABLED,
					AccountInformationImpl.class, primaryKey, nullModel);
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
	 * Returns all the account informations.
	 *
	 * @return the account informations
	 */
	@Override
	public List<AccountInformation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @return the range of account informations
	 */
	@Override
	public List<AccountInformation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account informations
	 */
	@Override
	public List<AccountInformation> findAll(int start, int end,
		OrderByComparator<AccountInformation> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account informations
	 * @param end the upper bound of the range of account informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of account informations
	 */
	@Override
	public List<AccountInformation> findAll(int start, int end,
		OrderByComparator<AccountInformation> orderByComparator,
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

		List<AccountInformation> list = null;

		if (retrieveFromCache) {
			list = (List<AccountInformation>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCOUNTINFORMATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTINFORMATION;

				if (pagination) {
					sql = sql.concat(AccountInformationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccountInformation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountInformation>)QueryUtil.list(q,
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
	 * Removes all the account informations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountInformation accountInformation : findAll()) {
			remove(accountInformation);
		}
	}

	/**
	 * Returns the number of account informations.
	 *
	 * @return the number of account informations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTINFORMATION);

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
		return AccountInformationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account information persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccountInformationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ACCOUNTINFORMATION = "SELECT accountInformation FROM AccountInformation accountInformation";
	private static final String _SQL_SELECT_ACCOUNTINFORMATION_WHERE_PKS_IN = "SELECT accountInformation FROM AccountInformation accountInformation WHERE accountInformationId IN (";
	private static final String _SQL_SELECT_ACCOUNTINFORMATION_WHERE = "SELECT accountInformation FROM AccountInformation accountInformation WHERE ";
	private static final String _SQL_COUNT_ACCOUNTINFORMATION = "SELECT COUNT(accountInformation) FROM AccountInformation accountInformation";
	private static final String _SQL_COUNT_ACCOUNTINFORMATION_WHERE = "SELECT COUNT(accountInformation) FROM AccountInformation accountInformation WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountInformation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountInformation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountInformation exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AccountInformationPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"data"
			});
}
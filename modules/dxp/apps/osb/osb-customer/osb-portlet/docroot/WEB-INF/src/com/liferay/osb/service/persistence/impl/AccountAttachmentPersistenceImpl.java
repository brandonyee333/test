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

import com.liferay.osb.exception.NoSuchAccountAttachmentException;
import com.liferay.osb.model.AccountAttachment;
import com.liferay.osb.model.impl.AccountAttachmentImpl;
import com.liferay.osb.model.impl.AccountAttachmentModelImpl;
import com.liferay.osb.service.persistence.AccountAttachmentPersistence;

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
import com.liferay.portal.kernel.util.StringUtil;

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
 * The persistence implementation for the account attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountAttachmentPersistence
 * @see com.liferay.osb.service.persistence.AccountAttachmentUtil
 * @generated
 */
@ProviderType
public class AccountAttachmentPersistenceImpl extends BasePersistenceImpl<AccountAttachment>
	implements AccountAttachmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountAttachmentUtil} to access the account attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountAttachmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			AccountAttachmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the account attachments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAccountEntryId(long accountEntryId,
		int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAccountEntryId(long accountEntryId,
		int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator,
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

		List<AccountAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<AccountAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountAttachment accountAttachment : list) {
					if ((accountEntryId != accountAttachment.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<AccountAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountAttachment>)QueryUtil.list(q,
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
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountAttachment> orderByComparator)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountAttachment> orderByComparator) {
		List<AccountAttachment> list = findByAccountEntryId(accountEntryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountAttachment> orderByComparator)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountAttachment> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<AccountAttachment> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	@Override
	public AccountAttachment[] findByAccountEntryId_PrevAndNext(
		long accountAttachmentId, long accountEntryId,
		OrderByComparator<AccountAttachment> orderByComparator)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = findByPrimaryKey(accountAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AccountAttachment[] array = new AccountAttachmentImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session,
					accountAttachment, accountEntryId, orderByComparator, true);

			array[1] = accountAttachment;

			array[2] = getByAccountEntryId_PrevAndNext(session,
					accountAttachment, accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountAttachment getByAccountEntryId_PrevAndNext(
		Session session, AccountAttachment accountAttachment,
		long accountEntryId,
		OrderByComparator<AccountAttachment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

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
			query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account attachments where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (AccountAttachment accountAttachment : findByAccountEntryId(
				accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountAttachment);
		}
	}

	/**
	 * Returns the number of account attachments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account attachments
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTATTACHMENT_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountAttachment.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_API = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_API",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API =
		new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_API",
			new String[] { Long.class.getName(), Long.class.getName() },
			AccountAttachmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.ACCOUNTPROJECTID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_API = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_API",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAEI_API(long accountEntryId,
		long accountProjectId) {
		return findByAEI_API(accountEntryId, accountProjectId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end) {
		return findByAEI_API(accountEntryId, accountProjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator) {
		return findByAEI_API(accountEntryId, accountProjectId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator,
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

		List<AccountAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<AccountAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountAttachment accountAttachment : list) {
					if ((accountEntryId != accountAttachment.getAccountEntryId()) ||
							(accountProjectId != accountAttachment.getAccountProjectId())) {
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

			query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
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
					list = (List<AccountAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountAttachment>)QueryUtil.list(q,
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
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment findByAEI_API_First(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountAttachment> orderByComparator)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = fetchByAEI_API_First(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment fetchByAEI_API_First(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountAttachment> orderByComparator) {
		List<AccountAttachment> list = findByAEI_API(accountEntryId,
				accountProjectId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment findByAEI_API_Last(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountAttachment> orderByComparator)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = fetchByAEI_API_Last(accountEntryId,
				accountProjectId, orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment fetchByAEI_API_Last(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountAttachment> orderByComparator) {
		int count = countByAEI_API(accountEntryId, accountProjectId);

		if (count == 0) {
			return null;
		}

		List<AccountAttachment> list = findByAEI_API(accountEntryId,
				accountProjectId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	@Override
	public AccountAttachment[] findByAEI_API_PrevAndNext(
		long accountAttachmentId, long accountEntryId, long accountProjectId,
		OrderByComparator<AccountAttachment> orderByComparator)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = findByPrimaryKey(accountAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AccountAttachment[] array = new AccountAttachmentImpl[3];

			array[0] = getByAEI_API_PrevAndNext(session, accountAttachment,
					accountEntryId, accountProjectId, orderByComparator, true);

			array[1] = accountAttachment;

			array[2] = getByAEI_API_PrevAndNext(session, accountAttachment,
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

	protected AccountAttachment getByAEI_API_PrevAndNext(Session session,
		AccountAttachment accountAttachment, long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountAttachment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

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
			query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(accountProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 */
	@Override
	public void removeByAEI_API(long accountEntryId, long accountProjectId) {
		for (AccountAttachment accountAttachment : findByAEI_API(
				accountEntryId, accountProjectId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(accountAttachment);
		}
	}

	/**
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the number of matching account attachments
	 */
	@Override
	public int countByAEI_API(long accountEntryId, long accountProjectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_API;

		Object[] finderArgs = new Object[] { accountEntryId, accountProjectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTATTACHMENT_WHERE);

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

	private static final String _FINDER_COLUMN_AEI_API_ACCOUNTENTRYID_2 = "accountAttachment.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_ACCOUNTPROJECTID_2 = "accountAttachment.accountProjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_API_T =
		new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_API_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API_T =
		new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_API_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AccountAttachmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.ACCOUNTPROJECTID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.TYPE_COLUMN_BITMASK |
			AccountAttachmentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_API_T = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_API_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @return the matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAEI_API_T(long accountEntryId,
		long accountProjectId, int type) {
		return findByAEI_API_T(accountEntryId, accountProjectId, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAEI_API_T(long accountEntryId,
		long accountProjectId, int type, int start, int end) {
		return findByAEI_API_T(accountEntryId, accountProjectId, type, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAEI_API_T(long accountEntryId,
		long accountProjectId, int type, int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator) {
		return findByAEI_API_T(accountEntryId, accountProjectId, type, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account attachments
	 */
	@Override
	public List<AccountAttachment> findByAEI_API_T(long accountEntryId,
		long accountProjectId, int type, int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API_T;
			finderArgs = new Object[] { accountEntryId, accountProjectId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_API_T;
			finderArgs = new Object[] {
					accountEntryId, accountProjectId, type,
					
					start, end, orderByComparator
				};
		}

		List<AccountAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<AccountAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountAttachment accountAttachment : list) {
					if ((accountEntryId != accountAttachment.getAccountEntryId()) ||
							(accountProjectId != accountAttachment.getAccountProjectId()) ||
							(type != accountAttachment.getType())) {
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

			query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTPROJECTID_2);

			query.append(_FINDER_COLUMN_AEI_API_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				qPos.add(type);

				if (!pagination) {
					list = (List<AccountAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountAttachment>)QueryUtil.list(q,
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
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment findByAEI_API_T_First(long accountEntryId,
		long accountProjectId, int type,
		OrderByComparator<AccountAttachment> orderByComparator)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = fetchByAEI_API_T_First(accountEntryId,
				accountProjectId, type, orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment fetchByAEI_API_T_First(long accountEntryId,
		long accountProjectId, int type,
		OrderByComparator<AccountAttachment> orderByComparator) {
		List<AccountAttachment> list = findByAEI_API_T(accountEntryId,
				accountProjectId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment findByAEI_API_T_Last(long accountEntryId,
		long accountProjectId, int type,
		OrderByComparator<AccountAttachment> orderByComparator)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = fetchByAEI_API_T_Last(accountEntryId,
				accountProjectId, type, orderByComparator);

		if (accountAttachment != null) {
			return accountAttachment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", accountProjectId=");
		msg.append(accountProjectId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountAttachmentException(msg.toString());
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment fetchByAEI_API_T_Last(long accountEntryId,
		long accountProjectId, int type,
		OrderByComparator<AccountAttachment> orderByComparator) {
		int count = countByAEI_API_T(accountEntryId, accountProjectId, type);

		if (count == 0) {
			return null;
		}

		List<AccountAttachment> list = findByAEI_API_T(accountEntryId,
				accountProjectId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	@Override
	public AccountAttachment[] findByAEI_API_T_PrevAndNext(
		long accountAttachmentId, long accountEntryId, long accountProjectId,
		int type, OrderByComparator<AccountAttachment> orderByComparator)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = findByPrimaryKey(accountAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AccountAttachment[] array = new AccountAttachmentImpl[3];

			array[0] = getByAEI_API_T_PrevAndNext(session, accountAttachment,
					accountEntryId, accountProjectId, type, orderByComparator,
					true);

			array[1] = accountAttachment;

			array[2] = getByAEI_API_T_PrevAndNext(session, accountAttachment,
					accountEntryId, accountProjectId, type, orderByComparator,
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

	protected AccountAttachment getByAEI_API_T_PrevAndNext(Session session,
		AccountAttachment accountAttachment, long accountEntryId,
		long accountProjectId, int type,
		OrderByComparator<AccountAttachment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTPROJECTID_2);

		query.append(_FINDER_COLUMN_AEI_API_T_TYPE_2);

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
			query.append(AccountAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(accountProjectId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 */
	@Override
	public void removeByAEI_API_T(long accountEntryId, long accountProjectId,
		int type) {
		for (AccountAttachment accountAttachment : findByAEI_API_T(
				accountEntryId, accountProjectId, type, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(accountAttachment);
		}
	}

	/**
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @return the number of matching account attachments
	 */
	@Override
	public int countByAEI_API_T(long accountEntryId, long accountProjectId,
		int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_API_T;

		Object[] finderArgs = new Object[] {
				accountEntryId, accountProjectId, type
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_T_ACCOUNTPROJECTID_2);

			query.append(_FINDER_COLUMN_AEI_API_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

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

	private static final String _FINDER_COLUMN_AEI_API_T_ACCOUNTENTRYID_2 = "accountAttachment.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_T_ACCOUNTPROJECTID_2 = "accountAttachment.accountProjectId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_T_TYPE_2 = "accountAttachment.type = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_API_FN_T = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountAttachmentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAEI_API_FN_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			AccountAttachmentModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.ACCOUNTPROJECTID_COLUMN_BITMASK |
			AccountAttachmentModelImpl.FILENAME_COLUMN_BITMASK |
			AccountAttachmentModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_API_FN_T = new FinderPath(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_API_FN_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or throws a {@link NoSuchAccountAttachmentException} if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment findByAEI_API_FN_T(long accountEntryId,
		long accountProjectId, String fileName, int type)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = fetchByAEI_API_FN_T(accountEntryId,
				accountProjectId, fileName, type);

		if (accountAttachment == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEntryId=");
			msg.append(accountEntryId);

			msg.append(", accountProjectId=");
			msg.append(accountProjectId);

			msg.append(", fileName=");
			msg.append(fileName);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountAttachmentException(msg.toString());
		}

		return accountAttachment;
	}

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment fetchByAEI_API_FN_T(long accountEntryId,
		long accountProjectId, String fileName, int type) {
		return fetchByAEI_API_FN_T(accountEntryId, accountProjectId, fileName,
			type, true);
	}

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	@Override
	public AccountAttachment fetchByAEI_API_FN_T(long accountEntryId,
		long accountProjectId, String fileName, int type,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				accountEntryId, accountProjectId, fileName, type
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T,
					finderArgs, this);
		}

		if (result instanceof AccountAttachment) {
			AccountAttachment accountAttachment = (AccountAttachment)result;

			if ((accountEntryId != accountAttachment.getAccountEntryId()) ||
					(accountProjectId != accountAttachment.getAccountProjectId()) ||
					!Objects.equals(fileName, accountAttachment.getFileName()) ||
					(type != accountAttachment.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_FN_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_FN_T_ACCOUNTPROJECTID_2);

			boolean bindFileName = false;

			if (fileName == null) {
				query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_1);
			}
			else if (fileName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_3);
			}
			else {
				bindFileName = true;

				query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_2);
			}

			query.append(_FINDER_COLUMN_AEI_API_FN_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				if (bindFileName) {
					qPos.add(fileName);
				}

				qPos.add(type);

				List<AccountAttachment> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AccountAttachmentPersistenceImpl.fetchByAEI_API_FN_T(long, long, String, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AccountAttachment accountAttachment = list.get(0);

					result = accountAttachment;

					cacheResult(accountAttachment);

					if ((accountAttachment.getAccountEntryId() != accountEntryId) ||
							(accountAttachment.getAccountProjectId() != accountProjectId) ||
							(accountAttachment.getFileName() == null) ||
							!accountAttachment.getFileName().equals(fileName) ||
							(accountAttachment.getType() != type)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T,
							finderArgs, accountAttachment);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T,
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
			return (AccountAttachment)result;
		}
	}

	/**
	 * Removes the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the account attachment that was removed
	 */
	@Override
	public AccountAttachment removeByAEI_API_FN_T(long accountEntryId,
		long accountProjectId, String fileName, int type)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = findByAEI_API_FN_T(accountEntryId,
				accountProjectId, fileName, type);

		return remove(accountAttachment);
	}

	/**
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the number of matching account attachments
	 */
	@Override
	public int countByAEI_API_FN_T(long accountEntryId, long accountProjectId,
		String fileName, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_API_FN_T;

		Object[] finderArgs = new Object[] {
				accountEntryId, accountProjectId, fileName, type
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ACCOUNTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_API_FN_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_API_FN_T_ACCOUNTPROJECTID_2);

			boolean bindFileName = false;

			if (fileName == null) {
				query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_1);
			}
			else if (fileName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_3);
			}
			else {
				bindFileName = true;

				query.append(_FINDER_COLUMN_AEI_API_FN_T_FILENAME_2);
			}

			query.append(_FINDER_COLUMN_AEI_API_FN_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(accountProjectId);

				if (bindFileName) {
					qPos.add(fileName);
				}

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

	private static final String _FINDER_COLUMN_AEI_API_FN_T_ACCOUNTENTRYID_2 = "accountAttachment.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_FN_T_ACCOUNTPROJECTID_2 = "accountAttachment.accountProjectId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_FN_T_FILENAME_1 = "accountAttachment.fileName IS NULL AND ";
	private static final String _FINDER_COLUMN_AEI_API_FN_T_FILENAME_2 = "accountAttachment.fileName = ? AND ";
	private static final String _FINDER_COLUMN_AEI_API_FN_T_FILENAME_3 = "(accountAttachment.fileName IS NULL OR accountAttachment.fileName = '') AND ";
	private static final String _FINDER_COLUMN_AEI_API_FN_T_TYPE_2 = "accountAttachment.type = ?";

	public AccountAttachmentPersistenceImpl() {
		setModelClass(AccountAttachment.class);
	}

	/**
	 * Caches the account attachment in the entity cache if it is enabled.
	 *
	 * @param accountAttachment the account attachment
	 */
	@Override
	public void cacheResult(AccountAttachment accountAttachment) {
		entityCache.putResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentImpl.class, accountAttachment.getPrimaryKey(),
			accountAttachment);

		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T,
			new Object[] {
				accountAttachment.getAccountEntryId(),
				accountAttachment.getAccountProjectId(),
				accountAttachment.getFileName(), accountAttachment.getType()
			}, accountAttachment);

		accountAttachment.resetOriginalValues();
	}

	/**
	 * Caches the account attachments in the entity cache if it is enabled.
	 *
	 * @param accountAttachments the account attachments
	 */
	@Override
	public void cacheResult(List<AccountAttachment> accountAttachments) {
		for (AccountAttachment accountAttachment : accountAttachments) {
			if (entityCache.getResult(
						AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountAttachmentImpl.class,
						accountAttachment.getPrimaryKey()) == null) {
				cacheResult(accountAttachment);
			}
			else {
				accountAttachment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account attachments.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountAttachmentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account attachment.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountAttachment accountAttachment) {
		entityCache.removeResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentImpl.class, accountAttachment.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AccountAttachmentModelImpl)accountAttachment,
			true);
	}

	@Override
	public void clearCache(List<AccountAttachment> accountAttachments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountAttachment accountAttachment : accountAttachments) {
			entityCache.removeResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountAttachmentImpl.class, accountAttachment.getPrimaryKey());

			clearUniqueFindersCache((AccountAttachmentModelImpl)accountAttachment,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		AccountAttachmentModelImpl accountAttachmentModelImpl) {
		Object[] args = new Object[] {
				accountAttachmentModelImpl.getAccountEntryId(),
				accountAttachmentModelImpl.getAccountProjectId(),
				accountAttachmentModelImpl.getFileName(),
				accountAttachmentModelImpl.getType()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_AEI_API_FN_T, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T, args,
			accountAttachmentModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AccountAttachmentModelImpl accountAttachmentModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					accountAttachmentModelImpl.getAccountEntryId(),
					accountAttachmentModelImpl.getAccountProjectId(),
					accountAttachmentModelImpl.getFileName(),
					accountAttachmentModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API_FN_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T, args);
		}

		if ((accountAttachmentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_API_FN_T.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					accountAttachmentModelImpl.getOriginalAccountEntryId(),
					accountAttachmentModelImpl.getOriginalAccountProjectId(),
					accountAttachmentModelImpl.getOriginalFileName(),
					accountAttachmentModelImpl.getOriginalType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API_FN_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_API_FN_T, args);
		}
	}

	/**
	 * Creates a new account attachment with the primary key. Does not add the account attachment to the database.
	 *
	 * @param accountAttachmentId the primary key for the new account attachment
	 * @return the new account attachment
	 */
	@Override
	public AccountAttachment create(long accountAttachmentId) {
		AccountAttachment accountAttachment = new AccountAttachmentImpl();

		accountAttachment.setNew(true);
		accountAttachment.setPrimaryKey(accountAttachmentId);

		return accountAttachment;
	}

	/**
	 * Removes the account attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment that was removed
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	@Override
	public AccountAttachment remove(long accountAttachmentId)
		throws NoSuchAccountAttachmentException {
		return remove((Serializable)accountAttachmentId);
	}

	/**
	 * Removes the account attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account attachment
	 * @return the account attachment that was removed
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	@Override
	public AccountAttachment remove(Serializable primaryKey)
		throws NoSuchAccountAttachmentException {
		Session session = null;

		try {
			session = openSession();

			AccountAttachment accountAttachment = (AccountAttachment)session.get(AccountAttachmentImpl.class,
					primaryKey);

			if (accountAttachment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountAttachment);
		}
		catch (NoSuchAccountAttachmentException nsee) {
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
	protected AccountAttachment removeImpl(AccountAttachment accountAttachment) {
		accountAttachment = toUnwrappedModel(accountAttachment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountAttachment)) {
				accountAttachment = (AccountAttachment)session.get(AccountAttachmentImpl.class,
						accountAttachment.getPrimaryKeyObj());
			}

			if (accountAttachment != null) {
				session.delete(accountAttachment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accountAttachment != null) {
			clearCache(accountAttachment);
		}

		return accountAttachment;
	}

	@Override
	public AccountAttachment updateImpl(AccountAttachment accountAttachment) {
		accountAttachment = toUnwrappedModel(accountAttachment);

		boolean isNew = accountAttachment.isNew();

		AccountAttachmentModelImpl accountAttachmentModelImpl = (AccountAttachmentModelImpl)accountAttachment;

		Session session = null;

		try {
			session = openSession();

			if (accountAttachment.isNew()) {
				session.save(accountAttachment);

				accountAttachment.setNew(false);
			}
			else {
				accountAttachment = (AccountAttachment)session.merge(accountAttachment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AccountAttachmentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					accountAttachmentModelImpl.getAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			args = new Object[] {
					accountAttachmentModelImpl.getAccountEntryId(),
					accountAttachmentModelImpl.getAccountProjectId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API,
				args);

			args = new Object[] {
					accountAttachmentModelImpl.getAccountEntryId(),
					accountAttachmentModelImpl.getAccountProjectId(),
					accountAttachmentModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API_T,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accountAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountAttachmentModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] {
						accountAttachmentModelImpl.getAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((accountAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountAttachmentModelImpl.getOriginalAccountEntryId(),
						accountAttachmentModelImpl.getOriginalAccountProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API,
					args);

				args = new Object[] {
						accountAttachmentModelImpl.getAccountEntryId(),
						accountAttachmentModelImpl.getAccountProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API,
					args);
			}

			if ((accountAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountAttachmentModelImpl.getOriginalAccountEntryId(),
						accountAttachmentModelImpl.getOriginalAccountProjectId(),
						accountAttachmentModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API_T,
					args);

				args = new Object[] {
						accountAttachmentModelImpl.getAccountEntryId(),
						accountAttachmentModelImpl.getAccountProjectId(),
						accountAttachmentModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_API_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_API_T,
					args);
			}
		}

		entityCache.putResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountAttachmentImpl.class, accountAttachment.getPrimaryKey(),
			accountAttachment, false);

		clearUniqueFindersCache(accountAttachmentModelImpl, false);
		cacheUniqueFindersCache(accountAttachmentModelImpl);

		accountAttachment.resetOriginalValues();

		return accountAttachment;
	}

	protected AccountAttachment toUnwrappedModel(
		AccountAttachment accountAttachment) {
		if (accountAttachment instanceof AccountAttachmentImpl) {
			return accountAttachment;
		}

		AccountAttachmentImpl accountAttachmentImpl = new AccountAttachmentImpl();

		accountAttachmentImpl.setNew(accountAttachment.isNew());
		accountAttachmentImpl.setPrimaryKey(accountAttachment.getPrimaryKey());

		accountAttachmentImpl.setAccountAttachmentId(accountAttachment.getAccountAttachmentId());
		accountAttachmentImpl.setUserId(accountAttachment.getUserId());
		accountAttachmentImpl.setUserName(accountAttachment.getUserName());
		accountAttachmentImpl.setCreateDate(accountAttachment.getCreateDate());
		accountAttachmentImpl.setAccountEntryId(accountAttachment.getAccountEntryId());
		accountAttachmentImpl.setAccountProjectId(accountAttachment.getAccountProjectId());
		accountAttachmentImpl.setFileName(accountAttachment.getFileName());
		accountAttachmentImpl.setFileSize(accountAttachment.getFileSize());
		accountAttachmentImpl.setType(accountAttachment.getType());

		return accountAttachmentImpl;
	}

	/**
	 * Returns the account attachment with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account attachment
	 * @return the account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	@Override
	public AccountAttachment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountAttachmentException {
		AccountAttachment accountAttachment = fetchByPrimaryKey(primaryKey);

		if (accountAttachment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accountAttachment;
	}

	/**
	 * Returns the account attachment with the primary key or throws a {@link NoSuchAccountAttachmentException} if it could not be found.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	@Override
	public AccountAttachment findByPrimaryKey(long accountAttachmentId)
		throws NoSuchAccountAttachmentException {
		return findByPrimaryKey((Serializable)accountAttachmentId);
	}

	/**
	 * Returns the account attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account attachment
	 * @return the account attachment, or <code>null</code> if a account attachment with the primary key could not be found
	 */
	@Override
	public AccountAttachment fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountAttachmentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccountAttachment accountAttachment = (AccountAttachment)serializable;

		if (accountAttachment == null) {
			Session session = null;

			try {
				session = openSession();

				accountAttachment = (AccountAttachment)session.get(AccountAttachmentImpl.class,
						primaryKey);

				if (accountAttachment != null) {
					cacheResult(accountAttachment);
				}
				else {
					entityCache.putResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountAttachmentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
					AccountAttachmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accountAttachment;
	}

	/**
	 * Returns the account attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment, or <code>null</code> if a account attachment with the primary key could not be found
	 */
	@Override
	public AccountAttachment fetchByPrimaryKey(long accountAttachmentId) {
		return fetchByPrimaryKey((Serializable)accountAttachmentId);
	}

	@Override
	public Map<Serializable, AccountAttachment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccountAttachment> map = new HashMap<Serializable, AccountAttachment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccountAttachment accountAttachment = fetchByPrimaryKey(primaryKey);

			if (accountAttachment != null) {
				map.put(primaryKey, accountAttachment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
					AccountAttachmentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccountAttachment)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCOUNTATTACHMENT_WHERE_PKS_IN);

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

			for (AccountAttachment accountAttachment : (List<AccountAttachment>)q.list()) {
				map.put(accountAttachment.getPrimaryKeyObj(), accountAttachment);

				cacheResult(accountAttachment);

				uncachedPrimaryKeys.remove(accountAttachment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccountAttachmentModelImpl.ENTITY_CACHE_ENABLED,
					AccountAttachmentImpl.class, primaryKey, nullModel);
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
	 * Returns all the account attachments.
	 *
	 * @return the account attachments
	 */
	@Override
	public List<AccountAttachment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of account attachments
	 */
	@Override
	public List<AccountAttachment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account attachments
	 */
	@Override
	public List<AccountAttachment> findAll(int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of account attachments
	 */
	@Override
	public List<AccountAttachment> findAll(int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator,
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

		List<AccountAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<AccountAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCOUNTATTACHMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTATTACHMENT;

				if (pagination) {
					sql = sql.concat(AccountAttachmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccountAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountAttachment>)QueryUtil.list(q,
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
	 * Removes all the account attachments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountAttachment accountAttachment : findAll()) {
			remove(accountAttachment);
		}
	}

	/**
	 * Returns the number of account attachments.
	 *
	 * @return the number of account attachments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTATTACHMENT);

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
		return AccountAttachmentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account attachment persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccountAttachmentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ACCOUNTATTACHMENT = "SELECT accountAttachment FROM AccountAttachment accountAttachment";
	private static final String _SQL_SELECT_ACCOUNTATTACHMENT_WHERE_PKS_IN = "SELECT accountAttachment FROM AccountAttachment accountAttachment WHERE accountAttachmentId IN (";
	private static final String _SQL_SELECT_ACCOUNTATTACHMENT_WHERE = "SELECT accountAttachment FROM AccountAttachment accountAttachment WHERE ";
	private static final String _SQL_COUNT_ACCOUNTATTACHMENT = "SELECT COUNT(accountAttachment) FROM AccountAttachment accountAttachment";
	private static final String _SQL_COUNT_ACCOUNTATTACHMENT_WHERE = "SELECT COUNT(accountAttachment) FROM AccountAttachment accountAttachment WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountAttachment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountAttachment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountAttachment exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AccountAttachmentPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
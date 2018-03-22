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

import com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.model.impl.AccountEnvironmentAttachmentImpl;
import com.liferay.osb.model.impl.AccountEnvironmentAttachmentModelImpl;
import com.liferay.osb.service.persistence.AccountEnvironmentAttachmentPersistence;

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
 * The persistence implementation for the account environment attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentPersistence
 * @see com.liferay.osb.service.persistence.AccountEnvironmentAttachmentUtil
 * @generated
 */
@ProviderType
public class AccountEnvironmentAttachmentPersistenceImpl
	extends BasePersistenceImpl<AccountEnvironmentAttachment>
	implements AccountEnvironmentAttachmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountEnvironmentAttachmentUtil} to access the account environment attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountEnvironmentAttachmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID =
		new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEnvironmentId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID =
		new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAccountEnvironmentId",
			new String[] { Long.class.getName() },
			AccountEnvironmentAttachmentModelImpl.ACCOUNTENVIRONMENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENVIRONMENTID = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAccountEnvironmentId", new String[] { Long.class.getName() });

	/**
	 * Returns all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @return the matching account environment attachments
	 */
	@Override
	public List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId) {
		return findByAccountEnvironmentId(accountEnvironmentId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @return the range of matching account environment attachments
	 */
	@Override
	public List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end) {
		return findByAccountEnvironmentId(accountEnvironmentId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account environment attachments
	 */
	@Override
	public List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator) {
		return findByAccountEnvironmentId(accountEnvironmentId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account environment attachments
	 */
	@Override
	public List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID;
			finderArgs = new Object[] { accountEnvironmentId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID;
			finderArgs = new Object[] {
					accountEnvironmentId,
					
					start, end, orderByComparator
				};
		}

		List<AccountEnvironmentAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEnvironmentAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountEnvironmentAttachment accountEnvironmentAttachment : list) {
					if ((accountEnvironmentId != accountEnvironmentAttachment.getAccountEnvironmentId())) {
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

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENVIRONMENTID_ACCOUNTENVIRONMENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountEnvironmentAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEnvironmentId);

				if (!pagination) {
					list = (List<AccountEnvironmentAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountEnvironmentAttachment>)QueryUtil.list(q,
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
	 * Returns the first account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	@Override
	public AccountEnvironmentAttachment findByAccountEnvironmentId_First(
		long accountEnvironmentId,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator)
		throws NoSuchAccountEnvironmentAttachmentException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = fetchByAccountEnvironmentId_First(accountEnvironmentId,
				orderByComparator);

		if (accountEnvironmentAttachment != null) {
			return accountEnvironmentAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEnvironmentId=");
		msg.append(accountEnvironmentId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentAttachmentException(msg.toString());
	}

	/**
	 * Returns the first account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	@Override
	public AccountEnvironmentAttachment fetchByAccountEnvironmentId_First(
		long accountEnvironmentId,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator) {
		List<AccountEnvironmentAttachment> list = findByAccountEnvironmentId(accountEnvironmentId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	@Override
	public AccountEnvironmentAttachment findByAccountEnvironmentId_Last(
		long accountEnvironmentId,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator)
		throws NoSuchAccountEnvironmentAttachmentException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = fetchByAccountEnvironmentId_Last(accountEnvironmentId,
				orderByComparator);

		if (accountEnvironmentAttachment != null) {
			return accountEnvironmentAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEnvironmentId=");
		msg.append(accountEnvironmentId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountEnvironmentAttachmentException(msg.toString());
	}

	/**
	 * Returns the last account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	@Override
	public AccountEnvironmentAttachment fetchByAccountEnvironmentId_Last(
		long accountEnvironmentId,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator) {
		int count = countByAccountEnvironmentId(accountEnvironmentId);

		if (count == 0) {
			return null;
		}

		List<AccountEnvironmentAttachment> list = findByAccountEnvironmentId(accountEnvironmentId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account environment attachments before and after the current account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the current account environment attachment
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 */
	@Override
	public AccountEnvironmentAttachment[] findByAccountEnvironmentId_PrevAndNext(
		long accountEnvironmentAttachmentId, long accountEnvironmentId,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator)
		throws NoSuchAccountEnvironmentAttachmentException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = findByPrimaryKey(accountEnvironmentAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AccountEnvironmentAttachment[] array = new AccountEnvironmentAttachmentImpl[3];

			array[0] = getByAccountEnvironmentId_PrevAndNext(session,
					accountEnvironmentAttachment, accountEnvironmentId,
					orderByComparator, true);

			array[1] = accountEnvironmentAttachment;

			array[2] = getByAccountEnvironmentId_PrevAndNext(session,
					accountEnvironmentAttachment, accountEnvironmentId,
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

	protected AccountEnvironmentAttachment getByAccountEnvironmentId_PrevAndNext(
		Session session,
		AccountEnvironmentAttachment accountEnvironmentAttachment,
		long accountEnvironmentId,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator,
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

		query.append(_SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_ACCOUNTENVIRONMENTID_ACCOUNTENVIRONMENTID_2);

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
			query.append(AccountEnvironmentAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEnvironmentId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountEnvironmentAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountEnvironmentAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account environment attachments where accountEnvironmentId = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 */
	@Override
	public void removeByAccountEnvironmentId(long accountEnvironmentId) {
		for (AccountEnvironmentAttachment accountEnvironmentAttachment : findByAccountEnvironmentId(
				accountEnvironmentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountEnvironmentAttachment);
		}
	}

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @return the number of matching account environment attachments
	 */
	@Override
	public int countByAccountEnvironmentId(long accountEnvironmentId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENVIRONMENTID;

		Object[] finderArgs = new Object[] { accountEnvironmentId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENVIRONMENTID_ACCOUNTENVIRONMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEnvironmentId);

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

	private static final String _FINDER_COLUMN_ACCOUNTENVIRONMENTID_ACCOUNTENVIRONMENTID_2 =
		"accountEnvironmentAttachment.accountEnvironmentId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_FN = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAEI_FN",
			new String[] { Long.class.getName(), String.class.getName() },
			AccountEnvironmentAttachmentModelImpl.ACCOUNTENVIRONMENTID_COLUMN_BITMASK |
			AccountEnvironmentAttachmentModelImpl.FILENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_FN = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAEI_FN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or throws a {@link NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	@Override
	public AccountEnvironmentAttachment findByAEI_FN(
		long accountEnvironmentId, String fileName)
		throws NoSuchAccountEnvironmentAttachmentException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = fetchByAEI_FN(accountEnvironmentId,
				fileName);

		if (accountEnvironmentAttachment == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEnvironmentId=");
			msg.append(accountEnvironmentId);

			msg.append(", fileName=");
			msg.append(fileName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountEnvironmentAttachmentException(msg.toString());
		}

		return accountEnvironmentAttachment;
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	@Override
	public AccountEnvironmentAttachment fetchByAEI_FN(
		long accountEnvironmentId, String fileName) {
		return fetchByAEI_FN(accountEnvironmentId, fileName, true);
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	@Override
	public AccountEnvironmentAttachment fetchByAEI_FN(
		long accountEnvironmentId, String fileName, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { accountEnvironmentId, fileName };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_AEI_FN,
					finderArgs, this);
		}

		if (result instanceof AccountEnvironmentAttachment) {
			AccountEnvironmentAttachment accountEnvironmentAttachment = (AccountEnvironmentAttachment)result;

			if ((accountEnvironmentId != accountEnvironmentAttachment.getAccountEnvironmentId()) ||
					!Objects.equals(fileName,
						accountEnvironmentAttachment.getFileName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_FN_ACCOUNTENVIRONMENTID_2);

			boolean bindFileName = false;

			if (fileName == null) {
				query.append(_FINDER_COLUMN_AEI_FN_FILENAME_1);
			}
			else if (fileName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_AEI_FN_FILENAME_3);
			}
			else {
				bindFileName = true;

				query.append(_FINDER_COLUMN_AEI_FN_FILENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEnvironmentId);

				if (bindFileName) {
					qPos.add(fileName);
				}

				List<AccountEnvironmentAttachment> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_FN,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AccountEnvironmentAttachmentPersistenceImpl.fetchByAEI_FN(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AccountEnvironmentAttachment accountEnvironmentAttachment = list.get(0);

					result = accountEnvironmentAttachment;

					cacheResult(accountEnvironmentAttachment);

					if ((accountEnvironmentAttachment.getAccountEnvironmentId() != accountEnvironmentId) ||
							(accountEnvironmentAttachment.getFileName() == null) ||
							!accountEnvironmentAttachment.getFileName()
															 .equals(fileName)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_FN,
							finderArgs, accountEnvironmentAttachment);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_FN, finderArgs);

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
			return (AccountEnvironmentAttachment)result;
		}
	}

	/**
	 * Removes the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the account environment attachment that was removed
	 */
	@Override
	public AccountEnvironmentAttachment removeByAEI_FN(
		long accountEnvironmentId, String fileName)
		throws NoSuchAccountEnvironmentAttachmentException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = findByAEI_FN(accountEnvironmentId,
				fileName);

		return remove(accountEnvironmentAttachment);
	}

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63; and fileName = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the number of matching account environment attachments
	 */
	@Override
	public int countByAEI_FN(long accountEnvironmentId, String fileName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_FN;

		Object[] finderArgs = new Object[] { accountEnvironmentId, fileName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_FN_ACCOUNTENVIRONMENTID_2);

			boolean bindFileName = false;

			if (fileName == null) {
				query.append(_FINDER_COLUMN_AEI_FN_FILENAME_1);
			}
			else if (fileName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_AEI_FN_FILENAME_3);
			}
			else {
				bindFileName = true;

				query.append(_FINDER_COLUMN_AEI_FN_FILENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEnvironmentId);

				if (bindFileName) {
					qPos.add(fileName);
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

	private static final String _FINDER_COLUMN_AEI_FN_ACCOUNTENVIRONMENTID_2 = "accountEnvironmentAttachment.accountEnvironmentId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_FN_FILENAME_1 = "accountEnvironmentAttachment.fileName IS NULL";
	private static final String _FINDER_COLUMN_AEI_FN_FILENAME_2 = "accountEnvironmentAttachment.fileName = ?";
	private static final String _FINDER_COLUMN_AEI_FN_FILENAME_3 = "(accountEnvironmentAttachment.fileName IS NULL OR accountEnvironmentAttachment.fileName = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_T = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AccountEnvironmentAttachmentModelImpl.ACCOUNTENVIRONMENTID_COLUMN_BITMASK |
			AccountEnvironmentAttachmentModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_T = new FinderPath(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAEI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or throws a {@link NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	@Override
	public AccountEnvironmentAttachment findByAEI_T(long accountEnvironmentId,
		int type) throws NoSuchAccountEnvironmentAttachmentException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = fetchByAEI_T(accountEnvironmentId,
				type);

		if (accountEnvironmentAttachment == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEnvironmentId=");
			msg.append(accountEnvironmentId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountEnvironmentAttachmentException(msg.toString());
		}

		return accountEnvironmentAttachment;
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	@Override
	public AccountEnvironmentAttachment fetchByAEI_T(
		long accountEnvironmentId, int type) {
		return fetchByAEI_T(accountEnvironmentId, type, true);
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	@Override
	public AccountEnvironmentAttachment fetchByAEI_T(
		long accountEnvironmentId, int type, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { accountEnvironmentId, type };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_AEI_T,
					finderArgs, this);
		}

		if (result instanceof AccountEnvironmentAttachment) {
			AccountEnvironmentAttachment accountEnvironmentAttachment = (AccountEnvironmentAttachment)result;

			if ((accountEnvironmentId != accountEnvironmentAttachment.getAccountEnvironmentId()) ||
					(type != accountEnvironmentAttachment.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_T_ACCOUNTENVIRONMENTID_2);

			query.append(_FINDER_COLUMN_AEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEnvironmentId);

				qPos.add(type);

				List<AccountEnvironmentAttachment> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_T,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AccountEnvironmentAttachmentPersistenceImpl.fetchByAEI_T(long, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AccountEnvironmentAttachment accountEnvironmentAttachment = list.get(0);

					result = accountEnvironmentAttachment;

					cacheResult(accountEnvironmentAttachment);

					if ((accountEnvironmentAttachment.getAccountEnvironmentId() != accountEnvironmentId) ||
							(accountEnvironmentAttachment.getType() != type)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_T,
							finderArgs, accountEnvironmentAttachment);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_T, finderArgs);

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
			return (AccountEnvironmentAttachment)result;
		}
	}

	/**
	 * Removes the account environment attachment where accountEnvironmentId = &#63; and type = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the account environment attachment that was removed
	 */
	@Override
	public AccountEnvironmentAttachment removeByAEI_T(
		long accountEnvironmentId, int type)
		throws NoSuchAccountEnvironmentAttachmentException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = findByAEI_T(accountEnvironmentId,
				type);

		return remove(accountEnvironmentAttachment);
	}

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63; and type = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the number of matching account environment attachments
	 */
	@Override
	public int countByAEI_T(long accountEnvironmentId, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_T;

		Object[] finderArgs = new Object[] { accountEnvironmentId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_AEI_T_ACCOUNTENVIRONMENTID_2);

			query.append(_FINDER_COLUMN_AEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEnvironmentId);

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

	private static final String _FINDER_COLUMN_AEI_T_ACCOUNTENVIRONMENTID_2 = "accountEnvironmentAttachment.accountEnvironmentId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_T_TYPE_2 = "accountEnvironmentAttachment.type = ?";

	public AccountEnvironmentAttachmentPersistenceImpl() {
		setModelClass(AccountEnvironmentAttachment.class);
	}

	/**
	 * Caches the account environment attachment in the entity cache if it is enabled.
	 *
	 * @param accountEnvironmentAttachment the account environment attachment
	 */
	@Override
	public void cacheResult(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		entityCache.putResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			accountEnvironmentAttachment.getPrimaryKey(),
			accountEnvironmentAttachment);

		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_FN,
			new Object[] {
				accountEnvironmentAttachment.getAccountEnvironmentId(),
				accountEnvironmentAttachment.getFileName()
			}, accountEnvironmentAttachment);

		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_T,
			new Object[] {
				accountEnvironmentAttachment.getAccountEnvironmentId(),
				accountEnvironmentAttachment.getType()
			}, accountEnvironmentAttachment);

		accountEnvironmentAttachment.resetOriginalValues();
	}

	/**
	 * Caches the account environment attachments in the entity cache if it is enabled.
	 *
	 * @param accountEnvironmentAttachments the account environment attachments
	 */
	@Override
	public void cacheResult(
		List<AccountEnvironmentAttachment> accountEnvironmentAttachments) {
		for (AccountEnvironmentAttachment accountEnvironmentAttachment : accountEnvironmentAttachments) {
			if (entityCache.getResult(
						AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountEnvironmentAttachmentImpl.class,
						accountEnvironmentAttachment.getPrimaryKey()) == null) {
				cacheResult(accountEnvironmentAttachment);
			}
			else {
				accountEnvironmentAttachment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account environment attachments.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountEnvironmentAttachmentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account environment attachment.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		entityCache.removeResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			accountEnvironmentAttachment.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AccountEnvironmentAttachmentModelImpl)accountEnvironmentAttachment,
			true);
	}

	@Override
	public void clearCache(
		List<AccountEnvironmentAttachment> accountEnvironmentAttachments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountEnvironmentAttachment accountEnvironmentAttachment : accountEnvironmentAttachments) {
			entityCache.removeResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountEnvironmentAttachmentImpl.class,
				accountEnvironmentAttachment.getPrimaryKey());

			clearUniqueFindersCache((AccountEnvironmentAttachmentModelImpl)accountEnvironmentAttachment,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		AccountEnvironmentAttachmentModelImpl accountEnvironmentAttachmentModelImpl) {
		Object[] args = new Object[] {
				accountEnvironmentAttachmentModelImpl.getAccountEnvironmentId(),
				accountEnvironmentAttachmentModelImpl.getFileName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_AEI_FN, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_FN, args,
			accountEnvironmentAttachmentModelImpl, false);

		args = new Object[] {
				accountEnvironmentAttachmentModelImpl.getAccountEnvironmentId(),
				accountEnvironmentAttachmentModelImpl.getType()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_AEI_T, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_T, args,
			accountEnvironmentAttachmentModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AccountEnvironmentAttachmentModelImpl accountEnvironmentAttachmentModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					accountEnvironmentAttachmentModelImpl.getAccountEnvironmentId(),
					accountEnvironmentAttachmentModelImpl.getFileName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_FN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_FN, args);
		}

		if ((accountEnvironmentAttachmentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_FN.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					accountEnvironmentAttachmentModelImpl.getOriginalAccountEnvironmentId(),
					accountEnvironmentAttachmentModelImpl.getOriginalFileName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_FN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_FN, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					accountEnvironmentAttachmentModelImpl.getAccountEnvironmentId(),
					accountEnvironmentAttachmentModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_T, args);
		}

		if ((accountEnvironmentAttachmentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_T.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					accountEnvironmentAttachmentModelImpl.getOriginalAccountEnvironmentId(),
					accountEnvironmentAttachmentModelImpl.getOriginalType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_T, args);
		}
	}

	/**
	 * Creates a new account environment attachment with the primary key. Does not add the account environment attachment to the database.
	 *
	 * @param accountEnvironmentAttachmentId the primary key for the new account environment attachment
	 * @return the new account environment attachment
	 */
	@Override
	public AccountEnvironmentAttachment create(
		long accountEnvironmentAttachmentId) {
		AccountEnvironmentAttachment accountEnvironmentAttachment = new AccountEnvironmentAttachmentImpl();

		accountEnvironmentAttachment.setNew(true);
		accountEnvironmentAttachment.setPrimaryKey(accountEnvironmentAttachmentId);

		return accountEnvironmentAttachment;
	}

	/**
	 * Removes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment that was removed
	 * @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 */
	@Override
	public AccountEnvironmentAttachment remove(
		long accountEnvironmentAttachmentId)
		throws NoSuchAccountEnvironmentAttachmentException {
		return remove((Serializable)accountEnvironmentAttachmentId);
	}

	/**
	 * Removes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account environment attachment
	 * @return the account environment attachment that was removed
	 * @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 */
	@Override
	public AccountEnvironmentAttachment remove(Serializable primaryKey)
		throws NoSuchAccountEnvironmentAttachmentException {
		Session session = null;

		try {
			session = openSession();

			AccountEnvironmentAttachment accountEnvironmentAttachment = (AccountEnvironmentAttachment)session.get(AccountEnvironmentAttachmentImpl.class,
					primaryKey);

			if (accountEnvironmentAttachment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountEnvironmentAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountEnvironmentAttachment);
		}
		catch (NoSuchAccountEnvironmentAttachmentException nsee) {
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
	protected AccountEnvironmentAttachment removeImpl(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		accountEnvironmentAttachment = toUnwrappedModel(accountEnvironmentAttachment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountEnvironmentAttachment)) {
				accountEnvironmentAttachment = (AccountEnvironmentAttachment)session.get(AccountEnvironmentAttachmentImpl.class,
						accountEnvironmentAttachment.getPrimaryKeyObj());
			}

			if (accountEnvironmentAttachment != null) {
				session.delete(accountEnvironmentAttachment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accountEnvironmentAttachment != null) {
			clearCache(accountEnvironmentAttachment);
		}

		return accountEnvironmentAttachment;
	}

	@Override
	public AccountEnvironmentAttachment updateImpl(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		accountEnvironmentAttachment = toUnwrappedModel(accountEnvironmentAttachment);

		boolean isNew = accountEnvironmentAttachment.isNew();

		AccountEnvironmentAttachmentModelImpl accountEnvironmentAttachmentModelImpl =
			(AccountEnvironmentAttachmentModelImpl)accountEnvironmentAttachment;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (accountEnvironmentAttachment.getCreateDate() == null)) {
			if (serviceContext == null) {
				accountEnvironmentAttachment.setCreateDate(now);
			}
			else {
				accountEnvironmentAttachment.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!accountEnvironmentAttachmentModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				accountEnvironmentAttachment.setModifiedDate(now);
			}
			else {
				accountEnvironmentAttachment.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (accountEnvironmentAttachment.isNew()) {
				session.save(accountEnvironmentAttachment);

				accountEnvironmentAttachment.setNew(false);
			}
			else {
				accountEnvironmentAttachment = (AccountEnvironmentAttachment)session.merge(accountEnvironmentAttachment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AccountEnvironmentAttachmentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					accountEnvironmentAttachmentModelImpl.getAccountEnvironmentId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENVIRONMENTID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accountEnvironmentAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountEnvironmentAttachmentModelImpl.getOriginalAccountEnvironmentId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENVIRONMENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID,
					args);

				args = new Object[] {
						accountEnvironmentAttachmentModelImpl.getAccountEnvironmentId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENVIRONMENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENVIRONMENTID,
					args);
			}
		}

		entityCache.putResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AccountEnvironmentAttachmentImpl.class,
			accountEnvironmentAttachment.getPrimaryKey(),
			accountEnvironmentAttachment, false);

		clearUniqueFindersCache(accountEnvironmentAttachmentModelImpl, false);
		cacheUniqueFindersCache(accountEnvironmentAttachmentModelImpl);

		accountEnvironmentAttachment.resetOriginalValues();

		return accountEnvironmentAttachment;
	}

	protected AccountEnvironmentAttachment toUnwrappedModel(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		if (accountEnvironmentAttachment instanceof AccountEnvironmentAttachmentImpl) {
			return accountEnvironmentAttachment;
		}

		AccountEnvironmentAttachmentImpl accountEnvironmentAttachmentImpl = new AccountEnvironmentAttachmentImpl();

		accountEnvironmentAttachmentImpl.setNew(accountEnvironmentAttachment.isNew());
		accountEnvironmentAttachmentImpl.setPrimaryKey(accountEnvironmentAttachment.getPrimaryKey());

		accountEnvironmentAttachmentImpl.setAccountEnvironmentAttachmentId(accountEnvironmentAttachment.getAccountEnvironmentAttachmentId());
		accountEnvironmentAttachmentImpl.setUserId(accountEnvironmentAttachment.getUserId());
		accountEnvironmentAttachmentImpl.setUserName(accountEnvironmentAttachment.getUserName());
		accountEnvironmentAttachmentImpl.setCreateDate(accountEnvironmentAttachment.getCreateDate());
		accountEnvironmentAttachmentImpl.setModifiedDate(accountEnvironmentAttachment.getModifiedDate());
		accountEnvironmentAttachmentImpl.setAccountEnvironmentId(accountEnvironmentAttachment.getAccountEnvironmentId());
		accountEnvironmentAttachmentImpl.setFileName(accountEnvironmentAttachment.getFileName());
		accountEnvironmentAttachmentImpl.setFileSize(accountEnvironmentAttachment.getFileSize());
		accountEnvironmentAttachmentImpl.setType(accountEnvironmentAttachment.getType());

		return accountEnvironmentAttachmentImpl;
	}

	/**
	 * Returns the account environment attachment with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account environment attachment
	 * @return the account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 */
	@Override
	public AccountEnvironmentAttachment findByPrimaryKey(
		Serializable primaryKey)
		throws NoSuchAccountEnvironmentAttachmentException {
		AccountEnvironmentAttachment accountEnvironmentAttachment = fetchByPrimaryKey(primaryKey);

		if (accountEnvironmentAttachment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountEnvironmentAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accountEnvironmentAttachment;
	}

	/**
	 * Returns the account environment attachment with the primary key or throws a {@link NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 */
	@Override
	public AccountEnvironmentAttachment findByPrimaryKey(
		long accountEnvironmentAttachmentId)
		throws NoSuchAccountEnvironmentAttachmentException {
		return findByPrimaryKey((Serializable)accountEnvironmentAttachmentId);
	}

	/**
	 * Returns the account environment attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account environment attachment
	 * @return the account environment attachment, or <code>null</code> if a account environment attachment with the primary key could not be found
	 */
	@Override
	public AccountEnvironmentAttachment fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AccountEnvironmentAttachmentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccountEnvironmentAttachment accountEnvironmentAttachment = (AccountEnvironmentAttachment)serializable;

		if (accountEnvironmentAttachment == null) {
			Session session = null;

			try {
				session = openSession();

				accountEnvironmentAttachment = (AccountEnvironmentAttachment)session.get(AccountEnvironmentAttachmentImpl.class,
						primaryKey);

				if (accountEnvironmentAttachment != null) {
					cacheResult(accountEnvironmentAttachment);
				}
				else {
					entityCache.putResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AccountEnvironmentAttachmentImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
					AccountEnvironmentAttachmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accountEnvironmentAttachment;
	}

	/**
	 * Returns the account environment attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment, or <code>null</code> if a account environment attachment with the primary key could not be found
	 */
	@Override
	public AccountEnvironmentAttachment fetchByPrimaryKey(
		long accountEnvironmentAttachmentId) {
		return fetchByPrimaryKey((Serializable)accountEnvironmentAttachmentId);
	}

	@Override
	public Map<Serializable, AccountEnvironmentAttachment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccountEnvironmentAttachment> map = new HashMap<Serializable, AccountEnvironmentAttachment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccountEnvironmentAttachment accountEnvironmentAttachment = fetchByPrimaryKey(primaryKey);

			if (accountEnvironmentAttachment != null) {
				map.put(primaryKey, accountEnvironmentAttachment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
					AccountEnvironmentAttachmentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(AccountEnvironmentAttachment)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE_PKS_IN);

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

			for (AccountEnvironmentAttachment accountEnvironmentAttachment : (List<AccountEnvironmentAttachment>)q.list()) {
				map.put(accountEnvironmentAttachment.getPrimaryKeyObj(),
					accountEnvironmentAttachment);

				cacheResult(accountEnvironmentAttachment);

				uncachedPrimaryKeys.remove(accountEnvironmentAttachment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccountEnvironmentAttachmentModelImpl.ENTITY_CACHE_ENABLED,
					AccountEnvironmentAttachmentImpl.class, primaryKey,
					nullModel);
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
	 * Returns all the account environment attachments.
	 *
	 * @return the account environment attachments
	 */
	@Override
	public List<AccountEnvironmentAttachment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @return the range of account environment attachments
	 */
	@Override
	public List<AccountEnvironmentAttachment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account environment attachments
	 */
	@Override
	public List<AccountEnvironmentAttachment> findAll(int start, int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of account environment attachments
	 */
	@Override
	public List<AccountEnvironmentAttachment> findAll(int start, int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator,
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

		List<AccountEnvironmentAttachment> list = null;

		if (retrieveFromCache) {
			list = (List<AccountEnvironmentAttachment>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT;

				if (pagination) {
					sql = sql.concat(AccountEnvironmentAttachmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccountEnvironmentAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountEnvironmentAttachment>)QueryUtil.list(q,
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
	 * Removes all the account environment attachments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountEnvironmentAttachment accountEnvironmentAttachment : findAll()) {
			remove(accountEnvironmentAttachment);
		}
	}

	/**
	 * Returns the number of account environment attachments.
	 *
	 * @return the number of account environment attachments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT);

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
		return AccountEnvironmentAttachmentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account environment attachment persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccountEnvironmentAttachmentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT = "SELECT accountEnvironmentAttachment FROM AccountEnvironmentAttachment accountEnvironmentAttachment";
	private static final String _SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE_PKS_IN =
		"SELECT accountEnvironmentAttachment FROM AccountEnvironmentAttachment accountEnvironmentAttachment WHERE accountEnvironmentAttachmentId IN (";
	private static final String _SQL_SELECT_ACCOUNTENVIRONMENTATTACHMENT_WHERE = "SELECT accountEnvironmentAttachment FROM AccountEnvironmentAttachment accountEnvironmentAttachment WHERE ";
	private static final String _SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT = "SELECT COUNT(accountEnvironmentAttachment) FROM AccountEnvironmentAttachment accountEnvironmentAttachment";
	private static final String _SQL_COUNT_ACCOUNTENVIRONMENTATTACHMENT_WHERE = "SELECT COUNT(accountEnvironmentAttachment) FROM AccountEnvironmentAttachment accountEnvironmentAttachment WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountEnvironmentAttachment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountEnvironmentAttachment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountEnvironmentAttachment exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AccountEnvironmentAttachmentPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
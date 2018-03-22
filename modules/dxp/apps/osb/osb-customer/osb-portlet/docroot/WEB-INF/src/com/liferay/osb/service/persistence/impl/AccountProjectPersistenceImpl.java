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

import com.liferay.osb.exception.NoSuchAccountProjectException;
import com.liferay.osb.model.AccountProject;
import com.liferay.osb.model.impl.AccountProjectImpl;
import com.liferay.osb.model.impl.AccountProjectModelImpl;
import com.liferay.osb.service.persistence.AccountProjectPersistence;

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
 * The persistence implementation for the account project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountProjectPersistence
 * @see com.liferay.osb.service.persistence.AccountProjectUtil
 * @generated
 */
@ProviderType
public class AccountProjectPersistenceImpl extends BasePersistenceImpl<AccountProject>
	implements AccountProjectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountProjectUtil} to access the account project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountProjectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
			AccountProjectModelImpl.FINDER_CACHE_ENABLED,
			AccountProjectImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
			AccountProjectModelImpl.FINDER_CACHE_ENABLED,
			AccountProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
			AccountProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
			AccountProjectModelImpl.FINDER_CACHE_ENABLED,
			AccountProjectImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
			AccountProjectModelImpl.FINDER_CACHE_ENABLED,
			AccountProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			AccountProjectModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
			AccountProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the account projects where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account projects
	 */
	@Override
	public List<AccountProject> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account projects where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account projects
	 * @param end the upper bound of the range of account projects (not inclusive)
	 * @return the range of matching account projects
	 */
	@Override
	public List<AccountProject> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account projects where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account projects
	 * @param end the upper bound of the range of account projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account projects
	 */
	@Override
	public List<AccountProject> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountProject> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account projects where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account projects
	 * @param end the upper bound of the range of account projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching account projects
	 */
	@Override
	public List<AccountProject> findByAccountEntryId(long accountEntryId,
		int start, int end,
		OrderByComparator<AccountProject> orderByComparator,
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

		List<AccountProject> list = null;

		if (retrieveFromCache) {
			list = (List<AccountProject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountProject accountProject : list) {
					if ((accountEntryId != accountProject.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_ACCOUNTPROJECT_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccountProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<AccountProject>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountProject>)QueryUtil.list(q,
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
	 * Returns the first account project in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account project
	 * @throws NoSuchAccountProjectException if a matching account project could not be found
	 */
	@Override
	public AccountProject findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountProject> orderByComparator)
		throws NoSuchAccountProjectException {
		AccountProject accountProject = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (accountProject != null) {
			return accountProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountProjectException(msg.toString());
	}

	/**
	 * Returns the first account project in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account project, or <code>null</code> if a matching account project could not be found
	 */
	@Override
	public AccountProject fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountProject> orderByComparator) {
		List<AccountProject> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account project in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account project
	 * @throws NoSuchAccountProjectException if a matching account project could not be found
	 */
	@Override
	public AccountProject findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountProject> orderByComparator)
		throws NoSuchAccountProjectException {
		AccountProject accountProject = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (accountProject != null) {
			return accountProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountProjectException(msg.toString());
	}

	/**
	 * Returns the last account project in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account project, or <code>null</code> if a matching account project could not be found
	 */
	@Override
	public AccountProject fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountProject> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<AccountProject> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account projects before and after the current account project in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountProjectId the primary key of the current account project
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account project
	 * @throws NoSuchAccountProjectException if a account project with the primary key could not be found
	 */
	@Override
	public AccountProject[] findByAccountEntryId_PrevAndNext(
		long accountProjectId, long accountEntryId,
		OrderByComparator<AccountProject> orderByComparator)
		throws NoSuchAccountProjectException {
		AccountProject accountProject = findByPrimaryKey(accountProjectId);

		Session session = null;

		try {
			session = openSession();

			AccountProject[] array = new AccountProjectImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, accountProject,
					accountEntryId, orderByComparator, true);

			array[1] = accountProject;

			array[2] = getByAccountEntryId_PrevAndNext(session, accountProject,
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

	protected AccountProject getByAccountEntryId_PrevAndNext(Session session,
		AccountProject accountProject, long accountEntryId,
		OrderByComparator<AccountProject> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNTPROJECT_WHERE);

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
			query.append(AccountProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accountProject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccountProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account projects where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (AccountProject accountProject : findByAccountEntryId(
				accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accountProject);
		}
	}

	/**
	 * Returns the number of account projects where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account projects
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNTPROJECT_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "accountProject.accountEntryId = ?";

	public AccountProjectPersistenceImpl() {
		setModelClass(AccountProject.class);
	}

	/**
	 * Caches the account project in the entity cache if it is enabled.
	 *
	 * @param accountProject the account project
	 */
	@Override
	public void cacheResult(AccountProject accountProject) {
		entityCache.putResult(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
			AccountProjectImpl.class, accountProject.getPrimaryKey(),
			accountProject);

		accountProject.resetOriginalValues();
	}

	/**
	 * Caches the account projects in the entity cache if it is enabled.
	 *
	 * @param accountProjects the account projects
	 */
	@Override
	public void cacheResult(List<AccountProject> accountProjects) {
		for (AccountProject accountProject : accountProjects) {
			if (entityCache.getResult(
						AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
						AccountProjectImpl.class, accountProject.getPrimaryKey()) == null) {
				cacheResult(accountProject);
			}
			else {
				accountProject.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account projects.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountProjectImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account project.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountProject accountProject) {
		entityCache.removeResult(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
			AccountProjectImpl.class, accountProject.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccountProject> accountProjects) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountProject accountProject : accountProjects) {
			entityCache.removeResult(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
				AccountProjectImpl.class, accountProject.getPrimaryKey());
		}
	}

	/**
	 * Creates a new account project with the primary key. Does not add the account project to the database.
	 *
	 * @param accountProjectId the primary key for the new account project
	 * @return the new account project
	 */
	@Override
	public AccountProject create(long accountProjectId) {
		AccountProject accountProject = new AccountProjectImpl();

		accountProject.setNew(true);
		accountProject.setPrimaryKey(accountProjectId);

		return accountProject;
	}

	/**
	 * Removes the account project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountProjectId the primary key of the account project
	 * @return the account project that was removed
	 * @throws NoSuchAccountProjectException if a account project with the primary key could not be found
	 */
	@Override
	public AccountProject remove(long accountProjectId)
		throws NoSuchAccountProjectException {
		return remove((Serializable)accountProjectId);
	}

	/**
	 * Removes the account project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account project
	 * @return the account project that was removed
	 * @throws NoSuchAccountProjectException if a account project with the primary key could not be found
	 */
	@Override
	public AccountProject remove(Serializable primaryKey)
		throws NoSuchAccountProjectException {
		Session session = null;

		try {
			session = openSession();

			AccountProject accountProject = (AccountProject)session.get(AccountProjectImpl.class,
					primaryKey);

			if (accountProject == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accountProject);
		}
		catch (NoSuchAccountProjectException nsee) {
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
	protected AccountProject removeImpl(AccountProject accountProject) {
		accountProject = toUnwrappedModel(accountProject);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountProject)) {
				accountProject = (AccountProject)session.get(AccountProjectImpl.class,
						accountProject.getPrimaryKeyObj());
			}

			if (accountProject != null) {
				session.delete(accountProject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accountProject != null) {
			clearCache(accountProject);
		}

		return accountProject;
	}

	@Override
	public AccountProject updateImpl(AccountProject accountProject) {
		accountProject = toUnwrappedModel(accountProject);

		boolean isNew = accountProject.isNew();

		AccountProjectModelImpl accountProjectModelImpl = (AccountProjectModelImpl)accountProject;

		Session session = null;

		try {
			session = openSession();

			if (accountProject.isNew()) {
				session.save(accountProject);

				accountProject.setNew(false);
			}
			else {
				accountProject = (AccountProject)session.merge(accountProject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AccountProjectModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					accountProjectModelImpl.getAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accountProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accountProjectModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] { accountProjectModelImpl.getAccountEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}
		}

		entityCache.putResult(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
			AccountProjectImpl.class, accountProject.getPrimaryKey(),
			accountProject, false);

		accountProject.resetOriginalValues();

		return accountProject;
	}

	protected AccountProject toUnwrappedModel(AccountProject accountProject) {
		if (accountProject instanceof AccountProjectImpl) {
			return accountProject;
		}

		AccountProjectImpl accountProjectImpl = new AccountProjectImpl();

		accountProjectImpl.setNew(accountProject.isNew());
		accountProjectImpl.setPrimaryKey(accountProject.getPrimaryKey());

		accountProjectImpl.setAccountProjectId(accountProject.getAccountProjectId());
		accountProjectImpl.setModifiedUserId(accountProject.getModifiedUserId());
		accountProjectImpl.setModifiedUserName(accountProject.getModifiedUserName());
		accountProjectImpl.setModifiedDate(accountProject.getModifiedDate());
		accountProjectImpl.setAccountEntryId(accountProject.getAccountEntryId());
		accountProjectImpl.setName(accountProject.getName());

		return accountProjectImpl;
	}

	/**
	 * Returns the account project with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account project
	 * @return the account project
	 * @throws NoSuchAccountProjectException if a account project with the primary key could not be found
	 */
	@Override
	public AccountProject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountProjectException {
		AccountProject accountProject = fetchByPrimaryKey(primaryKey);

		if (accountProject == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accountProject;
	}

	/**
	 * Returns the account project with the primary key or throws a {@link NoSuchAccountProjectException} if it could not be found.
	 *
	 * @param accountProjectId the primary key of the account project
	 * @return the account project
	 * @throws NoSuchAccountProjectException if a account project with the primary key could not be found
	 */
	@Override
	public AccountProject findByPrimaryKey(long accountProjectId)
		throws NoSuchAccountProjectException {
		return findByPrimaryKey((Serializable)accountProjectId);
	}

	/**
	 * Returns the account project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account project
	 * @return the account project, or <code>null</code> if a account project with the primary key could not be found
	 */
	@Override
	public AccountProject fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
				AccountProjectImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccountProject accountProject = (AccountProject)serializable;

		if (accountProject == null) {
			Session session = null;

			try {
				session = openSession();

				accountProject = (AccountProject)session.get(AccountProjectImpl.class,
						primaryKey);

				if (accountProject != null) {
					cacheResult(accountProject);
				}
				else {
					entityCache.putResult(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
						AccountProjectImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
					AccountProjectImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accountProject;
	}

	/**
	 * Returns the account project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountProjectId the primary key of the account project
	 * @return the account project, or <code>null</code> if a account project with the primary key could not be found
	 */
	@Override
	public AccountProject fetchByPrimaryKey(long accountProjectId) {
		return fetchByPrimaryKey((Serializable)accountProjectId);
	}

	@Override
	public Map<Serializable, AccountProject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccountProject> map = new HashMap<Serializable, AccountProject>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccountProject accountProject = fetchByPrimaryKey(primaryKey);

			if (accountProject != null) {
				map.put(primaryKey, accountProject);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
					AccountProjectImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccountProject)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCOUNTPROJECT_WHERE_PKS_IN);

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

			for (AccountProject accountProject : (List<AccountProject>)q.list()) {
				map.put(accountProject.getPrimaryKeyObj(), accountProject);

				cacheResult(accountProject);

				uncachedPrimaryKeys.remove(accountProject.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccountProjectModelImpl.ENTITY_CACHE_ENABLED,
					AccountProjectImpl.class, primaryKey, nullModel);
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
	 * Returns all the account projects.
	 *
	 * @return the account projects
	 */
	@Override
	public List<AccountProject> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account projects
	 * @param end the upper bound of the range of account projects (not inclusive)
	 * @return the range of account projects
	 */
	@Override
	public List<AccountProject> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account projects
	 * @param end the upper bound of the range of account projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account projects
	 */
	@Override
	public List<AccountProject> findAll(int start, int end,
		OrderByComparator<AccountProject> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account projects
	 * @param end the upper bound of the range of account projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of account projects
	 */
	@Override
	public List<AccountProject> findAll(int start, int end,
		OrderByComparator<AccountProject> orderByComparator,
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

		List<AccountProject> list = null;

		if (retrieveFromCache) {
			list = (List<AccountProject>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCOUNTPROJECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTPROJECT;

				if (pagination) {
					sql = sql.concat(AccountProjectModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccountProject>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccountProject>)QueryUtil.list(q,
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
	 * Removes all the account projects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountProject accountProject : findAll()) {
			remove(accountProject);
		}
	}

	/**
	 * Returns the number of account projects.
	 *
	 * @return the number of account projects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCOUNTPROJECT);

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
		return AccountProjectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account project persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccountProjectImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ACCOUNTPROJECT = "SELECT accountProject FROM AccountProject accountProject";
	private static final String _SQL_SELECT_ACCOUNTPROJECT_WHERE_PKS_IN = "SELECT accountProject FROM AccountProject accountProject WHERE accountProjectId IN (";
	private static final String _SQL_SELECT_ACCOUNTPROJECT_WHERE = "SELECT accountProject FROM AccountProject accountProject WHERE ";
	private static final String _SQL_COUNT_ACCOUNTPROJECT = "SELECT COUNT(accountProject) FROM AccountProject accountProject";
	private static final String _SQL_COUNT_ACCOUNTPROJECT_WHERE = "SELECT COUNT(accountProject) FROM AccountProject accountProject WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accountProject.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccountProject exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccountProject exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AccountProjectPersistenceImpl.class);
}
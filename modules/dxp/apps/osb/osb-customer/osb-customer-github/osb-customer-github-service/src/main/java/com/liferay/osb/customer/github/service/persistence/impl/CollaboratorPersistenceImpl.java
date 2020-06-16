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

package com.liferay.osb.customer.github.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.github.exception.NoSuchCollaboratorException;
import com.liferay.osb.customer.github.model.Collaborator;
import com.liferay.osb.customer.github.model.impl.CollaboratorImpl;
import com.liferay.osb.customer.github.model.impl.CollaboratorModelImpl;
import com.liferay.osb.customer.github.service.persistence.CollaboratorPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the collaborator service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CollaboratorPersistence
 * @see com.liferay.osb.customer.github.service.persistence.CollaboratorUtil
 * @generated
 */
@ProviderType
public class CollaboratorPersistenceImpl extends BasePersistenceImpl<Collaborator>
	implements CollaboratorPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CollaboratorUtil} to access the collaborator persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CollaboratorImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, CollaboratorImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, CollaboratorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, CollaboratorImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, CollaboratorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			CollaboratorModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			CollaboratorModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the collaborators where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching collaborators
	 */
	@Override
	public List<Collaborator> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the collaborators where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the collaborators where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<Collaborator> orderByComparator) {
		return findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the collaborators where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<Collaborator> orderByComparator,
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

		List<Collaborator> list = null;

		if (retrieveFromCache) {
			list = (List<Collaborator>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Collaborator collaborator : list) {
					if ((accountEntryId != collaborator.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_COLLABORATOR_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CollaboratorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (!pagination) {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	@Override
	public Collaborator findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (collaborator != null) {
			return collaborator;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCollaboratorException(msg.toString());
	}

	/**
	 * Returns the first collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	@Override
	public Collaborator fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<Collaborator> orderByComparator) {
		List<Collaborator> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	@Override
	public Collaborator findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (collaborator != null) {
			return collaborator;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCollaboratorException(msg.toString());
	}

	/**
	 * Returns the last collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	@Override
	public Collaborator fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<Collaborator> orderByComparator) {
		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<Collaborator> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the collaborators before and after the current collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param collaboratorId the primary key of the current collaborator
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next collaborator
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	@Override
	public Collaborator[] findByAccountEntryId_PrevAndNext(
		long collaboratorId, long accountEntryId,
		OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = findByPrimaryKey(collaboratorId);

		Session session = null;

		try {
			session = openSession();

			Collaborator[] array = new CollaboratorImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, collaborator,
					accountEntryId, orderByComparator, true);

			array[1] = collaborator;

			array[2] = getByAccountEntryId_PrevAndNext(session, collaborator,
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

	protected Collaborator getByAccountEntryId_PrevAndNext(Session session,
		Collaborator collaborator, long accountEntryId,
		OrderByComparator<Collaborator> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COLLABORATOR_WHERE);

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
			query.append(CollaboratorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(collaborator);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Collaborator> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the collaborators where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (Collaborator collaborator : findByAccountEntryId(accountEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(collaborator);
		}
	}

	/**
	 * Returns the number of collaborators where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching collaborators
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTENTRYID;

		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COLLABORATOR_WHERE);

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

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "collaborator.accountEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GITHUBUSERNAME =
		new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, CollaboratorImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGitHubUserName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GITHUBUSERNAME =
		new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, CollaboratorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGitHubUserName",
			new String[] { String.class.getName() },
			CollaboratorModelImpl.GITHUBUSERNAME_COLUMN_BITMASK |
			CollaboratorModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GITHUBUSERNAME = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGitHubUserName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the collaborators where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @return the matching collaborators
	 */
	@Override
	public List<Collaborator> findByGitHubUserName(String gitHubUserName) {
		return findByGitHubUserName(gitHubUserName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the collaborators where gitHubUserName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByGitHubUserName(String gitHubUserName,
		int start, int end) {
		return findByGitHubUserName(gitHubUserName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the collaborators where gitHubUserName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByGitHubUserName(String gitHubUserName,
		int start, int end, OrderByComparator<Collaborator> orderByComparator) {
		return findByGitHubUserName(gitHubUserName, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the collaborators where gitHubUserName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByGitHubUserName(String gitHubUserName,
		int start, int end, OrderByComparator<Collaborator> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GITHUBUSERNAME;
			finderArgs = new Object[] { gitHubUserName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GITHUBUSERNAME;
			finderArgs = new Object[] {
					gitHubUserName,
					
					start, end, orderByComparator
				};
		}

		List<Collaborator> list = null;

		if (retrieveFromCache) {
			list = (List<Collaborator>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Collaborator collaborator : list) {
					if (!Objects.equals(gitHubUserName,
								collaborator.getGitHubUserName())) {
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

			query.append(_SQL_SELECT_COLLABORATOR_WHERE);

			boolean bindGitHubUserName = false;

			if (gitHubUserName == null) {
				query.append(_FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_1);
			}
			else if (gitHubUserName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_3);
			}
			else {
				bindGitHubUserName = true;

				query.append(_FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CollaboratorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGitHubUserName) {
					qPos.add(gitHubUserName);
				}

				if (!pagination) {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	@Override
	public Collaborator findByGitHubUserName_First(String gitHubUserName,
		OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = fetchByGitHubUserName_First(gitHubUserName,
				orderByComparator);

		if (collaborator != null) {
			return collaborator;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gitHubUserName=");
		msg.append(gitHubUserName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCollaboratorException(msg.toString());
	}

	/**
	 * Returns the first collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	@Override
	public Collaborator fetchByGitHubUserName_First(String gitHubUserName,
		OrderByComparator<Collaborator> orderByComparator) {
		List<Collaborator> list = findByGitHubUserName(gitHubUserName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	@Override
	public Collaborator findByGitHubUserName_Last(String gitHubUserName,
		OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = fetchByGitHubUserName_Last(gitHubUserName,
				orderByComparator);

		if (collaborator != null) {
			return collaborator;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gitHubUserName=");
		msg.append(gitHubUserName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCollaboratorException(msg.toString());
	}

	/**
	 * Returns the last collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	@Override
	public Collaborator fetchByGitHubUserName_Last(String gitHubUserName,
		OrderByComparator<Collaborator> orderByComparator) {
		int count = countByGitHubUserName(gitHubUserName);

		if (count == 0) {
			return null;
		}

		List<Collaborator> list = findByGitHubUserName(gitHubUserName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the collaborators before and after the current collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param collaboratorId the primary key of the current collaborator
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next collaborator
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	@Override
	public Collaborator[] findByGitHubUserName_PrevAndNext(
		long collaboratorId, String gitHubUserName,
		OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = findByPrimaryKey(collaboratorId);

		Session session = null;

		try {
			session = openSession();

			Collaborator[] array = new CollaboratorImpl[3];

			array[0] = getByGitHubUserName_PrevAndNext(session, collaborator,
					gitHubUserName, orderByComparator, true);

			array[1] = collaborator;

			array[2] = getByGitHubUserName_PrevAndNext(session, collaborator,
					gitHubUserName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Collaborator getByGitHubUserName_PrevAndNext(Session session,
		Collaborator collaborator, String gitHubUserName,
		OrderByComparator<Collaborator> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COLLABORATOR_WHERE);

		boolean bindGitHubUserName = false;

		if (gitHubUserName == null) {
			query.append(_FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_1);
		}
		else if (gitHubUserName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_3);
		}
		else {
			bindGitHubUserName = true;

			query.append(_FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_2);
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
			query.append(CollaboratorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindGitHubUserName) {
			qPos.add(gitHubUserName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(collaborator);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Collaborator> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the collaborators where gitHubUserName = &#63; from the database.
	 *
	 * @param gitHubUserName the git hub user name
	 */
	@Override
	public void removeByGitHubUserName(String gitHubUserName) {
		for (Collaborator collaborator : findByGitHubUserName(gitHubUserName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(collaborator);
		}
	}

	/**
	 * Returns the number of collaborators where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @return the number of matching collaborators
	 */
	@Override
	public int countByGitHubUserName(String gitHubUserName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GITHUBUSERNAME;

		Object[] finderArgs = new Object[] { gitHubUserName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COLLABORATOR_WHERE);

			boolean bindGitHubUserName = false;

			if (gitHubUserName == null) {
				query.append(_FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_1);
			}
			else if (gitHubUserName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_3);
			}
			else {
				bindGitHubUserName = true;

				query.append(_FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGitHubUserName) {
					qPos.add(gitHubUserName);
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

	private static final String _FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_1 = "collaborator.gitHubUserName IS NULL";
	private static final String _FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_2 = "collaborator.gitHubUserName = ?";
	private static final String _FINDER_COLUMN_GITHUBUSERNAME_GITHUBUSERNAME_3 = "(collaborator.gitHubUserName IS NULL OR collaborator.gitHubUserName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, CollaboratorImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, CollaboratorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Integer.class.getName() },
			CollaboratorModelImpl.STATUS_COLUMN_BITMASK |
			CollaboratorModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the collaborators where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching collaborators
	 */
	@Override
	public List<Collaborator> findByStatus(int status) {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the collaborators where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByStatus(int status, int start, int end) {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the collaborators where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByStatus(int status, int start, int end,
		OrderByComparator<Collaborator> orderByComparator) {
		return findByStatus(status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the collaborators where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByStatus(int status, int start, int end,
		OrderByComparator<Collaborator> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status, start, end, orderByComparator };
		}

		List<Collaborator> list = null;

		if (retrieveFromCache) {
			list = (List<Collaborator>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Collaborator collaborator : list) {
					if ((status != collaborator.getStatus())) {
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

			query.append(_SQL_SELECT_COLLABORATOR_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CollaboratorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				if (!pagination) {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first collaborator in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	@Override
	public Collaborator findByStatus_First(int status,
		OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = fetchByStatus_First(status,
				orderByComparator);

		if (collaborator != null) {
			return collaborator;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCollaboratorException(msg.toString());
	}

	/**
	 * Returns the first collaborator in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	@Override
	public Collaborator fetchByStatus_First(int status,
		OrderByComparator<Collaborator> orderByComparator) {
		List<Collaborator> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last collaborator in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	@Override
	public Collaborator findByStatus_Last(int status,
		OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = fetchByStatus_Last(status, orderByComparator);

		if (collaborator != null) {
			return collaborator;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCollaboratorException(msg.toString());
	}

	/**
	 * Returns the last collaborator in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	@Override
	public Collaborator fetchByStatus_Last(int status,
		OrderByComparator<Collaborator> orderByComparator) {
		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<Collaborator> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the collaborators before and after the current collaborator in the ordered set where status = &#63;.
	 *
	 * @param collaboratorId the primary key of the current collaborator
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next collaborator
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	@Override
	public Collaborator[] findByStatus_PrevAndNext(long collaboratorId,
		int status, OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = findByPrimaryKey(collaboratorId);

		Session session = null;

		try {
			session = openSession();

			Collaborator[] array = new CollaboratorImpl[3];

			array[0] = getByStatus_PrevAndNext(session, collaborator, status,
					orderByComparator, true);

			array[1] = collaborator;

			array[2] = getByStatus_PrevAndNext(session, collaborator, status,
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

	protected Collaborator getByStatus_PrevAndNext(Session session,
		Collaborator collaborator, int status,
		OrderByComparator<Collaborator> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COLLABORATOR_WHERE);

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

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
			query.append(CollaboratorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(collaborator);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Collaborator> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the collaborators where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeByStatus(int status) {
		for (Collaborator collaborator : findByStatus(status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(collaborator);
		}
	}

	/**
	 * Returns the number of collaborators where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching collaborators
	 */
	@Override
	public int countByStatus(int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS;

		Object[] finderArgs = new Object[] { status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COLLABORATOR_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "collaborator.status = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_GHUN = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, CollaboratorImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAEI_GHUN",
			new String[] { Long.class.getName(), String.class.getName() },
			CollaboratorModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			CollaboratorModelImpl.GITHUBUSERNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_GHUN = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_GHUN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or throws a {@link NoSuchCollaboratorException} if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	@Override
	public Collaborator findByAEI_GHUN(long accountEntryId,
		String gitHubUserName) throws NoSuchCollaboratorException {
		Collaborator collaborator = fetchByAEI_GHUN(accountEntryId,
				gitHubUserName);

		if (collaborator == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEntryId=");
			msg.append(accountEntryId);

			msg.append(", gitHubUserName=");
			msg.append(gitHubUserName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCollaboratorException(msg.toString());
		}

		return collaborator;
	}

	/**
	 * Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	@Override
	public Collaborator fetchByAEI_GHUN(long accountEntryId,
		String gitHubUserName) {
		return fetchByAEI_GHUN(accountEntryId, gitHubUserName, true);
	}

	/**
	 * Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	@Override
	public Collaborator fetchByAEI_GHUN(long accountEntryId,
		String gitHubUserName, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { accountEntryId, gitHubUserName };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_AEI_GHUN,
					finderArgs, this);
		}

		if (result instanceof Collaborator) {
			Collaborator collaborator = (Collaborator)result;

			if ((accountEntryId != collaborator.getAccountEntryId()) ||
					!Objects.equals(gitHubUserName,
						collaborator.getGitHubUserName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COLLABORATOR_WHERE);

			query.append(_FINDER_COLUMN_AEI_GHUN_ACCOUNTENTRYID_2);

			boolean bindGitHubUserName = false;

			if (gitHubUserName == null) {
				query.append(_FINDER_COLUMN_AEI_GHUN_GITHUBUSERNAME_1);
			}
			else if (gitHubUserName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_AEI_GHUN_GITHUBUSERNAME_3);
			}
			else {
				bindGitHubUserName = true;

				query.append(_FINDER_COLUMN_AEI_GHUN_GITHUBUSERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (bindGitHubUserName) {
					qPos.add(gitHubUserName);
				}

				List<Collaborator> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_GHUN,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CollaboratorPersistenceImpl.fetchByAEI_GHUN(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Collaborator collaborator = list.get(0);

					result = collaborator;

					cacheResult(collaborator);

					if ((collaborator.getAccountEntryId() != accountEntryId) ||
							(collaborator.getGitHubUserName() == null) ||
							!collaborator.getGitHubUserName()
											 .equals(gitHubUserName)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_GHUN,
							finderArgs, collaborator);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_GHUN,
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
			return (Collaborator)result;
		}
	}

	/**
	 * Removes the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the collaborator that was removed
	 */
	@Override
	public Collaborator removeByAEI_GHUN(long accountEntryId,
		String gitHubUserName) throws NoSuchCollaboratorException {
		Collaborator collaborator = findByAEI_GHUN(accountEntryId,
				gitHubUserName);

		return remove(collaborator);
	}

	/**
	 * Returns the number of collaborators where accountEntryId = &#63; and gitHubUserName = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the number of matching collaborators
	 */
	@Override
	public int countByAEI_GHUN(long accountEntryId, String gitHubUserName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_GHUN;

		Object[] finderArgs = new Object[] { accountEntryId, gitHubUserName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COLLABORATOR_WHERE);

			query.append(_FINDER_COLUMN_AEI_GHUN_ACCOUNTENTRYID_2);

			boolean bindGitHubUserName = false;

			if (gitHubUserName == null) {
				query.append(_FINDER_COLUMN_AEI_GHUN_GITHUBUSERNAME_1);
			}
			else if (gitHubUserName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_AEI_GHUN_GITHUBUSERNAME_3);
			}
			else {
				bindGitHubUserName = true;

				query.append(_FINDER_COLUMN_AEI_GHUN_GITHUBUSERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (bindGitHubUserName) {
					qPos.add(gitHubUserName);
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

	private static final String _FINDER_COLUMN_AEI_GHUN_ACCOUNTENTRYID_2 = "collaborator.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_GHUN_GITHUBUSERNAME_1 = "collaborator.gitHubUserName IS NULL";
	private static final String _FINDER_COLUMN_AEI_GHUN_GITHUBUSERNAME_2 = "collaborator.gitHubUserName = ?";
	private static final String _FINDER_COLUMN_AEI_GHUN_GITHUBUSERNAME_3 = "(collaborator.gitHubUserName IS NULL OR collaborator.gitHubUserName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GHUN_S = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, CollaboratorImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGHUN_S",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GHUN_S =
		new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, CollaboratorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGHUN_S",
			new String[] { String.class.getName(), Integer.class.getName() },
			CollaboratorModelImpl.GITHUBUSERNAME_COLUMN_BITMASK |
			CollaboratorModelImpl.STATUS_COLUMN_BITMASK |
			CollaboratorModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GHUN_S = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGHUN_S",
			new String[] { String.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GHUN_S = new FinderPath(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGHUN_S",
			new String[] { String.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the collaborators where gitHubUserName = &#63; and status = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 * @return the matching collaborators
	 */
	@Override
	public List<Collaborator> findByGHUN_S(String gitHubUserName, int status) {
		return findByGHUN_S(gitHubUserName, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the collaborators where gitHubUserName = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByGHUN_S(String gitHubUserName, int status,
		int start, int end) {
		return findByGHUN_S(gitHubUserName, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the collaborators where gitHubUserName = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByGHUN_S(String gitHubUserName, int status,
		int start, int end, OrderByComparator<Collaborator> orderByComparator) {
		return findByGHUN_S(gitHubUserName, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the collaborators where gitHubUserName = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByGHUN_S(String gitHubUserName, int status,
		int start, int end, OrderByComparator<Collaborator> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GHUN_S;
			finderArgs = new Object[] { gitHubUserName, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GHUN_S;
			finderArgs = new Object[] {
					gitHubUserName, status,
					
					start, end, orderByComparator
				};
		}

		List<Collaborator> list = null;

		if (retrieveFromCache) {
			list = (List<Collaborator>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Collaborator collaborator : list) {
					if (!Objects.equals(gitHubUserName,
								collaborator.getGitHubUserName()) ||
							(status != collaborator.getStatus())) {
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

			query.append(_SQL_SELECT_COLLABORATOR_WHERE);

			boolean bindGitHubUserName = false;

			if (gitHubUserName == null) {
				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_1);
			}
			else if (gitHubUserName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_3);
			}
			else {
				bindGitHubUserName = true;

				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_2);
			}

			query.append(_FINDER_COLUMN_GHUN_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CollaboratorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGitHubUserName) {
					qPos.add(gitHubUserName);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first collaborator in the ordered set where gitHubUserName = &#63; and status = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	@Override
	public Collaborator findByGHUN_S_First(String gitHubUserName, int status,
		OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = fetchByGHUN_S_First(gitHubUserName, status,
				orderByComparator);

		if (collaborator != null) {
			return collaborator;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gitHubUserName=");
		msg.append(gitHubUserName);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCollaboratorException(msg.toString());
	}

	/**
	 * Returns the first collaborator in the ordered set where gitHubUserName = &#63; and status = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	@Override
	public Collaborator fetchByGHUN_S_First(String gitHubUserName, int status,
		OrderByComparator<Collaborator> orderByComparator) {
		List<Collaborator> list = findByGHUN_S(gitHubUserName, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last collaborator in the ordered set where gitHubUserName = &#63; and status = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	@Override
	public Collaborator findByGHUN_S_Last(String gitHubUserName, int status,
		OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = fetchByGHUN_S_Last(gitHubUserName, status,
				orderByComparator);

		if (collaborator != null) {
			return collaborator;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gitHubUserName=");
		msg.append(gitHubUserName);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCollaboratorException(msg.toString());
	}

	/**
	 * Returns the last collaborator in the ordered set where gitHubUserName = &#63; and status = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	@Override
	public Collaborator fetchByGHUN_S_Last(String gitHubUserName, int status,
		OrderByComparator<Collaborator> orderByComparator) {
		int count = countByGHUN_S(gitHubUserName, status);

		if (count == 0) {
			return null;
		}

		List<Collaborator> list = findByGHUN_S(gitHubUserName, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the collaborators before and after the current collaborator in the ordered set where gitHubUserName = &#63; and status = &#63;.
	 *
	 * @param collaboratorId the primary key of the current collaborator
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next collaborator
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	@Override
	public Collaborator[] findByGHUN_S_PrevAndNext(long collaboratorId,
		String gitHubUserName, int status,
		OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = findByPrimaryKey(collaboratorId);

		Session session = null;

		try {
			session = openSession();

			Collaborator[] array = new CollaboratorImpl[3];

			array[0] = getByGHUN_S_PrevAndNext(session, collaborator,
					gitHubUserName, status, orderByComparator, true);

			array[1] = collaborator;

			array[2] = getByGHUN_S_PrevAndNext(session, collaborator,
					gitHubUserName, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Collaborator getByGHUN_S_PrevAndNext(Session session,
		Collaborator collaborator, String gitHubUserName, int status,
		OrderByComparator<Collaborator> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_COLLABORATOR_WHERE);

		boolean bindGitHubUserName = false;

		if (gitHubUserName == null) {
			query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_1);
		}
		else if (gitHubUserName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_3);
		}
		else {
			bindGitHubUserName = true;

			query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_2);
		}

		query.append(_FINDER_COLUMN_GHUN_S_STATUS_2);

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
			query.append(CollaboratorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindGitHubUserName) {
			qPos.add(gitHubUserName);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(collaborator);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Collaborator> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the collaborators where gitHubUserName = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param statuses the statuses
	 * @return the matching collaborators
	 */
	@Override
	public List<Collaborator> findByGHUN_S(String gitHubUserName, int[] statuses) {
		return findByGHUN_S(gitHubUserName, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the collaborators where gitHubUserName = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param statuses the statuses
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByGHUN_S(String gitHubUserName,
		int[] statuses, int start, int end) {
		return findByGHUN_S(gitHubUserName, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the collaborators where gitHubUserName = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param statuses the statuses
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByGHUN_S(String gitHubUserName,
		int[] statuses, int start, int end,
		OrderByComparator<Collaborator> orderByComparator) {
		return findByGHUN_S(gitHubUserName, statuses, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the collaborators where gitHubUserName = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching collaborators
	 */
	@Override
	public List<Collaborator> findByGHUN_S(String gitHubUserName,
		int[] statuses, int start, int end,
		OrderByComparator<Collaborator> orderByComparator,
		boolean retrieveFromCache) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		if (statuses.length == 1) {
			return findByGHUN_S(gitHubUserName, statuses[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { gitHubUserName, StringUtil.merge(statuses) };
		}
		else {
			finderArgs = new Object[] {
					gitHubUserName, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<Collaborator> list = null;

		if (retrieveFromCache) {
			list = (List<Collaborator>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GHUN_S,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Collaborator collaborator : list) {
					if (!Objects.equals(gitHubUserName,
								collaborator.getGitHubUserName()) ||
							!ArrayUtil.contains(statuses,
								collaborator.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_COLLABORATOR_WHERE);

			boolean bindGitHubUserName = false;

			if (gitHubUserName == null) {
				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_1);
			}
			else if (gitHubUserName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_3);
			}
			else {
				bindGitHubUserName = true;

				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_2);
			}

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_GHUN_S_STATUS_7);

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
				query.append(CollaboratorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGitHubUserName) {
					qPos.add(gitHubUserName);
				}

				if (!pagination) {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GHUN_S,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GHUN_S,
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
	 * Removes all the collaborators where gitHubUserName = &#63; and status = &#63; from the database.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 */
	@Override
	public void removeByGHUN_S(String gitHubUserName, int status) {
		for (Collaborator collaborator : findByGHUN_S(gitHubUserName, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(collaborator);
		}
	}

	/**
	 * Returns the number of collaborators where gitHubUserName = &#63; and status = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param status the status
	 * @return the number of matching collaborators
	 */
	@Override
	public int countByGHUN_S(String gitHubUserName, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GHUN_S;

		Object[] finderArgs = new Object[] { gitHubUserName, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COLLABORATOR_WHERE);

			boolean bindGitHubUserName = false;

			if (gitHubUserName == null) {
				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_1);
			}
			else if (gitHubUserName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_3);
			}
			else {
				bindGitHubUserName = true;

				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_2);
			}

			query.append(_FINDER_COLUMN_GHUN_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGitHubUserName) {
					qPos.add(gitHubUserName);
				}

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
	 * Returns the number of collaborators where gitHubUserName = &#63; and status = any &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param statuses the statuses
	 * @return the number of matching collaborators
	 */
	@Override
	public int countByGHUN_S(String gitHubUserName, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.unique(statuses);

			Arrays.sort(statuses);
		}

		Object[] finderArgs = new Object[] {
				gitHubUserName, StringUtil.merge(statuses)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GHUN_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_COLLABORATOR_WHERE);

			boolean bindGitHubUserName = false;

			if (gitHubUserName == null) {
				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_1);
			}
			else if (gitHubUserName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_3);
			}
			else {
				bindGitHubUserName = true;

				query.append(_FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_2);
			}

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_GHUN_S_STATUS_7);

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

				if (bindGitHubUserName) {
					qPos.add(gitHubUserName);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GHUN_S,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GHUN_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_1 = "collaborator.gitHubUserName IS NULL AND ";
	private static final String _FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_2 = "collaborator.gitHubUserName = ? AND ";
	private static final String _FINDER_COLUMN_GHUN_S_GITHUBUSERNAME_3 = "(collaborator.gitHubUserName IS NULL OR collaborator.gitHubUserName = '') AND ";
	private static final String _FINDER_COLUMN_GHUN_S_STATUS_2 = "collaborator.status = ?";
	private static final String _FINDER_COLUMN_GHUN_S_STATUS_7 = "collaborator.status IN (";

	public CollaboratorPersistenceImpl() {
		setModelClass(Collaborator.class);
	}

	/**
	 * Caches the collaborator in the entity cache if it is enabled.
	 *
	 * @param collaborator the collaborator
	 */
	@Override
	public void cacheResult(Collaborator collaborator) {
		entityCache.putResult(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorImpl.class, collaborator.getPrimaryKey(), collaborator);

		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_GHUN,
			new Object[] {
				collaborator.getAccountEntryId(),
				collaborator.getGitHubUserName()
			}, collaborator);

		collaborator.resetOriginalValues();
	}

	/**
	 * Caches the collaborators in the entity cache if it is enabled.
	 *
	 * @param collaborators the collaborators
	 */
	@Override
	public void cacheResult(List<Collaborator> collaborators) {
		for (Collaborator collaborator : collaborators) {
			if (entityCache.getResult(
						CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
						CollaboratorImpl.class, collaborator.getPrimaryKey()) == null) {
				cacheResult(collaborator);
			}
			else {
				collaborator.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all collaborators.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CollaboratorImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the collaborator.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Collaborator collaborator) {
		entityCache.removeResult(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorImpl.class, collaborator.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CollaboratorModelImpl)collaborator, true);
	}

	@Override
	public void clearCache(List<Collaborator> collaborators) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Collaborator collaborator : collaborators) {
			entityCache.removeResult(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
				CollaboratorImpl.class, collaborator.getPrimaryKey());

			clearUniqueFindersCache((CollaboratorModelImpl)collaborator, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CollaboratorModelImpl collaboratorModelImpl) {
		Object[] args = new Object[] {
				collaboratorModelImpl.getAccountEntryId(),
				collaboratorModelImpl.getGitHubUserName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_AEI_GHUN, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_AEI_GHUN, args,
			collaboratorModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CollaboratorModelImpl collaboratorModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					collaboratorModelImpl.getAccountEntryId(),
					collaboratorModelImpl.getGitHubUserName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_GHUN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_GHUN, args);
		}

		if ((collaboratorModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_GHUN.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					collaboratorModelImpl.getOriginalAccountEntryId(),
					collaboratorModelImpl.getOriginalGitHubUserName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_GHUN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_AEI_GHUN, args);
		}
	}

	/**
	 * Creates a new collaborator with the primary key. Does not add the collaborator to the database.
	 *
	 * @param collaboratorId the primary key for the new collaborator
	 * @return the new collaborator
	 */
	@Override
	public Collaborator create(long collaboratorId) {
		Collaborator collaborator = new CollaboratorImpl();

		collaborator.setNew(true);
		collaborator.setPrimaryKey(collaboratorId);

		return collaborator;
	}

	/**
	 * Removes the collaborator with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param collaboratorId the primary key of the collaborator
	 * @return the collaborator that was removed
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	@Override
	public Collaborator remove(long collaboratorId)
		throws NoSuchCollaboratorException {
		return remove((Serializable)collaboratorId);
	}

	/**
	 * Removes the collaborator with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the collaborator
	 * @return the collaborator that was removed
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	@Override
	public Collaborator remove(Serializable primaryKey)
		throws NoSuchCollaboratorException {
		Session session = null;

		try {
			session = openSession();

			Collaborator collaborator = (Collaborator)session.get(CollaboratorImpl.class,
					primaryKey);

			if (collaborator == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCollaboratorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(collaborator);
		}
		catch (NoSuchCollaboratorException nsee) {
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
	protected Collaborator removeImpl(Collaborator collaborator) {
		collaborator = toUnwrappedModel(collaborator);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(collaborator)) {
				collaborator = (Collaborator)session.get(CollaboratorImpl.class,
						collaborator.getPrimaryKeyObj());
			}

			if (collaborator != null) {
				session.delete(collaborator);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (collaborator != null) {
			clearCache(collaborator);
		}

		return collaborator;
	}

	@Override
	public Collaborator updateImpl(Collaborator collaborator) {
		collaborator = toUnwrappedModel(collaborator);

		boolean isNew = collaborator.isNew();

		CollaboratorModelImpl collaboratorModelImpl = (CollaboratorModelImpl)collaborator;

		Session session = null;

		try {
			session = openSession();

			if (collaborator.isNew()) {
				session.save(collaborator);

				collaborator.setNew(false);
			}
			else {
				collaborator = (Collaborator)session.merge(collaborator);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CollaboratorModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					collaboratorModelImpl.getAccountEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
				args);

			args = new Object[] { collaboratorModelImpl.getGitHubUserName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GITHUBUSERNAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GITHUBUSERNAME,
				args);

			args = new Object[] { collaboratorModelImpl.getStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
				args);

			args = new Object[] {
					collaboratorModelImpl.getGitHubUserName(),
					collaboratorModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GHUN_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GHUN_S,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((collaboratorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						collaboratorModelImpl.getOriginalAccountEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] { collaboratorModelImpl.getAccountEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((collaboratorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GITHUBUSERNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						collaboratorModelImpl.getOriginalGitHubUserName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GITHUBUSERNAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GITHUBUSERNAME,
					args);

				args = new Object[] { collaboratorModelImpl.getGitHubUserName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GITHUBUSERNAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GITHUBUSERNAME,
					args);
			}

			if ((collaboratorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						collaboratorModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] { collaboratorModelImpl.getStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((collaboratorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GHUN_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						collaboratorModelImpl.getOriginalGitHubUserName(),
						collaboratorModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GHUN_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GHUN_S,
					args);

				args = new Object[] {
						collaboratorModelImpl.getGitHubUserName(),
						collaboratorModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GHUN_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GHUN_S,
					args);
			}
		}

		entityCache.putResult(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
			CollaboratorImpl.class, collaborator.getPrimaryKey(), collaborator,
			false);

		clearUniqueFindersCache(collaboratorModelImpl, false);
		cacheUniqueFindersCache(collaboratorModelImpl);

		collaborator.resetOriginalValues();

		return collaborator;
	}

	protected Collaborator toUnwrappedModel(Collaborator collaborator) {
		if (collaborator instanceof CollaboratorImpl) {
			return collaborator;
		}

		CollaboratorImpl collaboratorImpl = new CollaboratorImpl();

		collaboratorImpl.setNew(collaborator.isNew());
		collaboratorImpl.setPrimaryKey(collaborator.getPrimaryKey());

		collaboratorImpl.setCollaboratorId(collaborator.getCollaboratorId());
		collaboratorImpl.setUserId(collaborator.getUserId());
		collaboratorImpl.setCreateDate(collaborator.getCreateDate());
		collaboratorImpl.setAccountEntryId(collaborator.getAccountEntryId());
		collaboratorImpl.setEmailAddress(collaborator.getEmailAddress());
		collaboratorImpl.setFullName(collaborator.getFullName());
		collaboratorImpl.setGitHubUserName(collaborator.getGitHubUserName());
		collaboratorImpl.setStatus(collaborator.getStatus());

		return collaboratorImpl;
	}

	/**
	 * Returns the collaborator with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the collaborator
	 * @return the collaborator
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	@Override
	public Collaborator findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCollaboratorException {
		Collaborator collaborator = fetchByPrimaryKey(primaryKey);

		if (collaborator == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCollaboratorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return collaborator;
	}

	/**
	 * Returns the collaborator with the primary key or throws a {@link NoSuchCollaboratorException} if it could not be found.
	 *
	 * @param collaboratorId the primary key of the collaborator
	 * @return the collaborator
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	@Override
	public Collaborator findByPrimaryKey(long collaboratorId)
		throws NoSuchCollaboratorException {
		return findByPrimaryKey((Serializable)collaboratorId);
	}

	/**
	 * Returns the collaborator with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the collaborator
	 * @return the collaborator, or <code>null</code> if a collaborator with the primary key could not be found
	 */
	@Override
	public Collaborator fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
				CollaboratorImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Collaborator collaborator = (Collaborator)serializable;

		if (collaborator == null) {
			Session session = null;

			try {
				session = openSession();

				collaborator = (Collaborator)session.get(CollaboratorImpl.class,
						primaryKey);

				if (collaborator != null) {
					cacheResult(collaborator);
				}
				else {
					entityCache.putResult(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
						CollaboratorImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
					CollaboratorImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return collaborator;
	}

	/**
	 * Returns the collaborator with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param collaboratorId the primary key of the collaborator
	 * @return the collaborator, or <code>null</code> if a collaborator with the primary key could not be found
	 */
	@Override
	public Collaborator fetchByPrimaryKey(long collaboratorId) {
		return fetchByPrimaryKey((Serializable)collaboratorId);
	}

	@Override
	public Map<Serializable, Collaborator> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Collaborator> map = new HashMap<Serializable, Collaborator>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Collaborator collaborator = fetchByPrimaryKey(primaryKey);

			if (collaborator != null) {
				map.put(primaryKey, collaborator);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
					CollaboratorImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Collaborator)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COLLABORATOR_WHERE_PKS_IN);

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

			for (Collaborator collaborator : (List<Collaborator>)q.list()) {
				map.put(collaborator.getPrimaryKeyObj(), collaborator);

				cacheResult(collaborator);

				uncachedPrimaryKeys.remove(collaborator.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CollaboratorModelImpl.ENTITY_CACHE_ENABLED,
					CollaboratorImpl.class, primaryKey, nullModel);
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
	 * Returns all the collaborators.
	 *
	 * @return the collaborators
	 */
	@Override
	public List<Collaborator> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the collaborators.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of collaborators
	 */
	@Override
	public List<Collaborator> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the collaborators.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of collaborators
	 */
	@Override
	public List<Collaborator> findAll(int start, int end,
		OrderByComparator<Collaborator> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the collaborators.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of collaborators
	 */
	@Override
	public List<Collaborator> findAll(int start, int end,
		OrderByComparator<Collaborator> orderByComparator,
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

		List<Collaborator> list = null;

		if (retrieveFromCache) {
			list = (List<Collaborator>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COLLABORATOR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COLLABORATOR;

				if (pagination) {
					sql = sql.concat(CollaboratorModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Collaborator>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the collaborators from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Collaborator collaborator : findAll()) {
			remove(collaborator);
		}
	}

	/**
	 * Returns the number of collaborators.
	 *
	 * @return the number of collaborators
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COLLABORATOR);

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
		return CollaboratorModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the collaborator persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CollaboratorImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COLLABORATOR = "SELECT collaborator FROM Collaborator collaborator";
	private static final String _SQL_SELECT_COLLABORATOR_WHERE_PKS_IN = "SELECT collaborator FROM Collaborator collaborator WHERE collaboratorId IN (";
	private static final String _SQL_SELECT_COLLABORATOR_WHERE = "SELECT collaborator FROM Collaborator collaborator WHERE ";
	private static final String _SQL_COUNT_COLLABORATOR = "SELECT COUNT(collaborator) FROM Collaborator collaborator";
	private static final String _SQL_COUNT_COLLABORATOR_WHERE = "SELECT COUNT(collaborator) FROM Collaborator collaborator WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "collaborator.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Collaborator exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Collaborator exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CollaboratorPersistenceImpl.class);
}
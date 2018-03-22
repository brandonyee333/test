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

import com.liferay.osb.exception.NoSuchSearchFilterException;
import com.liferay.osb.model.SearchFilter;
import com.liferay.osb.model.impl.SearchFilterImpl;
import com.liferay.osb.model.impl.SearchFilterModelImpl;
import com.liferay.osb.service.persistence.SearchFilterPersistence;

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
 * The persistence implementation for the search filter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SearchFilterPersistence
 * @see com.liferay.osb.service.persistence.SearchFilterUtil
 * @generated
 */
@ProviderType
public class SearchFilterPersistenceImpl extends BasePersistenceImpl<SearchFilter>
	implements SearchFilterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SearchFilterUtil} to access the search filter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SearchFilterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterModelImpl.FINDER_CACHE_ENABLED, SearchFilterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterModelImpl.FINDER_CACHE_ENABLED, SearchFilterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterModelImpl.FINDER_CACHE_ENABLED, SearchFilterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterModelImpl.FINDER_CACHE_ENABLED, SearchFilterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			SearchFilterModelImpl.USERID_COLUMN_BITMASK |
			SearchFilterModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the search filters where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching search filters
	 */
	@Override
	public List<SearchFilter> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the search filters where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of search filters
	 * @param end the upper bound of the range of search filters (not inclusive)
	 * @return the range of matching search filters
	 */
	@Override
	public List<SearchFilter> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the search filters where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of search filters
	 * @param end the upper bound of the range of search filters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching search filters
	 */
	@Override
	public List<SearchFilter> findByUserId(long userId, int start, int end,
		OrderByComparator<SearchFilter> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the search filters where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of search filters
	 * @param end the upper bound of the range of search filters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching search filters
	 */
	@Override
	public List<SearchFilter> findByUserId(long userId, int start, int end,
		OrderByComparator<SearchFilter> orderByComparator,
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

		List<SearchFilter> list = null;

		if (retrieveFromCache) {
			list = (List<SearchFilter>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SearchFilter searchFilter : list) {
					if ((userId != searchFilter.getUserId())) {
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

			query.append(_SQL_SELECT_SEARCHFILTER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SearchFilterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<SearchFilter>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SearchFilter>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first search filter in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching search filter
	 * @throws NoSuchSearchFilterException if a matching search filter could not be found
	 */
	@Override
	public SearchFilter findByUserId_First(long userId,
		OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException {
		SearchFilter searchFilter = fetchByUserId_First(userId,
				orderByComparator);

		if (searchFilter != null) {
			return searchFilter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSearchFilterException(msg.toString());
	}

	/**
	 * Returns the first search filter in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching search filter, or <code>null</code> if a matching search filter could not be found
	 */
	@Override
	public SearchFilter fetchByUserId_First(long userId,
		OrderByComparator<SearchFilter> orderByComparator) {
		List<SearchFilter> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last search filter in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching search filter
	 * @throws NoSuchSearchFilterException if a matching search filter could not be found
	 */
	@Override
	public SearchFilter findByUserId_Last(long userId,
		OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException {
		SearchFilter searchFilter = fetchByUserId_Last(userId, orderByComparator);

		if (searchFilter != null) {
			return searchFilter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSearchFilterException(msg.toString());
	}

	/**
	 * Returns the last search filter in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching search filter, or <code>null</code> if a matching search filter could not be found
	 */
	@Override
	public SearchFilter fetchByUserId_Last(long userId,
		OrderByComparator<SearchFilter> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<SearchFilter> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the search filters before and after the current search filter in the ordered set where userId = &#63;.
	 *
	 * @param searchFilterId the primary key of the current search filter
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next search filter
	 * @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	 */
	@Override
	public SearchFilter[] findByUserId_PrevAndNext(long searchFilterId,
		long userId, OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException {
		SearchFilter searchFilter = findByPrimaryKey(searchFilterId);

		Session session = null;

		try {
			session = openSession();

			SearchFilter[] array = new SearchFilterImpl[3];

			array[0] = getByUserId_PrevAndNext(session, searchFilter, userId,
					orderByComparator, true);

			array[1] = searchFilter;

			array[2] = getByUserId_PrevAndNext(session, searchFilter, userId,
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

	protected SearchFilter getByUserId_PrevAndNext(Session session,
		SearchFilter searchFilter, long userId,
		OrderByComparator<SearchFilter> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SEARCHFILTER_WHERE);

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
			query.append(SearchFilterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(searchFilter);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SearchFilter> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the search filters where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (SearchFilter searchFilter : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(searchFilter);
		}
	}

	/**
	 * Returns the number of search filters where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching search filters
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SEARCHFILTER_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "searchFilter.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C = new FinderPath(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterModelImpl.FINDER_CACHE_ENABLED, SearchFilterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C = new FinderPath(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterModelImpl.FINDER_CACHE_ENABLED, SearchFilterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			SearchFilterModelImpl.USERID_COLUMN_BITMASK |
			SearchFilterModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SearchFilterModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_C = new FinderPath(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the search filters where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @return the matching search filters
	 */
	@Override
	public List<SearchFilter> findByU_C(long userId, long classNameId) {
		return findByU_C(userId, classNameId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the search filters where userId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of search filters
	 * @param end the upper bound of the range of search filters (not inclusive)
	 * @return the range of matching search filters
	 */
	@Override
	public List<SearchFilter> findByU_C(long userId, long classNameId,
		int start, int end) {
		return findByU_C(userId, classNameId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the search filters where userId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of search filters
	 * @param end the upper bound of the range of search filters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching search filters
	 */
	@Override
	public List<SearchFilter> findByU_C(long userId, long classNameId,
		int start, int end, OrderByComparator<SearchFilter> orderByComparator) {
		return findByU_C(userId, classNameId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the search filters where userId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of search filters
	 * @param end the upper bound of the range of search filters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching search filters
	 */
	@Override
	public List<SearchFilter> findByU_C(long userId, long classNameId,
		int start, int end, OrderByComparator<SearchFilter> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C;
			finderArgs = new Object[] { userId, classNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C;
			finderArgs = new Object[] {
					userId, classNameId,
					
					start, end, orderByComparator
				};
		}

		List<SearchFilter> list = null;

		if (retrieveFromCache) {
			list = (List<SearchFilter>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SearchFilter searchFilter : list) {
					if ((userId != searchFilter.getUserId()) ||
							(classNameId != searchFilter.getClassNameId())) {
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

			query.append(_SQL_SELECT_SEARCHFILTER_WHERE);

			query.append(_FINDER_COLUMN_U_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_CLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SearchFilterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				if (!pagination) {
					list = (List<SearchFilter>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SearchFilter>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching search filter
	 * @throws NoSuchSearchFilterException if a matching search filter could not be found
	 */
	@Override
	public SearchFilter findByU_C_First(long userId, long classNameId,
		OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException {
		SearchFilter searchFilter = fetchByU_C_First(userId, classNameId,
				orderByComparator);

		if (searchFilter != null) {
			return searchFilter;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSearchFilterException(msg.toString());
	}

	/**
	 * Returns the first search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching search filter, or <code>null</code> if a matching search filter could not be found
	 */
	@Override
	public SearchFilter fetchByU_C_First(long userId, long classNameId,
		OrderByComparator<SearchFilter> orderByComparator) {
		List<SearchFilter> list = findByU_C(userId, classNameId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching search filter
	 * @throws NoSuchSearchFilterException if a matching search filter could not be found
	 */
	@Override
	public SearchFilter findByU_C_Last(long userId, long classNameId,
		OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException {
		SearchFilter searchFilter = fetchByU_C_Last(userId, classNameId,
				orderByComparator);

		if (searchFilter != null) {
			return searchFilter;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSearchFilterException(msg.toString());
	}

	/**
	 * Returns the last search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching search filter, or <code>null</code> if a matching search filter could not be found
	 */
	@Override
	public SearchFilter fetchByU_C_Last(long userId, long classNameId,
		OrderByComparator<SearchFilter> orderByComparator) {
		int count = countByU_C(userId, classNameId);

		if (count == 0) {
			return null;
		}

		List<SearchFilter> list = findByU_C(userId, classNameId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the search filters before and after the current search filter in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param searchFilterId the primary key of the current search filter
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next search filter
	 * @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	 */
	@Override
	public SearchFilter[] findByU_C_PrevAndNext(long searchFilterId,
		long userId, long classNameId,
		OrderByComparator<SearchFilter> orderByComparator)
		throws NoSuchSearchFilterException {
		SearchFilter searchFilter = findByPrimaryKey(searchFilterId);

		Session session = null;

		try {
			session = openSession();

			SearchFilter[] array = new SearchFilterImpl[3];

			array[0] = getByU_C_PrevAndNext(session, searchFilter, userId,
					classNameId, orderByComparator, true);

			array[1] = searchFilter;

			array[2] = getByU_C_PrevAndNext(session, searchFilter, userId,
					classNameId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SearchFilter getByU_C_PrevAndNext(Session session,
		SearchFilter searchFilter, long userId, long classNameId,
		OrderByComparator<SearchFilter> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SEARCHFILTER_WHERE);

		query.append(_FINDER_COLUMN_U_C_USERID_2);

		query.append(_FINDER_COLUMN_U_C_CLASSNAMEID_2);

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
			query.append(SearchFilterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(classNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(searchFilter);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SearchFilter> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the search filters where userId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 */
	@Override
	public void removeByU_C(long userId, long classNameId) {
		for (SearchFilter searchFilter : findByU_C(userId, classNameId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(searchFilter);
		}
	}

	/**
	 * Returns the number of search filters where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @return the number of matching search filters
	 */
	@Override
	public int countByU_C(long userId, long classNameId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_C;

		Object[] finderArgs = new Object[] { userId, classNameId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SEARCHFILTER_WHERE);

			query.append(_FINDER_COLUMN_U_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

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

	private static final String _FINDER_COLUMN_U_C_USERID_2 = "searchFilter.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_CLASSNAMEID_2 = "searchFilter.classNameId = ?";

	public SearchFilterPersistenceImpl() {
		setModelClass(SearchFilter.class);
	}

	/**
	 * Caches the search filter in the entity cache if it is enabled.
	 *
	 * @param searchFilter the search filter
	 */
	@Override
	public void cacheResult(SearchFilter searchFilter) {
		entityCache.putResult(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterImpl.class, searchFilter.getPrimaryKey(), searchFilter);

		searchFilter.resetOriginalValues();
	}

	/**
	 * Caches the search filters in the entity cache if it is enabled.
	 *
	 * @param searchFilters the search filters
	 */
	@Override
	public void cacheResult(List<SearchFilter> searchFilters) {
		for (SearchFilter searchFilter : searchFilters) {
			if (entityCache.getResult(
						SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
						SearchFilterImpl.class, searchFilter.getPrimaryKey()) == null) {
				cacheResult(searchFilter);
			}
			else {
				searchFilter.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all search filters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SearchFilterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the search filter.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SearchFilter searchFilter) {
		entityCache.removeResult(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterImpl.class, searchFilter.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SearchFilter> searchFilters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SearchFilter searchFilter : searchFilters) {
			entityCache.removeResult(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
				SearchFilterImpl.class, searchFilter.getPrimaryKey());
		}
	}

	/**
	 * Creates a new search filter with the primary key. Does not add the search filter to the database.
	 *
	 * @param searchFilterId the primary key for the new search filter
	 * @return the new search filter
	 */
	@Override
	public SearchFilter create(long searchFilterId) {
		SearchFilter searchFilter = new SearchFilterImpl();

		searchFilter.setNew(true);
		searchFilter.setPrimaryKey(searchFilterId);

		return searchFilter;
	}

	/**
	 * Removes the search filter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param searchFilterId the primary key of the search filter
	 * @return the search filter that was removed
	 * @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	 */
	@Override
	public SearchFilter remove(long searchFilterId)
		throws NoSuchSearchFilterException {
		return remove((Serializable)searchFilterId);
	}

	/**
	 * Removes the search filter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the search filter
	 * @return the search filter that was removed
	 * @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	 */
	@Override
	public SearchFilter remove(Serializable primaryKey)
		throws NoSuchSearchFilterException {
		Session session = null;

		try {
			session = openSession();

			SearchFilter searchFilter = (SearchFilter)session.get(SearchFilterImpl.class,
					primaryKey);

			if (searchFilter == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSearchFilterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(searchFilter);
		}
		catch (NoSuchSearchFilterException nsee) {
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
	protected SearchFilter removeImpl(SearchFilter searchFilter) {
		searchFilter = toUnwrappedModel(searchFilter);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(searchFilter)) {
				searchFilter = (SearchFilter)session.get(SearchFilterImpl.class,
						searchFilter.getPrimaryKeyObj());
			}

			if (searchFilter != null) {
				session.delete(searchFilter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (searchFilter != null) {
			clearCache(searchFilter);
		}

		return searchFilter;
	}

	@Override
	public SearchFilter updateImpl(SearchFilter searchFilter) {
		searchFilter = toUnwrappedModel(searchFilter);

		boolean isNew = searchFilter.isNew();

		SearchFilterModelImpl searchFilterModelImpl = (SearchFilterModelImpl)searchFilter;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (searchFilter.getCreateDate() == null)) {
			if (serviceContext == null) {
				searchFilter.setCreateDate(now);
			}
			else {
				searchFilter.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!searchFilterModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				searchFilter.setModifiedDate(now);
			}
			else {
				searchFilter.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (searchFilter.isNew()) {
				session.save(searchFilter);

				searchFilter.setNew(false);
			}
			else {
				searchFilter = (SearchFilter)session.merge(searchFilter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SearchFilterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { searchFilterModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
				args);

			args = new Object[] {
					searchFilterModelImpl.getUserId(),
					searchFilterModelImpl.getClassNameId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((searchFilterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						searchFilterModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { searchFilterModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((searchFilterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						searchFilterModelImpl.getOriginalUserId(),
						searchFilterModelImpl.getOriginalClassNameId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C,
					args);

				args = new Object[] {
						searchFilterModelImpl.getUserId(),
						searchFilterModelImpl.getClassNameId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C,
					args);
			}
		}

		entityCache.putResult(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
			SearchFilterImpl.class, searchFilter.getPrimaryKey(), searchFilter,
			false);

		searchFilter.resetOriginalValues();

		return searchFilter;
	}

	protected SearchFilter toUnwrappedModel(SearchFilter searchFilter) {
		if (searchFilter instanceof SearchFilterImpl) {
			return searchFilter;
		}

		SearchFilterImpl searchFilterImpl = new SearchFilterImpl();

		searchFilterImpl.setNew(searchFilter.isNew());
		searchFilterImpl.setPrimaryKey(searchFilter.getPrimaryKey());

		searchFilterImpl.setSearchFilterId(searchFilter.getSearchFilterId());
		searchFilterImpl.setUserId(searchFilter.getUserId());
		searchFilterImpl.setUserName(searchFilter.getUserName());
		searchFilterImpl.setCreateDate(searchFilter.getCreateDate());
		searchFilterImpl.setModifiedDate(searchFilter.getModifiedDate());
		searchFilterImpl.setClassNameId(searchFilter.getClassNameId());
		searchFilterImpl.setName(searchFilter.getName());
		searchFilterImpl.setFilter(searchFilter.getFilter());
		searchFilterImpl.setVisibility(searchFilter.getVisibility());

		return searchFilterImpl;
	}

	/**
	 * Returns the search filter with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the search filter
	 * @return the search filter
	 * @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	 */
	@Override
	public SearchFilter findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSearchFilterException {
		SearchFilter searchFilter = fetchByPrimaryKey(primaryKey);

		if (searchFilter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSearchFilterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return searchFilter;
	}

	/**
	 * Returns the search filter with the primary key or throws a {@link NoSuchSearchFilterException} if it could not be found.
	 *
	 * @param searchFilterId the primary key of the search filter
	 * @return the search filter
	 * @throws NoSuchSearchFilterException if a search filter with the primary key could not be found
	 */
	@Override
	public SearchFilter findByPrimaryKey(long searchFilterId)
		throws NoSuchSearchFilterException {
		return findByPrimaryKey((Serializable)searchFilterId);
	}

	/**
	 * Returns the search filter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the search filter
	 * @return the search filter, or <code>null</code> if a search filter with the primary key could not be found
	 */
	@Override
	public SearchFilter fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
				SearchFilterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SearchFilter searchFilter = (SearchFilter)serializable;

		if (searchFilter == null) {
			Session session = null;

			try {
				session = openSession();

				searchFilter = (SearchFilter)session.get(SearchFilterImpl.class,
						primaryKey);

				if (searchFilter != null) {
					cacheResult(searchFilter);
				}
				else {
					entityCache.putResult(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
						SearchFilterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
					SearchFilterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return searchFilter;
	}

	/**
	 * Returns the search filter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param searchFilterId the primary key of the search filter
	 * @return the search filter, or <code>null</code> if a search filter with the primary key could not be found
	 */
	@Override
	public SearchFilter fetchByPrimaryKey(long searchFilterId) {
		return fetchByPrimaryKey((Serializable)searchFilterId);
	}

	@Override
	public Map<Serializable, SearchFilter> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SearchFilter> map = new HashMap<Serializable, SearchFilter>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SearchFilter searchFilter = fetchByPrimaryKey(primaryKey);

			if (searchFilter != null) {
				map.put(primaryKey, searchFilter);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
					SearchFilterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SearchFilter)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SEARCHFILTER_WHERE_PKS_IN);

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

			for (SearchFilter searchFilter : (List<SearchFilter>)q.list()) {
				map.put(searchFilter.getPrimaryKeyObj(), searchFilter);

				cacheResult(searchFilter);

				uncachedPrimaryKeys.remove(searchFilter.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SearchFilterModelImpl.ENTITY_CACHE_ENABLED,
					SearchFilterImpl.class, primaryKey, nullModel);
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
	 * Returns all the search filters.
	 *
	 * @return the search filters
	 */
	@Override
	public List<SearchFilter> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the search filters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of search filters
	 * @param end the upper bound of the range of search filters (not inclusive)
	 * @return the range of search filters
	 */
	@Override
	public List<SearchFilter> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the search filters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of search filters
	 * @param end the upper bound of the range of search filters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of search filters
	 */
	@Override
	public List<SearchFilter> findAll(int start, int end,
		OrderByComparator<SearchFilter> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the search filters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of search filters
	 * @param end the upper bound of the range of search filters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of search filters
	 */
	@Override
	public List<SearchFilter> findAll(int start, int end,
		OrderByComparator<SearchFilter> orderByComparator,
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

		List<SearchFilter> list = null;

		if (retrieveFromCache) {
			list = (List<SearchFilter>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SEARCHFILTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SEARCHFILTER;

				if (pagination) {
					sql = sql.concat(SearchFilterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SearchFilter>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SearchFilter>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the search filters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SearchFilter searchFilter : findAll()) {
			remove(searchFilter);
		}
	}

	/**
	 * Returns the number of search filters.
	 *
	 * @return the number of search filters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SEARCHFILTER);

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
		return SearchFilterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the search filter persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SearchFilterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SEARCHFILTER = "SELECT searchFilter FROM SearchFilter searchFilter";
	private static final String _SQL_SELECT_SEARCHFILTER_WHERE_PKS_IN = "SELECT searchFilter FROM SearchFilter searchFilter WHERE searchFilterId IN (";
	private static final String _SQL_SELECT_SEARCHFILTER_WHERE = "SELECT searchFilter FROM SearchFilter searchFilter WHERE ";
	private static final String _SQL_COUNT_SEARCHFILTER = "SELECT COUNT(searchFilter) FROM SearchFilter searchFilter";
	private static final String _SQL_COUNT_SEARCHFILTER_WHERE = "SELECT COUNT(searchFilter) FROM SearchFilter searchFilter WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "searchFilter.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SearchFilter exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SearchFilter exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SearchFilterPersistenceImpl.class);
}
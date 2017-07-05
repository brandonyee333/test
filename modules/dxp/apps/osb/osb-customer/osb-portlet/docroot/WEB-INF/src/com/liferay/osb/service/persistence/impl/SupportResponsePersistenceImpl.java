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

import com.liferay.osb.exception.NoSuchSupportResponseException;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.impl.SupportResponseImpl;
import com.liferay.osb.model.impl.SupportResponseModelImpl;
import com.liferay.osb.service.persistence.SupportResponsePersistence;

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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the support response service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportResponsePersistence
 * @see com.liferay.osb.service.persistence.SupportResponseUtil
 * @generated
 */
@ProviderType
public class SupportResponsePersistenceImpl extends BasePersistenceImpl<SupportResponse>
	implements SupportResponsePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SupportResponseUtil} to access the support response persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SupportResponseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED,
			SupportResponseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED,
			SupportResponseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED,
			SupportResponseImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			SupportResponseModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the support response where name = &#63; or throws a {@link NoSuchSupportResponseException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching support response
	 * @throws NoSuchSupportResponseException if a matching support response could not be found
	 */
	@Override
	public SupportResponse findByName(String name)
		throws NoSuchSupportResponseException {
		SupportResponse supportResponse = fetchByName(name);

		if (supportResponse == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSupportResponseException(msg.toString());
		}

		return supportResponse;
	}

	/**
	 * Returns the support response where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching support response, or <code>null</code> if a matching support response could not be found
	 */
	@Override
	public SupportResponse fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the support response where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching support response, or <code>null</code> if a matching support response could not be found
	 */
	@Override
	public SupportResponse fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof SupportResponse) {
			SupportResponse supportResponse = (SupportResponse)result;

			if (!Objects.equals(name, supportResponse.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SUPPORTRESPONSE_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				List<SupportResponse> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					SupportResponse supportResponse = list.get(0);

					result = supportResponse;

					cacheResult(supportResponse);

					if ((supportResponse.getName() == null) ||
							!supportResponse.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, supportResponse);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, finderArgs);

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
			return (SupportResponse)result;
		}
	}

	/**
	 * Removes the support response where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the support response that was removed
	 */
	@Override
	public SupportResponse removeByName(String name)
		throws NoSuchSupportResponseException {
		SupportResponse supportResponse = findByName(name);

		return remove(supportResponse);
	}

	/**
	 * Returns the number of support responses where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching support responses
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTRESPONSE_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "supportResponse.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "supportResponse.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(supportResponse.name IS NULL OR supportResponse.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLEVEL =
		new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED,
			SupportResponseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySupportLevel",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLEVEL =
		new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED,
			SupportResponseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySupportLevel",
			new String[] { Integer.class.getName() },
			SupportResponseModelImpl.SUPPORTLEVEL_COLUMN_BITMASK |
			SupportResponseModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTLEVEL = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySupportLevel",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the support responses where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @return the matching support responses
	 */
	@Override
	public List<SupportResponse> findBySupportLevel(int supportLevel) {
		return findBySupportLevel(supportLevel, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support responses where supportLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportLevel the support level
	 * @param start the lower bound of the range of support responses
	 * @param end the upper bound of the range of support responses (not inclusive)
	 * @return the range of matching support responses
	 */
	@Override
	public List<SupportResponse> findBySupportLevel(int supportLevel,
		int start, int end) {
		return findBySupportLevel(supportLevel, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support responses where supportLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportLevel the support level
	 * @param start the lower bound of the range of support responses
	 * @param end the upper bound of the range of support responses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support responses
	 */
	@Override
	public List<SupportResponse> findBySupportLevel(int supportLevel,
		int start, int end, OrderByComparator<SupportResponse> orderByComparator) {
		return findBySupportLevel(supportLevel, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the support responses where supportLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportLevel the support level
	 * @param start the lower bound of the range of support responses
	 * @param end the upper bound of the range of support responses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching support responses
	 */
	@Override
	public List<SupportResponse> findBySupportLevel(int supportLevel,
		int start, int end,
		OrderByComparator<SupportResponse> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLEVEL;
			finderArgs = new Object[] { supportLevel };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLEVEL;
			finderArgs = new Object[] {
					supportLevel,
					
					start, end, orderByComparator
				};
		}

		List<SupportResponse> list = null;

		if (retrieveFromCache) {
			list = (List<SupportResponse>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SupportResponse supportResponse : list) {
					if ((supportLevel != supportResponse.getSupportLevel())) {
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

			query.append(_SQL_SELECT_SUPPORTRESPONSE_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLEVEL_SUPPORTLEVEL_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SupportResponseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportLevel);

				if (!pagination) {
					list = (List<SupportResponse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportResponse>)QueryUtil.list(q,
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
	 * Returns the first support response in the ordered set where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support response
	 * @throws NoSuchSupportResponseException if a matching support response could not be found
	 */
	@Override
	public SupportResponse findBySupportLevel_First(int supportLevel,
		OrderByComparator<SupportResponse> orderByComparator)
		throws NoSuchSupportResponseException {
		SupportResponse supportResponse = fetchBySupportLevel_First(supportLevel,
				orderByComparator);

		if (supportResponse != null) {
			return supportResponse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLevel=");
		msg.append(supportLevel);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportResponseException(msg.toString());
	}

	/**
	 * Returns the first support response in the ordered set where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support response, or <code>null</code> if a matching support response could not be found
	 */
	@Override
	public SupportResponse fetchBySupportLevel_First(int supportLevel,
		OrderByComparator<SupportResponse> orderByComparator) {
		List<SupportResponse> list = findBySupportLevel(supportLevel, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support response in the ordered set where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support response
	 * @throws NoSuchSupportResponseException if a matching support response could not be found
	 */
	@Override
	public SupportResponse findBySupportLevel_Last(int supportLevel,
		OrderByComparator<SupportResponse> orderByComparator)
		throws NoSuchSupportResponseException {
		SupportResponse supportResponse = fetchBySupportLevel_Last(supportLevel,
				orderByComparator);

		if (supportResponse != null) {
			return supportResponse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLevel=");
		msg.append(supportLevel);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportResponseException(msg.toString());
	}

	/**
	 * Returns the last support response in the ordered set where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support response, or <code>null</code> if a matching support response could not be found
	 */
	@Override
	public SupportResponse fetchBySupportLevel_Last(int supportLevel,
		OrderByComparator<SupportResponse> orderByComparator) {
		int count = countBySupportLevel(supportLevel);

		if (count == 0) {
			return null;
		}

		List<SupportResponse> list = findBySupportLevel(supportLevel,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support responses before and after the current support response in the ordered set where supportLevel = &#63;.
	 *
	 * @param supportResponseId the primary key of the current support response
	 * @param supportLevel the support level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support response
	 * @throws NoSuchSupportResponseException if a support response with the primary key could not be found
	 */
	@Override
	public SupportResponse[] findBySupportLevel_PrevAndNext(
		long supportResponseId, int supportLevel,
		OrderByComparator<SupportResponse> orderByComparator)
		throws NoSuchSupportResponseException {
		SupportResponse supportResponse = findByPrimaryKey(supportResponseId);

		Session session = null;

		try {
			session = openSession();

			SupportResponse[] array = new SupportResponseImpl[3];

			array[0] = getBySupportLevel_PrevAndNext(session, supportResponse,
					supportLevel, orderByComparator, true);

			array[1] = supportResponse;

			array[2] = getBySupportLevel_PrevAndNext(session, supportResponse,
					supportLevel, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportResponse getBySupportLevel_PrevAndNext(Session session,
		SupportResponse supportResponse, int supportLevel,
		OrderByComparator<SupportResponse> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTRESPONSE_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTLEVEL_SUPPORTLEVEL_2);

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
			query.append(SupportResponseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportLevel);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportResponse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportResponse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support responses where supportLevel = &#63; from the database.
	 *
	 * @param supportLevel the support level
	 */
	@Override
	public void removeBySupportLevel(int supportLevel) {
		for (SupportResponse supportResponse : findBySupportLevel(
				supportLevel, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(supportResponse);
		}
	}

	/**
	 * Returns the number of support responses where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @return the number of matching support responses
	 */
	@Override
	public int countBySupportLevel(int supportLevel) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUPPORTLEVEL;

		Object[] finderArgs = new Object[] { supportLevel };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTRESPONSE_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLEVEL_SUPPORTLEVEL_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportLevel);

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

	private static final String _FINDER_COLUMN_SUPPORTLEVEL_SUPPORTLEVEL_2 = "supportResponse.supportLevel = ?";

	public SupportResponsePersistenceImpl() {
		setModelClass(SupportResponse.class);
	}

	/**
	 * Caches the support response in the entity cache if it is enabled.
	 *
	 * @param supportResponse the support response
	 */
	@Override
	public void cacheResult(SupportResponse supportResponse) {
		entityCache.putResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseImpl.class, supportResponse.getPrimaryKey(),
			supportResponse);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { supportResponse.getName() }, supportResponse);

		supportResponse.resetOriginalValues();
	}

	/**
	 * Caches the support responses in the entity cache if it is enabled.
	 *
	 * @param supportResponses the support responses
	 */
	@Override
	public void cacheResult(List<SupportResponse> supportResponses) {
		for (SupportResponse supportResponse : supportResponses) {
			if (entityCache.getResult(
						SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
						SupportResponseImpl.class,
						supportResponse.getPrimaryKey()) == null) {
				cacheResult(supportResponse);
			}
			else {
				supportResponse.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support responses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SupportResponseImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support response.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportResponse supportResponse) {
		entityCache.removeResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseImpl.class, supportResponse.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SupportResponseModelImpl)supportResponse, true);
	}

	@Override
	public void clearCache(List<SupportResponse> supportResponses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportResponse supportResponse : supportResponses) {
			entityCache.removeResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
				SupportResponseImpl.class, supportResponse.getPrimaryKey());

			clearUniqueFindersCache((SupportResponseModelImpl)supportResponse,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		SupportResponseModelImpl supportResponseModelImpl) {
		Object[] args = new Object[] { supportResponseModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
			supportResponseModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SupportResponseModelImpl supportResponseModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { supportResponseModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((supportResponseModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					supportResponseModelImpl.getOriginalName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new support response with the primary key. Does not add the support response to the database.
	 *
	 * @param supportResponseId the primary key for the new support response
	 * @return the new support response
	 */
	@Override
	public SupportResponse create(long supportResponseId) {
		SupportResponse supportResponse = new SupportResponseImpl();

		supportResponse.setNew(true);
		supportResponse.setPrimaryKey(supportResponseId);

		return supportResponse;
	}

	/**
	 * Removes the support response with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportResponseId the primary key of the support response
	 * @return the support response that was removed
	 * @throws NoSuchSupportResponseException if a support response with the primary key could not be found
	 */
	@Override
	public SupportResponse remove(long supportResponseId)
		throws NoSuchSupportResponseException {
		return remove((Serializable)supportResponseId);
	}

	/**
	 * Removes the support response with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support response
	 * @return the support response that was removed
	 * @throws NoSuchSupportResponseException if a support response with the primary key could not be found
	 */
	@Override
	public SupportResponse remove(Serializable primaryKey)
		throws NoSuchSupportResponseException {
		Session session = null;

		try {
			session = openSession();

			SupportResponse supportResponse = (SupportResponse)session.get(SupportResponseImpl.class,
					primaryKey);

			if (supportResponse == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportResponseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(supportResponse);
		}
		catch (NoSuchSupportResponseException nsee) {
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
	protected SupportResponse removeImpl(SupportResponse supportResponse) {
		supportResponse = toUnwrappedModel(supportResponse);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(supportResponse)) {
				supportResponse = (SupportResponse)session.get(SupportResponseImpl.class,
						supportResponse.getPrimaryKeyObj());
			}

			if (supportResponse != null) {
				session.delete(supportResponse);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (supportResponse != null) {
			clearCache(supportResponse);
		}

		return supportResponse;
	}

	@Override
	public SupportResponse updateImpl(SupportResponse supportResponse) {
		supportResponse = toUnwrappedModel(supportResponse);

		boolean isNew = supportResponse.isNew();

		SupportResponseModelImpl supportResponseModelImpl = (SupportResponseModelImpl)supportResponse;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (supportResponse.getCreateDate() == null)) {
			if (serviceContext == null) {
				supportResponse.setCreateDate(now);
			}
			else {
				supportResponse.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!supportResponseModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				supportResponse.setModifiedDate(now);
			}
			else {
				supportResponse.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (supportResponse.isNew()) {
				session.save(supportResponse);

				supportResponse.setNew(false);
			}
			else {
				supportResponse = (SupportResponse)session.merge(supportResponse);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SupportResponseModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					supportResponseModelImpl.getSupportLevel()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLEVEL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLEVEL,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((supportResponseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLEVEL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						supportResponseModelImpl.getOriginalSupportLevel()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLEVEL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLEVEL,
					args);

				args = new Object[] { supportResponseModelImpl.getSupportLevel() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLEVEL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLEVEL,
					args);
			}
		}

		entityCache.putResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseImpl.class, supportResponse.getPrimaryKey(),
			supportResponse, false);

		clearUniqueFindersCache(supportResponseModelImpl, false);
		cacheUniqueFindersCache(supportResponseModelImpl);

		supportResponse.resetOriginalValues();

		return supportResponse;
	}

	protected SupportResponse toUnwrappedModel(SupportResponse supportResponse) {
		if (supportResponse instanceof SupportResponseImpl) {
			return supportResponse;
		}

		SupportResponseImpl supportResponseImpl = new SupportResponseImpl();

		supportResponseImpl.setNew(supportResponse.isNew());
		supportResponseImpl.setPrimaryKey(supportResponse.getPrimaryKey());

		supportResponseImpl.setSupportResponseId(supportResponse.getSupportResponseId());
		supportResponseImpl.setUserId(supportResponse.getUserId());
		supportResponseImpl.setUserName(supportResponse.getUserName());
		supportResponseImpl.setCreateDate(supportResponse.getCreateDate());
		supportResponseImpl.setModifiedDate(supportResponse.getModifiedDate());
		supportResponseImpl.setName(supportResponse.getName());
		supportResponseImpl.setSupportLevel(supportResponse.getSupportLevel());
		supportResponseImpl.setSeverity1Response(supportResponse.getSeverity1Response());
		supportResponseImpl.setSeverity1Resolution(supportResponse.getSeverity1Resolution());
		supportResponseImpl.setSeverity2Response(supportResponse.getSeverity2Response());
		supportResponseImpl.setSeverity2Resolution(supportResponse.getSeverity2Resolution());
		supportResponseImpl.setSeverity3Response(supportResponse.getSeverity3Response());
		supportResponseImpl.setSeverity3Resolution(supportResponse.getSeverity3Resolution());

		return supportResponseImpl;
	}

	/**
	 * Returns the support response with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support response
	 * @return the support response
	 * @throws NoSuchSupportResponseException if a support response with the primary key could not be found
	 */
	@Override
	public SupportResponse findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSupportResponseException {
		SupportResponse supportResponse = fetchByPrimaryKey(primaryKey);

		if (supportResponse == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSupportResponseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return supportResponse;
	}

	/**
	 * Returns the support response with the primary key or throws a {@link NoSuchSupportResponseException} if it could not be found.
	 *
	 * @param supportResponseId the primary key of the support response
	 * @return the support response
	 * @throws NoSuchSupportResponseException if a support response with the primary key could not be found
	 */
	@Override
	public SupportResponse findByPrimaryKey(long supportResponseId)
		throws NoSuchSupportResponseException {
		return findByPrimaryKey((Serializable)supportResponseId);
	}

	/**
	 * Returns the support response with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support response
	 * @return the support response, or <code>null</code> if a support response with the primary key could not be found
	 */
	@Override
	public SupportResponse fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
				SupportResponseImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SupportResponse supportResponse = (SupportResponse)serializable;

		if (supportResponse == null) {
			Session session = null;

			try {
				session = openSession();

				supportResponse = (SupportResponse)session.get(SupportResponseImpl.class,
						primaryKey);

				if (supportResponse != null) {
					cacheResult(supportResponse);
				}
				else {
					entityCache.putResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
						SupportResponseImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
					SupportResponseImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return supportResponse;
	}

	/**
	 * Returns the support response with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportResponseId the primary key of the support response
	 * @return the support response, or <code>null</code> if a support response with the primary key could not be found
	 */
	@Override
	public SupportResponse fetchByPrimaryKey(long supportResponseId) {
		return fetchByPrimaryKey((Serializable)supportResponseId);
	}

	@Override
	public Map<Serializable, SupportResponse> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SupportResponse> map = new HashMap<Serializable, SupportResponse>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SupportResponse supportResponse = fetchByPrimaryKey(primaryKey);

			if (supportResponse != null) {
				map.put(primaryKey, supportResponse);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
					SupportResponseImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SupportResponse)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SUPPORTRESPONSE_WHERE_PKS_IN);

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

			for (SupportResponse supportResponse : (List<SupportResponse>)q.list()) {
				map.put(supportResponse.getPrimaryKeyObj(), supportResponse);

				cacheResult(supportResponse);

				uncachedPrimaryKeys.remove(supportResponse.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
					SupportResponseImpl.class, primaryKey, nullModel);
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
	 * Returns all the support responses.
	 *
	 * @return the support responses
	 */
	@Override
	public List<SupportResponse> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support responses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support responses
	 * @param end the upper bound of the range of support responses (not inclusive)
	 * @return the range of support responses
	 */
	@Override
	public List<SupportResponse> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support responses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support responses
	 * @param end the upper bound of the range of support responses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support responses
	 */
	@Override
	public List<SupportResponse> findAll(int start, int end,
		OrderByComparator<SupportResponse> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support responses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support responses
	 * @param end the upper bound of the range of support responses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of support responses
	 */
	@Override
	public List<SupportResponse> findAll(int start, int end,
		OrderByComparator<SupportResponse> orderByComparator,
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

		List<SupportResponse> list = null;

		if (retrieveFromCache) {
			list = (List<SupportResponse>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SUPPORTRESPONSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTRESPONSE;

				if (pagination) {
					sql = sql.concat(SupportResponseModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SupportResponse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportResponse>)QueryUtil.list(q,
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
	 * Removes all the support responses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SupportResponse supportResponse : findAll()) {
			remove(supportResponse);
		}
	}

	/**
	 * Returns the number of support responses.
	 *
	 * @return the number of support responses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTRESPONSE);

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
		return SupportResponseModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the support response persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SupportResponseImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SUPPORTRESPONSE = "SELECT supportResponse FROM SupportResponse supportResponse";
	private static final String _SQL_SELECT_SUPPORTRESPONSE_WHERE_PKS_IN = "SELECT supportResponse FROM SupportResponse supportResponse WHERE supportResponseId IN (";
	private static final String _SQL_SELECT_SUPPORTRESPONSE_WHERE = "SELECT supportResponse FROM SupportResponse supportResponse WHERE ";
	private static final String _SQL_COUNT_SUPPORTRESPONSE = "SELECT COUNT(supportResponse) FROM SupportResponse supportResponse";
	private static final String _SQL_COUNT_SUPPORTRESPONSE_WHERE = "SELECT COUNT(supportResponse) FROM SupportResponse supportResponse WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportResponse.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportResponse exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportResponse exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SupportResponsePersistenceImpl.class);
}
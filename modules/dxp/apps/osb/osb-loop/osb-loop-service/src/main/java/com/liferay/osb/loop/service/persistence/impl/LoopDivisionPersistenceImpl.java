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

package com.liferay.osb.loop.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopDivisionException;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.impl.LoopDivisionImpl;
import com.liferay.osb.loop.model.impl.LoopDivisionModelImpl;
import com.liferay.osb.loop.service.persistence.LoopDivisionPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the loop division service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopDivisionPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopDivisionUtil
 * @generated
 */
@ProviderType
public class LoopDivisionPersistenceImpl extends BasePersistenceImpl<LoopDivision>
	implements LoopDivisionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopDivisionUtil} to access the loop division persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopDivisionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionModelImpl.FINDER_CACHE_ENABLED, LoopDivisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionModelImpl.FINDER_CACHE_ENABLED, LoopDivisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_ORGANIZATIONID = new FinderPath(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionModelImpl.FINDER_CACHE_ENABLED, LoopDivisionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByOrganizationId",
			new String[] { Long.class.getName() },
			LoopDivisionModelImpl.ORGANIZATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORGANIZATIONID = new FinderPath(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOrganizationId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the loop division where organizationId = &#63; or throws a {@link NoSuchLoopDivisionException} if it could not be found.
	 *
	 * @param organizationId the organization ID
	 * @return the matching loop division
	 * @throws NoSuchLoopDivisionException if a matching loop division could not be found
	 */
	@Override
	public LoopDivision findByOrganizationId(long organizationId)
		throws NoSuchLoopDivisionException {
		LoopDivision loopDivision = fetchByOrganizationId(organizationId);

		if (loopDivision == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("organizationId=");
			msg.append(organizationId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopDivisionException(msg.toString());
		}

		return loopDivision;
	}

	/**
	 * Returns the loop division where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @return the matching loop division, or <code>null</code> if a matching loop division could not be found
	 */
	@Override
	public LoopDivision fetchByOrganizationId(long organizationId) {
		return fetchByOrganizationId(organizationId, true);
	}

	/**
	 * Returns the loop division where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop division, or <code>null</code> if a matching loop division could not be found
	 */
	@Override
	public LoopDivision fetchByOrganizationId(long organizationId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { organizationId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
					finderArgs, this);
		}

		if (result instanceof LoopDivision) {
			LoopDivision loopDivision = (LoopDivision)result;

			if ((organizationId != loopDivision.getOrganizationId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_LOOPDIVISION_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				List<LoopDivision> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
						finderArgs, list);
				}
				else {
					LoopDivision loopDivision = list.get(0);

					result = loopDivision;

					cacheResult(loopDivision);

					if ((loopDivision.getOrganizationId() != organizationId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
							finderArgs, loopDivision);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
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
			return (LoopDivision)result;
		}
	}

	/**
	 * Removes the loop division where organizationId = &#63; from the database.
	 *
	 * @param organizationId the organization ID
	 * @return the loop division that was removed
	 */
	@Override
	public LoopDivision removeByOrganizationId(long organizationId)
		throws NoSuchLoopDivisionException {
		LoopDivision loopDivision = findByOrganizationId(organizationId);

		return remove(loopDivision);
	}

	/**
	 * Returns the number of loop divisions where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @return the number of matching loop divisions
	 */
	@Override
	public int countByOrganizationId(long organizationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ORGANIZATIONID;

		Object[] finderArgs = new Object[] { organizationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LOOPDIVISION_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

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

	private static final String _FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2 = "loopDivision.organizationId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CI_T = new FinderPath(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionModelImpl.FINDER_CACHE_ENABLED, LoopDivisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCI_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CI_T = new FinderPath(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionModelImpl.FINDER_CACHE_ENABLED, LoopDivisionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCI_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			LoopDivisionModelImpl.COMPANYID_COLUMN_BITMASK |
			LoopDivisionModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CI_T = new FinderPath(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the loop divisions where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the matching loop divisions
	 */
	@Override
	public List<LoopDivision> findByCI_T(long companyId, int type) {
		return findByCI_T(companyId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop divisions where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of loop divisions
	 * @param end the upper bound of the range of loop divisions (not inclusive)
	 * @return the range of matching loop divisions
	 */
	@Override
	public List<LoopDivision> findByCI_T(long companyId, int type, int start,
		int end) {
		return findByCI_T(companyId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop divisions where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of loop divisions
	 * @param end the upper bound of the range of loop divisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching loop divisions
	 */
	@Override
	public List<LoopDivision> findByCI_T(long companyId, int type, int start,
		int end, OrderByComparator<LoopDivision> orderByComparator) {
		return findByCI_T(companyId, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop divisions where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of loop divisions
	 * @param end the upper bound of the range of loop divisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching loop divisions
	 */
	@Override
	public List<LoopDivision> findByCI_T(long companyId, int type, int start,
		int end, OrderByComparator<LoopDivision> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CI_T;
			finderArgs = new Object[] { companyId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CI_T;
			finderArgs = new Object[] {
					companyId, type,
					
					start, end, orderByComparator
				};
		}

		List<LoopDivision> list = null;

		if (retrieveFromCache) {
			list = (List<LoopDivision>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LoopDivision loopDivision : list) {
					if ((companyId != loopDivision.getCompanyId()) ||
							(type != loopDivision.getType())) {
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

			query.append(_SQL_SELECT_LOOPDIVISION_WHERE);

			query.append(_FINDER_COLUMN_CI_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_CI_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LoopDivisionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(type);

				if (!pagination) {
					list = (List<LoopDivision>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopDivision>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first loop division in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop division
	 * @throws NoSuchLoopDivisionException if a matching loop division could not be found
	 */
	@Override
	public LoopDivision findByCI_T_First(long companyId, int type,
		OrderByComparator<LoopDivision> orderByComparator)
		throws NoSuchLoopDivisionException {
		LoopDivision loopDivision = fetchByCI_T_First(companyId, type,
				orderByComparator);

		if (loopDivision != null) {
			return loopDivision;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchLoopDivisionException(msg.toString());
	}

	/**
	 * Returns the first loop division in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop division, or <code>null</code> if a matching loop division could not be found
	 */
	@Override
	public LoopDivision fetchByCI_T_First(long companyId, int type,
		OrderByComparator<LoopDivision> orderByComparator) {
		List<LoopDivision> list = findByCI_T(companyId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last loop division in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop division
	 * @throws NoSuchLoopDivisionException if a matching loop division could not be found
	 */
	@Override
	public LoopDivision findByCI_T_Last(long companyId, int type,
		OrderByComparator<LoopDivision> orderByComparator)
		throws NoSuchLoopDivisionException {
		LoopDivision loopDivision = fetchByCI_T_Last(companyId, type,
				orderByComparator);

		if (loopDivision != null) {
			return loopDivision;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchLoopDivisionException(msg.toString());
	}

	/**
	 * Returns the last loop division in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop division, or <code>null</code> if a matching loop division could not be found
	 */
	@Override
	public LoopDivision fetchByCI_T_Last(long companyId, int type,
		OrderByComparator<LoopDivision> orderByComparator) {
		int count = countByCI_T(companyId, type);

		if (count == 0) {
			return null;
		}

		List<LoopDivision> list = findByCI_T(companyId, type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the loop divisions before and after the current loop division in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param loopDivisionId the primary key of the current loop division
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next loop division
	 * @throws NoSuchLoopDivisionException if a loop division with the primary key could not be found
	 */
	@Override
	public LoopDivision[] findByCI_T_PrevAndNext(long loopDivisionId,
		long companyId, int type,
		OrderByComparator<LoopDivision> orderByComparator)
		throws NoSuchLoopDivisionException {
		LoopDivision loopDivision = findByPrimaryKey(loopDivisionId);

		Session session = null;

		try {
			session = openSession();

			LoopDivision[] array = new LoopDivisionImpl[3];

			array[0] = getByCI_T_PrevAndNext(session, loopDivision, companyId,
					type, orderByComparator, true);

			array[1] = loopDivision;

			array[2] = getByCI_T_PrevAndNext(session, loopDivision, companyId,
					type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LoopDivision getByCI_T_PrevAndNext(Session session,
		LoopDivision loopDivision, long companyId, int type,
		OrderByComparator<LoopDivision> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LOOPDIVISION_WHERE);

		query.append(_FINDER_COLUMN_CI_T_COMPANYID_2);

		query.append(_FINDER_COLUMN_CI_T_TYPE_2);

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
			query.append(LoopDivisionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(loopDivision);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LoopDivision> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the loop divisions where companyId = &#63; and type = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 */
	@Override
	public void removeByCI_T(long companyId, int type) {
		for (LoopDivision loopDivision : findByCI_T(companyId, type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(loopDivision);
		}
	}

	/**
	 * Returns the number of loop divisions where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the number of matching loop divisions
	 */
	@Override
	public int countByCI_T(long companyId, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CI_T;

		Object[] finderArgs = new Object[] { companyId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LOOPDIVISION_WHERE);

			query.append(_FINDER_COLUMN_CI_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_CI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_CI_T_COMPANYID_2 = "loopDivision.companyId = ? AND ";
	private static final String _FINDER_COLUMN_CI_T_TYPE_2 = "loopDivision.type = ?";

	public LoopDivisionPersistenceImpl() {
		setModelClass(LoopDivision.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the loop division in the entity cache if it is enabled.
	 *
	 * @param loopDivision the loop division
	 */
	@Override
	public void cacheResult(LoopDivision loopDivision) {
		entityCache.putResult(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionImpl.class, loopDivision.getPrimaryKey(), loopDivision);

		finderCache.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
			new Object[] { loopDivision.getOrganizationId() }, loopDivision);

		loopDivision.resetOriginalValues();
	}

	/**
	 * Caches the loop divisions in the entity cache if it is enabled.
	 *
	 * @param loopDivisions the loop divisions
	 */
	@Override
	public void cacheResult(List<LoopDivision> loopDivisions) {
		for (LoopDivision loopDivision : loopDivisions) {
			if (entityCache.getResult(
						LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
						LoopDivisionImpl.class, loopDivision.getPrimaryKey()) == null) {
				cacheResult(loopDivision);
			}
			else {
				loopDivision.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop divisions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopDivisionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop division.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopDivision loopDivision) {
		entityCache.removeResult(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionImpl.class, loopDivision.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LoopDivisionModelImpl)loopDivision, true);
	}

	@Override
	public void clearCache(List<LoopDivision> loopDivisions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopDivision loopDivision : loopDivisions) {
			entityCache.removeResult(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
				LoopDivisionImpl.class, loopDivision.getPrimaryKey());

			clearUniqueFindersCache((LoopDivisionModelImpl)loopDivision, true);
		}
	}

	protected void cacheUniqueFindersCache(
		LoopDivisionModelImpl loopDivisionModelImpl) {
		Object[] args = new Object[] { loopDivisionModelImpl.getOrganizationId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID, args,
			loopDivisionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LoopDivisionModelImpl loopDivisionModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					loopDivisionModelImpl.getOrganizationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID, args);
		}

		if ((loopDivisionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ORGANIZATIONID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loopDivisionModelImpl.getOriginalOrganizationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID, args);
		}
	}

	/**
	 * Creates a new loop division with the primary key. Does not add the loop division to the database.
	 *
	 * @param loopDivisionId the primary key for the new loop division
	 * @return the new loop division
	 */
	@Override
	public LoopDivision create(long loopDivisionId) {
		LoopDivision loopDivision = new LoopDivisionImpl();

		loopDivision.setNew(true);
		loopDivision.setPrimaryKey(loopDivisionId);

		loopDivision.setCompanyId(companyProvider.getCompanyId());

		return loopDivision;
	}

	/**
	 * Removes the loop division with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopDivisionId the primary key of the loop division
	 * @return the loop division that was removed
	 * @throws NoSuchLoopDivisionException if a loop division with the primary key could not be found
	 */
	@Override
	public LoopDivision remove(long loopDivisionId)
		throws NoSuchLoopDivisionException {
		return remove((Serializable)loopDivisionId);
	}

	/**
	 * Removes the loop division with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop division
	 * @return the loop division that was removed
	 * @throws NoSuchLoopDivisionException if a loop division with the primary key could not be found
	 */
	@Override
	public LoopDivision remove(Serializable primaryKey)
		throws NoSuchLoopDivisionException {
		Session session = null;

		try {
			session = openSession();

			LoopDivision loopDivision = (LoopDivision)session.get(LoopDivisionImpl.class,
					primaryKey);

			if (loopDivision == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopDivisionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopDivision);
		}
		catch (NoSuchLoopDivisionException nsee) {
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
	protected LoopDivision removeImpl(LoopDivision loopDivision) {
		loopDivision = toUnwrappedModel(loopDivision);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopDivision)) {
				loopDivision = (LoopDivision)session.get(LoopDivisionImpl.class,
						loopDivision.getPrimaryKeyObj());
			}

			if (loopDivision != null) {
				session.delete(loopDivision);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopDivision != null) {
			clearCache(loopDivision);
		}

		return loopDivision;
	}

	@Override
	public LoopDivision updateImpl(LoopDivision loopDivision) {
		loopDivision = toUnwrappedModel(loopDivision);

		boolean isNew = loopDivision.isNew();

		LoopDivisionModelImpl loopDivisionModelImpl = (LoopDivisionModelImpl)loopDivision;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (loopDivision.getCreateDate() == null)) {
			if (serviceContext == null) {
				loopDivision.setCreateDate(now);
			}
			else {
				loopDivision.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!loopDivisionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				loopDivision.setModifiedDate(now);
			}
			else {
				loopDivision.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (loopDivision.isNew()) {
				session.save(loopDivision);

				loopDivision.setNew(false);
			}
			else {
				loopDivision = (LoopDivision)session.merge(loopDivision);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LoopDivisionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					loopDivisionModelImpl.getCompanyId(),
					loopDivisionModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CI_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CI_T,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((loopDivisionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						loopDivisionModelImpl.getOriginalCompanyId(),
						loopDivisionModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CI_T,
					args);

				args = new Object[] {
						loopDivisionModelImpl.getCompanyId(),
						loopDivisionModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CI_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CI_T,
					args);
			}
		}

		entityCache.putResult(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionImpl.class, loopDivision.getPrimaryKey(), loopDivision,
			false);

		clearUniqueFindersCache(loopDivisionModelImpl, false);
		cacheUniqueFindersCache(loopDivisionModelImpl);

		loopDivision.resetOriginalValues();

		return loopDivision;
	}

	protected LoopDivision toUnwrappedModel(LoopDivision loopDivision) {
		if (loopDivision instanceof LoopDivisionImpl) {
			return loopDivision;
		}

		LoopDivisionImpl loopDivisionImpl = new LoopDivisionImpl();

		loopDivisionImpl.setNew(loopDivision.isNew());
		loopDivisionImpl.setPrimaryKey(loopDivision.getPrimaryKey());

		loopDivisionImpl.setLoopDivisionId(loopDivision.getLoopDivisionId());
		loopDivisionImpl.setCompanyId(loopDivision.getCompanyId());
		loopDivisionImpl.setUserId(loopDivision.getUserId());
		loopDivisionImpl.setUserName(loopDivision.getUserName());
		loopDivisionImpl.setCreateDate(loopDivision.getCreateDate());
		loopDivisionImpl.setModifiedDate(loopDivision.getModifiedDate());
		loopDivisionImpl.setOrganizationId(loopDivision.getOrganizationId());
		loopDivisionImpl.setParentLoopDivisionId(loopDivision.getParentLoopDivisionId());
		loopDivisionImpl.setType(loopDivision.getType());
		loopDivisionImpl.setSubtype(loopDivision.getSubtype());
		loopDivisionImpl.setExtraData(loopDivision.getExtraData());
		loopDivisionImpl.setImagePayload(loopDivision.getImagePayload());

		return loopDivisionImpl;
	}

	/**
	 * Returns the loop division with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop division
	 * @return the loop division
	 * @throws NoSuchLoopDivisionException if a loop division with the primary key could not be found
	 */
	@Override
	public LoopDivision findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopDivisionException {
		LoopDivision loopDivision = fetchByPrimaryKey(primaryKey);

		if (loopDivision == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopDivisionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopDivision;
	}

	/**
	 * Returns the loop division with the primary key or throws a {@link NoSuchLoopDivisionException} if it could not be found.
	 *
	 * @param loopDivisionId the primary key of the loop division
	 * @return the loop division
	 * @throws NoSuchLoopDivisionException if a loop division with the primary key could not be found
	 */
	@Override
	public LoopDivision findByPrimaryKey(long loopDivisionId)
		throws NoSuchLoopDivisionException {
		return findByPrimaryKey((Serializable)loopDivisionId);
	}

	/**
	 * Returns the loop division with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop division
	 * @return the loop division, or <code>null</code> if a loop division with the primary key could not be found
	 */
	@Override
	public LoopDivision fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
				LoopDivisionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopDivision loopDivision = (LoopDivision)serializable;

		if (loopDivision == null) {
			Session session = null;

			try {
				session = openSession();

				loopDivision = (LoopDivision)session.get(LoopDivisionImpl.class,
						primaryKey);

				if (loopDivision != null) {
					cacheResult(loopDivision);
				}
				else {
					entityCache.putResult(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
						LoopDivisionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
					LoopDivisionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopDivision;
	}

	/**
	 * Returns the loop division with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopDivisionId the primary key of the loop division
	 * @return the loop division, or <code>null</code> if a loop division with the primary key could not be found
	 */
	@Override
	public LoopDivision fetchByPrimaryKey(long loopDivisionId) {
		return fetchByPrimaryKey((Serializable)loopDivisionId);
	}

	@Override
	public Map<Serializable, LoopDivision> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopDivision> map = new HashMap<Serializable, LoopDivision>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopDivision loopDivision = fetchByPrimaryKey(primaryKey);

			if (loopDivision != null) {
				map.put(primaryKey, loopDivision);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
					LoopDivisionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopDivision)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPDIVISION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (LoopDivision loopDivision : (List<LoopDivision>)q.list()) {
				map.put(loopDivision.getPrimaryKeyObj(), loopDivision);

				cacheResult(loopDivision);

				uncachedPrimaryKeys.remove(loopDivision.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopDivisionModelImpl.ENTITY_CACHE_ENABLED,
					LoopDivisionImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop divisions.
	 *
	 * @return the loop divisions
	 */
	@Override
	public List<LoopDivision> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop divisions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop divisions
	 * @param end the upper bound of the range of loop divisions (not inclusive)
	 * @return the range of loop divisions
	 */
	@Override
	public List<LoopDivision> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop divisions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop divisions
	 * @param end the upper bound of the range of loop divisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop divisions
	 */
	@Override
	public List<LoopDivision> findAll(int start, int end,
		OrderByComparator<LoopDivision> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop divisions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop divisions
	 * @param end the upper bound of the range of loop divisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop divisions
	 */
	@Override
	public List<LoopDivision> findAll(int start, int end,
		OrderByComparator<LoopDivision> orderByComparator,
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

		List<LoopDivision> list = null;

		if (retrieveFromCache) {
			list = (List<LoopDivision>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPDIVISION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPDIVISION;

				if (pagination) {
					sql = sql.concat(LoopDivisionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopDivision>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopDivision>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the loop divisions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopDivision loopDivision : findAll()) {
			remove(loopDivision);
		}
	}

	/**
	 * Returns the number of loop divisions.
	 *
	 * @return the number of loop divisions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPDIVISION);

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
		return LoopDivisionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop division persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopDivisionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPDIVISION = "SELECT loopDivision FROM LoopDivision loopDivision";
	private static final String _SQL_SELECT_LOOPDIVISION_WHERE_PKS_IN = "SELECT loopDivision FROM LoopDivision loopDivision WHERE loopDivisionId IN (";
	private static final String _SQL_SELECT_LOOPDIVISION_WHERE = "SELECT loopDivision FROM LoopDivision loopDivision WHERE ";
	private static final String _SQL_COUNT_LOOPDIVISION = "SELECT COUNT(loopDivision) FROM LoopDivision loopDivision";
	private static final String _SQL_COUNT_LOOPDIVISION_WHERE = "SELECT COUNT(loopDivision) FROM LoopDivision loopDivision WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopDivision.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopDivision exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LoopDivision exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LoopDivisionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
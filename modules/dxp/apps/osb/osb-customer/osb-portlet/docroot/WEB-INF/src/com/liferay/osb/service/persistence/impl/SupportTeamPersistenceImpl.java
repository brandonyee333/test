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

import com.liferay.osb.exception.NoSuchSupportTeamException;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.impl.SupportTeamImpl;
import com.liferay.osb.model.impl.SupportTeamModelImpl;
import com.liferay.osb.service.persistence.AccountEntryPersistence;
import com.liferay.osb.service.persistence.SupportRegionPersistence;
import com.liferay.osb.service.persistence.SupportTeamPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
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
 * The persistence implementation for the support team service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamPersistence
 * @see com.liferay.osb.service.persistence.SupportTeamUtil
 * @generated
 */
@ProviderType
public class SupportTeamPersistenceImpl extends BasePersistenceImpl<SupportTeam>
	implements SupportTeamPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SupportTeamUtil} to access the support team persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SupportTeamImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID =
		new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByParentSupportTeamId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID =
		new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByParentSupportTeamId", new String[] { Long.class.getName() },
			SupportTeamModelImpl.PARENTSUPPORTTEAMID_COLUMN_BITMASK |
			SupportTeamModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTSUPPORTTEAMID = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByParentSupportTeamId", new String[] { Long.class.getName() });

	/**
	 * Returns all the support teams where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @return the matching support teams
	 */
	@Override
	public List<SupportTeam> findByParentSupportTeamId(long parentSupportTeamId) {
		return findByParentSupportTeamId(parentSupportTeamId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support teams where parentSupportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @return the range of matching support teams
	 */
	@Override
	public List<SupportTeam> findByParentSupportTeamId(
		long parentSupportTeamId, int start, int end) {
		return findByParentSupportTeamId(parentSupportTeamId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support teams where parentSupportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support teams
	 */
	@Override
	public List<SupportTeam> findByParentSupportTeamId(
		long parentSupportTeamId, int start, int end,
		OrderByComparator<SupportTeam> orderByComparator) {
		return findByParentSupportTeamId(parentSupportTeamId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support teams where parentSupportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching support teams
	 */
	@Override
	public List<SupportTeam> findByParentSupportTeamId(
		long parentSupportTeamId, int start, int end,
		OrderByComparator<SupportTeam> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID;
			finderArgs = new Object[] { parentSupportTeamId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID;
			finderArgs = new Object[] {
					parentSupportTeamId,
					
					start, end, orderByComparator
				};
		}

		List<SupportTeam> list = null;

		if (retrieveFromCache) {
			list = (List<SupportTeam>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SupportTeam supportTeam : list) {
					if ((parentSupportTeamId != supportTeam.getParentSupportTeamId())) {
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

			query.append(_SQL_SELECT_SUPPORTTEAM_WHERE);

			query.append(_FINDER_COLUMN_PARENTSUPPORTTEAMID_PARENTSUPPORTTEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SupportTeamModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentSupportTeamId);

				if (!pagination) {
					list = (List<SupportTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportTeam>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first support team in the ordered set where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support team
	 * @throws NoSuchSupportTeamException if a matching support team could not be found
	 */
	@Override
	public SupportTeam findByParentSupportTeamId_First(
		long parentSupportTeamId,
		OrderByComparator<SupportTeam> orderByComparator)
		throws NoSuchSupportTeamException {
		SupportTeam supportTeam = fetchByParentSupportTeamId_First(parentSupportTeamId,
				orderByComparator);

		if (supportTeam != null) {
			return supportTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentSupportTeamId=");
		msg.append(parentSupportTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportTeamException(msg.toString());
	}

	/**
	 * Returns the first support team in the ordered set where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support team, or <code>null</code> if a matching support team could not be found
	 */
	@Override
	public SupportTeam fetchByParentSupportTeamId_First(
		long parentSupportTeamId,
		OrderByComparator<SupportTeam> orderByComparator) {
		List<SupportTeam> list = findByParentSupportTeamId(parentSupportTeamId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support team in the ordered set where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support team
	 * @throws NoSuchSupportTeamException if a matching support team could not be found
	 */
	@Override
	public SupportTeam findByParentSupportTeamId_Last(
		long parentSupportTeamId,
		OrderByComparator<SupportTeam> orderByComparator)
		throws NoSuchSupportTeamException {
		SupportTeam supportTeam = fetchByParentSupportTeamId_Last(parentSupportTeamId,
				orderByComparator);

		if (supportTeam != null) {
			return supportTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentSupportTeamId=");
		msg.append(parentSupportTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportTeamException(msg.toString());
	}

	/**
	 * Returns the last support team in the ordered set where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support team, or <code>null</code> if a matching support team could not be found
	 */
	@Override
	public SupportTeam fetchByParentSupportTeamId_Last(
		long parentSupportTeamId,
		OrderByComparator<SupportTeam> orderByComparator) {
		int count = countByParentSupportTeamId(parentSupportTeamId);

		if (count == 0) {
			return null;
		}

		List<SupportTeam> list = findByParentSupportTeamId(parentSupportTeamId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support teams before and after the current support team in the ordered set where parentSupportTeamId = &#63;.
	 *
	 * @param supportTeamId the primary key of the current support team
	 * @param parentSupportTeamId the parent support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support team
	 * @throws NoSuchSupportTeamException if a support team with the primary key could not be found
	 */
	@Override
	public SupportTeam[] findByParentSupportTeamId_PrevAndNext(
		long supportTeamId, long parentSupportTeamId,
		OrderByComparator<SupportTeam> orderByComparator)
		throws NoSuchSupportTeamException {
		SupportTeam supportTeam = findByPrimaryKey(supportTeamId);

		Session session = null;

		try {
			session = openSession();

			SupportTeam[] array = new SupportTeamImpl[3];

			array[0] = getByParentSupportTeamId_PrevAndNext(session,
					supportTeam, parentSupportTeamId, orderByComparator, true);

			array[1] = supportTeam;

			array[2] = getByParentSupportTeamId_PrevAndNext(session,
					supportTeam, parentSupportTeamId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportTeam getByParentSupportTeamId_PrevAndNext(
		Session session, SupportTeam supportTeam, long parentSupportTeamId,
		OrderByComparator<SupportTeam> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTTEAM_WHERE);

		query.append(_FINDER_COLUMN_PARENTSUPPORTTEAMID_PARENTSUPPORTTEAMID_2);

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
			query.append(SupportTeamModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentSupportTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support teams where parentSupportTeamId = &#63; from the database.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 */
	@Override
	public void removeByParentSupportTeamId(long parentSupportTeamId) {
		for (SupportTeam supportTeam : findByParentSupportTeamId(
				parentSupportTeamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(supportTeam);
		}
	}

	/**
	 * Returns the number of support teams where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @return the number of matching support teams
	 */
	@Override
	public int countByParentSupportTeamId(long parentSupportTeamId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PARENTSUPPORTTEAMID;

		Object[] finderArgs = new Object[] { parentSupportTeamId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTTEAM_WHERE);

			query.append(_FINDER_COLUMN_PARENTSUPPORTTEAMID_PARENTSUPPORTTEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentSupportTeamId);

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

	private static final String _FINDER_COLUMN_PARENTSUPPORTTEAMID_PARENTSUPPORTTEAMID_2 =
		"supportTeam.parentSupportTeamId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLABORID =
		new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySupportLaborId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID =
		new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySupportLaborId",
			new String[] { Long.class.getName() },
			SupportTeamModelImpl.SUPPORTLABORID_COLUMN_BITMASK |
			SupportTeamModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTLABORID = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySupportLaborId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the support teams where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @return the matching support teams
	 */
	@Override
	public List<SupportTeam> findBySupportLaborId(long supportLaborId) {
		return findBySupportLaborId(supportLaborId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support teams where supportLaborId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportLaborId the support labor ID
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @return the range of matching support teams
	 */
	@Override
	public List<SupportTeam> findBySupportLaborId(long supportLaborId,
		int start, int end) {
		return findBySupportLaborId(supportLaborId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support teams where supportLaborId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportLaborId the support labor ID
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support teams
	 */
	@Override
	public List<SupportTeam> findBySupportLaborId(long supportLaborId,
		int start, int end, OrderByComparator<SupportTeam> orderByComparator) {
		return findBySupportLaborId(supportLaborId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support teams where supportLaborId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportLaborId the support labor ID
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching support teams
	 */
	@Override
	public List<SupportTeam> findBySupportLaborId(long supportLaborId,
		int start, int end, OrderByComparator<SupportTeam> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID;
			finderArgs = new Object[] { supportLaborId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLABORID;
			finderArgs = new Object[] {
					supportLaborId,
					
					start, end, orderByComparator
				};
		}

		List<SupportTeam> list = null;

		if (retrieveFromCache) {
			list = (List<SupportTeam>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SupportTeam supportTeam : list) {
					if ((supportLaborId != supportTeam.getSupportLaborId())) {
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

			query.append(_SQL_SELECT_SUPPORTTEAM_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SupportTeamModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportLaborId);

				if (!pagination) {
					list = (List<SupportTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportTeam>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first support team in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support team
	 * @throws NoSuchSupportTeamException if a matching support team could not be found
	 */
	@Override
	public SupportTeam findBySupportLaborId_First(long supportLaborId,
		OrderByComparator<SupportTeam> orderByComparator)
		throws NoSuchSupportTeamException {
		SupportTeam supportTeam = fetchBySupportLaborId_First(supportLaborId,
				orderByComparator);

		if (supportTeam != null) {
			return supportTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLaborId=");
		msg.append(supportLaborId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportTeamException(msg.toString());
	}

	/**
	 * Returns the first support team in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support team, or <code>null</code> if a matching support team could not be found
	 */
	@Override
	public SupportTeam fetchBySupportLaborId_First(long supportLaborId,
		OrderByComparator<SupportTeam> orderByComparator) {
		List<SupportTeam> list = findBySupportLaborId(supportLaborId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support team in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support team
	 * @throws NoSuchSupportTeamException if a matching support team could not be found
	 */
	@Override
	public SupportTeam findBySupportLaborId_Last(long supportLaborId,
		OrderByComparator<SupportTeam> orderByComparator)
		throws NoSuchSupportTeamException {
		SupportTeam supportTeam = fetchBySupportLaborId_Last(supportLaborId,
				orderByComparator);

		if (supportTeam != null) {
			return supportTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLaborId=");
		msg.append(supportLaborId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportTeamException(msg.toString());
	}

	/**
	 * Returns the last support team in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support team, or <code>null</code> if a matching support team could not be found
	 */
	@Override
	public SupportTeam fetchBySupportLaborId_Last(long supportLaborId,
		OrderByComparator<SupportTeam> orderByComparator) {
		int count = countBySupportLaborId(supportLaborId);

		if (count == 0) {
			return null;
		}

		List<SupportTeam> list = findBySupportLaborId(supportLaborId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support teams before and after the current support team in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportTeamId the primary key of the current support team
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support team
	 * @throws NoSuchSupportTeamException if a support team with the primary key could not be found
	 */
	@Override
	public SupportTeam[] findBySupportLaborId_PrevAndNext(long supportTeamId,
		long supportLaborId, OrderByComparator<SupportTeam> orderByComparator)
		throws NoSuchSupportTeamException {
		SupportTeam supportTeam = findByPrimaryKey(supportTeamId);

		Session session = null;

		try {
			session = openSession();

			SupportTeam[] array = new SupportTeamImpl[3];

			array[0] = getBySupportLaborId_PrevAndNext(session, supportTeam,
					supportLaborId, orderByComparator, true);

			array[1] = supportTeam;

			array[2] = getBySupportLaborId_PrevAndNext(session, supportTeam,
					supportLaborId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportTeam getBySupportLaborId_PrevAndNext(Session session,
		SupportTeam supportTeam, long supportLaborId,
		OrderByComparator<SupportTeam> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTTEAM_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

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
			query.append(SupportTeamModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportLaborId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support teams where supportLaborId = &#63; from the database.
	 *
	 * @param supportLaborId the support labor ID
	 */
	@Override
	public void removeBySupportLaborId(long supportLaborId) {
		for (SupportTeam supportTeam : findBySupportLaborId(supportLaborId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(supportTeam);
		}
	}

	/**
	 * Returns the number of support teams where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @return the number of matching support teams
	 */
	@Override
	public int countBySupportLaborId(long supportLaborId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUPPORTLABORID;

		Object[] finderArgs = new Object[] { supportLaborId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTTEAM_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportLaborId);

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

	private static final String _FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2 = "supportTeam.supportLaborId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			SupportTeamModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the support team where name = &#63; or throws a {@link NoSuchSupportTeamException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching support team
	 * @throws NoSuchSupportTeamException if a matching support team could not be found
	 */
	@Override
	public SupportTeam findByName(String name)
		throws NoSuchSupportTeamException {
		SupportTeam supportTeam = fetchByName(name);

		if (supportTeam == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSupportTeamException(msg.toString());
		}

		return supportTeam;
	}

	/**
	 * Returns the support team where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching support team, or <code>null</code> if a matching support team could not be found
	 */
	@Override
	public SupportTeam fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the support team where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching support team, or <code>null</code> if a matching support team could not be found
	 */
	@Override
	public SupportTeam fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof SupportTeam) {
			SupportTeam supportTeam = (SupportTeam)result;

			if (!Objects.equals(name, supportTeam.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SUPPORTTEAM_WHERE);

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

				List<SupportTeam> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					SupportTeam supportTeam = list.get(0);

					result = supportTeam;

					cacheResult(supportTeam);

					if ((supportTeam.getName() == null) ||
							!supportTeam.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, supportTeam);
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
			return (SupportTeam)result;
		}
	}

	/**
	 * Removes the support team where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the support team that was removed
	 */
	@Override
	public SupportTeam removeByName(String name)
		throws NoSuchSupportTeamException {
		SupportTeam supportTeam = findByName(name);

		return remove(supportTeam);
	}

	/**
	 * Returns the number of support teams where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching support teams
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTTEAM_WHERE);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "supportTeam.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "supportTeam.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(supportTeam.name IS NULL OR supportTeam.name = '')";

	public SupportTeamPersistenceImpl() {
		setModelClass(SupportTeam.class);
	}

	/**
	 * Caches the support team in the entity cache if it is enabled.
	 *
	 * @param supportTeam the support team
	 */
	@Override
	public void cacheResult(SupportTeam supportTeam) {
		entityCache.putResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamImpl.class, supportTeam.getPrimaryKey(), supportTeam);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { supportTeam.getName() }, supportTeam);

		supportTeam.resetOriginalValues();
	}

	/**
	 * Caches the support teams in the entity cache if it is enabled.
	 *
	 * @param supportTeams the support teams
	 */
	@Override
	public void cacheResult(List<SupportTeam> supportTeams) {
		for (SupportTeam supportTeam : supportTeams) {
			if (entityCache.getResult(
						SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
						SupportTeamImpl.class, supportTeam.getPrimaryKey()) == null) {
				cacheResult(supportTeam);
			}
			else {
				supportTeam.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support teams.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SupportTeamImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support team.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportTeam supportTeam) {
		entityCache.removeResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamImpl.class, supportTeam.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SupportTeamModelImpl)supportTeam, true);
	}

	@Override
	public void clearCache(List<SupportTeam> supportTeams) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportTeam supportTeam : supportTeams) {
			entityCache.removeResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
				SupportTeamImpl.class, supportTeam.getPrimaryKey());

			clearUniqueFindersCache((SupportTeamModelImpl)supportTeam, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SupportTeamModelImpl supportTeamModelImpl) {
		Object[] args = new Object[] { supportTeamModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
			supportTeamModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SupportTeamModelImpl supportTeamModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { supportTeamModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((supportTeamModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { supportTeamModelImpl.getOriginalName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new support team with the primary key. Does not add the support team to the database.
	 *
	 * @param supportTeamId the primary key for the new support team
	 * @return the new support team
	 */
	@Override
	public SupportTeam create(long supportTeamId) {
		SupportTeam supportTeam = new SupportTeamImpl();

		supportTeam.setNew(true);
		supportTeam.setPrimaryKey(supportTeamId);

		supportTeam.setCompanyId(companyProvider.getCompanyId());

		return supportTeam;
	}

	/**
	 * Removes the support team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportTeamId the primary key of the support team
	 * @return the support team that was removed
	 * @throws NoSuchSupportTeamException if a support team with the primary key could not be found
	 */
	@Override
	public SupportTeam remove(long supportTeamId)
		throws NoSuchSupportTeamException {
		return remove((Serializable)supportTeamId);
	}

	/**
	 * Removes the support team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support team
	 * @return the support team that was removed
	 * @throws NoSuchSupportTeamException if a support team with the primary key could not be found
	 */
	@Override
	public SupportTeam remove(Serializable primaryKey)
		throws NoSuchSupportTeamException {
		Session session = null;

		try {
			session = openSession();

			SupportTeam supportTeam = (SupportTeam)session.get(SupportTeamImpl.class,
					primaryKey);

			if (supportTeam == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportTeamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(supportTeam);
		}
		catch (NoSuchSupportTeamException nsee) {
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
	protected SupportTeam removeImpl(SupportTeam supportTeam) {
		supportTeam = toUnwrappedModel(supportTeam);

		supportTeamToAccountEntryTableMapper.deleteLeftPrimaryKeyTableMappings(supportTeam.getPrimaryKey());

		supportTeamToSupportRegionTableMapper.deleteLeftPrimaryKeyTableMappings(supportTeam.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(supportTeam)) {
				supportTeam = (SupportTeam)session.get(SupportTeamImpl.class,
						supportTeam.getPrimaryKeyObj());
			}

			if (supportTeam != null) {
				session.delete(supportTeam);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (supportTeam != null) {
			clearCache(supportTeam);
		}

		return supportTeam;
	}

	@Override
	public SupportTeam updateImpl(SupportTeam supportTeam) {
		supportTeam = toUnwrappedModel(supportTeam);

		boolean isNew = supportTeam.isNew();

		SupportTeamModelImpl supportTeamModelImpl = (SupportTeamModelImpl)supportTeam;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (supportTeam.getCreateDate() == null)) {
			if (serviceContext == null) {
				supportTeam.setCreateDate(now);
			}
			else {
				supportTeam.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!supportTeamModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				supportTeam.setModifiedDate(now);
			}
			else {
				supportTeam.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (supportTeam.isNew()) {
				session.save(supportTeam);

				supportTeam.setNew(false);
			}
			else {
				supportTeam = (SupportTeam)session.merge(supportTeam);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SupportTeamModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					supportTeamModelImpl.getParentSupportTeamId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PARENTSUPPORTTEAMID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID,
				args);

			args = new Object[] { supportTeamModelImpl.getSupportLaborId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((supportTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						supportTeamModelImpl.getOriginalParentSupportTeamId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARENTSUPPORTTEAMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID,
					args);

				args = new Object[] {
						supportTeamModelImpl.getParentSupportTeamId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARENTSUPPORTTEAMID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID,
					args);
			}

			if ((supportTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						supportTeamModelImpl.getOriginalSupportLaborId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID,
					args);

				args = new Object[] { supportTeamModelImpl.getSupportLaborId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID,
					args);
			}
		}

		entityCache.putResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamImpl.class, supportTeam.getPrimaryKey(), supportTeam,
			false);

		clearUniqueFindersCache(supportTeamModelImpl, false);
		cacheUniqueFindersCache(supportTeamModelImpl);

		supportTeam.resetOriginalValues();

		return supportTeam;
	}

	protected SupportTeam toUnwrappedModel(SupportTeam supportTeam) {
		if (supportTeam instanceof SupportTeamImpl) {
			return supportTeam;
		}

		SupportTeamImpl supportTeamImpl = new SupportTeamImpl();

		supportTeamImpl.setNew(supportTeam.isNew());
		supportTeamImpl.setPrimaryKey(supportTeam.getPrimaryKey());

		supportTeamImpl.setSupportTeamId(supportTeam.getSupportTeamId());
		supportTeamImpl.setCompanyId(supportTeam.getCompanyId());
		supportTeamImpl.setUserId(supportTeam.getUserId());
		supportTeamImpl.setUserName(supportTeam.getUserName());
		supportTeamImpl.setCreateDate(supportTeam.getCreateDate());
		supportTeamImpl.setModifiedDate(supportTeam.getModifiedDate());
		supportTeamImpl.setParentSupportTeamId(supportTeam.getParentSupportTeamId());
		supportTeamImpl.setSupportLaborId(supportTeam.getSupportLaborId());
		supportTeamImpl.setLocationSupportRegionId(supportTeam.getLocationSupportRegionId());
		supportTeamImpl.setName(supportTeam.getName());
		supportTeamImpl.setDescription(supportTeam.getDescription());
		supportTeamImpl.setType(supportTeam.getType());
		supportTeamImpl.setAssignedWork(supportTeam.getAssignedWork());
		supportTeamImpl.setMaxWork(supportTeam.getMaxWork());

		return supportTeamImpl;
	}

	/**
	 * Returns the support team with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support team
	 * @return the support team
	 * @throws NoSuchSupportTeamException if a support team with the primary key could not be found
	 */
	@Override
	public SupportTeam findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSupportTeamException {
		SupportTeam supportTeam = fetchByPrimaryKey(primaryKey);

		if (supportTeam == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSupportTeamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return supportTeam;
	}

	/**
	 * Returns the support team with the primary key or throws a {@link NoSuchSupportTeamException} if it could not be found.
	 *
	 * @param supportTeamId the primary key of the support team
	 * @return the support team
	 * @throws NoSuchSupportTeamException if a support team with the primary key could not be found
	 */
	@Override
	public SupportTeam findByPrimaryKey(long supportTeamId)
		throws NoSuchSupportTeamException {
		return findByPrimaryKey((Serializable)supportTeamId);
	}

	/**
	 * Returns the support team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support team
	 * @return the support team, or <code>null</code> if a support team with the primary key could not be found
	 */
	@Override
	public SupportTeam fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
				SupportTeamImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SupportTeam supportTeam = (SupportTeam)serializable;

		if (supportTeam == null) {
			Session session = null;

			try {
				session = openSession();

				supportTeam = (SupportTeam)session.get(SupportTeamImpl.class,
						primaryKey);

				if (supportTeam != null) {
					cacheResult(supportTeam);
				}
				else {
					entityCache.putResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
						SupportTeamImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
					SupportTeamImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return supportTeam;
	}

	/**
	 * Returns the support team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportTeamId the primary key of the support team
	 * @return the support team, or <code>null</code> if a support team with the primary key could not be found
	 */
	@Override
	public SupportTeam fetchByPrimaryKey(long supportTeamId) {
		return fetchByPrimaryKey((Serializable)supportTeamId);
	}

	@Override
	public Map<Serializable, SupportTeam> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SupportTeam> map = new HashMap<Serializable, SupportTeam>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SupportTeam supportTeam = fetchByPrimaryKey(primaryKey);

			if (supportTeam != null) {
				map.put(primaryKey, supportTeam);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
					SupportTeamImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SupportTeam)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SUPPORTTEAM_WHERE_PKS_IN);

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

			for (SupportTeam supportTeam : (List<SupportTeam>)q.list()) {
				map.put(supportTeam.getPrimaryKeyObj(), supportTeam);

				cacheResult(supportTeam);

				uncachedPrimaryKeys.remove(supportTeam.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
					SupportTeamImpl.class, primaryKey, nullModel);
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
	 * Returns all the support teams.
	 *
	 * @return the support teams
	 */
	@Override
	public List<SupportTeam> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @return the range of support teams
	 */
	@Override
	public List<SupportTeam> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support teams
	 */
	@Override
	public List<SupportTeam> findAll(int start, int end,
		OrderByComparator<SupportTeam> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of support teams
	 */
	@Override
	public List<SupportTeam> findAll(int start, int end,
		OrderByComparator<SupportTeam> orderByComparator,
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

		List<SupportTeam> list = null;

		if (retrieveFromCache) {
			list = (List<SupportTeam>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SUPPORTTEAM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTTEAM;

				if (pagination) {
					sql = sql.concat(SupportTeamModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SupportTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportTeam>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the support teams from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SupportTeam supportTeam : findAll()) {
			remove(supportTeam);
		}
	}

	/**
	 * Returns the number of support teams.
	 *
	 * @return the number of support teams
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTTEAM);

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

	/**
	 * Returns the primaryKeys of account entries associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @return long[] of the primaryKeys of account entries associated with the support team
	 */
	@Override
	public long[] getAccountEntryPrimaryKeys(long pk) {
		long[] pks = supportTeamToAccountEntryTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the account entries associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @return the account entries associated with the support team
	 */
	@Override
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk) {
		return getAccountEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the account entries associated with the support team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the support team
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @return the range of account entries associated with the support team
	 */
	@Override
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk,
		int start, int end) {
		return getAccountEntries(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries associated with the support team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the support team
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entries associated with the support team
	 */
	@Override
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk,
		int start, int end,
		OrderByComparator<com.liferay.osb.model.AccountEntry> orderByComparator) {
		return supportTeamToAccountEntryTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of account entries associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @return the number of account entries associated with the support team
	 */
	@Override
	public int getAccountEntriesSize(long pk) {
		long[] pks = supportTeamToAccountEntryTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the account entry is associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPK the primary key of the account entry
	 * @return <code>true</code> if the account entry is associated with the support team; <code>false</code> otherwise
	 */
	@Override
	public boolean containsAccountEntry(long pk, long accountEntryPK) {
		return supportTeamToAccountEntryTableMapper.containsTableMapping(pk,
			accountEntryPK);
	}

	/**
	 * Returns <code>true</code> if the support team has any account entries associated with it.
	 *
	 * @param pk the primary key of the support team to check for associations with account entries
	 * @return <code>true</code> if the support team has any account entries associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsAccountEntries(long pk) {
		if (getAccountEntriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPK the primary key of the account entry
	 */
	@Override
	public void addAccountEntry(long pk, long accountEntryPK) {
		SupportTeam supportTeam = fetchByPrimaryKey(pk);

		if (supportTeam == null) {
			supportTeamToAccountEntryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, accountEntryPK);
		}
		else {
			supportTeamToAccountEntryTableMapper.addTableMapping(supportTeam.getCompanyId(),
				pk, accountEntryPK);
		}
	}

	/**
	 * Adds an association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntry the account entry
	 */
	@Override
	public void addAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry) {
		SupportTeam supportTeam = fetchByPrimaryKey(pk);

		if (supportTeam == null) {
			supportTeamToAccountEntryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, accountEntry.getPrimaryKey());
		}
		else {
			supportTeamToAccountEntryTableMapper.addTableMapping(supportTeam.getCompanyId(),
				pk, accountEntry.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPKs the primary keys of the account entries
	 */
	@Override
	public void addAccountEntries(long pk, long[] accountEntryPKs) {
		long companyId = 0;

		SupportTeam supportTeam = fetchByPrimaryKey(pk);

		if (supportTeam == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = supportTeam.getCompanyId();
		}

		supportTeamToAccountEntryTableMapper.addTableMappings(companyId, pk,
			accountEntryPKs);
	}

	/**
	 * Adds an association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntries the account entries
	 */
	@Override
	public void addAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries) {
		addAccountEntries(pk,
			ListUtil.toLongArray(accountEntries,
				com.liferay.osb.model.AccountEntry.ACCOUNT_ENTRY_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the support team and its account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team to clear the associated account entries from
	 */
	@Override
	public void clearAccountEntries(long pk) {
		supportTeamToAccountEntryTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPK the primary key of the account entry
	 */
	@Override
	public void removeAccountEntry(long pk, long accountEntryPK) {
		supportTeamToAccountEntryTableMapper.deleteTableMapping(pk,
			accountEntryPK);
	}

	/**
	 * Removes the association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntry the account entry
	 */
	@Override
	public void removeAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry) {
		supportTeamToAccountEntryTableMapper.deleteTableMapping(pk,
			accountEntry.getPrimaryKey());
	}

	/**
	 * Removes the association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPKs the primary keys of the account entries
	 */
	@Override
	public void removeAccountEntries(long pk, long[] accountEntryPKs) {
		supportTeamToAccountEntryTableMapper.deleteTableMappings(pk,
			accountEntryPKs);
	}

	/**
	 * Removes the association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntries the account entries
	 */
	@Override
	public void removeAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries) {
		removeAccountEntries(pk,
			ListUtil.toLongArray(accountEntries,
				com.liferay.osb.model.AccountEntry.ACCOUNT_ENTRY_ID_ACCESSOR));
	}

	/**
	 * Sets the account entries associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPKs the primary keys of the account entries to be associated with the support team
	 */
	@Override
	public void setAccountEntries(long pk, long[] accountEntryPKs) {
		Set<Long> newAccountEntryPKsSet = SetUtil.fromArray(accountEntryPKs);
		Set<Long> oldAccountEntryPKsSet = SetUtil.fromArray(supportTeamToAccountEntryTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeAccountEntryPKsSet = new HashSet<Long>(oldAccountEntryPKsSet);

		removeAccountEntryPKsSet.removeAll(newAccountEntryPKsSet);

		supportTeamToAccountEntryTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeAccountEntryPKsSet));

		newAccountEntryPKsSet.removeAll(oldAccountEntryPKsSet);

		long companyId = 0;

		SupportTeam supportTeam = fetchByPrimaryKey(pk);

		if (supportTeam == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = supportTeam.getCompanyId();
		}

		supportTeamToAccountEntryTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newAccountEntryPKsSet));
	}

	/**
	 * Sets the account entries associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntries the account entries to be associated with the support team
	 */
	@Override
	public void setAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries) {
		try {
			long[] accountEntryPKs = new long[accountEntries.size()];

			for (int i = 0; i < accountEntries.size(); i++) {
				com.liferay.osb.model.AccountEntry accountEntry = accountEntries.get(i);

				accountEntryPKs[i] = accountEntry.getPrimaryKey();
			}

			setAccountEntries(pk, accountEntryPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	/**
	 * Returns the primaryKeys of support regions associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @return long[] of the primaryKeys of support regions associated with the support team
	 */
	@Override
	public long[] getSupportRegionPrimaryKeys(long pk) {
		long[] pks = supportTeamToSupportRegionTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the support regions associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @return the support regions associated with the support team
	 */
	@Override
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(long pk) {
		return getSupportRegions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the support regions associated with the support team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the support team
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @return the range of support regions associated with the support team
	 */
	@Override
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end) {
		return getSupportRegions(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support regions associated with the support team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the support team
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support regions associated with the support team
	 */
	@Override
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.model.SupportRegion> orderByComparator) {
		return supportTeamToSupportRegionTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of support regions associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @return the number of support regions associated with the support team
	 */
	@Override
	public int getSupportRegionsSize(long pk) {
		long[] pks = supportTeamToSupportRegionTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the support region is associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPK the primary key of the support region
	 * @return <code>true</code> if the support region is associated with the support team; <code>false</code> otherwise
	 */
	@Override
	public boolean containsSupportRegion(long pk, long supportRegionPK) {
		return supportTeamToSupportRegionTableMapper.containsTableMapping(pk,
			supportRegionPK);
	}

	/**
	 * Returns <code>true</code> if the support team has any support regions associated with it.
	 *
	 * @param pk the primary key of the support team to check for associations with support regions
	 * @return <code>true</code> if the support team has any support regions associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsSupportRegions(long pk) {
		if (getSupportRegionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPK the primary key of the support region
	 */
	@Override
	public void addSupportRegion(long pk, long supportRegionPK) {
		SupportTeam supportTeam = fetchByPrimaryKey(pk);

		if (supportTeam == null) {
			supportTeamToSupportRegionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, supportRegionPK);
		}
		else {
			supportTeamToSupportRegionTableMapper.addTableMapping(supportTeam.getCompanyId(),
				pk, supportRegionPK);
		}
	}

	/**
	 * Adds an association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegion the support region
	 */
	@Override
	public void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion) {
		SupportTeam supportTeam = fetchByPrimaryKey(pk);

		if (supportTeam == null) {
			supportTeamToSupportRegionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, supportRegion.getPrimaryKey());
		}
		else {
			supportTeamToSupportRegionTableMapper.addTableMapping(supportTeam.getCompanyId(),
				pk, supportRegion.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPKs the primary keys of the support regions
	 */
	@Override
	public void addSupportRegions(long pk, long[] supportRegionPKs) {
		long companyId = 0;

		SupportTeam supportTeam = fetchByPrimaryKey(pk);

		if (supportTeam == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = supportTeam.getCompanyId();
		}

		supportTeamToSupportRegionTableMapper.addTableMappings(companyId, pk,
			supportRegionPKs);
	}

	/**
	 * Adds an association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegions the support regions
	 */
	@Override
	public void addSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		addSupportRegions(pk,
			ListUtil.toLongArray(supportRegions,
				com.liferay.osb.model.SupportRegion.SUPPORT_REGION_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the support team and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team to clear the associated support regions from
	 */
	@Override
	public void clearSupportRegions(long pk) {
		supportTeamToSupportRegionTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPK the primary key of the support region
	 */
	@Override
	public void removeSupportRegion(long pk, long supportRegionPK) {
		supportTeamToSupportRegionTableMapper.deleteTableMapping(pk,
			supportRegionPK);
	}

	/**
	 * Removes the association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegion the support region
	 */
	@Override
	public void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion) {
		supportTeamToSupportRegionTableMapper.deleteTableMapping(pk,
			supportRegion.getPrimaryKey());
	}

	/**
	 * Removes the association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPKs the primary keys of the support regions
	 */
	@Override
	public void removeSupportRegions(long pk, long[] supportRegionPKs) {
		supportTeamToSupportRegionTableMapper.deleteTableMappings(pk,
			supportRegionPKs);
	}

	/**
	 * Removes the association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegions the support regions
	 */
	@Override
	public void removeSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		removeSupportRegions(pk,
			ListUtil.toLongArray(supportRegions,
				com.liferay.osb.model.SupportRegion.SUPPORT_REGION_ID_ACCESSOR));
	}

	/**
	 * Sets the support regions associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPKs the primary keys of the support regions to be associated with the support team
	 */
	@Override
	public void setSupportRegions(long pk, long[] supportRegionPKs) {
		Set<Long> newSupportRegionPKsSet = SetUtil.fromArray(supportRegionPKs);
		Set<Long> oldSupportRegionPKsSet = SetUtil.fromArray(supportTeamToSupportRegionTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeSupportRegionPKsSet = new HashSet<Long>(oldSupportRegionPKsSet);

		removeSupportRegionPKsSet.removeAll(newSupportRegionPKsSet);

		supportTeamToSupportRegionTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeSupportRegionPKsSet));

		newSupportRegionPKsSet.removeAll(oldSupportRegionPKsSet);

		long companyId = 0;

		SupportTeam supportTeam = fetchByPrimaryKey(pk);

		if (supportTeam == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = supportTeam.getCompanyId();
		}

		supportTeamToSupportRegionTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newSupportRegionPKsSet));
	}

	/**
	 * Sets the support regions associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegions the support regions to be associated with the support team
	 */
	@Override
	public void setSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		try {
			long[] supportRegionPKs = new long[supportRegions.size()];

			for (int i = 0; i < supportRegions.size(); i++) {
				com.liferay.osb.model.SupportRegion supportRegion = supportRegions.get(i);

				supportRegionPKs[i] = supportRegion.getPrimaryKey();
			}

			setSupportRegions(pk, supportRegionPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SupportTeamModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the support team persistence.
	 */
	public void afterPropertiesSet() {
		supportTeamToAccountEntryTableMapper = TableMapperFactory.getTableMapper("OSB_AccountEntries_SupportTeams",
				"companyId", "supportTeamId", "accountEntryId", this,
				accountEntryPersistence);

		supportTeamToSupportRegionTableMapper = TableMapperFactory.getTableMapper("OSB_SupportTeams_SupportRegions",
				"companyId", "supportTeamId", "supportRegionId", this,
				supportRegionPersistence);
	}

	public void destroy() {
		entityCache.removeCache(SupportTeamImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("OSB_AccountEntries_SupportTeams");
		TableMapperFactory.removeTableMapper("OSB_SupportTeams_SupportRegions");
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;
	protected TableMapper<SupportTeam, com.liferay.osb.model.AccountEntry> supportTeamToAccountEntryTableMapper;
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	protected TableMapper<SupportTeam, com.liferay.osb.model.SupportRegion> supportTeamToSupportRegionTableMapper;
	private static final String _SQL_SELECT_SUPPORTTEAM = "SELECT supportTeam FROM SupportTeam supportTeam";
	private static final String _SQL_SELECT_SUPPORTTEAM_WHERE_PKS_IN = "SELECT supportTeam FROM SupportTeam supportTeam WHERE supportTeamId IN (";
	private static final String _SQL_SELECT_SUPPORTTEAM_WHERE = "SELECT supportTeam FROM SupportTeam supportTeam WHERE ";
	private static final String _SQL_COUNT_SUPPORTTEAM = "SELECT COUNT(supportTeam) FROM SupportTeam supportTeam";
	private static final String _SQL_COUNT_SUPPORTTEAM_WHERE = "SELECT COUNT(supportTeam) FROM SupportTeam supportTeam WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportTeam.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportTeam exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportTeam exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SupportTeamPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
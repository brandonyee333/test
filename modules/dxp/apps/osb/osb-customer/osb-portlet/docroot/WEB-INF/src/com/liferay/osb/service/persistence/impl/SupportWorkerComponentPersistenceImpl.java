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

import com.liferay.osb.exception.NoSuchSupportWorkerComponentException;
import com.liferay.osb.model.SupportWorkerComponent;
import com.liferay.osb.model.impl.SupportWorkerComponentImpl;
import com.liferay.osb.model.impl.SupportWorkerComponentModelImpl;
import com.liferay.osb.service.persistence.SupportWorkerComponentPersistence;

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
 * The persistence implementation for the support worker component service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerComponentPersistence
 * @see com.liferay.osb.service.persistence.SupportWorkerComponentUtil
 * @generated
 */
@ProviderType
public class SupportWorkerComponentPersistenceImpl extends BasePersistenceImpl<SupportWorkerComponent>
	implements SupportWorkerComponentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SupportWorkerComponentUtil} to access the support worker component persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SupportWorkerComponentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTWORKERID =
		new FinderPath(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySupportWorkerId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID =
		new FinderPath(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySupportWorkerId",
			new String[] { Long.class.getName() },
			SupportWorkerComponentModelImpl.SUPPORTWORKERID_COLUMN_BITMASK |
			SupportWorkerComponentModelImpl.COMPONENT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTWORKERID = new FinderPath(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySupportWorkerId", new String[] { Long.class.getName() });

	/**
	 * Returns all the support worker components where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @return the matching support worker components
	 */
	@Override
	public List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId) {
		return findBySupportWorkerId(supportWorkerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support worker components where supportWorkerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportWorkerId the support worker ID
	 * @param start the lower bound of the range of support worker components
	 * @param end the upper bound of the range of support worker components (not inclusive)
	 * @return the range of matching support worker components
	 */
	@Override
	public List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end) {
		return findBySupportWorkerId(supportWorkerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support worker components where supportWorkerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportWorkerId the support worker ID
	 * @param start the lower bound of the range of support worker components
	 * @param end the upper bound of the range of support worker components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support worker components
	 */
	@Override
	public List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		OrderByComparator<SupportWorkerComponent> orderByComparator) {
		return findBySupportWorkerId(supportWorkerId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support worker components where supportWorkerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportWorkerId the support worker ID
	 * @param start the lower bound of the range of support worker components
	 * @param end the upper bound of the range of support worker components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching support worker components
	 */
	@Override
	public List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		OrderByComparator<SupportWorkerComponent> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID;
			finderArgs = new Object[] { supportWorkerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTWORKERID;
			finderArgs = new Object[] {
					supportWorkerId,
					
					start, end, orderByComparator
				};
		}

		List<SupportWorkerComponent> list = null;

		if (retrieveFromCache) {
			list = (List<SupportWorkerComponent>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SupportWorkerComponent supportWorkerComponent : list) {
					if ((supportWorkerId != supportWorkerComponent.getSupportWorkerId())) {
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

			query.append(_SQL_SELECT_SUPPORTWORKERCOMPONENT_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTWORKERID_SUPPORTWORKERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SupportWorkerComponentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportWorkerId);

				if (!pagination) {
					list = (List<SupportWorkerComponent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportWorkerComponent>)QueryUtil.list(q,
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
	 * Returns the first support worker component in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker component
	 * @throws NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	 */
	@Override
	public SupportWorkerComponent findBySupportWorkerId_First(
		long supportWorkerId,
		OrderByComparator<SupportWorkerComponent> orderByComparator)
		throws NoSuchSupportWorkerComponentException {
		SupportWorkerComponent supportWorkerComponent = fetchBySupportWorkerId_First(supportWorkerId,
				orderByComparator);

		if (supportWorkerComponent != null) {
			return supportWorkerComponent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportWorkerId=");
		msg.append(supportWorkerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerComponentException(msg.toString());
	}

	/**
	 * Returns the first support worker component in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker component, or <code>null</code> if a matching support worker component could not be found
	 */
	@Override
	public SupportWorkerComponent fetchBySupportWorkerId_First(
		long supportWorkerId,
		OrderByComparator<SupportWorkerComponent> orderByComparator) {
		List<SupportWorkerComponent> list = findBySupportWorkerId(supportWorkerId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support worker component in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker component
	 * @throws NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	 */
	@Override
	public SupportWorkerComponent findBySupportWorkerId_Last(
		long supportWorkerId,
		OrderByComparator<SupportWorkerComponent> orderByComparator)
		throws NoSuchSupportWorkerComponentException {
		SupportWorkerComponent supportWorkerComponent = fetchBySupportWorkerId_Last(supportWorkerId,
				orderByComparator);

		if (supportWorkerComponent != null) {
			return supportWorkerComponent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportWorkerId=");
		msg.append(supportWorkerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerComponentException(msg.toString());
	}

	/**
	 * Returns the last support worker component in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker component, or <code>null</code> if a matching support worker component could not be found
	 */
	@Override
	public SupportWorkerComponent fetchBySupportWorkerId_Last(
		long supportWorkerId,
		OrderByComparator<SupportWorkerComponent> orderByComparator) {
		int count = countBySupportWorkerId(supportWorkerId);

		if (count == 0) {
			return null;
		}

		List<SupportWorkerComponent> list = findBySupportWorkerId(supportWorkerId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support worker components before and after the current support worker component in the ordered set where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerComponentId the primary key of the current support worker component
	 * @param supportWorkerId the support worker ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support worker component
	 * @throws NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	 */
	@Override
	public SupportWorkerComponent[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerComponentId, long supportWorkerId,
		OrderByComparator<SupportWorkerComponent> orderByComparator)
		throws NoSuchSupportWorkerComponentException {
		SupportWorkerComponent supportWorkerComponent = findByPrimaryKey(supportWorkerComponentId);

		Session session = null;

		try {
			session = openSession();

			SupportWorkerComponent[] array = new SupportWorkerComponentImpl[3];

			array[0] = getBySupportWorkerId_PrevAndNext(session,
					supportWorkerComponent, supportWorkerId, orderByComparator,
					true);

			array[1] = supportWorkerComponent;

			array[2] = getBySupportWorkerId_PrevAndNext(session,
					supportWorkerComponent, supportWorkerId, orderByComparator,
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

	protected SupportWorkerComponent getBySupportWorkerId_PrevAndNext(
		Session session, SupportWorkerComponent supportWorkerComponent,
		long supportWorkerId,
		OrderByComparator<SupportWorkerComponent> orderByComparator,
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

		query.append(_SQL_SELECT_SUPPORTWORKERCOMPONENT_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTWORKERID_SUPPORTWORKERID_2);

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
			query.append(SupportWorkerComponentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportWorkerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportWorkerComponent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportWorkerComponent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support worker components where supportWorkerId = &#63; from the database.
	 *
	 * @param supportWorkerId the support worker ID
	 */
	@Override
	public void removeBySupportWorkerId(long supportWorkerId) {
		for (SupportWorkerComponent supportWorkerComponent : findBySupportWorkerId(
				supportWorkerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(supportWorkerComponent);
		}
	}

	/**
	 * Returns the number of support worker components where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @return the number of matching support worker components
	 */
	@Override
	public int countBySupportWorkerId(long supportWorkerId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUPPORTWORKERID;

		Object[] finderArgs = new Object[] { supportWorkerId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTWORKERCOMPONENT_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTWORKERID_SUPPORTWORKERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportWorkerId);

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

	private static final String _FINDER_COLUMN_SUPPORTWORKERID_SUPPORTWORKERID_2 =
		"supportWorkerComponent.supportWorkerId = ?";

	public SupportWorkerComponentPersistenceImpl() {
		setModelClass(SupportWorkerComponent.class);
	}

	/**
	 * Caches the support worker component in the entity cache if it is enabled.
	 *
	 * @param supportWorkerComponent the support worker component
	 */
	@Override
	public void cacheResult(SupportWorkerComponent supportWorkerComponent) {
		entityCache.putResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			supportWorkerComponent.getPrimaryKey(), supportWorkerComponent);

		supportWorkerComponent.resetOriginalValues();
	}

	/**
	 * Caches the support worker components in the entity cache if it is enabled.
	 *
	 * @param supportWorkerComponents the support worker components
	 */
	@Override
	public void cacheResult(
		List<SupportWorkerComponent> supportWorkerComponents) {
		for (SupportWorkerComponent supportWorkerComponent : supportWorkerComponents) {
			if (entityCache.getResult(
						SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
						SupportWorkerComponentImpl.class,
						supportWorkerComponent.getPrimaryKey()) == null) {
				cacheResult(supportWorkerComponent);
			}
			else {
				supportWorkerComponent.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support worker components.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SupportWorkerComponentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support worker component.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportWorkerComponent supportWorkerComponent) {
		entityCache.removeResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			supportWorkerComponent.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SupportWorkerComponent> supportWorkerComponents) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportWorkerComponent supportWorkerComponent : supportWorkerComponents) {
			entityCache.removeResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
				SupportWorkerComponentImpl.class,
				supportWorkerComponent.getPrimaryKey());
		}
	}

	/**
	 * Creates a new support worker component with the primary key. Does not add the support worker component to the database.
	 *
	 * @param supportWorkerComponentId the primary key for the new support worker component
	 * @return the new support worker component
	 */
	@Override
	public SupportWorkerComponent create(long supportWorkerComponentId) {
		SupportWorkerComponent supportWorkerComponent = new SupportWorkerComponentImpl();

		supportWorkerComponent.setNew(true);
		supportWorkerComponent.setPrimaryKey(supportWorkerComponentId);

		return supportWorkerComponent;
	}

	/**
	 * Removes the support worker component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportWorkerComponentId the primary key of the support worker component
	 * @return the support worker component that was removed
	 * @throws NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	 */
	@Override
	public SupportWorkerComponent remove(long supportWorkerComponentId)
		throws NoSuchSupportWorkerComponentException {
		return remove((Serializable)supportWorkerComponentId);
	}

	/**
	 * Removes the support worker component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support worker component
	 * @return the support worker component that was removed
	 * @throws NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	 */
	@Override
	public SupportWorkerComponent remove(Serializable primaryKey)
		throws NoSuchSupportWorkerComponentException {
		Session session = null;

		try {
			session = openSession();

			SupportWorkerComponent supportWorkerComponent = (SupportWorkerComponent)session.get(SupportWorkerComponentImpl.class,
					primaryKey);

			if (supportWorkerComponent == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportWorkerComponentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(supportWorkerComponent);
		}
		catch (NoSuchSupportWorkerComponentException nsee) {
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
	protected SupportWorkerComponent removeImpl(
		SupportWorkerComponent supportWorkerComponent) {
		supportWorkerComponent = toUnwrappedModel(supportWorkerComponent);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(supportWorkerComponent)) {
				supportWorkerComponent = (SupportWorkerComponent)session.get(SupportWorkerComponentImpl.class,
						supportWorkerComponent.getPrimaryKeyObj());
			}

			if (supportWorkerComponent != null) {
				session.delete(supportWorkerComponent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (supportWorkerComponent != null) {
			clearCache(supportWorkerComponent);
		}

		return supportWorkerComponent;
	}

	@Override
	public SupportWorkerComponent updateImpl(
		SupportWorkerComponent supportWorkerComponent) {
		supportWorkerComponent = toUnwrappedModel(supportWorkerComponent);

		boolean isNew = supportWorkerComponent.isNew();

		SupportWorkerComponentModelImpl supportWorkerComponentModelImpl = (SupportWorkerComponentModelImpl)supportWorkerComponent;

		Session session = null;

		try {
			session = openSession();

			if (supportWorkerComponent.isNew()) {
				session.save(supportWorkerComponent);

				supportWorkerComponent.setNew(false);
			}
			else {
				supportWorkerComponent = (SupportWorkerComponent)session.merge(supportWorkerComponent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SupportWorkerComponentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					supportWorkerComponentModelImpl.getSupportWorkerId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((supportWorkerComponentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						supportWorkerComponentModelImpl.getOriginalSupportWorkerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID,
					args);

				args = new Object[] {
						supportWorkerComponentModelImpl.getSupportWorkerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID,
					args);
			}
		}

		entityCache.putResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			supportWorkerComponent.getPrimaryKey(), supportWorkerComponent,
			false);

		supportWorkerComponent.resetOriginalValues();

		return supportWorkerComponent;
	}

	protected SupportWorkerComponent toUnwrappedModel(
		SupportWorkerComponent supportWorkerComponent) {
		if (supportWorkerComponent instanceof SupportWorkerComponentImpl) {
			return supportWorkerComponent;
		}

		SupportWorkerComponentImpl supportWorkerComponentImpl = new SupportWorkerComponentImpl();

		supportWorkerComponentImpl.setNew(supportWorkerComponent.isNew());
		supportWorkerComponentImpl.setPrimaryKey(supportWorkerComponent.getPrimaryKey());

		supportWorkerComponentImpl.setSupportWorkerComponentId(supportWorkerComponent.getSupportWorkerComponentId());
		supportWorkerComponentImpl.setSupportWorkerId(supportWorkerComponent.getSupportWorkerId());
		supportWorkerComponentImpl.setComponent(supportWorkerComponent.getComponent());

		return supportWorkerComponentImpl;
	}

	/**
	 * Returns the support worker component with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker component
	 * @return the support worker component
	 * @throws NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	 */
	@Override
	public SupportWorkerComponent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSupportWorkerComponentException {
		SupportWorkerComponent supportWorkerComponent = fetchByPrimaryKey(primaryKey);

		if (supportWorkerComponent == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSupportWorkerComponentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return supportWorkerComponent;
	}

	/**
	 * Returns the support worker component with the primary key or throws a {@link NoSuchSupportWorkerComponentException} if it could not be found.
	 *
	 * @param supportWorkerComponentId the primary key of the support worker component
	 * @return the support worker component
	 * @throws NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	 */
	@Override
	public SupportWorkerComponent findByPrimaryKey(
		long supportWorkerComponentId)
		throws NoSuchSupportWorkerComponentException {
		return findByPrimaryKey((Serializable)supportWorkerComponentId);
	}

	/**
	 * Returns the support worker component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker component
	 * @return the support worker component, or <code>null</code> if a support worker component with the primary key could not be found
	 */
	@Override
	public SupportWorkerComponent fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
				SupportWorkerComponentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SupportWorkerComponent supportWorkerComponent = (SupportWorkerComponent)serializable;

		if (supportWorkerComponent == null) {
			Session session = null;

			try {
				session = openSession();

				supportWorkerComponent = (SupportWorkerComponent)session.get(SupportWorkerComponentImpl.class,
						primaryKey);

				if (supportWorkerComponent != null) {
					cacheResult(supportWorkerComponent);
				}
				else {
					entityCache.putResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
						SupportWorkerComponentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerComponentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return supportWorkerComponent;
	}

	/**
	 * Returns the support worker component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportWorkerComponentId the primary key of the support worker component
	 * @return the support worker component, or <code>null</code> if a support worker component with the primary key could not be found
	 */
	@Override
	public SupportWorkerComponent fetchByPrimaryKey(
		long supportWorkerComponentId) {
		return fetchByPrimaryKey((Serializable)supportWorkerComponentId);
	}

	@Override
	public Map<Serializable, SupportWorkerComponent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SupportWorkerComponent> map = new HashMap<Serializable, SupportWorkerComponent>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SupportWorkerComponent supportWorkerComponent = fetchByPrimaryKey(primaryKey);

			if (supportWorkerComponent != null) {
				map.put(primaryKey, supportWorkerComponent);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerComponentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SupportWorkerComponent)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SUPPORTWORKERCOMPONENT_WHERE_PKS_IN);

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

			for (SupportWorkerComponent supportWorkerComponent : (List<SupportWorkerComponent>)q.list()) {
				map.put(supportWorkerComponent.getPrimaryKeyObj(),
					supportWorkerComponent);

				cacheResult(supportWorkerComponent);

				uncachedPrimaryKeys.remove(supportWorkerComponent.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
					SupportWorkerComponentImpl.class, primaryKey, nullModel);
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
	 * Returns all the support worker components.
	 *
	 * @return the support worker components
	 */
	@Override
	public List<SupportWorkerComponent> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support worker components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support worker components
	 * @param end the upper bound of the range of support worker components (not inclusive)
	 * @return the range of support worker components
	 */
	@Override
	public List<SupportWorkerComponent> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support worker components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support worker components
	 * @param end the upper bound of the range of support worker components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support worker components
	 */
	@Override
	public List<SupportWorkerComponent> findAll(int start, int end,
		OrderByComparator<SupportWorkerComponent> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support worker components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support worker components
	 * @param end the upper bound of the range of support worker components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of support worker components
	 */
	@Override
	public List<SupportWorkerComponent> findAll(int start, int end,
		OrderByComparator<SupportWorkerComponent> orderByComparator,
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

		List<SupportWorkerComponent> list = null;

		if (retrieveFromCache) {
			list = (List<SupportWorkerComponent>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SUPPORTWORKERCOMPONENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTWORKERCOMPONENT;

				if (pagination) {
					sql = sql.concat(SupportWorkerComponentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SupportWorkerComponent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportWorkerComponent>)QueryUtil.list(q,
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
	 * Removes all the support worker components from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SupportWorkerComponent supportWorkerComponent : findAll()) {
			remove(supportWorkerComponent);
		}
	}

	/**
	 * Returns the number of support worker components.
	 *
	 * @return the number of support worker components
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTWORKERCOMPONENT);

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
		return SupportWorkerComponentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the support worker component persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SupportWorkerComponentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SUPPORTWORKERCOMPONENT = "SELECT supportWorkerComponent FROM SupportWorkerComponent supportWorkerComponent";
	private static final String _SQL_SELECT_SUPPORTWORKERCOMPONENT_WHERE_PKS_IN = "SELECT supportWorkerComponent FROM SupportWorkerComponent supportWorkerComponent WHERE supportWorkerComponentId IN (";
	private static final String _SQL_SELECT_SUPPORTWORKERCOMPONENT_WHERE = "SELECT supportWorkerComponent FROM SupportWorkerComponent supportWorkerComponent WHERE ";
	private static final String _SQL_COUNT_SUPPORTWORKERCOMPONENT = "SELECT COUNT(supportWorkerComponent) FROM SupportWorkerComponent supportWorkerComponent";
	private static final String _SQL_COUNT_SUPPORTWORKERCOMPONENT_WHERE = "SELECT COUNT(supportWorkerComponent) FROM SupportWorkerComponent supportWorkerComponent WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportWorkerComponent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportWorkerComponent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportWorkerComponent exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SupportWorkerComponentPersistenceImpl.class);
}
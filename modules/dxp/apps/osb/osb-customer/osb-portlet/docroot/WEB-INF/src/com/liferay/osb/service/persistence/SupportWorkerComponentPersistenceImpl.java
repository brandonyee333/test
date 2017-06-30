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

package com.liferay.osb.service.persistence;

import com.liferay.osb.NoSuchSupportWorkerComponentException;
import com.liferay.osb.model.SupportWorkerComponent;
import com.liferay.osb.model.impl.SupportWorkerComponentImpl;
import com.liferay.osb.model.impl.SupportWorkerComponentModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the support worker component service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerComponentPersistence
 * @see SupportWorkerComponentUtil
 * @generated
 */
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTWORKERID =
		new FinderPath(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySupportWorkerId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID =
		new FinderPath(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySupportWorkerId",
			new String[] { Long.class.getName() },
			SupportWorkerComponentModelImpl.SUPPORTWORKERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTWORKERID = new FinderPath(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySupportWorkerId", new String[] { Long.class.getName() });
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

	/**
	 * Caches the support worker component in the entity cache if it is enabled.
	 *
	 * @param supportWorkerComponent the support worker component
	 */
	public void cacheResult(SupportWorkerComponent supportWorkerComponent) {
		EntityCacheUtil.putResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			supportWorkerComponent.getPrimaryKey(), supportWorkerComponent);

		supportWorkerComponent.resetOriginalValues();
	}

	/**
	 * Caches the support worker components in the entity cache if it is enabled.
	 *
	 * @param supportWorkerComponents the support worker components
	 */
	public void cacheResult(
		List<SupportWorkerComponent> supportWorkerComponents) {
		for (SupportWorkerComponent supportWorkerComponent : supportWorkerComponents) {
			if (EntityCacheUtil.getResult(
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
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SupportWorkerComponentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SupportWorkerComponentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support worker component.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportWorkerComponent supportWorkerComponent) {
		EntityCacheUtil.removeResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			supportWorkerComponent.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SupportWorkerComponent> supportWorkerComponents) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportWorkerComponent supportWorkerComponent : supportWorkerComponents) {
			EntityCacheUtil.removeResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
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
	 * @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorkerComponent remove(long supportWorkerComponentId)
		throws NoSuchSupportWorkerComponentException, SystemException {
		return remove(Long.valueOf(supportWorkerComponentId));
	}

	/**
	 * Removes the support worker component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support worker component
	 * @return the support worker component that was removed
	 * @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportWorkerComponent remove(Serializable primaryKey)
		throws NoSuchSupportWorkerComponentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SupportWorkerComponent supportWorkerComponent = (SupportWorkerComponent)session.get(SupportWorkerComponentImpl.class,
					primaryKey);

			if (supportWorkerComponent == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
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
		SupportWorkerComponent supportWorkerComponent)
		throws SystemException {
		supportWorkerComponent = toUnwrappedModel(supportWorkerComponent);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, supportWorkerComponent);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(supportWorkerComponent);

		return supportWorkerComponent;
	}

	@Override
	public SupportWorkerComponent updateImpl(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent,
		boolean merge) throws SystemException {
		supportWorkerComponent = toUnwrappedModel(supportWorkerComponent);

		boolean isNew = supportWorkerComponent.isNew();

		SupportWorkerComponentModelImpl supportWorkerComponentModelImpl = (SupportWorkerComponentModelImpl)supportWorkerComponent;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, supportWorkerComponent, merge);

			supportWorkerComponent.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SupportWorkerComponentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((supportWorkerComponentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(supportWorkerComponentModelImpl.getOriginalSupportWorkerId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID,
					args);

				args = new Object[] {
						Long.valueOf(supportWorkerComponentModelImpl.getSupportWorkerId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTWORKERID,
					args);
			}
		}

		EntityCacheUtil.putResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerComponentImpl.class,
			supportWorkerComponent.getPrimaryKey(), supportWorkerComponent);

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
	 * Returns the support worker component with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker component
	 * @return the support worker component
	 * @throws com.liferay.portal.NoSuchModelException if a support worker component with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportWorkerComponent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support worker component with the primary key or throws a {@link com.liferay.osb.NoSuchSupportWorkerComponentException} if it could not be found.
	 *
	 * @param supportWorkerComponentId the primary key of the support worker component
	 * @return the support worker component
	 * @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorkerComponent findByPrimaryKey(
		long supportWorkerComponentId)
		throws NoSuchSupportWorkerComponentException, SystemException {
		SupportWorkerComponent supportWorkerComponent = fetchByPrimaryKey(supportWorkerComponentId);

		if (supportWorkerComponent == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					supportWorkerComponentId);
			}

			throw new NoSuchSupportWorkerComponentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				supportWorkerComponentId);
		}

		return supportWorkerComponent;
	}

	/**
	 * Returns the support worker component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker component
	 * @return the support worker component, or <code>null</code> if a support worker component with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportWorkerComponent fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support worker component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportWorkerComponentId the primary key of the support worker component
	 * @return the support worker component, or <code>null</code> if a support worker component with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorkerComponent fetchByPrimaryKey(
		long supportWorkerComponentId) throws SystemException {
		SupportWorkerComponent supportWorkerComponent = (SupportWorkerComponent)EntityCacheUtil.getResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
				SupportWorkerComponentImpl.class, supportWorkerComponentId);

		if (supportWorkerComponent == _nullSupportWorkerComponent) {
			return null;
		}

		if (supportWorkerComponent == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				supportWorkerComponent = (SupportWorkerComponent)session.get(SupportWorkerComponentImpl.class,
						Long.valueOf(supportWorkerComponentId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (supportWorkerComponent != null) {
					cacheResult(supportWorkerComponent);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SupportWorkerComponentModelImpl.ENTITY_CACHE_ENABLED,
						SupportWorkerComponentImpl.class,
						supportWorkerComponentId, _nullSupportWorkerComponent);
				}

				closeSession(session);
			}
		}

		return supportWorkerComponent;
	}

	/**
	 * Returns all the support worker components where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @return the matching support worker components
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId) throws SystemException {
		return findBySupportWorkerId(supportWorkerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support worker components where supportWorkerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportWorkerId the support worker ID
	 * @param start the lower bound of the range of support worker components
	 * @param end the upper bound of the range of support worker components (not inclusive)
	 * @return the range of matching support worker components
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end) throws SystemException {
		return findBySupportWorkerId(supportWorkerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support worker components where supportWorkerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportWorkerId the support worker ID
	 * @param start the lower bound of the range of support worker components
	 * @param end the upper bound of the range of support worker components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support worker components
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorkerComponent> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

		List<SupportWorkerComponent> list = (List<SupportWorkerComponent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SupportWorkerComponent supportWorkerComponent : list) {
				if ((supportWorkerId != supportWorkerComponent.getSupportWorkerId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
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

			else {
				query.append(SupportWorkerComponentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportWorkerId);

				list = (List<SupportWorkerComponent>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

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
	 * @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorkerComponent findBySupportWorkerId_First(
		long supportWorkerId, OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerComponentException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorkerComponent fetchBySupportWorkerId_First(
		long supportWorkerId, OrderByComparator orderByComparator)
		throws SystemException {
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
	 * @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a matching support worker component could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorkerComponent findBySupportWorkerId_Last(
		long supportWorkerId, OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerComponentException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorkerComponent fetchBySupportWorkerId_Last(
		long supportWorkerId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBySupportWorkerId(supportWorkerId);

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
	 * @throws com.liferay.osb.NoSuchSupportWorkerComponentException if a support worker component with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorkerComponent[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerComponentId, long supportWorkerId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerComponentException, SystemException {
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
		long supportWorkerId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
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
	 * Returns all the support worker components.
	 *
	 * @return the support worker components
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorkerComponent> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support worker components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support worker components
	 * @param end the upper bound of the range of support worker components (not inclusive)
	 * @return the range of support worker components
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorkerComponent> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support worker components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support worker components
	 * @param end the upper bound of the range of support worker components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support worker components
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorkerComponent> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<SupportWorkerComponent> list = (List<SupportWorkerComponent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SUPPORTWORKERCOMPONENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTWORKERCOMPONENT.concat(SupportWorkerComponentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SupportWorkerComponent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SupportWorkerComponent>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the support worker components where supportWorkerId = &#63; from the database.
	 *
	 * @param supportWorkerId the support worker ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySupportWorkerId(long supportWorkerId)
		throws SystemException {
		for (SupportWorkerComponent supportWorkerComponent : findBySupportWorkerId(
				supportWorkerId)) {
			remove(supportWorkerComponent);
		}
	}

	/**
	 * Removes all the support worker components from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SupportWorkerComponent supportWorkerComponent : findAll()) {
			remove(supportWorkerComponent);
		}
	}

	/**
	 * Returns the number of support worker components where supportWorkerId = &#63;.
	 *
	 * @param supportWorkerId the support worker ID
	 * @return the number of matching support worker components
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySupportWorkerId(long supportWorkerId)
		throws SystemException {
		Object[] finderArgs = new Object[] { supportWorkerId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID,
				finderArgs, this);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SUPPORTWORKERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support worker components.
	 *
	 * @return the number of support worker components
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTWORKERCOMPONENT);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the support worker component persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.SupportWorkerComponent")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SupportWorkerComponent>> listenersList = new ArrayList<ModelListener<SupportWorkerComponent>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<SupportWorkerComponent>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SupportWorkerComponentImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = AccountAttachmentPersistence.class)
	protected AccountAttachmentPersistence accountAttachmentPersistence;
	@BeanReference(type = AccountCallPersistence.class)
	protected AccountCallPersistence accountCallPersistence;
	@BeanReference(type = AccountCustomerPersistence.class)
	protected AccountCustomerPersistence accountCustomerPersistence;
	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;
	@BeanReference(type = AccountEntryLanguagePersistence.class)
	protected AccountEntryLanguagePersistence accountEntryLanguagePersistence;
	@BeanReference(type = AccountEnvironmentPersistence.class)
	protected AccountEnvironmentPersistence accountEnvironmentPersistence;
	@BeanReference(type = AccountEnvironmentAttachmentPersistence.class)
	protected AccountEnvironmentAttachmentPersistence accountEnvironmentAttachmentPersistence;
	@BeanReference(type = AccountInformationPersistence.class)
	protected AccountInformationPersistence accountInformationPersistence;
	@BeanReference(type = AccountLinkPersistence.class)
	protected AccountLinkPersistence accountLinkPersistence;
	@BeanReference(type = AccountProjectPersistence.class)
	protected AccountProjectPersistence accountProjectPersistence;
	@BeanReference(type = AccountWorkerPersistence.class)
	protected AccountWorkerPersistence accountWorkerPersistence;
	@BeanReference(type = AppAuditPersistence.class)
	protected AppAuditPersistence appAuditPersistence;
	@BeanReference(type = AppEntryPersistence.class)
	protected AppEntryPersistence appEntryPersistence;
	@BeanReference(type = AppEntryRelPersistence.class)
	protected AppEntryRelPersistence appEntryRelPersistence;
	@BeanReference(type = AppFlagPersistence.class)
	protected AppFlagPersistence appFlagPersistence;
	@BeanReference(type = AppPackagePersistence.class)
	protected AppPackagePersistence appPackagePersistence;
	@BeanReference(type = AppPackagePluginPersistence.class)
	protected AppPackagePluginPersistence appPackagePluginPersistence;
	@BeanReference(type = AppPricingPersistence.class)
	protected AppPricingPersistence appPricingPersistence;
	@BeanReference(type = AppPricingItemPersistence.class)
	protected AppPricingItemPersistence appPricingItemPersistence;
	@BeanReference(type = AppVersionPersistence.class)
	protected AppVersionPersistence appVersionPersistence;
	@BeanReference(type = AssetAttachmentPersistence.class)
	protected AssetAttachmentPersistence assetAttachmentPersistence;
	@BeanReference(type = AssetAuditPersistence.class)
	protected AssetAuditPersistence assetAuditPersistence;
	@BeanReference(type = AssetLicensePersistence.class)
	protected AssetLicensePersistence assetLicensePersistence;
	@BeanReference(type = AssetListPersistence.class)
	protected AssetListPersistence assetListPersistence;
	@BeanReference(type = AssetListAssetEntryPersistence.class)
	protected AssetListAssetEntryPersistence assetListAssetEntryPersistence;
	@BeanReference(type = AssetReceiptPersistence.class)
	protected AssetReceiptPersistence assetReceiptPersistence;
	@BeanReference(type = AssetReceiptLicensePersistence.class)
	protected AssetReceiptLicensePersistence assetReceiptLicensePersistence;
	@BeanReference(type = AssetReceiptRedeemTokenPersistence.class)
	protected AssetReceiptRedeemTokenPersistence assetReceiptRedeemTokenPersistence;
	@BeanReference(type = AssetReceiptSupportPersistence.class)
	protected AssetReceiptSupportPersistence assetReceiptSupportPersistence;
	@BeanReference(type = AssetRecommendationEntryPersistence.class)
	protected AssetRecommendationEntryPersistence assetRecommendationEntryPersistence;
	@BeanReference(type = AssetRecommendationSetPersistence.class)
	protected AssetRecommendationSetPersistence assetRecommendationSetPersistence;
	@BeanReference(type = AssetStatsDayPersistence.class)
	protected AssetStatsDayPersistence assetStatsDayPersistence;
	@BeanReference(type = AssetStatsMonthPersistence.class)
	protected AssetStatsMonthPersistence assetStatsMonthPersistence;
	@BeanReference(type = AssetStatsWeekPersistence.class)
	protected AssetStatsWeekPersistence assetStatsWeekPersistence;
	@BeanReference(type = AuditActionPersistence.class)
	protected AuditActionPersistence auditActionPersistence;
	@BeanReference(type = AuditEntryPersistence.class)
	protected AuditEntryPersistence auditEntryPersistence;
	@BeanReference(type = ContractAuditPersistence.class)
	protected ContractAuditPersistence contractAuditPersistence;
	@BeanReference(type = ContractEntryPersistence.class)
	protected ContractEntryPersistence contractEntryPersistence;
	@BeanReference(type = CorpEntryPersistence.class)
	protected CorpEntryPersistence corpEntryPersistence;
	@BeanReference(type = CorpGroupPersistence.class)
	protected CorpGroupPersistence corpGroupPersistence;
	@BeanReference(type = CorpMembershipRequestPersistence.class)
	protected CorpMembershipRequestPersistence corpMembershipRequestPersistence;
	@BeanReference(type = CorpProjectPersistence.class)
	protected CorpProjectPersistence corpProjectPersistence;
	@BeanReference(type = CorpProjectMessagePersistence.class)
	protected CorpProjectMessagePersistence corpProjectMessagePersistence;
	@BeanReference(type = CountryAppPricingPersistence.class)
	protected CountryAppPricingPersistence countryAppPricingPersistence;
	@BeanReference(type = CurrencyEntryPersistence.class)
	protected CurrencyEntryPersistence currencyEntryPersistence;
	@BeanReference(type = DeveloperEntryPersistence.class)
	protected DeveloperEntryPersistence developerEntryPersistence;
	@BeanReference(type = ExternalIdMapperPersistence.class)
	protected ExternalIdMapperPersistence externalIdMapperPersistence;
	@BeanReference(type = FeedbackEntryPersistence.class)
	protected FeedbackEntryPersistence feedbackEntryPersistence;
	@BeanReference(type = HolidayCalendarPersistence.class)
	protected HolidayCalendarPersistence holidayCalendarPersistence;
	@BeanReference(type = HolidayCalendarRelPersistence.class)
	protected HolidayCalendarRelPersistence holidayCalendarRelPersistence;
	@BeanReference(type = HolidayEntryPersistence.class)
	protected HolidayEntryPersistence holidayEntryPersistence;
	@BeanReference(type = LCSSubscriptionEntryPersistence.class)
	protected LCSSubscriptionEntryPersistence lcsSubscriptionEntryPersistence;
	@BeanReference(type = LicenseEntryPersistence.class)
	protected LicenseEntryPersistence licenseEntryPersistence;
	@BeanReference(type = LicenseKeyPersistence.class)
	protected LicenseKeyPersistence licenseKeyPersistence;
	@BeanReference(type = LicenseKeySetPersistence.class)
	protected LicenseKeySetPersistence licenseKeySetPersistence;
	@BeanReference(type = MarketingEventPersistence.class)
	protected MarketingEventPersistence marketingEventPersistence;
	@BeanReference(type = OfferingBundlePersistence.class)
	protected OfferingBundlePersistence offeringBundlePersistence;
	@BeanReference(type = OfferingDefinitionPersistence.class)
	protected OfferingDefinitionPersistence offeringDefinitionPersistence;
	@BeanReference(type = OfferingEntryPersistence.class)
	protected OfferingEntryPersistence offeringEntryPersistence;
	@BeanReference(type = OrderEntryPersistence.class)
	protected OrderEntryPersistence orderEntryPersistence;
	@BeanReference(type = PartnerEntryPersistence.class)
	protected PartnerEntryPersistence partnerEntryPersistence;
	@BeanReference(type = PartnerWorkerPersistence.class)
	protected PartnerWorkerPersistence partnerWorkerPersistence;
	@BeanReference(type = PortalReleasePersistence.class)
	protected PortalReleasePersistence portalReleasePersistence;
	@BeanReference(type = ProductEntryPersistence.class)
	protected ProductEntryPersistence productEntryPersistence;
	@BeanReference(type = SearchFilterPersistence.class)
	protected SearchFilterPersistence searchFilterPersistence;
	@BeanReference(type = SecurityPatchPersistence.class)
	protected SecurityPatchPersistence securityPatchPersistence;
	@BeanReference(type = SupportLaborPersistence.class)
	protected SupportLaborPersistence supportLaborPersistence;
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	@BeanReference(type = SupportResponsePersistence.class)
	protected SupportResponsePersistence supportResponsePersistence;
	@BeanReference(type = SupportTeamPersistence.class)
	protected SupportTeamPersistence supportTeamPersistence;
	@BeanReference(type = SupportTeamLanguagePersistence.class)
	protected SupportTeamLanguagePersistence supportTeamLanguagePersistence;
	@BeanReference(type = SupportWorkerPersistence.class)
	protected SupportWorkerPersistence supportWorkerPersistence;
	@BeanReference(type = SupportWorkerAccountTierPersistence.class)
	protected SupportWorkerAccountTierPersistence supportWorkerAccountTierPersistence;
	@BeanReference(type = SupportWorkerComponentPersistence.class)
	protected SupportWorkerComponentPersistence supportWorkerComponentPersistence;
	@BeanReference(type = SupportWorkerSeverityPersistence.class)
	protected SupportWorkerSeverityPersistence supportWorkerSeverityPersistence;
	@BeanReference(type = TicketAttachmentPersistence.class)
	protected TicketAttachmentPersistence ticketAttachmentPersistence;
	@BeanReference(type = TicketCallPersistence.class)
	protected TicketCallPersistence ticketCallPersistence;
	@BeanReference(type = TicketCannedResponsePersistence.class)
	protected TicketCannedResponsePersistence ticketCannedResponsePersistence;
	@BeanReference(type = TicketCommentPersistence.class)
	protected TicketCommentPersistence ticketCommentPersistence;
	@BeanReference(type = TicketEntryPersistence.class)
	protected TicketEntryPersistence ticketEntryPersistence;
	@BeanReference(type = TicketFeedbackPersistence.class)
	protected TicketFeedbackPersistence ticketFeedbackPersistence;
	@BeanReference(type = TicketFlagPersistence.class)
	protected TicketFlagPersistence ticketFlagPersistence;
	@BeanReference(type = TicketInformationPersistence.class)
	protected TicketInformationPersistence ticketInformationPersistence;
	@BeanReference(type = TicketLinkPersistence.class)
	protected TicketLinkPersistence ticketLinkPersistence;
	@BeanReference(type = TicketSolutionPersistence.class)
	protected TicketSolutionPersistence ticketSolutionPersistence;
	@BeanReference(type = TicketWorkerPersistence.class)
	protected TicketWorkerPersistence ticketWorkerPersistence;
	@BeanReference(type = TrainingCertificatePersistence.class)
	protected TrainingCertificatePersistence trainingCertificatePersistence;
	@BeanReference(type = TrainingCertificateTemplatePersistence.class)
	protected TrainingCertificateTemplatePersistence trainingCertificateTemplatePersistence;
	@BeanReference(type = TrainingCoursePersistence.class)
	protected TrainingCoursePersistence trainingCoursePersistence;
	@BeanReference(type = TrainingCustomerPersistence.class)
	protected TrainingCustomerPersistence trainingCustomerPersistence;
	@BeanReference(type = TrainingEventPersistence.class)
	protected TrainingEventPersistence trainingEventPersistence;
	@BeanReference(type = TrainingExamPersistence.class)
	protected TrainingExamPersistence trainingExamPersistence;
	@BeanReference(type = TrainingExamResultPersistence.class)
	protected TrainingExamResultPersistence trainingExamResultPersistence;
	@BeanReference(type = TrainingExamResultItemPersistence.class)
	protected TrainingExamResultItemPersistence trainingExamResultItemPersistence;
	@BeanReference(type = TrainingExamResultSectionPersistence.class)
	protected TrainingExamResultSectionPersistence trainingExamResultSectionPersistence;
	@BeanReference(type = TrainingImportLogPersistence.class)
	protected TrainingImportLogPersistence trainingImportLogPersistence;
	@BeanReference(type = TrainingLinkedUserPersistence.class)
	protected TrainingLinkedUserPersistence trainingLinkedUserPersistence;
	@BeanReference(type = TrainingLocationPersistence.class)
	protected TrainingLocationPersistence trainingLocationPersistence;
	@BeanReference(type = TrainingWorkerPersistence.class)
	protected TrainingWorkerPersistence trainingWorkerPersistence;
	@BeanReference(type = UserProfilePersistence.class)
	protected UserProfilePersistence userProfilePersistence;
	@BeanReference(type = UserProfileHistoryPersistence.class)
	protected UserProfileHistoryPersistence userProfileHistoryPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SUPPORTWORKERCOMPONENT = "SELECT supportWorkerComponent FROM SupportWorkerComponent supportWorkerComponent";
	private static final String _SQL_SELECT_SUPPORTWORKERCOMPONENT_WHERE = "SELECT supportWorkerComponent FROM SupportWorkerComponent supportWorkerComponent WHERE ";
	private static final String _SQL_COUNT_SUPPORTWORKERCOMPONENT = "SELECT COUNT(supportWorkerComponent) FROM SupportWorkerComponent supportWorkerComponent";
	private static final String _SQL_COUNT_SUPPORTWORKERCOMPONENT_WHERE = "SELECT COUNT(supportWorkerComponent) FROM SupportWorkerComponent supportWorkerComponent WHERE ";
	private static final String _FINDER_COLUMN_SUPPORTWORKERID_SUPPORTWORKERID_2 =
		"supportWorkerComponent.supportWorkerId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportWorkerComponent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportWorkerComponent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportWorkerComponent exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SupportWorkerComponentPersistenceImpl.class);
	private static SupportWorkerComponent _nullSupportWorkerComponent = new SupportWorkerComponentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SupportWorkerComponent> toCacheModel() {
				return _nullSupportWorkerComponentCacheModel;
			}
		};

	private static CacheModel<SupportWorkerComponent> _nullSupportWorkerComponentCacheModel =
		new CacheModel<SupportWorkerComponent>() {
			public SupportWorkerComponent toEntityModel() {
				return _nullSupportWorkerComponent;
			}
		};
}
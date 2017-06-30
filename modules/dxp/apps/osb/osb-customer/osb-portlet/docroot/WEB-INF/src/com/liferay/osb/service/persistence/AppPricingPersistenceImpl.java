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

import com.liferay.osb.NoSuchAppPricingException;
import com.liferay.osb.model.AppPricing;
import com.liferay.osb.model.impl.AppPricingImpl;
import com.liferay.osb.model.impl.AppPricingModelImpl;

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
 * The persistence implementation for the app pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPricingPersistence
 * @see AppPricingUtil
 * @generated
 */
public class AppPricingPersistenceImpl extends BasePersistenceImpl<AppPricing>
	implements AppPricingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AppPricingUtil} to access the app pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AppPricingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPVERSIONID =
		new FinderPath(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingModelImpl.FINDER_CACHE_ENABLED, AppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppVersionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID =
		new FinderPath(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingModelImpl.FINDER_CACHE_ENABLED, AppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppVersionId",
			new String[] { Long.class.getName() },
			AppPricingModelImpl.APPVERSIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPVERSIONID = new FinderPath(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppVersionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingModelImpl.FINDER_CACHE_ENABLED, AppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingModelImpl.FINDER_CACHE_ENABLED, AppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the app pricing in the entity cache if it is enabled.
	 *
	 * @param appPricing the app pricing
	 */
	public void cacheResult(AppPricing appPricing) {
		EntityCacheUtil.putResult(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingImpl.class, appPricing.getPrimaryKey(), appPricing);

		appPricing.resetOriginalValues();
	}

	/**
	 * Caches the app pricings in the entity cache if it is enabled.
	 *
	 * @param appPricings the app pricings
	 */
	public void cacheResult(List<AppPricing> appPricings) {
		for (AppPricing appPricing : appPricings) {
			if (EntityCacheUtil.getResult(
						AppPricingModelImpl.ENTITY_CACHE_ENABLED,
						AppPricingImpl.class, appPricing.getPrimaryKey()) == null) {
				cacheResult(appPricing);
			}
			else {
				appPricing.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all app pricings.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AppPricingImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AppPricingImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the app pricing.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AppPricing appPricing) {
		EntityCacheUtil.removeResult(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingImpl.class, appPricing.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AppPricing> appPricings) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AppPricing appPricing : appPricings) {
			EntityCacheUtil.removeResult(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
				AppPricingImpl.class, appPricing.getPrimaryKey());
		}
	}

	/**
	 * Creates a new app pricing with the primary key. Does not add the app pricing to the database.
	 *
	 * @param appPricingId the primary key for the new app pricing
	 * @return the new app pricing
	 */
	public AppPricing create(long appPricingId) {
		AppPricing appPricing = new AppPricingImpl();

		appPricing.setNew(true);
		appPricing.setPrimaryKey(appPricingId);

		return appPricing;
	}

	/**
	 * Removes the app pricing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param appPricingId the primary key of the app pricing
	 * @return the app pricing that was removed
	 * @throws com.liferay.osb.NoSuchAppPricingException if a app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricing remove(long appPricingId)
		throws NoSuchAppPricingException, SystemException {
		return remove(Long.valueOf(appPricingId));
	}

	/**
	 * Removes the app pricing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the app pricing
	 * @return the app pricing that was removed
	 * @throws com.liferay.osb.NoSuchAppPricingException if a app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPricing remove(Serializable primaryKey)
		throws NoSuchAppPricingException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AppPricing appPricing = (AppPricing)session.get(AppPricingImpl.class,
					primaryKey);

			if (appPricing == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAppPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(appPricing);
		}
		catch (NoSuchAppPricingException nsee) {
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
	protected AppPricing removeImpl(AppPricing appPricing)
		throws SystemException {
		appPricing = toUnwrappedModel(appPricing);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, appPricing);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(appPricing);

		return appPricing;
	}

	@Override
	public AppPricing updateImpl(com.liferay.osb.model.AppPricing appPricing,
		boolean merge) throws SystemException {
		appPricing = toUnwrappedModel(appPricing);

		boolean isNew = appPricing.isNew();

		AppPricingModelImpl appPricingModelImpl = (AppPricingModelImpl)appPricing;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, appPricing, merge);

			appPricing.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AppPricingModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((appPricingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPricingModelImpl.getOriginalAppVersionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID,
					args);

				args = new Object[] {
						Long.valueOf(appPricingModelImpl.getAppVersionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID,
					args);
			}
		}

		EntityCacheUtil.putResult(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingImpl.class, appPricing.getPrimaryKey(), appPricing);

		return appPricing;
	}

	protected AppPricing toUnwrappedModel(AppPricing appPricing) {
		if (appPricing instanceof AppPricingImpl) {
			return appPricing;
		}

		AppPricingImpl appPricingImpl = new AppPricingImpl();

		appPricingImpl.setNew(appPricing.isNew());
		appPricingImpl.setPrimaryKey(appPricing.getPrimaryKey());

		appPricingImpl.setAppPricingId(appPricing.getAppPricingId());
		appPricingImpl.setUserId(appPricing.getUserId());
		appPricingImpl.setUserName(appPricing.getUserName());
		appPricingImpl.setCreateDate(appPricing.getCreateDate());
		appPricingImpl.setModifiedDate(appPricing.getModifiedDate());
		appPricingImpl.setAppEntryId(appPricing.getAppEntryId());
		appPricingImpl.setAppVersionId(appPricing.getAppVersionId());
		appPricingImpl.setName(appPricing.getName());
		appPricingImpl.setCurrencyEntryId(appPricing.getCurrencyEntryId());
		appPricingImpl.setStandardSupportPrice(appPricing.getStandardSupportPrice());
		appPricingImpl.setDeveloperSupportPrice(appPricing.getDeveloperSupportPrice());
		appPricingImpl.setRank(appPricing.getRank());

		return appPricingImpl;
	}

	/**
	 * Returns the app pricing with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the app pricing
	 * @return the app pricing
	 * @throws com.liferay.portal.NoSuchModelException if a app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPricing findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app pricing with the primary key or throws a {@link com.liferay.osb.NoSuchAppPricingException} if it could not be found.
	 *
	 * @param appPricingId the primary key of the app pricing
	 * @return the app pricing
	 * @throws com.liferay.osb.NoSuchAppPricingException if a app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricing findByPrimaryKey(long appPricingId)
		throws NoSuchAppPricingException, SystemException {
		AppPricing appPricing = fetchByPrimaryKey(appPricingId);

		if (appPricing == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + appPricingId);
			}

			throw new NoSuchAppPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				appPricingId);
		}

		return appPricing;
	}

	/**
	 * Returns the app pricing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the app pricing
	 * @return the app pricing, or <code>null</code> if a app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPricing fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app pricing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param appPricingId the primary key of the app pricing
	 * @return the app pricing, or <code>null</code> if a app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricing fetchByPrimaryKey(long appPricingId)
		throws SystemException {
		AppPricing appPricing = (AppPricing)EntityCacheUtil.getResult(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
				AppPricingImpl.class, appPricingId);

		if (appPricing == _nullAppPricing) {
			return null;
		}

		if (appPricing == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				appPricing = (AppPricing)session.get(AppPricingImpl.class,
						Long.valueOf(appPricingId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (appPricing != null) {
					cacheResult(appPricing);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AppPricingModelImpl.ENTITY_CACHE_ENABLED,
						AppPricingImpl.class, appPricingId, _nullAppPricing);
				}

				closeSession(session);
			}
		}

		return appPricing;
	}

	/**
	 * Returns all the app pricings where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @return the matching app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricing> findByAppVersionId(long appVersionId)
		throws SystemException {
		return findByAppVersionId(appVersionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app pricings where appVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appVersionId the app version ID
	 * @param start the lower bound of the range of app pricings
	 * @param end the upper bound of the range of app pricings (not inclusive)
	 * @return the range of matching app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricing> findByAppVersionId(long appVersionId, int start,
		int end) throws SystemException {
		return findByAppVersionId(appVersionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app pricings where appVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appVersionId the app version ID
	 * @param start the lower bound of the range of app pricings
	 * @param end the upper bound of the range of app pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricing> findByAppVersionId(long appVersionId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID;
			finderArgs = new Object[] { appVersionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPVERSIONID;
			finderArgs = new Object[] {
					appVersionId,
					
					start, end, orderByComparator
				};
		}

		List<AppPricing> list = (List<AppPricing>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPricing appPricing : list) {
				if ((appVersionId != appPricing.getAppVersionId())) {
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
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_APPPRICING_WHERE);

			query.append(_FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appVersionId);

				list = (List<AppPricing>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first app pricing in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app pricing
	 * @throws com.liferay.osb.NoSuchAppPricingException if a matching app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricing findByAppVersionId_First(long appVersionId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPricingException, SystemException {
		AppPricing appPricing = fetchByAppVersionId_First(appVersionId,
				orderByComparator);

		if (appPricing != null) {
			return appPricing;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appVersionId=");
		msg.append(appVersionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPricingException(msg.toString());
	}

	/**
	 * Returns the first app pricing in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app pricing, or <code>null</code> if a matching app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricing fetchByAppVersionId_First(long appVersionId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppPricing> list = findByAppVersionId(appVersionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app pricing in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app pricing
	 * @throws com.liferay.osb.NoSuchAppPricingException if a matching app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricing findByAppVersionId_Last(long appVersionId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPricingException, SystemException {
		AppPricing appPricing = fetchByAppVersionId_Last(appVersionId,
				orderByComparator);

		if (appPricing != null) {
			return appPricing;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appVersionId=");
		msg.append(appVersionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPricingException(msg.toString());
	}

	/**
	 * Returns the last app pricing in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app pricing, or <code>null</code> if a matching app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricing fetchByAppVersionId_Last(long appVersionId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAppVersionId(appVersionId);

		List<AppPricing> list = findByAppVersionId(appVersionId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app pricings before and after the current app pricing in the ordered set where appVersionId = &#63;.
	 *
	 * @param appPricingId the primary key of the current app pricing
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app pricing
	 * @throws com.liferay.osb.NoSuchAppPricingException if a app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricing[] findByAppVersionId_PrevAndNext(long appPricingId,
		long appVersionId, OrderByComparator orderByComparator)
		throws NoSuchAppPricingException, SystemException {
		AppPricing appPricing = findByPrimaryKey(appPricingId);

		Session session = null;

		try {
			session = openSession();

			AppPricing[] array = new AppPricingImpl[3];

			array[0] = getByAppVersionId_PrevAndNext(session, appPricing,
					appVersionId, orderByComparator, true);

			array[1] = appPricing;

			array[2] = getByAppVersionId_PrevAndNext(session, appPricing,
					appVersionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppPricing getByAppVersionId_PrevAndNext(Session session,
		AppPricing appPricing, long appVersionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPRICING_WHERE);

		query.append(_FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appVersionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPricing);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPricing> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app pricings.
	 *
	 * @return the app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricing> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app pricings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app pricings
	 * @param end the upper bound of the range of app pricings (not inclusive)
	 * @return the range of app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricing> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the app pricings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app pricings
	 * @param end the upper bound of the range of app pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricing> findAll(int start, int end,
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

		List<AppPricing> list = (List<AppPricing>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_APPPRICING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APPPRICING;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AppPricing>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AppPricing>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the app pricings where appVersionId = &#63; from the database.
	 *
	 * @param appVersionId the app version ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAppVersionId(long appVersionId)
		throws SystemException {
		for (AppPricing appPricing : findByAppVersionId(appVersionId)) {
			remove(appPricing);
		}
	}

	/**
	 * Removes all the app pricings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AppPricing appPricing : findAll()) {
			remove(appPricing);
		}
	}

	/**
	 * Returns the number of app pricings where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @return the number of matching app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAppVersionId(long appVersionId) throws SystemException {
		Object[] finderArgs = new Object[] { appVersionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPPRICING_WHERE);

			query.append(_FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appVersionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app pricings.
	 *
	 * @return the number of app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APPPRICING);

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
	 * Initializes the app pricing persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AppPricing")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AppPricing>> listenersList = new ArrayList<ModelListener<AppPricing>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AppPricing>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AppPricingImpl.class.getName());
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
	private static final String _SQL_SELECT_APPPRICING = "SELECT appPricing FROM AppPricing appPricing";
	private static final String _SQL_SELECT_APPPRICING_WHERE = "SELECT appPricing FROM AppPricing appPricing WHERE ";
	private static final String _SQL_COUNT_APPPRICING = "SELECT COUNT(appPricing) FROM AppPricing appPricing";
	private static final String _SQL_COUNT_APPPRICING_WHERE = "SELECT COUNT(appPricing) FROM AppPricing appPricing WHERE ";
	private static final String _FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2 = "appPricing.appVersionId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "appPricing.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AppPricing exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AppPricing exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AppPricingPersistenceImpl.class);
	private static AppPricing _nullAppPricing = new AppPricingImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AppPricing> toCacheModel() {
				return _nullAppPricingCacheModel;
			}
		};

	private static CacheModel<AppPricing> _nullAppPricingCacheModel = new CacheModel<AppPricing>() {
			public AppPricing toEntityModel() {
				return _nullAppPricing;
			}
		};
}
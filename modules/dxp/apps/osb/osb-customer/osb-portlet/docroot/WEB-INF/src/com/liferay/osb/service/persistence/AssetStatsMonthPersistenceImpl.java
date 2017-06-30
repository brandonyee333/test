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

import com.liferay.osb.NoSuchAssetStatsMonthException;
import com.liferay.osb.model.AssetStatsMonth;
import com.liferay.osb.model.impl.AssetStatsMonthImpl;
import com.liferay.osb.model.impl.AssetStatsMonthModelImpl;

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
 * The persistence implementation for the asset stats month service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsMonthPersistence
 * @see AssetStatsMonthUtil
 * @generated
 */
public class AssetStatsMonthPersistenceImpl extends BasePersistenceImpl<AssetStatsMonth>
	implements AssetStatsMonthPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetStatsMonthUtil} to access the asset stats month persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetStatsMonthImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsMonthModelImpl.FINDER_CACHE_ENABLED,
			AssetStatsMonthImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsMonthModelImpl.FINDER_CACHE_ENABLED,
			AssetStatsMonthImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetStatsMonthModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetStatsMonthModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsMonthModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C_M_Y = new FinderPath(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsMonthModelImpl.FINDER_CACHE_ENABLED,
			AssetStatsMonthImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C_M_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			AssetStatsMonthModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetStatsMonthModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetStatsMonthModelImpl.MONTH_COLUMN_BITMASK |
			AssetStatsMonthModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_M_Y = new FinderPath(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsMonthModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_M_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsMonthModelImpl.FINDER_CACHE_ENABLED,
			AssetStatsMonthImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsMonthModelImpl.FINDER_CACHE_ENABLED,
			AssetStatsMonthImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsMonthModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset stats month in the entity cache if it is enabled.
	 *
	 * @param assetStatsMonth the asset stats month
	 */
	public void cacheResult(AssetStatsMonth assetStatsMonth) {
		EntityCacheUtil.putResult(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsMonthImpl.class, assetStatsMonth.getPrimaryKey(),
			assetStatsMonth);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_M_Y,
			new Object[] {
				Long.valueOf(assetStatsMonth.getClassNameId()),
				Long.valueOf(assetStatsMonth.getClassPK()),
				Integer.valueOf(assetStatsMonth.getMonth()),
				Integer.valueOf(assetStatsMonth.getYear())
			}, assetStatsMonth);

		assetStatsMonth.resetOriginalValues();
	}

	/**
	 * Caches the asset stats months in the entity cache if it is enabled.
	 *
	 * @param assetStatsMonths the asset stats months
	 */
	public void cacheResult(List<AssetStatsMonth> assetStatsMonths) {
		for (AssetStatsMonth assetStatsMonth : assetStatsMonths) {
			if (EntityCacheUtil.getResult(
						AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
						AssetStatsMonthImpl.class,
						assetStatsMonth.getPrimaryKey()) == null) {
				cacheResult(assetStatsMonth);
			}
			else {
				assetStatsMonth.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset stats months.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetStatsMonthImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetStatsMonthImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset stats month.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetStatsMonth assetStatsMonth) {
		EntityCacheUtil.removeResult(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsMonthImpl.class, assetStatsMonth.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(assetStatsMonth);
	}

	@Override
	public void clearCache(List<AssetStatsMonth> assetStatsMonths) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetStatsMonth assetStatsMonth : assetStatsMonths) {
			EntityCacheUtil.removeResult(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
				AssetStatsMonthImpl.class, assetStatsMonth.getPrimaryKey());

			clearUniqueFindersCache(assetStatsMonth);
		}
	}

	protected void cacheUniqueFindersCache(AssetStatsMonth assetStatsMonth) {
		if (assetStatsMonth.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(assetStatsMonth.getClassNameId()),
					Long.valueOf(assetStatsMonth.getClassPK()),
					Integer.valueOf(assetStatsMonth.getMonth()),
					Integer.valueOf(assetStatsMonth.getYear())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_M_Y, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_M_Y, args,
				assetStatsMonth);
		}
		else {
			AssetStatsMonthModelImpl assetStatsMonthModelImpl = (AssetStatsMonthModelImpl)assetStatsMonth;

			if ((assetStatsMonthModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_C_M_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetStatsMonth.getClassNameId()),
						Long.valueOf(assetStatsMonth.getClassPK()),
						Integer.valueOf(assetStatsMonth.getMonth()),
						Integer.valueOf(assetStatsMonth.getYear())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_M_Y, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_M_Y, args,
					assetStatsMonth);
			}
		}
	}

	protected void clearUniqueFindersCache(AssetStatsMonth assetStatsMonth) {
		AssetStatsMonthModelImpl assetStatsMonthModelImpl = (AssetStatsMonthModelImpl)assetStatsMonth;

		Object[] args = new Object[] {
				Long.valueOf(assetStatsMonth.getClassNameId()),
				Long.valueOf(assetStatsMonth.getClassPK()),
				Integer.valueOf(assetStatsMonth.getMonth()),
				Integer.valueOf(assetStatsMonth.getYear())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_M_Y, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_M_Y, args);

		if ((assetStatsMonthModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C_M_Y.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(assetStatsMonthModelImpl.getOriginalClassNameId()),
					Long.valueOf(assetStatsMonthModelImpl.getOriginalClassPK()),
					Integer.valueOf(assetStatsMonthModelImpl.getOriginalMonth()),
					Integer.valueOf(assetStatsMonthModelImpl.getOriginalYear())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_M_Y, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_M_Y, args);
		}
	}

	/**
	 * Creates a new asset stats month with the primary key. Does not add the asset stats month to the database.
	 *
	 * @param assetStatsMonthId the primary key for the new asset stats month
	 * @return the new asset stats month
	 */
	public AssetStatsMonth create(long assetStatsMonthId) {
		AssetStatsMonth assetStatsMonth = new AssetStatsMonthImpl();

		assetStatsMonth.setNew(true);
		assetStatsMonth.setPrimaryKey(assetStatsMonthId);

		return assetStatsMonth;
	}

	/**
	 * Removes the asset stats month with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetStatsMonthId the primary key of the asset stats month
	 * @return the asset stats month that was removed
	 * @throws com.liferay.osb.NoSuchAssetStatsMonthException if a asset stats month with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth remove(long assetStatsMonthId)
		throws NoSuchAssetStatsMonthException, SystemException {
		return remove(Long.valueOf(assetStatsMonthId));
	}

	/**
	 * Removes the asset stats month with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset stats month
	 * @return the asset stats month that was removed
	 * @throws com.liferay.osb.NoSuchAssetStatsMonthException if a asset stats month with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetStatsMonth remove(Serializable primaryKey)
		throws NoSuchAssetStatsMonthException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetStatsMonth assetStatsMonth = (AssetStatsMonth)session.get(AssetStatsMonthImpl.class,
					primaryKey);

			if (assetStatsMonth == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetStatsMonthException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetStatsMonth);
		}
		catch (NoSuchAssetStatsMonthException nsee) {
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
	protected AssetStatsMonth removeImpl(AssetStatsMonth assetStatsMonth)
		throws SystemException {
		assetStatsMonth = toUnwrappedModel(assetStatsMonth);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetStatsMonth);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetStatsMonth);

		return assetStatsMonth;
	}

	@Override
	public AssetStatsMonth updateImpl(
		com.liferay.osb.model.AssetStatsMonth assetStatsMonth, boolean merge)
		throws SystemException {
		assetStatsMonth = toUnwrappedModel(assetStatsMonth);

		boolean isNew = assetStatsMonth.isNew();

		AssetStatsMonthModelImpl assetStatsMonthModelImpl = (AssetStatsMonthModelImpl)assetStatsMonth;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetStatsMonth, merge);

			assetStatsMonth.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetStatsMonthModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetStatsMonthModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetStatsMonthModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetStatsMonthModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						Long.valueOf(assetStatsMonthModelImpl.getClassNameId()),
						Long.valueOf(assetStatsMonthModelImpl.getClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsMonthImpl.class, assetStatsMonth.getPrimaryKey(),
			assetStatsMonth);

		clearUniqueFindersCache(assetStatsMonth);
		cacheUniqueFindersCache(assetStatsMonth);

		return assetStatsMonth;
	}

	protected AssetStatsMonth toUnwrappedModel(AssetStatsMonth assetStatsMonth) {
		if (assetStatsMonth instanceof AssetStatsMonthImpl) {
			return assetStatsMonth;
		}

		AssetStatsMonthImpl assetStatsMonthImpl = new AssetStatsMonthImpl();

		assetStatsMonthImpl.setNew(assetStatsMonth.isNew());
		assetStatsMonthImpl.setPrimaryKey(assetStatsMonth.getPrimaryKey());

		assetStatsMonthImpl.setAssetStatsMonthId(assetStatsMonth.getAssetStatsMonthId());
		assetStatsMonthImpl.setClassNameId(assetStatsMonth.getClassNameId());
		assetStatsMonthImpl.setClassPK(assetStatsMonth.getClassPK());
		assetStatsMonthImpl.setMonth(assetStatsMonth.getMonth());
		assetStatsMonthImpl.setYear(assetStatsMonth.getYear());
		assetStatsMonthImpl.setViewCount(assetStatsMonth.getViewCount());
		assetStatsMonthImpl.setDownloadCount(assetStatsMonth.getDownloadCount());
		assetStatsMonthImpl.setPurchaseCount(assetStatsMonth.getPurchaseCount());
		assetStatsMonthImpl.setCurrencyCodeRevenues(assetStatsMonth.getCurrencyCodeRevenues());

		return assetStatsMonthImpl;
	}

	/**
	 * Returns the asset stats month with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset stats month
	 * @return the asset stats month
	 * @throws com.liferay.portal.NoSuchModelException if a asset stats month with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetStatsMonth findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset stats month with the primary key or throws a {@link com.liferay.osb.NoSuchAssetStatsMonthException} if it could not be found.
	 *
	 * @param assetStatsMonthId the primary key of the asset stats month
	 * @return the asset stats month
	 * @throws com.liferay.osb.NoSuchAssetStatsMonthException if a asset stats month with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth findByPrimaryKey(long assetStatsMonthId)
		throws NoSuchAssetStatsMonthException, SystemException {
		AssetStatsMonth assetStatsMonth = fetchByPrimaryKey(assetStatsMonthId);

		if (assetStatsMonth == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + assetStatsMonthId);
			}

			throw new NoSuchAssetStatsMonthException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetStatsMonthId);
		}

		return assetStatsMonth;
	}

	/**
	 * Returns the asset stats month with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset stats month
	 * @return the asset stats month, or <code>null</code> if a asset stats month with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetStatsMonth fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset stats month with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetStatsMonthId the primary key of the asset stats month
	 * @return the asset stats month, or <code>null</code> if a asset stats month with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth fetchByPrimaryKey(long assetStatsMonthId)
		throws SystemException {
		AssetStatsMonth assetStatsMonth = (AssetStatsMonth)EntityCacheUtil.getResult(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
				AssetStatsMonthImpl.class, assetStatsMonthId);

		if (assetStatsMonth == _nullAssetStatsMonth) {
			return null;
		}

		if (assetStatsMonth == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetStatsMonth = (AssetStatsMonth)session.get(AssetStatsMonthImpl.class,
						Long.valueOf(assetStatsMonthId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetStatsMonth != null) {
					cacheResult(assetStatsMonth);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetStatsMonthModelImpl.ENTITY_CACHE_ENABLED,
						AssetStatsMonthImpl.class, assetStatsMonthId,
						_nullAssetStatsMonth);
				}

				closeSession(session);
			}
		}

		return assetStatsMonth;
	}

	/**
	 * Returns all the asset stats months where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching asset stats months
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsMonth> findByC_C(long classNameId, long classPK)
		throws SystemException {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset stats months where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset stats months
	 * @param end the upper bound of the range of asset stats months (not inclusive)
	 * @return the range of matching asset stats months
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsMonth> findByC_C(long classNameId, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset stats months where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset stats months
	 * @param end the upper bound of the range of asset stats months (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset stats months
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsMonth> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] { classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] {
					classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<AssetStatsMonth> list = (List<AssetStatsMonth>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetStatsMonth assetStatsMonth : list) {
				if ((classNameId != assetStatsMonth.getClassNameId()) ||
						(classPK != assetStatsMonth.getClassPK())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETSTATSMONTH_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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

				qPos.add(classNameId);

				qPos.add(classPK);

				list = (List<AssetStatsMonth>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset stats month
	 * @throws com.liferay.osb.NoSuchAssetStatsMonthException if a matching asset stats month could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth findByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetStatsMonthException, SystemException {
		AssetStatsMonth assetStatsMonth = fetchByC_C_First(classNameId,
				classPK, orderByComparator);

		if (assetStatsMonth != null) {
			return assetStatsMonth;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetStatsMonthException(msg.toString());
	}

	/**
	 * Returns the first asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset stats month, or <code>null</code> if a matching asset stats month could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetStatsMonth> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset stats month
	 * @throws com.liferay.osb.NoSuchAssetStatsMonthException if a matching asset stats month could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth findByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetStatsMonthException, SystemException {
		AssetStatsMonth assetStatsMonth = fetchByC_C_Last(classNameId, classPK,
				orderByComparator);

		if (assetStatsMonth != null) {
			return assetStatsMonth;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetStatsMonthException(msg.toString());
	}

	/**
	 * Returns the last asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset stats month, or <code>null</code> if a matching asset stats month could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C(classNameId, classPK);

		List<AssetStatsMonth> list = findByC_C(classNameId, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset stats months before and after the current asset stats month in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param assetStatsMonthId the primary key of the current asset stats month
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset stats month
	 * @throws com.liferay.osb.NoSuchAssetStatsMonthException if a asset stats month with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth[] findByC_C_PrevAndNext(long assetStatsMonthId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchAssetStatsMonthException, SystemException {
		AssetStatsMonth assetStatsMonth = findByPrimaryKey(assetStatsMonthId);

		Session session = null;

		try {
			session = openSession();

			AssetStatsMonth[] array = new AssetStatsMonthImpl[3];

			array[0] = getByC_C_PrevAndNext(session, assetStatsMonth,
					classNameId, classPK, orderByComparator, true);

			array[1] = assetStatsMonth;

			array[2] = getByC_C_PrevAndNext(session, assetStatsMonth,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetStatsMonth getByC_C_PrevAndNext(Session session,
		AssetStatsMonth assetStatsMonth, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETSTATSMONTH_WHERE);

		query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetStatsMonth);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetStatsMonth> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the asset stats month where classNameId = &#63; and classPK = &#63; and month = &#63; and year = &#63; or throws a {@link com.liferay.osb.NoSuchAssetStatsMonthException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param month the month
	 * @param year the year
	 * @return the matching asset stats month
	 * @throws com.liferay.osb.NoSuchAssetStatsMonthException if a matching asset stats month could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth findByC_C_M_Y(long classNameId, long classPK,
		int month, int year)
		throws NoSuchAssetStatsMonthException, SystemException {
		AssetStatsMonth assetStatsMonth = fetchByC_C_M_Y(classNameId, classPK,
				month, year);

		if (assetStatsMonth == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", month=");
			msg.append(month);

			msg.append(", year=");
			msg.append(year);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAssetStatsMonthException(msg.toString());
		}

		return assetStatsMonth;
	}

	/**
	 * Returns the asset stats month where classNameId = &#63; and classPK = &#63; and month = &#63; and year = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param month the month
	 * @param year the year
	 * @return the matching asset stats month, or <code>null</code> if a matching asset stats month could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth fetchByC_C_M_Y(long classNameId, long classPK,
		int month, int year) throws SystemException {
		return fetchByC_C_M_Y(classNameId, classPK, month, year, true);
	}

	/**
	 * Returns the asset stats month where classNameId = &#63; and classPK = &#63; and month = &#63; and year = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param month the month
	 * @param year the year
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset stats month, or <code>null</code> if a matching asset stats month could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth fetchByC_C_M_Y(long classNameId, long classPK,
		int month, int year, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, month, year };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C_M_Y,
					finderArgs, this);
		}

		if (result instanceof AssetStatsMonth) {
			AssetStatsMonth assetStatsMonth = (AssetStatsMonth)result;

			if ((classNameId != assetStatsMonth.getClassNameId()) ||
					(classPK != assetStatsMonth.getClassPK()) ||
					(month != assetStatsMonth.getMonth()) ||
					(year != assetStatsMonth.getYear())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_ASSETSTATSMONTH_WHERE);

			query.append(_FINDER_COLUMN_C_C_M_Y_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_M_Y_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_M_Y_MONTH_2);

			query.append(_FINDER_COLUMN_C_C_M_Y_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(month);

				qPos.add(year);

				List<AssetStatsMonth> list = q.list();

				result = list;

				AssetStatsMonth assetStatsMonth = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_M_Y,
						finderArgs, list);
				}
				else {
					assetStatsMonth = list.get(0);

					cacheResult(assetStatsMonth);

					if ((assetStatsMonth.getClassNameId() != classNameId) ||
							(assetStatsMonth.getClassPK() != classPK) ||
							(assetStatsMonth.getMonth() != month) ||
							(assetStatsMonth.getYear() != year)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_M_Y,
							finderArgs, assetStatsMonth);
					}
				}

				return assetStatsMonth;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_M_Y,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (AssetStatsMonth)result;
			}
		}
	}

	/**
	 * Returns all the asset stats months.
	 *
	 * @return the asset stats months
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsMonth> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset stats months.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset stats months
	 * @param end the upper bound of the range of asset stats months (not inclusive)
	 * @return the range of asset stats months
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsMonth> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset stats months.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset stats months
	 * @param end the upper bound of the range of asset stats months (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset stats months
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsMonth> findAll(int start, int end,
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

		List<AssetStatsMonth> list = (List<AssetStatsMonth>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETSTATSMONTH);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETSTATSMONTH;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetStatsMonth>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetStatsMonth>)QueryUtil.list(q,
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
	 * Removes all the asset stats months where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long classNameId, long classPK)
		throws SystemException {
		for (AssetStatsMonth assetStatsMonth : findByC_C(classNameId, classPK)) {
			remove(assetStatsMonth);
		}
	}

	/**
	 * Removes the asset stats month where classNameId = &#63; and classPK = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param month the month
	 * @param year the year
	 * @return the asset stats month that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsMonth removeByC_C_M_Y(long classNameId, long classPK,
		int month, int year)
		throws NoSuchAssetStatsMonthException, SystemException {
		AssetStatsMonth assetStatsMonth = findByC_C_M_Y(classNameId, classPK,
				month, year);

		return remove(assetStatsMonth);
	}

	/**
	 * Removes all the asset stats months from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetStatsMonth assetStatsMonth : findAll()) {
			remove(assetStatsMonth);
		}
	}

	/**
	 * Returns the number of asset stats months where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching asset stats months
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETSTATSMONTH_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset stats months where classNameId = &#63; and classPK = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param month the month
	 * @param year the year
	 * @return the number of matching asset stats months
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_M_Y(long classNameId, long classPK, int month,
		int year) throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, month, year };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_M_Y,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETSTATSMONTH_WHERE);

			query.append(_FINDER_COLUMN_C_C_M_Y_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_M_Y_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_M_Y_MONTH_2);

			query.append(_FINDER_COLUMN_C_C_M_Y_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(month);

				qPos.add(year);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_M_Y,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset stats months.
	 *
	 * @return the number of asset stats months
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETSTATSMONTH);

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
	 * Initializes the asset stats month persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetStatsMonth")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetStatsMonth>> listenersList = new ArrayList<ModelListener<AssetStatsMonth>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetStatsMonth>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetStatsMonthImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSETSTATSMONTH = "SELECT assetStatsMonth FROM AssetStatsMonth assetStatsMonth";
	private static final String _SQL_SELECT_ASSETSTATSMONTH_WHERE = "SELECT assetStatsMonth FROM AssetStatsMonth assetStatsMonth WHERE ";
	private static final String _SQL_COUNT_ASSETSTATSMONTH = "SELECT COUNT(assetStatsMonth) FROM AssetStatsMonth assetStatsMonth";
	private static final String _SQL_COUNT_ASSETSTATSMONTH_WHERE = "SELECT COUNT(assetStatsMonth) FROM AssetStatsMonth assetStatsMonth WHERE ";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "assetStatsMonth.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "assetStatsMonth.classPK = ?";
	private static final String _FINDER_COLUMN_C_C_M_Y_CLASSNAMEID_2 = "assetStatsMonth.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_M_Y_CLASSPK_2 = "assetStatsMonth.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_M_Y_MONTH_2 = "assetStatsMonth.month = ? AND ";
	private static final String _FINDER_COLUMN_C_C_M_Y_YEAR_2 = "assetStatsMonth.year = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetStatsMonth.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetStatsMonth exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetStatsMonth exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetStatsMonthPersistenceImpl.class);
	private static AssetStatsMonth _nullAssetStatsMonth = new AssetStatsMonthImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetStatsMonth> toCacheModel() {
				return _nullAssetStatsMonthCacheModel;
			}
		};

	private static CacheModel<AssetStatsMonth> _nullAssetStatsMonthCacheModel = new CacheModel<AssetStatsMonth>() {
			public AssetStatsMonth toEntityModel() {
				return _nullAssetStatsMonth;
			}
		};
}
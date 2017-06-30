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

import com.liferay.osb.NoSuchAssetStatsDayException;
import com.liferay.osb.model.AssetStatsDay;
import com.liferay.osb.model.impl.AssetStatsDayImpl;
import com.liferay.osb.model.impl.AssetStatsDayModelImpl;

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
 * The persistence implementation for the asset stats day service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsDayPersistence
 * @see AssetStatsDayUtil
 * @generated
 */
public class AssetStatsDayPersistenceImpl extends BasePersistenceImpl<AssetStatsDay>
	implements AssetStatsDayPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetStatsDayUtil} to access the asset stats day persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetStatsDayImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsDayModelImpl.FINDER_CACHE_ENABLED,
			AssetStatsDayImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsDayModelImpl.FINDER_CACHE_ENABLED,
			AssetStatsDayImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetStatsDayModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetStatsDayModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsDayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C_D_Y = new FinderPath(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsDayModelImpl.FINDER_CACHE_ENABLED,
			AssetStatsDayImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C_D_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			AssetStatsDayModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetStatsDayModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetStatsDayModelImpl.DAY_COLUMN_BITMASK |
			AssetStatsDayModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_D_Y = new FinderPath(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsDayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_D_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsDayModelImpl.FINDER_CACHE_ENABLED,
			AssetStatsDayImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsDayModelImpl.FINDER_CACHE_ENABLED,
			AssetStatsDayImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsDayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset stats day in the entity cache if it is enabled.
	 *
	 * @param assetStatsDay the asset stats day
	 */
	public void cacheResult(AssetStatsDay assetStatsDay) {
		EntityCacheUtil.putResult(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsDayImpl.class, assetStatsDay.getPrimaryKey(),
			assetStatsDay);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_D_Y,
			new Object[] {
				Long.valueOf(assetStatsDay.getClassNameId()),
				Long.valueOf(assetStatsDay.getClassPK()),
				Integer.valueOf(assetStatsDay.getDay()),
				Integer.valueOf(assetStatsDay.getYear())
			}, assetStatsDay);

		assetStatsDay.resetOriginalValues();
	}

	/**
	 * Caches the asset stats daies in the entity cache if it is enabled.
	 *
	 * @param assetStatsDaies the asset stats daies
	 */
	public void cacheResult(List<AssetStatsDay> assetStatsDaies) {
		for (AssetStatsDay assetStatsDay : assetStatsDaies) {
			if (EntityCacheUtil.getResult(
						AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
						AssetStatsDayImpl.class, assetStatsDay.getPrimaryKey()) == null) {
				cacheResult(assetStatsDay);
			}
			else {
				assetStatsDay.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset stats daies.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetStatsDayImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetStatsDayImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset stats day.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetStatsDay assetStatsDay) {
		EntityCacheUtil.removeResult(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsDayImpl.class, assetStatsDay.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(assetStatsDay);
	}

	@Override
	public void clearCache(List<AssetStatsDay> assetStatsDaies) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetStatsDay assetStatsDay : assetStatsDaies) {
			EntityCacheUtil.removeResult(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
				AssetStatsDayImpl.class, assetStatsDay.getPrimaryKey());

			clearUniqueFindersCache(assetStatsDay);
		}
	}

	protected void cacheUniqueFindersCache(AssetStatsDay assetStatsDay) {
		if (assetStatsDay.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(assetStatsDay.getClassNameId()),
					Long.valueOf(assetStatsDay.getClassPK()),
					Integer.valueOf(assetStatsDay.getDay()),
					Integer.valueOf(assetStatsDay.getYear())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_D_Y, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_D_Y, args,
				assetStatsDay);
		}
		else {
			AssetStatsDayModelImpl assetStatsDayModelImpl = (AssetStatsDayModelImpl)assetStatsDay;

			if ((assetStatsDayModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_C_D_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetStatsDay.getClassNameId()),
						Long.valueOf(assetStatsDay.getClassPK()),
						Integer.valueOf(assetStatsDay.getDay()),
						Integer.valueOf(assetStatsDay.getYear())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_D_Y, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_D_Y, args,
					assetStatsDay);
			}
		}
	}

	protected void clearUniqueFindersCache(AssetStatsDay assetStatsDay) {
		AssetStatsDayModelImpl assetStatsDayModelImpl = (AssetStatsDayModelImpl)assetStatsDay;

		Object[] args = new Object[] {
				Long.valueOf(assetStatsDay.getClassNameId()),
				Long.valueOf(assetStatsDay.getClassPK()),
				Integer.valueOf(assetStatsDay.getDay()),
				Integer.valueOf(assetStatsDay.getYear())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_D_Y, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_D_Y, args);

		if ((assetStatsDayModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C_D_Y.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(assetStatsDayModelImpl.getOriginalClassNameId()),
					Long.valueOf(assetStatsDayModelImpl.getOriginalClassPK()),
					Integer.valueOf(assetStatsDayModelImpl.getOriginalDay()),
					Integer.valueOf(assetStatsDayModelImpl.getOriginalYear())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_D_Y, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_D_Y, args);
		}
	}

	/**
	 * Creates a new asset stats day with the primary key. Does not add the asset stats day to the database.
	 *
	 * @param assetStatsDayId the primary key for the new asset stats day
	 * @return the new asset stats day
	 */
	public AssetStatsDay create(long assetStatsDayId) {
		AssetStatsDay assetStatsDay = new AssetStatsDayImpl();

		assetStatsDay.setNew(true);
		assetStatsDay.setPrimaryKey(assetStatsDayId);

		return assetStatsDay;
	}

	/**
	 * Removes the asset stats day with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetStatsDayId the primary key of the asset stats day
	 * @return the asset stats day that was removed
	 * @throws com.liferay.osb.NoSuchAssetStatsDayException if a asset stats day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay remove(long assetStatsDayId)
		throws NoSuchAssetStatsDayException, SystemException {
		return remove(Long.valueOf(assetStatsDayId));
	}

	/**
	 * Removes the asset stats day with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset stats day
	 * @return the asset stats day that was removed
	 * @throws com.liferay.osb.NoSuchAssetStatsDayException if a asset stats day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetStatsDay remove(Serializable primaryKey)
		throws NoSuchAssetStatsDayException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetStatsDay assetStatsDay = (AssetStatsDay)session.get(AssetStatsDayImpl.class,
					primaryKey);

			if (assetStatsDay == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetStatsDayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetStatsDay);
		}
		catch (NoSuchAssetStatsDayException nsee) {
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
	protected AssetStatsDay removeImpl(AssetStatsDay assetStatsDay)
		throws SystemException {
		assetStatsDay = toUnwrappedModel(assetStatsDay);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetStatsDay);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetStatsDay);

		return assetStatsDay;
	}

	@Override
	public AssetStatsDay updateImpl(
		com.liferay.osb.model.AssetStatsDay assetStatsDay, boolean merge)
		throws SystemException {
		assetStatsDay = toUnwrappedModel(assetStatsDay);

		boolean isNew = assetStatsDay.isNew();

		AssetStatsDayModelImpl assetStatsDayModelImpl = (AssetStatsDayModelImpl)assetStatsDay;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetStatsDay, merge);

			assetStatsDay.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetStatsDayModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetStatsDayModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetStatsDayModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetStatsDayModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						Long.valueOf(assetStatsDayModelImpl.getClassNameId()),
						Long.valueOf(assetStatsDayModelImpl.getClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
			AssetStatsDayImpl.class, assetStatsDay.getPrimaryKey(),
			assetStatsDay);

		clearUniqueFindersCache(assetStatsDay);
		cacheUniqueFindersCache(assetStatsDay);

		return assetStatsDay;
	}

	protected AssetStatsDay toUnwrappedModel(AssetStatsDay assetStatsDay) {
		if (assetStatsDay instanceof AssetStatsDayImpl) {
			return assetStatsDay;
		}

		AssetStatsDayImpl assetStatsDayImpl = new AssetStatsDayImpl();

		assetStatsDayImpl.setNew(assetStatsDay.isNew());
		assetStatsDayImpl.setPrimaryKey(assetStatsDay.getPrimaryKey());

		assetStatsDayImpl.setAssetStatsDayId(assetStatsDay.getAssetStatsDayId());
		assetStatsDayImpl.setClassNameId(assetStatsDay.getClassNameId());
		assetStatsDayImpl.setClassPK(assetStatsDay.getClassPK());
		assetStatsDayImpl.setDay(assetStatsDay.getDay());
		assetStatsDayImpl.setYear(assetStatsDay.getYear());
		assetStatsDayImpl.setViewCount(assetStatsDay.getViewCount());
		assetStatsDayImpl.setDownloadCount(assetStatsDay.getDownloadCount());
		assetStatsDayImpl.setPurchaseCount(assetStatsDay.getPurchaseCount());
		assetStatsDayImpl.setCurrencyCodeRevenues(assetStatsDay.getCurrencyCodeRevenues());

		return assetStatsDayImpl;
	}

	/**
	 * Returns the asset stats day with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset stats day
	 * @return the asset stats day
	 * @throws com.liferay.portal.NoSuchModelException if a asset stats day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetStatsDay findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset stats day with the primary key or throws a {@link com.liferay.osb.NoSuchAssetStatsDayException} if it could not be found.
	 *
	 * @param assetStatsDayId the primary key of the asset stats day
	 * @return the asset stats day
	 * @throws com.liferay.osb.NoSuchAssetStatsDayException if a asset stats day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay findByPrimaryKey(long assetStatsDayId)
		throws NoSuchAssetStatsDayException, SystemException {
		AssetStatsDay assetStatsDay = fetchByPrimaryKey(assetStatsDayId);

		if (assetStatsDay == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + assetStatsDayId);
			}

			throw new NoSuchAssetStatsDayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetStatsDayId);
		}

		return assetStatsDay;
	}

	/**
	 * Returns the asset stats day with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset stats day
	 * @return the asset stats day, or <code>null</code> if a asset stats day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetStatsDay fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset stats day with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetStatsDayId the primary key of the asset stats day
	 * @return the asset stats day, or <code>null</code> if a asset stats day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay fetchByPrimaryKey(long assetStatsDayId)
		throws SystemException {
		AssetStatsDay assetStatsDay = (AssetStatsDay)EntityCacheUtil.getResult(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
				AssetStatsDayImpl.class, assetStatsDayId);

		if (assetStatsDay == _nullAssetStatsDay) {
			return null;
		}

		if (assetStatsDay == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetStatsDay = (AssetStatsDay)session.get(AssetStatsDayImpl.class,
						Long.valueOf(assetStatsDayId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetStatsDay != null) {
					cacheResult(assetStatsDay);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetStatsDayModelImpl.ENTITY_CACHE_ENABLED,
						AssetStatsDayImpl.class, assetStatsDayId,
						_nullAssetStatsDay);
				}

				closeSession(session);
			}
		}

		return assetStatsDay;
	}

	/**
	 * Returns all the asset stats daies where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching asset stats daies
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsDay> findByC_C(long classNameId, long classPK)
		throws SystemException {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset stats daies where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset stats daies
	 * @param end the upper bound of the range of asset stats daies (not inclusive)
	 * @return the range of matching asset stats daies
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsDay> findByC_C(long classNameId, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset stats daies where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset stats daies
	 * @param end the upper bound of the range of asset stats daies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset stats daies
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsDay> findByC_C(long classNameId, long classPK,
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

		List<AssetStatsDay> list = (List<AssetStatsDay>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetStatsDay assetStatsDay : list) {
				if ((classNameId != assetStatsDay.getClassNameId()) ||
						(classPK != assetStatsDay.getClassPK())) {
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

			query.append(_SQL_SELECT_ASSETSTATSDAY_WHERE);

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

				list = (List<AssetStatsDay>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset stats day
	 * @throws com.liferay.osb.NoSuchAssetStatsDayException if a matching asset stats day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay findByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetStatsDayException, SystemException {
		AssetStatsDay assetStatsDay = fetchByC_C_First(classNameId, classPK,
				orderByComparator);

		if (assetStatsDay != null) {
			return assetStatsDay;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetStatsDayException(msg.toString());
	}

	/**
	 * Returns the first asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset stats day, or <code>null</code> if a matching asset stats day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetStatsDay> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset stats day
	 * @throws com.liferay.osb.NoSuchAssetStatsDayException if a matching asset stats day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay findByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetStatsDayException, SystemException {
		AssetStatsDay assetStatsDay = fetchByC_C_Last(classNameId, classPK,
				orderByComparator);

		if (assetStatsDay != null) {
			return assetStatsDay;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetStatsDayException(msg.toString());
	}

	/**
	 * Returns the last asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset stats day, or <code>null</code> if a matching asset stats day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C(classNameId, classPK);

		List<AssetStatsDay> list = findByC_C(classNameId, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset stats daies before and after the current asset stats day in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param assetStatsDayId the primary key of the current asset stats day
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset stats day
	 * @throws com.liferay.osb.NoSuchAssetStatsDayException if a asset stats day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay[] findByC_C_PrevAndNext(long assetStatsDayId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchAssetStatsDayException, SystemException {
		AssetStatsDay assetStatsDay = findByPrimaryKey(assetStatsDayId);

		Session session = null;

		try {
			session = openSession();

			AssetStatsDay[] array = new AssetStatsDayImpl[3];

			array[0] = getByC_C_PrevAndNext(session, assetStatsDay,
					classNameId, classPK, orderByComparator, true);

			array[1] = assetStatsDay;

			array[2] = getByC_C_PrevAndNext(session, assetStatsDay,
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

	protected AssetStatsDay getByC_C_PrevAndNext(Session session,
		AssetStatsDay assetStatsDay, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETSTATSDAY_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(assetStatsDay);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetStatsDay> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the asset stats day where classNameId = &#63; and classPK = &#63; and day = &#63; and year = &#63; or throws a {@link com.liferay.osb.NoSuchAssetStatsDayException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param day the day
	 * @param year the year
	 * @return the matching asset stats day
	 * @throws com.liferay.osb.NoSuchAssetStatsDayException if a matching asset stats day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay findByC_C_D_Y(long classNameId, long classPK, int day,
		int year) throws NoSuchAssetStatsDayException, SystemException {
		AssetStatsDay assetStatsDay = fetchByC_C_D_Y(classNameId, classPK, day,
				year);

		if (assetStatsDay == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", day=");
			msg.append(day);

			msg.append(", year=");
			msg.append(year);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAssetStatsDayException(msg.toString());
		}

		return assetStatsDay;
	}

	/**
	 * Returns the asset stats day where classNameId = &#63; and classPK = &#63; and day = &#63; and year = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param day the day
	 * @param year the year
	 * @return the matching asset stats day, or <code>null</code> if a matching asset stats day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay fetchByC_C_D_Y(long classNameId, long classPK,
		int day, int year) throws SystemException {
		return fetchByC_C_D_Y(classNameId, classPK, day, year, true);
	}

	/**
	 * Returns the asset stats day where classNameId = &#63; and classPK = &#63; and day = &#63; and year = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param day the day
	 * @param year the year
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset stats day, or <code>null</code> if a matching asset stats day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay fetchByC_C_D_Y(long classNameId, long classPK,
		int day, int year, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, day, year };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C_D_Y,
					finderArgs, this);
		}

		if (result instanceof AssetStatsDay) {
			AssetStatsDay assetStatsDay = (AssetStatsDay)result;

			if ((classNameId != assetStatsDay.getClassNameId()) ||
					(classPK != assetStatsDay.getClassPK()) ||
					(day != assetStatsDay.getDay()) ||
					(year != assetStatsDay.getYear())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_ASSETSTATSDAY_WHERE);

			query.append(_FINDER_COLUMN_C_C_D_Y_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_D_Y_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_D_Y_DAY_2);

			query.append(_FINDER_COLUMN_C_C_D_Y_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(day);

				qPos.add(year);

				List<AssetStatsDay> list = q.list();

				result = list;

				AssetStatsDay assetStatsDay = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_D_Y,
						finderArgs, list);
				}
				else {
					assetStatsDay = list.get(0);

					cacheResult(assetStatsDay);

					if ((assetStatsDay.getClassNameId() != classNameId) ||
							(assetStatsDay.getClassPK() != classPK) ||
							(assetStatsDay.getDay() != day) ||
							(assetStatsDay.getYear() != year)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_D_Y,
							finderArgs, assetStatsDay);
					}
				}

				return assetStatsDay;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_D_Y,
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
				return (AssetStatsDay)result;
			}
		}
	}

	/**
	 * Returns all the asset stats daies.
	 *
	 * @return the asset stats daies
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsDay> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset stats daies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset stats daies
	 * @param end the upper bound of the range of asset stats daies (not inclusive)
	 * @return the range of asset stats daies
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsDay> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset stats daies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset stats daies
	 * @param end the upper bound of the range of asset stats daies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset stats daies
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetStatsDay> findAll(int start, int end,
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

		List<AssetStatsDay> list = (List<AssetStatsDay>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETSTATSDAY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETSTATSDAY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetStatsDay>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetStatsDay>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the asset stats daies where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long classNameId, long classPK)
		throws SystemException {
		for (AssetStatsDay assetStatsDay : findByC_C(classNameId, classPK)) {
			remove(assetStatsDay);
		}
	}

	/**
	 * Removes the asset stats day where classNameId = &#63; and classPK = &#63; and day = &#63; and year = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param day the day
	 * @param year the year
	 * @return the asset stats day that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AssetStatsDay removeByC_C_D_Y(long classNameId, long classPK,
		int day, int year) throws NoSuchAssetStatsDayException, SystemException {
		AssetStatsDay assetStatsDay = findByC_C_D_Y(classNameId, classPK, day,
				year);

		return remove(assetStatsDay);
	}

	/**
	 * Removes all the asset stats daies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetStatsDay assetStatsDay : findAll()) {
			remove(assetStatsDay);
		}
	}

	/**
	 * Returns the number of asset stats daies where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching asset stats daies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETSTATSDAY_WHERE);

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
	 * Returns the number of asset stats daies where classNameId = &#63; and classPK = &#63; and day = &#63; and year = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param day the day
	 * @param year the year
	 * @return the number of matching asset stats daies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_D_Y(long classNameId, long classPK, int day, int year)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, day, year };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_D_Y,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETSTATSDAY_WHERE);

			query.append(_FINDER_COLUMN_C_C_D_Y_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_D_Y_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_D_Y_DAY_2);

			query.append(_FINDER_COLUMN_C_C_D_Y_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(day);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_D_Y,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset stats daies.
	 *
	 * @return the number of asset stats daies
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETSTATSDAY);

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
	 * Initializes the asset stats day persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetStatsDay")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetStatsDay>> listenersList = new ArrayList<ModelListener<AssetStatsDay>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetStatsDay>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetStatsDayImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSETSTATSDAY = "SELECT assetStatsDay FROM AssetStatsDay assetStatsDay";
	private static final String _SQL_SELECT_ASSETSTATSDAY_WHERE = "SELECT assetStatsDay FROM AssetStatsDay assetStatsDay WHERE ";
	private static final String _SQL_COUNT_ASSETSTATSDAY = "SELECT COUNT(assetStatsDay) FROM AssetStatsDay assetStatsDay";
	private static final String _SQL_COUNT_ASSETSTATSDAY_WHERE = "SELECT COUNT(assetStatsDay) FROM AssetStatsDay assetStatsDay WHERE ";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "assetStatsDay.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "assetStatsDay.classPK = ?";
	private static final String _FINDER_COLUMN_C_C_D_Y_CLASSNAMEID_2 = "assetStatsDay.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_D_Y_CLASSPK_2 = "assetStatsDay.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_D_Y_DAY_2 = "assetStatsDay.day = ? AND ";
	private static final String _FINDER_COLUMN_C_C_D_Y_YEAR_2 = "assetStatsDay.year = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetStatsDay.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetStatsDay exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetStatsDay exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetStatsDayPersistenceImpl.class);
	private static AssetStatsDay _nullAssetStatsDay = new AssetStatsDayImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetStatsDay> toCacheModel() {
				return _nullAssetStatsDayCacheModel;
			}
		};

	private static CacheModel<AssetStatsDay> _nullAssetStatsDayCacheModel = new CacheModel<AssetStatsDay>() {
			public AssetStatsDay toEntityModel() {
				return _nullAssetStatsDay;
			}
		};
}
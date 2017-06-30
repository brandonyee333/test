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

import com.liferay.osb.NoSuchAppPackageException;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.impl.AppPackageImpl;
import com.liferay.osb.model.impl.AppPackageModelImpl;

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
 * The persistence implementation for the app package service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPackagePersistence
 * @see AppPackageUtil
 * @generated
 */
public class AppPackagePersistenceImpl extends BasePersistenceImpl<AppPackage>
	implements AppPackagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AppPackageUtil} to access the app package persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AppPackageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPVERSIONID =
		new FinderPath(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageModelImpl.FINDER_CACHE_ENABLED, AppPackageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppVersionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID =
		new FinderPath(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageModelImpl.FINDER_CACHE_ENABLED, AppPackageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppVersionId",
			new String[] { Long.class.getName() },
			AppPackageModelImpl.APPVERSIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPVERSIONID = new FinderPath(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppVersionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_AVI_C = new FinderPath(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageModelImpl.FINDER_CACHE_ENABLED, AppPackageImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAVI_C",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AppPackageModelImpl.APPVERSIONID_COLUMN_BITMASK |
			AppPackageModelImpl.COMPATIBILITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AVI_C = new FinderPath(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAVI_C",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AVI_CP = new FinderPath(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageModelImpl.FINDER_CACHE_ENABLED, AppPackageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAVI_CP",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVI_CP =
		new FinderPath(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageModelImpl.FINDER_CACHE_ENABLED, AppPackageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAVI_CP",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			AppPackageModelImpl.APPVERSIONID_COLUMN_BITMASK |
			AppPackageModelImpl.COMPATIBILITYPLUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AVI_CP = new FinderPath(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAVI_CP",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageModelImpl.FINDER_CACHE_ENABLED, AppPackageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageModelImpl.FINDER_CACHE_ENABLED, AppPackageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the app package in the entity cache if it is enabled.
	 *
	 * @param appPackage the app package
	 */
	public void cacheResult(AppPackage appPackage) {
		EntityCacheUtil.putResult(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageImpl.class, appPackage.getPrimaryKey(), appPackage);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AVI_C,
			new Object[] {
				Long.valueOf(appPackage.getAppVersionId()),
				Integer.valueOf(appPackage.getCompatibility())
			}, appPackage);

		appPackage.resetOriginalValues();
	}

	/**
	 * Caches the app packages in the entity cache if it is enabled.
	 *
	 * @param appPackages the app packages
	 */
	public void cacheResult(List<AppPackage> appPackages) {
		for (AppPackage appPackage : appPackages) {
			if (EntityCacheUtil.getResult(
						AppPackageModelImpl.ENTITY_CACHE_ENABLED,
						AppPackageImpl.class, appPackage.getPrimaryKey()) == null) {
				cacheResult(appPackage);
			}
			else {
				appPackage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all app packages.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AppPackageImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AppPackageImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the app package.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AppPackage appPackage) {
		EntityCacheUtil.removeResult(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageImpl.class, appPackage.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(appPackage);
	}

	@Override
	public void clearCache(List<AppPackage> appPackages) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AppPackage appPackage : appPackages) {
			EntityCacheUtil.removeResult(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
				AppPackageImpl.class, appPackage.getPrimaryKey());

			clearUniqueFindersCache(appPackage);
		}
	}

	protected void cacheUniqueFindersCache(AppPackage appPackage) {
		if (appPackage.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(appPackage.getAppVersionId()),
					Integer.valueOf(appPackage.getCompatibility())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AVI_C, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AVI_C, args,
				appPackage);
		}
		else {
			AppPackageModelImpl appPackageModelImpl = (AppPackageModelImpl)appPackage;

			if ((appPackageModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_AVI_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPackage.getAppVersionId()),
						Integer.valueOf(appPackage.getCompatibility())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AVI_C, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AVI_C, args,
					appPackage);
			}
		}
	}

	protected void clearUniqueFindersCache(AppPackage appPackage) {
		AppPackageModelImpl appPackageModelImpl = (AppPackageModelImpl)appPackage;

		Object[] args = new Object[] {
				Long.valueOf(appPackage.getAppVersionId()),
				Integer.valueOf(appPackage.getCompatibility())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AVI_C, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AVI_C, args);

		if ((appPackageModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AVI_C.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(appPackageModelImpl.getOriginalAppVersionId()),
					Integer.valueOf(appPackageModelImpl.getOriginalCompatibility())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AVI_C, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AVI_C, args);
		}
	}

	/**
	 * Creates a new app package with the primary key. Does not add the app package to the database.
	 *
	 * @param appPackageId the primary key for the new app package
	 * @return the new app package
	 */
	public AppPackage create(long appPackageId) {
		AppPackage appPackage = new AppPackageImpl();

		appPackage.setNew(true);
		appPackage.setPrimaryKey(appPackageId);

		return appPackage;
	}

	/**
	 * Removes the app package with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param appPackageId the primary key of the app package
	 * @return the app package that was removed
	 * @throws com.liferay.osb.NoSuchAppPackageException if a app package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage remove(long appPackageId)
		throws NoSuchAppPackageException, SystemException {
		return remove(Long.valueOf(appPackageId));
	}

	/**
	 * Removes the app package with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the app package
	 * @return the app package that was removed
	 * @throws com.liferay.osb.NoSuchAppPackageException if a app package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPackage remove(Serializable primaryKey)
		throws NoSuchAppPackageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AppPackage appPackage = (AppPackage)session.get(AppPackageImpl.class,
					primaryKey);

			if (appPackage == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAppPackageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(appPackage);
		}
		catch (NoSuchAppPackageException nsee) {
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
	protected AppPackage removeImpl(AppPackage appPackage)
		throws SystemException {
		appPackage = toUnwrappedModel(appPackage);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, appPackage);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(appPackage);

		return appPackage;
	}

	@Override
	public AppPackage updateImpl(com.liferay.osb.model.AppPackage appPackage,
		boolean merge) throws SystemException {
		appPackage = toUnwrappedModel(appPackage);

		boolean isNew = appPackage.isNew();

		AppPackageModelImpl appPackageModelImpl = (AppPackageModelImpl)appPackage;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, appPackage, merge);

			appPackage.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AppPackageModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((appPackageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPackageModelImpl.getOriginalAppVersionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID,
					args);

				args = new Object[] {
						Long.valueOf(appPackageModelImpl.getAppVersionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID,
					args);
			}

			if ((appPackageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVI_CP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPackageModelImpl.getOriginalAppVersionId()),
						Boolean.valueOf(appPackageModelImpl.getOriginalCompatibilityPlus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AVI_CP, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVI_CP,
					args);

				args = new Object[] {
						Long.valueOf(appPackageModelImpl.getAppVersionId()),
						Boolean.valueOf(appPackageModelImpl.getCompatibilityPlus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AVI_CP, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVI_CP,
					args);
			}
		}

		EntityCacheUtil.putResult(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
			AppPackageImpl.class, appPackage.getPrimaryKey(), appPackage);

		clearUniqueFindersCache(appPackage);
		cacheUniqueFindersCache(appPackage);

		return appPackage;
	}

	protected AppPackage toUnwrappedModel(AppPackage appPackage) {
		if (appPackage instanceof AppPackageImpl) {
			return appPackage;
		}

		AppPackageImpl appPackageImpl = new AppPackageImpl();

		appPackageImpl.setNew(appPackage.isNew());
		appPackageImpl.setPrimaryKey(appPackage.getPrimaryKey());

		appPackageImpl.setAppPackageId(appPackage.getAppPackageId());
		appPackageImpl.setCreateDate(appPackage.getCreateDate());
		appPackageImpl.setModifiedDate(appPackage.getModifiedDate());
		appPackageImpl.setAppEntryId(appPackage.getAppEntryId());
		appPackageImpl.setAppVersionId(appPackage.getAppVersionId());
		appPackageImpl.setCompatibility(appPackage.getCompatibility());
		appPackageImpl.setCompatibilityPlus(appPackage.isCompatibilityPlus());
		appPackageImpl.setPrepackaged(appPackage.isPrepackaged());
		appPackageImpl.setDownloadCount(appPackage.getDownloadCount());

		return appPackageImpl;
	}

	/**
	 * Returns the app package with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the app package
	 * @return the app package
	 * @throws com.liferay.portal.NoSuchModelException if a app package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPackage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app package with the primary key or throws a {@link com.liferay.osb.NoSuchAppPackageException} if it could not be found.
	 *
	 * @param appPackageId the primary key of the app package
	 * @return the app package
	 * @throws com.liferay.osb.NoSuchAppPackageException if a app package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage findByPrimaryKey(long appPackageId)
		throws NoSuchAppPackageException, SystemException {
		AppPackage appPackage = fetchByPrimaryKey(appPackageId);

		if (appPackage == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + appPackageId);
			}

			throw new NoSuchAppPackageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				appPackageId);
		}

		return appPackage;
	}

	/**
	 * Returns the app package with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the app package
	 * @return the app package, or <code>null</code> if a app package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPackage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app package with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param appPackageId the primary key of the app package
	 * @return the app package, or <code>null</code> if a app package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage fetchByPrimaryKey(long appPackageId)
		throws SystemException {
		AppPackage appPackage = (AppPackage)EntityCacheUtil.getResult(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
				AppPackageImpl.class, appPackageId);

		if (appPackage == _nullAppPackage) {
			return null;
		}

		if (appPackage == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				appPackage = (AppPackage)session.get(AppPackageImpl.class,
						Long.valueOf(appPackageId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (appPackage != null) {
					cacheResult(appPackage);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AppPackageModelImpl.ENTITY_CACHE_ENABLED,
						AppPackageImpl.class, appPackageId, _nullAppPackage);
				}

				closeSession(session);
			}
		}

		return appPackage;
	}

	/**
	 * Returns all the app packages where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @return the matching app packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackage> findByAppVersionId(long appVersionId)
		throws SystemException {
		return findByAppVersionId(appVersionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app packages where appVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appVersionId the app version ID
	 * @param start the lower bound of the range of app packages
	 * @param end the upper bound of the range of app packages (not inclusive)
	 * @return the range of matching app packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackage> findByAppVersionId(long appVersionId, int start,
		int end) throws SystemException {
		return findByAppVersionId(appVersionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app packages where appVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appVersionId the app version ID
	 * @param start the lower bound of the range of app packages
	 * @param end the upper bound of the range of app packages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackage> findByAppVersionId(long appVersionId, int start,
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

		List<AppPackage> list = (List<AppPackage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPackage appPackage : list) {
				if ((appVersionId != appPackage.getAppVersionId())) {
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

			query.append(_SQL_SELECT_APPPACKAGE_WHERE);

			query.append(_FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppPackageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appVersionId);

				list = (List<AppPackage>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app package in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package
	 * @throws com.liferay.osb.NoSuchAppPackageException if a matching app package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage findByAppVersionId_First(long appVersionId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackageException, SystemException {
		AppPackage appPackage = fetchByAppVersionId_First(appVersionId,
				orderByComparator);

		if (appPackage != null) {
			return appPackage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appVersionId=");
		msg.append(appVersionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackageException(msg.toString());
	}

	/**
	 * Returns the first app package in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package, or <code>null</code> if a matching app package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage fetchByAppVersionId_First(long appVersionId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppPackage> list = findByAppVersionId(appVersionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app package in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package
	 * @throws com.liferay.osb.NoSuchAppPackageException if a matching app package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage findByAppVersionId_Last(long appVersionId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackageException, SystemException {
		AppPackage appPackage = fetchByAppVersionId_Last(appVersionId,
				orderByComparator);

		if (appPackage != null) {
			return appPackage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appVersionId=");
		msg.append(appVersionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackageException(msg.toString());
	}

	/**
	 * Returns the last app package in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package, or <code>null</code> if a matching app package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage fetchByAppVersionId_Last(long appVersionId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAppVersionId(appVersionId);

		List<AppPackage> list = findByAppVersionId(appVersionId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app packages before and after the current app package in the ordered set where appVersionId = &#63;.
	 *
	 * @param appPackageId the primary key of the current app package
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app package
	 * @throws com.liferay.osb.NoSuchAppPackageException if a app package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage[] findByAppVersionId_PrevAndNext(long appPackageId,
		long appVersionId, OrderByComparator orderByComparator)
		throws NoSuchAppPackageException, SystemException {
		AppPackage appPackage = findByPrimaryKey(appPackageId);

		Session session = null;

		try {
			session = openSession();

			AppPackage[] array = new AppPackageImpl[3];

			array[0] = getByAppVersionId_PrevAndNext(session, appPackage,
					appVersionId, orderByComparator, true);

			array[1] = appPackage;

			array[2] = getByAppVersionId_PrevAndNext(session, appPackage,
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

	protected AppPackage getByAppVersionId_PrevAndNext(Session session,
		AppPackage appPackage, long appVersionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPACKAGE_WHERE);

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

		else {
			query.append(AppPackageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appVersionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPackage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPackage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the app package where appVersionId = &#63; and compatibility = &#63; or throws a {@link com.liferay.osb.NoSuchAppPackageException} if it could not be found.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibility the compatibility
	 * @return the matching app package
	 * @throws com.liferay.osb.NoSuchAppPackageException if a matching app package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage findByAVI_C(long appVersionId, int compatibility)
		throws NoSuchAppPackageException, SystemException {
		AppPackage appPackage = fetchByAVI_C(appVersionId, compatibility);

		if (appPackage == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("appVersionId=");
			msg.append(appVersionId);

			msg.append(", compatibility=");
			msg.append(compatibility);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAppPackageException(msg.toString());
		}

		return appPackage;
	}

	/**
	 * Returns the app package where appVersionId = &#63; and compatibility = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibility the compatibility
	 * @return the matching app package, or <code>null</code> if a matching app package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage fetchByAVI_C(long appVersionId, int compatibility)
		throws SystemException {
		return fetchByAVI_C(appVersionId, compatibility, true);
	}

	/**
	 * Returns the app package where appVersionId = &#63; and compatibility = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibility the compatibility
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching app package, or <code>null</code> if a matching app package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage fetchByAVI_C(long appVersionId, int compatibility,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { appVersionId, compatibility };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_AVI_C,
					finderArgs, this);
		}

		if (result instanceof AppPackage) {
			AppPackage appPackage = (AppPackage)result;

			if ((appVersionId != appPackage.getAppVersionId()) ||
					(compatibility != appPackage.getCompatibility())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_APPPACKAGE_WHERE);

			query.append(_FINDER_COLUMN_AVI_C_APPVERSIONID_2);

			query.append(_FINDER_COLUMN_AVI_C_COMPATIBILITY_2);

			query.append(AppPackageModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appVersionId);

				qPos.add(compatibility);

				List<AppPackage> list = q.list();

				result = list;

				AppPackage appPackage = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AVI_C,
						finderArgs, list);
				}
				else {
					appPackage = list.get(0);

					cacheResult(appPackage);

					if ((appPackage.getAppVersionId() != appVersionId) ||
							(appPackage.getCompatibility() != compatibility)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AVI_C,
							finderArgs, appPackage);
					}
				}

				return appPackage;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AVI_C,
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
				return (AppPackage)result;
			}
		}
	}

	/**
	 * Returns all the app packages where appVersionId = &#63; and compatibilityPlus = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibilityPlus the compatibility plus
	 * @return the matching app packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackage> findByAVI_CP(long appVersionId,
		boolean compatibilityPlus) throws SystemException {
		return findByAVI_CP(appVersionId, compatibilityPlus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app packages where appVersionId = &#63; and compatibilityPlus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appVersionId the app version ID
	 * @param compatibilityPlus the compatibility plus
	 * @param start the lower bound of the range of app packages
	 * @param end the upper bound of the range of app packages (not inclusive)
	 * @return the range of matching app packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackage> findByAVI_CP(long appVersionId,
		boolean compatibilityPlus, int start, int end)
		throws SystemException {
		return findByAVI_CP(appVersionId, compatibilityPlus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app packages where appVersionId = &#63; and compatibilityPlus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appVersionId the app version ID
	 * @param compatibilityPlus the compatibility plus
	 * @param start the lower bound of the range of app packages
	 * @param end the upper bound of the range of app packages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackage> findByAVI_CP(long appVersionId,
		boolean compatibilityPlus, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVI_CP;
			finderArgs = new Object[] { appVersionId, compatibilityPlus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AVI_CP;
			finderArgs = new Object[] {
					appVersionId, compatibilityPlus,
					
					start, end, orderByComparator
				};
		}

		List<AppPackage> list = (List<AppPackage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPackage appPackage : list) {
				if ((appVersionId != appPackage.getAppVersionId()) ||
						(compatibilityPlus != appPackage.getCompatibilityPlus())) {
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
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_APPPACKAGE_WHERE);

			query.append(_FINDER_COLUMN_AVI_CP_APPVERSIONID_2);

			query.append(_FINDER_COLUMN_AVI_CP_COMPATIBILITYPLUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppPackageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appVersionId);

				qPos.add(compatibilityPlus);

				list = (List<AppPackage>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app package in the ordered set where appVersionId = &#63; and compatibilityPlus = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibilityPlus the compatibility plus
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package
	 * @throws com.liferay.osb.NoSuchAppPackageException if a matching app package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage findByAVI_CP_First(long appVersionId,
		boolean compatibilityPlus, OrderByComparator orderByComparator)
		throws NoSuchAppPackageException, SystemException {
		AppPackage appPackage = fetchByAVI_CP_First(appVersionId,
				compatibilityPlus, orderByComparator);

		if (appPackage != null) {
			return appPackage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appVersionId=");
		msg.append(appVersionId);

		msg.append(", compatibilityPlus=");
		msg.append(compatibilityPlus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackageException(msg.toString());
	}

	/**
	 * Returns the first app package in the ordered set where appVersionId = &#63; and compatibilityPlus = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibilityPlus the compatibility plus
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package, or <code>null</code> if a matching app package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage fetchByAVI_CP_First(long appVersionId,
		boolean compatibilityPlus, OrderByComparator orderByComparator)
		throws SystemException {
		List<AppPackage> list = findByAVI_CP(appVersionId, compatibilityPlus,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app package in the ordered set where appVersionId = &#63; and compatibilityPlus = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibilityPlus the compatibility plus
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package
	 * @throws com.liferay.osb.NoSuchAppPackageException if a matching app package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage findByAVI_CP_Last(long appVersionId,
		boolean compatibilityPlus, OrderByComparator orderByComparator)
		throws NoSuchAppPackageException, SystemException {
		AppPackage appPackage = fetchByAVI_CP_Last(appVersionId,
				compatibilityPlus, orderByComparator);

		if (appPackage != null) {
			return appPackage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appVersionId=");
		msg.append(appVersionId);

		msg.append(", compatibilityPlus=");
		msg.append(compatibilityPlus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackageException(msg.toString());
	}

	/**
	 * Returns the last app package in the ordered set where appVersionId = &#63; and compatibilityPlus = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibilityPlus the compatibility plus
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package, or <code>null</code> if a matching app package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage fetchByAVI_CP_Last(long appVersionId,
		boolean compatibilityPlus, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAVI_CP(appVersionId, compatibilityPlus);

		List<AppPackage> list = findByAVI_CP(appVersionId, compatibilityPlus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app packages before and after the current app package in the ordered set where appVersionId = &#63; and compatibilityPlus = &#63;.
	 *
	 * @param appPackageId the primary key of the current app package
	 * @param appVersionId the app version ID
	 * @param compatibilityPlus the compatibility plus
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app package
	 * @throws com.liferay.osb.NoSuchAppPackageException if a app package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage[] findByAVI_CP_PrevAndNext(long appPackageId,
		long appVersionId, boolean compatibilityPlus,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackageException, SystemException {
		AppPackage appPackage = findByPrimaryKey(appPackageId);

		Session session = null;

		try {
			session = openSession();

			AppPackage[] array = new AppPackageImpl[3];

			array[0] = getByAVI_CP_PrevAndNext(session, appPackage,
					appVersionId, compatibilityPlus, orderByComparator, true);

			array[1] = appPackage;

			array[2] = getByAVI_CP_PrevAndNext(session, appPackage,
					appVersionId, compatibilityPlus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppPackage getByAVI_CP_PrevAndNext(Session session,
		AppPackage appPackage, long appVersionId, boolean compatibilityPlus,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPACKAGE_WHERE);

		query.append(_FINDER_COLUMN_AVI_CP_APPVERSIONID_2);

		query.append(_FINDER_COLUMN_AVI_CP_COMPATIBILITYPLUS_2);

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
			query.append(AppPackageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appVersionId);

		qPos.add(compatibilityPlus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPackage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPackage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app packages.
	 *
	 * @return the app packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app packages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app packages
	 * @param end the upper bound of the range of app packages (not inclusive)
	 * @return the range of app packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the app packages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app packages
	 * @param end the upper bound of the range of app packages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of app packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackage> findAll(int start, int end,
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

		List<AppPackage> list = (List<AppPackage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_APPPACKAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APPPACKAGE.concat(AppPackageModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AppPackage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AppPackage>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the app packages where appVersionId = &#63; from the database.
	 *
	 * @param appVersionId the app version ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAppVersionId(long appVersionId)
		throws SystemException {
		for (AppPackage appPackage : findByAppVersionId(appVersionId)) {
			remove(appPackage);
		}
	}

	/**
	 * Removes the app package where appVersionId = &#63; and compatibility = &#63; from the database.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibility the compatibility
	 * @return the app package that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackage removeByAVI_C(long appVersionId, int compatibility)
		throws NoSuchAppPackageException, SystemException {
		AppPackage appPackage = findByAVI_C(appVersionId, compatibility);

		return remove(appPackage);
	}

	/**
	 * Removes all the app packages where appVersionId = &#63; and compatibilityPlus = &#63; from the database.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibilityPlus the compatibility plus
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAVI_CP(long appVersionId, boolean compatibilityPlus)
		throws SystemException {
		for (AppPackage appPackage : findByAVI_CP(appVersionId,
				compatibilityPlus)) {
			remove(appPackage);
		}
	}

	/**
	 * Removes all the app packages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AppPackage appPackage : findAll()) {
			remove(appPackage);
		}
	}

	/**
	 * Returns the number of app packages where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @return the number of matching app packages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAppVersionId(long appVersionId) throws SystemException {
		Object[] finderArgs = new Object[] { appVersionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPPACKAGE_WHERE);

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
	 * Returns the number of app packages where appVersionId = &#63; and compatibility = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibility the compatibility
	 * @return the number of matching app packages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAVI_C(long appVersionId, int compatibility)
		throws SystemException {
		Object[] finderArgs = new Object[] { appVersionId, compatibility };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AVI_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPPACKAGE_WHERE);

			query.append(_FINDER_COLUMN_AVI_C_APPVERSIONID_2);

			query.append(_FINDER_COLUMN_AVI_C_COMPATIBILITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appVersionId);

				qPos.add(compatibility);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AVI_C,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app packages where appVersionId = &#63; and compatibilityPlus = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param compatibilityPlus the compatibility plus
	 * @return the number of matching app packages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAVI_CP(long appVersionId, boolean compatibilityPlus)
		throws SystemException {
		Object[] finderArgs = new Object[] { appVersionId, compatibilityPlus };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AVI_CP,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPPACKAGE_WHERE);

			query.append(_FINDER_COLUMN_AVI_CP_APPVERSIONID_2);

			query.append(_FINDER_COLUMN_AVI_CP_COMPATIBILITYPLUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appVersionId);

				qPos.add(compatibilityPlus);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AVI_CP,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app packages.
	 *
	 * @return the number of app packages
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APPPACKAGE);

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
	 * Initializes the app package persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AppPackage")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AppPackage>> listenersList = new ArrayList<ModelListener<AppPackage>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AppPackage>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AppPackageImpl.class.getName());
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
	private static final String _SQL_SELECT_APPPACKAGE = "SELECT appPackage FROM AppPackage appPackage";
	private static final String _SQL_SELECT_APPPACKAGE_WHERE = "SELECT appPackage FROM AppPackage appPackage WHERE ";
	private static final String _SQL_COUNT_APPPACKAGE = "SELECT COUNT(appPackage) FROM AppPackage appPackage";
	private static final String _SQL_COUNT_APPPACKAGE_WHERE = "SELECT COUNT(appPackage) FROM AppPackage appPackage WHERE ";
	private static final String _FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2 = "appPackage.appVersionId = ?";
	private static final String _FINDER_COLUMN_AVI_C_APPVERSIONID_2 = "appPackage.appVersionId = ? AND ";
	private static final String _FINDER_COLUMN_AVI_C_COMPATIBILITY_2 = "appPackage.compatibility = ?";
	private static final String _FINDER_COLUMN_AVI_CP_APPVERSIONID_2 = "appPackage.appVersionId = ? AND ";
	private static final String _FINDER_COLUMN_AVI_CP_COMPATIBILITYPLUS_2 = "appPackage.compatibilityPlus = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "appPackage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AppPackage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AppPackage exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AppPackagePersistenceImpl.class);
	private static AppPackage _nullAppPackage = new AppPackageImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AppPackage> toCacheModel() {
				return _nullAppPackageCacheModel;
			}
		};

	private static CacheModel<AppPackage> _nullAppPackageCacheModel = new CacheModel<AppPackage>() {
			public AppPackage toEntityModel() {
				return _nullAppPackage;
			}
		};
}
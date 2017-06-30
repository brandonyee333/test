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

import com.liferay.osb.NoSuchAppVersionException;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.impl.AppVersionImpl;
import com.liferay.osb.model.impl.AppVersionModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
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
 * The persistence implementation for the app version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppVersionPersistence
 * @see AppVersionUtil
 * @generated
 */
public class AppVersionPersistenceImpl extends BasePersistenceImpl<AppVersion>
	implements AppVersionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AppVersionUtil} to access the app version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AppVersionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPENTRYID =
		new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPENTRYID =
		new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppEntryId",
			new String[] { Long.class.getName() },
			AppVersionModelImpl.APPENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPENTRYID = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Integer.class.getName() },
			AppVersionModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_V = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAEI_V",
			new String[] { Long.class.getName(), String.class.getName() },
			AppVersionModelImpl.APPENTRYID_COLUMN_BITMASK |
			AppVersionModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_V = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_V",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_GTVO = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_GtVO",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_GTVO =
		new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByAEI_GtVO",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_RT = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_RT",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_RT =
		new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_RT",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AppVersionModelImpl.APPENTRYID_COLUMN_BITMASK |
			AppVersionModelImpl.RELEASETYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_RT = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_RT",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_S = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_S = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AppVersionModelImpl.APPENTRYID_COLUMN_BITMASK |
			AppVersionModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_S = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, AppVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the app version in the entity cache if it is enabled.
	 *
	 * @param appVersion the app version
	 */
	public void cacheResult(AppVersion appVersion) {
		EntityCacheUtil.putResult(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionImpl.class, appVersion.getPrimaryKey(), appVersion);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_V,
			new Object[] {
				Long.valueOf(appVersion.getAppEntryId()),
				
			appVersion.getVersion()
			}, appVersion);

		appVersion.resetOriginalValues();
	}

	/**
	 * Caches the app versions in the entity cache if it is enabled.
	 *
	 * @param appVersions the app versions
	 */
	public void cacheResult(List<AppVersion> appVersions) {
		for (AppVersion appVersion : appVersions) {
			if (EntityCacheUtil.getResult(
						AppVersionModelImpl.ENTITY_CACHE_ENABLED,
						AppVersionImpl.class, appVersion.getPrimaryKey()) == null) {
				cacheResult(appVersion);
			}
			else {
				appVersion.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all app versions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AppVersionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AppVersionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the app version.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AppVersion appVersion) {
		EntityCacheUtil.removeResult(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionImpl.class, appVersion.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(appVersion);
	}

	@Override
	public void clearCache(List<AppVersion> appVersions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AppVersion appVersion : appVersions) {
			EntityCacheUtil.removeResult(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
				AppVersionImpl.class, appVersion.getPrimaryKey());

			clearUniqueFindersCache(appVersion);
		}
	}

	protected void cacheUniqueFindersCache(AppVersion appVersion) {
		if (appVersion.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(appVersion.getAppEntryId()),
					
					appVersion.getVersion()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_V, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_V, args,
				appVersion);
		}
		else {
			AppVersionModelImpl appVersionModelImpl = (AppVersionModelImpl)appVersion;

			if ((appVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_AEI_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appVersion.getAppEntryId()),
						
						appVersion.getVersion()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_V, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_V, args,
					appVersion);
			}
		}
	}

	protected void clearUniqueFindersCache(AppVersion appVersion) {
		AppVersionModelImpl appVersionModelImpl = (AppVersionModelImpl)appVersion;

		Object[] args = new Object[] {
				Long.valueOf(appVersion.getAppEntryId()),
				
				appVersion.getVersion()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_V, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_V, args);

		if ((appVersionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_V.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(appVersionModelImpl.getOriginalAppEntryId()),
					
					appVersionModelImpl.getOriginalVersion()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_V, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_V, args);
		}
	}

	/**
	 * Creates a new app version with the primary key. Does not add the app version to the database.
	 *
	 * @param appVersionId the primary key for the new app version
	 * @return the new app version
	 */
	public AppVersion create(long appVersionId) {
		AppVersion appVersion = new AppVersionImpl();

		appVersion.setNew(true);
		appVersion.setPrimaryKey(appVersionId);

		return appVersion;
	}

	/**
	 * Removes the app version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param appVersionId the primary key of the app version
	 * @return the app version that was removed
	 * @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion remove(long appVersionId)
		throws NoSuchAppVersionException, SystemException {
		return remove(Long.valueOf(appVersionId));
	}

	/**
	 * Removes the app version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the app version
	 * @return the app version that was removed
	 * @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppVersion remove(Serializable primaryKey)
		throws NoSuchAppVersionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AppVersion appVersion = (AppVersion)session.get(AppVersionImpl.class,
					primaryKey);

			if (appVersion == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAppVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(appVersion);
		}
		catch (NoSuchAppVersionException nsee) {
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
	protected AppVersion removeImpl(AppVersion appVersion)
		throws SystemException {
		appVersion = toUnwrappedModel(appVersion);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, appVersion);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(appVersion);

		return appVersion;
	}

	@Override
	public AppVersion updateImpl(com.liferay.osb.model.AppVersion appVersion,
		boolean merge) throws SystemException {
		appVersion = toUnwrappedModel(appVersion);

		boolean isNew = appVersion.isNew();

		AppVersionModelImpl appVersionModelImpl = (AppVersionModelImpl)appVersion;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, appVersion, merge);

			appVersion.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AppVersionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((appVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appVersionModelImpl.getOriginalAppEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(appVersionModelImpl.getAppEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPENTRYID,
					args);
			}

			if ((appVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(appVersionModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] {
						Integer.valueOf(appVersionModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((appVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_RT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appVersionModelImpl.getOriginalAppEntryId()),
						Integer.valueOf(appVersionModelImpl.getOriginalReleaseType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_RT, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_RT,
					args);

				args = new Object[] {
						Long.valueOf(appVersionModelImpl.getAppEntryId()),
						Integer.valueOf(appVersionModelImpl.getReleaseType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_RT, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_RT,
					args);
			}

			if ((appVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appVersionModelImpl.getOriginalAppEntryId()),
						Integer.valueOf(appVersionModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_S,
					args);

				args = new Object[] {
						Long.valueOf(appVersionModelImpl.getAppEntryId()),
						Integer.valueOf(appVersionModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_S,
					args);
			}
		}

		EntityCacheUtil.putResult(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
			AppVersionImpl.class, appVersion.getPrimaryKey(), appVersion);

		clearUniqueFindersCache(appVersion);
		cacheUniqueFindersCache(appVersion);

		return appVersion;
	}

	protected AppVersion toUnwrappedModel(AppVersion appVersion) {
		if (appVersion instanceof AppVersionImpl) {
			return appVersion;
		}

		AppVersionImpl appVersionImpl = new AppVersionImpl();

		appVersionImpl.setNew(appVersion.isNew());
		appVersionImpl.setPrimaryKey(appVersion.getPrimaryKey());

		appVersionImpl.setAppVersionId(appVersion.getAppVersionId());
		appVersionImpl.setUserId(appVersion.getUserId());
		appVersionImpl.setUserName(appVersion.getUserName());
		appVersionImpl.setCreateDate(appVersion.getCreateDate());
		appVersionImpl.setModifiedDate(appVersion.getModifiedDate());
		appVersionImpl.setAppEntryId(appVersion.getAppEntryId());
		appVersionImpl.setDeveloperEntryId(appVersion.getDeveloperEntryId());
		appVersionImpl.setDeveloperName(appVersion.getDeveloperName());
		appVersionImpl.setTitle(appVersion.getTitle());
		appVersionImpl.setDescription(appVersion.getDescription());
		appVersionImpl.setWebsite(appVersion.getWebsite());
		appVersionImpl.setDemoWebsite(appVersion.getDemoWebsite());
		appVersionImpl.setDocumentationWebsite(appVersion.getDocumentationWebsite());
		appVersionImpl.setReferenceWebsite(appVersion.getReferenceWebsite());
		appVersionImpl.setSourceCodeWebsite(appVersion.getSourceCodeWebsite());
		appVersionImpl.setSupportWebsite(appVersion.getSupportWebsite());
		appVersionImpl.setLabs(appVersion.isLabs());
		appVersionImpl.setProductType(appVersion.getProductType());
		appVersionImpl.setVersion(appVersion.getVersion());
		appVersionImpl.setVersionId(appVersion.getVersionId());
		appVersionImpl.setVersionOrder(appVersion.getVersionOrder());
		appVersionImpl.setChangeLog(appVersion.getChangeLog());
		appVersionImpl.setIconImageId(appVersion.getIconImageId());
		appVersionImpl.setSize(appVersion.getSize());
		appVersionImpl.setDownloadCount(appVersion.getDownloadCount());
		appVersionImpl.setPaclEnabled(appVersion.isPaclEnabled());
		appVersionImpl.setReleaseDate(appVersion.getReleaseDate());
		appVersionImpl.setReleaseType(appVersion.getReleaseType());
		appVersionImpl.setContractEntryId(appVersion.getContractEntryId());
		appVersionImpl.setSupported(appVersion.isSupported());
		appVersionImpl.setOrderURL(appVersion.getOrderURL());
		appVersionImpl.setHidden(appVersion.isHidden());
		appVersionImpl.setPortalRequired(appVersion.isPortalRequired());
		appVersionImpl.setStatus(appVersion.getStatus());
		appVersionImpl.setStatusByUserId(appVersion.getStatusByUserId());
		appVersionImpl.setStatusByUserName(appVersion.getStatusByUserName());
		appVersionImpl.setStatusDate(appVersion.getStatusDate());
		appVersionImpl.setStatusMessage(appVersion.getStatusMessage());

		return appVersionImpl;
	}

	/**
	 * Returns the app version with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the app version
	 * @return the app version
	 * @throws com.liferay.portal.NoSuchModelException if a app version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app version with the primary key or throws a {@link com.liferay.osb.NoSuchAppVersionException} if it could not be found.
	 *
	 * @param appVersionId the primary key of the app version
	 * @return the app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByPrimaryKey(long appVersionId)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByPrimaryKey(appVersionId);

		if (appVersion == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + appVersionId);
			}

			throw new NoSuchAppVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				appVersionId);
		}

		return appVersion;
	}

	/**
	 * Returns the app version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the app version
	 * @return the app version, or <code>null</code> if a app version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppVersion fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param appVersionId the primary key of the app version
	 * @return the app version, or <code>null</code> if a app version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByPrimaryKey(long appVersionId)
		throws SystemException {
		AppVersion appVersion = (AppVersion)EntityCacheUtil.getResult(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
				AppVersionImpl.class, appVersionId);

		if (appVersion == _nullAppVersion) {
			return null;
		}

		if (appVersion == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				appVersion = (AppVersion)session.get(AppVersionImpl.class,
						Long.valueOf(appVersionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (appVersion != null) {
					cacheResult(appVersion);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AppVersionModelImpl.ENTITY_CACHE_ENABLED,
						AppVersionImpl.class, appVersionId, _nullAppVersion);
				}

				closeSession(session);
			}
		}

		return appVersion;
	}

	/**
	 * Returns all the app versions where appEntryId = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @return the matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAppEntryId(long appEntryId)
		throws SystemException {
		return findByAppEntryId(appEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app versions where appEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId the app entry ID
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @return the range of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAppEntryId(long appEntryId, int start, int end)
		throws SystemException {
		return findByAppEntryId(appEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app versions where appEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId the app entry ID
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAppEntryId(long appEntryId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPENTRYID;
			finderArgs = new Object[] { appEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPENTRYID;
			finderArgs = new Object[] { appEntryId, start, end, orderByComparator };
		}

		List<AppVersion> list = (List<AppVersion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppVersion appVersion : list) {
				if ((appEntryId != appVersion.getAppEntryId())) {
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

			query.append(_SQL_SELECT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_APPENTRYID_APPENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId);

				list = (List<AppVersion>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app version in the ordered set where appEntryId = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByAppEntryId_First(long appEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByAppEntryId_First(appEntryId,
				orderByComparator);

		if (appVersion != null) {
			return appVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId=");
		msg.append(appEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppVersionException(msg.toString());
	}

	/**
	 * Returns the first app version in the ordered set where appEntryId = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByAppEntryId_First(long appEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppVersion> list = findByAppEntryId(appEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app version in the ordered set where appEntryId = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByAppEntryId_Last(long appEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByAppEntryId_Last(appEntryId,
				orderByComparator);

		if (appVersion != null) {
			return appVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId=");
		msg.append(appEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppVersionException(msg.toString());
	}

	/**
	 * Returns the last app version in the ordered set where appEntryId = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByAppEntryId_Last(long appEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAppEntryId(appEntryId);

		List<AppVersion> list = findByAppEntryId(appEntryId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app versions before and after the current app version in the ordered set where appEntryId = &#63;.
	 *
	 * @param appVersionId the primary key of the current app version
	 * @param appEntryId the app entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion[] findByAppEntryId_PrevAndNext(long appVersionId,
		long appEntryId, OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = findByPrimaryKey(appVersionId);

		Session session = null;

		try {
			session = openSession();

			AppVersion[] array = new AppVersionImpl[3];

			array[0] = getByAppEntryId_PrevAndNext(session, appVersion,
					appEntryId, orderByComparator, true);

			array[1] = appVersion;

			array[2] = getByAppEntryId_PrevAndNext(session, appVersion,
					appEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppVersion getByAppEntryId_PrevAndNext(Session session,
		AppVersion appVersion, long appEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPVERSION_WHERE);

		query.append(_FINDER_COLUMN_APPENTRYID_APPENTRYID_2);

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
			query.append(AppVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appVersion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app versions where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByStatus(int status) throws SystemException {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app versions where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @return the range of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByStatus(int status, int start, int end)
		throws SystemException {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app versions where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByStatus(int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status, start, end, orderByComparator };
		}

		List<AppVersion> list = (List<AppVersion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppVersion appVersion : list) {
				if ((status != appVersion.getStatus())) {
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

			query.append(_SQL_SELECT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				list = (List<AppVersion>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app version in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByStatus_First(int status,
		OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByStatus_First(status, orderByComparator);

		if (appVersion != null) {
			return appVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppVersionException(msg.toString());
	}

	/**
	 * Returns the first app version in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByStatus_First(int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppVersion> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app version in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByStatus_Last(int status,
		OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByStatus_Last(status, orderByComparator);

		if (appVersion != null) {
			return appVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppVersionException(msg.toString());
	}

	/**
	 * Returns the last app version in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByStatus_Last(int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStatus(status);

		List<AppVersion> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app versions before and after the current app version in the ordered set where status = &#63;.
	 *
	 * @param appVersionId the primary key of the current app version
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion[] findByStatus_PrevAndNext(long appVersionId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = findByPrimaryKey(appVersionId);

		Session session = null;

		try {
			session = openSession();

			AppVersion[] array = new AppVersionImpl[3];

			array[0] = getByStatus_PrevAndNext(session, appVersion, status,
					orderByComparator, true);

			array[1] = appVersion;

			array[2] = getByStatus_PrevAndNext(session, appVersion, status,
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

	protected AppVersion getByStatus_PrevAndNext(Session session,
		AppVersion appVersion, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPVERSION_WHERE);

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

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
			query.append(AppVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appVersion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the app version where appEntryId = &#63; and version = &#63; or throws a {@link com.liferay.osb.NoSuchAppVersionException} if it could not be found.
	 *
	 * @param appEntryId the app entry ID
	 * @param version the version
	 * @return the matching app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByAEI_V(long appEntryId, String version)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByAEI_V(appEntryId, version);

		if (appVersion == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("appEntryId=");
			msg.append(appEntryId);

			msg.append(", version=");
			msg.append(version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAppVersionException(msg.toString());
		}

		return appVersion;
	}

	/**
	 * Returns the app version where appEntryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param appEntryId the app entry ID
	 * @param version the version
	 * @return the matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByAEI_V(long appEntryId, String version)
		throws SystemException {
		return fetchByAEI_V(appEntryId, version, true);
	}

	/**
	 * Returns the app version where appEntryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param appEntryId the app entry ID
	 * @param version the version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByAEI_V(long appEntryId, String version,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { appEntryId, version };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_AEI_V,
					finderArgs, this);
		}

		if (result instanceof AppVersion) {
			AppVersion appVersion = (AppVersion)result;

			if ((appEntryId != appVersion.getAppEntryId()) ||
					!Validator.equals(version, appVersion.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_AEI_V_APPENTRYID_2);

			if (version == null) {
				query.append(_FINDER_COLUMN_AEI_V_VERSION_1);
			}
			else {
				if (version.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_AEI_V_VERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_AEI_V_VERSION_2);
				}
			}

			query.append(AppVersionModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId);

				if (version != null) {
					qPos.add(version);
				}

				List<AppVersion> list = q.list();

				result = list;

				AppVersion appVersion = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_V,
						finderArgs, list);
				}
				else {
					appVersion = list.get(0);

					cacheResult(appVersion);

					if ((appVersion.getAppEntryId() != appEntryId) ||
							(appVersion.getVersion() == null) ||
							!appVersion.getVersion().equals(version)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_V,
							finderArgs, appVersion);
					}
				}

				return appVersion;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_V,
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
				return (AppVersion)result;
			}
		}
	}

	/**
	 * Returns all the app versions where appEntryId = &#63; and versionOrder &ge; &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param versionOrder the version order
	 * @return the matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAEI_GtVO(long appEntryId, int versionOrder)
		throws SystemException {
		return findByAEI_GtVO(appEntryId, versionOrder, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app versions where appEntryId = &#63; and versionOrder &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId the app entry ID
	 * @param versionOrder the version order
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @return the range of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAEI_GtVO(long appEntryId, int versionOrder,
		int start, int end) throws SystemException {
		return findByAEI_GtVO(appEntryId, versionOrder, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app versions where appEntryId = &#63; and versionOrder &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId the app entry ID
	 * @param versionOrder the version order
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAEI_GtVO(long appEntryId, int versionOrder,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_GTVO;
		finderArgs = new Object[] {
				appEntryId, versionOrder,
				
				start, end, orderByComparator
			};

		List<AppVersion> list = (List<AppVersion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppVersion appVersion : list) {
				if ((appEntryId != appVersion.getAppEntryId()) ||
						(versionOrder != appVersion.getVersionOrder())) {
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

			query.append(_SQL_SELECT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_AEI_GTVO_APPENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_GTVO_VERSIONORDER_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId);

				qPos.add(versionOrder);

				list = (List<AppVersion>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app version in the ordered set where appEntryId = &#63; and versionOrder &ge; &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param versionOrder the version order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByAEI_GtVO_First(long appEntryId, int versionOrder,
		OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByAEI_GtVO_First(appEntryId, versionOrder,
				orderByComparator);

		if (appVersion != null) {
			return appVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId=");
		msg.append(appEntryId);

		msg.append(", versionOrder=");
		msg.append(versionOrder);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppVersionException(msg.toString());
	}

	/**
	 * Returns the first app version in the ordered set where appEntryId = &#63; and versionOrder &ge; &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param versionOrder the version order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByAEI_GtVO_First(long appEntryId, int versionOrder,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppVersion> list = findByAEI_GtVO(appEntryId, versionOrder, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app version in the ordered set where appEntryId = &#63; and versionOrder &ge; &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param versionOrder the version order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByAEI_GtVO_Last(long appEntryId, int versionOrder,
		OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByAEI_GtVO_Last(appEntryId, versionOrder,
				orderByComparator);

		if (appVersion != null) {
			return appVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId=");
		msg.append(appEntryId);

		msg.append(", versionOrder=");
		msg.append(versionOrder);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppVersionException(msg.toString());
	}

	/**
	 * Returns the last app version in the ordered set where appEntryId = &#63; and versionOrder &ge; &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param versionOrder the version order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByAEI_GtVO_Last(long appEntryId, int versionOrder,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAEI_GtVO(appEntryId, versionOrder);

		List<AppVersion> list = findByAEI_GtVO(appEntryId, versionOrder,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app versions before and after the current app version in the ordered set where appEntryId = &#63; and versionOrder &ge; &#63;.
	 *
	 * @param appVersionId the primary key of the current app version
	 * @param appEntryId the app entry ID
	 * @param versionOrder the version order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion[] findByAEI_GtVO_PrevAndNext(long appVersionId,
		long appEntryId, int versionOrder, OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = findByPrimaryKey(appVersionId);

		Session session = null;

		try {
			session = openSession();

			AppVersion[] array = new AppVersionImpl[3];

			array[0] = getByAEI_GtVO_PrevAndNext(session, appVersion,
					appEntryId, versionOrder, orderByComparator, true);

			array[1] = appVersion;

			array[2] = getByAEI_GtVO_PrevAndNext(session, appVersion,
					appEntryId, versionOrder, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppVersion getByAEI_GtVO_PrevAndNext(Session session,
		AppVersion appVersion, long appEntryId, int versionOrder,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPVERSION_WHERE);

		query.append(_FINDER_COLUMN_AEI_GTVO_APPENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_GTVO_VERSIONORDER_2);

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
			query.append(AppVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appEntryId);

		qPos.add(versionOrder);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appVersion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app versions where appEntryId = &#63; and releaseType = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param releaseType the release type
	 * @return the matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAEI_RT(long appEntryId, int releaseType)
		throws SystemException {
		return findByAEI_RT(appEntryId, releaseType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app versions where appEntryId = &#63; and releaseType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId the app entry ID
	 * @param releaseType the release type
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @return the range of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAEI_RT(long appEntryId, int releaseType,
		int start, int end) throws SystemException {
		return findByAEI_RT(appEntryId, releaseType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app versions where appEntryId = &#63; and releaseType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId the app entry ID
	 * @param releaseType the release type
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAEI_RT(long appEntryId, int releaseType,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_RT;
			finderArgs = new Object[] { appEntryId, releaseType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_RT;
			finderArgs = new Object[] {
					appEntryId, releaseType,
					
					start, end, orderByComparator
				};
		}

		List<AppVersion> list = (List<AppVersion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppVersion appVersion : list) {
				if ((appEntryId != appVersion.getAppEntryId()) ||
						(releaseType != appVersion.getReleaseType())) {
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

			query.append(_SQL_SELECT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_AEI_RT_APPENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_RT_RELEASETYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId);

				qPos.add(releaseType);

				list = (List<AppVersion>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app version in the ordered set where appEntryId = &#63; and releaseType = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param releaseType the release type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByAEI_RT_First(long appEntryId, int releaseType,
		OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByAEI_RT_First(appEntryId, releaseType,
				orderByComparator);

		if (appVersion != null) {
			return appVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId=");
		msg.append(appEntryId);

		msg.append(", releaseType=");
		msg.append(releaseType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppVersionException(msg.toString());
	}

	/**
	 * Returns the first app version in the ordered set where appEntryId = &#63; and releaseType = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param releaseType the release type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByAEI_RT_First(long appEntryId, int releaseType,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppVersion> list = findByAEI_RT(appEntryId, releaseType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app version in the ordered set where appEntryId = &#63; and releaseType = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param releaseType the release type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByAEI_RT_Last(long appEntryId, int releaseType,
		OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByAEI_RT_Last(appEntryId, releaseType,
				orderByComparator);

		if (appVersion != null) {
			return appVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId=");
		msg.append(appEntryId);

		msg.append(", releaseType=");
		msg.append(releaseType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppVersionException(msg.toString());
	}

	/**
	 * Returns the last app version in the ordered set where appEntryId = &#63; and releaseType = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param releaseType the release type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByAEI_RT_Last(long appEntryId, int releaseType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAEI_RT(appEntryId, releaseType);

		List<AppVersion> list = findByAEI_RT(appEntryId, releaseType,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app versions before and after the current app version in the ordered set where appEntryId = &#63; and releaseType = &#63;.
	 *
	 * @param appVersionId the primary key of the current app version
	 * @param appEntryId the app entry ID
	 * @param releaseType the release type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion[] findByAEI_RT_PrevAndNext(long appVersionId,
		long appEntryId, int releaseType, OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = findByPrimaryKey(appVersionId);

		Session session = null;

		try {
			session = openSession();

			AppVersion[] array = new AppVersionImpl[3];

			array[0] = getByAEI_RT_PrevAndNext(session, appVersion, appEntryId,
					releaseType, orderByComparator, true);

			array[1] = appVersion;

			array[2] = getByAEI_RT_PrevAndNext(session, appVersion, appEntryId,
					releaseType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppVersion getByAEI_RT_PrevAndNext(Session session,
		AppVersion appVersion, long appEntryId, int releaseType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPVERSION_WHERE);

		query.append(_FINDER_COLUMN_AEI_RT_APPENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_RT_RELEASETYPE_2);

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
			query.append(AppVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appEntryId);

		qPos.add(releaseType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appVersion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app versions where appEntryId = &#63; and status = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param status the status
	 * @return the matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAEI_S(long appEntryId, int status)
		throws SystemException {
		return findByAEI_S(appEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app versions where appEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId the app entry ID
	 * @param status the status
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @return the range of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAEI_S(long appEntryId, int status, int start,
		int end) throws SystemException {
		return findByAEI_S(appEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app versions where appEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId the app entry ID
	 * @param status the status
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findByAEI_S(long appEntryId, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_S;
			finderArgs = new Object[] { appEntryId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_S;
			finderArgs = new Object[] {
					appEntryId, status,
					
					start, end, orderByComparator
				};
		}

		List<AppVersion> list = (List<AppVersion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppVersion appVersion : list) {
				if ((appEntryId != appVersion.getAppEntryId()) ||
						(status != appVersion.getStatus())) {
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

			query.append(_SQL_SELECT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_AEI_S_APPENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId);

				qPos.add(status);

				list = (List<AppVersion>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app version in the ordered set where appEntryId = &#63; and status = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByAEI_S_First(long appEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByAEI_S_First(appEntryId, status,
				orderByComparator);

		if (appVersion != null) {
			return appVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId=");
		msg.append(appEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppVersionException(msg.toString());
	}

	/**
	 * Returns the first app version in the ordered set where appEntryId = &#63; and status = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByAEI_S_First(long appEntryId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppVersion> list = findByAEI_S(appEntryId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app version in the ordered set where appEntryId = &#63; and status = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion findByAEI_S_Last(long appEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = fetchByAEI_S_Last(appEntryId, status,
				orderByComparator);

		if (appVersion != null) {
			return appVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId=");
		msg.append(appEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppVersionException(msg.toString());
	}

	/**
	 * Returns the last app version in the ordered set where appEntryId = &#63; and status = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app version, or <code>null</code> if a matching app version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion fetchByAEI_S_Last(long appEntryId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAEI_S(appEntryId, status);

		List<AppVersion> list = findByAEI_S(appEntryId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app versions before and after the current app version in the ordered set where appEntryId = &#63; and status = &#63;.
	 *
	 * @param appVersionId the primary key of the current app version
	 * @param appEntryId the app entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app version
	 * @throws com.liferay.osb.NoSuchAppVersionException if a app version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion[] findByAEI_S_PrevAndNext(long appVersionId,
		long appEntryId, int status, OrderByComparator orderByComparator)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = findByPrimaryKey(appVersionId);

		Session session = null;

		try {
			session = openSession();

			AppVersion[] array = new AppVersionImpl[3];

			array[0] = getByAEI_S_PrevAndNext(session, appVersion, appEntryId,
					status, orderByComparator, true);

			array[1] = appVersion;

			array[2] = getByAEI_S_PrevAndNext(session, appVersion, appEntryId,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppVersion getByAEI_S_PrevAndNext(Session session,
		AppVersion appVersion, long appEntryId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPVERSION_WHERE);

		query.append(_FINDER_COLUMN_AEI_S_APPENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_S_STATUS_2);

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
			query.append(AppVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appVersion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app versions.
	 *
	 * @return the app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @return the range of app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the app versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app versions
	 * @param end the upper bound of the range of app versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of app versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppVersion> findAll(int start, int end,
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

		List<AppVersion> list = (List<AppVersion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_APPVERSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APPVERSION.concat(AppVersionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AppVersion>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AppVersion>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the app versions where appEntryId = &#63; from the database.
	 *
	 * @param appEntryId the app entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAppEntryId(long appEntryId) throws SystemException {
		for (AppVersion appVersion : findByAppEntryId(appEntryId)) {
			remove(appVersion);
		}
	}

	/**
	 * Removes all the app versions where status = &#63; from the database.
	 *
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByStatus(int status) throws SystemException {
		for (AppVersion appVersion : findByStatus(status)) {
			remove(appVersion);
		}
	}

	/**
	 * Removes the app version where appEntryId = &#63; and version = &#63; from the database.
	 *
	 * @param appEntryId the app entry ID
	 * @param version the version
	 * @return the app version that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AppVersion removeByAEI_V(long appEntryId, String version)
		throws NoSuchAppVersionException, SystemException {
		AppVersion appVersion = findByAEI_V(appEntryId, version);

		return remove(appVersion);
	}

	/**
	 * Removes all the app versions where appEntryId = &#63; and versionOrder &ge; &#63; from the database.
	 *
	 * @param appEntryId the app entry ID
	 * @param versionOrder the version order
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_GtVO(long appEntryId, int versionOrder)
		throws SystemException {
		for (AppVersion appVersion : findByAEI_GtVO(appEntryId, versionOrder)) {
			remove(appVersion);
		}
	}

	/**
	 * Removes all the app versions where appEntryId = &#63; and releaseType = &#63; from the database.
	 *
	 * @param appEntryId the app entry ID
	 * @param releaseType the release type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_RT(long appEntryId, int releaseType)
		throws SystemException {
		for (AppVersion appVersion : findByAEI_RT(appEntryId, releaseType)) {
			remove(appVersion);
		}
	}

	/**
	 * Removes all the app versions where appEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param appEntryId the app entry ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_S(long appEntryId, int status)
		throws SystemException {
		for (AppVersion appVersion : findByAEI_S(appEntryId, status)) {
			remove(appVersion);
		}
	}

	/**
	 * Removes all the app versions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AppVersion appVersion : findAll()) {
			remove(appVersion);
		}
	}

	/**
	 * Returns the number of app versions where appEntryId = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @return the number of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAppEntryId(long appEntryId) throws SystemException {
		Object[] finderArgs = new Object[] { appEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_APPENTRYID_APPENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_APPENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app versions where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByStatus(int status) throws SystemException {
		Object[] finderArgs = new Object[] { status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_STATUS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_STATUS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app versions where appEntryId = &#63; and version = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param version the version
	 * @return the number of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_V(long appEntryId, String version)
		throws SystemException {
		Object[] finderArgs = new Object[] { appEntryId, version };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_AEI_V_APPENTRYID_2);

			if (version == null) {
				query.append(_FINDER_COLUMN_AEI_V_VERSION_1);
			}
			else {
				if (version.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_AEI_V_VERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_AEI_V_VERSION_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId);

				if (version != null) {
					qPos.add(version);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_V,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app versions where appEntryId = &#63; and versionOrder &ge; &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param versionOrder the version order
	 * @return the number of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_GtVO(long appEntryId, int versionOrder)
		throws SystemException {
		Object[] finderArgs = new Object[] { appEntryId, versionOrder };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_GTVO,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_AEI_GTVO_APPENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_GTVO_VERSIONORDER_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId);

				qPos.add(versionOrder);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_AEI_GTVO,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app versions where appEntryId = &#63; and releaseType = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param releaseType the release type
	 * @return the number of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_RT(long appEntryId, int releaseType)
		throws SystemException {
		Object[] finderArgs = new Object[] { appEntryId, releaseType };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_RT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_AEI_RT_APPENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_RT_RELEASETYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId);

				qPos.add(releaseType);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_RT,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app versions where appEntryId = &#63; and status = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param status the status
	 * @return the number of matching app versions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_S(long appEntryId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { appEntryId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPVERSION_WHERE);

			query.append(_FINDER_COLUMN_AEI_S_APPENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId);

				qPos.add(status);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app versions.
	 *
	 * @return the number of app versions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APPVERSION);

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
	 * Initializes the app version persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AppVersion")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AppVersion>> listenersList = new ArrayList<ModelListener<AppVersion>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AppVersion>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AppVersionImpl.class.getName());
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
	private static final String _SQL_SELECT_APPVERSION = "SELECT appVersion FROM AppVersion appVersion";
	private static final String _SQL_SELECT_APPVERSION_WHERE = "SELECT appVersion FROM AppVersion appVersion WHERE ";
	private static final String _SQL_COUNT_APPVERSION = "SELECT COUNT(appVersion) FROM AppVersion appVersion";
	private static final String _SQL_COUNT_APPVERSION_WHERE = "SELECT COUNT(appVersion) FROM AppVersion appVersion WHERE ";
	private static final String _FINDER_COLUMN_APPENTRYID_APPENTRYID_2 = "appVersion.appEntryId = ?";
	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "appVersion.status = ?";
	private static final String _FINDER_COLUMN_AEI_V_APPENTRYID_2 = "appVersion.appEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_V_VERSION_1 = "appVersion.version IS NULL";
	private static final String _FINDER_COLUMN_AEI_V_VERSION_2 = "appVersion.version = ?";
	private static final String _FINDER_COLUMN_AEI_V_VERSION_3 = "(appVersion.version IS NULL OR appVersion.version = ?)";
	private static final String _FINDER_COLUMN_AEI_GTVO_APPENTRYID_2 = "appVersion.appEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_GTVO_VERSIONORDER_2 = "appVersion.versionOrder >= ?";
	private static final String _FINDER_COLUMN_AEI_RT_APPENTRYID_2 = "appVersion.appEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_RT_RELEASETYPE_2 = "appVersion.releaseType = ?";
	private static final String _FINDER_COLUMN_AEI_S_APPENTRYID_2 = "appVersion.appEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_S_STATUS_2 = "appVersion.status = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "appVersion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AppVersion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AppVersion exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AppVersionPersistenceImpl.class);
	private static AppVersion _nullAppVersion = new AppVersionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AppVersion> toCacheModel() {
				return _nullAppVersionCacheModel;
			}
		};

	private static CacheModel<AppVersion> _nullAppVersionCacheModel = new CacheModel<AppVersion>() {
			public AppVersion toEntityModel() {
				return _nullAppVersion;
			}
		};
}
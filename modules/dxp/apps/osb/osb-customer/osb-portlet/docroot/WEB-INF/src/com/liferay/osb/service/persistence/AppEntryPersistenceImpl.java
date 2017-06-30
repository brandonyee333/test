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

import com.liferay.osb.NoSuchAppEntryException;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.impl.AppEntryImpl;
import com.liferay.osb.model.impl.AppEntryModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.ImagePersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserGroupRolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.asset.service.persistence.AssetCategoryPersistence;
import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.messageboards.service.persistence.MBMessagePersistence;
import com.liferay.portlet.social.service.persistence.SocialActivityPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the app entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryPersistence
 * @see AppEntryUtil
 * @generated
 */
public class AppEntryPersistenceImpl extends BasePersistenceImpl<AppEntry>
	implements AppEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AppEntryUtil} to access the app entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AppEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AppEntryModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DEVELOPERENTRYID =
		new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDeveloperEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DEVELOPERENTRYID =
		new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByDeveloperEntryId", new String[] { Long.class.getName() },
			AppEntryModelImpl.DEVELOPERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DEVELOPERENTRYID = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDeveloperEntryId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_TITLE = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByTitle",
			new String[] { String.class.getName() },
			AppEntryModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TITLE = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTitle",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Integer.class.getName() },
			AppEntryModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DEI_LIKET =
		new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDEI_LikeT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_DEI_LIKET =
		new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByDEI_LikeT",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DEI_S = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDEI_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DEI_S = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AppEntryModelImpl.DEVELOPERENTRYID_COLUMN_BITMASK |
			AppEntryModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DEI_S = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, AppEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the app entry in the entity cache if it is enabled.
	 *
	 * @param appEntry the app entry
	 */
	public void cacheResult(AppEntry appEntry) {
		EntityCacheUtil.putResult(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryImpl.class, appEntry.getPrimaryKey(), appEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TITLE,
			new Object[] { appEntry.getTitle() }, appEntry);

		appEntry.resetOriginalValues();
	}

	/**
	 * Caches the app entries in the entity cache if it is enabled.
	 *
	 * @param appEntries the app entries
	 */
	public void cacheResult(List<AppEntry> appEntries) {
		for (AppEntry appEntry : appEntries) {
			if (EntityCacheUtil.getResult(
						AppEntryModelImpl.ENTITY_CACHE_ENABLED,
						AppEntryImpl.class, appEntry.getPrimaryKey()) == null) {
				cacheResult(appEntry);
			}
			else {
				appEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all app entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AppEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AppEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the app entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AppEntry appEntry) {
		EntityCacheUtil.removeResult(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryImpl.class, appEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(appEntry);
	}

	@Override
	public void clearCache(List<AppEntry> appEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AppEntry appEntry : appEntries) {
			EntityCacheUtil.removeResult(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
				AppEntryImpl.class, appEntry.getPrimaryKey());

			clearUniqueFindersCache(appEntry);
		}
	}

	protected void cacheUniqueFindersCache(AppEntry appEntry) {
		if (appEntry.isNew()) {
			Object[] args = new Object[] { appEntry.getTitle() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TITLE, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TITLE, args, appEntry);
		}
		else {
			AppEntryModelImpl appEntryModelImpl = (AppEntryModelImpl)appEntry;

			if ((appEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_TITLE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { appEntry.getTitle() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TITLE, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TITLE, args,
					appEntry);
			}
		}
	}

	protected void clearUniqueFindersCache(AppEntry appEntry) {
		AppEntryModelImpl appEntryModelImpl = (AppEntryModelImpl)appEntry;

		Object[] args = new Object[] { appEntry.getTitle() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TITLE, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TITLE, args);

		if ((appEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TITLE.getColumnBitmask()) != 0) {
			args = new Object[] { appEntryModelImpl.getOriginalTitle() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TITLE, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TITLE, args);
		}
	}

	/**
	 * Creates a new app entry with the primary key. Does not add the app entry to the database.
	 *
	 * @param appEntryId the primary key for the new app entry
	 * @return the new app entry
	 */
	public AppEntry create(long appEntryId) {
		AppEntry appEntry = new AppEntryImpl();

		appEntry.setNew(true);
		appEntry.setPrimaryKey(appEntryId);

		String uuid = PortalUUIDUtil.generate();

		appEntry.setUuid(uuid);

		return appEntry;
	}

	/**
	 * Removes the app entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param appEntryId the primary key of the app entry
	 * @return the app entry that was removed
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry remove(long appEntryId)
		throws NoSuchAppEntryException, SystemException {
		return remove(Long.valueOf(appEntryId));
	}

	/**
	 * Removes the app entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the app entry
	 * @return the app entry that was removed
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppEntry remove(Serializable primaryKey)
		throws NoSuchAppEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AppEntry appEntry = (AppEntry)session.get(AppEntryImpl.class,
					primaryKey);

			if (appEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAppEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(appEntry);
		}
		catch (NoSuchAppEntryException nsee) {
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
	protected AppEntry removeImpl(AppEntry appEntry) throws SystemException {
		appEntry = toUnwrappedModel(appEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, appEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(appEntry);

		return appEntry;
	}

	@Override
	public AppEntry updateImpl(com.liferay.osb.model.AppEntry appEntry,
		boolean merge) throws SystemException {
		appEntry = toUnwrappedModel(appEntry);

		boolean isNew = appEntry.isNew();

		AppEntryModelImpl appEntryModelImpl = (AppEntryModelImpl)appEntry;

		if (Validator.isNull(appEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			appEntry.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, appEntry, merge);

			appEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AppEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((appEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { appEntryModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { appEntryModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((appEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DEVELOPERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appEntryModelImpl.getOriginalDeveloperEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DEVELOPERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DEVELOPERENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(appEntryModelImpl.getDeveloperEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DEVELOPERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DEVELOPERENTRYID,
					args);
			}

			if ((appEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(appEntryModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] {
						Integer.valueOf(appEntryModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((appEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DEI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appEntryModelImpl.getOriginalDeveloperEntryId()),
						Integer.valueOf(appEntryModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DEI_S,
					args);

				args = new Object[] {
						Long.valueOf(appEntryModelImpl.getDeveloperEntryId()),
						Integer.valueOf(appEntryModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DEI_S,
					args);
			}
		}

		EntityCacheUtil.putResult(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryImpl.class, appEntry.getPrimaryKey(), appEntry);

		clearUniqueFindersCache(appEntry);
		cacheUniqueFindersCache(appEntry);

		return appEntry;
	}

	protected AppEntry toUnwrappedModel(AppEntry appEntry) {
		if (appEntry instanceof AppEntryImpl) {
			return appEntry;
		}

		AppEntryImpl appEntryImpl = new AppEntryImpl();

		appEntryImpl.setNew(appEntry.isNew());
		appEntryImpl.setPrimaryKey(appEntry.getPrimaryKey());

		appEntryImpl.setUuid(appEntry.getUuid());
		appEntryImpl.setAppEntryId(appEntry.getAppEntryId());
		appEntryImpl.setUserId(appEntry.getUserId());
		appEntryImpl.setUserName(appEntry.getUserName());
		appEntryImpl.setCreateDate(appEntry.getCreateDate());
		appEntryImpl.setModifiedDate(appEntry.getModifiedDate());
		appEntryImpl.setDeveloperEntryId(appEntry.getDeveloperEntryId());
		appEntryImpl.setDeveloperName(appEntry.getDeveloperName());
		appEntryImpl.setTitle(appEntry.getTitle());
		appEntryImpl.setDescription(appEntry.getDescription());
		appEntryImpl.setWebsite(appEntry.getWebsite());
		appEntryImpl.setDemoWebsite(appEntry.getDemoWebsite());
		appEntryImpl.setDocumentationWebsite(appEntry.getDocumentationWebsite());
		appEntryImpl.setReferenceWebsite(appEntry.getReferenceWebsite());
		appEntryImpl.setSourceCodeWebsite(appEntry.getSourceCodeWebsite());
		appEntryImpl.setSupportWebsite(appEntry.getSupportWebsite());
		appEntryImpl.setLabs(appEntry.isLabs());
		appEntryImpl.setProductType(appEntry.getProductType());
		appEntryImpl.setVersion(appEntry.getVersion());
		appEntryImpl.setChangeLog(appEntry.getChangeLog());
		appEntryImpl.setIconImageId(appEntry.getIconImageId());
		appEntryImpl.setPaclEnabled(appEntry.isPaclEnabled());
		appEntryImpl.setSize(appEntry.getSize());
		appEntryImpl.setDownloadCount(appEntry.getDownloadCount());
		appEntryImpl.setLicenseType(appEntry.getLicenseType());
		appEntryImpl.setLicenseLifetime(appEntry.getLicenseLifetime());
		appEntryImpl.setSupported(appEntry.isSupported());
		appEntryImpl.setOrderURL(appEntry.getOrderURL());
		appEntryImpl.setHidden(appEntry.isHidden());
		appEntryImpl.setPortalRequired(appEntry.isPortalRequired());
		appEntryImpl.setStatus(appEntry.getStatus());
		appEntryImpl.setStatusByUserId(appEntry.getStatusByUserId());
		appEntryImpl.setStatusByUserName(appEntry.getStatusByUserName());
		appEntryImpl.setStatusDate(appEntry.getStatusDate());
		appEntryImpl.setStatusVersionDate(appEntry.getStatusVersionDate());

		return appEntryImpl;
	}

	/**
	 * Returns the app entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the app entry
	 * @return the app entry
	 * @throws com.liferay.portal.NoSuchModelException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app entry with the primary key or throws a {@link com.liferay.osb.NoSuchAppEntryException} if it could not be found.
	 *
	 * @param appEntryId the primary key of the app entry
	 * @return the app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByPrimaryKey(long appEntryId)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByPrimaryKey(appEntryId);

		if (appEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + appEntryId);
			}

			throw new NoSuchAppEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				appEntryId);
		}

		return appEntry;
	}

	/**
	 * Returns the app entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the app entry
	 * @return the app entry, or <code>null</code> if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param appEntryId the primary key of the app entry
	 * @return the app entry, or <code>null</code> if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByPrimaryKey(long appEntryId)
		throws SystemException {
		AppEntry appEntry = (AppEntry)EntityCacheUtil.getResult(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
				AppEntryImpl.class, appEntryId);

		if (appEntry == _nullAppEntry) {
			return null;
		}

		if (appEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				appEntry = (AppEntry)session.get(AppEntryImpl.class,
						Long.valueOf(appEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (appEntry != null) {
					cacheResult(appEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AppEntryModelImpl.ENTITY_CACHE_ENABLED,
						AppEntryImpl.class, appEntryId, _nullAppEntry);
				}

				closeSession(session);
			}
		}

		return appEntry;
	}

	/**
	 * Returns all the app entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @return the range of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<AppEntry> list = (List<AppEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppEntry appEntry : list) {
				if (!Validator.equals(uuid, appEntry.getUuid())) {
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

			query.append(_SQL_SELECT_APPENTRY_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<AppEntry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByUuid_First(uuid, orderByComparator);

		if (appEntry != null) {
			return appEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryException(msg.toString());
	}

	/**
	 * Returns the first app entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppEntry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByUuid_Last(uuid, orderByComparator);

		if (appEntry != null) {
			return appEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryException(msg.toString());
	}

	/**
	 * Returns the last app entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<AppEntry> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app entries before and after the current app entry in the ordered set where uuid = &#63;.
	 *
	 * @param appEntryId the primary key of the current app entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry[] findByUuid_PrevAndNext(long appEntryId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = findByPrimaryKey(appEntryId);

		Session session = null;

		try {
			session = openSession();

			AppEntry[] array = new AppEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, appEntry, uuid,
					orderByComparator, true);

			array[1] = appEntry;

			array[2] = getByUuid_PrevAndNext(session, appEntry, uuid,
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

	protected AppEntry getByUuid_PrevAndNext(Session session,
		AppEntry appEntry, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPENTRY_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

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
			query.append(AppEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app entries that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByUuid(String uuid)
		throws SystemException {
		return filterFindByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entries that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @return the range of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByUuid(String uuid, int start, int end)
		throws SystemException {
		return filterFindByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entries that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid(uuid, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AppEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AppEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AppEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (uuid != null) {
				qPos.add(uuid);
			}

			return (List<AppEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the app entries before and after the current app entry in the ordered set of app entries that the user has permission to view where uuid = &#63;.
	 *
	 * @param appEntryId the primary key of the current app entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry[] filterFindByUuid_PrevAndNext(long appEntryId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(appEntryId, uuid, orderByComparator);
		}

		AppEntry appEntry = findByPrimaryKey(appEntryId);

		Session session = null;

		try {
			session = openSession();

			AppEntry[] array = new AppEntryImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(session, appEntry, uuid,
					orderByComparator, true);

			array[1] = appEntry;

			array[2] = filterGetByUuid_PrevAndNext(session, appEntry, uuid,
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

	protected AppEntry filterGetByUuid_PrevAndNext(Session session,
		AppEntry appEntry, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AppEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, AppEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, AppEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app entries where developerEntryId = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @return the matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByDeveloperEntryId(long developerEntryId)
		throws SystemException {
		return findByDeveloperEntryId(developerEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entries where developerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @return the range of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByDeveloperEntryId(long developerEntryId,
		int start, int end) throws SystemException {
		return findByDeveloperEntryId(developerEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entries where developerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByDeveloperEntryId(long developerEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DEVELOPERENTRYID;
			finderArgs = new Object[] { developerEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DEVELOPERENTRYID;
			finderArgs = new Object[] {
					developerEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AppEntry> list = (List<AppEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppEntry appEntry : list) {
				if ((developerEntryId != appEntry.getDeveloperEntryId())) {
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

			query.append(_SQL_SELECT_APPENTRY_WHERE);

			query.append(_FINDER_COLUMN_DEVELOPERENTRYID_DEVELOPERENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(developerEntryId);

				list = (List<AppEntry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app entry in the ordered set where developerEntryId = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByDeveloperEntryId_First(long developerEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByDeveloperEntryId_First(developerEntryId,
				orderByComparator);

		if (appEntry != null) {
			return appEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("developerEntryId=");
		msg.append(developerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryException(msg.toString());
	}

	/**
	 * Returns the first app entry in the ordered set where developerEntryId = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByDeveloperEntryId_First(long developerEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppEntry> list = findByDeveloperEntryId(developerEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app entry in the ordered set where developerEntryId = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByDeveloperEntryId_Last(long developerEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByDeveloperEntryId_Last(developerEntryId,
				orderByComparator);

		if (appEntry != null) {
			return appEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("developerEntryId=");
		msg.append(developerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryException(msg.toString());
	}

	/**
	 * Returns the last app entry in the ordered set where developerEntryId = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByDeveloperEntryId_Last(long developerEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDeveloperEntryId(developerEntryId);

		List<AppEntry> list = findByDeveloperEntryId(developerEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app entries before and after the current app entry in the ordered set where developerEntryId = &#63;.
	 *
	 * @param appEntryId the primary key of the current app entry
	 * @param developerEntryId the developer entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry[] findByDeveloperEntryId_PrevAndNext(long appEntryId,
		long developerEntryId, OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = findByPrimaryKey(appEntryId);

		Session session = null;

		try {
			session = openSession();

			AppEntry[] array = new AppEntryImpl[3];

			array[0] = getByDeveloperEntryId_PrevAndNext(session, appEntry,
					developerEntryId, orderByComparator, true);

			array[1] = appEntry;

			array[2] = getByDeveloperEntryId_PrevAndNext(session, appEntry,
					developerEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppEntry getByDeveloperEntryId_PrevAndNext(Session session,
		AppEntry appEntry, long developerEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPENTRY_WHERE);

		query.append(_FINDER_COLUMN_DEVELOPERENTRYID_DEVELOPERENTRYID_2);

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
			query.append(AppEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(developerEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app entries that the user has permission to view where developerEntryId = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @return the matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByDeveloperEntryId(long developerEntryId)
		throws SystemException {
		return filterFindByDeveloperEntryId(developerEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entries that the user has permission to view where developerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @return the range of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByDeveloperEntryId(long developerEntryId,
		int start, int end) throws SystemException {
		return filterFindByDeveloperEntryId(developerEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entries that the user has permissions to view where developerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByDeveloperEntryId(long developerEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByDeveloperEntryId(developerEntryId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_DEVELOPERENTRYID_DEVELOPERENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AppEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AppEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AppEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(developerEntryId);

			return (List<AppEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the app entries before and after the current app entry in the ordered set of app entries that the user has permission to view where developerEntryId = &#63;.
	 *
	 * @param appEntryId the primary key of the current app entry
	 * @param developerEntryId the developer entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry[] filterFindByDeveloperEntryId_PrevAndNext(
		long appEntryId, long developerEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByDeveloperEntryId_PrevAndNext(appEntryId,
				developerEntryId, orderByComparator);
		}

		AppEntry appEntry = findByPrimaryKey(appEntryId);

		Session session = null;

		try {
			session = openSession();

			AppEntry[] array = new AppEntryImpl[3];

			array[0] = filterGetByDeveloperEntryId_PrevAndNext(session,
					appEntry, developerEntryId, orderByComparator, true);

			array[1] = appEntry;

			array[2] = filterGetByDeveloperEntryId_PrevAndNext(session,
					appEntry, developerEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppEntry filterGetByDeveloperEntryId_PrevAndNext(
		Session session, AppEntry appEntry, long developerEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_DEVELOPERENTRYID_DEVELOPERENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AppEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, AppEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, AppEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(developerEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the app entry where title = &#63; or throws a {@link com.liferay.osb.NoSuchAppEntryException} if it could not be found.
	 *
	 * @param title the title
	 * @return the matching app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByTitle(String title)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByTitle(title);

		if (appEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("title=");
			msg.append(title);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAppEntryException(msg.toString());
		}

		return appEntry;
	}

	/**
	 * Returns the app entry where title = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param title the title
	 * @return the matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByTitle(String title) throws SystemException {
		return fetchByTitle(title, true);
	}

	/**
	 * Returns the app entry where title = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param title the title
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByTitle(String title, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { title };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TITLE,
					finderArgs, this);
		}

		if (result instanceof AppEntry) {
			AppEntry appEntry = (AppEntry)result;

			if (!Validator.equals(title, appEntry.getTitle())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_APPENTRY_WHERE);

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_1);
			}
			else {
				if (title.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TITLE_TITLE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TITLE_TITLE_2);
				}
			}

			query.append(AppEntryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (title != null) {
					qPos.add(title);
				}

				List<AppEntry> list = q.list();

				result = list;

				AppEntry appEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TITLE,
						finderArgs, list);
				}
				else {
					appEntry = list.get(0);

					cacheResult(appEntry);

					if ((appEntry.getTitle() == null) ||
							!appEntry.getTitle().equals(title)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TITLE,
							finderArgs, appEntry);
					}
				}

				return appEntry;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TITLE,
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
				return (AppEntry)result;
			}
		}
	}

	/**
	 * Returns all the app entries where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByStatus(int status) throws SystemException {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entries where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @return the range of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByStatus(int status, int start, int end)
		throws SystemException {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entries where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByStatus(int status, int start, int end,
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

		List<AppEntry> list = (List<AppEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppEntry appEntry : list) {
				if ((status != appEntry.getStatus())) {
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

			query.append(_SQL_SELECT_APPENTRY_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				list = (List<AppEntry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByStatus_First(int status,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByStatus_First(status, orderByComparator);

		if (appEntry != null) {
			return appEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryException(msg.toString());
	}

	/**
	 * Returns the first app entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByStatus_First(int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppEntry> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByStatus_Last(int status,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByStatus_Last(status, orderByComparator);

		if (appEntry != null) {
			return appEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryException(msg.toString());
	}

	/**
	 * Returns the last app entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByStatus_Last(int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStatus(status);

		List<AppEntry> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app entries before and after the current app entry in the ordered set where status = &#63;.
	 *
	 * @param appEntryId the primary key of the current app entry
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry[] findByStatus_PrevAndNext(long appEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = findByPrimaryKey(appEntryId);

		Session session = null;

		try {
			session = openSession();

			AppEntry[] array = new AppEntryImpl[3];

			array[0] = getByStatus_PrevAndNext(session, appEntry, status,
					orderByComparator, true);

			array[1] = appEntry;

			array[2] = getByStatus_PrevAndNext(session, appEntry, status,
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

	protected AppEntry getByStatus_PrevAndNext(Session session,
		AppEntry appEntry, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPENTRY_WHERE);

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
			query.append(AppEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app entries that the user has permission to view where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByStatus(int status)
		throws SystemException {
		return filterFindByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the app entries that the user has permission to view where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @return the range of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByStatus(int status, int start, int end)
		throws SystemException {
		return filterFindByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entries that the user has permissions to view where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByStatus(int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByStatus(status, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AppEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AppEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AppEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(status);

			return (List<AppEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the app entries before and after the current app entry in the ordered set of app entries that the user has permission to view where status = &#63;.
	 *
	 * @param appEntryId the primary key of the current app entry
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry[] filterFindByStatus_PrevAndNext(long appEntryId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByStatus_PrevAndNext(appEntryId, status,
				orderByComparator);
		}

		AppEntry appEntry = findByPrimaryKey(appEntryId);

		Session session = null;

		try {
			session = openSession();

			AppEntry[] array = new AppEntryImpl[3];

			array[0] = filterGetByStatus_PrevAndNext(session, appEntry, status,
					orderByComparator, true);

			array[1] = appEntry;

			array[2] = filterGetByStatus_PrevAndNext(session, appEntry, status,
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

	protected AppEntry filterGetByStatus_PrevAndNext(Session session,
		AppEntry appEntry, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AppEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, AppEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, AppEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app entries where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @return the matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByDEI_LikeT(long developerEntryId, String title)
		throws SystemException {
		return findByDEI_LikeT(developerEntryId, title, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entries where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @return the range of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByDEI_LikeT(long developerEntryId, String title,
		int start, int end) throws SystemException {
		return findByDEI_LikeT(developerEntryId, title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entries where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByDEI_LikeT(long developerEntryId, String title,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DEI_LIKET;
		finderArgs = new Object[] {
				developerEntryId, title,
				
				start, end, orderByComparator
			};

		List<AppEntry> list = (List<AppEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppEntry appEntry : list) {
				if ((developerEntryId != appEntry.getDeveloperEntryId()) ||
						!Validator.equals(title, appEntry.getTitle())) {
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

			query.append(_SQL_SELECT_APPENTRY_WHERE);

			query.append(_FINDER_COLUMN_DEI_LIKET_DEVELOPERENTRYID_2);

			if (title == null) {
				query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_1);
			}
			else {
				if (title.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(developerEntryId);

				if (title != null) {
					qPos.add(title);
				}

				list = (List<AppEntry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByDEI_LikeT_First(long developerEntryId, String title,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByDEI_LikeT_First(developerEntryId, title,
				orderByComparator);

		if (appEntry != null) {
			return appEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("developerEntryId=");
		msg.append(developerEntryId);

		msg.append(", title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryException(msg.toString());
	}

	/**
	 * Returns the first app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByDEI_LikeT_First(long developerEntryId, String title,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppEntry> list = findByDEI_LikeT(developerEntryId, title, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByDEI_LikeT_Last(long developerEntryId, String title,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByDEI_LikeT_Last(developerEntryId, title,
				orderByComparator);

		if (appEntry != null) {
			return appEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("developerEntryId=");
		msg.append(developerEntryId);

		msg.append(", title=");
		msg.append(title);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryException(msg.toString());
	}

	/**
	 * Returns the last app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByDEI_LikeT_Last(long developerEntryId, String title,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDEI_LikeT(developerEntryId, title);

		List<AppEntry> list = findByDEI_LikeT(developerEntryId, title,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app entries before and after the current app entry in the ordered set where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * @param appEntryId the primary key of the current app entry
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry[] findByDEI_LikeT_PrevAndNext(long appEntryId,
		long developerEntryId, String title, OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = findByPrimaryKey(appEntryId);

		Session session = null;

		try {
			session = openSession();

			AppEntry[] array = new AppEntryImpl[3];

			array[0] = getByDEI_LikeT_PrevAndNext(session, appEntry,
					developerEntryId, title, orderByComparator, true);

			array[1] = appEntry;

			array[2] = getByDEI_LikeT_PrevAndNext(session, appEntry,
					developerEntryId, title, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppEntry getByDEI_LikeT_PrevAndNext(Session session,
		AppEntry appEntry, long developerEntryId, String title,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPENTRY_WHERE);

		query.append(_FINDER_COLUMN_DEI_LIKET_DEVELOPERENTRYID_2);

		if (title == null) {
			query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_1);
		}
		else {
			if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_2);
			}
		}

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
			query.append(AppEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(developerEntryId);

		if (title != null) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app entries that the user has permission to view where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @return the matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByDEI_LikeT(long developerEntryId,
		String title) throws SystemException {
		return filterFindByDEI_LikeT(developerEntryId, title,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entries that the user has permission to view where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @return the range of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByDEI_LikeT(long developerEntryId,
		String title, int start, int end) throws SystemException {
		return filterFindByDEI_LikeT(developerEntryId, title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entries that the user has permissions to view where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByDEI_LikeT(long developerEntryId,
		String title, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByDEI_LikeT(developerEntryId, title, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_DEI_LIKET_DEVELOPERENTRYID_2);

		if (title == null) {
			query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_1);
		}
		else {
			if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AppEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AppEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AppEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(developerEntryId);

			if (title != null) {
				qPos.add(title);
			}

			return (List<AppEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the app entries before and after the current app entry in the ordered set of app entries that the user has permission to view where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * @param appEntryId the primary key of the current app entry
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry[] filterFindByDEI_LikeT_PrevAndNext(long appEntryId,
		long developerEntryId, String title, OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByDEI_LikeT_PrevAndNext(appEntryId, developerEntryId,
				title, orderByComparator);
		}

		AppEntry appEntry = findByPrimaryKey(appEntryId);

		Session session = null;

		try {
			session = openSession();

			AppEntry[] array = new AppEntryImpl[3];

			array[0] = filterGetByDEI_LikeT_PrevAndNext(session, appEntry,
					developerEntryId, title, orderByComparator, true);

			array[1] = appEntry;

			array[2] = filterGetByDEI_LikeT_PrevAndNext(session, appEntry,
					developerEntryId, title, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppEntry filterGetByDEI_LikeT_PrevAndNext(Session session,
		AppEntry appEntry, long developerEntryId, String title,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_DEI_LIKET_DEVELOPERENTRYID_2);

		if (title == null) {
			query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_1);
		}
		else {
			if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AppEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, AppEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, AppEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(developerEntryId);

		if (title != null) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app entries where developerEntryId = &#63; and status = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @return the matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByDEI_S(long developerEntryId, int status)
		throws SystemException {
		return findByDEI_S(developerEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entries where developerEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @return the range of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByDEI_S(long developerEntryId, int status,
		int start, int end) throws SystemException {
		return findByDEI_S(developerEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entries where developerEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findByDEI_S(long developerEntryId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DEI_S;
			finderArgs = new Object[] { developerEntryId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DEI_S;
			finderArgs = new Object[] {
					developerEntryId, status,
					
					start, end, orderByComparator
				};
		}

		List<AppEntry> list = (List<AppEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppEntry appEntry : list) {
				if ((developerEntryId != appEntry.getDeveloperEntryId()) ||
						(status != appEntry.getStatus())) {
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

			query.append(_SQL_SELECT_APPENTRY_WHERE);

			query.append(_FINDER_COLUMN_DEI_S_DEVELOPERENTRYID_2);

			query.append(_FINDER_COLUMN_DEI_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(developerEntryId);

				qPos.add(status);

				list = (List<AppEntry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByDEI_S_First(long developerEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByDEI_S_First(developerEntryId, status,
				orderByComparator);

		if (appEntry != null) {
			return appEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("developerEntryId=");
		msg.append(developerEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryException(msg.toString());
	}

	/**
	 * Returns the first app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByDEI_S_First(long developerEntryId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppEntry> list = findByDEI_S(developerEntryId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry findByDEI_S_Last(long developerEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = fetchByDEI_S_Last(developerEntryId, status,
				orderByComparator);

		if (appEntry != null) {
			return appEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("developerEntryId=");
		msg.append(developerEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryException(msg.toString());
	}

	/**
	 * Returns the last app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry, or <code>null</code> if a matching app entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry fetchByDEI_S_Last(long developerEntryId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDEI_S(developerEntryId, status);

		List<AppEntry> list = findByDEI_S(developerEntryId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app entries before and after the current app entry in the ordered set where developerEntryId = &#63; and status = &#63;.
	 *
	 * @param appEntryId the primary key of the current app entry
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry[] findByDEI_S_PrevAndNext(long appEntryId,
		long developerEntryId, int status, OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = findByPrimaryKey(appEntryId);

		Session session = null;

		try {
			session = openSession();

			AppEntry[] array = new AppEntryImpl[3];

			array[0] = getByDEI_S_PrevAndNext(session, appEntry,
					developerEntryId, status, orderByComparator, true);

			array[1] = appEntry;

			array[2] = getByDEI_S_PrevAndNext(session, appEntry,
					developerEntryId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppEntry getByDEI_S_PrevAndNext(Session session,
		AppEntry appEntry, long developerEntryId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPENTRY_WHERE);

		query.append(_FINDER_COLUMN_DEI_S_DEVELOPERENTRYID_2);

		query.append(_FINDER_COLUMN_DEI_S_STATUS_2);

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
			query.append(AppEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(developerEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app entries that the user has permission to view where developerEntryId = &#63; and status = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @return the matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByDEI_S(long developerEntryId, int status)
		throws SystemException {
		return filterFindByDEI_S(developerEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entries that the user has permission to view where developerEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @return the range of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByDEI_S(long developerEntryId, int status,
		int start, int end) throws SystemException {
		return filterFindByDEI_S(developerEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entries that the user has permissions to view where developerEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> filterFindByDEI_S(long developerEntryId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByDEI_S(developerEntryId, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_DEI_S_DEVELOPERENTRYID_2);

		query.append(_FINDER_COLUMN_DEI_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AppEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, AppEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, AppEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(developerEntryId);

			qPos.add(status);

			return (List<AppEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the app entries before and after the current app entry in the ordered set of app entries that the user has permission to view where developerEntryId = &#63; and status = &#63;.
	 *
	 * @param appEntryId the primary key of the current app entry
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry
	 * @throws com.liferay.osb.NoSuchAppEntryException if a app entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry[] filterFindByDEI_S_PrevAndNext(long appEntryId,
		long developerEntryId, int status, OrderByComparator orderByComparator)
		throws NoSuchAppEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByDEI_S_PrevAndNext(appEntryId, developerEntryId,
				status, orderByComparator);
		}

		AppEntry appEntry = findByPrimaryKey(appEntryId);

		Session session = null;

		try {
			session = openSession();

			AppEntry[] array = new AppEntryImpl[3];

			array[0] = filterGetByDEI_S_PrevAndNext(session, appEntry,
					developerEntryId, status, orderByComparator, true);

			array[1] = appEntry;

			array[2] = filterGetByDEI_S_PrevAndNext(session, appEntry,
					developerEntryId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppEntry filterGetByDEI_S_PrevAndNext(Session session,
		AppEntry appEntry, long developerEntryId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_DEI_S_DEVELOPERENTRYID_2);

		query.append(_FINDER_COLUMN_DEI_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(AppEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(AppEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, AppEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, AppEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(developerEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app entries.
	 *
	 * @return the app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @return the range of app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app entries
	 * @param end the upper bound of the range of app entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of app entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntry> findAll(int start, int end,
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

		List<AppEntry> list = (List<AppEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_APPENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APPENTRY.concat(AppEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AppEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AppEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the app entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (AppEntry appEntry : findByUuid(uuid)) {
			remove(appEntry);
		}
	}

	/**
	 * Removes all the app entries where developerEntryId = &#63; from the database.
	 *
	 * @param developerEntryId the developer entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDeveloperEntryId(long developerEntryId)
		throws SystemException {
		for (AppEntry appEntry : findByDeveloperEntryId(developerEntryId)) {
			remove(appEntry);
		}
	}

	/**
	 * Removes the app entry where title = &#63; from the database.
	 *
	 * @param title the title
	 * @return the app entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntry removeByTitle(String title)
		throws NoSuchAppEntryException, SystemException {
		AppEntry appEntry = findByTitle(title);

		return remove(appEntry);
	}

	/**
	 * Removes all the app entries where status = &#63; from the database.
	 *
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByStatus(int status) throws SystemException {
		for (AppEntry appEntry : findByStatus(status)) {
			remove(appEntry);
		}
	}

	/**
	 * Removes all the app entries where developerEntryId = &#63; and title LIKE &#63; from the database.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDEI_LikeT(long developerEntryId, String title)
		throws SystemException {
		for (AppEntry appEntry : findByDEI_LikeT(developerEntryId, title)) {
			remove(appEntry);
		}
	}

	/**
	 * Removes all the app entries where developerEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDEI_S(long developerEntryId, int status)
		throws SystemException {
		for (AppEntry appEntry : findByDEI_S(developerEntryId, status)) {
			remove(appEntry);
		}
	}

	/**
	 * Removes all the app entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AppEntry appEntry : findAll()) {
			remove(appEntry);
		}
	}

	/**
	 * Returns the number of app entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPENTRY_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app entries that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByUuid(String uuid) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_APPENTRY_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (uuid != null) {
				qPos.add(uuid);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of app entries where developerEntryId = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @return the number of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDeveloperEntryId(long developerEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { developerEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEVELOPERENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPENTRY_WHERE);

			query.append(_FINDER_COLUMN_DEVELOPERENTRYID_DEVELOPERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(developerEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEVELOPERENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app entries that the user has permission to view where developerEntryId = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @return the number of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByDeveloperEntryId(long developerEntryId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByDeveloperEntryId(developerEntryId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_APPENTRY_WHERE);

		query.append(_FINDER_COLUMN_DEVELOPERENTRYID_DEVELOPERENTRYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(developerEntryId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of app entries where title = &#63;.
	 *
	 * @param title the title
	 * @return the number of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTitle(String title) throws SystemException {
		Object[] finderArgs = new Object[] { title };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TITLE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPENTRY_WHERE);

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_1);
			}
			else {
				if (title.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TITLE_TITLE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TITLE_TITLE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (title != null) {
					qPos.add(title);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TITLE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app entries where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByStatus(int status) throws SystemException {
		Object[] finderArgs = new Object[] { status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_STATUS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPENTRY_WHERE);

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
	 * Returns the number of app entries that the user has permission to view where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByStatus(int status) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByStatus(status);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_APPENTRY_WHERE);

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(status);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of app entries where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @return the number of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDEI_LikeT(long developerEntryId, String title)
		throws SystemException {
		Object[] finderArgs = new Object[] { developerEntryId, title };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_DEI_LIKET,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPENTRY_WHERE);

			query.append(_FINDER_COLUMN_DEI_LIKET_DEVELOPERENTRYID_2);

			if (title == null) {
				query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_1);
			}
			else {
				if (title.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(developerEntryId);

				if (title != null) {
					qPos.add(title);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_DEI_LIKET,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app entries that the user has permission to view where developerEntryId = &#63; and title LIKE &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param title the title
	 * @return the number of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByDEI_LikeT(long developerEntryId, String title)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByDEI_LikeT(developerEntryId, title);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_APPENTRY_WHERE);

		query.append(_FINDER_COLUMN_DEI_LIKET_DEVELOPERENTRYID_2);

		if (title == null) {
			query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_1);
		}
		else {
			if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DEI_LIKET_TITLE_2);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(developerEntryId);

			if (title != null) {
				qPos.add(title);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of app entries where developerEntryId = &#63; and status = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @return the number of matching app entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDEI_S(long developerEntryId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { developerEntryId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPENTRY_WHERE);

			query.append(_FINDER_COLUMN_DEI_S_DEVELOPERENTRYID_2);

			query.append(_FINDER_COLUMN_DEI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(developerEntryId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app entries that the user has permission to view where developerEntryId = &#63; and status = &#63;.
	 *
	 * @param developerEntryId the developer entry ID
	 * @param status the status
	 * @return the number of matching app entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByDEI_S(long developerEntryId, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByDEI_S(developerEntryId, status);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_APPENTRY_WHERE);

		query.append(_FINDER_COLUMN_DEI_S_DEVELOPERENTRYID_2);

		query.append(_FINDER_COLUMN_DEI_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				AppEntry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(developerEntryId);

			qPos.add(status);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of app entries.
	 *
	 * @return the number of app entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APPENTRY);

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
	 * Initializes the app entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AppEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AppEntry>> listenersList = new ArrayList<ModelListener<AppEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AppEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AppEntryImpl.class.getName());
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
	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;
	@BeanReference(type = AssetCategoryPersistence.class)
	protected AssetCategoryPersistence assetCategoryPersistence;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	@BeanReference(type = MBMessagePersistence.class)
	protected MBMessagePersistence mbMessagePersistence;
	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;
	private static final String _SQL_SELECT_APPENTRY = "SELECT appEntry FROM AppEntry appEntry";
	private static final String _SQL_SELECT_APPENTRY_WHERE = "SELECT appEntry FROM AppEntry appEntry WHERE ";
	private static final String _SQL_COUNT_APPENTRY = "SELECT COUNT(appEntry) FROM AppEntry appEntry";
	private static final String _SQL_COUNT_APPENTRY_WHERE = "SELECT COUNT(appEntry) FROM AppEntry appEntry WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "appEntry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "appEntry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(appEntry.uuid IS NULL OR appEntry.uuid = ?)";
	private static final String _FINDER_COLUMN_DEVELOPERENTRYID_DEVELOPERENTRYID_2 =
		"appEntry.developerEntryId = ?";
	private static final String _FINDER_COLUMN_TITLE_TITLE_1 = "appEntry.title IS NULL";
	private static final String _FINDER_COLUMN_TITLE_TITLE_2 = "appEntry.title = ?";
	private static final String _FINDER_COLUMN_TITLE_TITLE_3 = "(appEntry.title IS NULL OR appEntry.title = ?)";
	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "appEntry.status = ?";
	private static final String _FINDER_COLUMN_DEI_LIKET_DEVELOPERENTRYID_2 = "appEntry.developerEntryId = ? AND ";
	private static final String _FINDER_COLUMN_DEI_LIKET_TITLE_1 = "appEntry.title LIKE NULL";
	private static final String _FINDER_COLUMN_DEI_LIKET_TITLE_2 = "appEntry.title LIKE ?";
	private static final String _FINDER_COLUMN_DEI_LIKET_TITLE_3 = "(appEntry.title IS NULL OR appEntry.title LIKE ?)";
	private static final String _FINDER_COLUMN_DEI_S_DEVELOPERENTRYID_2 = "appEntry.developerEntryId = ? AND ";
	private static final String _FINDER_COLUMN_DEI_S_STATUS_2 = "appEntry.status = ?";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "appEntry.appEntryId";
	private static final String _FILTER_SQL_SELECT_APPENTRY_WHERE = "SELECT DISTINCT {appEntry.*} FROM OSB_AppEntry appEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {OSB_AppEntry.*} FROM (SELECT DISTINCT appEntry.appEntryId FROM OSB_AppEntry appEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_APPENTRY_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN OSB_AppEntry ON TEMP_TABLE.appEntryId = OSB_AppEntry.appEntryId";
	private static final String _FILTER_SQL_COUNT_APPENTRY_WHERE = "SELECT COUNT(DISTINCT appEntry.appEntryId) AS COUNT_VALUE FROM OSB_AppEntry appEntry WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "appEntry";
	private static final String _FILTER_ENTITY_TABLE = "OSB_AppEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "appEntry.";
	private static final String _ORDER_BY_ENTITY_TABLE = "OSB_AppEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AppEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AppEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AppEntryPersistenceImpl.class);
	private static AppEntry _nullAppEntry = new AppEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AppEntry> toCacheModel() {
				return _nullAppEntryCacheModel;
			}
		};

	private static CacheModel<AppEntry> _nullAppEntryCacheModel = new CacheModel<AppEntry>() {
			public AppEntry toEntityModel() {
				return _nullAppEntry;
			}
		};
}
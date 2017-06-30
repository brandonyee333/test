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

import com.liferay.osb.NoSuchAppPackagePluginException;
import com.liferay.osb.model.AppPackagePlugin;
import com.liferay.osb.model.impl.AppPackagePluginImpl;
import com.liferay.osb.model.impl.AppPackagePluginModelImpl;

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
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the app package plugin service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPackagePluginPersistence
 * @see AppPackagePluginUtil
 * @generated
 */
public class AppPackagePluginPersistenceImpl extends BasePersistenceImpl<AppPackagePlugin>
	implements AppPackagePluginPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AppPackagePluginUtil} to access the app package plugin persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AppPackagePluginImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPPACKAGEID =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAppPackageId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPACKAGEID =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppPackageId",
			new String[] { Long.class.getName() },
			AppPackagePluginModelImpl.APPPACKAGEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPPACKAGEID = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppPackageId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_ASSETATTACHMENTID = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAssetAttachmentId", new String[] { Long.class.getName() },
			AppPackagePluginModelImpl.ASSETATTACHMENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETATTACHMENTID = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAssetAttachmentId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTEXTNAME =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByContextName",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTEXTNAME =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContextName",
			new String[] { String.class.getName() },
			AppPackagePluginModelImpl.CONTEXTNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTEXTNAME = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContextName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_API_CN = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAPI_CN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_CN =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAPI_CN",
			new String[] { Long.class.getName(), String.class.getName() },
			AppPackagePluginModelImpl.APPPACKAGEID_COLUMN_BITMASK |
			AppPackagePluginModelImpl.CONTEXTNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_API_CN = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAPI_CN",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_API_FN = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAPI_FN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_FN =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAPI_FN",
			new String[] { Long.class.getName(), String.class.getName() },
			AppPackagePluginModelImpl.APPPACKAGEID_COLUMN_BITMASK |
			AppPackagePluginModelImpl.FILENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_API_FN = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAPI_FN",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_API_PE = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAPI_PE",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_PE =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAPI_PE",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			AppPackagePluginModelImpl.APPPACKAGEID_COLUMN_BITMASK |
			AppPackagePluginModelImpl.PACLENABLED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_API_PE = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAPI_PE",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_API_PRR = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAPI_PRR",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_PRR =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAPI_PRR",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			AppPackagePluginModelImpl.APPPACKAGEID_COLUMN_BITMASK |
			AppPackagePluginModelImpl.PORTALRESTARTREQUIRED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_API_PRR = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAPI_PRR",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NOTAEI_CN =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByNotAEI_CN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOTAEI_CN =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByNotAEI_CN",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_API_BSN_BV =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAPI_BSN_BV",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_BSN_BV =
		new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAPI_BSN_BV",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			AppPackagePluginModelImpl.APPPACKAGEID_COLUMN_BITMASK |
			AppPackagePluginModelImpl.BUNDLESYMBOLICNAME_COLUMN_BITMASK |
			AppPackagePluginModelImpl.BUNDLEVERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_API_BSN_BV = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAPI_BSN_BV",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED,
			AppPackagePluginImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the app package plugin in the entity cache if it is enabled.
	 *
	 * @param appPackagePlugin the app package plugin
	 */
	public void cacheResult(AppPackagePlugin appPackagePlugin) {
		EntityCacheUtil.putResult(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginImpl.class, appPackagePlugin.getPrimaryKey(),
			appPackagePlugin);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ASSETATTACHMENTID,
			new Object[] { Long.valueOf(appPackagePlugin.getAssetAttachmentId()) },
			appPackagePlugin);

		appPackagePlugin.resetOriginalValues();
	}

	/**
	 * Caches the app package plugins in the entity cache if it is enabled.
	 *
	 * @param appPackagePlugins the app package plugins
	 */
	public void cacheResult(List<AppPackagePlugin> appPackagePlugins) {
		for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
			if (EntityCacheUtil.getResult(
						AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
						AppPackagePluginImpl.class,
						appPackagePlugin.getPrimaryKey()) == null) {
				cacheResult(appPackagePlugin);
			}
			else {
				appPackagePlugin.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all app package plugins.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AppPackagePluginImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AppPackagePluginImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the app package plugin.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AppPackagePlugin appPackagePlugin) {
		EntityCacheUtil.removeResult(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginImpl.class, appPackagePlugin.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(appPackagePlugin);
	}

	@Override
	public void clearCache(List<AppPackagePlugin> appPackagePlugins) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
			EntityCacheUtil.removeResult(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
				AppPackagePluginImpl.class, appPackagePlugin.getPrimaryKey());

			clearUniqueFindersCache(appPackagePlugin);
		}
	}

	protected void cacheUniqueFindersCache(AppPackagePlugin appPackagePlugin) {
		if (appPackagePlugin.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(appPackagePlugin.getAssetAttachmentId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ASSETATTACHMENTID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ASSETATTACHMENTID,
				args, appPackagePlugin);
		}
		else {
			AppPackagePluginModelImpl appPackagePluginModelImpl = (AppPackagePluginModelImpl)appPackagePlugin;

			if ((appPackagePluginModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_ASSETATTACHMENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPackagePlugin.getAssetAttachmentId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ASSETATTACHMENTID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ASSETATTACHMENTID,
					args, appPackagePlugin);
			}
		}
	}

	protected void clearUniqueFindersCache(AppPackagePlugin appPackagePlugin) {
		AppPackagePluginModelImpl appPackagePluginModelImpl = (AppPackagePluginModelImpl)appPackagePlugin;

		Object[] args = new Object[] {
				Long.valueOf(appPackagePlugin.getAssetAttachmentId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETATTACHMENTID,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ASSETATTACHMENTID,
			args);

		if ((appPackagePluginModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ASSETATTACHMENTID.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(appPackagePluginModelImpl.getOriginalAssetAttachmentId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETATTACHMENTID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ASSETATTACHMENTID,
				args);
		}
	}

	/**
	 * Creates a new app package plugin with the primary key. Does not add the app package plugin to the database.
	 *
	 * @param appPackagePluginId the primary key for the new app package plugin
	 * @return the new app package plugin
	 */
	public AppPackagePlugin create(long appPackagePluginId) {
		AppPackagePlugin appPackagePlugin = new AppPackagePluginImpl();

		appPackagePlugin.setNew(true);
		appPackagePlugin.setPrimaryKey(appPackagePluginId);

		return appPackagePlugin;
	}

	/**
	 * Removes the app package plugin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param appPackagePluginId the primary key of the app package plugin
	 * @return the app package plugin that was removed
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin remove(long appPackagePluginId)
		throws NoSuchAppPackagePluginException, SystemException {
		return remove(Long.valueOf(appPackagePluginId));
	}

	/**
	 * Removes the app package plugin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the app package plugin
	 * @return the app package plugin that was removed
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPackagePlugin remove(Serializable primaryKey)
		throws NoSuchAppPackagePluginException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AppPackagePlugin appPackagePlugin = (AppPackagePlugin)session.get(AppPackagePluginImpl.class,
					primaryKey);

			if (appPackagePlugin == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAppPackagePluginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(appPackagePlugin);
		}
		catch (NoSuchAppPackagePluginException nsee) {
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
	protected AppPackagePlugin removeImpl(AppPackagePlugin appPackagePlugin)
		throws SystemException {
		appPackagePlugin = toUnwrappedModel(appPackagePlugin);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, appPackagePlugin);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(appPackagePlugin);

		return appPackagePlugin;
	}

	@Override
	public AppPackagePlugin updateImpl(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin, boolean merge)
		throws SystemException {
		appPackagePlugin = toUnwrappedModel(appPackagePlugin);

		boolean isNew = appPackagePlugin.isNew();

		AppPackagePluginModelImpl appPackagePluginModelImpl = (AppPackagePluginModelImpl)appPackagePlugin;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, appPackagePlugin, merge);

			appPackagePlugin.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AppPackagePluginModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((appPackagePluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPACKAGEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getOriginalAppPackageId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPPACKAGEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPACKAGEID,
					args);

				args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getAppPackageId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPPACKAGEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPACKAGEID,
					args);
			}

			if ((appPackagePluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTEXTNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						appPackagePluginModelImpl.getOriginalContextName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTEXTNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTEXTNAME,
					args);

				args = new Object[] { appPackagePluginModelImpl.getContextName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTEXTNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTEXTNAME,
					args);
			}

			if ((appPackagePluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_CN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getOriginalAppPackageId()),
						
						appPackagePluginModelImpl.getOriginalContextName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_CN, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_CN,
					args);

				args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getAppPackageId()),
						
						appPackagePluginModelImpl.getContextName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_CN, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_CN,
					args);
			}

			if ((appPackagePluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_FN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getOriginalAppPackageId()),
						
						appPackagePluginModelImpl.getOriginalFileName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_FN, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_FN,
					args);

				args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getAppPackageId()),
						
						appPackagePluginModelImpl.getFileName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_FN, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_FN,
					args);
			}

			if ((appPackagePluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_PE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getOriginalAppPackageId()),
						Boolean.valueOf(appPackagePluginModelImpl.getOriginalPaclEnabled())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_PE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_PE,
					args);

				args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getAppPackageId()),
						Boolean.valueOf(appPackagePluginModelImpl.getPaclEnabled())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_PE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_PE,
					args);
			}

			if ((appPackagePluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_PRR.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getOriginalAppPackageId()),
						Boolean.valueOf(appPackagePluginModelImpl.getOriginalPortalRestartRequired())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_PRR, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_PRR,
					args);

				args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getAppPackageId()),
						Boolean.valueOf(appPackagePluginModelImpl.getPortalRestartRequired())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_PRR, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_PRR,
					args);
			}

			if ((appPackagePluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_BSN_BV.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getOriginalAppPackageId()),
						
						appPackagePluginModelImpl.getOriginalBundleSymbolicName(),
						
						appPackagePluginModelImpl.getOriginalBundleVersion()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_BSN_BV,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_BSN_BV,
					args);

				args = new Object[] {
						Long.valueOf(appPackagePluginModelImpl.getAppPackageId()),
						
						appPackagePluginModelImpl.getBundleSymbolicName(),
						
						appPackagePluginModelImpl.getBundleVersion()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_BSN_BV,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_BSN_BV,
					args);
			}
		}

		EntityCacheUtil.putResult(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
			AppPackagePluginImpl.class, appPackagePlugin.getPrimaryKey(),
			appPackagePlugin);

		clearUniqueFindersCache(appPackagePlugin);
		cacheUniqueFindersCache(appPackagePlugin);

		return appPackagePlugin;
	}

	protected AppPackagePlugin toUnwrappedModel(
		AppPackagePlugin appPackagePlugin) {
		if (appPackagePlugin instanceof AppPackagePluginImpl) {
			return appPackagePlugin;
		}

		AppPackagePluginImpl appPackagePluginImpl = new AppPackagePluginImpl();

		appPackagePluginImpl.setNew(appPackagePlugin.isNew());
		appPackagePluginImpl.setPrimaryKey(appPackagePlugin.getPrimaryKey());

		appPackagePluginImpl.setAppPackagePluginId(appPackagePlugin.getAppPackagePluginId());
		appPackagePluginImpl.setCreateDate(appPackagePlugin.getCreateDate());
		appPackagePluginImpl.setModifiedDate(appPackagePlugin.getModifiedDate());
		appPackagePluginImpl.setAppEntryId(appPackagePlugin.getAppEntryId());
		appPackagePluginImpl.setAppVersionId(appPackagePlugin.getAppVersionId());
		appPackagePluginImpl.setAppPackageId(appPackagePlugin.getAppPackageId());
		appPackagePluginImpl.setAssetAttachmentId(appPackagePlugin.getAssetAttachmentId());
		appPackagePluginImpl.setFileName(appPackagePlugin.getFileName());
		appPackagePluginImpl.setBundleSymbolicName(appPackagePlugin.getBundleSymbolicName());
		appPackagePluginImpl.setBundleVersion(appPackagePlugin.getBundleVersion());
		appPackagePluginImpl.setContextName(appPackagePlugin.getContextName());
		appPackagePluginImpl.setPaclEnabled(appPackagePlugin.isPaclEnabled());
		appPackagePluginImpl.setRelengHash(appPackagePlugin.getRelengHash());
		appPackagePluginImpl.setPortalRestartRequired(appPackagePlugin.isPortalRestartRequired());

		return appPackagePluginImpl;
	}

	/**
	 * Returns the app package plugin with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the app package plugin
	 * @return the app package plugin
	 * @throws com.liferay.portal.NoSuchModelException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPackagePlugin findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app package plugin with the primary key or throws a {@link com.liferay.osb.NoSuchAppPackagePluginException} if it could not be found.
	 *
	 * @param appPackagePluginId the primary key of the app package plugin
	 * @return the app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByPrimaryKey(long appPackagePluginId)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByPrimaryKey(appPackagePluginId);

		if (appPackagePlugin == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					appPackagePluginId);
			}

			throw new NoSuchAppPackagePluginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				appPackagePluginId);
		}

		return appPackagePlugin;
	}

	/**
	 * Returns the app package plugin with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the app package plugin
	 * @return the app package plugin, or <code>null</code> if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPackagePlugin fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app package plugin with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param appPackagePluginId the primary key of the app package plugin
	 * @return the app package plugin, or <code>null</code> if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByPrimaryKey(long appPackagePluginId)
		throws SystemException {
		AppPackagePlugin appPackagePlugin = (AppPackagePlugin)EntityCacheUtil.getResult(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
				AppPackagePluginImpl.class, appPackagePluginId);

		if (appPackagePlugin == _nullAppPackagePlugin) {
			return null;
		}

		if (appPackagePlugin == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				appPackagePlugin = (AppPackagePlugin)session.get(AppPackagePluginImpl.class,
						Long.valueOf(appPackagePluginId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (appPackagePlugin != null) {
					cacheResult(appPackagePlugin);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AppPackagePluginModelImpl.ENTITY_CACHE_ENABLED,
						AppPackagePluginImpl.class, appPackagePluginId,
						_nullAppPackagePlugin);
				}

				closeSession(session);
			}
		}

		return appPackagePlugin;
	}

	/**
	 * Returns all the app package plugins where appPackageId = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @return the matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAppPackageId(long appPackageId)
		throws SystemException {
		return findByAppPackageId(appPackageId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app package plugins where appPackageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @return the range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAppPackageId(long appPackageId,
		int start, int end) throws SystemException {
		return findByAppPackageId(appPackageId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app package plugins where appPackageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAppPackageId(long appPackageId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPACKAGEID;
			finderArgs = new Object[] { appPackageId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPPACKAGEID;
			finderArgs = new Object[] {
					appPackageId,
					
					start, end, orderByComparator
				};
		}

		List<AppPackagePlugin> list = (List<AppPackagePlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPackagePlugin appPackagePlugin : list) {
				if ((appPackageId != appPackagePlugin.getAppPackageId())) {
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

			query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_APPPACKAGEID_APPPACKAGEID_2);

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

				qPos.add(appPackageId);

				list = (List<AppPackagePlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAppPackageId_First(long appPackageId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAppPackageId_First(appPackageId,
				orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAppPackageId_First(long appPackageId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppPackagePlugin> list = findByAppPackageId(appPackageId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAppPackageId_Last(long appPackageId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAppPackageId_Last(appPackageId,
				orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAppPackageId_Last(long appPackageId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAppPackageId(appPackageId);

		List<AppPackagePlugin> list = findByAppPackageId(appPackageId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63;.
	 *
	 * @param appPackagePluginId the primary key of the current app package plugin
	 * @param appPackageId the app package ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin[] findByAppPackageId_PrevAndNext(
		long appPackagePluginId, long appPackageId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = findByPrimaryKey(appPackagePluginId);

		Session session = null;

		try {
			session = openSession();

			AppPackagePlugin[] array = new AppPackagePluginImpl[3];

			array[0] = getByAppPackageId_PrevAndNext(session, appPackagePlugin,
					appPackageId, orderByComparator, true);

			array[1] = appPackagePlugin;

			array[2] = getByAppPackageId_PrevAndNext(session, appPackagePlugin,
					appPackageId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppPackagePlugin getByAppPackageId_PrevAndNext(Session session,
		AppPackagePlugin appPackagePlugin, long appPackageId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

		query.append(_FINDER_COLUMN_APPPACKAGEID_APPPACKAGEID_2);

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

		qPos.add(appPackageId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPackagePlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPackagePlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the app package plugin where assetAttachmentId = &#63; or throws a {@link com.liferay.osb.NoSuchAppPackagePluginException} if it could not be found.
	 *
	 * @param assetAttachmentId the asset attachment ID
	 * @return the matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAssetAttachmentId(long assetAttachmentId)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAssetAttachmentId(assetAttachmentId);

		if (appPackagePlugin == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("assetAttachmentId=");
			msg.append(assetAttachmentId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAppPackagePluginException(msg.toString());
		}

		return appPackagePlugin;
	}

	/**
	 * Returns the app package plugin where assetAttachmentId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param assetAttachmentId the asset attachment ID
	 * @return the matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAssetAttachmentId(long assetAttachmentId)
		throws SystemException {
		return fetchByAssetAttachmentId(assetAttachmentId, true);
	}

	/**
	 * Returns the app package plugin where assetAttachmentId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param assetAttachmentId the asset attachment ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAssetAttachmentId(long assetAttachmentId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { assetAttachmentId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_ASSETATTACHMENTID,
					finderArgs, this);
		}

		if (result instanceof AppPackagePlugin) {
			AppPackagePlugin appPackagePlugin = (AppPackagePlugin)result;

			if ((assetAttachmentId != appPackagePlugin.getAssetAttachmentId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_ASSETATTACHMENTID_ASSETATTACHMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetAttachmentId);

				List<AppPackagePlugin> list = q.list();

				result = list;

				AppPackagePlugin appPackagePlugin = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ASSETATTACHMENTID,
						finderArgs, list);
				}
				else {
					appPackagePlugin = list.get(0);

					cacheResult(appPackagePlugin);

					if ((appPackagePlugin.getAssetAttachmentId() != assetAttachmentId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ASSETATTACHMENTID,
							finderArgs, appPackagePlugin);
					}
				}

				return appPackagePlugin;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ASSETATTACHMENTID,
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
				return (AppPackagePlugin)result;
			}
		}
	}

	/**
	 * Returns all the app package plugins where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @return the matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByContextName(String contextName)
		throws SystemException {
		return findByContextName(contextName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app package plugins where contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contextName the context name
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @return the range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByContextName(String contextName,
		int start, int end) throws SystemException {
		return findByContextName(contextName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app package plugins where contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contextName the context name
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByContextName(String contextName,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTEXTNAME;
			finderArgs = new Object[] { contextName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTEXTNAME;
			finderArgs = new Object[] { contextName, start, end, orderByComparator };
		}

		List<AppPackagePlugin> list = (List<AppPackagePlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPackagePlugin appPackagePlugin : list) {
				if (!Validator.equals(contextName,
							appPackagePlugin.getContextName())) {
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

			query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

			if (contextName == null) {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1);
			}
			else {
				if (contextName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2);
				}
			}

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

				if (contextName != null) {
					qPos.add(contextName);
				}

				list = (List<AppPackagePlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app package plugin in the ordered set where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByContextName_First(String contextName,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByContextName_First(contextName,
				orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contextName=");
		msg.append(contextName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the first app package plugin in the ordered set where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByContextName_First(String contextName,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppPackagePlugin> list = findByContextName(contextName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app package plugin in the ordered set where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByContextName_Last(String contextName,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByContextName_Last(contextName,
				orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contextName=");
		msg.append(contextName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the last app package plugin in the ordered set where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByContextName_Last(String contextName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByContextName(contextName);

		List<AppPackagePlugin> list = findByContextName(contextName, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app package plugins before and after the current app package plugin in the ordered set where contextName = &#63;.
	 *
	 * @param appPackagePluginId the primary key of the current app package plugin
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin[] findByContextName_PrevAndNext(
		long appPackagePluginId, String contextName,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = findByPrimaryKey(appPackagePluginId);

		Session session = null;

		try {
			session = openSession();

			AppPackagePlugin[] array = new AppPackagePluginImpl[3];

			array[0] = getByContextName_PrevAndNext(session, appPackagePlugin,
					contextName, orderByComparator, true);

			array[1] = appPackagePlugin;

			array[2] = getByContextName_PrevAndNext(session, appPackagePlugin,
					contextName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppPackagePlugin getByContextName_PrevAndNext(Session session,
		AppPackagePlugin appPackagePlugin, String contextName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

		if (contextName == null) {
			query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1);
		}
		else {
			if (contextName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2);
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (contextName != null) {
			qPos.add(contextName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPackagePlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPackagePlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app package plugins where appPackageId = &#63; and contextName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param contextName the context name
	 * @return the matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_CN(long appPackageId,
		String contextName) throws SystemException {
		return findByAPI_CN(appPackageId, contextName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app package plugins where appPackageId = &#63; and contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param contextName the context name
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @return the range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_CN(long appPackageId,
		String contextName, int start, int end) throws SystemException {
		return findByAPI_CN(appPackageId, contextName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app package plugins where appPackageId = &#63; and contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param contextName the context name
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_CN(long appPackageId,
		String contextName, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_CN;
			finderArgs = new Object[] { appPackageId, contextName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_API_CN;
			finderArgs = new Object[] {
					appPackageId, contextName,
					
					start, end, orderByComparator
				};
		}

		List<AppPackagePlugin> list = (List<AppPackagePlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPackagePlugin appPackagePlugin : list) {
				if ((appPackageId != appPackagePlugin.getAppPackageId()) ||
						!Validator.equals(contextName,
							appPackagePlugin.getContextName())) {
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

			query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_API_CN_APPPACKAGEID_2);

			if (contextName == null) {
				query.append(_FINDER_COLUMN_API_CN_CONTEXTNAME_1);
			}
			else {
				if (contextName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_API_CN_CONTEXTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_API_CN_CONTEXTNAME_2);
				}
			}

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

				qPos.add(appPackageId);

				if (contextName != null) {
					qPos.add(contextName);
				}

				list = (List<AppPackagePlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAPI_CN_First(long appPackageId,
		String contextName, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAPI_CN_First(appPackageId,
				contextName, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(", contextName=");
		msg.append(contextName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAPI_CN_First(long appPackageId,
		String contextName, OrderByComparator orderByComparator)
		throws SystemException {
		List<AppPackagePlugin> list = findByAPI_CN(appPackageId, contextName,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAPI_CN_Last(long appPackageId,
		String contextName, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAPI_CN_Last(appPackageId,
				contextName, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(", contextName=");
		msg.append(contextName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAPI_CN_Last(long appPackageId,
		String contextName, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAPI_CN(appPackageId, contextName);

		List<AppPackagePlugin> list = findByAPI_CN(appPackageId, contextName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63; and contextName = &#63;.
	 *
	 * @param appPackagePluginId the primary key of the current app package plugin
	 * @param appPackageId the app package ID
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin[] findByAPI_CN_PrevAndNext(
		long appPackagePluginId, long appPackageId, String contextName,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = findByPrimaryKey(appPackagePluginId);

		Session session = null;

		try {
			session = openSession();

			AppPackagePlugin[] array = new AppPackagePluginImpl[3];

			array[0] = getByAPI_CN_PrevAndNext(session, appPackagePlugin,
					appPackageId, contextName, orderByComparator, true);

			array[1] = appPackagePlugin;

			array[2] = getByAPI_CN_PrevAndNext(session, appPackagePlugin,
					appPackageId, contextName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppPackagePlugin getByAPI_CN_PrevAndNext(Session session,
		AppPackagePlugin appPackagePlugin, long appPackageId,
		String contextName, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

		query.append(_FINDER_COLUMN_API_CN_APPPACKAGEID_2);

		if (contextName == null) {
			query.append(_FINDER_COLUMN_API_CN_CONTEXTNAME_1);
		}
		else {
			if (contextName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_API_CN_CONTEXTNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_API_CN_CONTEXTNAME_2);
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appPackageId);

		if (contextName != null) {
			qPos.add(contextName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPackagePlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPackagePlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app package plugins where appPackageId = &#63; and fileName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param fileName the file name
	 * @return the matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_FN(long appPackageId,
		String fileName) throws SystemException {
		return findByAPI_FN(appPackageId, fileName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app package plugins where appPackageId = &#63; and fileName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param fileName the file name
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @return the range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_FN(long appPackageId,
		String fileName, int start, int end) throws SystemException {
		return findByAPI_FN(appPackageId, fileName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app package plugins where appPackageId = &#63; and fileName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param fileName the file name
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_FN(long appPackageId,
		String fileName, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_FN;
			finderArgs = new Object[] { appPackageId, fileName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_API_FN;
			finderArgs = new Object[] {
					appPackageId, fileName,
					
					start, end, orderByComparator
				};
		}

		List<AppPackagePlugin> list = (List<AppPackagePlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPackagePlugin appPackagePlugin : list) {
				if ((appPackageId != appPackagePlugin.getAppPackageId()) ||
						!Validator.equals(fileName,
							appPackagePlugin.getFileName())) {
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

			query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_API_FN_APPPACKAGEID_2);

			if (fileName == null) {
				query.append(_FINDER_COLUMN_API_FN_FILENAME_1);
			}
			else {
				if (fileName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_API_FN_FILENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_API_FN_FILENAME_2);
				}
			}

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

				qPos.add(appPackageId);

				if (fileName != null) {
					qPos.add(fileName);
				}

				list = (List<AppPackagePlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param fileName the file name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAPI_FN_First(long appPackageId,
		String fileName, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAPI_FN_First(appPackageId,
				fileName, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(", fileName=");
		msg.append(fileName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param fileName the file name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAPI_FN_First(long appPackageId,
		String fileName, OrderByComparator orderByComparator)
		throws SystemException {
		List<AppPackagePlugin> list = findByAPI_FN(appPackageId, fileName, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param fileName the file name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAPI_FN_Last(long appPackageId,
		String fileName, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAPI_FN_Last(appPackageId,
				fileName, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(", fileName=");
		msg.append(fileName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param fileName the file name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAPI_FN_Last(long appPackageId,
		String fileName, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAPI_FN(appPackageId, fileName);

		List<AppPackagePlugin> list = findByAPI_FN(appPackageId, fileName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63; and fileName = &#63;.
	 *
	 * @param appPackagePluginId the primary key of the current app package plugin
	 * @param appPackageId the app package ID
	 * @param fileName the file name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin[] findByAPI_FN_PrevAndNext(
		long appPackagePluginId, long appPackageId, String fileName,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = findByPrimaryKey(appPackagePluginId);

		Session session = null;

		try {
			session = openSession();

			AppPackagePlugin[] array = new AppPackagePluginImpl[3];

			array[0] = getByAPI_FN_PrevAndNext(session, appPackagePlugin,
					appPackageId, fileName, orderByComparator, true);

			array[1] = appPackagePlugin;

			array[2] = getByAPI_FN_PrevAndNext(session, appPackagePlugin,
					appPackageId, fileName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppPackagePlugin getByAPI_FN_PrevAndNext(Session session,
		AppPackagePlugin appPackagePlugin, long appPackageId, String fileName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

		query.append(_FINDER_COLUMN_API_FN_APPPACKAGEID_2);

		if (fileName == null) {
			query.append(_FINDER_COLUMN_API_FN_FILENAME_1);
		}
		else {
			if (fileName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_API_FN_FILENAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_API_FN_FILENAME_2);
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appPackageId);

		if (fileName != null) {
			qPos.add(fileName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPackagePlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPackagePlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app package plugins where appPackageId = &#63; and paclEnabled = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param paclEnabled the pacl enabled
	 * @return the matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_PE(long appPackageId,
		boolean paclEnabled) throws SystemException {
		return findByAPI_PE(appPackageId, paclEnabled, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app package plugins where appPackageId = &#63; and paclEnabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param paclEnabled the pacl enabled
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @return the range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_PE(long appPackageId,
		boolean paclEnabled, int start, int end) throws SystemException {
		return findByAPI_PE(appPackageId, paclEnabled, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app package plugins where appPackageId = &#63; and paclEnabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param paclEnabled the pacl enabled
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_PE(long appPackageId,
		boolean paclEnabled, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_PE;
			finderArgs = new Object[] { appPackageId, paclEnabled };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_API_PE;
			finderArgs = new Object[] {
					appPackageId, paclEnabled,
					
					start, end, orderByComparator
				};
		}

		List<AppPackagePlugin> list = (List<AppPackagePlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPackagePlugin appPackagePlugin : list) {
				if ((appPackageId != appPackagePlugin.getAppPackageId()) ||
						(paclEnabled != appPackagePlugin.getPaclEnabled())) {
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

			query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_API_PE_APPPACKAGEID_2);

			query.append(_FINDER_COLUMN_API_PE_PACLENABLED_2);

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

				qPos.add(appPackageId);

				qPos.add(paclEnabled);

				list = (List<AppPackagePlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param paclEnabled the pacl enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAPI_PE_First(long appPackageId,
		boolean paclEnabled, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAPI_PE_First(appPackageId,
				paclEnabled, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(", paclEnabled=");
		msg.append(paclEnabled);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param paclEnabled the pacl enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAPI_PE_First(long appPackageId,
		boolean paclEnabled, OrderByComparator orderByComparator)
		throws SystemException {
		List<AppPackagePlugin> list = findByAPI_PE(appPackageId, paclEnabled,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param paclEnabled the pacl enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAPI_PE_Last(long appPackageId,
		boolean paclEnabled, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAPI_PE_Last(appPackageId,
				paclEnabled, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(", paclEnabled=");
		msg.append(paclEnabled);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param paclEnabled the pacl enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAPI_PE_Last(long appPackageId,
		boolean paclEnabled, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAPI_PE(appPackageId, paclEnabled);

		List<AppPackagePlugin> list = findByAPI_PE(appPackageId, paclEnabled,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63; and paclEnabled = &#63;.
	 *
	 * @param appPackagePluginId the primary key of the current app package plugin
	 * @param appPackageId the app package ID
	 * @param paclEnabled the pacl enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin[] findByAPI_PE_PrevAndNext(
		long appPackagePluginId, long appPackageId, boolean paclEnabled,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = findByPrimaryKey(appPackagePluginId);

		Session session = null;

		try {
			session = openSession();

			AppPackagePlugin[] array = new AppPackagePluginImpl[3];

			array[0] = getByAPI_PE_PrevAndNext(session, appPackagePlugin,
					appPackageId, paclEnabled, orderByComparator, true);

			array[1] = appPackagePlugin;

			array[2] = getByAPI_PE_PrevAndNext(session, appPackagePlugin,
					appPackageId, paclEnabled, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppPackagePlugin getByAPI_PE_PrevAndNext(Session session,
		AppPackagePlugin appPackagePlugin, long appPackageId,
		boolean paclEnabled, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

		query.append(_FINDER_COLUMN_API_PE_APPPACKAGEID_2);

		query.append(_FINDER_COLUMN_API_PE_PACLENABLED_2);

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

		qPos.add(appPackageId);

		qPos.add(paclEnabled);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPackagePlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPackagePlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app package plugins where appPackageId = &#63; and portalRestartRequired = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param portalRestartRequired the portal restart required
	 * @return the matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_PRR(long appPackageId,
		boolean portalRestartRequired) throws SystemException {
		return findByAPI_PRR(appPackageId, portalRestartRequired,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app package plugins where appPackageId = &#63; and portalRestartRequired = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param portalRestartRequired the portal restart required
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @return the range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_PRR(long appPackageId,
		boolean portalRestartRequired, int start, int end)
		throws SystemException {
		return findByAPI_PRR(appPackageId, portalRestartRequired, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the app package plugins where appPackageId = &#63; and portalRestartRequired = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param portalRestartRequired the portal restart required
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_PRR(long appPackageId,
		boolean portalRestartRequired, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_PRR;
			finderArgs = new Object[] { appPackageId, portalRestartRequired };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_API_PRR;
			finderArgs = new Object[] {
					appPackageId, portalRestartRequired,
					
					start, end, orderByComparator
				};
		}

		List<AppPackagePlugin> list = (List<AppPackagePlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPackagePlugin appPackagePlugin : list) {
				if ((appPackageId != appPackagePlugin.getAppPackageId()) ||
						(portalRestartRequired != appPackagePlugin.getPortalRestartRequired())) {
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

			query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_API_PRR_APPPACKAGEID_2);

			query.append(_FINDER_COLUMN_API_PRR_PORTALRESTARTREQUIRED_2);

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

				qPos.add(appPackageId);

				qPos.add(portalRestartRequired);

				list = (List<AppPackagePlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param portalRestartRequired the portal restart required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAPI_PRR_First(long appPackageId,
		boolean portalRestartRequired, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAPI_PRR_First(appPackageId,
				portalRestartRequired, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(", portalRestartRequired=");
		msg.append(portalRestartRequired);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param portalRestartRequired the portal restart required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAPI_PRR_First(long appPackageId,
		boolean portalRestartRequired, OrderByComparator orderByComparator)
		throws SystemException {
		List<AppPackagePlugin> list = findByAPI_PRR(appPackageId,
				portalRestartRequired, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param portalRestartRequired the portal restart required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAPI_PRR_Last(long appPackageId,
		boolean portalRestartRequired, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAPI_PRR_Last(appPackageId,
				portalRestartRequired, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(", portalRestartRequired=");
		msg.append(portalRestartRequired);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param portalRestartRequired the portal restart required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAPI_PRR_Last(long appPackageId,
		boolean portalRestartRequired, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAPI_PRR(appPackageId, portalRestartRequired);

		List<AppPackagePlugin> list = findByAPI_PRR(appPackageId,
				portalRestartRequired, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63; and portalRestartRequired = &#63;.
	 *
	 * @param appPackagePluginId the primary key of the current app package plugin
	 * @param appPackageId the app package ID
	 * @param portalRestartRequired the portal restart required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin[] findByAPI_PRR_PrevAndNext(
		long appPackagePluginId, long appPackageId,
		boolean portalRestartRequired, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = findByPrimaryKey(appPackagePluginId);

		Session session = null;

		try {
			session = openSession();

			AppPackagePlugin[] array = new AppPackagePluginImpl[3];

			array[0] = getByAPI_PRR_PrevAndNext(session, appPackagePlugin,
					appPackageId, portalRestartRequired, orderByComparator, true);

			array[1] = appPackagePlugin;

			array[2] = getByAPI_PRR_PrevAndNext(session, appPackagePlugin,
					appPackageId, portalRestartRequired, orderByComparator,
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

	protected AppPackagePlugin getByAPI_PRR_PrevAndNext(Session session,
		AppPackagePlugin appPackagePlugin, long appPackageId,
		boolean portalRestartRequired, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

		query.append(_FINDER_COLUMN_API_PRR_APPPACKAGEID_2);

		query.append(_FINDER_COLUMN_API_PRR_PORTALRESTARTREQUIRED_2);

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

		qPos.add(appPackageId);

		qPos.add(portalRestartRequired);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPackagePlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPackagePlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app package plugins where appEntryId &ne; &#63; and contextName = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param contextName the context name
	 * @return the matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByNotAEI_CN(long appEntryId,
		String contextName) throws SystemException {
		return findByNotAEI_CN(appEntryId, contextName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app package plugins where appEntryId &ne; &#63; and contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId the app entry ID
	 * @param contextName the context name
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @return the range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByNotAEI_CN(long appEntryId,
		String contextName, int start, int end) throws SystemException {
		return findByNotAEI_CN(appEntryId, contextName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app package plugins where appEntryId &ne; &#63; and contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId the app entry ID
	 * @param contextName the context name
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByNotAEI_CN(long appEntryId,
		String contextName, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NOTAEI_CN;
		finderArgs = new Object[] {
				appEntryId, contextName,
				
				start, end, orderByComparator
			};

		List<AppPackagePlugin> list = (List<AppPackagePlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPackagePlugin appPackagePlugin : list) {
				if ((appEntryId != appPackagePlugin.getAppEntryId()) ||
						!Validator.equals(contextName,
							appPackagePlugin.getContextName())) {
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

			query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_NOTAEI_CN_APPENTRYID_2);

			if (contextName == null) {
				query.append(_FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_1);
			}
			else {
				if (contextName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_2);
				}
			}

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

				qPos.add(appEntryId);

				if (contextName != null) {
					qPos.add(contextName);
				}

				list = (List<AppPackagePlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByNotAEI_CN_First(long appEntryId,
		String contextName, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByNotAEI_CN_First(appEntryId,
				contextName, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId=");
		msg.append(appEntryId);

		msg.append(", contextName=");
		msg.append(contextName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the first app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByNotAEI_CN_First(long appEntryId,
		String contextName, OrderByComparator orderByComparator)
		throws SystemException {
		List<AppPackagePlugin> list = findByNotAEI_CN(appEntryId, contextName,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByNotAEI_CN_Last(long appEntryId,
		String contextName, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByNotAEI_CN_Last(appEntryId,
				contextName, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId=");
		msg.append(appEntryId);

		msg.append(", contextName=");
		msg.append(contextName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the last app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByNotAEI_CN_Last(long appEntryId,
		String contextName, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByNotAEI_CN(appEntryId, contextName);

		List<AppPackagePlugin> list = findByNotAEI_CN(appEntryId, contextName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app package plugins before and after the current app package plugin in the ordered set where appEntryId &ne; &#63; and contextName = &#63;.
	 *
	 * @param appPackagePluginId the primary key of the current app package plugin
	 * @param appEntryId the app entry ID
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin[] findByNotAEI_CN_PrevAndNext(
		long appPackagePluginId, long appEntryId, String contextName,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = findByPrimaryKey(appPackagePluginId);

		Session session = null;

		try {
			session = openSession();

			AppPackagePlugin[] array = new AppPackagePluginImpl[3];

			array[0] = getByNotAEI_CN_PrevAndNext(session, appPackagePlugin,
					appEntryId, contextName, orderByComparator, true);

			array[1] = appPackagePlugin;

			array[2] = getByNotAEI_CN_PrevAndNext(session, appPackagePlugin,
					appEntryId, contextName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppPackagePlugin getByNotAEI_CN_PrevAndNext(Session session,
		AppPackagePlugin appPackagePlugin, long appEntryId, String contextName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

		query.append(_FINDER_COLUMN_NOTAEI_CN_APPENTRYID_2);

		if (contextName == null) {
			query.append(_FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_1);
		}
		else {
			if (contextName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_2);
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appEntryId);

		if (contextName != null) {
			qPos.add(contextName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPackagePlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPackagePlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @return the matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_BSN_BV(long appPackageId,
		String bundleSymbolicName, String bundleVersion)
		throws SystemException {
		return findByAPI_BSN_BV(appPackageId, bundleSymbolicName,
			bundleVersion, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @return the range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_BSN_BV(long appPackageId,
		String bundleSymbolicName, String bundleVersion, int start, int end)
		throws SystemException {
		return findByAPI_BSN_BV(appPackageId, bundleSymbolicName,
			bundleVersion, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPackageId the app package ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findByAPI_BSN_BV(long appPackageId,
		String bundleSymbolicName, String bundleVersion, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_API_BSN_BV;
			finderArgs = new Object[] {
					appPackageId, bundleSymbolicName, bundleVersion
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_API_BSN_BV;
			finderArgs = new Object[] {
					appPackageId, bundleSymbolicName, bundleVersion,
					
					start, end, orderByComparator
				};
		}

		List<AppPackagePlugin> list = (List<AppPackagePlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPackagePlugin appPackagePlugin : list) {
				if ((appPackageId != appPackagePlugin.getAppPackageId()) ||
						!Validator.equals(bundleSymbolicName,
							appPackagePlugin.getBundleSymbolicName()) ||
						!Validator.equals(bundleVersion,
							appPackagePlugin.getBundleVersion())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_API_BSN_BV_APPPACKAGEID_2);

			if (bundleSymbolicName == null) {
				query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_1);
			}
			else {
				if (bundleSymbolicName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_2);
				}
			}

			if (bundleVersion == null) {
				query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_1);
			}
			else {
				if (bundleVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_2);
				}
			}

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

				qPos.add(appPackageId);

				if (bundleSymbolicName != null) {
					qPos.add(bundleSymbolicName);
				}

				if (bundleVersion != null) {
					qPos.add(bundleVersion);
				}

				list = (List<AppPackagePlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAPI_BSN_BV_First(long appPackageId,
		String bundleSymbolicName, String bundleVersion,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAPI_BSN_BV_First(appPackageId,
				bundleSymbolicName, bundleVersion, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(", bundleSymbolicName=");
		msg.append(bundleSymbolicName);

		msg.append(", bundleVersion=");
		msg.append(bundleVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the first app package plugin in the ordered set where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAPI_BSN_BV_First(long appPackageId,
		String bundleSymbolicName, String bundleVersion,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppPackagePlugin> list = findByAPI_BSN_BV(appPackageId,
				bundleSymbolicName, bundleVersion, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin findByAPI_BSN_BV_Last(long appPackageId,
		String bundleSymbolicName, String bundleVersion,
		OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = fetchByAPI_BSN_BV_Last(appPackageId,
				bundleSymbolicName, bundleVersion, orderByComparator);

		if (appPackagePlugin != null) {
			return appPackagePlugin;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPackageId=");
		msg.append(appPackageId);

		msg.append(", bundleSymbolicName=");
		msg.append(bundleSymbolicName);

		msg.append(", bundleVersion=");
		msg.append(bundleVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPackagePluginException(msg.toString());
	}

	/**
	 * Returns the last app package plugin in the ordered set where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app package plugin, or <code>null</code> if a matching app package plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin fetchByAPI_BSN_BV_Last(long appPackageId,
		String bundleSymbolicName, String bundleVersion,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAPI_BSN_BV(appPackageId, bundleSymbolicName,
				bundleVersion);

		List<AppPackagePlugin> list = findByAPI_BSN_BV(appPackageId,
				bundleSymbolicName, bundleVersion, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app package plugins before and after the current app package plugin in the ordered set where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	 *
	 * @param appPackagePluginId the primary key of the current app package plugin
	 * @param appPackageId the app package ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app package plugin
	 * @throws com.liferay.osb.NoSuchAppPackagePluginException if a app package plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin[] findByAPI_BSN_BV_PrevAndNext(
		long appPackagePluginId, long appPackageId, String bundleSymbolicName,
		String bundleVersion, OrderByComparator orderByComparator)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = findByPrimaryKey(appPackagePluginId);

		Session session = null;

		try {
			session = openSession();

			AppPackagePlugin[] array = new AppPackagePluginImpl[3];

			array[0] = getByAPI_BSN_BV_PrevAndNext(session, appPackagePlugin,
					appPackageId, bundleSymbolicName, bundleVersion,
					orderByComparator, true);

			array[1] = appPackagePlugin;

			array[2] = getByAPI_BSN_BV_PrevAndNext(session, appPackagePlugin,
					appPackageId, bundleSymbolicName, bundleVersion,
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

	protected AppPackagePlugin getByAPI_BSN_BV_PrevAndNext(Session session,
		AppPackagePlugin appPackagePlugin, long appPackageId,
		String bundleSymbolicName, String bundleVersion,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPACKAGEPLUGIN_WHERE);

		query.append(_FINDER_COLUMN_API_BSN_BV_APPPACKAGEID_2);

		if (bundleSymbolicName == null) {
			query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_1);
		}
		else {
			if (bundleSymbolicName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_2);
			}
		}

		if (bundleVersion == null) {
			query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_1);
		}
		else {
			if (bundleVersion.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_3);
			}
			else {
				query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_2);
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appPackageId);

		if (bundleSymbolicName != null) {
			qPos.add(bundleSymbolicName);
		}

		if (bundleVersion != null) {
			qPos.add(bundleVersion);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPackagePlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPackagePlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app package plugins.
	 *
	 * @return the app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app package plugins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @return the range of app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the app package plugins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app package plugins
	 * @param end the upper bound of the range of app package plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPackagePlugin> findAll(int start, int end,
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

		List<AppPackagePlugin> list = (List<AppPackagePlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_APPPACKAGEPLUGIN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APPPACKAGEPLUGIN;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AppPackagePlugin>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AppPackagePlugin>)QueryUtil.list(q,
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
	 * Removes all the app package plugins where appPackageId = &#63; from the database.
	 *
	 * @param appPackageId the app package ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAppPackageId(long appPackageId)
		throws SystemException {
		for (AppPackagePlugin appPackagePlugin : findByAppPackageId(
				appPackageId)) {
			remove(appPackagePlugin);
		}
	}

	/**
	 * Removes the app package plugin where assetAttachmentId = &#63; from the database.
	 *
	 * @param assetAttachmentId the asset attachment ID
	 * @return the app package plugin that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AppPackagePlugin removeByAssetAttachmentId(long assetAttachmentId)
		throws NoSuchAppPackagePluginException, SystemException {
		AppPackagePlugin appPackagePlugin = findByAssetAttachmentId(assetAttachmentId);

		return remove(appPackagePlugin);
	}

	/**
	 * Removes all the app package plugins where contextName = &#63; from the database.
	 *
	 * @param contextName the context name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByContextName(String contextName)
		throws SystemException {
		for (AppPackagePlugin appPackagePlugin : findByContextName(contextName)) {
			remove(appPackagePlugin);
		}
	}

	/**
	 * Removes all the app package plugins where appPackageId = &#63; and contextName = &#63; from the database.
	 *
	 * @param appPackageId the app package ID
	 * @param contextName the context name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAPI_CN(long appPackageId, String contextName)
		throws SystemException {
		for (AppPackagePlugin appPackagePlugin : findByAPI_CN(appPackageId,
				contextName)) {
			remove(appPackagePlugin);
		}
	}

	/**
	 * Removes all the app package plugins where appPackageId = &#63; and fileName = &#63; from the database.
	 *
	 * @param appPackageId the app package ID
	 * @param fileName the file name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAPI_FN(long appPackageId, String fileName)
		throws SystemException {
		for (AppPackagePlugin appPackagePlugin : findByAPI_FN(appPackageId,
				fileName)) {
			remove(appPackagePlugin);
		}
	}

	/**
	 * Removes all the app package plugins where appPackageId = &#63; and paclEnabled = &#63; from the database.
	 *
	 * @param appPackageId the app package ID
	 * @param paclEnabled the pacl enabled
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAPI_PE(long appPackageId, boolean paclEnabled)
		throws SystemException {
		for (AppPackagePlugin appPackagePlugin : findByAPI_PE(appPackageId,
				paclEnabled)) {
			remove(appPackagePlugin);
		}
	}

	/**
	 * Removes all the app package plugins where appPackageId = &#63; and portalRestartRequired = &#63; from the database.
	 *
	 * @param appPackageId the app package ID
	 * @param portalRestartRequired the portal restart required
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAPI_PRR(long appPackageId, boolean portalRestartRequired)
		throws SystemException {
		for (AppPackagePlugin appPackagePlugin : findByAPI_PRR(appPackageId,
				portalRestartRequired)) {
			remove(appPackagePlugin);
		}
	}

	/**
	 * Removes all the app package plugins where appEntryId &ne; &#63; and contextName = &#63; from the database.
	 *
	 * @param appEntryId the app entry ID
	 * @param contextName the context name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByNotAEI_CN(long appEntryId, String contextName)
		throws SystemException {
		for (AppPackagePlugin appPackagePlugin : findByNotAEI_CN(appEntryId,
				contextName)) {
			remove(appPackagePlugin);
		}
	}

	/**
	 * Removes all the app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63; from the database.
	 *
	 * @param appPackageId the app package ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAPI_BSN_BV(long appPackageId,
		String bundleSymbolicName, String bundleVersion)
		throws SystemException {
		for (AppPackagePlugin appPackagePlugin : findByAPI_BSN_BV(
				appPackageId, bundleSymbolicName, bundleVersion)) {
			remove(appPackagePlugin);
		}
	}

	/**
	 * Removes all the app package plugins from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AppPackagePlugin appPackagePlugin : findAll()) {
			remove(appPackagePlugin);
		}
	}

	/**
	 * Returns the number of app package plugins where appPackageId = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @return the number of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAppPackageId(long appPackageId) throws SystemException {
		Object[] finderArgs = new Object[] { appPackageId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPPACKAGEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_APPPACKAGEID_APPPACKAGEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appPackageId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_APPPACKAGEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app package plugins where assetAttachmentId = &#63;.
	 *
	 * @param assetAttachmentId the asset attachment ID
	 * @return the number of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAssetAttachmentId(long assetAttachmentId)
		throws SystemException {
		Object[] finderArgs = new Object[] { assetAttachmentId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSETATTACHMENTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_ASSETATTACHMENTID_ASSETATTACHMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetAttachmentId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ASSETATTACHMENTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app package plugins where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @return the number of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByContextName(String contextName) throws SystemException {
		Object[] finderArgs = new Object[] { contextName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTEXTNAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPPACKAGEPLUGIN_WHERE);

			if (contextName == null) {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1);
			}
			else {
				if (contextName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (contextName != null) {
					qPos.add(contextName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTEXTNAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app package plugins where appPackageId = &#63; and contextName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param contextName the context name
	 * @return the number of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAPI_CN(long appPackageId, String contextName)
		throws SystemException {
		Object[] finderArgs = new Object[] { appPackageId, contextName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_API_CN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_API_CN_APPPACKAGEID_2);

			if (contextName == null) {
				query.append(_FINDER_COLUMN_API_CN_CONTEXTNAME_1);
			}
			else {
				if (contextName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_API_CN_CONTEXTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_API_CN_CONTEXTNAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appPackageId);

				if (contextName != null) {
					qPos.add(contextName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_API_CN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app package plugins where appPackageId = &#63; and fileName = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param fileName the file name
	 * @return the number of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAPI_FN(long appPackageId, String fileName)
		throws SystemException {
		Object[] finderArgs = new Object[] { appPackageId, fileName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_API_FN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_API_FN_APPPACKAGEID_2);

			if (fileName == null) {
				query.append(_FINDER_COLUMN_API_FN_FILENAME_1);
			}
			else {
				if (fileName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_API_FN_FILENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_API_FN_FILENAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appPackageId);

				if (fileName != null) {
					qPos.add(fileName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_API_FN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app package plugins where appPackageId = &#63; and paclEnabled = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param paclEnabled the pacl enabled
	 * @return the number of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAPI_PE(long appPackageId, boolean paclEnabled)
		throws SystemException {
		Object[] finderArgs = new Object[] { appPackageId, paclEnabled };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_API_PE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_API_PE_APPPACKAGEID_2);

			query.append(_FINDER_COLUMN_API_PE_PACLENABLED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appPackageId);

				qPos.add(paclEnabled);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_API_PE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app package plugins where appPackageId = &#63; and portalRestartRequired = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param portalRestartRequired the portal restart required
	 * @return the number of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAPI_PRR(long appPackageId, boolean portalRestartRequired)
		throws SystemException {
		Object[] finderArgs = new Object[] { appPackageId, portalRestartRequired };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_API_PRR,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_API_PRR_APPPACKAGEID_2);

			query.append(_FINDER_COLUMN_API_PRR_PORTALRESTARTREQUIRED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appPackageId);

				qPos.add(portalRestartRequired);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_API_PRR,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app package plugins where appEntryId &ne; &#63; and contextName = &#63;.
	 *
	 * @param appEntryId the app entry ID
	 * @param contextName the context name
	 * @return the number of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByNotAEI_CN(long appEntryId, String contextName)
		throws SystemException {
		Object[] finderArgs = new Object[] { appEntryId, contextName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOTAEI_CN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_NOTAEI_CN_APPENTRYID_2);

			if (contextName == null) {
				query.append(_FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_1);
			}
			else {
				if (contextName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId);

				if (contextName != null) {
					qPos.add(contextName);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOTAEI_CN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app package plugins where appPackageId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	 *
	 * @param appPackageId the app package ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @return the number of matching app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAPI_BSN_BV(long appPackageId, String bundleSymbolicName,
		String bundleVersion) throws SystemException {
		Object[] finderArgs = new Object[] {
				appPackageId, bundleSymbolicName, bundleVersion
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_API_BSN_BV,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_APPPACKAGEPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_API_BSN_BV_APPPACKAGEID_2);

			if (bundleSymbolicName == null) {
				query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_1);
			}
			else {
				if (bundleSymbolicName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_2);
				}
			}

			if (bundleVersion == null) {
				query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_1);
			}
			else {
				if (bundleVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appPackageId);

				if (bundleSymbolicName != null) {
					qPos.add(bundleSymbolicName);
				}

				if (bundleVersion != null) {
					qPos.add(bundleVersion);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_API_BSN_BV,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app package plugins.
	 *
	 * @return the number of app package plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APPPACKAGEPLUGIN);

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
	 * Initializes the app package plugin persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AppPackagePlugin")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AppPackagePlugin>> listenersList = new ArrayList<ModelListener<AppPackagePlugin>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AppPackagePlugin>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AppPackagePluginImpl.class.getName());
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
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_APPPACKAGEPLUGIN = "SELECT appPackagePlugin FROM AppPackagePlugin appPackagePlugin";
	private static final String _SQL_SELECT_APPPACKAGEPLUGIN_WHERE = "SELECT appPackagePlugin FROM AppPackagePlugin appPackagePlugin WHERE ";
	private static final String _SQL_COUNT_APPPACKAGEPLUGIN = "SELECT COUNT(appPackagePlugin) FROM AppPackagePlugin appPackagePlugin";
	private static final String _SQL_COUNT_APPPACKAGEPLUGIN_WHERE = "SELECT COUNT(appPackagePlugin) FROM AppPackagePlugin appPackagePlugin WHERE ";
	private static final String _FINDER_COLUMN_APPPACKAGEID_APPPACKAGEID_2 = "appPackagePlugin.appPackageId = ?";
	private static final String _FINDER_COLUMN_ASSETATTACHMENTID_ASSETATTACHMENTID_2 =
		"appPackagePlugin.assetAttachmentId = ?";
	private static final String _FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1 = "appPackagePlugin.contextName IS NULL";
	private static final String _FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2 = "appPackagePlugin.contextName = ?";
	private static final String _FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3 = "(appPackagePlugin.contextName IS NULL OR appPackagePlugin.contextName = ?)";
	private static final String _FINDER_COLUMN_API_CN_APPPACKAGEID_2 = "appPackagePlugin.appPackageId = ? AND ";
	private static final String _FINDER_COLUMN_API_CN_CONTEXTNAME_1 = "appPackagePlugin.contextName IS NULL";
	private static final String _FINDER_COLUMN_API_CN_CONTEXTNAME_2 = "appPackagePlugin.contextName = ?";
	private static final String _FINDER_COLUMN_API_CN_CONTEXTNAME_3 = "(appPackagePlugin.contextName IS NULL OR appPackagePlugin.contextName = ?)";
	private static final String _FINDER_COLUMN_API_FN_APPPACKAGEID_2 = "appPackagePlugin.appPackageId = ? AND ";
	private static final String _FINDER_COLUMN_API_FN_FILENAME_1 = "appPackagePlugin.fileName IS NULL";
	private static final String _FINDER_COLUMN_API_FN_FILENAME_2 = "appPackagePlugin.fileName = ?";
	private static final String _FINDER_COLUMN_API_FN_FILENAME_3 = "(appPackagePlugin.fileName IS NULL OR appPackagePlugin.fileName = ?)";
	private static final String _FINDER_COLUMN_API_PE_APPPACKAGEID_2 = "appPackagePlugin.appPackageId = ? AND ";
	private static final String _FINDER_COLUMN_API_PE_PACLENABLED_2 = "appPackagePlugin.paclEnabled = ?";
	private static final String _FINDER_COLUMN_API_PRR_APPPACKAGEID_2 = "appPackagePlugin.appPackageId = ? AND ";
	private static final String _FINDER_COLUMN_API_PRR_PORTALRESTARTREQUIRED_2 = "appPackagePlugin.portalRestartRequired = ?";
	private static final String _FINDER_COLUMN_NOTAEI_CN_APPENTRYID_2 = "appPackagePlugin.appEntryId != ? AND ";
	private static final String _FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_1 = "appPackagePlugin.contextName IS NULL";
	private static final String _FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_2 = "appPackagePlugin.contextName = ?";
	private static final String _FINDER_COLUMN_NOTAEI_CN_CONTEXTNAME_3 = "(appPackagePlugin.contextName IS NULL OR appPackagePlugin.contextName = ?)";
	private static final String _FINDER_COLUMN_API_BSN_BV_APPPACKAGEID_2 = "appPackagePlugin.appPackageId = ? AND ";
	private static final String _FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_1 = "appPackagePlugin.bundleSymbolicName IS NULL AND ";
	private static final String _FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_2 = "appPackagePlugin.bundleSymbolicName = ? AND ";
	private static final String _FINDER_COLUMN_API_BSN_BV_BUNDLESYMBOLICNAME_3 = "(appPackagePlugin.bundleSymbolicName IS NULL OR appPackagePlugin.bundleSymbolicName = ?) AND ";
	private static final String _FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_1 = "appPackagePlugin.bundleVersion IS NULL";
	private static final String _FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_2 = "appPackagePlugin.bundleVersion = ?";
	private static final String _FINDER_COLUMN_API_BSN_BV_BUNDLEVERSION_3 = "(appPackagePlugin.bundleVersion IS NULL OR appPackagePlugin.bundleVersion = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "appPackagePlugin.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AppPackagePlugin exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AppPackagePlugin exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AppPackagePluginPersistenceImpl.class);
	private static AppPackagePlugin _nullAppPackagePlugin = new AppPackagePluginImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AppPackagePlugin> toCacheModel() {
				return _nullAppPackagePluginCacheModel;
			}
		};

	private static CacheModel<AppPackagePlugin> _nullAppPackagePluginCacheModel = new CacheModel<AppPackagePlugin>() {
			public AppPackagePlugin toEntityModel() {
				return _nullAppPackagePlugin;
			}
		};
}
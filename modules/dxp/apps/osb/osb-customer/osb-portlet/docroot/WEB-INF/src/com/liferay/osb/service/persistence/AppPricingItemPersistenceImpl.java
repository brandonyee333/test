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

import com.liferay.osb.NoSuchAppPricingItemException;
import com.liferay.osb.model.AppPricingItem;
import com.liferay.osb.model.impl.AppPricingItemImpl;
import com.liferay.osb.model.impl.AppPricingItemModelImpl;

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
 * The persistence implementation for the app pricing item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPricingItemPersistence
 * @see AppPricingItemUtil
 * @generated
 */
public class AppPricingItemPersistenceImpl extends BasePersistenceImpl<AppPricingItem>
	implements AppPricingItemPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AppPricingItemUtil} to access the app pricing item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AppPricingItemImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPPRICINGID =
		new FinderPath(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemModelImpl.FINDER_CACHE_ENABLED,
			AppPricingItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAppPricingId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPRICINGID =
		new FinderPath(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemModelImpl.FINDER_CACHE_ENABLED,
			AppPricingItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppPricingId",
			new String[] { Long.class.getName() },
			AppPricingItemModelImpl.APPPRICINGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPPRICINGID = new FinderPath(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppPricingId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETLICENSEID =
		new FinderPath(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemModelImpl.FINDER_CACHE_ENABLED,
			AppPricingItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAssetLicenseId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLICENSEID =
		new FinderPath(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemModelImpl.FINDER_CACHE_ENABLED,
			AppPricingItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetLicenseId",
			new String[] { Long.class.getName() },
			AppPricingItemModelImpl.ASSETLICENSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETLICENSEID = new FinderPath(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetLicenseId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_API_ALI = new FinderPath(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemModelImpl.FINDER_CACHE_ENABLED,
			AppPricingItemImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAPI_ALI",
			new String[] { Long.class.getName(), Long.class.getName() },
			AppPricingItemModelImpl.APPPRICINGID_COLUMN_BITMASK |
			AppPricingItemModelImpl.ASSETLICENSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_API_ALI = new FinderPath(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAPI_ALI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemModelImpl.FINDER_CACHE_ENABLED,
			AppPricingItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemModelImpl.FINDER_CACHE_ENABLED,
			AppPricingItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the app pricing item in the entity cache if it is enabled.
	 *
	 * @param appPricingItem the app pricing item
	 */
	public void cacheResult(AppPricingItem appPricingItem) {
		EntityCacheUtil.putResult(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemImpl.class, appPricingItem.getPrimaryKey(),
			appPricingItem);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_API_ALI,
			new Object[] {
				Long.valueOf(appPricingItem.getAppPricingId()),
				Long.valueOf(appPricingItem.getAssetLicenseId())
			}, appPricingItem);

		appPricingItem.resetOriginalValues();
	}

	/**
	 * Caches the app pricing items in the entity cache if it is enabled.
	 *
	 * @param appPricingItems the app pricing items
	 */
	public void cacheResult(List<AppPricingItem> appPricingItems) {
		for (AppPricingItem appPricingItem : appPricingItems) {
			if (EntityCacheUtil.getResult(
						AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
						AppPricingItemImpl.class, appPricingItem.getPrimaryKey()) == null) {
				cacheResult(appPricingItem);
			}
			else {
				appPricingItem.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all app pricing items.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AppPricingItemImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AppPricingItemImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the app pricing item.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AppPricingItem appPricingItem) {
		EntityCacheUtil.removeResult(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemImpl.class, appPricingItem.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(appPricingItem);
	}

	@Override
	public void clearCache(List<AppPricingItem> appPricingItems) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AppPricingItem appPricingItem : appPricingItems) {
			EntityCacheUtil.removeResult(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
				AppPricingItemImpl.class, appPricingItem.getPrimaryKey());

			clearUniqueFindersCache(appPricingItem);
		}
	}

	protected void cacheUniqueFindersCache(AppPricingItem appPricingItem) {
		if (appPricingItem.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(appPricingItem.getAppPricingId()),
					Long.valueOf(appPricingItem.getAssetLicenseId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_API_ALI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_API_ALI, args,
				appPricingItem);
		}
		else {
			AppPricingItemModelImpl appPricingItemModelImpl = (AppPricingItemModelImpl)appPricingItem;

			if ((appPricingItemModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_API_ALI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPricingItem.getAppPricingId()),
						Long.valueOf(appPricingItem.getAssetLicenseId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_API_ALI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_API_ALI, args,
					appPricingItem);
			}
		}
	}

	protected void clearUniqueFindersCache(AppPricingItem appPricingItem) {
		AppPricingItemModelImpl appPricingItemModelImpl = (AppPricingItemModelImpl)appPricingItem;

		Object[] args = new Object[] {
				Long.valueOf(appPricingItem.getAppPricingId()),
				Long.valueOf(appPricingItem.getAssetLicenseId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_ALI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_API_ALI, args);

		if ((appPricingItemModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_API_ALI.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(appPricingItemModelImpl.getOriginalAppPricingId()),
					Long.valueOf(appPricingItemModelImpl.getOriginalAssetLicenseId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_API_ALI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_API_ALI, args);
		}
	}

	/**
	 * Creates a new app pricing item with the primary key. Does not add the app pricing item to the database.
	 *
	 * @param appPricingItemId the primary key for the new app pricing item
	 * @return the new app pricing item
	 */
	public AppPricingItem create(long appPricingItemId) {
		AppPricingItem appPricingItem = new AppPricingItemImpl();

		appPricingItem.setNew(true);
		appPricingItem.setPrimaryKey(appPricingItemId);

		return appPricingItem;
	}

	/**
	 * Removes the app pricing item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param appPricingItemId the primary key of the app pricing item
	 * @return the app pricing item that was removed
	 * @throws com.liferay.osb.NoSuchAppPricingItemException if a app pricing item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem remove(long appPricingItemId)
		throws NoSuchAppPricingItemException, SystemException {
		return remove(Long.valueOf(appPricingItemId));
	}

	/**
	 * Removes the app pricing item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the app pricing item
	 * @return the app pricing item that was removed
	 * @throws com.liferay.osb.NoSuchAppPricingItemException if a app pricing item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPricingItem remove(Serializable primaryKey)
		throws NoSuchAppPricingItemException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AppPricingItem appPricingItem = (AppPricingItem)session.get(AppPricingItemImpl.class,
					primaryKey);

			if (appPricingItem == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAppPricingItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(appPricingItem);
		}
		catch (NoSuchAppPricingItemException nsee) {
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
	protected AppPricingItem removeImpl(AppPricingItem appPricingItem)
		throws SystemException {
		appPricingItem = toUnwrappedModel(appPricingItem);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, appPricingItem);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(appPricingItem);

		return appPricingItem;
	}

	@Override
	public AppPricingItem updateImpl(
		com.liferay.osb.model.AppPricingItem appPricingItem, boolean merge)
		throws SystemException {
		appPricingItem = toUnwrappedModel(appPricingItem);

		boolean isNew = appPricingItem.isNew();

		AppPricingItemModelImpl appPricingItemModelImpl = (AppPricingItemModelImpl)appPricingItem;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, appPricingItem, merge);

			appPricingItem.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AppPricingItemModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((appPricingItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPRICINGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPricingItemModelImpl.getOriginalAppPricingId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPPRICINGID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPRICINGID,
					args);

				args = new Object[] {
						Long.valueOf(appPricingItemModelImpl.getAppPricingId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPPRICINGID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPRICINGID,
					args);
			}

			if ((appPricingItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLICENSEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appPricingItemModelImpl.getOriginalAssetLicenseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETLICENSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLICENSEID,
					args);

				args = new Object[] {
						Long.valueOf(appPricingItemModelImpl.getAssetLicenseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETLICENSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLICENSEID,
					args);
			}
		}

		EntityCacheUtil.putResult(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
			AppPricingItemImpl.class, appPricingItem.getPrimaryKey(),
			appPricingItem);

		clearUniqueFindersCache(appPricingItem);
		cacheUniqueFindersCache(appPricingItem);

		return appPricingItem;
	}

	protected AppPricingItem toUnwrappedModel(AppPricingItem appPricingItem) {
		if (appPricingItem instanceof AppPricingItemImpl) {
			return appPricingItem;
		}

		AppPricingItemImpl appPricingItemImpl = new AppPricingItemImpl();

		appPricingItemImpl.setNew(appPricingItem.isNew());
		appPricingItemImpl.setPrimaryKey(appPricingItem.getPrimaryKey());

		appPricingItemImpl.setAppPricingItemId(appPricingItem.getAppPricingItemId());
		appPricingItemImpl.setAppPricingId(appPricingItem.getAppPricingId());
		appPricingItemImpl.setAssetLicenseId(appPricingItem.getAssetLicenseId());
		appPricingItemImpl.setCurrencyEntryId(appPricingItem.getCurrencyEntryId());
		appPricingItemImpl.setPrice(appPricingItem.getPrice());

		return appPricingItemImpl;
	}

	/**
	 * Returns the app pricing item with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the app pricing item
	 * @return the app pricing item
	 * @throws com.liferay.portal.NoSuchModelException if a app pricing item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPricingItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app pricing item with the primary key or throws a {@link com.liferay.osb.NoSuchAppPricingItemException} if it could not be found.
	 *
	 * @param appPricingItemId the primary key of the app pricing item
	 * @return the app pricing item
	 * @throws com.liferay.osb.NoSuchAppPricingItemException if a app pricing item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem findByPrimaryKey(long appPricingItemId)
		throws NoSuchAppPricingItemException, SystemException {
		AppPricingItem appPricingItem = fetchByPrimaryKey(appPricingItemId);

		if (appPricingItem == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + appPricingItemId);
			}

			throw new NoSuchAppPricingItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				appPricingItemId);
		}

		return appPricingItem;
	}

	/**
	 * Returns the app pricing item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the app pricing item
	 * @return the app pricing item, or <code>null</code> if a app pricing item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppPricingItem fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app pricing item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param appPricingItemId the primary key of the app pricing item
	 * @return the app pricing item, or <code>null</code> if a app pricing item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem fetchByPrimaryKey(long appPricingItemId)
		throws SystemException {
		AppPricingItem appPricingItem = (AppPricingItem)EntityCacheUtil.getResult(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
				AppPricingItemImpl.class, appPricingItemId);

		if (appPricingItem == _nullAppPricingItem) {
			return null;
		}

		if (appPricingItem == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				appPricingItem = (AppPricingItem)session.get(AppPricingItemImpl.class,
						Long.valueOf(appPricingItemId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (appPricingItem != null) {
					cacheResult(appPricingItem);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AppPricingItemModelImpl.ENTITY_CACHE_ENABLED,
						AppPricingItemImpl.class, appPricingItemId,
						_nullAppPricingItem);
				}

				closeSession(session);
			}
		}

		return appPricingItem;
	}

	/**
	 * Returns all the app pricing items where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @return the matching app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricingItem> findByAppPricingId(long appPricingId)
		throws SystemException {
		return findByAppPricingId(appPricingId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app pricing items where appPricingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPricingId the app pricing ID
	 * @param start the lower bound of the range of app pricing items
	 * @param end the upper bound of the range of app pricing items (not inclusive)
	 * @return the range of matching app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricingItem> findByAppPricingId(long appPricingId,
		int start, int end) throws SystemException {
		return findByAppPricingId(appPricingId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app pricing items where appPricingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPricingId the app pricing ID
	 * @param start the lower bound of the range of app pricing items
	 * @param end the upper bound of the range of app pricing items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricingItem> findByAppPricingId(long appPricingId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPRICINGID;
			finderArgs = new Object[] { appPricingId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPPRICINGID;
			finderArgs = new Object[] {
					appPricingId,
					
					start, end, orderByComparator
				};
		}

		List<AppPricingItem> list = (List<AppPricingItem>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPricingItem appPricingItem : list) {
				if ((appPricingId != appPricingItem.getAppPricingId())) {
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

			query.append(_SQL_SELECT_APPPRICINGITEM_WHERE);

			query.append(_FINDER_COLUMN_APPPRICINGID_APPPRICINGID_2);

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

				qPos.add(appPricingId);

				list = (List<AppPricingItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app pricing item in the ordered set where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app pricing item
	 * @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem findByAppPricingId_First(long appPricingId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPricingItemException, SystemException {
		AppPricingItem appPricingItem = fetchByAppPricingId_First(appPricingId,
				orderByComparator);

		if (appPricingItem != null) {
			return appPricingItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPricingId=");
		msg.append(appPricingId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPricingItemException(msg.toString());
	}

	/**
	 * Returns the first app pricing item in the ordered set where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem fetchByAppPricingId_First(long appPricingId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppPricingItem> list = findByAppPricingId(appPricingId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app pricing item in the ordered set where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app pricing item
	 * @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem findByAppPricingId_Last(long appPricingId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPricingItemException, SystemException {
		AppPricingItem appPricingItem = fetchByAppPricingId_Last(appPricingId,
				orderByComparator);

		if (appPricingItem != null) {
			return appPricingItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPricingId=");
		msg.append(appPricingId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPricingItemException(msg.toString());
	}

	/**
	 * Returns the last app pricing item in the ordered set where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem fetchByAppPricingId_Last(long appPricingId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAppPricingId(appPricingId);

		List<AppPricingItem> list = findByAppPricingId(appPricingId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app pricing items before and after the current app pricing item in the ordered set where appPricingId = &#63;.
	 *
	 * @param appPricingItemId the primary key of the current app pricing item
	 * @param appPricingId the app pricing ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app pricing item
	 * @throws com.liferay.osb.NoSuchAppPricingItemException if a app pricing item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem[] findByAppPricingId_PrevAndNext(
		long appPricingItemId, long appPricingId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPricingItemException, SystemException {
		AppPricingItem appPricingItem = findByPrimaryKey(appPricingItemId);

		Session session = null;

		try {
			session = openSession();

			AppPricingItem[] array = new AppPricingItemImpl[3];

			array[0] = getByAppPricingId_PrevAndNext(session, appPricingItem,
					appPricingId, orderByComparator, true);

			array[1] = appPricingItem;

			array[2] = getByAppPricingId_PrevAndNext(session, appPricingItem,
					appPricingId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppPricingItem getByAppPricingId_PrevAndNext(Session session,
		AppPricingItem appPricingItem, long appPricingId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPRICINGITEM_WHERE);

		query.append(_FINDER_COLUMN_APPPRICINGID_APPPRICINGID_2);

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

		qPos.add(appPricingId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPricingItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPricingItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app pricing items where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @return the matching app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricingItem> findByAssetLicenseId(long assetLicenseId)
		throws SystemException {
		return findByAssetLicenseId(assetLicenseId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app pricing items where assetLicenseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetLicenseId the asset license ID
	 * @param start the lower bound of the range of app pricing items
	 * @param end the upper bound of the range of app pricing items (not inclusive)
	 * @return the range of matching app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricingItem> findByAssetLicenseId(long assetLicenseId,
		int start, int end) throws SystemException {
		return findByAssetLicenseId(assetLicenseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app pricing items where assetLicenseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetLicenseId the asset license ID
	 * @param start the lower bound of the range of app pricing items
	 * @param end the upper bound of the range of app pricing items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricingItem> findByAssetLicenseId(long assetLicenseId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLICENSEID;
			finderArgs = new Object[] { assetLicenseId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETLICENSEID;
			finderArgs = new Object[] {
					assetLicenseId,
					
					start, end, orderByComparator
				};
		}

		List<AppPricingItem> list = (List<AppPricingItem>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppPricingItem appPricingItem : list) {
				if ((assetLicenseId != appPricingItem.getAssetLicenseId())) {
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

			query.append(_SQL_SELECT_APPPRICINGITEM_WHERE);

			query.append(_FINDER_COLUMN_ASSETLICENSEID_ASSETLICENSEID_2);

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

				qPos.add(assetLicenseId);

				list = (List<AppPricingItem>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app pricing item in the ordered set where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app pricing item
	 * @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem findByAssetLicenseId_First(long assetLicenseId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPricingItemException, SystemException {
		AppPricingItem appPricingItem = fetchByAssetLicenseId_First(assetLicenseId,
				orderByComparator);

		if (appPricingItem != null) {
			return appPricingItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetLicenseId=");
		msg.append(assetLicenseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPricingItemException(msg.toString());
	}

	/**
	 * Returns the first app pricing item in the ordered set where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem fetchByAssetLicenseId_First(long assetLicenseId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppPricingItem> list = findByAssetLicenseId(assetLicenseId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app pricing item in the ordered set where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app pricing item
	 * @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem findByAssetLicenseId_Last(long assetLicenseId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPricingItemException, SystemException {
		AppPricingItem appPricingItem = fetchByAssetLicenseId_Last(assetLicenseId,
				orderByComparator);

		if (appPricingItem != null) {
			return appPricingItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetLicenseId=");
		msg.append(assetLicenseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppPricingItemException(msg.toString());
	}

	/**
	 * Returns the last app pricing item in the ordered set where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem fetchByAssetLicenseId_Last(long assetLicenseId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAssetLicenseId(assetLicenseId);

		List<AppPricingItem> list = findByAssetLicenseId(assetLicenseId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app pricing items before and after the current app pricing item in the ordered set where assetLicenseId = &#63;.
	 *
	 * @param appPricingItemId the primary key of the current app pricing item
	 * @param assetLicenseId the asset license ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app pricing item
	 * @throws com.liferay.osb.NoSuchAppPricingItemException if a app pricing item with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem[] findByAssetLicenseId_PrevAndNext(
		long appPricingItemId, long assetLicenseId,
		OrderByComparator orderByComparator)
		throws NoSuchAppPricingItemException, SystemException {
		AppPricingItem appPricingItem = findByPrimaryKey(appPricingItemId);

		Session session = null;

		try {
			session = openSession();

			AppPricingItem[] array = new AppPricingItemImpl[3];

			array[0] = getByAssetLicenseId_PrevAndNext(session, appPricingItem,
					assetLicenseId, orderByComparator, true);

			array[1] = appPricingItem;

			array[2] = getByAssetLicenseId_PrevAndNext(session, appPricingItem,
					assetLicenseId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppPricingItem getByAssetLicenseId_PrevAndNext(Session session,
		AppPricingItem appPricingItem, long assetLicenseId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPPRICINGITEM_WHERE);

		query.append(_FINDER_COLUMN_ASSETLICENSEID_ASSETLICENSEID_2);

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

		qPos.add(assetLicenseId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appPricingItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppPricingItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; or throws a {@link com.liferay.osb.NoSuchAppPricingItemException} if it could not be found.
	 *
	 * @param appPricingId the app pricing ID
	 * @param assetLicenseId the asset license ID
	 * @return the matching app pricing item
	 * @throws com.liferay.osb.NoSuchAppPricingItemException if a matching app pricing item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem findByAPI_ALI(long appPricingId, long assetLicenseId)
		throws NoSuchAppPricingItemException, SystemException {
		AppPricingItem appPricingItem = fetchByAPI_ALI(appPricingId,
				assetLicenseId);

		if (appPricingItem == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("appPricingId=");
			msg.append(appPricingId);

			msg.append(", assetLicenseId=");
			msg.append(assetLicenseId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAppPricingItemException(msg.toString());
		}

		return appPricingItem;
	}

	/**
	 * Returns the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param appPricingId the app pricing ID
	 * @param assetLicenseId the asset license ID
	 * @return the matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem fetchByAPI_ALI(long appPricingId, long assetLicenseId)
		throws SystemException {
		return fetchByAPI_ALI(appPricingId, assetLicenseId, true);
	}

	/**
	 * Returns the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param appPricingId the app pricing ID
	 * @param assetLicenseId the asset license ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching app pricing item, or <code>null</code> if a matching app pricing item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem fetchByAPI_ALI(long appPricingId,
		long assetLicenseId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { appPricingId, assetLicenseId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_API_ALI,
					finderArgs, this);
		}

		if (result instanceof AppPricingItem) {
			AppPricingItem appPricingItem = (AppPricingItem)result;

			if ((appPricingId != appPricingItem.getAppPricingId()) ||
					(assetLicenseId != appPricingItem.getAssetLicenseId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_APPPRICINGITEM_WHERE);

			query.append(_FINDER_COLUMN_API_ALI_APPPRICINGID_2);

			query.append(_FINDER_COLUMN_API_ALI_ASSETLICENSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appPricingId);

				qPos.add(assetLicenseId);

				List<AppPricingItem> list = q.list();

				result = list;

				AppPricingItem appPricingItem = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_API_ALI,
						finderArgs, list);
				}
				else {
					appPricingItem = list.get(0);

					cacheResult(appPricingItem);

					if ((appPricingItem.getAppPricingId() != appPricingId) ||
							(appPricingItem.getAssetLicenseId() != assetLicenseId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_API_ALI,
							finderArgs, appPricingItem);
					}
				}

				return appPricingItem;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_API_ALI,
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
				return (AppPricingItem)result;
			}
		}
	}

	/**
	 * Returns all the app pricing items.
	 *
	 * @return the app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricingItem> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app pricing items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app pricing items
	 * @param end the upper bound of the range of app pricing items (not inclusive)
	 * @return the range of app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricingItem> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the app pricing items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app pricing items
	 * @param end the upper bound of the range of app pricing items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppPricingItem> findAll(int start, int end,
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

		List<AppPricingItem> list = (List<AppPricingItem>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_APPPRICINGITEM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APPPRICINGITEM;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AppPricingItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AppPricingItem>)QueryUtil.list(q,
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
	 * Removes all the app pricing items where appPricingId = &#63; from the database.
	 *
	 * @param appPricingId the app pricing ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAppPricingId(long appPricingId)
		throws SystemException {
		for (AppPricingItem appPricingItem : findByAppPricingId(appPricingId)) {
			remove(appPricingItem);
		}
	}

	/**
	 * Removes all the app pricing items where assetLicenseId = &#63; from the database.
	 *
	 * @param assetLicenseId the asset license ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAssetLicenseId(long assetLicenseId)
		throws SystemException {
		for (AppPricingItem appPricingItem : findByAssetLicenseId(
				assetLicenseId)) {
			remove(appPricingItem);
		}
	}

	/**
	 * Removes the app pricing item where appPricingId = &#63; and assetLicenseId = &#63; from the database.
	 *
	 * @param appPricingId the app pricing ID
	 * @param assetLicenseId the asset license ID
	 * @return the app pricing item that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AppPricingItem removeByAPI_ALI(long appPricingId, long assetLicenseId)
		throws NoSuchAppPricingItemException, SystemException {
		AppPricingItem appPricingItem = findByAPI_ALI(appPricingId,
				assetLicenseId);

		return remove(appPricingItem);
	}

	/**
	 * Removes all the app pricing items from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AppPricingItem appPricingItem : findAll()) {
			remove(appPricingItem);
		}
	}

	/**
	 * Returns the number of app pricing items where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @return the number of matching app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAppPricingId(long appPricingId) throws SystemException {
		Object[] finderArgs = new Object[] { appPricingId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPPRICINGID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPPRICINGITEM_WHERE);

			query.append(_FINDER_COLUMN_APPPRICINGID_APPPRICINGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appPricingId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_APPPRICINGID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app pricing items where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @return the number of matching app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAssetLicenseId(long assetLicenseId)
		throws SystemException {
		Object[] finderArgs = new Object[] { assetLicenseId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSETLICENSEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPPRICINGITEM_WHERE);

			query.append(_FINDER_COLUMN_ASSETLICENSEID_ASSETLICENSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetLicenseId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ASSETLICENSEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app pricing items where appPricingId = &#63; and assetLicenseId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @param assetLicenseId the asset license ID
	 * @return the number of matching app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAPI_ALI(long appPricingId, long assetLicenseId)
		throws SystemException {
		Object[] finderArgs = new Object[] { appPricingId, assetLicenseId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_API_ALI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPPRICINGITEM_WHERE);

			query.append(_FINDER_COLUMN_API_ALI_APPPRICINGID_2);

			query.append(_FINDER_COLUMN_API_ALI_ASSETLICENSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appPricingId);

				qPos.add(assetLicenseId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_API_ALI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app pricing items.
	 *
	 * @return the number of app pricing items
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APPPRICINGITEM);

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
	 * Initializes the app pricing item persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AppPricingItem")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AppPricingItem>> listenersList = new ArrayList<ModelListener<AppPricingItem>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AppPricingItem>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AppPricingItemImpl.class.getName());
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
	private static final String _SQL_SELECT_APPPRICINGITEM = "SELECT appPricingItem FROM AppPricingItem appPricingItem";
	private static final String _SQL_SELECT_APPPRICINGITEM_WHERE = "SELECT appPricingItem FROM AppPricingItem appPricingItem WHERE ";
	private static final String _SQL_COUNT_APPPRICINGITEM = "SELECT COUNT(appPricingItem) FROM AppPricingItem appPricingItem";
	private static final String _SQL_COUNT_APPPRICINGITEM_WHERE = "SELECT COUNT(appPricingItem) FROM AppPricingItem appPricingItem WHERE ";
	private static final String _FINDER_COLUMN_APPPRICINGID_APPPRICINGID_2 = "appPricingItem.appPricingId = ?";
	private static final String _FINDER_COLUMN_ASSETLICENSEID_ASSETLICENSEID_2 = "appPricingItem.assetLicenseId = ?";
	private static final String _FINDER_COLUMN_API_ALI_APPPRICINGID_2 = "appPricingItem.appPricingId = ? AND ";
	private static final String _FINDER_COLUMN_API_ALI_ASSETLICENSEID_2 = "appPricingItem.assetLicenseId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "appPricingItem.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AppPricingItem exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AppPricingItem exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AppPricingItemPersistenceImpl.class);
	private static AppPricingItem _nullAppPricingItem = new AppPricingItemImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AppPricingItem> toCacheModel() {
				return _nullAppPricingItemCacheModel;
			}
		};

	private static CacheModel<AppPricingItem> _nullAppPricingItemCacheModel = new CacheModel<AppPricingItem>() {
			public AppPricingItem toEntityModel() {
				return _nullAppPricingItem;
			}
		};
}